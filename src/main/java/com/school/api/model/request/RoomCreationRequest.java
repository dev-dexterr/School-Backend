package com.school.api.model.request;

import lombok.Data;

@Data
public class RoomCreationRequest {
    private String name;
    private Long lectureId;
}
