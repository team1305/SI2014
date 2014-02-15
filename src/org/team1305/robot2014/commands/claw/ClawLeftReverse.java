/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team1305.robot2014.commands.claw;

import org.team1305.robot2014.commands.CommandBase;

/**
 *
 * @author Kim
 */
public class ClawLeftReverse extends CommandBase {
    
    public ClawLeftReverse() {
        // Use requires() here to declare subsystem dependencies
        requires(claw);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        claw.clawLeftReverse();
        claw.getPot();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
