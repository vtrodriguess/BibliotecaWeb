package controller;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAO;
import model.JavaBeans;

@WebServlet(urlPatterns = { "/Controller", "/main", "/inserir", "/alugar", "/alugar_form", "/remover", "/devolver", "/devolver_form" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JavaBeans livros = new JavaBeans();
	DAO dao = new DAO();

	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String caminho = request.getServletPath();
		if (caminho.equals("/main")) {
			livros(request, response);
		} else if (caminho.equals("/inserir")) {
			adicionar(request, response);
		} else if (caminho.equals("/alugar")) {
			editarAlugar(request, response);
		} else if (caminho.equals("/alugar_form")) {
			alugarLivro(request, response);
		} else if (caminho.equals("/devolver")) {
			editarDevolver(request, response);
		} else if (caminho.equals("/devolver_form")) {
			devolverLivro(request, response);
		} else if (caminho.equals("/remover")) {
			removerLivro(request, response);
		} else {
			response.sendRedirect("index.html");
		}
	}

	protected void livros(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<JavaBeans> lista = dao.listar();

		request.setAttribute("livros", lista);
		RequestDispatcher rd = request.getRequestDispatcher("biblioteca.jsp");
		rd.forward(request, response);
	}

	protected void adicionar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		livros.setTitulo(request.getParameter("titulo"));
		livros.setAutor(request.getParameter("autor"));
		livros.setAno(request.getParameter("ano"));
		int disponivel = Integer.parseInt(request.getParameter("disponivel"));
		livros.setDisponivel(disponivel);

		dao.inserir(livros);

		response.sendRedirect("main");
	}

	protected void editarAlugar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		livros.setId(id);
		dao.selecionarLivro(livros);

		request.setAttribute("id", livros.getId());
		request.setAttribute("alugar", livros.getDisponivel());
		RequestDispatcher rd = request.getRequestDispatcher("alugar.jsp");
		rd.forward(request, response);

	}

	protected void alugarLivro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		livros.setId(request.getParameter("id"));
		int disponivel = Integer.parseInt(request.getParameter("alugar"));
		livros.setDisponivel(disponivel);
		dao.alugar(livros);
		response.sendRedirect("main");
	}

	protected void editarDevolver(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id_input");
		livros.setId(id);
		dao.selecionarLivro(livros);

		request.setAttribute("id", livros.getId());
		request.setAttribute("devolver", livros.getDisponivel());
		RequestDispatcher rd = request.getRequestDispatcher("devolver.jsp");
		rd.forward(request, response);

	}

	protected void devolverLivro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		livros.setId(request.getParameter("id_input"));
		int disponivel = Integer.parseInt(request.getParameter("devolver_input"));
		livros.setDisponivel(disponivel);
		dao.devolver(livros);
		response.sendRedirect("main");
	}

	protected void removerLivro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		livros.setId(id);
		dao.remover(livros);
		response.sendRedirect("main");
	}

}
