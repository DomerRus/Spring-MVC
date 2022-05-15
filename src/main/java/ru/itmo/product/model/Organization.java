package ru.itmo.product.model;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import ru.itmo.product.model.enums.OrganizationType;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Organization {
    @Id
    //@SequenceGenerator(name="ORGANIZATION_SEQUENCE", sequenceName="SQ_ORGANIZATION_SEQUENCE")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @Column(name = "name", nullable = false)
    private String name; //Поле не может быть null, Строка не может быть пустой
    @Length(max = 1599)
    @Column(name = "fullName", nullable = false)
    private String fullName; //Длина строки не должна быть больше 1599, Поле не может быть
    @Min(0)
    @Column(name = "employeesCount", nullable = false)
    private Long employeesCount; //Значение поля должно быть больше 0
    @Column(name = "type", nullable = false)
    private OrganizationType type; //Поле не может быть
}
