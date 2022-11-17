package main.rest.computer;

import main.rest.user.User;
import main.rest.user.UserController;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ComputerController {

    UserController uc = new UserController();

    List<Computer> compList = new ArrayList<>();

    @GetMapping("/users/{userId}/computers")
    public List<Computer> getComputerList(@PathVariable String userId){
        if(compList.isEmpty())
            compList.addAll(uc.getUsers().stream().filter(user -> user.getUserId().equals(Integer.parseInt(userId)))
                .findFirst().get().getComputers().stream().toList());
        return compList;
    }

    @PostMapping("/users/{userId}/computers")
    public void addComputer(@PathVariable String userId, @RequestBody Computer computer){
        if (compList.isEmpty())
            compList.addAll(uc.getUsers().stream().filter(user -> user.getUserId().equals(Integer.parseInt(userId)))
                .findFirst().get().getComputers().stream().toList());
        User user = uc.getUser(userId);
        System.out.println(user.toString());
        compList.add(computer);
        user.setComputers(compList);
        uc.updateUser(userId, user);
    }
}
