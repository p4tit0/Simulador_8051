/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.BorderLayout;
import java.awt.Component;
import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.AbstractListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author famil
 */
public class Ram extends javax.swing.JFrame {

    /**
     * Creates new form Ram
     */
    //
    //20h - 2Fh
    //30h - 7Fh
    
    static DefaultTableModel hexDM;
    static DefaultTableModel bankDM;
    static DefaultTableModel addrDM;
    
    public Ram() {
        initComponents();
        hexDM = (DefaultTableModel) hexTable.getModel(); 
        bankDM = (DefaultTableModel) bankTable.getModel(); 
        addrDM = (DefaultTableModel) addrTable.getModel(); 
        
        ListModel lm = new AbstractListModel(){
                
            public String[] headers = fillHeaders();
            
            String[] fillHeaders(){
                ArrayList<String> a = new ArrayList();
                for(int i = 3; i < 8; i++){
                    a.add(Integer.toHexString(i*16));
                }
                return a.toArray(new String[0]);
            }
            
            @Override
            public int getSize() {
                return headers.length;
            }

            @Override
            public Object getElementAt(int index) {
                return headers[index];
            }
            
            public void setHeaders(String[] headers){
                this.headers = headers;
            }

        };
        
        //Cria row header
        JList rowHeader = new JList(lm);
        rowHeader.setFixedCellWidth(26);
        rowHeader.setFixedCellHeight(hexTable.getRowHeight());

        //Set Renderer
        rowHeader.setCellRenderer(new RowRenderer(hexTable));

        //ScrollPane
        hexScrollPane.setRowHeaderView(rowHeader);
        getContentPane().add(hexScrollPane, BorderLayout.CENTER);
        
        for(int i = 3; i < 8; i++){
            hexDM.addRow(new Object[]{"00","00","00","00","00","00","00","00", "00","00","00","00","00","00","00","00"});
        }
        

        //hexTable.setMaximumSize(new Dimension(hexTable.getPreferredSize().width,
        //                            dm.getRowCount() * hexTable.getRowHeight()));
        
        lm = new AbstractListModel(){
                
            public String[] headers = {"00", "01", "02", "03"};
            
            @Override
            public int getSize() {
                return headers.length;
            }

            @Override
            public Object getElementAt(int index) {
                return headers[index];
            }
        };
        
        rowHeader = new JList(lm);
        rowHeader.setFixedCellWidth(26);
        rowHeader.setFixedCellHeight(bankTable.getRowHeight());
        rowHeader.setCellRenderer(new RowRenderer(bankTable));
        bankScrollPane.setRowHeaderView(rowHeader);
        getContentPane().add(bankScrollPane, BorderLayout.CENTER);
        for(int i = 0; i < 4; i++){
            bankDM.addRow(new Object[]{"00","00","00","00","00","00","00","00"});
        }
        
        lm = new AbstractListModel(){
                
            public String[] headers = {"20", "21", "22", "23", "24", "25", "26",
                "27", "28", "29", "2A", "2B", "2C", "2D", "2E", "2F"};
            
            @Override
            public int getSize() {
                return headers.length;
            }

            @Override
            public Object getElementAt(int index) {
                return headers[index];
            }
        };
        
        rowHeader = new JList(lm);
        rowHeader.setFixedCellWidth(26);
        rowHeader.setFixedCellHeight(addrTable.getRowHeight());
        rowHeader.setCellRenderer(new RowRenderer(addrTable));
        addrScrollPane.setRowHeaderView(rowHeader);
        getContentPane().add(addrScrollPane, BorderLayout.CENTER);        
        for(int i = 0; i < 16; i++){
            addrDM.addRow(new Object[]{"0","0","0","0","0","0","0","0"});
        }
        DefaultTableCellRenderer rightRender = new DefaultTableCellRenderer();
        rightRender.setHorizontalAlignment(JLabel.CENTER);
        for(int i = 0; i < addrTable.getColumnCount(); i++)
            addrTable.getColumnModel().getColumn(i).setCellRenderer(rightRender);
        
        for(int i = 0; i < Memory.ram.length; i++){
            setByte(i, Memory.ram[i]);
        }
        
    }
    
    
    class RowRenderer extends JLabel implements ListCellRenderer{
        
        public RowRenderer(JTable table){
            JTableHeader header = table.getTableHeader();
            setOpaque(true);
            setBorder(UIManager.getBorder("TableHeader.cellBorder"));
            setHorizontalAlignment(CENTER);
            setForeground(header.getForeground());
            setBackground(header.getBackground());
            setFont(header.getFont());
            
        }
        
        @Override
        public Component getListCellRendererComponent(JList list, Object obj, 
                int index, boolean selected, boolean focused) {
            setText((obj==null) ? "" : obj.toString());
            return this;
        }
        
    }
    
