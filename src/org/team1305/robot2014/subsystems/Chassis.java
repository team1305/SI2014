/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team1305.robot2014.subsystems;

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
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private boolean isSmoothing = true;
    private final Talon tLeftFront = new Talon(RobotMap.PWM_DRIVE_LEFT_FRONT);
    private final Talon tLeftBack = new Talon(RobotMap.PWM_DRIVE_LEFT_BACK);
    private final Talon tRightFront = new Talon(RobotMap.PWM_DRIVE_RIGHT_FRONT);
    private final Talon tRightBack = new Talon(RobotMap.PWM_DRIVE_RIGHT_BACK);
    private final RobotDrive robotDrive = new RobotDrive(tLeftFront, tLeftBack, tRightFront, tRightBack);
    
    private final ManualPIDLink driveLink = new ManualPIDLink();
    private final ManualPIDLink steerLink = new ManualPIDLink();
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
    
    private Timer delayTimer = new Timer();
    private static final double DELAY_BEFORE_TIMER = 0.5;
    private boolean isDone = false;
    private int currentState = 0;
    /**
     * This is da chassis constructor thingy yo.
     */
    public Chassis() {
        drivePID.enable();
        steerPID.enable();
        rotatePID.enable();
        
//        drivePID.setPercentTolerance(10.0);
//        steerPID.setPercentTolerance(10.0);
//        rotatePID.setPercentTolerance(10.0);
//        
        drivePID.setOutputRange(-1.0, 1.0);
        steerPID.setOutputRange(-1.0, 1.0);
        rotatePID.setOutputRange(-1.0, 1.0);
        
        drivePID.setInputRange(-1.0, 1.0);
        steerPID.setInputRange(-1.0, 1.0);
        rotatePID.setInputRange(-1.0, 1.0);
        
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        
        SmartDashboard.putBoolean("SmoothingStatus", isSmoothing);
    }
    
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
            drivePID.setSetpoint(-magnitude*Math.abs(magnitude));
            steerPID.setSetpoint(direction*Math.abs(direction));
            rotatePID.setSetpoint(-rotation*Math.abs(rotation));
            robotDrive.mecanumDrive_Cartesian(drivePID.get(), steerPID.get(), rotatePID.get(), 90.0);
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
     * Plz no y u do dis pls stahp.
     */
    
    public void mobilityMovement(){
        switch (currentState){
            case 0:
                delayTimer.start();
                robotDrive.arcadeDrive(1, 0);
                currentState++;
                break;
            case 1:
                if (delayTimer.get()>=DELAY_BEFORE_TIMER)
                {
                    robotDrive.arcadeDrive(-1, 0);
                    currentState++;
                }
                break;
            case 2:
                robotDrive.arcadeDrive(0,0);
                currentState++;
                
                break;
            case 3:
                isDone = true;
                break;
        }
        
    }
    
    public void stopPlz(){
        robotDrive.arcadeDrive(0,0);
    }
    
    
    
    }
