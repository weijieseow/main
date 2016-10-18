package seedu.menion.logic.commands;

import seedu.menion.commons.core.Messages;
import seedu.menion.commons.core.UnmodifiableObservableList;
import seedu.menion.model.activity.Activity;
import seedu.menion.model.activity.ReadOnlyActivity;
/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case sensitive.
 */
public class UnCompleteCommand extends Command {

    public static final String COMMAND_WORD = "Uncomplete";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": UnCompletes an activity using their type and index: "
            + "\n"
            + "Parameters: [Activity_Type] + [Activity_Index] \n"
            + "Example: " + COMMAND_WORD + " " + Activity.EVENT_TYPE + " 1";
    
    public static final String MESSAGE_UNCOMPLETED_ACTIVITY_SUCCESS = "UnCompleted Activity: %1$s";
    
    public final int targetIndex;
    public final String targetType;
    
    
    public UnCompleteCommand(String[] splited) {
        this.targetType = splited[1];
        this.targetIndex = Integer.valueOf(splited[2]);
    }

    @Override
    public CommandResult execute() {
        
        UnmodifiableObservableList<ReadOnlyActivity> lastShownList;

        if (targetType.equals(Activity.FLOATING_TASK_TYPE)) {
            lastShownList = model.getFilteredFloatingTaskList();
        }
        else if (targetType.equals(Activity.TASK_TYPE)) {
            lastShownList = model.getFilteredTaskList();
        }
        else {
            lastShownList = model.getFilteredEventList();
        }
        
        if (lastShownList.size() < targetIndex) {
            indicateAttemptToExecuteIncorrectCommand();
            return new CommandResult(Messages.MESSAGE_INVALID_ACTIVITY_DISPLAYED_INDEX);
        }
        
        callUnCompleteActivity(targetType); // Calls the correct method depending on type of activity.
        ReadOnlyActivity activityToUnComplete = lastShownList.get(targetIndex - 1);
        
        return new CommandResult(String.format(MESSAGE_UNCOMPLETED_ACTIVITY_SUCCESS, activityToUnComplete));
    }

    private void callUnCompleteActivity(String targetType) {
        
        if (targetType.equals(Activity.FLOATING_TASK_TYPE)) {
            model.UncompleteFloatingTask(targetIndex - 1);
        }
        else if (targetType.equals(Activity.TASK_TYPE)) {
            model.UncompleteTask(targetIndex - 1);
        }
        else {
            model.UncompleteEvent(targetIndex - 1);
        }
    }

    /*
     * Complete command supports undo
     */
    @Override
    public boolean undo() {
        return true;
    }

}
