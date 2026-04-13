package com.springboot.spring_jpa_mapping.DAO;

import com.springboot.spring_jpa_mapping.entity.Course;
import com.springboot.spring_jpa_mapping.entity.Instructor;
import com.springboot.spring_jpa_mapping.entity.InstructorDetail;
import com.springboot.spring_jpa_mapping.entity.Student;

import java.util.List;

public interface AppDAO {

    void save(Instructor instructor);

    Instructor findInsructorById(int id);

    void deleteInstructorbyId( int id);

    InstructorDetail findInstructorDetailById(int id);

    void deleteInstructorDetailById(int id);

    List<Course> findCourseByInstructorId(int id);

    Instructor findInstructorByIdJoinFetch(int id);

    void update(Instructor instructor);

    void updateCourse(Course course);

    Course findCourseById(int id);

    void deleteInstructorById(int id);

    void deleteCourse(int id);

    void save(Course course);

    Course findCourseAndReviewsByCourseId(int id);

    Course findCourseAndStudentByCourseId(int id);

    Student findStudentAndCoursesByStudentId(int id);

    void update(Student student);

    void deleteStudentById(int id);

}
