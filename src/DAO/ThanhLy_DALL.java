/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.Sach;
import DTO.ThanhLySach;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tiena
 */
public class ThanhLy_DALL {
    
    public List<ThanhLySach> selectAll() {
        List<ThanhLySach> thanhLySachs = new ArrayList<>();
        Connection connection = KetNoiSQL.getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = "select * from ThanhLySach";
            System.out.println(query);
			
            ResultSet rs =  statement.executeQuery(query);
			
            while(rs.next()) {
                String maThanhLySach = rs.getString("maThanhLySach");
                String maSach = rs.getString("maSach");
                int soLuongSachHong = rs.getInt("soLuongSachHong");
                String lyDoThanhLy = rs.getString("lyDoThanhLy");
                LocalDate ngayThanhLy = rs.getDate("ngayThanhLy").toLocalDate();
                String ghiChu = rs.getString("ghiChu");
                double tongTienThanhLy = rs.getDouble("tongTienThanhLy");
                ThanhLySach thanhLySach = new ThanhLySach(maThanhLySach, maSach, soLuongSachHong, lyDoThanhLy, ngayThanhLy, ghiChu, tongTienThanhLy);
                thanhLySachs.add(thanhLySach);
            }
            rs.close();
            statement.close();
            connection.close();
            return thanhLySachs;
			
        } catch (SQLException e) {
		System.out.println(e);
        }
        return thanhLySachs;
    }
    
    public List<Sach> selectidBook(){
        List<Sach>  maSachToTenSachMap = new ArrayList<>();
        Connection connection = KetNoiSQL.getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = "select * from ThongtinSach";
            System.out.println(query);
			
            ResultSet rss =  statement.executeQuery(query);
			
            while(rss.next()) {
                String masach = rss.getString("maSach");
                String tensach = rss.getString("tenSach");
                Sach book = new Sach();
                book.setMaSach(masach);
                book.setTenSach(tensach);
                maSachToTenSachMap.add(book);
            }
            rss.close();
            statement.close();
            connection.close();
            return maSachToTenSachMap;
			
        } catch (SQLException e) {
		System.out.println(e);
        }
        return maSachToTenSachMap;
    }
    
    public boolean add(ThanhLySach thanhly) {
        Connection connection = KetNoiSQL.getConnection();

        try {
            // The SQL query with placeholders (?)
            String query = "INSERT INTO ThanhLySach (maSach, soLuongSachHong, lyDoThanhLy, ngayThanhLy, ghiChu, tongTienThanhLy) VALUES (?, ?, ?, ?, ?, ?, ?)";
            
            // Create a PreparedStatement
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Set values for the placeholders
            preparedStatement.setString(2, thanhly.getMaSach());
            preparedStatement.setInt(3, thanhly.getSoLuongSachHong());
            preparedStatement.setString(4, thanhly.getLyDoThanhLy());
            preparedStatement.setDate(5, java.sql.Date.valueOf(thanhly.getNgayThanhLy())); // Assuming ngayThanhLy is of LocalDate type
            preparedStatement.setString(6, thanhly.getGhiChu());
            preparedStatement.setDouble(7, thanhly.getTongTienThanhLy());

            // Execute the query
            preparedStatement.executeUpdate();
            
            // Close the PreparedStatement
            preparedStatement.close();
            connection.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately in your application
        }
        return false;
    }
    
    public boolean Update(ThanhLySach thanhly){
        Connection connection = KetNoiSQL.getConnection();

        try {
            // The SQL query with placeholders (?)
            String query = "UPDATE ThanhLySach "+
			    "SET maSach=?, soLuongSachHong=?, lyDoThanhLy=?, ghiChu= ?, tongTienThanhLy = ?"+
			    "WHERE maThanhLySach=?";
            
            // Create a PreparedStatement
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Set values for the placeholders
            preparedStatement.setString(1, thanhly.getMaSach());
            preparedStatement.setInt(2, thanhly.getSoLuongSachHong());
            preparedStatement.setString(3, thanhly.getLyDoThanhLy());
            preparedStatement.setString(4, thanhly.getGhiChu()); // Assuming ngayThanhLy is of LocalDate type
            preparedStatement.setDouble(5, thanhly.getTongTienThanhLy());
            preparedStatement.setString(6, thanhly.getMaThanhLySach());

            // Execute the query
            preparedStatement.executeUpdate();
            
            // Close the PreparedStatement
            preparedStatement.close();
            connection.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately in your application
        }
        return false;
    }
    
    public int GetSoLuongSachHongByIdBook(String id){
        int Soluong = 0;
        Connection connection = KetNoiSQL.getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = "select soLuongSachHong "
                    + "from khoSach "
                    + "where maSach = "+id;
            System.out.println(query);
			
            ResultSet rss =  statement.executeQuery(query);
			
            while(rss.next()) {
                Soluong= rss.getInt("soLuongSachHong");
            }
            rss.close();
            statement.close();
            connection.close();
            return Soluong;
			
        } catch (SQLException e) {
		System.out.println(e);
        }
        return Soluong;
    }
    
    public int GetSoLuongSachHongByIdThanhLy(String id){
        int Soluong = 0;
        Connection connection = KetNoiSQL.getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = "select soLuongSachHong "
                    + "from ThanhLySach "
                    + "where maSach = "+id;
            System.out.println(query);
			
            ResultSet rss =  statement.executeQuery(query);
			
            while(rss.next()) {
                Soluong= rss.getInt("soLuongSachHong");
            }
            rss.close();
            statement.close();
            connection.close();
            return Soluong;
			
        } catch (SQLException e) {
		System.out.println(e);
        }
        return Soluong;
    }
    
    public void UpdateKhoAfterAction(ThanhLySach thanhly){
        Connection connection = KetNoiSQL.getConnection();

        try {
            // The SQL query with placeholders (?)
            String query = "UPDATE khoSach "+
			    "SET soLuongSachHong = soLuongSachHong - ?"+
			    "WHERE maThanhLySach=?";
            
            // Create a PreparedStatement
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Set values for the placeholders
            preparedStatement.setInt(1, thanhly.getSoLuongSachHong());
            preparedStatement.setString(2, thanhly.getMaThanhLySach());
            

            // Execute the query
            preparedStatement.executeUpdate();
            
            // Close the PreparedStatement
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately in your application
        }
        
    }
}
