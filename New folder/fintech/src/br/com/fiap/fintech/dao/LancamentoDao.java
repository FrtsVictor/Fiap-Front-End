package br.com.fiap.fintech.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.fiap.fintech.exception.EntityNotFoundException;
import br.com.fiap.fintech.models.Conta;
import br.com.fiap.fintech.models.Lancamento;
import br.com.fiap.fintech.singleton.DatabaseConnection;

public class LancamentoDao implements IBaseDao<Lancamento> {
	
	private static final String TABLE_NAME = "fintech_admin.T_FNT_LANCAMENTO";

    @Override
    public Optional<Lancamento> getById(Long id, boolean closeConnection) {
        final String sql = String.format("SELECT * FROM %s WHERE cd_lancamento = ?", TABLE_NAME);
        final Connection con = DatabaseConnection.getConnection();
        Lancamento lancamento = null;

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lancamento = mapResultToLancamento(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            DatabaseConnection.freeConnection();
        }

        if (closeConnection)
            DatabaseConnection.freeConnection();

        return Optional.ofNullable(lancamento);
    }

    @Override
    public List<Lancamento> getAll(boolean closeConnection) {
        String sql = String.format("SELECT * FROM %s", TABLE_NAME);
        final Connection con = DatabaseConnection.getConnection();
        List<Lancamento> usuarios = new ArrayList<>();

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    usuarios.add(mapResultToLancamento(rs));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            DatabaseConnection.freeConnection();
        }

        if (closeConnection)
            DatabaseConnection.freeConnection();

        return usuarios;
    }    
    
    public List<Lancamento> getAllByUserId(Long userId, boolean closeConnection) {
        String sql = String.format("SELECT * FROM %s WHERE cd_usuario = ?", TABLE_NAME);
        final Connection con = DatabaseConnection.getConnection();
        List<Lancamento> lancamentos = new ArrayList<>();
        
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
        	stmt.setLong(1, userId);
        	
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lancamentos.add(mapResultToLancamento(rs));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            DatabaseConnection.freeConnection();
        }

        if (closeConnection)
            DatabaseConnection.freeConnection();

        return lancamentos;
    }

    @Override
    public void save(Lancamento lancamento, boolean closeConnection) {
        final String sql = String.format("INSERT INTO %s (cd_conta, vl_lancamento, dc_lancamento, tl_titulo, dt_data)  VALUES  (?, ?, ?, ?, ?)", TABLE_NAME);
        final Connection con = DatabaseConnection.getConnection();

        try (PreparedStatement stmt = con.prepareStatement(sql)) {            
            stmt.setLong(1, lancamento.getConta().getId());            
            stmt.setDouble(2, lancamento.getValor());
            stmt.setString(3, lancamento.getDescricao());
            stmt.setString(4, lancamento.getTitulo());
            stmt.setObject(5, lancamento.getData());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            DatabaseConnection.freeConnection();
        }

        if (closeConnection)
            DatabaseConnection.freeConnection();
    }

    @Override
    public void updateById(Long id, Lancamento lancamento, boolean closeConnection) {
        final String sql = String.format("UPDATE %s SET vl_lancamento = ?, dc_lancamento = ?, tl_titulo = ?, dt_data = ?, dt_atualizacao = ? WHERE cd_lancamento = ?", TABLE_NAME);
        final Connection con = DatabaseConnection.getConnection();

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setDouble(1, lancamento.getValor());
            stmt.setString(2, lancamento.getDescricao());
            stmt.setString(3, lancamento.getTitulo());
            stmt.setObject(4, lancamento.getData());
            stmt.setObject(5, LocalDateTime.now());
            stmt.setLong(6, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            DatabaseConnection.freeConnection();
        }

        if (closeConnection)
            DatabaseConnection.freeConnection();
    }

    @Override
    public void deleteById(Long id, boolean closeConnection) {
        String sql = String.format("DELETE FROM %s WHERE cd_lancamento = ?", TABLE_NAME);
        final Connection con = DatabaseConnection.getConnection();

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            DatabaseConnection.freeConnection();
        }

        if (closeConnection)
            DatabaseConnection.freeConnection();
    }

    private Lancamento mapResultToLancamento(ResultSet rs) throws SQLException {
        try {
            Long id = rs.getLong("cd_lancamento");
            Long contaId = rs.getLong("cd_conta");
            Double valor = rs.getDouble("vl_lancamento");
            
            String descricao = rs.getString("dc_lancamento");
            String titulo= rs.getString("tl_titulo");
            LocalDateTime dataCriacao = rs.getObject("dt_criacao", LocalDateTime.class);
            LocalDateTime dataAtualizacao = rs.getObject("dt_atualizacao", LocalDateTime.class);
            LocalDate data = rs.getObject("dt_data", LocalDate.class);
            Conta conta = new ContaDao().getById(contaId, false)
                    .orElseThrow(() -> new EntityNotFoundException("No conta found for id" + contaId));

            return new Lancamento(id,  titulo, descricao, valor, data, conta,  dataCriacao, dataAtualizacao);
        } catch (Exception e) {
            e.printStackTrace();
            DatabaseConnection.freeConnection();
            throw new SQLException("Error mapping Lancamento");
        }
    }
}
