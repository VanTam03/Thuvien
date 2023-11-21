package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.TaiKhoan;

public class TaiKhoan_DAO implements DAO_Interface<TaiKhoan> {

    public static TaiKhoan_DAO getInstance() {
        return new TaiKhoan_DAO();
    }

    @Override
    public int add(TaiKhoan taiKhoan) {
        int rowsAffected = 0;
        String sql = "INSERT INTO dbo.[TaiKhoan] (maTaikhoan, matKhau, loaitaikhoan, tenNguoidung, ngaySinh, Email, SDT, gioiTinh, trangThai, soLuongmuon) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, taiKhoan.getMaTaikhoan());
            pst.setString(2, taiKhoan.getMatKhau());
            pst.setString(3, taiKhoan.getLoaitaikhoan());
            pst.setString(4, taiKhoan.getTenNguoidung());
            pst.setString(5, taiKhoan.getNgaySinh());
            pst.setString(6, taiKhoan.getEmail());
            pst.setString(7, taiKhoan.getSDT());
            pst.setString(8, taiKhoan.getGioiTinh());
            pst.setInt(9, taiKhoan.getTrangThai());
            pst.setInt(10, taiKhoan.getSoLuongmuon());

            rowsAffected = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    @Override
    public int update(TaiKhoan taiKhoan) {
        int rowsAffected = 0;
        String sql = "UPDATE dbo.[TaiKhoan] SET matKhau = ?, loaitaikhoan = ?, tenNguoidung = ?, ngaySinh = ?, " +
                "Email = ?, SDT = ?, gioiTinh = ?, trangThai = ?, soLuongmuon = ? WHERE maTaikhoan = ?";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, taiKhoan.getMatKhau());
            pst.setString(2, taiKhoan.getLoaitaikhoan());
            pst.setString(3, taiKhoan.getTenNguoidung());
            pst.setString(4, taiKhoan.getNgaySinh());
            pst.setString(5, taiKhoan.getEmail());
            pst.setString(6, taiKhoan.getSDT());
            pst.setString(7, taiKhoan.getGioiTinh());
            pst.setInt(8, taiKhoan.getTrangThai());
            pst.setInt(9, taiKhoan.getSoLuongmuon());
            pst.setString(10, taiKhoan.getMaTaikhoan());

            rowsAffected = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    @Override
    public int delete(String maTaikhoan) {
        int rowsAffected = 0;
        String sql = "DELETE FROM dbo.[TaiKhoan] WHERE maTaikhoan = ?";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, maTaikhoan);
            rowsAffected = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    @Override
    public List<TaiKhoan> selectAll() {
        List<TaiKhoan> rowSelected = new ArrayList<>();
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM dbo.[TaiKhoan]");
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                TaiKhoan taiKhoan = new TaiKhoan();
                taiKhoan.setMaTaikhoan(rs.getString("maTaikhoan"));
                taiKhoan.setMatKhau(rs.getString("matKhau"));
                taiKhoan.setLoaitaikhoan(rs.getString("loaitaikhoan"));
                taiKhoan.setTenNguoidung(rs.getString("tenNguoidung"));
                taiKhoan.setNgaySinh(rs.getString("ngaySinh"));
                taiKhoan.setEmail(rs.getString("Email"));
                taiKhoan.setSDT(rs.getString("SDT"));
                taiKhoan.setGioiTinh(rs.getString("gioiTinh"));
                taiKhoan.setTrangThai(rs.getInt("trangThai"));
                taiKhoan.setSoLuongmuon(rs.getInt("soLuongmuon"));

                rowSelected.add(taiKhoan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowSelected;
    }

    @Override
    public TaiKhoan selectById(String maTaikhoan) {
        TaiKhoan result = new TaiKhoan();
        String sql = "SELECT * FROM dbo.[TaiKhoan] WHERE maTaikhoan = ?";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, maTaikhoan);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                result.setMaTaikhoan(rs.getString("maTaikhoan"));
                result.setMatKhau(rs.getString("matKhau"));
                result.setLoaitaikhoan(rs.getString("loaitaikhoan"));
                result.setTenNguoidung(rs.getString("tenNguoidung"));
                result.setNgaySinh(rs.getString("ngaySinh"));
                result.setEmail(rs.getString("Email"));
                result.setSDT(rs.getString("SDT"));
                result.setGioiTinh(rs.getString("gioiTinh"));
                result.setTrangThai(rs.getInt("trangThai"));
                result.setSoLuongmuon(rs.getInt("soLuongmuon"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
