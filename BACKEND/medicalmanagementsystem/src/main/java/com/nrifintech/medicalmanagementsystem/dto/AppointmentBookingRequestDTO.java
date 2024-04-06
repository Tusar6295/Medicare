package com.nrifintech.medicalmanagementsystem.dto;

import java.time.LocalDate;

import com.nrifintech.medicalmanagementsystem.utility.annotations.PastDate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AppointmentBookingRequestDTO {
    @NotNull
     private Long doctorId;

    @NotNull
     private Long patientId;


    //@Column(columnDefinition="NUMBER(4) CHECK (TO_DATE(experience_start,  < EXTRACT(YEAR FROM SYSDATE))")appdate cannot be more than 2 weeks from sysdate during insertion
    @NotNull
    @PastDate
    private LocalDate appDate;

    @NotNull
    @Max(value=24, message="Slot is only upto 24")
    @Min(value=1, message="Slot is starting from 1")
    private Integer slot;


    
    
   
    
}