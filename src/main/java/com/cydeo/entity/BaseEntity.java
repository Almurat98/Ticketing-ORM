package com.cydeo.entity;

import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@MappedSuperclass
@EntityListeners(BaseEntityListener.class)
public class BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,updatable = false)
    LocalDateTime insertDateTime;
    @Column(nullable = false,updatable = false)
    Long insertUserId;
    @Column(nullable = false)
    LocalDateTime lastUpdateDateTime;
    @Column(nullable = false)
    Long lastUpdateUserId;
    private boolean isDeleted = false;



}
