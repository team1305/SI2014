/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team1305.robot2014.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.team1305.robot2014.RobotMap;
import org.team1305.robot2014.commands.pneumatic.Run;

/**
 * The Pneumatic class is simply a structure to manage the compressor. 
 * Once it is started, nothing should have to worry about it. A single call to
 * compressor.start() should be made at the first-initialization of the robot.
 * @author Paul Belanger
 */
public class Pneumatic extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private final Compressor compressor = new Compressor(RobotMap.DIO_PRESSURE_SENSOR,
                                                         RobotMap.REL_COMPRESSOR);
    

    public void initDefaultCommand() {
        //The compressor simply runs. Nothing else to worry about.
        setDefaultCommand(new Run());
    }
    public void start(){
        compressor.start();
    }
}
