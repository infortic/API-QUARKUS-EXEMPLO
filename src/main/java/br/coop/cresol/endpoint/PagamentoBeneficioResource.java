package br.coop.cresol.endpoint;

import br.coop.cresol.dto.PagamentoBeneficioBuscarDTO;
import br.coop.cresol.service.PagamentoBeneficioService;
import br.coop.cresol.ultil.Util;
import br.coop.cresol.vo.PagamentoBeneficioVO;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.List;

@Tag(name = "Pagamento Beneficio")
@Path("/pagamento-beneficio")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class PagamentoBeneficioResource {

	@Inject
	PagamentoBeneficioService pagamentoBeneficioService;
	
	@Inject
	Util util;
	
	@GET
	@Operation(summary = "BuscaPaginada", description = "Utiliza o serviço injetado para realizar busca paginada contendo 20 itens por vez.")
	@Path("/numeroPagina/{numeroPagina}")
	public List<PagamentoBeneficioBuscarDTO> buscaPaginada(
			@DefaultValue("1") @QueryParam("numeroPagina") Integer numeroPagina, 
			@QueryParam("numeroBeneficio") Long numeroBeneficio, 
			@QueryParam("codigoPac") Long codigoPac, 
			@QueryParam("digitoVerificadorContaCorrente") Long digitoVerificadorContaCorrente,
			@QueryParam("codigoCooperativa") Long codigoCooperativa,
			@QueryParam("numeroContaCorrente") Long numeroContaCorrente,
			@QueryParam("valorBeneficio") Long valorBeneficio
			) {
		this.util.numeroDepaginaNegativa(numeroPagina);
		return this.pagamentoBeneficioService.buscaPagamentoBeneficio(numeroPagina, numeroBeneficio, codigoCooperativa, codigoPac, digitoVerificadorContaCorrente, numeroContaCorrente, valorBeneficio);
	}

//	@POST
//	@Produces(APPLICATION_JSON)
//	@Transactional
//	public void incluirPagamentoBeneficio(PagamentoBeneficioVO pagamentoBeneficio) {
//		this.pagamentoBeneficioService.salvarPagamentoBeneficio(pagamentoBeneficio);
//	}
//	
//	@PUT
//	@Produces(APPLICATION_JSON)
//	@Transactional
//	public void atualizarPagamentoBeneficio(PagamentoBeneficioVO pagamentoBeneficio) {
//		this.pagamentoBeneficioService.atualizarPagamentoBeneficio(pagamentoBeneficio);
//	}
//	
//	@DELETE
//	@Produces(APPLICATION_JSON)
//	@Transactional
//	public void removerPagamentoBeneficio(PagamentoBeneficioVO pagamentoBeneficio) {
//		this.pagamentoBeneficioService.removerPagamentoBeneficio(pagamentoBeneficio);
//	}
}