package ui;

import bean.Area;
import bean.Group;
import bean.GroupMember;
import dao.AreaDao;
import dao.GroupDao;
import dao.GroupMemberDao;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class MainFrame extends JFrame {
    private java.util.List<Area> areas;
    private java.util.List<Group> groups;
    private List<GroupMember> groupMembers;
    private AreaDao areaDao = new AreaDao();
    private GroupDao groupDao = new GroupDao();
    private GroupMemberDao groupMemberDao = new GroupMemberDao();
    private GroupPanel groupPanel = new GroupPanel(this);
    public MainFrame(){
        initUI();
        loadData();
    }

    private void initUI(){
        Container c = this.getContentPane();
        c.setLayout(new BorderLayout());

        c.add(groupPanel, BorderLayout.CENTER);
    }

    private void loadData(){
        WaitingDialog wdlg = new WaitingDialog() {
            @Override
            public Object work() {
                areas = areaDao.getAreas();
                System.out.println("Query area successfully. Get results : " + areas.size());
                groups = groupDao.getAllGroup();
                System.out.println("Query group successfully. Get results : " + groups.size());
                groupMembers = groupMemberDao.getAllGroupMember();
                System.out.println("Query group member successfully. Get results : " + groupMembers.size());
                return null;
            }
        };
        groupPanel.loadData(groups);
    }

    public List<Area> getAreas() {
        return areas;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public List<GroupMember> getGroupMembers() {
        return groupMembers;
    }

    public AreaDao getAreaDao() {
        return areaDao;
    }

    public GroupDao getGroupDao() {
        return groupDao;
    }

    public GroupMemberDao getGroupMemberDao() {
        return groupMemberDao;
    }

    public GroupPanel getGroupPanel() {
        return groupPanel;
    }

    public static void main(String[] args) {
        MainFrame f = new MainFrame();
        f.setSize(800,600);
        f.setTitle("Group Management");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setVisible(true);

    }
}
