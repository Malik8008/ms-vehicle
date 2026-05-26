package az.msvehicle.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "v_id")
    Long id;
    String brand;
    String model;
    boolean isDeleted;
    Long userId;
    @CreationTimestamp
    LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "person_id")
    Person person;
}
