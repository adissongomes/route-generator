package br.com.mentoring.route.generator.domain;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class GeneratorService implements ApplicationRunner {

    private static final int NUMBER_THREADS = 3;

    private final ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_THREADS);

    public void generate() {
        for (int i = 0; i < NUMBER_THREADS; i++) {
            executorService.submit(new RouteRunnable());
        }

        Runtime.getRuntime().addShutdownHook(new Thread(executorService::shutdownNow));
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        generate();
    }
}
