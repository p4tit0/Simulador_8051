/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import InstructionSet.Instruction;
import com.formdev.flatlaf.FlatDarkLaf;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author famil
 */
public class CodeMemory extends javax.swing.JFrame {

    /**
     * Creates new form CodeMemory
     */
    
    public static Cpu cpu = new Cpu();
    public static Memory memory = cpu.memory;
    
    public CodeMemory() {
        initComponents();
        DefaultTableCellRenderer rightRender = new DefaultTableCellRenderer();
        rightRender.setHorizontalAlignment(JLabel.CENTER);
        
        for(int i = 0; i < hexTable.getColumnCount(); i++)
            hexTable.getColumnModel().getColumn(i).setCellRenderer(rightRender);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        hexTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        mneTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        execBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(736, 441));

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
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
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
        jScrollPane1.setViewportView(hexTable);
        hexTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (hexTable.getColumnModel().getColumnCount() > 0) {
            hexTable.getColumnModel().getColumn(0).setResizable(false);
            hexTable.getColumnModel().getColumn(1).setResizable(false);
            hexTable.getColumnModel().getColumn(2).setResizable(false);
            hexTable.getColumnModel().getColumn(3).setResizable(false);
            hexTable.getColumnModel().getColumn(4).setResizable(false);
            hexTable.getColumnModel().getColumn(5).setResizable(false);
            hexTable.getColumnModel().getColumn(6).setResizable(false);
            hexTable.getColumnModel().getColumn(7).setResizable(false);
            hexTable.getColumnModel().getColumn(8).setResizable(false);
            hexTable.getColumnModel().getColumn(9).setResizable(false);
            hexTable.getColumnModel().getColumn(10).setResizable(false);
            hexTable.getColumnModel().getColumn(11).setResizable(false);
            hexTable.getColumnModel().getColumn(12).setResizable(false);
            hexTable.getColumnModel().getColumn(13).setResizable(false);
            hexTable.getColumnModel().getColumn(14).setResizable(false);
            hexTable.getColumnModel().getColumn(15).setResizable(false);
        }

        jLabel1.setText("Instructions");

        jLabel2.setText("HEX");

        mneTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Adress", "Mnemonic", "Opcode"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        mneTable.setRowHeight(26);
        mneTable.setShowGrid(false);
        mneTable.getTableHeader().setReorderingAllowed(false);
        mneTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mneTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(mneTable);
        if (mneTable.getColumnModel().getColumnCount() > 0) {
            mneTable.getColumnModel().getColumn(0).setResizable(false);
            mneTable.getColumnModel().getColumn(0).setPreferredWidth(60);
            mneTable.getColumnModel().getColumn(1).setResizable(false);
            mneTable.getColumnModel().getColumn(2).setResizable(false);
            mneTable.getColumnModel().getColumn(2).setPreferredWidth(40);
        }

        jButton1.setText("Load HEX code");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        execBtn.setText("Execute");
        execBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                execBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(204, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(execBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(14, 14, 14))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(execBtn)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Charge cg = new Charge();
        cg.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void execBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_execBtnActionPerformed
        if (cpu.memory != null){
            cpu.exec();
        }
    }//GEN-LAST:event_execBtnActionPerformed

    private void mneTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mneTableMouseClicked
        int line = mneTable.rowAtPoint(evt.getPoint());
        DefaultTableModel model = (DefaultTableModel) mneTable.getModel();
        String stAdress = (String) model.getValueAt(line, 0);
        //System.out.println(stAdress);
        if(stAdress != null){
            int adress = Integer.parseInt(stAdress, 16);
            hexTable.changeSelection(adress/16, adress%16, false, false);
        }
    }//GEN-LAST:event_mneTableMouseClicked

    static void loadHexTable(ArrayList<Integer> data){        
        DefaultTableModel model = (DefaultTableModel) hexTable.getModel();
        int column = 0;
        Object[] row = new Object[model.getColumnCount()];
        for(Integer i : data){
            row[column] = String.format("%02x", i).toUpperCase();
            column++;
            if (column > model.getColumnCount() - 1 || model.getColumnCount() * model.getRowCount() + column == data.size()){
                column = 0;
                model.addRow(row);
                row = new Object[model.getColumnCount()];
            }
        }
    }
    
    static void loadMnemonicTable(Instruction[] inst){
        DefaultTableModel model = (DefaultTableModel) mneTable.getModel();
        for(int i = 0; i < inst.length; i++){
            // falta setar o endereço, corno
            Object[] row = new Object[model.getColumnCount()];
            row[0] = String.format("%04x", i).toUpperCase();
            if(inst[i] == null) {
                //model.addRow(row);
                continue;
            }
            
            String operands = " ";
            if(inst[i].args.length > 0){
                for(int j: inst[i].args){
                    operands += Integer.toHexString(j).toUpperCase() + ", ";
                }
                operands = operands.substring(0, operands.length() - 2);
            }
            row[1] = inst[i].mnemonic + operands;
            row[2] = Integer.toHexString(inst[i].opCode).toUpperCase();
            model.addRow(row);
        }
    }
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
                new CodeMemory().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton execBtn;
    private static javax.swing.JTable hexTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private static javax.swing.JTable mneTable;
    // End of variables declaration//GEN-END:variables
}
