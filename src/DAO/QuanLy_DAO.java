package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


import DTO.QuanLy;


public class QuanLy_DAO {
  KetNoiSQL connect = new KetNoiSQL();

  //danh sách admin và thủ thư
  public ArrayList dsAllTaiKhoan(){
    ArrayList<QuanLy> dsql = new ArrayList<>();
    PreparedStatement ps = null;
    ResultSet rs = null;
    try{
      String sql = "select* from QuanLy";
      ps = connect.getConnection().prepareStatement(sql);
      rs = ps.executeQuery();
      while (rs.next()) {
        QuanLy ql = new QuanLy();
        ql.setMaQuanly(rs.getString("maQuanLy"));
        ql.setMatKhau(rs.getString("matKhau"));
        ql.setTenQuanly(rs.getString("tenQuanLy"));
        ql.setNgaySinh(rs.getString("ngaySinh"));
        ql.setGioiTinh(rs.getString("gioiTinh"));
        ql.setDiaChi(rs.getString("diaChi"));
        ql.setSDT(rs.getString("sdt"));
        ql.setEmail(rs.getString("email"));
        ql.setTrangThai(Integer.parseInt(rs.getString("trangThai")));

        dsql.add(ql);
      }
      // dsql.addAll(new QuanLyDocGia_DAO().dsDOCGIA());
    }catch(Exception e){
      e. printStackTrace();
    }
    return dsql;
  }

  //thêm admin và thủ thư
  public boolean themQuanLy(QuanLy ql){
    try{
      String sql = "insert into QuanLy values (?,?,?,?,?,?,?,?,?)";
      PreparedStatement ps = connect.getConnection().prepareStatement(sql);
      ps.setString(1, ql.getMaQuanly());
      ps.setString(2, ql.getMatKhau());
      ps.setString(3, ql.getTenQuanly());
      ps.setString(4, ql.getNgaySinh());
      ps.setString(5, ql.getGioiTinh());
      ps.setString(6, ql.getDiaChi());
      ps.setString(7, ql.getSDT());
      ps.setString(8, ql.getEmail());
      ps.setInt(9, ql.getTrangThai());

      return ps.executeUpdate()>0;
    }catch(Exception e){
      e.printStackTrace();
    }
    return false;
  }

  //sửa admin và thủ thư
  public boolean suaQuanLy(QuanLy ql){
    try {
      String sql = "update QuanLy set matKhau=?, tenQuanLy=?, ngaySinh=?, gioiTinh=?, diaChi=?, sdt=?, email=?, trangThai=? where maQuanly=?";
      PreparedStatement ps = connect.getConnection().prepareStatement(sql);
      ps.setString(1, ql.getMatKhau());
      ps.setString(2, ql.getTenQuanly());
      ps.setString(3, ql.getNgaySinh());
      ps.setString(4, ql.getGioiTinh());
      ps.setString(5, ql.getDiaChi());
      ps.setString(6, ql.getSDT());
      ps.setString(7, ql.getEmail());
      ps.setInt(8,ql.getTrangThai());
      ps.setString(9, ql.getMaQuanly());

      return ps.executeUpdate()>0;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  //Khóa tài khoản admin, thủ thư
  public boolean khoaTKQL(String maTaiKhoan){
    try {
      String sql = "update QuanLy set trangThai=0 where maQuanLy=?";
      PreparedStatement ps = connect.getConnection().prepareStatement(sql);
      ps.setString(1, maTaiKhoan);

      return ps.executeUpdate()>0;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  //mở khóa tài khoản
  public boolean moKhoa(String maTaiKhoan){
    try {
      String sql = "update QuanLy set trangThai=1 where maQuanLy=?";
      PreparedStatement ps = connect.getConnection().prepareStatement(sql);
      ps.setString(1, maTaiKhoan);

      return ps.executeUpdate()>0;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

    //kiểm tra mã tài khoản đã tồn tại chưa
    public boolean checkMaQuanLy(String maTaiKhoan){
      PreparedStatement ps = null;
      ResultSet rs = null;
      
      try{
        String sql = "select Count(*) FROM QuanLy WHERE maQuanLy="+maTaiKhoan;
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
}
