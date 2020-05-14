package evertonsavio.dev.kanbanfullstack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@SpringBootApplication
public class KanbanfullstackApplication {

	public static void main(String[] args) {
		SpringApplication.run(KanbanfullstackApplication.class, args);
	}

}
