package ui;

import bean.Area;
import bean.Group;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AddGroupDialog extends JDialog implements ActionListener, ItemListener {
    private JTextField tfName = new JTextField();
    private JComboBox<Area> cbArea = new JComboBox<>();
    private JComboBox<Area> cbStreet = new JComboBox<>();
    private JButton btnSave = new JButton("Save");
    private JButton btnClose = new JButton("Close");
    private MainFrame mainFrame;
    private List<Area> areas;

    public AddGroupDialog(MainFrame mainFrame, List<Area> areas){
        this.areas = areas;
        this.mainFrame = mainFrame;
        initUI();
        setModal(true);
        setTitle("Add Group");
        setSize(400, 200);
        this.setLocation((int)(mainFrame.getWidth() / 2 - this.getWidth() /2 + mainFrame.getLocation().getX()),
                (int)(mainFrame.getHeight() / 2 - this.getHeight() / 2 + mainFrame.getLocation().getY()));
    }

    private void initUI(){
        List<Area> districts = areas.stream().filter(area -> area.getPid() == 0).collect(Collectors.toList());
        districts.forEach(area -> cbArea.addItem(area));
        cbArea.setSelectedIndex(-1);
        Container c = getContentPane();
        c.setLayout(new GridLayout(0, 2, 10, 10));
        c.add(new JLabel("Name"));
        c.add(tfName);
        c.add(new JLabel("Area"));
        c.add(cbArea);
        c.add(new JLabel("Street"));
        c.add(cbStreet);
        c.add(btnSave);
        c.add(btnClose);
        btnSave.addActionListener(this);
        btnClose.addActionListener(this);
        cbArea.addItemListener(this);
        cbStreet.addItemListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSave){
            doSave();
        } else if (e.getSource() == btnClose){
            setVisible(false);
        }
    }

    private void doSave(){
        if (tfName.getText() == null || tfName.getText().trim().length() == 0){
            JOptionPane.showMessageDialog(this, "input name");
            return;
        }
        for (int i = 0; i < mainFrame.getGroups().size(); i++) {
            Group g = mainFrame.getGroups().get(i);
            if (tfName.equals(g.getName())){
                JOptionPane.showMessageDialog(this, "Group name duplicate for "+ g.getName());
                return;
            }
        }
        if (cbArea.getSelectedIndex() == -1){
            JOptionPane.showMessageDialog(this, "select area");
            return;
        }
        if (cbStreet.getSelectedIndex() == -1){
            JOptionPane.showMessageDialog(this, "select street");
            return;
        }
        Area area = (Area)cbArea.getSelectedItem();
        Area street = (Area)cbStreet.getSelectedItem();
        Group group = new Group();
        group.setName(tfName.getText());
        group.setStatus((byte)1);
        group.setTime(0);
        group.setCover("");
        group.setIntro("");
        group.setArea(area.getId());
        group.setStreet(street.getId());
        group.setArea_name(area.getLabel());
        group.setStreet_name(street.getLabel());
        WaitingDialog wd = new WaitingDialog() {
            @Override
            public Object work() {
                return mainFrame.getGroupDao().saveGroup(group);
            }
        };
        if ((int)wd.getReturnResult() >= 1){
            mainFrame.getGroups().add(group);
            mainFrame.getGroupPanel().loadData(mainFrame.getGroups());
            setVisible(false);
            JOptionPane.showMessageDialog(mainFrame, "add group successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "add group failed.");
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (cbArea == e.getSource()){
            if (e.getStateChange() == ItemEvent.SELECTED){
                Area area = (Area) e.getItem();
                cbStreet.removeAllItems();
                List<Area> streets = areas.stream().filter(area1 -> area1.getPid() == area.getId()).collect(Collectors.toList());
                streets.forEach(s -> cbStreet.addItem(s));
            }
        }
    }


}
