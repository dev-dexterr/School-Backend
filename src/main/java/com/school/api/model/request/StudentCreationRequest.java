package com.school.api.model.request;

import lombok.Data;

@Data
public class StudentCreationRequest {
    private String firstName;
    private String lastName;
    private Long age;
    private String mobile;
    private String email;
}
