package com.nrifintech.medicalmanagementsystem.dto;



    

import java.time.LocalDate;

import com.nrifintech.medicalmanagementsystem.utility.annotations.PastDate;
import com.nrifintech.medicalmanagementsystem.utility.enums.BloodGroup;
import com.nrifintech.medicalmanagementsystem.utility.enums.Gender;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDistribution {
    private Long total_appointments;
    private String name;
    private Gender gender;
    private BloodGroup bloodgroup;
    private LocalDate firstAppointment;
    private LocalDate lastAppointment;
//     //private Integer age_group;
//     private BloodGroup most_common_blood_group;
//     private Gender most_common_gender;
//    // private Gender gender;
//    // private BloodGroup blood_group;


}
