package com.sun.jna.platform.dnd;

import com.sun.jna.platform.WindowUtils;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Area;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class GhostedDragImage {
    private static final float DEFAULT_ALPHA = 0.5f;
    private static final int SLIDE_INTERVAL = 33;
    private Window dragImage;
    private Point origin;

    public GhostedDragImage(Component component, final Icon icon, Point point, final Point point2) {
        Window window = new Window(JOptionPane.getRootFrame(), (component instanceof Window ? (Window) component : SwingUtilities.getWindowAncestor(component)).getGraphicsConfiguration()) { // from class: com.sun.jna.platform.dnd.GhostedDragImage.1
            private static final long serialVersionUID = 1;

            public Dimension getMaximumSize() {
                return getPreferredSize();
            }

            public Dimension getMinimumSize() {
                return getPreferredSize();
            }

            public Dimension getPreferredSize() {
                return new Dimension(icon.getIconWidth(), icon.getIconHeight());
            }

            public void paint(Graphics graphics) {
                icon.paintIcon(this, graphics, 0, 0);
            }
        };
        this.dragImage = window;
        window.setFocusableWindowState(false);
        this.dragImage.setName("###overrideRedirect###");
        Icon icon2 = new Icon() { // from class: com.sun.jna.platform.dnd.GhostedDragImage.2
            public int getIconHeight() {
                return icon.getIconHeight();
            }

            public int getIconWidth() {
                return icon.getIconWidth();
            }

            public void paintIcon(Component component2, Graphics graphics, int i, int i2) {
                Graphics graphicsCreate = graphics.create();
                Area area = new Area(new Rectangle(i, i2, getIconWidth(), getIconHeight()));
                area.subtract(new Area(new Rectangle((point2.x + i) - 1, (point2.y + i2) - 1, 3, 3)));
                graphicsCreate.setClip(area);
                icon.paintIcon(component2, graphicsCreate, i, i2);
                graphicsCreate.dispose();
            }
        };
        this.dragImage.pack();
        WindowUtils.setWindowMask(this.dragImage, icon2);
        WindowUtils.setWindowAlpha(this.dragImage, 0.5f);
        move(point);
        this.dragImage.setVisible(true);
    }

    public void dispose() {
        this.dragImage.dispose();
        this.dragImage = null;
    }

    public void move(Point point) {
        if (this.origin == null) {
            this.origin = point;
        }
        this.dragImage.setLocation(point.x, point.y);
    }

    public void returnToOrigin() {
        final Timer timer = new Timer(33, (ActionListener) null);
        timer.addActionListener(new ActionListener() { // from class: com.sun.jna.platform.dnd.GhostedDragImage.3
            public void actionPerformed(ActionEvent actionEvent) {
                Point locationOnScreen = GhostedDragImage.this.dragImage.getLocationOnScreen();
                Point point = new Point(GhostedDragImage.this.origin);
                int i = (point.x - locationOnScreen.x) / 2;
                int i2 = (point.y - locationOnScreen.y) / 2;
                if (i == 0 && i2 == 0) {
                    timer.stop();
                    GhostedDragImage.this.dispose();
                } else {
                    locationOnScreen.translate(i, i2);
                    GhostedDragImage.this.move(locationOnScreen);
                }
            }
        });
        timer.setInitialDelay(0);
        timer.start();
    }

    public void setAlpha(float f) {
        WindowUtils.setWindowAlpha(this.dragImage, f);
    }
}
