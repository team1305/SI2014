/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team1305.robot2014.commands.autonomous;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team1305.robot2014.Robot1305;
import org.team1305.robot2014.commands.Wait;
import org.team1305.robot2014.commands.catapult.CatapultLockNLoad;
import org.team1305.robot2014.commands.catapult.CatapultLockNLoadGroup;
import org.team1305.robot2014.commands.claw.ClawClose;
import org.team1305.robot2014.commands.claw.ClawIntake;
import org.team1305.robot2014.commands.claw.ClawOpen;

/**
 *
 * @author Root 1
 */
public class AutonomousMasterGroup extends CommandGroup {
    double hotCount;
    
    
    public AutonomousMasterGroup() {
//          addSequential(new ClawClose());
//          SmartDashboard.putString("AUTO STATE", "Locking and Loading");
//          addSequential(new CatapultLockNLoadGroup());
//          SmartDashboard.putString("AUTO STATE", "Closing claws");
//          //Closes claws to clamp ball and prevent it falling off.
//          SmartDashboard.putString("AUTO STATE", "Moving");
//          //Moves 
//          addSequential(new AutonomousMobility());
//          addSequential(new Wait());
//          addSequential(new ClawOpen());
//          addSequential(new AutonomousMobility());
//          SmartDashboard.putString("AUTO STATE", "Reading camera stuff");
//          //addSequential(new AutonomousFire());
//          addSequential(new ClawIntake());
          //addSequential(new Wait());
        }
    }