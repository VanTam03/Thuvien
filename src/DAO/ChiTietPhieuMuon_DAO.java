package DAO;

import DTO.ChiTietPhieuMuon;
import DTO.PhieuMuon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChiTietPhieuMuon_DAO implements DAO_Interface<ChiTietPhieuMuon> {

    public static ChiTietPhieuMuon_DAO getInstance() {
        return new ChiTietPhieuMuon_DAO();
    }

    @Override
    public int add(ChiTietPhieuMuon chiTietPhieuMuon) {
        int rowsAffected = 0;
        String sql = "INSERT INTO dbo.[ChiTietPhieuMuon] (maPhieuMuon, maSach, ngayThucTra, tienPhat, tinhTrangSach) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, chiTietPhieuMuon.getMaPhieumuon());
            pst.setString(2, chiTietPhieuMuon.getMaSach());
            pst.setString(3, chiTietPhieuMuon.getNgayThuctra());
            pst.setString(4, chiTietPhieuMuon.getTienPhat());
            pst.setString(5, chiTietPhieuMuon.getTinhTrangSach());
            rowsAffected = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PhieuMuon phieuMuon = PhieuMuon_DAO.getInstance().selectById(chiTietPhieuMuon.getMaPhieumuon());
        phieuMuon.setSoLuongSach(phieuMuon.getSoLuongSach()+1);
        PhieuMuon_DAO.getInstance().update(phieuMuon);
        return rowsAffected;
    }

    @Override
    public int update(ChiTietPhieuMuon chiTietPhieuMuon) {
        int rowsAffected = 0;
        String sql = "UPDATE dbo.[ChiTietPhieuMuon] SET ngayThucTra = ?, tienPhat = ?, tinhTrangSach = ? WHERE maPhieuMuon = ? AND maSach = ?";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, chiTietPhieuMuon.getNgayThuctra());
            pst.setString(2, chiTietPhieuMuon.getTienPhat());
            pst.setString(3, chiTietPhieuMuon.getTinhTrangSach());
            pst.setString(4, chiTietPhieuMuon.getMaPhieumuon());
            pst.setString(5, chiTietPhieuMuon.getMaSach());
            rowsAffected = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    @Override
    public int delete(String maPhieumuon) {
        int rowsAffected = 0;
        String sql = "DELETE FROM dbo.[ChiTietPhieuMuon] WHERE maPhieuMuon = ?";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, maPhieumuon);
            rowsAffected = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PhieuMuon phieuMuon = PhieuMuon_DAO.getInstance().selectById(maPhieumuon);
        phieuMuon.setSoLuongSach(phieuMuon.getSoLuongSach()-1);
        PhieuMuon_DAO.getInstance().update(phieuMuon);
        return rowsAffected;
    }
    public int delete(String maPhieumuon, String maSach) {
        int rowsAffected = 0;
        String sql = "DELETE FROM dbo.[ChiTietPhieuMuon] WHERE maPhieuMuon = ? AND maSach = ?";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, maPhieumuon);
            pst.setString(2, maSach);
            rowsAffected = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PhieuMuon phieuMuon = PhieuMuon_DAO.getInstance().selectById(maPhieumuon);
        phieuMuon.setSoLuongSach(phieuMuon.getSoLuongSach()-1);
        PhieuMuon_DAO.getInstance().update(phieuMuon);
        return rowsAffected;
    }

    @Override
    public List<ChiTietPhieuMuon> selectAll() {
        List<ChiTietPhieuMuon> rowSelected = new ArrayList<>();
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM dbo.[ChiTietPhieuMuon]");
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                String maPhieumuon = rs.getString("maPhieumuon");
                String maSach = rs.getString("maSach");
                String ngayThuctra = rs.getString("ngayThuctra");
                String tienPhat = rs.getString("tienPhat");
                String tinhTrangSach = rs.getString("tinhTrangSach");
                ChiTietPhieuMuon chiTietPhieuMuon = new ChiTietPhieuMuon(maPhieumuon, maSach, ngayThuctra, tienPhat, tinhTrangSach);
                rowSelected.add(chiTietPhieuMuon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowSelected;
    }

    @Override
    public ChiTietPhieuMuon selectById(String maPhieumuon) {
        ChiTietPhieuMuon result = new ChiTietPhieuMuon();
        String sql = "SELECT * FROM dbo.[ChiTietPhieuMuon] WHERE maPhieumuon = ?";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, maPhieumuon);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                result.setMaPhieumuon(rs.getString("maPhieumuon"));
                result.setMaSach(rs.getString("maSach"));
                result.setNgayThuctra(rs.getString("ngayThuctra"));
                result.setTienPhat(rs.getString("tienPhat"));
                result.setTinhTrangSach(rs.getString("tinhTrangSach"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public  List<ChiTietPhieuMuon> selectAllById(String maPhieumuon) {
        List<ChiTietPhieuMuon> result = new ArrayList<>();
        String sql = "SELECT * FROM dbo.[ChiTietPhieuMuon] WHERE maPhieumuon = ?";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, maPhieumuon);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String maSach = rs.getString("maSach");
                String ngayThuctra = rs.getString("ngayThuctra");
                String tienPhat = rs.getString("tienPhat");
                String tinhTrangSach = rs.getString("tinhTrangSach");
                ChiTietPhieuMuon chiTietPhieuMuon = new ChiTietPhieuMuon(maPhieumuon, maSach, ngayThuctra, tienPhat, tinhTrangSach);
                result.add(chiTietPhieuMuon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
