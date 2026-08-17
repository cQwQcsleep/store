package com.sun.jna.platform.dnd;

import com.sun.jna.Platform;
import com.sun.org.apache.bcel.internal.Const;
import java.awt.AlphaComposite;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Point;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceContext;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import java.awt.dnd.DragSourceMotionListener;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.InvalidDnDOperationException;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.text.JTextComponent;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class DragHandler implements DragGestureListener, DragSourceListener, DragSourceMotionListener {
    protected static final int COPY = 1;
    static final int COPY_MASK;
    public static final float DEFAULT_GHOST_ALPHA = 0.5f;
    static final int KEY_MASK = 9152;
    protected static final int LINK = 1073741824;
    static final int LINK_MASK;
    protected static final int MOVE = 2;
    static final int MOVE_MASK = 64;
    protected static final int NONE = 0;
    static final boolean OSX;
    public static final int UNKNOWN_MODIFIERS = -1;
    private static int modifiers;
    private static Transferable transferable;
    private Component dragSource;
    private boolean fixCursor = true;
    private GhostedDragImage ghost;
    private float ghostAlpha;
    private Point imageOffset;
    private String lastAction;
    private Dimension maxGhostSize;
    private boolean moved;
    private int supportedActions;
    private static final Logger LOG = Logger.getLogger(DragHandler.class.getName());
    public static final Dimension MAX_GHOST_SIZE = new Dimension(Const.CHOP_FRAME_MAX, Const.CHOP_FRAME_MAX);
    public static final Transferable UNKNOWN_TRANSFERABLE = null;

    static {
        boolean zIsMac = Platform.isMac();
        OSX = zIsMac;
        COPY_MASK = zIsMac ? 512 : 128;
        LINK_MASK = zIsMac ? 768 : 192;
        modifiers = -1;
        transferable = null;
    }

    public DragHandler(Component component, int i) {
        this.maxGhostSize = MAX_GHOST_SIZE;
        this.ghostAlpha = 0.5f;
        this.dragSource = component;
        this.supportedActions = i;
        try {
            String property = System.getProperty("DragHandler.alpha");
            if (property != null) {
                try {
                    this.ghostAlpha = Float.parseFloat(property);
                } catch (NumberFormatException unused) {
                }
            }
            String property2 = System.getProperty("DragHandler.maxDragImageSize");
            if (property2 != null) {
                String[] strArrSplit = property2.split("x");
                if (strArrSplit.length == 2) {
                    this.maxGhostSize = new Dimension(Integer.parseInt(strArrSplit[0]), Integer.parseInt(strArrSplit[1]));
                }
            }
        } catch (NumberFormatException | SecurityException unused2) {
        }
        disableSwingDragSupport(component);
        DragSource.getDefaultDragSource().createDefaultDragGestureRecognizer(component, this.supportedActions, this);
    }

    public static String actionString(int i) {
        if (i == 1) {
            return "COPY";
        }
        if (i == 2) {
            return "MOVE";
        }
        if (i == 3) {
            return "MOVE|COPY";
        }
        switch (i) {
            case 1073741824:
                return "LINK";
            case 1073741825:
                return "COPY|LINK";
            case 1073741826:
                return "MOVE|LINK";
            case 1073741827:
                return "MOVE|COPY|LINK";
            default:
                return "NONE";
        }
    }

    private void describe(String str, DragSourceEvent dragSourceEvent) {
        Logger logger = LOG;
        Level level = Level.FINE;
        if (logger.isLoggable(level)) {
            StringBuilder sb = new StringBuilder("drag: ");
            sb.append(str);
            DragSourceContext dragSourceContext = dragSourceEvent.getDragSourceContext();
            if (dragSourceEvent instanceof DragSourceDragEvent) {
                DragSourceDragEvent dragSourceDragEvent = (DragSourceDragEvent) dragSourceEvent;
                sb.append(": src=");
                sb.append(actionString(dragSourceContext.getSourceActions()));
                sb.append(" usr=");
                sb.append(actionString(dragSourceDragEvent.getUserAction()));
                sb.append(" tgt=");
                sb.append(actionString(dragSourceDragEvent.getTargetActions()));
                sb.append(" act=");
                sb.append(actionString(dragSourceDragEvent.getDropAction()));
                sb.append(" mods=");
                sb.append(dragSourceDragEvent.getGestureModifiersEx());
            } else {
                sb.append(": e=");
                sb.append(dragSourceEvent);
            }
            String string = sb.toString();
            if (string.equals(this.lastAction)) {
                return;
            }
            logger.log(level, string);
            this.lastAction = string;
        }
    }

    private void disableSwingDragSupport(Component component) {
        if (component instanceof JTree) {
            ((JTree) component).setDragEnabled(false);
            return;
        }
        if (component instanceof JList) {
            ((JList) component).setDragEnabled(false);
            return;
        }
        if (component instanceof JTable) {
            ((JTable) component).setDragEnabled(false);
            return;
        }
        if (component instanceof JTextComponent) {
            ((JTextComponent) component).setDragEnabled(false);
        } else if (component instanceof JColorChooser) {
            ((JColorChooser) component).setDragEnabled(false);
        } else if (component instanceof JFileChooser) {
            ((JFileChooser) component).setDragEnabled(false);
        }
    }

    private Point getImageLocation(Point point) {
        point.translate(this.imageOffset.x, this.imageOffset.y);
        return point;
    }

    public static int getModifiers() {
        return modifiers;
    }

    public static Transferable getTransferable(DropTargetEvent dropTargetEvent) {
        if (dropTargetEvent instanceof DropTargetDragEvent) {
            try {
                return ((DropTargetDragEvent) dropTargetEvent).getTransferable();
            } catch (Exception unused) {
            }
        } else if (dropTargetEvent instanceof DropTargetDropEvent) {
            return ((DropTargetDropEvent) dropTargetEvent).getTransferable();
        }
        return transferable;
    }

    private int reduce(int i) {
        if ((i & 2) != 0 && i != 2) {
            return 2;
        }
        if ((i & 1) == 0 || i == 1) {
            return i;
        }
        return 1;
    }

    public int adjustDropAction(DragSourceEvent dragSourceEvent) {
        int dropAction = getDropAction(dragSourceEvent);
        if (dragSourceEvent instanceof DragSourceDragEvent) {
            DragSourceDragEvent dragSourceDragEvent = (DragSourceDragEvent) dragSourceEvent;
            if (dropAction == 0 && (dragSourceDragEvent.getGestureModifiersEx() & KEY_MASK) == 0) {
                return getAcceptableDropAction(dragSourceDragEvent.getTargetActions());
            }
        }
        return dropAction;
    }

    public boolean canDrag(DragGestureEvent dragGestureEvent) {
        int modifiersEx = dragGestureEvent.getTriggerEvent().getModifiersEx() & KEY_MASK;
        if (modifiersEx == 64) {
            return (this.supportedActions & 2) != 0;
        }
        if (modifiersEx == COPY_MASK) {
            return (this.supportedActions & 1) != 0;
        }
        return (modifiersEx == LINK_MASK && (this.supportedActions & 1073741824) == 0) ? false : true;
    }

    public Image createDragImage(GraphicsConfiguration graphicsConfiguration, Icon icon) {
        int iconWidth = icon.getIconWidth();
        int iconHeight = icon.getIconHeight();
        BufferedImage bufferedImageCreateCompatibleImage = graphicsConfiguration.createCompatibleImage(iconWidth, iconHeight, 3);
        Graphics2D graphics = bufferedImageCreateCompatibleImage.getGraphics();
        graphics.setComposite(AlphaComposite.Clear);
        graphics.fillRect(0, 0, iconWidth, iconHeight);
        graphics.setComposite(AlphaComposite.getInstance(2, this.ghostAlpha));
        icon.paintIcon(this.dragSource, graphics, 0, 0);
        graphics.dispose();
        return bufferedImageCreateCompatibleImage;
    }

    public void dragDropEnd(DragSourceDropEvent dragSourceDropEvent) {
        describe("end", dragSourceDropEvent);
        setModifiers(-1);
        transferable = UNKNOWN_TRANSFERABLE;
        if (this.ghost != null) {
            boolean dropSuccess = dragSourceDropEvent.getDropSuccess();
            GhostedDragImage ghostedDragImage = this.ghost;
            if (dropSuccess) {
                ghostedDragImage.dispose();
            } else {
                ghostedDragImage.returnToOrigin();
            }
            this.ghost = null;
        }
        dragSourceDropEvent.getDragSourceContext().getDragSource().removeDragSourceMotionListener(this);
        this.moved = false;
    }

    public void dragEnter(DragSourceDragEvent dragSourceDragEvent) {
        describe("enter", dragSourceDragEvent);
        GhostedDragImage ghostedDragImage = this.ghost;
        if (ghostedDragImage != null) {
            ghostedDragImage.move(getImageLocation(dragSourceDragEvent.getLocation()));
        }
        updateCursor(dragSourceDragEvent);
    }

    public void dragExit(DragSourceEvent dragSourceEvent) {
        describe("exit", dragSourceEvent);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v3, types: [int] */
    /* JADX WARN: Type inference failed for: r6v6, types: [com.sun.jna.platform.dnd.DragHandler, java.awt.dnd.DragSourceMotionListener] */
    /* JADX WARN: Type inference failed for: r6v8 */
    /* JADX WARN: Type inference failed for: r6v9 */
    public void dragGestureRecognized(DragGestureEvent dragGestureEvent) {
        DragHandler dragHandler;
        DragGestureEvent dragGestureEvent2;
        if ((dragGestureEvent.getDragAction() & this.supportedActions) == 0 || !canDrag(dragGestureEvent)) {
            return;
        }
        setModifiers(dragGestureEvent.getTriggerEvent().getModifiersEx() & KEY_MASK);
        Transferable transferable2 = getTransferable(dragGestureEvent);
        if (transferable2 == null) {
            return;
        }
        try {
            Point point = new Point(0, 0);
            Icon dragIcon = getDragIcon(dragGestureEvent, point);
            Point dragOrigin = dragGestureEvent.getDragOrigin();
            int i = point.x - dragOrigin.x;
            int i2 = point.y;
            dragHandler = dragOrigin.y;
            Point point2 = new Point(i, i2 - dragHandler);
            this.imageOffset = point2;
            Icon iconScaleDragIcon = scaleDragIcon(dragIcon, point2);
            try {
                if (iconScaleDragIcon == null || !DragSource.isDragImageSupported()) {
                    DragHandler dragHandler2 = this;
                    dragGestureEvent2 = dragGestureEvent;
                    if (iconScaleDragIcon != null) {
                        Point locationOnScreen = dragHandler2.dragSource.getLocationOnScreen();
                        locationOnScreen.translate(dragOrigin.x, dragOrigin.y);
                        GhostedDragImage ghostedDragImage = new GhostedDragImage(dragHandler2.dragSource, iconScaleDragIcon, dragHandler2.getImageLocation(locationOnScreen), new Point(-dragHandler2.imageOffset.x, -dragHandler2.imageOffset.y));
                        dragHandler2.ghost = ghostedDragImage;
                        ghostedDragImage.setAlpha(dragHandler2.ghostAlpha);
                    }
                    dragGestureEvent2.startDrag((Cursor) null, transferable2, dragHandler2);
                    dragHandler = dragHandler2;
                } else {
                    DragHandler dragHandler3 = this;
                    dragGestureEvent.startDrag((Cursor) null, createDragImage(dragGestureEvent.getComponent().getGraphicsConfiguration(), iconScaleDragIcon), this.imageOffset, transferable2, dragHandler3);
                    dragGestureEvent2 = dragGestureEvent;
                    dragHandler = dragHandler3;
                }
                dragHandler.dragStarted(dragGestureEvent2);
                dragHandler.moved = false;
                dragGestureEvent2.getDragSource().addDragSourceMotionListener((DragSourceMotionListener) dragHandler);
                transferable = transferable2;
            } catch (InvalidDnDOperationException unused) {
                GhostedDragImage ghostedDragImage2 = dragHandler.ghost;
                if (ghostedDragImage2 != null) {
                    ghostedDragImage2.dispose();
                    dragHandler.ghost = null;
                }
            }
        } catch (InvalidDnDOperationException unused2) {
            dragHandler = this;
        }
    }

    public void dragMouseMoved(DragSourceDragEvent dragSourceDragEvent) {
        describe("move", dragSourceDragEvent);
        GhostedDragImage ghostedDragImage = this.ghost;
        if (ghostedDragImage != null) {
            ghostedDragImage.move(getImageLocation(dragSourceDragEvent.getLocation()));
        }
        if (this.moved) {
            updateCursor(dragSourceDragEvent);
        }
        this.moved = true;
    }

    public void dragOver(DragSourceDragEvent dragSourceDragEvent) {
        describe("over", dragSourceDragEvent);
        GhostedDragImage ghostedDragImage = this.ghost;
        if (ghostedDragImage != null) {
            ghostedDragImage.move(getImageLocation(dragSourceDragEvent.getLocation()));
        }
        updateCursor(dragSourceDragEvent);
    }

    public void dragStarted(DragGestureEvent dragGestureEvent) {
    }

    public void dropActionChanged(DragSourceDragEvent dragSourceDragEvent) {
        describe("change", dragSourceDragEvent);
        setModifiers(dragSourceDragEvent.getGestureModifiersEx() & KEY_MASK);
        GhostedDragImage ghostedDragImage = this.ghost;
        if (ghostedDragImage != null) {
            ghostedDragImage.move(getImageLocation(dragSourceDragEvent.getLocation()));
        }
        updateCursor(dragSourceDragEvent);
    }

    public int getAcceptableDropAction(int i) {
        return reduce(i & this.supportedActions);
    }

    public Cursor getCursorForAction(int i) {
        if (i == 1) {
            return DragSource.DefaultCopyDrop;
        }
        if (i != 2) {
            return i != 1073741824 ? DragSource.DefaultMoveNoDrop : DragSource.DefaultLinkDrop;
        }
        return DragSource.DefaultMoveDrop;
    }

    public Icon getDragIcon(DragGestureEvent dragGestureEvent, Point point) {
        return null;
    }

    public int getDropAction(DragSourceEvent dragSourceEvent) {
        if (dragSourceEvent instanceof DragSourceDragEvent) {
            return ((DragSourceDragEvent) dragSourceEvent).getDropAction();
        }
        if (dragSourceEvent instanceof DragSourceDropEvent) {
            return ((DragSourceDropEvent) dragSourceEvent).getDropAction();
        }
        return 0;
    }

    public abstract Transferable getTransferable(DragGestureEvent dragGestureEvent);

    public Icon scaleDragIcon(Icon icon, Point point) {
        return icon;
    }

    public void setModifiers(int i) {
        modifiers = i;
    }

    public void updateCursor(DragSourceEvent dragSourceEvent) {
        if (this.fixCursor) {
            dragSourceEvent.getDragSourceContext().setCursor(getCursorForAction(adjustDropAction(dragSourceEvent)));
        }
    }
}
