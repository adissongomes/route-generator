package br.com.mentoring.route.generator.domain;

import br.com.mentoring.route.generator.domain.dto.RouteDTO;
import br.com.mentoring.route.generator.domain.entity.Route;
import br.com.mentoring.route.generator.domain.entity.RouteStatus;
import br.com.mentoring.route.generator.domain.fsm.FinalStateReachedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.random.RandomGenerator;

public class RouteRunnable implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(RouteRunnable.class);

    private final ThreadLocal<Route> routeThreadLocal = new ThreadLocal<>();
    private final RandomGenerator randomGenerator = RandomGenerator.getDefault();

    private final int maxRoutesToSimulate;

    private final boolean shouldSleep;
    private final RouteSender routeSender;
    private final TransactionTemplate transactionTemplate;

    private int completedRoutes = 0;

    public RouteRunnable(RouteSender routeSender, TransactionTemplate transactionTemplate) {
        this(Integer.MAX_VALUE, true, routeSender, transactionTemplate);
    }

    public RouteRunnable(int maxRoutesToSimulate, boolean shouldSleep, RouteSender routeSender, TransactionTemplate transactionTemplate) {
        this.maxRoutesToSimulate = maxRoutesToSimulate;
        this.shouldSleep = shouldSleep;
        this.routeSender = routeSender;
        this.transactionTemplate = transactionTemplate;
    }

    @Override
    public void run() {
        while (completedRoutes < maxRoutesToSimulate) {
            if (shouldSleep) {
                sleep();
            }
            simulate();
        }
    }

    private void sleep() {
        try {
            long millis = randomGenerator.nextLong(10) * 1000;
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalThreadStateException("Thread was interrupted");
        }
    }

    private void simulate() {
        transactionTemplate.executeWithoutResult(action -> {
            if (routeThreadLocal.get() == null) {
                routeThreadLocal.set(Route.newRandom());
            }
            var route = routeThreadLocal.get();
            int randomValue = randomGenerator.nextInt(25);

            if (randomValue < 5) {
                rollback(route);
            } else {
                change(route);
            }
        });
    }

    private void change(Route route) {
        try {
            route.changeStatus();
            routeSender.send(RouteDTO.fromRoute(route));
            if (route.getStatus() == RouteStatus.COMPLETED || route.getStatus() == RouteStatus.CANCELED) {
                purgeRoute();
            }
            logger.info("Route {} status = {}", route.getId(), route.getStatus());
        } catch (FinalStateReachedException e) {
            purgeRoute();
        }
    }

    private void rollback(Route route) {
        try {
            route.rollbackStatus();
            logger.info("Route {} status = {}", route.getId(), route.getStatus());
        } catch (IllegalStateException e) {
            logger.warn("Route {} rollback failed: {}", route.getId(), e.getMessage());
        }
    }

    private void purgeRoute() {
        logger.info("Route {} completed", routeThreadLocal.get().getId());
        routeThreadLocal.remove();
        completedRoutes++;
    }
}
