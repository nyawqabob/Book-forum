package by.iba.boot_learning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication

public class SpringBootWebApplication implements CommandLineRunner {


    @Autowired
    private ApplicationContext applicationContext;

    public static void main(String[] args) throws Exception {

        SpringApplication.run(SpringBootWebApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        String[] beans = applicationContext.getBeanDefinitionNames();
        for (String bean : beans) {
            if(applicationContext.getBean(bean).getClass().toString().contains("by.iba.boot_learning"))
                System.out.println(applicationContext.getBean(bean).getClass());

        }
    }

}

