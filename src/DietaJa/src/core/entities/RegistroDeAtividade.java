package core.entities;

import java.util.Date;

import core.entities.base.Entity;

public class RegistroDeAtividade extends Entity {
	protected int RegistroDeAtividadeID;
	protected Date Registro;
	protected String Sentimento;
	protected int UsuarioID;
	protected int DietaID;
	protected int RefeicaoID;
	protected int PorcaoDeAlimentoID;
	protected int DiaDaSemanaID;
	protected Usuario Usuario;
	protected Dieta Dieta;
	protected Refeicao Refeicao;
	protected PorcaoDeAlimento PorcaoDeAlimento;
	protected DiaDaSemana DiaDaSemana;
	
	public int getRegistroDeAtividadeID() {
		return RegistroDeAtividadeID;
	}
	public void setRegistroDeAtividadeID(int registroDeAtividadeID) {
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
	public int getUsuarioID() {
		return UsuarioID;
	}
	public void setUsuarioID(int usuarioID) {
		UsuarioID = usuarioID;
	}
	public Usuario getUsuario() {
		return Usuario;
	}
	public void setUsuario(Usuario usuario) {
		Usuario = usuario;
	}
	public int getDietaID() {
		return DietaID;
	}
	public void setDietaID(int dietaID) {
		DietaID = dietaID;
	}
	public int getRefeicaoID() {
		return RefeicaoID;
	}
	public void setRefeicaoID(int refeicaoID) {
		RefeicaoID = refeicaoID;
	}
	public int getPorcaoDeAlimentoID() {
		return PorcaoDeAlimentoID;
	}
	public void setPorcaoDeAlimentoID(int porcaoDeAlimentoID) {
		PorcaoDeAlimentoID = porcaoDeAlimentoID;
	}
	public int getDiaDaSemanaID() {
		return DiaDaSemanaID;
	}
	public void setDiaDaSemanaID(int diaDaSemanaID) {
		DiaDaSemanaID = diaDaSemanaID;
	}
	public Dieta getDieta() {
		return Dieta;
	}
	public void setDieta(Dieta dieta) {
		Dieta = dieta;
	}
	public Refeicao getRefeicao() {
		return Refeicao;
	}
	public void setRefeicao(Refeicao refeicao) {
		Refeicao = refeicao;
	}
	public PorcaoDeAlimento getPorcaoDeAlimento() {
		return PorcaoDeAlimento;
	}
	public void setPorcaoDeAlimento(PorcaoDeAlimento porcaoDeAlimento) {
		PorcaoDeAlimento = porcaoDeAlimento;
	}
	public DiaDaSemana getDiaDaSemana() {
		return DiaDaSemana;
	}
	public void setDiaDaSemana(DiaDaSemana diaDaSemana) {
		DiaDaSemana = diaDaSemana;
	}
	
}
