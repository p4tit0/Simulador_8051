/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import static Main.Memory.ram;

/**
 *
 * @author space
 */
public class Timer extends Thread{
    private int mode;
    private boolean counter;
    private int int_bit;
    private int int_vec;
    private int th;
    private int tl;
    
    Timer(int timer, int mode, boolean counter)
    {
        System.out.println("Timer criado");
        this.mode = mode;
        this.counter = counter;
        if (timer == 0)
        {
            this.th = 0x8C;
            this.tl = 0x8A;
            this.int_bit = 5;
            this.int_vec = 0x000B;
        }
        else if (timer == 1)
        {
            this.th = 0x8D;
            this.tl = 0x8B;
            this.int_bit = 7;
            this.int_vec = 0x001B;
        }
        System.out.println(this.mode);
    }
    
    public void run()
    {
        switch (mode)
        {
            case 0:
                int time = (ram[th] << 5) |  (ram[tl] & 0b00011111);
                while (time < 0x1FFF)
                {
                    try {
                        Thread.sleep(1000);
                    } catch(InterruptedException e) {
                        System.out.println("An Excetion occured: " + e);
                    }
                    time++;
                    ram[th] = time >> 5;
                    ram[tl] = time & 0b00011111;
                }
                try {
                    Memory.setBit(0x88 + int_bit, 1);
                } catch (Exception e){
                    System.out.println("An Excetion occured: " + e);
                }
                break;
            case 1:
                time = (ram[th] << 8) |  ram[tl];
                while (time < 0xFFFF)
                {
                    System.out.println(time);
                    try {
                        Thread.sleep(1000);
                    } catch(InterruptedException e) {
                        System.out.println("An Excetion occured: " + e);
                    }
                    time++;
                    ram[th] = time >> 8;
                    ram[tl] = time & 0xFF;
                }
                try {
                    Memory.setBit(0x88 + int_bit, 1);
                } catch (Exception e){
                    System.out.println("An Excetion occured: " + e);
                }
                ram[th] = 0;
                ram[tl] = 0;
                break;
            case 2:
                ram[tl] = ram[th];
                while (Memory.ram[tl] < 0xFF)
                {
                    try {
                        Thread.sleep(1000);
                    } catch(InterruptedException e) {
                        System.out.println("An Excetion occured: " + e);
                    }
                    ram[tl]++;
                }
                try {
                    Memory.setBit(0x88 + int_bit, 1);
                } catch (Exception e){
                    System.out.println("An Excetion occured: " + e);
                }
                ram[tl] = ram[th];
                break;
            case 3:
                while (ram[th] < 0xFF || ram[tl] < 0xFF)
                {
                    try {
                        Thread.sleep(1000);
                    } catch(InterruptedException e) {
                        System.out.println("An Excetion occured: " + e);
                    }
                    if (ram[th] < 0xFF && (ram[0x88] >> 7) == 0)
                        ram[th]++;
                    else
                    {
                        try {
                            Memory.setBit(0x8F, 1);
                        } catch (Exception e){
                            System.out.println("An Excetion occured: " + e);
                        }
                        Memory.ram[th] = 0;
                        Cpu.Int_aux = Cpu.PC;
                        Cpu.PC = int_vec;
                        System.out.println("CHAMAR INTERRUPÇÂO");
                    }
                    if (Memory.ram[tl] < 0xFF && ((ram[0x88] >> 5) & 1) == 0)
                        Memory.ram[tl]++;
                    else
                    {
                        try {
                            Memory.setBit(0x8D, 1);
                        } catch (Exception e){
                            System.out.println("An Excetion occured: " + e);
                        }
                        Memory.ram[th] = 0;
                        Cpu.Int_aux = Cpu.PC;
                        Cpu.PC = int_vec;
                        System.out.println("CHAMAR INTERRUPÇÂO");
                    }
                }
                break;
        }
        Cpu.Int_aux = Cpu.PC;
        Cpu.PC = int_vec;
        System.out.println("CHAMAR INTERRUPÇÂO");
    }
}
