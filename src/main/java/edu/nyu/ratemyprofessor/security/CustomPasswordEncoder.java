package edu.nyu.ratemyprofessor.security;

import java.security.SecureRandom;
import org.apache.commons.codec.digest.DigestUtils;

public class CustomPasswordEncoder {

  private static final SecureRandom RANDOM = new SecureRandom();

  public String encode(CharSequence rawPassword) {
    String salt = getRandomSalt();
    return salt + ":" + hashPassword(rawPassword.toString(), salt);
  }

  public boolean matches(CharSequence rawPassword, String encodedPassword) {
    String[] parts = encodedPassword.split(":");
    if (parts.length != 2) {
      return false;
    }
    String salt = parts[0];
    String encodedRawPass = hashPassword(rawPassword.toString(), salt);
    return encodedPassword.equals(salt + ":" + encodedRawPass);
  }

  private String getRandomSalt() {
    byte[] salt = new byte[16];
    RANDOM.nextBytes(salt);
    return bytesToHex(salt);
  }

  private String hashPassword(String password, String salt) {
    return DigestUtils.sha256Hex(salt + password);
  }

  private String bytesToHex(byte[] bytes) {
    StringBuilder sb = new StringBuilder();
    for (byte b : bytes) {
      sb.append(String.format("%02x", b));
    }
    return sb.toString();
  }
}
