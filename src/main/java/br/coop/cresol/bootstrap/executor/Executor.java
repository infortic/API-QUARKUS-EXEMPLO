package br.coop.cresol.bootstrap.executor;

import static br.coop.cresol.bootstrap.Constantes.PROCESSAMENTO;

import java.util.function.Supplier;

import br.coop.cresol.bootstrap.ProcessamentoExecutorService;

/**
 * Singleton supplier que fornece a implementacao do servico de executor de threads.
 * @author Theodoro Mattoso
 */
public enum Executor implements Supplier<ProcessamentoExecutorService> {

    EXECUTOR;

    private static final ProcessamentoExecutorService service = ProcessamentoExecutorService.of(PROCESSAMENTO, 10);

    @Override
    public ProcessamentoExecutorService get() {
        return service;
    }

}