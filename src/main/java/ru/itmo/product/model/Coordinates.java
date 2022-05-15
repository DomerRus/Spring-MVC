package ru.itmo.product.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Coordinates {
    @Id
    //@SequenceGenerator(name="COORDINATES_SEQUENCE", sequenceName="SQ_COORDINATES_SEQUENCE")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "x")
    private Integer x;

    @Min(-687L)
    @Column(name = "y")
    private Double y; //Значение поля должно быть больше -687
}
