package com.springboot.hibernate_jpa_crud;

import com.springboot.hibernate_jpa_crud.dao.StudentDAO;
import com.springboot.hibernate_jpa_crud.dao.StudentDAOImpl;
import com.springboot.hibernate_jpa_crud.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SpringbootHibernateJpaCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootHibernateJpaCrudApplication.class, args);
    }

    /**
     * create commandLineRunner method for do crud operation in direct command line
     * @param studentDAO
     * @return
     */
    @Bean
    public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
        return runner -> {
            //createStudent(studentDAO);
            createMultipleStudent(studentDAO);
            //readStdent(studentDAO);
            //queryForStudents(studentDAO);
            //queryForStudentByLastName(studentDAO);
            //updateStudent(studentDAO);
            //deleteStudent(studentDAO);
            //deleteAllStudents(studentDAO);
        };
    }

    private void deleteAllStudents(StudentDAO studentDAO) {
        System.out.println("Deleting all sudents..");
        int numRowsDeleted = studentDAO.deleteAll();
        System.out.println("Deleted row count : "+numRowsDeleted);
    }

    private void deleteStudent(StudentDAO studentDAO) {
        //delete the student
        int studentId = 2;
        System.out.println("Deleting the student by id : "+studentId);
        studentDAO.delete(studentId);
    }

    /**
     * updating the student using update method
     * @param studentDAO
     */
    private void updateStudent(StudentDAO studentDAO) {

        int studentId = 1;
        System.out.println("Getting student with id : "+ studentId);
        Student student = studentDAO.findById(studentId);
        System.out.println("Updating student ...");

        //change first name
        student.setFirstName("jeetpari");
        studentDAO.update(student);

        //display updated student
        System.out.println("Updated student : "+ student);
    }

    /**
     * Retrive studenr data by their last name from database using manual jpql query method
     * @param studentDAO
     */
    private void queryForStudentByLastName(StudentDAO studentDAO) {

        //get the list of students
        List<Student> students = studentDAO.findByLastName("Gosai");

        //display list of students
        for (Student tempStudent : students){
            System.out.println(tempStudent);
        }

    }

    /**
     * Retrive data from database using manual jpql query method
     * @param studentDAO
     */
    private void queryForStudents(StudentDAO studentDAO) {

        //get the list of students
        List<Student> theStudent = studentDAO.findAll();

        //display list of students
        for (Student tempStudent : theStudent){
            System.out.println(tempStudent);
        }
    }

    /**
     * create student object and insert into database
     * then retrieve this student from database
     * @param studentDAO
     */
    private void readStdent(StudentDAO studentDAO) {

        //create the student object
        System.out.println("Creating the student object..");
        Student student = new Student("Harsh","Gosai","harsh@gmail.com");

        //save the student object
        System.out.println("Saving the student...");
        studentDAO.save(student);

        //display id of the saved student
        System.out.println("Saved student Generated id : " + student.getId());

        //retrival of student by its id
        System.out.println("\nRetriving student with id : " + student.getId());
        Student student1 = studentDAO.findById(student.getId());
        System.out.println("Found the Student : " + student1);
    }

    /**
     * create 3 student objects and insert them into database
     * @param studentDAO
     */
    private void createMultipleStudent(StudentDAO studentDAO) {

        //create multiple student object
        System.out.println("Creating 3 student object..");
        Student student1 = new Student("Jeet","Gosai","jeet@gmail.com");
        Student student2 = new Student("Vijay","Gosai","vijay@gmail.com");
        Student student3 = new Student("Rudra","Gosai","rudra@gmail.com");

        //save the student object
        System.out.println("Saving the student...");
        studentDAO.save(student1);
        studentDAO.save(student2);
        studentDAO.save(student3);

    }

    /**
     * create single student object and inset it into database for testing
     * @param studentDAO
     */
    private void createStudent(StudentDAO studentDAO) {

        //create the student object
        System.out.println("Creating the student object..");
        Student student = new Student("Harsh","Gosai","harsh@gmail.com");

        //save the student object
        System.out.println("Saving the student...");
        studentDAO.save(student);

        //display id of the saved student
        System.out.println("Saved student Generated id : " + student.getId());

    }
}
