package org.sarthak.sample.Entities;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.sarthak.sample.Modals.Utilities;


import javax.persistence.*;

@Entity
@Getter @Setter
@EqualsAndHashCode
@SequenceGenerator(name="seq", initialValue=1, allocationSize=1)
@Table(name = "lead")
public class FetchLeadPersistance {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    @Column(name = "id")
    private int id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name="mobile", unique = true, length = 10)
    private String mobile;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "location_type")
    private Utilities.LOCATION locationType;
    @Column(name = "location_string")
    private String locationString;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Utilities.STATUS status;
    @Column(name = "communication")
    private String communication = "";
}
