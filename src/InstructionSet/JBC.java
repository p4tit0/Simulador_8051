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
public class JBC extends Instruction{

    public JBC(int _byte, int[] args){
        super(_byte, args, "JBC", "JBC");
    }

    @Override
    public void exec(){
        System.out.println("exec: JBC");
    }
}