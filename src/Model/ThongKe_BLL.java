/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import DAO.ThongKe_DAL;
import java.util.List;

/**
 *
 * @author tiena
 */
public class ThongKe_BLL {
    private ThongKe_DAL thongKe_DAL;

    public ThongKe_BLL() {
        thongKe_DAL = new ThongKe_DAL();
    }
    public List<String> getAllCategories() {
        return thongKe_DAL.getAllCategories();
    }
}
