package com.project.ktsession.KTSession.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCourseRequest {

    private String id;
    private String title;
    private String description;
    private String price;
    private String link;
    private String trending;
}