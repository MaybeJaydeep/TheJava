import DI_Bean.SpringLaptop;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"DI_Bean", "IoC"})
public class AppConfig {
}

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);
        
        SpringLaptop laptop = context.getBean(SpringLaptop.class);
        laptop.makeLaptop();
    }
}