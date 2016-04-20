package com.codechampions.controllers;

import com.codechampions.entities.*;
import com.codechampions.services.*;
import com.codechampions.utils.PasswordHash;
import com.fasterxml.jackson.annotation.JsonView;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jack on 12/9/15.
 */

@RestController
public class CodeChampionsController {
    @Autowired
    UserRepository users;
    @Autowired
    MessageRepository messages;
    @Autowired
    ClassroomRepository classrooms;
    @Autowired
    UploadRepository uploads;
    @Autowired
    StatRepository stats;

    public String game1_1InitialCode = ("//Javascript goes here \n moveDown();");
    public String game1_2InitialCode = ("//Javascript goes here \n");
    public String game1_3InitialCode = ("//Javascript goes here \n");
    public String game2_1InitialCode = ("animal1 = \"PUT_NAME_HERE\";\nanimal2 = \nanimal3 = \nanimal4 = ");
    public String game2_2InitialCode = ("//Strings\ngreeting = \"\";\n//Numbers\nx=\ny=\n//Boolean\nisFriendly=");
    public String game2_3InitialCode = ("openDoor(PUT_NUMBER_HERE);");
    public String game3_1InitialCode = ("if(PUT_CONDITION_HERE){\nPUT_FUNCTION_HERE\n}\n");
    public String game3_2InitialCode = ("if(PUT_CONDITION_HERE){\nPUT_IF_CODE_HERE\n}\nelse{\nPUT_ELSE_CODE_HERE\n}\n");
    public String game3_3InitialCode = ("if(PUT_CONDITION_HERE){\nPUT_IF_CODE_HERE\n}\nelse if(PUT_ELSE_IF_CONDITION){\nPUT_ELSE_IF_CODE_HERE\n}\nelse{\nPUT_ELSE_CODE_HERE\n}");

    Server dbui = null;

