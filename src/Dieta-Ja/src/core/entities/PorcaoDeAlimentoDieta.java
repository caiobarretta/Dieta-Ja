package core.entities;

//Classe que representa a associação entre Porção de alimento e dieta
public class PorcaoDeAlimentoDieta {
	protected Integer ID;
	protected Integer PorcAlimentoID;
	protected Integer DietaID;
	
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public Integer getPorcAlimentoID() {
		return PorcAlimentoID;
	}
	public void setPorcAlimentoID(Integer porcAlimentoID) {
		PorcAlimentoID = porcAlimentoID;
	}
	public Integer getDietaID() {
		return DietaID;
	}
	public void setDietaID(Integer dietaID) {
		DietaID = dietaID;
	}
}
