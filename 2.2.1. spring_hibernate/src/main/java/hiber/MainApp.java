package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car car1 = new Car("Model1", 10);
      Car car2 = new Car("Model2", 20);
      Car car3 = new Car("Model3", 30);
      Car car4 = new Car("Model4", 40);

      User user1 = new User("Andrey", "Petrov", "petrov@mail.ru");
      user1.setCar(car1);
      User user2 = new User("Anton", "Ivanov", "ivanov@mail.ru");
      user2.setCar(car2);
      User user3 = new User("Alex", "Pavlov", "pavlov@mail.ru");
      user3.setCar(car3);
      User user4 = new User("Dmitry", "Gromov", "gromov@mail.ru");
      user4.setCar(car4);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar());
         System.out.println();
      }

      User userCar = userService.getUserByCar("Model1", 10);
      System.out.println(userCar);



      context.close();
   }
}
