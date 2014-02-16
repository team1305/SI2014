 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team1305.robot2014.subsystems;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team1305.robot2014.RobotMap;
import org.team1305.robot2014.commands.claw.ClawPark;
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
    private final double CLAW_P =  0.5;
    private final double CLAW_I = 0.02;
    private final double CLAW_D = 0.0;
    
    private final double CLAW_P2 = -0.5;
    private final double CLAW_I2 = -0.02;
    private final double CLAW_D2 = 0.0;
    
    //Constants for potientiometer claw positions
    private final double POTVALUE_LEFT_PARK = 0;
    private final double POTVALUE_LEFT_OPEN = 2.5;
    private final double POTVALUE_LEFT_CLOSED = 3.4;
    private final double POTVALUE_RIGHT_PARK = 5;
    private final double POTVALUE_RIGHT_OPEN = 2;
    private final double POTVALUE_RIGHT_CLOSED = 1.20;
    
    //the sensor and motor objects. 
    private final Talon mLeftClaw = new Talon(RobotMap.PWM_CLAW_LEFT);
    private final Talon mRightClaw = new Talon(RobotMap.PWM_CLAW_RIGHT);
    private final AnalogPotentiometer potLeft = new AnalogPotentiometer(RobotMap.AN_CLAW_POT_LEFT);
    private final AnalogPotentiometer potRight = new AnalogPotentiometer(RobotMap.AN_CLAW_POT_RIGHT);
    //the pid controllers which actually control the arm motion
    private final PIDController leftPID = new PIDController(CLAW_P2, CLAW_I2, CLAW_D2, potLeft, mLeftClaw);
    private final PIDController rightPID = new PIDController(CLAW_P, CLAW_I, CLAW_D, potRight, mRightClaw); 
    //an integer representing the current state of the claw subsystem
    private int state = 0;
    
    public Claw(){
        leftPID.enable();
        rightPID.enable();
        //TODO: Set input and output ranges
        leftPID.setInputRange(0.0, 5.0);
        rightPID.setInputRange(0.0, 5.0);
        leftPID.setOutputRange(-1, 1);
        rightPID.setOutputRange(-1, 1);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new ClawStop());
    }
    /**
     * Sets the claws to the fully-retracted park position. 
     * This is the state that the robot will start in at the beginning
     * of the match. 
     * Ideally, once the match starts, the claws will never go back to this state.
     */
    public void park(){
        if (potLeft.get() >= 0.1){
            leftPID.setSetpoint(POTVALUE_LEFT_PARK);
            //mLeftClaw.set(0);
        }
        else{
            //mLeftClaw.set(-0.3);
            leftPID.disable();
        }
        
        if (potRight.get() <= 4.90){
            rightPID.setSetpoint(POTVALUE_RIGHT_PARK);
            //mRightClaw.set(0.0);
        }
        else{
            //mRightClaw.set(-0.3);
            rightPID.disable();
        }
        state = 0;
        SmartDashboard.putString("CONTROL MODE", "AUTO MODE");
        SmartDashboard.putString("Claw Status", "In park");
//        mLeftClaw.set(0.0);
//        mRightClaw.set(0.0);
    }
    
    
    /**
     * Sets the claws to the regular-opened position.
     * This is how the robot should be normally driving around in the field.
     * The driver should approach the ball in this state.
     */
    public void open(){
        if (potLeft.get() <= 2.4){
            leftPID.setSetpoint(POTVALUE_LEFT_OPEN);
            //mLeftClaw.set(0.3);
        }
        else if (potLeft.get() >= 2.6){
            leftPID.setSetpoint(POTVALUE_LEFT_OPEN);
            //mLeftClaw.set(0.3);
        }
        else{
            leftPID.disable();
            //mLeftClaw.set(0);
        }
        
        if (potRight.get() <= 1.95){
            //mRightClaw.set(0.3);
            rightPID.setSetpoint(POTVALUE_RIGHT_OPEN);
        }
        else if (potRight.get() >= 2.05){
            //mRightClaw.set(0.3);
            rightPID.setSetpoint(POTVALUE_RIGHT_OPEN);
        }
        else{
            rightPID.disable();
            //mRightClaw.set(0);
        }
        
        //rightPID.setSetpoint(POTVALUE_RIGHT_OPEN);
        state = 1;
        SmartDashboard.putString("CONTROL MODE", "AUTO MODE");
        SmartDashboard.putString("Claw Status", "Opening");
//        mLeftClaw.set(0.4);
//        mRightClaw.set(0.4);
    }
    
    public void getPot(){
        SmartDashboard.putNumber("Left Claw", potLeft.pidGet());
        SmartDashboard.putNumber("Right Claw", potRight.pidGet());
        SmartDashboard.putNumber("LeftPID", leftPID.get());
        SmartDashboard.putNumber("RightPID", rightPID.get());
    }
    /**
     * Close the claws, attempting to grasp the ball. 
     * This method will close the claws, attempting to grasp the ball.
     * Ideally, the closed position should be one in which the claws do not
     * interfere with the operation of the catapult, so that the catapult 
     * may fire with the claws in their closed state. 
     */
    public void close(){
        if (potLeft.get() >= 3.5){
            //mLeftClaw.set(0.3);
            leftPID.setSetpoint(POTVALUE_LEFT_CLOSED);
        }
        else if (potLeft.get() <= 3.3){
            //mLeftClaw.set(0.3);
            leftPID.setSetpoint(POTVALUE_LEFT_CLOSED);
        }
        else{
            leftPID.disable();
           mLeftClaw.set(0);
        }
        
        if (potRight.get() >= 1.25){
            rightPID.setSetpoint(POTVALUE_RIGHT_CLOSED);
            //mRightClaw.set(0.3);
        }
        else if (potRight.get() <= 1.15){
            rightPID.setSetpoint(POTVALUE_RIGHT_CLOSED);
            //mRightClaw.set(0.3);
        }
        else{
            mRightClaw.set(0);
            rightPID.disable();
        }
        state = 2; 
        SmartDashboard.putString("CONTROL MODE", "AUTO MODE");
        SmartDashboard.putString("Claw Status", "Closing");
//        mLeftClaw.set(-0.4);
//        mRightClaw.set(-0.4);
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
    
    public void clawRight(){
        //rightPID.disable();
        //leftPID.disable();
        SmartDashboard.putString("CONTROL MODE", "MANUAL MODE");
        mRightClaw.set(0.3);
    }
    
    public void clawLeft(){
        //leftPID.disable();
        //rightPID.disable();
        SmartDashboard.putString("CONTROL MODE", "MANUAL MODE");
        mLeftClaw.set(0.3);
    }
    
    public void clawRightReverse(){
        //rightPID.disable();
        //leftPID.disable();
        SmartDashboard.putString("CONTROL MODE", "MANUAL MODE");
        mRightClaw.set(-0.3);
    }
    
    public void clawLeftReverse(){
        //leftPID.disable();
        //rightPID.disable();
        SmartDashboard.putString("CONTROL MODE", "MANUAL MODE");
        mLeftClaw.set(-0.3);
    }
    
    /**
     * Stops the claw movement.
     */
    public void clawstop() {
        rightPID.disable();
        leftPID.disable();
        mLeftClaw.set(0);
        mRightClaw.set(0);
    }
    
    public void enable() {
        rightPID.enable();
        leftPID.enable();
    }
}
