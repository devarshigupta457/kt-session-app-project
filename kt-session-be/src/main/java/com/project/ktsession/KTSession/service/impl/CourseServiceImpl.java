package com.project.ktsession.KTSession.service.impl;

import com.project.ktsession.KTSession.dto.request.CreateCourseRequest;
import com.project.ktsession.KTSession.dto.request.UpdateCourseRequest;
import com.project.ktsession.KTSession.dto.response.CourseResponse;
import com.project.ktsession.KTSession.entity.Course;
import com.project.ktsession.KTSession.repository.CourseRepository;
import com.project.ktsession.KTSession.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CourseResponse createCourse(CreateCourseRequest request) {

        Course course = new Course();

        course.setId(request.getId());
        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setPrice(request.getPrice());
        course.setLink(request.getLink());
        course.setTrending(request.getTrending());

        course = courseRepository.save(course);

        return mapToResponse(course);
    }

    @Override
    public CourseResponse getCourseById(String courseId) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new RuntimeException("Course not found."));

        return mapToResponse(course);
    }

    @Override
    public List<CourseResponse> getAllCourses() {

        return courseRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CourseResponse updateCourse(String courseId,
                                       UpdateCourseRequest request) {
        Course course =  courseRepository.findById(courseId)
                .orElseThrow(()-> new RuntimeException("Course not found."));
        modelMapper.map(request, course);
        Course updatedCourse = courseRepository.save(course);
        return mapToResponse(updatedCourse);
    }

    @Override
    public void deleteCourse(String courseId) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new RuntimeException("Course not found."));

        courseRepository.delete(course);
    }

    private CourseResponse mapToResponse(Course course) {

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