package ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame(){
        initUI();
    }

    private void initUI(){
        Container c = this.getContentPane();
        c.setLayout(new BorderLayout());
        GroupPanel gp = new GroupPanel();
        JTabbedPane tab = new JTabbedPane();
        tab.add("Group", gp);

    }

    public static void main(String[] args) {
        MainFrame f = new MainFrame();
        f.setSize(800,600);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
