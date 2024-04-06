    
    
package com.nrifintech.medicalmanagementsystem.dto;

import java.time.LocalDate;

import com.nrifintech.medicalmanagementsystem.utility.annotations.PastDate;
import com.nrifintech.medicalmanagementsystem.utility.enums.AppointmentStatus;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentSearchResponseDTO {    
    //@Column(columnDefinition="NUMBER(4) CHECK (TO_DATE(experience_start,  < EXTRACT(YEAR FROM SYSDATE))")appdate cannot be more than 2 weeks from sysdate during insertion
    private Long appId;


    private Long doctorId;
    private String doctorName;
    private Long patientId;
    private String patientName;

    private LocalDate appDate;

    
    private Integer slot;

    
    private AppointmentStatus appStatus;

    private String specialization;

    
}
