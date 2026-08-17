package com.sun.jna.platform;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.Platform;
import com.sun.jna.Pointer;
import com.sun.jna.platform.unix.X11;
import com.sun.jna.platform.win32.GDI32;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.Kernel32Util;
import com.sun.jna.platform.win32.PsapiUtil;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.Win32Exception;
import com.sun.jna.platform.win32.WinBase;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinError;
import com.sun.jna.platform.win32.WinGDI;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import defpackage.aca;
import defpackage.yba;
import java.awt.AWTEvent;
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.AWTEventListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerEvent;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.PathIterator;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.RootPaneContainer;
import javax.swing.SwingUtilities;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class WindowUtils {
    private static final Logger LOG = Logger.getLogger(WindowUtils.class.getName());
    public static final Shape MASK_NONE = null;
    private static final String TRANSPARENT_ALPHA = "transparent-alpha";
    private static final String TRANSPARENT_OLD_BG = "transparent-old-bg";
    private static final String TRANSPARENT_OLD_OPAQUE = "transparent-old-opaque";

    public static class HeavyweightForcer extends Window {
        private static final long serialVersionUID = 1;
        private final boolean packed;

        public HeavyweightForcer(Window window) {
            super(window);
            pack();
            this.packed = true;
        }

        public Rectangle getBounds() {
            return getOwner().getBounds();
        }

        public boolean isVisible() {
            return this.packed;
        }
    }

    public static class Holder {
        public static final NativeWindowUtils INSTANCE;
        public static boolean requiresVisible;

        static {
            if (Platform.isWindows()) {
                INSTANCE = new W32WindowUtils();
                return;
            }
            if (Platform.isMac()) {
                INSTANCE = new MacWindowUtils();
            } else if (!Platform.isX11()) {
                zwe.a("No support for ", System.getProperty("os.name"));
            } else {
                INSTANCE = new X11WindowUtils();
                requiresVisible = System.getProperty("java.version").matches("^1\\.4\\..*");
            }
        }

        private Holder() {
        }
    }

    public static class RepaintTrigger extends JComponent {
        private static final long serialVersionUID = 1;
        private final JComponent content;
        private Rectangle dirty;
        private final Listener listener = createListener();

        public class Listener extends WindowAdapter implements AWTEventListener, ComponentListener, HierarchyListener {
            public Listener() {
            }

            public void componentHidden(ComponentEvent componentEvent) {
            }

            public void componentMoved(ComponentEvent componentEvent) {
            }

            public void componentResized(ComponentEvent componentEvent) {
                RepaintTrigger repaintTrigger = RepaintTrigger.this;
                repaintTrigger.setSize(repaintTrigger.getParent().getSize());
                RepaintTrigger.this.repaint();
            }

            public void componentShown(ComponentEvent componentEvent) {
                RepaintTrigger.this.repaint();
            }

            public void eventDispatched(AWTEvent aWTEvent) {
                MouseEvent mouseEvent;
                Component component;
                if ((aWTEvent instanceof MouseEvent) && (component = (mouseEvent = (MouseEvent) aWTEvent).getComponent()) != null && SwingUtilities.isDescendingFrom(component, RepaintTrigger.this.content)) {
                    MouseEvent mouseEventConvertMouseEvent = SwingUtilities.convertMouseEvent(component, mouseEvent, RepaintTrigger.this.content);
                    Component deepestComponentAt = SwingUtilities.getDeepestComponentAt(RepaintTrigger.this.content, mouseEventConvertMouseEvent.getX(), mouseEventConvertMouseEvent.getY());
                    if (deepestComponentAt != null) {
                        RepaintTrigger.this.setCursor(deepestComponentAt.getCursor());
                    }
                }
            }

            public void hierarchyChanged(HierarchyEvent hierarchyEvent) {
                RepaintTrigger.this.repaint();
            }

            public void windowOpened(WindowEvent windowEvent) {
                RepaintTrigger.this.repaint();
            }
        }

        public RepaintTrigger(JComponent jComponent) {
            this.content = jComponent;
        }

        public void addNotify() {
            super.addNotify();
            Window windowAncestor = SwingUtilities.getWindowAncestor(this);
            setSize(getParent().getSize());
            windowAncestor.addComponentListener(this.listener);
            windowAncestor.addWindowListener(this.listener);
            Toolkit.getDefaultToolkit().addAWTEventListener(this.listener, 48L);
        }

        public Listener createListener() {
            return new Listener();
        }

        public void paintComponent(Graphics graphics) {
            Rectangle clipBounds = graphics.getClipBounds();
            Rectangle rectangle = this.dirty;
            if (rectangle != null && rectangle.contains(clipBounds)) {
                this.dirty = null;
                return;
            }
            Rectangle rectangle2 = this.dirty;
            if (rectangle2 == null) {
                this.dirty = clipBounds;
            } else {
                this.dirty = rectangle2.union(clipBounds);
            }
            this.content.repaint(this.dirty);
        }

        public void removeNotify() {
            Toolkit.getDefaultToolkit().removeAWTEventListener(this.listener);
            Window windowAncestor = SwingUtilities.getWindowAncestor(this);
            windowAncestor.removeComponentListener(this.listener);
            windowAncestor.removeWindowListener(this.listener);
            super.removeNotify();
        }
    }

    public static class X11WindowUtils extends NativeWindowUtils {
        private static final String OPACITY = "_NET_WM_WINDOW_OPACITY";
        private static final long OPAQUE = 4294967295L;
        private long[] alphaVisualIDs;
        private boolean didCheck;

        public interface PixmapSource {
            X11.Pixmap getPixmap(X11.Display display, X11.Window window);
        }

        public class X11TransparentContentPane extends NativeWindowUtils.TransparentContentPane {
            private static final long serialVersionUID = 1;
            private Memory buffer;
            private final int[] pixel;
            private int[] pixels;

            public X11TransparentContentPane(Container container) {
                super(container);
                this.pixel = new int[4];
            }

            /* JADX WARN: Type inference fix 'apply assigned field type' failed
            java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
            	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
            	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
            	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
             */
            @Override // com.sun.jna.platform.WindowUtils.NativeWindowUtils.TransparentContentPane
            public void paintDirect(BufferedImage bufferedImage, Rectangle rectangle) {
                Window windowAncestor = SwingUtilities.getWindowAncestor(this);
                X11 x11 = X11.INSTANCE;
                X11.Display displayXOpenDisplay = x11.XOpenDisplay(null);
                X11.Window drawable = X11WindowUtils.getDrawable(windowAncestor);
                Point point = new Point();
                X11.Window contentWindow = X11WindowUtils.getContentWindow(windowAncestor, displayXOpenDisplay, drawable, point);
                X11.GC gcXCreateGC = x11.XCreateGC(displayXOpenDisplay, contentWindow, new NativeLong(0L), null);
                Raster data = bufferedImage.getData();
                int i = rectangle.width;
                int i2 = rectangle.height;
                Memory memory = this.buffer;
                if (memory == null || memory.size() != i * i2 * 4) {
                    int i3 = i * i2;
                    this.buffer = new Memory(i3 * 4);
                    this.pixels = new int[i3];
                }
                for (int i4 = 0; i4 < i2; i4++) {
                    int i5 = 0;
                    while (i5 < i) {
                        data.getPixel(i5, i4, this.pixel);
                        int[] iArr = this.pixel;
                        int i6 = iArr[3] & 255;
                        this.pixels[(i4 * i) + i5] = ((iArr[1] & 255) << 8) | ((iArr[0] & 255) << 16) | (i6 << 24) | (iArr[2] & 255);
                        i5++;
                        data = data;
                    }
                }
                X11.XWindowAttributes xWindowAttributes = new X11.XWindowAttributes();
                x11.XGetWindowAttributes(displayXOpenDisplay, contentWindow, xWindowAttributes);
                X11.XImage xImageXCreateImage = x11.XCreateImage(displayXOpenDisplay, xWindowAttributes.visual, 32, 2, 0, this.buffer, i, i2, 32, i * 4);
                Memory memory2 = this.buffer;
                int[] iArr2 = this.pixels;
                memory2.write(0L, iArr2, 0, iArr2.length);
                point.x += rectangle.x;
                point.y += rectangle.y;
                x11.XPutImage(displayXOpenDisplay, contentWindow, gcXCreateGC, xImageXCreateImage, 0, 0, point.x, point.y, i, i2);
                x11.XFree(xImageXCreateImage.getPointer());
                x11.XFreeGC(displayXOpenDisplay, gcXCreateGC);
                x11.XCloseDisplay(displayXOpenDisplay);
            }
        }

        private X11WindowUtils() {
            this.alphaVisualIDs = new long[0];
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static X11.Pixmap createBitmap(X11.Display display, X11.Window window, Raster raster) {
            X11 x11 = X11.INSTANCE;
            Rectangle bounds = raster.getBounds();
            int i = bounds.x + bounds.width;
            int i2 = bounds.y + bounds.height;
            X11.Pixmap pixmapXCreatePixmap = x11.XCreatePixmap(display, window, i, i2, 1);
            X11.GC gcXCreateGC = x11.XCreateGC(display, pixmapXCreatePixmap, new NativeLong(0L), null);
            if (gcXCreateGC == null) {
                return null;
            }
            x11.XSetForeground(display, gcXCreateGC, new NativeLong(0L));
            x11.XFillRectangle(display, pixmapXCreatePixmap, gcXCreateGC, 0, 0, i, i2);
            final ArrayList arrayList = new ArrayList();
            try {
                RasterRangesUtils.outputOccupiedRanges(raster, new RasterRangesUtils.RangesOutput() { // from class: com.sun.jna.platform.WindowUtils.X11WindowUtils.1
                    @Override // com.sun.jna.platform.RasterRangesUtils.RangesOutput
                    public boolean outputRange(int i3, int i4, int i5, int i6) {
                        arrayList.add(new Rectangle(i3, i4, i5, i6));
                        return true;
                    }
                });
                X11.XRectangle[] xRectangleArr = (X11.XRectangle[]) new X11.XRectangle().toArray(arrayList.size());
                for (int i3 = 0; i3 < xRectangleArr.length; i3++) {
                    Rectangle rectangle = (Rectangle) arrayList.get(i3);
                    xRectangleArr[i3].x = (short) rectangle.x;
                    xRectangleArr[i3].y = (short) rectangle.y;
                    xRectangleArr[i3].width = (short) rectangle.width;
                    xRectangleArr[i3].height = (short) rectangle.height;
                    Pointer pointer = xRectangleArr[i3].getPointer();
                    pointer.setShort(0L, (short) rectangle.x);
                    pointer.setShort(2L, (short) rectangle.y);
                    pointer.setShort(4L, (short) rectangle.width);
                    pointer.setShort(6L, (short) rectangle.height);
                    xRectangleArr[i3].setAutoSynch(false);
                }
                x11.XSetForeground(display, gcXCreateGC, new NativeLong(1L));
                x11.XFillRectangles(display, pixmapXCreatePixmap, gcXCreateGC, xRectangleArr, xRectangleArr.length);
                return pixmapXCreatePixmap;
            } finally {
                x11.XFreeGC(display, gcXCreateGC);
            }
        }

        private synchronized long[] getAlphaVisualIDs() {
            if (this.didCheck) {
                return this.alphaVisualIDs;
            }
            this.didCheck = true;
            X11 x11 = X11.INSTANCE;
            X11.XVisualInfo xVisualInfoXGetVisualInfo = null;
            X11.Display displayXOpenDisplay = x11.XOpenDisplay(null);
            if (displayXOpenDisplay == null) {
                return this.alphaVisualIDs;
            }
            try {
                int iXDefaultScreen = x11.XDefaultScreen(displayXOpenDisplay);
                X11.XVisualInfo xVisualInfo = new X11.XVisualInfo();
                xVisualInfo.screen = iXDefaultScreen;
                xVisualInfo.depth = 32;
                xVisualInfo.c_class = 4;
                NativeLong nativeLong = new NativeLong(14L);
                IntByReference intByReference = new IntByReference();
                xVisualInfoXGetVisualInfo = x11.XGetVisualInfo(displayXOpenDisplay, nativeLong, xVisualInfo, intByReference);
                if (xVisualInfoXGetVisualInfo == null) {
                    if (xVisualInfoXGetVisualInfo != null) {
                        x11.XFree(xVisualInfoXGetVisualInfo.getPointer());
                    }
                    x11.XCloseDisplay(displayXOpenDisplay);
                    return this.alphaVisualIDs;
                }
                ArrayList arrayList = new ArrayList();
                X11.XVisualInfo[] xVisualInfoArr = (X11.XVisualInfo[]) xVisualInfoXGetVisualInfo.toArray(intByReference.getValue());
                int i = 0;
                for (int i2 = 0; i2 < xVisualInfoArr.length; i2++) {
                    X11.Xrender.XRenderPictFormat xRenderPictFormatXRenderFindVisualFormat = X11.Xrender.INSTANCE.XRenderFindVisualFormat(displayXOpenDisplay, xVisualInfoArr[i2].visual);
                    if (xRenderPictFormatXRenderFindVisualFormat.type == 1 && xRenderPictFormatXRenderFindVisualFormat.direct.alphaMask != 0) {
                        arrayList.add(xVisualInfoArr[i2].visualid);
                    }
                }
                this.alphaVisualIDs = new long[arrayList.size()];
                while (true) {
                    long[] jArr = this.alphaVisualIDs;
                    if (i >= jArr.length) {
                        x11.XFree(xVisualInfoXGetVisualInfo.getPointer());
                        x11.XCloseDisplay(displayXOpenDisplay);
                        return jArr;
                    }
                    jArr[i] = ((Number) arrayList.get(i)).longValue();
                    i++;
                }
            } catch (Throwable th) {
                if (xVisualInfoXGetVisualInfo != null) {
                    x11.XFree(xVisualInfoXGetVisualInfo.getPointer());
                }
                x11.XCloseDisplay(displayXOpenDisplay);
                throw th;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static X11.Window getContentWindow(Window window, X11.Display display, X11.Window window2, Point point) {
            X11.Window window3;
            if ((!(window instanceof Frame) || ((Frame) window).isUndecorated()) && (!(window instanceof Dialog) || ((Dialog) window).isUndecorated())) {
                return window2;
            }
            X11 x11 = X11.INSTANCE;
            X11.WindowByReference windowByReference = new X11.WindowByReference();
            X11.WindowByReference windowByReference2 = new X11.WindowByReference();
            PointerByReference pointerByReference = new PointerByReference();
            IntByReference intByReference = new IntByReference();
            x11.XQueryTree(display, window2, windowByReference, windowByReference2, pointerByReference, intByReference);
            Pointer value = pointerByReference.getValue();
            int[] intArray = value.getIntArray(0L, intByReference.getValue());
            if (intArray.length > 0) {
                window3 = new X11.Window(intArray[0]);
                X11.XWindowAttributes xWindowAttributes = new X11.XWindowAttributes();
                x11.XGetWindowAttributes(display, window3, xWindowAttributes);
                point.x = -xWindowAttributes.x;
                point.y = -xWindowAttributes.y;
            } else {
                window3 = window2;
            }
            x11.XFree(value);
            return window3;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static X11.Window getDrawable(Component component) {
            int componentID = (int) Native.getComponentID(component);
            if (componentID == 0) {
                return null;
            }
            return new X11.Window(componentID);
        }

        private static long getVisualID(GraphicsConfiguration graphicsConfiguration) {
            try {
                return ((Number) graphicsConfiguration.getClass().getMethod("getVisual", null).invoke(graphicsConfiguration, null)).longValue();
            } catch (Exception e) {
                e.printStackTrace();
                return -1L;
            }
        }

        private void setWindowShape(final Window window, final PixmapSource pixmapSource) {
            whenDisplayable(window, new Runnable() { // from class: com.sun.jna.platform.WindowUtils.X11WindowUtils.4
                @Override // java.lang.Runnable
                public void run() {
                    X11 x11 = X11.INSTANCE;
                    X11.Pixmap pixmap = null;
                    X11.Display displayXOpenDisplay = x11.XOpenDisplay(null);
                    if (displayXOpenDisplay == null) {
                        return;
                    }
                    try {
                        X11.Window drawable = X11WindowUtils.getDrawable(window);
                        pixmap = pixmapSource.getPixmap(displayXOpenDisplay, drawable);
                        X11.Xext.INSTANCE.XShapeCombineMask(displayXOpenDisplay, drawable, 0, 0, 0, pixmap == null ? X11.Pixmap.None : pixmap, 0);
                        if (pixmap != null) {
                            x11.XFreePixmap(displayXOpenDisplay, pixmap);
                        }
                        x11.XCloseDisplay(displayXOpenDisplay);
                        X11WindowUtils x11WindowUtils = X11WindowUtils.this;
                        x11WindowUtils.setForceHeavyweightPopups(x11WindowUtils.getWindow(window), pixmap != null);
                    } catch (Throwable th) {
                        if (pixmap != null) {
                            x11.XFreePixmap(displayXOpenDisplay, pixmap);
                        }
                        x11.XCloseDisplay(displayXOpenDisplay);
                        throw th;
                    }
                }
            });
        }

        @Override // com.sun.jna.platform.WindowUtils.NativeWindowUtils
        public GraphicsConfiguration getAlphaCompatibleGraphicsConfiguration() {
            if (isWindowAlphaSupported()) {
                for (GraphicsDevice graphicsDevice : GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()) {
                    GraphicsConfiguration[] configurations = graphicsDevice.getConfigurations();
                    for (int i = 0; i < configurations.length; i++) {
                        long visualID = getVisualID(configurations[i]);
                        for (long j : getAlphaVisualIDs()) {
                            if (visualID == j) {
                                return configurations[i];
                            }
                        }
                    }
                }
            }
            return super.getAlphaCompatibleGraphicsConfiguration();
        }

        @Override // com.sun.jna.platform.WindowUtils.NativeWindowUtils
        public boolean isWindowAlphaSupported() {
            return getAlphaVisualIDs().length > 0;
        }

        @Override // com.sun.jna.platform.WindowUtils.NativeWindowUtils
        public void setMask(Component component, final Raster raster) {
            setWindowShape(getWindow(component), new PixmapSource() { // from class: com.sun.jna.platform.WindowUtils.X11WindowUtils.5
                @Override // com.sun.jna.platform.WindowUtils.X11WindowUtils.PixmapSource
                public X11.Pixmap getPixmap(X11.Display display, X11.Window window) {
                    Raster raster2 = raster;
                    if (raster2 != null) {
                        return X11WindowUtils.createBitmap(display, window, raster2);
                    }
                    return null;
                }
            });
        }

        @Override // com.sun.jna.platform.WindowUtils.NativeWindowUtils
        public void setWindowAlpha(final Window window, final float f) {
            if (isWindowAlphaSupported()) {
                whenDisplayable(window, new Runnable() { // from class: com.sun.jna.platform.WindowUtils.X11WindowUtils.2
                    @Override // java.lang.Runnable
                    public void run() {
                        X11 x11 = X11.INSTANCE;
                        X11.Display displayXOpenDisplay = x11.XOpenDisplay(null);
                        if (displayXOpenDisplay == null) {
                            return;
                        }
                        try {
                            X11.Window drawable = X11WindowUtils.getDrawable(window);
                            float f2 = f;
                            if (f2 == 1.0f) {
                                x11.XDeleteProperty(displayXOpenDisplay, drawable, x11.XInternAtom(displayXOpenDisplay, X11WindowUtils.OPACITY, false));
                            } else {
                                x11.XChangeProperty(displayXOpenDisplay, drawable, x11.XInternAtom(displayXOpenDisplay, X11WindowUtils.OPACITY, false), X11.XA_CARDINAL, 32, 0, new IntByReference((int) (f2 * 4.2949673E9f)).getPointer(), 1);
                            }
                        } finally {
                            x11.XCloseDisplay(displayXOpenDisplay);
                        }
                    }
                });
            } else {
                c41.a("This X11 display does not provide a 32-bit visual");
            }
        }

        @Override // com.sun.jna.platform.WindowUtils.NativeWindowUtils
        public void setWindowTransparent(final Window window, final boolean z) {
            if (!(window instanceof RootPaneContainer)) {
                w01.a("Window must be a RootPaneContainer");
                return;
            }
            if (!isWindowAlphaSupported()) {
                c41.a("This X11 display does not provide a 32-bit visual");
            } else {
                if (!window.getGraphicsConfiguration().equals(getAlphaCompatibleGraphicsConfiguration())) {
                    yba.a("Window GraphicsConfiguration '", window.getGraphicsConfiguration(), "' does not support transparency");
                    return;
                }
                if (z == (window.getBackground() != null && window.getBackground().getAlpha() == 0)) {
                    return;
                }
                whenDisplayable(window, new Runnable() { // from class: com.sun.jna.platform.WindowUtils.X11WindowUtils.3
                    @Override // java.lang.Runnable
                    public void run() {
                        JRootPane rootPane = window.getRootPane();
                        JLayeredPane layeredPane = rootPane.getLayeredPane();
                        X11TransparentContentPane contentPane = rootPane.getContentPane();
                        if (contentPane instanceof X11TransparentContentPane) {
                            contentPane.setTransparent(z);
                        } else if (z) {
                            X11TransparentContentPane x11TransparentContentPane = X11WindowUtils.this.new X11TransparentContentPane(contentPane);
                            rootPane.setContentPane(x11TransparentContentPane);
                            layeredPane.add(new RepaintTrigger(x11TransparentContentPane), JLayeredPane.DRAG_LAYER);
                        }
                        X11WindowUtils.this.setLayersTransparent(window, z);
                        X11WindowUtils.this.setForceHeavyweightPopups(window, z);
                        X11WindowUtils.this.setDoubleBuffered(window, !z);
                    }
                });
            }
        }
    }

    public static List<DesktopWindow> getAllWindows(boolean z) {
        return getInstance().getAllWindows(z);
    }

    public static GraphicsConfiguration getAlphaCompatibleGraphicsConfiguration() {
        return getInstance().getAlphaCompatibleGraphicsConfiguration();
    }

    public static Dimension getIconSize(WinDef.HICON hicon) {
        return getInstance().getIconSize(hicon);
    }

    private static NativeWindowUtils getInstance() {
        return Holder.INSTANCE;
    }

    public static String getProcessFilePath(WinDef.HWND hwnd) {
        return getInstance().getProcessFilePath(hwnd);
    }

    public static BufferedImage getWindowIcon(WinDef.HWND hwnd) {
        return getInstance().getWindowIcon(hwnd);
    }

    public static Rectangle getWindowLocationAndSize(WinDef.HWND hwnd) {
        return getInstance().getWindowLocationAndSize(hwnd);
    }

    public static String getWindowTitle(WinDef.HWND hwnd) {
        return getInstance().getWindowTitle(hwnd);
    }

    public static boolean isWindowAlphaSupported() {
        return getInstance().isWindowAlphaSupported();
    }

    public static void setComponentMask(Component component, Shape shape) {
        getInstance().setWindowMask(component, shape);
    }

    public static void setWindowAlpha(Window window, float f) {
        getInstance().setWindowAlpha(window, Math.max(0.0f, Math.min(f, 1.0f)));
    }

    public static void setWindowMask(Window window, Shape shape) {
        getInstance().setWindowMask((Component) window, shape);
    }

    public static void setWindowTransparent(Window window, boolean z) {
        getInstance().setWindowTransparent(window, z);
    }

    public static void setWindowMask(Window window, Icon icon) {
        getInstance().setWindowMask((Component) window, icon);
    }

    public static abstract class NativeWindowUtils {

        public abstract class TransparentContentPane extends JPanel implements AWTEventListener {
            private static final long serialVersionUID = 1;
            private boolean transparent;

            public TransparentContentPane(Container container) {
                super(new BorderLayout());
                add(container, "Center");
                setTransparent(true);
                if (container instanceof JPanel) {
                    ((JComponent) container).setOpaque(false);
                }
            }

            public void addNotify() {
                super.addNotify();
                Toolkit.getDefaultToolkit().addAWTEventListener(this, 2L);
            }

            public void eventDispatched(AWTEvent aWTEvent) {
                if (aWTEvent.getID() == 300) {
                    ContainerEvent containerEvent = (ContainerEvent) aWTEvent;
                    if (SwingUtilities.isDescendingFrom(containerEvent.getChild(), this)) {
                        NativeWindowUtils.this.setDoubleBuffered(containerEvent.getChild(), false);
                    }
                }
            }

            public void paint(Graphics graphics) {
                if (!this.transparent) {
                    super.paint(graphics);
                    return;
                }
                Rectangle clipBounds = graphics.getClipBounds();
                int i = clipBounds.width;
                int i2 = clipBounds.height;
                if (getWidth() <= 0 || getHeight() <= 0) {
                    return;
                }
                BufferedImage bufferedImage = new BufferedImage(i, i2, 3);
                Graphics2D graphics2DCreateGraphics = bufferedImage.createGraphics();
                graphics2DCreateGraphics.setComposite(AlphaComposite.Clear);
                graphics2DCreateGraphics.fillRect(0, 0, i, i2);
                graphics2DCreateGraphics.dispose();
                Graphics2D graphics2DCreateGraphics2 = bufferedImage.createGraphics();
                graphics2DCreateGraphics2.translate(-clipBounds.x, -clipBounds.y);
                super.paint(graphics2DCreateGraphics2);
                graphics2DCreateGraphics2.dispose();
                paintDirect(bufferedImage, clipBounds);
            }

            public abstract void paintDirect(BufferedImage bufferedImage, Rectangle rectangle);

            public void removeNotify() {
                Toolkit.getDefaultToolkit().removeAWTEventListener(this);
                super.removeNotify();
            }

            public void setTransparent(boolean z) {
                this.transparent = z;
                setOpaque(!z);
                setDoubleBuffered(!z);
                repaint();
            }
        }

        public List<DesktopWindow> getAllWindows(boolean z) {
            throw new UnsupportedOperationException("This platform is not supported, yet.");
        }

        public GraphicsConfiguration getAlphaCompatibleGraphicsConfiguration() {
            return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        }

        public Dimension getIconSize(WinDef.HICON hicon) {
            throw new UnsupportedOperationException("This platform is not supported, yet.");
        }

        public String getProcessFilePath(WinDef.HWND hwnd) {
            throw new UnsupportedOperationException("This platform is not supported, yet.");
        }

        public Window getWindow(Component component) {
            return component instanceof Window ? (Window) component : SwingUtilities.getWindowAncestor(component);
        }

        public BufferedImage getWindowIcon(WinDef.HWND hwnd) {
            throw new UnsupportedOperationException("This platform is not supported, yet.");
        }

        public Rectangle getWindowLocationAndSize(WinDef.HWND hwnd) {
            throw new UnsupportedOperationException("This platform is not supported, yet.");
        }

        public String getWindowTitle(WinDef.HWND hwnd) {
            throw new UnsupportedOperationException("This platform is not supported, yet.");
        }

        public boolean isWindowAlphaSupported() {
            return false;
        }

        public void setDoubleBuffered(Component component, boolean z) {
            if (component instanceof JComponent) {
                ((JComponent) component).setDoubleBuffered(z);
            }
            if ((component instanceof JRootPane) && z) {
                ((JRootPane) component).setDoubleBuffered(true);
                return;
            }
            if (component instanceof Container) {
                for (Component component2 : ((Container) component).getComponents()) {
                    setDoubleBuffered(component2, z);
                }
            }
        }

        public void setForceHeavyweightPopups(Window window, boolean z) {
            if (window instanceof HeavyweightForcer) {
                return;
            }
            for (Window window2 : window.getOwnedWindows()) {
                if (window2 instanceof HeavyweightForcer) {
                    if (z) {
                        return;
                    } else {
                        window2.dispose();
                    }
                }
            }
            Boolean boolValueOf = Boolean.valueOf(System.getProperty("jna.force_hw_popups", "true"));
            if (z && boolValueOf.booleanValue()) {
                new HeavyweightForcer(window);
            }
        }

        public void setLayersTransparent(Window window, boolean z) {
            Color color = z ? new Color(0, 0, 0, 0) : null;
            if (window instanceof RootPaneContainer) {
                JRootPane rootPane = ((RootPaneContainer) window).getRootPane();
                JLayeredPane layeredPane = rootPane.getLayeredPane();
                JComponent contentPane = rootPane.getContentPane();
                JComponent jComponent = contentPane instanceof JComponent ? contentPane : null;
                if (z) {
                    layeredPane.putClientProperty(WindowUtils.TRANSPARENT_OLD_OPAQUE, Boolean.valueOf(layeredPane.isOpaque()));
                    layeredPane.setOpaque(false);
                    rootPane.putClientProperty(WindowUtils.TRANSPARENT_OLD_OPAQUE, Boolean.valueOf(rootPane.isOpaque()));
                    rootPane.setOpaque(false);
                    if (jComponent != null) {
                        jComponent.putClientProperty(WindowUtils.TRANSPARENT_OLD_OPAQUE, Boolean.valueOf(jComponent.isOpaque()));
                        jComponent.setOpaque(false);
                    }
                    rootPane.putClientProperty(WindowUtils.TRANSPARENT_OLD_BG, rootPane.getParent().getBackground());
                } else {
                    Boolean bool = Boolean.TRUE;
                    layeredPane.setOpaque(bool.equals(layeredPane.getClientProperty(WindowUtils.TRANSPARENT_OLD_OPAQUE)));
                    layeredPane.putClientProperty(WindowUtils.TRANSPARENT_OLD_OPAQUE, (Object) null);
                    rootPane.setOpaque(bool.equals(rootPane.getClientProperty(WindowUtils.TRANSPARENT_OLD_OPAQUE)));
                    rootPane.putClientProperty(WindowUtils.TRANSPARENT_OLD_OPAQUE, (Object) null);
                    if (jComponent != null) {
                        jComponent.setOpaque(bool.equals(jComponent.getClientProperty(WindowUtils.TRANSPARENT_OLD_OPAQUE)));
                        jComponent.putClientProperty(WindowUtils.TRANSPARENT_OLD_OPAQUE, (Object) null);
                    }
                    color = (Color) rootPane.getClientProperty(WindowUtils.TRANSPARENT_OLD_BG);
                    rootPane.putClientProperty(WindowUtils.TRANSPARENT_OLD_BG, (Object) null);
                }
            }
            window.setBackground(color);
        }

        public void setMask(Component component, Raster raster) {
            throw new UnsupportedOperationException("Window masking is not available");
        }

        public void setWindowAlpha(Window window, float f) {
        }

        public void setWindowMask(Component component, Raster raster) {
            if (component.isLightweight()) {
                aca.a("Component must be heavyweight: ", component);
            } else {
                setMask(component, raster);
            }
        }

        public void setWindowTransparent(Window window, boolean z) {
        }

        public Raster toRaster(Shape shape) {
            if (shape == WindowUtils.MASK_NONE) {
                return null;
            }
            Rectangle bounds = shape.getBounds();
            if (bounds.width <= 0 || bounds.height <= 0) {
                return null;
            }
            BufferedImage bufferedImage = new BufferedImage(bounds.x + bounds.width, bounds.y + bounds.height, 12);
            Graphics2D graphics2DCreateGraphics = bufferedImage.createGraphics();
            graphics2DCreateGraphics.setColor(Color.black);
            graphics2DCreateGraphics.fillRect(0, 0, bounds.x + bounds.width, bounds.y + bounds.height);
            graphics2DCreateGraphics.setColor(Color.white);
            graphics2DCreateGraphics.fill(shape);
            return bufferedImage.getRaster();
        }

        public Shape toShape(Raster raster) {
            final Area area = new Area(new Rectangle(0, 0, 0, 0));
            RasterRangesUtils.outputOccupiedRanges(raster, new RasterRangesUtils.RangesOutput() { // from class: com.sun.jna.platform.WindowUtils.NativeWindowUtils.3
                @Override // com.sun.jna.platform.RasterRangesUtils.RangesOutput
                public boolean outputRange(int i, int i2, int i3, int i4) {
                    area.add(new Area(new Rectangle(i, i2, i3, i4)));
                    return true;
                }
            });
            return area;
        }

        public void whenDisplayable(Component component, final Runnable runnable) {
            if (component.isDisplayable() && (!Holder.requiresVisible || component.isVisible())) {
                runnable.run();
            } else if (Holder.requiresVisible) {
                getWindow(component).addWindowListener(new WindowAdapter() { // from class: com.sun.jna.platform.WindowUtils.NativeWindowUtils.1
                    public void windowClosed(WindowEvent windowEvent) {
                        windowEvent.getWindow().removeWindowListener(this);
                    }

                    public void windowOpened(WindowEvent windowEvent) {
                        windowEvent.getWindow().removeWindowListener(this);
                        runnable.run();
                    }
                });
            } else {
                component.addHierarchyListener(new HierarchyListener() { // from class: com.sun.jna.platform.WindowUtils.NativeWindowUtils.2
                    public void hierarchyChanged(HierarchyEvent hierarchyEvent) {
                        if ((hierarchyEvent.getChangeFlags() & 2) == 0 || !hierarchyEvent.getComponent().isDisplayable()) {
                            return;
                        }
                        hierarchyEvent.getComponent().removeHierarchyListener(this);
                        runnable.run();
                    }
                });
            }
        }

        public void setWindowMask(Component component, Shape shape) {
            setWindowMask(component, toRaster(shape));
        }

        public void setWindowMask(Component component, Icon icon) {
            setWindowMask(component, toRaster(component, icon));
        }

        public Raster toRaster(Component component, Icon icon) {
            if (icon == null) {
                return null;
            }
            Rectangle rectangle = new Rectangle(0, 0, icon.getIconWidth(), icon.getIconHeight());
            BufferedImage bufferedImage = new BufferedImage(rectangle.width, rectangle.height, 2);
            Graphics2D graphics2DCreateGraphics = bufferedImage.createGraphics();
            graphics2DCreateGraphics.setComposite(AlphaComposite.Clear);
            graphics2DCreateGraphics.fillRect(0, 0, rectangle.width, rectangle.height);
            graphics2DCreateGraphics.setComposite(AlphaComposite.SrcOver);
            icon.paintIcon(component, graphics2DCreateGraphics, 0, 0);
            return bufferedImage.getAlphaRaster();
        }
    }

    public static class MacWindowUtils extends NativeWindowUtils {
        private static final String WDRAG = "apple.awt.draggableWindowBackground";

        public static class OSXMaskingContentPane extends JPanel {
            private static final long serialVersionUID = 1;
            private Shape shape;

            public OSXMaskingContentPane(Component component) {
                super(new BorderLayout());
                if (component != null) {
                    add(component, "Center");
                }
            }

            public void paint(Graphics graphics) {
                Graphics2D graphics2DCreate = graphics.create();
                graphics2DCreate.setComposite(AlphaComposite.Clear);
                graphics2DCreate.fillRect(0, 0, getWidth(), getHeight());
                graphics2DCreate.dispose();
                if (this.shape == null) {
                    super.paint(graphics);
                    return;
                }
                Graphics2D graphics2DCreate2 = graphics.create();
                graphics2DCreate2.setClip(this.shape);
                super.paint(graphics2DCreate2);
                graphics2DCreate2.dispose();
            }

            public void setMask(Shape shape) {
                this.shape = shape;
                repaint();
            }
        }

        private MacWindowUtils() {
        }

        private void fixWindowDragging(Window window, String str) {
            if (window instanceof RootPaneContainer) {
                JRootPane rootPane = ((RootPaneContainer) window).getRootPane();
                if (((Boolean) rootPane.getClientProperty(WDRAG)) == null) {
                    rootPane.putClientProperty(WDRAG, Boolean.FALSE);
                    if (window.isDisplayable()) {
                        WindowUtils.LOG.log(Level.WARNING, "{0}(): To avoid content dragging, {1}() must be called before the window is realized, or apple.awt.draggableWindowBackground must be set to Boolean.FALSE before the window is realized.  If you really want content dragging, set apple.awt.draggableWindowBackground on the window''s root pane to Boolean.TRUE before calling {2}() to hide this message.", new Object[]{str, str, str});
                    }
                }
            }
        }

        private OSXMaskingContentPane installMaskingPane(Window window) {
            if (!(window instanceof RootPaneContainer)) {
                Component component = window.getComponentCount() > 0 ? window.getComponent(0) : null;
                if (component instanceof OSXMaskingContentPane) {
                    return (OSXMaskingContentPane) component;
                }
                OSXMaskingContentPane oSXMaskingContentPane = new OSXMaskingContentPane(component);
                window.add(oSXMaskingContentPane);
                return oSXMaskingContentPane;
            }
            RootPaneContainer rootPaneContainer = (RootPaneContainer) window;
            OSXMaskingContentPane contentPane = rootPaneContainer.getContentPane();
            if (contentPane instanceof OSXMaskingContentPane) {
                return contentPane;
            }
            OSXMaskingContentPane oSXMaskingContentPane2 = new OSXMaskingContentPane(contentPane);
            rootPaneContainer.setContentPane(oSXMaskingContentPane2);
            return oSXMaskingContentPane2;
        }

        private void setBackgroundTransparent(Window window, boolean z, String str) {
            JRootPane rootPane = window instanceof RootPaneContainer ? ((RootPaneContainer) window).getRootPane() : null;
            if (z) {
                if (rootPane != null) {
                    rootPane.putClientProperty(WindowUtils.TRANSPARENT_OLD_BG, window.getBackground());
                }
                window.setBackground(new Color(0, 0, 0, 0));
            } else if (rootPane != null) {
                Color color = (Color) rootPane.getClientProperty(WindowUtils.TRANSPARENT_OLD_BG);
                if (color != null) {
                    color = new Color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
                }
                window.setBackground(color);
                rootPane.putClientProperty(WindowUtils.TRANSPARENT_OLD_BG, (Object) null);
            } else {
                window.setBackground((Color) null);
            }
            fixWindowDragging(window, str);
        }

        @Override // com.sun.jna.platform.WindowUtils.NativeWindowUtils
        public boolean isWindowAlphaSupported() {
            return true;
        }

        @Override // com.sun.jna.platform.WindowUtils.NativeWindowUtils
        public void setWindowAlpha(final Window window, final float f) {
            if (window instanceof RootPaneContainer) {
                ((RootPaneContainer) window).getRootPane().putClientProperty("Window.alpha", Float.valueOf(f));
                fixWindowDragging(window, "setWindowAlpha");
            }
            whenDisplayable(window, new Runnable() { // from class: com.sun.jna.platform.WindowUtils.MacWindowUtils.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        Object objInvoke = window.getClass().getMethod("getPeer", null).invoke(window, null);
                        objInvoke.getClass().getMethod("setAlpha", Float.TYPE).invoke(objInvoke, Float.valueOf(f));
                    } catch (Exception unused) {
                    }
                }
            });
        }

        @Override // com.sun.jna.platform.WindowUtils.NativeWindowUtils
        public void setWindowMask(Component component, Raster raster) {
            if (raster != null) {
                setWindowMask(component, toShape(raster));
            } else {
                setWindowMask(component, (Shape) new Rectangle(0, 0, component.getWidth(), component.getHeight()));
            }
        }

        @Override // com.sun.jna.platform.WindowUtils.NativeWindowUtils
        public void setWindowTransparent(Window window, boolean z) {
            if (z != (window.getBackground() != null && window.getBackground().getAlpha() == 0)) {
                setBackgroundTransparent(window, z, "setWindowTransparent");
            }
        }

        @Override // com.sun.jna.platform.WindowUtils.NativeWindowUtils
        public void setWindowMask(Component component, Shape shape) {
            if (component instanceof Window) {
                Window window = (Window) component;
                installMaskingPane(window).setMask(shape);
                setBackgroundTransparent(window, shape != WindowUtils.MASK_NONE, "setWindowMask");
            }
        }
    }

    public static class W32WindowUtils extends NativeWindowUtils {

        public class W32TransparentContentPane extends NativeWindowUtils.TransparentContentPane {
            private static final long serialVersionUID = 1;
            private Dimension bitmapSize;
            private WinDef.HBITMAP hBitmap;
            private WinDef.HDC memDC;
            private Pointer pbits;

            public W32TransparentContentPane(Container container) {
                super(container);
            }

            private void disposeBackingStore() {
                GDI32 gdi32 = GDI32.INSTANCE;
                WinDef.HBITMAP hbitmap = this.hBitmap;
                if (hbitmap != null) {
                    gdi32.DeleteObject(hbitmap);
                    this.hBitmap = null;
                }
                WinDef.HDC hdc = this.memDC;
                if (hdc != null) {
                    gdi32.DeleteDC(hdc);
                    this.memDC = null;
                }
            }

            /* JADX WARN: Code duplicated, block: B:70:0x01bc A[ADDED_TO_REGION] */
            @Override // com.sun.jna.platform.WindowUtils.NativeWindowUtils.TransparentContentPane
            public void paintDirect(BufferedImage bufferedImage, Rectangle rectangle) throws Throwable {
                WinDef.HDC hdc;
                WinDef.HWND hwnd;
                WinDef.HDC hdc2;
                WinDef.HDC hdc3;
                int i;
                int i2;
                Window windowAncestor = SwingUtilities.getWindowAncestor(this);
                GDI32 gdi32 = GDI32.INSTANCE;
                User32 user32 = User32.INSTANCE;
                Point pointConvertPoint = SwingUtilities.convertPoint(this, rectangle.x, rectangle.y, windowAncestor);
                int i3 = rectangle.width;
                int i4 = rectangle.height;
                int width = windowAncestor.getWidth();
                int height = windowAncestor.getHeight();
                WinNT.HANDLE handle = null;
                WinDef.HDC hdcGetDC = user32.GetDC(null);
                try {
                    if (this.memDC == null) {
                        try {
                            this.memDC = gdi32.CreateCompatibleDC(hdcGetDC);
                        } catch (Throwable th) {
                            th = th;
                            hdc = hdcGetDC;
                            user32 = user32;
                            hwnd = null;
                            user32.ReleaseDC(hwnd, hdc);
                            hdc2 = this.memDC;
                            if (hdc2 != null && handle != null) {
                                gdi32.SelectObject(hdc2, handle);
                            }
                            throw th;
                        }
                    }
                    if (this.hBitmap == null || !windowAncestor.getSize().equals(this.bitmapSize)) {
                        WinDef.HBITMAP hbitmap = this.hBitmap;
                        if (hbitmap != null) {
                            gdi32.DeleteObject(hbitmap);
                            this.hBitmap = null;
                        }
                        WinGDI.BITMAPINFO bitmapinfo = new WinGDI.BITMAPINFO();
                        WinGDI.BITMAPINFOHEADER bitmapinfoheader = bitmapinfo.bmiHeader;
                        bitmapinfoheader.biWidth = width;
                        bitmapinfoheader.biHeight = height;
                        bitmapinfoheader.biPlanes = (short) 1;
                        bitmapinfoheader.biBitCount = (short) 32;
                        bitmapinfoheader.biCompression = 0;
                        bitmapinfoheader.biSizeImage = width * height * 4;
                        PointerByReference pointerByReference = new PointerByReference();
                        try {
                            hdc3 = hdcGetDC;
                            i = 0;
                            i2 = 4;
                            try {
                                this.hBitmap = gdi32.CreateDIBSection(this.memDC, bitmapinfo, 0, pointerByReference, null, 0);
                                this.pbits = pointerByReference.getValue();
                                this.bitmapSize = new Dimension(width, height);
                            } catch (Throwable th2) {
                                th = th2;
                                hdc = hdc3;
                                hwnd = null;
                                handle = null;
                                user32.ReleaseDC(hwnd, hdc);
                                hdc2 = this.memDC;
                                if (hdc2 != null) {
                                    gdi32.SelectObject(hdc2, handle);
                                }
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            hdc = hdcGetDC;
                            hwnd = null;
                            handle = null;
                            user32.ReleaseDC(hwnd, hdc);
                            hdc2 = this.memDC;
                            if (hdc2 != null) {
                                gdi32.SelectObject(hdc2, handle);
                            }
                            throw th;
                        }
                    } else {
                        hdc3 = hdcGetDC;
                        i = 0;
                        i2 = 4;
                    }
                    WinNT.HANDLE handleSelectObject = gdi32.SelectObject(this.memDC, this.hBitmap);
                    try {
                        Raster data = bufferedImage.getData();
                        int[] iArr = new int[i2];
                        int[] iArr2 = new int[i3];
                        int i5 = i;
                        while (i5 < i4) {
                            int i6 = i;
                            while (i6 < i3) {
                                data.getPixel(i6, i5, iArr);
                                int i7 = i2;
                                int i8 = i4;
                                iArr2[i6] = ((iArr[i] & 255) << 16) | ((iArr[1] & 255) << 8) | ((iArr[3] & 255) << 24) | (iArr[2] & 255);
                                i6++;
                                i4 = i8;
                                i2 = i7;
                                i5 = i5;
                            }
                            int i9 = i4;
                            int i10 = i5;
                            int i11 = i2;
                            WinNT.HANDLE handle2 = handleSelectObject;
                            try {
                                Raster raster = data;
                                int i12 = i3;
                                int[] iArr3 = iArr;
                                handle = handle2;
                                try {
                                    this.pbits.write(((((height - (pointConvertPoint.y + i10)) - 1) * width) + pointConvertPoint.x) * 4, iArr2, 0, i12);
                                    i5 = i10 + 1;
                                    data = raster;
                                    iArr = iArr3;
                                    handleSelectObject = handle;
                                    i4 = i9;
                                    i2 = i11;
                                    i3 = i12;
                                } catch (Throwable th4) {
                                    th = th4;
                                    user32 = user32;
                                    hdc = hdc3;
                                    hwnd = null;
                                    user32.ReleaseDC(hwnd, hdc);
                                    hdc2 = this.memDC;
                                    if (hdc2 != null) {
                                        gdi32.SelectObject(hdc2, handle);
                                    }
                                    throw th;
                                }
                            } catch (Throwable th5) {
                                th = th5;
                                handle = handle2;
                                user32 = user32;
                                hdc = hdc3;
                                hwnd = null;
                                user32.ReleaseDC(hwnd, hdc);
                                hdc2 = this.memDC;
                                if (hdc2 != null) {
                                    gdi32.SelectObject(hdc2, handle);
                                }
                                throw th;
                            }
                        }
                        handle = handleSelectObject;
                        WinUser.SIZE size = new WinUser.SIZE();
                        size.cx = windowAncestor.getWidth();
                        size.cy = windowAncestor.getHeight();
                        WinDef.POINT point = new WinDef.POINT();
                        point.x = windowAncestor.getX();
                        point.y = windowAncestor.getY();
                        WinDef.POINT point2 = new WinDef.POINT();
                        WinUser.BLENDFUNCTION blendfunction = new WinUser.BLENDFUNCTION();
                        WinDef.HWND hWnd = W32WindowUtils.this.getHWnd(windowAncestor);
                        ByteByReference byteByReference = new ByteByReference();
                        IntByReference intByReference = new IntByReference();
                        byte alpha = W32WindowUtils.this.getAlpha(windowAncestor);
                        try {
                            if (user32.GetLayeredWindowAttributes(hWnd, null, byteByReference, intByReference) && (intByReference.getValue() & 2) != 0) {
                                alpha = byteByReference.getValue();
                            }
                        } catch (UnsatisfiedLinkError unused) {
                        }
                        blendfunction.SourceConstantAlpha = alpha;
                        blendfunction.AlphaFormat = (byte) 1;
                        user32 = user32;
                        hdc = hdc3;
                        try {
                            user32.UpdateLayeredWindow(hWnd, hdc, point, size, this.memDC, point2, 0, blendfunction, 2);
                            user32.ReleaseDC(null, hdc);
                            WinDef.HDC hdc4 = this.memDC;
                            if (hdc4 == null || handle == null) {
                                return;
                            }
                            gdi32.SelectObject(hdc4, handle);
                        } catch (Throwable th6) {
                            th = th6;
                            hwnd = null;
                            user32.ReleaseDC(hwnd, hdc);
                            hdc2 = this.memDC;
                            if (hdc2 != null) {
                                gdi32.SelectObject(hdc2, handle);
                            }
                            throw th;
                        }
                    } catch (Throwable th7) {
                        th = th7;
                        handle = handleSelectObject;
                    }
                } catch (Throwable th8) {
                    th = th8;
                    hdc = hdcGetDC;
                }
            }

            @Override // com.sun.jna.platform.WindowUtils.NativeWindowUtils.TransparentContentPane
            public void removeNotify() {
                super.removeNotify();
                disposeBackingStore();
            }

            @Override // com.sun.jna.platform.WindowUtils.NativeWindowUtils.TransparentContentPane
            public void setTransparent(boolean z) {
                super.setTransparent(z);
                if (z) {
                    return;
                }
                disposeBackingStore();
            }
        }

        private W32WindowUtils() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public byte getAlpha(Window window) {
            Byte b;
            if (!(window instanceof RootPaneContainer) || (b = (Byte) ((RootPaneContainer) window).getRootPane().getClientProperty(WindowUtils.TRANSPARENT_ALPHA)) == null) {
                return (byte) -1;
            }
            return b.byteValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public WinDef.HWND getHWnd(Component component) {
            WinDef.HWND hwnd = new WinDef.HWND();
            hwnd.setPointer(Native.getComponentPointer(component));
            return hwnd;
        }

        private void setMask(Component component, Area area) {
            GDI32 gdi32 = GDI32.INSTANCE;
            PathIterator pathIterator = area.getPathIterator((AffineTransform) null);
            int i = pathIterator.getWindingRule() == 1 ? 2 : 1;
            float[] fArr = new float[6];
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            int i2 = 0;
            while (!pathIterator.isDone()) {
                int iCurrentSegment = pathIterator.currentSegment(fArr);
                if (iCurrentSegment == 0) {
                    arrayList.add(new WinDef.POINT((int) fArr[0], (int) fArr[1]));
                    i2 = 1;
                } else if (iCurrentSegment == 1) {
                    i2++;
                    arrayList.add(new WinDef.POINT((int) fArr[0], (int) fArr[1]));
                } else {
                    if (iCurrentSegment != 4) {
                        y04.a("Area is not polygonal: ", area);
                        return;
                    }
                    arrayList2.add(Integer.valueOf(i2));
                }
                pathIterator.next();
            }
            WinDef.POINT[] pointArr = (WinDef.POINT[]) new WinDef.POINT().toArray(arrayList.size());
            WinDef.POINT[] pointArr2 = (WinDef.POINT[]) arrayList.toArray(new WinDef.POINT[arrayList.size()]);
            for (int i3 = 0; i3 < pointArr.length; i3++) {
                WinDef.POINT point = pointArr[i3];
                WinDef.POINT point2 = pointArr2[i3];
                point.x = point2.x;
                point.y = point2.y;
            }
            int size = arrayList2.size();
            int[] iArr = new int[size];
            for (int i4 = 0; i4 < size; i4++) {
                iArr[i4] = ((Integer) arrayList2.get(i4)).intValue();
            }
            setWindowRegion(component, gdi32.CreatePolyPolygonRgn(pointArr, iArr, size, i));
        }

        private void setWindowRegion(final Component component, final WinDef.HRGN hrgn) {
            whenDisplayable(component, new Runnable() { // from class: com.sun.jna.platform.WindowUtils.W32WindowUtils.3
                @Override // java.lang.Runnable
                public void run() {
                    GDI32 gdi32 = GDI32.INSTANCE;
                    try {
                        boolean z = true;
                        User32.INSTANCE.SetWindowRgn(W32WindowUtils.this.getHWnd(component), hrgn, true);
                        W32WindowUtils w32WindowUtils = W32WindowUtils.this;
                        Window window = w32WindowUtils.getWindow(component);
                        if (hrgn == null) {
                            z = false;
                        }
                        w32WindowUtils.setForceHeavyweightPopups(window, z);
                    } finally {
                        gdi32.DeleteObject(hrgn);
                    }
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void storeAlpha(Window window, byte b) {
            if (window instanceof RootPaneContainer) {
                ((RootPaneContainer) window).getRootPane().putClientProperty(WindowUtils.TRANSPARENT_ALPHA, b == -1 ? null : Byte.valueOf(b));
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean usingUpdateLayeredWindow(Window window) {
            return (window instanceof RootPaneContainer) && ((RootPaneContainer) window).getRootPane().getClientProperty(WindowUtils.TRANSPARENT_OLD_BG) != null;
        }

        @Override // com.sun.jna.platform.WindowUtils.NativeWindowUtils
        public List<DesktopWindow> getAllWindows(final boolean z) {
            final LinkedList linkedList = new LinkedList();
            if (User32.INSTANCE.EnumWindows(new WinUser.WNDENUMPROC() { // from class: com.sun.jna.platform.WindowUtils.W32WindowUtils.5
                @Override // com.sun.jna.platform.win32.WinUser.WNDENUMPROC
                public boolean callback(WinDef.HWND hwnd, Pointer pointer) {
                    try {
                        if (z && !User32.INSTANCE.IsWindowVisible(hwnd)) {
                            return true;
                        }
                        linkedList.add(new DesktopWindow(hwnd, W32WindowUtils.this.getWindowTitle(hwnd), W32WindowUtils.this.getProcessFilePath(hwnd), W32WindowUtils.this.getWindowLocationAndSize(hwnd)));
                        return true;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return true;
                    }
                }
            }, null)) {
                return linkedList;
            }
            throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
        }

        /* JADX WARN: Code duplicated, block: B:37:0x0095 A[Catch: all -> 0x0039, TRY_ENTER, TryCatch #0 {all -> 0x0039, blocks: (B:3:0x0005, B:5:0x000d, B:19:0x003c, B:21:0x0048, B:23:0x0050, B:25:0x0065, B:37:0x0095, B:39:0x0099, B:41:0x00a1, B:43:0x00b6), top: B:78:0x0005 }] */
        /* JADX WARN: Code duplicated, block: B:39:0x0099 A[Catch: all -> 0x0039, TryCatch #0 {all -> 0x0039, blocks: (B:3:0x0005, B:5:0x000d, B:19:0x003c, B:21:0x0048, B:23:0x0050, B:25:0x0065, B:37:0x0095, B:39:0x0099, B:41:0x00a1, B:43:0x00b6), top: B:78:0x0005 }] */
        /* JADX WARN: Code duplicated, block: B:41:0x00a1 A[Catch: all -> 0x0039, TryCatch #0 {all -> 0x0039, blocks: (B:3:0x0005, B:5:0x000d, B:19:0x003c, B:21:0x0048, B:23:0x0050, B:25:0x0065, B:37:0x0095, B:39:0x0099, B:41:0x00a1, B:43:0x00b6), top: B:78:0x0005 }] */
        /* JADX WARN: Code duplicated, block: B:43:0x00b6 A[Catch: all -> 0x0039, TRY_LEAVE, TryCatch #0 {all -> 0x0039, blocks: (B:3:0x0005, B:5:0x000d, B:19:0x003c, B:21:0x0048, B:23:0x0050, B:25:0x0065, B:37:0x0095, B:39:0x0099, B:41:0x00a1, B:43:0x00b6), top: B:78:0x0005 }] */
        /* JADX WARN: Code duplicated, block: B:57:0x00e8  */
        /* JADX WARN: Code duplicated, block: B:62:0x00fb  */
        @Override // com.sun.jna.platform.WindowUtils.NativeWindowUtils
        public Dimension getIconSize(WinDef.HICON hicon) {
            WinDef.HBITMAP hbitmap;
            Pointer pointer;
            Pointer pointer2;
            GDI32 gdi32;
            int iGetObject;
            Dimension dimension;
            WinDef.HBITMAP hbitmap2;
            WinDef.HBITMAP hbitmap3;
            WinDef.HBITMAP hbitmap4;
            WinDef.HBITMAP hbitmap5;
            WinGDI.ICONINFO iconinfo = new WinGDI.ICONINFO();
            try {
                if (!User32.INSTANCE.GetIconInfo(hicon, iconinfo)) {
                    Dimension dimension2 = new Dimension();
                    WinDef.HBITMAP hbitmap6 = iconinfo.hbmColor;
                    if (hbitmap6 != null && hbitmap6.getPointer() != Pointer.NULL) {
                        GDI32.INSTANCE.DeleteObject(iconinfo.hbmColor);
                    }
                    WinDef.HBITMAP hbitmap7 = iconinfo.hbmMask;
                    if (hbitmap7 != null && hbitmap7.getPointer() != Pointer.NULL) {
                        GDI32.INSTANCE.DeleteObject(iconinfo.hbmMask);
                    }
                    return dimension2;
                }
                iconinfo.read();
                WinGDI.BITMAP bitmap = new WinGDI.BITMAP();
                WinDef.HBITMAP hbitmap8 = iconinfo.hbmColor;
                if (hbitmap8 == null) {
                    hbitmap = iconinfo.hbmMask;
                    if (hbitmap != null) {
                        pointer = hbitmap.getPointer();
                        pointer2 = Pointer.NULL;
                        if (pointer != pointer2) {
                            gdi32 = GDI32.INSTANCE;
                            iGetObject = gdi32.GetObject(iconinfo.hbmMask, bitmap.size(), bitmap.getPointer());
                            bitmap.read();
                            if (iGetObject > 0) {
                                dimension = new Dimension(bitmap.bmWidth.intValue(), bitmap.bmHeight.intValue() / 2);
                                hbitmap2 = iconinfo.hbmColor;
                                if (hbitmap2 != null) {
                                    gdi32.DeleteObject(iconinfo.hbmColor);
                                }
                                hbitmap3 = iconinfo.hbmMask;
                                if (hbitmap3 != null) {
                                }
                                return dimension;
                            }
                        }
                    }
                    hbitmap4 = iconinfo.hbmColor;
                    if (hbitmap4 != null) {
                        GDI32.INSTANCE.DeleteObject(iconinfo.hbmColor);
                    }
                    hbitmap5 = iconinfo.hbmMask;
                    if (hbitmap5 != null) {
                        GDI32.INSTANCE.DeleteObject(iconinfo.hbmMask);
                    }
                    return new Dimension();
                }
                Pointer pointer3 = hbitmap8.getPointer();
                Pointer pointer4 = Pointer.NULL;
                if (pointer3 != pointer4) {
                    gdi32 = GDI32.INSTANCE;
                    int iGetObject2 = gdi32.GetObject(iconinfo.hbmColor, bitmap.size(), bitmap.getPointer());
                    bitmap.read();
                    if (iGetObject2 > 0) {
                        dimension = new Dimension(bitmap.bmWidth.intValue(), bitmap.bmHeight.intValue());
                        WinDef.HBITMAP hbitmap9 = iconinfo.hbmColor;
                        if (hbitmap9 != null && hbitmap9.getPointer() != pointer4) {
                            gdi32.DeleteObject(iconinfo.hbmColor);
                        }
                        WinDef.HBITMAP hbitmap10 = iconinfo.hbmMask;
                        if (hbitmap10 != null && hbitmap10.getPointer() != pointer4) {
                            gdi32.DeleteObject(iconinfo.hbmMask);
                        }
                    }
                    hbitmap4 = iconinfo.hbmColor;
                    if (hbitmap4 != null && hbitmap4.getPointer() != Pointer.NULL) {
                        GDI32.INSTANCE.DeleteObject(iconinfo.hbmColor);
                    }
                    hbitmap5 = iconinfo.hbmMask;
                    if (hbitmap5 != null && hbitmap5.getPointer() != Pointer.NULL) {
                        GDI32.INSTANCE.DeleteObject(iconinfo.hbmMask);
                    }
                    return new Dimension();
                }
                hbitmap = iconinfo.hbmMask;
                if (hbitmap != null) {
                    pointer = hbitmap.getPointer();
                    pointer2 = Pointer.NULL;
                    if (pointer != pointer2) {
                        gdi32 = GDI32.INSTANCE;
                        iGetObject = gdi32.GetObject(iconinfo.hbmMask, bitmap.size(), bitmap.getPointer());
                        bitmap.read();
                        if (iGetObject > 0) {
                            dimension = new Dimension(bitmap.bmWidth.intValue(), bitmap.bmHeight.intValue() / 2);
                            hbitmap2 = iconinfo.hbmColor;
                            if (hbitmap2 != null && hbitmap2.getPointer() != pointer2) {
                                gdi32.DeleteObject(iconinfo.hbmColor);
                            }
                            hbitmap3 = iconinfo.hbmMask;
                            if (hbitmap3 != null || hbitmap3.getPointer() == pointer2) {
                                return dimension;
                            }
                            gdi32.DeleteObject(iconinfo.hbmMask);
                        }
                    }
                }
                hbitmap4 = iconinfo.hbmColor;
                if (hbitmap4 != null) {
                    GDI32.INSTANCE.DeleteObject(iconinfo.hbmColor);
                }
                hbitmap5 = iconinfo.hbmMask;
                if (hbitmap5 != null) {
                    GDI32.INSTANCE.DeleteObject(iconinfo.hbmMask);
                }
                return new Dimension();
                return dimension;
            } catch (Throwable th) {
                WinDef.HBITMAP hbitmap11 = iconinfo.hbmColor;
                if (hbitmap11 != null && hbitmap11.getPointer() != Pointer.NULL) {
                    GDI32.INSTANCE.DeleteObject(iconinfo.hbmColor);
                }
                WinDef.HBITMAP hbitmap12 = iconinfo.hbmMask;
                if (hbitmap12 != null && hbitmap12.getPointer() != Pointer.NULL) {
                    GDI32.INSTANCE.DeleteObject(iconinfo.hbmMask);
                }
                throw th;
            }
        }

        @Override // com.sun.jna.platform.WindowUtils.NativeWindowUtils
        public String getProcessFilePath(WinDef.HWND hwnd) {
            Kernel32 kernel32;
            IntByReference intByReference = new IntByReference();
            User32.INSTANCE.GetWindowThreadProcessId(hwnd, intByReference);
            Kernel32 kernel33 = Kernel32.INSTANCE;
            WinNT.HANDLE handleOpenProcess = kernel33.OpenProcess(1024, false, intByReference.getValue());
            if (handleOpenProcess == null) {
                if (kernel33.GetLastError() != 5) {
                    throw new Win32Exception(kernel33.GetLastError());
                }
                handleOpenProcess = kernel33.OpenProcess(4096, false, intByReference.getValue());
                if (handleOpenProcess == null) {
                    if (kernel33.GetLastError() == 5) {
                        return "";
                    }
                    throw new Win32Exception(kernel33.GetLastError());
                }
            }
            try {
                String strGetProcessImageFileName = PsapiUtil.GetProcessImageFileName(handleOpenProcess);
                if (strGetProcessImageFileName.startsWith("\\Device\\Mup\\")) {
                    String strConcat = "\\".concat(strGetProcessImageFileName.substring(11));
                    kernel33.CloseHandle(handleOpenProcess);
                    return strConcat;
                }
                char[] cArr = new char[50];
                WinNT.HANDLE handleFindFirstVolume = kernel33.FindFirstVolume(cArr, 50);
                if (handleFindFirstVolume == null || handleFindFirstVolume.equals(WinBase.INVALID_HANDLE_VALUE)) {
                    throw new Win32Exception(Native.getLastError());
                }
                do {
                    try {
                        for (String str : Kernel32Util.getVolumePathNamesForVolumeName(Native.toString(cArr))) {
                            if (str.matches("[a-zA-Z]:\\\\")) {
                                for (String str2 : Kernel32Util.queryDosDevice(str.substring(0, 2), 1024)) {
                                    if (strGetProcessImageFileName.startsWith(str2)) {
                                        String str3 = str + strGetProcessImageFileName.substring(str2.length() + 1);
                                        Kernel32 kernel34 = Kernel32.INSTANCE;
                                        kernel34.FindVolumeClose(handleFindFirstVolume);
                                        kernel34.CloseHandle(handleOpenProcess);
                                        return str3;
                                    }
                                }
                            }
                        }
                        kernel32 = Kernel32.INSTANCE;
                    } catch (Throwable th) {
                        Kernel32.INSTANCE.FindVolumeClose(handleFindFirstVolume);
                        throw th;
                    }
                } while (kernel32.FindNextVolume(handleFindFirstVolume, cArr, 50));
                if (Native.getLastError() != 18) {
                    throw new Win32Exception(Native.getLastError());
                }
                kernel32.FindVolumeClose(handleFindFirstVolume);
                kernel32.CloseHandle(handleOpenProcess);
                return strGetProcessImageFileName;
            } catch (Throwable th2) {
                Kernel32.INSTANCE.CloseHandle(handleOpenProcess);
                throw th2;
            }
        }

        @Override // com.sun.jna.platform.WindowUtils.NativeWindowUtils
        public BufferedImage getWindowIcon(WinDef.HWND hwnd) {
            WinDef.HWND hwnd2;
            WinDef.DWORDByReference dWORDByReference = new WinDef.DWORDByReference();
            User32 user32 = User32.INSTANCE;
            WinDef.LRESULT lresultSendMessageTimeout = user32.SendMessageTimeout(hwnd, 127, new WinDef.WPARAM(1L), new WinDef.LPARAM(0L), 2, WinError.ERROR_USER_PROFILE_LOAD, dWORDByReference);
            if (lresultSendMessageTimeout.intValue() == 0) {
                lresultSendMessageTimeout = user32.SendMessageTimeout(hwnd, 127, new WinDef.WPARAM(0L), new WinDef.LPARAM(0L), 2, WinError.ERROR_USER_PROFILE_LOAD, dWORDByReference);
            }
            if (lresultSendMessageTimeout.intValue() == 0) {
                hwnd2 = hwnd;
                lresultSendMessageTimeout = user32.SendMessageTimeout(hwnd2, 127, new WinDef.WPARAM(2L), new WinDef.LPARAM(0L), 2, WinError.ERROR_USER_PROFILE_LOAD, dWORDByReference);
            } else {
                hwnd2 = hwnd;
            }
            if (lresultSendMessageTimeout.intValue() == 0) {
                lresultSendMessageTimeout = new WinDef.LRESULT(user32.GetClassLongPtr(hwnd2, -14).intValue());
                dWORDByReference.getValue().setValue(lresultSendMessageTimeout.intValue());
            }
            if (lresultSendMessageTimeout.intValue() == 0) {
                lresultSendMessageTimeout = new WinDef.LRESULT(user32.GetClassLongPtr(hwnd2, -34).intValue());
                dWORDByReference.getValue().setValue(lresultSendMessageTimeout.intValue());
            }
            if (lresultSendMessageTimeout.intValue() == 0) {
                return null;
            }
            WinDef.HICON hicon = new WinDef.HICON(new Pointer(dWORDByReference.getValue().longValue()));
            Dimension iconSize = getIconSize(hicon);
            if (iconSize.width == 0 || iconSize.height == 0) {
                return null;
            }
            int i = iconSize.width;
            int i2 = iconSize.height;
            int i3 = ((i * i2) * 24) / 8;
            byte[] bArr = new byte[i3];
            long j = i3;
            Memory memory = new Memory(j);
            byte[] bArr2 = new byte[i3];
            Memory memory2 = new Memory(j);
            WinGDI.BITMAPINFO bitmapinfo = new WinGDI.BITMAPINFO();
            WinGDI.BITMAPINFOHEADER bitmapinfoheader = new WinGDI.BITMAPINFOHEADER();
            bitmapinfo.bmiHeader = bitmapinfoheader;
            bitmapinfoheader.biWidth = i;
            bitmapinfoheader.biHeight = i2;
            bitmapinfoheader.biPlanes = (short) 1;
            bitmapinfoheader.biBitCount = (short) 24;
            bitmapinfoheader.biCompression = 0;
            bitmapinfoheader.write();
            bitmapinfo.write();
            WinDef.HDC hdcGetDC = user32.GetDC(null);
            WinGDI.ICONINFO iconinfo = new WinGDI.ICONINFO();
            user32.GetIconInfo(hicon, iconinfo);
            iconinfo.read();
            GDI32 gdi32 = GDI32.INSTANCE;
            gdi32.GetDIBits(hdcGetDC, iconinfo.hbmColor, 0, i2, memory, bitmapinfo, 0);
            memory.read(0L, bArr, 0, i3);
            gdi32.GetDIBits(hdcGetDC, iconinfo.hbmMask, 0, i2, memory2, bitmapinfo, 0);
            memory2.read(0L, bArr2, 0, i3);
            BufferedImage bufferedImage = new BufferedImage(i, i2, 2);
            int i4 = i2 - 1;
            int i5 = 0;
            for (int i6 = 0; i6 < i3; i6 += 3) {
                bufferedImage.setRGB(i5, i4, (bArr[i6] & 255) | ((bArr[i6 + 1] & 255) << 8) | ((bArr[i6 + 2] & 255) << 16) | (((255 - bArr2[i6]) & 255) << 24));
                i5 = (i5 + 1) % i;
                if (i5 == 0) {
                    i4--;
                }
            }
            User32.INSTANCE.ReleaseDC(null, hdcGetDC);
            return bufferedImage;
        }

        @Override // com.sun.jna.platform.WindowUtils.NativeWindowUtils
        public Rectangle getWindowLocationAndSize(WinDef.HWND hwnd) {
            WinDef.RECT rect = new WinDef.RECT();
            if (!User32.INSTANCE.GetWindowRect(hwnd, rect)) {
                throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
            }
            int i = rect.left;
            return new Rectangle(i, rect.top, Math.abs(rect.right - i), Math.abs(rect.bottom - rect.top));
        }

        @Override // com.sun.jna.platform.WindowUtils.NativeWindowUtils
        public String getWindowTitle(WinDef.HWND hwnd) {
            User32 user32 = User32.INSTANCE;
            int iGetWindowTextLength = user32.GetWindowTextLength(hwnd) + 1;
            char[] cArr = new char[iGetWindowTextLength];
            return Native.toString(Arrays.copyOfRange(cArr, 0, user32.GetWindowText(hwnd, cArr, iGetWindowTextLength)));
        }

        @Override // com.sun.jna.platform.WindowUtils.NativeWindowUtils
        public boolean isWindowAlphaSupported() {
            return Boolean.getBoolean("sun.java2d.noddraw");
        }

        @Override // com.sun.jna.platform.WindowUtils.NativeWindowUtils
        public void setWindowAlpha(final Window window, final float f) {
            if (isWindowAlphaSupported()) {
                whenDisplayable(window, new Runnable() { // from class: com.sun.jna.platform.WindowUtils.W32WindowUtils.1
                    @Override // java.lang.Runnable
                    public void run() {
                        boolean z;
                        WinDef.HWND hWnd = W32WindowUtils.this.getHWnd(window);
                        User32 user32 = User32.INSTANCE;
                        int iGetWindowLong = user32.GetWindowLong(hWnd, -20);
                        byte b = (byte) (((int) (f * 255.0f)) & 255);
                        if (W32WindowUtils.this.usingUpdateLayeredWindow(window)) {
                            WinUser.BLENDFUNCTION blendfunction = new WinUser.BLENDFUNCTION();
                            blendfunction.SourceConstantAlpha = b;
                            blendfunction.AlphaFormat = (byte) 1;
                            z = true;
                            user32.UpdateLayeredWindow(hWnd, null, null, null, null, null, 0, blendfunction, 2);
                        } else {
                            z = true;
                            if (f == 1.0f) {
                                user32.SetWindowLong(hWnd, -20, iGetWindowLong & (-524289));
                            } else {
                                user32.SetWindowLong(hWnd, -20, iGetWindowLong | 524288);
                                user32.SetLayeredWindowAttributes(hWnd, 0, b, 2);
                            }
                        }
                        W32WindowUtils.this.setForceHeavyweightPopups(window, f != 1.0f ? z : false);
                        W32WindowUtils.this.storeAlpha(window, b);
                    }
                });
            } else {
                c41.a("Set sun.java2d.noddraw=true to enable transparent windows");
            }
        }

        @Override // com.sun.jna.platform.WindowUtils.NativeWindowUtils
        public void setWindowMask(Component component, Shape shape) {
            if (shape instanceof Area) {
                Area area = (Area) shape;
                if (area.isPolygonal()) {
                    setMask(component, area);
                    return;
                }
            }
            super.setWindowMask(component, shape);
        }

        @Override // com.sun.jna.platform.WindowUtils.NativeWindowUtils
        public void setWindowTransparent(final Window window, final boolean z) {
            if (!(window instanceof RootPaneContainer)) {
                w01.a("Window must be a RootPaneContainer");
            } else {
                if (!isWindowAlphaSupported()) {
                    c41.a("Set sun.java2d.noddraw=true to enable transparent windows");
                    return;
                }
                if (z == (window.getBackground() != null && window.getBackground().getAlpha() == 0)) {
                    return;
                }
                whenDisplayable(window, new Runnable() { // from class: com.sun.jna.platform.WindowUtils.W32WindowUtils.2
                    @Override // java.lang.Runnable
                    public void run() {
                        User32 user32 = User32.INSTANCE;
                        WinDef.HWND hWnd = W32WindowUtils.this.getHWnd(window);
                        int iGetWindowLong = user32.GetWindowLong(hWnd, -20);
                        JRootPane rootPane = window.getRootPane();
                        JLayeredPane layeredPane = rootPane.getLayeredPane();
                        W32TransparentContentPane contentPane = rootPane.getContentPane();
                        if (contentPane instanceof W32TransparentContentPane) {
                            contentPane.setTransparent(z);
                        } else if (z) {
                            W32TransparentContentPane w32TransparentContentPane = W32WindowUtils.this.new W32TransparentContentPane(contentPane);
                            rootPane.setContentPane(w32TransparentContentPane);
                            layeredPane.add(new RepaintTrigger(w32TransparentContentPane), JLayeredPane.DRAG_LAYER);
                        }
                        if (z && !W32WindowUtils.this.usingUpdateLayeredWindow(window)) {
                            user32.SetWindowLong(hWnd, -20, iGetWindowLong | 524288);
                        } else if (!z && W32WindowUtils.this.usingUpdateLayeredWindow(window)) {
                            user32.SetWindowLong(hWnd, -20, iGetWindowLong & (-524289));
                        }
                        W32WindowUtils.this.setLayersTransparent(window, z);
                        W32WindowUtils.this.setForceHeavyweightPopups(window, z);
                        W32WindowUtils.this.setDoubleBuffered(window, !z);
                    }
                });
            }
        }

        @Override // com.sun.jna.platform.WindowUtils.NativeWindowUtils
        public void setMask(Component component, Raster raster) {
            GDI32 gdi32 = GDI32.INSTANCE;
            final WinDef.HRGN hrgnCreateRectRgn = raster != null ? gdi32.CreateRectRgn(0, 0, 0, 0) : null;
            if (hrgnCreateRectRgn != null) {
                final WinDef.HRGN hrgnCreateRectRgn2 = gdi32.CreateRectRgn(0, 0, 0, 0);
                try {
                    RasterRangesUtils.outputOccupiedRanges(raster, new RasterRangesUtils.RangesOutput() { // from class: com.sun.jna.platform.WindowUtils.W32WindowUtils.4
                        @Override // com.sun.jna.platform.RasterRangesUtils.RangesOutput
                        public boolean outputRange(int i, int i2, int i3, int i4) {
                            GDI32 gdi33 = GDI32.INSTANCE;
                            gdi33.SetRectRgn(hrgnCreateRectRgn2, i, i2, i + i3, i2 + i4);
                            WinDef.HRGN hrgn = hrgnCreateRectRgn;
                            return gdi33.CombineRgn(hrgn, hrgn, hrgnCreateRectRgn2, 2) != 0;
                        }
                    });
                    gdi32.DeleteObject(hrgnCreateRectRgn2);
                } catch (Throwable th) {
                    gdi32.DeleteObject(hrgnCreateRectRgn2);
                    throw th;
                }
            }
            setWindowRegion(component, hrgnCreateRectRgn);
        }
    }
}
