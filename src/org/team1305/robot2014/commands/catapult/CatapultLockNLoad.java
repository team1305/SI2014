/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team1305.robot2014.commands.catapult;

import edu.wpi.first.wpilibj.Timer;
import org.team1305.robot2014.commands.CommandBase;

/**
 *
 * @author Root 1
 */
public class CatapultLockNLoad extends CommandBase {
    
    private int currentState = 0;
    private Timer delayTimer = new Timer();
    private static final int DELAY_BEFORE_TIMER = 500;
    private boolean isDone = false;
    
    public CatapultLockNLoad() {
        // Use requires() here to declare subsystem dependencies
        requires(catapult);
        setInterruptible(false);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        switch (currentState){
            case 0:
                delayTimer.start();
                catapult.setTransmissionEngaged(true);
                currentState++;
                break;
            case 1:
                if (delayTimer.get()>=DELAY_BEFORE_TIMER)
                {
                    currentState++;
                }
                break;
            case 2:
                if (!catapult.winch()){
                currentState++;
                }
                break;
            case 3:
                isDone = true;
                break;
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
