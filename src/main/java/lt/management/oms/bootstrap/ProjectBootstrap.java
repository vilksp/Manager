package lt.management.oms.bootstrap;

import lt.management.oms.model.Address;
import lt.management.oms.model.Project;
import lt.management.oms.model.Task;
import lt.management.oms.repository.ProjectRepository;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ProjectBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final ProjectRepository projectRepository;


    public ProjectBootstrap(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;

    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        projectRepository.saveAll(getProjects());
        event.getApplicationContext();

    }

    private List<Project> getProjects() {
        List<Project> projectList = new ArrayList<>(2);
        List<Task> taskList = new ArrayList<>();

        Project fakeProjectOne = new Project();
        Task taskOne = new Task();
        //   taskOne.setId(1l);
        taskOne.setTaskName("Test 1");
        taskOne.setProject(fakeProjectOne);
        taskList.add(taskOne);

        Task taskTwo = new Task();
        //   taskTwo.setId(2l);
        taskTwo.setTaskName("Test 2");
        taskTwo.setProject(fakeProjectOne);
        taskList.add(taskTwo);


        Address fakeAdressOne = new Address();
        fakeAdressOne.setId(1L);
        fakeAdressOne.setCity("Test City");
        fakeAdressOne.setHouse_number(15);
        fakeAdressOne.setProject(fakeProjectOne);

        fakeProjectOne.setId(1L);
        fakeProjectOne.setAddress(fakeAdressOne);
        fakeProjectOne.setBudget(15000);
        fakeProjectOne.setDeadline(LocalDate.now().plusDays(30));
        fakeProjectOne.setStartDate(LocalDate.now());
        fakeProjectOne.setTasks(taskList);

        projectList.add(fakeProjectOne);

        Project fakeProjectTwo = new Project();

        fakeProjectTwo.setId(2l);

        Task taskThree = new Task();
        //  taskThree.setId(3L);
        taskThree.setTaskName("Task number 3");
        taskThree.setProject(fakeProjectTwo);


        Address fakeAddressTwo = new Address();
        fakeAddressTwo.setId(2L);
        fakeAddressTwo.setCity("TEST CITY");
        fakeAddressTwo.setHouse_number(51);
        fakeAddressTwo.setProject(fakeProjectTwo);
        fakeProjectTwo.setAddress(fakeAddressTwo);
        fakeProjectTwo.setStartDate(LocalDate.now().plusDays(30));
        fakeProjectTwo.setDeadline(fakeProjectTwo.getStartDate().plusDays(68));
        fakeProjectTwo.setTasks(Collections.singletonList(taskThree));

        projectList.add(fakeProjectTwo);


        return projectList;
    }


}
