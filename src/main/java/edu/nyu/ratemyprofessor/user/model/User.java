package edu.nyu.ratemyprofessor.user.model;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import edu.nyu.ratemyprofessor.objects.models.*;

@Data
@Entity
public class User {

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

    @NonNull
    @NotBlank
    @Column(nullable = false)
    private School school;

    private Number expectedYearOfGraduation;

}
