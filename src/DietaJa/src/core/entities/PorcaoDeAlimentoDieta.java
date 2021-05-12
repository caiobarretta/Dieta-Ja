package core.entities;

public class PorcaoDeAlimentoDieta {
	protected int ID;
	protected int PorcAlimentoID;
	protected int DietaID;
	protected Dieta Dieta;
	protected PorcaoDeAlimento PorcAlimento;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getPorcAlimentoID() {
		return PorcAlimentoID;
	}
	public void setPorcAlimentoID(int porcAlimentoID) {
		PorcAlimentoID = porcAlimentoID;
	}
	public int getDietaID() {
		return DietaID;
	}
	public void setDietaID(int dietaID) {
		DietaID = dietaID;
	}
	public Dieta getDieta() {
		return Dieta;
	}
	public void setDieta(Dieta dieta) {
		Dieta = dieta;
	}
	public PorcaoDeAlimento getPorcAlimento() {
		return PorcAlimento;
	}
	public void setPorcAlimento(PorcaoDeAlimento porcAlimento) {
		PorcAlimento = porcAlimento;
	}
	
}
