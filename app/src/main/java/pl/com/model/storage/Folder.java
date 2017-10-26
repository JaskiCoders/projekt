package pl.com.model.storage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Entity
@Table(name = "folder")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Folder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @NotNull
    @Column
    private String folderName;
    @NotNull
    @Column
    private String createDate;
    @NotNull
    @OneToMany
    private Set<File> files;
    @NotNull
    @OneToMany
    private Set<Folder> subFolders;
}
