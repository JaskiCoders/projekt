package pl.com.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "user")
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String login;
    @NotNull
    private String password;
    @NotNull
    private Role role;

}
