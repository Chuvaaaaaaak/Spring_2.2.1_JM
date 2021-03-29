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

      User user1 = new User("user1", "user1", "user1@1");
      User user2 = new User("user2", "user2", "user2@2");
      User user3 = new User("user3", "user3", "user@3");
      User user4 = new User("user4", "user4", "user4@4");

      user1.setCar(new Car("car1", 11));
      user2.setCar(new Car("car2", 22));
      user3.setCar(new Car("car3", 33));
      user4.setCar(new Car("car4", 44));
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
         System.out.println();
      }

         User user = userService.getUserByCar("car3", 33);
         System.out.println("The requested car is owned by user:");
         System.out.println(("Id: " + user.getId() + ", "
                 + "first Name: = " + user.getFirstName() + ", "
                 + "last name: " + user.getLastName() + ", "
                 + "email = " + user.getEmail()));

      context.close();
   }
}
