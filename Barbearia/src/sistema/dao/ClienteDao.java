package sistema.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sistema.bd.Conexao;
import sistema.model.Cliente;

public class ClienteDao extends Conexao implements ICrud<Cliente> {

    private PreparedStatement ps = null;

    @Override
    public List<Cliente> listar(String nome) {
        try {
            List<Cliente> lista = new LinkedList<>();
            String sql = "SELECT * FROM cliente WHERE cliente_nome LIKE ?";
            ps = getPreparedStatement(sql);
            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cliente c = new Cliente();
                preencherLista(c, rs);
                lista.add(c);
            }
            return lista;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Cliente salvar(Cliente item) {
        String sql = "INSERT INTO cliente (cliente_nome, cliente_cpf, cliente_rg, cliente_data_nascimento, cliente_endereco, "
                + "cliente_telefone, cliente_email) VALUES (?,?,?,?,?,?,?)";
        try {
            ps = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, item.getNome());
            ps.setString(2, item.getCPF());
            ps.setString(3, item.getRG());
            ps.setDate(4, java.sql.Date.valueOf(item.getDataNascimento()));
            ps.setString(5, item.getEndereco());
            ps.setString(6, item.getTelefone());
            ps.setString(7, item.getEmail());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            Cliente idCliente = new Cliente();
            if (rs.next()) {
                idCliente.setId(rs.getInt(1));
            }
            rs.close();
            ps.close();
            return idCliente;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);

        }
        return null;
    }

    @Override
    public Cliente alterar(Cliente item) {
        String sql = "UPDATE cliente SET cliente_nome = ?, cliente_cpf = ?, cliente_rg = ?,"
                + " cliente_data_nascimento = ?, cliente_endereco = ?, cliente_telefone = ?, cliente_email = ? WHERE cliente_id = ?";
        try {
            ps = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, item.getNome());
            ps.setString(2, item.getCPF());
            ps.setString(3, item.getRG());
            ps.setDate(4, java.sql.Date.valueOf(item.getDataNascimento()));
            ps.setString(5, item.getEndereco());
            ps.setString(6, item.getTelefone());
            ps.setString(7, item.getEmail());
            ps.setInt(8, item.getId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            Cliente idCliente = new Cliente();
            if (rs.next()) {
                idCliente.setId(rs.getInt(1));
            }
            rs.close();
            ps.close();
            return idCliente;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);

        }

        return null;
    }

    @Override
    public Boolean excluir(Integer id) {
        String sql = "SELECT (SELECT COUNT(agenda_id) FROM agenda WHERE cliente_id = ?) as c1, "
                + "(SELECT COUNT(venda_id) FROM venda WHERE cliente_id = ?) as c2";
        try {
            ps = getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            if (rs.getInt("c1") > 0 || rs.getInt("c2") > 0) {
                JOptionPane.showMessageDialog(null, "Registro não pode ser deletado\n existem registros relacionados", "Alerta", JOptionPane.WARNING_MESSAGE);
            } else {
                sql = "DELETE FROM cliente WHERE cliente_id = ?";
                ps = getConnection().prepareStatement(sql);
                ps.setInt(1, id);
                int contador = ps.executeUpdate();
                return contador > 0;
            }
            rs.close();
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private void preencherLista(Cliente f, ResultSet rs) throws SQLException {
        f.setId(rs.getInt("cliente_id"));
        f.setNome(rs.getString("cliente_nome"));
        f.setCPF(rs.getString("cliente_cpf"));
        f.setRG(rs.getString("cliente_rg"));
        Date d = new Date(0);
        d = rs.getDate("cliente_data_nascimento");
        Instant instant = Instant.ofEpochMilli(d.getTime());
        LocalDate localDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
        f.setDataNascimento(localDate);
        f.setTelefone(rs.getString("cliente_telefone"));
        f.setEmail(rs.getString("cliente_email"));
        f.setEndereco(rs.getString("cliente_endereco"));
    }

    public Boolean checkCPFCadastro(String cpf) {
        try {
            String sql = "SELECT COUNT(cliente_id) contador FROM cliente WHERE cliente_cpf = ? ";
            ps = getPreparedStatement(sql);
            ps.setString(1, cpf);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt("contador") >= 1;

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }

    public Boolean checkCPFUpdate(String cpf, Integer id) {
        try {
            String sql = "SELECT COUNT(cliente_id) contador FROM cliente WHERE cliente_cpf = ? AND cliente_id <> ?";
            ps = getPreparedStatement(sql);
            ps.setString(1, cpf);
            ps.setInt(2, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt("contador") >= 1;

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    public Cliente listarId(Integer id) {
        return null;
    }

    public List<Cliente> aniversariantes(int selectedIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
