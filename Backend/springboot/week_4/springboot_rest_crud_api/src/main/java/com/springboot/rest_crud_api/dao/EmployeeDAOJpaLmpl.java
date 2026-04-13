package com.springboot.rest_crud_api.dao;

import com.springboot.rest_crud_api.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOJpaLmpl implements EmployeeDAO{

   EntityManager entityManager;

   @Autowired
   public EmployeeDAOJpaLmpl(EntityManager entityManager){
       this.entityManager = entityManager;
   }

    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee", Employee.class);
        List<Employee> employees = theQuery.getResultList();
        return employees;
    }

    @Override
    public Employee findById(int id) {
       Employee employee = entityManager.find(Employee.class, id);
        return employee;
    }

    @Override
    public Employee save(Employee employee) {
       Employee employee1 = entityManager.merge(employee);
        return employee1;
    }

    @Override
    public void deletebyId(int id) {
        Employee employee = entityManager.find(Employee.class, id);

        entityManager.remove(employee);
    }


}
