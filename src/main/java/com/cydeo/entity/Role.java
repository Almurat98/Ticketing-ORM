package com.cydeo.entity;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "roles")
@Where(clause = "is_deleted=false")
public class Role extends BaseEntity {

    private String description;

}
