package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import ftc.backcode.OpModeBase;
import ftc.backcode.Stage;

/**
 * Created by union on 11/15/16.
 */

@Autonomous (name = "Autonomous", group = "UserOP")
public class MainAutonomous extends OpModeBase {

    public void start()
    {
        //Enumerator Call - Complete After Competition
        for (Stage s : Stage.values())
        {
            s.execute();
        }

        //Stage 0 completed in initialization


        int rgb0 = OpModeBase.colorFront.argb();
        int rgb1 = OpModeBase.colorMid.argb();
        int rgb2 = OpModeBase.colorBack.argb();
        int rgb3Red = OpModeBase.colorBeacon.red();
        int rgb3Blue = OpModeBase.colorBeacon.blue();

        //set to needed distance for the beacon
        float dist = 0;

        //Guarantee that this is how you get ultrasonic values
        float disLeft = 0; //(float) OpModeBase.usLeft.getUltrasonicLevel();
        float disRight = 0; //(float) OpModeBase.usRight.getUltrasonicLevel();

        //Change this angle depending on starting location on field
        float angle1 = 45f;

        //Drive to line until center sensor is aligned
        while (true)
        {
            driveAngle(45, 0.75f);
            if (rgb1 > 175)
            {
                break;
            }
        }

        //turn until parallel with wall
        while (true)
        {
            if (disLeft > disRight + 1)
            {
                turn(1);
            }
            else if (disLeft < disRight - 1)
            {
                turn(-1);
            }
            else if (disLeft < disRight - 5 && disLeft > disRight - 5)
            {
                break;
            }

        }

        //Move to the beacon until distance is dist
        while (true)
        {


            if (rgb0 > 175 && rgb1 > 175 && rgb2 > 175)
            {
                while (disLeft > dist)
                {
                    move(1, 0, 0);
                }
                break;
            }
            //Move to guarantee that robot is alligned with line

        }
        while (true)
        {
            if (rgb3Red > rgb3Blue)
            {
                //sensor is on left side
                //press accordingly, if red, press left. If blue, press right (two opModes)
                telemetry.addData("press left", "");
            }
            else
            {
                //press accordingly, if red, press right. If blue, press left (two opModes)
                telemetry.addData("press right", "");
            }
            while (rgb2 > 175)
            {
                move (-1, 0, 0);
            }
            while (rgb0 > 175 && rgb1 > 175)
            {
                move (0, -1, 0);
            }
            break;
        }

        while (true)
        {
            while (rgb0 < 175 && rgb1 < 175)
            {
                move (0, -1, 0);
            }
            while (rgb2 < 175)
            {
                move (1, 0, 0);
            }
            break;
        }

        while (true)
        {
            if (rgb0 > 175 && rgb1 > 175 && rgb2 > 175)
            {
                while (disLeft > dist)
                {
                    move(1, 0, 0);
                }
                break;
            }
            //Move to guarantee that robot is aligned with line
        }

        while (true)
        {
            if (rgb3Red > rgb3Blue)
            {
                //sensor is on left side
                //press accordingly, if red, press left. If blue, press right (two opModes)
            }
            else
            {
                //press accordingly, if red, press right. If blue, press left (two opModes)
            }

            //Figure out this distance value
            float distance = 0;
            //Figure out the angle of driving backwards
            float angle = 0;

            resetEncoders(true);
            int encoderVal = 0;
            while (encoderVal < distance)
            {
                driveAngle(angle, 1);
                encoderVal = OpModeBase.right1.getCurrentPosition();
            }
            break;
        }
    }

    public void loop() {




    }


}
