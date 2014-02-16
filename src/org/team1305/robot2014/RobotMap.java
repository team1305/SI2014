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
   //Camera
    public static final String SMARTDASH_HOT_TARGET_CHECKS = "Hot Target Checks";
    public static final String SMARTDASH_HOT_TARGET_FINDS = "Hot Target Finds";
    public static final int MAX_AUTONOMOUS_HOT_TARGET_CHECKS = 3;
    public static final int SOL_CAMERA_LIGHT_RING = 4;
    
    //PWM channels
    //*Chassis
    public static final int PWM_DRIVE_LEFT_FRONT  = 1;
    public static final int PWM_DRIVE_LEFT_BACK   = 2;
    public static final int PWM_DRIVE_RIGHT_FRONT = 3;
    public static final int PWM_DRIVE_RIGHT_BACK  = 4;
    //*Catapult
    public static final int PWM_PULLBACK_RIGHT = 5; //Right pullback motor.
    public static final int PWM_PULLBACK_LEFT  = 6; //Left pullback motor
    //*Claw
    public static final int PWM_CLAW_LEFT  = 7;
    public static final int PWM_CLAW_RIGHT = 8;
    
    //Relays
    //*Pneumatic
    public static final int REL_COMPRESSOR = 1;
    //*Underglow
    public static final int REL_UNDERGLOW  = 2;
    
    //Solenoids
    //*Catapult
    public static final int SOL_LATCH = 1;  //Latches Catapult down for safety.
    
    public static final int SOL_GEAR  = 2; //Shifts Catapult gear transmission.
    //*Pneumatic

    //Digital I/O
    //*Chassis
    public static final int DIO_ENC_LEFTWHEEL_P1 = 1; //Encoder on the right drivewheel.
    public static final int DIO_ENC_LEFTTWHEEL_P2 = 2; //Encoder on the right drivewheel.
    public static final int DIO_ENC_RIGHTWHEEL_P1  = 3; //Encoder on the left drivewheel.
    public static final int DIO_ENC_RIGHTWHEEL_P2  = 4; //Encoder on the left drivewheel.
    public static final int DIO_ENC_LEFTWHEEL_BACK_P1 = 9; //Encoder on the right drivewheel.
    public static final int DIO_ENC_LEFTWHEEL_BACK_P2 = 10; //Encoder on the right drivewheel.
    public static final int DIO_ENC_RIGHTWHEEL_BACK_P1  = 11; //Encoder on the left drivewheel.
    public static final int DIO_ENC_RIGHTWHEEL_BACK_P2  = 12; //Encoder on the left drivewheel.
    //*Catapult
    public static final int DIO_LIMIT_CAT_BOTTOM = 5;
    
    public static final int DIO_PRESSURE_SENSOR  = 6;

    public static final int DIO_ENC_TOWERLEFT = 7;
    public static final int DIO_ENC_TOWERRIGHT = 8;


    //Analog Channels
    //*Catapult
    public static final int AN_PULLBACK_POT   = 4; //Potentiometer located on the pullback mechanism.
    //*Claw
    public static final int AN_CLAW_POT_LEFT  = 2;
    public static final int AN_CLAW_POT_RIGHT = 3;
    
    public static final int AN_GYRO = 1;
    
    
}
