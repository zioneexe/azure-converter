package cl.labs.conversion.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import java.time.Instant;

@Getter
@Setter
@Entity
public class JobFile {
    @Id
    @Column(name = "job_file_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @NotNull
    @Nationalized
    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Size(max = 512)
    @NotNull
    @Nationalized
    @Column(name = "storage_path", nullable = false, length = 512)
    private String storagePath;

    @ColumnDefault("sysutcdatetime()")
    @Column(name = "uploaded_at")
    private Instant uploadedAt;

}