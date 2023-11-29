package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "AutoTimedA2", group = "Furious Frogs")
//@Disabled
public class TimeBasedAutoA2 extends TimeBasedAutoBase {
    public void goToBackStage() {
        //go straight
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 0.58) {
            move(0, 1, 0);
        }
        stopChassis();

        //strafe
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 2.02) {
            move(1, 0, 0);
        }
        stopChassis();


        //go straight
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 2.40) {
            move(0, 1, 0);
        }
        stopChassis();


        //strafe
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < .22) {
            move(-1, 0, 0);
        }
        stopChassis();


        //rotate
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 1.50) {
            move(0, 0, 1);
        }
        stopChassis();


        //go straight through the door
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 5.85)) {
            move(0, 1, 0);
        }
        stopChassis();
        runtime.reset();

        //strafe
        while (opModeIsActive() && (runtime.seconds() < 2.3)) {
            move(-1, 0, 0);
        }
        stopChassis();
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
            move(1, 0, 0);
        }
        stopChassis();


        runtime.reset();

        while (opModeIsActive() && (runtime.seconds() < .5)) {
            move(0, 1, 0);
        }
        stopChassis();


    }

}