package cl.labs.conversion.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@Getter
@Setter
@Entity
public class Role {
    @Id
    @Column(name = "role_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Nationalized
    @Column(name = "role_name", nullable = false, length = 50)
    private String roleName;

    @Column(name = "max_requests")
    private Integer maxRequests;

    @Column(name = "max_files")
    private Integer maxFiles;

}