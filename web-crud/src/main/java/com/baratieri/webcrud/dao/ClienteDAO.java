package com.baratieri.webcrud.dao;

import com.baratieri.webcrud.model.Cliente;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private static final Connection con = ConnectionDB.createConnection();
    private static String sql = null;

    public static void create(Cliente cliente) {
        sql = "insert into cliente values (null ,?,?,?,?)";
        try {
            assert con != null;
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, cliente.getNome());
            pst.setString(2, cliente.getCpf());
            pst.setString(3, cliente.getNascimento());
            pst.setString(4, cliente.getSituacao());

            pst.executeUpdate();
            System.out.println("Conectado com sucesso!");

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e.getMessage());

        }

    }

    public static List<Cliente> find(String pesquisa) {

        sql = String.format("SELECT * FROM cliente WHERE nome like '%s%%' OR cpf LIKE '%s%%' ", pesquisa, pesquisa);
        List<Cliente> clientes = new ArrayList<>();
        try {
            assert con != null;
            Statement stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(resultSet.getInt("id"));
                cliente.setNome(resultSet.getString("nome"));
                cliente.setCpf(resultSet.getString("cpf"));
                cliente.setNascimento(resultSet.getString("nascimento"));
                cliente.setSituacao(resultSet.getString("situacao"));
                clientes.add(cliente);
            }
            System.out.println("correct find clients.");
            return clientes;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }

    public static void delete(int clienteId) {
        sql = "DELETE FROM cliente WHERE id = ?";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, clienteId);
            stmt.executeUpdate();

            System.out.println("--correct delete on cliente");

        } catch (SQLException e) {
            System.out.println("--incorrect delete on cliente. " + e.getMessage());
        }
    }

    public static Cliente findById(int clienteId) {

        sql = String.format("SELECT * FROM cliente WHERE id = %d ", clienteId);

        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            Cliente cliente = new Cliente();

            while (resultSet.next()) {
                cliente.setId(resultSet.getInt("id"));
                cliente.setNome(resultSet.getString("nome"));
                cliente.setCpf(resultSet.getString("cpf"));
                cliente.setNascimento(resultSet.getString("nascimento"));
                cliente.setSituacao(resultSet.getString("situacao"));
            }

            System.out.println("--correct find by pk clientes");
            return cliente;

        } catch(SQLException e) {

            System.out.println("--incorrect find by pk clientes. " + e.getMessage());
            return null;
        }
    }

    public static void update(Cliente cliente) {
        sql = "UPDATE cliente SET nome=?, cpf=?, nascimento=?, situacao=? WHERE id=?";

        try {
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, cliente.getNome());
            pst.setString(2, cliente.getCpf());
            pst.setString(3, cliente.getNascimento());
            pst.setString(4, cliente.getSituacao());
            pst.setInt(5, cliente.getId());

            pst.executeUpdate();

            System.out.println("--correct update on database");

        } catch(SQLException e) {
            System.out.println("--incorrect update on database. " + e.getMessage());
        }
    }
}
