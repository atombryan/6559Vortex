package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import ftc.backcode.OpModeBase;

/**
 * Created by union on 11/11/16.
 */


//@TeleOp(name = "testOp",group = "UserOP")
public class Tester extends OpModeBase {

    @Override
    public void init()
    {
        right1 = hardwareMap.dcMotor.get("rightFront");
        left1 = hardwareMap.dcMotor.get("leftFront");
        right2 = hardwareMap.dcMotor.get("rightBack");
        left2 = hardwareMap.dcMotor.get("leftBack");
        resetEncoders(true);
    }

    public void start() {}

    public void loop()
    {

        move(gamepad1.y ? 0.25 : 0,gamepad1.x ? 0.25 : 0 ,0);
        telemetry.addData("topLeft: ",left1.getCurrentPosition());
        telemetry.addData("bottomRight: ",right2.getCurrentPosition());
        telemetry.addData("topRight: ",right1.getCurrentPosition());
        telemetry.addData("bottomLeft: ",left2.getCurrentPosition());

        if (gamepad1.start)
        {
            resetEncoders(true);
        }

    }
}