    public static void setByte(int address, int value){
        
        if(address > 0x7f){
            //memória alta
            
        }
        else{
            //memória baixa
            if(address < 0x20){
                //banco de registradores
                bankDM.setValueAt(Integer.toHexString(value).toUpperCase(), address/8, address%8);
            }
            else if(address < 0x30){
                //registadores de bit endereçável
                int localAddr = address - 0x20;
                String num = String.format("%8s", Integer.toBinaryString(value)).replace(" ", "0");
                for(int i = 0; i < addrDM.getColumnCount(); i++){
                    addrDM.setValueAt(num.charAt(i), localAddr, i);
                }
            }
            else{
                //registradores de uso geral
                int localAddr = address - 0x30;
                hexDM.setValueAt(Integer.toHexString(value).toUpperCase(), localAddr/hexDM.getColumnCount() , localAddr%hexDM.getColumnCount());
            }
        }
    }
    
    public static void setBit(int address, int bit, int value){
        
        if(address < 0x7F)
            addrDM.setValueAt(value, address-0x20, 7-bit);
        
        System.out.println("Adress: "+address + " bit: " + bit);
        
    }
    
    public static void reset(){
        //hexTable
        for(int row = 0; row < hexDM.getRowCount(); row++){
            for(int collumn = 0; collumn < hexDM.getColumnCount(); collumn++){
                hexDM.setValueAt("0", row, collumn); 
            }
        }
        //bankTable
        for(int row = 0; row < bankDM.getRowCount(); row++){
            for(int collumn = 0; collumn < bankDM.getColumnCount(); collumn++){
                bankDM.setValueAt("0", row, collumn); 
            }
        }
        //addrTable
        for(int row = 0; row < addrDM.getRowCount(); row++){
            for(int collumn = 0; collumn < addrDM.getColumnCount(); collumn++){
                addrDM.setValueAt("0", row, collumn); 
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        hexScrollPane = new javax.swing.JScrollPane();
        hexTable = new javax.swing.JTable();
        bankScrollPane = new javax.swing.JScrollPane();
        bankTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        addrScrollPane = new javax.swing.JScrollPane();
        addrTable = new javax.swing.JTable();

        setResizable(false);

        hexTable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        hexTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "0A", "0B", "0C", "0D", "0E", "0F"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        hexTable.setColumnSelectionAllowed(true);
        hexTable.setRowHeight(26);
        hexTable.setShowGrid(false);
        hexTable.getTableHeader().setReorderingAllowed(false);
        hexScrollPane.setViewportView(hexTable);
        hexTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (hexTable.getColumnModel().getColumnCount() > 0) {
            hexTable.getColumnModel().getColumn(0).setResizable(false);
            hexTable.getColumnModel().getColumn(1).setResizable(false);
            hexTable.getColumnModel().getColumn(2).setResizable(false);
            hexTable.getColumnModel().getColumn(3).setResizable(false);
            hexTable.getColumnModel().getColumn(4).setResizable(false);
            hexTable.getColumnModel().getColumn(5).setResizable(false);
            hexTable.getColumnModel().getColumn(6).setResizable(false);
            hexTable.getColumnModel().getColumn(7).setResizable(false);
        }

        bankTable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        bankTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "R0", "R1", "R2", "R3", "R4", "R5", "R6", "R7"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        bankTable.setRowHeight(26);
        bankTable.setShowGrid(false);
        bankTable.getTableHeader().setReorderingAllowed(false);
        bankScrollPane.setViewportView(bankTable);
        bankTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (bankTable.getColumnModel().getColumnCount() > 0) {
            bankTable.getColumnModel().getColumn(0).setResizable(false);
            bankTable.getColumnModel().getColumn(1).setResizable(false);
            bankTable.getColumnModel().getColumn(2).setResizable(false);
            bankTable.getColumnModel().getColumn(3).setResizable(false);
            bankTable.getColumnModel().getColumn(4).setResizable(false);
            bankTable.getColumnModel().getColumn(5).setResizable(false);
            bankTable.getColumnModel().getColumn(6).setResizable(false);
            bankTable.getColumnModel().getColumn(7).setResizable(false);
        }

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Register banks");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("General Porpouse Registers");

        addrTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "7", "6", "5", "4", "3", "2", "1", "0"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        addrScrollPane.setViewportView(addrTable);
        if (addrTable.getColumnModel().getColumnCount() > 0) {
            addrTable.getColumnModel().getColumn(0).setResizable(false);
            addrTable.getColumnModel().getColumn(1).setResizable(false);
            addrTable.getColumnModel().getColumn(2).setResizable(false);
            addrTable.getColumnModel().getColumn(3).setResizable(false);
            addrTable.getColumnModel().getColumn(4).setResizable(false);
            addrTable.getColumnModel().getColumn(5).setResizable(false);
            addrTable.getColumnModel().getColumn(6).setResizable(false);
            addrTable.getColumnModel().getColumn(7).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addrScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 677, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(hexScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(bankScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bankScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))
                    .addComponent(hexScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addrScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            javax.swing.UIManager.setLookAndFeel( new FlatDarkLaf() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
            ex.printStackTrace();
            
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ram().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane addrScrollPane;
    private javax.swing.JTable addrTable;
    private javax.swing.JScrollPane bankScrollPane;
    private static javax.swing.JTable bankTable;
    private javax.swing.JScrollPane hexScrollPane;
    private static javax.swing.JTable hexTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}


