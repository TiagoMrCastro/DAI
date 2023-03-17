/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

/**
 *
 * @author tiago
 */
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilizadorDAO {

    private Connection conn;

    public UtilizadorDAO() {
        conn = Conexao.getConnection();
    }

    public boolean login(String id, String pw) {
        boolean sucesso = false;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Utilizadores WHERE id = ? AND pw = ?");
            ps.setString(1, id);
            ps.setString(2, pw);
            ResultSet rs = ps.executeQuery();
            sucesso = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sucesso;
    }

    public void inserirUtilizador(Utilizador utilizador) {
        String sql = "INSERT INTO utilizadores (id, password, tipo) VALUES (?, ?, ?)";

        try ( Connection conn = Conexao.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, utilizador.getId());
            pstmt.setString(2, utilizador.getPassword());
            pstmt.setString(3, utilizador.getTipo());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir o utilizador: " + e.getMessage());
        }
    }

    //public Administrador(String nome, String email, String id, String pw, String tipo) {
    public void adicionarAdministrador(Administrador admin) throws SQLException {
        String sql = "INSERT INTO utilizadores (nome, email, id, password, tipo) VALUES (?, ?, ?, ?, ?, ?,?)";
        try ( PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, admin.getNome());
            stmt.setString(2, admin.getEmail());
            stmt.setString(3, admin.getId());
            stmt.setString(4, admin.getPassword());
            stmt.setString(5, admin.getTipo());
            stmt.executeUpdate();
        }
    }

    public void adicionarJogador(Jogador jogador) throws SQLException {
        String sql = "INSERT INTO utilizadores(nome, email, dataNascimento, altura, peso, id, pw, tipo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try ( PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, jogador.getNome());
            stmt.setString(2, jogador.getEmail());
            stmt.setDate(3, java.sql.Date.valueOf(jogador.getDataNascimento()));
            stmt.setFloat(4, jogador.getAltura());
            stmt.setFloat(5, jogador.getPeso());
            stmt.setString(6, jogador.getId());
            stmt.setString(7, jogador.getPassword());
            stmt.setString(8, jogador.getTipo());
            stmt.executeUpdate();
        }
    }

    public void adicionarTreinador(Treinador treinador) throws SQLException {
        String sql = "INSERT INTO utilizadores(id_utilizador, pw, nome, email) VALUES (?, ?, ?, ?)";
        try ( PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, treinador.getId());
            stmt.setString(2, treinador.getPassword());
            stmt.setString(3, treinador.getNome());
            stmt.setString(4, treinador.getEmail());
            stmt.executeUpdate();
        }
    }
    // public Jogador(String nome, String email, LocalDate dataNascimento, float altura, float peso, String id, String pw, String tipo) {//

    public void atualizarJogador(Jogador jogador) throws SQLException {
        String sql = "UPDATE Jogador SET pw = ?, nome = ?, email = ?, altura = ?, peso = ?, dataNascimento = ? WHERE id_utilizador = ?";
        try ( PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, jogador.getPassword());
            stmt.setString(2, jogador.getNome());
            stmt.setString(3, jogador.getEmail());
            stmt.setFloat(4, jogador.getAltura());
            stmt.setFloat(5, jogador.getPeso());
            stmt.setDate(7, java.sql.Date.valueOf(jogador.getDataNascimento()));
            stmt.setString(8, jogador.getId());
            stmt.executeUpdate();
        }
    }

    public void removerJogador(String id) throws SQLException {
        String sql = "DELETE FROM utilizadores WHERE id = ?";
        try ( PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        }
    }
    
    public List<Utilizador> buscarUtilizadorPorTipo(String tipo) {
    List<Utilizador> utilizadores = new ArrayList<>();
    String query = "SELECT * FROM utilizadores WHERE tipo = ?";

    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, tipo);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            String id = rs.getString("id");
            String senha = rs.getString("senha");
            Utilizador utilizador = new Utilizador(id, senha, tipo);
            utilizadores.add(utilizador);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return utilizadores;
}

    public Utilizador buscarUtilizadorPorIdSenha(String id, String senha) {
        Utilizador utilizador = null;
        String query = "SELECT * FROM utilizadores WHERE id = ? AND password = ?";

        try ( PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, id);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String tipo = rs.getString("tipo");
                utilizador = new Utilizador(id, senha, tipo);

                // Preencha os campos específicos do tipo de usuário (Administrador, Treinador, Jogador) aqui.
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return utilizador;
    }
}
