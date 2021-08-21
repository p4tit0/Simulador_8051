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
public class MOV extends Instruction{

    public MOV(int _byte, int[] args){
        super(_byte, args, "MOV", "MOV");
    }

    @Override
    public void exec(){
        System.out.println("exec: MOV");
    }
}