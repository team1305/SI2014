/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team1305.robot2014.subsystems;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.team1305.robot2014.RobotMap;

/**
 * 
 * @author Paul Belanger
 * @since 2014-02-03
 */
public class Claw extends Subsystem {
    //PID constatnts for the claw PIDs.
    //TODO: get proper values for these.
    private final double CLAW_P = 0.0;
    private final double CLAW_I = 0.0;
    private final double CLAW_D = 0.0;
    
    //Constants for potientiometer claw positions
    private final int POTVALUE_LEFT_PARK = 0;
    private final int POTVALUE_LEFT_OPEN = 0;
    private final int POTVALUE_LEFT_CLOSED = 0;
    private final int POTVALUE_RIGHT_PARK = 0;
    private final int POTVALUE_RIGHT_OPEN = 0;
    private final int POTVALUE_RIGHT_CLOSED = 0;
    

    //the sensor and motor objects. 
    private final Talon mLeftClaw = new Talon(RobotMap.PWM_CLAW_LEFT);
    private final Talon mRightClaw = new Talon(RobotMap.PWM_CLAW_RIGHT);
    private final AnalogPotentiometer potLeft = new AnalogPotentiometer(RobotMap.AN_CLAW_POT_LEFT);
    private final AnalogPotentiometer potRight = new AnalogPotentiometer(RobotMap.AN_CLAW_POT_RIGHT);
    //the pid controllers which actually control the arm motion
    private final PIDController leftPID = new PIDController(CLAW_P, CLAW_I, CLAW_D, potLeft, mLeftClaw);
    private final PIDController rightPID = new PIDController(CLAW_P, CLAW_I, CLAW_D, potRight, mRightClaw); 
    //an integer representing the current state of the claw subsystem
    private int state = 0;
    
    public Claw(){
        leftPID.enable();
        rightPID.enable();
        //TODO: Set input and output ranges
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    /**
     * Sets the claws to the fully-retracted park position. 
     * This is the state that the robot will start in at the beginning
     * of the match. 
     * Ideally, once the match starts, the claws will never go back to this state.
     */
    public void park(){
        leftPID.setSetpoint(POTVALUE_LEFT_PARK);
        rightPID.setSetpoint(POTVALUE_RIGHT_PARK);
        state = 0;
    }
    /**
     * Sets the claws to the regular-opened position.
     * This is how the robot should be normally driving around in the field.
     * The driver should approach the ball in this state.
     */
    public void open(){
        leftPID.setSetpoint(POTVALUE_LEFT_OPEN);
        rightPID.setSetpoint(POTVALUE_RIGHT_OPEN);
        state = 1;
    }
    /**
     * Close the claws, attempting to grasp the ball. 
     * This method will close the claws, attempting to grasp the ball.
     * Ideally, the closed position should be one in which the claws do not
     * interfere with the operation of the catapult, so that the catapult 
     * may fire with the claws in their closed state. 
     */
    public void close(){
        leftPID.setSetpoint(POTVALUE_LEFT_CLOSED);
        rightPID.setSetpoint(POTVALUE_RIGHT_CLOSED);
        state = 2; 
    }
    /**
     * Returns the current state of the claw subsystem
     * @return 0 if the claws are in the parked state;
     * 1 if the claws are in the open state;
     * 2 if the claws are in the closed state.
     */
    public int getState(){
        return state;
    }
    
}
