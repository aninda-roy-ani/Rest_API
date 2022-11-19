package main.rest.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userr;

    @GetMapping("/users")
    public List<User> getUsers(){
        return userr.findAll();
    }

    @GetMapping("/users/{userId}")
    public User getUser(@PathVariable String userId){
        //return userList.stream().filter(user -> user.getUserId().equals(Integer.parseInt(userId))).findFirst().get();

        return userr.findById(Integer.parseInt(userId)).orElseThrow();
    }

    @PostMapping("/users")
    public void addUser(@RequestBody User user){
        //userList.add(user);
        userr.save(user);
    }

    @PutMapping("/users/{userId}")
    public void updateUser(@PathVariable String userId, @RequestBody User user){
        //userList.stream().filter(user1 -> user1.getUserId().equals(Integer.parseInt(userId))).
        //        findFirst().get().setUser(user);
        userr.save(user);
    }

    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable String userId){
        //userList.removeIf(user -> user.getUserId().equals(Integer.parseInt(userId)));
        userr.deleteById(Integer.parseInt(userId));
    }

}
