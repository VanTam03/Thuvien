/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import DAO.*;
import DTO.*;
import Model.DanhSachLoaiThe;
import Model.DanhSachTaiKhoan;
import Model.PhanLoaiThe;
import Model.PhieuTra_BLL;
import Model.TaiKhoan;
import Model.ThanhLyBLL;
import Model.check;

import org.apache.poi.ss.formula.functions.T;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import static java.time.zone.ZoneRulesProvider.refresh;
import java.util.AbstractMap;

/**
 *
 * @author 1
 */
public class TrangChuThuThu extends javax.swing.JFrame {
    DefaultTableModel defaultTableModel_DM;
    DefaultTableModel defaultTableModel_TL;
    DefaultTableModel defaultTableModel_TG;
    DefaultTableModel defaultTableModel_Sach;
    DefaultTableModel defaultTableModel_CTPM;
    DefaultTableModel defaultTableModelPhieuTra;
    PhieuTra_BLL phieuTra_BLL = new PhieuTra_BLL();
    DefaultTableModel defaultTableModelXuat;
    ThanhLyBLL thanhLyBLL = new ThanhLyBLL();

    DefaultTableModel defaultTableModel_CTPN;
    DefaultTableModel defaultTableModelTraCuu;

    /**
     * Creates new form TrangChuThuThu
     */
    public TrangChuThuThu() {
        initComponents();
        loadmaDanhMuc();
        loadComboBoxTheLoai();
        loadComboBoxDanhMuc();
        loadmaTheLoai();
        loadmaTacGia();
        loadThongTinSach();
        loadChiTietPhieuMuon();
        loadChiTietTPhieuNhap();
        loadPhieuTra(phieuTra_BLL.loaddata());
        loaddataPX(thanhLyBLL.loaddata());
    }

    private void loaddataPX(AbstractMap.SimpleEntry<List<Sach>, List<ThanhLySach>> data) {
        List<Sach> processedBooks = data.getKey();
        List<ThanhLySach> processedExports = data.getValue();

        defaultTableModelXuat = new DefaultTableModel();
        TABLEPhieuXuat.setModel(defaultTableModelXuat);
        defaultTableModelXuat.addColumn("Mã phiếu xuất");
        defaultTableModelXuat.addColumn("Mã quản lý");
        defaultTableModelXuat.addColumn("Mã sách");
        defaultTableModelXuat.addColumn("Tên sách");
        defaultTableModelXuat.addColumn("Số Lượng");
        defaultTableModelXuat.addColumn("Lý do");
        defaultTableModelXuat.addColumn("Ngày xuất");
        defaultTableModelXuat.addColumn("Ghi chú");
        defaultTableModelXuat.addColumn("Tổng tiền");

        // Duyệt qua danh sách sách và thanh lý sách đã xử lý
        for (ThanhLySach thanhLySach : processedExports) {
            // Tìm sách tương ứng trong danh sách đã xử lý
            Sach book = findBookByMaSach(processedBooks, thanhLySach.getMaSach());

            // Kiểm tra nếu sách không null
            if (book != null) {
                // Thêm thông tin vào bảng
                defaultTableModelXuat.addRow(new Object[]{
                        thanhLySach.getMaThanhLySach(),
                        thanhLySach.getMaQuanLy(),
                        thanhLySach.getMaSach(),
                        book.getTenSach(),  // Sử dụng tên sách từ danh sách sách
                        thanhLySach.getSoLuongSachHong(),
                        thanhLySach.getLyDoThanhLy(),
                        thanhLySach.getNgayThanhLy(),
                        thanhLySach.getGhiChu(),
                        thanhLySach.getTongTienThanhLy()
                });
            }
        }
    }

    private Sach findBookByMaSach(List<Sach> books, String maSach) {
        for (Sach book : books) {
            if (book.getMaSach().equals(maSach)) {
                return book;
            }
        }
        return null;
    }
    
    private void loadPhieuTra(List<PhieuMuon> phieuMuons) {
        defaultTableModelPhieuTra = new DefaultTableModel();
        table_PhieuTra.setModel(defaultTableModelPhieuTra);
        defaultTableModelPhieuTra.addColumn("Mã phiếu mượn");
        defaultTableModelPhieuTra.addColumn("Ngày mượn");
        defaultTableModelPhieuTra.addColumn("Số ngày mượn");
        defaultTableModelPhieuTra.addColumn("Hạn trả sách");
        defaultTableModelPhieuTra.addColumn("Số lượng sách");
        defaultTableModelPhieuTra.addColumn("Mã tài khoản");
        defaultTableModelPhieuTra.addColumn("Mã quản lý");
        defaultTableModelPhieuTra.addColumn("Ghi chú");
        defaultTableModelPhieuTra.addColumn("Trạng thái");

        for (PhieuMuon phieuMuon : phieuMuons) {
            defaultTableModelPhieuTra.addRow(new Object[] { phieuMuon.getMaPhieuMuon(), phieuMuon.getNgayMuon(),
                    phieuMuon.getSoNgayMuon(), phieuMuon.getHanTraSach(),
                    phieuMuon.getSoLuongSach(), phieuMuon.getMaTaikhoan(), phieuMuon.getMaQuanly(),
                    phieuMuon.getGhiChu(), phieuMuon.getTrangThai() });
        }
    }

    public void loadChiTietTPhieuNhap() {
        defaultTableModel_CTPN = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jTable1.setModel(defaultTableModel_CTPN);
        defaultTableModel_CTPN.addColumn("Mã phiếu nhập");
        defaultTableModel_CTPN.addColumn("Mã sách");
        defaultTableModel_CTPN.addColumn("Tên sách");
        defaultTableModel_CTPN.addColumn("Mã tác giả");
        defaultTableModel_CTPN.addColumn("Mã thể loại");
        defaultTableModel_CTPN.addColumn("Nhà xuất bản");
        defaultTableModel_CTPN.addColumn("Năm xuất bản");
        defaultTableModel_CTPN.addColumn("Số lượng");
        defaultTableModel_CTPN.addColumn("Giá nhập");
        List<ChiTietPhieuNhapSach> chiTietPhieuNhapSaches = ChiTietPhieuNhap_DAO.getInstance().selectAll();
        for (ChiTietPhieuNhapSach ctpns : chiTietPhieuNhapSaches) {
            defaultTableModel_CTPN.addRow(new Object[] { ctpns.getMaPhieuNhap(), ctpns.getMaSach(), ctpns.getTenSach(),
                    ctpns.getMaTacGia(), ctpns.getMaTheLoai(), ctpns.getNXB(), ctpns.getNamXuatBan(),
                    ctpns.getSoLuongNhap(), ctpns.getGiaNhap() });
        }
    }

    public void loadChiTietPhieuMuon() {
        defaultTableModel_CTPM = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jTable4.setModel(defaultTableModel_CTPM);
        defaultTableModel_CTPM.addColumn("Mã phiếu mượn");
        defaultTableModel_CTPM.addColumn("Mã sách");
        defaultTableModel_CTPM.addColumn("Tình trạng sách");
        List<ChiTietPhieuMuon> chiTietPhieuNhapSaches = ChiTietPhieuMuon_DAO.getInstance().selectAll();
        for (ChiTietPhieuMuon ctpns : chiTietPhieuNhapSaches) {
            defaultTableModel_CTPM
                    .addRow(new Object[] { ctpns.getMaPhieumuon(), ctpns.getMaSach(), ctpns.getTinhTrangSach() });
        }
    }

    private void loadComboBoxDanhMuc() {
        List<DanhMucSach> DanhMuc = DanhMucSach_DAO.getInstance().selectAll();
        for (DanhMucSach dm : DanhMuc) {
            Hc_maDM2.addItem(dm.getMaDM());
        }
    }

    private void loadComboBoxTheLoai() {
        List<PhanLoaiSach> theLoais = PhanLoaiSach_DAO.getInstance().selectAll();
        for (PhanLoaiSach tl : theLoais) {
            Hc_maTheLoai2.addItem(tl.getMaTheLoai());
        }
    }

    public void loadmaTacGia() {
        defaultTableModel_TG = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        QLTGiaTable.setModel(defaultTableModel_TG);
        defaultTableModel_TG.addColumn("Mã tác giả");
        defaultTableModel_TG.addColumn("Tên tác giả");
        defaultTableModel_TG.addColumn("Số lượng sách");
        List<TacGia> tacGia = TacGia_DAO.getInstance().selectAll();
        for (TacGia tg : tacGia) {
            defaultTableModel_TG.addRow(new Object[] { tg.getMaTacGia(), tg.getTenTacGia(),
                    tg.getSoSach() });
        }
    }

    public void loadmaDanhMuc() {
        defaultTableModel_DM = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tbl_DMSach4.setModel(defaultTableModel_DM);
        defaultTableModel_DM.addColumn("Mã danh mục");
        defaultTableModel_DM.addColumn("Tên danh mục");
        List<DanhMucSach> danhMucSach = DanhMucSach_DAO.getInstance().selectAll();
        for (DanhMucSach dms : danhMucSach) {
            defaultTableModel_DM.addRow(new Object[] { dms.getMaDM(), dms.getTenDM() });
        }
    }

    public void loadmaTheLoai() {
        defaultTableModel_TL = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tbl_DMSach5.setModel(defaultTableModel_TL);
        defaultTableModel_TL.addColumn("Mã thể loại");
        defaultTableModel_TL.addColumn("Tên thể loại");
        List<PhanLoaiSach> theLoais = PhanLoaiSach_DAO.getInstance().selectAll();
        for (PhanLoaiSach tl : theLoais) {
            defaultTableModel_TL.addRow(new Object[] { tl.getMaTheLoai(), tl.getTenTheLoai() });
        }
    }

