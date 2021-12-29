/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InstructionSet;

/**
 * Classe base para todas as intruções do 8051
 * @author  Gerson Menezes & Vinícius Santos
 * @version 1.0
 */
public class Instruction {
    
    public int opCode;
    public int[] args;
    public String mnemonic;
    public String description;
    public String[] operands;
    
    /**
     * Metodo de execução padrão para todas as intruções
     * @throws Exception
     */
    public void exec()throws Exception{
        System.out.println("exec: Null");
    }
    
    /**
     * Método contrutor base para todas as intruções. Responsável por fornecer todas as informações básicas sobre a intrução
     * @param _byte opCode da intrução
     * @param args Endereço dos operandos
     * @param mnemonic Minemônico da intrução
     * @param description Descrição da intrução
     * @param operands Nomeclatura dos operandos
     */
    public Instruction(int _byte, int[] args, String mnemonic, String description, String[] operands){
        this.opCode = _byte;
        this.args = args;
        this.mnemonic = mnemonic;
        this.description = description;
        this.operands = operands;
    }
    
}
