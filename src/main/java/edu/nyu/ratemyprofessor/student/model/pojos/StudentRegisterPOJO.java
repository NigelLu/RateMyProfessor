package edu.nyu.ratemyprofessor.student.model.pojos;

import java.lang.reflect.Field;

public class StudentRegisterPOJO {
  private String email;
  private Long schoolId;
  private String password;
  private String lastName;
  private String firstName;
  private Integer expectedYearOfGraduation;

  public boolean hasAllRequiredFields() {
    Field[] fields = this.getClass().getDeclaredFields();

    for (Field field : fields) {
      try {
        field.setAccessible(true);
        if (field.get(this) == null) {
          return false;
        }
      } catch (IllegalAccessException e) {
        e.printStackTrace();
        return false;
      }
    }
    return true;
  }

  public String getLastName() {
    return this.lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public Integer getExpectedYearOfGraduation() {
    return this.expectedYearOfGraduation;
  }

  public void setExpectedYearOfGraduation(Integer expectedYearOfGraduation) {
    this.expectedYearOfGraduation = expectedYearOfGraduation;
  }

  public Long getSchoolId() {
    return this.schoolId;
  }

  public void setSchoolId(Long schoolId) {
    this.schoolId = schoolId;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
