package com.example.hellospring.Service;


import com.example.hellospring.Model.Request;
import com.example.hellospring.Model.Responce;
import com.example.hellospring.Repo.ChaiRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChaiService {

    @Autowired
    ChaiRepo chaiRepo;

    public Responce addchai(Request request){
        System.out.println("Chaiiiiii");
        System.out.println(request);
        request.setId(request.getId());
        Request request1 = chaiRepo.save(request);
        Responce responce = new Responce();
        responce.setChai(request1.getChai());
        System.out.println(responce);
        return responce;


    }
}
