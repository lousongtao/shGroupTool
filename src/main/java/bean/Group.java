package bean;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="cmf_group")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private byte status;
    private int time;
    private int area;
    private int street;
    @Column(columnDefinition = "TEXT")
    private String intro;
    private String cover;
    private String area_name;
    private String street_name;
}
