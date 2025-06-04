package com.torombolinebookstore.db_api_gtw.controller;

import com.torombolinebookstore.db_api_gtw.dao.AuthorDAO;
import com.torombolinebookstore.db_api_gtw.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorDAO authorDAO;


    @GetMapping("/{id}")
    public String getAuthorById(@PathVariable int id) {
        Author response = authorDAO.getAuthorById(id);
        return response==null?"nada":response.toString();
    }
}
