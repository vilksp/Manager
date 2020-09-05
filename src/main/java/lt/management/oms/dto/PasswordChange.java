package lt.management.oms.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class PasswordChange {
	
	@NotNull private String username;
	@NotBlank(message="Current Password must not be blank")
	private String currentPassword;

	@NotBlank(message="New Password must not be blank")
	private String newPassword;

	@NotBlank(message="Confirm Password must not be blank")
	private String confirmPassword;

	public PasswordChange(String username) {this.username = username;}

}
