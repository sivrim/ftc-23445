package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "AutoTimedF2", group = "Furious Frogs")
//@Disabled
public class TimeBasedAutoF2 extends TimeBasedAutoBase {
    public void goToBackStage() {
        log("do nothing", "do nothing", 10000);
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 2.7) {
            move(0, 1, 0);
        }
        stopChassis();
        log("motion", "straight done. try strafe right", 2000);

        log("motion", "trying rotation", 2000);
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 1.32) {
            move(0, 0, -1);
        }
        stopChassis();
        log("motion", "straight done. try strafe right", 2000);


        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 4.5)) {
            move(0, 1, 0);
        }
        stopChassis();
        runtime.reset();

        log("motion", "try rotate", 2000);

        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.3)) {
            move(0, 0, -1);
        }
        stopChassis();
        runtime.reset();
    }

}
