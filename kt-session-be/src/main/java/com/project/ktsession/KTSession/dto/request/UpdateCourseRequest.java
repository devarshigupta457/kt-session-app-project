package com.project.ktsession.KTSession.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCourseRequest {

    private String title;
    private String description;
    private String price;
    private String link;
    private String trending;

}