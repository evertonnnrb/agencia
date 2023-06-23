package com.agencia.jdbc.dao;

import com.agencia.entities.Comprador;
import com.agencia.excpetions.DBException;
import com.agencia.jdbc.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompradorDao implements Dao<Comprador> {
    private final Connection conn = ConnectionFactory.getConnection();

    @Override
    public boolean save(Comprador comprador) {
        String sql = "insert into comprador (cpf,nome)values(?,?)";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, comprador.getCpf());
            pst.setString(2, comprador.getNome());
            int i = pst.executeUpdate();
            return i > 0;
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public boolean update(Comprador comprador) {
        String sql = "update comprador set cpf=?, nome=? where id=?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, comprador.getCpf());
            pst.setString(2, comprador.getNome());
            pst.setInt(3, comprador.getId());
            return (pst.executeUpdate()) > 0;
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "delete from comprador where id = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, id);
            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public List<Comprador> findAll() {
        String sql = "select * from comprador limit 5";
        List<Comprador> compradorList = new ArrayList<>();
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Comprador comprador = new Comprador();
                comprador.setId(rs.getInt("id"));
                comprador.setCpf(rs.getString("cpf"));
                comprador.setNome(rs.getString("nome"));
                compradorList.add(comprador);
            }
            return compradorList;
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    public Comprador findById(int id) {
        String sql = "select nome,cpf from comprador where id = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, id);
            Comprador comprador = createcomprador(pst);
            if (comprador != null) return comprador;
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
        return null;
    }

    public Comprador findByName(String name){
        String sql = "select * from comprador where nome like '%"+name+"%'";
        try(PreparedStatement pst = conn.prepareStatement(sql)){
            return createcomprador(pst);
        }catch (SQLException e){
            throw new DBException(e.getMessage());
        }
    }

    public Comprador findByCpf(String cpf){
        String sql = "select * from comprador where cpf = ?";
        try(PreparedStatement pst = conn.prepareStatement(sql)){
            pst.setString(1, cpf);
            return createcomprador(pst);
        }catch (SQLException e){
            throw new DBException(e.getMessage());
        }
    }

    public List<Comprador> findListLikeNames(String name) {
        String sql = "select * from comprador where nome like ? ";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, name);
            return createCompradorList(pst);
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    private static Comprador createcomprador(PreparedStatement pst) throws SQLException {
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            Comprador comprador = new Comprador();
            comprador.setCpf(rs.getString("cpf"));
            comprador.setNome(rs.getString("nome"));
            return comprador;
        }
        rs.close();
        return null;
    }

    private static List<Comprador> createCompradorList(PreparedStatement pst) throws SQLException {
        ResultSet rs = pst.executeQuery();
        List<Comprador> compradorList = new ArrayList<>();
        while (rs.next()) {
            Comprador comprador = new Comprador();
            comprador.setId(rs.getInt("id"));
            comprador.setNome(rs.getString("nome"));
            comprador.setCpf(rs.getString("cpf"));
            compradorList.add(comprador);
        }
        rs.close();
        return compradorList;
    }
}
