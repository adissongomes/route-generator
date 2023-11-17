package br.com.mentoring.route.generator.domain.fsm;

import br.com.mentoring.route.generator.domain.entity.RouteStatus;

public class StartedState implements RouteState {
    @Override
    public RouteStatus next() {
        return RouteStatus.COMPLETED;
    }

    @Override
    public RouteStatus previous() {
        return RouteStatus.ACCEPTED;
    }
}
