package br.coop.cresol.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.coop.cresol.dto.PagamentoBeneficioBuscarDTO;
import br.coop.cresol.repository.PagamentoBeneficioRepository;
import br.coop.cresol.vo.PagamentoBeneficioVO;


@ApplicationScoped
public class PagamentoBeneficioService {

	@Inject
	PagamentoBeneficioRepository pagamentoBeneficioRepository; 
	
    public List<PagamentoBeneficioBuscarDTO> buscaPagamentoBeneficio(int numeroPagina, Long numeroBeneficio, 
			Long codigoCooperativa, Long codigoPac, Long digitoVerificadorContaCorrente, Long numeroContaCorrente, Long valorBeneficio) {
        return this.pagamentoBeneficioRepository.buscaPagamentoBeneficio(numeroPagina, numeroBeneficio, codigoCooperativa, codigoPac, digitoVerificadorContaCorrente, numeroContaCorrente, valorBeneficio);
    }

//    public void salvarPagamentoBeneficio(PagamentoBeneficioVO pagamentoBeneficioVO) {
//    	pagamentoBeneficioRepository.salvarPagamentoBeneficio(pagamentoBeneficioVO);
//    }
//    
//    public void atualizarPagamentoBeneficio(PagamentoBeneficioVO pagamentoBeneficioVO) {
//    	pagamentoBeneficioRepository.salvarPagamentoBeneficio(pagamentoBeneficioVO);
//    }
//    
//    public void removerPagamentoBeneficio (PagamentoBeneficioVO pagamentoBeneficioVO) {
//    	pagamentoBeneficioRepository.removerPagamentoBeneficio(pagamentoBeneficioVO);
//    }
}