package model;

public class JavaBeans {

	private String titulo;
	private String autor;
	private String ano;
	private String id;
	private int disponivel = 1;

	public JavaBeans() {

	}

	public JavaBeans(String titulo, String autor, String ano, String id, int disponivel) {
		super();
		this.titulo = titulo;
		this.autor = autor;
		this.ano = ano;
		this.id = id;
		this.disponivel = disponivel;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

	public int getDisponivel() {
		return disponivel;
	}

	public void setDisponivel(int disponivel) {
		this.disponivel = disponivel;
	}
	
	

}
