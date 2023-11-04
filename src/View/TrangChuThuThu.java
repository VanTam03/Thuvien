
package View;

import DAO.*;
import DTO.*;
import Model.DanhSachLoaiThe;
import Model.DanhSachTaiKhoan;
import Model.PhanLoaiThe;
import Model.TaiKhoan;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

//import jdk.nashorn.internal.parser.TokenType;
/**
 *
 * @author KHP2T
 */
public class TrangChuThuThu extends javax.swing.JFrame {
        DefaultTableModel defaultTableModel_DM;
        DefaultTableModel defaultTableModel_TL;
        DefaultTableModel defaultTableModel_TG;

        public TrangChuThuThu() {
                initComponents();
                loadmaDanhMuc();
                loadComboBoxTheLoai();
                loadComboBoxDanhMuc();
                loadmaTheLoai();
                // loadmaTacGia();
        }

        private void loadComboBoxDanhMuc() {
                List<DanhMucSach> DanhMuc = DanhMucSach_DAO.getInstance().selectAll();
                for (DanhMucSach dm : DanhMuc) {
                        Hc_maDM2.addItem(dm.getMaDM());
                }
        }

        private void loadComboBoxTheLoai() {
                List<PhanLoaiSach> theLoais = PhanLoaiSach_DAO.getInstance().selectTheLoaiAll();
                for (PhanLoaiSach tl : theLoais) {
                        Hc_maTheLoai2.addItem(tl.getMaTheloai());
                }
        }