    public void loadThongTinSach() {
        defaultTableModel_Sach = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblH_Sach2.setModel(defaultTableModel_Sach);
        defaultTableModel_Sach.addColumn("Mã sách");
        defaultTableModel_Sach.addColumn("Tên sách");
        defaultTableModel_Sach.addColumn("Mã danh mục sách");
        defaultTableModel_Sach.addColumn("Mã thể loại");
        defaultTableModel_Sach.addColumn("Mã tác giả");
        defaultTableModel_Sach.addColumn("Tên tác giả");
        defaultTableModel_Sach.addColumn("NXB");
        defaultTableModel_Sach.addColumn("Năm xuất bản");
        defaultTableModel_Sach.addColumn("Giá tiền sách");
        defaultTableModel_Sach.addColumn("Tình trạng sách");
        defaultTableModel_Sach.addColumn("Tóm tắt ND");
        List<Sach> saches = Sach_DAO.getInstance().selectAll();
        for (Sach sach : saches) {
            defaultTableModel_Sach.addRow(new Object[] { sach.getMaSach(), sach.getTenSach(), sach.getMaDMSach(),
                    sach.getMaTheLoai(), sach.getMaTacGia(), sach.getTenTacGia(), sach.getNXB(), sach.getNamXuatBan(),
                    sach.getGiaTienSach(), sach.getTinhTrangSach(), sach.getTomTatND() });
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jTP_main2 = new javax.swing.JTabbedPane();
        jPanel28 = new javax.swing.JPanel();
        quanlyttdg2 = new javax.swing.JTabbedPane();
        jPanel29 = new javax.swing.JPanel();
        jScrollPane17 = new javax.swing.JScrollPane();
        tableDocgia2 = new javax.swing.JTable();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        gioitinhnam16 = new javax.swing.JRadioButton();
        gioitinhnu16 = new javax.swing.JRadioButton();
        sdt2 = new javax.swing.JTextField();
        matKhauField = new javax.swing.JTextField();
        themmoidg2 = new javax.swing.JButton();
        updatedg2 = new javax.swing.JButton();
        khoatk8 = new javax.swing.JButton();
        ngaysinh2 = new javax.swing.JTextField();
        mokhoa3 = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel92 = new javax.swing.JLabel();
        jLabel127 = new javax.swing.JLabel();
        soLuongmuonLabel = new javax.swing.JLabel();
        tenDocGiaField = new javax.swing.JTextField();
        maDocGiaField = new javax.swing.JTextField();
        soLuongmuonField = new javax.swing.JTextField();
        jLabel143 = new javax.swing.JLabel();
        timKiemDG = new javax.swing.JTextField();
        emailDocgia4 = new javax.swing.JTextField();
        jLabel129 = new javax.swing.JLabel();
        hanDungField1 = new javax.swing.JTextField();
        ngayMotheLabel = new javax.swing.JLabel();
        ngayMotheField = new javax.swing.JTextField();
        Hc_maTheLoai3 = new javax.swing.JComboBox<>();
        updatedg4 = new javax.swing.JButton();
        jPanel40 = new javax.swing.JPanel();
        jScrollPane25 = new javax.swing.JScrollPane();
        tableDocgia3 = new javax.swing.JTable();
        jLabel131 = new javax.swing.JLabel();
        jLabel132 = new javax.swing.JLabel();
        themmoidg3 = new javax.swing.JButton();
        updatedg3 = new javax.swing.JButton();
        mokhoa4 = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel137 = new javax.swing.JLabel();
        jLabel139 = new javax.swing.JLabel();
        thoiGianField = new javax.swing.JTextField();
        maLoaiField = new javax.swing.JTextField();
        soLuongField = new javax.swing.JTextField();
        tenLoaiField = new javax.swing.JTextField();
        tenLoaiField2 = new javax.swing.JTextField();
        jLabel141 = new javax.swing.JLabel();
        thoiGianField1 = new javax.swing.JTextField();
        jLabel142 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel31 = new javax.swing.JPanel();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        H_tenSach2 = new javax.swing.JTextField();
        jLabel95 = new javax.swing.JLabel();
        H_soLuongCon2 = new javax.swing.JTextField();
        jLabel96 = new javax.swing.JLabel();
        H_tacGia4 = new javax.swing.JTextField();
        jLabel97 = new javax.swing.JLabel();
        H_nhaXB2 = new javax.swing.JTextField();
        jLabel98 = new javax.swing.JLabel();
        H_namXB2 = new javax.swing.JTextField();
        jLabel99 = new javax.swing.JLabel();
        H_tomTat2 = new javax.swing.JTextField();
        jLabel100 = new javax.swing.JLabel();
        H_tenDM2 = new javax.swing.JTextField();
        jLabel101 = new javax.swing.JLabel();
        H_tenTheLoai2 = new javax.swing.JTextField();
        jScrollPane18 = new javax.swing.JScrollPane();
        tblH_Sach2 = new javax.swing.JTable();
        btnH_themSach2 = new javax.swing.JButton();
        btnH_suaSach2 = new javax.swing.JButton();
        btn_lamMoiSach2 = new javax.swing.JButton();
        jLabel102 = new javax.swing.JLabel();
        Hc_maTheLoai2 = new javax.swing.JComboBox<>();
        jLabel103 = new javax.swing.JLabel();
        Hc_maDM2 = new javax.swing.JComboBox<>();
        jSeparator6 = new javax.swing.JSeparator();
        khoatk10 = new javax.swing.JButton();
        khoatk11 = new javax.swing.JButton();
        jLabel104 = new javax.swing.JLabel();
        H_tacGia5 = new javax.swing.JTextField();
        H_tenSach3 = new javax.swing.JTextField();
        jPanel32 = new javax.swing.JPanel();
        jScrollPane19 = new javax.swing.JScrollPane();
        tbl_DMSach4 = new javax.swing.JTable();
        jLabel105 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        txt_maDMSach4 = new javax.swing.JTextField();
        txt_tenDMSach10 = new javax.swing.JTextField();
        btn_ThemDMSach6 = new javax.swing.JButton();
        btn_LuuDMSach6 = new javax.swing.JButton();
        btn_SuaDMSach6 = new javax.swing.JButton();
        txt_timkiemDMSach21 = new javax.swing.JTextField();
        jLabel108 = new javax.swing.JLabel();
        btn_lammoi6 = new javax.swing.JButton();
        jPanel33 = new javax.swing.JPanel();
        jScrollPane20 = new javax.swing.JScrollPane();
        tbl_DMSach5 = new javax.swing.JTable();
        jLabel109 = new javax.swing.JLabel();
        jLabel110 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        txt_maDMSach5 = new javax.swing.JTextField();
        txt_tenDMSach11 = new javax.swing.JTextField();
        btn_ThemDMSach7 = new javax.swing.JButton();
        btn_LuuDMSach7 = new javax.swing.JButton();
        btn_SuaDMSach7 = new javax.swing.JButton();
        txt_timkiemDMSach22 = new javax.swing.JTextField();
        jLabel112 = new javax.swing.JLabel();
        btn_lammoi7 = new javax.swing.JButton();
        jPanel34 = new javax.swing.JPanel();
        jLabel113 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        jLabel115 = new javax.swing.JLabel();
        txt_tenDMSach12 = new javax.swing.JTextField();
        btn_ThemDMSach8 = new javax.swing.JButton();
        btn_LuuDMSach8 = new javax.swing.JButton();
        btn_SuaDMSach8 = new javax.swing.JButton();
        txt_timkiemDMSach23 = new javax.swing.JTextField();
        jLabel116 = new javax.swing.JLabel();
        btn_lammoi8 = new javax.swing.JButton();
        jLabel117 = new javax.swing.JLabel();
        txt_tenDMSach13 = new javax.swing.JTextField();
        txt_tenDMSach14 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        QLTGiaTable = new javax.swing.JTable();
        jPK_QuanLyPhieuMuon4 = new javax.swing.JPanel();
        jTPK_QuanLyPM4 = new javax.swing.JTabbedPane();
        Panel_DanhSachPM14 = new javax.swing.JPanel();
        K_tieuDe15 = new javax.swing.JLabel();
        txt_timkiemDMSach24 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        TABLEPhieuXuat = new javax.swing.JTable();
        Panel_DanhSachPM15 = new javax.swing.JPanel();
        K_tieuDe16 = new javax.swing.JLabel();
        btnK_themPM33 = new javax.swing.JButton();
        txt_timkiemDMSach25 = new javax.swing.JTextField();
        btnQLPN = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPK_QuanLyPhieuMuon5 = new javax.swing.JPanel();
        jTPK_QuanLyPM5 = new javax.swing.JTabbedPane();
        Panel_DanhSachPM17 = new javax.swing.JPanel();
        Panel_DanhSachPM16 = new javax.swing.JPanel();
        K_tieuDe17 = new javax.swing.JLabel();
        btnChiTietView = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        table_PhieuTra = new javax.swing.JTable();
        textboxsearch4 = new javax.swing.JTextField();
        jComboBox11 = new javax.swing.JComboBox<>();
        Panel_DanhSachPM18 = new javax.swing.JPanel();
        K_tieuDe19 = new javax.swing.JLabel();
        txt_timkiemDMSach28 = new javax.swing.JTextField();
        gioitinhnam21 = new javax.swing.JRadioButton();
        gioitinhnu21 = new javax.swing.JRadioButton();
        btnK_themPM40 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jPanel35 = new javax.swing.JPanel();
        jTabbedPane7 = new javax.swing.JTabbedPane();
        jPanel37 = new javax.swing.JPanel();
        jLabel121 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tabletksach2 = new javax.swing.JTable();
        jLabel128 = new javax.swing.JLabel();
        cbb_chucNangThongKe7 = new javax.swing.JComboBox<>();
        labelSoluongthongkeSach = new javax.swing.JLabel();
        fieldSoluongthongkesach = new javax.swing.JTextField();
        jPanel39 = new javax.swing.JPanel();
        timkiem2 = new javax.swing.JPanel();
        jLabel124 = new javax.swing.JLabel();
        textboxsearch2 = new javax.swing.JTextField();
        jButton13 = new javax.swing.JButton();
        jLabel125 = new javax.swing.JLabel();
        jLabel126 = new javax.swing.JLabel();
        jComboBox7 = new javax.swing.JComboBox<>();
        jComboBox8 = new javax.swing.JComboBox<>();
        jScrollPane24 = new javax.swing.JScrollPane();
        tableSearchSach2 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 26)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 102));
        jLabel6.setText("QUẢN LÝ THƯ VIỆN");

        jButton1.setBackground(new java.awt.Color(255, 204, 204));
        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/choice.png"))); // NOI18N
        jButton1.setText("Đăng xuất");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/SGU.png"))); // NOI18N

        jLabel37.setFont(new java.awt.Font("Times New Roman", 1, 26)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 0, 102));
        jLabel37.setText(" TRƯỜNG ĐẠI HỌC SÀI GÒN");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(12, Short.MAX_VALUE)
                        .addComponent(jLabel29))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jButton1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel37)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));

        jTP_main2.setBackground(new java.awt.Color(255, 255, 204));
        jTP_main2.setForeground(new java.awt.Color(0, 0, 102));
        jTP_main2.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTP_main2.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTP_main2.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jTP_main2.setMaximumSize(new java.awt.Dimension(300, 300));
        jTP_main2.setPreferredSize(new java.awt.Dimension(300, 300));
        jTP_main2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTP_main2MouseClicked(evt);
            }
        });

        jPanel28.setBackground(new java.awt.Color(204, 204, 255));

        quanlyttdg2.setForeground(new java.awt.Color(0, 0, 153));
        quanlyttdg2.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N

        jPanel29.setBackground(new java.awt.Color(255, 255, 204));

        tableDocgia2.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        tableDocgia2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableDocgia2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDocgia2MouseClicked(evt);
            }
        });
        jScrollPane17.setViewportView(tableDocgia2);

        jLabel86.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(0, 0, 0));
        jLabel86.setText("Mã độc giả:");

        jLabel87.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(0, 0, 0));
        jLabel87.setText("Tên độc giả:");

        jLabel88.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(0, 0, 0));
        jLabel88.setText("Giới tính:");

        jLabel89.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(0, 0, 0));
        jLabel89.setText("Số điện thoại:");

        jLabel90.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(0, 0, 0));
        jLabel90.setText("Phân loại");

        jLabel91.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(0, 0, 0));
        jLabel91.setText("Ngày sinh");

        gioitinhnam16.setBackground(new java.awt.Color(255, 255, 204));
        buttonGroup1.add(gioitinhnam16);
        gioitinhnam16.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        gioitinhnam16.setForeground(new java.awt.Color(0, 0, 0));
        gioitinhnam16.setText("Nam");

        gioitinhnu16.setBackground(new java.awt.Color(255, 255, 204));
        buttonGroup1.add(gioitinhnu16);
        gioitinhnu16.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        gioitinhnu16.setForeground(new java.awt.Color(0, 0, 0));
        gioitinhnu16.setText("Nữ");

        sdt2.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        sdt2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                sdt2KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                sdt2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                sdt2KeyTyped(evt);
            }
        });

        matKhauField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        themmoidg2.setBackground(new java.awt.Color(255, 204, 204));
        themmoidg2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        themmoidg2.setForeground(new java.awt.Color(0, 0, 0));
        themmoidg2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/plus.png"))); // NOI18N
        themmoidg2.setText("Thêm mới");
        themmoidg2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themmoidg2ActionPerformed(evt);
            }
        });

        updatedg2.setBackground(new java.awt.Color(255, 204, 204));
        updatedg2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        updatedg2.setForeground(new java.awt.Color(0, 0, 0));
        updatedg2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/exchange1.png"))); // NOI18N
        updatedg2.setText("Sửa ");
        updatedg2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatedg2ActionPerformed(evt);
            }
        });

        khoatk8.setBackground(new java.awt.Color(255, 204, 204));
        khoatk8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        khoatk8.setForeground(new java.awt.Color(0, 0, 0));
        khoatk8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/block-user.png"))); // NOI18N
        khoatk8.setText("Xóa");
        khoatk8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                khoatk8ActionPerformed(evt);
            }
        });

        ngaysinh2.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        ngaysinh2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ngaysinh2FocusLost(evt);
            }
        });
        ngaysinh2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ngaysinh2KeyPressed(evt);
            }
        });

        mokhoa3.setBackground(new java.awt.Color(255, 204, 204));
        mokhoa3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        mokhoa3.setForeground(new java.awt.Color(0, 0, 0));
        mokhoa3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/eraser.png"))); // NOI18N
        mokhoa3.setText("Làm mới");
        mokhoa3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mokhoa3ActionPerformed(evt);
            }
        });

        jLabel92.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel92.setForeground(new java.awt.Color(0, 0, 0));
        jLabel92.setText("Mật khẩu:");

        jLabel127.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel127.setForeground(new java.awt.Color(0, 0, 0));
        jLabel127.setText("Email:");

        soLuongmuonLabel.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        soLuongmuonLabel.setForeground(new java.awt.Color(0, 0, 0));
        soLuongmuonLabel.setText("Số lượng mượn:");

        tenDocGiaField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        maDocGiaField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        soLuongmuonField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        jLabel143.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel143.setForeground(new java.awt.Color(0, 0, 0));
        jLabel143.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/searching.png"))); // NOI18N
        jLabel143.setText("Tìm kiếm:");

        timKiemDG.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        timKiemDG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timKiemDGActionPerformed(evt);
            }
        });

        emailDocgia4.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        emailDocgia4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailDocgia4ActionPerformed(evt);
            }
        });

        jLabel129.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel129.setForeground(new java.awt.Color(0, 0, 0));
        jLabel129.setText("Hạn dùng:");

        hanDungField1.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        ngayMotheLabel.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        ngayMotheLabel.setForeground(new java.awt.Color(0, 0, 0));
        ngayMotheLabel.setText("Ngày mở thẻ:");

        ngayMotheField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        updatedg4.setBackground(new java.awt.Color(255, 204, 204));
        updatedg4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        updatedg4.setForeground(new java.awt.Color(0, 0, 0));
        updatedg4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/6492572_pixian_ai (1).png"))); // NOI18N
        updatedg4.setText("Gia hạn");
        updatedg4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatedg4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane17)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel29Layout.createSequentialGroup()
                                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel87)
                                    .addComponent(jLabel92)
                                    .addComponent(jLabel86)
                                    .addComponent(jLabel88))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel29Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(gioitinhnam16)
                                        .addGap(45, 45, 45)
                                        .addComponent(gioitinhnu16))
                                    .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(matKhauField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                        .addComponent(maDocGiaField, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(tenDocGiaField)))
                                .addGap(58, 58, 58)
                                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel89)
                                    .addComponent(jLabel90)
                                    .addComponent(jLabel91)
                                    .addComponent(jLabel127))
                                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(sdt2)
                                    .addComponent(ngaysinh2)
                                    .addComponent(emailDocgia4)
                                    .addComponent(Hc_maTheLoai3, 0, 200, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(soLuongmuonLabel)
                                    .addComponent(jLabel129)
                                    .addComponent(ngayMotheLabel))
                                .addGap(32, 32, 32)
                                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(hanDungField1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(soLuongmuonField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ngayMotheField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel29Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel143)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(timKiemDG, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(themmoidg2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(updatedg2)
                                .addGap(18, 18, 18)
                                .addComponent(khoatk8)
                                .addGap(18, 18, 18)
                                .addComponent(mokhoa3)
                                .addGap(18, 18, 18)
                                .addComponent(updatedg4)))
                        .addGap(0, 36, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jSeparator5)
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel86)
                            .addComponent(maDocGiaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel92)
                            .addComponent(matKhauField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel87)
                            .addComponent(tenDocGiaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel88)
                            .addComponent(gioitinhnam16)
                            .addComponent(gioitinhnu16)))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel89)
                            .addComponent(sdt2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel129)
                            .addComponent(hanDungField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel91)
                            .addComponent(ngaysinh2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ngayMotheLabel)
                            .addComponent(ngayMotheField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel127)
                            .addComponent(emailDocgia4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(soLuongmuonLabel)
                            .addComponent(soLuongmuonField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel90)
                            .addComponent(Hc_maTheLoai3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel143)
                    .addComponent(timKiemDG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(themmoidg2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updatedg2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(khoatk8, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mokhoa3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updatedg4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane17, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                .addContainerGap())
        );

        quanlyttdg2.addTab("Quản lý Độc giả", jPanel29);

        jPanel40.setBackground(new java.awt.Color(255, 255, 204));

        tableDocgia3.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        tableDocgia3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableDocgia3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDocgia3MouseClicked(evt);
            }
        });
        jScrollPane25.setViewportView(tableDocgia3);

        jLabel131.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel131.setText("Mã phân loại:");

        jLabel132.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel132.setText("Số lượng sách:");

        themmoidg3.setBackground(new java.awt.Color(255, 204, 204));
        themmoidg3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        themmoidg3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/plus.png"))); // NOI18N
        themmoidg3.setText("Thêm mới");
        themmoidg3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themmoidg3ActionPerformed(evt);
            }
        });

        updatedg3.setBackground(new java.awt.Color(255, 204, 204));
        updatedg3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        updatedg3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/exchange1.png"))); // NOI18N
        updatedg3.setText("Sửa ");
        updatedg3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatedg3ActionPerformed(evt);
            }
        });

        mokhoa4.setBackground(new java.awt.Color(255, 204, 204));
        mokhoa4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        mokhoa4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/eraser.png"))); // NOI18N
        mokhoa4.setText("Làm mới");
        mokhoa4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mokhoa4ActionPerformed(evt);
            }
        });

        jLabel137.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel137.setText("Tên phân loại:");

        jLabel139.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel139.setText("Thời gian mượn:");

        thoiGianField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        maLoaiField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        maLoaiField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maLoaiFieldActionPerformed(evt);
            }
        });

        soLuongField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        tenLoaiField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        tenLoaiField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tenLoaiFieldActionPerformed(evt);
            }
        });

        tenLoaiField2.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        tenLoaiField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tenLoaiField2ActionPerformed(evt);
            }
        });

        jLabel141.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel141.setText("Giá tiền mở thẻ:");

        thoiGianField1.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        jLabel142.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel142.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/searching.png"))); // NOI18N
        jLabel142.setText("Hỗ trợ tìm kiếm:");

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel40Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel40Layout.createSequentialGroup()
                                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel137, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel131))
                                .addGap(37, 37, 37)
                                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(maLoaiField, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                    .addComponent(tenLoaiField)))
                            .addGroup(jPanel40Layout.createSequentialGroup()
                                .addComponent(jLabel141, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(thoiGianField1, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)))
                        .addGap(96, 96, 96)
                        .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel132, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel139, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(soLuongField, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                            .addComponent(thoiGianField))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane25, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel142)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tenLoaiField2, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(159, 159, 159)
                        .addComponent(themmoidg3)
                        .addGap(18, 18, 18)
                        .addComponent(updatedg3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(mokhoa4, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)))
                .addContainerGap())
            .addComponent(jSeparator7)
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel40Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel40Layout.createSequentialGroup()
                        .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel131)
                            .addComponent(maLoaiField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel137)
                            .addComponent(tenLoaiField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel141)
                            .addComponent(thoiGianField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel40Layout.createSequentialGroup()
                        .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel132)
                            .addComponent(soLuongField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel139)
                            .addComponent(thoiGianField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(61, 61, 61)))
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(themmoidg3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updatedg3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mokhoa4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tenLoaiField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel142))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane25, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                .addContainerGap())
        );

        quanlyttdg2.addTab("Phân loại Độc giả", jPanel40);

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(quanlyttdg2)
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(quanlyttdg2)
        );

        jTP_main2.addTab("     QUẢN LÝ ĐỘC GIẢ ", new javax.swing.ImageIcon(getClass().getResource("/Images/reading.png")), jPanel28); // NOI18N

        jPanel30.setBackground(new java.awt.Color(204, 204, 255));

        jTabbedPane4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jTabbedPane4.setForeground(new java.awt.Color(0, 0, 153));
        jTabbedPane4.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N

        jPanel31.setBackground(new java.awt.Color(255, 255, 204));
        jPanel31.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel93.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel93.setForeground(new java.awt.Color(102, 51, 0));
        jLabel93.setText("Mã sách:");

        jLabel94.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel94.setForeground(new java.awt.Color(102, 51, 0));
        jLabel94.setText("Tên sách:");

        H_tenSach2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel95.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel95.setForeground(new java.awt.Color(102, 51, 0));
        jLabel95.setText("Giá tiền:");

        H_soLuongCon2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        H_soLuongCon2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                H_soLuongCon2ActionPerformed(evt);
            }
        });

        jLabel96.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel96.setForeground(new java.awt.Color(102, 51, 0));
        jLabel96.setText("Tác giả:");

        H_tacGia4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel97.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel97.setForeground(new java.awt.Color(102, 51, 0));
        jLabel97.setText("Nhà xuất bản:");

        H_nhaXB2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel98.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel98.setForeground(new java.awt.Color(102, 51, 0));
        jLabel98.setText("Năm xuất bản:");

        H_namXB2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel99.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel99.setForeground(new java.awt.Color(102, 51, 0));
        jLabel99.setText("Tóm tắt nội dung:");

        H_tomTat2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        H_tomTat2.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        jLabel100.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel100.setForeground(new java.awt.Color(102, 51, 0));
        jLabel100.setText("Tên danh mục:");

        H_tenDM2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        H_tenDM2.setEnabled(false);

        jLabel101.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel101.setForeground(new java.awt.Color(102, 51, 0));
        jLabel101.setText("Thể loại:");

        H_tenTheLoai2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        H_tenTheLoai2.setEnabled(false);

        tblH_Sach2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        tblH_Sach2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblH_Sach2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblH_Sach2MouseClicked(evt);
            }
        });
        jScrollPane18.setViewportView(tblH_Sach2);

        btnH_themSach2.setBackground(new java.awt.Color(255, 204, 204));
        btnH_themSach2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnH_themSach2.setForeground(new java.awt.Color(0, 0, 0));
        btnH_themSach2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/plus.png"))); // NOI18N
        btnH_themSach2.setText("Thêm");
        btnH_themSach2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnH_themSach2ActionPerformed(evt);
            }
        });

        btnH_suaSach2.setBackground(new java.awt.Color(255, 204, 204));
        btnH_suaSach2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnH_suaSach2.setForeground(new java.awt.Color(0, 0, 0));
        btnH_suaSach2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/exchange1.png"))); // NOI18N
        btnH_suaSach2.setText("Sửa ");
        btnH_suaSach2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnH_suaSach2ActionPerformed(evt);
            }
        });

        btn_lamMoiSach2.setBackground(new java.awt.Color(255, 204, 204));
        btn_lamMoiSach2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_lamMoiSach2.setForeground(new java.awt.Color(0, 0, 0));
        btn_lamMoiSach2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/eraser.png"))); // NOI18N
        btn_lamMoiSach2.setText("Làm mới");
        btn_lamMoiSach2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lamMoiSach2ActionPerformed(evt);
            }
        });

        jLabel102.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel102.setForeground(new java.awt.Color(102, 51, 0));
        jLabel102.setText("Mã thể loại:");

        Hc_maTheLoai2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        Hc_maTheLoai2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Hc_maTheLoai2ActionPerformed(evt);
            }
        });

        jLabel103.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel103.setForeground(new java.awt.Color(102, 51, 0));
        jLabel103.setText("Mã danh mục:");

        Hc_maDM2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        Hc_maDM2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Hc_maDM2ActionPerformed(evt);
            }
        });

        khoatk10.setBackground(new java.awt.Color(255, 204, 204));
        khoatk10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        khoatk10.setForeground(new java.awt.Color(0, 0, 0));
        khoatk10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/block-user.png"))); // NOI18N
        khoatk10.setText("Xóa");
        khoatk10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                khoatk10ActionPerformed(evt);
            }
        });

        khoatk11.setBackground(new java.awt.Color(255, 204, 204));
        khoatk11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        khoatk11.setForeground(new java.awt.Color(0, 0, 0));
        khoatk11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/searching.png"))); // NOI18N
        khoatk11.setText("Hổ trợ tìm kiếm");
        khoatk11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                khoatk11ActionPerformed(evt);
            }
        });

        jLabel104.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel104.setForeground(new java.awt.Color(102, 51, 0));
        jLabel104.setText("Mã Tác giả:");

        H_tacGia5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        H_tenSach3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGap(1096, 1096, 1096)
                        .addComponent(jSeparator6))
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel31Layout.createSequentialGroup()
                                    .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel93)
                                        .addComponent(jLabel94)
                                        .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel101))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(H_tenSach2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(H_tenSach3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Hc_maTheLoai2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(H_tenTheLoai2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(57, 57, 57)
                                    .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel95)
                                        .addComponent(jLabel97)
                                        .addComponent(jLabel104)
                                        .addComponent(jLabel96))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(H_tacGia5, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                            .addComponent(H_tacGia4)
                                            .addComponent(H_nhaXB2))
                                        .addComponent(H_soLuongCon2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(47, 47, 47)
                                    .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel31Layout.createSequentialGroup()
                                            .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel103)
                                                .addComponent(jLabel100))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(H_tenDM2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(Hc_maDM2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanel31Layout.createSequentialGroup()
                                            .addComponent(jLabel99)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(H_tomTat2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(jPanel31Layout.createSequentialGroup()
                                    .addComponent(btnH_themSach2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnH_suaSach2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(khoatk10, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(khoatk11, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btn_lamMoiSach2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(156, 156, 156)))
                            .addGroup(jPanel31Layout.createSequentialGroup()
                                .addComponent(jLabel98)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(H_namXB2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 1105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel93)
                    .addComponent(H_tenSach2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel96)
                    .addComponent(H_tacGia5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel103)
                    .addComponent(Hc_maDM2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel94)
                    .addComponent(H_tenSach3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel104)
                    .addComponent(H_tacGia4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel100)
                    .addComponent(H_tenDM2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel102)
                            .addComponent(Hc_maTheLoai2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel97)
                            .addComponent(H_nhaXB2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel99))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel101)
                            .addComponent(H_tenTheLoai2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel95)
                            .addComponent(H_soLuongCon2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel98)
                            .addComponent(H_namXB2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(H_tomTat2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnH_themSach2)
                    .addComponent(btnH_suaSach2)
                    .addComponent(khoatk10, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_lamMoiSach2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(khoatk11, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(314, 314, 314))
        );

        jTabbedPane4.addTab("Quản lý Sách", jPanel31);

        jPanel32.setBackground(new java.awt.Color(255, 255, 204));

        tbl_DMSach4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        tbl_DMSach4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbl_DMSach4.setGridColor(new java.awt.Color(255, 255, 255));
        tbl_DMSach4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_DMSach4MouseClicked(evt);
            }
        });
        jScrollPane19.setViewportView(tbl_DMSach4);

        jLabel105.setFont(new java.awt.Font("Times New Roman", 1, 22)); // NOI18N
        jLabel105.setForeground(new java.awt.Color(0, 0, 0));
        jLabel105.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/book.png"))); // NOI18N
        jLabel105.setText("Thông tin danh mục Sách:");

        jLabel106.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel106.setForeground(new java.awt.Color(0, 0, 0));
        jLabel106.setText("Mã danh mục:");

        jLabel107.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel107.setForeground(new java.awt.Color(0, 0, 0));
        jLabel107.setText("Tên danh mục:");

        txt_maDMSach4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt_maDMSach4.setEnabled(false);

        txt_tenDMSach10.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        btn_ThemDMSach6.setBackground(new java.awt.Color(255, 204, 204));
        btn_ThemDMSach6.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btn_ThemDMSach6.setForeground(new java.awt.Color(0, 0, 0));
        btn_ThemDMSach6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/them.png"))); // NOI18N
        btn_ThemDMSach6.setText("Thêm");
        btn_ThemDMSach6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemDMSach6ActionPerformed(evt);
            }
        });

        btn_LuuDMSach6.setBackground(new java.awt.Color(255, 204, 204));
        btn_LuuDMSach6.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btn_LuuDMSach6.setForeground(new java.awt.Color(0, 0, 0));
        btn_LuuDMSach6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/luu.png"))); // NOI18N
        btn_LuuDMSach6.setText("Lưu");
        btn_LuuDMSach6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LuuDMSach6ActionPerformed(evt);
            }
        });

        btn_SuaDMSach6.setBackground(new java.awt.Color(255, 204, 204));
        btn_SuaDMSach6.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btn_SuaDMSach6.setForeground(new java.awt.Color(0, 0, 0));
        btn_SuaDMSach6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/exchange1.png"))); // NOI18N
        btn_SuaDMSach6.setText("Sửa");
        btn_SuaDMSach6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SuaDMSach6ActionPerformed(evt);
            }
        });

        txt_timkiemDMSach21.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt_timkiemDMSach21.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_timkiemDMSach21KeyReleased(evt);
            }
        });

        jLabel108.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel108.setForeground(new java.awt.Color(0, 0, 102));
        jLabel108.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/searching.png"))); // NOI18N
        jLabel108.setText("Tìm kiếm");

        btn_lammoi6.setBackground(new java.awt.Color(255, 204, 204));
        btn_lammoi6.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btn_lammoi6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/lammoi.png"))); // NOI18N
        btn_lammoi6.setText("Làm mới");
        btn_lammoi6.setEnabled(false);
        btn_lammoi6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lammoi6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel32Layout.createSequentialGroup()
                                .addComponent(jLabel106)
                                .addGap(18, 18, 18)
                                .addComponent(txt_maDMSach4))
                            .addGroup(jPanel32Layout.createSequentialGroup()
                                .addComponent(jLabel107)
                                .addGap(15, 15, 15)
                                .addComponent(txt_tenDMSach10, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btn_ThemDMSach6, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_SuaDMSach6, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btn_lammoi6)
                                    .addComponent(btn_LuuDMSach6, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(83, 83, 83))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                                .addComponent(jLabel105)
                                .addGap(121, 121, 121)))))
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addComponent(txt_timkiemDMSach21, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel108))
                    .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(71, Short.MAX_VALUE))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_timkiemDMSach21, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel108)
                            .addComponent(jLabel105))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel106)
                            .addComponent(txt_maDMSach4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(47, 47, 47)
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel107)
                            .addComponent(txt_tenDMSach10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_ThemDMSach6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_LuuDMSach6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_SuaDMSach6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_lammoi6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(262, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Quản lý Danh mục", jPanel32);

        jPanel33.setBackground(new java.awt.Color(255, 255, 204));

        tbl_DMSach5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        tbl_DMSach5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbl_DMSach5.setGridColor(new java.awt.Color(255, 255, 255));
        tbl_DMSach5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_DMSach5MouseClicked(evt);
            }
        });
        jScrollPane20.setViewportView(tbl_DMSach5);

        jLabel109.setFont(new java.awt.Font("Times New Roman", 1, 22)); // NOI18N
        jLabel109.setForeground(new java.awt.Color(0, 0, 0));
        jLabel109.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/book.png"))); // NOI18N
        jLabel109.setText("Thông tin thể loại Sách:");

        jLabel110.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel110.setForeground(new java.awt.Color(0, 0, 0));
        jLabel110.setText("Mã thể loại:");

        jLabel111.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel111.setForeground(new java.awt.Color(0, 0, 0));
        jLabel111.setText("Tên thể loại:");

        txt_maDMSach5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt_maDMSach5.setEnabled(false);

        txt_tenDMSach11.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        btn_ThemDMSach7.setBackground(new java.awt.Color(255, 204, 204));
        btn_ThemDMSach7.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btn_ThemDMSach7.setForeground(new java.awt.Color(0, 0, 0));
        btn_ThemDMSach7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/them.png"))); // NOI18N
        btn_ThemDMSach7.setText("Thêm");
        btn_ThemDMSach7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemDMSach7ActionPerformed(evt);
            }
        });

        btn_LuuDMSach7.setBackground(new java.awt.Color(255, 204, 204));
        btn_LuuDMSach7.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btn_LuuDMSach7.setForeground(new java.awt.Color(0, 0, 0));
        btn_LuuDMSach7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/luu.png"))); // NOI18N
        btn_LuuDMSach7.setText("Lưu");
        btn_LuuDMSach7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LuuDMSach7ActionPerformed(evt);
            }
        });

        btn_SuaDMSach7.setBackground(new java.awt.Color(255, 204, 204));
        btn_SuaDMSach7.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btn_SuaDMSach7.setForeground(new java.awt.Color(0, 0, 0));
        btn_SuaDMSach7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/exchange1.png"))); // NOI18N
        btn_SuaDMSach7.setText("Sửa");
        btn_SuaDMSach7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SuaDMSach7ActionPerformed(evt);
            }
        });

        txt_timkiemDMSach22.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt_timkiemDMSach22.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_timkiemDMSach22KeyReleased(evt);
            }
        });

        jLabel112.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel112.setForeground(new java.awt.Color(0, 0, 102));
        jLabel112.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/searching.png"))); // NOI18N
        jLabel112.setText("Tìm kiếm");

        btn_lammoi7.setBackground(new java.awt.Color(255, 204, 204));
        btn_lammoi7.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btn_lammoi7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/lammoi.png"))); // NOI18N
        btn_lammoi7.setText("Làm mới");
        btn_lammoi7.setEnabled(false);
        btn_lammoi7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lammoi7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel33Layout.createSequentialGroup()
                                .addComponent(jLabel110)
                                .addGap(18, 18, 18)
                                .addComponent(txt_maDMSach5))
                            .addGroup(jPanel33Layout.createSequentialGroup()
                                .addComponent(jLabel111)
                                .addGap(15, 15, 15)
                                .addComponent(txt_tenDMSach11, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel33Layout.createSequentialGroup()
                        .addContainerGap(179, Short.MAX_VALUE)
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel33Layout.createSequentialGroup()
                                .addComponent(jLabel109)
                                .addGap(121, 121, 121))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel33Layout.createSequentialGroup()
                                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btn_ThemDMSach7, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_SuaDMSach7, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btn_lammoi7)
                                    .addComponent(btn_LuuDMSach7, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(83, 83, 83)))))
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addComponent(txt_timkiemDMSach22, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel112))
                    .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(76, Short.MAX_VALUE))
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_timkiemDMSach22, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel112))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(jLabel109)
                        .addGap(44, 44, 44)
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel110)
                            .addComponent(txt_maDMSach5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(47, 47, 47)
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel111)
                            .addComponent(txt_tenDMSach11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_ThemDMSach7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_LuuDMSach7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_SuaDMSach7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_lammoi7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(262, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Quản lý Thể loại", jPanel33);

        jPanel34.setBackground(new java.awt.Color(255, 255, 204));

        jLabel113.setFont(new java.awt.Font("Times New Roman", 1, 22)); // NOI18N
        jLabel113.setForeground(new java.awt.Color(0, 0, 0));
        jLabel113.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/book.png"))); // NOI18N
        jLabel113.setText("Thông tin tác giả:");

        jLabel114.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel114.setForeground(new java.awt.Color(0, 0, 0));
        jLabel114.setText("Mã tác giả:");

        jLabel115.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel115.setForeground(new java.awt.Color(0, 0, 0));
        jLabel115.setText("Tên tác giả:");

        txt_tenDMSach12.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        btn_ThemDMSach8.setBackground(new java.awt.Color(255, 204, 204));
        btn_ThemDMSach8.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btn_ThemDMSach8.setForeground(new java.awt.Color(0, 0, 0));
        btn_ThemDMSach8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/them.png"))); // NOI18N
        btn_ThemDMSach8.setText("Thêm");
        btn_ThemDMSach8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemDMSach8ActionPerformed(evt);
            }
        });

        btn_LuuDMSach8.setBackground(new java.awt.Color(255, 204, 204));
        btn_LuuDMSach8.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btn_LuuDMSach8.setForeground(new java.awt.Color(0, 0, 0));
        btn_LuuDMSach8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/luu.png"))); // NOI18N
        btn_LuuDMSach8.setText("Lưu");
        btn_LuuDMSach8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LuuDMSach8ActionPerformed(evt);
            }
        });

        btn_SuaDMSach8.setBackground(new java.awt.Color(255, 204, 204));
        btn_SuaDMSach8.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btn_SuaDMSach8.setForeground(new java.awt.Color(0, 0, 0));
        btn_SuaDMSach8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/exchange1.png"))); // NOI18N
        btn_SuaDMSach8.setText("Sửa");
        btn_SuaDMSach8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SuaDMSach8ActionPerformed(evt);
            }
        });

        txt_timkiemDMSach23.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt_timkiemDMSach23.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_timkiemDMSach23KeyReleased(evt);
            }
        });

        jLabel116.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel116.setForeground(new java.awt.Color(0, 0, 102));
        jLabel116.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/searching.png"))); // NOI18N
        jLabel116.setText("Tìm kiếm");

        btn_lammoi8.setBackground(new java.awt.Color(255, 204, 204));
        btn_lammoi8.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btn_lammoi8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/lammoi.png"))); // NOI18N
        btn_lammoi8.setText("Làm mới");
        btn_lammoi8.setEnabled(false);
        btn_lammoi8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lammoi8ActionPerformed(evt);
            }
        });

        jLabel117.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel117.setForeground(new java.awt.Color(0, 0, 0));
        jLabel117.setText("Số lượng sách:");

        txt_tenDMSach13.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        txt_tenDMSach14.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        QLTGiaTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã tác giả", "Tên tác giả", "Số lượng sách"
            }
        ));
        jScrollPane3.setViewportView(QLTGiaTable);

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel115)
                            .addComponent(jLabel117)
                            .addComponent(jLabel114))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_tenDMSach12, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_tenDMSach13, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_tenDMSach14, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel34Layout.createSequentialGroup()
                        .addContainerGap(184, Short.MAX_VALUE)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel34Layout.createSequentialGroup()
                                .addComponent(jLabel113)
                                .addGap(123, 123, 123))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel34Layout.createSequentialGroup()
                                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btn_ThemDMSach8, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_SuaDMSach8, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btn_lammoi8)
                                    .addComponent(btn_LuuDMSach8, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(85, 85, 85)))))
                .addGap(5, 5, 5)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addComponent(txt_timkiemDMSach23, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel116))
                    .addComponent(jScrollPane3))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_timkiemDMSach23, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel116))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addComponent(jLabel113)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_tenDMSach14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel114))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel115)
                            .addComponent(txt_tenDMSach13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel117)
                            .addComponent(txt_tenDMSach12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(146, 146, 146)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_ThemDMSach8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_LuuDMSach8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_SuaDMSach8, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_lammoi8, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(261, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Quản lý Tác giả", jPanel34);

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane4)
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane4)
        );

        jTP_main2.addTab("          QUẢN LÝ SÁCH  ", new javax.swing.ImageIcon(getClass().getResource("/Images/books.png")), jPanel30); // NOI18N

        jPK_QuanLyPhieuMuon4.setBackground(new java.awt.Color(204, 204, 255));
        jPK_QuanLyPhieuMuon4.setPreferredSize(new java.awt.Dimension(1000, 704));

        jTPK_QuanLyPM4.setForeground(new java.awt.Color(51, 0, 102));
        jTPK_QuanLyPM4.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jTPK_QuanLyPM4.setPreferredSize(new java.awt.Dimension(900, 704));

        Panel_DanhSachPM14.setBackground(new java.awt.Color(255, 255, 204));
        Panel_DanhSachPM14.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        K_tieuDe15.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        K_tieuDe15.setForeground(new java.awt.Color(0, 0, 0));
        K_tieuDe15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/bill.png"))); // NOI18N
        K_tieuDe15.setText("DANH SÁCH PHIẾU XUẤT");

        txt_timkiemDMSach24.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt_timkiemDMSach24.setText("Tìm kiếm");
        txt_timkiemDMSach24.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_timkiemDMSach24KeyReleased(evt);
            }
        });

        TABLEPhieuXuat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        TABLEPhieuXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TABLEPhieuXuatMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TABLEPhieuXuat);

        javax.swing.GroupLayout Panel_DanhSachPM14Layout = new javax.swing.GroupLayout(Panel_DanhSachPM14);
        Panel_DanhSachPM14.setLayout(Panel_DanhSachPM14Layout);
        Panel_DanhSachPM14Layout.setHorizontalGroup(
            Panel_DanhSachPM14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_DanhSachPM14Layout.createSequentialGroup()
                .addGroup(Panel_DanhSachPM14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_DanhSachPM14Layout.createSequentialGroup()
                        .addGap(316, 316, 316)
                        .addComponent(K_tieuDe15, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Panel_DanhSachPM14Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addGroup(Panel_DanhSachPM14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 984, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_timkiemDMSach24, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        Panel_DanhSachPM14Layout.setVerticalGroup(
            Panel_DanhSachPM14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_DanhSachPM14Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(K_tieuDe15, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_timkiemDMSach24, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jTPK_QuanLyPM4.addTab("Danh sách phiếu xuất", Panel_DanhSachPM14);

        Panel_DanhSachPM15.setBackground(new java.awt.Color(255, 255, 204));
        Panel_DanhSachPM15.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        K_tieuDe16.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        K_tieuDe16.setForeground(new java.awt.Color(0, 0, 0));
        K_tieuDe16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/bill.png"))); // NOI18N
        K_tieuDe16.setText("DANH SÁCH CÁC PHIẾU NHẬP");

        btnK_themPM33.setBackground(new java.awt.Color(255, 204, 204));
        btnK_themPM33.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnK_themPM33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/plus.png"))); // NOI18N
        btnK_themPM33.setText("Xem chi tiết");
        btnK_themPM33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnK_themPM33btnK_themPM1ActionPerformed(evt);
            }
        });

        txt_timkiemDMSach25.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt_timkiemDMSach25.setText("Tìm kiếm");
        txt_timkiemDMSach25.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_timkiemDMSach25txt_timkiemDMSach3KeyReleased(evt);
            }
        });

        btnQLPN.setBackground(new java.awt.Color(255, 204, 204));
        btnQLPN.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnQLPN.setForeground(new java.awt.Color(0, 0, 0));
        btnQLPN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/plus.png"))); // NOI18N
        btnQLPN.setText("Quản lý phiếu nhập");
        btnQLPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQLPNbtnK_themPM2ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout Panel_DanhSachPM15Layout = new javax.swing.GroupLayout(Panel_DanhSachPM15);
        Panel_DanhSachPM15.setLayout(Panel_DanhSachPM15Layout);
        Panel_DanhSachPM15Layout.setHorizontalGroup(
            Panel_DanhSachPM15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_DanhSachPM15Layout.createSequentialGroup()
                .addGroup(Panel_DanhSachPM15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_DanhSachPM15Layout.createSequentialGroup()
                        .addGap(316, 316, 316)
                        .addComponent(K_tieuDe16, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Panel_DanhSachPM15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(Panel_DanhSachPM15Layout.createSequentialGroup()
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnQLPN, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnK_themPM33))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Panel_DanhSachPM15Layout.createSequentialGroup()
                            .addGap(98, 98, 98)
                            .addGroup(Panel_DanhSachPM15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txt_timkiemDMSach25, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 984, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        Panel_DanhSachPM15Layout.setVerticalGroup(
            Panel_DanhSachPM15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_DanhSachPM15Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(K_tieuDe16, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_timkiemDMSach25, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(Panel_DanhSachPM15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnQLPN)
                    .addComponent(btnK_themPM33))
                .addGap(30, 30, 30))
        );

        jTPK_QuanLyPM4.addTab("Danh sách phiếu nhập", Panel_DanhSachPM15);

        javax.swing.GroupLayout jPK_QuanLyPhieuMuon4Layout = new javax.swing.GroupLayout(jPK_QuanLyPhieuMuon4);
        jPK_QuanLyPhieuMuon4.setLayout(jPK_QuanLyPhieuMuon4Layout);
        jPK_QuanLyPhieuMuon4Layout.setHorizontalGroup(
            jPK_QuanLyPhieuMuon4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTPK_QuanLyPM4, javax.swing.GroupLayout.DEFAULT_SIZE, 1126, Short.MAX_VALUE)
        );
        jPK_QuanLyPhieuMuon4Layout.setVerticalGroup(
            jPK_QuanLyPhieuMuon4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTPK_QuanLyPM4, javax.swing.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
        );

        jTP_main2.addTab(" QUẢN LÝ NHẬP XUẤT ", new javax.swing.ImageIcon(getClass().getResource("/Images/exchange.png")), jPK_QuanLyPhieuMuon4); // NOI18N

        jPK_QuanLyPhieuMuon5.setBackground(new java.awt.Color(204, 204, 255));
        jPK_QuanLyPhieuMuon5.setPreferredSize(new java.awt.Dimension(1000, 704));

        jTPK_QuanLyPM5.setForeground(new java.awt.Color(51, 0, 102));
        jTPK_QuanLyPM5.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jTPK_QuanLyPM5.setPreferredSize(new java.awt.Dimension(900, 704));

        Panel_DanhSachPM17.setBackground(new java.awt.Color(255, 255, 204));
        Panel_DanhSachPM17.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        Panel_DanhSachPM16.setBackground(new java.awt.Color(255, 255, 204));
        Panel_DanhSachPM16.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        K_tieuDe17.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        K_tieuDe17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/bill.png"))); // NOI18N
        K_tieuDe17.setText("DANH SÁCH CÁC PHIẾU TRẢ");

        btnChiTietView.setBackground(new java.awt.Color(255, 204, 204));
        btnChiTietView.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnChiTietView.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/plus.png"))); // NOI18N
        btnChiTietView.setText("Xem chi tiết");
        btnChiTietView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChiTietViewActionPerformed(evt);
            }
        });

        table_PhieuTra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane6.setViewportView(table_PhieuTra);

        textboxsearch4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textboxsearch4ActionPerformed(evt);
            }
        });
        textboxsearch4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textboxsearch4KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textboxsearch4KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textboxsearch4KeyTyped(evt);
            }
        });

        jComboBox11.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jComboBox11.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Đã trả", "Chưa trả" }));
        jComboBox11.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox11ItemStateChanged(evt);
            }
        });
        jComboBox11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Panel_DanhSachPM16Layout = new javax.swing.GroupLayout(Panel_DanhSachPM16);
        Panel_DanhSachPM16.setLayout(Panel_DanhSachPM16Layout);
        Panel_DanhSachPM16Layout.setHorizontalGroup(
            Panel_DanhSachPM16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_DanhSachPM16Layout.createSequentialGroup()
                .addGroup(Panel_DanhSachPM16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(Panel_DanhSachPM16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(Panel_DanhSachPM16Layout.createSequentialGroup()
                            .addGap(923, 923, 923)
                            .addComponent(btnChiTietView))
                        .addGroup(Panel_DanhSachPM16Layout.createSequentialGroup()
                            .addGap(340, 340, 340)
                            .addComponent(jComboBox11, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(K_tieuDe17, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(Panel_DanhSachPM16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 1041, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(textboxsearch4, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        Panel_DanhSachPM16Layout.setVerticalGroup(
            Panel_DanhSachPM16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_DanhSachPM16Layout.createSequentialGroup()
                .addGroup(Panel_DanhSachPM16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_DanhSachPM16Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(K_tieuDe17, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Panel_DanhSachPM16Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(Panel_DanhSachPM16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textboxsearch4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox11, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(btnChiTietView)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Panel_DanhSachPM17Layout = new javax.swing.GroupLayout(Panel_DanhSachPM17);
        Panel_DanhSachPM17.setLayout(Panel_DanhSachPM17Layout);
        Panel_DanhSachPM17Layout.setHorizontalGroup(
            Panel_DanhSachPM17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1120, Short.MAX_VALUE)
            .addGroup(Panel_DanhSachPM17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Panel_DanhSachPM17Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Panel_DanhSachPM16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        Panel_DanhSachPM17Layout.setVerticalGroup(
            Panel_DanhSachPM17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 478, Short.MAX_VALUE)
            .addGroup(Panel_DanhSachPM17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Panel_DanhSachPM17Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Panel_DanhSachPM16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTPK_QuanLyPM5.addTab("Danh sách phiếu trả", Panel_DanhSachPM17);

        Panel_DanhSachPM18.setBackground(new java.awt.Color(255, 255, 204));
        Panel_DanhSachPM18.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        K_tieuDe19.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        K_tieuDe19.setForeground(new java.awt.Color(0, 0, 0));
        K_tieuDe19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/bill.png"))); // NOI18N
        K_tieuDe19.setText("CÁC PHIẾU MƯỢN ĐÃ ĐĂNG KÝ");

        txt_timkiemDMSach28.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt_timkiemDMSach28.setText("Tìm kiếm");
        txt_timkiemDMSach28.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_timkiemDMSach28txt_timkiemDMSach3KeyReleased(evt);
            }
        });

        gioitinhnam21.setBackground(new java.awt.Color(255, 255, 204));
        gioitinhnam21.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        gioitinhnam21.setForeground(new java.awt.Color(0, 0, 0));
        gioitinhnam21.setText("Theo tên độc giả");

        gioitinhnu21.setBackground(new java.awt.Color(255, 255, 204));
        gioitinhnu21.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        gioitinhnu21.setForeground(new java.awt.Color(0, 0, 0));
        gioitinhnu21.setText("Theo mã phiếu mượn");

        btnK_themPM40.setBackground(new java.awt.Color(255, 204, 204));
        btnK_themPM40.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnK_themPM40.setForeground(new java.awt.Color(0, 0, 0));
        btnK_themPM40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/plus.png"))); // NOI18N
        btnK_themPM40.setText("Quản lý phiếu mượn");
        btnK_themPM40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnK_themPM40btnK_themPM2ActionPerformed(evt);
            }
        });

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(jTable4);

        javax.swing.GroupLayout Panel_DanhSachPM18Layout = new javax.swing.GroupLayout(Panel_DanhSachPM18);
        Panel_DanhSachPM18.setLayout(Panel_DanhSachPM18Layout);
        Panel_DanhSachPM18Layout.setHorizontalGroup(
            Panel_DanhSachPM18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_DanhSachPM18Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnK_themPM40, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95))
            .addGroup(Panel_DanhSachPM18Layout.createSequentialGroup()
                .addGroup(Panel_DanhSachPM18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_DanhSachPM18Layout.createSequentialGroup()
                        .addGap(316, 316, 316)
                        .addComponent(K_tieuDe19, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Panel_DanhSachPM18Layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(txt_timkiemDMSach28, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(gioitinhnam21)
                        .addGap(32, 32, 32)
                        .addComponent(gioitinhnu21))
                    .addGroup(Panel_DanhSachPM18Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 950, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(156, Short.MAX_VALUE))
        );
        Panel_DanhSachPM18Layout.setVerticalGroup(
            Panel_DanhSachPM18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_DanhSachPM18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(K_tieuDe19, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Panel_DanhSachPM18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_timkiemDMSach28, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gioitinhnam21)
                    .addComponent(gioitinhnu21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(btnK_themPM40)
                .addGap(29, 29, 29))
        );

        jTPK_QuanLyPM5.addTab("Danh sách phiếu mượn", Panel_DanhSachPM18);

        javax.swing.GroupLayout jPK_QuanLyPhieuMuon5Layout = new javax.swing.GroupLayout(jPK_QuanLyPhieuMuon5);
        jPK_QuanLyPhieuMuon5.setLayout(jPK_QuanLyPhieuMuon5Layout);
        jPK_QuanLyPhieuMuon5Layout.setHorizontalGroup(
            jPK_QuanLyPhieuMuon5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTPK_QuanLyPM5, javax.swing.GroupLayout.DEFAULT_SIZE, 1126, Short.MAX_VALUE)
        );
        jPK_QuanLyPhieuMuon5Layout.setVerticalGroup(
            jPK_QuanLyPhieuMuon5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTPK_QuanLyPM5, javax.swing.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
        );

        jTP_main2.addTab(" QUẢN LÝ MƯỢN TRẢ ", new javax.swing.ImageIcon(getClass().getResource("/Images/exchange.png")), jPK_QuanLyPhieuMuon5); // NOI18N

        jPanel35.setBackground(new java.awt.Color(204, 204, 255));

        jTabbedPane7.setForeground(new java.awt.Color(0, 0, 102));
        jTabbedPane7.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N

        jPanel37.setBackground(new java.awt.Color(255, 255, 204));

        jLabel121.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel121.setForeground(new java.awt.Color(204, 0, 0));
        jLabel121.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/open-book.png"))); // NOI18N
        jLabel121.setText("Lựa chọn thông tin bạn muốn thống kê!");

        tabletksach2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", "S001", "Giáo trình kỹ thuật xung số và ứng dụng", "7"},
                {"2", "S002", "Lập trình cơ bản với C", "15"},
                {"3", "S003", "Trường điện từ - Lý thuyết và bài tập", "5"},
                {"4", "S004", "Cơ sở dữ liệu", "9"},
                {"5", "S005", "Tin học văn phòng", "2"},
                {"6", "S006", "Bơm nhiệt", "3"},
                {"7", "S007", "Cơ sở thiết kế máy", "4"},
                {"8", "S008", "Đo lường nhiệt", "2"},
                {"9", "S009", "Nhiên liệu sạch", "3"},
                {"10", "S010", null, "0"}
            },
            new String [] {
                "STT", "Mã sách", "Tên sách", "Số lượng"
            }
        ));
        jScrollPane7.setViewportView(tabletksach2);

        jLabel128.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel128.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/book.png"))); // NOI18N
        jLabel128.setText("Lựa chọn:");

        cbb_chucNangThongKe7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        cbb_chucNangThongKe7.setForeground(new java.awt.Color(0, 0, 153));
        cbb_chucNangThongKe7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Số lượng sách còn", "Số lượng sách hỏng", "Tổng số lượng" }));
        cbb_chucNangThongKe7.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbb_chucNangThongKe7ItemStateChanged(evt);
            }
        });

        labelSoluongthongkeSach.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        labelSoluongthongkeSach.setText("Số lượng:");

        fieldSoluongthongkesach.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel37Layout.createSequentialGroup()
                                .addComponent(jLabel128)
                                .addGap(22, 22, 22)
                                .addComponent(cbb_chucNangThongKe7, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(labelSoluongthongkeSach)
                                .addGap(18, 18, 18)
                                .addComponent(fieldSoluongthongkesach, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 833, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(jLabel121, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(188, Short.MAX_VALUE))
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbb_chucNangThongKe7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel128, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSoluongthongkeSach, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldSoluongthongkesach))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel121)
                .addGap(128, 128, 128))
        );

        jTabbedPane7.addTab("Thống Kê Sách", jPanel37);

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane7)
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane7)
        );

        jTP_main2.addTab("                   THỐNG KÊ ", new javax.swing.ImageIcon(getClass().getResource("/Images/statistics.png")), jPanel35); // NOI18N

        jPanel39.setBackground(new java.awt.Color(204, 204, 255));

        timkiem2.setBackground(new java.awt.Color(255, 255, 204));

        jLabel124.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel124.setForeground(new java.awt.Color(0, 0, 255));
        jLabel124.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/searching.png"))); // NOI18N
        jLabel124.setText("Tìm kiếm sách:");

        textboxsearch2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textboxsearch2ActionPerformed(evt);
            }
        });
        textboxsearch2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textboxsearch2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textboxsearch2KeyTyped(evt);
            }
        });

        jButton13.setBackground(new java.awt.Color(255, 204, 204));
        jButton13.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton13.setText("Tìm kiếm");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jLabel125.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel125.setForeground(new java.awt.Color(0, 0, 102));
        jLabel125.setText("Lọc theo danh mục:");

        jLabel126.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel126.setForeground(new java.awt.Color(0, 0, 102));
        jLabel126.setText("Lọc theo thể loại:");

        jComboBox7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tìm theo danh mục", "Chuyên ngành Điện-Điện tử", "Chuyên ngành Cơ khí", "Chuyên ngành Công nghệ thông tin", "Chuyên ngành Xây dựng", "Sách Tiếng Anh", "Kỹ năng sống" }));
        jComboBox7.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox7ItemStateChanged(evt);
            }
        });

        jComboBox8.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tìm theo thể loại", "Giáo trình học", "Sách tham khảo", "Văn hóa lịch sử", "Chính trị, Pháp luật", "Tạp chí" }));
        jComboBox8.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox8ItemStateChanged(evt);
            }
        });

        tableSearchSach2.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        tableSearchSach2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên sách", "Tác giả", "Nhà sản xuất", "Số lượng"
            }
        ));
        jScrollPane24.setViewportView(tableSearchSach2);

        javax.swing.GroupLayout timkiem2Layout = new javax.swing.GroupLayout(timkiem2);
        timkiem2.setLayout(timkiem2Layout);
        timkiem2Layout.setHorizontalGroup(
            timkiem2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(timkiem2Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(timkiem2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane24)
                    .addGroup(timkiem2Layout.createSequentialGroup()
                        .addGroup(timkiem2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel126)
                            .addComponent(jLabel125, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel124))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(timkiem2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(timkiem2Layout.createSequentialGroup()
                                .addComponent(textboxsearch2, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(timkiem2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jComboBox8, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBox7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 381, Short.MAX_VALUE)))
                .addContainerGap())
        );
        timkiem2Layout.setVerticalGroup(
            timkiem2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(timkiem2Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(timkiem2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(textboxsearch2)
                    .addComponent(jLabel124, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(36, 36, 36)
                .addGroup(timkiem2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel125)
                    .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(timkiem2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(timkiem2Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel126))
                    .addGroup(timkiem2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox8)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane24, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1126, Short.MAX_VALUE)
            .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(timkiem2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 624, Short.MAX_VALUE)
            .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(timkiem2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTP_main2.addTab("                      TRA CỨU", new javax.swing.ImageIcon(getClass().getResource("/Images/research.png")), jPanel39); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTP_main2, javax.swing.GroupLayout.PREFERRED_SIZE, 1435, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jTP_main2, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TABLEPhieuXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TABLEPhieuXuatMouseClicked
        List<String> thanhLySach = new ArrayList<>();
        int row = TABLEPhieuXuat.getSelectedRow();
        String Id = TABLEPhieuXuat.getValueAt(row, 0).toString();
        String Idqly = TABLEPhieuXuat.getValueAt(row, 1).toString();
        String Idbook = TABLEPhieuXuat.getValueAt(row, 2).toString();
        String namebook = TABLEPhieuXuat.getValueAt(row, 3).toString();
        String soluong = TABLEPhieuXuat.getValueAt(row, 4).toString();
        String lydo = TABLEPhieuXuat.getValueAt(row, 5).toString();
        String ngayxuat = TABLEPhieuXuat.getValueAt(row, 6).toString();
        String ghichu = TABLEPhieuXuat.getValueAt(row, 7).toString();
        String tongtien = TABLEPhieuXuat.getValueAt(row, 8).toString();
        thanhLySach.add(Id); 
        thanhLySach.add(Idqly); 
        thanhLySach.add(Idbook); 
        thanhLySach.add(namebook); 
        thanhLySach.add(soluong); 
        thanhLySach.add(lydo); 
        thanhLySach.add(ngayxuat); 
        thanhLySach.add(ghichu); 
        thanhLySach.add(tongtien);
        ThuThuQuanLyPhieuXuat thuQuanLyPhieuXuat = new ThuThuQuanLyPhieuXuat(thanhLySach);
        thuQuanLyPhieuXuat.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_TABLEPhieuXuatMouseClicked

    private void txt_timkiemDMSach24KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_timkiemDMSach24KeyReleased
        String searchTerm = txt_timkiemDMSach24.getText();
        loaddataPX((AbstractMap.SimpleEntry<List<Sach>, List<ThanhLySach>>) thanhLyBLL.search(searchTerm));    }//GEN-LAST:event_txt_timkiemDMSach24KeyReleased

    private void btnChiTietViewActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnChiTietViewActionPerformed
        int row = table_PhieuTra.getSelectedRow();
        String id = table_PhieuTra.getValueAt(row, 0).toString();
        // boolean tinhtrang = false;
        // if((table_PhieuTra.getValueAt(row, 8).toString()).equals("Chưa trả")){
        // tinhtrang = true;
        // }
        ThuThuChiTietPhieuTra thuthu = new ThuThuChiTietPhieuTra(id);
        thuthu.setVisible(true);
        this.dispose();
    }// GEN-LAST:event_btnChiTietViewActionPerformed

    private void textboxsearch4ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_textboxsearch4ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_textboxsearch4ActionPerformed

    private void textboxsearch4KeyPressed(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_textboxsearch4KeyPressed
        // TODO add your handling code here:
    }// GEN-LAST:event_textboxsearch4KeyPressed

    private void textboxsearch4KeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_textboxsearch4KeyReleased
        String searchTerm = textboxsearch4.getText();
        loadPhieuTra(phieuTra_BLL.search(searchTerm));
    }// GEN-LAST:event_textboxsearch4KeyReleased

    private void textboxsearch4KeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_textboxsearch4KeyTyped
        // TODO add your handling code here:
    }// GEN-LAST:event_textboxsearch4KeyTyped

    private void jComboBox11ItemStateChanged(java.awt.event.ItemEvent evt) {// GEN-FIRST:event_jComboBox11ItemStateChanged
        // TODO add your handling code here:
    }// GEN-LAST:event_jComboBox11ItemStateChanged

    private void jComboBox11ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jComboBox11ActionPerformed
        String select = jComboBox11.getSelectedItem().toString();
        if (select.equals("Tất cả")) {
            loadPhieuTra(phieuTra_BLL.loaddata());
        } else {
            loadPhieuTra(phieuTra_BLL.loadPMintoTT(select));
        }

    }// GEN-LAST:event_jComboBox11ActionPerformed

    private void emailDocgia4ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_emailDocgia4ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_emailDocgia4ActionPerformed

    private void cbb_chucNangThongKe7ItemStateChanged(java.awt.event.ItemEvent evt) {// GEN-FIRST:event_cbb_chucNangThongKe7ItemStateChanged
        // TODO add your handling code here:
        int index = cbb_chucNangThongKe7.getSelectedIndex();
        DefaultTableModel sachtb = (DefaultTableModel) tabletksach2.getModel();
        sachtb.setRowCount(0);
        List<KhoSach> sachByCate;
        int soluong =0;
        if (index==0){
            sachByCate = ThongKeDao.getInstance().SachCon();
            soluong = ThongKeDao.getInstance().SoLuongSachCon();
            int i = 0;
            for (KhoSach ks : sachByCate) {
                i++;
                Sach s = Sach_DAO.getInstance().selectById(ks.getMaSach());
                sachtb.addRow(new Object[] { i, s.getMaSach(), s.getTenSach(), ks.getSoLuongCon() });
            }
        }else if(index==1){
            sachByCate = ThongKeDao.getInstance().SachHong();
            soluong = ThongKeDao.getInstance().SoLuongSachHong();
            int i = 0;
            for (KhoSach ks : sachByCate) {
                i++;
//            KhoSach khoSach = KhoSach_DAO.getInstance().selectById(s.getMaSach());
                Sach s = Sach_DAO.getInstance().selectById(ks.getMaSach());
                sachtb.addRow(new Object[] { i, s.getMaSach(), s.getTenSach(), ks.getSoLuongSachHong() });
            }
        }else{
            sachByCate = ThongKeDao.getInstance().ToanBoSach();
            soluong = ThongKeDao.getInstance().SoLuongTong();
            int i = 0;
            for (KhoSach ks : sachByCate) {
                i++;
//            KhoSach khoSach = KhoSach_DAO.getInstance().selectById(s.getMaSach());
                Sach s = Sach_DAO.getInstance().selectById(ks.getMaSach());
                sachtb.addRow(new Object[] { i, s.getMaSach(), s.getTenSach(), ks.getTongSoLuong() });
            }

        }
        // List<sach_th> sachByCate = getSach.getSachByCategory(DM);
        fieldSoluongthongkesach.setText(String.valueOf(soluong));
    }// GEN-LAST:event_cbb_chucNangThongKe7ItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        new DangNhap().setVisible(true);
        this.setVisible(false);
    }// GEN-LAST:event_jButton1ActionPerformed

    private void sdt2KeyPressed(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_sdt2KeyPressed
        // TODO add your handling code here:
    }// GEN-LAST:event_sdt2KeyPressed

    private void sdt2KeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_sdt2KeyReleased
        // TODO add your handling code here:
    }// GEN-LAST:event_sdt2KeyReleased

    private void sdt2KeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_sdt2KeyTyped
        // TODO add your handling code here:
    }// GEN-LAST:event_sdt2KeyTyped

    private void ngaysinh2FocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_ngaysinh2FocusLost
        // TODO add your handling code here:
    }// GEN-LAST:event_ngaysinh2FocusLost

    private void ngaysinh2KeyPressed(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_ngaysinh2KeyPressed
        // TODO add your handling code here:
    }// GEN-LAST:event_ngaysinh2KeyPressed

    private void Hc_maTheLoai3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_Hc_maTheLoai3ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_Hc_maTheLoai3ActionPerformed

    private void emailDocgia3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_emailDocgia3ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_emailDocgia3ActionPerformed

    private void timKiemDGActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_timKiemDGActionPerformed
    }// GEN-LAST:event_timKiemDGActionPerformed

    // click bảng phân loại thẻ
    private void tableDocgia3MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tableDocgia3MouseClicked
        int lineSelect = tableDocgia3.getSelectedRow();
        maLoaiField.setText(tableDocgia3.getValueAt(lineSelect, 0).toString().trim());
        tenLoaiField.setText(tableDocgia3.getValueAt(lineSelect, 1).toString());
        soLuongField.setText(tableDocgia3.getValueAt(lineSelect, 2).toString());
        thoiGianField.setText(tableDocgia3.getValueAt(lineSelect, 3).toString());
        thoiGianField1.setText(tableDocgia3.getValueAt(lineSelect, 4).toString());
        maLoaiField.setEditable(false);
    }

    // load dữ liệu lên bảng phân loại thẻ
    private void loadTableLoaiThe(JTable tb, DanhSachLoaiThe dslt) {
        String[] nameColumnLoaiThe = { "Mã loại thẻ", "Tên loại thẻ",
                "Số sách được mượn", "Thời gian mượn", "Giá tiền mở thẻ" };
        DefaultTableModel fault = new DefaultTableModel();
        for (String col : nameColumnLoaiThe) {
            fault.addColumn(col);
        }
        fault.setRowCount(0);

        for (PhanLoaiThe the : dslt.getDsLoaiThe()) {
            Vector t = new Vector<>();
            t.add(the.getMaLoaiThe());
            t.add(the.getTenLoaiThe());
            t.add(the.getSoLuongSachMuon());
            t.add(the.getThoiGianMuonToiDa());
            t.add(the.getGiaTienGiaHan());
            fault.addRow(t);
        }
        tb.setModel(fault);
    }

    // làm mới loại thẻ
    private void resetLoaiThe() {
        maLoaiField.setText("");
        tenLoaiField.setText("");
        soLuongField.setText("");
        thoiGianField.setText("");
        thoiGianField1.setText("");
        maLoaiField.setEditable(true);
    }

    private void mokhoa4ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_mokhoa4ActionPerformed
        resetLoaiThe();
    }

    // thêm loại thẻ
    private void themmoidg3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_themmoidg3ActionPerformed
        if (maLoaiField.getText().equals("") || soLuongField.getText().equals("")
                || thoiGianField.getText().equals("")) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Vui Lòng Nhập Đủ Thông Tin!");
        } else if (!new QLDG_PhanLoai_DAO().checkMaThe(maLoaiField.getText().toString())) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Mã Thẻ Đã Tồn Tại!!");
        } else {
            int x = JOptionPane.showConfirmDialog(JOptionPane.getRootFrame(), "Bạn Có Chắn Chắc Thêm Loại Thẻ!");
            if (x == JOptionPane.NO_OPTION)
                return;
            PhanLoaiThe loaiThe = new PhanLoaiThe();
            loaiThe.setMaLoaiThe(maLoaiField.getText());
            loaiThe.setTenLoaiThe(tenLoaiField.getText());
            loaiThe.setSoLuongSachMuon(Integer.parseInt(soLuongField.getText()));
            loaiThe.setThoiGianMuonToiDa(Integer.parseInt(thoiGianField.getText()));
            loaiThe.setGiaTienGiaHan(thoiGianField1.getText());

            if (new QLDG_PhanLoai_DAO().Add_LoaiThe(loaiThe)) {
                loadTableLoaiThe(tableDocgia3, new DanhSachLoaiThe(new QLDG_PhanLoai_DAO().dsLoaiThe()));
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Thêm Thành Công!");
                resetLoaiThe();
            } else {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Quá Trình Thêm Xảy Ra Lỗi!");
            }
        }
    }

    // sửa loại thẻ
    private void updatedg3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_updatedg3ActionPerformed
        if (maLoaiField.getText().equals("") || soLuongField.getText().equals("")
                || thoiGianField.getText().equals("")) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Vui Lòng Nhập Đủ Thông Tin!");
        } else if (new QLDG_PhanLoai_DAO().checkMaThe(maLoaiField.getText().toString())) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Mã Thẻ Đã Tồn Tại!!");
        } else {
            int x = JOptionPane.showConfirmDialog(JOptionPane.getRootFrame(), "Bạn Có Chắn Chắc Sửa Loại Thẻ!");
            if (x == JOptionPane.NO_OPTION)
                return;
            PhanLoaiThe loaiThe = new PhanLoaiThe();
            loaiThe.setMaLoaiThe(maLoaiField.getText());
            loaiThe.setTenLoaiThe(tenLoaiField.getText());
            loaiThe.setSoLuongSachMuon(Integer.parseInt(soLuongField.getText()));
            loaiThe.setThoiGianMuonToiDa(Integer.parseInt(thoiGianField.getText()));
            loaiThe.setGiaTienGiaHan(thoiGianField1.getText());

            if (new QLDG_PhanLoai_DAO().update_LoaiThe(loaiThe)) {
                loadTableLoaiThe(tableDocgia3, new DanhSachLoaiThe(new QLDG_PhanLoai_DAO().dsLoaiThe()));
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Sửa Thành Công!");
                resetLoaiThe();
            } else {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Quá Trình Sửa Xảy Ra Lỗi!");
            }
        }
    }
    // ======================== Loại Thẻ end ======================

    /*
     * -----------------------------------------------------------------------------
     */

    // ======================== Độc giả start ======================

    // bảng độc giả
    public void loadTableDocGia(JTable tb, DanhSachTaiKhoan dg) {
        String[] columnNames = { "Mã độc giả", "Tên độc giả", "Loại Tài Khoản", "Mật khẩu", "Số điện thoại",
                "Ngày sinh", "Email", "Giới tính", "Địa Chỉ", "Ngày Mở Thẻ", "Hạn Sử Dụng", "SoLuongMuon",
                "Trạng Thái" };
        DefaultTableModel fault = new DefaultTableModel();
        for (String col : columnNames) {
            fault.addColumn(col);
        }

        fault.setRowCount(0);

        for (TaiKhoan tk : dg.getDsTaiKhoan()) {
            Vector t = new Vector<>();
            t.add(tk.getTenTaiKhoan());
            t.add(tk.getTenNguoiDung());
            t.add(tk.getLoaiTK());
            t.add(tk.getMatKhau());
            t.add(tk.getSdt());
            t.add(tk.getNgaySinh());
            t.add(tk.getEmail());
            t.add(tk.getGioiTinh());
            t.add(tk.getDiaChi());
            t.add(tk.getNgayMoThe());
            t.add(tk.getHanSuDung());
            t.add(tk.getSoLuongMuon());
            t.add(tk.getTrangThai());
            fault.addRow(t);
        }
        tb.setModel(fault);
    }

    // sự kiện click bảng độc giả
    private void tableDocgia2MouseClicked(java.awt.event.MouseEvent evt) {
        // tableDocgia2.setRowSelectionAllowed(true);
        int lineSelect = tableDocgia2.getSelectedRow();

        maDocGiaField.setText(tableDocgia2.getValueAt(lineSelect, 0).toString().trim());
        tenDocGiaField.setText(tableDocgia2.getValueAt(lineSelect, 1).toString());
        Hc_maTheLoai3
                .setSelectedItem(new QuanLiDocGia_DAO().tenLoai(tableDocgia2.getValueAt(lineSelect, 2).toString()));

        matKhauField.setText((String) tableDocgia2.getValueAt(lineSelect, 3));
        sdt2.setText(tableDocgia2.getValueAt(lineSelect, 4).toString().trim());
        ngaysinh2.setText((String) tableDocgia2.getValueAt(lineSelect, 5));

        emailDocgia4.setText(tableDocgia2.getValueAt(lineSelect, 6).toString().trim());
        String gioiTinh = tableDocgia2.getValueAt(lineSelect, 7).toString();
        if (gioiTinh.equals("Nam")) {
            gioitinhnam16.setSelected(true);
        } else
            gioitinhnu16.setSelected(true);

        // địa chỉ col 8

        ngayMotheField.setText(tableDocgia2.getValueAt(lineSelect, 9).toString().trim());
        hanDungField1.setText(tableDocgia2.getValueAt(lineSelect, 10).toString().trim());
        // soLuongmuonField.setText(new
        // QuanLiDocGia_DAO().hanDungThe(maDocGiaField.getText()));
        // số lượng mượn column 11
        if (Integer.parseInt(tableDocgia2.getValueAt(lineSelect, 12).toString()) == 0) {
            khoatk8.setEnabled(false);
        } else {
            khoatk8.setEnabled(true);
        }
    }

    // làm mới độc giả
    private void resetDG() {
        maDocGiaField.setText("");
        matKhauField.setText("");
        tenDocGiaField.setText("");
        buttonGroup1.clearSelection();
        sdt2.setText("");
        ngaysinh2.setText("");
        Hc_maTheLoai3.setSelectedItem("");
        soLuongmuonField.setText("");
        emailDocgia4.setText("");
        ngayMotheField.setText("");
        hanDungField1.setText("");

        khoatk8.setEnabled(true);
    }

    // làm mới độc gải
    private void mokhoa3ActionPerformed(java.awt.event.ActionEvent evt) {
        resetDG();
    }

    // thêm độc giả
    private void themmoidg2ActionPerformed(java.awt.event.ActionEvent evt) {
        if (maDocGiaField.getText().equals("") || matKhauField.getText().equals("")
                || Hc_maTheLoai3.getSelectedItem().equals("") || sdt2.getText().equals("")) {
            JOptionPane.showMessageDialog((JOptionPane.getRootFrame()), "Vui Lòng Nhập Đủ Thông Tin!");
        } else if (!new QuanLiDocGia_DAO().checkMaTaiKhoan(maDocGiaField.getText())) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Mã Độc Giả Đã Tồn Tại!");
        } else if (!new check().isDateValid(ngaysinh2.getText())) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Vui Lòng Nhập Đúng Định Dàng Ngày yyyy-mm-dd!");
        } else if (!new check().isValidGmail(emailDocgia4.getText())) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),
                    "Vui Lòng Nhập Đúng Định Dạng Email!");
        } else if (!new check().isValidPhoneNumber(sdt2.getText())) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),
                    "Vui Lòng Nhập Đúng Định Dạng Số Điện Thoại!");
        } else {
            int x = JOptionPane.showConfirmDialog(JOptionPane.getRootFrame(), "Bạn Có Chắc Chắn Thêm Độc Giả!");
            if (x == JOptionPane.NO_OPTION)
                return;
            TaiKhoan dg = new TaiKhoan();
            dg.setTenTaiKhoan(maDocGiaField.getText());
            dg.setMatKhau(matKhauField.getText());
            dg.setTenNguoiDung(tenDocGiaField.getText());
            dg.setLoaiTK(new QuanLiDocGia_DAO().maLoaiThe(Hc_maTheLoai3.getSelectedItem().toString()));
            dg.setEmail(emailDocgia4.getText());
            if (gioitinhnam16.isSelected()) {
                dg.setGioiTinh("Nam");
            } else if (gioitinhnu16.isSelected()) {
                dg.setGioiTinh("Nữ");
            } else {
                dg.setGioiTinh("");
            }
            dg.setNgaySinh(ngaysinh2.getText());
            dg.setSdt(sdt2.getText());
            dg.setTrangThai(1);
            dg.setSoLuongMuon(0);
            dg.setNgayMoThe(ngayMotheField.getText());
            dg.setHanSuDung(hanDungField1.getText());
            // địa chỉ

            if (new QuanLiDocGia_DAO().Add_DG(dg)) {
                loadTableDocGia(tableDocgia2, new DanhSachTaiKhoan(new QuanLiDocGia_DAO().dsDOCGIA()));
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Thêm Thành Công!");
                resetDG();
            } else {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Thêm Lỗi!");
            }
        }
    }

    // Cập nhật độc giả
    private void updatedg2ActionPerformed(java.awt.event.ActionEvent evt) {
        if (maDocGiaField.getText().equals("") || matKhauField.getText().equals("")
                || emailDocgia4.getText().equals("") || sdt2.getText().equals("")) {
            JOptionPane.showMessageDialog((JOptionPane.getRootFrame()), "Vui Lòng Nhập Đủ Thông Tin!");
        } else if (new QuanLiDocGia_DAO().checkMaTaiKhoan(maDocGiaField.getText())) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Mã Độc Giả Không Tồn Tại!");
        } else if (!new check().isDateValid(ngaysinh2.getText())) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Vui Lòng Nhập Đúng Định Dàng Ngày yyy-mm-dd!");
        } else if (!new check().isValidGmail(emailDocgia4.getText())) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),
                    "Vui Lòng Nhập Đúng Định Dạng Email!");
        } else if (!new check().isValidPhoneNumber(sdt2.getText())) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),
                    "Vui Lòng Nhập Đúng Định Dạng Số Điện Thoại!");
        } else {
            int x = JOptionPane.showConfirmDialog(JOptionPane.getRootFrame(),
                    "Bạn Có Chắn Chắc Cập Nhật Thông Tin Độc Giả!");
            if (x == JOptionPane.NO_OPTION)
                return;
            TaiKhoan dg = new TaiKhoan();
            dg.setTenTaiKhoan(maDocGiaField.getText());
            dg.setMatKhau(matKhauField.getText());
            dg.setTenNguoiDung(tenDocGiaField.getText());
            // String[] dsTenThe = new QuanLiDocGia_DAO().tenLoaiThe();
            // // dg.setLoaiTK(dsTenThe[Hc_maTheLoai3.getSelectedIndex()]);
            // System.out.print(dsTenThe[Hc_maTheLoai3.getSelectedIndex()]);
            dg.setLoaiTK(new QuanLiDocGia_DAO().maLoaiThe(Hc_maTheLoai3.getSelectedItem().toString()));

            // dg.setLoaiTK("3000");
            dg.setEmail(emailDocgia4.getText());
            if (gioitinhnam16.isSelected()) {
                dg.setGioiTinh("Nam");
            } else if (gioitinhnu16.isSelected()) {
                dg.setGioiTinh("Nữ");
            } else {
                dg.setGioiTinh("");
            }
            dg.setNgaySinh(ngaysinh2.getText());
            dg.setSdt(sdt2.getText());
            dg.setTrangThai(1);
            dg.setSoLuongMuon(0);
            // dg.setDiaChi();
            dg.setNgayMoThe(ngayMotheField.getText());
            dg.setHanSuDung(hanDungField1.getText());

            if (new QuanLiDocGia_DAO().update_DG(dg)) {
                loadTableDocGia(tableDocgia2, new DanhSachTaiKhoan(new QuanLiDocGia_DAO().dsDOCGIA()));
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Cập Nhật Thành Công!");
                resetDG();
            } else {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Cập Nhật Lỗi");
            }
        }
    }

    // khóa độc giả
    private void khoatk8ActionPerformed(java.awt.event.ActionEvent evt) {
        if (maDocGiaField.getText().equals("")) {
            JOptionPane.showMessageDialog((rootPane), "Vui Lòng Nhập Mã Độc Giả!");
        } else if (new QuanLiDocGia_DAO().checkMaTaiKhoan(maDocGiaField.getText())) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Mã Độc Giả Không Tồn Tại!");
        } else {
            int x = JOptionPane.showConfirmDialog(JOptionPane.getRootFrame(), "Bạn Có Chắn Chắn Khóa Độc Giả!");
            if (x == JOptionPane.NO_OPTION)
                return;
            if (new QuanLiDocGia_DAO().delete_DG(maDocGiaField.getText())) {
                loadTableDocGia(tableDocgia2, new DanhSachTaiKhoan(new QuanLiDocGia_DAO().dsDOCGIA()));
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Khóa Thành Công!");
                resetDG();
            } else {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Khóa Lỗi!");
            }
        }
    }

    // gia hạn loại thẻ độc giả
    private void updatedg4ActionPerformed(java.awt.event.ActionEvent evt) {
        if (maDocGiaField.getText().equals("")) {
            JOptionPane.showMessageDialog((rootPane), "Vui Lòng Nhập Mã Độc Giả!");
        } else if (new QuanLiDocGia_DAO().checkMaTaiKhoan(maDocGiaField.getText())) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Mã Độc Giả Không Tồn Tại!");
        } else {
            int x = JOptionPane.showConfirmDialog(JOptionPane.getRootFrame(), "Bạn Có Chắn Chắn Gia Hạn Thẻ Độc Giả!");
            if (x == JOptionPane.NO_OPTION)
                return;
            String maThe = new QuanLiDocGia_DAO().maLoaiThe(Hc_maTheLoai3.getSelectedItem().toString());
            x = JOptionPane.showConfirmDialog(JOptionPane.getRootFrame(),
                    "Bạn Chắn Chắn Đã Thu Phí Gia Hạn Thẻ: " + new QuanLiDocGia_DAO().phiGiaHan(maThe));
            if (x == JOptionPane.NO_OPTION)
                return;
            if (new QuanLiDocGia_DAO().giaHan_DG(maDocGiaField.getText())) {
                loadTableDocGia(tableDocgia2, new DanhSachTaiKhoan(new QuanLiDocGia_DAO().dsDOCGIA()));
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Gia Hạn Thành Công!");
                resetDG();
            } else {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Gia Hạn Lỗi!");
            }
        }
    }

    private void maLoaiFieldActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_maLoaiFieldActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_maLoaiFieldActionPerformed

    private void tenLoaiFieldActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_tenLoaiFieldActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_tenLoaiFieldActionPerformed

    private void tenLoaiField1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_tenLoaiField1ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_tenLoaiField1ActionPerformed

    private void tenLoaiField2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_tenLoaiField2ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_tenLoaiField2ActionPerformed

    private void tenLoaiField3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_tenLoaiField3ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_tenLoaiField3ActionPerformed

    private void H_soLuongCon2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_H_soLuongCon2ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_H_soLuongCon2ActionPerformed

    private void tblH_Sach2MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tblH_Sach2MouseClicked
        // TODO add your handling code here:
        int selectedRow = tblH_Sach2.getSelectedRow();
        H_tenSach2.setText((String) tblH_Sach2.getValueAt(selectedRow, 0));
        H_tenSach3.setText((String) tblH_Sach2.getValueAt(selectedRow, 1));
        Hc_maDM2.setSelectedItem((String) tblH_Sach2.getValueAt(selectedRow, 2));
        Hc_maTheLoai2.setSelectedItem((String) tblH_Sach2.getValueAt(selectedRow, 3));
        H_tacGia4.setText((String) tblH_Sach2.getValueAt(selectedRow, 4));
        H_tacGia5.setText((String) tblH_Sach2.getValueAt(selectedRow, 5));
        H_nhaXB2.setText((String) tblH_Sach2.getValueAt(selectedRow, 6));
        H_namXB2.setText(String.valueOf(tblH_Sach2.getValueAt(selectedRow, 7)));
        H_soLuongCon2.setText(String.valueOf(tblH_Sach2.getValueAt(selectedRow, 8)));
        H_tomTat2.setText((String) tblH_Sach2.getValueAt(selectedRow, 10));

    }// GEN-LAST:event_tblH_Sach2MouseClicked

    private void btnH_themSach2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnH_themSach2ActionPerformed
        // TODO add your handling code here:
        if (H_tenSach3.getText().trim().equals("")
                || H_tenTheLoai2.getText().trim().equals("") || H_tenDM2.getText().trim().equals("")
                || H_soLuongCon2.getText().trim().equals("") || H_tacGia4.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Bạn chưa nhập đủ thông tin!");
        } else {
            int x = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn thêm không?");
            if (x == JOptionPane.NO_OPTION) {
                return;
            } else {
                Sach sach = new Sach();
                sach.setMaSach(H_tenSach2.getText());
                KhoSach khoSach = KhoSach_DAO.getInstance().selectById(H_tenSach2.getText());
                if (khoSach.getMaSach().equals("")) {
                    khoSach.setMaSach(H_tenSach2.getText());
                    khoSach.setSoLuongSachHong(0);
                    khoSach.setSoLuongCon(0);
                    khoSach.setTongSoLuong(0);
                    KhoSach_DAO.getInstance().add(khoSach);
                }
                sach.setTenSach(H_tenSach3.getText());
                sach.setMaDMSach(Hc_maDM2.getItemAt(Hc_maDM2.getSelectedIndex()));
                sach.setMaTheLoai(Hc_maTheLoai2.getItemAt(Hc_maTheLoai2.getSelectedIndex()));
                TacGia tacGia = TacGia_DAO.getInstance().selectById(H_tacGia4.getText());
                if (tacGia.getMaTacGia().equals("")) {
                    if (H_tacGia5.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Mã tác giả mới, vui lòng nhập tên tác giả!");
                    } else {
                        // tacGia.setMaTacGia(H_tacGia4.getText());
                        tacGia.setTenTacGia(H_tacGia5.getText());
                        TacGia_DAO.getInstance().add(tacGia);
                    }
                }
                sach.setTenTacGia(tacGia.getTenTacGia());
                sach.setMaTacGia(H_tacGia4.getText());
                sach.setNXB(H_nhaXB2.getText());
                sach.setNamXuatBan(Integer.parseInt(H_namXB2.getText()));
                // sach.setSoLuongCon(Integer.parseInt(H_soLuongCon.getText()));
                sach.setGiaTienSach(Double.parseDouble(H_soLuongCon2.getText()));
                sach.setTomTatND(H_tomTat2.getText());
                // sach.setAnh(H_linkAnh.getText());
                if (Sach_DAO.getInstance().add(sach) > 0) {
                    JOptionPane.showMessageDialog(null, "Đã thêm dữ liệu của sách.");
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Thêm sách thất bại! \n Hãy kiểm tra lại mã sách.");
                }
            }
            refresh();
            loadThongTinSach();
        }
    }// GEN-LAST:event_btnH_themSach2ActionPerformed

    private void btnH_suaSach2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnH_suaSach2ActionPerformed
        // TODO add your handling code here:
        if (H_tenSach2.getText().trim().equals("") || H_tenTheLoai2.getText().trim().equals("")
                || H_tenDM2.getText().trim().equals("") || H_namXB2.getText().trim().equals("")
                || H_nhaXB2.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn thông tin sách muốn sửa!");
        } else {
            int x = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn thay đổi không?");
            if (x == JOptionPane.NO_OPTION) {
                return;
            } else {
                Sach sach = new Sach();
                sach.setMaSach(H_tenSach2.getText());
                sach.setTenSach(H_tenSach3.getText());
                sach.setMaDMSach(Hc_maDM2.getItemAt(Hc_maDM2.getSelectedIndex()));
                sach.setMaTheLoai(Hc_maTheLoai2.getItemAt(Hc_maTheLoai2.getSelectedIndex()));
                sach.setTacGia(H_tacGia5.getText());
                sach.setMaTacGia(H_tacGia4.getText());
                sach.setNXB(H_nhaXB2.getText());
                sach.setNamXuatBan(Integer.parseInt(H_namXB2.getText()));
                // sach.setSoLuongCon(Integer.parseInt(H_soLuongCon.getText()));
                sach.setGiaTienSach(Double.parseDouble(H_soLuongCon2.getText()));
                sach.setTomTatND(H_tomTat2.getText());

                // s_Service.updateSach(sach);
                if (Sach_DAO.getInstance().update(sach) > 0) {
                    JOptionPane.showMessageDialog(null, "Sửa sách thành công!");
                } else {
                    JOptionPane.showMessageDialog(null, "Sửa sách thất bại!");
                }
                // defaultTableModel_S.setRowCount(0);
                // setTableData_S(s_Service.getDSSach());
            }
        }
        loadThongTinSach();
    }// GEN-LAST:event_btnH_suaSach2ActionPerformed

    private void btn_lamMoiSach2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btn_lamMoiSach2ActionPerformed
        // TODO add your handling code here:
        H_tenSach2.setText("");
        H_tenSach3.setText("");
        H_soLuongCon2.setText("");
        H_namXB2.setText("");
        H_tacGia5.setText("");
        H_tacGia4.setText("");
        H_nhaXB2.setText("");
        H_tomTat2.setText("");
    }// GEN-LAST:event_btn_lamMoiSach2ActionPerformed

    private void Hc_maTheLoai2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_Hc_maTheLoai2ActionPerformed
        // TODO add your handling code here:
        PhanLoaiSach phanLoaiSach = PhanLoaiSach_DAO.getInstance().selectById((String) Hc_maTheLoai2.getSelectedItem());
        H_tenTheLoai2.setText(phanLoaiSach.getTenTheLoai());
    }// GEN-LAST:event_Hc_maTheLoai2ActionPerformed

    private void Hc_maDM2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_Hc_maDM2ActionPerformed
        // TODO add your handling code here:
        String sql = "SELECT tenDMSach FROM DanhMucSach WHERE maDMSach = ?";
        try (Connection conn = KetNoiSQL.getConnection()) {
            String maDMString = (String) Hc_maDM2.getSelectedItem();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, maDMString);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                H_tenDM2.setText(rs.getString("tenDMSach"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }// GEN-LAST:event_Hc_maDM2ActionPerformed

    private void khoatk10ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_khoatk10ActionPerformed
        // TODO add your handling code here:
        if (H_tenSach2.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn mã sách muốn xóa!");
        } else {
            int x = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa ?");
            if (x == JOptionPane.NO_OPTION || x==JOptionPane.CANCEL_OPTION) {
                return;
            } else {
                if (Sach_DAO.getInstance().delete(H_tenSach2.getText()) > 0) {
                    JOptionPane.showMessageDialog(null, "Xóa thành công.");
                } else {
                    JOptionPane.showMessageDialog(null, "Xóa sách thất bại! \n Hãy kiểm tra lại mã sách.");
                }
            }
        }
        loadThongTinSach();
    }// GEN-LAST:event_khoatk10ActionPerformed

    private void khoatk11ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_khoatk11ActionPerformed
        // TODO add your handling code here:
        new TrangChuThuThu_TimKiem(2).setVisible(true);
        this.setVisible(false);
    }// GEN-LAST:event_khoatk11ActionPerformed

    private void tbl_DMSach4MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tbl_DMSach4MouseClicked
        // TODO add your handling code here:
        int selectedRow = tbl_DMSach4.getSelectedRow();

        txt_maDMSach4.setText((String) tbl_DMSach4.getValueAt(selectedRow, 0));
        txt_tenDMSach10.setText((String) tbl_DMSach4.getValueAt(selectedRow, 1));

        txt_maDMSach4.setEnabled(false);
        btn_SuaDMSach6.setEnabled(true);
        btn_ThemDMSach6.setEnabled(false);
        btn_LuuDMSach6.setEnabled(false);
        btn_lammoi6.setEnabled(true);
    }// GEN-LAST:event_tbl_DMSach4MouseClicked

    private void btn_ThemDMSach6ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btn_ThemDMSach6ActionPerformed
        // TODO add your handling code here:
        txt_tenDMSach10.requestFocus();
        txt_maDMSach4.setEnabled(true);
        txt_tenDMSach10.setEnabled(true);
        btn_ThemDMSach6.setEnabled(false);
        btn_LuuDMSach6.setEnabled(true);
        btn_lammoi6.setEnabled(true);
        btn_SuaDMSach6.setEnabled(false);

        txt_maDMSach4.setText("");
        txt_tenDMSach10.setText("");
    }// GEN-LAST:event_btn_ThemDMSach6ActionPerformed

    private void btn_LuuDMSach6ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btn_LuuDMSach6ActionPerformed
        // TODO add your handling code here:
        if (txt_maDMSach4.getText().trim().equals("") || txt_tenDMSach10.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Bạn chưa nhập đủ thông tin!");

        } else {
            int x = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn thêm không?");
            if (x == JOptionPane.NO_OPTION) {
                return;
            } else {
                DanhMucSach danhmuc = new DanhMucSach();
                danhmuc.setMaDM(txt_maDMSach4.getText());
                danhmuc.setTenDM(txt_tenDMSach10.getText());

                if (DanhMucSach_DAO.getInstance().add(danhmuc) > 0) {
                    JOptionPane.showMessageDialog(null, "Thêm thành công!");
                } else {
                    JOptionPane.showMessageDialog(null, "Thêm thất bại!");
                }
            }
            loadmaDanhMuc();
            btn_lammoi6ActionPerformed(evt);
        }
    }// GEN-LAST:event_btn_LuuDMSach6ActionPerformed

    private void btn_SuaDMSach6ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btn_SuaDMSach6ActionPerformed
        // TODO add your handling code here:
        if (txt_maDMSach4.getText().trim().equals("") || txt_tenDMSach10.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn thông tin danh mục muốn sửa muốn sửa!");
        } else {
            int x = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn thay đổi không?");
            if (x == JOptionPane.NO_OPTION) {
                return;
            } else {
                DanhMucSach danhmuc = new DanhMucSach();
                danhmuc.setMaDM(txt_maDMSach4.getText());
                danhmuc.setTenDM(txt_tenDMSach10.getText());
                DanhMucSach_DAO.getInstance().update(danhmuc);
            }
            loadmaDanhMuc();
            btn_lammoi6ActionPerformed(evt);
        }
    }// GEN-LAST:event_btn_SuaDMSach6ActionPerformed

    private void txt_timkiemDMSach21KeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txt_timkiemDMSach21KeyReleased
        // TODO add your handling code here:
        String query = txt_timkiemDMSach21.getText();
        TableRowSorter<DefaultTableModel> tbl = new TableRowSorter<DefaultTableModel>(defaultTableModel_DM);
        tbl_DMSach4.setRowSorter(tbl);
        tbl.setRowFilter(RowFilter.regexFilter(query));
    }// GEN-LAST:event_txt_timkiemDMSach21KeyReleased

    private void btn_lammoi6ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btn_lammoi6ActionPerformed
        // TODO add your handling code here:
        txt_maDMSach4.setEnabled(false);
        txt_maDMSach4.setText("");
        txt_tenDMSach10.setText("");
        btn_SuaDMSach6.setEnabled(true);
        btn_ThemDMSach6.setEnabled(true);
        btn_LuuDMSach6.setEnabled(false);
    }// GEN-LAST:event_btn_lammoi6ActionPerformed

    private void QLTGiaTableMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_QLTGiaTableMouseClicked
        // TODO add your handling code here:
        int selectedRow = QLTGiaTable.getSelectedRow();
        txt_tenDMSach14.setText((String) QLTGiaTable.getValueAt(selectedRow, 0));
        txt_tenDMSach13.setText((String) QLTGiaTable.getValueAt(selectedRow, 1));
        txt_tenDMSach12.setText(String.valueOf(QLTGiaTable.getValueAt(selectedRow, 2)));
        btn_SuaDMSach8.setEnabled(true);
        btn_ThemDMSach8.setEnabled(false);
        btn_LuuDMSach8.setEnabled(false);
        btn_lammoi8.setEnabled(true);
    }// GEN-LAST:event_QLTGiaTableMouseClicked

    private void tbl_DMSach5MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tbl_DMSach5MouseClicked
        // TODO add your handling code here:
        int selectedRow = tbl_DMSach5.getSelectedRow();

        txt_maDMSach5.setText((String) tbl_DMSach5.getValueAt(selectedRow, 0));
        txt_tenDMSach11.setText((String) tbl_DMSach5.getValueAt(selectedRow, 1));

        txt_maDMSach5.setEnabled(false);
        txt_tenDMSach11.setEnabled(true);
        btn_SuaDMSach7.setEnabled(true);
        btn_ThemDMSach7.setEnabled(false);
        btn_LuuDMSach7.setEnabled(false);
        btn_lammoi7.setEnabled(true);
    }

    private void btn_ThemDMSach7ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btn_ThemDMSach7ActionPerformed
        // TODO add your handling code here:
        txt_tenDMSach11.requestFocus();
        txt_maDMSach5.setEnabled(true);
        txt_tenDMSach11.setEnabled(true);
        btn_ThemDMSach7.setEnabled(false);
        btn_LuuDMSach7.setEnabled(true);
        btn_lammoi7.setEnabled(true);
        btn_SuaDMSach7.setEnabled(false);

        txt_maDMSach5.setText("");
        txt_tenDMSach11.setText("");
    }// GEN-LAST:event_btn_ThemDMSach7ActionPerformed

    private void btn_LuuDMSach7ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btn_LuuDMSach7ActionPerformed
        // TODO add your handling code here:
        if (txt_maDMSach5.getText().trim().equals("") || txt_tenDMSach11.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Bạn chưa nhập đủ thông tin!");

        } else {
            int x = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn thêm không?");
            if (x == JOptionPane.NO_OPTION) {
                return;
            } else {
                PhanLoaiSach theLoai = new PhanLoaiSach();
                theLoai.setMaTheLoai(txt_maDMSach5.getText());
                theLoai.setTenTheLoai(txt_tenDMSach11.getText());
                if (PhanLoaiSach_DAO.getInstance().add(theLoai) > 0) {
                    JOptionPane.showMessageDialog(null, "Thêm thành công!");
                } else {
                    JOptionPane.showMessageDialog(null, "Thêm thất bại!");
                }
            }
            loadmaTheLoai();
            btn_lammoi7ActionPerformed(evt);
        }
    }// GEN-LAST:event_btn_LuuDMSach7ActionPerformed

    private void btn_SuaDMSach7ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btn_SuaDMSach7ActionPerformed
        // TODO add your handling code here:
        if (txt_maDMSach5.getText().trim().equals("") || txt_tenDMSach11.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn thông tin danh mục muốn sửa muốn sửa!");
        } else {
            int x = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn thay đổi không?");
            if (x == JOptionPane.NO_OPTION) {
                return;
            } else {
                PhanLoaiSach theLoai = new PhanLoaiSach();
                theLoai.setMaTheLoai(txt_maDMSach5.getText());
                theLoai.setTenTheLoai(txt_tenDMSach11.getText());
                if (PhanLoaiSach_DAO.getInstance().update(theLoai) > 0) {
                    JOptionPane.showMessageDialog(null, "Sửa thành công!");
                } else {
                    JOptionPane.showMessageDialog(null, "Sửa thất bại!");
                }
            }
            loadmaTheLoai();
            btn_lammoi7ActionPerformed(evt);
        }
    }// GEN-LAST:event_btn_SuaDMSach7ActionPerformed

    private void txt_timkiemDMSach22KeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txt_timkiemDMSach22KeyReleased
        // TODO add your handling code here:
        String query = txt_timkiemDMSach22.getText();
        TableRowSorter<DefaultTableModel> tbl = new TableRowSorter<DefaultTableModel>(defaultTableModel_TL);
        tbl_DMSach5.setRowSorter(tbl);
        tbl.setRowFilter(RowFilter.regexFilter(query));
    }// GEN-LAST:event_txt_timkiemDMSach22KeyReleased

    private void btn_lammoi7ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btn_lammoi7ActionPerformed
        // TODO add your handling code here:
        txt_maDMSach5.setEnabled(false);
        txt_tenDMSach11.setEnabled(false);
        txt_maDMSach5.setText("");
        txt_tenDMSach11.setText("");
        btn_SuaDMSach7.setEnabled(true);
        btn_ThemDMSach7.setEnabled(true);
        btn_LuuDMSach7.setEnabled(false);
    }// GEN-LAST:event_btn_lammoi7ActionPerformed

    private void btn_ThemDMSach8ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btn_ThemDMSach8ActionPerformed
        // TODO add your handling code here:
        txt_tenDMSach13.requestFocus();
        txt_tenDMSach14.setEnabled(true);
        txt_tenDMSach13.setEnabled(true);
        txt_tenDMSach12.setEnabled(true);
        btn_ThemDMSach8.setEnabled(false);
        btn_LuuDMSach8.setEnabled(true);
        btn_lammoi8.setEnabled(true);
        btn_SuaDMSach8.setEnabled(false);

        txt_tenDMSach12.setText("");
        txt_tenDMSach13.setText("");
        txt_tenDMSach14.setText("");
    }// GEN-LAST:event_btn_ThemDMSach8ActionPerformed

    private void btn_LuuDMSach8ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btn_LuuDMSach8ActionPerformed
        // TODO add your handling code here:
        if (txt_tenDMSach14.getText().trim().equals("") || txt_tenDMSach13.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Bạn chưa nhập đủ thông tin!");

        } else {
            int x = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn thêm không?");
            if (x == JOptionPane.NO_OPTION) {
                return;
            } else {
                TacGia tacGia = new TacGia();
                tacGia.setMaTacGia(txt_tenDMSach14.getText());
                tacGia.setTenTacGia(txt_tenDMSach13.getText());
                if (txt_tenDMSach12.getText().equals("")) {
                    tacGia.setSoSach(0);
                } else {
                    tacGia.setSoSach(Integer.parseInt(txt_tenDMSach12.getText()));
                }
                if (TacGia_DAO.getInstance().add(tacGia) > 0) {
                    JOptionPane.showMessageDialog(null, "Thêm thành công!");
                } else {
                    JOptionPane.showMessageDialog(null, "Thêm thất bại!");
                }
            }
            loadmaTacGia();
            btn_lammoi8ActionPerformed(evt);
        }
    }// GEN-LAST:event_btn_LuuDMSach8ActionPerformed

    private void btn_SuaDMSach8ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btn_SuaDMSach8ActionPerformed
        // TODO add your handling code here:
        if (txt_tenDMSach14.getText().trim().equals("") || txt_tenDMSach13.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn thông tin danh mục muốn sửa muốn sửa!");
        } else {
            int x = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn thay đổi không?");
            if (x == JOptionPane.NO_OPTION) {
                return;
            } else {
                TacGia tacGia = new TacGia();
                tacGia.setMaTacGia(txt_tenDMSach14.getText());
                tacGia.setTenTacGia(txt_tenDMSach13.getText());
                if (txt_tenDMSach12.getText().equals("")) {
                    tacGia.setSoSach(0);
                } else {
                    tacGia.setSoSach(Integer.parseInt(txt_tenDMSach12.getText()));
                }
                if (TacGia_DAO.getInstance().update(tacGia) > 0) {
                    JOptionPane.showMessageDialog(null, "Sửa thành công!");
                } else {
                    JOptionPane.showMessageDialog(null, "Sửa thất bại!");
                }
            }
            loadmaTacGia();
            btn_lammoi8ActionPerformed(evt);
        }
    }// GEN-LAST:event_btn_SuaDMSach8ActionPerformed

    private void txt_timkiemDMSach23KeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txt_timkiemDMSach23KeyReleased
        // TODO add your handling code here:
         String query = txt_timkiemDMSach23.getText();
         TableRowSorter<DefaultTableModel> tbl = new
         TableRowSorter<DefaultTableModel>(defaultTableModel_TG);
         QLTGiaTable.setRowSorter(tbl);
         tbl.setRowFilter(RowFilter.regexFilter(query));
    }// GEN-LAST:event_txt_timkiemDMSach23KeyReleased

    private void btn_lammoi8ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btn_lammoi8ActionPerformed
        // TODO add your handling code here:
        txt_tenDMSach14.setEnabled(false);
        txt_tenDMSach14.setText("");
        txt_tenDMSach13.setText("");
        txt_tenDMSach12.setText("");
        btn_SuaDMSach8.setEnabled(true);
        btn_ThemDMSach8.setEnabled(true);
        btn_LuuDMSach8.setEnabled(false);
    }// GEN-LAST:event_btn_lammoi8ActionPerformed

    private void btnK_themPM31btnK_themPM1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnK_themPM31btnK_themPM1ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_btnK_themPM31btnK_themPM1ActionPerformed

    private void txt_timkiemDMSach24txt_timkiemDMSach3KeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txt_timkiemDMSach24txt_timkiemDMSach3KeyReleased
        // TODO add your handling code here:
    }// GEN-LAST:event_txt_timkiemDMSach24txt_timkiemDMSach3KeyReleased

    private void btnK_themPM32btnK_themPM2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnK_themPM32btnK_themPM2ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_btnK_themPM32btnK_themPM2ActionPerformed

    private void btnK_themPM33btnK_themPM1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnK_themPM33btnK_themPM1ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_btnK_themPM33btnK_themPM1ActionPerformed

    private void txt_timkiemDMSach25txt_timkiemDMSach3KeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txt_timkiemDMSach25txt_timkiemDMSach3KeyReleased
        // TODO add your handling code here:
        String query = txt_timkiemDMSach25.getText();
        TableRowSorter<DefaultTableModel> tbl = new TableRowSorter<DefaultTableModel>(defaultTableModel_CTPN);
        jTable1.setRowSorter(tbl);
        tbl.setRowFilter(RowFilter.regexFilter(query));
    }// GEN-LAST:event_txt_timkiemDMSach25txt_timkiemDMSach3KeyReleased

    private void btnQLPNbtnK_themPM2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnQLPNbtnK_themPM2ActionPerformed
        // TODO add your handling code here:
        new TrangChuThuThu_QLPNhap().setVisible(true);
        this.setVisible(false);
    }// GEN-LAST:event_btnQLPNbtnK_themPM2ActionPerformed

    private void btnK_themPM35btnK_themPM1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnK_themPM35btnK_themPM1ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_btnK_themPM35btnK_themPM1ActionPerformed

    private void txt_timkiemDMSach26txt_timkiemDMSach3KeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txt_timkiemDMSach26txt_timkiemDMSach3KeyReleased
        // TODO add your handling code here:
    }// GEN-LAST:event_txt_timkiemDMSach26txt_timkiemDMSach3KeyReleased

    private void btnK_themPM36btnK_themPM2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnK_themPM36btnK_themPM2ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_btnK_themPM36btnK_themPM2ActionPerformed

    private void cbb_chucNangThongKe6ItemStateChanged(java.awt.event.ItemEvent evt) {// GEN-FIRST:event_cbb_chucNangThongKe6ItemStateChanged
        // TODO add your handling code here:
    }// GEN-LAST:event_cbb_chucNangThongKe6ItemStateChanged

    // private void cbb_chucNangThongKe7ItemStateChanged(java.awt.event.ItemEvent
    // evt) {// GEN-FIRST:event_cbb_chucNangThongKe7ItemStateChanged
    // // TODO add your handling code here:
    // }// GEN-LAST:event_cbb_chucNangThongKe7ItemStateChanged

    private void textboxsearch2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_textboxsearch2ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_textboxsearch2ActionPerformed

    private void textboxsearch2KeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_textboxsearch2KeyReleased
        // TODO add your handling code here:
    }// GEN-LAST:event_textboxsearch2KeyReleased

    private void textboxsearch2KeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_textboxsearch2KeyTyped
        // TODO add your handling code here:
        String textSearch = textboxsearch2.getText();
        List<Sach> searchSach = Sach_DAO.getInstance().selectAll();
        List<Sach> resultSearch = new ArrayList<>();
        DefaultTableModel sachtb = (DefaultTableModel) tableSearchSach2.getModel();
        sachtb.setRowCount(0);
        int i = 0;
        for (Sach s : searchSach) {
            if (s.getTenSach().toLowerCase().contains(textSearch.toLowerCase()))
                resultSearch.add(s);
        }
        for (Sach s : resultSearch) {
            i++;
            KhoSach khoSach = KhoSach_DAO.getInstance().selectById(s.getMaSach());
            sachtb.addRow(new Object[] { i, s.getTenSach(), s.getTenTacGia(), s.getNXB(),
                    khoSach.getSoLuongCon() });
        }
    }// GEN-LAST:event_textboxsearch2KeyTyped

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:

    }// GEN-LAST:event_jButton13ActionPerformed

    private void jComboBox7ItemStateChanged(java.awt.event.ItemEvent evt) {// GEN-FIRST:event_jComboBox7ItemStateChanged
        // TODO add your handling code here:
        String DM = "DM00";
        int index = jComboBox7.getSelectedIndex();
        if (index == 0)
            return;
        DM += Integer.toString(index);
        // List<sach_th> sachByCate = getSach.getSachByCategory(DM);
        List<Sach> sachByCate = Sach_DAO.getInstance().selectByCategory(DM);
        DefaultTableModel sachtb = (DefaultTableModel) tableSearchSach2.getModel();
        sachtb.setRowCount(0);
        int i = 0;
        for (Sach s : sachByCate) {
            i++;
            KhoSach khoSach = KhoSach_DAO.getInstance().selectById(s.getMaSach());
            sachtb.addRow(new Object[] { i, s.getTenSach(), s.getTenTacGia(), s.getNXB(),
                    khoSach.getSoLuongCon() });
        }
    }// GEN-LAST:event_jComboBox7ItemStateChanged

    private void jComboBox8ItemStateChanged(java.awt.event.ItemEvent evt) {// GEN-FIRST:event_jComboBox8ItemStateChanged
        // TODO add your handling code here:
        String TL = "TL00";
        int index = jComboBox8.getSelectedIndex();
        if (index == 0)
            return;
        TL += Integer.toString(index);
        List<Sach> sachBytheloai = Sach_DAO.getInstance().selectByGenre(TL);
        DefaultTableModel sachtb = (DefaultTableModel) tableSearchSach2.getModel();
        sachtb.setRowCount(0);
        int i = 0;
        for (Sach s : sachBytheloai) {
            i++;
            KhoSach khoSach = KhoSach_DAO.getInstance().selectById(s.getMaSach());
            sachtb.addRow(new Object[] { i, s.getTenSach(), s.getTenTacGia(), s.getNXB(),
                    khoSach.getSoLuongCon() });
        }
    }// GEN-LAST:event_jComboBox8ItemStateChanged

    private void btnK_themPM37btnK_themPM1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnK_themPM37btnK_themPM1ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_btnK_themPM37btnK_themPM1ActionPerformed

    private void txt_timkiemDMSach27txt_timkiemDMSach3KeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txt_timkiemDMSach27txt_timkiemDMSach3KeyReleased
        // TODO add your handling code here:
    }// GEN-LAST:event_txt_timkiemDMSach27txt_timkiemDMSach3KeyReleased

    private void btnK_themPM38btnK_themPM2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnK_themPM38btnK_themPM2ActionPerformed
        // TODO add your handling code here:

    }// GEN-LAST:event_btnK_themPM38btnK_themPM2ActionPerformed

    private void btnK_themPM39btnK_themPM1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnK_themPM39btnK_themPM1ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_btnK_themPM39btnK_themPM1ActionPerformed

    private void txt_timkiemDMSach28txt_timkiemDMSach3KeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txt_timkiemDMSach28txt_timkiemDMSach3KeyReleased
        // TODO add your handling code here:
        String query = txt_timkiemDMSach28.getText();
        TableRowSorter<DefaultTableModel> tbl = new TableRowSorter<DefaultTableModel>(defaultTableModel_CTPM);
        jTable4.setRowSorter(tbl);
        tbl.setRowFilter(RowFilter.regexFilter(query));
    }// GEN-LAST:event_txt_timkiemDMSach28txt_timkiemDMSach3KeyReleased

    private void btnK_themPM40btnK_themPM2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnK_themPM40btnK_themPM2ActionPerformed
        // TODO add your handling code here:
        new TrangChuThuThu_QLPM_1(2).setVisible(true);
        this.setVisible(false);
    }// GEN-LAST:event_btnK_themPM40btnK_themPM2ActionPerformed

    private void jTP_main2MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jTP_main2MouseClicked
        // TODO add your handling code here:
    }// GEN-LAST:event_jTP_main2MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TrangChuThuThu.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TrangChuThuThu.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TrangChuThuThu.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TrangChuThuThu.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TrangChuThuThu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField H_namXB2;
    private javax.swing.JTextField H_nhaXB2;
    private javax.swing.JTextField H_soLuongCon2;
    private javax.swing.JTextField H_tacGia4;
    private javax.swing.JTextField H_tacGia5;
    private javax.swing.JTextField H_tenDM2;
    private javax.swing.JTextField H_tenSach2;
    private javax.swing.JTextField H_tenSach3;
    private javax.swing.JTextField H_tenTheLoai2;
    private javax.swing.JTextField H_tomTat2;
    private javax.swing.JComboBox<String> Hc_maDM2;
    private javax.swing.JComboBox<String> Hc_maTheLoai2;
    private javax.swing.JComboBox<String> Hc_maTheLoai3;
    private javax.swing.JLabel K_tieuDe15;
    private javax.swing.JLabel K_tieuDe16;
    private javax.swing.JLabel K_tieuDe17;
    private javax.swing.JLabel K_tieuDe19;
    private javax.swing.JPanel Panel_DanhSachPM14;
    private javax.swing.JPanel Panel_DanhSachPM15;
    private javax.swing.JPanel Panel_DanhSachPM16;
    private javax.swing.JPanel Panel_DanhSachPM17;
    private javax.swing.JPanel Panel_DanhSachPM18;
    private javax.swing.JTable QLTGiaTable;
    private javax.swing.JTable TABLEPhieuXuat;
    private javax.swing.JButton btnChiTietView;
    private javax.swing.JButton btnH_suaSach2;
    private javax.swing.JButton btnH_themSach2;
    private javax.swing.JButton btnK_themPM33;
    private javax.swing.JButton btnK_themPM40;
    private javax.swing.JButton btnQLPN;
    private javax.swing.JButton btn_LuuDMSach6;
    private javax.swing.JButton btn_LuuDMSach7;
    private javax.swing.JButton btn_LuuDMSach8;
    private javax.swing.JButton btn_SuaDMSach6;
    private javax.swing.JButton btn_SuaDMSach7;
    private javax.swing.JButton btn_SuaDMSach8;
    private javax.swing.JButton btn_ThemDMSach6;
    private javax.swing.JButton btn_ThemDMSach7;
    private javax.swing.JButton btn_ThemDMSach8;
    private javax.swing.JButton btn_lamMoiSach2;
    private javax.swing.JButton btn_lammoi6;
    private javax.swing.JButton btn_lammoi7;
    private javax.swing.JButton btn_lammoi8;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbb_chucNangThongKe7;
    private javax.swing.JTextField emailDocgia4;
    private javax.swing.JTextField fieldSoluongthongkesach;
    private javax.swing.JRadioButton gioitinhnam16;
    private javax.swing.JRadioButton gioitinhnam21;
    private javax.swing.JRadioButton gioitinhnu16;
    private javax.swing.JRadioButton gioitinhnu21;
    private javax.swing.JTextField hanDungField1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton13;
    private javax.swing.JComboBox<String> jComboBox11;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JComboBox<String> jComboBox8;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel139;
    private javax.swing.JLabel jLabel141;
    private javax.swing.JLabel jLabel142;
    private javax.swing.JLabel jLabel143;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPK_QuanLyPhieuMuon4;
    private javax.swing.JPanel jPK_QuanLyPhieuMuon5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JTabbedPane jTPK_QuanLyPM4;
    private javax.swing.JTabbedPane jTPK_QuanLyPM5;
    private javax.swing.JTabbedPane jTP_main2;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTabbedPane jTabbedPane7;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable4;
    private javax.swing.JButton khoatk10;
    private javax.swing.JButton khoatk11;
    private javax.swing.JButton khoatk8;
    private javax.swing.JLabel labelSoluongthongkeSach;
    private javax.swing.JTextField maDocGiaField;
    private javax.swing.JTextField maLoaiField;
    private javax.swing.JTextField matKhauField;
    private javax.swing.JButton mokhoa3;
    private javax.swing.JButton mokhoa4;
    private javax.swing.JTextField ngayMotheField;
    private javax.swing.JLabel ngayMotheLabel;
    private javax.swing.JTextField ngaysinh2;
    private javax.swing.JTabbedPane quanlyttdg2;
    private javax.swing.JTextField sdt2;
    private javax.swing.JTextField soLuongField;
    private javax.swing.JTextField soLuongmuonField;
    private javax.swing.JLabel soLuongmuonLabel;
    private javax.swing.JTable tableDocgia2;
    private javax.swing.JTable tableDocgia3;
    private javax.swing.JTable tableSearchSach2;
    private javax.swing.JTable table_PhieuTra;
    private javax.swing.JTable tabletksach2;
    private javax.swing.JTable tblH_Sach2;
    private javax.swing.JTable tbl_DMSach4;
    private javax.swing.JTable tbl_DMSach5;
    private javax.swing.JTextField tenDocGiaField;
    private javax.swing.JTextField tenLoaiField;
    private javax.swing.JTextField tenLoaiField2;
    private javax.swing.JTextField textboxsearch2;
    private javax.swing.JTextField textboxsearch4;
    private javax.swing.JButton themmoidg2;
    private javax.swing.JButton themmoidg3;
    private javax.swing.JTextField thoiGianField;
    private javax.swing.JTextField thoiGianField1;
    private javax.swing.JTextField timKiemDG;
    private javax.swing.JPanel timkiem2;
    private javax.swing.JTextField txt_maDMSach4;
    private javax.swing.JTextField txt_maDMSach5;
    private javax.swing.JTextField txt_tenDMSach10;
    private javax.swing.JTextField txt_tenDMSach11;
    private javax.swing.JTextField txt_tenDMSach12;
    private javax.swing.JTextField txt_tenDMSach13;
    private javax.swing.JTextField txt_tenDMSach14;
    private javax.swing.JTextField txt_timkiemDMSach21;
    private javax.swing.JTextField txt_timkiemDMSach22;
    private javax.swing.JTextField txt_timkiemDMSach23;
    private javax.swing.JTextField txt_timkiemDMSach24;
    private javax.swing.JTextField txt_timkiemDMSach25;
    private javax.swing.JTextField txt_timkiemDMSach28;
    private javax.swing.JButton updatedg2;
    private javax.swing.JButton updatedg3;
    private javax.swing.JButton updatedg4;
    // End of variables declaration//GEN-END:variables
}
