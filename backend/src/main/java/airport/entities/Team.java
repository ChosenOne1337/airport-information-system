package airport.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "team")
@Getter @Setter
public class Team extends AbstractEntity<Long> {

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    private List<Employee> employees = new ArrayList<>();

    @Formula("(select coalesce(avg(e.salary), 0.0) from employee e where e.team_id = id)")
    private Double averageSalary;

}
