/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team1305.robot2014.commands.catapult;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team1305.robot2014.commands.CommandBase;

/**
 *
 * @author Root 1
 */
public class CatapultLockNLoad extends CommandBase {
    
    private int currentState; // = 0;
    private Timer delayTimer = new Timer();
    private Timer reverseTimer = new Timer();
    private static final double DELAY_BEFORE_TIMER = 0.50;
    private static final double DELAY_DURING_REVERSE = 0.00;
    private boolean isDone = false;
    public boolean shooterLoaded = false;
    //boolean hadToWind = false;
    
    public CatapultLockNLoad() {
        // Use requires() here to declare subsystem dependencies
        requires(catapult);
        //setInterruptible(false);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        if (catapult.GetBottomLimitSWState())
            currentState = 4;
        else
            currentState = 0;
//        //kshadToWind = false;
    }

    // Called repeatedly when this Command is scheduled to run
    /**
     * Group which controls the winch loading.
     * Sets gearbox in enabled position, then starts the winch motor [if it isn't already at the bottom].
     */
    protected void execute() {
        
        switch (currentState){
            case 0:
                //Starts delayTimer, engages transmission.
                isDone = false;
                delayTimer.start();
                SmartDashboard.putString("Winch", "Engaging");
                catapult.unpullFiringPin();
                catapult.set(1);
                currentState++;
                break;
            case 1:
                if (delayTimer.get()>=DELAY_BEFORE_TIMER)
                {
                    //enables winch if it isn't already loaded.
                    catapult.WinchAtLimit();
                    delayTimer.stop();
                    currentState++;
                }
                break;
            case 2:
                if (catapult.WinchAtLimit()==true){
                    SmartDashboard.putString("Winch", "Should be raising");
                    reverseTimer.start();
                    catapult.set(-1);
                    currentState++;
                }
                break;
            case 3:
                if (reverseTimer.get()>=DELAY_DURING_REVERSE){
                    catapult.set(0);
                    reverseTimer.stop();
                    currentState++;
                }
                break;
            case 4:
                isDone = true;
                SmartDashboard.putString("Winch", "Finished raising");
                break;
        }
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(isDone == true){
           isDone = false;
           delayTimer.stop();
           delayTimer.reset();
           reverseTimer.stop();
           reverseTimer.reset();
           currentState = 0;
           SmartDashboard.putString("Winch", "Exiting Group");
           return true; 
        }
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
