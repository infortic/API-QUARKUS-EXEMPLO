package br.coop.cresol.endpoint;

import org.junit.jupiter.api.Test;

import br.coop.cresol.dto.PagamentoBeneficioBuscarDTO;
import br.coop.cresol.service.PagamentoBeneficioService;
import br.coop.cresol.vo.PagamentoBeneficioVO;
import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import java.util.List;

import javax.inject.Inject;

@QuarkusTest
public class PagamentoBeneficioResourceTest {

	PagamentoBeneficioBuscarDTO pagamentoBeneficioDTO = new PagamentoBeneficioBuscarDTO(1088L, 235L, 2564L, 22354L, 2154L, 0252L);
	
	@Inject
	PagamentoBeneficioService pagamentoBeneficioService; 
	
    @Test
    public void PagamentoBeneficioEndPointPesquisarTest() {
        given()
          .when().get("/pagamento-beneficio")
          .then().statusCode(200);
    }
    
//    @Test
//    public void PagamentoBeneficioServicoIcluirTest() {
//    	pagamentoBeneficioService.salvarPagamentoBeneficio(pagamentoBeneficioDTO);	    	
//    }
//    
    
//    @Test
//    public void PagamentoBeneficioServicoRemoverTest() {
//    	pagamentoBeneficioService.removerPagamentoBeneficio(pagamentoBeneficioDTO);
//    }
//    
//    
//    @Test
//    public void PagamentoBeneficioServicoAtualizarTest() {
//    	pagamentoBeneficioDTO.setNumeroContaCorrente(23232L);
//    	pagamentoBeneficioService.atualizarPagamentoBeneficio(pagamentoBeneficioDTO);
//    }

}