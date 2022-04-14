package com.baratieri.webcrud.controller;

import com.baratieri.webcrud.dao.ClienteDAO;
import com.baratieri.webcrud.model.Cliente;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletClienteUpdate", value = "/ServletClienteUpdate")
public class ServletClienteUpdate extends HttpServlet {


    public ServletClienteUpdate() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int clienteId = Integer.parseInt(request.getParameter("clienteId"));
        Cliente cliente = ClienteDAO.findById(clienteId);

        request.setAttribute("cliente", cliente);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("formUpdate.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        Cliente cliente = new Cliente();

        cliente.setId(Integer.parseInt(request.getParameter("id")));
        cliente.setNome(request.getParameter("nome"));
        cliente.setCpf(request.getParameter("cpf"));
        cliente.setNascimento(request.getParameter("nascimento"));
        cliente.setSituacao(request.getParameter("situacao"));

        ClienteDAO.update(cliente);

        ServletClientAndFind clientAndFind = new ServletClientAndFind();
        clientAndFind.doGet(request, response);
    }
}
