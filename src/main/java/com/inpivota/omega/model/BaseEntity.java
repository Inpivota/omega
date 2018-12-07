package com.inpivota.omega.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.Instant;
import java.util.UUID;

@Data
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue
    @Type(type = "org.hibernate.type.UUIDCharType")
    @JsonIgnore
    private UUID id;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    @JsonIgnore
    private Instant createdAt = Instant.now();

    public abstract String getUiLabel();
}
