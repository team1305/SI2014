
package org.team1305.robot2014;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.DigitalIOButton;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.team1305.robot2014.commands.chassis.ToggleSmoothing;

/**
 * Controls all operator input into the robot.
 */
public class OI {    
    public static final int AXIS_XL = 1;
    public static final int AXIS_YL = 2;
    public static final int AXIS_XR = 3;
    public static final int AXIS_YR = 4;
    public static final int AXIS_TRIG = 5;
    
    private final Joystick driveStick = new Joystick(1);
    Button driveStart = new JoystickButton(driveStick, 8);
    
    public OI(){
        driveStart.whenPressed(new ToggleSmoothing());
    }
    

    //// CREATING BUTTON
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // Another type of button you can create is a DigitalIOButton, which is
    // a button or switch hooked up to the cypress module. These are useful if
    // you want to build a customized operator interface.
    // Button button = new DigitalIOButton(1);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    
    
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

