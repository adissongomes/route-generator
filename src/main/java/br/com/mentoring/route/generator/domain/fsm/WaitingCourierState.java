package br.com.mentoring.route.generator.domain.fsm;

import br.com.mentoring.route.generator.domain.entity.RouteStatus;

public class WaitingCourierState implements RouteState {
    @Override
    public RouteStatus next() {
        return RouteStatus.ACCEPTED;
    }

    @Override
    public RouteStatus previous() {
        return RouteStatus.CREATED;
    }
}
