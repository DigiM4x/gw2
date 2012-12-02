package com.gui;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;


public class CoreTable extends DefaultTableModel {
   private Object[][] objects;
   private String[] columns;
   private String[] columnNames;
   private Class c;
   protected Vector<List<Object>> dataVector = new Vector<List<Object>>();

   public CoreTable(CoreTableModel[] items, Class c) {
      columns = items[0].getColumns();
      columnNames = columns;
      //objects = new Object[items.length][columns.length];
      this.c = c;
      
      for (int i = 0; i < items.length; i++) {
         dataVector.add(create(items[i],c));
      }
   }
   private List<Object> create(Object o, Class c) {
      try{
         List<Object> list = new ArrayList<Object>();
         for (int j = 0; j < columns.length; j++) {
            String method = "get" + columns[j];
            Method m = c.getMethod(method, null);
            list.add( m.invoke(o, new Object[] {}));
            
         }
         return list;
      }catch(Exception e) {
         e.printStackTrace();
         throw new RuntimeException("Error creating table for Class " + c.getName());
      }
   }
   
   
   public void addRow(Object o) {
      dataVector.add(create(o,c));
   }
   
   @Override
   public Vector getDataVector() {
      return dataVector;
   }
   
   @Override
   public String getColumnName(int c) {
      return columnNames[c];
   }
   @Override
   public int getColumnCount() {
      return columns.length;
   }

   @Override
   public int getRowCount() {      
      return dataVector != null ? dataVector.size() : 0;
   }

   @Override
   public Object getValueAt(int arg0, int arg1) {
      return dataVector.get(arg0).get(arg1);
   }

   @Override
   public boolean isCellEditable(int r, int c) {
      return false;
   }
 
}
