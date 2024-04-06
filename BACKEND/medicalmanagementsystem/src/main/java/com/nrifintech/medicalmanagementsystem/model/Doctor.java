package com.nrifintech.medicalmanagementsystem.model;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.core.annotation.Order;

import com.nrifintech.medicalmanagementsystem.utility.annotations.PastDate;
import com.nrifintech.medicalmanagementsystem.utility.enums.Gender;
import com.nrifintech.medicalmanagementsystem.utility.enums.Rating;
import com.nrifintech.medicalmanagementsystem.utility.enums.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;

@Order(5)
@Data
@Entity
// @Table(indexes = {
//   @Index(columnList = "name"),
//   @Index(columnList = "gender"),
//   @Index(columnList = "fees"),
//   @Index(columnList = "qualification"),
//   @Index(columnList = "rating"),
//   @Index(columnList = "status"),
//   @Index(columnList = "fees, rating, status"),
//   @Index(columnList = "fees, qualification, status"),
//   @Index(columnList = "fees, qualification, rating, status"),
//   @Index(columnList = "rating, qualification, status"),
// })
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctor_seq")
    @SequenceGenerator(name = "doctor_seq", sequenceName = "DOCTOR_SEQ", allocationSize = 1)
    private Long id;
    // @Id
    // @GeneratedValue(strategy = GenerationType.UUID)
    // private String id;
    //Either we use the pre built B-Tree or customize our own :-(
    
    @NotBlank
    @Column(length=50, nullable=false)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable=false, length=2)
    private Gender gender;

    @NotBlank
    @Column(nullable=false, length=100)
    private String qualification;

    //need to add check constraint for email in ORACLE
    //@Column(columnDefinition="VARCHAR2(100) CHECK (REGEXP_LIKE(email, '[a-zA-Z0-9._%-]+@[a-zA-Z0-9._%-]+\.[a-zA-Z]{2,4}'))")
    @NotBlank
    @Email
    @Column(length=50, nullable=false)
    private String email;

   
    // @Column(columnDefinition="NUMBER(4,0) CHECK (fees > 199 AND fees < 1001)")
    // @Max(value = 1001, message="Fees cannot be more than 1000")
    //@NotBlank
    @NotNull
    @Min(value = 1, message="Fees cannot be less than 1")
    @Column(nullable=false)
    private Integer fees;

    // @Min(value = 2024, message="Starting experience cannot be less than 200")
    // @Max(value = 2025, message="Starting experience cannot be more than 1000")
    //@Column(columndefinition="NUMBER(4) CHECK (experience_start >= 1950 AND experience_start < EXTRACT(YEAR FROM SYSDATE))")
    //@Column(columnDefinition="NUMBER(4) CHECK (experience_start < EXTRACT(YEAR FROM SYSDATE))")
    @NotBlank
    @Column(nullable=false)
    private String experienceStart;

    @NotNull
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="specialization_id", nullable=false)
    private Specialization specialization;

    
    @Column(nullable=true)
    private LocalDate leaveStart;

    @Column(nullable=true)
    private LocalDate leaveEnd;



  //We might have to change this depending on whether we calculate and floor() or ceil() it or let it remain as is
  // @NotBlank
  @NotNull
  @Max(value=5, message="Cannot be more than 5")
  @Min(value=1, message="Cannot be less than 1")
  @Column(nullable=false, length=2, columnDefinition = "INT DEFAULT 5")
  private Integer rating;

    // @NotBlank
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable=false, length=10)
    private Status status;

    @OneToOne
    @JoinColumn(name="user_id", nullable=false, unique=true)
    private User user;

    /*@OneToMany(mappedBy = "A")
    private List<A> a;*/

    private String profileImgUrl;

    
}
