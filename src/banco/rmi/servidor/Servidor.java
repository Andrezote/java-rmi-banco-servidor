package banco.rmi.servidor;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import banco.rmi.remote.IRemoteBanco;

public class Servidor implements IRemoteBanco{
	
	public static void main(String[] args) {
		
		try {
			System.out.println("Construindo Servidor Remoto...");
			Servidor servidor = new Servidor();
			IRemoteBanco stub = (IRemoteBanco)
					UnicastRemoteObject.exportObject(servidor,0);
			
			Registry registry = LocateRegistry.getRegistry(9876);
			
			registry.bind("Servidor_aula", stub);
			
			System.out.println("servidor iniciado...");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private List<Usuario> usuarios = preencheusuarios();
	int conexoes = 0;

	@Override
	public void deposito(double vlr, int id) {
		Usuario user = new Usuario();
		user = checaUsuario(id);
		if (user == null) {
			System.out.println("Usuario nao cadastrado");
		}else {
			conexoes++;
			user.setSaldo(user.getSaldo() + vlr);
		}
	}

	public Usuario checaUsuario(int id) {
		for (Usuario usuario : usuarios) {
			if (id == usuario.getId()){
				return usuario;
			}
		}
		return null;
	}

	@Override
	public double saldo(int id) {
		Usuario user = new Usuario();
		user = checaUsuario(id);
		if (user == null) {
			System.out.println("Usuario nao cadastrado");
			return (0);
		}else {
			return user.getSaldo();
		}
	}

	@Override
	public void saque(double vlr, int id, Instant data) {
		Usuario user = new Usuario();
		user = checaUsuario(id);
		if (user == null) {
			System.out.println("Usuario nao cadastrado");
		}else {
			if(user.getUltimoSaque()!= null) {
				Instant fim = data;
				Instant inicio = user.getUltimoSaque();
				Duration duracao = Duration.between(inicio,fim);
				long duracaoEmMilissegundos = duracao.toMinutes();
				if (duracaoEmMilissegundos < 2) {
					System.out.println("babybaby erro aguarde");
				}else {
					user.setSaldo(user.getSaldo() - vlr);
					user.setUltimoSaque(data);
				}
			}
			else {
				user.setSaldo(user.getSaldo() - vlr);
				user.setUltimoSaque(data);
			}
		}
		
	}
	
	public ArrayList<Usuario> preencheusuarios(){
		ArrayList<Usuario> users = new ArrayList<>();
		for(int i = 1; i<= 5; i++) {
			Usuario u = new Usuario();
			u.setId(i);
			u.setNome("nome " + i);
			u.setSaldo(0);
			users.add(u);
		}
		return users;
	}
	
	

}
