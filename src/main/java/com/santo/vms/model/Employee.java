package com.santo.vms.model;

import com.santo.vms.enums.Gender;
import com.santo.vms.utilities.enums.EntityStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
@AnalyzerDef(name = "employeeanalyzer",
        tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
        filters = {
                @TokenFilterDef(factory = LowerCaseFilterFactory.class),
                @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
                        @org.hibernate.search.annotations.Parameter(name = "language", value = "English")})})
public class Employee {
    @Id
    @Column
    private String id;

    private String firstName;

    private String surname;

    private String employeeNo;

    private String nationalId;

    private LocalDate dob;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String locationId;

    private String phoneNo;

    private String email;

    private String status;

    private String designation;

    @ManyToOne
    private Department department;

    @OneToOne
    private VisitLog visit;

    @OneToOne
    User user;

    @Version
    @Column
    protected Long entityVersion;

    @Enumerated(EnumType.STRING)
    private EntityStatus entityStatus;

    private OffsetDateTime dateCreated;

    @UpdateTimestamp
    protected OffsetDateTime lastUpdated;

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", employeeNo='" + employeeNo + '\'' +
                ", nationalId='" + nationalId + '\'' +
                ", gender=" + gender +
                ", locationId='" + locationId + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", entityStatus=" + entityStatus +
                '}';
    }
}
