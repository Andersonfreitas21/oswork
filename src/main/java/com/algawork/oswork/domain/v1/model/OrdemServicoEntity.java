package com.algawork.oswork.domain.v1.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "ordem_servico")
public class OrdemServicoEntity implements Serializable {

    private static final long serialVersionUID = -8717304840514975265L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID uuid;

    @Column(name = "descricao_os", columnDefinition = "LONGTEXT")
    private String descricao;
    @Column(name = "preco", precision = 10, scale = 4, nullable = false)
    private BigDecimal preco;
    @Column(name = "data_abertura", columnDefinition = "TIMESTAMP")
    private LocalDateTime dataAbertura;
    @Column(name = "data_finalizacao", columnDefinition = "TIMESTAMP")
    private LocalDateTime dataFinalizacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "ENUM('ABERTA', 'FINALIZADA', 'CANCELADA')", nullable = false)
    private StatusOrdemServico status;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "uuid_cliente", referencedColumnName = "uuid", nullable = false)
    private ClienteEntity cliente;

    @OneToMany(mappedBy = "ordemServico")
    private List<ComentarioEntity> comentarios;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt;

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    @PrePersist
    public void prePersist() {
        final LocalDateTime dateNow = LocalDateTime.now();
        createdAt = dateNow;
        updatedAt = dateNow;
    }
}

