package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

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
        frontLeftMotor.setPower = (0,1,0);
  frontRightMotor.setPower = (0,1,0);
  backLeftMotor.setPower = (0,1,0);
  backRightMotor.setPower = (0,1,0);

  frontLeftMotor.setPower = (0,0,-1.0);
  frontRightMotor.setPower = (0,0,1.0);
  backLeftMotor.setPower = (0,0,-1.0);
  backRightMotor.setPower = (0,0,1.0);

  public double getDESIRED_DISTANCE() {
    return DESIRED_DISTANCE;
  }

  //Detecting if april tag 1 is detected and desired distance is 45 inches(Blue side)
    if (final double DESIRED_DISTANCE = 45.0;)&&(aprilTag(1) DETECTED);{
//everything we need to do in autonomous
  }

  //Detecting if april tag 7 is detected and desired distance is 33 inches(Red Side)
    if (final double DESIRED_DISTANCE = 33.0;)&&(aprilTag(7) DETECTED);{
      //everything we need to do in autonomous
  }

  //Detecting if april tag 1 is detected and desired distance is 45 inches(Blue Side)
    if (final double DESIRED_DISTANCE = 94.0;)&&(aprilTag(1) DETECTED);{
//everything we need to do in autonomous
  }

  //Detecting if april tag 7 is detected and desired distance is 73 inches(Red Side)
    if (final double DESIRED_DISTANCE = 73.0;)&&(aprilTag(7) DETECTED);{
//everything we need to do in autonomous
  }
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

}
