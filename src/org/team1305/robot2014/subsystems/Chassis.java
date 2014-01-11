/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.team1305.robot2014.subsystems;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.team1305.robot2014.RobotMap;
import org.team1305.robot2014.commands.ArcadeDrive;

/**
 *
 * @author Jagg2
 */
public class Chassis extends Subsystem {
    
    // Drive Victor
    Victor leftDrive        = new Victor(RobotMap.PORT_LEFTMOTOR);
    Victor rightDrive       = new Victor(RobotMap.PORT_RIGHTMOTOR);
    RobotDrive robotDrive   = new RobotDrive(leftDrive, rightDrive);

    protected void initDefaultCommand() {
        setDefaultCommand(new ArcadeDrive());
    }
    
    public void arcadeDrive(double move, double rotate ){
    
        robotDrive.arcadeDrive (move, rotate);
    }
    
    public void Stop(){
    
        leftDrive.set(0);
        rightDrive.set(0);
}
}