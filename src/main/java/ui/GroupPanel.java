package ui;

import bean.Group;
import javafx.scene.control.TableSelectionModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GroupPanel extends JPanel implements ActionListener {

    private JTable table = new JTable();
    private TableModel tableModel = new TableModel();
    private JButton btnAddStreet = new JButton("Add Street");
    private JButton btnAddGroup = new JButton("Add Group");
    private JButton btnMember = new JButton("Add Member");
    private List<Group> groups = new ArrayList<>();
    private MainFrame mainFrame;
    public GroupPanel(MainFrame mainFrame){
        this.mainFrame = mainFrame;
        initUI();
    }

    private void initUI(){
        table.setModel(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane jsp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        JPanel pButton = new JPanel();
        pButton.add(btnAddStreet);
        pButton.add(btnAddGroup);
        pButton.add(btnMember);
        btnAddStreet.addActionListener(this);
        btnAddGroup.addActionListener(this);
        btnMember.addActionListener(this);
        setLayout(new BorderLayout());
        add(jsp, BorderLayout.CENTER);
        add(pButton, BorderLayout.SOUTH);
    }

    public void loadData(List<Group> data){
        groups = data;
        tableModel.fireTableDataChanged();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnMember){
            addGroupMember();
        } else if (e.getSource() == btnAddGroup){
            addGroup();
        } else if (e.getSource() == btnAddStreet){
            addStreet();
        }
    }

    private void addStreet(){
        AddStreetDialog dlg = new AddStreetDialog(this.mainFrame, mainFrame.getAreas());
        dlg.setVisible(true);
    }

    private void addGroupMember(){
        int row = table.getSelectedRow();
        if (row < 0){
            JOptionPane.showMessageDialog(this, "choose one group");
            return;
        }
        Group g = groups.get(table.getSelectedRow());
        GroupMemberDialog dlg = new GroupMemberDialog(mainFrame, g);
        dlg.setVisible(true);
    }

    private void addGroup(){
        AddGroupDialog dlg = new AddGroupDialog(this.mainFrame, mainFrame.getAreas());
        dlg.setVisible(true);
    }

    public JTable getTable() {
        return table;
    }

    public TableModel getTableModel() {
        return tableModel;
    }

    class TableModel extends DefaultTableModel{
        private String[] header = new String[]{"id", "name", "status", "time", "area", "street", "intro", "cover","area_name", "street_name"};

        @Override
        public int getRowCount() {
            if (groups == null) return 0;
            return groups.size();
        }

        @Override
        public int getColumnCount() {
            return header.length;
        }

        @Override
        public Object getValueAt(int row, int column) {
            Group group = groups.get(row);
            switch (column){
                case 0:
                    return group.getId();
                case 1:
                    return group.getName();
                case 2:
                    return group.getStatus();
                case 3:
                    return group.getTime();
                case 4:
                    return group.getArea();
                case 5:
                    return group.getStreet();
                case 6:
                    return group.getIntro();
                case 7:
                    return group.getCover();
                case 8:
                    return group.getArea_name();
                case 9:
                    return group.getStreet_name();
            }
            return "";
        }

        @Override
        public String getColumnName(int column) {
            return header[column];
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }
}
