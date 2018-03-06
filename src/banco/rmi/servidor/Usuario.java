package banco.rmi.servidor;

import java.time.Instant;

public class Usuario {
	
	private String nome;
	private int id;
	private double saldo;
	private Instant UltimoSaque;
	
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Instant getUltimoSaque() {
		return UltimoSaque;
	}
	public void setUltimoSaque(Instant ultimoSaque) {
		this.UltimoSaque = ultimoSaque;
	}
	

}
