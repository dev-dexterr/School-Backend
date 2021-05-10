package com.school.api.model.request;

import lombok.Data;

import java.util.List;

@Data
public class RoomFeeRequest {
    private List<Long> roomIds;
    private Long studentId;
}
