/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team1305.robot2014.commands.catapult;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.team1305.robot2014.commands.claw.ClawOpen;

/**
 *
 * @author Root 1
 */
public class CatapultLockNLoadGroup extends CommandGroup {
    
    public CatapultLockNLoadGroup() {
        addSequential(new ClawOpen());
        addSequential(new CatapultLockNLoad());
    }
}
