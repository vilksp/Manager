package lt.management.oms.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lt.management.oms.model.User;

import java.time.LocalDate;

/**
 * DTO class for user requests by ROLE_USER
 *
 * @author Edgaras Venzlauskas
 * @version 1.0
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String description;

    public User toUser(){
        User user = new User();
        user.setId(id);
        user.setCreateDate(LocalDate.now());
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setDescription(description);
        return user;
    }

    public static UserDto fromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setDescription(user.getDescription());
        return userDto;
    }
}