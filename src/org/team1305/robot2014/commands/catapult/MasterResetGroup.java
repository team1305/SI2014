/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team1305.robot2014.commands.catapult;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author NNSRI
 */
public class MasterResetGroup extends CommandGroup {
    
    public MasterResetGroup() {
        addSequential(new MasterReset());
        addSequential(new CatapultLockNLoadGroup());
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        
    }
}
