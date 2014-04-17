/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team1305.robot2014.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author NNSRI
 */
public class Wait extends CommandBase {
    private Timer t = new Timer();
    private static final double DELAY_BEFORE_TIMER = 1.0;
    public Wait() {
        // Use requires() here to declare subsystem dependencies
        requires(chassis);
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        t.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        SmartDashboard.putNumber("DelayTimer", t.get());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (t.get() >= DELAY_BEFORE_TIMER);
    
    }

    // Called once after isFinished returns true
    protected void end() {
        t.stop();
        t.reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
