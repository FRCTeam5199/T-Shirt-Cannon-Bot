package frc.tilt;

import frc.Shooter.Shooter;

public class Hood{
    Tilt tilt;
    Shooter shooter = new Shooter();

    double[] anglePositions = {30.0, 45.0, 60.0, 90.0};
    int angleIndex;
    
    // constructors for the shooter motors, default percent output, and default set starting position
    public Hood(int[] hoodMotorCANIDs, int positionIndex, double percentOutput){
        tilt = new Tilt(hoodMotorCANIDs, percentOutput);
        tilt.setToAngle(anglePositions[positionIndex]);

        angleIndex = positionIndex;
    }
    public Hood(int[] hoodMotorCANIDs, int positionIndex){
        this(hoodMotorCANIDs, positionIndex, 0.1); // defauit output is 10%
    }
    public Hood(int[] hoodMotorCANIDs, double percentOutput){
        this(hoodMotorCANIDs, 2, percentOutput); // defauit position is anglePositions[2], or 90 degrees
    }
    public Hood(int[] hoodMotorCANIDs){
        this(hoodMotorCANIDs, 2, 0.1); //
    }

    // and for moving between the set positions, we have this function
    public void move(int spaces){
        if( (angleIndex + spaces > -1) || (angleIndex + spaces < anglePositions.length) ){ // make sure angle moved to exists, and isn't out of bounds
            angleIndex += spaces;
            tilt.setToAngle(anglePositions[angleIndex]);

        } else { System.out.println("cannot move anymore, don't break me :("); } // console indication that this crap don't work
    }

}