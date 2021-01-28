package br.coop.cresol.dto;


public class PagamentoBeneficioAtualizarDTO {
	
    	private Long numeroBeneficio;
    	private Long codigoCooperativa;
    	private Long codigoPac;
    	private Long digitoVerificadorContaCorrente;
    	private Long numeroContaCorrente;
    	private Long valorBeneficio;
		
		public Long getNumeroBeneficio() {
			return numeroBeneficio;
		}
		
		public void setNumeroBeneficio(Long numeroBeneficio) {
			this.numeroBeneficio = numeroBeneficio;
		}
		
		public Long getCodigoCooperativa() {
			return codigoCooperativa;
		}
		
		public void setCodigoCooperativa(Long codigoCooperativa) {
			this.codigoCooperativa = codigoCooperativa;
		}
		
		public Long getCodigoPac() {
			return codigoPac;
		}
		
		public void setCodigoPac(Long codigoPac) {
			this.codigoPac = codigoPac;
		}
		
		public Long getDigitoVerificadorContaCorrente() {
			return digitoVerificadorContaCorrente;
		}
		
		public void setDigitoVerificadorContaCorrente(Long digitoVerificadorContaCorrente) {
			this.digitoVerificadorContaCorrente = digitoVerificadorContaCorrente;
		}
		
		public Long getNumeroContaCorrente() {
			return numeroContaCorrente;
		}
		
		public void setNumeroContaCorrente(Long numeroContaCorrente) {
			this.numeroContaCorrente = numeroContaCorrente;
		}
		
		public Long getValorBeneficio() {
			return valorBeneficio;
		}
		
		public void setValorBeneficio(Long valorBeneficio) {
			this.valorBeneficio = valorBeneficio;
		}

		public PagamentoBeneficioAtualizarDTO(Long numeroBeneficio, Long codigoCooperativa, Long codigoPac, Long digitoVerificadorContaCorrente,
				Long numeroContaCorrente, Long valorBeneficio) {
			super();
			this.numeroBeneficio = numeroBeneficio;
			this.codigoCooperativa = codigoCooperativa;
			this.codigoPac = codigoPac;
			this.digitoVerificadorContaCorrente = digitoVerificadorContaCorrente;
			this.numeroContaCorrente = numeroContaCorrente;
			this.valorBeneficio = valorBeneficio;
		}

		@Override
		public String toString() {
			return "PagamentoBeneficioDTO [numeroBeneficio=" + numeroBeneficio + ", codigoCooperativa=" + codigoCooperativa + ", codigoPac="
					+ codigoPac + ", digitoVerificadorContaCorrente=" + digitoVerificadorContaCorrente + ", numeroContaCorrente="
					+ numeroContaCorrente + ", valorBeneficio=" + valorBeneficio + "]";
		}
		
		
}