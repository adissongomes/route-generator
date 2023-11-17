package br.com.mentoring.route.generator.domain.fsm;

import br.com.mentoring.route.generator.domain.entity.RouteStatus;

public class CompletedState implements RouteState {
    @Override
    public RouteStatus next() {
        throw new FinalStateReachedException(RouteStatus.COMPLETED);
    }

    @Override
    public RouteStatus previous() {
        return RouteStatus.STARTED;
    }
}
