package com.project.ktsession.KTSession.repository;

import com.project.ktsession.KTSession.entity.FavoriteCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteCourseRepository extends JpaRepository<FavoriteCourse, String> {

    Optional<FavoriteCourse> findByUserIdAndCourseId(String userId, String courseId);

    List<FavoriteCourse> findByUserId(String userId);

    void deleteByUserIdAndCourseId(String userId, String courseId);
}
