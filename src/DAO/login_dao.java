/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.QuanLy;
import DTO.TaiKhoan;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tiena
 */
public class login_dao {
    public List<QuanLy> getall_Admin(){
        List<QuanLy> quanLys = new ArrayList<QuanLy>();
        Connection connection = KetNoiSQL.getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = "select * from QuanLy where tenQuanLy ='ADMIN'";
            System.out.println(query);
			
            ResultSet rss =  statement.executeQuery(query);
			
            while(rss.next()) {
                String maQuanly = rss.getString("maQuanLy");
                String matKhau = rss.getString("matKhau");
                String tenQuanly = rss.getString("tenQuanLy");
                String ngaySinh = rss.getString("ngaySinh");
                String gioiTinh = rss.getString("gioiTinh");
                String diaChi = rss.getString("diaChi");
                String SDT = rss.getString("sdt");
                String Email = rss.getString("email");
                int trangThai = rss.getInt("trangThai");
                    
                QuanLy quanLy = new QuanLy(maQuanly, matKhau, tenQuanly, ngaySinh, gioiTinh, diaChi, SDT, Email, trangThai );
                quanLys.add(quanLy);
            }
            rss.close();
            statement.close();
            connection.close();
            return quanLys;
			
        } catch (Exception e) {
		e.printStackTrace();// TODO: handle exception
        }
        return quanLys;
    }
    public List<QuanLy> getall_Thuthu(){
        List<QuanLy> quanLyss = new ArrayList<QuanLy>();
        Connection connection = KetNoiSQL.getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = "select * from QuanLy where tenQuanLy =N'THỦ THƯ'";
            System.out.println(query);
			
            ResultSet rss =  statement.executeQuery(query);
			
            while(rss.next()) {
                String maQuanly = rss.getString("maQuanLy");
                String matKhau = rss.getString("matKhau");
                String tenQuanly = rss.getString("tenQuanLy");
                String ngaySinh = rss.getString("ngaySinh");
                String gioiTinh = rss.getString("gioiTinh");
                String diaChi = rss.getString("diaChi");
                String SDT = rss.getString("sdt");
                String Email = rss.getString("email");
                int trangThai = rss.getInt("trangThai");
                    
                QuanLy quanLy = new QuanLy(maQuanly, matKhau, tenQuanly, ngaySinh, gioiTinh, diaChi, SDT, Email, trangThai );
                quanLyss.add(quanLy);
            }
            rss.close();
            statement.close();
            connection.close();
            return quanLyss;
			
        } catch (Exception e) {
		e.printStackTrace();// TODO: handle exception
        }
        return quanLyss;
    }
    
    public List<TaiKhoan> getall_Docgia(){
        List<TaiKhoan> taiKhoans = new ArrayList<TaiKhoan>();
        Connection connection = KetNoiSQL.getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = "select * from TaiKhoan";
            System.out.println(query);
			
            ResultSet rss =  statement.executeQuery(query);
			
            while(rss.next()) {
                String maTk = rss.getString("maTaiKhoan");
                String matKhau = rss.getString("matKhau");
                String loaiTK = rss.getString("loaiTaiKhoan");
                String name = rss.getString("tenNguoiDung");
                String ngaysinh = rss.getString("ngaySinh");
                String gioitinh = rss.getString("gioiTinh");
                String email = rss.getString("email");
                String sdt = rss.getString("sdt");
                int trangThai = rss.getInt("trangThai");
                int SLmuon = rss.getInt("soLuongMuon");
                
                TaiKhoan taiKhoan = new TaiKhoan(maTk, matKhau, loaiTK, name, ngaysinh, gioitinh, email, sdt, trangThai , SLmuon);
                taiKhoans.add(taiKhoan);
            }
            rss.close();
            statement.close();
            connection.close();
            return taiKhoans;
			
        } catch (Exception e) {
		e.printStackTrace();// TODO: handle exception
        }
        return taiKhoans;
    }
}
