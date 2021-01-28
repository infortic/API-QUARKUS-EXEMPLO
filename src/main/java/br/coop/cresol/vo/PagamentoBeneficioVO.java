package br.coop.cresol.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "beneficio", schema = "public")
public class PagamentoBeneficioVO implements Comparable<PagamentoBeneficioVO>{

	@Id
	@Column(name = "nro_beneficio", unique = true, nullable = false)
	private Long numeroBeneficio;

	@Column(name = "cod_cooperativa", nullable = false)
	private Long codigoCooperativa;

	@Column(name = "cod_pac", nullable = true)
	private Long codigoPac;

	@Column(name = "dv_conta_corrente", nullable = false)
	private Long digitoVerificadorContaCorrente;

	@Column(name = "nro_conta_corrente", nullable = false)
	private Long numeroContaCorrente;

	@Column(name = "vlr_beneficio", nullable = false)
	private Long valorBeneficio;

	@Override
	public int compareTo(PagamentoBeneficioVO o) {
		
		return 0;
	}	
	
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

	
	
	
	
}
