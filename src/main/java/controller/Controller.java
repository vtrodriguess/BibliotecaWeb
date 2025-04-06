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

@WebServlet(urlPatterns = { "/main", "/inserir", "/alugar", "/remover", "/devolver", "/livros" })
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
			RequestDispatcher rd = request.getRequestDispatcher("biblioteca.html");
		    rd.forward(request, response);
		} else if (caminho.equals("/livros")){
			livros(request, response);
		} else {
			response.sendRedirect("index.html");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String caminho = request.getServletPath();

		if (caminho.equals("/inserir")) {
			adicionar(request, response);
		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String caminho = request.getServletPath();

		if (caminho.equals("/alugar")) {
			alugarLivro(request, response);
		} else if (caminho.equals("/devolver")) {
			devolverLivro(request, response);
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String caminho = request.getServletPath();

		if (caminho.equals("/remover")) {
			removerLivro(request, response);

		}
	}

	protected void livros(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<JavaBeans> lista = dao.listar();

		Gson gson = new Gson();
		String json = gson.toJson(lista);

		response.setContentType("application/json");
		PrintWriter saida = response.getWriter();
		saida.print(json);
		saida.flush();
	}

	protected void adicionar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BufferedReader br = request.getReader();
		Gson gson = new Gson();
		JavaBeans livros = gson.fromJson(br, JavaBeans.class);

		dao.inserir(livros);

		gson = new Gson();
		String json = gson.toJson(livros);

		response.setContentType("application/json");
		PrintWriter saida = response.getWriter();
		saida.print(json);
		saida.flush();

	}

	protected void alugarLivro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		livros.setId(request.getParameter("id"));
		livros.setDisponivel(0);
		dao.alugar(livros);
		response.setStatus(204);
	}

	protected void devolverLivro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		livros.setId(request.getParameter("id"));
		livros.setDisponivel(1);
		dao.devolver(livros);
		response.setStatus(204);
	}

	protected void removerLivro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		livros.setId(id);
		dao.remover(livros);
		response.setStatus(204);
	}

}
