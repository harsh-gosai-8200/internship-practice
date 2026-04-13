//package com.springboot.rest_api_jparepository.rest;
//
//import com.springboot.rest_api_jparepository.entity.Employee;
//import com.springboot.rest_api_jparepository.services.EmployeeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import tools.jackson.databind.json.JsonMapper;
//
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api")
//public class EmployeeRestController {
//
//    private EmployeeService employeeService;
//    private JsonMapper jsonMapper;
//
//    @Autowired
//    public EmployeeRestController(EmployeeService employeeService, JsonMapper jsonMapper){
//        this.employeeService = employeeService;
//        this.jsonMapper = jsonMapper;
//    }
//
//    @GetMapping("/employees")
//    public List<Employee> findAll(){
//        return employeeService.findAll();
//    }
//
//    @PostMapping("/employees")
//    public Employee addEmployee(@RequestBody Employee employee){
//        employee.setId(0);
//        Employee employee1 = employeeService.save(employee);
//        return employee1;
//    }
//
//    @PutMapping("/employees")
//    public Employee updateEmployee(@RequestBody Employee employee){
//        return employeeService.save(employee);
//    }
//
//    @GetMapping("/employees/{employeeId}")
//    public Employee getEmployee(@PathVariable int employeeId){
//        Employee employee = employeeService.findById(employeeId);
//
//        if(employee == null){
//            throw new RuntimeException("Employee not found - " + employeeId);
//        }
//        return employee;
//    }
//
//    @DeleteMapping("/employees/{employeeId}")
//    public String deleteEmployee(@PathVariable int employeeId){
//        Employee employee = employeeService.findById(employeeId);
//
//        if(employee == null){
//            throw  new RuntimeException("Employee id is not found - " + employeeId);
//        }
//
//        employeeService.deletebyId(employeeId);
//
//        return "Deleted employee id - " + employeeId;
//    }
//
//    @PatchMapping("/employees/{employeeId}")
//    public Employee patchEmployee(@RequestBody Map<String, Object> patchPayload, @PathVariable int employeeId){
//        Employee employee = employeeService. findById(employeeId);
//
//        if(employee == null){
//            throw new RuntimeException("Employee id is not found - " + employeeId);
//        }
//
//        if(patchPayload.containsKey("id")){
//            throw new RuntimeException("Employee id is not allowed in request body - " + employeeId);
//        }
//
//        Employee employee1 = jsonMapper.updateValue(employee, patchPayload);
//
//        Employee dbEmployee = employeeService.save(employee1);
//        return dbEmployee;
//    }
//}
