package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous(name="Auto", group="Furious Frogs")
@Disabled
public class OpModeAuto extends LinearOpMode {

    /* Declare OpMode members. */
    DcMotor armMotor = null;
    private ElapsedTime runtime = new ElapsedTime();

        @Override
    public void runOpMode() {

        MacanumWheels wheels = new MacanumWheels(hardwareMap);

        DistanceSensor leftDistanceSensor = (DistanceSensor) hardwareMap.get("left2m");
        DistanceSensor rightDistanceSensor = (DistanceSensor) hardwareMap.get("right2m");

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Step through each leg of the path, ensuring that the Auto mode has not been stopped along the way
        //TODO -- Come up with these steps. -- Ani2 and Adi

        // Step 1:  Drive forward 12 inches
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

        //Step 2.5 TODO -- Determine quadrant Kermit was placed in
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.4)) {
            //leftDistance rightDistance
            double l = leftDistanceSensor.getDistance(DistanceUnit.INCH);
            double r = rightDistanceSensor.getDistance(DistanceUnit.INCH);


            if ((inRange(l, 48, 60) && (inRange(r, 24, 36) || inRange(r, 72, 84)))) /* A4 */ {
                // strat here
            }

            else if ((inRange(l, 24, 36) || inRange(l, 72, 84)) && inRange(r, 48, 60)) /* F4 */ {
                // strat here
            }

            else if (inRange(l, 24, 36) && (inRange(r, 24, 36) || inRange(r, 96, 108))) /* F2 */ {
                // strat here
            }

            else if ((inRange(l, 24, 36) || inRange(l, 96, 108)) && inRange(r, 24, 36)) /* A2 */ {
                // strat here
            }
                    /*                     * && r = 36-44 INCHES and right w/ robot = 24-32 INCHES) {
                quadrant = 1 // A4 }
            else if left = 24-32 INCHES and (right = 24-32 INCHES and right w/ robot = 24-32 INCHES) {
                quadrant = 2 // F4 }
            else if left = 12-20 INCHES and (right = 48-56 INCHES and right w/ robot = 24-32 INCHES) {
                quadrant = 3 // F2 }
            else if left = 24-32 INCHES and (right = 12-20 INCHES and right w/ robot = 12-20 INCHES) {
                quadrant = 4 // A2 }
            */
        }

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
        while (opModeIsActive() && (runtime.seconds() < 1.0)) {
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

public boolean inRange(double distance, int start, int end){
    return distance > start && distance < end;
    }
}
