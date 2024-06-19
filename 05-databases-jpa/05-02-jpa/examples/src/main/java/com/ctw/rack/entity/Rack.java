package com.ctw.rack.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import java.util.Date;

@Entity
@Table(name = "T_RACK")

@NamedQuery(
        name = Rack.GET_ALL,
        query = "select r from T_RACK r")
public class Rack {
    public static final String GET_ALL = "Rack.getAll";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rackIdGenerator")
    @SequenceGenerator(name = "rackIdGenerator", sequenceName = "SEQ_RACK_ID")
    private Long id;
    @Column(name = "SERIAL_NUMBER", length = 20, nullable = false)
    private String serialNumber;
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private Status status;
    @Transient
    private Integer age;
    @Temporal(TemporalType.DATE)
    @Column(name = "ASSEMBLED_AT")
    private Date assembledAt;
    @Enumerated(EnumType.STRING)
    @Column(name = "DEFAULT_LOCATION")
    private DefaultLocation defaultLocation;
    @Column(name = "TEAM_ID", nullable = false)
    private Long teamId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID", updatable = false, insertable = false, nullable = false)
    private Team team;
}
