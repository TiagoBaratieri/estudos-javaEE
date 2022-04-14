package com.baratieri.webcrud.controller;

import com.baratieri.webcrud.dao.ClienteDAO;
import com.baratieri.webcrud.model.Cliente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletClientAndFind", value = "/ServletClientAndFind")
public class ServletClientAndFind extends HttpServlet {

    public ServletClientAndFind() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pesquisa = request.getParameter("pesquisa");
        if (pesquisa == null) {
            pesquisa = "";
        }

        List<Cliente> clientes = ClienteDAO.find(pesquisa);
        request.setAttribute("clientes", clientes);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("lista.jsp");
        requestDispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cliente cliente = new Cliente();

        cliente.setNome(request.getParameter("nome"));
        cliente.setCpf(request.getParameter("cpf"));
        cliente.setNascimento(request.getParameter("nascimento"));
        cliente.setSituacao(request.getParameter("situacao"));

        ClienteDAO.create(cliente);

        doGet(request, response);

    }
}
