package core.entities;

import core.entities.base.Entity;

//Classe que representa uma Porção de alimentos
public class PorcaoDeAlimento extends Entity {
	
	private Integer quantidade;
	private TipoMedidaEnum tipoMedida;
	public PorcaoDeAlimento() {
		this.setAtivo(true);
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public TipoMedidaEnum getTipoMedida() {
		return tipoMedida;
	}
	public void setTipoMedida(TipoMedidaEnum tipoMedia) {
		this.tipoMedida = tipoMedia;
	}
}
