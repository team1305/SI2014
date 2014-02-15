/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team1305.robot2014.commands.pneumatic;

import org.team1305.robot2014.commands.CommandBase;

/**
 *
 * @author Paul Belanger
 */
public class Run extends CommandBase {
    
    public Run() {
        requires(pneumatic);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        pneumatic.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        pneumatic.update();
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
