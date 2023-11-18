package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "AutoTimedF2", group = "Furious Frogs")
//@Disabled
public class TimeBasedAutoF2 extends TimeBasedAutoBase {
    public void goToBackStage() {

        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 2.85) {
            move(0, 1, 0);
        }
        stopChassis();

        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 1.41) {
            move(0, 0, -1);
        }
        stopChassis();


        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 5.05)) {
            move(0, 1, 0);
        }
        stopChassis();
        runtime.reset();


        while (opModeIsActive() && (runtime.seconds() < 2.5)) {
            move(1, 0, 0);
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
