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
public class AJMP extends Instruction{

    public AJMP(int _byte, int[] args){
        super(_byte, args, "AJMP", "AJMP");
    }

    @Override
    public void exec(){
        System.out.println("exec: AJMP");
    }
}