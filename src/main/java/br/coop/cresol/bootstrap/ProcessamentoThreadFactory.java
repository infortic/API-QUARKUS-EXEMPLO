package br.coop.cresol.bootstrap;

import static java.util.Objects.isNull;
import static java.text.MessageFormat.format;
import static java.lang.Thread.NORM_PRIORITY;
import static java.lang.Thread.currentThread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Singleton responsável por fabricar threads para execução do sistema.
 *
 * @author Theodoro Mattoso
 */
public enum ProcessamentoThreadFactory implements ThreadFactory {

    THREAD_FACTORY;

    private String nome = "treinamento-ms-cleiton";
    private String nomePattern;

    private final ThreadGroup group;
    private final AtomicInteger nroThread = new AtomicInteger(1);

    ProcessamentoThreadFactory() {
        final SecurityManager secManager = System.getSecurityManager();

        this.nomePattern = "{0}-thread-{1}";
        this.group = (!isNull(secManager)) ? secManager.getThreadGroup() : currentThread().getThreadGroup();
    }

    @Override
    public Thread newThread(final Runnable runnable) {
        final String nomeParent = currentThread().getName();
        final String threadName = format(this.nomePattern, this.nome, this.nroThread.getAndIncrement(), nomeParent);

        final Thread thread = new Thread(this.group, runnable, threadName, 0);

        if (thread.isDaemon()) {
            thread.setDaemon(false);
        }

        if (thread.getPriority() != NORM_PRIORITY) {
            thread.setPriority(NORM_PRIORITY);
        }

        return thread;
    }

}