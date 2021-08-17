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
    //int opCode;
    public int[] args;
    public String mnemonic;
    public String description;
    
    public void exec(){
        System.out.println("exec: Null");
    }
    
}
