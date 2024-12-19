package com.gateway.gateway_services.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserDto {
  private Long id;
  private String username;
  private String password;  
}
