package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import system.model.User;
import system.service.UserService;

import java.util.List;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/userssystem")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public String getAllUsers(){
        return userService.getAllUsers().toString();
    }

    @RequestMapping(value = "/validate", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView validateUser(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userfromserver", new User());
        modelAndView.setViewName("user_check_page");
        return modelAndView;
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    @ResponseBody
    public String checkUsers(@ModelAttribute("userfromserver") User user){
        if (!isUserValid(user)) {
            return "invalid";
        }
        if (userService.getAllUsers().contains(user)) {
            return "valid";
        }
        return "invalid";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView addUser(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("newuser", new User());
        modelAndView.setViewName("user_add_page");
        return modelAndView;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public String addUser(@ModelAttribute("newuser") User user){
        if (!isUserValid(user)) {
            return "incorrect input";
        }
        for (User user2: userService.getAllUsers()) {
            if (user.getName().equals(user2.getName())) {
                return "User with the same name already exist!";
            }
        }
        userService.addUser(user);
        return "Used successfully added!";
    }

    public boolean isUserValid(User user) {
        String regex = ".*[^a-zA-Z0-9].*";
        if (user.getName().equals("") || user.getPassword().equals("")) {
            return false;
        }
        if (Pattern.matches(regex, user.getName()) || Pattern.matches(regex, user.getPassword())) {
            return false;
        }
        return true;
    }

}
