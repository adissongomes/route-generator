package br.com.mentoring.route.generator.domain;

import br.com.mentoring.route.generator.domain.entity.Route;
import br.com.mentoring.route.generator.domain.entity.RouteStatus;

import java.util.List;

public interface RouteRepository {
    void save(Route route);
    List<Route> findAll();
    List<Route> findByStatus(RouteStatus status);
}
