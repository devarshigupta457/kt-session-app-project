package com.project.ktsession.KTSession.service;

import com.project.ktsession.KTSession.dto.request.FavoriteCourseRequest;
import com.project.ktsession.KTSession.dto.response.CourseResponse;

import java.util.List;

public interface FavoriteCourseService {

    String addFavorite(FavoriteCourseRequest request);

    String removeFavorite(FavoriteCourseRequest request);

    List<CourseResponse> fetchFavoriteCourses(String userId);
}
