package br.com.fiap.fintech.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.fiap.fintech.models.Usuario;
import br.com.fiap.fintech.singleton.DatabaseConnection;

public class UsuarioDao implements IBaseDao<Usuario> {
    private static final String TABLE_NAME = "fintech_admin.T_FNT_USUARIO";

    @Override
    public Optional<Usuario> getById(Long id, boolean closeConnection) {
        final String sql = String.format("SELECT * FROM %s WHERE cd_usuario = ?", TABLE_NAME);
        final Connection con = DatabaseConnection.getConnection();
        Usuario usuario = null;

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    usuario = mapResultToUsuario(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            DatabaseConnection.freeConnection();
        }

        if (closeConnection)
            DatabaseConnection.freeConnection();

        return Optional.ofNullable(usuario);
    }    
    
    public Optional<Usuario> getByEmail(String email, boolean closeConnection) {
        final String sql = String.format("SELECT * FROM %s WHERE em_email= ?", TABLE_NAME);
        final Connection con = DatabaseConnection.getConnection();
        Usuario usuario = null;

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    usuario = mapResultToUsuario(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            DatabaseConnection.freeConnection();
        }

        if (closeConnection)
            DatabaseConnection.freeConnection();

        return Optional.ofNullable(usuario);
    }

    @Override
    public List<Usuario> getAll(boolean closeConnection) {
        String sql = String.format("SELECT * FROM %s", TABLE_NAME);
        final Connection con = DatabaseConnection.getConnection();
        List<Usuario> usuarios = new ArrayList<>();

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    usuarios.add(mapResultToUsuario(rs));
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
    public void save(Usuario usuario, boolean closeConnection) {
        final String sql = String.format("INSERT INTO %s (ps_senha, nm_usuario, em_email)  VALUES  (?, ?, ?, ?)", TABLE_NAME);
        final Connection con = DatabaseConnection.getConnection();

        try (PreparedStatement stmt = con.prepareStatement(sql)) {            
            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getNome());
            stmt.setString(3, usuario.getSenha());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            DatabaseConnection.freeConnection();
        }

        if (closeConnection)
            DatabaseConnection.freeConnection();
    }

    @Override
    public void updateById(Long id, Usuario usuario, boolean closeConnection) {
        final String sql = String.format("UPDATE %s SET ps_senha = ?, nm_usuario = ?, em_email = ? WHERE cd_usuario = ?", TABLE_NAME);
        final Connection con = DatabaseConnection.getConnection();

        try (PreparedStatement stmt = con.prepareStatement(sql)) {            
            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getNome());
            stmt.setString(3, usuario.getSenha());
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
        String sql = String.format("DELETE FROM %s WHERE cd_usuario = ?", TABLE_NAME);
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

    private Usuario mapResultToUsuario(ResultSet rs) throws SQLException {
        try {
            Long id = rs.getLong("cd_usuario");            
            String email = rs.getString("em_email");
            String nome = rs.getString("nm_usuario");
            String senha = rs.getString("ps_senha");
            LocalDateTime dataCriacao = rs.getObject("dt_criacao", LocalDateTime.class);
            LocalDateTime dataAtualizacao = rs.getObject("dt_atualizacao", LocalDateTime.class);
            return new Usuario(id, email, nome, senha, dataCriacao, dataAtualizacao);
        } catch (Exception e) {
            e.printStackTrace();
            DatabaseConnection.freeConnection();
            throw new SQLException("Error mapping Usuario");
        }
    }
}
