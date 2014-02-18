/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team1305.robot2014.commands.autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team1305.robot2014.commands.camera.CameraActive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team1305.robot2014.commands.catapult.CatapultFire;
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
        if (DriverStation.getInstance().getLocation() == 2){
        addParallel(new CatapultLockNLoad());
        addSequential(new AutonomousRotate());
        addSequential(new CameraActive());
            if (hotCount >= 2){
                 // System.out.println("Detected high amount of Hot Goals");
                SmartDashboard.putString("HEY THIS WORKS", "BOO");
                addSequential(new CatapultFire());
                addSequential(new AutonomousMobility());
            }
            else{
                //System.out.println("No hot goal");
                SmartDashboard.putString("HEY THIS WORKS", "HEY");
                addSequential(new AutonomousMobility());
                addSequential(new CatapultFire());
            }
        
        }
        else{
          addParallel(new CameraActive());
          addParallel(new ClawOpen());
          SmartDashboard.putString("AUTO STATE", "Locking and Loading");
          addSequential(new CatapultLockNLoad());
          SmartDashboard.putString("AUTO STATE", "Closing claws");
          addSequential(new ClawClose());
          SmartDashboard.putString("AUTO STATE", "Moving");
          addSequential(new AutonomousMobility());
          SmartDashboard.putNumber("What I read", hotCount);
          SmartDashboard.putString("AUTO STATE", "Reading camera stuff");
          hotCount = SmartDashboard.getNumber("Hot Count");
          SmartDashboard.putNumber("What I read", hotCount);
          addSequential(new ClawOpen());
        if (hotCount >= 2){
           // System.out.println("Detected high amount of Hot Goals");
            SmartDashboard.putString("HEY THIS WORKS", "BOO");
            addSequential(new CatapultFire());
            
        }
        else{
            //System.out.println("No hot goal");
            SmartDashboard.putString("HEY THIS WORKS", "HEY");
            
            addSequential(new CatapultFire());
        }
        }
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.
        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
