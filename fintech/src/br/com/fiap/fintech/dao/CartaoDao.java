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
import br.com.fiap.fintech.models.Cartao;
import br.com.fiap.fintech.models.Conta;
import br.com.fiap.fintech.models.EBandeiraCartao;
import br.com.fiap.fintech.models.ETipoCartao;
import br.com.fiap.fintech.singleton.DatabaseConnection;

public class CartaoDao implements IBaseDao<Cartao> {
    private static final String TABLE_NAME = "fintech_admin.T_FNT_CARTAO";

    @Override
    public Optional<Cartao> getById(Long id, boolean closeConnection) {
        final String sql = String.format("SELECT * FROM %s WHERE cd_cartao = ?", TABLE_NAME);
        final Connection con = DatabaseConnection.getConnection();
        Cartao cartao = null;

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    cartao = mapResultToCartao(rs);
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
    public List<Cartao> getAll(boolean closeConnection) {
        String sql = String.format("SELECT * FROM %s", TABLE_NAME);
        final Connection con = DatabaseConnection.getConnection();
        List<Cartao> usuarios = new ArrayList<>();

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    usuarios.add(mapResultToCartao(rs));
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
    public void save(Cartao cartao, boolean closeConnection) {
        final String sql = String.format("INSERT INTO %s (cd_tipo_cartao, cd_conta, nr_cartao, nm_cartao,  dt_validade, bn_bandeira)  VALUES  (?, ?, ?, ?, ?, ?)", TABLE_NAME);
        final Connection con = DatabaseConnection.getConnection();

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setLong(1, cartao.getTipoCartao().getValue());
            stmt.setLong(2, cartao.getConta().getId());
            stmt.setLong(3, cartao.getNumero());
            stmt.setString(4, cartao.getNome());
            stmt.setObject(5, cartao.getValidade());
            stmt.setInt(6, cartao.getBandeira().getValue());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            DatabaseConnection.freeConnection();
        }

        if (closeConnection)
            DatabaseConnection.freeConnection();
    }

    @Override
    public void updateById(Long id, Cartao cartao, boolean closeConnection) {
        final String sql = String.format("UPDATE %s SET cd_tipo_cartao = ?, bn_bandeira = ?,  nm_cartao = ?, dt_atualizacao = ? WHERE cd_cartao = ?", TABLE_NAME);
        final Connection con = DatabaseConnection.getConnection();

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, cartao.getTipoCartao().getValue());
            stmt.setInt(2, cartao.getBandeira().getValue());
            stmt.setString(3, cartao.getNome());
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
        String sql = String.format("DELETE FROM %s WHERE cd_cartao = ?", TABLE_NAME);
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

    private Cartao mapResultToCartao(ResultSet rs) throws SQLException {
        try {
            Long id = rs.getLong("cd_cartao");
            ETipoCartao tipoCartao = ETipoCartao.valueOf(rs.getInt("cd_tipo_cartao"));
            Long contaId = rs.getLong("cd_conta");
            Long numeroCartao = rs.getLong("nr_cartao");
            String nomeCartao = rs.getString("nm_cartao");
            LocalDate dataValidade = rs.getObject("dt_validade", LocalDate.class);
            EBandeiraCartao bandeira = EBandeiraCartao.valueOf(rs.getInt("bn_bandeira"));
            LocalDateTime dataCriacao = rs.getObject("dt_criacao", LocalDateTime.class);
            LocalDateTime dataAtualizacao = rs.getObject("dt_atualizacao", LocalDateTime.class);

            Conta conta = new ContaDao().getById(contaId, false)
                    .orElseThrow(() -> new EntityNotFoundException("No conta fount for id" + contaId));

            return new Cartao(id, tipoCartao, conta, nomeCartao, numeroCartao, dataValidade, bandeira, dataCriacao, dataAtualizacao);
        } catch (Exception e) {
            e.printStackTrace();
            DatabaseConnection.freeConnection();
            throw new SQLException("Error mapping Cartao");
        }
    }
}
