package br.com.newgaragem.domain.carro;

import br.com.newgaragem.domain.garagem.Garagem;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Carro")
public class Carro implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "carro_id_seq")
    @SequenceGenerator(name = "carro_id_seq", sequenceName = "carro_id_seq", allocationSize = 1)
    @Column(name = "id")
    @Getter
    private long id;

    @NotEmpty
    @Size
    @Column(name = "nome")
    @Getter
    @Setter
    private String nome;

    @NotEmpty
    @Size
    @Column(name = "marca")
    @Getter
    @Setter
    private String marca;

    @NotNull
    @Column(name = "ano")
    @Temporal(value = TemporalType.DATE)
    @DateTimeFormat (pattern = "dd/MM/yyyy")
    @Getter
    @Setter
    private Date ano;

    @NotNull
    @Max(4)
    @Min(2)
    @Column(name = "portas")
    @Getter
    @Setter
    private Integer portas;

    @Size
    @Column(name = "opcionais")
    @Getter
    @Setter
    private String opciononais;

    @NotEmpty
    @Size
    @Column(name = "valor")
    @Getter
    @Setter
    private String Valor;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "garagem_id", referencedColumnName = "id")
    @Getter
    @Setter
    private Garagem garagem;

}
