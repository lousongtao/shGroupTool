package ui;

import bean.Group;
import bean.GroupMember;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GroupMemberDialog extends JDialog implements ActionListener {
    private MainFrame mainFrame;
    private Group group;
    private JLabel lbGroup = new JLabel("");
    private JTable table = new JTable();
    private TableModel tableModel = new TableModel();
    private List<GroupMember> groupMembers = new ArrayList<>();
    private JButton btnAddMember = new JButton("Add");
    private JButton btnSave = new JButton("Save");
    private JButton btnRemove = new JButton("Remove");
    private JButton btnClose = new JButton("Close");
    public GroupMemberDialog(MainFrame mainFrame, Group group){
        this.mainFrame = mainFrame;
        this.group = group;
        initUI();
        initData();
    }

    private void initData(){
        List<GroupMember> gms = mainFrame.getGroupMembers().stream().filter(gs -> gs.getGroup_id() == group.getId()).collect(Collectors.toList());
        groupMembers.clear();
        groupMembers.addAll(gms);
        tableModel.fireTableDataChanged();
    }

    private void initUI(){
        lbGroup.setText("Group ID : " + group.getId() + ", name : " + group.getName() + ", area : " + group.getArea_name() + ", street : " + group.getStreet_name());
        table.setModel(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane jsp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        JPanel pButton = new JPanel();
        pButton.add(btnAddMember);
        pButton.add(btnRemove);
        pButton.add(btnSave);
        pButton.add(btnClose);
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        c.add(lbGroup, BorderLayout.NORTH);
        c.add(jsp, BorderLayout.CENTER);
        c.add(pButton, BorderLayout.SOUTH);
        setModal(true);
        setTitle("Group Member Management");
        setSize(mainFrame.getWidth(), mainFrame.getHeight());
        setLocation(mainFrame.getLocation());
        btnAddMember.addActionListener(this);
        btnClose.addActionListener(this);
        btnRemove.addActionListener(this);
        btnSave.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSave){
            doSave();
        } else if (e.getSource() == btnRemove){
            doRemove();
        } else if (e.getSource() == btnClose){
            setVisible(false);
        } else if (e.getSource() == btnAddMember){
            doAdd();
        }
    }

    private void doAdd(){
        AddGroupMemberDialog dlg = new AddGroupMemberDialog(mainFrame);
        dlg.setVisible(true);
        GroupMember gm = dlg.getGroupMember();
        if (gm != null){
            gm.setStatus((byte)-1);
            gm.setGroup_id(group.getId());
            gm.setUser_id(0);
            gm.setGroup_name(group.getName());
            gm.setArea_name(group.getArea_name());
            gm.setStreet_name(group.getStreet_name());
            groupMembers.add(gm);
            tableModel.fireTableDataChanged();
        }
    }

    private void doRemove(){
        int row = table.getSelectedRow();
        if (row < 0)
            return;
        GroupMember gm = groupMembers.get(row);
        if (gm.getId() > 0){
            JOptionPane.showMessageDialog(this, "这条记录已经插入数据库, 不能移除. ");
            return;
        }
        groupMembers.remove(row);
        tableModel.fireTableDataChanged();
    }

    private void doSave(){
        final List<GroupMember> gms = groupMembers.stream().filter(gm -> gm.getId() == 0).collect(Collectors.toList());
        if (gms.isEmpty()){
            JOptionPane.showMessageDialog(this, "no new record");
            return;
        }
        WaitingDialog wd = new WaitingDialog() {
            @Override
            public Object work() {
                return mainFrame.getGroupMemberDao().save(gms);
            }
        };
        List<GroupMember> gms2 = (List<GroupMember>)wd.getReturnResult();
        if (gms2 == null){
            JOptionPane.showMessageDialog(this, "save failed");
        } else {
            JOptionPane.showMessageDialog(this, "save successfully");
            mainFrame.getGroupMembers().addAll(gms2);
            initData();
        }
    }

    class TableModel extends DefaultTableModel{
        private String[] header = new String[]{"id", "group_id", "user_id", "role", "title", "idcard", "name", "status", "group_name", "area_name", "street_name", "telephone"};

        @Override
        public int getRowCount() {
            if (groupMembers == null) return 0;
            return groupMembers.size();
        }

        @Override
        public int getColumnCount() {
            return header.length;
        }

        @Override
        public String getColumnName(int column) {
            return header[column];
        }
        @Override
        public Object getValueAt(int row, int column) {
            GroupMember gm = groupMembers.get(row);
            switch (column){
                case 0:
                    return gm.getId();
                case 1:
                    return gm.getGroup_id();
                case 2:
                    return gm.getUser_id();
                case 3:
                    return gm.getRole();
                case 4:
                    return gm.getTitle();
                case 5:
                    return gm.getIdcard();
                case 6:
                    return gm.getName();
                case 7:
                    return gm.getStatus();
                case 8:
                    return gm.getGroup_name();
                case 9:
                    return gm.getArea_name();
                case 10:
                    return gm.getStreet_name();
                case 11:
                    return gm.getTelephone();
            }
            return "";
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }
}
