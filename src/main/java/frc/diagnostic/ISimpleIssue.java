package frc.diagnostic;

import frc.misc.UserInterface;
import frc.misc.Clientside;
import frc.misc.ISubsystem;


@Clientside
public interface ISimpleIssue {
    
    static void robotPeriodic() {
        if (UserInterface.GET_RANDOM_FIX.getEntry().getBoolean(false)) {
            UserInterface.GET_RANDOM_FIX.getEntry().setBoolean(false);
            System.out.println("issues reported:" + IssueHandler.issues.keySet().size());
        }
    }

    
}
