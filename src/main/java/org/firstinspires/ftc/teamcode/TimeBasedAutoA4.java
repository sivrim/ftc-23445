package org.firstinspires.ftc.teamcode;

import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;

import java.util.List;


@Autonomous(name = "AutoTimedA4", group = "Furious Frogs")
//@Disabled
public class TimeBasedAutoA4 extends TimeBasedAutoBase {
    public void goToBackStage() {
        log("do nothing", "do nothing", 10000);
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 0.12) {
            move(0, 1, 0);
        }
        stopChassis();

        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 3.5) {
            move(-1, 0, 0);
        }
        stopChassis();

        runtime.reset();

        log("motion", "try rotate", 2000);

        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.3)) {
            move(0, 0, 1);
        }
        stopChassis();
        runtime.reset();
    }

}
