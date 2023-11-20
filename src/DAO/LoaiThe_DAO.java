package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.LoaiThe;

public class LoaiThe_DAO implements DAO_Interface<LoaiThe> {

    public static LoaiThe_DAO getInstance() {
        return new LoaiThe_DAO();
    }

    @Override
    public int add(LoaiThe loaiThe) {
        int rowsAffected = 0;
        String sql = "INSERT INTO dbo.[LoaiThe] (maLoaiThe, tenLoaiThe, SoSachDuocMuon, thoiGianDuocMuonToiDa, giaTienGiaHan) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, loaiThe.getMaLoaiThe());
            pst.setString(2, loaiThe.getTenLoaiThe());
            pst.setInt(3, loaiThe.getSoSachDuocMuon());
            pst.setInt(4, loaiThe.getThoiGianDuocMuonToiDa());
            pst.setDouble(5, loaiThe.getGiaTienGiaHan());

            rowsAffected = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    @Override
    public int update(LoaiThe loaiThe) {
        int rowsAffected = 0;
        String sql = "UPDATE dbo.[LoaiThe] SET tenLoaiThe = ?, SoSachDuocMuon = ?, thoiGianDuocMuonToiDa = ?, " +
                "giaTienGiaHan = ? WHERE maLoaiThe = ?";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, loaiThe.getTenLoaiThe());
            pst.setInt(2, loaiThe.getSoSachDuocMuon());
            pst.setInt(3, loaiThe.getThoiGianDuocMuonToiDa());
            pst.setDouble(4, loaiThe.getGiaTienGiaHan());
            pst.setString(5, loaiThe.getMaLoaiThe());

            rowsAffected = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    @Override
    public int delete(String maLoaiThe) {
        int rowsAffected = 0;
        String sql = "DELETE FROM dbo.[LoaiThe] WHERE maLoaiThe = ?";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, maLoaiThe);
            rowsAffected = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    @Override
    public List<LoaiThe> selectAll() {
        List<LoaiThe> rowSelected = new ArrayList<>();
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM dbo.[LoaiThe]");
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                LoaiThe loaiThe = new LoaiThe();
                loaiThe.setMaLoaiThe(rs.getString("maLoaiThe"));
                loaiThe.setTenLoaiThe(rs.getString("tenLoaiThe"));
                loaiThe.setSoSachDuocMuon(rs.getInt("SoSachDuocMuon"));
                loaiThe.setThoiGianDuocMuonToiDa(rs.getInt("thoiGianDuocMuonToiDa"));
                loaiThe.setGiaTienGiaHan(rs.getDouble("giaTienGiaHan"));

                rowSelected.add(loaiThe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowSelected;
    }

    @Override
    public LoaiThe selectById(String maLoaiThe) {
        LoaiThe result = new LoaiThe();
        String sql = "SELECT * FROM dbo.[LoaiThe] WHERE maLoaiThe = ?";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, maLoaiThe);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                result.setMaLoaiThe(rs.getString("maLoaiThe"));
                result.setTenLoaiThe(rs.getString("tenLoaiThe"));
                result.setSoSachDuocMuon(rs.getInt("SoSachDuocMuon"));
                result.setThoiGianDuocMuonToiDa(rs.getInt("thoiGianDuocMuonToiDa"));
                result.setGiaTienGiaHan(rs.getDouble("giaTienGiaHan"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
