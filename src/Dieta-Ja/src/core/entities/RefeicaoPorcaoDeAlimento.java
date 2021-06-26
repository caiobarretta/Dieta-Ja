package core.entities;


//Classe que representa uma associação entre Refeição e Porção de Alimento
public class RefeicaoPorcaoDeAlimento {
	protected Integer ID;
	protected RefeicaoEnum Refeicao;
	protected Integer PorcAlimentoID;
	
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public RefeicaoEnum getRefeicaoID() {
		return Refeicao;
	}
	public void setRefeicaoID(RefeicaoEnum refeicao) {
		Refeicao = refeicao;
	}
	public Integer getPorcAlimentoID() {
		return PorcAlimentoID;
	}
	public void setPorcAlimentoID(Integer porcAlimentoID) {
		PorcAlimentoID = porcAlimentoID;
	}
	
}
