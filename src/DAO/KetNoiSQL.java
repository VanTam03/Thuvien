// /*
//  * To change this license header, choose License Headers in Project Properties.
//  * To change this template file, choose Tools | Templates
//  * and open the template in the editor.
//  */
// package DAO;

// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.util.logging.Level;
// import java.util.logging.Logger;
// //password máy Trâm: duckg0946945409

// public class KetNoiSQL {
//     public static String databaseName = "QuanLyThuVien";

//     public static Connection getConnection() {
//         String url = "net.sourceforge.jtds.jdbc.Driver";
//         try {
//             Class.forName(url);
//             String dbUrl = "jdbc:sqlserver://DESKTOP-KRD2V7D: 1433;" +
//                     "user=sa; password=duckg0946945409; databaseName=QuanLyThuVien; encrypt=false";
//             return DriverManager.getConnection(dbUrl);

//             // return DriverManager.getConnection(dbUrl,user, pass);

//         } catch (Exception ex) {
//             Logger.getLogger(KetNoiSQL.class.getName()).log(Level.SEVERE, null, ex);
//             System.out.println("Connected to SQL server in catch");
//             return null;

//         }
//     }

//     public static void main(String[] args) {
//         KetNoiSQL.getConnection();
//         System.out.println("Connected to SQL server");
//     }
// }
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
//password máy Trâm: duckg0946945409

public class KetNoiSQL {
    public static String databaseName = "QuanLyThuVien";

    public static Connection getConnection() {
        String url = "net.sourceforge.jtds.jdbc.Driver";
        try {
            Class.forName(url);
            String dbUrl = "jdbc:jtds:sqlserver://localhost:1433/" + databaseName;
            return DriverManager.getConnection(dbUrl, "sa", "100903");

            // return DriverManager.getConnection(dbUrl,user, pass);

        } catch (Exception ex) {
            Logger.getLogger(KetNoiSQL.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Connected to SQL server in catch");
            return null;

        }
    }

    public static void main(String[] args) {
        KetNoiSQL.getConnection();
        System.out.println("Connected to SQL server");
    }
}
