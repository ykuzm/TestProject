package system.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import system.additional.TrainSearch;
import system.model.Station;
import system.model.Train;
import system.model.User;
import system.service.ScheduleService;
import system.service.TicketService;
import system.service.TrainService;
import system.service.UserService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes(value="role")
@RequestMapping(value = "/railway", method = RequestMethod.GET)
public class LogInController {

    private static final Logger logger = Logger.getLogger(LogInController.class);

    @Autowired
    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Returns index page
     *
     * @return index page
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("trainSearch", new TrainSearch());
        modelAndView.setViewName("index");
        return modelAndView;
    }

    /**
     * Returns user_logout_result_page
     *
     * @param sessionStatus session status
     * @return user_logout_result_page
     */
    @RequestMapping(value = "/logout/result", method = RequestMethod.GET)
    public String logout(SessionStatus sessionStatus){
        sessionStatus.setComplete();
        return "logout_page";
    }

    /**
     * Returns view of login form
     *
     * @return view of user_login_page
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(){
        logger.info("Trying to log in.");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("login_page");
        return modelAndView;
    }

    /**
     * Returns user_login_result_page
     *
     * @param userDetails user details
     * @return user_login_result_page
     */
    @RequestMapping(value = "/login/result", method = RequestMethod.POST)
    public ModelAndView loginResult(@AuthenticationPrincipal UserDetails userDetails){
        User user = userService.getUserByLogin(userDetails.getUsername());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("role", user.isAdmin() ? "admin" : "user");
        modelAndView.setViewName("login_result_page");
        return modelAndView;
    }

    /**
     * Returns user_login_error_page
     *
     * @return user_login_error_page
     */
    @RequestMapping(value = "/login/error", method = RequestMethod.POST)
    public String loginError(){
        return "login_error_page";
    }

    /**
     * Returns view of admin panel
     *
     * @param userDetails user details
     * @return account_admin_page
     */
    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public ModelAndView adminAccount(@AuthenticationPrincipal UserDetails userDetails){
        User user = userService.getUserByLogin(userDetails.getUsername());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("admin_panel");
        return modelAndView;
    }

    /**
     * Returns view of user panel
     *
     * @param userDetails user details
     * @return account_user_page
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ModelAndView userAccount(@AuthenticationPrincipal UserDetails userDetails){
        User user = userService.getUserByLogin(userDetails.getUsername());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("user_panel");
        return modelAndView;
    }

    /**
     * Returns view of register form
     *
     * @return user_register_page
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("register_page");
        return modelAndView;
    }

    /**
     * Returns view of the registration result
     *
     * @param user user
     * @return view of the registration result
     */
    @RequestMapping(value = "/register/result", method = RequestMethod.POST)
    public ModelAndView registerResult(@ModelAttribute("user") User user){
        ModelAndView modelAndView = new ModelAndView();
        try {
            userService.addUser(user);
            modelAndView.addObject("user", user);
            modelAndView.setViewName("register_result_page");
        } catch (Exception e) {
            modelAndView.addObject("exception", e.getMessage());
            modelAndView.setViewName("register_error_page");
        }
        return modelAndView;
    }

}
