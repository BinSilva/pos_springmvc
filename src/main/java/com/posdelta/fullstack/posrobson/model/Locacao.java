package com.posdelta.fullstack.posrobson.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;


@Entity
public class Locacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(precision = 10, scale = 2)
    @NumberFormat(pattern = "#,##0.00")
    private BigDecimal valorTotal;

    @NotNull(message = "O campo data de locação é obrigatória!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataLocacao;

    @NotNull(message = "O campo data de devolução é obrigatória!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataDevolucao;

    @ManyToOne
    @JoinColumn(name = "id_carro")
    @NotNull(message = "O campo carro é obrigatório!")
    private Carro carro;

    @ManyToOne
    @JoinColumn(name = "id_motorista")
    @NotNull(message = "O campo motorista é obrigatório!")
    private Motorista motorista;

	public Locacao() {
		super();
	}

	public Locacao(Long id, BigDecimal valorTotal, Date dataLocacao, Date dataDevolucao, Carro carro, Motorista motorista) {
		super();
		this.id = id;
		this.valorTotal = valorTotal;
		this.dataLocacao = dataLocacao;
		this.dataDevolucao = dataDevolucao;
		this.carro = carro;
		this.motorista = motorista;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Date getDataLocacao() {
		return dataLocacao;
	}

	public void setDataLocacao(Date dataLocacao) {
		this.dataLocacao = dataLocacao;
	}

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public Motorista getMotorista() {
		return motorista;
	}

	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carro == null) ? 0 : carro.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Locacao other = (Locacao) obj;
		if (carro == null) {
			if (other.carro != null)
				return false;
		} else if (!carro.equals(other.carro))
			return false;
		return true;
	}
    
    
}
