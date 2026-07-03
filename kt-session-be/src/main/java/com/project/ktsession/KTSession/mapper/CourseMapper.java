package com.project.ktsession.KTSession.mapper;

import com.project.ktsession.KTSession.dto.request.CreateCourseRequest;
import com.project.ktsession.KTSession.dto.request.UpdateCourseRequest;
import com.project.ktsession.KTSession.dto.response.CourseResponse;
import com.project.ktsession.KTSession.entity.Course;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CourseMapper {

    public Course toEntity(CreateCourseRequest request) {

        Course course = new Course();

        course.setId(UUID.randomUUID().toString());
        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setPrice(request.getPrice());
        course.setLink(request.getLink());
        course.setTrending(request.getTrending());

        return course;
    }

    public void updateEntity(UpdateCourseRequest request,
                             Course course) {

        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setPrice(request.getPrice());
        course.setLink(request.getLink());
        course.setTrending(request.getTrending());
    }

    public CourseResponse toResponse(Course course) {

        CourseResponse response = new CourseResponse();

        response.setId(course.getId());
        response.setTitle(course.getTitle());
        response.setDescription(course.getDescription());
        response.setPrice(course.getPrice());
        response.setLink(course.getLink());
        response.setTrending(course.getTrending());

        return response;
    }
}