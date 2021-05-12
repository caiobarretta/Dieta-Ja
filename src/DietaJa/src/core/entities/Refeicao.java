package core.entities;

import java.util.ArrayList;
import java.util.List;

import core.entities.base.Entity;

public class Refeicao extends Entity {
	
	protected List<RefeicaoPorcaoDeAlimento> RefeicaoPorcaoDeAlimentos;
	
	public Refeicao(){
        setRefeicaoPorcaoDeAlimentos(new ArrayList<RefeicaoPorcaoDeAlimento>());
    }

	public List<RefeicaoPorcaoDeAlimento> getRefeicaoPorcaoDeAlimentos() {
		return RefeicaoPorcaoDeAlimentos;
	}

	public void setRefeicaoPorcaoDeAlimentos(List<RefeicaoPorcaoDeAlimento> refeicaoPorcaoDeAlimentos) {
		RefeicaoPorcaoDeAlimentos = refeicaoPorcaoDeAlimentos;
	}

    
}
