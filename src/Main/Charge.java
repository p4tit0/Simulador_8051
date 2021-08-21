/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import InstructionSet.Instruction;
import com.formdev.flatlaf.FlatDarkLaf;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author famil
 */
public class Charge extends javax.swing.JFrame {

    /**
     * Creates new form Charge
     */
    Object[][] inst_array = null;
    
    
    public Charge() {
        initComponents();
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
        hexArea = new javax.swing.JTextArea();
        btOpen = new javax.swing.JButton();
        btCharge = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        hexArea.setEditable(false);
        hexArea.setColumns(20);
        hexArea.setRows(5);
        jScrollPane1.setViewportView(hexArea);

        btOpen.setText("Open");
        btOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btOpenActionPerformed(evt);
            }
        });

        btCharge.setText("Load");
        btCharge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btChargeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btOpen)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btCharge)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btOpen)
                            .addComponent(btCharge)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btOpenActionPerformed
        JFileChooser jf = new JFileChooser();
        jf.setCurrentDirectory(new File("src\\res")); // tira DESGRAÇA
        jf.addChoosableFileFilter(new FileNameExtensionFilter("HEX Documents", "hex"));
        jf.setAcceptAllFileFilterUsed(false);
        int r = jf.showOpenDialog(null);
        if(r == JFileChooser.APPROVE_OPTION){
            //inst.clear();
            hexArea.setText("");
            read(jf.getSelectedFile());
        }
    }//GEN-LAST:event_btOpenActionPerformed

    private void btChargeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btChargeActionPerformed
        //memory.load(inst_array);
        if (inst_array != null){
            Object[] mem = CodeMemory.cpu.load(inst_array);
            CodeMemory.loadHexTable((ArrayList<Integer>)mem[0]);
            CodeMemory.loadMnemonicTable((Instruction[])mem[1], ((ArrayList<Integer>)mem[2]).toArray(new Integer[0]));
            this.dispose();
        }
    }//GEN-LAST:event_btChargeActionPerformed
    
    //-=-=-=-=-=-=-=-=-=- <APAGA> -=-=-=-=-=-=-=-=-=-=-=-
    public static void print2D(Object[][] mat)
    {
        for (Object[] row : mat) {
            System.out.println(String.format("[%s, %s]", row[0], Arrays.toString((int[]) row[1])));
        }
    }
    //-=-=-=-=-=-=-=-=-=- </APAGA> -=-=-=-=-=-=-=-=-=-=-=-
    
    void read(File file){
        ArrayList<Object[]> inst = new ArrayList<Object[]>();
        try {
            FileReader fr = new FileReader(file);
            BufferedReader bf = new BufferedReader(fr);
            while(bf.ready()){
                String line = bf.readLine();
                Object[] divLine = new Object[2];
                
                int itSize = Integer.parseInt(line.substring(1, 3), 16);
                //divLine[0] = Integer.parseInt(line.substring(1, 3), 16);
                //divLine[1] = Integer.parseInt(line.substring(3, 7), 16);
                divLine[0] = Integer.parseInt(line.substring(7, 9), 16);
                int[] instruct = new int[itSize];
                for (int i = 0; i < itSize; i++){
                    instruct[i] = Integer.parseInt(line.substring(9 + 2 * i, 11 + 2 * i), 16);
                }
                divLine[1] = instruct;
                //divLine[4] = Integer.parseInt(line.substring(line.length()-2), 16);
                
                //System.out.println(String.format("[%s, %s, %s, %s, %s]", divLine[0], divLine[1], divLine[2], Arrays.toString((int[]) divLine[3]), divLine[4]));
                hexArea.setText(hexArea.getText() + line + "\n");
                inst.add(divLine);
            }
        } catch(IOException e){
            JOptionPane.showMessageDialog(null, "Erro ao ler arquivo");
        }
        inst_array = new Object[inst.size()][];
        for (int i = 0; i < inst.size(); i++) {
            Object[] row = inst.get(i);
            inst_array[i] = row;
        }
        //System.out.println("-=-=-=-= <INST> =-=-=-=-");
        //print2D(inst_array);
        //System.out.println("-=-=-=-= </INST> =-=-=-=-");
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
                new Charge().setVisible(true);
            }
        });

        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCharge;
    private javax.swing.JButton btOpen;
    private javax.swing.JTextArea hexArea;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
