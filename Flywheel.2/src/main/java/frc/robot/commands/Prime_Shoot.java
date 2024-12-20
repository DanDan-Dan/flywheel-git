// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Flywheel;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Prime_Shoot extends SequentialCommandGroup {
  /** Creates a new Prime_Shoot. */
  public Prime_Shoot(Flywheel flywheel, Conveyor Convayer) {
   
    addCommands(  new InstantCommand(()-> flywheel.RunFullspeed(), flywheel),
    new WaitUntilCommand(()-> flywheel.isFlywheelAtSetpoint()),
    new InstantCommand(()-> Convayer.MoveNoteToShooter()),
    new WaitCommand(2),
    new InstantCommand(()-> flywheel.Stop())

    );
  }
}
