package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

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
        int armInit, armInit2;
        double clawOffset = 0.0;
        double armOffset = 0.20;
        double ARM_SPEED = 0.01;

        double CLAW_SPEED = 0.02;                 // sets rate to move servo

        TouchSensor touchSensor;  // Touch sensor Object


        DcMotor armMotor = hardwareMap.dcMotor.get("armMotor");
        MacanumWheelsTeleop wheels = new MacanumWheelsTeleop(hardwareMap, telemetry);
        Servo clawServo = hardwareMap.servo.get("clawServo");
        Servo droneServo = hardwareMap.servo.get("droneServo");
        DcMotor armMotor2 = hardwareMap.dcMotor.get("armMotor2");

        DistanceSensor throttleSensor = hardwareMap.get(DistanceSensor.class, "slow");

        touchSensor = hardwareMap.get(TouchSensor.class, "sensor_touch");

        //armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //armMotor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        clawServo.setPosition(clawOffset);

        droneServo.setPosition(0);

        armInit = armMotor.getTargetPosition();
        armInit2 = armMotor2.getTargetPosition();
        //droneServo.setPosition(-1);
        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double chassisY = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
            //  System.out.println("gamepad1.left_stick_y is " + chassisY);
            double chassisX = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            //  System.out.println("gamepad1.left_stick_x is " + chassisX);
            double chassisTurn = gamepad1.right_stick_x;
            //  System.out.println("chassisTurn is " + chassisTurn);
            //  double armTurn = gamepad2.right_stick_x;

            double power = 0.7;
            if(throttleSensor != null){
                telemetry.addData("distance sensor", "present");
                double distance = throttleSensor.getDistance(DistanceUnit.INCH);
                telemetry.addData("distance sensor", "distance is " + distance);
                if(distance > 0.0 && distance < 10 && chassisY > 0.5){
                    power = .2;
                }
                telemetry.addData("distance sensor", "power is " + power);
            }
            wheels.move(chassisX, chassisY, chassisTurn, power);

            //System.out.println("dpadUp is " + dpadUp);
            //System.out.println("dpadDown is " + dpadDown);
            telemetry.addData("claw", "Offset = %.2f", clawServo.getPosition());
            telemetry.update();
            // Use gamepad dpad up and down open and close the claw
            if (gamepad2.left_bumper)
                clawOffset += CLAW_SPEED;
                //else if (gamepad2.right_bumper && touchSensor.isPressed())
            else if (gamepad2.right_bumper)
                clawOffset -= CLAW_SPEED;
            // Move servos to new position
            clawOffset = Range.clip(clawOffset, 0.0, 0.7);
            clawServo.setPosition(clawOffset);

            if (gamepad2.dpad_up)
                armOffset += ARM_SPEED;
            else if (gamepad2.dpad_down)
                armOffset -= ARM_SPEED;
            armOffset = Range.clip(armOffset, 0.0, 1.0);

            if (gamepad2.y) {
                droneServo.setPosition(1);
                telemetry.addData("Drone Servo", "Pressed gamepad2");
                telemetry.update();

            }

            if (gamepad1.y) {
                droneServo.setPosition(0);
                telemetry.addData("Drone Servo", "Pressed gamepad1");
                telemetry.update();
            }

            telemetry.addData("claw", "Offset = %.2f", clawOffset);
            telemetry.addData("clawfrom servo", "Offset = %.2f", clawServo.getPosition());
            telemetry.update();

            // Use gamepad dpad up and down open and close the claw


            double armLY = gamepad2.left_stick_y;
            double powerArmLY = armLY * -1.0;
             if (touchSensor.isPressed()){
                 if(armLY > 0)
                      powerArmLY = 0;
              }
            armMotor.setPower(powerArmLY);
            System.out.println("armMotor is " + armMotor);

            if (armMotor2 != null) {
                double armRY = gamepad2.right_stick_y;
                double armRightY = armRY * 0.4;
                double powerArmLY2 = armLY * -armOffset;
                if (powerArmLY2 > 0) {
                    powerArmLY2 = powerArmLY2 * 1;
                }
                armMotor2.setPower(powerArmLY2);
                armMotor2.setPower(armRightY);

            }

            // Bring back to starting position to grab the pixel
            if (gamepad2.a) {
                armMotor.setTargetPosition(armInit);
                armMotor2.setTargetPosition(armInit2);
            }
            telemetry.addData("claw", "Offset = %.2f", clawOffset);
            telemetry.addData("clawfrom servo", "Offset = %.2f", clawServo.getPosition());
            telemetry.addData("Motors", "armoffset= %.2f and powerArmY =%.2f", armOffset,armLY);
            // telemetry.addData("Motors",  "armMotor2= %2d ", armMotor2.getTargetPosition());
            telemetry.update();

        }
    }
}
