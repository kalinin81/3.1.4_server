package web.controller;

import com.google.gson.Gson;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


@RestController
@RequestMapping("/rest")
public class UserRestController {

    private UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUsers() {
        Gson gson = new Gson();
        String json = gson.toJson(userService.getAllUsers());
        return json;
    }

    private String getJson(Object object) {
        Gson gson = new Gson();
        String json = gson.toJson(object);
        return json;
    }
    @PostMapping("admin/edit")
    public String editUser(@RequestBody User user) {
        userService.update(user);
        return getJson(user);
    }

    @PostMapping("admin/delete")
    public String deleteUser(@RequestBody String id){
        userService.deleteUser(Long.parseLong(id));
        return id;
    }

    @PostMapping("admin/add")
    public String addUser(@RequestBody User user){
        userService.insert(user);
        return getJson(user);
    }

    @PostMapping("findByUsername")
    public User findByUsername(@RequestBody String login) {
        User byUsername = userService.findByUsername(login);
        return byUsername;
    }

    @GetMapping("allRoles")
    public String getAllRoles() {
        Gson gson = new Gson();
        return gson.toJson(userService.getAllRoles());
    }

}
