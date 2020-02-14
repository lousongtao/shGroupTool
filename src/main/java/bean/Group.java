package bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="cmf_group")
public class Group {
    @Id
    private int id;
    private String name;
    private byte status;
    private int time;
    private int area;
    private int street;
    @Column(columnDefinition = "TEXT")
    private String intro;
    private String cover;
    private String areaName;
    private String streetName;
}
