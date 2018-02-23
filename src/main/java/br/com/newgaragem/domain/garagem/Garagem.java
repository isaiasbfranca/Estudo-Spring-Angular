package br.com.newgaragem.domain.garagem;

import br.com.newgaragem.domain.carro.Carro;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "garagem")
public class Garagem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "garagem_id_seq")
    @SequenceGenerator(name = "garagem-id_seq",sequenceName = "estado_id_seq",allocationSize = 1)
    @Column(name = "id")
    @Getter
    private long id;

    @NotEmpty
    @Size(max = 50)
    @Column(name = "nome")
    @Getter
    @Setter
    private String nome;

    @NotEmpty
    @Size(max = 14)
    @Column(name = "cnpj")
    @Getter
    @Setter
    private String cnpj;

    @NotEmpty
    @Size(max = 80)
    @Column(name = "endereco")
    @Getter
    @Setter
    private String endereco;

    @NotEmpty
    @Size(max = 8)
    @Column(name = "cep")
    @Getter
    @Setter
    private String cep;

    @NotEmpty
    @Size(max = 11)
    @Column(name = "telefone")
    @Getter
    @Setter
    private String telefone;

    @OneToMany (fetch = FetchType.EAGER, mappedBy = "garagem")
    @Getter
    @Setter
    private Carro carro;
}
