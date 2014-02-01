/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team1305.robot2014.subsystems;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.team1305.robot2014.ManualPIDLink;
import org.team1305.robot2014.RobotMap;
import org.team1305.robot2014.commands.chassis.MecanumDrive;

/**
 *
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
    
    private ManualPIDLink driveLink = new ManualPIDLink();
    private ManualPIDLink steerLink = new ManualPIDLink();
    private ManualPIDLink rotateLink = new ManualPIDLink();
    
    private final double DRIVE_P = 0.0;
    private final double DRIVE_I = 0.4;
    private final double DRIVE_D = -0.1;
    
    private final double STEER_P = 0.0;
    private final double STEER_I = 0.4;
    private final double STEER_D = -0.1;
    
    private final double ROTATE_P = 0.0;
    private final double ROTATE_I = 0.4;
    private final double ROTATE_D = -0.1;
    
    private final PIDController drivePID = new PIDController(DRIVE_P, DRIVE_I, DRIVE_D, driveLink, driveLink);
    private final PIDController steerPID = new PIDController(STEER_P, STEER_I, STEER_D, steerLink, steerLink);
    private final PIDController rotatePID = new PIDController(ROTATE_P, ROTATE_I, ROTATE_D, rotateLink, rotateLink);
    
    public Chassis() {
        drivePID.enable();
        steerPID.enable();
        rotatePID.enable();
        
        drivePID.setOutputRange(-1.0, 1.0);
        steerPID.setOutputRange(-1.0, 1.0);
        rotatePID.setOutputRange(-1.0, 1.0);
        
        drivePID.setInputRange(-1.0, 1.0);
        steerPID.setInputRange(-1.0, 1.0);
        rotatePID.setInputRange(-1.0, 1.0);
        
        
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new MecanumDrive());
      
    }
    public void mecanumDrive_Polar(double magnitude, double direction, double rotation){
        if (isSmoothing){
            drivePID.setSetpoint(magnitude);
            steerPID.setSetpoint(direction);
            rotatePID.setSetpoint(rotation);
            robotDrive.mecanumDrive_Polar(drivePID.get(), steerPID.get(), rotatePID.get());
            
        }
        else{
            robotDrive.mecanumDrive_Polar(magnitude, direction, rotation);
        }
        
    }
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
    }
    public void stopPlz(){
        robotDrive.arcadeDrive(0,0);
    }
    
    
    
    }
