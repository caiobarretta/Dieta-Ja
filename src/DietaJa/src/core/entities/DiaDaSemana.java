package core.entities;

import java.util.ArrayList;
import java.util.List;

import core.entities.base.Entity;

public class DiaDaSemana extends Entity {
	
	protected List<PorcaoDeAlimentoDiasdaSemana> PorcaoDeAlimentoDiasdaSemanas;
	
	public DiaDaSemana() {
		setPorcaoDeAlimentoDiasdaSemanas(new ArrayList<PorcaoDeAlimentoDiasdaSemana>());
	}
	
	public List<PorcaoDeAlimentoDiasdaSemana> getPorcaoDeAlimentoDiasdaSemanas() {
		return PorcaoDeAlimentoDiasdaSemanas;
	}

	public void setPorcaoDeAlimentoDiasdaSemanas(List<PorcaoDeAlimentoDiasdaSemana> porcaoDeAlimentoDiasdaSemanas) {
		PorcaoDeAlimentoDiasdaSemanas = porcaoDeAlimentoDiasdaSemanas;
	}

	
}
