package mizael.jackeline.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(
        name = "AGENDAMENTO",
        uniqueConstraints = @UniqueConstraint(columnNames = {"data", "horario"})
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "servico")
    private String servico;

    @Column(name = "data")
    private LocalDate data;

    @Column(name = "horario")
    private LocalTime horario;

    @Column(name = "status")
    private String status;

    @Column(name = "id_evento_calendar")
    private String idEventoCalendar;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;
}
