package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GroupPanel extends JPanel implements ActionListener {
    private String[] header = new String[]{"id", "name", "status", "time", "area_name", "street_name"};
    private JTable table = new JTable();
    private TableModel tableModel = new DefaultTableModel(header, 0);
    private JButton btnAdd = new JButton("Add");
    private JButton btnMember = new JButton("Member");
    public GroupPanel(){
        initUI();
        loadData();
    }

    private void loadData(){

    }

    private void initUI(){
        table.setModel(tableModel);
        JScrollPane jsp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        JPanel pButton = new JPanel();
        pButton.add(btnAdd);
        pButton.add(btnMember);
        btnAdd.addActionListener(this);
        btnMember.addActionListener(this);
        setLayout(new BorderLayout());
        add(jsp, BorderLayout.CENTER);
        add(pButton, BorderLayout.SOUTH);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnMember){

        } else if (e.getSource() == btnAdd){

        }
    }
}
