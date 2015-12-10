package com.codechampions.controllers;

import com.codechampions.entities.User;
import com.codechampions.services.UserRepository;
import com.codechampions.utils.PasswordHash;
import org.hibernate.annotations.SourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

/**
 * Created by Jack on 12/9/15.
 */

@RestController
public class CodeChampionsController {
    @Autowired
    UserRepository users;

    @PostConstruct
    public void init() throws InvalidKeySpecException, NoSuchAlgorithmException, FileNotFoundException {
        User admin = users.findOneByUsername("Admin");
        if (admin == null) {
            admin = new User();
            admin.username = "Admin";
            //admin.password = PasswordHash.createHash("Admin");
            admin.password = "Admin";
            users.save(admin);
        }
    }

    @RequestMapping("/newUser")
    public void createUser(HttpServletResponse response, String username, String password) throws Exception {
            if (username == null || password == null) {
                response.sendError(403, "Please enter both a username and password!");
            }
            else {
            User user = new User();
            user.username = username;
            //user.password = PasswordHash.createHash(password);
            user.password = password;
            users.save(user);
            response.sendRedirect("/#/home");
            }
    }

    @RequestMapping("/login")
    public void login(HttpServletResponse response, HttpSession session, @RequestBody User userParams) throws Exception {
        User user = users.findOneByUsername(userParams.username);
        session.setAttribute("username", userParams.username);

        if (user == null) {
            response.sendError(403, "Username does not exist!");
        }
        else if (userParams.username == null || userParams.password == null) {
            response.sendError(404, "Please enter both a username and password!");
        }
      //  else if (PasswordHash.validatePassword(password, user.password))
        else if (userParams.password.equals(user.password)) {
            System.out.println("Success!");
        }
        else {
            response.sendError(405, "Wrong password");
        }
    }

    @RequestMapping("/logout")
    public void logout(HttpSession session, HttpServletResponse response) throws IOException {
        session.invalidate();
        response.sendRedirect("/");
    }

    @RequestMapping("/users")
    public List<User> users() {
        return (List<User>) users.findAll();
    }

}
