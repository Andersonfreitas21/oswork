package com.algawork.oswork.domain.v1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "comentario")
public class ComentarioEntity implements Serializable {

    private static final long serialVersionUID = -8717304840514975265L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID uuid;

    @Column(name = "descricao_comentario", columnDefinition = "LONGTEXT")
    private String descricao;

    @Column(name = "data_envio", columnDefinition = "TIMESTAMP")
    private LocalDateTime dataEnvio;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "uuid_ordemServico", referencedColumnName = "uuid", nullable = false)
    private OrdemServicoEntity ordemServico;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt;

    @PreUpdate
    public void preUpdate(){updatedAt = LocalDateTime.now();}

    @PrePersist
    public void prePersist(){
        final LocalDateTime dateNow = LocalDateTime.now();
        createdAt = dateNow;
        updatedAt = dateNow;
    }
}
