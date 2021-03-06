/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team1305.robot2014.commands.underglow;

import edu.wpi.first.wpilibj.Timer;
import org.team1305.robot2014.commands.CommandBase;

/**
 *
 * @author Root 1
 */
public class FastBlink extends CommandBase {
    private final Timer timer=new Timer();
    private final double BLINK_PERIOD=0.50;
    public FastBlink() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        timer.start();
        underglow.on();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(timer.get()>= BLINK_PERIOD){
            timer.reset();
            underglow.toggle();
        }
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
