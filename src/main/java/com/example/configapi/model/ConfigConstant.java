package com.example.configapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "config_constants", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"constantName", "environment"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfigConstant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String constantName;
    private String constantValue;
    private String environment;
    private String description;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
