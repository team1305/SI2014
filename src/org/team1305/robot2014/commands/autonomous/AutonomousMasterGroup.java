/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team1305.robot2014.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team1305.robot2014.commands.catapult.CatapultLockNLoad;
import org.team1305.robot2014.commands.claw.ClawClose;
import org.team1305.robot2014.commands.claw.ClawOpen;

/**
 *
 * @author Root 1
 */
public class AutonomousMasterGroup extends CommandGroup {
    double hotCount;
    
    public AutonomousMasterGroup() {
          SmartDashboard.putString("AUTO STATE", "Locking and Loading");
          //addSequential(new CatapultLockNLoad());
          SmartDashboard.putString("AUTO STATE", "Closing claws");
          //Closes claws to clamp ball and prevent it falling off.
          //addSequential(new ClawClose());
          SmartDashboard.putString("AUTO STATE", "Moving");
          //Moves 
          addSequential(new AutonomousMobility());
          SmartDashboard.putString("AUTO STATE", "Reading camera stuff");
          //NOT NEEDED
          //addSequential(new ClawOpen());
          //addSequential(new AutonomousFire());
        
        }
    }

