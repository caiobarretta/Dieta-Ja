package core.entities;

//Classe que representa uma associação de Porção de alimento com Dias da Semana
public class PorcaoDeAlimentoDiasdaSemana {
	protected Integer ID;
	protected Integer DiaSemanaID;
	protected Integer PorcaoAlimentoID;
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public Integer getDiaSemanaID() {
		return DiaSemanaID;
	}
	public void setDiaSemanaID(Integer diaSemanaID) {
		DiaSemanaID = diaSemanaID;
	}
	public Integer getPorcaoAlimentoID() {
		return PorcaoAlimentoID;
	}
	public void setPorcaoAlimentoID(Integer porcaoAlimentoID) {
		PorcaoAlimentoID = porcaoAlimentoID;
	}
	
	
}
