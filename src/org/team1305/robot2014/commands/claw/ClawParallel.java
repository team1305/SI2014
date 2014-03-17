/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.team1305.robot2014.commands.claw;

import edu.wpi.first.wpilibj.command.CommandGroup;
//import org.team1305.robot2014.commands.CommandBase.law;

/**
 *
 * @author Kim
 */
public class ClawParallel extends CommandGroup {
    
    public ClawParallel() {
        addSequential(this);
        //addParallel(claw.clawLeft);
    }
}
