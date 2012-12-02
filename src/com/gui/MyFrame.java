package com.gui;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

public class MyFrame extends JFrame{
   
   private JTabbedPane tabs;
   public MyFrame() {
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(400,450);
      tabs = new JTabbedPane();
      add(tabs);
      setVisible(true);
   }
   
   public void addTable(CoreTable t, String name) {
      JTable tb = new JTable(t);
      tabs.add(new JScrollPane(tb),name);
   }
   
   

}
