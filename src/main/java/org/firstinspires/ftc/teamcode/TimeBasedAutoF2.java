package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "AutoTimedF2", group = "Furious Frogs")
//@Disabled
public class TimeBasedAutoF2 extends TimeBasedAutoBase {
    public void goToBackStage() {
        //go straight
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 0.58) {
            move(0, 1, 0);
        }
        stopChassis();
        displayImuTelemetryData();
        //strafe
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 1.9) {
            move(-1, 0, 0);
        }
        stopChassis();
        displayImuTelemetryData();

        //go straight
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 2.40) {
            move(0, 1, 0);
        }
        stopChassis();
        displayImuTelemetryData();

        //strafe
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < .22) {
            move(1, 0, 0);
        }
        stopChassis();
        displayImuTelemetryData();

        //rotate
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 1.50) {
            move(0, 0, -1);
        }
        stopChassis();
        displayImuTelemetryData();

        //go straight through the door
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 5.75)) {
            move(0, 1, 0);
        }
        stopChassis();
        displayImuTelemetryData();
        runtime.reset();

        //strafe
        while (opModeIsActive() && (runtime.seconds() < 2.3)) {
            move(1, 0, 0);
        }
        stopChassis();
        displayImuTelemetryData();
        runtime.reset();
    }


    public void park() {
        runtime.reset();

        while (opModeIsActive() && (runtime.seconds() < .7)) {
            move(0, -1, 0);
        }
        stopChassis();


        runtime.reset();

        while (opModeIsActive() && (runtime.seconds() < 2.1)) {
            move(-1, 0, 0);
        }
        stopChassis();


        runtime.reset();

        while (opModeIsActive() && (runtime.seconds() < .5)) {
            move(0, 1, 0);
        }
        stopChassis();


    }

}
