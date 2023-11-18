/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ChiTietPhieuMuon;
import DTO.PhieuMuon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

/**
 *
 * @author tiena
 */
public class PhieuTra_DAL {
    public List<PhieuMuon> loaddata(){
        List<PhieuMuon> phieuMuons = new ArrayList<>();
        Connection connection = KetNoiSQL.getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = "select * from PhieuMuon";
            System.out.println(query);
			
            ResultSet rs =  statement.executeQuery(query);
			
            while(rs.next()) {
                String id = rs.getString("maPhieuMuon");
                LocalDate date = rs.getDate("ngayMuon").toLocalDate();
                int soNgayMuon = rs.getInt("soNgayMuon");
                LocalDate hantra = rs.getDate("hanTraSach").toLocalDate();
                int SoLuongSach = rs.getInt("soLuongSach");
                String maTaiKhoan = rs.getString("maTaiKhoan");
                String maQLy = rs.getString("maQuanLy");
                String ghiChu = rs.getString("ghiChu");
                String trangthai = rs.getString("trangthai");
                PhieuMuon phieuMuon = new PhieuMuon(id, date, soNgayMuon, hantra, SoLuongSach, maTaiKhoan, maQLy, ghiChu,trangthai );
                phieuMuons.add(phieuMuon);
            }
            rs.close();
            statement.close();
            connection.close();
            return phieuMuons;
			
        } catch (SQLException e) {
		System.out.println(e);
        }
        return phieuMuons;
    }
    
    public List<ChiTietPhieuMuon> loaddataCT(String id){
        List<ChiTietPhieuMuon> chiTietPhieuMuons = new ArrayList<>();
        Connection connection = KetNoiSQL.getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = "select * from ChiTietPhieuMuon where maPhieuMuon = '"+id+"'";
            System.out.println(query);
			
            ResultSet rs =  statement.executeQuery(query);
			
            while(rs.next()) {
                String maSach = rs.getString("maSach");
                LocalDate ngayThucTra = null;

                // Check if ngayThucTra is not null before trying to convert
                java.sql.Date ngayThucTraSqlDate = rs.getDate("ngayThucTra");
                if (ngayThucTraSqlDate != null) {
                    ngayThucTra = ngayThucTraSqlDate.toLocalDate();
                }

                double tienPhat = rs.getDouble("tienPhat");
                String tinhTrang = rs.getString("tinhTrangSach");

                ChiTietPhieuMuon chiTietPhieuMuon = new ChiTietPhieuMuon();
                chiTietPhieuMuon.setMaSach(maSach);
                chiTietPhieuMuon.setNgayThuctra(ngayThucTra);
                chiTietPhieuMuon.setTienPhat(tienPhat);
                chiTietPhieuMuon.setTinhTrangSach(tinhTrang);

                chiTietPhieuMuons.add(chiTietPhieuMuon);
            }
            rs.close();
            statement.close();
            connection.close();
            return chiTietPhieuMuons;
			
        } catch (SQLException e) {
		System.out.println(e);
        }
        return chiTietPhieuMuons;
    }
    
    public void updateTraSach(String id, String idbook, String tinhtrang){
        Connection connection = KetNoiSQL.getConnection();
        
        
        try {

            // The SQL query with placeholders (?)
            String query = "UPDATE ChiTietPhieuMuon " +
                        "SET ngayThucTra = CURRENT_TIMESTAMP , tinhTrangSach= ? " +
                        "WHERE maPhieuMuon = ? AND maSach = ?";
            
            // Create a PreparedStatement
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Set values for the placeholders
            preparedStatement.setString(1, tinhtrang);

            preparedStatement.setString(2, id);
            preparedStatement.setString(3, idbook);

            System.out.println(query);

            // Execute the query
            preparedStatement.executeUpdate();
            
            // Close the PreparedStatement
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately in your application
        }
    }
    
    public String getTinhTrangNow(String id, String idbook) {
        Connection connection = KetNoiSQL.getConnection();
        String tinhtrang = "";

        try {
            Statement statement = connection.createStatement();
            String query = "SELECT tinhTrangSach FROM chiTietPhieuMuon WHERE maPhieuMuon = '" + id + "' AND maSach = '" + idbook + "'";
            System.out.println(query);

            ResultSet rs = statement.executeQuery(query);

            // Kiểm tra xem có dữ liệu trong ResultSet không
            if (rs.next()) {
                tinhtrang = rs.getString("tinhTrangSach");
            }

            rs.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println(e);
        }
        return tinhtrang;
    }
    
    public void updateTinhTrang(String id, String idbook, String tinhtrang){
        Connection connection = KetNoiSQL.getConnection();
        
        
        try {

            // The SQL query with placeholders (?)
            String query = "UPDATE ChiTietPhieuMuon " +
                        "SET tinhTrangSach= ? " +
                        "WHERE maPhieuMuon = ? AND maSach = ?";
            
            // Create a PreparedStatement
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Set values for the placeholders
            preparedStatement.setString(1, tinhtrang);

            preparedStatement.setString(2, id);
            preparedStatement.setString(3, idbook);

            System.out.println(query);

            // Execute the query
            preparedStatement.executeUpdate();
            
            // Close the PreparedStatement
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately in your application
        }
    }
    
    
    public void updateKho(int slgCon, int slgHong, String idsbook){
        Connection connection = KetNoiSQL.getConnection();
        
        
        try {

            // The SQL query with placeholders (?)
            String query = "UPDATE khoSach " +
                        "SET soLuongCon= soLuongCon + (?) , soLuongSachHong = soLuongSachHong + (?) "+
                        "WHERE maSach = ?";
            
            // Create a PreparedStatement
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Set values for the placeholders
            preparedStatement.setInt(1, slgCon);

            preparedStatement.setInt(2, slgHong);
            preparedStatement.setString(3, idsbook);

            System.out.println(query);

            // Execute the query
            preparedStatement.executeUpdate();
            
            // Close the PreparedStatement
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately in your application
        }
    }
    
