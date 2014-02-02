/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team1305.robot2014.subsystems;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.team1305.robot2014.RobotMap;

/**
 *
 * @author Root 1
 */

public class Catapult extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private final Talon mLeftPullback = new Talon (RobotMap.PWM_PULLBACK_LEFT);
    private final Talon mRightPullback = new Talon (RobotMap.PWM_PULLBACK_RIGHT);
    
    private final Solenoid sGearSolenoid = new Solenoid (RobotMap.SOL_GEAR);
    private final Solenoid sLatchSolenoid = new Solenoid (RobotMap.SOL_LATCH);
    
    private final AnalogPotentiometer aPullbackPot = new AnalogPotentiometer (RobotMap.AN_PULLBACK_POT);
    
    private final DigitalInput dBottomLimit = new DigitalInput (RobotMap.DIO_LIMIT_CAT_BOTTOM);
    
    private boolean fireLock;

    public Catapult() {
        this.fireLock = true;
    }
    
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void fire(){
        if (fireLock = true){
            
            
        }
        
        
    }
    
    public void load(){
        if (dBottomLimit.get() == false){
            sGearSolenoid.set(true);
        
            mLeftPullback.set(100);
            mRightPullback.set(-100);
        }else{
            mLeftPullback.set(0);
            mRightPullback.set(0);
        }
    }
    
    public void lock(){
        fireLock = true;
        
        
    }
    
    public void unlock(){
        fireLock = false;
        
        
    }
    
    public void stop(){
        mLeftPullback.set(0);
        mRightPullback.set(0);
        
    }
    
}
