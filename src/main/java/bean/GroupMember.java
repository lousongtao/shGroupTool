package bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="cmf_group_member")
public class GroupMember {
    @Id
    private int id;
    private int groupId;
    private int userId;
    private int role;
    private String title;
    private String idcard;
    private String name;
    @Column(columnDefinition = "tinyint")
    private byte status;
    private String groupName;
    private String areaName;
    private String streetName;
}
