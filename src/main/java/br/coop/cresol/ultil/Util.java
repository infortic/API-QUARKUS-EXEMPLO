package br.coop.cresol.ultil;

import javax.enterprise.context.ApplicationScoped;

import br.coop.cresol.bootstrap.Constantes;

@ApplicationScoped
public class Util {

	public void numeroDepaginaNegativa(Integer numeroPagina) {
		if(numeroPagina < 1) {
			new  Exception(Constantes.ERRO_NUMERO_PAGINA_NEGATIVO);
		}
	}
	
}
