package frc.tilt;

import frc.Shooter.*;

public class Hood{
    public Tilt tilt;
    public Shooter shooter = new Shooter();

    double[] anglePositions = {30.0, 45.0, 60.0}; // change for different angle positions
    int angleIndex;
    
    // constructors for the shooter motors, default percent output, and default set starting position
    public Hood(int[] hoodMotorCANIDs, double percentOutput, int positionIndex){
        tilt = new Tilt(hoodMotorCANIDs, percentOutput);
        tilt.setToAngle(anglePositions[positionIndex]);

        angleIndex = positionIndex;
    }
    public Hood(int[] hoodMotorCANIDs, int positionIndex){
        this(hoodMotorCANIDs, 0.1, positionIndex); // defauit output is 10%
    }
    public Hood(int[] hoodMotorCANIDs, double percentOutput){
        this(hoodMotorCANIDs,percentOutput, 1); // defauit position is anglePositions[2], or 90 degrees
    }
    public Hood(int[] hoodMotorCANIDs){
        this(hoodMotorCANIDs, 0.1, 1);
    }


    // and for moving between the set positions, we have these 2 functions
    public void moveTo(int space){
        angleIndex = space;
        tilt.setToAngle(anglePositions[space]);
    }
    public void move(int spaces){
        if( (angleIndex + spaces > -1) || (angleIndex + spaces < anglePositions.length) ){ // make sure angle moved to exists, and isn't out of bounds
            angleIndex += spaces;
            tilt.setToAngle(anglePositions[angleIndex]);

        } else { System.out.println("cannot move anymore, don't break me :("); } // console indication that this crap don't work
    }

    // finally, i just want an easier way to get the current position
    public double currentAnglePosition(){
        return anglePositions[angleIndex];
    }
}