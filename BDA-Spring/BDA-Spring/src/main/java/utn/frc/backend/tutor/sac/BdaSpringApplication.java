package utn.frc.backend.tutor.sac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import utn.frc.backend.tutor.sac.dal.EMBaseRepository;

@SpringBootApplication
//@EnableJpaRepositories(repositoryBaseClass = EMBaseRepository.class)
public class BdaSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(BdaSpringApplication.class, args);
    }

}
