package View;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class check {
  // kiểm tra định dạng sdt
  public boolean isValidPhoneNumber(String phoneNumber) {
    // Sử dụng biểu thức chính quy để kiểm tra định dạng số điện thoại (di động Việt
    // Nam)
    String regex = "0[0-9]{9}"; // Đây là định dạng số điện thoại di động 10 chữ số của Việt Nam
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(phoneNumber);
    return matcher.matches();
  }

  // kiểm tra định dạng email
  public boolean isValidGmail(String email) {
    // Sử dụng biểu thức chính quy để kiểm tra định dạng email Gmail
    String regex = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(email);
    return matcher.matches();
  }

  // kiểm tra ngày đã đúng định dạng yyyy-mm-dd chưa
  public boolean isDateValid(String dateStr) {
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
}
