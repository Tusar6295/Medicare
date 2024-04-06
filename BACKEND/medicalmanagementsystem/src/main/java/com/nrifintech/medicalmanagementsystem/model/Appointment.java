package com.nrifintech.medicalmanagementsystem.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.core.annotation.Order;

import com.nrifintech.medicalmanagementsystem.utility.annotations.PastDate;
import com.nrifintech.medicalmanagementsystem.utility.enums.AppointmentStatus;
import com.nrifintech.medicalmanagementsystem.utility.enums.Slot;

import jakarta.persistence.Column;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Index;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Order(6)
@Data
@Entity
// @Table(name = "A", uniqueConstraints = {
//     @UniqueConstraint(columnNames = {"d_id","date", "slot","appointmentStatus"}),
//     @UniqueConstraint(columnNames = {"d_id","date", "p_id","appointmentStatus"})},
    
//     indexes = {
//     @Index(columnList = "date"),
//      @Index(columnList = "slot"),
//     @Index(columnList = "appointment_status"),
//     @Index(columnList = "f"),
//     @Index(columnList = "d"),
//     @Index(columnList = "p"),
// })
@Table(name = "Appointment")
public class Appointment {
     @Id
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appointment_seq")
@SequenceGenerator(name = "appointment_seq", sequenceName = "APPOINTMENT_SEQ", allocationSize = 1)
    private Long id;


    //@Column(columnDefinition="NUMBER(4) CHECK (TO_DATE(experience_start,  < EXTRACT(YEAR FROM SYSDATE))")appdate cannot be more than 2 weeks from sysdate during insertion
    @NotNull
    @Column(nullable=false)
    private LocalDate appDate;

    @NotNull
    @Max(value=24, message="Slot is only upto 24")
    @Min(value=1, message="Slot is starting from 1")
    @Column(nullable=false, length=2)
    private Integer slot;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable=false, length=20, columnDefinition = "VARCHAR2(20) DEFAULT 'UPCOMING'")
    private AppointmentStatus appStatus;

        
  

    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="doctor_id", nullable=false)
    private Doctor doctor;

    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="patient_id", nullable=false)
    private Patient patient;
    

}

/*
ALTER TABLE appointment
ADD CONSTRAINT appointment_date_constraint
CHECK (TO_DATE(TO_CHAR(appointment_date),'YYYY') < SYSDATE)

CREATE INDEX doctor_name_index
ON doctor(name);
CREATE INDEX doctor_fees_index
ON doctor(fees);
CREATE INDEX doctor_rating_index
ON doctor(rating);
CREATE BITMAP INDEX doctor_gender_index
ON doctor(gender);
CREATE BITMAP INDEX doctor_status_index
ON doctor(status);

CREATE INDEX appointment_date_index
ON appointment(appointment_date);


CREATE BITMAP INDEX appointment_status_index
ON appointment(appointment_status);

CREATE BITMAP INDEX appointment_slot_index
ON appointment(slot);

CREATE BITMAP INDEX user_username_index
ON "user"(username);

CREATE BITMAP INDEX user_username_password_index
ON "user"(username,password);

*/
