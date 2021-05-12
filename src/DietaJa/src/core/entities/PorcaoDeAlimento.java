package core.entities;

import java.util.ArrayList;
import java.util.List;

import core.entities.base.Entity;

public class PorcaoDeAlimento extends Entity {
	
	public PorcaoDeAlimento() {
		PorcaoDeAlimentoDiasdaSemanas =  new ArrayList<PorcaoDeAlimentoDiasdaSemana>();
		PorcaoDeAlimentoDietas = new ArrayList<PorcaoDeAlimentoDieta>();
		RefeicaoPorcaoDeAlimentos = new ArrayList<RefeicaoPorcaoDeAlimento>();
	}

	protected List<PorcaoDeAlimentoDiasdaSemana> PorcaoDeAlimentoDiasdaSemanas;
	protected List<PorcaoDeAlimentoDieta> PorcaoDeAlimentoDietas;
	protected List<RefeicaoPorcaoDeAlimento> RefeicaoPorcaoDeAlimentos;
	
	public List<PorcaoDeAlimentoDiasdaSemana> getPorcaoDeAlimentoDiasdaSemanas() {
		return PorcaoDeAlimentoDiasdaSemanas;
	}
	public void setPorcaoDeAlimentoDiasdaSemanas(List<PorcaoDeAlimentoDiasdaSemana> porcaoDeAlimentoDiasdaSemanas) {
		PorcaoDeAlimentoDiasdaSemanas = porcaoDeAlimentoDiasdaSemanas;
	}
	public List<PorcaoDeAlimentoDieta> getPorcaoDeAlimentoDietas() {
		return PorcaoDeAlimentoDietas;
	}
	public void setPorcaoDeAlimentoDietas(List<PorcaoDeAlimentoDieta> porcaoDeAlimentoDietas) {
		PorcaoDeAlimentoDietas = porcaoDeAlimentoDietas;
	}
	public List<RefeicaoPorcaoDeAlimento> getRefeicaoPorcaoDeAlimentos() {
		return RefeicaoPorcaoDeAlimentos;
	}
	public void setRefeicaoPorcaoDeAlimentos(List<RefeicaoPorcaoDeAlimento> refeicaoPorcaoDeAlimentos) {
		RefeicaoPorcaoDeAlimentos = refeicaoPorcaoDeAlimentos;
	}

	
}
