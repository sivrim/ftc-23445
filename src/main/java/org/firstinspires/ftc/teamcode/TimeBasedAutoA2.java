package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "AutoTimedA2", group = "Furious Frogs")
//@Disabled
public class TimeBasedAutoA2 extends TimeBasedAutoBase {
    public void goToStartingPositionAfterHangingSpecimen() {
        //go straight
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 0.58) {
            move(0, 1, 0);
        }
        stopChassis();

        //turn 180 degrees for april tags
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 3.00) {
            move(, 0, -1);
        }
        stopChassis();

for (AprilTagDetection detection : aprilTag.getDetections())  {

     Orientation rot = Orientation.getOrientation(detection.rawPose.R, AxesReference.INTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);

     // Original source data
     double poseX = detection.rawPose.x;
     double poseY = detection.rawPose.y;
     double poseZ = detection.rawPose.z;

     double poseAX = rot.firstAngle;
     double poseAY = rot.secondAngle;
     double poseAZ = rot.thirdAngle;
     }
        //turn 180 degrees back hanging specimen
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 2.40) {
            move(0, 0, 1);
        }
        stopChassis();

       //hang specimen(cannot do with claw not being made)(will remove after claw is made)

    
        //go backwards straight 
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 0.58) {
            move(0, -1, 0);
        }
        stopChassis();




 
