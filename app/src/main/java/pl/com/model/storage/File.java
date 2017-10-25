package pl.com.model.storage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import pl.com.model.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "file")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class File {

    @Id
    @Column(unique = true)
    private String pathToFile;
    @NotNull
    @Column
    private String fileName;
    //todo add user as notnull
    //@NotNull
    @ManyToOne
    private User author;
    @NotNull
    @Column
    private LocalDateTime createDate;
    @NotNull
    @Column
    private LocalDateTime modificationDate;

}