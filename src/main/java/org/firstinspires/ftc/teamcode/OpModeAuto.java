package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.troubleshoot.Logs;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;

import java.util.List;

@Autonomous(name="AutoFF", group="Furious Frog")
@Disabled
public class OpModeAuto extends LinearOpMode
{

    /* Declare OpMode members. */
    DcMotor armMotor = null;
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {

        Logs.setTelemetry(telemetry);

        MacanumWheels wheels = new MacanumWheels(hardwareMap);

        // Send telemetry message to signify robot waiting;
        Logs.log3("Status", "Ready to run");

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        Logs.log3("Status", "Started");

        //Step 0.5 TODO STOP_AND_RESET_ENCODERS

        // Step 1:  Drive forward 20 inch
        // TODO --  Determine quadrant
        int quadrant = getQuadrant();
        
        // Step 1:  Drive forward 20 inch
        // TODO --  Determine which stripe is the pixel on
        int stripe = getStripe();

        // TODO --  Go to stripe, place pixel and revert back out of stripe boundary
        if(stripe == 1) {

        } else if(stripe == 2) {

        }else if(stripe == 3) {

        }

        runtime.reset();

        //TODO Go to back board
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 10.0)) {

        }

        //TODO Drop yellow pixel
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 10.0)) {

        }

        //TODO park

        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);
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

        if(currentRecognitions.size() == 1){
            //find location, move robot and place purple pixel

            Recognition recognition = currentRecognitions.get(0);
            telemetry.addData("recognition",
                    String.format("recognition right %s, left %s, top %s,bottom %s ", recognition.getRight(),
                            recognition.getLeft(), recognition.getTop(), recognition.getBottom()));
            visionPortal.stopStreaming();
        } else {
            telemetry.addData("no recognition", currentRecognitions.size());
            //move robot to backstage
        }

        telemetry.addData("Path", "Leg 3: %4.1f S Elapsed", runtime.seconds());
        telemetry.update();
        return 0;
    }

    private int getQuadrant() {
        return 1;
    }

}
