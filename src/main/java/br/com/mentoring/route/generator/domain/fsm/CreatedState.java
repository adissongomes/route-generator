package br.com.mentoring.route.generator.domain.fsm;

import br.com.mentoring.route.generator.domain.entity.RouteStatus;

public class CreatedState implements RouteState {
    @Override
    public RouteStatus next() {
        return RouteStatus.WAITING_COURIER;
    }

    @Override
    public RouteStatus previous() {
        throw new IllegalStateException("There is no previous status for " + RouteStatus.CREATED);
    }
}
