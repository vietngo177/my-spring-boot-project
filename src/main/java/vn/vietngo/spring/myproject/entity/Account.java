package vn.vietngo.spring.myproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.lang.NonNull;

import java.util.Collection;

@Entity
@Table(name="accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="tendangnhap")
    @NotNull(message = "Vui lòng điền vào trường này")
    private String tenDangNhap;

    @Column(name="matkhau")
    @NotNull(message = "Vui lòng điền vào trường này")
    @Size(min = 6, message = "Độ dài tối thiểu là 6 kí tự")
//    @Pattern(regexp = "^(?=.*[0-9])(?=.*[@#$%^&+=!])(?=\\S+$).*$",
//            message = "Mật khẩu phải chứa ít nhất 1 chữ số và 1 ký tự đặc biệt")
    private String matKhau;

    @Column(name="hovaten")
    private String hoVaTen;

    @Column(name="email")
    @NotNull(message = "Vui lòng điền vào trường này")
    @Email
    private String email;

    @Column(name="active")
    private boolean active = true;

    @Column(name="resetpasswordtoken")
    private String resetPasswordToken;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name="accounts_roles",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name="role_id")
    )
    private Collection<Role> roles;

    public Account() {
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public Account(Long id, String tenDangNhap, String matKhau, String hoVaTen, String email, Collection<Role> roles) {
        this.id = id;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.hoVaTen = hoVaTen;
        this.email = email;
        this.roles = roles;
    }

    public Account(String email, boolean gioiTinh, String hoVaTen, String matKhau, String tenDangNhap) {
        this.email = email;
        this.hoVaTen = hoVaTen;
        this.matKhau = matKhau;
        this.tenDangNhap = tenDangNhap;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHoVaTen() {
        return hoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    public  String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", tenDangNhap='" + tenDangNhap + '\'' +
                ", matKhau='" + matKhau + '\'' +
                ", hoVaTen='" + hoVaTen + '\'' +
                ", email='" + email + '\'' +
                ", active='" + active + '\'' +
                '}';
    }
}
