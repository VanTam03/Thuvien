/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import DAO.login_dao;
import DTO.QuanLy;
import DTO.TaiKhoan;
import java.util.List;

/**
 *
 * @author tiena
 */
public class Login_BLL {
    login_dao loginDao = new login_dao();

    public boolean checkUser(String user, String pass, String position) {
        List<QuanLy> adminList = loginDao.getall_Admin();
        List<QuanLy> thuThuList = loginDao.getall_Thuthu();
        List<TaiKhoan> docGiaList = loginDao.getall_Docgia();

        if ("Admin".equals(position)) {
            for (QuanLy quanLy : adminList) {
                System.err.println(quanLy.getMaQuanly());
                System.err.println(quanLy.getMatKhau());

                if (user.equalsIgnoreCase(quanLy.getMaQuanly()) && pass.equalsIgnoreCase(quanLy.getMatKhau())) {
                    System.out.println("Đăng nhập thành công với tư cách Admin");
                    return true;
                }
            }
        } 
        else if ("Thủ thư".equals(position)) {
            for (QuanLy quanLy : thuThuList) {
                System.err.println(quanLy.getMaQuanly());
                System.err.println(quanLy.getMatKhau());
                if (user.equalsIgnoreCase(quanLy.getMaQuanly()) && pass.equalsIgnoreCase(quanLy.getMatKhau())) {
                    
                    System.out.println("Đăng nhập thành công với tư cách Thủ thư");
                    return true;
                }
            }
        } 
        else {
            for (TaiKhoan taiKhoan : docGiaList) {
                if (user.equalsIgnoreCase(taiKhoan.getMaTaikhoan()) && pass.equalsIgnoreCase(taiKhoan.getMatKhau())) {
                    System.out.println("Đăng nhập thành công với tư cách Độc giả");
                    return true;
                }
            }
        }
        return false;
    }

    
}
