package ru.itmo.product.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itmo.product.model.enums.UnitOfMeasure;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Product {
    @Id
    //@SequenceGenerator(name="PRODUCT_SEQUENCE", sequenceName="SQ_PRODUCT_SEQUENCE")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @Column(name = "name", nullable = false)
    private String name; //Поле не может быть null, Строка не может быть пустой
    @OneToOne
    @JoinColumn(name = "coordinates_id")
    private Coordinates coordinates; //Поле не может быть
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    @Column(name = "creationDate", nullable = false)
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    @Min(0)
    @Column(name = "price", nullable = false)
    private Integer price; //Поле может быть null, Значение поля должно быть больше 0
    @Column(name = "manufactureCost", nullable = false)
    private Double manufactureCost; //Поле не может быть null
    @Column(name = "unitOfMeasure", nullable = false)
    private UnitOfMeasure unitOfMeasure; //Поле может быть null
    @OneToOne
    @JoinColumn(name = "manufacturer_id")
    private Organization manufacturer; //Поле может быть null

    @PrePersist
    void prePersist() {
        this.creationDate = LocalDateTime.now();
    }
}