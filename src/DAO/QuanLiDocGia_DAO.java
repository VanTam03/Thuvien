package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


import Model.TaiKhoan;

public class QuanLiDocGia_DAO {
  
  KetNoiSQL connect = new KetNoiSQL();
  ArrayList<TaiKhoan> dsDG = new ArrayList<>();

  //kiểm tra mã tài khoản đã tồn tại chưa
  public boolean checkMaTaiKhoan(String maTaiKhoan){
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    try{
      String sql = "select Count(*) FROM TAIKHOAN WHERE maTaiKhoan="+maTaiKhoan;
      ps = connect.getConnection().prepareStatement(sql);
      rs = ps.executeQuery();
      if (rs.next()) {
        int count = rs.getInt(1); // Lấy kết quả đếm
        if(count == 0) return true; //không tồn tại mã tại khoản
      }
    }catch(Exception e){
      e.printStackTrace();
    }
    return false;
  }

  public ArrayList dsDOCGIA(){

    PreparedStatement ps = null;
    ResultSet rs = null;
    
    try{
      String sql = "select* FROM TAIKHOAN WHERE trangThai=1";
      ps = connect.getConnection().prepareStatement(sql);
      rs = ps.executeQuery();
      
      while(rs.next()){
        TaiKhoan dg = new TaiKhoan();
        dg.setTenTaiKhoan(rs.getString("maTaiKhoan"));
        dg.setTenNguoiDung(rs.getString("tenNguoiDung"));
        dg.setLoaiTK(rs.getString("loaiTaiKhoan"));
        dg.setMatKhau(rs.getString("matKhau"));
        dg.setGioiTinh(rs.getString("gioiTinh"));
        dg.setEmail(rs.getString("email"));
        dg.setNgaySinh(rs.getString("ngaySinh"));
        dg.setSdt(rs.getString("sdt"));
        dg.setSoLuongMuon(rs.getInt("soLuongMuon"));
        dg.setTrangThai(rs.getInt("trangThai"));
        dg.setLoaiTK(rs.getString("loaiTaiKhoan"));
        //tên loại thẻ, hạn dùng thẻ
        dsDG.add(dg);
      }
    } catch(Exception e){
      e.printStackTrace();
    }
    return dsDG;
  } 

  public boolean Add_DG(TaiKhoan tk){
    String sql="insert into TAIKHOAN values (?,?,?,?,?,?,?,?,?,?)";
    try{
      PreparedStatement ps = connect.getConnection().prepareStatement(sql);
      ps.setString(1, tk.getTenTaiKhoan());
      ps.setString(2, tk.getMatKhau());
      ps.setString(3, tk.getLoaiTK());
      ps.setString(4, tk.getTenNguoiDung());
      ps.setString(5, tk.getNgaySinh());
      ps.setString(6, tk.getGioiTinh());
      ps.setString(7, tk.getEmail());
      ps.setString(8, tk.getSdt());
      ps.setInt(9, tk.getTrangThai());
      ps.setInt(10, tk.getSoLuongMuon());

      return ps.executeUpdate()>0;
    }catch(Exception e){
      e.printStackTrace();
    }
    return false;
  }

  public boolean update_DG(TaiKhoan tk){
    String sql = "update TAIKHOAN set matKhau=?, loaiTaiKhoan=?, tenNguoiDung=?, ngaySinh=?, gioiTinh=?, email=?, sdt=?, soLuongMuon=? where maTaiKhoan=?";
    try{
      PreparedStatement ps = connect.getConnection().prepareStatement(sql);
      ps.setString(1, tk.getMatKhau());
      ps.setString(2, tk.getLoaiTK());
      ps.setString(3, tk.getTenNguoiDung());
      ps.setString(4, tk.getNgaySinh());
      ps.setString(5, tk.getGioiTinh());
      ps.setString(6, tk.getEmail());
      ps.setString(7, tk.getSdt());
      ps.setInt(8, tk.getSoLuongMuon());
      ps.setString(9, tk.getTenTaiKhoan());

      return ps.executeUpdate()>0;
    }catch (Exception e){
      e.printStackTrace();
    }
    return false;
  }

  public boolean delete_DG(String maTK){
    String sql = "update TAIKHOAN set trangThai=0 where maTaiKhoan='"+maTK+"'";
    try{
      PreparedStatement ps = connect.getConnection().prepareStatement(sql);
      return ps.executeUpdate()>0;
    }catch(Exception e){
      e.printStackTrace();
    }
    return false;
  }

  public String hanDungThe(String maLoaiThe){
    String sql = "select hanSuDung from LoaiThe where maLoaiThe="+maLoaiThe;
    try{
      PreparedStatement ps = connect.getConnection().prepareStatement(sql);
      ResultSet rs = ps.executeQuery();
      if(rs.next()){
        return rs.getString("hanSuDung");
      }
    }catch(Exception e){
      e.printStackTrace();
    }
    return "";
  }
  public ArrayList timKiem(String ndung){
    ArrayList<TaiKhoan> dstk = new ArrayList<>();
    try {
      String sql= "SELECT * FROM TaiKhoan WHERE maTaiKhoan LIKE '%"+ndung+"%' or tenNguoiDung LIKE '%"+ndung+"%'";
      PreparedStatement ps = connect.getConnection().prepareStatement(sql);
      ResultSet rs = ps.executeQuery();
      while(rs.next()){
        TaiKhoan dg = new TaiKhoan();
        dg.setTenTaiKhoan(rs.getString("maTaiKhoan"));
        dg.setTenNguoiDung(rs.getString("tenNguoiDung"));
        dg.setLoaiTK(rs.getString("loaiTaiKhoan"));
        dg.setMatKhau(rs.getString("matKhau"));
        dg.setGioiTinh(rs.getString("gioiTinh"));
        dg.setEmail(rs.getString("email"));
        dg.setNgaySinh(rs.getString("ngaySinh"));
        dg.setSdt(rs.getString("sdt"));
        dg.setSoLuongMuon(rs.getInt("soLuongMuon"));
        dg.setTrangThai(rs.getInt("trangThai"));
        dg.setLoaiTK(rs.getString("loaiTaiKhoan"));
        
        dstk.add(dg);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return dstk;
  }
}

