/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import InstructionSet.Instruction;
import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;

/**
 *a
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
        errorLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        hexArea.setEditable(false);
        hexArea.setColumns(20);
        hexArea.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
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

        errorLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        errorLabel.setForeground(new java.awt.Color(255, 102, 102));
        errorLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btOpen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btCharge))
                    .addComponent(errorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(errorLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            btCharge.setEnabled(true);
            hexArea.setText("");
            errorLabel.setText("");
            read(jf.getSelectedFile());
        }
    }//GEN-LAST:event_btOpenActionPerformed

    private void btChargeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btChargeActionPerformed
        //memory.load(inst_array);
        if (inst_array != null){
            Memory.reset();
            CodeMemory.reset();
            Ram.reset();
            Cpu.reset();
            
            Object[] mem = Cpu.memory.load(inst_array);
            CodeMemory.loadHexTable((ArrayList<Integer>)mem[0]);
            CodeMemory.loadMnemonicTable((Instruction[])mem[1]);
            this.dispose();
            //CodeMemory.color(0);
        }
    }//GEN-LAST:event_btChargeActionPerformed
    
    void read(File file){
        ArrayList<Object[]> inst = new ArrayList<Object[]>();
        try {
            FileReader fr = new FileReader(file);
            BufferedReader bf = new BufferedReader(fr);
            int lineIdx = 0;
            ArrayList<Integer> linesToPaint = new ArrayList();
            while(bf.ready()){
                String line = bf.readLine();
                Object[] divLine = new Object[3];
                
                int byte_sum = 0;
                for (int i = 0; i < line.substring(1, line.length()-2).length()/2; i++){
                    byte_sum += Integer.parseInt(line.substring(1+2 * i, 3 + 2 * i), 16);
                }
                String str_check_sum = String.format("%x", ~byte_sum + 1);
                str_check_sum = str_check_sum.substring(str_check_sum.length()-2, str_check_sum.length());
                System.out.println(str_check_sum);
                
                if (!str_check_sum.toUpperCase().equals(line.substring(line.length()-2))){
                    btCharge.setEnabled(false);
                    System.out.println("Deu Merda");
                }
                
                int itSize = Integer.parseInt(line.substring(1, 3), 16);
                //divLine[0] = Integer.parseInt(line.substring(1, 3), 16);
                divLine[2] = Integer.parseInt(line.substring(3, 7), 16);
                divLine[0] = Integer.parseInt(line.substring(7, 9), 16);
                int[] instruct = new int[itSize];
                for (int i = 0; i < itSize; i++){
                    instruct[i] = Integer.parseInt(line.substring(9 + 2 * i, 11 + 2 * i), 16);
                }
                
                divLine[1] = instruct;
                //divLine[4] = Integer.parseInt(line.substring(line.length()-2), 16);
                
                hexArea.setText(hexArea.getText() + line + "\n");
                
                if (!str_check_sum.toUpperCase().equals(line.substring(line.length()-2))){
                    btCharge.setEnabled(false);
                    linesToPaint.add(lineIdx);
                    errorLabel.setText("CheckSum error");
                }
                lineIdx++;
                inst.add(divLine);
            }
            
            Color color = new Color(209, 38, 38);
            for(int i : linesToPaint){
                try {
                    //aa
                    int startIndex = hexArea.getLineStartOffset(i);
                    int endIndex = hexArea.getLineEndOffset(i);
                    hexArea.getHighlighter().addHighlight(startIndex, endIndex, new DefaultHighlighter.DefaultHighlightPainter(color));
                } catch (BadLocationException ex) {
                    Logger.getLogger(Charge.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch(IOException e){
            JOptionPane.showMessageDialog(null, "Erro ao ler arquivo");
        }
        inst_array = new Object[Memory.rom.length][];
        for (int i = 0; i < inst.size(); i++) {
            Object[] row = inst.get(i);
            inst_array[i + (int)row[2]] = new Object[]{row[0], row[1]};
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
                new Charge().setVisible(true);
            }
        });

        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCharge;
    private javax.swing.JButton btOpen;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JTextArea hexArea;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
