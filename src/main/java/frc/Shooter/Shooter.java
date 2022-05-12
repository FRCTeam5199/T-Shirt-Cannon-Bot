package frc.Shooter;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;

public class Shooter {
    public static Solenoid shooterSolenoid = new Solenoid(PneumaticsModuleType.CTREPCM, 0);
    public static Solenoid reserveSolenoid = new Solenoid(PneumaticsModuleType.CTREPCM, 0);

    public static void fireShot() {
        reserveSolenoid.set(false);
        shooterSolenoid.set(true);
    } 

    public static void resetShooter() {
        shooterSolenoid.set(false);
    }

    public static void openReserve() {
        shooterSolenoid.set(false);
        reserveSolenoid.set(true);
    }

    public static void closeReserve() {
        reserveSolenoid.set(false);
    }
}
