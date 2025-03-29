package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/biblioteca?useTimeZone=true&serverTimeZone=UTC";
	private String user = "root";
	private String password = "Bahia1931";

	private Connection conectarr() {
		Connection conecta = null;
		try {
			Class.forName(driver);
			conecta = DriverManager.getConnection(url, user, password);
			System.out.println("Conectado ao banco de dados");
			return conecta;
		} catch (Exception e) {
			System.out.println("Erro ao conectar ao banco: " + e.getMessage());
			return null;
		}
	}

	public void inserir(JavaBeans livros) {
		String criar = "insert into livros (titulo, autor, ano, disponivel) values (?,?,?,?)";

		try {
			Connection conectar = conectarr();
			PreparedStatement pst = conectar.prepareStatement(criar);
			pst.setString(1, livros.getTitulo());
			pst.setString(2, livros.getAutor());
			pst.setString(3, livros.getAno());
			pst.setInt(4, livros.getDisponivel());
			pst.executeUpdate();
			conectar.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public ArrayList<JavaBeans> listar() {
		String ler = "select * from livros order by autor";
		ArrayList<JavaBeans> livros = new ArrayList<JavaBeans>();

		try {
			Connection conectar = conectarr();
			PreparedStatement pst = conectar.prepareStatement(ler);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String id = rs.getString(1);
				String titulo = rs.getString(2);
				String autor = rs.getString(3);
				String ano = rs.getString(4);
				int disponivel = rs.getInt(5);
				livros.add(new JavaBeans(titulo, autor, ano, id, disponivel));

			}
			conectar.close();
			return livros;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public void selecionarLivro(JavaBeans livro) {
		String ler = "select * from livros where idcont = ?";
		try {
			Connection conectar = conectarr();
			PreparedStatement pst = conectar.prepareStatement(ler);
			pst.setString(1, livro.getId());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				livro.setId(rs.getString(1));
				livro.setTitulo(rs.getString(2));
				livro.setAutor(rs.getString(3));
				livro.setAno(rs.getString(4));
			}
			conectar.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void alugar(JavaBeans livros) {
		String aluga = "update livros set disponivel=? where idcont=?";
		try {
			Connection conectar = conectarr();
			PreparedStatement pst = conectar.prepareStatement(aluga);
			pst.setInt(1, livros.getDisponivel());
			pst.setString(2, livros.getId());
			pst.executeUpdate();
			conectar.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void devolver(JavaBeans livros) {
		String devolve = "update livros set disponivel=1 where idcont=?";
		try {
			Connection conectar = conectarr();
			PreparedStatement pst = conectar.prepareStatement(devolve);
			pst.setString(1, livros.getId());
			pst.executeUpdate();
			conectar.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void remover(JavaBeans livros) {
		String deletar = "delete from livros where idcont=?";
		try {
			Connection conectar = conectarr();
			PreparedStatement pst = conectar.prepareStatement(deletar);
			pst.setString(1, livros.getId());
			pst.executeUpdate();
			conectar.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
