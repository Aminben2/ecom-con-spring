package com.nttdata.spring_boot_app.controller;

import com.nttdata.spring_boot_app.dto.UserDto;
import com.nttdata.spring_boot_app.entity.User;
import com.nttdata.spring_boot_app.repository.UserRepo;
import java.util.List;
import java.util.Map;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

  UserRepo userRepo;
  PasswordEncoder passwordEncoder;

  public UserController(UserRepo userRepo, PasswordEncoder passwordEncoder) {
    this.userRepo = userRepo;
    this.passwordEncoder = passwordEncoder;
  }

  @GetMapping("/")
  public String getUsers(Map<String, Object> model) {
    List<User> users = userRepo.findAll();
    List<UserDto> usersDto = users.stream().map(this::mapToUserDto).toList();
    model.put("users", usersDto);
    return "/user/users";
  }

  @GetMapping("/add")
  public String addUser(Map<String, Object> model) {
    model.put("user", new UserDto());
    List<User> users = userRepo.findAll();
    List<UserDto> usersDto = users.stream().map(this::mapToUserDto).toList();
    model.put("users", usersDto);
    return "/user/users";
  }

  @GetMapping("/edit/{id}")
  public String editUser(Map<String, Object> model, @PathVariable Long id) {
    User user = userRepo.findById(id).orElse(null);
    model.put("user", mapToUserDto(user));
    List<User> users = userRepo.findAll();
    List<UserDto> usersDto = users.stream().map(this::mapToUserDto).toList();
    model.put("users", usersDto);
    return "/user/users";
  }

  @PostMapping("/update")
  public String updateUser(Map<String, Object> model, UserDto user) {
    userRepo.save(mapToUser(user));
    List<User> users = userRepo.findAll();
    List<UserDto> usersDto = users.stream().map(this::mapToUserDto).toList();
    model.put("users", usersDto);
    return "/user/users";
  }

  @PostMapping("/save")
  public String createUser(Map<String, Object> model, UserDto user) {
    userRepo.save(mapToUser(user));
    List<User> users = userRepo.findAll();
    List<UserDto> usersDto = users.stream().map(this::mapToUserDto).toList();
    model.put("users", usersDto);
    return "/user/users";
  }

  @GetMapping("/{id}")
  public String deleteUser(Map<String, Object> model, @PathVariable Long id) {
    userRepo.deleteById(id);
    List<User> users = userRepo.findAll();
    List<UserDto> usersDto = users.stream().map(this::mapToUserDto).toList();
    model.put("users", usersDto);
    return "/user/users";
  }

  private UserDto mapToUserDto(User user) {
    UserDto userDto = new UserDto();
    String[] str = user.getName().split(" ");
    userDto.setFirstName(str[0]);
    userDto.setLastName(str[1]);
    userDto.setEmail(user.getEmail());
    userDto.setId(user.getId());
    return userDto;
  }

  private User mapToUser(UserDto userDto) {
    User user = new User();
    user.setName(userDto.getFirstName() + " " + userDto.getLastName());
    user.setEmail(userDto.getEmail());
    user.setId(userDto.getId());
    if (userDto.getPassword() != null) {
      user.setPassword(passwordEncoder.encode(userDto.getPassword()));
    }
    return user;
  }
}
