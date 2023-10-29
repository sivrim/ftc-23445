/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.List;

@TeleOp(name = "Teleop", group = "Furious Frog")
//@Disabled
public class OpModeTeleOp extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // Make sure your ID's match your configuration

        List<HardwareMap.DeviceMapping<? extends HardwareDevice>> allDeviceMappings = hardwareMap.allDeviceMappings;

        allDeviceMappings.forEach(d -> {
            System.out.println(d.getDeviceTypeClass().getCanonicalName());
        });

        DcMotor armMotor = hardwareMap.dcMotor.get("armMotor");
        MacanumWheels wheels = new MacanumWheels(hardwareMap);
        Servo clawServo = hardwareMap.servo.get("clawServo");
        DcMotor armMotor2 = null;
        if (hardwareMap.dcMotor.contains("armMotor2")) {
            armMotor2 = hardwareMap.dcMotor.get("armMotor2");
        }
        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double chassisY = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
            System.out.println("gamepad1.left_stick_y is " + chassisY);
            double chassisX = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            System.out.println("gamepad1.left_stick_x is " + chassisX);
            double chassisTurn = gamepad1.right_stick_x;
            System.out.println("chassisTurn is " + chassisTurn);
            wheels.move(chassisX, chassisY, chassisTurn);

            boolean dpadUp = gamepad2.dpad_up;
            boolean dpadDown = gamepad2.dpad_down;

            System.out.println("dpadUp is " + dpadUp);
            System.out.println("dpadDown is " + dpadDown);

            if (dpadUp) {
                clawServo.setPosition(1);
            } else if (dpadDown) {
                clawServo.setPosition(-1);
            }


            double armY = gamepad2.left_stick_y;
            double powerArmY = armY * .6;
            armMotor.setPower(powerArmY);
            System.out.println("armMotor is " + armMotor);

            if (armMotor2 != null) {
                double armY2 = gamepad2.right_stick_y;
                double powerArmY2 = armY2 * .8;
                if (powerArmY2 > 0) {
                    powerArmY2 = powerArmY2 * .5;
                }
                armMotor2.setPower(powerArmY2);

            }


        }
    }
}
