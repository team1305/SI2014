package org.team1305.robot2014;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 * 
 * A consistent naming scheme should be followed whenever declaring constants here.
 * All PWMs should be prefixed with PWM_.
 * All Relays should be prefixed with REL_.
 * All digital IO should be prefixed with DIO_. 
 * All analog channels should be prefixed with AN_.
 * All solenoids should be prefixed with SOL_.
 */
public class RobotMap {
    
    //PWM channels
    //*Chassis
    public static final int PWM_DRIVE_LEFT_FRONT  = 1;
    public static final int PWM_DRIVE_LEFT_BACK   = 2;
    public static final int PWM_DRIVE_RIGHT_FRONT = 3;
    public static final int PWM_DRIVE_RIGHT_BACK  = 4;
    //*Catapult
    public static final int PWM_PULLBACK_RIGHT = 5; //Right pullback motor.
    //*Claw
    public static final int PWM_CLAW_LEFT  = 7;
    public static final int PWM_CLAW_RIGHT = 8;
    
    //Relays
    //*Pneumatic
    public static final int REL_COMPRESSOR = 1;
    
    //Solenoids
    //*Catapult
    public static final int SOL_LATCH = 1;  //Latches Catapult down for safety.
    public static final int SOL_CLAW = 7;
    //*Pneumatic

    //Digital I/O
    //*Catapult
    public static final int DIO_LIMIT_CAT_BOTTOM = 5;
    
    public static final int DIO_PRESSURE_SENSOR  = 6;
    
}
