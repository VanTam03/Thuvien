package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Model.DanhSachLoaiThe;
import Model.PhanLoaiThe;
import Model.TaiKhoan;

public class QuanLiDocGia_DAO {

  KetNoiSQL connect = new KetNoiSQL();
  private ArrayList<TaiKhoan> dsDG = new ArrayList<>();
  
  DanhSachLoaiThe dsThe = new DanhSachLoaiThe(new QLDG_PhanLoai_DAO().dsLoaiThe());

  // kiểm tra mã tài khoản đã tồn tại chưa
  public boolean checkMaTaiKhoan(String maTaiKhoan) {
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
      String sql = "select Count(*) FROM TAIKHOAN WHERE maTaiKhoan=" + maTaiKhoan;
      ps = connect.getConnection().prepareStatement(sql);
      rs = ps.executeQuery();
      if (rs.next()) {
        int count = rs.getInt(1); // Lấy kết quả đếm
        if (count == 0)
          return true; // không tồn tại mã tài khoản
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  //tên loại thẻ
  public String[] tenLoaiThe() {
    String[] names = null; // Khởi tạo mảng là null
    int index = 0;
    try {
      String sql = "SELECT DISTINCT tenLoaiThe FROM LoaiThe";
      PreparedStatement ps = connect.getConnection().prepareStatement(sql);
      ResultSet rs = ps.executeQuery();

      // Đếm số lượng dòng kết quả để biết kích thước của mảng
      int rowCount = 0;
      ArrayList<String> dsTemp = new ArrayList<>();
      while (rs.next()) {
        rowCount++;
        dsTemp.add(rs.getString("tenLoaiThe").toString());
      }
      names = new String[rowCount]; // Khởi tạo mảng với kích thước đã biết

      for(String name : dsTemp) {
        names[index++] = name;
      }
    } catch (Exception e) {
      e.printStackTrace(); // Xử lý ngoại lệ
    }
    return names;
  }

  //danh sách độc giả
  public ArrayList dsDOCGIA() {
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
      String sql = "select* FROM TAIKHOAN";
      ps = connect.getConnection().prepareStatement(sql);
      rs = ps.executeQuery();

      while (rs.next()) {
        TaiKhoan dg = new TaiKhoan();
        dg.setTenTaiKhoan(rs.getString("maTaiKhoan"));
        dg.setTenNguoiDung(rs.getString("tenNguoiDung"));
        dg.setLoaiTK(rs.getString("loaiTaiKhoan"));
        dg.setMatKhau(rs.getString("matKhau"));
        dg.setGioiTinh(rs.getString("gioiTinh"));
        dg.setEmail(rs.getString("email"));
        dg.setNgaySinh(rs.getString("ngaySinh"));
        dg.setSdt(rs.getString("sdt"));
        dg.setDiaChi(rs.getString("diaChi"));
        dg.setNgayMoThe(rs.getString("ngayMoThe"));
        dg.setHanSuDung(rs.getString("hanSuDung"));
        dg.setSoLuongMuon(rs.getInt("soLuongMuon"));
        dg.setTrangThai(rs.getInt("trangThai"));
        dsDG.add(dg);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return dsDG;
  }

  //thêm độc giả
  public boolean Add_DG(TaiKhoan tk) {
    String sql = "insert into TAIKHOAN values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
    try {
      PreparedStatement ps = connect.getConnection().prepareStatement(sql);
      ps.setString(1, tk.getTenTaiKhoan());
      ps.setString(2, tk.getMatKhau());
      ps.setString(3, tk.getLoaiTK());
      ps.setString(4, tk.getTenNguoiDung());
      ps.setString(5, tk.getNgaySinh());
      ps.setString(6, tk.getGioiTinh());
      ps.setString(7, tk.getEmail());
      ps.setString(8, tk.getSdt());
      ps.setString(9, tk.getDiaChi());
      ps.setString(10, tk.getNgayMoThe());
      ps.setString(11, tk.getHanSuDung());
      ps.setInt(12, tk.getTrangThai());
      ps.setInt(13, tk.getSoLuongMuon());

      return ps.executeUpdate() > 0;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  //cập nhật độc giả
  public boolean update_DG(TaiKhoan tk) {
    String sql = "update TAIKHOAN set matKhau=?, tenNguoiDung=?, ngaySinh=?, gioiTinh=?, email=?, sdt=?, soLuongMuon=?, loaiTaiKhoan=?, diaChi=?, ngayMoThe=?, hanSuDung=? where maTaiKhoan=?";
    try {
      PreparedStatement ps = connect.getConnection().prepareStatement(sql);
      ps.setString(1, tk.getMatKhau());
      ps.setString(2, tk.getTenNguoiDung());
      ps.setString(3, tk.getNgaySinh());
      ps.setString(4, tk.getGioiTinh());
      ps.setString(5, tk.getEmail());
      ps.setString(6, tk.getSdt());
      ps.setInt(7, tk.getSoLuongMuon());
      ps.setString(8, tk.getLoaiTK());
      ps.setString(9, tk.getDiaChi());
      ps.setString(10, tk.getNgayMoThe());
      ps.setString(11, tk.getHanSuDung());
      ps.setString(12, tk.getTenTaiKhoan());

      return ps.executeUpdate() > 0;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  //khóa độc giả
  public boolean delete_DG(String maTK) {
    String sql = "update TAIKHOAN set trangThai=0 where maTaiKhoan='" + maTK + "'";
    try {
      PreparedStatement ps = connect.getConnection().prepareStatement(sql);
      return ps.executeUpdate() > 0;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  // //lấy hạn dùng của mã thẻ từ bảng LoaiThe
  // public String hanDungThe(String maLoaiThe) {
  //   String sql = "select hanSuDung from LoaiThe where maLoaiThe=" + maLoaiThe;
  //   try {
  //     PreparedStatement ps = connect.getConnection().prepareStatement(sql);
  //     ResultSet rs = ps.executeQuery();
  //     if (rs.next()) {
  //       return rs.getString("hanSuDung");
  //     }
  //   } catch (Exception e) {
  //     e.printStackTrace();
  //   }
  //   return "";
  // }

  //lấy phí gia hạn của mã thẻ từ bảng LoaiThe
  public String phiGiaHan(String maLoaiThe) {
    String sql = "select giaTienGiaHan from LoaiThe where maLoaiThe=" + maLoaiThe;
    try {
      PreparedStatement ps = connect.getConnection().prepareStatement(sql);
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        return rs.getString("giaTienGiaHan");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "";
  }

  //chọn tên loại theo mã
  public String selectTenLoaiThe(String maLoai, DanhSachLoaiThe dsThe){
    String tenLoai=null;
    for(PhanLoaiThe loai : dsThe.getDsLoaiThe()){
      if(loai.getMaLoaiThe().equals(maLoai)){
        return loai.getTenLoaiThe();
      }
    }
    return "";
  }

  //gia hạn độc giả (table TaiKhoan)
  public boolean giaHan_DG(String maTK) {
    String sql = "update TAIKHOAN set trangThai=1 where maTaiKhoan='" + maTK + "'";
    try {
      PreparedStatement ps = connect.getConnection().prepareStatement(sql);
      return ps.executeUpdate() > 0;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  //gia hạn độc giả (table LoaiThe)
  public boolean giaHan_DG_LoaiThe(String maTK){
    String sql = "update LoaiThe set hanSuDung = DATEADD(YEAR, 1, hanSuDung) where maLoaiThe='" + maTK + "'";
    try {
      PreparedStatement ps = connect.getConnection().prepareStatement(sql);
      return ps.executeUpdate() > 0;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  //tự động khóa tài khoản khi quá hạn sử dụng
  public boolean khoaQuaHan(){
    try {
      String sql = "update TaiKhoan set trangThai=0 where TaiKhoan.maTaiKhoan = (select maLoaiThe from LoaiThe where hanSuDung < GETDATE())";
      PreparedStatement ps = connect.getConnection().prepareStatement(sql);
      return ps.executeUpdate()>0;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  public ArrayList timKiem(String ndung){
    ArrayList<TaiKhoan> dsTK = new ArrayList<>();
    try{
      String sql= "SELECT * FROM TaiKhoan WHERE maTaiKhoan LIKE '%"+ndung+"%' or tenNguoiDung LIKE '%"+ndung+"%' or sdt LIKE '%"+ndung+"%'";
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
        dg.setDiaChi(rs.getString("diaChi"));
        dg.setNgayMoThe(rs.getString("ngayMoThe"));
        dg.setHanSuDung(rs.getString("hanSuDung"));
        dg.setSoLuongMuon(rs.getInt("soLuongMuon"));
        dg.setTrangThai(rs.getInt("trangThai"));
        dsDG.add(dg);
      }
    }catch (Exception e) {
      e.printStackTrace();
    }
    return dsTK;
  }
}
