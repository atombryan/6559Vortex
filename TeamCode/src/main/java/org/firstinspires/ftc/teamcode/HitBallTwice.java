package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import java.util.Timer;
import java.util.TimerTask;

import ftc.backcode.OpModeBase;

/**
 * Created by bryanperkins on 12/10/16.
 */
@Autonomous (name = "AutoAndBallTwice", group = "UserOP")
public class HitBallTwice extends OpModeBase {
    public static TimerTask shoot, shootPause, shootAgain,stopAll, ramBall, stopAllAgain;
    public static Timer time;

    public void init() {
        super.init();
        time = new Timer();
        shoot = new TimerTask() {
            @Override
            public void run() {
                move(0, 0, 0, false);
                catapult.setPower(-1);
            }
        };
        shootPause = new TimerTask() {
            @Override
            public void run() {
                move(0, 0, 0, false);
                catapult.setPower(0);
            }
        };
        shootAgain = new TimerTask() {
            @Override
            public void run() {
                move(0, 0, 0, false);
                catapult.setPower(-1);
            }
        };
        stopAll = new TimerTask() {
            @Override
            public void run() {
                catapult.setPower(0);
                move(0, 0, 0, false);
            }
        };
        ramBall = new TimerTask() {
            @Override
            public void run() {
                move(-1, 0, 0, false);
            }
        };
        stopAllAgain = new TimerTask() {
            @Override
            public void run() {
                catapult.setPower(0);
                move(0, 0, 0, false);
            }
        };
    }
    public void start()
    {
        move(-1,0,0,false);
        time.schedule(shoot, 1000);
        time.schedule(shootPause, 2000);
        time.schedule(shootAgain,2500);
        time.schedule(stopAll,3500);
        time.schedule(ramBall,4500);
        time.schedule(stopAllAgain, 7500);
    }
    public void loop()
    {

    }

}