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
    String userID = "";

    @GetMapping("/users/{userId}/computers")
    public List<Computer> getComputerList(@PathVariable String userId){
        if(compList.isEmpty())
            compList.addAll(uc.getUsers().stream().filter(user -> user.getUserId().equals(Integer.parseInt(userId)))
                .findFirst().get().getComputers().stream().toList());
        else if (!userId.equals(userID)) {
            compList = new ArrayList<>();
            compList.addAll(uc.getUsers().stream().filter(user -> user.getUserId().equals(Integer.parseInt(userId)))
                    .findFirst().get().getComputers().stream().toList());
        }
        return compList;
    }

    @GetMapping("/users/{userId}/computers/{compId}")
    public Computer getComputer(@PathVariable String userId, @PathVariable String compId){
        if(compList.isEmpty())
            compList.addAll(uc.getUsers().stream().filter(user -> user.getUserId().equals(Integer.parseInt(userId)))
                    .findFirst().get().getComputers().stream().toList());
        else if (!userId.equals(userID)) {
            compList = new ArrayList<>();
            compList.addAll(uc.getUsers().stream().filter(user -> user.getUserId().equals(Integer.parseInt(userId)))
                    .findFirst().get().getComputers().stream().toList());
        }
        return compList.stream().filter(computer -> computer.getId().equals(compId)).findFirst().get();
    }

    @PostMapping("/users/{userId}/computers")
    public void addComputer(@PathVariable String userId, @RequestBody Computer computer){
        if (compList.isEmpty())
            compList.addAll(uc.getUsers().stream().filter(user -> user.getUserId().equals(Integer.parseInt(userId)))
                .findFirst().get().getComputers().stream().toList());
        else if (!userId.equals(userID)) {
            compList = new ArrayList<>();
            compList.addAll(uc.getUsers().stream().filter(user -> user.getUserId().equals(Integer.parseInt(userId)))
                    .findFirst().get().getComputers().stream().toList());
        }
        User user = uc.getUser(userId);
        compList.add(computer);
        user.setComputers(compList);
        uc.updateUser(userId, user);
    }

    @PutMapping("/users/{userId}/computers/{compId}")
    public void updateComputer(@PathVariable String userId, @PathVariable String compId, @RequestBody Computer computer){
        if (compList.isEmpty())
            compList.addAll(uc.getUsers().stream().filter(user -> user.getUserId().equals(Integer.parseInt(userId)))
                    .findFirst().get().getComputers().stream().toList());
        else if (!userId.equals(userID)) {
            compList = new ArrayList<>();
            compList.addAll(uc.getUsers().stream().filter(user -> user.getUserId().equals(Integer.parseInt(userId)))
                    .findFirst().get().getComputers().stream().toList());
        }
        compList.stream().filter(comp -> comp.getId().equals(compId)).findFirst().get().setComputer(computer);
        User user = uc.getUser(userId);
        user.setComputers(compList);
        uc.updateUser(userId,user);
    }

    @DeleteMapping("/users/{userId}/computers/{compId}")
    public void deleteComputer(@PathVariable String userId, @PathVariable String compId){
        if (compList.isEmpty())
            compList.addAll(uc.getUsers().stream().filter(user -> user.getUserId().equals(Integer.parseInt(userId)))
                    .findFirst().get().getComputers().stream().toList());
        else if (!userId.equals(userID)) {
            compList = new ArrayList<>();
            compList.addAll(uc.getUsers().stream().filter(user -> user.getUserId().equals(Integer.parseInt(userId)))
                    .findFirst().get().getComputers().stream().toList());
        }
        compList.removeIf(computer -> computer.getId().equals(compId));
    }
}
