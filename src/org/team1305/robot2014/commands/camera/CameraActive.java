/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team1305.robot2014.commands.camera;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team1305.robot2014.commands.CommandBase;
import org.team1305.robot2014.RobotMap;

/**
 *
 * @author Kim
 */
public class CameraActive extends CommandBase {
    private int hotTargetChecks;
    public CameraActive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(camera);
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        this.setTimeout(cameraTimeout);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        camera.analyzeImage();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        hotTargetChecks = (int) SmartDashboard.getNumber(RobotMap.SMARTDASH_HOT_TARGET_CHECKS, 0.0);
        return (hotTargetChecks > RobotMap.MAX_AUTONOMOUS_HOT_TARGET_CHECKS);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
