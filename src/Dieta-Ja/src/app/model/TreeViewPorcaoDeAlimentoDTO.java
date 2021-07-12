package app.model;

public class TreeViewPorcaoDeAlimentoDTO {
	private String porcaoDeAlimento;
	private String DiaDaSemana;
	private String Refeicao;
	
	public String getPorcaoDeAlimento() {
		return porcaoDeAlimento;
	}
	public void setPorcaoDeAlimento(String porcaoDeAlimento) {
		this.porcaoDeAlimento = porcaoDeAlimento;
	}
	public String getDiaDaSemana() {
		return DiaDaSemana;
	}
	public void setDiaDaSemana(String diaDaSemana) {
		DiaDaSemana = diaDaSemana;
	}
	public String getRefeicao() {
		return Refeicao;
	}
	public void setRefeicao(String refeicao) {
		Refeicao = refeicao;
	}
	
	@Override
	public String toString() {
		return String.format("porcaoDeAlimento: %s DiaDaSemana: %s Refeicao: %s", getPorcaoDeAlimento(), getDiaDaSemana(), getRefeicao());
	}
}
