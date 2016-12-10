package ftc.backcode;

import android.support.annotation.ColorRes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;

/**
 * Created by union on 11/8/16.
 */

public abstract class OpModeBase extends OpMode {

    public static DcMotor left1;
    public static DcMotor right1;
    public static DcMotor combine;
    public static DcMotor left2;
    public static DcMotor right2;
    public static DcMotor catapult;
    public static ColorSensor colorBack;
    public static ColorSensor colorMid;
    public static ColorSensor colorFront;
    public static ColorSensor colorBeacon;
    public static UltrasonicSensor usLeft;
    public static UltrasonicSensor usRight;

    public void init()
    {
        //Motors
        right1 = hardwareMap.dcMotor.get("rightFront");
        left1 = hardwareMap.dcMotor.get("leftFront");
        right2 = hardwareMap.dcMotor.get("rightBack");
        left2 = hardwareMap.dcMotor.get("leftBack");
        combine = hardwareMap.dcMotor.get("combine");
        catapult = hardwareMap.dcMotor.get("Catapult");

        //Color Sensors
        colorBack = hardwareMap.colorSensor.get("colorBack");
        colorMid = hardwareMap.colorSensor.get("colorMid");
        colorFront = hardwareMap.colorSensor.get("colorFront");
        colorBeacon = hardwareMap.colorSensor.get("colorBeacon");

        //Ultrasonic Sensors
        usLeft = hardwareMap.ultrasonicSensor.get("usLeft");
        usRight = hardwareMap.ultrasonicSensor.get("usRight");


    }

    public abstract void loop();
    public abstract void start();

    private static double _topLeft, _topRight, _bottomLeft, _bottomRight, _maxVector;

    public static void move(double leftX, double leftY, double rightX){
        //Each joystick alone gives the wheel a unique set of instructions
        //These equations add them all together
        _topLeft = leftX+leftY+rightX;
        _topRight = -leftX+leftY+rightX;
        _bottomLeft = leftX-leftY+rightX;
        _bottomRight = -leftX-leftY+rightX;

        //Find the largest absolute value
        _maxVector = Math.max(Math.max(Math.abs(_topLeft), Math.abs(_topRight)),
                Math.max(Math.abs(_bottomLeft), Math.abs(_bottomRight)));

        //If the vector is being divided is less than 1, set it to just 1. Allow for micromanagement
        //Also makes sure we don't divide by zero
        _maxVector = _maxVector > 1 ? _maxVector : 1;

        //Set power to values divided by the largest so numbers are in range and proportional
        left1.setPower(_topLeft/_maxVector);
        left2.setPower(_topRight/_maxVector);
        right1.setPower(_bottomLeft/_maxVector);
        right2.setPower(_bottomRight/_maxVector);

    }
    public static void turn(double turnSpeed){
        move(0,0,turnSpeed);
    }

    public static void resetEncoders(boolean useEncoders)
    {
        left1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        if (!useEncoders) {
            left1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            left2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            right1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            right2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
        else
        {
            left1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            left2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            right1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            right2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }
}
