package org.firstinspires.ftc.teamcode;

import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.troubleshoot.Logs;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;

import java.util.List;

@Autonomous(name="AutoFF", group="Furious Frogs")
//@Disabled
public class OpModeAuto extends LinearOpMode
{

    /* Declare OpMode members. */
    DcMotor armMotor = null;
    private ElapsedTime runtime = new ElapsedTime();
    private TfodProcessor tfod;
    private VisionPortal visionPortal;
    MacanumWheels wheels;
    Servo clawServo;
    DcMotor armMotor2;
    @Override
    public void runOpMode() {

        Logs.setTelemetry(telemetry);

        initTfod();

        wheels = new MacanumWheels(hardwareMap, telemetry);
        armMotor = hardwareMap.dcMotor.get("armMotor");
        clawServo = hardwareMap.servo.get("clawServo");
        armMotor2 = hardwareMap.dcMotor.get("armMotor2");

        telemetry.addData("Status", "Ready to run");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        telemetry.addData("Status", "Started");
        telemetry.update();
        sleep(1000);


        int stripe = getStripe();

        // TODO --  Go to stripe, place pixel and revert back out of stripe boundary
        if(stripe == 1) {

        } else if(stripe == 2) {

        }else if(stripe == 3) {

        }

        //move robot to stripe. TODO this is dynamic. Fill above if-else clauses instead
        wheels.goForward(100);

        //TODO Place purple pixel on the spike mark
        armMotor2.setTargetPosition(-1000);
        clawServo.setPosition(0.8);
        armMotor2.setTargetPosition(5);
        clawServo.setPosition(0.3);

        //back-out so as not to trip the placed pixel. From game manual 2. section 4.4.2 --> "Autonomous points are Scored AT REST for the following achievements...."
        wheels.back(100);

        //Assuming we are in A4. Turn left and move toward the backstage
        while (opModeIsActive() && (runtime.seconds() < 2.0)) {
            wheels.rotateLeft90(100);
            wheels.goForward(100);
            wheels.stop();
        }

        //Drop yellow pixel
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 10.0)) {
            armMotor.setTargetPosition(100);
            armMotor2.setTargetPosition(-50);
            clawServo.setPosition(0.8);
        }

        //TODO park

//
//        armMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        armMotor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        armMotor2.setPower(.5);
//
//        armMotor2.setTargetPosition(-1000);
//        armMotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

//        clawServo.setPosition(.8);

        sleep(1000);

        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);
    }

    /**
     * Tell which stripe has pixel (1 for left, 2 for center, 3 for right)
     *
     * If cannot detect pixel, then return 1.
     * @return
     */
    private int getStripe() {

        int stripe = 0;
        TfodProcessor tfod = TfodProcessor.easyCreateWithDefaults();

        VisionPortal visionPortal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, "webcam"))
                .setCameraResolution(new Size(640, 480))
                .addProcessor(tfod)
                .build();

        visionPortal.resumeStreaming();
        telemetry.addData(" > Camera Status", visionPortal.getCameraState());
        telemetry.update();
        sleep(2000);
        List<Recognition> currentRecognitions = tfod.getRecognitions();
        visionPortal.stopStreaming();
        telemetry.addData(" > Camera Status", visionPortal.getCameraState());
        telemetry.update();
        sleep(2000);

        telemetry.addData(" > currentRecognitions.size()", currentRecognitions.size());
        telemetry.update();
        sleep(2000);

        if(currentRecognitions.size() == 1){
            //find location, move robot and place purple pixel

            Recognition recognition = currentRecognitions.get(0);
            telemetry.addData("recognition",
                    String.format("recognition right %s, left %s, top %s,bottom %s ", recognition.getRight(),
                            recognition.getLeft(), recognition.getTop(), recognition.getBottom()));
            telemetry.update();
            sleep(4000);

            double x = (recognition.getLeft() + recognition.getRight()) / 2 ;
            double y = (recognition.getTop()  + recognition.getBottom()) / 2 ;

            telemetry.addData("Image", "%s (%.0f %% Conf.)", recognition.getLabel(), recognition.getConfidence() * 100);
            telemetry.addData("- Position", "%.0f / %.0f", x, y);
            telemetry.addData("- Size", "%.0f x %.0f", recognition.getWidth(), recognition.getHeight());

            sleep(4000);

            visionPortal.stopStreaming();
        } else {
            telemetry.addData("recognition", "none");
            sleep(1000);
            telemetry.update();
            //move robot to backstage
        }

        telemetry.addData("Path", "Leg 3: %4.1f S Elapsed", runtime.seconds());
        telemetry.update();

        return stripe;
    }


    public boolean inRange(double distance, int start, int end){
        return distance > start && distance < end;
    }

    private void initTfod() {

        // Create the TensorFlow processor the easy way.
        tfod = TfodProcessor.easyCreateWithDefaults();
            visionPortal = VisionPortal.easyCreateWithDefaults(
                    hardwareMap.get(WebcamName.class, "webcam"), tfod);

    }

    private String getQuadrant() {

        DistanceSensor leftDistanceSensor = (DistanceSensor) hardwareMap.get("left2m");
        DistanceSensor rightDistanceSensor = (DistanceSensor) hardwareMap.get("right2m");

        telemetry.addData("Detecting Quadrant", "start");
        telemetry.update();

        double l = leftDistanceSensor.getDistance(DistanceUnit.INCH);
        double r = rightDistanceSensor.getDistance(DistanceUnit.INCH);

        telemetry.addData("l", l);
        telemetry.addData("r", r);

        telemetry.update();
        sleep(10000);
        String quadrant = "";
        if ((inRange(l, 48, 60) && (inRange(r, 24, 36) || inRange(r, 72, 84)))) /* A4 */ {
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

    public void moveMotor(DcMotor motor, double speed, int targetTicks){
        int newTarget = motor.getCurrentPosition() + targetTicks;
        motor.setTargetPosition(newTarget);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor.setPower(Math.abs(speed));
        while (motor.isBusy()) {
            // Display it for the driver.
            telemetry.addData("Running to",  " %7d", newTarget);
            telemetry.addData("Currently at",  " at %7d ", motor.getCurrentPosition());
            telemetry.update();
        }
    }


}
