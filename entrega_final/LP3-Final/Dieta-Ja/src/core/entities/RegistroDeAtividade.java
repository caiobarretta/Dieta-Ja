package core.entities;

import java.util.Date;

import core.entities.base.Entity;

//Classe que representa um registro de atividade.
public class RegistroDeAtividade {
	
	//ID_RegistroDeAtividade
	private Integer registroDeAtividadeID;
	//ID_Dieta
	private Integer dietaID;
	//ID_PorcaoDeAlimento
	private Integer PorcaoDeAlimentoID;
	//ID_Usuario
	private Integer usuarioID;
	//Registro
	private String registro;
	//Comentarios
	private String comentarios;
	//Sentimento
	private Integer Sentimento;
	//Refeicao
	private Integer Refeicao;
	//DiaDaSemana
	private Integer DiaDaSemana;
	
	public Integer getRegistroDeAtividadeID() {
		return registroDeAtividadeID;
	}
	public void setRegistroDeAtividadeID(Integer registroDeAtividadeID) {
		this.registroDeAtividadeID = registroDeAtividadeID;
	}
	public String getRegistro() {
		return registro;
	}
	public void setRegistro(String registro) {
		this.registro = registro;
	}
	public Integer getSentimento() {
		return Sentimento;
	}
	public void setSentimento(Integer sentimento) {
		Sentimento = sentimento;
	}
	public Integer getUsuarioID() {
		return usuarioID;
	}
	public void setUsuarioID(Integer usuarioID) {
		this.usuarioID = usuarioID;
	}
	public Integer getDietaID() {
		return dietaID;
	}
	public void setDietaID(Integer dietaID) {
		this.dietaID = dietaID;
	}
	public Integer getRefeicaoID() {
		return Refeicao;
	}
	public void setRefeicaoID(Integer refeicao) {
		Refeicao = refeicao;
	}
	public Integer getPorcaoDeAlimentoID() {
		return PorcaoDeAlimentoID;
	}
	public void setPorcaoDeAlimentoID(Integer porcaoDeAlimentoID) {
		PorcaoDeAlimentoID = porcaoDeAlimentoID;
	}
	public Integer getDiaDaSemanaID() {
		return DiaDaSemana;
	}
	public void setDiaDaSemanaID(Integer diaDaSemana) {
		DiaDaSemana = diaDaSemana;
	}
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
}
