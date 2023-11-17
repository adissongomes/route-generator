package br.com.mentoring.route.generator.domain.dto;

import br.com.mentoring.route.generator.domain.entity.Route;
import br.com.mentoring.route.generator.domain.entity.RouteStatus;

import java.time.Instant;
import java.util.UUID;

public record RouteDTO(
        UUID id,
        UUID originId,
        UUID destinationId,
        UUID courierId,
        RouteStatus status,
        Instant eventTime) {

    public static RouteDTO fromRoute(Route route) {
        return new RouteDTO(
                route.getId(),
                route.getOriginId(),
                route.getDestinationId(),
                route.getCourierId(),
                route.getStatus(),
                route.getEventTime()
        );
    }
}
