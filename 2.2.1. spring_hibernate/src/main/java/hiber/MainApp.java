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

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");

      Car bmw = new Car("BMW X", 7);
      Car mercedes = new Car("Mercedes", 600);
      Car volvo = new Car("Volvo XC", 90);

      userService.add(user1.setUserCar(bmw).setUser(user1));
      userService.add(user2.setUserCar(mercedes).setUser(user2));
      userService.add(user3.setUserCar(volvo).setUser(user3));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+(user.getUserCar() != null ? user.getUserCar().getModel() : "No car:("));
         System.out.println();
      }

      List<User> usersCar = userService.getUserIfCar("BMW X", 7);
      for (User user : usersCar) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+(user.getUserCar() != null ? user.getUserCar().getModel() : "No car:("));
         System.out.println();
      }
      context.close();
   }
}
