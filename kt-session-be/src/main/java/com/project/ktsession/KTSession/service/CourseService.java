package com.project.ktsession.KTSession.service;

import com.project.ktsession.KTSession.dto.request.CreateCourseRequest;
import com.project.ktsession.KTSession.dto.request.UpdateCourseRequest;
import com.project.ktsession.KTSession.dto.response.CourseResponse;

import java.util.List;

public interface CourseService {

    CourseResponse createCourse(CreateCourseRequest request);

    CourseResponse getCourseById(String courseId);

    List<CourseResponse> getAllCourses();

    CourseResponse updateCourse(String courseId,
                                UpdateCourseRequest request);

    void deleteCourse(String courseId);
}