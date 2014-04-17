/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team1305.robot2014.subsystems;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team1305.robot2014.RobotMap;
import org.team1305.robot2014.commands.chassis.ArcadeDrive;

/**
 *Chassis is the culmination of all the driving stuffs in the robot.  
 * PID loops all get set/enabled here.
 * Mecanum Drive is all put together here as well.
 * Home of StopPlz, toggleSmoothing, etc.
 * @author Root 1
 */
public class Chassis extends Subsystem {
    //Driving links, such as smoothing and connecting Talons.
    //********************************************************//
    private final Talon tLeftFront = new Talon(RobotMap.PWM_DRIVE_LEFT_FRONT);
    private final Talon tLeftBack = new Talon(RobotMap.PWM_DRIVE_LEFT_BACK);
    private final Talon tRightFront = new Talon(RobotMap.PWM_DRIVE_RIGHT_FRONT);
    private final Talon tRightBack = new Talon(RobotMap.PWM_DRIVE_RIGHT_BACK);
    private final RobotDrive robotDrive = new RobotDrive(tLeftFront, tLeftBack, tRightFront, tRightBack);
    //********************************************************//
    
    
    
    //Timers and delays.
    //********************************************************//
    private final Timer mobilityTimer = new Timer();
    private final Timer rotateTimer = new Timer();
    private static final double DELAY_BEFORE_MOBILITY_TIMER = 1.50;
//    private static final double DELAY_AFTER_MOBILITY_TIMER = 1;
    private static final double DELAY_BEFORE_ROTATE_TIMER = 0.5;
    private static final double DELAY_AFTER_ROTATE_TIMER = 0.5;
    private boolean isDone = false;
    private int currentState = 0;
    private int currentRotateState = 0;
    public boolean lowGear = false;
    //********************************************************//
    
    /**
     * This is the chassis constructor.
     */
    public Chassis() {
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
    }
    
    /**
     * 
     */
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ArcadeDrive());
    }
    
    public void arcadeDrive(double move, double steer) {
        if(lowGear == false){
        robotDrive.arcadeDrive(move, steer, true);
        }
        else{
            robotDrive.arcadeDrive((move/2), (steer/1.3), true);
        }
        
    }    
    
    /**
     * Rotates the robot towards a camera target in autonomous.
     * @return 
     */
    public boolean rotateMovement(){
        switch(currentRotateState){
            case 0:
                rotateTimer.reset();
                rotateTimer.start();
                currentState++;
                break;
            case 1:
                if (rotateTimer.get()>=DELAY_BEFORE_ROTATE_TIMER){
                    currentState++;
                    break;
                }
                robotDrive.arcadeDrive(0, 0.2);
                break;
            case 2:
                isDone = true;
                return true;
        }
        return false;
    }
    
    /**
     * Rotates the robot back in autonomous.
     * @return 
     */
    public boolean rotateBack(){
        switch(currentRotateState){
            case 0:
                rotateTimer.reset();
                rotateTimer.start();
                currentState++;
                break;
            case 1:
                if (rotateTimer.get()>=DELAY_AFTER_ROTATE_TIMER){
                    currentState++;
                    break;
                }    
            robotDrive.arcadeDrive(0,-0.2);
            case 2:
                isDone = true;
                return true;
        }
        return false;
    }
    
    /**
     * Moves the robot in autonomous for mobility movement.
     * Uses mobilityTimer to order events, drives forward for X seconds and then drives backwards for X seconds.
     * @return Returns true if the function is done.
     */
    public boolean mobilityMovement(){
        switch (currentState){
            case 0:
                mobilityTimer.reset();
                mobilityTimer.start();
                currentState++;
                break;
            case 1:
                if (mobilityTimer.get()>=DELAY_BEFORE_MOBILITY_TIMER)
                {
                    mobilityTimer.stop();
                    currentState++;
                }
                robotDrive.mecanumDrive_Cartesian(0.6, 0, 0, 90);
                break;
            case 2:
                robotDrive.mecanumDrive_Cartesian(0, 0, 0, 90);
                isDone = true;
                currentState = 0;
                return true; 
        }
        return false;
    }
    
    public void switchGear(){
        if (lowGear == false){
            lowGear = true;
            SmartDashboard.putString("Gear Status", "In Low Gear");
        }
        else{
            lowGear = false;
            SmartDashboard.putString("Gear Status", "In High Gear");
        }
    }
    
    /**
     * Stops the robot.
     */
    public void stopPlz(){
        robotDrive.arcadeDrive(0,0);
    }
    
    }
