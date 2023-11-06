package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;

import java.util.List;

@Autonomous(name="Auto", group="Furious Frog")
@Disabled
public class OpModeAuto extends LinearOpMode
{

    /* Declare OpMode members. */
    DcMotor armMotor = null;
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {

        MacanumWheels wheels = new MacanumWheels(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Step through each leg of the path, ensuring that the Auto mode has not been stopped along the way
        //TODO -- Come up with these steps. -- Ani2 and Adi

        // Step 1:  Drive forward
        // TODO --  Determine how long we will need to go, in terms of distance and/or time -- Ani1 and Vishnu
        wheels.goForward();

        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.0)) {
            telemetry.addData("Path", "Leg 1: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        // Step 2:  Turn and keep on looking for April tags
        //TODO
        runtime.reset();
        while (opModeIsActive() /* && Apritags not detected */) {
            telemetry.addData("Path", "Leg 1: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        //Step 2.5 TODO based on april tag reading, determine quadrant Kermit was placed in

        // Step 3: Rotate back to original orientation
        //TODO
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.3)) {
            telemetry.addData("Path", "Leg 2: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        // Step 4: Move ahead remaining distance
        //TODO
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 10.0)) {

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
        }

        // Step 5: Place the purple pixel on ground
        //TODO

        // Step 6: Move forward or back based on quadrant and so as to avoid the bars when moving
        //TODO

        //Step 6.5 turn 90  degree in the right direction. Stop when turned by 90 degrees
        //TODO

        // Step 7: Move to the board till center tag is in the middle
        //TODO

        // Step 8: Place the yellow pixel
        //TODO

        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);
    }

}
