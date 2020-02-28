package bean;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="cmf_area")
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int pid;
    private String label;

    @Override
    public String toString() {
        return id + " " + label ;
    }
}
