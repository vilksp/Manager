package lt.management.oms;

import lt.management.oms.enums.Priority;
import lt.management.oms.enums.Status;
import lt.management.oms.model.Address;
import lt.management.oms.model.Project;
import lt.management.oms.model.Role;
import lt.management.oms.model.User;
import lt.management.oms.service.ProjectService;
import lt.management.oms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDate;

@SpringBootApplication
public class OrderManagementSystemApplication implements CommandLineRunner {

    UserService service;
    ProjectService projectService;

    public static void main(String[] args) {
        SpringApplication.run(OrderManagementSystemApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:3000");
            }
        };
    }

    @Autowired
    public void setService(@Lazy UserService service) {
        this.service = service;
    }

    @Autowired
    public void setProjectService(@Lazy ProjectService projectService) {
        this.projectService = projectService;
    }

    @Override
    public void run(String... args) {
        Role role1 = new Role();
        role1.setName("ROLE_ADMIN");
        role1.setStatus(Status.ACTIVE);
        role1.setCreateDate(LocalDate.now());

        Role role2 = new Role();
        role2.setName("ROLE_USER");
        role2.setStatus(Status.ACTIVE);
        role2.setCreateDate(LocalDate.now());

        service.createRole(role1);
        service.createRole(role2);

        User user1 = new User();
        user1.setCreateDate(LocalDate.now());
        user1.setUsername("admin");
        user1.setFirstName("Admin");
        user1.setLastName("Admin");
        user1.setEmail("some@email.com");
        user1.setPassword("admin");
        service.register(user1, "ROLE_ADMIN");

        User user2 = new User();
        user2.setCreateDate(LocalDate.now());
        user2.setUsername("user");
        user2.setFirstName("User");
        user2.setLastName("User");
        user2.setEmail("some@email.com");
        user2.setPassword("user");
        service.register(user2, "ROLE_USER");

        User user3 = new User();
        user3.setCreateDate(LocalDate.now());
        user3.setUsername("jonny");
        user3.setFirstName("Jon");
        user3.setLastName("Doe");
        user3.setEmail("jondoe@email.com");
        user3.setPassword("jonny");
        user3.setDescription("I am jonny and i am en engineer");

        service.register(user3, "ROLE_USER");

        Address address = new Address();
        address.setCity("Vilnius");
        address.setHouse_number(22);
        address.setStreet("Some street 123");
        address.setCreateDate(LocalDate.now());
        address.setUpdateDate(LocalDate.now());

        Project task = new Project();
        task.setCreateDate(LocalDate.now());
        task.setUpdateDate(LocalDate.now());
        task.setName("Some task");
        task.setStatus(Status.ACTIVE);
        task.setPriority(Priority.HIGH);

        Project project = new Project();
        project.setName("Apartment 22001");
        project.setPriority(Priority.LOW);
        project.setCreateDate(LocalDate.now());
        project.setUpdateDate(LocalDate.now());
        project.setBudget(100000);
        project.setDeadline(LocalDate.of(2020, 12, 1));
        project.setStatus(Status.ACTIVE);

        address.setProject(project);
        //   task.setProject(project);
        project.setAddress(address);
        //  project.addProjectToList(task);

        projectService.createProject(project);

    }
}
