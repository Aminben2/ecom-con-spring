package com.nttdata.spring_boot_app.service;

import com.nttdata.spring_boot_app.dto.UserDto;
import com.nttdata.spring_boot_app.entity.Role;
import com.nttdata.spring_boot_app.entity.User;
import com.nttdata.spring_boot_app.repository.RoleRepo;
import com.nttdata.spring_boot_app.repository.UserRepo;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private UserRepo userRepository;
  private RoleRepo roleRepository;
  private PasswordEncoder passwordEncoder;

  public UserService(
      UserRepo userRepository, RoleRepo roleRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public void saveUser(UserDto userDto) {
    User user = new User();
    user.setName(userDto.getFirstName() + " " + userDto.getLastName());
    user.setEmail(userDto.getEmail());
    user.setPassword(passwordEncoder.encode(userDto.getPassword()));
    Role role = roleRepository.findByName("ROLE_ADMIN");
    if (role == null) {
      role = checkRoleExist();
    }
    user.setRoles(List.of(role));
    userRepository.save(user);
  }

  public User findUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  public List<UserDto> findAllUsers() {
    List<User> users = userRepository.findAll();
    return users.stream().map(this::mapToUserDto).collect(Collectors.toList());
  }

  private UserDto mapToUserDto(User user) {
    UserDto userDto = new UserDto();
    String[] str = user.getName().split(" ");
    userDto.setFirstName(str[0]);
    userDto.setLastName(str[1]);
    userDto.setEmail(user.getEmail());
    return userDto;
  }

  private Role checkRoleExist() {
    Role role = new Role();
    role.setName("ROLE_ADMIN");
    return roleRepository.save(role);
  }
}
