package com.project.ktsession.KTSession.service.impl;

import com.project.ktsession.KTSession.dto.request.FavoriteCourseRequest;
import com.project.ktsession.KTSession.dto.response.CourseResponse;
import com.project.ktsession.KTSession.entity.FavoriteCourse;
import com.project.ktsession.KTSession.exception.BadRequestException;
import com.project.ktsession.KTSession.exception.ResourceNotFoundException;
import com.project.ktsession.KTSession.repository.CourseRepository;
import com.project.ktsession.KTSession.repository.FavoriteCourseRepository;
import com.project.ktsession.KTSession.service.FavoriteCourseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FavoriteCourseServiceImpl implements FavoriteCourseService {

    private final FavoriteCourseRepository favoriteRepository;
    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;

    @Override
    public String addFavorite(FavoriteCourseRequest request) {

        favoriteRepository.findByUserIdAndCourseId(
                        request.getUserId(),
                        request.getCourseId())
                .ifPresent(f -> {
                    throw new BadRequestException("Course already added to favorite.");
                });

        FavoriteCourse favorite = new FavoriteCourse();

        favorite.setId(UUID.randomUUID().toString());
        favorite.setUserId(request.getUserId());
        favorite.setCourseId(request.getCourseId());


        favoriteRepository.save(favorite);

        return "Favorite Course Added Successfully";
    }

    @Override
    public String removeFavorite(FavoriteCourseRequest request) {

        FavoriteCourse favorite = favoriteRepository
                .findByUserIdAndCourseId(request.getUserId(), request.getCourseId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Favorite Course Not Found"));

        favoriteRepository.delete(favorite);

        return "Favorite Course Removed Successfully";
    }

    @Override
    public List<CourseResponse> fetchFavoriteCourses(String userId) {

        List<FavoriteCourse> favorites = favoriteRepository.findByUserId(userId);

        return favorites.stream()
                .map(favorite -> courseRepository.findById(favorite.getCourseId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Course Not Found")))
                .map(course -> modelMapper.map(course, CourseResponse.class))
                .toList();
    }
}