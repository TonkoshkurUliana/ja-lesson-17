package app;

import domain.University;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.expression.ParseException;
import service.UniversityService;

@SpringBootApplication
@ComponentScan("service")
@EntityScan("domain")
@EnableJpaRepositories("dao")
public class Application {
    public static void main(String[] args) throws ParseException {
        ConfigurableApplicationContext cx =  SpringApplication.run(Application.class, args);

        UniversityService universityService = cx.getBean(UniversityService.class);

        University university_1 = new University();
        university_1.setName("KNU");
        university_1.setAddressUniversity("Kriviy Rih");
        university_1.setAmountStudents(1500);
        university_1.setLevelAccreditation(4);
        university_1.setNumberInstitutes(3);

        University university_2 = new University();
        university_2.setName("LPU");
        university_2.setAddressUniversity("Lviv");
        university_2.setAmountStudents(2400);
        university_2.setLevelAccreditation(4);
        university_2.setNumberInstitutes(8);

        System.out.println("-----------------------------Creat-----------------------------------");
        universityService.save(university_1);
        universityService.save(university_2);
        System.out.println(university_1);
        System.out.println(university_2);

        System.out.println("-----------------------------Read-----------------------------------");
        universityService.findAll().stream().forEach( System.out::println);

        System.out.println("-----------------------------Update-------------------------------------");
        university_2.setNumberInstitutes(17);
        university_2.setLevelAccreditation(5);
        universityService.update(university_2);
        universityService.findAll().stream().forEach( System.out::println);

        System.out.println("------------------------------Other--------------------------------");
        universityService.findByName("KNU").stream().forEach( System.out::println);
        universityService.findByAmountStudents(1500).stream().forEach( System.out::println);
        universityService.findByAddressUniversity("Lviv").stream().forEach( System.out::println);
        System.out.println(universityService.findById(52));

        System.out.println("-----------------------------Delete----------------------------------");
        universityService.deleteById(52);
        universityService.findAll().stream().forEach( System.out::println);
    }
}
