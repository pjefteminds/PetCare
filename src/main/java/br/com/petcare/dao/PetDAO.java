package br.com.petcare.dao;

import br.com.petcare.model.Cliente;
import br.com.petcare.model.Pet;
import br.com.petcare.util.ConexaoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PetDAO {

    public Pet salvar(Pet pet) {
        String sql = "INSERT INTO Pet (nome, especie, raca, idade, cliente_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, pet.getNome());
            stmt.setString(2, pet.getEspecie());
            stmt.setString(3, pet.getRaca());
            stmt.setInt(4, pet.getIdade());
            if (pet.getCliente() != null) {
                stmt.setInt(5, pet.getCliente().getId());
            } else {
                stmt.setNull(5, java.sql.Types.INTEGER);
            }
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        pet.setId(rs.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pet;
    }

    public List<Pet> listarTodos() {
        List<Pet> pets = new ArrayList<>();
        String sql = "SELECT * FROM Pet";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            ClienteDAO clienteDAO = new ClienteDAO();
            while (rs.next()) {
                Pet p = new Pet();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setEspecie(rs.getString("especie"));
                p.setRaca(rs.getString("raca"));
                p.setIdade(rs.getInt("idade"));
                
                int clienteId = rs.getInt("cliente_id");
                if (clienteId > 0) {
                    Cliente c = clienteDAO.buscarPorId(clienteId);
                    p.setCliente(c);
                }
                pets.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pets;
    }

    public Pet buscarPorId(int id) {
        String sql = "SELECT * FROM Pet WHERE id = ?";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Pet p = new Pet();
                    p.setId(rs.getInt("id"));
                    p.setNome(rs.getString("nome"));
                    p.setEspecie(rs.getString("especie"));
                    p.setRaca(rs.getString("raca"));
                    p.setIdade(rs.getInt("idade"));
                    
                    int clienteId = rs.getInt("cliente_id");
                    if (clienteId > 0) {
                        ClienteDAO clienteDAO = new ClienteDAO();
                        Cliente c = clienteDAO.buscarPorId(clienteId);
                        p.setCliente(c);
                    }
                    return p;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean remover(int id) {
        String sql = "DELETE FROM Pet WHERE id = ?";
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
