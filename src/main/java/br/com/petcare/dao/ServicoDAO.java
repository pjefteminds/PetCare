package br.com.petcare.dao;

import br.com.petcare.model.Servico;
import br.com.petcare.util.ConexaoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServicoDAO {

    public Servico salvar(Servico servico) {
        String sql = "INSERT INTO Servico (descricao, preco, duracaoMinutos) VALUES (?, ?, ?)";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, servico.getDescricao());
            stmt.setDouble(2, servico.getPreco());
            stmt.setInt(3, servico.getDuracaoMinutos());
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        servico.setId(rs.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return servico;
    }

    public List<Servico> listarTodos() {
        List<Servico> servicos = new ArrayList<>();
        String sql = "SELECT * FROM Servico";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Servico s = new Servico();
                s.setId(rs.getInt("id"));
                s.setDescricao(rs.getString("descricao"));
                s.setPreco(rs.getDouble("preco"));
                s.setDuracaoMinutos(rs.getInt("duracaoMinutos"));
                servicos.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return servicos;
    }

    public Servico buscarPorId(int id) {
        String sql = "SELECT * FROM Servico WHERE id = ?";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Servico s = new Servico();
                    s.setId(rs.getInt("id"));
                    s.setDescricao(rs.getString("descricao"));
                    s.setPreco(rs.getDouble("preco"));
                    s.setDuracaoMinutos(rs.getInt("duracaoMinutos"));
                    return s;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean remover(int id) {
        String sql = "DELETE FROM Servico WHERE id = ?";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
