package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * LinearOpMode vs OpMode :
 *
 *  Someone suggested online that "Linear OpMode works better for Autonomous, and regular OpMode work better for Teleop."
 *
 *  The boilerplate for OpMode is easiest to use to interpret the TeleOp program, as the state of the robot is constantly changing.
 *
 *  The LinearOpMode is easiest to use for someone completely new to programming
 */
@TeleOp
//@Autonomous
//@Disabled
public class FirstOpMode extends LinearOpMode {
    private Gyroscope imu;
    private DcMotor motorTest;
    private DigitalChannel digitalTouch;
    private DistanceSensor sensorColorRange;
    private Servo servoTest;

    /**
     *  gets called when a user selects and runs the op mode
     *
     *
     */
    @Override
    public void runOpMode() throws InterruptedException {
        // The name (motorTest here) must match the name defined for the motor in RC configuration
        motorTest = hardwareMap.get(DcMotor.class, "motorTest");
        imu = hardwareMap.get(Gyroscope.class, "imu");
        motorTest = hardwareMap.get(DcMotor.class, "motorTest");

//        digitalTouch = hardwareMap.get(DigitalChannel.class, "digitalTouch");
//        sensorColorRange = hardwareMap.get(DistanceSensor.class, "sensorColorRange");
//        servoTest = hardwareMap.get(Servo.class, "servoTest");

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // the op mode prompts the user to push the start button to continue

        // Wait for the game to start (driver presses PLAY) . Otherwise Robot will start doing its thing without start button press.
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.addData("Status", "Running");

            telemetry.addData("Current motor position", motorTest.getCurrentPosition());

            telemetry.update();
        }
    }
}
