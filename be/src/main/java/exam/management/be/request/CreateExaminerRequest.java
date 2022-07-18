package exam.management.be.request;

import net.bytebuddy.utility.nullability.MaybeNull;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.List;

public class CreateExaminerRequest {
    @NotNull(message = "Tên không được bỏ trống.")
    @Length(min = 2, max = 50, message = "Độ dài tên không phù hợp.")
    private String name;

    @NotNull(message = "Email không được bỏ trống.")
    @Length(min = 6, max = 50, message = "Độ dài email không hợp lệ.")
    private String email;

    private List<Integer> modules;

    @NotNull(message = "Số điện thoại không được bỏ trống.")
    @Length(min = 10, max = 12, message = "Số điện thoại không hợp lệ.")
    private String phone;

    @NotNull(message = "Phòng làm việc không được bỏ trống.")
    private String workRoom;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @MaybeNull
    public List<Integer> getModules() {
        return modules;
    }

    public void setModules(List<Integer> modules) {
        this.modules = modules;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWorkRoom() {
        return workRoom;
    }

    public void setWorkRoom(String workRoom) {
        this.workRoom = workRoom;
    }
}
