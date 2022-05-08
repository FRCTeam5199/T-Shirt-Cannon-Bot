package frc.shooter;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;

public class Shooter {
    public static Solenoid shooterSolenoid = new Solenoid(PneumaticsModuleType.CTREPCM, 0);

    public static void fireShot() {
        shooterSolenoid.set(true);
    } 

    public static void resetShooter() {
        shooterSolenoid.set(false);
    }
}
