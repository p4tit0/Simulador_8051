/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InstructionSet;

/**
 * Classe base para todas as instruções do 8051
 * @author Gerson Menezes & Vinícius Santos
 * @version 1.0
 */
public class Instruction {
    
    /**
     * opCode da instrução
     */
    public int opCode;

    /**
     * Operandos da instrução
     */
    public int[] args;

    /**
     * Minemônico da instrução
     */
    public String mnemonic;

    /**
     * Descrição da instrução
     */
    public String description;

    /**
     * Nomeclatura dos operandos
     */
    public String[] operands;
    
    /**
     * Metodo de execução padrão para todas as instruções
     * @throws Exception Qualquer possóvel erro de execução
     */
    public void exec()throws Exception{
        System.out.println("exec: Null");
    }
    
    /**
     * Método contrutor base para todas as intruções. Responsável por fornecer todas as informações básicas sobre a instrução
     * @param _byte opCode da instrução
     * @param args Operandos da instrução
     * @param mnemonic Minemônico da instrução
     * @param description Descrição da instrução
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
