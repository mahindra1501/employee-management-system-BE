package com.springrest.empRepo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springrest.entity.neovaEmp;

public interface EmpRepos extends JpaRepository<neovaEmp, Integer> {

}
