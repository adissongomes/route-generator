package br.com.mentoring.route.generator.domain;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class GeneratorService implements ApplicationRunner {

    private static final int NUMBER_THREADS = 3;

    private final ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_THREADS);

    private final RouteSender routeSender;
    private final TransactionTemplate transactionTemplate;

    public GeneratorService(RouteSender routeSender, TransactionTemplate transactionTemplate) {
        this.routeSender = routeSender;
        this.transactionTemplate = transactionTemplate;
    }

    public void generate() {
        for (int i = 0; i < NUMBER_THREADS; i++) {
            executorService.submit(new RouteRunnable(routeSender, transactionTemplate));
        }

        Runtime.getRuntime().addShutdownHook(new Thread(executorService::shutdownNow));
    }

    @Override
    public void run(ApplicationArguments args) {
        generate();
    }
}
