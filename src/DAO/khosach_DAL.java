/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.KhoSach;
import DTO.LoadKhoDTO;
import DTO.QuanLy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tiena
 */
public class khosach_DAL {
    public List<KhoSach> getAllKhoSach(){
        List<KhoSach> khoSachs = new ArrayList<KhoSach>();
        Connection connection = KetNoiSQL.getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = "select * from Khosach";
            System.out.println(query);
			
            ResultSet rss =  statement.executeQuery(query);
			
            while(rss.next()) {
                String Masach = rss.getString("maSach");
                int tongsoluong = rss.getInt("tongSoLuong");
                int soluongcon = rss.getInt("soLuongCon");
                int sachhong = rss.getInt("soLuongSachHong");
                
                    
                KhoSach khoSach = new KhoSach(Masach, tongsoluong, soluongcon, sachhong );
                khoSachs.add(khoSach);
            }
            rss.close();
            statement.close();
            connection.close();
            return khoSachs;
			
        } catch (SQLException e) {
		System.out.println(e);
        }
        return khoSachs;
    } 
    
    public void update(KhoSach khoSach){
        Connection connection = KetNoiSQL.getConnection();
        try {
            String query = "UPDATE khoSach "+
			    "SET tongSoLuong=?, soLuongCon=?, soLuongSachHong=?"+
			    "WHERE maSach=?";
            PreparedStatement prepare_statement = connection.prepareStatement(query);	
            prepare_statement.setInt(1,khoSach.getTongSoLuong());
            prepare_statement.setInt(2,khoSach.getSoLuongCon());
            prepare_statement.setInt(3,khoSach.getSoLuongSachHong());
            prepare_statement.setString(4,khoSach.getMaSach());

            prepare_statement.executeUpdate();
            System.out.println(query);
            connection.close();
	} catch (SQLException e) {
            System.out.println(e);
	}
    }
    
    public List<LoadKhoDTO> getKhobysearch(String search) {
        List<LoadKhoDTO> loadKhoDTOs = new ArrayList<>();
        Connection connection = KetNoiSQL.getConnection();

        try {
            try (Statement statement = connection.createStatement()) {
                String query = "SELECT thongtinsach.maSach, thongtinsach.tensach, khosach.tongSoLuong, khosach.soLuongCon, khosach.soLuongSachHong " +
                               "FROM thongtinsach " +
                               "JOIN khosach ON thongtinsach.maSach = khosach.maSach " +
                               "WHERE thongtinsach.maSach LIKE N'%" + search + "%' " +
                               "OR thongtinsach.tensach LIKE N'%" + search + "%'";
                System.out.println(query);

                try (ResultSet rss = statement.executeQuery(query)) {
                    while (rss.next()) {
                        String masach = rss.getString("maSach");
                        String tensach = rss.getString("tensach");
                        int tongsoluong = rss.getInt("tongSoLuong");
                        int soluongcon = rss.getInt("soLuongCon");
                        int sachhong = rss.getInt("soLuongSachHong");

                        LoadKhoDTO loadKhoDTO = new LoadKhoDTO(masach, tensach, tongsoluong, soluongcon, sachhong);
                        loadKhoDTOs.add(loadKhoDTO);
                    }
                }
            }
            connection.close();
            return loadKhoDTOs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loadKhoDTOs;
    }

    
    public List<LoadKhoDTO> loadKho_into(String conditon, String sortOder){
        List<LoadKhoDTO> loadKhoDTOs = new ArrayList<>();
        Connection connection = KetNoiSQL.getConnection();
        try {
            try (Statement statement = connection.createStatement()) {
                String query = "SELECT thongtinsach.maSach, thongtinsach.tensach, khosach.tongSoLuong, khosach.soLuongCon, khosach.soLuongSachHong\n" +
                        "FROM thongtinsach\n" +
                        "JOIN khosach ON thongtinsach.maSach = khosach.maSach\n" +
                        "ORDER BY "+conditon+" " + sortOder;
                System.out.println(query);
                
                try (ResultSet rss = statement.executeQuery(query)) {
                    while(rss.next()) {
                        String Masach = rss.getString("maSach");
                        String tensach = rss.getString("tensach");
                        int tongsoluong = rss.getInt("tongSoLuong");
                        int soluongcon = rss.getInt("soLuongCon");
                        int sachhong = rss.getInt("soLuongSachHong");
                        
                        
                        LoadKhoDTO loadKhoDTO = new LoadKhoDTO(Masach, tensach,tongsoluong, soluongcon, sachhong );
                        loadKhoDTOs.add(loadKhoDTO);
                    }
                }
            }
            connection.close();
            return loadKhoDTOs;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return loadKhoDTOs;
    }
}
