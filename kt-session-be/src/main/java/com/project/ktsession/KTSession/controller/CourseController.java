package com.project.ktsession.KTSession.controller;

import com.project.ktsession.KTSession.dto.request.CreateCourseRequest;
import com.project.ktsession.KTSession.dto.request.UpdateCourseRequest;
import com.project.ktsession.KTSession.dto.response.ApiResponse;
import com.project.ktsession.KTSession.dto.response.CourseResponse;
import com.project.ktsession.KTSession.service.CourseService;
//import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kt-session")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CourseController {

    private final CourseService courseService;

    /**
     * Create Course
     */
    @PostMapping("/addCourse")
    public ResponseEntity<ApiResponse<CourseResponse>> createCourse(
             @RequestBody CreateCourseRequest request) {

        CourseResponse response = courseService.createCourse(request);

        ApiResponse<CourseResponse> apiResponse =
                new ApiResponse<>(true, "Course created successfully.", response);

        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    /**
     * Get Course By Id
     */
    @GetMapping("fetchCourses/{courseId}")
    public ResponseEntity<ApiResponse<CourseResponse>> getCourseById(
            @PathVariable String courseId) {

        CourseResponse response = courseService.getCourseById(courseId);

        ApiResponse<CourseResponse> apiResponse =
                new ApiResponse<>(true, "Course fetched successfully.", response);

        return ResponseEntity.ok(apiResponse);
    }

    /**
     * Get All Courses
     */
    @GetMapping("/fetchAllCourses")
    public ResponseEntity<ApiResponse<List<CourseResponse>>> getAllCourses() {

        List<CourseResponse> response = courseService.getAllCourses();

        ApiResponse<List<CourseResponse>> apiResponse =
                new ApiResponse<>(true, "Courses fetched successfully.", response);

        return ResponseEntity.ok(apiResponse);
    }

    /**
     * Update Course
     */
    @PutMapping("/updateCourse/{courseId}")
    public ResponseEntity<ApiResponse<CourseResponse>> updateCourse(
            @PathVariable String courseId,
            @RequestBody UpdateCourseRequest request) {

        CourseResponse response = courseService.updateCourse(courseId, request);

        ApiResponse<CourseResponse> apiResponse =
                new ApiResponse<>(true, "Course updated successfully.", response);

        return ResponseEntity.ok(apiResponse);
    }

    /**
     * Delete Course
     */
    @DeleteMapping("/deleteCourse/{courseId}")
    public ResponseEntity<ApiResponse<Void>> deleteCourse(
            @PathVariable String courseId) {

        courseService.deleteCourse(courseId);

        ApiResponse<Void> apiResponse =
                new ApiResponse<>(true, "Course deleted successfully.", null);

        return ResponseEntity.ok(apiResponse);
    }
}