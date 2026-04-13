package com.springboot.spring_jpa_mapping;

import com.springboot.spring_jpa_mapping.DAO.AppDAO;
import com.springboot.spring_jpa_mapping.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SpringJpaMappingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringJpaMappingApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner (AppDAO appDAO){

        return runner -> {
            //createInstructor(appDAO);
            //findInstructor(appDAO);
            //deleteInstructor(appDAO);
            //findInstructorDetail(appDAO);
            //deleteInstructorDetail(appDAO);
            //createInstructorWithCourses(appDAO);
            //findInstructorWithCourses(appDAO);
            //findCoursesForInstructor(appDAO);
            //findInstructorWithCousesJoinFetch(appDAO);
            //updateInstructor(appDAO);
            //updatingCourse(appDAO);
            //deletingInstructorById(appDAO);
            //deleteCourseById(appDAO);
            //createCourseAndReviews(appDAO);
            //retriveCourseAndReviews(appDAO);
            //deleteCourseAndReviewsById(appDAO);
            //createCourseAndStudents(appDAO);
            //findCourseAndStudents(appDAO);
            //findStudentAndCourses(appDAO);
            //addMoreCoursesForStudent(appDAO);
            deleteStudent(appDAO);
        };
    }

    private void deleteStudent(AppDAO appDAO) {
        int id = 1;
        System.out.println("deleting student id : " + id);
        appDAO.deleteStudentById(id);
        System.out.println("done!");
    }

    private void addMoreCoursesForStudent(AppDAO appDAO) {
        int id = 2;
        Student student = appDAO.findStudentAndCoursesByStudentId(id);
        Course course = new Course("python");
        student.setCourse(course);
        System.out.println("Saving student : " + student);
        System.out.println("Associated courses : " + student.getCourses());
        appDAO.update(student);
        System.out.println("Done!");
    }

    private void findStudentAndCourses(AppDAO appDAO) {
        int id = 2;
        Student student = appDAO.findStudentAndCoursesByStudentId(id);
        System.out.println("Loaded Student : " + student);
        System.out.println("Courses : " + student.getCourses());
        System.out.println("Done!");
    }

    private void findCourseAndStudents(AppDAO appDAO) {
        int id = 10;
        Course course = appDAO.findCourseAndStudentByCourseId(id);
        System.out.println("loaded course : " + course);
        System.out.println("students : " + course.getStudents());
        System.out.println("done!");
    }

    private void createCourseAndStudents(AppDAO appDAO) {
        Course course = new Course("java");
        Student student1 = new Student("harsh", "gosai", "harsh@gmail.com");
        Student student2 = new Student("jeet", "gosai", "jeet@gmail.com");
        course.addStudents(student1);
        course.addStudents(student2);
        System.out.println("saving the course : "+ course);
        System.out.println("saving associated student : " + course.getStudents());
        appDAO.save(course);
        System.out.println("Done!");
    }

    private void deleteCourseAndReviewsById(AppDAO appDAO) {
        int id = 10;
        System.out.println("Deleting course by id : " + id);
        appDAO.deleteCourse(id);
        System.out.println("Done!");
    }

    private void retriveCourseAndReviews(AppDAO appDAO) {
        int id = 10;
        System.out.println("Fetching course and reviews by id : " + id);
        Course course = appDAO.findCourseAndReviewsByCourseId(id);
        System.out.println("given course : " + course);
        System.out.println("course reviews : " + course.getReviews());
        System.out.println("Done!");
    }

    private void createCourseAndReviews(AppDAO appDAO) {
        Course course = new Course("Nursing");
        course.addReview(new Review("that was amazing..."));
        course.addReview(new Review("good but should improve..."));
        course.addReview(new Review("can add some more content..."));
        System.out.println("Saving the course...");
        System.out.println(course);
        System.out.println(course.getReviews());
        appDAO.save(course);
        System.out.println("Done!");
    }

    private void deleteCourseById(AppDAO appDAO) {
        int id = 10;
        System.out.println("Deleting course by id : " + id);
        appDAO.deleteCourse(id);
        System.out.println("Done!");
    }

    private void deletingInstructorById(AppDAO appDAO) {
        int id = 1;
        System.out.println("Deleting Instructor by Id : " + id);
        appDAO.deleteInstructorById(id);
        System.out.println("Done!");
    }

    private void updatingCourse(AppDAO appDAO) {
        int id = 10;
        System.out.println("finding the course : " + id);
        Course course = appDAO.findCourseById(id);
        System.out.println("Updating the course by id :" + id);
        course.setTitle("upsc");
        appDAO.updateCourse(course);
        System.out.println("Done!");
    }

    private void updateInstructor(AppDAO appDAO) {
        int id = 1;
        System.out.println("FInd the instructor : " + id);
        Instructor instructor = appDAO.findInsructorById(id);
        System.out.println("Updating the instructor by id : " + id);
        instructor.setLastName("patel");
        appDAO.update(instructor);
        System.out.println("Done!");
    }

    private void findInstructorWithCousesJoinFetch(AppDAO appDAO) {
        int id = 1;
        System.out.println("Finding isstructor id : " + id);
        Instructor instructor = appDAO.findInstructorByIdJoinFetch(id);
        System.out.println("Instructor : " + instructor);
        System.out.println("associated insructor details : "+ instructor.getInstructorDetail());
        System.out.println("The associated Courses : " + instructor.getCourse());
        System.out.println("Done!");
    }

    private void findCoursesForInstructor(AppDAO appDAO) {
        int id = 1;
        System.out.println("Finding instructor id : " + id);
        Instructor instructor= appDAO.findInsructorById(id);
        System.out.println("instructor : " + instructor);

        System.out.println("Finding courses for instructor id0 : " + id);
        List<Course> courses = appDAO.findCourseByInstructorId(id);
        instructor.setCourse(courses);
        System.out.println("the associated courses : " + instructor.getCourse());
        System.out.println("Done!");
    }

    private void findInstructorWithCourses(AppDAO appDAO) {
        int id = 1;
        System.out.println("Finding instructor id : " + id);
        Instructor instructor= appDAO.findInsructorById(id);
        System.out.println("instructor : " + instructor);
        System.out.println("associated Courses : " + instructor.getCourse());
        System.out.println("Done!");
    }

    private void createInstructorWithCourses(AppDAO appDAO) {
        Instructor instructor = new Instructor("gosai", "jeet", "jeet@gmail.com");
        InstructorDetail instructorDetail = new InstructorDetail("budyCodez.yt", "buddyCode");
        instructor.setInstructorDetail(instructorDetail);

        Course course1 = new Course("Jeemains");
        Course course2 = new Course("neet");
        Course course3 = new Course("gate");
        Course course4 = new Course("viteee");
        instructor.add(course1);
        instructor.add(course2);
        instructor.add(course3);
        instructor.add(course4);

        System.out.println("Saving Instror : " + instructor);
        System.out.println("the courses : " + instructor.getCourse());

        appDAO.save(instructor);
        System.out.println("Done!");
    }

    private void deleteInstructorDetail(AppDAO appDAO) {
        int id = 2;
        System.out.println("Deleting instructor detail by id : " + id);
        appDAO.deleteInstructorDetailById(id);
        System.out.println("Done!");
    }

    private void findInstructorDetail(AppDAO appDAO) {
        int id = 2;
        InstructorDetail instructorDetail = appDAO.findInstructorDetailById(id);
        System.out.println("instructor detail : " + instructorDetail);
        System.out.println("the associated instructor : " + instructorDetail.getInstructor());
        System.out.println("Done!");
    }

    private void deleteInstructor(AppDAO appDAO) {
        int id = 1;
        System.out.println("Deleting the instructor by id : " + id);
        appDAO.deleteInstructorbyId(id);
        System.out.println("Deleted !!");
    }

    private void findInstructor(AppDAO appDAO) {
        int id = 1;
        System.out.println("Finding instructor id : " + id);
        Instructor instructor = appDAO.findInsructorById(1);
        System.out.println("insrtructor : "+ instructor);
        System.out.println("the associated instructor only : " + instructor.getInstructorDetail());
    }

    private void createInstructor(AppDAO appDAO) {
        Instructor instructor = new Instructor("Harsh", "Gosai", "harsh@gmail.com");
        InstructorDetail instructorDetail = new InstructorDetail("budyCodez.yt", "buddyCode");
        instructor.setInstructorDetail(instructorDetail);
        System.out.println("Saving instructor : " + instructor);
        appDAO.save(instructor);
    }
}
