/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InstructionSet;

/**
 *
 * @author famil
 */
public class Instruction {
    
    public int opCode;
    public int[] args;
    public String mnemonic;
    public String description;
    public String[] operands;
    
    public void exec(){
        System.out.println("exec: Null");
    }
    
    public Instruction(int _byte, int[] args, String mnemonic, String description, String[] operands){
        this.opCode = _byte;
        this.args = args;
        this.mnemonic = mnemonic;
        this.description = description;
        this.operands = operands;
    }
    
}
