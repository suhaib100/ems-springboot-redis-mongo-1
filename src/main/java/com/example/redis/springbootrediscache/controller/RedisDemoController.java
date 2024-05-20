package com.example.redis.springbootrediscache.controller;

import com.example.redis.springbootrediscache.model.User;
import com.example.redis.springbootrediscache.repository.UserDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/redis")
public class RedisDemoController {

    @Autowired
    UserDBRepository repository;

    @PostMapping("/user")
//    @CachePut(value = "user", key = "#user.id")
    public User addNewUser(@RequestBody User user) {
        User userNew = repository.save(user);

        return userNew;
    }

    @GetMapping("/getUserById")
    @Cacheable(value = "user", key = "#id")
    public User getUserById(@RequestParam String id){
        System.out.println("called from db");
        return  repository.findById(id)!=null? repository.findById(id).get():null;
    }

    @GetMapping("/all")
    public List<User> getUserById(){
        return  repository.findAll();
    }

    @PutMapping("/update")
    @CachePut(value = "user", key = "#user.id")
    public User updateUser(@RequestBody User user) {
        return repository.save(user);
    }

    @DeleteMapping("/delete")
    @CacheEvict(value = "user", key = "#id")
    public String deleteUserById(@RequestParam String id) {
        repository.deleteById(id);
        return "Successfully Deleted";
    }
}



//
//
//import com.example.redis.springbootrediscache.model.User;
//import com.example.redis.springbootrediscache.repository.UserDBRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api")
//public class RedisDemoController {
//
//    @Autowired
//    private UserDBRepository employeeRepository;
//
//    @PostMapping("/employees")
//    public User addNewUser(@RequestBody User user) {
//        User userNew = employeeRepository.save(user);
//
//        return userNew;
//    }
//
//    @GetMapping("/employees/{id}")
//    @Cacheable(value = "employees",key = "#employeeId")
//    public User getUserById(@PathVariable String id){
//               System.out.println("Employee fetching from database:: "+id);
//
//        return  employeeRepository.findById(id)!=null? employeeRepository.findById(id).get():null;
//    }
//
//    @GetMapping("/employees")
//    public ResponseEntity<List<User>> getAllEmployees() {
//        return ResponseEntity.ok(employeeRepository.findAll());
//    }
//    @PutMapping("/update")
//PutMapping    public User updateUser(@RequestBody User user) {
//        return employeeRepository.save(user);
//    }
//
//    @DeleteMapping("/delete")
//    @CacheEvict(value = "user", key = "#id")
//    public String deleteUserById(@RequestParam String id) {
//        employeeRepository.deleteById(id);
//        return "Successfully Deleted";
//    }
//
////    @PostMapping("/employees")
////    public Employee addEmployee(@RequestBody Employee employee) {
////        Employee employee1 = employeeRepository.save(employee);
////
////        return employee1;
////    }
////
////
////    @GetMapping("/employees")
////    public ResponseEntity<List<Employee>> getAllEmployees() {
////        return ResponseEntity.ok(employeeRepository.findAll());
////    }
////
////    @GetMapping("employees/{employeeId}")
////    @Cacheable(value = "employees",key = "#employeeId")
////    public Employee findEmployeeById(@PathVariable(value = "employeeId") String employeeId) {
////        System.out.println("Employee fetching from database:: "+employeeId);
////        return employeeRepository.findById(employeeId).orElseThrow(
////                () -> new ResouceNotFoundException("Employee not found" + employeeId));
////
////    }
////
////
////    @PutMapping("employees/{employeeId}")
////    @CachePut(value = "employees",key = "#employeeId")
////    public Employee updateEmployee(@PathVariable(value = "employeeId") String employeeId,
////                                                   @RequestBody Employee employeeDetails) {
////        Employee employee = employeeRepository.findById(employeeId)
////                .orElseThrow(() -> new ResouceNotFoundException("Employee not found for this id :: " + employeeId));
////        employee.setName(employeeDetails.getName());
////        final Employee updatedEmployee = employeeRepository.save(employee);
////        return updatedEmployee;
////
////    }
////
////
////    @DeleteMapping("employees/{id}")
////    @CacheEvict(value = "employees", allEntries = true)
////    public void deleteEmployee(@PathVariable(value = "id") String employeeId) {
////        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
////                () -> new ResouceNotFoundException("Employee not found" + employeeId));
////        employeeRepository.delete(employee);
////    }
//}
