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

public class AddStreetDialog extends JDialog implements ActionListener {
    private JTextField tfName = new JTextField();
    private JComboBox<Area> cbArea = new JComboBox<>();
    private JButton btnSave = new JButton("Save");
    private JButton btnClose = new JButton("Close");
    private MainFrame mainFrame;
    private List<Area> areas;

    public AddStreetDialog(MainFrame mainFrame, List<Area> areas){
        this.areas = areas;
        this.mainFrame = mainFrame;
        initUI();
        setModal(true);
        setTitle("Add Street");
        setSize(400, 150);
        this.setLocation((int)(mainFrame.getWidth() / 2 - this.getWidth() /2 + mainFrame.getLocation().getX()),
                (int)(mainFrame.getHeight() / 2 - this.getHeight() / 2 + mainFrame.getLocation().getY()));
    }

    private void initUI(){
        List<Area> districts = areas.stream().filter(area -> area.getPid() == 0).collect(Collectors.toList());
        districts.forEach(area -> cbArea.addItem(area));
        cbArea.setSelectedIndex(-1);
        Container c = getContentPane();
        c.setLayout(new GridLayout(0, 2, 10, 10));
        c.add(new JLabel("Street Name"));
        c.add(tfName);
        c.add(new JLabel("Area"));
        c.add(cbArea);
        c.add(btnSave);
        c.add(btnClose);
        btnSave.addActionListener(this);
        btnClose.addActionListener(this);
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
        if (cbArea.getSelectedIndex() == -1){
            JOptionPane.showMessageDialog(this, "select area");
            return;
        }
        for (int i = 0; i < mainFrame.getAreas().size(); i++) {
            Area area = mainFrame.getAreas().get(i);
            if (area.getPid() == ((Area)cbArea.getSelectedItem()).getId()
                    && area.getLabel().equals(tfName.getText())){
                JOptionPane.showMessageDialog(this, "Street name duplicate for "+ tfName.getText());
                return;
            }
        }
        Area street = new Area();
        street.setPid(((Area)cbArea.getSelectedItem()).getId());
        street.setLabel(tfName.getText());
        WaitingDialog wd = new WaitingDialog() {
            @Override
            public Object work() {
                return mainFrame.getAreaDao().saveArea(street);
            }
        };
        if ((int)wd.getReturnResult() >= 1){
            mainFrame.getAreas().add(street);
            JOptionPane.showMessageDialog(mainFrame, "add street successfully.");
            setVisible(false);
        } else {
            JOptionPane.showMessageDialog(this, "add street failed.");
        }
    }


}
