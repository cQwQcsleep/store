package com.sun.jna.platform.dnd;

import java.awt.Component;
import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class DropHandler implements DropTargetListener {
    private static final Logger LOG = Logger.getLogger(DropHandler.class.getName());
    private int acceptedActions;
    private List<DataFlavor> acceptedFlavors;
    private boolean active;
    private DropTarget dropTarget;
    private String lastAction;
    private DropTargetPainter painter;

    public DropHandler(Component component, int i, DataFlavor[] dataFlavorArr, DropTargetPainter dropTargetPainter) {
        this.active = true;
        this.acceptedActions = i;
        this.acceptedFlavors = Arrays.asList(dataFlavorArr);
        this.painter = dropTargetPainter;
        this.dropTarget = new DropTarget(component, i, this, this.active);
    }

    private void describe(String str, DropTargetEvent dropTargetEvent) {
        Logger logger = LOG;
        Level level = Level.FINE;
        if (logger.isLoggable(level)) {
            StringBuilder sb = new StringBuilder("drop: ");
            sb.append(str);
            if (dropTargetEvent instanceof DropTargetDragEvent) {
                DropTarget dropTarget = dropTargetEvent.getDropTargetContext().getDropTarget();
                DropTargetDragEvent dropTargetDragEvent = (DropTargetDragEvent) dropTargetEvent;
                sb.append(": src=");
                sb.append(DragHandler.actionString(dropTargetDragEvent.getSourceActions()));
                sb.append(" tgt=");
                sb.append(DragHandler.actionString(dropTarget.getDefaultActions()));
                sb.append(" act=");
                sb.append(DragHandler.actionString(dropTargetDragEvent.getDropAction()));
            } else if (dropTargetEvent instanceof DropTargetDropEvent) {
                DropTarget dropTarget2 = dropTargetEvent.getDropTargetContext().getDropTarget();
                DropTargetDropEvent dropTargetDropEvent = (DropTargetDropEvent) dropTargetEvent;
                sb.append(": src=");
                sb.append(DragHandler.actionString(dropTargetDropEvent.getSourceActions()));
                sb.append(" tgt=");
                sb.append(DragHandler.actionString(dropTarget2.getDefaultActions()));
                sb.append(" act=");
                sb.append(DragHandler.actionString(dropTargetDropEvent.getDropAction()));
            }
            String string = sb.toString();
            if (string.equals(this.lastAction)) {
                return;
            }
            logger.log(level, string);
            this.lastAction = string;
        }
    }

    public int acceptOrReject(DropTargetDragEvent dropTargetDragEvent) {
        int dropAction = getDropAction(dropTargetDragEvent);
        if (dropAction != 0) {
            dropTargetDragEvent.acceptDrag(dropAction);
            return dropAction;
        }
        dropTargetDragEvent.rejectDrag();
        return dropAction;
    }

    public boolean canDrop(DropTargetEvent dropTargetEvent, int i, Point point) {
        return true;
    }

    public void dragEnter(DropTargetDragEvent dropTargetDragEvent) {
        describe("enter(tgt)", dropTargetDragEvent);
        paintDropTarget(dropTargetDragEvent, acceptOrReject(dropTargetDragEvent), dropTargetDragEvent.getLocation());
    }

    public void dragExit(DropTargetEvent dropTargetEvent) {
        describe("exit(tgt)", dropTargetEvent);
        paintDropTarget(dropTargetEvent, 0, null);
    }

    public void dragOver(DropTargetDragEvent dropTargetDragEvent) {
        describe("over(tgt)", dropTargetDragEvent);
        paintDropTarget(dropTargetDragEvent, acceptOrReject(dropTargetDragEvent), dropTargetDragEvent.getLocation());
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: java.awt.datatransfer.UnsupportedFlavorException */
    public void drop(DropTargetDropEvent dropTargetDropEvent) throws UnsupportedFlavorException {
        describe("drop(tgt)", dropTargetDropEvent);
        int dropAction = getDropAction(dropTargetDropEvent);
        if (dropAction != 0) {
            dropTargetDropEvent.acceptDrop(dropAction);
            try {
                drop(dropTargetDropEvent, dropAction);
                dropTargetDropEvent.dropComplete(true);
            } catch (Exception unused) {
                dropTargetDropEvent.dropComplete(false);
            }
        } else {
            dropTargetDropEvent.rejectDrop();
        }
        paintDropTarget(dropTargetDropEvent, 0, dropTargetDropEvent.getLocation());
    }

    public abstract void drop(DropTargetDropEvent dropTargetDropEvent, int i) throws UnsupportedFlavorException, IOException;

    public void dropActionChanged(DropTargetDragEvent dropTargetDragEvent) {
        describe("change(tgt)", dropTargetDragEvent);
        paintDropTarget(dropTargetDragEvent, acceptOrReject(dropTargetDragEvent), dropTargetDragEvent.getLocation());
    }

    public int getDropAction(DropTargetEvent dropTargetEvent) {
        Point point;
        int i;
        int i2;
        int dropAction;
        int sourceActions;
        DataFlavor[] currentDataFlavors;
        Point location;
        int dropAction2;
        DataFlavor[] dataFlavorArr = new DataFlavor[0];
        if (!(dropTargetEvent instanceof DropTargetDragEvent)) {
            if (dropTargetEvent instanceof DropTargetDropEvent) {
                DropTargetDropEvent dropTargetDropEvent = (DropTargetDropEvent) dropTargetEvent;
                dropAction = dropTargetDropEvent.getDropAction();
                sourceActions = dropTargetDropEvent.getSourceActions();
                currentDataFlavors = dropTargetDropEvent.getCurrentDataFlavors();
                location = dropTargetDropEvent.getLocation();
            } else {
                point = null;
                i = 0;
                i2 = 0;
            }
            if (isSupported(dataFlavorArr) || (dropAction2 = getDropAction(dropTargetEvent, i, i2, getDropActionsForFlavors(dataFlavorArr))) == 0 || !canDrop(dropTargetEvent, dropAction2, point)) {
                return 0;
            }
            return dropAction2;
        }
        DropTargetDragEvent dropTargetDragEvent = (DropTargetDragEvent) dropTargetEvent;
        dropAction = dropTargetDragEvent.getDropAction();
        sourceActions = dropTargetDragEvent.getSourceActions();
        currentDataFlavors = dropTargetDragEvent.getCurrentDataFlavors();
        location = dropTargetDragEvent.getLocation();
        int i3 = dropAction;
        point = location;
        dataFlavorArr = currentDataFlavors;
        i2 = sourceActions;
        i = i3;
        if (isSupported(dataFlavorArr)) {
        }
        return 0;
    }

    public int getDropActionsForFlavors(DataFlavor[] dataFlavorArr) {
        return this.acceptedActions;
    }

    public DropTarget getDropTarget() {
        return this.dropTarget;
    }

    public boolean isActive() {
        return this.active;
    }

    public boolean isSupported(DataFlavor[] dataFlavorArr) {
        HashSet hashSet = new HashSet(Arrays.asList(dataFlavorArr));
        hashSet.retainAll(this.acceptedFlavors);
        return !hashSet.isEmpty();
    }

    public boolean modifiersActive(int i) {
        int modifiers = DragHandler.getModifiers();
        if (modifiers == -1) {
            return i == 1073741824 || i == 1;
        }
        return modifiers != 0;
    }

    public void paintDropTarget(DropTargetEvent dropTargetEvent, int i, Point point) {
        DropTargetPainter dropTargetPainter = this.painter;
        if (dropTargetPainter != null) {
            dropTargetPainter.paintDropTarget(dropTargetEvent, i, point);
        }
    }

    public void setActive(boolean z) {
        this.active = z;
        DropTarget dropTarget = this.dropTarget;
        if (dropTarget != null) {
            dropTarget.setActive(z);
        }
    }

    public DropHandler(Component component, int i, DataFlavor[] dataFlavorArr) {
        this(component, i, dataFlavorArr, null);
    }

    public DropHandler(Component component, int i) {
        this(component, i, new DataFlavor[0]);
    }

    public int getDropAction(DropTargetEvent dropTargetEvent, int i, int i2, int i3) {
        int i4;
        boolean zModifiersActive = modifiersActive(i);
        int i5 = i & i3;
        if (i5 != 0 || zModifiersActive) {
            return (!zModifiersActive || (i4 = i5 & i2) == i) ? i : i4;
        }
        return i3 & i2;
    }
}
