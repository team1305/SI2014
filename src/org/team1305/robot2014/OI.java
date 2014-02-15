
package org.team1305.robot2014;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.DigitalIOButton;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team1305.robot2014.commands.camera.CameraActive;
import org.team1305.robot2014.commands.catapult.CatapultFire;
import org.team1305.robot2014.commands.catapult.CatapultLock;
import org.team1305.robot2014.commands.catapult.CatapultLockNLoad;
import org.team1305.robot2014.commands.catapult.CatapultStop;
import org.team1305.robot2014.commands.catapult.CatapultUnlock;
import org.team1305.robot2014.commands.chassis.ToggleSmoothing;
import org.team1305.robot2014.commands.claw.Close;
import org.team1305.robot2014.commands.claw.Open;
import org.team1305.robot2014.commands.claw.Park;

/**
 * Controls all operator input into the robot.
 */
public class OI {    
    //Axis defninitions
    //***THE CONTROLLER MUST BE IN XINPUT MODE***
    //***MAKE SURE THE SWITCH ON THE BACK OF THE CONTROLLER IS SET TO 'X'***
    //Axis' for all controller input.
    public static final int AXIS_XL = 1;
    public static final int AXIS_YL = 2;
    public static final int AXIS_XR = 4;
    public static final int AXIS_YR = 5;
    public static final int AXIS_TRIG = 3;
    
    //Buttons on the controller for input.
    public static final int BTN_A = 1;
    public static final int BTN_B = 2;
    public static final int BTN_X = 3;
    public static final int BTN_Y = 4;
    public static final int BTN_START = 8;
    public static final int BTN_BACK =7;
    public static final int BTN_LB = 5; //right and left bumpers
    public static final int BTN_RB = 6;
    public static final int BTN_LCLICK = 9;
    public static final int BTN_RCLICK = 10;
    public static final int BTN_LTrigger = 11;
    
    //All keybindings for X-MODE
    private final Joystick driveStick = new Joystick(1);
    Button driveA = new JoystickButton(driveStick, BTN_A);
    Button driveB = new JoystickButton(driveStick, BTN_B);
    Button driveX = new JoystickButton(driveStick, BTN_X);
    Button driveY = new JoystickButton(driveStick, BTN_Y);
    Button driveStart = new JoystickButton(driveStick, BTN_START);
    Button driveAnalyze = new JoystickButton(driveStick, BTN_BACK);
    Button driveLTrigger = new JoystickButton(driveStick, BTN_LB);
    Button driveRB = new JoystickButton(driveStick, BTN_RB);
    Button driveLB = new JoystickButton(driveStick, BTN_LB);
    Button driveLClick = new JoystickButton(driveStick, BTN_LCLICK);
    Button driveRClick = new JoystickButton(driveStick, BTN_RCLICK);
        
    public OI(){
        driveStart.whenPressed(new ToggleSmoothing());
        driveAnalyze.whenPressed(new CameraActive());
        driveLTrigger.whenPressed(new CatapultStop());
        driveX.whenPressed(new CatapultLockNLoad());
        driveY.whenPressed(new CatapultUnlock());
        driveB.whenPressed(new CatapultLock());
        driveA.whenPressed(new CatapultFire());
        driveRB.whenPressed(new Open());
        driveLClick.whenPressed(new Close());
        driveLB.whenPressed(new Park());
    }
    public double getDriveXL(){     
        SmartDashboard.putNumber("XL", driveStick.getRawAxis(AXIS_XL));
        return driveStick.getRawAxis(AXIS_XL);
    }
    public double getDriveYL(){
        SmartDashboard.putNumber("YL", driveStick.getRawAxis(AXIS_YL));
        return driveStick.getRawAxis(AXIS_YL);
    }
    public double getDriveXR(){
        SmartDashboard.putNumber("XR", driveStick.getRawAxis(AXIS_XR));
        return driveStick.getRawAxis(AXIS_XR);
    }
    public double getDriveYR(){
        SmartDashboard.putNumber("YR", driveStick.getRawAxis(AXIS_YR));
        return driveStick.getRawAxis(AXIS_YR);
    }
    public double getDriveTRIG(){
       SmartDashboard.putNumber("TRIG", driveStick.getRawAxis(AXIS_TRIG));

        return driveStick.getRawAxis(AXIS_TRIG);
    }       

}

