package seedu.menion.commons.events.ui;

import seedu.menion.commons.events.BaseEvent;
import seedu.menion.model.task.ReadOnlyTask;

/**
 * Represents a selection change in the Activity List Panel
 */
public class ActivityPanelSelectionChangedEvent extends BaseEvent {


    private final ReadOnlyTask newSelection;

    public ActivityPanelSelectionChangedEvent(ReadOnlyTask newSelection){
        this.newSelection = newSelection;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    public ReadOnlyTask getNewSelection() {
        return newSelection;
    }
}