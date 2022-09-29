package com.example.hellospring.Model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
public class Responce {


    private int quantity;
    private String Chai;
    public List<Request> chailist;
}
