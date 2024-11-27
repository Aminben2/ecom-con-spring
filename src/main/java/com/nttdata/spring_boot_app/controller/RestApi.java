package com.nttdata.spring_boot_app.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("http://localhost:4200")
@RequestMapping("api")
@RestController
public class RestApi {

    @Data
    @AllArgsConstructor
    @RequiredArgsConstructor
    class Course {
        private String name;
        private int nb;
    }

    @GetMapping(path = "/courses",produces = "application/json")
    public @ResponseBody List<Course> getAll(){
        List<Course> data  = new ArrayList<>();
        data.add(new Course("test",10));
        data.add(new Course("test1",10));
        data.add(new Course("test2",10));
        data.add(new Course("test3",10));
        data.add(new Course("test4",10));
        data.add(new Course("test5",10));
        return data;
    }
}
