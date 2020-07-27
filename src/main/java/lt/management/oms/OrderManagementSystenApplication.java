package lt.management.oms;

import lt.management.oms.enums.Status;
import lt.management.oms.model.Role;
import lt.management.oms.model.User;
import lt.management.oms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;

import java.time.LocalDate;

@SpringBootApplication
public class OrderManagementSystenApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(OrderManagementSystenApplication.class, args);
	}

	UserService service;

	@Autowired
	public void setService( @Lazy UserService service){
		this.service = service;
	}

	@Override
	public void run(String... args) throws Exception {
		Role role1= new Role();
		role1.setName("ROLE_ADMIN");
		role1.setStatus(Status.ACTIVE);
		role1.setCreateDate(LocalDate.now());

		Role role2= new Role();
		role2.setName("ROLE_USER");
		role2.setStatus(Status.ACTIVE);
		role2.setCreateDate(LocalDate.now());

		service.createRole(role1);
		service.createRole(role2);

		User user1=new User();
		user1.setCreateDate(LocalDate.now());
		user1.setUsername("admin");
		user1.setFirstName("Admin");
		user1.setLastName("Admin");
		user1.setEmail("some@email.com");
		user1.setPassword("admin");
		service.register(user1,"ROLE_ADMIN");

		User user2=new User();
		user2.setCreateDate(LocalDate.now());
		user2.setUsername("user");
		user2.setFirstName("User");
		user2.setLastName("User");
		user2.setEmail("some@email.com");
		user2.setPassword("user");
		service.register(user2,"ROLE_USER");
	}
}
