package org.firstinspires.ftc.teamcode;

import android.util.TimeUtils;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import ftc.backcode.OpModeBase;
import ftc.backcode.Stage;
import java.lang.Object;
import java.util.Calendar;

/**
 * Created by union on 11/15/16.
 */

@Autonomous (name = "Autonomous", group = "UserOP")
public class LightOnlyAutonomous extends OpModeBase {

    public boolean isBlue = true;

    public void start()
    {

        OpModeBase.resetEncoders(true);
        //Enumerator Call - Complete After Competition
        for (Stage s : Stage.values())
        {
            s.execute();
        }

        //Stage 0 completed in initialization
        long time;
        int rgb0 = OpModeBase.colorFront.argb();
        int rgb1 = OpModeBase.colorMid.argb();
        int rgb2 = OpModeBase.colorBack.argb();
        int rgb3 = OpModeBase.colorBeacon.argb();
        int rgb3Red = OpModeBase.colorBeacon.red();
        int rgb3Blue = OpModeBase.colorBeacon.blue();
        boolean firstLight = false, secondLight = false;

        float angle = 45f;
        telemetry.addData("First angle set to", angle);
        float angle2 = -45f;
        telemetry.addData("Second angle set to", angle2);
        float givenPos = 10;
        telemetry.addData("Given Distance set to", givenPos);

        while(rgb1 < 150)
        {
            OpModeBase.driveAngle(angle,1);
        }
        while (true)
        {
            if (rgb0 < 150) OpModeBase.turn(-1);
            else OpModeBase.move(0,1,0,false);
            if (rgb3 > 100) break;
        }
        press(rgb3Blue, rgb3Red, rgb3);
        while (rgb1 > 150)
        {
            move (-1,0,0,false);
        }
        while (rgb1 < 150)
        {
            move (1,0,0,false);
        }
        while (true)
        {
            if (rgb0 < 150) OpModeBase.turn(-1);
            else OpModeBase.move(0,1,0,false);
            if (rgb3 > 100) break;
        }
        press(rgb3Blue, rgb3Red, rgb3);

        while(right1.getCurrentPosition() < givenPos)
        {
            driveAngle(angle2,1);
        }
    }

    public void press(int rgb3Blue, int rgb3Red, int rgb3)
    {
        //Assuming sensor is on the left
        if (rgb3Blue > rgb3Red)
        {
            if (isBlue)
            {
                //Press left
            }
            else
            {
                //Press right
            }
        }
        else
        {
            if (isBlue)
            {
                //Press right
            }
            else
            {
                //Press left
            }
        }
    }

    public void loop() {




    }


}
