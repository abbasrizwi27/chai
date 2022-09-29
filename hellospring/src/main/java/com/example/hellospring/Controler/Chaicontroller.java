package com.example.hellospring.Controler;


import com.example.hellospring.Model.Request;
import com.example.hellospring.Model.Responce;
import com.example.hellospring.Service.ChaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
public class Chaicontroller {

    @Autowired
    ChaiService chaiService;

    @PostMapping(value = "addchai", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "text/html")
    public Responce addchai(@RequestBody Request request){
        System.out.println(request);
       return chaiService.addchai(request);

    }
}
