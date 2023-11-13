package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class MacanumWheels {
    public DcMotor frontLeftMotor = null;
    public DcMotor backLeftMotor = null;
    public DcMotor frontRightMotor = null;
    public DcMotor backRightMotor = null;
    Telemetry telemetry;
    double powerRatio = 0.7;

    static final double     COUNTS_PER_MOTOR_REV    = 1440 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 1.0 ;     // No External Gearing.
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = 0.6;
    static final double     TURN_SPEED              = 0.5;

    public MacanumWheels(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;
        frontLeftMotor = hardwareMap.dcMotor.get(DeviceNames.MOTOR_FRONT_LEFT);
        backLeftMotor = hardwareMap.dcMotor.get(DeviceNames.MOTOR_BACK_LEFT);
        frontRightMotor = hardwareMap.dcMotor.get(DeviceNames.MOTOR_FRONT_RIGHT);
        backRightMotor = hardwareMap.dcMotor.get(DeviceNames.MOTOR_BACK_RIGHT);

        // Reverse the right side motors. This may be wrong for your setup.
        // If your robot moves backwards when commanded to go forwards,
        // reverse the left side instead.
        // See the note about this earlier on this page.
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    public void stop(){
        move(0,0,0);
    }

    public void goForward(int targetTicks){
        move(0,1,0, targetTicks);
    }

    public void back(int targetTicks){
        move(0,1,0, targetTicks);
    }

    public void rotateLeft90(int targetTicks){
        move(0,1,0, targetTicks);
    }

    public void rightRight90(int targetTicks){
        move(0,1,0, targetTicks);
    }


    public void strafeLeft(int targetTicks){
        move(0,1,0, targetTicks);
    }


    public void strafeRight(int targetTicks){
        move(1,0,0, targetTicks);
    }

    /**
     * Takes the actual values (for teleop) or the equivalent values(for auton) and moves the macanum wheels accordingly
     * @param x  How much is the left joystick is pressed along x axis. Values have to be between 1 and -1
     * @param y How much is the left joystick is pressed along y axis. Values have to be between 1 and -1
     * @param turn How much is the right joystick is pressed along x axis. Values have to be between 1 and -1
     */
    public void move(double x, double y, double turn){
        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio,
        // but only if at least one is out of the range [-1, 1]
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(turn), 1);
        double frontLeftPower = (y + x + turn) / denominator;
        double backLeftPower = (y - x + turn) / denominator;
        double frontRightPower = (y - x - turn) / denominator;
        double backRightPower = (y + x - turn) / denominator;

        double powerRatio = 0.2;
        setPower(frontLeftPower, backLeftPower, frontRightPower, backRightPower, powerRatio);
    }

    public void setMode(DcMotor.RunMode mode){
        frontLeftMotor.setMode(mode);
        backLeftMotor.setMode(mode);
        frontRightMotor.setMode(mode);
        backRightMotor.setMode(mode);
    }

    /**
     * TODO move till desired movement is achieved
     */
    public void move(double x, double y, double turn, int targetTicks) {

        setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(turn), 1);

        double frontLeftPower = (y + x + turn) / denominator;
        double backLeftPower = (y - x + turn) / denominator;
        double frontRightPower = (y - x - turn) / denominator;
        double backRightPower = (y + x - turn) / denominator;

        double powerRatio = 0.2;

        telemetry.addData("power ", String.format("%s %s %s %s", frontLeftPower, backLeftPower, frontRightPower, backRightPower));
        telemetry.update();

        sleep(4000);

        setPower(frontLeftPower, backLeftPower, frontRightPower, backRightPower, powerRatio);

        setTargetPosition(targetTicks, frontLeftPower, backLeftPower, frontRightPower, backRightPower);

        setMode(DcMotor.RunMode.RUN_TO_POSITION);

        setPower(frontLeftPower, backLeftPower, frontRightPower, backRightPower, 0);

        while (isAnyMotorBusy()){
            sleep(10);
        }
        setPower(frontLeftPower, backLeftPower, frontRightPower, backRightPower, 0);

    }

    public void setTargetPosition(int targetTicks, double frontLeftPower, double backLeftPower, double frontRightPower, double backRightPower) {

        telemetry.addData("target position ", (int) (frontLeftPower * targetTicks));
        telemetry.update();
        sleep(5000);

        frontLeftMotor.setTargetPosition(frontLeftMotor.getCurrentPosition() + (int) (frontLeftPower * targetTicks));
        backLeftMotor.setTargetPosition(backLeftMotor.getCurrentPosition() +(int) (backLeftPower * targetTicks));
        frontRightMotor.setTargetPosition(frontRightMotor.getCurrentPosition() +(int) (frontRightPower * targetTicks));
        backRightMotor.setTargetPosition(backRightMotor.getCurrentPosition() +(int) (backRightPower * targetTicks));
    }

    public boolean isAnyMotorBusy() {
        return frontLeftMotor.isBusy() || backLeftMotor.isBusy() || frontRightMotor.isBusy() || backRightMotor.isBusy();
    }



    public void setPower(double frontLeftPower, double backLeftPower, double frontRightPower, double backRightPower, double powerRatio) {
        telemetry.addData("power ", powerRatio * frontLeftPower);
        telemetry.update();
        sleep(5000);

        frontLeftMotor.setPower(powerRatio * frontLeftPower);
        backLeftMotor.setPower(powerRatio * backLeftPower);
        frontRightMotor.setPower(powerRatio * frontRightPower);
        backRightMotor.setPower(powerRatio * backRightPower);
    }

    public final void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }



}
