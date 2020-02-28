package bean;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="cmf_group_member")
public class GroupMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int group_id;
    private int user_id;
    private int role;
    private String title;
    private String idcard;
    private String name;
    @Column(columnDefinition = "tinyint")
    private byte status;
    private String group_name;
    private String area_name;
    private String street_name;
    private String telephone;
}
