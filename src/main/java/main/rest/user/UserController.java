package main.rest.user;

import main.rest.computer.Computer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class UserController {

    static List<User> userList = new ArrayList<>(Arrays.asList(
            new User(1, "Linda", new ArrayList<>(
                    Arrays.asList(
                            new Computer("a", "Fujitsu")
                    )
            )),
            new User(2, "Adam", new ArrayList<>(
                    Arrays.asList(
                            new Computer("a", "Fujitsu"),
                            new Computer( "b" , "Dell")
                    )
            )),
            new User( 3, "Montana" , new ArrayList<>(
                    Arrays.asList(
                            new Computer( "b" , "Dell")
                    )
            )))
    );

    @GetMapping("/users")
    public List<User> getUsers(){
        return userList;
    }

    @GetMapping("/users/{userId}")
    public User getUser(@PathVariable String userId){
        return userList.stream().filter(user -> user.getUserId().equals(Integer.parseInt(userId))).findFirst().get();
    }

    @PostMapping("/users")
    public void addUser(@RequestBody User user){
        System.out.println(user.getUserId());
        System.out.println(user.getName());
        userList.add(user);
    }

    @PutMapping("/users/{userId}")
    public void updateUser(@PathVariable String userId, @RequestBody User user){
        userList.stream().filter(user1 -> user1.getUserId().equals(Integer.parseInt(userId))).
                findFirst().get().setUser(user);
    }

    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable String userId){
        userList.removeIf(user -> user.getUserId().equals(Integer.parseInt(userId)));
    }

}
