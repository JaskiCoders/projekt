package pl.com.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "role")
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue
    @Column
    private Long id;
    @NotNull
    @Column
    private String name;
    @NotNull
    @Column
    private RoleType type;
}
