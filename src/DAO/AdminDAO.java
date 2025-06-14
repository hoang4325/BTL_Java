package DAO;

import form.ForgotPassword;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Admin;
import model.ConnectDB;

public class AdminDAO {

    Connection con = ConnectDB.getConnection();
    PreparedStatement ps;
    Statement st;
    ResultSet rs;

    public int getMaxRowAdminTable() {
        int row = 0;
        try {
            st = con.createStatement();
            rs = st.executeQuery("Select max(id) from admin");
            while (rs.next()) {
                row = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row + 1;
    }
    
    public boolean isAdminNameExist(String username){
        try {
            ps = con.prepareStatement("Select * from admin where username = ?");
            ps.setString(1, username);
            rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean insert(Admin admin){
        String sql = "insert into admin (id, username, password, s_ques, ans) values (?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, admin.getId());
            ps.setString(2, admin.getUsername());
            ps.setString(3, admin.getPassword());
            ps.setString(4, admin.getsQues());
            ps.setString(5, admin.getAns());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public boolean login(String username, String password){
        try {
            ps = con.prepareStatement("select * from admin where username = ? and password = ?");
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean getSecurity(String username){
        try {
            ps = con.prepareStatement("select * from admin where username = ?");
            ps.setString(1, username);
            rs = ps.executeQuery();
            if(rs.next()){
                ForgotPassword.jTextField3.setText(rs.getString(4));
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean getAns(String username, String newAns){
        try {
            ps = con.prepareStatement("select * from admin where username = ?");
            ps.setString(1, username);
            rs = ps.executeQuery();
            if(rs.next()){
                String oldAns = rs.getString(5);
                if(newAns.equals(oldAns)){
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean setPassword(String username, String password){
        String sql = "update admin set password = ? where username = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, password);
            ps.setString(2, username);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        }
    }
}
