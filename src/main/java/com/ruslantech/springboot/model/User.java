package com.ruslantech.springboot.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "user")
public class User implements UserDetails {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "name")
   private String firstName;

   @Column(name = "last_name")
   private String lastName;

   @Column(name = "email")
   private String email;

   @Column(name = "password")
   private String password;

   @ManyToMany(fetch = FetchType.LAZY, cascade= CascadeType.ALL)
   @JoinTable(name = "user_role",
           joinColumns = { @JoinColumn(name = "id") },
           inverseJoinColumns = { @JoinColumn(name = "id_role")})
   private Set<Role> roles;

   public User() {
   }
   
   public User(String firstName, String lastName, String email) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(final String lastName) {
      this.lastName = lastName;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {

      this.email = email;
   }

   public Set<Role> getRoles() {
      return roles;
   }

   public void setRoles(Set<Role> roles) {
      this.roles = roles;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   @Override
   public String getPassword() {
      return password;
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return getRoles();
   }

   @Override
   public String getUsername() {
      return getFirstName();
   }

   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
      return true;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      User user = (User) o;

      if (id != null ? !id.equals(user.id) : user.id != null) return false;
      if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
      if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
      if (email != null ? !email.equals(user.email) : user.email != null) return false;
      if (password != null ? !password.equals(user.password) : user.password != null) return false;
      return roles != null ? roles.equals(user.roles) : user.roles == null;
   }

   @Override
   public int hashCode() {
      int result = id != null ? id.hashCode() : 0;
      result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
      result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
      result = 31 * result + (email != null ? email.hashCode() : 0);
      result = 31 * result + (password != null ? password.hashCode() : 0);
      result = 31 * result + (roles != null ? roles.hashCode() : 0);
      return result;
   }
}
