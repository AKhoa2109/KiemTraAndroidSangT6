package vn.ute.KiemTraAndroid.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@AllArgsConstructor
//@NoArgsConstructor
public class UserResponse {
    private Long user_id;
    private String name;
    private String email;
    private String image;

    public UserResponse(Long user_id, String name, String email, String image) {
        this.user_id = user_id;
        this.name = name;
        this.email = email;
        this.image = image;
    }

    public UserResponse() {
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
