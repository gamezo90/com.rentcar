package com.rentcar.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;

import javax.persistence.*;

@Data
@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Credentials {

   private String login;

   @JsonIgnore
   private String password;

   private String email;
}

