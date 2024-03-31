package dsi.esprit.tn.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clubs")
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String name;
    @NotBlank
    @Size(max = 600)
    private String description;
    @NotBlank
    @Size(max = 20)
    private String type;
    @NotBlank
    @Size(max = 20)
    private String field;
    @NotBlank
    @Size(max = 20)
    private String status;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "club")
    Set<clubFile> files;
}
