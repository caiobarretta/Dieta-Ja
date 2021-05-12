package core.entities;

public class RefeicaoPorcaoDeAlimento {
	protected int ID;
	protected int RefeicaoID;
	protected int PorcAlimentoID;
	protected PorcaoDeAlimento PorcAlimento;
	protected Refeicao Refeicao;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getRefeicaoID() {
		return RefeicaoID;
	}
	public void setRefeicaoID(int refeicaoID) {
		RefeicaoID = refeicaoID;
	}
	public int getPorcAlimentoID() {
		return PorcAlimentoID;
	}
	public void setPorcAlimentoID(int porcAlimentoID) {
		PorcAlimentoID = porcAlimentoID;
	}
	public PorcaoDeAlimento getPorcAlimento() {
		return PorcAlimento;
	}
	public void setPorcAlimento(PorcaoDeAlimento porcAlimento) {
		PorcAlimento = porcAlimento;
	}
	public Refeicao getRefeicao() {
		return Refeicao;
	}
	public void setRefeicao(Refeicao refeicao) {
		Refeicao = refeicao;
	}
	
}
