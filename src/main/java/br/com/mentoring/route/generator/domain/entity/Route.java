package br.com.mentoring.route.generator.domain.entity;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class Route {
    private final UUID id;
    private final UUID originId;
    private final UUID destinationId;
    private final UUID courierId;
    private RouteStatus status;
    private Instant eventTime;

    private Route(UUID id,
                  UUID originId,
                  UUID destinationId,
                  UUID courierId,
                  RouteStatus status,
                  Instant eventTime) {

        this.id = id;
        this.originId = originId;
        this.destinationId = destinationId;
        this.courierId = courierId;
        this.status = status;
        this.eventTime = eventTime;
    }

    public static Route newRandom() {
        return new Route(
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID(),
                RouteStatus.CREATED,
                Instant.now()
        );
    }

    public UUID getId() {
        return id;
    }

    public UUID getOriginId() {
        return originId;
    }

    public UUID getDestinationId() {
        return destinationId;
    }

    public UUID getCourierId() {
        return courierId;
    }

    public RouteStatus getStatus() {
        return status;
    }

    public Instant getEventTime() {
        return eventTime;
    }

    public void changeStatus() {
        this.status = this.status.getState().next();
        this.eventTime = Instant.now();
    }

    public void rollbackStatus() {
        this.status = this.status.getState().previous();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Objects.equals(id, route.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", status=" + status +
                ", eventTime=" + eventTime +
                '}';
    }
}
