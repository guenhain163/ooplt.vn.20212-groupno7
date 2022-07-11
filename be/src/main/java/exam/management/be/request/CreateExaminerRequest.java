package exam.management.be.request;

import com.sun.istack.Nullable;
import net.bytebuddy.utility.nullability.MaybeNull;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.List;

public class CreateExaminerRequest {
    @NotNull
    @Length(min = 2, max = 50)
    private String name;

    @NotNull
    @Length(min = 6, max = 50)
    private String email;

    private List<Integer> modules;

    @NotNull
//    @Length(min = 10, max = 12)
    private String phone;

    @NotNull
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
