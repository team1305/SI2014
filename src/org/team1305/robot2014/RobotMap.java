package org.team1305.robot2014;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static final int leftMotor = 1;
    // public static final int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static final int rangefinderPort = 1;
    // public static final int rangefinderModule = 1;
    
    //PWM channels
    public static final int PORT_LEFTMOTOR = 1;
    public static final int PORT_RIGHTMOTOR = 2;
    
    // Joystick Button Mapping
    public static final int JOY_ARCADE_DRIVE_MOVE_AXIS = 2;
    public static final int JOY_ARCADE_DRIVE_TURN_AXIS = 3;
}
