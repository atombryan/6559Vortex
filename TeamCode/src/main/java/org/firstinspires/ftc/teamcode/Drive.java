/*
Copyright (c) 2016 Robert Atkinson

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Robert Atkinson nor the names of his contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESSFOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import java.lang.Math;
import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.*;

/**
 * {@link Drive} illustrates various ways in which telemetry can be
 * transmitted from the robot controller to the driver station. The sample illustrates
 * numeric and text data, formatted output, and optimized evaluation of expensive-to-acquire
 * information. The telemetry {@link Telemetry#log() log} is illustrated by scrolling a poem
 * to the driver station.
 *
 * @see Telemetry
 */
@TeleOp(name = "simpleDrive",group = "UserOP")
public class Drive extends OpMode{

    //Initializes motors
    DcMotor left1;
    DcMotor right1;
    DcMotor combine;
    DcMotor left2;
    DcMotor right2;

    //Values used to calculate power
    double topLeft, topRight, bottomLeft, bottomRight, maxVector;

    public void init()
    {
        //matching our variables to the hardware
        right1 = hardwareMap.dcMotor.get("rightFront");
        left1 = hardwareMap.dcMotor.get("leftFront");
        right2 = hardwareMap.dcMotor.get("rightBack");
        left2 = hardwareMap.dcMotor.get("leftBack");
        combine = hardwareMap.dcMotor.get("combine");
    }
    


    public void loop()
    {
        //Each joystick alone gives the wheel a unique set of instructions
        //These equations add them all together
        topLeft = gamepad1.left_stick_x+gamepad1.left_stick_y+gamepad1.right_stick_x;
        topRight = -gamepad1.left_stick_x+gamepad1.left_stick_y+gamepad1.right_stick_x;
        bottomLeft = gamepad1.left_stick_x-gamepad1.left_stick_y+gamepad1.right_stick_x;
        bottomRight = -gamepad1.left_stick_x-gamepad1.left_stick_y+gamepad1.right_stick_x;

        //Find the largest absolute value
        maxVector = Math.max(Math.max(Math.abs(topLeft), Math.abs(topRight)),
                Math.max(Math.abs(bottomLeft), Math.abs(bottomRight)));


        //If the vector is being divided is less than 1, set it to just 1. Allow for micromanagement
        //Also makes sure we don't divide by zero
        maxVector = maxVector > 1 ? maxVector : 1;

        //Set power to values divided by the largest so numbers are in range and proportional
        left1.setPower(topLeft/maxVector);
        left2.setPower(topRight/maxVector);
        right1.setPower(bottomLeft/maxVector);
        right2.setPower(bottomRight/maxVector);
    }
    public static void motorTriggerBoolean (DcMotor combineMotor, boolean triggerBool)
    {


    }
}
