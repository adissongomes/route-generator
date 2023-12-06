package br.com.mentoring.route.generator.domain;

import br.com.mentoring.route.generator.domain.dto.RouteDTO;

public interface RouteSender {
    void send(RouteDTO routeDTO);
}
