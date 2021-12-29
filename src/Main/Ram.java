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
 * Representação visual da memória RAM
 * @author  Gerson Menezes & Vinícius Santos
 * @version 1.0
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
    static DefaultTableModel speAddrDM;
    static DefaultTableModel speDM;
    
    /**
     * Método construtor da classe. Responsável por definir todas as propriedades da janela
     */
    public Ram() {
        initComponents();
        hexDM = (DefaultTableModel) hexTable.getModel(); 
        bankDM = (DefaultTableModel) bankTable.getModel(); 
        addrDM = (DefaultTableModel) addrTable.getModel(); 
        speAddrDM = (DefaultTableModel) speAddrTable.getModel(); 
        speDM = (DefaultTableModel) speTable.getModel(); 
        
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
        //getContentPane().add(hexScrollPane, BorderLayout.CENTER);
        
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
       
        for(int i = 0; i < 16; i++){
            addrDM.addRow(new Object[]{"0","0","0","0","0","0","0","0"});
        }
        
        DefaultTableCellRenderer centerRender = new DefaultTableCellRenderer();
        centerRender.setHorizontalAlignment(JLabel.CENTER);
        for(int i = 0; i < addrTable.getColumnCount(); i++)
            addrTable.getColumnModel().getColumn(i).setCellRenderer(centerRender);
        
        /////////////////////////Registradores especiais de bit endereçável////////////////////////////
        lm = new AbstractListModel(){
                
            public String[] headers = {"ACC", "B", "PSW", "P0", "P1", "P2", "P3",
            "IP", "IE", "TCON", "SCON"};
            
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
        rowHeader.setFixedCellWidth(50);
        rowHeader.setFixedCellHeight(speAddrTable.getRowHeight());
        rowHeader.setCellRenderer(new RowRenderer(speAddrTable));
        speAddrScrollPane.setRowHeaderView(rowHeader);       
        for(int i = 0; i < 11; i++){
            speAddrDM.addRow(new Object[]{"0","0","0","0","0","0","0","0"});
        }
        //////////////////////////////////Registradores especiais//////////////////////////////////////////
        lm = new AbstractListModel(){
                
            public String[] headers = {"SP", "DPL", "DPH", "TMOD", "TH0", "TL0", "TH1",
            "TL1", "SBUF", "PCON"};
            
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
        rowHeader.setFixedCellWidth(50);
        rowHeader.setFixedCellHeight(speTable.getRowHeight());
        rowHeader.setCellRenderer(new RowRenderer(speTable));
        speScrollPane.setRowHeaderView(rowHeader);     
        for(int i = 0; i < 10; i++){
            speDM.addRow(new Object[]{"00"});
        }
        
        speTable.getTableHeader().setUI(null);
        
        for(int i = 0; i < speTable.getColumnCount(); i++)
            speTable.getColumnModel().getColumn(i).setCellRenderer(centerRender);
        
        ////////////////////////////////////////////////////////////////////////////////////////////////
        for(int i = 0; i < Memory.ram.length; i++){
            setByte(i, Memory.ram[i]);
        }
        
    }
    
    /**
     * Renderizador de linha customizado para as JTables. Responsável por remover o cabeçalho das mesmas
     */
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
    
    /**
     * Altera o valor de um byte na representação da memória
     * @param address Endereço do da memória a ser alterado
     * @param value Novo valor do Byte
     */
    public static void setByte(int address, int value){
        
        if(address > 0x7f){
            //memória alta
            if (Memory.isBitAddressable(address)){
                int row = getSpeRow(address);
                String num = String.format("%8s", Integer.toBinaryString(value)).replace(" ", "0");
                for(int i = 0; i < speAddrDM.getColumnCount(); i++){
                    speAddrDM.setValueAt(num.charAt(i), row, i);
                }
            }
            else{
                int row = 0;
                
                switch(address){
                    case 0x81://SP
                        row = 0;
                        break;
                    case 0x82://DPL
                        row = 1;
                        break;
                    case 0x83://DPH
                        row = 2;
                        break;
                    case 0x89://TMOD
                        row = 3;
                        break;
                    case 0x8C://TH0
                        row = 4;
                        break;
                    case 0x8A://TL0
                        row = 5;
                        break;
                    case 0x8D://TH1
                        row = 6;
                        break;
                    case 0x8B://TL1
                        row = 7;
                        break;
                    case 0x99://SBUF
                        row = 8;
                        break;
                    case 0x87://PCON
                        row = 9;
                        break;
                    default:
                        row = -1;
                }
                if(row != -1)
                    speTable.setValueAt(Integer.toHexString(value).toUpperCase(), row, 0);
            }
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
    
    /**
     * Altera o valor de um bit específico na representação da memória
     * @param address Endereço do da memória a ser alterado
     * @param bit Posição do bit que será alterado
     * @param value Novo valor do bit (1 ou 0)
     */
    public static void setBit(int address, int bit, int value){
        
        if(address > 0x7F){
           // memória alta
           int row = getSpeRow(address);
           if (row != -1){
               speAddrDM.setValueAt(value, row, 7-bit);
           }
           else{
               System.out.println("row out of range");
           }
        }
        else{
            //memória baixa
            addrDM.setValueAt(value, address-0x20, 7-bit);
        }
        System.out.println("Adress: "+address + " bit: " + bit);
        
    }
    
    /**
     * Obtém o índice da linha em que um registrador especial se encontra com base em seu endereço
     * @param address Endereço na memória do registrador especial
     * @return Índice da linha do registrador
     */
    public static int getSpeRow(int address){
        int row = 0;
        switch(address){
                case 0xE0://ACC
                   row = 0;
                   break;
                case 0xF0://B
                   row = 1;
                   break;
                case 0xD0://PSW
                   row = 2;
                   break;
                case 0x80://P0
                   row = 3;
                   break;
                case 0x90://P1
                   row = 4;
                   break;
                case 0xA0://P2
                   row = 5;
                   break;
                case 0xB0://P3
                   row = 6;
                   break;
                case 0xB8://IP
                   row = 7;
                   break;
                case 0xA8://IE
                   row = 8;
                   break;
                case 0x88://TCON
                   row = 9;
                   break;
                case 0x98://SCON
                   row = 10;
                   break;
                default:
                   return -1;
           }
        return row;
    }
    
    /**
     * Limpa toda a representação da memória, mudando todos os valores para 0
     */
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
        speAddrScrollPane = new javax.swing.JScrollPane();
        speAddrTable = new javax.swing.JTable();
        speScrollPane = new javax.swing.JScrollPane();
        speTable = new javax.swing.JTable();

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
            addrTable.getColumnModel().getColumn(1).setHeaderValue("6");
            addrTable.getColumnModel().getColumn(2).setResizable(false);
            addrTable.getColumnModel().getColumn(2).setHeaderValue("5");
            addrTable.getColumnModel().getColumn(3).setResizable(false);
            addrTable.getColumnModel().getColumn(3).setHeaderValue("4");
            addrTable.getColumnModel().getColumn(4).setResizable(false);
            addrTable.getColumnModel().getColumn(4).setHeaderValue("3");
            addrTable.getColumnModel().getColumn(5).setResizable(false);
            addrTable.getColumnModel().getColumn(5).setHeaderValue("2");
            addrTable.getColumnModel().getColumn(6).setResizable(false);
            addrTable.getColumnModel().getColumn(6).setHeaderValue("1");
            addrTable.getColumnModel().getColumn(7).setResizable(false);
            addrTable.getColumnModel().getColumn(7).setHeaderValue("0");
        }

        speAddrTable.setModel(new javax.swing.table.DefaultTableModel(
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
        speAddrScrollPane.setViewportView(speAddrTable);
        if (speAddrTable.getColumnModel().getColumnCount() > 0) {
            speAddrTable.getColumnModel().getColumn(0).setResizable(false);
            speAddrTable.getColumnModel().getColumn(1).setResizable(false);
            speAddrTable.getColumnModel().getColumn(1).setHeaderValue("6");
            speAddrTable.getColumnModel().getColumn(2).setResizable(false);
            speAddrTable.getColumnModel().getColumn(2).setHeaderValue("5");
            speAddrTable.getColumnModel().getColumn(3).setResizable(false);
            speAddrTable.getColumnModel().getColumn(3).setHeaderValue("4");
            speAddrTable.getColumnModel().getColumn(4).setResizable(false);
            speAddrTable.getColumnModel().getColumn(4).setHeaderValue("3");
            speAddrTable.getColumnModel().getColumn(5).setResizable(false);
            speAddrTable.getColumnModel().getColumn(5).setHeaderValue("2");
            speAddrTable.getColumnModel().getColumn(6).setResizable(false);
            speAddrTable.getColumnModel().getColumn(6).setHeaderValue("1");
            speAddrTable.getColumnModel().getColumn(7).setResizable(false);
            speAddrTable.getColumnModel().getColumn(7).setHeaderValue("0");
        }

        speTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        speScrollPane.setViewportView(speTable);
        if (speTable.getColumnModel().getColumnCount() > 0) {
            speTable.getColumnModel().getColumn(0).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addrScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(speAddrScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(speScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(hexScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bankScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addrScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(speAddrScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(speScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 45, Short.MAX_VALUE)))
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
    private javax.swing.JScrollPane speAddrScrollPane;
    private javax.swing.JTable speAddrTable;
    private javax.swing.JScrollPane speScrollPane;
    private static javax.swing.JTable speTable;
    // End of variables declaration//GEN-END:variables
}


