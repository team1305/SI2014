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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team1305.robot2014.RobotMap;
import org.team1305.robot2014.commands.claw.Park;

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
    private final double CLAW_P = 0.3;
    private final double CLAW_I = 0.01;
    private final double CLAW_D = -0.1;
    
    private final double CLAW_P2 = -0.3;
    private final double CLAW_I2 = -0.01;
    private final double CLAW_D2 = 0.1;
    
    //Constants for potientiometer claw positions
    private final double POTVALUE_LEFT_PARK = 0.26;
    private final double POTVALUE_LEFT_OPEN = 2.75;
    private final double POTVALUE_LEFT_CLOSED = 3.68;
    private final double POTVALUE_RIGHT_PARK = 4.97;
    private final double POTVALUE_RIGHT_OPEN = 2.29;
    private final double POTVALUE_RIGHT_CLOSED = 1.36;
    

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
        leftPID.setOutputRange(-0.2, 0.2);
        rightPID.setOutputRange(-0.2, 0.2);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new Park());
    }
    /**
     * Sets the claws to the fully-retracted park position. 
     * This is the state that the robot will start in at the beginning
     * of the match. 
     * Ideally, once the match starts, the claws will never go back to this state.
     */
    public void park(){
        if (potLeft.get() >= 0.2){
            leftPID.setSetpoint(POTVALUE_LEFT_PARK);
        }
        else{
            leftPID.disable();
        }
        
        if (potRight.get() <= 4.8){
            rightPID.setSetpoint(POTVALUE_RIGHT_PARK);
        }
        else{
            rightPID.disable();
            mRightClaw.set(0.0);
        }
        state = 0;
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
        if (potLeft.get() <= 2.7){
            leftPID.setSetpoint(POTVALUE_LEFT_OPEN);
        }
        else if (potLeft.get() >= 2.9){
            leftPID.setSetpoint(POTVALUE_LEFT_OPEN);
        }
        else{
            leftPID.disable();
        }
        
        if (potRight.get() <= 2.3){
            rightPID.setSetpoint(POTVALUE_RIGHT_OPEN);
        }
        else if (potRight.get() >= 2.5){
            rightPID.setSetpoint(POTVALUE_RIGHT_OPEN);
        }
        else{
            rightPID.disable();
        }
        
        rightPID.setSetpoint(POTVALUE_RIGHT_OPEN);
        state = 1;
        SmartDashboard.putString("Claw Status", "Opening");
//        mLeftClaw.set(0.4);
//        mRightClaw.set(0.4);
    }
    
    public void getPot(){
        SmartDashboard.putNumber("Left Claw", potLeft.pidGet());
        SmartDashboard.putNumber("Right Claw", potRight.pidGet());
    }
    /**
     * Close the claws, attempting to grasp the ball. 
     * This method will close the claws, attempting to grasp the ball.
     * Ideally, the closed position should be one in which the claws do not
     * interfere with the operation of the catapult, so that the catapult 
     * may fire with the claws in their closed state. 
     */
    public void close(){
        if (potLeft.get() >= 3.7){
            leftPID.setSetpoint(POTVALUE_LEFT_CLOSED);
        }
        else if (potLeft.get() <= 3.6){
            leftPID.setSetpoint(POTVALUE_LEFT_CLOSED);
        }
        else{
            leftPID.disable();
        }
        
        if (potRight.get() >= 1.4){
            rightPID.setSetpoint(POTVALUE_RIGHT_CLOSED);
        }
        else if (potRight.get() <= 1.3){
            rightPID.setSetpoint(POTVALUE_RIGHT_CLOSED);
        }
        else{
            rightPID.disable();
        }
        state = 2; 
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
    /**
     * Stops the claw movement.
     */
    public void clawstop() {
        mLeftClaw.set(0);
        mRightClaw.set(0);
    }
    
    public void enable() {
        rightPID.enable();
        leftPID.enable();
    }
}
