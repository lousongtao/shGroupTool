package bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="cmf_area")
public class Area {
    @Id
    private int id;
    private int pid;
    private String label;

    @Override
    public String toString() {
        return id + " " + label ;
    }
}
