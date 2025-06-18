package com.example.configapi.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "config_constants", uniqueConstraints = @UniqueConstraint(columnNames = {"constant_name", "environment"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfigConstant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "constant_name", nullable = false)
    private String constantName;

    @Column(name = "constant_value", nullable = false)
    private String constantValue;

    @Column(name = "environment", nullable = false)
    private String environment;

    private String description;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