    @PostConstruct
    public void init() throws InvalidKeySpecException, NoSuchAlgorithmException, FileNotFoundException, SQLException {
        dbui = Server.createWebServer().start();

        User admin = users.findOneByUsername("Admin");
        if (admin == null) {
            admin = new User();
            admin.username = "Admin";
            admin.password = PasswordHash.createHash("Admin");
            admin.accessType = User.AccessType.ADMIN;
            admin.avatar = "octopus.png";
            users.save(admin);

            User jack = new User();
            jack.username = "Jack";
            jack.password = PasswordHash.createHash("Jack");
            jack.accessType = User.AccessType.TEACHER;
            jack.email = "Jack@CodeChampions.com";
            jack.avatar = "octopus.png";
            users.save(jack);

            User terry = new User();
            terry.username = "Terry";
            terry.password = PasswordHash.createHash("Terry");
            terry.accessType = User.AccessType.TEACHER;
            terry.avatar = "pirate.png";
            users.save(terry);

            User kelleeMorgan = new User();
            kelleeMorgan.username = "Kellee-Morgan";
            kelleeMorgan.password = PasswordHash.createHash("Kellee-Morgan");
            kelleeMorgan.accessType = User.AccessType.TEACHER;
            kelleeMorgan.avatar = "princess.png";
            users.save(kelleeMorgan);

            User calvin = new User();
            calvin.username = "Calvin";
            calvin.password = PasswordHash.createHash("Calvin");
            calvin.accessType = User.AccessType.STUDENT;
            calvin.avatar = "cat.png";
            users.save(calvin);

            User nathan = new User();
            nathan.username = "Nathan";
            nathan.password = PasswordHash.createHash("Nathan");
            nathan.accessType = User.AccessType.STUDENT;
            nathan.avatar = "octopus.png";
            users.save(nathan);

            User zach = new User();
            zach.username = "Zach";
            zach.password = PasswordHash.createHash("Zach");
            zach.accessType = User.AccessType.STUDENT;
            zach.avatar = "pirate.png";
            users.save(zach);

            User betsy = new User();
            betsy.username = "Betsy";
            betsy.password = PasswordHash.createHash("Betsy");
            betsy.accessType = User.AccessType.STUDENT;
            betsy.avatar = "princess.png";
            users.save(betsy);

            User katie = new User();
            katie.username = "Katie";
            katie.password = PasswordHash.createHash("Katie");
            katie.accessType = User.AccessType.STUDENT;
            katie.avatar = "cat.png";
            users.save(katie);
        }

            Message message1 = new Message(1, -1, "Lesson11 Message Board", admin, LocalDateTime.now().toString());
            Message message2 = new Message(2, -1, "Game11 Message Board", admin, LocalDateTime.now().toString());
            Message message3 = new Message(3, -1, "Lesson12 Message Board", admin, LocalDateTime.now().toString());
            Message message4 = new Message(4, -1, "Game12 Message Board", admin, LocalDateTime.now().toString());
            Message message5 = new Message(5, -1, "Game13 Message Board", admin, LocalDateTime.now().toString());
            Message message6 = new Message(6, -1, "Lesson21 Message Board", admin, LocalDateTime.now().toString());
            Message message7 = new Message(7, -1, "Game21 Message Board", admin, LocalDateTime.now().toString());
            Message message8 = new Message(8, -1, "Game22 Message Board", admin, LocalDateTime.now().toString());
            Message message9 = new Message(9, -1, "Lesson22 Message Board", admin, LocalDateTime.now().toString());
            Message message10 = new Message(10, -1, "Game23 Message Board", admin, LocalDateTime.now().toString());
            Message message11 = new Message(11, -1, "Lesson31 Message Board", admin, LocalDateTime.now().toString());
            Message message12 = new Message(12, -1, "Game31 Message Board", admin, LocalDateTime.now().toString());
            Message message13 = new Message(13, -1, "Lesson32 Message Board", admin, LocalDateTime.now().toString());
            Message message14 = new Message(14, -1, "Game32 Message Board", admin, LocalDateTime.now().toString());
            Message message15 = new Message(15, -1, "Lesson33 Message Board", admin, LocalDateTime.now().toString());
            Message message16 = new Message(16, -1, "Game33 Message Board", admin, LocalDateTime.now().toString());

            messages.save(message1);
            messages.save(message2);
            messages.save(message3);
            messages.save(message4);
            messages.save(message5);
            messages.save(message6);
            messages.save(message7);
            messages.save(message8);
            messages.save(message9);
            messages.save(message10);
            messages.save(message11);
            messages.save(message12);
            messages.save(message13);
            messages.save(message14);
            messages.save(message15);
            messages.save(message16);
    }

    @RequestMapping(path = "/newUser", method = RequestMethod.POST)
    public User createUser(HttpServletResponse response, @RequestBody User tempUser, HttpSession session) throws Exception {
        if (tempUser.username == null || tempUser.password == null) {
            response.sendError(403, "Please enter both a username and password!");
        } else if (users.findOneByUsername(tempUser.username) != null) {
            response.sendError(404, "Username already exists");
        } else {
            session.setAttribute("username", tempUser.username);
            User user = new User();
            user.username = tempUser.username;
            user.password = PasswordHash.createHash(tempUser.password);
            user.email = tempUser.email;
            user.accessType = tempUser.accessType;
            if (tempUser.avatar == null) {
                user.avatar = "cat.png";
            }
            else {
                user.avatar = tempUser.avatar;
            }
            users.save(user);
            System.out.println("Success!");
            return user;
        }
        return null;
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public User login(HttpServletResponse response, HttpSession session, @RequestBody User tempUser) throws Exception {
        User user = users.findOneByUsername(tempUser.username);
        session.setAttribute("username", tempUser.username);

        if (tempUser.username == null || tempUser.password == null) {
            response.sendError(404, "Please enter both a username and password!");
        } else if (user == null) {
            response.sendError(403, "Username does not exist!");
        } else if (!PasswordHash.validatePassword(tempUser.password, user.password)) {
            response.sendError(405, "Wrong Password!");
        }
        else {
            System.out.println("Success!");
            return user;
        }
        return null;
    }

    @RequestMapping("/logout")
    public void logout(HttpSession session) throws IOException {
        session.invalidate();
        System.out.println("Successfully Logged Out!");
    }

    @JsonView(View.userSummary.class)
    @RequestMapping("/users")
    public List<User> users() {
        return (List<User>) users.findAll();
    }

    @RequestMapping(path = "/editUser/{id}", method = RequestMethod.PUT)
    public void editUser(HttpSession session, HttpServletResponse response, @RequestBody User tempUser) throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        session.getAttribute(tempUser.username);
        User user = users.findOne(tempUser.id);

        if (tempUser.username == null) {
            response.sendError(403, "Not logged in.");
        } else if (users.findOneByUsername(tempUser.username) != null) {
            response.sendError(404, "Username already exists!");
        } else {
            user.username = tempUser.username;
            session.setAttribute("username", user.username);
            users.save(user);
        }
    }

