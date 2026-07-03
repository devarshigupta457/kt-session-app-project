package com.project.ktsession.KTSession.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteCourseResponse {

    private String id;
    private String userId;
    private String courseId;
}