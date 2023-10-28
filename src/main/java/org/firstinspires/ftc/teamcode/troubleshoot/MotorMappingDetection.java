package org.firstinspires.ftc.teamcode.troubleshoot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.MacanumWheels;

@Autonomous(name="MotorMappingDetection", group="Furious Frog")
@Disabled
public class MotorMappingDetection extends LinearOpMode {
    /* Declare OpMode members. */
    DcMotor armMotor = null;
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {

        MacanumWheels wheels = new MacanumWheels(hardwareMap);
        DcMotor armMotor = hardwareMap.dcMotor.get("armMotor");

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        runtime.reset();

        moveOneMotor(wheels, wheels.frontLeftMotor, "front left motor");
        moveOneMotor(wheels, wheels.backLeftMotor, "back left motor");
        moveOneMotor(wheels, wheels.backRightMotor, "back right motor");
        moveOneMotor(wheels, wheels.frontRightMotor, "front right motor");

        moveOneMotor(wheels, armMotor, "arm motor");

        wheels.stop();
    }

    private void moveOneMotor(MacanumWheels wheels, DcMotor motor, String motorName) {
        while (opModeIsActive() && (runtime.seconds() < 0.4)) {
            telemetry.addData("Moving", motorName);
            motor.setPower(1/2);
            telemetry.update();
        }
        wheels.stop();
        runtime.reset();

        pause();
    }

    private void pause() {
        while (opModeIsActive() && (runtime.seconds() < 5)) {
            telemetry.addData("Status", "Pause");
            telemetry.update();
        }
        runtime.reset();
    }


}