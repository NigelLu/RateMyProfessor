package edu.nyu.ratemyprofessor.user.model;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.validation.constraints.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import edu.nyu.ratemyprofessor.objects.models.*;

@Data
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(nullable = false)
    @Email(message = "User email is invalid")
    private String email;

    @NonNull
    @NotBlank
    @Column(nullable = false)
    private String lastName;

    @NonNull
    @NotBlank
    @Column(nullable = false)
    private String password;

    @NonNull
    @NotBlank
    @Column(nullable = false)
    private String firstName;

    private Number expectedYearOfGraduation;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "school", nullable = false)
    private School school;

    @OneToMany(mappedBy = "student")
    private List<Rating> ratinglList;

    @OneToMany(mappedBy = "student")
    private List<Saving> savingList;
}
