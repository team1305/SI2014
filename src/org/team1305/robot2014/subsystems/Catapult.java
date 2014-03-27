/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team1305.robot2014.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team1305.robot2014.RobotMap;

/**
 *
 * @author Root 1
 */

public class Catapult extends Subsystem {
    //Winch motors.
    private final Talon mRightPullback = new Talon (RobotMap.PWM_PULLBACK_RIGHT);
    
    //Solenoids for controlling the shooting mechanism actions.
    private final Solenoid sLatchSolenoid = new Solenoid (RobotMap.SOL_LATCH);
    
    //Limit switch for detecting if the shooter is done loading.
    private final DigitalInput dBottomLimit = new DigitalInput (RobotMap.DIO_LIMIT_CAT_BOTTOM);
    
    //Locks firing.
    private boolean fireLock;
    private boolean isLoaded = false;

    /**
     * Locks firing.
     */
    public Catapult() {
        this.fireLock = true;
    }
    
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    /**
     * Gives the status of fireLock.
     * @return True if Lock is active, false if system is able to be triggered.
     */
   public boolean GetLockState(){
       return fireLock;
   }
    
    /**
     * Released the mechanism, firing the catapult.  Will not trigger without fireLock being disabled.
     * @param engaged Latch on.
     */
    public void PullFiringPin(boolean engaged){
        if (fireLock == false){
            //Stops all actions, fireLock must be false.
            sLatchSolenoid.set(true);
            fireLock = true;
            isLoaded = false;
        }else{
            sLatchSolenoid.set(false);
            
        }
    }
    
    public void AutoFire(){
        //Overrides safety mechanisms in autonomous to fire catapult.
        sLatchSolenoid.set(true);
        fireLock = true;
        isLoaded = false;
    }
    
    public boolean GetBottomLimitSWState()
    {
         return dBottomLimit.get();
    }
    
    /**
     * Loads catapult until the bottomLimit is reached.
     * @return Returns true if motor is still running, false if limit is hit, to LockNLoad.
     */
    public boolean WinchAtLimit(){
        if (dBottomLimit.get() == false && isLoaded == false){
            mRightPullback.set(1);
            sLatchSolenoid.set(false);
            return false;
        }else{
            mRightPullback.set(0);
            isLoaded = true;
            return true;
        }
        
    }
    
    public void set(int speed){
        mRightPullback.set(speed);
    }
    
    /**
     * Prevents catapult from firing.
     */
    public void Lock(){
        fireLock = true;
    }
    
    /**
     * Allows catapult to fire.
     */
    public void Unlock(){
        fireLock = false;
        SmartDashboard.putBoolean("Fire Lock", fireLock);
        SmartDashboard.putBoolean("Limit Switch", dBottomLimit.get());
    }
    
    /**
     * Stops all motion in the catapult loading process.
     */
    public void Stop(){
        mRightPullback.set(0);
    }
    
}
