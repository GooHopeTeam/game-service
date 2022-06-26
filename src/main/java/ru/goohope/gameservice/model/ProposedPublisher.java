package ru.goohope.gameservice.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "proposed_publisher")
public class ProposedPublisher extends Publisher {
}
