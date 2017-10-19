package pl.com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    @Column
    private Long id;
    @NotNull
    @Column(unique = true)
    private String login;
    @NotNull
    @Column(unique = true)
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
