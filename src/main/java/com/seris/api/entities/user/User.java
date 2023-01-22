package com.seris.api.entities.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seris.api.annotations.Attribute;
import com.seris.api.entities.BaseEntity;
import com.seris.api.enums.Role;
import com.seris.api.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class User extends BaseEntity implements UserDetails {
    @Attribute(value = "Нэвтрэх нэр", trim = true, lower = true, noSpace = true)
    @NotNull
    @NotEmpty
    @Column(unique = true)
    @Length(min = 2, max = 15)
    private String username;

    @Attribute(value = "Нууц үг", descDev = "BCrypt ашиглаж encode хийсэн")
    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @Attribute(value = "Статус", skip = true)
    private Status status;

    @Attribute(value = "Хэрэглэгчийн эрх", skip = true)
    @NotNull(message = "Эрх тодорхойгүй байна")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<Role> roles;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(e -> new SimpleGrantedAuthority(e.name())).toList();
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return status == Status.ACTIVE;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return status == Status.ACTIVE;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return status == Status.ACTIVE;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return status == Status.ACTIVE;
    }
}
