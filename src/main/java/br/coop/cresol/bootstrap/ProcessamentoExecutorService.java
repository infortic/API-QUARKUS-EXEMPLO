package br.coop.cresol.bootstrap;

import static br.coop.cresol.bootstrap.ProcessamentoThreadFactory.THREAD_FACTORY;
import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.String.format;
import static java.util.Objects.requireNonNull;
import static java.util.concurrent.Executors.newFixedThreadPool;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Executor de threads do sistema, garante a orquestração das requisições assíncronas. Foi incluido o uso do {@link CompletionService} para
 * os principais cenários de uso, pois ele é mais performático.
 *
 * @author Theodoro Mattoso
 *
 * @see {@link ProcessamentoThreadFactory}
 */
public class ProcessamentoExecutorService implements ExecutorService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProcessamentoExecutorService.class);

	private final String nome;
	private final Integer nroThreads;
	private final ExecutorService executorService;
	@SuppressWarnings("rawtypes")
	private CompletionService completionService;

	private ProcessamentoExecutorService(final String nome, final Integer qtdThreads) {
		this.nome = nome;
		this.nroThreads = qtdThreads;

		this.executorService = newFixedThreadPool(qtdThreads, THREAD_FACTORY);
		this.completionService = new ExecutorCompletionService<>(this.executorService);
	}

	/**
	 * Factory method que ira se encarregar de construir um novo executor para o sistema.
	 *
	 * @param nome
	 *            {@link String} Nome do executor
	 *
	 * @param qtdThreads
	 *            {@link Integer} numero de threads que o pool de execucao ira controlar.
	 *
	 * @throws NullPointerException
	 *             caso algum dos parametros esteja nulo.
	 * @throws IllegalArgumentException
	 *             caso algum dos parametros seja invalido.
	 */
	public static ProcessamentoExecutorService of(final String nome, final Integer qtdThreads) {
		requireNonNull(nome, "Toda thread deve ter um nome associado.");
		requireNonNull(qtdThreads, "A aplicação deve receber uma quantidade de threads.");

		checkArgument(!nome.isEmpty(), "Toda thread deve ter um nome associado.");
		checkArgument(qtdThreads > 0, "A aplicação deve receber uma quantidade de threads.");

		LOGGER.info(format("Executor de threads do sistema iniciado com %d threads", qtdThreads));

		return new ProcessamentoExecutorService(nome, qtdThreads);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void execute(Runnable command) {
		completionService.submit(command, null);
	}

	@Override
	public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
		return this.executorService.awaitTermination(timeout, unit);
	}

	/**
	 * Código copiado e adaptado do método invokeAll da classe {@link AbstractExecutorService} para realizar o submit das tasks utilizando o
	 * CompletionService.
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
		if (tasks == null)
			throw new NullPointerException();
		ArrayList<Future<T>> futures = new ArrayList<Future<T>>(tasks.size());
		boolean done = false;
		try {
			for (Callable<T> task : tasks) {
				futures.add(this.completionService.submit(task));
			}
			for (int i = 0, size = futures.size(); i < size; i++) {
				Future<T> f = futures.get(i);
				if (!f.isDone()) {
					try {
						f.get();
					} catch (CancellationException ignore) {
					} catch (ExecutionException ignore) {
					}
				}
			}
			done = true;
			return futures;
		} finally {
			if (!done)
				for (int i = 0, size = futures.size(); i < size; i++)
					futures.get(i).cancel(true);
		}
	}

	/**
	 * Se for necessário utilizar/respeitar o timeout, é possível implementar este método baseado no invokeAll do
	 * {@link AbstractExecutorService} e adaptar para usar o CompletionService para dar submit nas tasks. Foi decidido não realizar esta
	 * implementação sem que houvesse de fato a necessidade.
	 *
	 */
	@Override
	public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
		return this.invokeAll(tasks);
	}

	/**
	 * Usando o ExecutorService teremos a funcionalidade operando, porém usando um recurso mais antigo do Java. Também notamos que este
	 * método já usa o mecanismo CompletionService internamente para sua execução, então salvo alguma necessidade específica não é
	 * necessário alterar esta implementação.
	 */
	@Override
	public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
		return this.executorService.invokeAny(tasks);
	}

	/**
	 * Usando o ExecutorService teremos a funcionalidade operando, porém usando um recurso mais antigo do Java. Também notamos que este
	 * método já usa o mecanismo CompletionService internamente para sua execução, então salvo alguma necessidade específica não é
	 * necessário alterar esta implementação.
	 */
	@Override
	public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
			throws InterruptedException, ExecutionException, TimeoutException {
		return executorService.invokeAny(tasks, timeout, unit);
	}

	@Override
	public boolean isShutdown() {
		return this.executorService.isShutdown();
	}

	@Override
	public boolean isTerminated() {
		return this.executorService.isTerminated();
	}

	@Override
	public void shutdown() {
		this.completionService = null;
		this.executorService.shutdown();
	}

	@Override
	public List<Runnable> shutdownNow() {
		this.completionService = null;
		return this.executorService.shutdownNow();
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> Future<T> submit(Callable<T> task) {
		return this.completionService.submit(task);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Future<?> submit(Runnable task) {
		return this.completionService.submit(task, null);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> Future<T> submit(Runnable task, T result) {
		return this.completionService.submit(task, result);
	}

	public String getName() {
		return this.nome;
	}

	public Integer getNroThreads() {
		return this.nroThreads;
	}

	/**
	 * Retorna quantas threads ainda estao disponíveis no pool.
	 *
	 * @return {@link Integer}
	 */
	public Integer nroThreadsDisponiveis() {
		ThreadPoolExecutor pool = ThreadPoolExecutor.class.cast(executorService);
		return pool.getCorePoolSize() - pool.getActiveCount();
	}

}