package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Cart;
import model.ConnectDB;
import model.Payment;
import model.Product;

public class DAO {

    Connection con = ConnectDB.getConnection();
    PreparedStatement ps;
    Statement st;
    ResultSet rs;

    public boolean insertProduct(Product p) {
        String sql = "insert into product (name, price, image) values (?, ?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getName());
            ps.setDouble(2, p.getPrice());
            ps.setBytes(3, p.getImage());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public void getallProduct(JTable table) {
        String sql = "select * from product order by id asc";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row;
            while (rs.next()) {
                row = new Object[4];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getDouble(3);
                row[3] = rs.getBytes(4);
                model.addRow(row);
            }
        } catch (Exception e) {
        }
    }

    public boolean update(Product p) {
        String sql = "update product set name = ?, price = ? where id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getName());
            ps.setDouble(2, p.getPrice());
            ps.setInt(3, p.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            return false;
        }

    }

    public boolean delete(Product p) {
        try {
            ps = con.prepareStatement("delete from product where id = ?");
            ps.setInt(1, p.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            return false;
        }

    }

    public int getMaxRowAOrderTable() {
        int row = 0;
        try {
            st = con.createStatement();
            rs = st.executeQuery("Select max(cid) from cart");
            while (rs.next()) {
                row = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row + 1;
    }

    public boolean isProductExist(int cid, int pid) {
        try {
            ps = con.prepareStatement("select * from cart where cid = ? and pid = ?");
            ps.setInt(1, cid);
            ps.setInt(2, pid);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean insertCart(Cart c) {
        String sql = "insert into cart (cid, pid, pName, qty, price, total) values (?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, c.getId());
            ps.setInt(2, c.getPid());
            ps.setString(3, c.getpName());
            ps.setInt(4, c.getQty());
            ps.setDouble(5, c.getPrice());
            ps.setDouble(6, c.getTotal());
            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            return false;
        }
    }

    public int getMaxRowAPaymentTable() {
        int row = 0;
        try {
            st = con.createStatement();
            rs = st.executeQuery("Select max(pid) from payment");
            while (rs.next()) {
                row = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row + 1;
    }

    public int getMaxRowACartTable() {
        int row = 0;
        try {
            st = con.createStatement();
            rs = st.executeQuery("Select max(cid) from cart");
            while (rs.next()) {
                row = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }

    public double subTotal() {
        double subtotal = 0.0;
        int cid = getMaxRowACartTable();
        try {
            st = con.createStatement();
            rs = st.executeQuery("select sum(total) as 'total' from cart where cid = '" + cid + "'");
            if (rs.next()) {
                subtotal = rs.getDouble(1);
            }
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return subtotal;
    }

    public void getProductFromCart(JTable table) {
        int cid = getMaxRowACartTable();
        String sql = "select * from cart where cid = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, cid);
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row;
            while (rs.next()) {
                row = new Object[6];
                row[0] = rs.getInt(1);
                row[1] = rs.getInt(2);
                row[2] = rs.getString(3);
                row[3] = rs.getInt(4);
                row[4] = rs.getDouble(5);
                row[5] = rs.getDouble(6);
                model.addRow(row);
            }
        } catch (Exception e) {
        }
    }
    
    public boolean insertPayment(Payment p) {
        String sql = "insert into payment (cName, proid, pName, total, pdate) values (?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
//            ps.setInt(1, p.getPid());
            ps.setString(1, p.getcName());
            ps.setString(2, p.getProId());
            ps.setString(3, p.getProName());
            ps.setDouble(4, p.getTotal());
            ps.setString(5, p.getDate());
            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean deleteCart(int cid) {
        try {
            ps = con.prepareStatement("delete from cart where cid = ?");
            ps.setInt(1, cid);
            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            return false;
        }
    }
    
    public void getPaymentDetail(JTable table) {
        String sql = "select * from payment order by pid desc";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row;
            while (rs.next()) {
                row = new Object[6];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getDouble(5);
                row[5] = rs.getString(6);
                model.addRow(row);
            }
        } catch (Exception e) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
