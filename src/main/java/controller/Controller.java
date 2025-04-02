package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.google.gson.Gson;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAO;
import model.JavaBeans;

@WebServlet(urlPatterns = { "/main", "/inserir", "/alugar", "/alugar_form", "/remover", "/devolver", "/devolver_form" })
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
		System.out.println(caminho);

		if (caminho.equals("/main")) {
			livros(request, response);
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String teste = request.getServletPath();

		if (teste.equals("/inserir")) {
			adicionar(request, response);
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
		
		BufferedReader br = request.getReader();
		Gson gson = new Gson();
		JavaBeans livros= gson.fromJson(br, JavaBeans.class);
		
		dao.inserir(livros);
		
		gson = new Gson();
		String json = gson.toJson(livros);
		
		response.setContentType("application/json");
		PrintWriter saida = response.getWriter();
        saida.print(json);
        saida.flush();
		
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
