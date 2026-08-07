package br.com.petcare.dao;

import br.com.petcare.model.Agendamento;
import br.com.petcare.model.Funcionario;
import br.com.petcare.model.Pet;
import br.com.petcare.model.Servico;
import br.com.petcare.util.ConexaoBD;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoDAO {

    public Agendamento salvar(Agendamento agendamento) {
        String sql = "INSERT INTO Agendamento (data, hora, status, pet_id, servico_id, funcionario_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setDate(1, Date.valueOf(agendamento.getData()));
            stmt.setTime(2, Time.valueOf(agendamento.getHora()));
            stmt.setString(3, agendamento.getStatus());
            
            if (agendamento.getPet() != null) {
                stmt.setInt(4, agendamento.getPet().getId());
            } else {
                stmt.setNull(4, java.sql.Types.INTEGER);
            }
            
            if (agendamento.getServico() != null) {
                stmt.setInt(5, agendamento.getServico().getId());
            } else {
                stmt.setNull(5, java.sql.Types.INTEGER);
            }
            
            if (agendamento.getFuncionario() != null) {
                stmt.setInt(6, agendamento.getFuncionario().getId());
            } else {
                stmt.setNull(6, java.sql.Types.INTEGER);
            }
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        agendamento.setId(rs.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return agendamento;
    }

    public List<Agendamento> listarTodos() {
        List<Agendamento> agendamentos = new ArrayList<>();
        String sql = "SELECT * FROM Agendamento";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            PetDAO petDAO = new PetDAO();
            ServicoDAO servicoDAO = new ServicoDAO();
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
            
            while (rs.next()) {
                Agendamento a = new Agendamento();
                a.setId(rs.getInt("id"));
                
                Date dataSql = rs.getDate("data");
                if (dataSql != null) {
                    a.setData(dataSql.toLocalDate());
                }
                
                Time horaSql = rs.getTime("hora");
                if (horaSql != null) {
                    a.setHora(horaSql.toLocalTime());
                }
                
                a.setStatus(rs.getString("status"));
                
                int petId = rs.getInt("pet_id");
                if (petId > 0) {
                    Pet pet = petDAO.buscarPorId(petId);
                    a.setPet(pet);
                }
                
                int servicoId = rs.getInt("servico_id");
                if (servicoId > 0) {
                    Servico servico = servicoDAO.buscarPorId(servicoId);
                    a.setServico(servico);
                }
                
                int funcionarioId = rs.getInt("funcionario_id");
                if (funcionarioId > 0) {
                    Funcionario funcionario = funcionarioDAO.buscarPorId(funcionarioId);
                    a.setFuncionario(funcionario);
                }
                
                agendamentos.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return agendamentos;
    }

    public Agendamento buscarPorId(int id) {
        String sql = "SELECT * FROM Agendamento WHERE id = ?";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Agendamento a = new Agendamento();
                    a.setId(rs.getInt("id"));
                    
                    Date dataSql = rs.getDate("data");
                    if (dataSql != null) {
                        a.setData(dataSql.toLocalDate());
                    }
                    
                    Time horaSql = rs.getTime("hora");
                    if (horaSql != null) {
                        a.setHora(horaSql.toLocalTime());
                    }
                    
                    a.setStatus(rs.getString("status"));
                    
                    PetDAO petDAO = new PetDAO();
                    int petId = rs.getInt("pet_id");
                    if (petId > 0) {
                        a.setPet(petDAO.buscarPorId(petId));
                    }
                    
                    ServicoDAO servicoDAO = new ServicoDAO();
                    int servicoId = rs.getInt("servico_id");
                    if (servicoId > 0) {
                        a.setServico(servicoDAO.buscarPorId(servicoId));
                    }
                    
                    FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
                    int funcionarioId = rs.getInt("funcionario_id");
                    if (funcionarioId > 0) {
                        a.setFuncionario(funcionarioDAO.buscarPorId(funcionarioId));
                    }
                    
                    return a;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean remover(int id) {
        String sql = "DELETE FROM Agendamento WHERE id = ?";
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
