
package Main;

import InstructionSet.Instruction;
import InstructionSet.InstructionSet_8051;
import static Main.Cpu.memory;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A classe Memory é a representação da memória do microcrontrolador no programa, contendo tanto a memória RAM quanato a ROM e os metodos necessários para a manipulação das mesmas. 
 * @author Gerson Menezes & Vinícius Santos
 * @version 1.0
 */
public class Memory {
    
    /**
     * O atributo rom representa a memória ROM do microcontrolador
     */
    public static Instruction[] rom = new Instruction[4095];

    /**
     * O atributo ram representa a memória RAM do microcontrolador
     */
    public static int[] ram = new int[256];
        
    /**
     * Insere as intruções na memória ROM 
     * @param data Array de inteiros contendo a lista de upcodes
     * @return Array bidimencional de objetos, contendo a lista de upcodes e a lsita de intruções
     */
    public Object[] load(int[] data){
        
        for (int i = 0; i<data.length; i++){
            Object[] opcode_info = InstructionSet_8051.instruction_set[data[i]];
            try {
                int[] args = new int[(int) opcode_info[0] - 1];
                for (int k = 1; k<=args.length; k++){
                    args[k-1] = data[i+k];
                }
                System.out.println(data[i] +" "+ opcode_info[1]);
                Constructor c = Class.forName("InstructionSet." + opcode_info[1]).getConstructor(new Class[]{int.class, int[].class, String[].class});
                c.setAccessible(true);
                rom[i] = (Instruction) c.newInstance(data[i], args, (String[]) opcode_info[2]);
                i += args.length;
                
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        
        Object[] mem = {data, rom};
        return mem;
    }
    
    /**
     * Converte um dado sem sinal em um dado sinalizado
     * @param unsignedData inteiro correspondente ao dado não sinalzado
     * @return inteiro correspondente ao dadi sinalizado
     */
    public static int toSignedNumber(int unsignedData) {
        if (unsignedData >= 0 && unsignedData <= 127)
            return unsignedData; 
        return unsignedData - 256;
    }
    
    /**
     * Verifica se um endereço da memória é de bit endereçavel
     * @param address interio que representa o enderaço da memoria a ser verificado
     * @return valor booleano representando o resultato da verificação
     */
    public static boolean isBitAddressable(int address) {
        Integer[] bit_address_sfrs = new Integer[]{128, 136, 144, 152, 160, 168, 176, 184, 208, 224, 240};
        return ((address >= 32 && address <= 47) || (Arrays.asList(bit_address_sfrs).contains(address)));
    }
    
    /**
     * Altera o valor salvo em uma posição da memória
     * @param address edereço que será alterado
     * @param value inteiro que contem o valor que será salvo na memória (0 - 1)
     * @throws Exception Aponta qualquer erro nos valores informados
     */
    public static void setByte(int address, int value) throws Exception{
        value &= 0xFF;
        
        ram[address] = value;
        Ram.setByte(address, value);
        
        if (address == 0xE0 || address == 0xD0)
            updateParity();
    }
    
    
    public static void updateParity()throws Exception {
        int cont = 0;
        for (int i = 0; i < 8; i++) cont += getBit(0xE0, i);
        setBit(0xD0, 0, cont % 2);
    }
    
    
    /**
     * Adiciona o endereço pelo valor inserido
     * @param address edereço que será adicionado
     * @param value inteiro que contem o valor que adicionará
     * @throws Exception Aponta qualquer erro nos valores informados
     */
    public static void addByte(int address, int value) throws Exception{
        if (address < 0 || address > ram.length - 1) {
            throw new Exception("Address out of range");
        }
        ram[address] += value;
        Ram.setByte(address, ram[address]);
    }
    
    
    /**
     * Retorna o byte no endereço inserido
     * @param address edereço desejado
     * @throws Exception Aponta qualquer erro no endereço inserido
     */
    public static int getByte(int address) throws Exception{
        if (address < 0 || address > ram.length - 1) {
            throw new Exception("Address out of range");
        }
        return ram[address];
    }
    
    
    /**
     * Altera um bit específico de uma posição de memória
     * @param address interio que representa o endereço da memória que será alterado
     * @param bit interio que representa o bit que será alterado
     * @param value inteiro contendo o novo valor do bit (0 - 1)
     * @throws Exception Aponta qualquer erro nos valores informados
     */
    public static void setBit(int address, int bit, int value) throws Exception{
        if (isBitAddressable(address)) {
            if (value == 1) {
                int data = ram[address];
                int mask = 1;
                mask <<= bit;
                data |= mask;
                ram[address] = data;
            } else if (value == 0) {
                int data = ram[address];
                int mask = 1;
                mask = mask << bit ^ 0xFF;
                data &= mask;
                ram[address] = data;
            } else {
                throw new Exception("Invalid value");
            }
            Ram.setBit(address, bit, value);
        } else {
            throw new Exception(String.format("%02x", address).toUpperCase() + " isn't bit addressable");
        }
    }
    
    /**
     * Obtem o valor de um bit específico em uma posição de memória
     * @param address interio que representa o endereço da memória a ser verificado
     * @param bit inteiro contendo a posição do bit que será verificado 
     * @return inteiro contendo o valor do bit
     * @throws Exception Aponta qualquer erro nos valores informados
     */
    public static int getBit(int address, int bit) throws Exception{
        if (isBitAddressable(address)) {
            int data = ram[address];
            data >>= bit;
            data &= 0x1;
            return data;
        }
        throw new Exception(String.format("%02x", address).toUpperCase() + " isn't bit addressable");
    }
    
    /**
     * Obtem o qual banco está selecionado PSW
     * @return intero contendo o número do banco
     */
    public static int getBank(){
        return (ram[0xD0] & 0x18) >> 3;
    }
    
    /**
     * Altera o banco seleconádo no PSW
     * @param value número do novo banco
     */
    public static void setBank(int value){
        value <<= 3;
        ram[0xD0] |= value;
        value |= 0xE7;
        ram[0xD0] &= value;
    }
    
    /**
     * Limpa as memórias RAM e ROM
     */
    public static void reset(){
        rom = new Instruction[rom.length];
        ram = new int[ram.length];
        ram[0x81] = 0x07;
    }
}
