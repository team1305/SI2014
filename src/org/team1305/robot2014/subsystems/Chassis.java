/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team1305.robot2014.subsystems;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team1305.robot2014.ManualPIDLink;
import org.team1305.robot2014.RobotMap;
import org.team1305.robot2014.commands.chassis.MecanumDrive;

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
    private boolean isSmoothing = true;
    private final Talon tLeftFront = new Talon(RobotMap.PWM_DRIVE_LEFT_FRONT);
    private final Talon tLeftBack = new Talon(RobotMap.PWM_DRIVE_LEFT_BACK);
    private final Talon tRightFront = new Talon(RobotMap.PWM_DRIVE_RIGHT_FRONT);
    private final Talon tRightBack = new Talon(RobotMap.PWM_DRIVE_RIGHT_BACK);
    private final RobotDrive robotDrive = new RobotDrive(tLeftFront, tLeftBack, tRightFront, tRightBack);
    //********************************************************//
    
    //PID variables, should not be touched.
    //********************************************************//
    private final ManualPIDLink steerLink = new ManualPIDLink();
    private final ManualPIDLink driveLink = new ManualPIDLink();
    private final ManualPIDLink rotateLink = new ManualPIDLink();
    
    private final double DRIVE_P = 0.0;
    private final double DRIVE_I = 0.4;
    private final double DRIVE_D = -0.01;
    private final double STEER_P = 0.0;
    private final double STEER_I = 0.4;
    private final double STEER_D = -0.01;
    private final double ROTATE_P = 0.0;
    private final double ROTATE_I = 0.4;
    private final double ROTATE_D = -0.01;
    
    private final PIDController drivePID = new PIDController(DRIVE_P, DRIVE_I, DRIVE_D, driveLink, driveLink);
    private final PIDController steerPID = new PIDController(STEER_P, STEER_I, STEER_D, steerLink, steerLink);
    private final PIDController rotatePID = new PIDController(ROTATE_P, ROTATE_I, ROTATE_D, rotateLink, rotateLink);
    //********************************************************//
    
    //Timers and delays.
    //********************************************************//
    private Timer mobilityTimer = new Timer();
    private Timer rotateTimer = new Timer();
    private static final double DELAY_BEFORE_MOBILITY_TIMER = 5;
    private static final double DELAY_AFTER_MOBILITY_TIMER = 10;
    private static final double DELAY_BEFORE_ROTATE_TIMER = 0.5;
    private static final double DELAY_AFTER_ROTATE_TIMER = 0.5;
    private boolean isDone = false;
    private int currentState = 0;
    private int currentRotateState = 0;
    public boolean lowGear = false;
    Gyro gyro = new Gyro(RobotMap.AN_GYRO);
    //********************************************************//
    
    /**
     * This is the chassis constructor.
     */
    public Chassis() {
        drivePID.enable();
        steerPID.enable();
        rotatePID.enable();

        if (lowGear == false){
            drivePID.setOutputRange(-1.0, 1.0);
            steerPID.setOutputRange(-1.0, 1.0);
            rotatePID.setOutputRange(-1.0, 1.0);
        }
        else{
            drivePID.setOutputRange(-0.5, 0.5);
            steerPID.setOutputRange(-0.5, 0.5);
            rotatePID.setOutputRange(-0.7, 0.7);
        }
        
        drivePID.setInputRange(-1.0, 1.0);
        steerPID.setInputRange(-1.0, 1.0);
        rotatePID.setInputRange(-1.0, 1.0);
        
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        
        SmartDashboard.putBoolean("SmoothingStatus", isSmoothing);
        
        gyro.reset();
        gyro.setSensitivity(0.007);
        
    }
    
    /**
     * 
     */
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new MecanumDrive());
    }
    
    /**
     * This is the basis of all movement on the robot.
     * It includes the 'if' statement to switch between smoothing and non-smoothing.
     * Setpoints get set from their points.
     * @param magnitude Y movement.
     * @param direction X movement.
     * @param rotation Rotation movement.
     */
    public void mecanumDrive_Cartesian(double magnitude, double direction, double rotation){
        if (isSmoothing){
//            drivePID.setSetpoint(-magnitude*Math.abs(magnitude));
//            steerPID.setSetpoint(direction*Math.abs(direction));
//            rotatePID.setSetpoint(-rotation*Math.abs(rotation));
            //robotDrive.mecanumDrive_Cartesian(drivePID.get(), steerPID.get(), rotatePID.get(),90.0);
            //robotDrive.mecanumDrive_Cartesian(drivePID.get(), steerPID.get(), rotatePID.get(), gyro.getAngle()+90.0);
            robotDrive.mecanumDrive_Cartesian(magnitude, direction, rotation, gyro.getAngle()+90);
            SmartDashboard.putNumber("Gyro Angle", gyro.getAngle());
            SmartDashboard.putNumber("Pre-PID magnitude", magnitude);
            SmartDashboard.putNumber("Pre-PID direction", direction);
            SmartDashboard.putNumber("Pre-PID rotation", rotation);
            SmartDashboard.putNumber("Post-PID magnitude", drivePID.get());
            SmartDashboard.putNumber("Post-PID direction", steerPID.get());
            SmartDashboard.putNumber("Post-PID rotation", rotatePID.get());
        } 
        else{
            robotDrive.mecanumDrive_Cartesian(-magnitude*Math.abs(magnitude), direction*Math.abs(direction), -rotation*Math.abs(rotation), 90.0);
        }
        
    }
   
    /**
     * Enables and disables PID control (ie smoothing)
     * Note that this is completely not needed but Paul didn't know otherwise.
     */
    public void toggleSmoothing(){
        isSmoothing = !isSmoothing;
        if (isSmoothing){
            drivePID.enable();
            steerPID.enable();
            rotatePID.enable();
        }
        else{
            drivePID.disable();
            steerPID.disable();
            rotatePID.disable();
        }
        SmartDashboard.putBoolean("SmoothingStatus", isSmoothing);
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
                robotDrive.arcadeDrive(0, 1);
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
                mobilityTimer.start();
                
                currentState++;
                break;
            case 1:
                if (mobilityTimer.get()>=DELAY_BEFORE_MOBILITY_TIMER)
                {
                    
                    currentState++;
                }
                robotDrive.arcadeDrive(1, 0);
                break;
            case 2:
                if (mobilityTimer.get()>=DELAY_AFTER_MOBILITY_TIMER){
                    robotDrive.arcadeDrive(0,0);
                    currentState++;
                }      
                robotDrive.arcadeDrive(-1, 0);
                break;
            case 3:
                isDone = true;
                return true; 
        }
        return false;
    }
    
    public void switchGear(){
        if (lowGear == false){
            lowGear = true;
        }
        else{
            lowGear = false;
        }
    }
    
    /**
     * Stops the robot.
     */
    public void stopPlz(){
        robotDrive.arcadeDrive(0,0);
    }
    
    }
