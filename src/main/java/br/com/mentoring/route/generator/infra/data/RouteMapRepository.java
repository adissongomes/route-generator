package br.com.mentoring.route.generator.infra.data;

import br.com.mentoring.route.generator.domain.RouteRepository;
import br.com.mentoring.route.generator.domain.entity.Route;
import br.com.mentoring.route.generator.domain.entity.RouteStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class RouteMapRepository implements RouteRepository {
    private Map<UUID, Route> data = new ConcurrentHashMap<>();

    @Override
    public void save(Route route) {
        data.put(route.getId(), route);
    }

    @Override
    public List<Route> findAll() {
        return data.values().stream().toList();
    }

    @Override
    public List<Route> findByStatus(RouteStatus status) {
        return data.values().stream().filter(r -> r.getStatus() == status).toList();
    }
}