        // public void loadmaTacGia(){
        // defaultTableModel_TG = new DefaultTableModel(){
        // @Override
        // public boolean isCellEditable(int row, int column){
        // return false;
        // }
        // };
        // // tbl_DMSach4.setModel(defaultTableModel_DM);
        // defaultTableModel_TG.addColumn("Mã tác giả");
        // defaultTableModel_TG.addColumn("Tên tác giả");
        // defaultTableModel_TG.addColumn("Số lượng sách");
        // List <TacGia> tacGia = TacGia_DAO.getInstance().selectAll();
        // for (TacGia tg : tacGia){
        // defaultTableModel_TG.addRow(new Object[]{tg.getMaTacGia(), tg.getTenTacGia(),
        // tg.getSoSach()});
        // }
        // }

        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                gioitinhbtngroup = new javax.swing.ButtonGroup();
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
                Hc_maTheLoai3 = new javax.swing.JComboBox<>();
                jLabel127 = new javax.swing.JLabel();
                jLabel128 = new javax.swing.JLabel();
                emailDocgia3 = new javax.swing.JTextField();
                tenDocGiaField = new javax.swing.JTextField();
                maDocGiaField = new javax.swing.JTextField();
                hanDungField = new javax.swing.JTextField();
                jLabel143 = new javax.swing.JLabel();
                timKiemDG = new javax.swing.JTextField();
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
                jLabel138 = new javax.swing.JLabel();
                tenLoaiField1 = new javax.swing.JTextField();
                jLabel140 = new javax.swing.JLabel();
                tenLoaiField2 = new javax.swing.JTextField();
                jLabel141 = new javax.swing.JLabel();
                thoiGianField1 = new javax.swing.JTextField();
                tenLoaiField3 = new javax.swing.JTextField();
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
                jScrollPane26 = new javax.swing.JScrollPane();
                AuthManagerTable = new javax.swing.JTable();
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
                btnK_themPM33 = new javax.swing.JButton();
                txt_timkiemDMSach25 = new javax.swing.JTextField();
                btnK_themPM34 = new javax.swing.JButton();
                jScrollPane1 = new javax.swing.JScrollPane();
                jTable1 = new javax.swing.JTable();
                jPanel35 = new javax.swing.JPanel();
                jTabbedPane7 = new javax.swing.JTabbedPane();
                jPanel36 = new javax.swing.JPanel();
                jLabel118 = new javax.swing.JLabel();
                cbb_chucNangThongKe6 = new javax.swing.JComboBox<>();
                jScrollPane21 = new javax.swing.JScrollPane();
                tabletkbandoc2 = new javax.swing.JTable();
                jLabel119 = new javax.swing.JLabel();
                jPanel37 = new javax.swing.JPanel();
                jLabel120 = new javax.swing.JLabel();
                cbb_chucNangThongKe7 = new javax.swing.JComboBox<>();
                jScrollPane22 = new javax.swing.JScrollPane();
                tabletksach2 = new javax.swing.JTable();
                jLabel121 = new javax.swing.JLabel();
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
                jPK_QuanLyPhieuMuon5 = new javax.swing.JPanel();
                jTPK_QuanLyPM5 = new javax.swing.JTabbedPane();
                Panel_DanhSachPM17 = new javax.swing.JPanel();
                K_tieuDe18 = new javax.swing.JLabel();
                btnK_themPM37 = new javax.swing.JButton();
                txt_timkiemDMSach27 = new javax.swing.JTextField();
                gioitinhnam20 = new javax.swing.JRadioButton();
                gioitinhnu20 = new javax.swing.JRadioButton();
                btnK_themPM38 = new javax.swing.JButton();
                Panel_DanhSachPM18 = new javax.swing.JPanel();
                K_tieuDe19 = new javax.swing.JLabel();
                btnK_themPM39 = new javax.swing.JButton();
                txt_timkiemDMSach28 = new javax.swing.JTextField();
                gioitinhnam21 = new javax.swing.JRadioButton();
                gioitinhnu21 = new javax.swing.JRadioButton();
                btnK_themPM40 = new javax.swing.JButton();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                setTitle("Trang chủ dành cho Thủ thư");
                setLocation(new java.awt.Point(0, 0));

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
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout
                                                                .createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jLabel29)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jLabel6,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                294,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(jButton1)
                                                                .addContainerGap())
                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                .addGap(122, 122, 122)
                                                                                .addComponent(jLabel37)
                                                                                .addContainerGap(948,
                                                                                                Short.MAX_VALUE))));
                jPanel1Layout.setVerticalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout
                                                                .createSequentialGroup()
                                                                .addContainerGap(12, Short.MAX_VALUE)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jLabel29,
                                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(10, 10, 10)
                                                                                                .addComponent(jLabel6)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                22,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addContainerGap())
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(40, 40, 40)
                                                                .addComponent(jButton1)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE))
                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                jPanel1Layout.createSequentialGroup()
                                                                                                .addContainerGap(69,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addComponent(jLabel37)
                                                                                                .addGap(12, 12, 12))));

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
                gioitinhbtngroup.add(gioitinhnam16);
                gioitinhnam16.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
                gioitinhnam16.setForeground(new java.awt.Color(0, 0, 0));
                gioitinhnam16.setText("Nam");

                gioitinhnu16.setBackground(new java.awt.Color(255, 255, 204));
                gioitinhbtngroup.add(gioitinhnu16);
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

                Hc_maTheLoai3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
                Hc_maTheLoai3.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                Hc_maTheLoai3ActionPerformed(evt);
                        }
                });

                jLabel127.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
                jLabel127.setForeground(new java.awt.Color(0, 0, 0));
                jLabel127.setText("Email:");

                jLabel128.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
                jLabel128.setForeground(new java.awt.Color(0, 0, 0));
                jLabel128.setText("Hạn dùng:");

                emailDocgia3.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
                emailDocgia3.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                emailDocgia3ActionPerformed(evt);
                        }
                });

                tenDocGiaField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

                maDocGiaField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

                hanDungField.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

                jLabel143.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
                jLabel143.setForeground(new java.awt.Color(0, 0, 0));
                jLabel143.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/searching.png"))); // NOI18N
                jLabel143.setText("Hỗ trợ tìm kiếm");

                timKiemDG.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

                javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
                jPanel29.setLayout(jPanel29Layout);
                jPanel29Layout.setHorizontalGroup(
                                jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel29Layout.createSequentialGroup()
                                                                .addGroup(jPanel29Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel29Layout
                                                                                                .createSequentialGroup()
                                                                                                .addContainerGap()
                                                                                                .addComponent(jSeparator5))
                                                                                .addGroup(jPanel29Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(27, 27, 27)
                                                                                                .addGroup(jPanel29Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addGroup(jPanel29Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addGroup(jPanel29Layout
                                                                                                                                                .createParallelGroup(
                                                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                                                                jPanel29Layout.createSequentialGroup()
                                                                                                                                                                                .addGap(55, 55, 55)
                                                                                                                                                                                .addGroup(jPanel29Layout
                                                                                                                                                                                                .createParallelGroup(
                                                                                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                                                                .addComponent(jLabel88)
                                                                                                                                                                                                .addComponent(jLabel87)
                                                                                                                                                                                                .addComponent(jLabel128)
                                                                                                                                                                                                .addComponent(jLabel92)
                                                                                                                                                                                                .addComponent(jLabel86))
                                                                                                                                                                                .addGap(34, 34, 34)
                                                                                                                                                                                .addGroup(jPanel29Layout
                                                                                                                                                                                                .createParallelGroup(
                                                                                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                                                                .addGroup(jPanel29Layout
                                                                                                                                                                                                                .createSequentialGroup()
                                                                                                                                                                                                                .addGap(122, 122,
                                                                                                                                                                                                                                122)
                                                                                                                                                                                                                .addComponent(gioitinhnam16)
                                                                                                                                                                                                                .addGap(44, 44, 44)
                                                                                                                                                                                                                .addComponent(gioitinhnu16))
                                                                                                                                                                                                .addGroup(jPanel29Layout
                                                                                                                                                                                                                .createParallelGroup(
                                                                                                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                                                                                                                                false)
                                                                                                                                                                                                                .addComponent(tenDocGiaField)
                                                                                                                                                                                                                .addComponent(matKhauField)
                                                                                                                                                                                                                .addComponent(maDocGiaField)
                                                                                                                                                                                                                .addComponent(hanDungField,
                                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                                                343,
                                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                                                                                                                .addPreferredGap(
                                                                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                                                                                                100,
                                                                                                                                                                                                Short.MAX_VALUE)
                                                                                                                                                                                .addGroup(jPanel29Layout
                                                                                                                                                                                                .createParallelGroup(
                                                                                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                                                                .addComponent(jLabel89)
                                                                                                                                                                                                .addComponent(jLabel90)
                                                                                                                                                                                                .addComponent(jLabel91)
                                                                                                                                                                                                .addComponent(jLabel127))
                                                                                                                                                                                .addGap(18, 18, 18)
                                                                                                                                                                                .addGroup(jPanel29Layout
                                                                                                                                                                                                .createParallelGroup(
                                                                                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                                                                                                                false)
                                                                                                                                                                                                .addComponent(ngaysinh2)
                                                                                                                                                                                                .addComponent(sdt2)
                                                                                                                                                                                                .addComponent(Hc_maTheLoai3,
                                                                                                                                                                                                                0,
                                                                                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                                                Short.MAX_VALUE)
                                                                                                                                                                                                .addComponent(emailDocgia3,
                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                                310,
                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                                                                                .addGroup(jPanel29Layout
                                                                                                                                                                .createSequentialGroup()
                                                                                                                                                                .addGap(8, 8, 8)
                                                                                                                                                                .addComponent(jLabel143)
                                                                                                                                                                .addGap(27, 27, 27)
                                                                                                                                                                .addComponent(timKiemDG,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                178,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                                .addPreferredGap(
                                                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                Short.MAX_VALUE)
                                                                                                                                                                .addComponent(themmoidg2)
                                                                                                                                                                .addGap(18, 18, 18)
                                                                                                                                                                .addComponent(updatedg2,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                111,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                                .addGap(18, 18, 18)
                                                                                                                                                                .addComponent(khoatk8,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                121,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                                .addGap(18, 18, 18)
                                                                                                                                                                .addComponent(mokhoa3,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                151,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                                .addGap(8, 8, 8)))
                                                                                                                                .addGap(42, 42, 42))
                                                                                                                .addComponent(jScrollPane17))))
                                                                .addContainerGap()));
                jPanel29Layout.setVerticalGroup(
                                jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout
                                                                .createSequentialGroup()
                                                                .addGap(27, 27, 27)
                                                                .addGroup(jPanel29Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel29Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGroup(jPanel29Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(jLabel86)
                                                                                                                .addComponent(jLabel89)
                                                                                                                .addComponent(sdt2,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addGroup(jPanel29Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addGroup(jPanel29Layout
                                                                                                                                .createParallelGroup(
                                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                                .addComponent(jLabel91)
                                                                                                                                .addComponent(ngaysinh2,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                .addComponent(jLabel92))
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addGroup(jPanel29Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addGroup(jPanel29Layout
                                                                                                                                .createParallelGroup(
                                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                                .addComponent(jLabel127)
                                                                                                                                .addComponent(emailDocgia3,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                .addComponent(jLabel87))
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addGroup(jPanel29Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(jLabel88)
                                                                                                                .addGroup(jPanel29Layout
                                                                                                                                .createParallelGroup(
                                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                                .addComponent(Hc_maTheLoai3,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addComponent(jLabel90)))
                                                                                                .addGap(10, 10, 10)
                                                                                                .addComponent(jLabel128))
                                                                                .addGroup(jPanel29Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(maDocGiaField,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addComponent(matKhauField,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addComponent(tenDocGiaField,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addGroup(jPanel29Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(gioitinhnam16)
                                                                                                                .addComponent(gioitinhnu16))
                                                                                                .addGap(4, 4, 4)
                                                                                                .addComponent(hanDungField,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jSeparator5,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                10,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel29Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel29Layout
                                                                                                .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                .addComponent(mokhoa3,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                41,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addComponent(khoatk8,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                41,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addComponent(updatedg2,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                41,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addComponent(themmoidg2,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                41,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                jPanel29Layout.createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(jLabel143)
                                                                                                                .addComponent(timKiemDG,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(jScrollPane17,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                154, Short.MAX_VALUE)
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
                jLabel131.setForeground(new java.awt.Color(0, 0, 0));
                jLabel131.setText("Mã phân loại:");

                jLabel132.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
                jLabel132.setForeground(new java.awt.Color(0, 0, 0));
                jLabel132.setText("Số lượng sách:");

                themmoidg3.setBackground(new java.awt.Color(255, 204, 204));
                themmoidg3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
                themmoidg3.setForeground(new java.awt.Color(0, 0, 0));
                themmoidg3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/plus.png"))); // NOI18N
                themmoidg3.setText("Thêm mới");
                themmoidg3.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                themmoidg3ActionPerformed(evt);
                        }
                });

                updatedg3.setBackground(new java.awt.Color(255, 204, 204));
                updatedg3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
                updatedg3.setForeground(new java.awt.Color(0, 0, 0));
                updatedg3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/exchange1.png"))); // NOI18N
                updatedg3.setText("Sửa ");
                updatedg3.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                updatedg3ActionPerformed(evt);
                        }
                });

                mokhoa4.setBackground(new java.awt.Color(255, 204, 204));
                mokhoa4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
                mokhoa4.setForeground(new java.awt.Color(0, 0, 0));
                mokhoa4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/eraser.png"))); // NOI18N
                mokhoa4.setText("Làm mới");
                mokhoa4.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                mokhoa4ActionPerformed(evt);
                        }
                });

                jLabel137.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
                jLabel137.setForeground(new java.awt.Color(0, 0, 0));
                jLabel137.setText("Tên phân loại:");

                jLabel139.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
                jLabel139.setForeground(new java.awt.Color(0, 0, 0));
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

                jLabel138.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
                jLabel138.setForeground(new java.awt.Color(0, 0, 0));
                jLabel138.setText("Ngày mở thẻ:");

                tenLoaiField1.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

                jLabel140.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
                jLabel140.setForeground(new java.awt.Color(0, 0, 0));
                jLabel140.setText("Hạn dùng thẻ:");

                tenLoaiField2.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

                jLabel141.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
                jLabel141.setForeground(new java.awt.Color(0, 0, 0));
                jLabel141.setText("Giá tiền mở thẻ:");

                thoiGianField1.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N

                tenLoaiField3.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
                tenLoaiField3.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                // tenLoaiField3ActionPerformed(evt);
                        }
                });

                jLabel142.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
                jLabel142.setForeground(new java.awt.Color(0, 0, 0));
                jLabel142.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/searching.png"))); // NOI18N
                jLabel142.setText("Hỗ trợ tìm kiếm:");

                javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
                jPanel40.setLayout(jPanel40Layout);
                jPanel40Layout.setHorizontalGroup(
                                jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel40Layout.createSequentialGroup()
                                                                .addGroup(jPanel40Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel40Layout
                                                                                                .createSequentialGroup()
                                                                                                .addContainerGap()
                                                                                                .addComponent(jSeparator7))
                                                                                .addGroup(jPanel40Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(27, 27, 27)
                                                                                                .addGroup(jPanel40Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                                jPanel40Layout.createSequentialGroup()
                                                                                                                                                .addGap(55, 55, 55)
                                                                                                                                                .addGroup(jPanel40Layout
                                                                                                                                                                .createParallelGroup(
                                                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                                .addGroup(jPanel40Layout
                                                                                                                                                                                .createSequentialGroup()
                                                                                                                                                                                .addGroup(jPanel40Layout
                                                                                                                                                                                                .createParallelGroup(
                                                                                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                                                                .addComponent(jLabel137,
                                                                                                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                                                                                                                .addComponent(jLabel131)
                                                                                                                                                                                                .addComponent(jLabel138))
                                                                                                                                                                                .addGap(18, 18, 18)
                                                                                                                                                                                .addGroup(jPanel40Layout
                                                                                                                                                                                                .createParallelGroup(
                                                                                                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                                                                                                                                false)
                                                                                                                                                                                                .addComponent(tenLoaiField,
                                                                                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                                                260,
                                                                                                                                                                                                                Short.MAX_VALUE)
                                                                                                                                                                                                .addComponent(tenLoaiField1)
                                                                                                                                                                                                .addComponent(maLoaiField)))
                                                                                                                                                                .addGroup(jPanel40Layout
                                                                                                                                                                                .createSequentialGroup()
                                                                                                                                                                                .addComponent(jLabel140)
                                                                                                                                                                                .addGap(18, 18, 18)
                                                                                                                                                                                .addComponent(tenLoaiField3,
                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                260,
                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                                                                                .addGap(105, 105,
                                                                                                                                                                105)
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
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                                .addComponent(jLabel141,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                153,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                                                .addPreferredGap(
                                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                                                                .addGroup(jPanel40Layout
                                                                                                                                                                .createParallelGroup(
                                                                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                                                                                                false)
                                                                                                                                                                .addComponent(thoiGianField)
                                                                                                                                                                .addComponent(soLuongField)
                                                                                                                                                                .addComponent(thoiGianField1,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                260,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                                                                                .addComponent(jScrollPane25,
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addGroup(jPanel40Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                                                                                .addComponent(jLabel142)
                                                                                                                                .addGap(18, 18, 18)
                                                                                                                                .addComponent(tenLoaiField2,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                259,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addGap(153, 153,
                                                                                                                                                153)
                                                                                                                                .addComponent(themmoidg3)
                                                                                                                                .addGap(18, 18, 18)
                                                                                                                                .addComponent(updatedg3,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                111,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addGap(18, 18, 18)
                                                                                                                                .addComponent(mokhoa4,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                151,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addGap(42, 42, 42)))))
                                                                .addContainerGap()));
                jPanel40Layout.setVerticalGroup(
                                jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel40Layout
                                                                .createSequentialGroup()
                                                                .addGap(9, 9, 9)
                                                                .addGroup(jPanel40Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addGroup(jPanel40Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGroup(jPanel40Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(jLabel131)
                                                                                                                .addComponent(maLoaiField,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addGap(18, 18, 18)
                                                                                                .addGroup(jPanel40Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(jLabel137)
                                                                                                                .addComponent(tenLoaiField,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addGap(17, 17, 17)
                                                                                                .addGroup(jPanel40Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(jLabel138)
                                                                                                                .addComponent(tenLoaiField1,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                .addGroup(jPanel40Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGroup(jPanel40Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(jLabel132)
                                                                                                                .addComponent(soLuongField,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addGap(5, 5, 5)
                                                                                                .addGroup(jPanel40Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(jLabel139)
                                                                                                                .addComponent(thoiGianField,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addGap(18, 18, 18)
                                                                                                .addGroup(jPanel40Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(jLabel141)
                                                                                                                .addComponent(thoiGianField1,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                                .addGap(22, 22, 22)
                                                                .addGroup(jPanel40Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel140)
                                                                                .addComponent(tenLoaiField3,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(31, 31, 31)
                                                                .addComponent(jSeparator7,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                10,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel40Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(themmoidg3,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                41,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(updatedg3,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                41,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(mokhoa4,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                41,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(tenLoaiField2,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jLabel142))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(jScrollPane25,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                389, Short.MAX_VALUE)
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

                javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
                jPanel31.setLayout(jPanel31Layout);
                jPanel31Layout.setHorizontalGroup(
                                jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel31Layout.createSequentialGroup()
                                                                .addGroup(jPanel31Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel31Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(40, 40, 40)
                                                                                                .addGroup(jPanel31Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addGroup(jPanel31Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addGroup(jPanel31Layout
                                                                                                                                                .createParallelGroup(
                                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                .addGroup(jPanel31Layout
                                                                                                                                                                .createSequentialGroup()
                                                                                                                                                                .addGroup(jPanel31Layout
                                                                                                                                                                                .createParallelGroup(
                                                                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                                                .addComponent(jLabel93)
                                                                                                                                                                                .addComponent(jLabel94)
                                                                                                                                                                                .addComponent(jLabel102,
                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                107,
                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                                                                .addGroup(jPanel31Layout
                                                                                                                                                                                .createParallelGroup(
                                                                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                                                .addGroup(jPanel31Layout
                                                                                                                                                                                                .createSequentialGroup()
                                                                                                                                                                                                .addGap(36, 36, 36)
                                                                                                                                                                                                .addComponent(Hc_maTheLoai2,
                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                                253,
                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                                                                                                                jPanel31Layout.createSequentialGroup()
                                                                                                                                                                                                                .addGap(34, 34, 34)
                                                                                                                                                                                                                .addComponent(H_tenSach2,
                                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                                                255,
                                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                                                                                                                .addGroup(jPanel31Layout
                                                                                                                                                                .createParallelGroup(
                                                                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                                                                                .addComponent(H_tenTheLoai2,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                257,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                                                                                jPanel31Layout.createSequentialGroup()
                                                                                                                                                                                                .addGroup(jPanel31Layout
                                                                                                                                                                                                                .createParallelGroup(
                                                                                                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                                                                                .addComponent(jLabel98)
                                                                                                                                                                                                                .addComponent(jLabel101)
                                                                                                                                                                                                                .addComponent(jLabel95))
                                                                                                                                                                                                .addGap(18, 18, 18)
                                                                                                                                                                                                .addGroup(jPanel31Layout
                                                                                                                                                                                                                .createParallelGroup(
                                                                                                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                                                                                .addComponent(H_namXB2,
                                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                                                258,
                                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                                                                                .addComponent(H_soLuongCon2,
                                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                                                258,
                                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                                                                                                                .addPreferredGap(
                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                                                147,
                                                                                                                                                Short.MAX_VALUE)
                                                                                                                                .addGroup(jPanel31Layout
                                                                                                                                                .createParallelGroup(
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
                                                                                                                .addGroup(jPanel31Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addComponent(btnH_themSach2,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                128,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addPreferredGap(
                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                                                .addComponent(btnH_suaSach2,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                119,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addPreferredGap(
                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                                                .addComponent(khoatk10,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                121,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addGap(18, 18, 18)
                                                                                                                                .addComponent(btnH_luuSach2,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                241,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addGap(0, 37, Short.MAX_VALUE)))
                                                                                                .addGap(18, 18, 18)
                                                                                                .addGroup(jPanel31Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addGroup(jPanel31Layout
                                                                                                                                .createParallelGroup(
                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                                                false)
                                                                                                                                .addComponent(H_nhaXB2,
                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                335,
                                                                                                                                                Short.MAX_VALUE)
                                                                                                                                .addComponent(H_tenDM2,
                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                335,
                                                                                                                                                Short.MAX_VALUE)
                                                                                                                                .addComponent(H_tomTat2,
                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                335,
                                                                                                                                                Short.MAX_VALUE)
                                                                                                                                .addComponent(H_tacGia4)
                                                                                                                                .addComponent(Hc_maDM2,
                                                                                                                                                0,
                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                Short.MAX_VALUE)
                                                                                                                                .addComponent(H_tacGia5))
                                                                                                                .addGroup(jPanel31Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addComponent(khoatk11,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                210,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addGap(35, 35, 35)
                                                                                                                                .addComponent(btn_lamMoiSach2,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                154,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                                                .addGroup(jPanel31Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(999, 999, 999)
                                                                                                .addComponent(jSeparator6))
                                                                                .addGroup(jPanel31Layout
                                                                                                .createSequentialGroup()
                                                                                                .addContainerGap()
                                                                                                .addComponent(jScrollPane18)))
                                                                .addContainerGap())
                                                .addGroup(jPanel31Layout.createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel31Layout.createSequentialGroup()
                                                                                .addGap(181, 181, 181)
                                                                                .addComponent(H_tenSach3,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                255,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addContainerGap(715,
                                                                                                Short.MAX_VALUE))));
                jPanel31Layout.setVerticalGroup(
                                jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel31Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(jPanel31Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel31Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGroup(jPanel31Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(jLabel93)
                                                                                                                .addComponent(H_tenSach2,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addGap(18, 18, 18))
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                jPanel31Layout.createSequentialGroup()
                                                                                                                .addGroup(jPanel31Layout
                                                                                                                                .createParallelGroup(
                                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                                .addComponent(H_tacGia5,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addComponent(jLabel96))
                                                                                                                .addPreferredGap(
                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                                                .addGroup(jPanel31Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel94)
                                                                                .addComponent(jLabel104)
                                                                                .addComponent(H_tacGia4,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel31Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel102)
                                                                                .addComponent(Hc_maTheLoai2,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jLabel97)
                                                                                .addComponent(H_nhaXB2,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(13, 13, 13)
                                                                .addGroup(jPanel31Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(H_tenTheLoai2,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jLabel101)
                                                                                .addComponent(Hc_maDM2,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jLabel103))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel31Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(H_soLuongCon2,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jLabel95)
                                                                                .addComponent(jLabel100)
                                                                                .addComponent(H_tenDM2,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(21, 21, 21)
                                                                .addGroup(jPanel31Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel31Layout
                                                                                                .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                .addComponent(H_namXB2,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addComponent(jLabel98)
                                                                                                .addComponent(jLabel99))
                                                                                .addComponent(H_tomTat2,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                59,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel31Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(btnH_themSach2)
                                                                                .addComponent(btnH_suaSach2)
                                                                                .addComponent(khoatk10,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                41,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(btnH_luuSach2,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(btn_lamMoiSach2,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(khoatk11,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                41,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jSeparator6,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jScrollPane18,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                224,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(83, 83, 83))
                                                .addGroup(jPanel31Layout.createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel31Layout.createSequentialGroup()
                                                                                .addGap(61, 61, 61)
                                                                                .addComponent(H_tenSach3,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addContainerGap(619,
                                                                                                Short.MAX_VALUE))));

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
                                                                .addGroup(jPanel32Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel32Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(85, 85, 85)
                                                                                                .addGroup(jPanel32Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                                false)
                                                                                                                .addGroup(jPanel32Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addComponent(jLabel106)
                                                                                                                                .addGap(18, 18, 18)
                                                                                                                                .addComponent(txt_maDMSach4))
                                                                                                                .addGroup(jPanel32Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addComponent(jLabel107)
                                                                                                                                .addGap(15, 15, 15)
                                                                                                                                .addComponent(txt_tenDMSach10,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                279,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                77,
                                                                                                                Short.MAX_VALUE))
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                jPanel32Layout.createSequentialGroup()
                                                                                                                .addContainerGap(
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                Short.MAX_VALUE)
                                                                                                                .addGroup(jPanel32Layout
                                                                                                                                .createParallelGroup(
                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                                                                jPanel32Layout.createSequentialGroup()
                                                                                                                                                                .addGroup(jPanel32Layout
                                                                                                                                                                                .createParallelGroup(
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
                                                                                                                                                                .addGroup(jPanel32Layout
                                                                                                                                                                                .createParallelGroup(
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
                                                                                                                                                                .addGap(121, 121,
                                                                                                                                                                                121)))))
                                                                .addGroup(jPanel32Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addGroup(jPanel32Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(txt_timkiemDMSach21,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                345,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addComponent(jLabel108))
                                                                                .addComponent(jScrollPane19,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                482,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addContainerGap(86, Short.MAX_VALUE)));
                jPanel32Layout.setVerticalGroup(
                                jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel32Layout.createSequentialGroup()
                                                                .addGroup(jPanel32Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel32Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(92, 92, 92)
                                                                                                .addGroup(jPanel32Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(txt_timkiemDMSach21,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                37,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(jLabel108)
                                                                                                                .addComponent(jLabel105))
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addComponent(jScrollPane19,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                312,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(jPanel32Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(190, 190, 190)
                                                                                                .addGroup(jPanel32Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(jLabel106)
                                                                                                                .addComponent(txt_maDMSach4,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addGap(47, 47, 47)
                                                                                                .addGroup(jPanel32Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(jLabel107)
                                                                                                                .addComponent(txt_tenDMSach10,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addGap(43, 43, 43)
                                                                                                .addGroup(jPanel32Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(btn_ThemDMSach6,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                40,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(btn_LuuDMSach6,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                40,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addGap(18, 18, 18)
                                                                                                .addGroup(jPanel32Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(btn_SuaDMSach6,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                38,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(btn_lammoi6,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                38,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                                .addContainerGap(262, Short.MAX_VALUE)));

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
                                                                .addGroup(jPanel33Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel33Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(85, 85, 85)
                                                                                                .addGroup(jPanel33Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                                false)
                                                                                                                .addGroup(jPanel33Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addComponent(jLabel110)
                                                                                                                                .addGap(18, 18, 18)
                                                                                                                                .addComponent(txt_maDMSach5))
                                                                                                                .addGroup(jPanel33Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addComponent(jLabel111)
                                                                                                                                .addGap(15, 15, 15)
                                                                                                                                .addComponent(txt_tenDMSach11,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                279,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                98,
                                                                                                                Short.MAX_VALUE))
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                jPanel33Layout.createSequentialGroup()
                                                                                                                .addContainerGap(
                                                                                                                                196,
                                                                                                                                Short.MAX_VALUE)
                                                                                                                .addGroup(jPanel33Layout
                                                                                                                                .createParallelGroup(
                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                                                                jPanel33Layout.createSequentialGroup()
                                                                                                                                                                .addComponent(jLabel109)
                                                                                                                                                                .addGap(121, 121,
                                                                                                                                                                                121))
                                                                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                                                                jPanel33Layout.createSequentialGroup()
                                                                                                                                                                .addGroup(jPanel33Layout
                                                                                                                                                                                .createParallelGroup(
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
                                                                                                                                                                .addGroup(jPanel33Layout
                                                                                                                                                                                .createParallelGroup(
                                                                                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                                                                                                .addComponent(btn_lammoi7)
                                                                                                                                                                                .addComponent(btn_LuuDMSach7,
                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                147,
                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                                                                .addGap(83, 83, 83)))))
                                                                .addGroup(jPanel33Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addGroup(jPanel33Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(txt_timkiemDMSach22,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                345,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addComponent(jLabel112))
                                                                                .addComponent(jScrollPane20,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                482,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addContainerGap(88, Short.MAX_VALUE)));
                jPanel33Layout.setVerticalGroup(
                                jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel33Layout.createSequentialGroup()
                                                                .addGroup(jPanel33Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel33Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(92, 92, 92)
                                                                                                .addGroup(jPanel33Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(txt_timkiemDMSach22,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                37,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(jLabel112))
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(jScrollPane20,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                306,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(jPanel33Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(114, 114, 114)
                                                                                                .addComponent(jLabel109)
                                                                                                .addGap(44, 44, 44)
                                                                                                .addGroup(jPanel33Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(jLabel110)
                                                                                                                .addComponent(txt_maDMSach5,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addGap(47, 47, 47)
                                                                                                .addGroup(jPanel33Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(jLabel111)
                                                                                                                .addComponent(txt_tenDMSach11,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addGap(43, 43, 43)
                                                                                                .addGroup(jPanel33Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(btn_ThemDMSach7,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                40,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(btn_LuuDMSach7,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                40,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addGap(18, 18, 18)
                                                                                                .addGroup(jPanel33Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(btn_SuaDMSach7,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                38,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(btn_lammoi7,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                38,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                                .addContainerGap(262, Short.MAX_VALUE)));

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

                AuthManagerTable.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
                AuthManagerTable.setModel(new javax.swing.table.DefaultTableModel(
                                new Object[][] {

                                },
                                new String[] {

                                }));
                AuthManagerTable.setGridColor(new java.awt.Color(255, 255, 255));
                AuthManagerTable.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                AuthManagerTableMouseClicked(evt);
                        }
                });
                jScrollPane26.setViewportView(AuthManagerTable);

                javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
                jPanel34.setLayout(jPanel34Layout);
                jPanel34Layout.setHorizontalGroup(
                                jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel34Layout.createSequentialGroup()
                                                                .addGroup(jPanel34Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel34Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(43, 43, 43)
                                                                                                .addGroup(jPanel34Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(jLabel115)
                                                                                                                .addComponent(jLabel117)
                                                                                                                .addComponent(jLabel114))
                                                                                                .addGap(30, 30, 30)
                                                                                                .addGroup(jPanel34Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(txt_tenDMSach12,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                279,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(txt_tenDMSach13,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                279,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(txt_tenDMSach14,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                279,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                107,
                                                                                                                Short.MAX_VALUE))
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                jPanel34Layout.createSequentialGroup()
                                                                                                                .addContainerGap(
                                                                                                                                199,
                                                                                                                                Short.MAX_VALUE)
                                                                                                                .addGroup(jPanel34Layout
                                                                                                                                .createParallelGroup(
                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                                                                jPanel34Layout.createSequentialGroup()
                                                                                                                                                                .addComponent(jLabel113)
                                                                                                                                                                .addGap(123, 123,
                                                                                                                                                                                123))
                                                                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                                                                jPanel34Layout.createSequentialGroup()
                                                                                                                                                                .addGroup(jPanel34Layout
                                                                                                                                                                                .createParallelGroup(
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
                                                                                                                                                                .addGroup(jPanel34Layout
                                                                                                                                                                                .createParallelGroup(
                                                                                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                                                                                                .addComponent(btn_lammoi8)
                                                                                                                                                                                .addComponent(btn_LuuDMSach8,
                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                147,
                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                                                                .addGap(85, 85, 85)))))
                                                                .addGap(5, 5, 5)
                                                                .addGroup(jPanel34Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel34Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(txt_timkiemDMSach23,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                345,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addComponent(jLabel116))
                                                                                .addComponent(jScrollPane26,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                482,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addContainerGap(81, Short.MAX_VALUE)));
                jPanel34Layout.setVerticalGroup(
                                jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel34Layout.createSequentialGroup()
                                                                .addGroup(jPanel34Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel34Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(92, 92, 92)
                                                                                                .addGroup(jPanel34Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(txt_timkiemDMSach23,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                37,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(jLabel116))
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(jScrollPane26,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                306,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(jPanel34Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(45, 45, 45)
                                                                                                .addComponent(jLabel113)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addGroup(jPanel34Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(txt_tenDMSach14,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(jLabel114))
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addGroup(jPanel34Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(jLabel115)
                                                                                                                .addComponent(txt_tenDMSach13,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addGroup(jPanel34Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(jLabel117)
                                                                                                                .addComponent(txt_tenDMSach12,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addGap(146, 146, 146)
                                                                                                .addGroup(jPanel34Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(btn_ThemDMSach8,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                40,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(btn_LuuDMSach8,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                40,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addGap(18, 18, 18)
                                                                                                .addGroup(jPanel34Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(btn_SuaDMSach8,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                38,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(btn_lammoi8,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                38,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                                .addContainerGap(261, Short.MAX_VALUE)));

                jTabbedPane4.addTab("Quản lý Tác giả", jPanel34);

                javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
                jPanel30.setLayout(jPanel30Layout);
                jPanel30Layout.setHorizontalGroup(
                                jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jTabbedPane4));
                jPanel30Layout.setVerticalGroup(
                                jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jTabbedPane4));

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
                K_tieuDe15.setForeground(new java.awt.Color(0, 0, 0));
                K_tieuDe15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/bill.png"))); // NOI18N
                K_tieuDe15.setText("CÁC PHIẾU MƯỢN ĐÃ ĐĂNG KÝ");

                btnK_themPM31.setBackground(new java.awt.Color(255, 204, 204));
                btnK_themPM31.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
                btnK_themPM31.setForeground(new java.awt.Color(0, 0, 0));
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
                gioitinhbtngroup.add(gioitinhnam17);
                gioitinhnam17.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
                gioitinhnam17.setForeground(new java.awt.Color(0, 0, 0));
                gioitinhnam17.setText("Theo tên độc giả");

                gioitinhnu17.setBackground(new java.awt.Color(255, 255, 204));
                gioitinhbtngroup.add(gioitinhnu17);
                gioitinhnu17.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
                gioitinhnu17.setForeground(new java.awt.Color(0, 0, 0));
                gioitinhnu17.setText("Theo mã phiếu mượn");

                btnK_themPM32.setBackground(new java.awt.Color(255, 204, 204));
                btnK_themPM32.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
                btnK_themPM32.setForeground(new java.awt.Color(0, 0, 0));
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
                                                                .addGroup(Panel_DanhSachPM14Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(Panel_DanhSachPM14Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(316, 316, 316)
                                                                                                .addComponent(K_tieuDe15,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                432,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(Panel_DanhSachPM14Layout
                                                                                                .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                .addGroup(Panel_DanhSachPM14Layout
                                                                                                                .createSequentialGroup()
                                                                                                                .addContainerGap(
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                Short.MAX_VALUE)
                                                                                                                .addComponent(btnK_themPM32)
                                                                                                                .addGap(35, 35, 35)
                                                                                                                .addComponent(btnK_themPM31))
                                                                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                Panel_DanhSachPM14Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addGap(81, 81, 81)
                                                                                                                                .addGroup(Panel_DanhSachPM14Layout
                                                                                                                                                .createParallelGroup(
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
                                                                .addContainerGap(90, Short.MAX_VALUE)));
                Panel_DanhSachPM14Layout.setVerticalGroup(
                                Panel_DanhSachPM14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(Panel_DanhSachPM14Layout.createSequentialGroup()
                                                                .addGap(60, 60, 60)
                                                                .addComponent(K_tieuDe15,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                46,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(Panel_DanhSachPM14Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(txt_timkiemDMSach24,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                37,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(gioitinhnam17)
                                                                                .addComponent(gioitinhnu17))
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jScrollPane2,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                458, Short.MAX_VALUE)
                                                                .addGap(18, 18, 18)
                                                                .addGroup(Panel_DanhSachPM14Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(btnK_themPM32)
                                                                                .addComponent(btnK_themPM31))
                                                                .addGap(30, 30, 30)));

                jTPK_QuanLyPM4.addTab("Danh sách phiếu mượn", Panel_DanhSachPM14);

                Panel_DanhSachPM15.setBackground(new java.awt.Color(255, 255, 204));
                Panel_DanhSachPM15.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

                K_tieuDe16.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
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

                btnK_themPM34.setBackground(new java.awt.Color(255, 204, 204));
                btnK_themPM34.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
                btnK_themPM34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/plus.png"))); // NOI18N
                btnK_themPM34.setText("Quản lý phiếu mượn");
                btnK_themPM34.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnK_themPM34btnK_themPM2ActionPerformed(evt);
                        }
                });

                jTable1.setModel(new javax.swing.table.DefaultTableModel(
                                new Object[][] {
                                                { null, null, null, null },
                                                { null, null, null, null },
                                                { null, null, null, null },
                                                { null, null, null, null }
                                },
                                new String[] {
                                                "Title 1", "Title 2", "Title 3", "Title 4"
                                }));
                jScrollPane1.setViewportView(jTable1);

                javax.swing.GroupLayout Panel_DanhSachPM15Layout = new javax.swing.GroupLayout(Panel_DanhSachPM15);
                Panel_DanhSachPM15.setLayout(Panel_DanhSachPM15Layout);
                Panel_DanhSachPM15Layout.setHorizontalGroup(
                                Panel_DanhSachPM15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(Panel_DanhSachPM15Layout.createSequentialGroup()
                                                                .addGroup(Panel_DanhSachPM15Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(Panel_DanhSachPM15Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(316, 316, 316)
                                                                                                .addComponent(K_tieuDe16,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                432,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(Panel_DanhSachPM15Layout
                                                                                                .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                                false)
                                                                                                .addGroup(Panel_DanhSachPM15Layout
                                                                                                                .createSequentialGroup()
                                                                                                                .addContainerGap(
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                Short.MAX_VALUE)
                                                                                                                .addComponent(btnK_themPM34,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                248,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addGap(18, 18, 18)
                                                                                                                .addComponent(btnK_themPM33))
                                                                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                Panel_DanhSachPM15Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addGap(98, 98, 98)
                                                                                                                                .addGroup(Panel_DanhSachPM15Layout
                                                                                                                                                .createParallelGroup(
                                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                .addComponent(txt_timkiemDMSach25,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                345,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                .addComponent(jScrollPane1,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                984,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                                                .addContainerGap(73, Short.MAX_VALUE)));
                Panel_DanhSachPM15Layout.setVerticalGroup(
                                Panel_DanhSachPM15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(Panel_DanhSachPM15Layout.createSequentialGroup()
                                                                .addGap(60, 60, 60)
                                                                .addComponent(K_tieuDe16,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                46,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(txt_timkiemDMSach25,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                37,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jScrollPane1,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                458, Short.MAX_VALUE)
                                                                .addGap(18, 18, 18)
                                                                .addGroup(Panel_DanhSachPM15Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(btnK_themPM34)
                                                                                .addComponent(btnK_themPM33))
                                                                .addGap(30, 30, 30)));

                jTPK_QuanLyPM4.addTab("Danh sách phiếu nhập", Panel_DanhSachPM15);

                javax.swing.GroupLayout jPK_QuanLyPhieuMuon4Layout = new javax.swing.GroupLayout(jPK_QuanLyPhieuMuon4);
                jPK_QuanLyPhieuMuon4.setLayout(jPK_QuanLyPhieuMuon4Layout);
                jPK_QuanLyPhieuMuon4Layout.setHorizontalGroup(
                                jPK_QuanLyPhieuMuon4Layout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jTPK_QuanLyPM4, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                1155, Short.MAX_VALUE));
                jPK_QuanLyPhieuMuon4Layout.setVerticalGroup(
                                jPK_QuanLyPhieuMuon4Layout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jTPK_QuanLyPM4, javax.swing.GroupLayout.DEFAULT_SIZE, 755,
                                                                Short.MAX_VALUE));

                jTP_main2.addTab(" QUẢN LÝ MƯỢN TRẢ ",
                                new javax.swing.ImageIcon(getClass().getResource("/Images/exchange.png")),
                                jPK_QuanLyPhieuMuon4); // NOI18N

                jPanel35.setBackground(new java.awt.Color(204, 204, 255));

                jTabbedPane7.setForeground(new java.awt.Color(0, 0, 102));
                jTabbedPane7.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N

                jPanel36.setBackground(new java.awt.Color(255, 255, 204));
                jPanel36.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

                jLabel118.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
                jLabel118.setForeground(new java.awt.Color(0, 0, 0));
                jLabel118.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/book.png"))); // NOI18N
                jLabel118.setText("Lựa chọn: ");

                cbb_chucNangThongKe6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
                cbb_chucNangThongKe6.setForeground(new java.awt.Color(0, 0, 153));
                cbb_chucNangThongKe6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {
                                "Bạn đọc chưa trả sách", "Bạn đọc mượn quá hạn", "Bạn đọc mượn nhiều nhất" }));
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
                                                                .addGroup(jPanel36Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel36Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(146, 146, 146)
                                                                                                .addComponent(jLabel118)
                                                                                                .addGap(26, 26, 26)
                                                                                                .addComponent(cbb_chucNangThongKe6,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                308,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(jPanel36Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(54, 54, 54)
                                                                                                .addGroup(jPanel36Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(jScrollPane21,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                956,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(jLabel119,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                363,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                jPanel36Layout.setVerticalGroup(
                                jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel36Layout.createSequentialGroup()
                                                                .addGap(91, 91, 91)
                                                                .addGroup(jPanel36Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel118,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                34,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(cbb_chucNangThongKe6,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                34,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jScrollPane21,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                263,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(91, 91, 91)
                                                                .addComponent(jLabel119)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));

                jTabbedPane7.addTab("Thông Kê Bạn Đọc", jPanel36);

                jPanel37.setBackground(new java.awt.Color(255, 255, 204));

                jLabel120.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
                jLabel120.setForeground(new java.awt.Color(0, 0, 0));
                jLabel120.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/book.png"))); // NOI18N
                jLabel120.setText("Lựa chọn:");

                cbb_chucNangThongKe7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
                cbb_chucNangThongKe7.setForeground(new java.awt.Color(0, 0, 153));
                cbb_chucNangThongKe7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {
                                "Sách được mượn nhiều nhất theo ngày", "Tổng số sách đượn mượn theo từng tháng" }));
                cbb_chucNangThongKe7.addItemListener(new java.awt.event.ItemListener() {
                        public void itemStateChanged(java.awt.event.ItemEvent evt) {
                                cbb_chucNangThongKe7ItemStateChanged(evt);
                        }
                });

                tabletksach2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
                tabletksach2.setModel(new javax.swing.table.DefaultTableModel(
                                new Object[][] {

                                },
                                new String[] {

                                }));
                jScrollPane22.setViewportView(tabletksach2);

                jLabel121.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
                jLabel121.setForeground(new java.awt.Color(204, 0, 0));
                jLabel121.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/open-book.png"))); // NOI18N
                jLabel121.setText("Lựa chọn thông tin bạn muốn thống kê!");

                javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
                jPanel37.setLayout(jPanel37Layout);
                jPanel37Layout.setHorizontalGroup(
                                jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel37Layout.createSequentialGroup()
                                                                .addGap(109, 109, 109)
                                                                .addGroup(jPanel37Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jLabel121,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                363,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGroup(jPanel37Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(jLabel120)
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(cbb_chucNangThongKe7,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                409,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addComponent(jScrollPane22,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                897,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                jPanel37Layout.setVerticalGroup(
                                jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel37Layout.createSequentialGroup()
                                                                .addContainerGap(217, Short.MAX_VALUE)
                                                                .addGroup(jPanel37Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel120,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                34,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(cbb_chucNangThongKe7,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                34,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jScrollPane22,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                281,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(jLabel121)
                                                                .addGap(123, 123, 123)));

                jTabbedPane7.addTab("Thống Kê Sách", jPanel37);

                jPanel38.setBackground(new java.awt.Color(255, 255, 204));

                jLabel122.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
                jLabel122.setForeground(new java.awt.Color(0, 0, 0));
                jLabel122.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/book.png"))); // NOI18N
                jLabel122.setText("Lựa chọn:");

                cbb_chucNangThongKe8.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
                cbb_chucNangThongKe8.setForeground(new java.awt.Color(0, 0, 153));
                cbb_chucNangThongKe8.setModel(
                                new javax.swing.DefaultComboBoxModel<>(new String[] { "Tổng tiền phạt theo tháng" }));

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
                                                                .addGap(77, 77, 77)
                                                                .addGroup(jPanel38Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jLabel123,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                363,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jScrollPane23,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                940,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGroup(jPanel38Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(jLabel122)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addComponent(cbb_chucNangThongKe8,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                352,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                jPanel38Layout.setVerticalGroup(
                                jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel38Layout.createSequentialGroup()
                                                                .addGap(105, 105, 105)
                                                                .addGroup(jPanel38Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel122,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                34,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(cbb_chucNangThongKe8,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                34,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(30, 30, 30)
                                                                .addComponent(jScrollPane23,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                260,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jLabel123)
                                                                .addContainerGap(238, Short.MAX_VALUE)));

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
                jComboBox8.setModel(new javax.swing.DefaultComboBoxModel<>(
                                new String[] { "Tìm theo thể loại", "Giáo trình học", "Sách tham khảo",
                                                "Văn hóa lịch sử", "Chính trị, Pháp luật", "Tạp chí" }));
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
                                                                .addGroup(timkiem2Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jScrollPane24)
                                                                                .addGroup(timkiem2Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGroup(timkiem2Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(jLabel126)
                                                                                                                .addComponent(jLabel125,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                184,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(jLabel124))
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addGroup(timkiem2Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addGroup(timkiem2Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addComponent(textboxsearch2,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                353,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addGap(18, 18, 18)
                                                                                                                                .addComponent(jButton13,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                134,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                .addGroup(timkiem2Layout
                                                                                                                                .createParallelGroup(
                                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                                                                false)
                                                                                                                                .addComponent(jComboBox8,
                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                                                0,
                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                Short.MAX_VALUE)
                                                                                                                                .addComponent(jComboBox7,
                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                247,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                                .addGap(0, 410, Short.MAX_VALUE)))
                                                                .addContainerGap()));
                timkiem2Layout.setVerticalGroup(
                                timkiem2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(timkiem2Layout.createSequentialGroup()
                                                                .addGap(58, 58, 58)
                                                                .addGroup(timkiem2Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                false)
                                                                                .addComponent(jButton13,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                34, Short.MAX_VALUE)
                                                                                .addComponent(textboxsearch2)
                                                                                .addComponent(jLabel124,
                                                                                                javax.swing.GroupLayout.Alignment.TRAILING))
                                                                .addGap(36, 36, 36)
                                                                .addGroup(timkiem2Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel125)
                                                                                .addComponent(jComboBox7,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                35,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(timkiem2Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                false)
                                                                                .addGroup(timkiem2Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(27, 27, 27)
                                                                                                .addComponent(jLabel126))
                                                                                .addGroup(timkiem2Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(jComboBox8)))
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jScrollPane24,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                386,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));

                javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
                jPanel39.setLayout(jPanel39Layout);
                jPanel39Layout.setHorizontalGroup(
                                jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 1155, Short.MAX_VALUE)
                                                .addGroup(jPanel39Layout.createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(timkiem2,
                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                jPanel39Layout.setVerticalGroup(
                                jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 755, Short.MAX_VALUE)
                                                .addGroup(jPanel39Layout.createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(timkiem2,
                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));

                jTP_main2.addTab("                      TRA CỨU",
                                new javax.swing.ImageIcon(getClass().getResource("/Images/research.png")), jPanel39); // NOI18N

                jPK_QuanLyPhieuMuon5.setBackground(new java.awt.Color(204, 204, 255));
                jPK_QuanLyPhieuMuon5.setPreferredSize(new java.awt.Dimension(1000, 704));

                jTPK_QuanLyPM5.setForeground(new java.awt.Color(51, 0, 102));
                jTPK_QuanLyPM5.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
                jTPK_QuanLyPM5.setPreferredSize(new java.awt.Dimension(900, 704));

                Panel_DanhSachPM17.setBackground(new java.awt.Color(255, 255, 204));
                Panel_DanhSachPM17.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

                K_tieuDe18.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
                K_tieuDe18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/bill.png"))); // NOI18N
                K_tieuDe18.setText("CÁC PHIẾU MƯỢN ĐÃ ĐĂNG KÝ");

                btnK_themPM37.setBackground(new java.awt.Color(255, 204, 204));
                btnK_themPM37.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
                btnK_themPM37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/plus.png"))); // NOI18N
                btnK_themPM37.setText("Xem chi tiết");
                btnK_themPM37.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnK_themPM37btnK_themPM1ActionPerformed(evt);
                        }
                });

                txt_timkiemDMSach27.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
                txt_timkiemDMSach27.setText("Tìm kiếm");
                txt_timkiemDMSach27.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyReleased(java.awt.event.KeyEvent evt) {
                                txt_timkiemDMSach27txt_timkiemDMSach3KeyReleased(evt);
                        }
                });

                gioitinhnam20.setBackground(new java.awt.Color(255, 255, 204));
                gioitinhbtngroup.add(gioitinhnam20);
                gioitinhnam20.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
                gioitinhnam20.setText("Theo tên độc giả");

                gioitinhnu20.setBackground(new java.awt.Color(255, 255, 204));
                gioitinhbtngroup.add(gioitinhnu20);
                gioitinhnu20.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
                gioitinhnu20.setText("Theo mã phiếu mượn");

                btnK_themPM38.setBackground(new java.awt.Color(255, 204, 204));
                btnK_themPM38.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
                btnK_themPM38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/plus.png"))); // NOI18N
                btnK_themPM38.setText("Quản lý phiếu mượn");
                btnK_themPM38.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnK_themPM38btnK_themPM2ActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout Panel_DanhSachPM17Layout = new javax.swing.GroupLayout(Panel_DanhSachPM17);
                Panel_DanhSachPM17.setLayout(Panel_DanhSachPM17Layout);
                Panel_DanhSachPM17Layout.setHorizontalGroup(
                                Panel_DanhSachPM17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                Panel_DanhSachPM17Layout.createSequentialGroup()
                                                                                .addContainerGap(511, Short.MAX_VALUE)
                                                                                .addGroup(Panel_DanhSachPM17Layout
                                                                                                .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                .addGroup(Panel_DanhSachPM17Layout
                                                                                                                .createSequentialGroup()
                                                                                                                .addComponent(btnK_themPM38,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                248,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addGap(47, 47, 47)
                                                                                                                .addComponent(btnK_themPM37))
                                                                                                .addGroup(Panel_DanhSachPM17Layout
                                                                                                                .createSequentialGroup()
                                                                                                                .addComponent(gioitinhnam20)
                                                                                                                .addGap(37, 37, 37)
                                                                                                                .addComponent(gioitinhnu20)
                                                                                                                .addGap(228, 228,
                                                                                                                                228)))
                                                                                .addGap(29, 29, 29))
                                                .addGroup(Panel_DanhSachPM17Layout.createSequentialGroup()
                                                                .addGroup(Panel_DanhSachPM17Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(Panel_DanhSachPM17Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(316, 316, 316)
                                                                                                .addComponent(K_tieuDe18,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                432,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(Panel_DanhSachPM17Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(164, 164, 164)
                                                                                                .addComponent(txt_timkiemDMSach27,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                345,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                Panel_DanhSachPM17Layout.setVerticalGroup(
                                Panel_DanhSachPM17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(Panel_DanhSachPM17Layout.createSequentialGroup()
                                                                .addGap(60, 60, 60)
                                                                .addComponent(K_tieuDe18,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                46,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                53, Short.MAX_VALUE)
                                                                .addComponent(txt_timkiemDMSach27,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                37,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(Panel_DanhSachPM17Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(gioitinhnam20)
                                                                                .addComponent(gioitinhnu20))
                                                                .addGap(177, 177, 177)
                                                                .addGroup(Panel_DanhSachPM17Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(btnK_themPM38)
                                                                                .addComponent(btnK_themPM37))
                                                                .addGap(30, 30, 30)));

                jTPK_QuanLyPM5.addTab("Danh sách phiếu mượn", Panel_DanhSachPM17);

                Panel_DanhSachPM18.setBackground(new java.awt.Color(255, 255, 204));
                Panel_DanhSachPM18.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

                K_tieuDe19.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
                K_tieuDe19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/bill.png"))); // NOI18N
                K_tieuDe19.setText("CÁC PHIẾU MƯỢN ĐÃ ĐĂNG KÝ");

                btnK_themPM39.setBackground(new java.awt.Color(255, 204, 204));
                btnK_themPM39.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
                btnK_themPM39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/plus.png"))); // NOI18N
                btnK_themPM39.setText("Xem chi tiết");
                btnK_themPM39.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnK_themPM39btnK_themPM1ActionPerformed(evt);
                        }
                });

                txt_timkiemDMSach28.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
                txt_timkiemDMSach28.setText("Tìm kiếm");
                txt_timkiemDMSach28.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyReleased(java.awt.event.KeyEvent evt) {
                                txt_timkiemDMSach28txt_timkiemDMSach3KeyReleased(evt);
                        }
                });

                gioitinhnam21.setBackground(new java.awt.Color(255, 255, 204));
                gioitinhbtngroup.add(gioitinhnam21);
                gioitinhnam21.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
                gioitinhnam21.setText("Theo tên độc giả");

                gioitinhnu21.setBackground(new java.awt.Color(255, 255, 204));
                gioitinhbtngroup.add(gioitinhnu21);
                gioitinhnu21.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
                gioitinhnu21.setText("Theo mã phiếu mượn");

                btnK_themPM40.setBackground(new java.awt.Color(255, 204, 204));
                btnK_themPM40.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
                btnK_themPM40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/plus.png"))); // NOI18N
                btnK_themPM40.setText("Quản lý phiếu mượn");
                btnK_themPM40.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnK_themPM40btnK_themPM2ActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout Panel_DanhSachPM18Layout = new javax.swing.GroupLayout(Panel_DanhSachPM18);
                Panel_DanhSachPM18.setLayout(Panel_DanhSachPM18Layout);
                Panel_DanhSachPM18Layout.setHorizontalGroup(
                                Panel_DanhSachPM18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(Panel_DanhSachPM18Layout.createSequentialGroup()
                                                                .addGap(316, 316, 316)
                                                                .addComponent(K_tieuDe19,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                432,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                Panel_DanhSachPM18Layout.createSequentialGroup()
                                                                                .addContainerGap(148, Short.MAX_VALUE)
                                                                                .addGroup(Panel_DanhSachPM18Layout
                                                                                                .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                .addGroup(Panel_DanhSachPM18Layout
                                                                                                                .createSequentialGroup()
                                                                                                                .addComponent(btnK_themPM40,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                248,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addGap(47, 47, 47)
                                                                                                                .addComponent(btnK_themPM39))
                                                                                                .addGroup(Panel_DanhSachPM18Layout
                                                                                                                .createSequentialGroup()
                                                                                                                .addComponent(txt_timkiemDMSach28,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                345,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addGap(18, 18, 18)
                                                                                                                .addComponent(gioitinhnam21)
                                                                                                                .addGap(37, 37, 37)
                                                                                                                .addComponent(gioitinhnu21)
                                                                                                                .addGap(228, 228,
                                                                                                                                228)))
                                                                                .addGap(29, 29, 29)));
                Panel_DanhSachPM18Layout.setVerticalGroup(
                                Panel_DanhSachPM18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(Panel_DanhSachPM18Layout.createSequentialGroup()
                                                                .addGap(60, 60, 60)
                                                                .addComponent(K_tieuDe19,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                46,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addGroup(Panel_DanhSachPM18Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(txt_timkiemDMSach28,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                37,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(gioitinhnam21)
                                                                                .addComponent(gioitinhnu21))
                                                                .addGap(170, 170, 170)
                                                                .addGroup(Panel_DanhSachPM18Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(btnK_themPM40)
                                                                                .addComponent(btnK_themPM39))
                                                                .addGap(30, 30, 30)));

                jTPK_QuanLyPM5.addTab("Danh sách phiếu mượn", Panel_DanhSachPM18);

                javax.swing.GroupLayout jPK_QuanLyPhieuMuon5Layout = new javax.swing.GroupLayout(jPK_QuanLyPhieuMuon5);
                jPK_QuanLyPhieuMuon5.setLayout(jPK_QuanLyPhieuMuon5Layout);
                jPK_QuanLyPhieuMuon5Layout.setHorizontalGroup(
                                jPK_QuanLyPhieuMuon5Layout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jTPK_QuanLyPM5, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                1155, Short.MAX_VALUE));
                jPK_QuanLyPhieuMuon5Layout.setVerticalGroup(
                                jPK_QuanLyPhieuMuon5Layout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jTPK_QuanLyPM5, javax.swing.GroupLayout.DEFAULT_SIZE, 755,
                                                                Short.MAX_VALUE));

                jTP_main2.addTab(" QUẢN LÝ MƯỢN TRẢ ",
                                new javax.swing.ImageIcon(getClass().getResource("/Images/exchange.png")),
                                jPK_QuanLyPhieuMuon5); // NOI18N

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jTP_main2, javax.swing.GroupLayout.Alignment.TRAILING,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, 1460,
                                                                Short.MAX_VALUE));
                jPanel2Layout.setVerticalGroup(
                                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jTP_main2,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                515,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 18, Short.MAX_VALUE)));

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jPanel1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(jPanel2,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));

                pack();
                setLocationRelativeTo(null);
        }// </editor-fold>//GEN-END:initComponents

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
                List<PhanLoaiSach> theLoais = PhanLoaiSach_DAO.getInstance().selectTheLoaiAll();
                for (PhanLoaiSach tl : theLoais) {
                        defaultTableModel_TL.addRow(new Object[] { tl.getMaTheloai(), tl.getTenTheloai() });
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

        private void AuthManagerTableMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_AuthManagerTableMouseClicked
                // TODO add your handling code here:
        }// GEN-LAST:event_AuthManagerTableMouseClicked

        // kiểm tra định dạng email
        public static boolean isValidGmail(String email) {
                // Sử dụng biểu thức chính quy để kiểm tra định dạng email Gmail
                String regex = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(email);
                return matcher.matches();
        }

        // kiểm tra định dạng sdt
        public static boolean isValidPhoneNumber(String phoneNumber) {
                // Sử dụng biểu thức chính quy để kiểm tra định dạng số điện thoại (di động Việt
                // Nam)
                String regex = "0[0-9]{9}"; // Đây là định dạng số điện thoại di động 10 chữ số của Việt Nam
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(phoneNumber);
                return matcher.matches();
        }

        // bảng độc giả
        public void loadTableDocGia(JTable tb) {
                String[] columnNames = { "Mã độc giả", "Tên độc giả", "Loại Tài Khoản", "Mật khẩu", "Số điện thoại",
                                "Ngày sinh", "Email",
                                "Giới tính", "SoLuongMuon" };
                DefaultTableModel fault = new DefaultTableModel();
                for (String col : columnNames) {
                        fault.addColumn(col);
                }

                DanhSachTaiKhoan dg = new DanhSachTaiKhoan(new QuanLiDocGia_DAO().dsDOCGIA());
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
                        t.add(tk.getSoLuongMuon());
                        fault.addRow(t);
                }
                tb.setModel(fault);
        }

        private void resetDG() {
                maDocGiaField.setText("");
                matKhauField.setText("");
                tenDocGiaField.setText("");
                gioitinhbtngroup.clearSelection();
                sdt2.setText("");
                ngaysinh2.setText("");
                emailDocgia3.setText("");
                Hc_maTheLoai3.setSelectedItem("");
                hanDungField.setText("");
        }

        private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
                new DangNhap().setVisible(true);
                this.setVisible(false);
        }// GEN-LAST:event_jButton1ActionPerformed

        // ================================================= Quản lí độc giả
        // ===========================
        private void tableDocgia2MouseClicked(java.awt.event.MouseEvent evt) {
                tableDocgia2.setRowSelectionAllowed(true);
                int lineSelect = tableDocgia2.getSelectedRow();

                maDocGiaField.setText((String) tableDocgia2.getValueAt(lineSelect, 0));
                tenDocGiaField.setText((String) tableDocgia2.getValueAt(lineSelect, 1));
                Hc_maTheLoai3.setSelectedItem((String) tableDocgia2.getValueAt(lineSelect, 2));

                matKhauField.setText((String) tableDocgia2.getValueAt(lineSelect, 3));
                sdt2.setText((String) tableDocgia2.getValueAt(lineSelect, 4));
                ngaysinh2.setText((String) tableDocgia2.getValueAt(lineSelect, 5));
                emailDocgia3.setText((String) tableDocgia2.getValueAt(lineSelect, 6));

                String gioiTinh = tableDocgia2.getValueAt(lineSelect, 7).toString();
                if (gioiTinh.equals("Nam")) {
                        gioitinhnam16.setSelected(true);
                } else
                        gioitinhnu16.setSelected(true);
                hanDungField.setText(new QuanLiDocGia_DAO().hanDungThe(maDocGiaField.getText()));
        }

        private void themmoidg2ActionPerformed(java.awt.event.ActionEvent evt) {
                if (maDocGiaField.getText().equals("") || matKhauField.getText().equals("")
                                || emailDocgia3.getText().equals("") || sdt2.getText().equals("")) {
                        JOptionPane.showMessageDialog((JOptionPane.getRootFrame()), "Vui Lòng Nhập Đủ Thông Tin!");
                } else if (!new QuanLiDocGia_DAO().checkMaTaiKhoan(maDocGiaField.getText())) {
                        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Mã Độc Giả Đã Tồn Tại!");
                } else if (!isValidGmail(emailDocgia3.getText())) {
                        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),
                                        "Vui Lòng Nhập Đúng Định Dạng Email!");
                } else if (!isValidPhoneNumber(sdt2.getText())) {
                        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),
                                        "Vui Lòng Nhập Đúng Định Dạng Số Điện Thoại!");
                } else {
                        TaiKhoan dg = new TaiKhoan();
                        dg.setTenTaiKhoan(maDocGiaField.getText());
                        dg.setMatKhau(matKhauField.getText());
                        dg.setTenNguoiDung(tenDocGiaField.getText());
                        dg.setLoaiTK(Hc_maTheLoai3.getSelectedItem().toString());
                        dg.setEmail(emailDocgia3.getText());
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

                        if (new QuanLiDocGia_DAO().Add_DG(dg)) {
                                loadTableDocGia(tableDocgia2);
                                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Thêm Thành Công!");
                                resetDG();
                        } else {
                                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Thêm Lỗi");
                        }
                }
        }// GEN-LAST:event_themmoidg2ActionPerformed

        private void updatedg2ActionPerformed(java.awt.event.ActionEvent evt) {
                if (maDocGiaField.getText().equals("") || matKhauField.getText().equals("")
                                || emailDocgia3.getText().equals("") || sdt2.getText().equals("")) {
                        JOptionPane.showMessageDialog((JOptionPane.getRootFrame()), "Vui Lòng Nhập Đủ Thông Tin!");
                } else if (new QuanLiDocGia_DAO().checkMaTaiKhoan(maDocGiaField.getText())) {
                        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Mã Độc Giả Không Tồn Tại!");
                } else if (!isValidGmail(emailDocgia3.getText())) {
                        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),
                                        "Vui Lòng Nhập Đúng Định Dạng Email!");
                } else if (!isValidPhoneNumber(sdt2.getText())) {
                        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),
                                        "Vui Lòng Nhập Đúng Định Dạng Số Điện Thoại!");
                } else {
                        TaiKhoan dg = new TaiKhoan();
                        dg.setTenTaiKhoan(maDocGiaField.getText());
                        dg.setMatKhau(matKhauField.getText());
                        dg.setTenNguoiDung(tenDocGiaField.getText());
                        dg.setLoaiTK(Hc_maTheLoai3.getSelectedItem().toString());
                        dg.setEmail(emailDocgia3.getText());
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

                        if (new QuanLiDocGia_DAO().update_DG(dg)) {
                                loadTableDocGia(tableDocgia2);
                                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Sủa Thành Công!");
                                resetDG();
                        } else {
                                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Sủa Lỗi");
                        }
                }
        }

        private void khoatk8ActionPerformed(java.awt.event.ActionEvent evt) {
                if (maDocGiaField.getText().equals("") || matKhauField.getText().equals("")
                                || emailDocgia3.getText().equals("") || sdt2.getText().equals("")) {
                        JOptionPane.showMessageDialog((rootPane), "Vui Lòng Nhập Đủ Thông Tin!");
                } else if (new QuanLiDocGia_DAO().checkMaTaiKhoan(maDocGiaField.getText())) {
                        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Mã Độc Giả Không Tồn Tại!");
                } else {
                        if (new QuanLiDocGia_DAO().delete_DG(maDocGiaField.getText())) {
                                loadTableDocGia(tableDocgia2);
                                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Xóa Thành Công!");
                                resetDG();
                        } else {
                                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Xóa Lỗi");
                        }
                }
        }

        private void mokhoa3ActionPerformed(java.awt.event.ActionEvent evt) {
                resetDG();
        }

        // ===================================== Quản lí độc giả
        // ============================

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

        private void khoatk9ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_khoatk9ActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_khoatk9ActionPerformed

        private void H_soLuongCon2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_H_soLuongCon2ActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_H_soLuongCon2ActionPerformed

        private void tblH_Sach2MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tblH_Sach2MouseClicked
                // TODO add your handling code here:
        }// GEN-LAST:event_tblH_Sach2MouseClicked

        private void btnH_themSach2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnH_themSach2ActionPerformed
                // TODO add your handling code here:
                if (H_tenSach2.getText().trim().equals("") || H_tenSach3.getText().trim().equals("")
                                || H_tenTheLoai2.getText().trim().equals("") || H_tenDM2.getText().trim().equals("")
                                || H_soLuongCon2.getText().trim().equals("")) {
                        JOptionPane.showMessageDialog(null, "Bạn chưa nhập đủ thông tin!");
                } else {
                        int x = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn thêm không?");
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
                                // sach.setAnh(H_linkAnh.getText());
                                KhoSach khoSach = new KhoSach(H_tenSach2.getText(), 0, 0, 0);
                                KhoSach_DAO.getInstance().add(khoSach);
                                if (Sach_DAO.getInstance().add(sach) > 0) {
                                        JOptionPane.showMessageDialog(null, "Đã thêm dữ liệu của sách.");
                                } else {
                                        JOptionPane.showMessageDialog(null,
                                                        "Thêm sách thất bại! \n Hãy kiểm tra lại mã sách.");
                                }
                        }
                        refresh();
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
                                // test push code and sync with vscode
                                sach.setMaSach(H_tenSach3.getText());
                                sach.setTenSach(H_tenSach2.getText());
                                sach.setMaDMSach(Hc_maDM2.getItemAt(Hc_maDM2.getSelectedIndex()));
                                sach.setMaTheLoai(Hc_maTheLoai2.getItemAt(Hc_maTheLoai2.getSelectedIndex()));
                                sach.setTacGia(H_tacGia5.getText());
                                sach.setMaTacGia(H_tacGia4.getText());
                                sach.setNXB(H_nhaXB2.getText());
                                sach.setNamXuatBan(Integer.parseInt(H_namXB2.getText()));
                                // sach.setSoLuongCon(Integer.parseInt(H_soLuongCon.getText()));
                                sach.setTomTatND(H_tomTat2.getText());

                                // s_Service.updateSach(sach);
                                Sach_DAO.getInstance().update(sach);
                                // defaultTableModel_S.setRowCount(0);
                                // setTableData_S(s_Service.getDSSach());
                        }
                }
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

        private void Hc_maTheLoai2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_Hc_maDM2ActionPerformed
                PhanLoaiSach phanLoaiSach = PhanLoaiSach_DAO.getInstance()
                                .selectById((String) Hc_maTheLoai2.getSelectedItem());
                H_tenTheLoai2.setText(phanLoaiSach.getTenTheloai());
        }

        private void khoatk10ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_khoatk10ActionPerformed
                // TODO add your handling code here:
                if (H_tenSach2.getText().trim().equals("")) {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn mã sách muốn xóa!");
                } else {
                        int x = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa ?");
                        if (x == JOptionPane.NO_OPTION) {
                                return;
                        } else {
                                if (Sach_DAO.getInstance().delete(H_tenSach2.getText()) > 0) {
                                        JOptionPane.showMessageDialog(null, "Xóa thành công.");
                                } else {
                                        JOptionPane.showMessageDialog(null,
                                                        "Xóa sách thất bại! \n Hãy kiểm tra lại mã sách.");
                                }
                        }
                }
        }// GEN-LAST:event_khoatk10ActionPerformed

        private void khoatk11ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_khoatk11ActionPerformed
                // TODO add your handling code here:
                new TrangChuThuThu_TimKiem().setVisible(true);
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
                txt_tenDMSach10.setEnabled(false);
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
                                TheLoai theLoai = new TheLoai();
                                theLoai.setMaTheLoai(txt_maDMSach5.getText());
                                theLoai.setTenTheLoai(txt_tenDMSach11.getText());
                                if (TheLoai_DAO.getInstance().add(theLoai) > 0) {
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
                                TheLoai theLoai = new TheLoai();
                                theLoai.setMaTheLoai(txt_maDMSach5.getText());
                                theLoai.setTenTheLoai(txt_tenDMSach11.getText());
                                if (TheLoai_DAO.getInstance().update(theLoai) > 0) {
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
                        loadmaTheLoai();
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
                        // loadmaTheLoai();
                        btn_lammoi8ActionPerformed(evt);
                }
        }// GEN-LAST:event_btn_SuaDMSach8ActionPerformed

        private void txt_timkiemDMSach23KeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txt_timkiemDMSach23KeyReleased
                // TODO add your handling code here:
                // String query = txt_timkiemDMSach22.getText();
                // TableRowSorter<DefaultTableModel> tbl = new
                // TableRowSorter<DefaultTableModel>(defaultTableModel_TL);
                // tbl_DMSach5.setRowSorter(tbl);
                // tbl.setRowFilter(RowFilter.regexFilter(query));
        }// GEN-LAST:event_txt_timkiemDMSach23KeyReleased

        private void btn_lammoi8ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btn_lammoi8ActionPerformed
                // TODO add your handling code here:
                txt_tenDMSach14.setEnabled(false);
                txt_tenDMSach13.setEnabled(false);
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
        }// GEN-LAST:event_txt_timkiemDMSach25txt_timkiemDMSach3KeyReleased

        private void btnK_themPM34btnK_themPM2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnK_themPM34btnK_themPM2ActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_btnK_themPM34btnK_themPM2ActionPerformed

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

        private void cbb_chucNangThongKe7ItemStateChanged(java.awt.event.ItemEvent evt) {// GEN-FIRST:event_cbb_chucNangThongKe7ItemStateChanged
                // TODO add your handling code here:
        }// GEN-LAST:event_cbb_chucNangThongKe7ItemStateChanged

        private void textboxsearch2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_textboxsearch2ActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_textboxsearch2ActionPerformed

        private void textboxsearch2KeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_textboxsearch2KeyReleased
                // TODO add your handling code here:
        }// GEN-LAST:event_textboxsearch2KeyReleased

        private void textboxsearch2KeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_textboxsearch2KeyTyped
                // TODO add your handling code here:
        }// GEN-LAST:event_textboxsearch2KeyTyped

        private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton13ActionPerformed
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
        }// GEN-LAST:event_txt_timkiemDMSach28txt_timkiemDMSach3KeyReleased

        private void btnK_themPM40btnK_themPM2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnK_themPM40btnK_themPM2ActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_btnK_themPM40btnK_themPM2ActionPerformed

        private void btnK_themPM41btnK_themPM1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnK_themPM41btnK_themPM1ActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_btnK_themPM41btnK_themPM1ActionPerformed

        private void txt_timkiemDMSach29txt_timkiemDMSach3KeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txt_timkiemDMSach29txt_timkiemDMSach3KeyReleased
                // TODO add your handling code here:
        }// GEN-LAST:event_txt_timkiemDMSach29txt_timkiemDMSach3KeyReleased

        private void btnK_themPM42btnK_themPM2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnK_themPM42btnK_themPM2ActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_btnK_themPM42btnK_themPM2ActionPerformed

        private void jTP_main2MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jTP_main2MouseClicked
                // TODO add your handling code here:
        }// GEN-LAST:event_jTP_main2MouseClicked

        private void Hc_maTheLoai3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_Hc_maTheLoai3ActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_Hc_maTheLoai3ActionPerformed

        private void emailDocgia3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_emailDocgia3ActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_emailDocgia3ActionPerformed

        private void tableDocgia3MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tableDocgia3MouseClicked
                int lineSelect = tableDocgia3.getSelectedRow();
                maLoaiField.setText(tableDocgia3.getValueAt(lineSelect, 0).toString());
                tenLoaiField.setText(tableDocgia3.getValueAt(lineSelect, 1).toString());
                tenLoaiField1.setText(tableDocgia3.getValueAt(lineSelect, 2).toString());
                tenLoaiField3.setText(tableDocgia3.getValueAt(lineSelect, 3).toString());
                soLuongField.setText(tableDocgia3.getValueAt(lineSelect, 4).toString());
                thoiGianField.setText(tableDocgia3.getValueAt(lineSelect, 5).toString());
                thoiGianField1.setText(tableDocgia3.getValueAt(lineSelect, 6).toString());
        }

        // load dữ liệu lên bảng phân loại thẻ
        private void loadTableLoaiThe(JTable tb) {
                String[] nameColumnLoaiThe = { "Mã loại thẻ", "Tên loại thẻ", "Ngày mở thẻ", "Hạn dùng thẻ",
                                "Số sách được mượn", "Thời gian mượn", "Giá tiền mở thẻ" };
                DefaultTableModel fault = new DefaultTableModel();
                for (String col : nameColumnLoaiThe) {
                        fault.addColumn(col);
                }
                DanhSachLoaiThe dslt = new DanhSachLoaiThe(new QLDG_PhanLoai_DAO().dsLoaiThe());
                fault.setRowCount(0);

                for (PhanLoaiThe the : dslt.getDsLoaiThe()) {
                        Vector t = new Vector<>();
                        t.add(the.getMaLoaiThe());
                        t.add(the.getTenLoaiThe());
                        t.add(the.getNgayMoThe());
                        t.add(the.getHanDungThe());
                        t.add(the.getSoLuongSachMuon());
                        t.add(the.getThoiGianMuonToiDa());
                        t.add(the.getGiaTienGiaHan());
                        fault.addRow(t);
                }
                tb.setModel(fault);
        }

        private void resetLoaiThe() {
                maLoaiField.setText("");
                tenLoaiField.setText("");
                tenLoaiField1.setText("");
                tenLoaiField3.setText("");
                soLuongField.setText("");
                thoiGianField.setText("");
                thoiGianField1.setText("");
        }

        // kiểm tra ngày đã đúng định dạng yyyy-mm-dd chưa
        public static boolean isDateValid(String dateStr) {
                // Định dạng mẫu yyyy-MM-dd
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                sdf.setLenient(false); // Vô hiệu hóa chế độ linh hoạt
                try {
                        // Thử chuyển đổi chuỗi thành ngày
                        sdf.parse(dateStr);
                        return true;
                } catch (ParseException e) {
                        return false; // Nếu có ngoại lệ ParseException, chuỗi không hợp lệ
                }
        }

        private void themmoidg3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_themmoidg3ActionPerformed
                if (maLoaiField.getText().equals("") || soLuongField.getText().equals("")
                                || thoiGianField.getText().equals("")) {
                        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Vui Lòng Nhập Đủ Thông Tin!");
                } else if (new QuanLiDocGia_DAO().checkMaTaiKhoan(maLoaiField.getText().toString())) {
                        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Người Dùng Hiện Chưa Có Tài Khoản!");
                } else if (new QLDG_PhanLoai_DAO().checkMaThe(maLoaiField.getText().toString())) {
                        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Mã Thẻ Đã Tồn Tại!!");
                } else if (!isDateValid(tenLoaiField1.getText()) || !isDateValid(tenLoaiField3.getText())) {
                        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),
                                        "Vui Lòng Nhập Ngày Theo Định Dạng 'yyyy-mm-dd'!");
                } else {
                        PhanLoaiThe loaiThe = new PhanLoaiThe();
                        loaiThe.setMaLoaiThe(maLoaiField.getText());
                        loaiThe.setTenLoaiThe(tenLoaiField.getText());
                        loaiThe.setNgayMoThe(tenLoaiField1.getText());
                        loaiThe.setHanDungThe(tenLoaiField3.getText());
                        loaiThe.setSoLuongSachMuon(Integer.parseInt(soLuongField.getText()));
                        loaiThe.setThoiGianMuonToiDa(Integer.parseInt(thoiGianField.getText()));
                        loaiThe.setGiaTienGiaHan(thoiGianField1.getText());

                        if (new QLDG_PhanLoai_DAO().Add_LoaiThe(loaiThe)) {
                                loadTableLoaiThe(tableDocgia3);
                                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Thêm Thành Công!");
                                resetLoaiThe();
                        } else {
                                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Quá Trình Thêm Xảy Ra Lỗi!");
                        }
                }
        }

        private void updatedg3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_updatedg3ActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_updatedg3ActionPerformed

        private void mokhoa4ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_mokhoa4ActionPerformed
                resetLoaiThe();
        }

        private void khoatk13ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_khoatk13ActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_khoatk13ActionPerformed

        private void maLoaiFieldActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_maLoaiFieldActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_maLoaiFieldActionPerformed

        private void tenLoaiFieldActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_tenLoaiFieldActionPerformed
                // TODO add your handling code here:
        }// GEN-LAST:event_tenLoaiFieldActionPerformed

        public static void main(String args[]) {
                java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                                new TrangChuThuThu().setVisible(true);
                        }
                });
        }

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JTable AuthManagerTable;
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
        private javax.swing.JLabel K_tieuDe18;
        private javax.swing.JLabel K_tieuDe19;
        private javax.swing.JPanel Panel_DanhSachPM14;
        private javax.swing.JPanel Panel_DanhSachPM15;
        private javax.swing.JPanel Panel_DanhSachPM17;
        private javax.swing.JPanel Panel_DanhSachPM18;
        private javax.swing.JButton btnH_luuSach2;
        private javax.swing.JButton btnH_suaSach2;
        private javax.swing.JButton btnH_themSach2;
        private javax.swing.JButton btnK_themPM31;
        private javax.swing.JButton btnK_themPM32;
        private javax.swing.JButton btnK_themPM33;
        private javax.swing.JButton btnK_themPM34;
        private javax.swing.JButton btnK_themPM37;
        private javax.swing.JButton btnK_themPM38;
        private javax.swing.JButton btnK_themPM39;
        private javax.swing.JButton btnK_themPM40;
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
        private javax.swing.JComboBox<String> cbb_chucNangThongKe6;
        private javax.swing.JComboBox<String> cbb_chucNangThongKe7;
        private javax.swing.JComboBox<String> cbb_chucNangThongKe8;
        private javax.swing.JTextField emailDocgia3;
        private javax.swing.ButtonGroup gioitinhbtngroup;
        private javax.swing.JRadioButton gioitinhnam16;
        private javax.swing.JRadioButton gioitinhnam17;
        private javax.swing.JRadioButton gioitinhnam20;
        private javax.swing.JRadioButton gioitinhnam21;
        private javax.swing.JRadioButton gioitinhnu16;
        private javax.swing.JRadioButton gioitinhnu17;
        private javax.swing.JRadioButton gioitinhnu20;
        private javax.swing.JRadioButton gioitinhnu21;
        private javax.swing.JTextField hanDungField;
        private javax.swing.JButton jButton1;
        private javax.swing.JButton jButton13;
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
        private javax.swing.JLabel jLabel118;
        private javax.swing.JLabel jLabel119;
        private javax.swing.JLabel jLabel120;
        private javax.swing.JLabel jLabel121;
        private javax.swing.JLabel jLabel122;
        private javax.swing.JLabel jLabel123;
        private javax.swing.JLabel jLabel124;
        private javax.swing.JLabel jLabel125;
        private javax.swing.JLabel jLabel126;
        private javax.swing.JLabel jLabel127;
        private javax.swing.JLabel jLabel128;
        private javax.swing.JLabel jLabel131;
        private javax.swing.JLabel jLabel132;
        private javax.swing.JLabel jLabel137;
        private javax.swing.JLabel jLabel138;
        private javax.swing.JLabel jLabel139;
        private javax.swing.JLabel jLabel140;
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
        private javax.swing.JScrollPane jScrollPane22;
        private javax.swing.JScrollPane jScrollPane23;
        private javax.swing.JScrollPane jScrollPane24;
        private javax.swing.JScrollPane jScrollPane25;
        private javax.swing.JScrollPane jScrollPane26;
        private javax.swing.JSeparator jSeparator5;
        private javax.swing.JSeparator jSeparator6;
        private javax.swing.JSeparator jSeparator7;
        private javax.swing.JTabbedPane jTPK_QuanLyPM4;
        private javax.swing.JTabbedPane jTPK_QuanLyPM5;
        private javax.swing.JTabbedPane jTP_main2;
        private javax.swing.JTabbedPane jTabbedPane4;
        private javax.swing.JTabbedPane jTabbedPane7;
        private javax.swing.JTable jTable1;
        private javax.swing.JTable jTable2;
        private javax.swing.JButton khoatk10;
        private javax.swing.JButton khoatk11;
        private javax.swing.JButton khoatk8;
        private javax.swing.JTextField maDocGiaField;
        private javax.swing.JTextField maLoaiField;
        private javax.swing.JTextField matKhauField;
        private javax.swing.JButton mokhoa3;
        private javax.swing.JButton mokhoa4;
        private javax.swing.JTextField ngaysinh2;
        private javax.swing.JTabbedPane quanlyttdg2;
        private javax.swing.JTextField sdt2;
        private javax.swing.JTextField soLuongField;
        private javax.swing.JTable tableDocgia2;
        private javax.swing.JTable tableDocgia3;
        private javax.swing.JTable tableSearchSach2;
        private javax.swing.JTable tabletkbandoc2;
        private javax.swing.JTable tabletksach2;
        private javax.swing.JTable tabletktienphat2;
        private javax.swing.JTable tblH_Sach2;
        private javax.swing.JTable tbl_DMSach4;
        private javax.swing.JTable tbl_DMSach5;
        private javax.swing.JTextField tenDocGiaField;
        private javax.swing.JTextField tenLoaiField;
        private javax.swing.JTextField tenLoaiField1;
        private javax.swing.JTextField tenLoaiField2;
        private javax.swing.JTextField tenLoaiField3;
        private javax.swing.JTextField textboxsearch2;
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
        private javax.swing.JTextField txt_timkiemDMSach27;
        private javax.swing.JTextField txt_timkiemDMSach28;
        private javax.swing.JButton updatedg2;
        private javax.swing.JButton updatedg3;
        // End of variables declaration//GEN-END:variables
}
