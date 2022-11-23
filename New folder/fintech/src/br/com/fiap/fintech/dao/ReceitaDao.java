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
import br.com.fiap.fintech.models.Receita;
import br.com.fiap.fintech.singleton.DatabaseConnection;

public class ReceitaDao implements IBaseDao<Receita> {
    private static final String TABLE_NAME = "fintech_admin.T_FNT_RECEITA";

    @Override
    public Optional<Receita> getById(Long id, boolean closeConnection) {
        final String sql = String.format("SELECT * FROM %s WHERE cd_receita = ?", TABLE_NAME);
        final Connection con = DatabaseConnection.getConnection();
        Receita receita = null;

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    receita = mapResultToReceita(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            DatabaseConnection.freeConnection();
        }

        if (closeConnection)
            DatabaseConnection.freeConnection();

        return Optional.ofNullable(receita);
    }

    @Override
    public List<Receita> getAll(boolean closeConnection) {
        String sql = String.format("SELECT * FROM %s", TABLE_NAME);
        final Connection con = DatabaseConnection.getConnection();
        List<Receita> usuarios = new ArrayList<>();

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    usuarios.add(mapResultToReceita(rs));
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

    @Override
    public void save(Receita receita, boolean closeConnection) {
        final String sql = String.format("INSERT INTO %s (cd_conta, nm_receita, dc_receita)  VALUES  (?, ?, ?)", TABLE_NAME);
        final Connection con = DatabaseConnection.getConnection();

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setLong(1, receita.getConta().getId());
            stmt.setString(2, receita.getNome());
            stmt.setString(3, receita.getDescricao());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            DatabaseConnection.freeConnection();
        }

        if (closeConnection)
            DatabaseConnection.freeConnection();
    }

    @Override
    public void updateById(Long id, Receita receita, boolean closeConnection) {
        final String sql = String.format("UPDATE %s SET nm_receita = ?, dc_receita = ?, dt_atualizacao = ? WHERE cd_receita = ?", TABLE_NAME);
        final Connection con = DatabaseConnection.getConnection();

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, receita.getNome());
            stmt.setString(2, receita.getDescricao());
            stmt.setObject(3, LocalDateTime.now());
            stmt.setLong(4, id);
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
        String sql = String.format("DELETE FROM %s WHERE cd_receita = ?", TABLE_NAME);
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

    private Receita mapResultToReceita(ResultSet rs) throws SQLException {
        try {
            Long id = rs.getLong("cd_receita");
            Long contaId = rs.getLong("cd_conta");
            String nomeReceita = rs.getString("nm_receita");
            String descricao = rs.getString("dc_receita");
            LocalDateTime dataCriacao = rs.getObject("dt_criacao", LocalDateTime.class);
            LocalDateTime dataAtualizacao = rs.getObject("dt_atualizacao", LocalDateTime.class);

            Conta conta = new ContaDao().getById(contaId, false)
                    .orElseThrow(() -> new EntityNotFoundException("No conta fount for id" + contaId));

            return new Receita(id, nomeReceita, conta, descricao, dataCriacao, dataAtualizacao);
        } catch (Exception e) {
            e.printStackTrace();
            DatabaseConnection.freeConnection();
            throw new SQLException("Error mapping Receita");
        }
    }
}
