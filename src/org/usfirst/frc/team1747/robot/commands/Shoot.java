package org.usfirst.frc.team1747.robot.commands;

import org.usfirst.frc.team1747.robot.Robot;
import org.usfirst.frc.team1747.robot.subsystems.Intake;
import org.usfirst.frc.team1747.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shoot extends Command {

	Shooter shooter;
	Intake intake;
	double startTime;
	double time = -1;

	public Shoot() {
		shooter = Robot.getShooter();
		intake = Robot.getIntake();
		requires(shooter);
		requires(intake);
	}

	@Override
	protected void initialize() {
		double speed = SmartDashboard.getNumber("Target Shooter Speed", .6);
		shooter.shoot(speed);
		// double targetSpeed = SmartDashboard.getNumber("Target Shooter Speed",
		// .6) * -20.0;
		// shooter.enablePID();
		// shooter.setSetpoint(targetSpeed);
		time = System.currentTimeMillis();
	}

	@Override
	protected void execute() {
		shooter.runPID();
		if (System.currentTimeMillis() - time > 3000) {
			intake.intakeBall();
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		shooter.shoot(0.0);
		// shooter.disablePID();
		intake.rollerControl(0);
	}

	@Override
	protected void interrupted() {
		end();
	}
}