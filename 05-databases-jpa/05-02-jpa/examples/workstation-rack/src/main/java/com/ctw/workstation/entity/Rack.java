package com.ctw.workstation.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;

import java.util.Date;

@Entity
@Table(name = "T_RACK")


public class Rack extends PanacheEntityBase {
    public static final String GET_ALL = "Rack.getAll";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rackIdGenerator")
    @SequenceGenerator(name = "rackIdGenerator", sequenceName = "SEQ_RACK_ID")
    public Long id;
    @Column(name = "SERIAL_NUMBER", length = 20, nullable = false)
    public String serialNumber;
    @Transient
    public Integer age;
    @Temporal(TemporalType.DATE)
    @Column(name = "ASSEMBLED_AT")
    public Date assembledAt;
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    public Status status;
    @Column(name = "TEAM_ID", nullable = false)
    public Long teamId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID", updatable = false, insertable = false, nullable = false)
    public Team team;
}
