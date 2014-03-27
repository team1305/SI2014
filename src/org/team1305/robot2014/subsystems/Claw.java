 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team1305.robot2014.subsystems;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team1305.robot2014.RobotMap;
import org.team1305.robot2014.commands.claw.ClawStop;

/**
 * The two claws that are used to grasp the ball.
 * The claws have three defined states: parked, open, and closed. 
 * 
 * All matches should start with the claws in the "Park" position.
 * 
 * @author Paul Belanger
 * @since 2014-02-03
 */
public class Claw extends Subsystem {
    //PID constatnts for the claw PIDs.
    //TODO: get proper values for these.
    

    //the sensor and motor objects. 
    private final Talon mLeft = new Talon(RobotMap.PWM_CLAW_LEFT);
    private final Talon mRight = new Talon(RobotMap.PWM_CLAW_RIGHT);
    //the pid controllers which actually control the arm motion
    //an integer representing the current state of the claw subsystem
    
    private final Solenoid solClaw = new Solenoid(RobotMap.SOL_CLAW);
    
    
    public Claw(){
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new ClawStop());
    }
    
    
    /**
     * Sets the claws to the regular-opened position.
     * This is how the robot should be normally driving around in the field.
     * The driver should approach the ball in this state.
     */
    public void open(){
        solClaw.set(false);
    }
    
    /**
     * Close the claws, attempting to grasp the ball. 
     * This method will close the claws, attempting to grasp the ball.
     * Ideally, the closed position should be one in which the claws do not
     * interfere with the operation of the catapult, so that the catapult 
     * may fire with the claws in their closed state. 
     */
    public void close(){
        solClaw.set(true);
    }
    /**
     * Stops the claw movement.
     */
    public void stop() {
        mLeft.set(0);
        mRight.set(0);
    }
    
    public void intake() {
        mLeft.set(1);
        mRight.set(1);
    }
    
    public void eject() {
        mLeft.set(-1);
        mRight.set(-1);
    }
    
}
