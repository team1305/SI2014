/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team1305.robot2014.commands.claw;

import edu.wpi.first.wpilibj.Timer;
import org.team1305.robot2014.commands.CommandBase;

/**
 *
 * @author Root 1
 */
public class ClawOpen extends CommandBase {
    
    private Timer delayTimer = new Timer();
    private static final double DELAY_BEFORE_TIMER = 0.5;
    
    public ClawOpen() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(claw);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        delayTimer.start();
        claw.open();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return delayTimer.get() >= DELAY_BEFORE_TIMER;
    }

    // Called once after isFinished returns true
    protected void end() {
        claw.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        claw.stop();
    }
}
