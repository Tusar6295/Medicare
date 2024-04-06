package com.nrifintech.medicalmanagementsystem.dto;

import java.util.Optional;

import lombok.Data;

@Data
public class UserIdentificationDTO {

    Long userId;
    String userType;
    Long userTypeId;
}
