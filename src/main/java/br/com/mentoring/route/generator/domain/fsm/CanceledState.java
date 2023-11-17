package br.com.mentoring.route.generator.domain.fsm;

import br.com.mentoring.route.generator.domain.entity.RouteStatus;

public class CanceledState implements RouteState {
    @Override
    public RouteStatus next() {
        throw new FinalStateReachedException(RouteStatus.CANCELED);
    }

    @Override
    public RouteStatus previous() {
        throw new FinalStateReachedException(RouteStatus.CANCELED);
    }
}