//    public List<ChiTietPhieuMuon> getAlldate(String id){
//        List<ChiTietPhieuMuon> chiTietPhieuMuons = new ArrayList<>();
//        Connection connection = KetNoiSQL.getConnection();
//        try {
//            Statement statement = connection.createStatement();
//            String query = "select * from ChiTietPhieuMuon where maPhieuMuon = '"+id+"'";
//            System.out.println(query);
//			
//            ResultSet rs =  statement.executeQuery(query);
//			
//            while(rs.next()) {
//                String maSach = rs.getString("maSach");
//                LocalDate ngayThucTra = null;
//
//                // Check if ngayThucTra is not null before trying to convert
//                java.sql.Date ngayThucTraSqlDate = rs.getDate("ngayThucTra");
//                if (ngayThucTraSqlDate != null) {
//                    ngayThucTra = ngayThucTraSqlDate.toLocalDate();
//                }
//
//                double tienPhat = rs.getDouble("tienPhat");
//                String tinhTrang = rs.getString("tinhTrangSach");
//
//                ChiTietPhieuMuon chiTietPhieuMuon = new ChiTietPhieuMuon();
//                chiTietPhieuMuon.setMaSach(maSach);
//                chiTietPhieuMuon.setNgayThuctra(ngayThucTra);
//                chiTietPhieuMuon.setTienPhat(tienPhat);
//                chiTietPhieuMuon.setTinhTrangSach(tinhTrang);
//
//                chiTietPhieuMuons.add(chiTietPhieuMuon);
//            }
//            rs.close();
//            statement.close();
//            connection.close();
//            return chiTietPhieuMuons;
//			
//        } catch (SQLException e) {
//		System.out.println(e);
//        }
//        return chiTietPhieuMuons;
//    
//    }
    
    public void updateTTPhieuMuon(String id){
        Connection connection = KetNoiSQL.getConnection();
        
        
        try {

            // The SQL query with placeholders (?)
            String query = "UPDATE phieuMuon " +
                        "SET trangThai = N'Đã trả' "+
                        "WHERE maPhieuMuon = ?";
            
            // Create a PreparedStatement
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Set values for the placeholders
            
            preparedStatement.setString(1, id);

            System.out.println(query);

            // Execute the query
            preparedStatement.executeUpdate();
            
            // Close the PreparedStatement
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately in your application
        }
    }
    
    public List<PhieuMuon> search(String searchTerm){
        List<PhieuMuon> phieuMuons = new ArrayList<>();
        Connection connection = KetNoiSQL.getConnection();
        try {
            try (Statement statement = connection.createStatement()) {
                String query = "SELECT * FROM phieuMuon " +
                           " WHERE maPhieuMuon LIKE '%" + searchTerm + "%' " +
                           " OR maTaiKhoan  LIKE '%" + searchTerm + "%'";
                System.out.println(query);
                
                ResultSet rs =  statement.executeQuery(query);
			
                while(rs.next()) {
                    String id = rs.getString("maPhieuMuon");
                    LocalDate date = rs.getDate("ngayMuon").toLocalDate();
                    int soNgayMuon = rs.getInt("soNgayMuon");
                    LocalDate hantra = rs.getDate("hanTraSach").toLocalDate();
                    int SoLuongSach = rs.getInt("soLuongSach");
                    String maTaiKhoan = rs.getString("maTaiKhoan");
                    String maQLy = rs.getString("maQuanLy");
                    String ghiChu = rs.getString("ghiChu");
                    String trangthai = rs.getString("trangthai");
                    PhieuMuon phieuMuon = new PhieuMuon(id, date, soNgayMuon, hantra, SoLuongSach, maTaiKhoan, maQLy, ghiChu,trangthai );
                    phieuMuons.add(phieuMuon);
                }
            }
            connection.close();
            return phieuMuons;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return phieuMuons;
    }
    
    public List<PhieuMuon> loadPMintoTT(String select){
        List<PhieuMuon> phieuMuons = new ArrayList<>();
        Connection connection = KetNoiSQL.getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = "select * from PhieuMuon where trangThai = N'"+select+"'";
            System.out.println(query);
			
            ResultSet rs =  statement.executeQuery(query);
			
            while(rs.next()) {
                String id = rs.getString("maPhieuMuon");
                LocalDate date = rs.getDate("ngayMuon").toLocalDate();
                int soNgayMuon = rs.getInt("soNgayMuon");
                LocalDate hantra = rs.getDate("hanTraSach").toLocalDate();
                int SoLuongSach = rs.getInt("soLuongSach");
                String maTaiKhoan = rs.getString("maTaiKhoan");
                String maQLy = rs.getString("maQuanLy");
                String ghiChu = rs.getString("ghiChu");
                String trangthai = rs.getString("trangthai");
                PhieuMuon phieuMuon = new PhieuMuon(id, date, soNgayMuon, hantra, SoLuongSach, maTaiKhoan, maQLy, ghiChu,trangthai );
                phieuMuons.add(phieuMuon);
            }
            rs.close();
            statement.close();
            connection.close();
            return phieuMuons;
			
        } catch (SQLException e) {
		System.out.println(e);
        }
        return phieuMuons;
    }
    
}
