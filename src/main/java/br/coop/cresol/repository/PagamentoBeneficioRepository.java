package br.coop.cresol.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;

import br.coop.cresol.dto.PagamentoBeneficioBuscarDTO;
import br.coop.cresol.vo.PagamentoBeneficioVO;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Parameters;

@ApplicationScoped
public class PagamentoBeneficioRepository implements PanacheRepository<PagamentoBeneficioVO> {

	
	
	public List<PagamentoBeneficioBuscarDTO> buscaPagamentoBeneficio(Integer numeroPagina, Long numeroBeneficio, Long codigoCooperativa,
			Long codigoPac, Long digitoVerificadorContaCorrente, Long numeroContaCorrente, Long valorBeneficio) {

		Map<String, Object> params = new HashMap<>();

		StringBuilder query = new StringBuilder();

		PanacheQuery<PagamentoBeneficioBuscarDTO> pagamentoBeneficioQuary = null;

		this.filtrar(numeroBeneficio, codigoCooperativa, codigoPac, digitoVerificadorContaCorrente, numeroContaCorrente, valorBeneficio,
				params, query);

		if (query.equals("")) {

			PanacheQuery<PagamentoBeneficioVO>	pagamentoBeneficio =  this.findAll();

		} else {

			PanacheQuery<PagamentoBeneficioVO>	pagamentoBeneficio = this.find(query.toString(), params);
			
		}
		
		return pagamentoBeneficioQuary.page(Page.of(numeroPagina-1, 20)).list();

	}

	public void filtrar(Long numeroBeneficio, Long codigoCooperativa, Long codigoPac, Long digitoVerificadorContaCorrente,
			Long numeroContaCorrente, Long valorBeneficio, Map<String, Object> params, StringBuilder query) {
		
		if (numeroBeneficio != null) {
			params.put("numeroBeneficio", numeroBeneficio);
			query.append( " numeroBeneficio = :numeroBeneficio ");
		}

		if (codigoCooperativa != null) {
			params.put("codigoCooperativa", codigoCooperativa);
			query.append( " codigoCooperativa = :codigoCooperativa  ");
		}

		if (codigoPac != null) {
			params.put("codigoPac", codigoPac);
			query.append( "  codigoPac = :codigoPac and ");
		}

		if (digitoVerificadorContaCorrente != null) {
			params.put("digitoVerificadorContaCorrente", digitoVerificadorContaCorrente);
			query.append( "  digitoVerificadorContaCorrente = :digitoVerificadorContaCorrente and ");
		}

		if (numeroContaCorrente != null) {
			params.put("numeroContaCorrente", numeroContaCorrente);
			query.append( "  numeroContaCorrente = :numeroContaCorrente and ");
		}

		if (valorBeneficio != null) {
			params.put("valorBeneficio", valorBeneficio);
			query.append( "  valorBeneficio = :valorBeneficio and ");
		}
	}

//	public void salvarPagamentoBeneficio(PagamentoBeneficioVO pagamentoBeneficioVO) {
//		this.persist(pagamentoBeneficioVO);
//	}
//
//	public void alterarPagamentoBeneficio(PagamentoBeneficioVO pagamentoBeneficioVO) {
//		this.persist(pagamentoBeneficioVO);
//	}
//
//	public void removerPagamentoBeneficio(PagamentoBeneficioVO pagamentoBeneficioVO) {
//		this.delete(pagamentoBeneficioVO);
//	}

}
