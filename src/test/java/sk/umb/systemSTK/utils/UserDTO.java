package sk.umb.systemSTK.utils;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    Long userId;
    String userName;
    @JsonIgnore
    String password;
}
