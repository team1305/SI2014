/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team1305.robot2014.commands.catapult;

import org.team1305.robot2014.commands.CommandBase;

/**
 *CURRENTLY UNUSED
 * @author Root 1
 */
public class CatapultFire extends CommandBase {
    
    boolean engaged;
    
    public CatapultFire() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(catapult);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        System.out.println("In CatapultFire");
        //if (oi.RTrigAndBumpPressed() & claw.ClawsAreClearToFire())
       // if (claw.ClawsAwayFromCatapult())
            catapult.PullFiringPin(engaged);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
