package org.team1305.robot2014;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    
    //PWM channels
    //*Chassis
    public static final int PWM_DRIVE_LEFT_FRONT = 1;
    public static final int PWM_DRIVE_LEFT_BACK = 2;
    public static final int PWM_DRIVE_RIGHT_FRONT = 3;
    public static final int PWM_DRIVE_RIGHT_BACK = 4;
    //*Catapult
    public static final int PWM_PULLBACK_RIGHT = 5; //Right pullback motor.
    public static final int PWM_PULLBACK_LEFT = 6; //Left pullback motor.
    
    //Solenoids
    //*Catapult
    public static final int SOL_LATCH = 1;  //Latches Catapult down for safety.
    public static final int SOL_GEAR = 2; //Shifts Catapult gear transmission.
    
    //Encoders
    //*Chassis
    public static final int DIO_ENC_RIGHTWHEEL_P1 = 1; //Encoder on the right drivewheel.
    public static final int DIO_ENC_RIGHTWHEEL_P2 = 2; //Encoder on the right drivewheel.
    public static final int DIO_ENC_LEFTWHEEL_P1 = 3; //Encoder on the left drivewheel.
    public static final int DIO_ENC_LEFTWHEEL_P2 = 4; //Encoder on the left drivewheel.
    
    //Limit Switch
    //*Catapult
    public static final int DIO_LIMIT_CAT_BOTTOM = 1;
    
    //Analog Channels
    //*Catapult
    public static final int AN_PULLBACK_POT = 1; //Potentiometer located on the pullback mechanism.
    
    
    
}
