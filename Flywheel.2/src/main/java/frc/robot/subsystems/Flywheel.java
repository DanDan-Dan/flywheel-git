package frc.robot.subsystems;
    
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class Flywheel extends SubsystemBase {
    private SparkPIDController pid;
    private  CANSparkMax FlywheelMotor1;
    private  CANSparkMax FlywheelMotor2;
    private  CANSparkMax FlywheelMotor3;
    private  RelativeEncoder encoder;
  /** Creates a new ExampleSubsystem. */
  public Flywheel() {
    FlywheelMotor1 = new CANSparkMax (4, MotorType.kBrushless);
    FlywheelMotor2 = new CANSparkMax (5, MotorType.kBrushless);
    FlywheelMotor3 = new CANSparkMax (6, MotorType.kBrushless);

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
    FlywheelMotor1.set(1.0);
    FlywheelMotor2.set(1.0);
    FlywheelMotor3.set(1.0);
  }
  public void Halfspeed (){
    FlywheelMotor1.set(0.5);
    FlywheelMotor2.set(0.5);
    FlywheelMotor3.set(0.5);
  }
  public void Reverse (){
    FlywheelMotor1.set(-0.5);
    FlywheelMotor2.set(-0.5);
    FlywheelMotor3.set(-0.5);
  }
  public void Stop(){
    FlywheelMotor1.set(0);
    FlywheelMotor2.set(0);
    FlywheelMotor3.set(0);
  }
  
    private void setFlyWheelVelocity( double velocity){
    pid.setReference(velocity, ControlType.kVelocity);
   }

   private boolean isFlywheelAtSrtpoint(){
    return true;
   }
}