    @JsonView(View.userSummaryWithMessages.class)
    @RequestMapping("/messages")
    public List<Message> messages() {
        return (List<Message>) messages.findAll();
    }

    @RequestMapping("/showReplies/{id}")
    public List<Message> showReplies(@PathVariable("id") int id) {
        return messages.findAllByReplyId(id);
    }

    @RequestMapping("/addMessage/{id}")
    public Message addMessage(HttpServletResponse response, HttpSession session, @PathVariable("id") int id, @RequestBody Message tempMessage) throws IOException {
        String username = (String) session.getAttribute("username");
        User user = users.findOneByUsername(username);

        Message message = messages.findOne(id);

        if (user == null) {
            response.sendError(403, "You are not logged in!");
            return null;
        }
        Message newMessage = new Message();
        newMessage.replyId = message.id;
        newMessage.messageText = tempMessage.messageText;
        newMessage.user = user;
        messages.save(newMessage);
        System.out.println("New message added!");
        return newMessage;
    }

    @JsonView(View.userSummaryWithMessages.class)
    @RequestMapping("/newPM")
    public Message newPM(HttpSession session, @RequestBody Message tempMessage) {
        String username = (String) session.getAttribute("username");
        User user = users.findOneByUsername(username);
        User user2 = users.findOneByUsername(tempMessage.replyUser.username);
        Message message = new Message();
        if (messages.findOne(tempMessage.replyId) != null) {
            message.replyId = tempMessage.replyId;
        }
        message.messageText = tempMessage.messageText;
        message.user = user;
        message.replyUser = user2;

        message.messageTime = LocalDateTime.now().toString();
        String[] tempArray = message.messageTime.split("T");
        String[] date = tempArray[0].split("-");
        String[] time = tempArray[1].split(":");
        String dateStr = date[1].toString() + "-" + date[2].toString() + "-" + date[0].toString() + " ";
        String timeStr = time[0].toString() + ":" + time[1].toString();
        String cleanTime = dateStr + timeStr;
        message.messageTime = cleanTime;

        messages.save(message);
        System.out.println("New Private Message!");
        return message;
    }

    @JsonView(View.userSummaryWithMessages.class)
    @RequestMapping("/myPM")
    public List<Message> myPM(HttpSession session, HttpServletResponse response) {
        String username = (String) session.getAttribute("username");
        User user = users.findOneByUsername(username);
        List<Message> myMessages = new ArrayList();
        myMessages.addAll(messages.findAllByUser(user));
        myMessages.addAll(messages.findAllByReplyUser(user));
        return myMessages;
    }

    @RequestMapping("/deleteMessage/{id}")
    public void deleteMessage(HttpSession session, HttpServletResponse response, @PathVariable("id") int id) throws IOException {
        String username = (String) session.getAttribute("username");
        User user = users.findOneByUsername(username);
        Message message = messages.findOne(id);
        if (user == message.user || user.accessType == User.AccessType.ADMIN) {
            messages.delete(id);
            System.out.println("Success");
        }
        else {
            response.sendError(403, "Can't delete messages that you didn't create");
        }
    }

