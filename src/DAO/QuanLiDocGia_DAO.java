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

  //ds tên loại thẻ
  public String[] tenLoaiThe() {
    String[] names = new String[dsThe.getDsLoaiThe().size()]; // Khởi tạo mảng là null
    int index = 0;
      for(PhanLoaiThe name : dsThe.getDsLoaiThe()) {
        names[index++] = name.getTenLoaiThe();
      }
    return names;
  }

  //mã loại thẻ
   public String maLoaiThe(String ten) {
    String ma = null;
      for(PhanLoaiThe loai : dsThe.getDsLoaiThe()) {
        if(loai.getTenLoaiThe().equals(ten)){
          return loai.getMaLoaiThe();
        }
      }
    return ma;
  }

  
  //chọn tên loại theo mã
  public String tenLoai(String maLoai){
    String ten = null;
      for(PhanLoaiThe loai : dsThe.getDsLoaiThe()) {
        if(loai.getMaLoaiThe().equals(maLoai)){
          return loai.getTenLoaiThe();
        }
      }
    return ten;
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


  //gia hạn độc giả (table TaiKhoan)
  public boolean giaHan_DG(String maTK) {
    String sql = "update TAIKHOAN set hanSuDung = DATEADD(YEAR, 1, hanSuDung), trangThai=1 where maTaiKhoan='" + maTK + "'";
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
      String sql = "update TaiKhoan set trangThai=0 where hanSuDung < GETDATE()";
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
    return dsDG;
  }
}
