package com.nrifintech.medicalmanagementsystem.dto;


    

import java.time.LocalDate;

import com.nrifintech.medicalmanagementsystem.utility.annotations.PastDate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopDepartmentAndDoctor {
    private String specialization;
    private Long total_appointments;
    private Double avg_rating;
    private String top_rating_doctor;
    private String top_appointment_doctor;

}

    
  
