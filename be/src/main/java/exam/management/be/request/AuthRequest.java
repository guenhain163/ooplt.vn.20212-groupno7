package exam.management.be.request;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class AuthRequest {
    @NotNull(message = "Email không được bỏ trống.")
    @Email(message = "Địa chỉ email không hợp lệ.")
    @Length(min = 6, max = 50)
    private String email;

    @NotNull(message = "Mật khẩu không được bỏ trống.")
    @Length(min = 5, max = 32, message = "Mật khẩu phải có độ dài từ 6 đến 32 ký tự.")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail() {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
