package br.com.mentoring.route.generator.domain.fsm;

import br.com.mentoring.route.generator.domain.entity.RouteStatus;

public class FinalStateReachedException extends RuntimeException {
    public FinalStateReachedException(RouteStatus routeStatus) {
        super("State %s is final".formatted(routeStatus));
    }
}
