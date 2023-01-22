package com.seris.api.entities;

import com.seris.api.annotations.Attribute;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@Data
@DynamicUpdate
public abstract class BaseEntity {
    @Attribute(value = "ID", skip = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Attribute(value = "uuid", skip = true)
    @NotEmpty
    @NotNull
    @Column(unique = true)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String uuid = UUID.randomUUID().toString();

    @Attribute(value = "Үүсгэсэн огноо", skip = true)
    @NotNull
    private LocalDateTime created;

    @Attribute(value = "Үүсгэсэн огноо", skip = true)
    @NotNull
    private LocalDateTime updated;

    @PrePersist
    protected void onCreate() {
        created = LocalDateTime.now();
        updated = created;
    }

    @PreUpdate
    protected void onUpdate() {
        updated = LocalDateTime.now();
    }
}
