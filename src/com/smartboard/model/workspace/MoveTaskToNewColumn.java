package com.smartboard.model.workspace;

import com.smartboard.model.workspace.Columnable;

public interface MoveTaskToNewColumn extends Columnable {
    public void moveTaskToNewColumn(Column c1, Column c2);
}