    @RequestMapping("/read/{id}")
    public void isRead(HttpServletResponse response, @PathVariable("id") int id) {
        Message pM = messages.findOne(id);
        pM.isRead = true;
        messages.save(pM);

        for (Message temp : messages.findAll()) {
            if (pM.id == temp.replyId) {
                temp.isRead = true;
                messages.save(temp);
            }
        }
    }


    @RequestMapping("/getGameCode")
    public User user(HttpSession session) {
        String username = (String) session.getAttribute("username");
        User user = users.findOneByUsername(username);

        if (user.game1_1Code == null) {
            user.game1_1Code = game1_1InitialCode;
        }
        if (user.game1_2Code == null) {
            user.game1_2Code = game1_2InitialCode;
        }
        if (user.game1_3Code == null) {
            user.game1_3Code = game1_3InitialCode;
        }
        if (user.game2_1Code == null) {
            user.game2_1Code = game2_1InitialCode;
        }
        if (user.game2_2Code == null) {
            user.game2_2Code = game2_2InitialCode;
        }
        if (user.game2_3Code == null) {
            user.game2_3Code = game2_3InitialCode;
        }
        if (user.game3_1Code == null) {
            user.game3_1Code = game3_1InitialCode;
        }
        if (user.game3_2Code == null) {
            user.game3_2Code = game3_2InitialCode;
        }
        if (user.game3_3Code == null) {
            user.game3_3Code = game3_3InitialCode;
        }
        return user;
    }

    @RequestMapping("/putGameCode")
    public void putGameCode(HttpSession session, @RequestBody User tempUser) {
        String username = (String) session.getAttribute("username");
        User user = users.findOneByUsername(username);
        if (tempUser.game1_1Code != null) {
            user.game1_1Code = tempUser.game1_1Code;
        }
        if (tempUser.game1_2Code != null) {
            user.game1_2Code = tempUser.game1_2Code;
        }
        if (tempUser.game1_3Code != null) {
            user.game1_3Code = tempUser.game1_3Code;
        }
        if (tempUser.game2_1Code != null) {
            user.game2_1Code = tempUser.game2_1Code;
        }
        if (tempUser.game2_2Code != null) {
            user.game2_2Code = tempUser.game2_2Code;
        }
        if (tempUser.game2_3Code != null) {
            user.game2_3Code = tempUser.game2_3Code;
        }
        if (tempUser.game3_1Code != null) {
            user.game3_1Code = tempUser.game3_1Code;
        }
        if (tempUser.game3_2Code != null) {
            user.game3_2Code = tempUser.game3_2Code;
        }
        if (tempUser.game3_3Code != null) {
            user.game3_3Code = tempUser.game3_3Code;
        }
        users.save(user);
    }

    @RequestMapping("/getLessonProgress")
    public User user2(HttpSession session) {
        String username = (String) session.getAttribute("username");
        return users.findOneByUsername(username);
    }

    @RequestMapping("/putLesson1Progress")
    public void putLesson1Progress(HttpSession session, @RequestBody User tempUser) {
        String username = (String) session.getAttribute("username");
        User user = users.findOneByUsername(username);
        user.lesson1Progress = tempUser.lesson1Progress;
        users.save(user);
    }

    @RequestMapping("/putLesson2Progress")
    public void putLesson2Progress(HttpSession session, @RequestBody User tempUser) {
        String username = (String) session.getAttribute("username");
        User user = users.findOneByUsername(username);
        user.lesson2Progress = tempUser.lesson2Progress;
        users.save(user);
    }

