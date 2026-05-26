package az.msvehicle.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_id")
    Long id;
    String name;
    String surname;
    int age;
    String phone;
    @CreationTimestamp
    LocalDateTime createdAt;
    boolean isDeleted;
    @OneToMany(mappedBy = "person")
    List<Vehicle> vehicles = new ArrayList<>();
}
