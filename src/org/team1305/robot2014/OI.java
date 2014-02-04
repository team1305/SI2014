
package org.team1305.robot2014;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.DigitalIOButton;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.team1305.robot2014.commands.catapult.CatapultFire;
import org.team1305.robot2014.commands.catapult.CatapultLock;
import org.team1305.robot2014.commands.catapult.CatapultLockNLoad;
import org.team1305.robot2014.commands.catapult.CatapultUnlock;
import org.team1305.robot2014.commands.chassis.ToggleSmoothing;

/**
 * Controls all operator input into the robot.
 */
public class OI {    
    //Axis defninitions
    //***THE CONTROLLER MUST BE IN XINPUT MODE***
    //***MAKE SURE THE SWITCH ON THE BACK OF THE CONTROLLER IS SET TO 'X'***
    //TODO: check these axis numbers
    public static final int AXIS_XL = 1;
    public static final int AXIS_YL = 2;
    public static final int AXIS_XR = 3;
    public static final int AXIS_YR = 4;
    public static final int AXIS_TRIG = 5;
    
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
    
    private final Joystick driveStick = new Joystick(1);
    Button driveA = new JoystickButton(driveStick, BTN_A);
    Button driveB = new JoystickButton(driveStick, BTN_B);
    Button driveX = new JoystickButton(driveStick, BTN_X);
    Button driveY = new JoystickButton(driveStick, BTN_Y);
    Button driveStart = new JoystickButton(driveStick, BTN_START);
    Button driveBack = new JoystickButton(driveStick, BTN_BACK);
    Button driveLB = new JoystickButton(driveStick, BTN_LB);
    Button driveRB = new JoystickButton(driveStick, BTN_RB);
    Button driveLClick = new JoystickButton(driveStick, BTN_LCLICK);
    Button driveRClick = new JoystickButton(driveStick, BTN_RCLICK);
        
    public OI(){
        driveStart.whenPressed(new ToggleSmoothing());
        driveX.whenPressed(new CatapultLockNLoad());
        driveY.whenPressed(new CatapultUnlock());
        driveB.whenPressed(new CatapultLock());
        driveA.whenPressed(new CatapultFire());
    }
    public double getDriveXL(){
        return driveStick.getRawAxis(AXIS_XL);
    }
    public double getDriveYL(){
        return driveStick.getRawAxis(AXIS_YL);
    }
    public double getDriveXR(){
        return driveStick.getRawAxis(AXIS_XR);
    }
    public double getDriveYR(){
        return driveStick.getRawAxis(AXIS_YR);
    }
    public double getDriveTRIG(){
        return driveStick.getRawAxis(AXIS_TRIG);
    }
}

