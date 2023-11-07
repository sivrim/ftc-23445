package org.firstinspires.ftc.teamcode.troubleshoot;
import com.qualcomm.robotcore.util.RobotLog;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Logs {
    public static Telemetry telemetry;
    public static final String prefix = "Kermit:";

    public static void setTelemetry(Telemetry telemetrii) {
        telemetry = telemetrii;
    }

    public static void log(String str){
        RobotLog.d(prefix + str);
    }

    public static void log2(String str){
        RobotLog.d(prefix + str);
        telemetry.log().add(str);
    }

    public static void log3(String group, String str){
        RobotLog.d(prefix + str);
        telemetry.addData(group, str);
        telemetry.update();
    }

}
