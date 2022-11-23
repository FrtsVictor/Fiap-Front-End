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
import br.com.fiap.fintech.models.CategoriaLancamento;
import br.com.fiap.fintech.models.Usuario;
import br.com.fiap.fintech.singleton.DatabaseConnection;

public class CategoriaLancamentoDao implements IBaseDao<CategoriaLancamento> {
    private static final String TABLE_NAME = "fintech_admin.T_FNT_CATEGORIA";

    @Override
    public Optional<CategoriaLancamento> getById(Long id, boolean closeConnection) {
        final String sql = String.format("SELECT * FROM %s WHERE cd_categoria = ?", TABLE_NAME);
        final Connection con = DatabaseConnection.getConnection();
        CategoriaLancamento cartao = null;

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    cartao = mapResultToCategoriaLancamento(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            DatabaseConnection.freeConnection();
        }

        if (closeConnection)
            DatabaseConnection.freeConnection();

        return Optional.ofNullable(cartao);
    }

    @Override
    public List<CategoriaLancamento> getAll(boolean closeConnection) {
        String sql = String.format("SELECT * FROM %s", TABLE_NAME);
        final Connection con = DatabaseConnection.getConnection();
        List<CategoriaLancamento> usuarios = new ArrayList<>();

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    usuarios.add(mapResultToCategoriaLancamento(rs));
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
    public void save(CategoriaLancamento cartao, boolean closeConnection) {
        final String sql = String.format("INSERT INTO %s (nm_categoria, dc_categoria, cd_usuario)  VALUES  (?, ?, ? )", TABLE_NAME);
        final Connection con = DatabaseConnection.getConnection();

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, cartao.getNome());
            stmt.setString(2, cartao.getDescricao());
            stmt.setLong(3, cartao.getUsuario().getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            DatabaseConnection.freeConnection();
        }

        if (closeConnection)
            DatabaseConnection.freeConnection();
    }

    @Override
    public void updateById(Long id, CategoriaLancamento categoria, boolean closeConnection) {
        final String sql = String.format("UPDATE %s SET nm_categoria = ?, dc_categoria = ?, dt_atualizacao = ? WHERE cd_categoria = ?", TABLE_NAME);
        final Connection con = DatabaseConnection.getConnection();

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, categoria.getNome());
            stmt.setString(2, categoria.getDescricao());
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
        String sql = String.format("DELETE FROM %s WHERE cd_categoria = ?", TABLE_NAME);
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

    private CategoriaLancamento mapResultToCategoriaLancamento(ResultSet rs) throws SQLException {
        try {
            Long id = rs.getLong("cd_categoria");
            Long usuarioId = rs.getLong("cd_usuario");
            String nome = rs.getString("nm_categoria");
            String descricao = rs.getString("dc_categoria");
            LocalDateTime dataCriacao = rs.getObject("dt_criacao", LocalDateTime.class);
            LocalDateTime dataAtualizacao = rs.getObject("dt_atualizacao", LocalDateTime.class);

            Usuario usuario = new UsuarioDao().getById(usuarioId, false)
                    .orElseThrow(() -> new EntityNotFoundException("No user Found for id" + usuarioId));

            return new CategoriaLancamento(id, nome, descricao, usuario, dataCriacao, dataAtualizacao);
        } catch (Exception e) {
            e.printStackTrace();
            DatabaseConnection.freeConnection();
            throw new SQLException("Error mapping CategoriaLancamento");
        }
    }
}
