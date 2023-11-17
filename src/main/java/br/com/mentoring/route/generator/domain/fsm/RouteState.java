package br.com.mentoring.route.generator.domain.fsm;

import br.com.mentoring.route.generator.domain.entity.RouteStatus;

public interface RouteState {
    RouteStatus next();

    RouteStatus previous();
}
