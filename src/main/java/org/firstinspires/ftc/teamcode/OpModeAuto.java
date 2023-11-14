package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.troubleshoot.Logs;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;

import java.util.List;

@Autonomous(name="AutoFF", group="Furious Frogs")
//@Disabled
public class OpModeAuto extends LinearOpMode {

    /* Declare OpMode members. */
    DcMotor armMotor = null;
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {

        Logs.setTelemetry(telemetry);

        MacanumWheels wheels = new MacanumWheels(hardwareMap, telemetry);
// DistanceSensor leftDistanceSensor = (DistanceSensor) hardwareMap.get("left2m");
// DistanceSensor rightDistanceSensor = (DistanceSensor) hardwareMap.get("right2m");
        DcMotor armMotor = hardwareMap.dcMotor.get("armMotor");
        Servo clawServo = hardwareMap.servo.get("clawServo");
        DcMotor armMotor2 = null;
        if (hardwareMap.dcMotor.contains("armMotor2")) {
            armMotor2 = hardwareMap.dcMotor.get("armMotor2");
        }

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");
        telemetry.update();


        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        telemetry.addData("Status", "Started");
        telemetry.update();
        //sleep(1000);
        //Step 0.5 TODO STOP_AND_RESET_ENCODERS


        armMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armMotor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armMotor2.setPower(.5);

        armMotor2.setTargetPosition(-1000);
        armMotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        clawServo.setPosition(.8);

        sleep(1000);

        clawServo.setPosition(.3);
        // Step 1:  Drive forward 20 inch
        // TODO --  Determine quadrant
        //String quadrant = getQuadrant(leftDistanceSensor, rightDistanceSensor);

        stripeDetection();

        runtime.reset();

        telemetry.addData("moving robot", "moving");
        telemetry.update();
        wheels.goForward(100);

        //sleep(4000);

        telemetry.addData("moved robot", "moved");
        telemetry.update();

        //TODO Place purple pixel on the spike mark
        armMotor2.setTargetPosition(-1000);
        clawServo.setPosition(0.8);
        armMotor2.setTargetPosition(5);
        clawServo.setPosition(0.3);
        wheels.back(100);
        telemetry.addData("Purple Pixel", "Placed");
        telemetry.update();

        //TODO Go to back board
        wheels.rotateLeft90(100);
        wheels.goForward(100);

        //TODO Drop yellow pixel
        armMotor.setTargetPosition(100);
        armMotor2.setTargetPosition(-50);
        clawServo.setPosition(0.8);
        wheels.back(20);
        telemetry.addData("Yellow Pixel", "Dropped");
        telemetry.update();


        //TODO move out of the way and park
        wheels.strafeLeft(50);
        wheels.goForward(10);
        wheels.stop();

        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);
    }

    private void stripeDetection() {
        // Step 1:  Drive forward 20 inch
        // TODO --  Determine which stripe is the pixel on
        int stripe = getStripe();

        // TODO --  Go to stripe, place pixel and revert back out of stripe boundary
        if (stripe == 1) {

        }

        else if (stripe == 2) {

        }

        else if (stripe == 3) {

        }
    }

    /**
     * Tell which stripe has pixel (1 for left, 2 for center, 3 for right)
     *
     * If cannot detect pixel, then return 2.
     * @return
     */
    private int getStripe() {
        TfodProcessor tfod = TfodProcessor.easyCreateWithDefaults();

        VisionPortal visionPortal = VisionPortal.easyCreateWithDefaults(
                hardwareMap.get(WebcamName.class, "webcam"), tfod);

        List<Recognition> currentRecognitions = tfod.getRecognitions();

        if (currentRecognitions.size() == 1) {
            //find location, move robot and place purple pixel

            Recognition recognition = currentRecognitions.get(0);
            telemetry.addData("recognition",
                    String.format("recognition right %s, left %s, top %s,bottom %s ", recognition.getRight(),
                            recognition.getLeft(), recognition.getTop(), recognition.getBottom()));
            telemetry.update();
            sleep(4000);
            visionPortal.stopStreaming();
        }

        else {
            telemetry.addData("no recognition", currentRecognitions.size());
            sleep(1000);
            telemetry.update();
            //move robot to backstage
        }

        telemetry.addData("Path", "Leg 3: %4.1f S Elapsed", runtime.seconds());
        telemetry.update();
        return 0;
    }

    private String getQuadrant(DistanceSensor leftDistanceSensor, DistanceSensor rightDistanceSensor) {

        telemetry.addData("Detecting Quadrant", "start");
        telemetry.update();

        double l = leftDistanceSensor.getDistance(DistanceUnit.INCH);
        double r = rightDistanceSensor.getDistance(DistanceUnit.INCH);

        telemetry.addData("l", l);
        telemetry.addData("r", r);

        telemetry.update();
        sleep(10000);
        String quadrant = "";
        if (inRange(l, 48, 60) && (inRange(r, 24, 36) || inRange(r, 72, 84))) /* A4 */ {
            quadrant = "A2";
        } else if ((inRange(l, 24, 36) || inRange(l, 72, 84)) && inRange(r, 48, 60)) /* F4 */ {
            quadrant = "F2";
        } else if (inRange(l, 24, 36) && (inRange(r, 24, 36) || inRange(r, 96, 108))) /* F2 */ {
            quadrant = "A4";
        } else if ((inRange(l, 24, 36) || inRange(l, 96, 108)) && inRange(r, 24, 36)) /* A2 */ {
            quadrant = "F4";
        }
        telemetry.addData("Detected Quadrant", quadrant);
        telemetry.update();
        sleep(100000);
        return quadrant;
    }

    public boolean inRange(double distance, int start, int end){
        return distance > start && distance < end;
    }

}
