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
public class InstructionSet_8051 {
    public static Object[][] instruction_set = new Object[][] {
	{1, "NOP", new String[0]}, 
	{2, "AJMP", new String[] {"addr11"}}, 
	{3, "LJMP", new String[] {"addr16"}}, 
	{1, "RR", new String[] {"A"}}, 
	{1, "INC", new String[] {"A"}}, 
	{2, "INC", new String[] {"direct"}}, 
	{1, "INC", new String[] {"@R0"}}, 
	{1, "INC", new String[] {"@R1"}}, 
	{1, "INC", new String[] {"R0"}}, 
	{1, "INC", new String[] {"R1"}}, 
	{1, "INC", new String[] {"R2"}}, 
	{1, "INC", new String[] {"R3"}}, 
	{1, "INC", new String[] {"R4"}}, 
	{1, "INC", new String[] {"R5"}}, 
	{1, "INC", new String[] {"R6"}}, 
	{1, "INC", new String[] {"R7"}}, 
	{3, "JBC", new String[] {"bit", "offset"}}, 
	{2, "ACALL", new String[] {"addr11"}}, 
	{3, "LCALL", new String[] {"addr16"}}, 
	{1, "RRC", new String[] {"A"}}, 
	{1, "DEC", new String[] {"A"}}, 
	{2, "DEC", new String[] {"direct"}}, 
	{1, "DEC", new String[] {"@R0"}}, 
	{1, "DEC", new String[] {"@R1"}}, 
	{1, "DEC", new String[] {"R0"}}, 
	{1, "DEC", new String[] {"R1"}}, 
	{1, "DEC", new String[] {"R2"}}, 
	{1, "DEC", new String[] {"R3"}}, 
	{1, "DEC", new String[] {"R4"}}, 
	{1, "DEC", new String[] {"R5"}}, 
	{1, "DEC", new String[] {"R6"}}, 
	{1, "DEC", new String[] {"R7"}}, 
	{3, "JB", new String[] {"bit", "offset"}}, 
	{2, "AJMP", new String[] {"addr11"}}, 
	{1, "RET", new String[0]}, 
	{1, "RL", new String[] {"A"}}, 
	{2, "ADD", new String[] {"A", "#immed"}}, 
	{2, "ADD", new String[] {"A", "direct"}}, 
	{1, "ADD", new String[] {"A", "@R0"}}, 
	{1, "ADD", new String[] {"A", "@R1"}}, 
	{1, "ADD", new String[] {"A", "R0"}}, 
	{1, "ADD", new String[] {"A", "R1"}}, 
	{1, "ADD", new String[] {"A", "R2"}}, 
	{1, "ADD", new String[] {"A", "R3"}}, 
	{1, "ADD", new String[] {"A", "R4"}}, 
	{1, "ADD", new String[] {"A", "R5"}}, 
	{1, "ADD", new String[] {"A", "R6"}}, 
	{1, "ADD", new String[] {"A", "R7"}}, 
	{3, "JNB", new String[] {"bit", "offset"}}, 
	{2, "ACALL", new String[] {"addr11"}}, 
	{1, "RETI", new String[0]}, 
	{1, "RLC", new String[] {"A"}}, 
	{2, "ADDC", new String[] {"A", "#immed"}}, 
	{2, "ADDC", new String[] {"A", "direct"}}, 
	{1, "ADDC", new String[] {"A", "@R0"}}, 
	{1, "ADDC", new String[] {"A", "@R1"}}, 
	{1, "ADDC", new String[] {"A", "R0"}}, 
	{1, "ADDC", new String[] {"A", "R1"}}, 
	{1, "ADDC", new String[] {"A", "R2"}}, 
	{1, "ADDC", new String[] {"A", "R3"}}, 
	{1, "ADDC", new String[] {"A", "R4"}}, 
	{1, "ADDC", new String[] {"A", "R5"}}, 
	{1, "ADDC", new String[] {"A", "R6"}}, 
	{1, "ADDC", new String[] {"A", "R7"}}, 
	{2, "JC", new String[] {"offset"}}, 
	{2, "AJMP", new String[] {"addr11"}}, 
	{2, "ORL", new String[] {"direct", "A"}}, 
	{3, "ORL", new String[] {"direct", "#immed"}}, 
	{2, "ORL", new String[] {"A", "#immed"}}, 
	{2, "ORL", new String[] {"A", "direct"}}, 
	{1, "ORL", new String[] {"A", "@R0"}}, 
	{1, "ORL", new String[] {"A", "@R1"}}, 
	{1, "ORL", new String[] {"A", "R0"}}, 
	{1, "ORL", new String[] {"A", "R1"}}, 
	{1, "ORL", new String[] {"A", "R2"}}, 
	{1, "ORL", new String[] {"A", "R3"}}, 
	{1, "ORL", new String[] {"A", "R4"}}, 
	{1, "ORL", new String[] {"A", "R5"}}, 
	{1, "ORL", new String[] {"A", "R6"}}, 
	{1, "ORL", new String[] {"A", "R7"}}, 
	{2, "JNC", new String[] {"offset"}}, 
	{2, "ACALL", new String[] {"addr11"}}, 
	{2, "ANL", new String[] {"direct", "A"}}, 
	{3, "ANL", new String[] {"direct", "#immed"}}, 
	{2, "ANL", new String[] {"A", "#immed"}}, 
	{2, "ANL", new String[] {"A", "direct"}}, 
	{1, "ANL", new String[] {"A", "@R0"}}, 
	{1, "ANL", new String[] {"A", "@R1"}}, 
	{1, "ANL", new String[] {"A", "R0"}}, 
	{1, "ANL", new String[] {"A", "R1"}}, 
	{1, "ANL", new String[] {"A", "R2"}}, 
	{1, "ANL", new String[] {"A", "R3"}}, 
	{1, "ANL", new String[] {"A", "R4"}}, 
	{1, "ANL", new String[] {"A", "R5"}}, 
	{1, "ANL", new String[] {"A", "R6"}}, 
	{1, "ANL", new String[] {"A", "R7"}}, 
	{2, "JZ", new String[] {"offset"}}, 
	{2, "AJMP", new String[] {"addr11"}}, 
	{2, "XRL", new String[] {"direct", "A"}}, 
	{3, "XRL", new String[] {"direct", "#immed"}}, 
	{2, "XRL", new String[] {"A", "#immed"}}, 
	{2, "XRL", new String[] {"A", "direct"}}, 
	{1, "XRL", new String[] {"A", "@R0"}}, 
	{1, "XRL", new String[] {"A", "@R1"}}, 
	{1, "XRL", new String[] {"A", "R0"}}, 
	{1, "XRL", new String[] {"A", "R1"}}, 
	{1, "XRL", new String[] {"A", "R2"}}, 
	{1, "XRL", new String[] {"A", "R3"}}, 
	{1, "XRL", new String[] {"A", "R4"}}, 
	{1, "XRL", new String[] {"A", "R5"}}, 
	{1, "XRL", new String[] {"A", "R6"}}, 
	{1, "XRL", new String[] {"A", "R7"}}, 
	{2, "JNZ", new String[] {"offset"}}, 
	{2, "ACALL", new String[] {"addr11"}}, 
	{2, "ORL", new String[] {"C", "bit"}}, 
	{1, "JMP", new String[] {"@A+DPTR"}}, 
	{2, "MOV", new String[] {"A", "#immed"}}, 
	{3, "MOV", new String[] {"direct", "#immed"}}, 
	{2, "MOV", new String[] {"@R0", "#immed"}}, 
	{2, "MOV", new String[] {"@R1", "#immed"}}, 
	{2, "MOV", new String[] {"R0", "#immed"}}, 
	{2, "MOV", new String[] {"R1", "#immed"}}, 
	{2, "MOV", new String[] {"R2", "#immed"}}, 
	{2, "MOV", new String[] {"R3", "#immed"}}, 
	{2, "MOV", new String[] {"R4", "#immed"}}, 
	{2, "MOV", new String[] {"R5", "#immed"}}, 
	{2, "MOV", new String[] {"R6", "#immed"}}, 
	{2, "MOV", new String[] {"R7", "#immed"}}, 
	{2, "SJMP", new String[] {"offset"}}, 
	{2, "AJMP", new String[] {"addr11"}}, 
	{2, "ANL", new String[] {"C", "bit"}}, 
	{1, "MOVC", new String[] {"A", "@A+PC"}}, 
	{1, "DIV", new String[] {"AB"}}, 
	{3, "MOV", new String[] {"direct", "direct"}}, 
	{2, "MOV", new String[] {"direct", "@R0"}}, 
	{2, "MOV", new String[] {"direct", "@R1"}}, 
	{2, "MOV", new String[] {"direct", "R0"}}, 
	{2, "MOV", new String[] {"direct", "R1"}}, 
	{2, "MOV", new String[] {"direct", "R2"}}, 
	{2, "MOV", new String[] {"direct", "R3"}}, 
	{2, "MOV", new String[] {"direct", "R4"}}, 
	{2, "MOV", new String[] {"direct", "R5"}}, 
	{2, "MOV", new String[] {"direct", "R6"}}, 
	{2, "MOV", new String[] {"direct", "R7"}}, 
	{3, "MOV", new String[] {"DPTR", "#immed"}}, 
	{2, "ACALL", new String[] {"addr11"}}, 
	{2, "MOV", new String[] {"bit", "C"}}, 
	{1, "MOVC", new String[] {"A", "@A+DPTR"}}, 
	{2, "SUBB", new String[] {"A", "#immed"}}, 
	{2, "SUBB", new String[] {"A", "direct"}}, 
	{1, "SUBB", new String[] {"A", "@R0"}}, 
	{1, "SUBB", new String[] {"A", "@R1"}}, 
	{1, "SUBB", new String[] {"A", "R0"}}, 
	{1, "SUBB", new String[] {"A", "R1"}}, 
	{1, "SUBB", new String[] {"A", "R2"}}, 
	{1, "SUBB", new String[] {"A", "R3"}}, 
	{1, "SUBB", new String[] {"A", "R4"}}, 
	{1, "SUBB", new String[] {"A", "R5"}}, 
	{1, "SUBB", new String[] {"A", "R6"}}, 
	{1, "SUBB", new String[] {"A", "R7"}}, 
	{2, "ORL", new String[] {"C", "/bit"}}, 
	{2, "AJMP", new String[] {"addr11"}}, 
	{2, "MOV", new String[] {"C", "bit"}}, 
	{1, "INC", new String[] {"DPTR"}}, 
	{1, "MUL", new String[] {"AB"}}, 
	{0, "reserved", new String[0]}, 
	{2, "MOV", new String[] {"@R0", "direct"}}, 
	{2, "MOV", new String[] {"@R1", "direct"}}, 
	{2, "MOV", new String[] {"R0", "direct"}}, 
	{2, "MOV", new String[] {"R1", "direct"}}, 
	{2, "MOV", new String[] {"R2", "direct"}}, 
	{2, "MOV", new String[] {"R3", "direct"}}, 
	{2, "MOV", new String[] {"R4", "direct"}}, 
	{2, "MOV", new String[] {"R5", "direct"}}, 
	{2, "MOV", new String[] {"R6", "direct"}}, 
	{2, "MOV", new String[] {"R7", "direct"}}, 
	{2, "ANL", new String[] {"C", "/bit"}}, 
	{2, "ACALL", new String[] {"addr11"}}, 
	{2, "CPL", new String[] {"bit"}}, 
	{1, "CPL", new String[] {"C"}}, 
	{3, "CJNE", new String[] {"A", "#immed", "offset"}}, 
	{3, "CJNE", new String[] {"A", "direct", "offset"}}, 
	{3, "CJNE", new String[] {"@R0", "#immed", "offset"}}, 
	{3, "CJNE", new String[] {"@R1", "#immed", "offset"}}, 
	{3, "CJNE", new String[] {"R0", "#immed", "offset"}}, 
	{3, "CJNE", new String[] {"R1", "#immed", "offset"}}, 
	{3, "CJNE", new String[] {"R2", "#immed", "offset"}}, 
	{3, "CJNE", new String[] {"R3", "#immed", "offset"}}, 
	{3, "CJNE", new String[] {"R4", "#immed", "offset"}}, 
	{3, "CJNE", new String[] {"R5", "#immed", "offset"}}, 
	{3, "CJNE", new String[] {"R6", "#immed", "offset"}}, 
	{3, "CJNE", new String[] {"R7", "#immed", "offset"}}, 
	{2, "PUSH", new String[] {"direct"}}, 
	{2, "AJMP", new String[] {"addr11"}}, 
	{2, "CLR", new String[] {"bit"}}, 
	{1, "CLR", new String[] {"C"}}, 
	{1, "SWAP", new String[] {"A"}}, 
	{2, "XCH", new String[] {"A", "direct"}}, 
	{1, "XCH", new String[] {"A", "@R0"}}, 
	{1, "XCH", new String[] {"A", "@R1"}}, 
	{1, "XCH", new String[] {"A", "R0"}}, 
	{1, "XCH", new String[] {"A", "R1"}}, 
	{1, "XCH", new String[] {"A", "R2"}}, 
	{1, "XCH", new String[] {"A", "R3"}}, 
	{1, "XCH", new String[] {"A", "R4"}}, 
	{1, "XCH", new String[] {"A", "R5"}}, 
	{1, "XCH", new String[] {"A", "R6"}}, 
	{1, "XCH", new String[] {"A", "R7"}}, 
	{2, "POP", new String[] {"direct"}}, 
	{2, "ACALL", new String[] {"addr11"}}, 
	{2, "SETB", new String[] {"bit"}}, 
	{1, "SETB", new String[] {"C"}}, 
	{1, "DA", new String[] {"A"}}, 
	{3, "DJNZ", new String[] {"direct", "offset"}}, 
	{1, "XCHD", new String[] {"A", "@R0"}}, 
	{1, "XCHD", new String[] {"A", "@R1"}}, 
	{2, "DJNZ", new String[] {"R0", "offset"}}, 
	{2, "DJNZ", new String[] {"R1", "offset"}}, 
	{2, "DJNZ", new String[] {"R2", "offset"}}, 
	{2, "DJNZ", new String[] {"R3", "offset"}}, 
	{2, "DJNZ", new String[] {"R4", "offset"}}, 
	{2, "DJNZ", new String[] {"R5", "offset"}}, 
	{2, "DJNZ", new String[] {"R6", "offset"}}, 
	{2, "DJNZ", new String[] {"R7", "offset"}}, 
	{1, "MOVX", new String[] {"A", "@DPTR"}}, 
	{2, "AJMP", new String[] {"addr11"}}, 
	{1, "MOVX", new String[] {"A", "@R0"}}, 
	{1, "MOVX", new String[] {"A", "@R1"}}, 
	{1, "CLR", new String[] {"A"}}, 
	{2, "MOV", new String[] {"A", "direct"}}, 
	{1, "MOV", new String[] {"A", "@R0"}}, 
	{1, "MOV", new String[] {"A", "@R1"}}, 
	{1, "MOV", new String[] {"A", "R0"}}, 
	{1, "MOV", new String[] {"A", "R1"}}, 
	{1, "MOV", new String[] {"A", "R2"}}, 
	{1, "MOV", new String[] {"A", "R3"}}, 
	{1, "MOV", new String[] {"A", "R4"}}, 
	{1, "MOV", new String[] {"A", "R5"}}, 
	{1, "MOV", new String[] {"A", "R6"}}, 
	{1, "MOV", new String[] {"A", "R7"}}, 
	{1, "MOVX", new String[] {"@DPTR", "A"}}, 
	{2, "ACALL", new String[] {"addr11"}}, 
	{1, "MOVX", new String[] {"@R0", "A"}}, 
	{1, "MOVX", new String[] {"@R1", "A"}}, 
	{1, "CPL", new String[] {"A"}}, 
	{2, "MOV", new String[] {"direct", "A"}}, 
	{1, "MOV", new String[] {"@R0", "A"}}, 
	{1, "MOV", new String[] {"@R1", "A"}}, 
	{1, "MOV", new String[] {"R0", "A"}}, 
	{1, "MOV", new String[] {"R1", "A"}}, 
	{1, "MOV", new String[] {"R2", "A"}}, 
	{1, "MOV", new String[] {"R3", "A"}}, 
	{1, "MOV", new String[] {"R4", "A"}}, 
	{1, "MOV", new String[] {"R5", "A"}}, 
	{1, "MOV", new String[] {"R6", "A"}}, 
	{1, "MOV", new String[] {"R7", "A"}}};
}