package com.nrifintech.medicalmanagementsystem.dto;

import java.time.LocalDate;

import com.nrifintech.medicalmanagementsystem.utility.annotations.PastDate;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Data
public class AppointmentBookingResponseDTO {
    
     private Long doctorId;

    
     private Long patientId;

    //@Column(columnDefinition="NUMBER(4) CHECK (TO_DATE(experience_start,  < EXTRACT(YEAR FROM SYSDATE))")appdate cannot be more than 2 weeks from sysdate during insertion
    
    
    private LocalDate appDate;

    
    private Integer slot;


    
    
   
    
}