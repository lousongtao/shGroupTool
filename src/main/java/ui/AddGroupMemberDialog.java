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
    private JButton btnSave = new JButton("Add into list");
    private JButton btnClose = new JButton("Close");
    private JRadioButton rb1 = new JRadioButton("组员", true);
    private JRadioButton rb2 = new JRadioButton("组长助理");
    private JRadioButton rb3 = new JRadioButton("卫生干部");
    private JRadioButton rb4 = new JRadioButton("指导医生");
    private JRadioButton rb5 = new JRadioButton("组长");

    private MainFrame mainFrame;
    public AddGroupMemberDialog(MainFrame mainFrame){
        this.mainFrame = mainFrame;
        JPanel prb = new JPanel();
        prb.add(rb1);
        prb.add(rb2);
        prb.add(rb3);
        prb.add(rb4);
        prb.add(rb5);
        ButtonGroup bg = new ButtonGroup();
        bg.add(rb1);
        bg.add(rb2);
        bg.add(rb3);
        bg.add(rb4);
        bg.add(rb5);
        JPanel pButton = new JPanel();
        pButton.add(btnSave);
        pButton.add(btnClose);
        Insets insets = new Insets(5,0,0,0);
        Container c = getContentPane();
        c.setLayout(new GridBagLayout());
        JLabel lbName = new JLabel("Name");
        JLabel lbID = new JLabel("ID Card");
        JLabel lbTele = new JLabel("Telephone");
        JLabel lbRole = new JLabel("Role");
        c.add(lbName,   new GridBagConstraints(0, 0, 1, 1,0,0, GridBagConstraints.WEST, GridBagConstraints.BOTH, insets,0,0));
        c.add(tfName,   new GridBagConstraints(1, 0, 1, 1,1,1, GridBagConstraints.WEST, GridBagConstraints.BOTH, insets,0,0));
        c.add(lbID,     new GridBagConstraints(0, 1, 1, 1,0,0, GridBagConstraints.WEST, GridBagConstraints.BOTH, insets,0,0));
        c.add(tfIDCard, new GridBagConstraints(1, 1, 1, 1,1,1, GridBagConstraints.WEST, GridBagConstraints.BOTH, insets,0,0));
        c.add(lbTele,   new GridBagConstraints(0, 2, 1, 1,0,0, GridBagConstraints.WEST, GridBagConstraints.BOTH, insets,0,0));
        c.add(tfTelephone, new GridBagConstraints(1, 2, 1, 1,1,1, GridBagConstraints.WEST, GridBagConstraints.BOTH, insets,0,0));
        c.add(lbRole,   new GridBagConstraints(0, 3, 1, 1,0,0, GridBagConstraints.WEST, GridBagConstraints.BOTH, insets,0,0));
        c.add(prb,      new GridBagConstraints(0, 4, 2, 1,1,1, GridBagConstraints.WEST, GridBagConstraints.BOTH, insets,0,0));
        c.add(pButton,  new GridBagConstraints(0, 5, 2, 1,1,1, GridBagConstraints.WEST, GridBagConstraints.BOTH, insets,0,0));

        btnSave.addActionListener(this);
        btnClose.addActionListener(this);
        setModal(true);
        setTitle("Add Group Member");
        setSize(500, 200);
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
            doSave();
        }
    }

    private void doSave(){
        if (tfName.getText() == null || tfName.getText().trim().length() == 0){
            JOptionPane.showMessageDialog(this, "no name");
            return;
        }
        gm = new GroupMember();
        gm.setName(tfName.getText());
        gm.setIdcard(tfIDCard.getText());
        gm.setTelephone(tfTelephone.getText());
        if (rb1.isSelected()) {
            gm.setRole(1);
            gm.setTitle(rb1.getText());
        } else if (rb2.isSelected()) {
            gm.setRole(2);
            gm.setTitle(rb2.getText());
        } else if (rb3.isSelected()) {
            gm.setRole(3);
            gm.setTitle(rb3.getText());
        } else if (rb4.isSelected()) {
            gm.setRole(4);
            gm.setTitle(rb4.getText());
        } else if (rb5.isSelected()) {
            gm.setRole(5);
            gm.setTitle(rb5.getText());
        }
        setVisible(false);
    }

}
