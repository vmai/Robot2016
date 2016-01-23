package org.usfirst.frc.team1747.robot;

import org.usfirst.frc.team1747.robot.commands.BallEject;
import org.usfirst.frc.team1747.robot.commands.IntakeBall;
import org.usfirst.frc.team1747.robot.commands.IntakeManual;

import org.usfirst.frc.team1747.robot.commands.Shoot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.USBCamera;

public class OI {

	private CyborgController controller;

	public OI() {
		getController().buttonA.whenPressed(new Shoot(SmartDashboard.getNumber("Shooter Speed", 1.0)));
		getController().buttonA.cancelWhenPressed(new Shoot(0.0));
		getController().buttonB.whenPressed(new IntakeBall());
		getController().buttonX.whenPressed(new BallEject());
		getController().buttonY.whenPressed(new IntakeManual(SmartDashboard.getNumber("Intake Speed", 1.0)));
		getController().buttonY.cancelWhenPressed(new IntakeManual(0.0));
	}

	public CyborgController getController() {
		if (controller == null) {
			controller = new CyborgController(0);
		}
		return controller;
	}
	
	private USBCamera intakeCamera;
	
	public USBCamera getIntakeCamera() {
		if (intakeCamera == null){
			intakeCamera = new USBCamera();
			intakeCamera.openCamera(); 
			
		}
		return intakeCamera;
	}
	private USBCamera shooterCamera;
	
	public USBCamera getShooterCamera() {
		if (shooterCamera == null){
			shooterCamera = new USBCamera();
			shooterCamera.openCamera();
			shooterCamera.startCapture();
		}
		return shooterCamera;
	}
}