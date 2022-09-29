package com.example.hellospring.Model;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;



@Getter
@Setter
@Entity
@Table(name = "chai_detail")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "Chai", nullable = false)
    private String Chai;

    @Column(name = "quantity")
    private Integer quantity;



    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", Chai='" + Chai + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
