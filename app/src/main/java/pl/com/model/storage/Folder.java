package pl.com.model.storage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Data
@Entity
@Table(name = "file ")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Folder {

    private String folderName;
    private String createDate;
    @OneToMany
    private Set<File> files;
    @OneToMany
    private Set<Folder> subFolders;
}
