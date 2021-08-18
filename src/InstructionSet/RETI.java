/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InstructionSet;

/**
 *
 * @author space
 */
public class RETI extends Instruction{
    
    public RETI(int _byte, int[] args){
        super(_byte, args, "RETI", "RETI");
    }
    
    @Override
    public void exec(){
        System.out.println("exec: RETI");
    }
}
