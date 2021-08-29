package com.example.sub.controller;


import com.example.common.EmpDTO;
import com.example.sub.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SubscriberController {

    @Autowired
    EmpService empService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping(value = "/employee")
    public ResponseEntity<List<EmpDTO>> select() throws Exception {
        List<EmpDTO> list = empService.getEmpList();
        run();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    public void run() throws Exception {

        mongoTemplate.save(new Book(500, "Core Java", 200, "Kathy Sierra", 1065.5));
        mongoTemplate.save(new Book(501, "JSP & Servlets", 350, "Kathy Sierra", 1749.0));
        mongoTemplate.save(new Book(502, "Spring in Action", 480, "Craig Walls", 940.75));
        mongoTemplate.save(new Book(503, "Pro Angular", 260, "Freeman", 1949.25));
        mongoTemplate.save(new Book(504, "HTML CSS", 100, "Thomas Powell", 2317.09));
        mongoTemplate.save(new Book(505, "Hibernate in Action", 180, "Gavin King", 889.25));
        mongoTemplate.save(new Book(506, "Practical MongoDB", 180, "Shakuntala Gupta", 785.0));
        mongoTemplate.save(new Book(507, "Pro Spring Boot", 850, "Felipe Gutierrez", 2167.99));
        mongoTemplate.save(new Book(508, "Beginning jQuery", 180, "Franklin", 1500.00));
        mongoTemplate.save(new Book(509, "Java Design Patterns", 114, "Devendra Singh", 919.99));

        System.out.println("------All records has been saved successfully-------");

        List<Book> list = mongoTemplate.findAll(Book.class);

        System.out.println(list);
    }

}

