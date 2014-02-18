/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team1305.robot2014.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.team1305.robot2014.RobotMap;
import org.team1305.robot2014.commands.passer.PassDisable;

/**
 *
 * @author Root 1
 */
public class Passer extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    private final Solenoid sLPasser = new Solenoid (RobotMap.SOL_LPASSER);
    private final Solenoid sRpasser = new Solenoid (RobotMap.SOL_RPASSER);
    
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new PassDisable());
    }
    
    /**
     * Fires passing mechanism.
     */
    public void enable(){
        sLPasser.set(true);
        sRpasser.set(true);
    }
    
    /**
     * Retracts passing mechanism.
     */
    public void disable(){
        sLPasser.set(false);
        sRpasser.set(false);
    }
}
