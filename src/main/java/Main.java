import bean.Area;
import bean.Group;
import bean.GroupMember;
import dao.AreaDao;
import dao.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class Main {
    private List<Area> areas;
    private List<Group> groups;
    private List<GroupMember> groupMembers;
    public Main(){
        //load data
        loadData();
    }

    private void loadData(){
        AreaDao dao = new AreaDao();
        areas = dao.getAreas();

    }
    public static void main(String[] args) {
        new Main();
    }
}
