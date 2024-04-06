package com.nrifintech.medicalmanagementsystem.model;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.core.annotation.Order;

import com.nrifintech.medicalmanagementsystem.utility.annotations.PastDate;
import com.nrifintech.medicalmanagementsystem.utility.enums.BloodGroup;
import com.nrifintech.medicalmanagementsystem.utility.enums.Gender;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Order(4)
@Data
@Entity
// @Table(indexes = {
//   @Index(columnList = "name")
// })
public class Patient {

  @Id
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_seq")
@SequenceGenerator(name = "patient_seq", sequenceName = "PATIENT_SEQ", allocationSize = 1)
  private Long id;
    // @Id
    // @GeneratedValue(strategy = GenerationType.UUID)
    // private String id;

    @NotBlank
    @Column(length = 100,nullable=false)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable=false, length=2)
    private Gender gender;


    //@Column(columndefinition="VARCHAR2(100) CHECK (REGEXP_LIKE(email, '[a-zA-Z0-9._%-]+@[a-zA-Z0-9._%-]+\.[a-zA-Z]{2,4}'))")
    @NotBlank
    @Email
    @Column(length = 100, nullable=false)
    private String email;

    //@Column(columndefinition="NUMBER(4) CHECK ( birth_year > 1920 AND birth_year < EXTRACT(YEAR FROM SYSDATE))")
    //@Column(columnDefinition="NUMBER(4) CHECK (birth_year < EXTRACT(YEAR FROM SYSDATE))")
    @NotBlank
    @PastDate
    @Column(nullable=false)
    private String birthYear;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length=3, nullable=false)
    private BloodGroup bloodGroup;

    @NotBlank
    @Column(length=20, nullable=false)
    private String contactNumber;

    @OneToOne
    @JoinColumn(name="user_id", nullable=false, unique=true)
    private User user;

    /*@OneToMany(mappedBy = "A")
    private List<A> a;*/




    
}
