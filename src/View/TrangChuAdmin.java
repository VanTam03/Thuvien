/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import DAO.*;
import DTO.*;
import Model.DanhSachLoaiThe;
import Model.DanhSachQuanLy;
import Model.DanhSachTaiKhoan;
import Model.PhanLoaiThe;
import Model.PhieuTra_BLL;
import Model.TaiKhoan;
import Model.check;
import Model.kho_BLL;
import java.util.List;

import static java.time.zone.ZoneRulesProvider.refresh;

/**
 *
 * @author 1
 */
public class TrangChuAdmin extends javax.swing.JFrame {

    /**
     * Creates new form TrangChuAdmin
     */
    DefaultTableModel defaultTableModelPhieuTra;
    DefaultTableModel defaultTableModelkho;

    DefaultTableModel defaultTableModel_CTPN;
    DefaultTableModel defaultTableModel_DM;
    DefaultTableModel defaultTableModel_TL;
    DefaultTableModel defaultTableModel_TG;
    DefaultTableModel defaultTableModel_Sach;
    DefaultTableModel defaultTableModel_CTPM;
    kho_BLL khobll = new kho_BLL();

    PhieuTra_BLL phieuTra_BLL = new PhieuTra_BLL();

    public TrangChuAdmin() {
        initComponents();

        loadkho(khobll.loadKho());
        loadPhieuTra(phieuTra_BLL.loaddata());
        loadmaDanhMuc();
        loadComboBoxTheLoai();
        loadComboBoxDanhMuc();
        loadmaTheLoai();
        loadmaTacGia();
        loadThongTinSach();
        loadChiTietPhieuMuon();
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

    private void loadkho(List<LoadKhoDTO> loadkhodtos) {
        defaultTableModelkho = new DefaultTableModel();
        tableKho.setModel(defaultTableModelkho);
        defaultTableModelkho.addColumn("Mã sách");
        defaultTableModelkho.addColumn("Tên sách");
        defaultTableModelkho.addColumn("Tổng số Lượng sách");
        defaultTableModelkho.addColumn("Tổng số sách còn lại");
        defaultTableModelkho.addColumn("Tổng số sách hỏng");

        for (LoadKhoDTO loadKhoDTO : loadkhodtos) {
            defaultTableModelkho.addRow(new Object[] { loadKhoDTO.getMaSach(), loadKhoDTO.getTensach(),
                    loadKhoDTO.getTongSoLuong(), loadKhoDTO.getSoLuongCon(),
                    loadKhoDTO.getSoLuongSachHong() });
        }
    }

    public void loadChiTietPhieuMuon() {
        defaultTableModel_CTPM = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jTable2.setModel(defaultTableModel_CTPM);
        defaultTableModel_CTPM.addColumn("Mã phiếu mượn");
        defaultTableModel_CTPM.addColumn("Mã sách");
        defaultTableModel_CTPM.addColumn("Tình trạng sách");
        List<ChiTietPhieuMuon> chiTietPhieuNhapSaches = ChiTietPhieuMuon_DAO.getInstance().selectAll();
        for (ChiTietPhieuMuon ctpns : chiTietPhieuNhapSaches) {
            defaultTableModel_CTPM.addRow(new Object[] { ctpns.getMaPhieumuon(), ctpns.getMaSach(), ctpns.getTinhTrangSach()});
        }
    }

    private void loadComboBoxDanhMuc() {
        List <DanhMucSach> DanhMuc = DanhMucSach_DAO.getInstance().selectAll();
        for (DanhMucSach dm : DanhMuc){
            Hc_maDM2.addItem(dm.getMaDM());
        }
    }

    private void loadComboBoxTheLoai() {
        List<PhanLoaiSach> theLoais = PhanLoaiSach_DAO.getInstance().selectAll();
        for (PhanLoaiSach tl : theLoais) {
            Hc_maTheLoai2.addItem(tl.getMaTheLoai());
        }
    }

    public void loadmaTacGia(){
        defaultTableModel_TG = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        QLTGiaTable.setModel(defaultTableModel_TG);
        defaultTableModel_TG.addColumn("Mã tác giả");
        defaultTableModel_TG.addColumn("Tên tác giả");
        defaultTableModel_TG.addColumn("Số lượng sách");
        List <TacGia> tacGia = TacGia_DAO.getInstance().selectAll();
        for (TacGia tg : tacGia){
            defaultTableModel_TG.addRow(new Object[]{tg.getMaTacGia(), tg.getTenTacGia(),
                    tg.getSoSach()});
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
            defaultTableModel_TL.addRow(new Object[] {tl.getMaTheLoai(), tl.getTenTheLoai() });
        }
    }
    public void loadThongTinSach(){
        defaultTableModel_Sach = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
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
        List <Sach> saches = Sach_DAO.getInstance().selectAll();
        for (Sach sach : saches){
            defaultTableModel_Sach.addRow(new Object[]{sach.getMaSach(), sach.getTenSach(), sach.getMaDMSach(),
                    sach.getMaTheLoai(), sach.getMaTacGia(), sach.getTenTacGia(), sach.getNXB(), sach.getNamXuatBan(),
                    sach.getGiaTienSach(), sach.getTinhTrangSach(), sach.getTomTatND()});
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
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jTP_main2 = new javax.swing.JTabbedPane();
        jPanel28 = new javax.swing.JPanel();
        quanlyttdg2 = new javax.swing.JTabbedPane();
        jPanel29 = new javax.swing.JPanel();
        jScrollPane17 = new javax.swing.JScrollPane();
        tableDocgia2 = new javax.swing.JTable();
        gioitinhnam16 = new javax.swing.JRadioButton();
        gioitinhnu16 = new javax.swing.JRadioButton();
        themmoidg2 = new javax.swing.JButton();
        updatedg2 = new javax.swing.JButton();
        khoatk8 = new javax.swing.JButton();
        mokhoa3 = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JSeparator();
        khoatk9 = new javax.swing.JButton();
        jLabel86 = new javax.swing.JLabel();
        maDocGiaField = new javax.swing.JTextField();
        matKhauField = new javax.swing.JTextField();
        jLabel92 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        gioitinhnam18 = new javax.swing.JRadioButton();
        gioitinhnu18 = new javax.swing.JRadioButton();
        jLabel90 = new javax.swing.JLabel();
        jLabel127 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        sdt2 = new javax.swing.JTextField();
        ngaysinh2 = new javax.swing.JTextField();
        emailDocgia4 = new javax.swing.JTextField();
        Hc_maTheLoai3 = new javax.swing.JComboBox<>();
        soLuongmuonLabel = new javax.swing.JLabel();
        ngayMotheLabel = new javax.swing.JLabel();
        jLabel135 = new javax.swing.JLabel();
        hanDungField1 = new javax.swing.JTextField();
        ngayMotheField = new javax.swing.JTextField();
        soLuongmuonField = new javax.swing.JTextField();
        tenDocGiaField = new javax.swing.JTextField();
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
        btnH_luuSach2 = new javax.swing.JButton();
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
        btnK_themPM31 = new javax.swing.JButton();
        txt_timkiemDMSach24 = new javax.swing.JTextField();
        gioitinhnam17 = new javax.swing.JRadioButton();
        gioitinhnu17 = new javax.swing.JRadioButton();
        btnK_themPM32 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        Panel_DanhSachPM15 = new javax.swing.JPanel();
        K_tieuDe16 = new javax.swing.JLabel();
        btnChiTietView = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_PhieuTra = new javax.swing.JTable();
        textboxsearch4 = new javax.swing.JTextField();
        jComboBox11 = new javax.swing.JComboBox<>();
        jPanel35 = new javax.swing.JPanel();
        jTabbedPane7 = new javax.swing.JTabbedPane();
        jPanel36 = new javax.swing.JPanel();
        jLabel118 = new javax.swing.JLabel();
        cbb_chucNangThongKe6 = new javax.swing.JComboBox<>();
        jScrollPane21 = new javax.swing.JScrollPane();
        tabletkbandoc2 = new javax.swing.JTable();
        jLabel119 = new javax.swing.JLabel();
        jPanel37 = new javax.swing.JPanel();
        labelSoluongthongkeSach = new javax.swing.JLabel();
        cbb_chucNangThongKe7 = new javax.swing.JComboBox<>();
        jLabel121 = new javax.swing.JLabel();
        jLabel128 = new javax.swing.JLabel();
        fieldSoluongthongkesach = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabletksach2 = new javax.swing.JTable();
        jPanel38 = new javax.swing.JPanel();
        jLabel122 = new javax.swing.JLabel();
        cbb_chucNangThongKe8 = new javax.swing.JComboBox<>();
        jScrollPane23 = new javax.swing.JScrollPane();
        tabletktienphat2 = new javax.swing.JTable();
        jLabel123 = new javax.swing.JLabel();
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
        jPK_QuanLyTaiKhoan = new javax.swing.JPanel();
        timkiem4 = new javax.swing.JPanel();
        jScrollPane30 = new javax.swing.JScrollPane();
        tableSearchSach7 = new javax.swing.JTable();
        themmoidg6 = new javax.swing.JButton();
        updatedg6 = new javax.swing.JButton();
        khoatk18 = new javax.swing.JButton();
        khoatk19 = new javax.swing.JButton();
        khoatk20 = new javax.swing.JButton();
        textboxsearch16 = new javax.swing.JTextField();
        jLabel180 = new javax.swing.JLabel();
        jLabel179 = new javax.swing.JLabel();
        gioitinhnam22 = new javax.swing.JRadioButton();
        gioitinhnu22 = new javax.swing.JRadioButton();
        Hc_maTheLoai6 = new javax.swing.JComboBox<>();
        jLabel175 = new javax.swing.JLabel();
        jLabel174 = new javax.swing.JLabel();
        jLabel173 = new javax.swing.JLabel();
        textboxsearch14 = new javax.swing.JTextField();
        textboxsearch15 = new javax.swing.JTextField();
        jLabel176 = new javax.swing.JLabel();
        jLabel181 = new javax.swing.JLabel();
        jLabel177 = new javax.swing.JLabel();
        tenDocGiaField5 = new javax.swing.JTextField();
        tenDocGiaField6 = new javax.swing.JTextField();
        matKhauField3 = new javax.swing.JTextField();
        QuanLyKho = new javax.swing.JPanel();
        timkiem3 = new javax.swing.JPanel();
        jLabel129 = new javax.swing.JLabel();
        textboxsearch3 = new javax.swing.JTextField();
        jLabel130 = new javax.swing.JLabel();
        jLabel133 = new javax.swing.JLabel();
        jComboBox10 = new javax.swing.JComboBox<>();
        jScrollPane26 = new javax.swing.JScrollPane();
        tableKho = new javax.swing.JTable();
        jLabel134 = new javax.swing.JLabel();
        jLabel136 = new javax.swing.JLabel();
        jLabel143 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtTenSach = new javax.swing.JTextArea();
        txtMaSach = new javax.swing.JTextField();
        updatedg7 = new javax.swing.JButton();
        radioTang = new javax.swing.JRadioButton();
        radioGiam = new javax.swing.JRadioButton();
        btnH_luuSach3 = new javax.swing.JButton();
        txtTongSL = new javax.swing.JTextField();
        txtTongSLSachHong = new javax.swing.JTextField();
        txtTongSLCL = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));

        jButton1.setBackground(new java.awt.Color(255, 204, 204));
        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/choice.png"))); // NOI18N
        jButton1.setText("Đăng xuất");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/SGU.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 26)); // NOI18N
        jLabel1.setText("QUẢN LÝ THƯ VIỆN");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 26)); // NOI18N
        jLabel2.setText(" TRƯỜNG ĐẠI HỌC SÀI GÒN");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 108,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1)
                                .addContainerGap()));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jButton1)
                                .addContainerGap(40, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 112,
                                                Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(11, 11, 11)
                                                .addComponent(jLabel1)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel2)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap()));

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
                new Object[][] {

                },
                new String[] {

                }));
        tableDocgia2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDocgia2MouseClicked(evt);
            }
        });
        jScrollPane17.setViewportView(tableDocgia2);

        gioitinhnam16.setBackground(new java.awt.Color(255, 255, 204));
        gioitinhnam16.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        gioitinhnam16.setText("Nam");

        gioitinhnu16.setBackground(new java.awt.Color(255, 255, 204));
        gioitinhnu16.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        gioitinhnu16.setText("Nữ");

        themmoidg2.setBackground(new java.awt.Color(255, 204, 204));
        themmoidg2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        themmoidg2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/plus.png"))); // NOI18N
        themmoidg2.setText("Thêm mới");
        themmoidg2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themmoidg2ActionPerformed(evt);
            }
        });

        updatedg2.setBackground(new java.awt.Color(255, 204, 204));
        updatedg2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        updatedg2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/exchange1.png"))); // NOI18N
        updatedg2.setText("Sửa ");
        updatedg2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatedg2ActionPerformed(evt);
            }
        });

        khoatk8.setBackground(new java.awt.Color(255, 204, 204));
        khoatk8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        khoatk8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/block-user.png"))); // NOI18N
        khoatk8.setText("Xóa");
        khoatk8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                khoatk8ActionPerformed(evt);
            }
        });

        mokhoa3.setBackground(new java.awt.Color(255, 204, 204));
        mokhoa3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        mokhoa3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/eraser.png"))); // NOI18N
        mokhoa3.setText("Làm mới");
        mokhoa3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mokhoa3ActionPerformed(evt);
            }
        });

        khoatk9.setBackground(new java.awt.Color(255, 204, 204));
        khoatk9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        khoatk9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/searching.png"))); // NOI18N
        khoatk9.setText("Hổ trợ tìm kiếm");
        khoatk9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                khoatk9ActionPerformed(evt);
            }
        });

        jLabel86.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(0, 0, 0));
        jLabel86.setText("Mã độc giả:");

        maDocGiaField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        matKhauField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        jLabel92.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel92.setForeground(new java.awt.Color(0, 0, 0));
        jLabel92.setText("Mật khẩu:");

        jLabel87.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(0, 0, 0));
        jLabel87.setText("Tên độc giả:");

        jLabel88.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(0, 0, 0));
        jLabel88.setText("Giới tính:");

        gioitinhnam18.setBackground(new java.awt.Color(255, 255, 204));
        buttonGroup2.add(gioitinhnam18);
        gioitinhnam18.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        gioitinhnam18.setForeground(new java.awt.Color(0, 0, 0));
        gioitinhnam18.setText("Nam");

        gioitinhnu18.setBackground(new java.awt.Color(255, 255, 204));
        buttonGroup2.add(gioitinhnu18);
        gioitinhnu18.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        gioitinhnu18.setForeground(new java.awt.Color(0, 0, 0));
        gioitinhnu18.setText("Nữ");

        jLabel90.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(0, 0, 0));
        jLabel90.setText("Phân loại");

        jLabel127.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel127.setForeground(new java.awt.Color(0, 0, 0));
        jLabel127.setText("Email:");

        jLabel91.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(0, 0, 0));
        jLabel91.setText("Ngày sinh");

        jLabel89.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(0, 0, 0));
        jLabel89.setText("Số điện thoại:");

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

        emailDocgia4.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        emailDocgia4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailDocgia4ActionPerformed(evt);
            }
        });

        soLuongmuonLabel.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        soLuongmuonLabel.setForeground(new java.awt.Color(0, 0, 0));
        soLuongmuonLabel.setText("Số lượng mượn:");

        ngayMotheLabel.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        ngayMotheLabel.setForeground(new java.awt.Color(0, 0, 0));
        ngayMotheLabel.setText("Ngày mở thẻ:");

        jLabel135.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel135.setForeground(new java.awt.Color(0, 0, 0));
        jLabel135.setText("Hạn dùng:");

        hanDungField1.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        ngayMotheField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        soLuongmuonField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        tenDocGiaField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
                jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel29Layout.createSequentialGroup()
                                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel29Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jSeparator5))
                                        .addGroup(jPanel29Layout.createSequentialGroup()
                                                .addGap(27, 27, 27)
                                                .addGroup(jPanel29Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel29Layout.createSequentialGroup()
                                                                .addComponent(themmoidg2)
                                                                .addGap(52, 52, 52)
                                                                .addComponent(updatedg2,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 111,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(47, 47, 47)
                                                                .addComponent(khoatk8,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 121,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(68, 68, 68)
                                                                .addComponent(khoatk9,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 210,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                        99, Short.MAX_VALUE)
                                                                .addComponent(mokhoa3,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 151,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(89, 89, 89))
                                                        .addComponent(jScrollPane17))))
                                .addContainerGap())
                        .addGroup(jPanel29Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel87)
                                        .addComponent(jLabel92)
                                        .addComponent(jLabel86)
                                        .addComponent(jLabel88))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel29Layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(gioitinhnam18)
                                                .addGap(45, 45, 45)
                                                .addComponent(gioitinhnu18))
                                        .addGroup(jPanel29Layout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(matKhauField, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(maDocGiaField, javax.swing.GroupLayout.Alignment.LEADING,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                                .addComponent(tenDocGiaField,
                                                        javax.swing.GroupLayout.Alignment.LEADING)))
                                .addGap(58, 58, 58)
                                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel89)
                                        .addComponent(jLabel90)
                                        .addComponent(jLabel91)
                                        .addComponent(jLabel127))
                                .addGroup(jPanel29Layout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(sdt2)
                                        .addComponent(ngaysinh2)
                                        .addComponent(emailDocgia4)
                                        .addComponent(Hc_maTheLoai3, javax.swing.GroupLayout.PREFERRED_SIZE, 200,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(soLuongmuonLabel)
                                        .addComponent(jLabel135)
                                        .addComponent(ngayMotheLabel))
                                .addGap(32, 32, 32)
                                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(hanDungField1, javax.swing.GroupLayout.PREFERRED_SIZE, 200,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(soLuongmuonField, javax.swing.GroupLayout.PREFERRED_SIZE, 200,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(ngayMotheField, javax.swing.GroupLayout.PREFERRED_SIZE, 200,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        jPanel29Layout.setVerticalGroup(
                jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel29Layout.createSequentialGroup()
                                                .addGroup(jPanel29Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel86)
                                                        .addComponent(maDocGiaField,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel29Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel92)
                                                        .addComponent(matKhauField,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel29Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel87)
                                                        .addComponent(tenDocGiaField,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel29Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel88)
                                                        .addComponent(gioitinhnam18)
                                                        .addComponent(gioitinhnu18)))
                                        .addGroup(jPanel29Layout.createSequentialGroup()
                                                .addGroup(jPanel29Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel89)
                                                        .addComponent(sdt2, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel135)
                                                        .addComponent(hanDungField1,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel29Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel91)
                                                        .addComponent(ngaysinh2, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(ngayMotheLabel)
                                                        .addComponent(ngayMotheField,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel29Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel127)
                                                        .addComponent(emailDocgia4,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(soLuongmuonLabel)
                                                        .addComponent(soLuongmuonField,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel29Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel90)
                                                        .addComponent(Hc_maTheLoai3,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(mokhoa3, javax.swing.GroupLayout.PREFERRED_SIZE, 41,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel29Layout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(themmoidg2, javax.swing.GroupLayout.PREFERRED_SIZE, 41,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(updatedg2, javax.swing.GroupLayout.PREFERRED_SIZE, 41,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(khoatk8, javax.swing.GroupLayout.PREFERRED_SIZE, 41,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(khoatk9, javax.swing.GroupLayout.PREFERRED_SIZE, 41,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane17, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                                .addContainerGap()));

        quanlyttdg2.addTab("Quản lý Độc giả", jPanel29);

        jPanel40.setBackground(new java.awt.Color(255, 255, 204));

        tableDocgia3.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        tableDocgia3.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {

                },
                new String[] {

                }));
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
                                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel40Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jSeparator7))
                                        .addGroup(jPanel40Layout.createSequentialGroup()
                                                .addGap(27, 27, 27)
                                                .addGroup(jPanel40Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                jPanel40Layout.createSequentialGroup()
                                                                        .addGap(55, 55, 55)
                                                                        .addGroup(jPanel40Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel40Layout
                                                                                        .createSequentialGroup()
                                                                                        .addGroup(jPanel40Layout
                                                                                                .createParallelGroup(
                                                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                .addComponent(jLabel137,
                                                                                                        javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                .addComponent(
                                                                                                        jLabel131))
                                                                                        .addGap(37, 37, 37)
                                                                                        .addGroup(jPanel40Layout
                                                                                                .createParallelGroup(
                                                                                                        javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                        false)
                                                                                                .addComponent(
                                                                                                        tenLoaiField,
                                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                        250,
                                                                                                        Short.MAX_VALUE)
                                                                                                .addComponent(
                                                                                                        maLoaiField))
                                                                                        .addGap(96, 96, 96)
                                                                                        .addGroup(jPanel40Layout
                                                                                                .createParallelGroup(
                                                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                .addComponent(jLabel132,
                                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                        155,
                                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addComponent(jLabel139,
                                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                        174,
                                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                .addGroup(jPanel40Layout
                                                                                        .createSequentialGroup()
                                                                                        .addComponent(jLabel141,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                153,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addPreferredGap(
                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                        .addComponent(thoiGianField1,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                250,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                        .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addGroup(jPanel40Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                false)
                                                                                .addComponent(soLuongField,
                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                        250, Short.MAX_VALUE)
                                                                                .addComponent(thoiGianField))
                                                                        .addGap(0, 0, Short.MAX_VALUE))
                                                        .addComponent(jScrollPane25,
                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel40Layout.createSequentialGroup()
                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                .addComponent(jLabel142)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(tenLoaiField2,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 259,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(153, 153, 153)
                                                                .addComponent(themmoidg3)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(updatedg3,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 111,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(mokhoa4,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 151,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(42, 42, 42)))))
                                .addContainerGap()));
        jPanel40Layout.setVerticalGroup(
                jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel40Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel40Layout
                                                .createSequentialGroup()
                                                .addGroup(jPanel40Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel131)
                                                        .addComponent(maLoaiField,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel132)
                                                        .addComponent(soLuongField,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel40Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel137)
                                                        .addComponent(tenLoaiField,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel40Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel141)
                                                        .addComponent(thoiGianField1,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel40Layout
                                                .createSequentialGroup()
                                                .addGroup(jPanel40Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel139)
                                                        .addComponent(thoiGianField,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(67, 67, 67)))
                                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(themmoidg3, javax.swing.GroupLayout.PREFERRED_SIZE, 41,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(updatedg3, javax.swing.GroupLayout.PREFERRED_SIZE, 41,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(mokhoa4, javax.swing.GroupLayout.PREFERRED_SIZE, 41,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tenLoaiField2, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel142))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane25, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                                .addContainerGap()));

        quanlyttdg2.addTab("Phân loại Độc giả", jPanel40);

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
                jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(quanlyttdg2));
        jPanel28Layout.setVerticalGroup(
                jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(quanlyttdg2));

        jTP_main2.addTab("     QUẢN LÝ ĐỘC GIẢ ",
                new javax.swing.ImageIcon(getClass().getResource("/Images/reading.png")), jPanel28); // NOI18N

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
                new Object[][] {

                },
                new String[] {

                }));
        tblH_Sach2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblH_Sach2MouseClicked(evt);
            }
        });
        jScrollPane18.setViewportView(tblH_Sach2);

        btnH_themSach2.setBackground(new java.awt.Color(255, 204, 204));
        btnH_themSach2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnH_themSach2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/plus.png"))); // NOI18N
        btnH_themSach2.setText("Thêm");
        btnH_themSach2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnH_themSach2ActionPerformed(evt);
            }
        });

        btnH_suaSach2.setBackground(new java.awt.Color(255, 204, 204));
        btnH_suaSach2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnH_suaSach2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/exchange1.png"))); // NOI18N
        btnH_suaSach2.setText("Sửa ");
        btnH_suaSach2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnH_suaSach2ActionPerformed(evt);
            }
        });

        btnH_luuSach2.setBackground(new java.awt.Color(255, 204, 204));
        btnH_luuSach2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnH_luuSach2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/luu.png"))); // NOI18N
        btnH_luuSach2.setText("Xem tình trạng sách");
        btnH_luuSach2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnH_luuSach2ActionPerformed(evt);
            }
        });

        btn_lamMoiSach2.setBackground(new java.awt.Color(255, 204, 204));
        btn_lamMoiSach2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
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
        khoatk10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/block-user.png"))); // NOI18N
        khoatk10.setText("Xóa");
        khoatk10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                khoatk10ActionPerformed(evt);
            }
        });

        khoatk11.setBackground(new java.awt.Color(255, 204, 204));
        khoatk11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
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
        H_tenSach3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                H_tenSach3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
                jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel31Layout.createSequentialGroup()
                                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel31Layout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addGroup(jPanel31Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel31Layout.createSequentialGroup()
                                                                .addGroup(jPanel31Layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel31Layout.createSequentialGroup()
                                                                                .addGroup(jPanel31Layout
                                                                                        .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(jLabel98)
                                                                                        .addComponent(jLabel101)
                                                                                        .addComponent(jLabel95))
                                                                                .addPreferredGap(
                                                                                        javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(H_namXB2,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        258,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(jPanel31Layout.createSequentialGroup()
                                                                                .addGroup(jPanel31Layout
                                                                                        .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(jLabel93)
                                                                                        .addComponent(jLabel94)
                                                                                        .addComponent(jLabel102,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                107,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGap(26, 26, 26)
                                                                                .addGroup(jPanel31Layout
                                                                                        .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                false)
                                                                                        .addComponent(H_tenTheLoai2)
                                                                                        .addComponent(Hc_maTheLoai2, 0,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                        .addComponent(H_soLuongCon2,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                263,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(H_tenSach2,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                267,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(H_tenSach3,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                267,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                        114, Short.MAX_VALUE)
                                                                .addGroup(jPanel31Layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel96,
                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(jLabel97,
                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(jLabel103,
                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(jLabel100,
                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(jLabel99,
                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(jLabel104,
                                                                                javax.swing.GroupLayout.Alignment.TRAILING)))
                                                        .addGroup(jPanel31Layout.createSequentialGroup()
                                                                .addComponent(btnH_themSach2,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 128,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(btnH_suaSach2,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 119,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(khoatk10,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 121,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(btnH_luuSach2,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 241,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 8, Short.MAX_VALUE)))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel31Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel31Layout
                                                                .createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING,
                                                                        false)
                                                                .addComponent(H_nhaXB2,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE, 335,
                                                                        Short.MAX_VALUE)
                                                                .addComponent(H_tenDM2,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE, 335,
                                                                        Short.MAX_VALUE)
                                                                .addComponent(H_tomTat2,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE, 335,
                                                                        Short.MAX_VALUE)
                                                                .addComponent(H_tacGia4)
                                                                .addComponent(Hc_maDM2, 0,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        Short.MAX_VALUE)
                                                                .addComponent(H_tacGia5))
                                                        .addGroup(jPanel31Layout.createSequentialGroup()
                                                                .addComponent(khoatk11,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 210,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(35, 35, 35)
                                                                .addComponent(btn_lamMoiSach2,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 154,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(jPanel31Layout.createSequentialGroup()
                                                .addGap(999, 999, 999)
                                                .addComponent(jSeparator6))
                                        .addGroup(jPanel31Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jScrollPane18)))
                                .addContainerGap()));
        jPanel31Layout.setVerticalGroup(
                jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel31Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel31Layout.createSequentialGroup()
                                                .addGroup(jPanel31Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel93)
                                                        .addComponent(H_tenSach2,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(20, 20, 20))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout
                                                .createSequentialGroup()
                                                .addGroup(jPanel31Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(H_tacGia5, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel96))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel94)
                                        .addComponent(jLabel104)
                                        .addComponent(H_tacGia4, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(H_tenSach3, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel102)
                                        .addComponent(Hc_maTheLoai2, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel97)
                                        .addComponent(H_nhaXB2, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(13, 13, 13)
                                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(H_tenTheLoai2, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel101)
                                        .addComponent(Hc_maDM2, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel103))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(H_soLuongCon2, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel95)
                                        .addComponent(jLabel100)
                                        .addComponent(H_tenDM2, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(21, 21, 21)
                                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel31Layout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(H_namXB2, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel98)
                                                .addComponent(jLabel99))
                                        .addComponent(H_tomTat2, javax.swing.GroupLayout.PREFERRED_SIZE, 59,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnH_themSach2)
                                        .addComponent(btnH_suaSach2)
                                        .addComponent(khoatk10, javax.swing.GroupLayout.PREFERRED_SIZE, 41,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnH_luuSach2, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btn_lamMoiSach2, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(khoatk11, javax.swing.GroupLayout.PREFERRED_SIZE, 41,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 224,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(83, 83, 83)));

        jTabbedPane4.addTab("Quản lý Sách", jPanel31);

        jPanel32.setBackground(new java.awt.Color(255, 255, 204));

        tbl_DMSach4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        tbl_DMSach4.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {

                },
                new String[] {

                }));
        tbl_DMSach4.setGridColor(new java.awt.Color(255, 255, 255));
        tbl_DMSach4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_DMSach4MouseClicked(evt);
            }
        });
        jScrollPane19.setViewportView(tbl_DMSach4);

        jLabel105.setFont(new java.awt.Font("Times New Roman", 1, 22)); // NOI18N
        jLabel105.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/book.png"))); // NOI18N
        jLabel105.setText("Thông tin danh mục Sách:");

        jLabel106.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel106.setText("Mã danh mục:");

        jLabel107.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel107.setText("Tên danh mục:");

        txt_maDMSach4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt_maDMSach4.setEnabled(false);

        txt_tenDMSach10.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        btn_ThemDMSach6.setBackground(new java.awt.Color(255, 204, 204));
        btn_ThemDMSach6.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btn_ThemDMSach6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/them.png"))); // NOI18N
        btn_ThemDMSach6.setText("Thêm");
        btn_ThemDMSach6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemDMSach6ActionPerformed(evt);
            }
        });

        btn_LuuDMSach6.setBackground(new java.awt.Color(255, 204, 204));
        btn_LuuDMSach6.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btn_LuuDMSach6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/luu.png"))); // NOI18N
        btn_LuuDMSach6.setText("Lưu");
        btn_LuuDMSach6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LuuDMSach6ActionPerformed(evt);
            }
        });

        btn_SuaDMSach6.setBackground(new java.awt.Color(255, 204, 204));
        btn_SuaDMSach6.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
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
                                                .addGroup(jPanel32Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                false)
                                                        .addGroup(jPanel32Layout.createSequentialGroup()
                                                                .addComponent(jLabel106)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(txt_maDMSach4))
                                                        .addGroup(jPanel32Layout.createSequentialGroup()
                                                                .addComponent(jLabel107)
                                                                .addGap(15, 15, 15)
                                                                .addComponent(txt_tenDMSach10,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 279,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62,
                                                        Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout
                                                .createSequentialGroup()
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(jPanel32Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                jPanel32Layout.createSequentialGroup()
                                                                        .addGroup(jPanel32Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addComponent(btn_ThemDMSach6,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        136,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(btn_SuaDMSach6,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        136,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGap(18, 18, 18)
                                                                        .addGroup(jPanel32Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addComponent(btn_lammoi6)
                                                                                .addComponent(btn_LuuDMSach6,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        147,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGap(83, 83, 83))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                jPanel32Layout.createSequentialGroup()
                                                                        .addComponent(jLabel105)
                                                                        .addGap(121, 121, 121)))))
                                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel32Layout.createSequentialGroup()
                                                .addComponent(txt_timkiemDMSach21,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 345,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel108))
                                        .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 482,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(72, Short.MAX_VALUE)));
        jPanel32Layout.setVerticalGroup(
                jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel32Layout.createSequentialGroup()
                                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel32Layout.createSequentialGroup()
                                                .addGap(92, 92, 92)
                                                .addGroup(jPanel32Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(txt_timkiemDMSach21,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 37,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel108)
                                                        .addComponent(jLabel105))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        312, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel32Layout.createSequentialGroup()
                                                .addGap(190, 190, 190)
                                                .addGroup(jPanel32Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel106)
                                                        .addComponent(txt_maDMSach4,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(47, 47, 47)
                                                .addGroup(jPanel32Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel107)
                                                        .addComponent(txt_tenDMSach10,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(43, 43, 43)
                                                .addGroup(jPanel32Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(btn_ThemDMSach6,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btn_LuuDMSach6,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel32Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(btn_SuaDMSach6,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 38,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btn_lammoi6,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 38,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(258, Short.MAX_VALUE)));

        jTabbedPane4.addTab("Quản lý Danh mục", jPanel32);

        jPanel33.setBackground(new java.awt.Color(255, 255, 204));

        tbl_DMSach5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        tbl_DMSach5.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {

                },
                new String[] {

                }));
        tbl_DMSach5.setGridColor(new java.awt.Color(255, 255, 255));
        tbl_DMSach5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_DMSach5MouseClicked(evt);
            }
        });
        jScrollPane20.setViewportView(tbl_DMSach5);

        jLabel109.setFont(new java.awt.Font("Times New Roman", 1, 22)); // NOI18N
        jLabel109.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/book.png"))); // NOI18N
        jLabel109.setText("Thông tin thể loại Sách:");

        jLabel110.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel110.setText("Mã thể loại:");

        jLabel111.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel111.setText("Tên thể loại:");

        txt_maDMSach5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt_maDMSach5.setEnabled(false);

        txt_tenDMSach11.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        btn_ThemDMSach7.setBackground(new java.awt.Color(255, 204, 204));
        btn_ThemDMSach7.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btn_ThemDMSach7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/them.png"))); // NOI18N
        btn_ThemDMSach7.setText("Thêm");
        btn_ThemDMSach7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemDMSach7ActionPerformed(evt);
            }
        });

        btn_LuuDMSach7.setBackground(new java.awt.Color(255, 204, 204));
        btn_LuuDMSach7.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btn_LuuDMSach7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/luu.png"))); // NOI18N
        btn_LuuDMSach7.setText("Lưu");
        btn_LuuDMSach7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LuuDMSach7ActionPerformed(evt);
            }
        });

        btn_SuaDMSach7.setBackground(new java.awt.Color(255, 204, 204));
        btn_SuaDMSach7.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
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
                                                .addGroup(jPanel33Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                false)
                                                        .addGroup(jPanel33Layout.createSequentialGroup()
                                                                .addComponent(jLabel110)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(txt_maDMSach5))
                                                        .addGroup(jPanel33Layout.createSequentialGroup()
                                                                .addComponent(jLabel111)
                                                                .addGap(15, 15, 15)
                                                                .addComponent(txt_tenDMSach11,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 279,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80,
                                                        Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel33Layout
                                                .createSequentialGroup()
                                                .addContainerGap(178, Short.MAX_VALUE)
                                                .addGroup(jPanel33Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                jPanel33Layout.createSequentialGroup()
                                                                        .addComponent(jLabel109)
                                                                        .addGap(121, 121, 121))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                jPanel33Layout.createSequentialGroup()
                                                                        .addGroup(jPanel33Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addComponent(btn_ThemDMSach7,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        136,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(btn_SuaDMSach7,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        136,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGap(18, 18, 18)
                                                                        .addGroup(jPanel33Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addComponent(btn_lammoi7)
                                                                                .addComponent(btn_LuuDMSach7,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        147,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGap(83, 83, 83)))))
                                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel33Layout.createSequentialGroup()
                                                .addComponent(txt_timkiemDMSach22,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 345,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel112))
                                        .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 482,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(77, Short.MAX_VALUE)));
        jPanel33Layout.setVerticalGroup(
                jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel33Layout.createSequentialGroup()
                                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel33Layout.createSequentialGroup()
                                                .addGap(92, 92, 92)
                                                .addGroup(jPanel33Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(txt_timkiemDMSach22,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 37,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel112))
                                                .addGap(18, 18, 18)
                                                .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        306, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel33Layout.createSequentialGroup()
                                                .addGap(114, 114, 114)
                                                .addComponent(jLabel109)
                                                .addGap(44, 44, 44)
                                                .addGroup(jPanel33Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel110)
                                                        .addComponent(txt_maDMSach5,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(47, 47, 47)
                                                .addGroup(jPanel33Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel111)
                                                        .addComponent(txt_tenDMSach11,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(43, 43, 43)
                                                .addGroup(jPanel33Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(btn_ThemDMSach7,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btn_LuuDMSach7,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel33Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(btn_SuaDMSach7,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 38,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btn_lammoi7,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 38,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(258, Short.MAX_VALUE)));

        jTabbedPane4.addTab("Quản lý Thể loại", jPanel33);

        jPanel34.setBackground(new java.awt.Color(255, 255, 204));

        jLabel113.setFont(new java.awt.Font("Times New Roman", 1, 22)); // NOI18N
        jLabel113.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/book.png"))); // NOI18N
        jLabel113.setText("Thông tin tác giả:");

        jLabel114.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel114.setText("Mã tác giả:");

        jLabel115.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel115.setText("Tên tác giả:");

        txt_tenDMSach12.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        btn_ThemDMSach8.setBackground(new java.awt.Color(255, 204, 204));
        btn_ThemDMSach8.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btn_ThemDMSach8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/them.png"))); // NOI18N
        btn_ThemDMSach8.setText("Thêm");
        btn_ThemDMSach8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemDMSach8ActionPerformed(evt);
            }
        });

        btn_LuuDMSach8.setBackground(new java.awt.Color(255, 204, 204));
        btn_LuuDMSach8.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btn_LuuDMSach8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/luu.png"))); // NOI18N
        btn_LuuDMSach8.setText("Lưu");
        btn_LuuDMSach8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LuuDMSach8ActionPerformed(evt);
            }
        });

        btn_SuaDMSach8.setBackground(new java.awt.Color(255, 204, 204));
        btn_SuaDMSach8.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
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
        jLabel117.setText("Số lượng sách:");

        txt_tenDMSach13.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        txt_tenDMSach14.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        QLTGiaTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                        { null, null, null },
                        { null, null, null },
                        { null, null, null },
                        { null, null, null }
                },
                new String[] {
                        "Mã tác giả", "Tên tác giả", "Số lượng sách"
                }));

        jScrollPane3.setViewportView(QLTGiaTable);
        QLTGiaTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                QLTGiaTableMouseClicked(evt);
            }
        });
        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
                jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel34Layout.createSequentialGroup()
                                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel34Layout.createSequentialGroup()
                                                .addGap(43, 43, 43)
                                                .addGroup(jPanel34Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel115)
                                                        .addComponent(jLabel117)
                                                        .addComponent(jLabel114))
                                                .addGap(30, 30, 30)
                                                .addGroup(jPanel34Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txt_tenDMSach12,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 279,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txt_tenDMSach13,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 279,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txt_tenDMSach14,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 279,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                        160, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel34Layout
                                                .createSequentialGroup()
                                                .addContainerGap(252, Short.MAX_VALUE)
                                                .addGroup(jPanel34Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                jPanel34Layout.createSequentialGroup()
                                                                        .addComponent(jLabel113)
                                                                        .addGap(123, 123, 123))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                jPanel34Layout.createSequentialGroup()
                                                                        .addGroup(jPanel34Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addComponent(btn_ThemDMSach8,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        136,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(btn_SuaDMSach8,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        136,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGap(18, 18, 18)
                                                                        .addGroup(jPanel34Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addComponent(btn_lammoi8)
                                                                                .addComponent(btn_LuuDMSach8,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        147,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGap(85, 85, 85)))))
                                .addGap(5, 5, 5)
                                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel34Layout.createSequentialGroup()
                                                .addComponent(txt_timkiemDMSach23,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 345,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel116)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(jScrollPane3))
                                .addContainerGap()));
        jPanel34Layout.setVerticalGroup(
                jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel34Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel34Layout.createSequentialGroup()
                                                .addGroup(jPanel34Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(txt_timkiemDMSach23,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 37,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel116))
                                                .addGap(18, 18, 18)
                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 307,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel34Layout.createSequentialGroup()
                                                .addComponent(jLabel113)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel34Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(txt_tenDMSach14,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel114))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel34Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel115)
                                                        .addComponent(txt_tenDMSach13,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel34Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel117)
                                                        .addComponent(txt_tenDMSach12,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(146, 146, 146)
                                                .addGroup(jPanel34Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(btn_ThemDMSach8,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btn_LuuDMSach8,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel34Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(btn_SuaDMSach8,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 38,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btn_lammoi8,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 38,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(257, Short.MAX_VALUE)));

        jTabbedPane4.addTab("Quản lý Tác giả", jPanel34);

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
                jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTabbedPane4));
        jPanel30Layout.setVerticalGroup(
                jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 751, Short.MAX_VALUE));

        jTP_main2.addTab("          QUẢN LÝ SÁCH  ",
                new javax.swing.ImageIcon(getClass().getResource("/Images/books.png")), jPanel30); // NOI18N

        jPK_QuanLyPhieuMuon4.setBackground(new java.awt.Color(204, 204, 255));
        jPK_QuanLyPhieuMuon4.setPreferredSize(new java.awt.Dimension(1000, 704));

        jTPK_QuanLyPM4.setForeground(new java.awt.Color(51, 0, 102));
        jTPK_QuanLyPM4.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jTPK_QuanLyPM4.setPreferredSize(new java.awt.Dimension(900, 704));

        Panel_DanhSachPM14.setBackground(new java.awt.Color(255, 255, 204));
        Panel_DanhSachPM14.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        K_tieuDe15.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        K_tieuDe15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/bill.png"))); // NOI18N
        K_tieuDe15.setText("CÁC PHIẾU MƯỢN ĐÃ ĐĂNG KÝ");

        btnK_themPM31.setBackground(new java.awt.Color(255, 204, 204));
        btnK_themPM31.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnK_themPM31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/plus.png"))); // NOI18N
        btnK_themPM31.setText("Xem chi tiết");
        btnK_themPM31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnK_themPM31btnK_themPM1ActionPerformed(evt);
            }
        });

        txt_timkiemDMSach24.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt_timkiemDMSach24.setText("Tìm kiếm");
        txt_timkiemDMSach24.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_timkiemDMSach24txt_timkiemDMSach3KeyReleased(evt);
            }
        });

        gioitinhnam17.setBackground(new java.awt.Color(255, 255, 204));
        gioitinhnam17.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        gioitinhnam17.setText("Theo tên độc giả");

        gioitinhnu17.setBackground(new java.awt.Color(255, 255, 204));
        gioitinhnu17.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        gioitinhnu17.setText("Theo mã phiếu mượn");

        btnK_themPM32.setBackground(new java.awt.Color(255, 204, 204));
        btnK_themPM32.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnK_themPM32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/plus.png"))); // NOI18N
        btnK_themPM32.setText("Quản lý phiếu mượn");
        btnK_themPM32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnK_themPM32btnK_themPM2ActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null }
                },
                new String[] {
                        "Title 1", "Title 2", "Title 3", "Title 4"
                }));
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout Panel_DanhSachPM14Layout = new javax.swing.GroupLayout(Panel_DanhSachPM14);
        Panel_DanhSachPM14.setLayout(Panel_DanhSachPM14Layout);
        Panel_DanhSachPM14Layout.setHorizontalGroup(
                Panel_DanhSachPM14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(Panel_DanhSachPM14Layout.createSequentialGroup()
                                .addGroup(Panel_DanhSachPM14Layout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(Panel_DanhSachPM14Layout.createSequentialGroup()
                                                .addGap(316, 316, 316)
                                                .addComponent(K_tieuDe15, javax.swing.GroupLayout.PREFERRED_SIZE, 432,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(Panel_DanhSachPM14Layout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(Panel_DanhSachPM14Layout.createSequentialGroup()
                                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                Short.MAX_VALUE)
                                                        .addComponent(btnK_themPM32)
                                                        .addGap(35, 35, 35)
                                                        .addComponent(btnK_themPM31))
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                        Panel_DanhSachPM14Layout.createSequentialGroup()
                                                                .addGap(81, 81, 81)
                                                                .addGroup(Panel_DanhSachPM14Layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jScrollPane2,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                984,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(Panel_DanhSachPM14Layout
                                                                                .createSequentialGroup()
                                                                                .addComponent(txt_timkiemDMSach24,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        345,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(49, 49, 49)
                                                                                .addComponent(gioitinhnam17)
                                                                                .addGap(38, 38, 38)
                                                                                .addComponent(gioitinhnu17))))))
                                .addContainerGap(61, Short.MAX_VALUE)));
        Panel_DanhSachPM14Layout.setVerticalGroup(
                Panel_DanhSachPM14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(Panel_DanhSachPM14Layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(K_tieuDe15, javax.swing.GroupLayout.PREFERRED_SIZE, 46,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(Panel_DanhSachPM14Layout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txt_timkiemDMSach24, javax.swing.GroupLayout.PREFERRED_SIZE, 37,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(gioitinhnam17)
                                        .addComponent(gioitinhnu17))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addGroup(Panel_DanhSachPM14Layout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnK_themPM32)
                                        .addComponent(btnK_themPM31))
                                .addGap(30, 30, 30)));

        jTPK_QuanLyPM4.addTab("Danh sách phiếu mượn", Panel_DanhSachPM14);

        Panel_DanhSachPM15.setBackground(new java.awt.Color(255, 255, 204));
        Panel_DanhSachPM15.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        K_tieuDe16.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        K_tieuDe16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/bill.png"))); // NOI18N
        K_tieuDe16.setText("DANH SÁCH CÁC PHIẾU TRẢ");

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
                new Object[][] {
                        {},
                        {},
                        {},
                        {}
                },
                new String[] {

                }));
        jScrollPane1.setViewportView(table_PhieuTra);

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

        javax.swing.GroupLayout Panel_DanhSachPM15Layout = new javax.swing.GroupLayout(Panel_DanhSachPM15);
        Panel_DanhSachPM15.setLayout(Panel_DanhSachPM15Layout);
        Panel_DanhSachPM15Layout.setHorizontalGroup(
                Panel_DanhSachPM15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(Panel_DanhSachPM15Layout.createSequentialGroup()
                                .addGroup(Panel_DanhSachPM15Layout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(Panel_DanhSachPM15Layout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(Panel_DanhSachPM15Layout.createSequentialGroup()
                                                        .addGap(923, 923, 923)
                                                        .addComponent(btnChiTietView))
                                                .addGroup(Panel_DanhSachPM15Layout.createSequentialGroup()
                                                        .addGap(340, 340, 340)
                                                        .addComponent(K_tieuDe16,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 432,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(Panel_DanhSachPM15Layout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(Panel_DanhSachPM15Layout.createSequentialGroup()
                                                        .addComponent(textboxsearch4,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 275,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(
                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jComboBox11,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 133,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        1041, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(45, Short.MAX_VALUE)));
        Panel_DanhSachPM15Layout.setVerticalGroup(
                Panel_DanhSachPM15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(Panel_DanhSachPM15Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(K_tieuDe16, javax.swing.GroupLayout.PREFERRED_SIZE, 46,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60,
                                        Short.MAX_VALUE)
                                .addGroup(Panel_DanhSachPM15Layout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(textboxsearch4, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jComboBox11, javax.swing.GroupLayout.PREFERRED_SIZE, 33,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 245,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(btnChiTietView)));

        jTPK_QuanLyPM4.addTab("Danh sách phiếu trả", Panel_DanhSachPM15);

        javax.swing.GroupLayout jPK_QuanLyPhieuMuon4Layout = new javax.swing.GroupLayout(jPK_QuanLyPhieuMuon4);
        jPK_QuanLyPhieuMuon4.setLayout(jPK_QuanLyPhieuMuon4Layout);
        jPK_QuanLyPhieuMuon4Layout.setHorizontalGroup(
                jPK_QuanLyPhieuMuon4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTPK_QuanLyPM4, javax.swing.GroupLayout.DEFAULT_SIZE, 1126, Short.MAX_VALUE));
        jPK_QuanLyPhieuMuon4Layout.setVerticalGroup(
                jPK_QuanLyPhieuMuon4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTPK_QuanLyPM4, javax.swing.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE));

        jTP_main2.addTab(" QUẢN LÝ MƯỢN TRẢ ",
                new javax.swing.ImageIcon(getClass().getResource("/Images/exchange.png")), jPK_QuanLyPhieuMuon4); // NOI18N

        jPanel35.setBackground(new java.awt.Color(204, 204, 255));

        jTabbedPane7.setForeground(new java.awt.Color(0, 0, 102));
        jTabbedPane7.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N

        jPanel36.setBackground(new java.awt.Color(255, 255, 204));
        jPanel36.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel118.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel118.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/book.png"))); // NOI18N
        jLabel118.setText("Lựa chọn: ");

        cbb_chucNangThongKe6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        cbb_chucNangThongKe6.setForeground(new java.awt.Color(0, 0, 153));
        cbb_chucNangThongKe6.setModel(new javax.swing.DefaultComboBoxModel<>(
                new String[] { "Bạn đọc chưa trả sách", "Bạn đọc mượn quá hạn", "Bạn đọc mượn nhiều nhất" }));
        cbb_chucNangThongKe6.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbb_chucNangThongKe6ItemStateChanged(evt);
            }
        });

        tabletkbandoc2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        tabletkbandoc2.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {

                },
                new String[] {

                }));
        jScrollPane21.setViewportView(tabletkbandoc2);

        jLabel119.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel119.setForeground(new java.awt.Color(204, 0, 0));
        jLabel119.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/open-book.png"))); // NOI18N
        jLabel119.setText("Lựa chọn thông tin bạn muốn thống kê!");

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
                jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel36Layout.createSequentialGroup()
                                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel36Layout.createSequentialGroup()
                                                .addGap(146, 146, 146)
                                                .addComponent(jLabel118)
                                                .addGap(26, 26, 26)
                                                .addComponent(cbb_chucNangThongKe6,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 308,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel36Layout.createSequentialGroup()
                                                .addGap(54, 54, 54)
                                                .addGroup(jPanel36Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane21,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 956,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel119, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                363, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        jPanel36Layout.setVerticalGroup(
                jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel36Layout.createSequentialGroup()
                                .addGap(91, 91, 91)
                                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel118, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbb_chucNangThongKe6, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 263,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(91, 91, 91)
                                .addComponent(jLabel119)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        jTabbedPane7.addTab("Thông Kê Bạn Đọc", jPanel36);

        jPanel37.setBackground(new java.awt.Color(255, 255, 204));

        labelSoluongthongkeSach.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        labelSoluongthongkeSach.setText("Số lượng:");

        cbb_chucNangThongKe7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        cbb_chucNangThongKe7.setForeground(new java.awt.Color(0, 0, 153));
        cbb_chucNangThongKe7.setModel(new javax.swing.DefaultComboBoxModel<>(
                new String[] { "Số lượng sách còn", "Số lượng sách hỏng", "Tổng số lượng" }));
        cbb_chucNangThongKe7.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbb_chucNangThongKe7ItemStateChanged(evt);
            }
        });

        jLabel121.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel121.setForeground(new java.awt.Color(204, 0, 0));
        jLabel121.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/open-book.png"))); // NOI18N
        jLabel121.setText("Lựa chọn thông tin bạn muốn thống kê!");

        jLabel128.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel128.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/book.png"))); // NOI18N
        jLabel128.setText("Lựa chọn:");

        fieldSoluongthongkesach.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        tabletksach2.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null }
                },
                new String[] {
                        "STT", "Mã sách", "Tên sách", "Số lượng"
                }));
        jScrollPane5.setViewportView(tabletksach2);

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
                jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel37Layout.createSequentialGroup()
                                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel37Layout.createSequentialGroup()
                                                .addGap(134, 134, 134)
                                                .addComponent(jLabel121, javax.swing.GroupLayout.PREFERRED_SIZE, 363,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel37Layout.createSequentialGroup()
                                                .addGap(111, 111, 111)
                                                .addGroup(jPanel37Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel37Layout.createSequentialGroup()
                                                                .addComponent(jLabel128)
                                                                .addGap(22, 22, 22)
                                                                .addComponent(cbb_chucNangThongKe7,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 409,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(labelSoluongthongkeSach)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(fieldSoluongthongkesach,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 80,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(jScrollPane5,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 833,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(182, Short.MAX_VALUE)));
        jPanel37Layout.setVerticalGroup(
                jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel37Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cbb_chucNangThongKe7, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel128, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelSoluongthongkeSach, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(fieldSoluongthongkesach))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 275,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50,
                                        Short.MAX_VALUE)
                                .addComponent(jLabel121)
                                .addGap(96, 96, 96)));

        jTabbedPane7.addTab("Thống Kê Sách", jPanel37);

        jPanel38.setBackground(new java.awt.Color(255, 255, 204));

        jLabel122.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel122.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/book.png"))); // NOI18N
        jLabel122.setText("Lựa chọn:");

        cbb_chucNangThongKe8.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        cbb_chucNangThongKe8.setForeground(new java.awt.Color(0, 0, 153));
        cbb_chucNangThongKe8
                .setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tổng tiền phạt theo tháng" }));

        tabletktienphat2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        tabletktienphat2.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {

                },
                new String[] {

                }));
        jScrollPane23.setViewportView(tabletktienphat2);

        jLabel123.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel123.setForeground(new java.awt.Color(204, 0, 0));
        jLabel123.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/open-book.png"))); // NOI18N
        jLabel123.setText("Lựa chọn thông tin bạn muốn thống kê!");

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
                jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel38Layout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel123, javax.swing.GroupLayout.PREFERRED_SIZE, 363,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, 940,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel38Layout.createSequentialGroup()
                                                .addComponent(jLabel122)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(cbb_chucNangThongKe8,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 352,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        jPanel38Layout.setVerticalGroup(
                jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel38Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel122, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbb_chucNangThongKe8, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, 260,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel123)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        jTabbedPane7.addTab("Thông Kê Tiền Phạt", jPanel38);

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
                jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTabbedPane7));
        jPanel35Layout.setVerticalGroup(
                jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTabbedPane7));

        jTP_main2.addTab("                   THỐNG KÊ ",
                new javax.swing.ImageIcon(getClass().getResource("/Images/statistics.png")), jPanel35); // NOI18N

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
        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tìm theo danh mục",
                "Chuyên ngành Điện-Điện tử", "Chuyên ngành Cơ khí", "Chuyên ngành Công nghệ thông tin",
                "Chuyên ngành Xây dựng", "Sách Tiếng Anh", "Kỹ năng sống" }));
        jComboBox7.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox7ItemStateChanged(evt);
            }
        });

        jComboBox8.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tìm theo thể loại", "Giáo trình học",
                "Sách tham khảo", "Văn hóa lịch sử", "Chính trị, Pháp luật", "Tạp chí" }));
        jComboBox8.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox8ItemStateChanged(evt);
            }
        });

        tableSearchSach2.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        tableSearchSach2.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {

                },
                new String[] {
                        "STT", "Tên sách", "Tác giả", "Nhà sản xuất", "Số lượng"
                }));
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
                                                .addGroup(timkiem2Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel126)
                                                        .addComponent(jLabel125, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel124))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(timkiem2Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(timkiem2Layout.createSequentialGroup()
                                                                .addComponent(textboxsearch2,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 353,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jButton13,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 134,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(timkiem2Layout
                                                                .createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.TRAILING,
                                                                        false)
                                                                .addComponent(jComboBox8,
                                                                        javax.swing.GroupLayout.Alignment.LEADING, 0,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        Short.MAX_VALUE)
                                                                .addComponent(jComboBox7,
                                                                        javax.swing.GroupLayout.Alignment.LEADING,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 247,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(0, 381, Short.MAX_VALUE)))
                                .addContainerGap()));
        timkiem2Layout.setVerticalGroup(
                timkiem2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(timkiem2Layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addGroup(timkiem2Layout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, 34,
                                                Short.MAX_VALUE)
                                        .addComponent(textboxsearch2)
                                        .addComponent(jLabel124, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(36, 36, 36)
                                .addGroup(timkiem2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel125)
                                        .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(timkiem2Layout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(timkiem2Layout.createSequentialGroup()
                                                .addGap(27, 27, 27)
                                                .addComponent(jLabel126))
                                        .addGroup(timkiem2Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(jComboBox8)))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane24, javax.swing.GroupLayout.PREFERRED_SIZE, 386,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
                jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 1126, Short.MAX_VALUE)
                        .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(timkiem2, javax.swing.GroupLayout.Alignment.TRAILING,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        Short.MAX_VALUE)));
        jPanel39Layout.setVerticalGroup(
                jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 624, Short.MAX_VALUE)
                        .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(timkiem2, javax.swing.GroupLayout.Alignment.TRAILING,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        Short.MAX_VALUE)));

        jTP_main2.addTab("                      TRA CỨU",
                new javax.swing.ImageIcon(getClass().getResource("/Images/research.png")), jPanel39); // NOI18N

        jPK_QuanLyTaiKhoan.setBackground(new java.awt.Color(204, 204, 255));
        jPK_QuanLyTaiKhoan.setPreferredSize(new java.awt.Dimension(1000, 704));

        timkiem4.setBackground(new java.awt.Color(255, 255, 204));

        tableSearchSach7.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        tableSearchSach7.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {

                },
                new String[] {
                        "Mã độc giả", "Tên độc giả", "Mật khẩu", "Trạng thái", "Hạn dùng", "Phí duy trì" }));
        jScrollPane30.setViewportView(tableSearchSach7);

        themmoidg6.setBackground(new java.awt.Color(255, 204, 204));
        themmoidg6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        themmoidg6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/plus.png"))); // NOI18N
        themmoidg6.setText("Thêm");
        themmoidg6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themmoidg6ActionPerformed(evt);
            }
        });

        updatedg6.setBackground(new java.awt.Color(255, 204, 204));
        updatedg6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        updatedg6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/exchange1.png"))); // NOI18N
        updatedg6.setText("Sửa ");
        updatedg6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatedg6ActionPerformed(evt);
            }
        });

        khoatk18.setBackground(new java.awt.Color(255, 204, 204));
        khoatk18.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        khoatk18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/eraser.png"))); // NOI18N
        khoatk18.setText("Làm mới");
        khoatk18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                khoatk18ActionPerformed(evt);
            }
        });

        khoatk19.setBackground(new java.awt.Color(255, 204, 204));
        khoatk19.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        khoatk19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/password.png"))); // NOI18N
        khoatk19.setText("Khóa");
        khoatk19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                khoatk19ActionPerformed(evt);
            }
        });

        khoatk20.setBackground(new java.awt.Color(255, 204, 204));
        khoatk20.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        khoatk20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/man.png"))); // NOI18N
        khoatk20.setText("Mở Khóa");
        khoatk20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                khoatk20ActionPerformed(evt);
            }
        });

        textboxsearch16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textboxsearch16ActionPerformed(evt);
            }
        });
        textboxsearch16.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textboxsearch16KeyReleased(evt);
            }

            public void keyTyped(java.awt.event.KeyEvent evt) {
                textboxsearch16KeyTyped(evt);
            }
        });

        jLabel180.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel180.setText("Ngày sinh:");

        jLabel179.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel179.setText("Giới tính:");

        gioitinhnam22.setBackground(new java.awt.Color(255, 255, 204));
        buttonGroup2.add(gioitinhnam22);
        gioitinhnam22.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        gioitinhnam22.setText("Nam");

        gioitinhnu22.setBackground(new java.awt.Color(255, 255, 204));
        buttonGroup2.add(gioitinhnu22);
        gioitinhnu22.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        gioitinhnu22.setText("Nữ");

        Hc_maTheLoai6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        Hc_maTheLoai6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Hc_maTheLoai6ActionPerformed(evt);
            }
        });

        jLabel175.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel175.setText("Tên quản lý:");

        jLabel174.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel174.setText("Mật khẩu:");

        jLabel173.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel173.setText("Mã độc giả:");

        textboxsearch14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textboxsearch14ActionPerformed(evt);
            }
        });
        textboxsearch14.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textboxsearch14KeyReleased(evt);
            }

            public void keyTyped(java.awt.event.KeyEvent evt) {
                textboxsearch14KeyTyped(evt);
            }
        });

        textboxsearch15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textboxsearch15ActionPerformed(evt);
            }
        });
        textboxsearch15.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textboxsearch15KeyReleased(evt);
            }

            public void keyTyped(java.awt.event.KeyEvent evt) {
                textboxsearch15KeyTyped(evt);
            }
        });

        jLabel176.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel176.setText("SDT:");

        jLabel181.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel181.setText("Địa chỉ:");

        jLabel177.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel177.setText("Email:");

        tenDocGiaField5.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        tenDocGiaField6.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        matKhauField3.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

        javax.swing.GroupLayout timkiem4Layout = new javax.swing.GroupLayout(timkiem4);
        timkiem4.setLayout(timkiem4Layout);
        timkiem4Layout.setHorizontalGroup(
                timkiem4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(timkiem4Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(timkiem4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(timkiem4Layout.createSequentialGroup()
                                                .addComponent(jScrollPane30)
                                                .addGap(25, 25, 25))
                                        .addGroup(timkiem4Layout.createSequentialGroup()
                                                .addGroup(timkiem4Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                false)
                                                        .addGroup(timkiem4Layout.createSequentialGroup()
                                                                .addComponent(themmoidg6)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(updatedg6,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 111,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(khoatk18)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(khoatk19,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 121,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                                        .addGroup(timkiem4Layout.createSequentialGroup()
                                                                .addGroup(timkiem4Layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(timkiem4Layout.createSequentialGroup()
                                                                                .addComponent(jLabel179)
                                                                                .addGap(112, 112, 112)
                                                                                .addComponent(gioitinhnam22)
                                                                                .addGap(42, 42, 42)
                                                                                .addComponent(gioitinhnu22))
                                                                        .addGroup(timkiem4Layout.createSequentialGroup()
                                                                                .addGroup(timkiem4Layout
                                                                                        .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(jLabel174)
                                                                                        .addComponent(jLabel175)
                                                                                        .addComponent(jLabel173)
                                                                                        .addComponent(jLabel180))
                                                                                .addGap(47, 47, 47)
                                                                                .addGroup(timkiem4Layout
                                                                                        .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(textboxsearch16,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                250,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addGroup(timkiem4Layout
                                                                                                .createParallelGroup(
                                                                                                        javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                        false)
                                                                                                .addComponent(
                                                                                                        textboxsearch15,
                                                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                .addComponent(
                                                                                                        Hc_maTheLoai6,
                                                                                                        javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                        0, 250,
                                                                                                        Short.MAX_VALUE)
                                                                                                .addComponent(
                                                                                                        textboxsearch14)))))
                                                                .addGap(103, 103, 103)))
                                                .addGroup(timkiem4Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(timkiem4Layout.createSequentialGroup()
                                                                .addGap(60, 60, 60)
                                                                .addGroup(timkiem4Layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel181)
                                                                        .addComponent(jLabel176)
                                                                        .addComponent(jLabel177))
                                                                .addGap(74, 74, 74)
                                                                .addGroup(timkiem4Layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(tenDocGiaField5,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                250,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(tenDocGiaField6,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                250,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(matKhauField3,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                250,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addComponent(khoatk20))
                                                .addGap(0, 114, Short.MAX_VALUE)))));
        timkiem4Layout.setVerticalGroup(
                timkiem4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(timkiem4Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(timkiem4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(textboxsearch14, javax.swing.GroupLayout.PREFERRED_SIZE, 36,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel173)
                                        .addComponent(jLabel176)
                                        .addComponent(matKhauField3, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(15, 15, 15)
                                .addGroup(timkiem4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, timkiem4Layout
                                                .createSequentialGroup()
                                                .addGap(3, 3, 3)
                                                .addGroup(timkiem4Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(textboxsearch15,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 33,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel181)
                                                        .addComponent(tenDocGiaField6,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(timkiem4Layout.createSequentialGroup()
                                                .addComponent(jLabel174)
                                                .addGap(9, 9, 9)))
                                .addGap(12, 12, 12)
                                .addGroup(timkiem4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, timkiem4Layout
                                                .createSequentialGroup()
                                                .addGap(3, 3, 3)
                                                .addGroup(timkiem4Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel177)
                                                        .addComponent(tenDocGiaField5,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(timkiem4Layout.createSequentialGroup()
                                                .addGroup(timkiem4Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel175)
                                                        .addComponent(Hc_maTheLoai6,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(6, 6, 6)))
                                .addGap(15, 15, 15)
                                .addGroup(timkiem4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel179)
                                        .addComponent(gioitinhnam22)
                                        .addComponent(gioitinhnu22))
                                .addGap(12, 12, 12)
                                .addGroup(timkiem4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel180)
                                        .addComponent(textboxsearch16, javax.swing.GroupLayout.PREFERRED_SIZE, 33,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(timkiem4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(themmoidg6, javax.swing.GroupLayout.PREFERRED_SIZE, 41,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(updatedg6, javax.swing.GroupLayout.PREFERRED_SIZE, 41,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(khoatk18, javax.swing.GroupLayout.PREFERRED_SIZE, 41,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(khoatk19, javax.swing.GroupLayout.PREFERRED_SIZE, 41,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(khoatk20, javax.swing.GroupLayout.PREFERRED_SIZE, 41,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane30, javax.swing.GroupLayout.PREFERRED_SIZE, 178,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(23, Short.MAX_VALUE)));

        javax.swing.GroupLayout jPK_QuanLyTaiKhoanLayout = new javax.swing.GroupLayout(jPK_QuanLyTaiKhoan);
        jPK_QuanLyTaiKhoan.setLayout(jPK_QuanLyTaiKhoanLayout);
        jPK_QuanLyTaiKhoanLayout.setHorizontalGroup(
                jPK_QuanLyTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 1126, Short.MAX_VALUE)
                        .addGroup(
                                jPK_QuanLyTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(timkiem4, javax.swing.GroupLayout.Alignment.TRAILING,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        jPK_QuanLyTaiKhoanLayout.setVerticalGroup(
                jPK_QuanLyTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 515, Short.MAX_VALUE)
                        .addGroup(
                                jPK_QuanLyTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(timkiem4, javax.swing.GroupLayout.Alignment.TRAILING,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        jTP_main2.addTab("QUẢN LÝ TÀI KHOẢN", new javax.swing.ImageIcon(getClass().getResource(
                "/Images/png-transparent-user-profile-computer-icons-login-user-avatars-thumbnail_pixian_ai.png")),
                jPK_QuanLyTaiKhoan); // NOI18N

        QuanLyKho.setBackground(new java.awt.Color(204, 204, 255));

        timkiem3.setBackground(new java.awt.Color(255, 255, 204));

        jLabel129.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel129.setForeground(new java.awt.Color(0, 0, 255));
        jLabel129.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/searching.png"))); // NOI18N
        jLabel129.setText("Tìm kiếm sách:");

        textboxsearch3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textboxsearch3ActionPerformed(evt);
            }
        });
        textboxsearch3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textboxsearch3KeyPressed(evt);
            }

            public void keyReleased(java.awt.event.KeyEvent evt) {
                textboxsearch3KeyReleased(evt);
            }

            public void keyTyped(java.awt.event.KeyEvent evt) {
                textboxsearch3KeyTyped(evt);
            }
        });

        jLabel130.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel130.setForeground(new java.awt.Color(0, 0, 102));
        jLabel130.setText("Mã sách:");

        jLabel133.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel133.setForeground(new java.awt.Color(0, 0, 102));
        jLabel133.setText("Tổng số lượng sách:");

        jComboBox10.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jComboBox10.setModel(new javax.swing.DefaultComboBoxModel<>(
                new String[] { "Mã sách", "Tổng số lượng sách", "Tổng số sách còn lại", "Tổng số sách hỏng" }));
        jComboBox10.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox10ItemStateChanged(evt);
            }
        });
        jComboBox10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox10ActionPerformed(evt);
            }
        });

        tableKho.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        tableKho.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {

                },
                new String[] {

                }));
        tableKho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableKhoMouseClicked(evt);
            }
        });
        jScrollPane26.setViewportView(tableKho);

        jLabel134.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel134.setForeground(new java.awt.Color(0, 0, 102));
        jLabel134.setText("Tên sách:");

        jLabel136.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel136.setForeground(new java.awt.Color(0, 0, 102));
        jLabel136.setText("Tổng sách còn lại:");

        jLabel143.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel143.setForeground(new java.awt.Color(0, 0, 102));
        jLabel143.setText("Tổng sách hỏng:");

        txtTenSach.setColumns(20);
        txtTenSach.setRows(5);
        jScrollPane4.setViewportView(txtTenSach);

        txtMaSach.setEditable(false);
        txtMaSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSachActionPerformed(evt);
            }
        });
        txtMaSach.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaSachKeyReleased(evt);
            }

            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMaSachKeyTyped(evt);
            }
        });

        updatedg7.setBackground(new java.awt.Color(255, 204, 204));
        updatedg7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        updatedg7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/exchange1.png"))); // NOI18N
        updatedg7.setText("Cập nhật");
        updatedg7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatedg7ActionPerformed(evt);
            }
        });

        radioTang.setBackground(new java.awt.Color(255, 255, 204));
        buttonGroup1.add(radioTang);
        radioTang.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        radioTang.setSelected(true);
        radioTang.setText("Tăng dần");
        radioTang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioTangActionPerformed(evt);
            }
        });

        radioGiam.setBackground(new java.awt.Color(255, 255, 204));
        buttonGroup1.add(radioGiam);
        radioGiam.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        radioGiam.setText("Giảm dần");
        radioGiam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioTangActionPerformed(evt);
            }
        });

        btnH_luuSach3.setBackground(new java.awt.Color(255, 204, 204));
        btnH_luuSach3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnH_luuSach3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/luu.png"))); // NOI18N
        btnH_luuSach3.setText("Quản lý phiếu xuất");
        btnH_luuSach3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnH_luuSach3ActionPerformed(evt);
            }
        });

        txtTongSL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongSLActionPerformed(evt);
            }
        });
        txtTongSL.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTongSLKeyReleased(evt);
            }

            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTongSLKeyTyped(evt);
            }
        });

        txtTongSLSachHong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongSLSachHongActionPerformed(evt);
            }
        });
        txtTongSLSachHong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTongSLSachHongKeyReleased(evt);
            }

            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTongSLSachHongKeyTyped(evt);
            }
        });

        txtTongSLCL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongSLCLActionPerformed(evt);
            }
        });
        txtTongSLCL.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTongSLCLKeyReleased(evt);
            }

            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTongSLCLKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout timkiem3Layout = new javax.swing.GroupLayout(timkiem3);
        timkiem3.setLayout(timkiem3Layout);
        timkiem3Layout.setHorizontalGroup(
                timkiem3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(timkiem3Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(timkiem3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(timkiem3Layout.createSequentialGroup()
                                                .addComponent(jLabel129)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(timkiem3Layout.createSequentialGroup()
                                                .addGroup(timkiem3Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane26)
                                                        .addGroup(timkiem3Layout.createSequentialGroup()
                                                                .addGroup(timkiem3Layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(timkiem3Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                false)
                                                                                .addGroup(timkiem3Layout
                                                                                        .createSequentialGroup()
                                                                                        .addComponent(jLabel130)
                                                                                        .addGap(18, 18, 18)
                                                                                        .addComponent(txtMaSach))
                                                                                .addGroup(timkiem3Layout
                                                                                        .createSequentialGroup()
                                                                                        .addGroup(timkiem3Layout
                                                                                                .createParallelGroup(
                                                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                .addComponent(jLabel133)
                                                                                                .addComponent(
                                                                                                        jLabel136))
                                                                                        .addPreferredGap(
                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                        .addGroup(timkiem3Layout
                                                                                                .createParallelGroup(
                                                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                .addComponent(txtTongSL,
                                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                        276,
                                                                                                        Short.MAX_VALUE)
                                                                                                .addComponent(
                                                                                                        txtTongSLCL))))
                                                                        .addGroup(timkiem3Layout.createSequentialGroup()
                                                                                .addComponent(textboxsearch3,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        275,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(updatedg7,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        147,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addGap(45, 45, 45)
                                                                .addGroup(timkiem3Layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(timkiem3Layout.createSequentialGroup()
                                                                                .addComponent(btnH_luuSach3)
                                                                                .addPreferredGap(
                                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                        30, Short.MAX_VALUE)
                                                                                .addComponent(jComboBox10,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        133,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(
                                                                                        javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(radioTang)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(radioGiam))
                                                                        .addGroup(timkiem3Layout.createSequentialGroup()
                                                                                .addGroup(timkiem3Layout
                                                                                        .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                false)
                                                                                        .addGroup(timkiem3Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(jLabel134)
                                                                                                .addPreferredGap(
                                                                                                        javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addComponent(
                                                                                                        jScrollPane4,
                                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                        412,
                                                                                                        Short.MAX_VALUE))
                                                                                        .addGroup(timkiem3Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(jLabel143)
                                                                                                .addPreferredGap(
                                                                                                        javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addComponent(
                                                                                                        txtTongSLSachHong)))
                                                                                .addGap(0, 0, Short.MAX_VALUE)))))
                                                .addContainerGap()))));
        timkiem3Layout.setVerticalGroup(
                timkiem3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(timkiem3Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(timkiem3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(timkiem3Layout.createSequentialGroup()
                                                .addGroup(timkiem3Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel130)
                                                        .addComponent(jLabel134)
                                                        .addComponent(txtMaSach, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(timkiem3Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jLabel133)
                                                        .addComponent(txtTongSL, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 64,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(timkiem3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(timkiem3Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addGroup(timkiem3Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel136)
                                                        .addComponent(txtTongSLCL,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 35,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, timkiem3Layout
                                                .createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(timkiem3Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(txtTongSLSachHong,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 35,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel143))
                                                .addGap(11, 11, 11)))
                                .addComponent(jLabel129)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(timkiem3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(updatedg7, javax.swing.GroupLayout.PREFERRED_SIZE, 41,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, 33,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textboxsearch3, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(radioTang)
                                        .addComponent(radioGiam)
                                        .addComponent(btnH_luuSach3, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane26, javax.swing.GroupLayout.PREFERRED_SIZE, 248,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(17, 17, 17)));

        javax.swing.GroupLayout QuanLyKhoLayout = new javax.swing.GroupLayout(QuanLyKho);
        QuanLyKho.setLayout(QuanLyKhoLayout);
        QuanLyKhoLayout.setHorizontalGroup(
                QuanLyKhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 1126, Short.MAX_VALUE)
                        .addGroup(QuanLyKhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(timkiem3, javax.swing.GroupLayout.Alignment.TRAILING,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        Short.MAX_VALUE)));
        QuanLyKhoLayout.setVerticalGroup(
                QuanLyKhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 529, Short.MAX_VALUE)
                        .addGroup(QuanLyKhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(timkiem3, javax.swing.GroupLayout.Alignment.TRAILING,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        Short.MAX_VALUE)));

        jTP_main2.addTab("QUẢN LÝ KHO", new javax.swing.ImageIcon(
                getClass().getResource("/Images/94cca0bbb32505724cef0ad5c30465f1_pixian_ai.png")), QuanLyKho); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jTP_main2, javax.swing.GroupLayout.DEFAULT_SIZE, 1431, Short.MAX_VALUE)
                                .addContainerGap()));
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 24, Short.MAX_VALUE)
                                .addComponent(jTP_main2, javax.swing.GroupLayout.PREFERRED_SIZE, 515,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox10ItemStateChanged(java.awt.event.ItemEvent evt) {// GEN-FIRST:event_jComboBox10ItemStateChanged
        // TODO add your handling code here:
    }// GEN-LAST:event_jComboBox10ItemStateChanged

    private void textboxsearch3KeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_textboxsearch3KeyTyped
        // TODO add your handling code here:
    }// GEN-LAST:event_textboxsearch3KeyTyped

    private void textboxsearch3KeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_textboxsearch3KeyReleased
        String searchTerm = textboxsearch3.getText();
        loadkho(khobll.search(searchTerm));
    }// GEN-LAST:event_textboxsearch3KeyReleased

    private void textboxsearch3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_textboxsearch3ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_textboxsearch3ActionPerformed

    private void txtMaSachKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtMaSachKeyTyped
        // TODO add your handling code here:
    }// GEN-LAST:event_txtMaSachKeyTyped

    private void txtMaSachKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtMaSachKeyReleased

    }// GEN-LAST:event_txtMaSachKeyReleased

    private void txtMaSachActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtMaSachActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txtMaSachActionPerformed

    private void updatedg7ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_updatedg7ActionPerformed
        KhoSach newkho = new KhoSach();
        newkho.setMaSach(txtMaSach.getText());
        newkho.setTongSoLuong(Integer.parseInt(txtTongSL.getText()));
        newkho.setSoLuongCon(Integer.parseInt(txtTongSLCL.getText()));
        newkho.setSoLuongSachHong(Integer.parseInt(txtTongSLSachHong.getText()));
        if (khobll.update(newkho)) {
            JOptionPane.showMessageDialog(null, "Cập nhật thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Cập nhật thất bại!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

        }
        loadkho(khobll.loadKho());
    }// GEN-LAST:event_updatedg7ActionPerformed

    private void jComboBox10ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jComboBox10ActionPerformed

        if (radioTang.isSelected()) {
            // Thực hiện công việc khi radioNamAdmin được chọn
            loadkho(khobll.loadKho_into(jComboBox10.getSelectedItem().toString(), "ASC"));
        } else {
            // Thực hiện công việc khi radioNamAdmin không được chọn
            loadkho(khobll.loadKho_into(jComboBox10.getSelectedItem().toString(), "DESC"));
        }
    }// GEN-LAST:event_jComboBox10ActionPerformed

    private void btnH_luuSach3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnH_luuSach3ActionPerformed
        TrangChuThuThu_QLPXuat trangChuThuThu_QLPXuat = new TrangChuThuThu_QLPXuat();
        trangChuThuThu_QLPXuat.setVisible(true);
        this.dispose();
    }// GEN-LAST:event_btnH_luuSach3ActionPerformed

    private void tableKhoMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tableKhoMouseClicked
        int row = tableKho.getSelectedRow();
        txtMaSach.setText(tableKho.getValueAt(row, 0).toString());
        txtTenSach.setText(tableKho.getValueAt(row, 1).toString());
        txtTongSL.setText(tableKho.getValueAt(row, 2).toString());
        txtTongSLCL.setText(tableKho.getValueAt(row, 3).toString());
        txtTongSLSachHong.setText(tableKho.getValueAt(row, 4).toString());
    }// GEN-LAST:event_tableKhoMouseClicked

    private void txtTongSLActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtTongSLActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txtTongSLActionPerformed

    private void txtTongSLKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtTongSLKeyReleased
        // TODO add your handling code here:
    }// GEN-LAST:event_txtTongSLKeyReleased

    private void txtTongSLKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtTongSLKeyTyped
        // TODO add your handling code here:
    }// GEN-LAST:event_txtTongSLKeyTyped

    private void txtTongSLSachHongActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtTongSLSachHongActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txtTongSLSachHongActionPerformed

    private void txtTongSLSachHongKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtTongSLSachHongKeyReleased
        // TODO add your handling code here:
    }// GEN-LAST:event_txtTongSLSachHongKeyReleased

    private void txtTongSLSachHongKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtTongSLSachHongKeyTyped
        // TODO add your handling code here:
    }// GEN-LAST:event_txtTongSLSachHongKeyTyped

    private void txtTongSLCLActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtTongSLCLActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txtTongSLCLActionPerformed

    private void txtTongSLCLKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtTongSLCLKeyReleased
        // TODO add your handling code here:
    }// GEN-LAST:event_txtTongSLCLKeyReleased

    private void txtTongSLCLKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtTongSLCLKeyTyped
        // TODO add your handling code here:
    }// GEN-LAST:event_txtTongSLCLKeyTyped

    private void radioTangActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_radioTangActionPerformed
        if (jComboBox10.getSelectedItem() != null) {
            if (radioTang.isSelected()) {
                // Thực hiện công việc khi radioNamAdmin được chọn
                loadkho(khobll.loadKho_into(jComboBox10.getSelectedItem().toString(), "ASC"));
            } else {
                // Thực hiện công việc khi radioNamAdmin không được chọn
                loadkho(khobll.loadKho_into(jComboBox10.getSelectedItem().toString(), "DESC"));
            }
        }
    }// GEN-LAST:event_radioTangActionPerformed

    private void textboxsearch4ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_textboxsearch4ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_textboxsearch4ActionPerformed

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

    private void textboxsearch4KeyPressed(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_textboxsearch4KeyPressed
        // TODO add your handling code here:
    }// GEN-LAST:event_textboxsearch4KeyPressed

    private void textboxsearch3KeyPressed(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_textboxsearch3KeyPressed
        // TODO add your handling code here:
    }// GEN-LAST:event_textboxsearch3KeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
        TrangChuChinh trangChuChinh = new TrangChuChinh();
        trangChuChinh.setVisible(true);

        dispose();
    }// GEN-LAST:event_jButton1ActionPerformed

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

    // ======================== Tài Khoản Quản Lý Header ======================

    // sự kiện click bảng
    private void tableSearchSach7MouseClicked(java.awt.event.MouseEvent evt) {
        int lineSelect = tableSearchSach7.getSelectedRow();
        textboxsearch14.setText(tableSearchSach7.getValueAt(lineSelect, 0).toString().trim());
        textboxsearch15.setText(tableSearchSach7.getValueAt(lineSelect, 1).toString());
        Hc_maTheLoai6.setSelectedItem(tableSearchSach7.getValueAt(lineSelect, 2).toString());
        matKhauField3.setText(tableSearchSach7.getValueAt(lineSelect, 3).toString().trim());
        String gioiTinh = tableSearchSach7.getValueAt(lineSelect, 4).toString();
        if (gioiTinh.equals("Nam")) {
            gioitinhnam22.setSelected(true);
        } else
            gioitinhnu22.setSelected(true);
        textboxsearch16.setText(tableSearchSach7.getValueAt(lineSelect, 5).toString());
        tenDocGiaField5.setText(tableSearchSach7.getValueAt(lineSelect, 6).toString().trim());
        tenDocGiaField6.setText(tableSearchSach7.getValueAt(lineSelect, 7).toString());

        int trangThai = Integer.parseInt(tableSearchSach7.getValueAt(lineSelect, 8).toString());
        if (trangThai == 0) {
            khoatk20.setEnabled(true);
            khoatk19.setEnabled(false);
        } else {
            khoatk20.setEnabled(false);
            khoatk19.setEnabled(true);
        }
    }

    // load bảng tài khoản quản lý
    private void loadTaiKhoanQuanLy(JTable tb, DanhSachQuanLy dsql) {
        String[] nameColumn = { "Mã Quản Lý", "Mật Khẩu", "Tên Quản Lý", "Số Điện Thoại", "Giới Tính", "Ngày Sinh",
                "Email", "Địa Chỉ", "Trạng thái", "Quyền" };

        DefaultTableModel faut = new DefaultTableModel();
        for (String col : nameColumn) {
            faut.addColumn(col);
        }
        faut.setRowCount(0);
        for (QuanLy ql : dsql.getDsQuanLy()) {
            Vector t = new Vector<>();
            t.add(ql.getMaQuanly());
            t.add(ql.getMatKhau());
            t.add(ql.getTenQuanly());
            t.add(ql.getSDT());
            t.add(ql.getGioiTinh());
            t.add(ql.getNgaySinh());
            t.add(ql.getEmail());
            t.add(ql.getDiaChi());
            t.add(ql.getTrangThai());

            faut.addRow(t);
        }
        tb.setModel(faut);
    }

    // làm mới quản lý
    public void resetQuanLy() {
        textboxsearch14.setText("");
        Hc_maTheLoai3.setSelectedItem("Admin");
        textboxsearch15.setText("");
        // giới tính
        textboxsearch16.setText("");
        matKhauField3.setText("");
        tenDocGiaField5.setText("");
        tenDocGiaField6.setText("");

        khoatk19.setEnabled(true);
        khoatk20.setEnabled(true);

    }

    // thêm quản lý
    private void themmoidg6ActionPerformed(java.awt.event.ActionEvent evt) {
        if (textboxsearch14.getText().equals("") || textboxsearch15.getText().equals("")
                || matKhauField3.getText().equals("")) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Vui Lòng Nhập Đủ Thông Tin!");
        } else if (!new check().isDateValid(textboxsearch16.getText())) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),
                    "Vui Lòng Nhập Ngày Theo Định Dạng 'yyyy-mm-dd'!");
        } else if (!new QuanLy_DAO().checkMaQuanLy(textboxsearch14.getText())) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Mã Quản Lý Đã Tồn Tại!");
        } else if (!new check().isValidGmail(tenDocGiaField5.getText())) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),
                    "Vui Lòng Nhập Đúng Định Dạng Email!");
        } else if (!new check().isValidPhoneNumber(matKhauField3.getText())) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),
                    "Vui Lòng Nhập Đúng Định Dạng Số Điện Thoại!");
        } else {
            int x = JOptionPane.showConfirmDialog(JOptionPane.getRootFrame(), "Bạn Có Chắc Chắn Muốn Thêm Tài Khoản!");
            if (x == JOptionPane.NO_OPTION) {
                return;
            }
            QuanLy ql = new QuanLy();
            ql.setMaQuanly(textboxsearch14.getText());
            ql.setMatKhau(textboxsearch15.getText());
            ql.setTenQuanly(Hc_maTheLoai6.getSelectedItem().toString());
            if (gioitinhnam22.isSelected()) {
                ql.setGioiTinh("Nam");
            } else if (gioitinhnu22.isSelected()) {
                ql.setGioiTinh("Nữ");
            } else {
                ql.setGioiTinh("");
            }
            ql.setNgaySinh(textboxsearch16.getText());
            ql.setSDT(matKhauField3.getText());
            ql.setEmail(tenDocGiaField5.getText());
            ql.setDiaChi(tenDocGiaField6.getText());
            ql.setTrangThai(1);

            if (new QuanLy_DAO().themQuanLy(ql)) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Thêm Thành Công!");
                resetQuanLy();
                loadTaiKhoanQuanLy(tableSearchSach7, new DanhSachQuanLy(new QuanLy_DAO().dsAllTaiKhoan()));
            } else {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Thêm Lỗi");
            }
        }
    }

    // sửa quản lý
    private void updatedg6ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_updatedg6ActionPerformed
        if (textboxsearch14.getText().equals("") || textboxsearch15.getText().equals("")
                || matKhauField3.getText().equals("")) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Vui Lòng Nhập Đủ Thông Tin!");
        } else if (!new check().isDateValid(textboxsearch16.getText())) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),
                    "Vui Lòng Nhập Ngày Theo Định Dạng 'yyyy-mm-dd'!");
        } else if (new QuanLy_DAO().checkMaQuanLy(textboxsearch14.getText())) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Mã Quản Lý Không Tồn Tại!");
        } else if (!new check().isValidGmail(tenDocGiaField5.getText())) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),
                    "Vui Lòng Nhập Đúng Định Dạng Email!");
        } else if (!new check().isValidPhoneNumber(matKhauField3.getText())) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),
                    "Vui Lòng Nhập Đúng Định Dạng Số Điện Thoại!");
        } else {
            int x = JOptionPane.showConfirmDialog(JOptionPane.getRootFrame(), "Bạn Có Chắc Chắn Muốn Sửa Tài Khoản!");
            if (x == JOptionPane.NO_OPTION) {
                return;
            }
            QuanLy ql = new QuanLy();
            ql.setMaQuanly(textboxsearch14.getText());
            ql.setMatKhau(textboxsearch15.getText());
            ql.setTenQuanly(Hc_maTheLoai6.getSelectedItem().toString());
            if (gioitinhnam22.isSelected()) {
                ql.setGioiTinh("Nam");
            } else if (gioitinhnu22.isSelected()) {
                ql.setGioiTinh("Nữ");
            } else {
                ql.setGioiTinh("");
            }
            ql.setNgaySinh(textboxsearch16.getText());
            ql.setSDT(matKhauField3.getText());
            ql.setEmail(tenDocGiaField5.getText());
            ql.setDiaChi(tenDocGiaField6.getText());
            ql.setTrangThai(1);

            if (new QuanLy_DAO().suaQuanLy(ql)) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Sửa Thành Công!");
                resetQuanLy();
                loadTaiKhoanQuanLy(tableSearchSach7, new DanhSachQuanLy(new QuanLy_DAO().dsAllTaiKhoan()));

            } else {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Sửa Lỗi");
            }
        }
    }

    // làm mới quản lý
    private void khoatk18ActionPerformed(java.awt.event.ActionEvent evt) {
        resetQuanLy();
    }

    // khóa quản lý
    private void khoatk19ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_khoatk19ActionPerformed
        if (textboxsearch14.getText().equals("")) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Vui Lòng Nhập Mã Quản Lý!");
        } else if (new QuanLy_DAO().checkMaQuanLy(textboxsearch14.getText())) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Mã Quản Lý Không Tồn Tại!");
        } else {
            int x = JOptionPane.showConfirmDialog(JOptionPane.getRootFrame(), "Bạn Có Chắc Chắn Muốn Khóa Tài Khoản!");
            if (x == JOptionPane.NO_OPTION) {
                return;
            } else if (new QuanLy_DAO().khoaTKQL(textboxsearch14.getText())) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Khóa Thành Công!");
                resetQuanLy();
                loadTaiKhoanQuanLy(tableSearchSach7, new DanhSachQuanLy(new QuanLy_DAO().dsAllTaiKhoan()));
            } else {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Khóa Lỗi");
            }
        }
    }

    // mở khóa quản lý
    private void khoatk20ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_khoatk20ActionPerformed
        if (textboxsearch14.getText().equals("")) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Vui Lòng Nhập Mã Quản Lý!");
        } else if (new QuanLy_DAO().checkMaQuanLy(textboxsearch14.getText())) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Mã Quản Lý Không Tồn Tại!");
        } else {
            int x = JOptionPane.showConfirmDialog(JOptionPane.getRootFrame(),
                    "Bạn Có Chắc Chắn Muốn Mở Khóa Tài Khoản!");
            if (x == JOptionPane.NO_OPTION) {
                return;
            } else if (new QuanLy_DAO().moKhoa(textboxsearch14.getText())) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Mở Khóa Thành Công!");
                resetQuanLy();
                loadTaiKhoanQuanLy(tableSearchSach7, new DanhSachQuanLy(new QuanLy_DAO().dsAllTaiKhoan()));
            } else {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Mở Khóa Lỗi");
            }
        }
    }

    // ======================== Tài Khoản Quản Lý end ======================

    /*
     * -----------------------------------------------------------------------------
     */

    // ======================== Loại Thẻ start ======================

    // click bảng phân loại thẻ
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
    private void khoatk9ActionPerformed(java.awt.event.ActionEvent evt) {
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

    // ======================== Độc giả end ======================

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

    private void btnH_themSach2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnH_themSach2ActionPerformed
        // TODO add your handling code here:
        if (H_tenSach3.getText().trim().equals("")
                || H_tenTheLoai2.getText().trim().equals("") || H_tenDM2.getText().trim().equals("")
                || H_soLuongCon2.getText().trim().equals("") || H_tacGia4.getText().trim().equals("") ) {
            JOptionPane.showMessageDialog(null, "Bạn chưa nhập đủ thông tin!");
        } else {
            int x = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn thêm không?");
            if (x == JOptionPane.NO_OPTION) {
                return;
            } else {
                Sach sach = new Sach();
                sach.setMaSach(H_tenSach2.getText());
                KhoSach khoSach = KhoSach_DAO.getInstance().selectById(H_tenSach2.getText());
                if (khoSach.getMaSach().equals("")){
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
                if (tacGia.getMaTacGia().equals("")){
                    if (H_tacGia5.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "Mã tác giả mới, vui lòng nhập tên tác giả!");
                    }else {
//                        tacGia.setMaTacGia(H_tacGia4.getText());
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
                if (Sach_DAO.getInstance().update(sach)>0){
                    JOptionPane.showMessageDialog(null, "Sửa sách thành công!");
                }else{
                    JOptionPane.showMessageDialog(null, "Sửa sách thất bại!");
                }
                // defaultTableModel_S.setRowCount(0);
                // setTableData_S(s_Service.getDSSach());
            }
        }
        loadThongTinSach();
    }// GEN-LAST:event_btnH_suaSach2ActionPerformed

    private void btnH_luuSach2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnH_luuSach2ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_btnH_luuSach2ActionPerformed

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
    }// GEN-LAST:event_Hc_maTheLoai2ActionPerformed

    private void Hc_maDM2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_Hc_maDM2ActionPerformed
        // TODO add your handling code here:
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
        new TrangChuThuThu_TimKiem(1).setVisible(true);
        this.setVisible(false);
    }// GEN-LAST:event_khoatk11ActionPerformed

    private void H_tenSach3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_H_tenSach3ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_H_tenSach3ActionPerformed

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
    }// GEN-LAST:event_tbl_DMSach5MouseClicked

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
        new TrangChuThuThu_QLPM_1(1).setVisible(true);
        this.setVisible(false);
    }// GEN-LAST:event_btnK_themPM32btnK_themPM2ActionPerformed

    private void btnK_themPM33btnK_themPM1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnK_themPM33btnK_themPM1ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_btnK_themPM33btnK_themPM1ActionPerformed

    private void btnK_themPM34btnK_themPM2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnK_themPM34btnK_themPM2ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_btnK_themPM34btnK_themPM2ActionPerformed

    private void H_tenSach5ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_H_tenSach5ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_H_tenSach5ActionPerformed

    private void H_tenSach6ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_H_tenSach6ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_H_tenSach6ActionPerformed

    private void H_tenSach8ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_H_tenSach8ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_H_tenSach8ActionPerformed

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
    }// GEN-LAST:event_textboxsearch2KeyReleased

    private void textboxsearch2KeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_textboxsearch2KeyTyped
        // TODO add your handling code here:
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

    private void textboxsearch16ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_textboxsearch16ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_textboxsearch16ActionPerformed

    private void textboxsearch16KeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_textboxsearch16KeyReleased
        // TODO add your handling code here:
    }// GEN-LAST:event_textboxsearch16KeyReleased

    private void textboxsearch16KeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_textboxsearch16KeyTyped
        // TODO add your handling code here:
    }// GEN-LAST:event_textboxsearch16KeyTyped

    private void Hc_maTheLoai6ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_Hc_maTheLoai6ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_Hc_maTheLoai6ActionPerformed

    private void textboxsearch14ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_textboxsearch14ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_textboxsearch14ActionPerformed

    private void textboxsearch14KeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_textboxsearch14KeyReleased
        // TODO add your handling code here:
    }// GEN-LAST:event_textboxsearch14KeyReleased

    private void textboxsearch14KeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_textboxsearch14KeyTyped
        // TODO add your handling code here:
    }// GEN-LAST:event_textboxsearch14KeyTyped

    private void textboxsearch15ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_textboxsearch15ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_textboxsearch15ActionPerformed

    private void textboxsearch15KeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_textboxsearch15KeyReleased
        // TODO add your handling code here:
    }// GEN-LAST:event_textboxsearch15KeyReleased

    private void textboxsearch15KeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_textboxsearch15KeyTyped
        // TODO add your handling code here:
    }// GEN-LAST:event_textboxsearch15KeyTyped

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jButton14ActionPerformed

    private void jComboBox9ItemStateChanged(java.awt.event.ItemEvent evt) {// GEN-FIRST:event_jComboBox9ItemStateChanged
        // TODO add your handling code here:
    }// GEN-LAST:event_jComboBox9ItemStateChanged

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
            java.util.logging.Logger.getLogger(TrangChuAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TrangChuAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TrangChuAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TrangChuAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TrangChuAdmin().setVisible(true);
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
    private javax.swing.JComboBox<String> Hc_maTheLoai6;
    private javax.swing.JLabel K_tieuDe15;
    private javax.swing.JLabel K_tieuDe16;
    private javax.swing.JPanel Panel_DanhSachPM14;
    private javax.swing.JPanel Panel_DanhSachPM15;
    private javax.swing.JTable QLTGiaTable;
    private javax.swing.JPanel QuanLyKho;
    private javax.swing.JButton btnChiTietView;
    private javax.swing.JButton btnH_luuSach2;
    private javax.swing.JButton btnH_luuSach3;
    private javax.swing.JButton btnH_suaSach2;
    private javax.swing.JButton btnH_themSach2;
    private javax.swing.JButton btnK_themPM31;
    private javax.swing.JButton btnK_themPM32;
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
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JComboBox<String> cbb_chucNangThongKe6;
    private javax.swing.JComboBox<String> cbb_chucNangThongKe7;
    private javax.swing.JComboBox<String> cbb_chucNangThongKe8;
    private javax.swing.JTextField emailDocgia4;
    private javax.swing.JTextField fieldSoluongthongkesach;
    private javax.swing.JRadioButton gioitinhnam16;
    private javax.swing.JRadioButton gioitinhnam17;
    private javax.swing.JRadioButton gioitinhnam18;
    private javax.swing.JRadioButton gioitinhnam22;
    private javax.swing.JRadioButton gioitinhnu16;
    private javax.swing.JRadioButton gioitinhnu17;
    private javax.swing.JRadioButton gioitinhnu18;
    private javax.swing.JRadioButton gioitinhnu22;
    private javax.swing.JTextField hanDungField1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton13;
    private javax.swing.JComboBox<String> jComboBox10;
    private javax.swing.JComboBox<String> jComboBox11;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JComboBox<String> jComboBox8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel139;
    private javax.swing.JLabel jLabel141;
    private javax.swing.JLabel jLabel142;
    private javax.swing.JLabel jLabel143;
    private javax.swing.JLabel jLabel173;
    private javax.swing.JLabel jLabel174;
    private javax.swing.JLabel jLabel175;
    private javax.swing.JLabel jLabel176;
    private javax.swing.JLabel jLabel177;
    private javax.swing.JLabel jLabel179;
    private javax.swing.JLabel jLabel180;
    private javax.swing.JLabel jLabel181;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JPanel jPK_QuanLyTaiKhoan;
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
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JScrollPane jScrollPane26;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane30;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JTabbedPane jTPK_QuanLyPM4;
    private javax.swing.JTabbedPane jTP_main2;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTabbedPane jTabbedPane7;
    private javax.swing.JTable jTable2;
    private javax.swing.JButton khoatk10;
    private javax.swing.JButton khoatk11;
    private javax.swing.JButton khoatk18;
    private javax.swing.JButton khoatk19;
    private javax.swing.JButton khoatk20;
    private javax.swing.JButton khoatk8;
    private javax.swing.JButton khoatk9;
    private javax.swing.JLabel labelSoluongthongkeSach;
    private javax.swing.JTextField maDocGiaField;
    private javax.swing.JTextField maLoaiField;
    private javax.swing.JTextField matKhauField;
    private javax.swing.JTextField matKhauField3;
    private javax.swing.JButton mokhoa3;
    private javax.swing.JButton mokhoa4;
    private javax.swing.JTextField ngayMotheField;
    private javax.swing.JLabel ngayMotheLabel;
    private javax.swing.JTextField ngaysinh2;
    private javax.swing.JTabbedPane quanlyttdg2;
    private javax.swing.JRadioButton radioGiam;
    private javax.swing.JRadioButton radioTang;
    private javax.swing.JTextField sdt2;
    private javax.swing.JTextField soLuongField;
    private javax.swing.JTextField soLuongmuonField;
    private javax.swing.JLabel soLuongmuonLabel;
    private javax.swing.JTable tableDocgia2;
    private javax.swing.JTable tableDocgia3;
    private javax.swing.JTable tableKho;
    private javax.swing.JTable tableSearchSach2;
    private javax.swing.JTable tableSearchSach7;
    private javax.swing.JTable table_PhieuTra;
    private javax.swing.JTable tabletkbandoc2;
    private javax.swing.JTable tabletksach2;
    private javax.swing.JTable tabletktienphat2;
    private javax.swing.JTable tblH_Sach2;
    private javax.swing.JTable tbl_DMSach4;
    private javax.swing.JTable tbl_DMSach5;
    private javax.swing.JTextField tenDocGiaField;
    private javax.swing.JTextField tenDocGiaField5;
    private javax.swing.JTextField tenDocGiaField6;
    private javax.swing.JTextField tenLoaiField;
    private javax.swing.JTextField tenLoaiField2;
    private javax.swing.JTextField textboxsearch14;
    private javax.swing.JTextField textboxsearch15;
    private javax.swing.JTextField textboxsearch16;
    private javax.swing.JTextField textboxsearch2;
    private javax.swing.JTextField textboxsearch3;
    private javax.swing.JTextField textboxsearch4;
    private javax.swing.JButton themmoidg2;
    private javax.swing.JButton themmoidg3;
    private javax.swing.JButton themmoidg6;
    private javax.swing.JTextField thoiGianField;
    private javax.swing.JTextField thoiGianField1;
    private javax.swing.JPanel timkiem2;
    private javax.swing.JPanel timkiem3;
    private javax.swing.JPanel timkiem4;
    private javax.swing.JTextField txtMaSach;
    private javax.swing.JTextArea txtTenSach;
    private javax.swing.JTextField txtTongSL;
    private javax.swing.JTextField txtTongSLCL;
    private javax.swing.JTextField txtTongSLSachHong;
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
    private javax.swing.JButton updatedg2;
    private javax.swing.JButton updatedg3;
    private javax.swing.JButton updatedg6;
    private javax.swing.JButton updatedg7;
    // End of variables declaration//GEN-END:variables
}
