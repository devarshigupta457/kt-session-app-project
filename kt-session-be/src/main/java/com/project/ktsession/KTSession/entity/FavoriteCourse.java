package com.project.ktsession.KTSession.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "KTS_FAV_COURSE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteCourse {

    @Id
    private String id;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "COURSE_ID")
    private String courseId;
}
