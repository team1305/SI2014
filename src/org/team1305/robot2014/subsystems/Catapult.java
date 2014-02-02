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
    
    /**
     * Gives the status of fireLock.
     * @return True if lock is active, false if system is able to be triggered.
     */
   public boolean getLockState(){
       return fireLock;
   }
    
    /**
     * Released the mechanism, firing the catapult.  Will not trigger without fireLock being disabled.
     * @param engaged Latch on.
     */
    public void pullFiringPin(boolean engaged){
        if (fireLock == false && engaged == true){
            //Stops all actions, fireLock must be false.
            
            sLatchSolenoid.set(true);
        }else{
            sLatchSolenoid.set(false);
            
        }
    }
    
    /**
     * Moves gears to allow loading of catapult.
     * @param engaged Gears in position to load.
     */
    public void setTransmissionEngaged(boolean engaged){
        sGearSolenoid.set(engaged);
        
    }
    
    /**
     * Loads catapult until the bottomLimit is reached.
     * @return Returns true if motor is still running, false if limit is hit, to LockNLoad.
     */
    public boolean winch(){
        if (dBottomLimit.get() == false){
            mLeftPullback.set(1);
            mRightPullback.set(1);
            return true;
        }else{
            mLeftPullback.set(0);
            mRightPullback.set(0);
            return false;
        }
        
        
    }
    
    /**
     * Prevents catapult from firing.
     */
    public void lock(){
        fireLock = true;
        
        
    }
    
    /**
     * Allows catapult to fire.
     */
    public void unlock(){
        fireLock = false;
        
        
    }
    
    /**
     * Stops all motion in the catapult loading process.
     */
    public void stop(){
        mLeftPullback.set(0);
        mRightPullback.set(0);
        
    }
    
}
