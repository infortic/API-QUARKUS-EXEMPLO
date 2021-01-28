package br.coop.cresol.bootstrap;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class Aplicacao {

    private static final String CRESOL_SYMBOL = "cresol_symbol.txt";

    private static final Logger LOG = LoggerFactory.getLogger(Aplicacao.class);

    void onStart(@Observes StartupEvent event) {
        this.logarSimbolo();
        LOG.info("Aplicacao sendo inicializada...");
    }

    void onStop(@Observes ShutdownEvent event) {
        LOG.info("Aplicacao sendo encerrada...");
    }

    private void logarSimbolo() {
        try(Stream<String> stream = Files.lines(Paths.get(getClass().getClassLoader().getResource(CRESOL_SYMBOL).toURI()))) {
            stream.forEach(LOG::info);
        } catch (Exception e) {/** NOOP */}
    }
}