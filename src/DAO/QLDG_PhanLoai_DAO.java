package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Model.TaiKhoan;
import Model.PhanLoaiThe;

public class QLDG_PhanLoai_DAO {
  KetNoiSQL connect = new KetNoiSQL();
  ArrayList<PhanLoaiThe> dsLoaiThe = new ArrayList<>();

  //kiểm tra mã thẻ đã tồn tại chưa
  public boolean checkMaThe(String maThe){
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    try{
      String sql = "select Count(*) FROM LoaiThe WHERE maLoaiThe="+maThe;
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

  public ArrayList dsLoaiThe(){
    PreparedStatement ps = null;
    ResultSet rs = null;

    try{
      String sql = "Select* from LoaiThe";
      ps = connect.getConnection().prepareStatement(sql);
      rs = ps.executeQuery();

      while(rs.next()){
        PhanLoaiThe loaiThe = new PhanLoaiThe();
        loaiThe.setMaLoaiThe(rs.getString("maLoaiThe"));
        loaiThe.setTenLoaiThe(rs.getString("tenLoaiThe"));
        loaiThe.setSoLuongSachMuon(rs.getInt("soSachDuocMuon"));
        loaiThe.setThoiGianMuonToiDa(rs.getInt("thoiGianDuocMuonToiDa"));
        loaiThe.setGiaTienGiaHan(rs.getString("giaTienGiaHan"));

        dsLoaiThe.add(loaiThe);
      }
    }catch(Exception e){
      e.printStackTrace();
    }
    return dsLoaiThe;
  }

  public boolean Add_LoaiThe(PhanLoaiThe loaiThe){
    String sql = "insert into LoaiThe values(?,?,?,?,?)";
    try{
      PreparedStatement ps = connect.getConnection().prepareStatement(sql);
      ps.setString(1, loaiThe.getMaLoaiThe());
      ps.setString(2, loaiThe.getTenLoaiThe());
      ps.setInt(5, loaiThe.getSoLuongSachMuon());
      ps.setInt(6, loaiThe.getThoiGianMuonToiDa());
      ps.setString(7, loaiThe.getGiaTienGiaHan());

      return ps.executeUpdate()>0;
    }catch(Exception e){
      e.printStackTrace();
    }
    return false;
  }

  public boolean update_LoaiThe(PhanLoaiThe loaiThe){
    String sql = "update LoaiThe set tenLoaiThe=?, soSachDuocMuon=?, thoiGianDuocMuonToiDa=?, giaTienGiaHan=? where maLoaiThe=?";
    try{
      PreparedStatement ps = connect.getConnection().prepareStatement(sql);
      ps.setString(1, loaiThe.getTenLoaiThe());
      ps.setInt(4, loaiThe.getSoLuongSachMuon());
      ps.setInt(5, loaiThe.getThoiGianMuonToiDa());
      ps.setString(6, loaiThe.getGiaTienGiaHan());
      ps.setString(7, loaiThe.getMaLoaiThe());

      return ps.executeUpdate()>0;
    }catch(Exception e){
      e.printStackTrace();
    }
    return false;
  }

  public String tenLoaiThe(String maLoaiThe){
    if(maLoaiThe.equals("")) return "";
    ArrayList<TaiKhoan> dstk = new QuanLiDocGia_DAO().dsDOCGIA();
    for (TaiKhoan tk : dstk) {
      String ten = tk.getTenTaiKhoan().replaceAll("\\s","");
      if(ten.equals(maLoaiThe)){
        return tk.getLoaiTK();
      }
    }
    return "";
  }

  public ArrayList timKiem(String ndung){
    ArrayList<PhanLoaiThe> dsthe = new ArrayList<>();
    try {
      String sql= "SELECT * FROM LoaiThe WHERE maLoaiThe LIKE '%"+ndung+"%' or tenLoaiThe LIKE '%"+ndung+"%'";
      PreparedStatement ps = connect.getConnection().prepareStatement(sql);
      ResultSet rs = ps.executeQuery();
      while(rs.next()){
         PhanLoaiThe loaiThe = new PhanLoaiThe();
        loaiThe.setMaLoaiThe(rs.getString("maLoaiThe"));
        loaiThe.setTenLoaiThe(rs.getString("tenLoaiThe"));
        loaiThe.setSoLuongSachMuon(rs.getInt("soSachDuocMuon"));
        loaiThe.setThoiGianMuonToiDa(rs.getInt("thoiGianDuocMuonToiDa"));
        loaiThe.setGiaTienGiaHan(rs.getString("giaTienGiaHan"));

        dsthe.add(loaiThe);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return dsthe;
  }
}
