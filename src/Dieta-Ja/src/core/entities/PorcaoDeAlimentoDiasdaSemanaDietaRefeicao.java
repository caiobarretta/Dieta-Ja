package core.entities;

//Classe que representa uma Associação de Porção de alimento com Dias da Semana, Dieta e Refeicao
public class PorcaoDeAlimentoDiasdaSemanaDietaRefeicao {
	
	//# ID_PorcaoDeAlimento, ID_Dieta, DiaDaSemana, Refeicao
	private Integer ID_Dieta;
	private Integer ID_PorcaoAlimento;
	
	private RefeicaoEnum Refeicao;
	private DiaDaSemanaEnum DiaSemana;
	
	public Integer getID_Dieta() {
		return ID_Dieta;
	}
	public void setID_Dieta(Integer iD_Dieta) {
		ID_Dieta = iD_Dieta;
	}
	public Integer getID_PorcaoAlimento() {
		return ID_PorcaoAlimento;
	}
	public void setID_PorcaoAlimento(Integer iD_PorcaoAlimento) {
		ID_PorcaoAlimento = iD_PorcaoAlimento;
	}
	public RefeicaoEnum getRefeicao() {
		return Refeicao;
	}
	public void setRefeicao(RefeicaoEnum refeicao) {
		Refeicao = refeicao;
	}
	public DiaDaSemanaEnum getDiaSemana() {
		return DiaSemana;
	}
	public void setDiaSemana(DiaDaSemanaEnum diaSemana) {
		DiaSemana = diaSemana;
	}
	
	
}
