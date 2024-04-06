package com.nrifintech.medicalmanagementsystem.dto;

import java.time.LocalDate;

import com.nrifintech.medicalmanagementsystem.utility.annotations.PastDate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class AppointmentSearchRequestDTO {

    
    private Long patientId;

    private Long doctorId;

    private String doctorName;
    //@Column(columnDefinition="NUMBER(4) CHECK (TO_DATE(experience_start,  < EXTRACT(YEAR FROM SYSDATE))")appdate cannot be more than 2 weeks from sysdate during insertion
    private LocalDate startDate;
    private LocalDate endDate;

    private String specialization;

    private String appointmentStatus;

    @Min(value=0, message="Offset cannot be less than 0")
    private Integer offset;

    @Max(value=24, message="Slot is only upto 24")
    @Min(value=1, message="Slot is starting from 1")
    private Integer slot;
    
    private Integer pageSize;

    private String sortBy;




    
    
   
    
}