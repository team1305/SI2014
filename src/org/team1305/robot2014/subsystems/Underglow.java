/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team1305.robot2014.subsystems;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.team1305.robot2014.RobotMap;

/**
 * The blue underglow for the robot.
 * All timing of underglow flashing should be controlled by the calling command
 * 
 * @author Paul Belanger
 */
public class Underglow extends Subsystem {
    //The underglow must exist on a Relay due to its high current requirements. 
    private final Relay lights = new Relay(RobotMap.REL_UNDERGLOW);
    
    public Underglow(){
        //We don't want to run the relay backward.
        lights.setDirection(Relay.Direction.kForward);
        //have a well-defined current state.
        lights.set(Relay.Value.kOff);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    /**
     * Turns the underglow on.
     */
    public void on(){
        lights.set(Relay.Value.kOn);
    }
    /**
     * Turns the underglow off. 
     */
    
    /**
     * Turns underglow off.
     */
    public void off(){
        lights.set(Relay.Value.kOn);
    }
    
    /**
     * Changes the state of the underglow [On/Off].
     */
    public void toggle(){
        if(lights.get() == Relay.Value.kOff){
            lights.set(Relay.Value.kOn);
        }
        else{
            lights.set(Relay.Value.kOff);
        }
    }
}
