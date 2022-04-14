package com.baratieri.webcrud.controller;

import com.baratieri.webcrud.dao.ClienteDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletDeleteCliente", value = "/ServletDeleteCliente")
public class ServletDeleteCliente extends HttpServlet {

    public ServletDeleteCliente(){
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int clienteId = Integer.parseInt(request.getParameter("clienteId"));

        ClienteDAO.delete(clienteId);

        ServletClientAndFind clientAndFind = new ServletClientAndFind();
        clientAndFind.doGet(request,response);
    }

}
