package com.example.hellospring.Repo;

import com.example.hellospring.Model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChaiRepo extends JpaRepository<Request,Long> {


}
