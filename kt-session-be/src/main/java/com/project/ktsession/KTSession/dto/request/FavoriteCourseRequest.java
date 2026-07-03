package com.project.ktsession.KTSession.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteCourseRequest {
    private String courseId;
    private String userId;
}
