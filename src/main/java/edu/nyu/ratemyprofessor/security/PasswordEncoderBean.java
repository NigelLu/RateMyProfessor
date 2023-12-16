package edu.nyu.ratemyprofessor.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PasswordEncoderBean {

  @Bean
  public CustomPasswordEncoder passwordEncoder() {
    /**
     * PasswordEncoder Bean using BCrypt
     */
    return new CustomPasswordEncoder();
  }
}
