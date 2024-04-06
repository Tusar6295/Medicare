    
    
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
public class AppointmentStatusDistributionWithSpecialization {    

    String specializations;

    Long pending;

    Long cancelled;

    Long completed;




} 

    
  
