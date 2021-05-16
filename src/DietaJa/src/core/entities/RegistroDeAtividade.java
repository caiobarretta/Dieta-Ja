package core.entities;

import java.util.Date;

import core.entities.base.Entity;

public class RegistroDeAtividade {
	protected Integer RegistroDeAtividadeID;
	protected Date Registro;
	protected String Sentimento;
	protected Integer UsuarioID;
	protected Integer DietaID;
	protected RefeicaoEnum Refeicao;
	protected Integer PorcaoDeAlimentoID;
	protected DiaDaSemanaEnum DiaDaSemana;
	
	public Integer getRegistroDeAtividadeID() {
		return RegistroDeAtividadeID;
	}
	public void setRegistroDeAtividadeID(Integer registroDeAtividadeID) {
		RegistroDeAtividadeID = registroDeAtividadeID;
	}
	public Date getRegistro() {
		return Registro;
	}
	public void setRegistro(Date registro) {
		Registro = registro;
	}
	public String getSentimento() {
		return Sentimento;
	}
	public void setSentimento(String sentimento) {
		Sentimento = sentimento;
	}
	public Integer getUsuarioID() {
		return UsuarioID;
	}
	public void setUsuarioID(Integer usuarioID) {
		UsuarioID = usuarioID;
	}
	public Integer getDietaID() {
		return DietaID;
	}
	public void setDietaID(Integer dietaID) {
		DietaID = dietaID;
	}
	public RefeicaoEnum getRefeicaoID() {
		return Refeicao;
	}
	public void setRefeicaoID(RefeicaoEnum refeicao) {
		Refeicao = refeicao;
	}
	public Integer getPorcaoDeAlimentoID() {
		return PorcaoDeAlimentoID;
	}
	public void setPorcaoDeAlimentoID(Integer porcaoDeAlimentoID) {
		PorcaoDeAlimentoID = porcaoDeAlimentoID;
	}
	public DiaDaSemanaEnum getDiaDaSemanaID() {
		return DiaDaSemana;
	}
	public void setDiaDaSemanaID(DiaDaSemanaEnum diaDaSemana) {
		DiaDaSemana = diaDaSemana;
	}
}
