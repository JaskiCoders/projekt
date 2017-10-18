package pl.com.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "user")
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue
    @Column
    private Long id;
    @NotNull
    @Column
    private String login;
    @NotNull
    @Column
    private String username;
    @NotNull
    @Column
    private String password;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;
    @NotNull
    @Column
    private boolean enabled;

}
