package br.com.mentoring.route.generator.domain.fsm;

import br.com.mentoring.route.generator.domain.entity.RouteStatus;

public class AcceptedState implements RouteState {
    @Override
    public RouteStatus next() {
        return RouteStatus.STARTED;
    }

    @Override
    public RouteStatus previous() {
        return RouteStatus.WAITING_COURIER;
    }
}
