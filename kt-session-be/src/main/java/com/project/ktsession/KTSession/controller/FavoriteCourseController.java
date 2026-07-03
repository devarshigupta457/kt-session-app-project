package com.project.ktsession.KTSession.controller;

import com.project.ktsession.KTSession.dto.request.FavoriteCourseRequest;
import com.project.ktsession.KTSession.dto.request.FavoriteUserRequest;
import com.project.ktsession.KTSession.dto.response.ApiResponse;
import com.project.ktsession.KTSession.service.FavoriteCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kt-session")
@RequiredArgsConstructor
public class FavoriteCourseController {

    private final FavoriteCourseService favoriteService;

    @PostMapping("/addFavCourse")
    public ResponseEntity<ApiResponse<?>> addFavorite(
            @RequestBody FavoriteCourseRequest request) {

        String message = favoriteService.addFavorite(request);

        return ResponseEntity.ok(
                new ApiResponse<>(true, message, null));
    }

    @DeleteMapping("/removeFavorite")
    public ResponseEntity<ApiResponse<?>> removeFavorite(
            @RequestBody FavoriteCourseRequest request) {

        String message = favoriteService.removeFavorite(request);

        return ResponseEntity.ok(
                new ApiResponse<>(true, message, null));
    }

    @PostMapping("/fetchFavoriteCourse")
    public ResponseEntity<ApiResponse<?>> fetchFavoriteCourse(
            @RequestBody FavoriteUserRequest request) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Favorite Courses fetched successfully.",
                        favoriteService.fetchFavoriteCourses(request.getUserId())
                ));
    }
}