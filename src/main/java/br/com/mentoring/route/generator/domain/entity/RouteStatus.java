package br.com.mentoring.route.generator.domain.entity;

import br.com.mentoring.route.generator.domain.fsm.*;

public enum RouteStatus {
    CREATED(new CreatedState()),
    WAITING_COURIER(new WaitingCourierState()),
    ACCEPTED(new AcceptedState()),
    STARTED(new StartedState()),
    COMPLETED(new CompletedState()),
    CANCELED(new CanceledState());

    private RouteState state;

    RouteStatus(RouteState state) {
        this.state = state;
    }

    public RouteState getState() {
        return state;
    }
}
