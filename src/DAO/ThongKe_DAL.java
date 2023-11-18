/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tiena
 */
public class ThongKe_DAL {
    public List<String> getAllCategories() {
        List<String> categories = new ArrayList<>();
        Connection connection = KetNoiSQL.getConnection();
        try {           
            Statement statement = connection.createStatement();
            String query = "SELECT maTheLoai FROM phanloaisach";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String maTheLoai = resultSet.getString("maTheLoai");
                categories.add(maTheLoai);
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }
}
