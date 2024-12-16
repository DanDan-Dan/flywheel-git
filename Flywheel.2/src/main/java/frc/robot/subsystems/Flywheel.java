package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MotorSetPoint;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class Flywheel extends SubsystemBase {
  private SparkPIDController pid;
  private CANSparkMax FlywheelMotor1;
  private CANSparkMax FlywheelMotor2;
  private CANSparkMax FlywheelMotor3;
  private RelativeEncoder encoder;
  private double motorSetPoint;

  /** Creates a Subsystem. */
  public Flywheel() {
    FlywheelMotor1 = new CANSparkMax(4, MotorType.kBrushless);
    FlywheelMotor2 = new CANSparkMax(5, MotorType.kBrushless);
    FlywheelMotor3 = new CANSparkMax(6, MotorType.kBrushless);

    // Reset motor controller to factory defaults
    FlywheelMotor1.restoreFactoryDefaults();
    FlywheelMotor2.restoreFactoryDefaults();
    FlywheelMotor3.restoreFactoryDefaults();

    FlywheelMotor1.setIdleMode(IdleMode.kCoast);
    FlywheelMotor2.setIdleMode(IdleMode.kCoast);
    FlywheelMotor3.setIdleMode(IdleMode.kCoast);

    FlywheelMotor1.setInverted(false);
    FlywheelMotor2.setInverted(false);
    FlywheelMotor3.setInverted(false);
    
    FlywheelMotor2.follow(FlywheelMotor1);
    FlywheelMotor3.follow(FlywheelMotor1);

    pid = FlywheelMotor1.getPIDController();
    encoder = FlywheelMotor1.getEncoder();

    pid.setP(0.1);
    pid.setI(0);
    pid.setD(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void RunFullspeed() {
    motorSetPoint= MotorSetPoint.FLYWHEEL_FULL_SPEED;
   setFlyWheelVelocity(motorSetPoint);

  }

  public void Halfspeed() {
    motorSetPoint= MotorSetPoint.FLYWHEEL_HALF_SPEED;
   setFlyWheelVelocity(motorSetPoint);

  }

  public void Reverse() {
    motorSetPoint= -MotorSetPoint.FLYWHEEL_FULL_SPEED;
   setFlyWheelVelocity(motorSetPoint);

  }

  public void Stop() {
    FlywheelMotor1.stopMotor();

  }

  private void setFlyWheelVelocity(double velocity) {
    pid.setReference(velocity, ControlType.kVelocity);
  }

  private boolean isFlywheelAtSetpoint() {
    double currentVelocity = encoder.getVelocity();
    double tolerance= MotorSetPoint.FLYWHEEL_TOLERANCE;
    boolean isFlywheelAtSetpoint= Math.abs(currentVelocity- motorSetPoint ) <= MotorSetPoint.FLYWHEEL_TOLERANCE;
    return motorSetPoint == currentVelocity; 
  }
}