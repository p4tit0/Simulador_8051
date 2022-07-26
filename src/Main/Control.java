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
public class Control extends Thread
{
    private final int TMOD = 0x89;
    private final int TCON = 0x88;
    
    
    private int get_flag(int addr, int bit) 
    {
        int flag = ram[addr];
        flag >>= bit;
        flag &= 0x1;
        return flag;
    } 
    
    
    @Override
    public void run()
    {  
        Timer t0 = null;
        Timer t1 = null;
        
        /*
        ram[this.TMOD] = 0b00000001;
        ram[this.TCON] = 0b00010000;
        ram[0x8C] = 0xFF;
        ram[0x8A] = 0xF0;
        */
        while (true) 
        {   
            // --------------------------- TIMER 0 ----------------------------
            boolean int0 = true;
            if (get_flag(TMOD, 3) == 1) 
                int0 = get_flag(TCON, 1) == 1;
            
            int mode0 = (get_flag(TMOD, 1) << 1) | get_flag(TMOD, 0);
            
            if ((t0 == null || !t0.isAlive()) && get_flag(TCON, 4) == 1 && int0)
            {
                t0 = new Timer(0, mode0, (get_flag(TMOD, 2) == 1));
                t0.start();
            }
            
            if (t0 != null && t0.isAlive() && get_flag(TCON, 4) == 0)
                t0.interrupt();
            
            // --------------------------- TIMER 1 ----------------------------
            boolean int1 = true;
            if (get_flag(TMOD, 7) == 1) 
                int1 = get_flag(TCON, 3) == 1;
            
            int mode1 = (get_flag(TMOD, 5) << 1) | get_flag(TMOD, 4);
            if ((t1 == null || !t1.isAlive()) && get_flag(TCON, 6) == 1 && int1)
            {
                t1 = new Timer(1, mode1, (get_flag(TMOD, 6) == 1));
                t1.start();
            }
            
            if (t1 != null && t1.isAlive() && get_flag(TCON, 6) == 0)
                t1.interrupt();
        }
    }  
}
