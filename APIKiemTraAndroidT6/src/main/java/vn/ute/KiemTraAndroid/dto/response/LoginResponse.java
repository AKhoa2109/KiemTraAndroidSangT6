/*Huynh Thai Toan 22110436*/
package vn.ute.KiemTraAndroid.dto.response;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.ute.KiemTraAndroid.entity.Product;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
	private String message;
    private String email;
    private int userId;
}
/*Huynh Thai Toan 22110436*/