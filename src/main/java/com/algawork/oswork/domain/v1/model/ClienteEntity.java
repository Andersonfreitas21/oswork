package com.algawork.oswork.domain.v1.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "cliente")
public class ClienteEntity implements Serializable {

    private static final long serialVersionUID = -8717304840514975265L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID uuid;

    @Column(name = "cl_nome", length = 150, nullable = false)
    private String nome;
    @Column(name = "cl_email", length = 50, nullable = true)
    private String email;
    @Column(name = "cl_telefone", length = 30, nullable = true)
    private String telefone;

    @OneToMany(mappedBy = "cliente")
    private List<OrdemServicoEntity> ordemServicoEntityList;

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
