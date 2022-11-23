package br.com.fiap.fintech.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.fiap.fintech.exception.EntityNotFoundException;
import br.com.fiap.fintech.models.Conta;
import br.com.fiap.fintech.models.Usuario;
import br.com.fiap.fintech.singleton.DatabaseConnection;

public class ContaDao implements IBaseDao<Conta> {
    private static final String TABLE_NAME = "fintech_admin.T_FNT_CONTA";

    @Override
    public Optional<Conta> getById(Long id, boolean closeConnection) {
        final String sql = String.format("SELECT * FROM %s WHERE cd_conta = ?", TABLE_NAME);
        final Connection con = DatabaseConnection.getConnection();
        Conta conta = null;

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    conta = mapResultToConta(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            DatabaseConnection.freeConnection();
        }

        if (closeConnection)
            DatabaseConnection.freeConnection();

        return Optional.ofNullable(conta);
    }
    
    
    public Optional<Conta> getByUserId(Long id, boolean closeConnection) {
        final String sql = String.format("SELECT * FROM %s WHERE cd_usuario = ?", TABLE_NAME);
        final Connection con = DatabaseConnection.getConnection();
        Conta conta = null;

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    conta = mapResultToConta(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            DatabaseConnection.freeConnection();
        }

        if (closeConnection)
            DatabaseConnection.freeConnection();

        return Optional.ofNullable(conta);
    }

    @Override
    public List<Conta> getAll(boolean closeConnection) {
        String sql = String.format("SELECT * FROM %s", TABLE_NAME);
        final Connection con = DatabaseConnection.getConnection();
        List<Conta> usuarios = new ArrayList<>();

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    usuarios.add(mapResultToConta(rs));
                }
            }
        } catch (Exception e) {
            DatabaseConnection.freeConnection();
            e.printStackTrace();
        }

        if (closeConnection)
            DatabaseConnection.freeConnection();

        return usuarios;
    }

    @Override
    public void save(Conta conta, boolean closeConnection) {
        final String sql = String.format("INSERT INTO %s (cd_usuario, nm_conta, vl_saldo)  VALUES  (?, ?, ?)", TABLE_NAME);
        final Connection con = DatabaseConnection.getConnection();

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setLong(1, conta.getUsuario().getId());
            stmt.setString(2, conta.getNome());
            stmt.setDouble(3, conta.getSaldo());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            DatabaseConnection.freeConnection();
        }
    }

    @Override
    public void updateById(Long id, Conta conta, boolean closeConnection) {
        final String sql = String.format("UPDATE %s SET cd_usuario = ?, nm_conta = ?, vl_saldo = ?, dt_atualizacao = ? WHERE cd_conta = ?", TABLE_NAME);
        final Connection con = DatabaseConnection.getConnection();

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setLong(1, conta.getUsuario().getId());
            stmt.setString(2, conta.getNome());
            stmt.setDouble(3, conta.getSaldo());
            stmt.setObject(4, LocalDateTime.now());
            stmt.setLong(5, id);
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
        String sql = String.format("DELETE FROM %s WHERE cd_conta = ?", TABLE_NAME);
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

    private Conta mapResultToConta(ResultSet rs) throws SQLException {
        try {
            Long id = rs.getLong("cd_conta");
            Long userId = rs.getLong("cd_usuario");
            String nome = rs.getString("nm_conta");
            Double saldo = rs.getDouble("vl_saldo");
            LocalDateTime dataCriacao = rs.getObject("dt_criacao", LocalDateTime.class);
            LocalDateTime dataAtualizacao = rs.getObject("dt_atualizacao", LocalDateTime.class);

            Usuario usuario = new UsuarioDao().getById(userId, false)
                    .orElseThrow(() -> new EntityNotFoundException("No user Found for id" + userId));
            return new Conta(id, usuario, nome, saldo, dataCriacao, dataAtualizacao);
        } catch (Exception e) {
            e.printStackTrace();
            DatabaseConnection.freeConnection();
            throw new SQLException("Error mapping Usuario");
        }
    }
}