    @RequestMapping("/putLesson3Progress")
    public void putLesson3Progress(HttpSession session, @RequestBody User tempUser) {
        String username = (String) session.getAttribute("username");
        User user = users.findOneByUsername(username);
        user.lesson3Progress = tempUser.lesson3Progress;
        users.save(user);
    }

    @RequestMapping("/incrementProgress1/{id}")
    public void incrementProgress1(HttpSession session, @PathVariable("id") int id) {
        String username = (String) session.getAttribute("username");
        User user = users.findOneByUsername(username);
        if (user.lesson1Progress < id) {
            user.lesson1Progress++;
            users.save(user);
            System.out.println("Progress Incremented!");
        } else {
            System.out.println("Game is completed!");
        }
    }

    @RequestMapping("/incrementProgress2/{id}")
    public void incrementProgress2(HttpSession session, @PathVariable("id") int id) {
        String username = (String) session.getAttribute("username");
        User user = users.findOneByUsername(username);
        if (user.lesson2Progress < id) {
            user.lesson2Progress++;
            users.save(user);
            System.out.println("Progress Incremented!");
        } else {
            System.out.println("Game is completed!");
        }
    }

    @RequestMapping("/incrementProgress3/{id}")
    public void incrementProgress3(HttpSession session, @PathVariable("id") int id) {
        String username = (String) session.getAttribute("username");
        User user = users.findOneByUsername(username);
        if (user.lesson3Progress < id) {
            user.lesson3Progress++;
            users.save(user);
            System.out.println("Progress Incremented!");
        } else {
            System.out.println("Game is completed!");
        }
    }

    @RequestMapping("/createClassroom")
    public Classroom classroom (HttpSession session, HttpServletResponse response, @RequestBody Classroom tempClass) throws IOException {
        String username = (String) session.getAttribute("username");
        User user = users.findOneByUsername(username);

        if (user.accessType == User.AccessType.TEACHER) {
            Classroom classroom = new Classroom();
            classroom.className = tempClass.className;
            classroom.owner = user;

            Message newBoard = new Message();
            newBoard.replyId = -1;
            newBoard.messageText = String.format("%s Message Board", classroom.className);
            newBoard.user = user;
            newBoard.messageTime = LocalDateTime.now().toString();
            messages.save(newBoard);

            classroom.messageBoard = newBoard;
            classrooms.save(classroom);
            return classroom;
        } else {
            response.sendError(403, "Only teachers can create a classroom");
        }
        return null;
    }

    @RequestMapping("/addStudent/{id}")
    public void addStudent(HttpServletResponse response, @PathVariable("id") int id, @RequestBody User tempUser) throws IOException {
        Classroom classroom = classrooms.findOne(id);
        User user = users.findOneByUsername(tempUser.username);
        if (user.accessType == User.AccessType.STUDENT) {
            classroom.classStudents.add(user);
            classrooms.save(classroom);
        }
        else {
            response.sendError(403, "Only students can be added to a classroom");
        }
    }

    @JsonView(View.userSummarywithClassrooms.class)
    @RequestMapping("/classrooms")
    public List<Classroom> classrooms() {
        return (List<Classroom>) classrooms.findAll();
    }

    @RequestMapping("/myClasses")
    public List<Classroom> myClasses(HttpSession session) {
        String username = (String) session.getAttribute("username");
        User user = users.findOneByUsername(username);
        if (user.accessType == User.AccessType.STUDENT) {
            List<Classroom> studentClasses = (List<Classroom>) classrooms.findAll();
            studentClasses = studentClasses.stream()
                    .filter(x -> {
                        return x.classStudents.contains(user);
                    })
                    .collect(Collectors.toList());
            return studentClasses;
        }
        return classrooms.findAllByOwner(user);
    }

    @RequestMapping("myClasses/{id}")
    public Classroom myClass(@PathVariable("id") int id) {
        return classrooms.findOne(id);
    }

