package ui;

import bean.GroupMember;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddGroupMemberDialog extends JDialog implements ActionListener {
    private JTextField tfName = new JTextField();
    private JTextField tfIDCard = new JTextField();
    private JTextField tfTelephone = new JTextField();
    private GroupMember gm;
    private JButton btnSave = new JButton("Save");
    private JButton btnClose = new JButton("Close");
    private JComboBox<Role> cbRole = new JComboBox();
    private MainFrame mainFrame;
    public AddGroupMemberDialog(MainFrame mainFrame){
        this.mainFrame = mainFrame;
        cbRole.addItem(new Role(5, "组长"));
        cbRole.addItem(new Role(4, "指导医生"));
        cbRole.addItem(new Role(3, "卫生干部"));
        cbRole.addItem(new Role(2, "组长助理"));
        cbRole.addItem(new Role(1, "组员"));
        Container c = getContentPane();
        c.setLayout(new GridLayout(0, 2, 10, 10));
        c.add(new JLabel("Name"));
        c.add(tfName);
        c.add(new JLabel("ID Card"));
        c.add(tfIDCard);
        c.add(new JLabel("Telephone"));
        c.add(tfTelephone);
        c.add(new JLabel("Role"));
        c.add(cbRole);
        c.add(btnSave);
        c.add(btnClose);
        btnSave.addActionListener(this);
        btnClose.addActionListener(this);
        setModal(true);
        setTitle("Add Group Member");
        setSize(300, 200);
        this.setLocation((int)(mainFrame.getWidth() / 2 - this.getWidth() /2 + mainFrame.getLocation().getX()),
                (int)(mainFrame.getHeight() / 2 - this.getHeight() / 2 + mainFrame.getLocation().getY()));
    }

    public GroupMember getGroupMember(){
        return gm;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnClose){
            setVisible(false);
        } else if (e.getSource() == btnSave){
            if (tfName.getText() == null || tfName.getText().trim().length() == 0){
                JOptionPane.showMessageDialog(this, "no name");
                return;
            }
            gm = new GroupMember();
            gm.setName(tfName.getText());
            gm.setIdcard(tfIDCard.getText());
            gm.setTelephone(tfTelephone.getText());
            gm.setRole(((Role)cbRole.getSelectedItem()).value);
            gm.setTitle(((Role)cbRole.getSelectedItem()).name);
            setVisible(false);
        }
    }

    class Role {
        private int value;
        private String name;
        public Role(int value, String name){
            this.value = value;
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return value + " " + name;
        }
    }
}
