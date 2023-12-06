package br.com.mentoring.route.generator.infra.kafka;

import br.com.mentoring.route.generator.domain.RouteSender;
import br.com.mentoring.route.generator.domain.dto.RouteDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class RouteSenderImpl implements RouteSender {

    private final KafkaTemplate<String, RouteDTO> kafkaTemplate;
    private final String topicName;

    public RouteSenderImpl(KafkaTemplate<String, RouteDTO> kafkaTemplate,
                           @Value("${kafka.topic.route}") String topicName) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicName = topicName;
    }

    @Override
    public void send(RouteDTO routeDTO) {
        kafkaTemplate.send(topicName, routeDTO.id().toString(), routeDTO);
        kafkaTemplate.flush();
    }
}