    @RequestMapping("removeStudent/{id}")
    public void removeStudent(HttpSession session, HttpServletResponse response, @PathVariable("id") int id, @RequestBody User tempUser) throws IOException {
        String username = (String) session.getAttribute("username");
        User user = users.findOneByUsername(username);
        User student = users.findOneByUsername(tempUser.username);
        Classroom classroom = classrooms.findOne(id);

        if (user == classroom.owner || user.accessType == User.AccessType.ADMIN) {
            classroom.classStudents.remove(student);
            System.out.println("Success!");
            classrooms.save(classroom);
        }
        else {
            response.sendError(403, "You don't own this classroom!");
        }
    }

    @RequestMapping("/uploads")
    public List<Upload> files() {
        return (List<Upload>) uploads.findAll();
    }

    @RequestMapping("/upload")
    public void upload(HttpSession session, HttpServletResponse response, MultipartFile file, int id, String displayName) throws IOException {
        String username = (String) session.getAttribute("username");
        User user = users.findOneByUsername(username);

        Classroom classroom = classrooms.findOne(id);
        if ((user.accessType == User.AccessType.TEACHER) || (user.accessType == User.AccessType.ADMIN)) {
            File notes = File.createTempFile("file", file.getOriginalFilename(), new File("public/classNotes"));
            FileOutputStream fos = new FileOutputStream(notes);
            fos.write(file.getBytes());

            Upload upload = new Upload();
            upload.fileName = file.getOriginalFilename();
            upload.name = notes.getName();
            upload.uploadTime = LocalDateTime.now();
            upload.uploadUser = user;
            upload.uploadClass = classroom;
            if (displayName.length() < 1) {
                upload.displayName = upload.fileName;
            }
            else {
                upload.displayName = displayName;
            }
            uploads.save(upload);
            response.sendRedirect("/#/classroom/" + id);
        }
        else {
            response.sendError(403, "Students can't upload images!");
        }
    }

    @RequestMapping("/myUploads/{id}")
    public List<Upload> myUploads(@PathVariable("id") int id) {
        Classroom classroom = classrooms.findOne(id);
        return uploads.findAllByUploadClass(classroom);
    }

    @RequestMapping("/deleteUpload")
    public void deleteUpload(HttpSession session, HttpServletResponse response, @RequestBody Upload tempUpload) throws IOException {
        String username = (String) session.getAttribute("username");
        User user = users.findOneByUsername(username);
        Upload upload = uploads.findOne(tempUpload.id);

        if ((upload.uploadUser == user) || (user.accessType == User.AccessType.ADMIN)) {
            uploads.delete(upload);

            File diskFile = new File("public/classNotes", upload.name);
            diskFile.delete();
        }
        else {
            response.sendError(403, "You can only delete uploads that you created!");
        }
    }

    @RequestMapping("/myStats")
    public Stat myStats(HttpSession session) {
        String username = (String) session.getAttribute("username");
        User user = users.findOneByUsername(username);
        Stat stat = new Stat();
        stat.user = user;
        if (user.lesson1Progress == 3) {
            stat.lessonsFinished++;
        }
        if (user.lesson2Progress == 3) {
            stat.lessonsFinished++;
        }
        if (user.lesson3Progress == 3) {
            stat.lessonsFinished++;
        }

        stat.gamesFinished = user.lesson1Progress + user.lesson2Progress + user.lesson3Progress;

        String totalLines = (user.game1_1Code + "\n") + (user.game1_2Code + "\n") + (user.game1_3Code + "\n") + (user.game2_1Code + "\n") + (user.game2_2Code + "\n") + (user.game2_3Code + "\n") + (user.game3_1Code + "\n") + (user.game3_2Code + "\n") + user.game3_3Code;
        String[] lines = totalLines.split("\n");
        List<String> linesList = Arrays.asList(lines);
        List<String> filteredList =  linesList.stream()
                .filter(x ->  {
                    return !x.equals("null") && !x.startsWith("//");
                })
                .collect(Collectors.toList());

        stat.linesCoded = filteredList.size();
        stats.save(stat);
        return stat;
    }
}