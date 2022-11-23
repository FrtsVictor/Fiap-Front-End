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
import br.com.fiap.fintech.models.Cartao;
import br.com.fiap.fintech.models.CategoriaLancamento;
import br.com.fiap.fintech.models.Conta;
import br.com.fiap.fintech.models.ETipoLancamento;
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
        final String sql = String.format("INSERT INTO %s (cd_categoria, cd_conta, cd_cartao,  cd_tipo_pagamento, vl_lancamento, dc_lancamento)  VALUES  (?, ?, ?, ?, ?, ?)", TABLE_NAME);
        final Connection con = DatabaseConnection.getConnection();

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setLong(1, lancamento.getCategoriaLancamento().getId());
            stmt.setLong(2, lancamento.getConta().getId());

            if (lancamento.getCartao().isPresent()) {
                stmt.setLong(3, lancamento.getCartao().get().getId());
            } else {
                stmt.setObject(3, null);
            }

            stmt.setInt(4, lancamento.getTipoLancamento().getValue());
            stmt.setDouble(5, lancamento.getValor());
            stmt.setString(6, lancamento.getDescricao());
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
        final String sql = String.format("UPDATE %s SET cd_categoria = ?, cd_cartao = ?,  cd_tipo_pagamento = ?, vl_lancamento = ?, dc_lancamento = ?, dt_atualizacao = ? WHERE cd_lancamento = ?", TABLE_NAME);
        final Connection con = DatabaseConnection.getConnection();

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setLong(1, lancamento.getCategoriaLancamento().getId());

            if (lancamento.getCartao().isPresent()) {
                stmt.setLong(2, lancamento.getCartao().get().getId());
            } else {
                stmt.setObject(2, null);
            }

            stmt.setInt(3, lancamento.getTipoLancamento().getValue());
            stmt.setDouble(4, lancamento.getValor());
            stmt.setString(5, lancamento.getDescricao());
            stmt.setObject(6, LocalDateTime.now());
            stmt.setLong(7, id);
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
            Long categoriaId = rs.getLong("cd_categoria");
            Long contaId = rs.getLong("cd_conta");
            Long cartaoId = rs.getLong("cd_cartao");
            ETipoLancamento tipoLancamento = ETipoLancamento.valueOf(rs.getInt("cd_tipo_pagamento"));
            Double valor = rs.getDouble("vl_lancamento");
            String descricao = rs.getString("dc_lancamento");
            LocalDateTime dataCriacao = rs.getObject("dt_criacao", LocalDateTime.class);
            LocalDateTime dataAtualizacao = rs.getObject("dt_atualizacao", LocalDateTime.class);

            Conta conta = new ContaDao().getById(contaId, false)
                    .orElseThrow(() -> new EntityNotFoundException("No conta found for id" + contaId));

            CategoriaLancamento categoriaLancamento = new CategoriaLancamentoDao().getById(categoriaId, false)
                    .orElseThrow(() -> new EntityNotFoundException("No categoriaLancamento found for id" + categoriaId));

            Cartao cartao = new CartaoDao().getById(cartaoId, false)
                    .orElseThrow(() -> new EntityNotFoundException("No cartao found for id" + cartaoId));

            return new Lancamento(id, categoriaLancamento, conta, tipoLancamento, valor, descricao, Optional.ofNullable(cartao), dataCriacao, dataAtualizacao);
        } catch (Exception e) {
            e.printStackTrace();
            DatabaseConnection.freeConnection();
            throw new SQLException("Error mapping Lancamento");
        }
    }
}
