/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author admin
 */
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import model.ConnectDB;

public class ProductDAO {
    private Connection con;

    public ProductDAO(Connection con) {
        this.con = con;
    }

    public boolean insertProduct(String name, double price, String imagePath) {
        String sql = "INSERT INTO product (name, price, image) VALUES (?, ?, ?)";
        try (
            PreparedStatement ps = con.prepareStatement(sql);
            FileInputStream fis = new FileInputStream(new File(imagePath));
        ) {
            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setBinaryStream(3, fis, new File(imagePath).length());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Lỗi insert: " + name + " → " + e.getMessage());
            return false;
        }
    }

    public void insertSampleProducts() {
        List<Object[]> products = new ArrayList<>();
        products.add(new Object[]{"Cà phê sữa đá", 25000.0, "images/ca_phe_sua_da.png"});
        products.add(new Object[]{"Cà phê đen đá", 20000.0, "images/ca_phe_den_da.png"});
        products.add(new Object[]{"Bạc xỉu", 28000.0, "images/bac_xiu.png"});
        products.add(new Object[]{"Trà đào cam sả", 35000.0, "images/tra_dao_cam_sa.png"});
        products.add(new Object[]{"Trà chanh mật ong", 30000.0, "images/tra_chanh_mat_ong.png"});
        products.add(new Object[]{"Sinh tố bơ", 40000.0, "images/sinh_to_bo.png"});
        products.add(new Object[]{"Nước ép cà rốt", 30000.0, "images/nuoc_ep_ca_rot.png"});
        products.add(new Object[]{"Soda việt quất", 35000.0, "images/soda_viet_quat.png"});
        products.add(new Object[]{"Bánh tiramisu", 45000.0, "images/banh_tiramisu.png"});
        products.add(new Object[]{"Bánh mousse chanh dây", 42000.0, "images/banh_mousse_chanh_day.png"});

        for (Object[] p : products) {
            insertProduct((String) p[0], (Double) p[1], (String) p[2]);
        }
    }
    public static void main(String[] args) {
        Connection con = ConnectDB.getConnection();
        ProductDAO dao = new ProductDAO(con);
        dao.insertSampleProducts();
    }
}

