package com.sun.jna.platform.win32;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.DirectColorModel;
import java.awt.image.Raster;
import java.util.Hashtable;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class GDI32Util {
    private static final int[] SCREENSHOT_BAND_MASKS;
    private static final DirectColorModel SCREENSHOT_COLOR_MODEL;

    static {
        DirectColorModel directColorModel = new DirectColorModel(24, Winspool.PRINTER_ENUM_ICONMASK, Winspool.PRINTER_CHANGE_JOB, 255);
        SCREENSHOT_COLOR_MODEL = directColorModel;
        SCREENSHOT_BAND_MASKS = new int[]{directColorModel.getRedMask(), directColorModel.getGreenMask(), directColorModel.getBlueMask()};
    }

    /* JADX WARN: Code duplicated, block: B:121:0x01b0  */
    /* JADX WARN: Code duplicated, block: B:126:0x01ca  */
    /* JADX WARN: Code duplicated, block: B:128:0x01cd  */
    /* JADX WARN: Code duplicated, block: B:132:0x01e0  */
    /* JADX WARN: Code duplicated, block: B:135:0x01e6  */
    /* JADX WARN: Code duplicated, block: B:139:0x01f9  */
    /* JADX WARN: Code duplicated, block: B:142:0x0204  */
    /* JADX WARN: Code duplicated, block: B:144:0x0209  */
    /* JADX WARN: Code duplicated, block: B:146:0x020c  */
    /* JADX WARN: Code duplicated, block: B:152:0x022b  */
    /* JADX WARN: Code duplicated, block: B:156:0x0242  */
    /* JADX WARN: Code duplicated, block: B:161:0x025f  */
    /* JADX WARN: Code duplicated, block: B:163:0x0262 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:164:0x0263  */
    /* JADX WARN: Code duplicated, block: B:165:0x0264  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v17, types: [com.sun.jna.platform.win32.GDI32] */
    /* JADX WARN: Type inference failed for: r3v6, types: [com.sun.jna.platform.win32.GDI32] */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v22 */
    /* JADX WARN: Type inference failed for: r5v23 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r9v0, types: [com.sun.jna.platform.win32.WinNT$HANDLE] */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v10 */
    /* JADX WARN: Type inference failed for: r9v11 */
    /* JADX WARN: Type inference failed for: r9v15 */
    /* JADX WARN: Type inference failed for: r9v16 */
    /* JADX WARN: Type inference failed for: r9v17 */
    /* JADX WARN: Type inference failed for: r9v18 */
    /* JADX WARN: Type inference failed for: r9v2, types: [com.sun.jna.platform.win32.WinNT$HANDLE] */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Type inference failed for: r9v5 */
    /* JADX WARN: Type inference failed for: r9v6 */
    /* JADX WARN: Type inference failed for: r9v7 */
    /* JADX WARN: Type inference failed for: r9v8 */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public static BufferedImage getScreenshot(WinDef.HWND hwnd) throws Throwable {
        WinDef.HDC hdc;
        WinDef.HDC hdcCreateCompatibleDC;
        WinDef.HBITMAP hbitmapCreateCompatibleBitmap;
        ?? r9;
        Win32Exception win32Exception;
        Win32Exception win32Exception2;
        Win32Exception win32Exception3;
        WinNT.HANDLE handleSelectObject;
        BufferedImage bufferedImage;
        ?? r10;
        WinNT.HANDLE handleSelectObject2;
        WinDef.HDC hdc2;
        ?? r11;
        ?? r5;
        ?? r12;
        ?? r6;
        WinDef.HBITMAP hbitmap;
        WinDef.RECT rect = new WinDef.RECT();
        User32 user32 = User32.INSTANCE;
        if (!user32.GetWindowRect(hwnd, rect)) {
            throw new Win32Exception(Native.getLastError());
        }
        Rectangle rectangle = rect.toRectangle();
        int i = rectangle.width;
        int i2 = rectangle.height;
        if (i == 0 || i2 == 0) {
            k2d.a("Window width and/or height were 0 even though GetWindowRect did not appear to fail.");
            return null;
        }
        WinDef.HDC hdcGetDC = user32.GetDC(hwnd);
        if (hdcGetDC == null) {
            throw new Win32Exception(Native.getLastError());
        }
        try {
            GDI32 gdi32 = GDI32.INSTANCE;
            hdcCreateCompatibleDC = gdi32.CreateCompatibleDC(hdcGetDC);
            if (hdcCreateCompatibleDC == null) {
                hdc = hdcGetDC;
                try {
                    throw new Win32Exception(Native.getLastError());
                } catch (Win32Exception e) {
                    e = e;
                    hdcCreateCompatibleDC = hdcCreateCompatibleDC;
                    r10 = 0;
                    hbitmapCreateCompatibleBitmap = null;
                    if (r10 != 0 && ((handleSelectObject2 = GDI32.INSTANCE.SelectObject(hdcCreateCompatibleDC, r10)) == null || WinGDI.HGDI_ERROR.equals(handleSelectObject2))) {
                        Win32Exception win32Exception4 = new Win32Exception(Native.getLastError());
                        win32Exception4.addSuppressedReflected(e);
                        e = win32Exception4;
                    }
                    if (hbitmapCreateCompatibleBitmap != null && !GDI32.INSTANCE.DeleteObject(hbitmapCreateCompatibleBitmap)) {
                        Win32Exception win32Exception5 = new Win32Exception(Native.getLastError());
                        win32Exception5.addSuppressedReflected(e);
                        e = win32Exception5;
                    }
                    if (hdcCreateCompatibleDC != null && !GDI32.INSTANCE.DeleteDC(hdcCreateCompatibleDC)) {
                        Win32Exception win32Exception6 = new Win32Exception(Native.getLastError());
                        win32Exception6.addSuppressedReflected(e);
                        e = win32Exception6;
                    }
                    if (User32.INSTANCE.ReleaseDC(hwnd, hdc) != 0) {
                        k2d.a("Device context did not release properly.");
                        return null;
                    }
                    bufferedImage = null;
                    if (e == null) {
                        return bufferedImage;
                    }
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    hdcCreateCompatibleDC = hdcCreateCompatibleDC;
                    r9 = 0;
                    hbitmapCreateCompatibleBitmap = null;
                    if (r9 == 0 && ((handleSelectObject = GDI32.INSTANCE.SelectObject(hdcCreateCompatibleDC, r9)) == null || WinGDI.HGDI_ERROR.equals(handleSelectObject))) {
                        win32Exception = new Win32Exception(Native.getLastError());
                    } else {
                        win32Exception = null;
                    }
                    if (hbitmapCreateCompatibleBitmap != null && !GDI32.INSTANCE.DeleteObject(hbitmapCreateCompatibleBitmap)) {
                        win32Exception3 = new Win32Exception(Native.getLastError());
                        if (win32Exception != null) {
                            win32Exception3.addSuppressedReflected(win32Exception);
                        }
                        win32Exception = win32Exception3;
                    }
                    if (hdcCreateCompatibleDC != null && !GDI32.INSTANCE.DeleteDC(hdcCreateCompatibleDC)) {
                        win32Exception2 = new Win32Exception(Native.getLastError());
                        if (win32Exception != null) {
                            win32Exception2.addSuppressedReflected(win32Exception);
                        }
                    }
                    if (User32.INSTANCE.ReleaseDC(hwnd, hdc) == 0) {
                        throw th;
                    }
                    k2d.a("Device context did not release properly.");
                    return null;
                }
            }
            try {
                hbitmapCreateCompatibleBitmap = gdi32.CreateCompatibleBitmap(hdcGetDC, i, i2);
                if (hbitmapCreateCompatibleBitmap == null) {
                    hdc = hdcGetDC;
                    try {
                        throw new Win32Exception(Native.getLastError());
                    } catch (Win32Exception e2) {
                        e = e2;
                        hbitmapCreateCompatibleBitmap = hbitmapCreateCompatibleBitmap;
                        hdcCreateCompatibleDC = hdcCreateCompatibleDC;
                        r10 = 0;
                        if (r10 != 0) {
                            Win32Exception win32Exception7 = new Win32Exception(Native.getLastError());
                            win32Exception7.addSuppressedReflected(e);
                            e = win32Exception7;
                        }
                        if (hbitmapCreateCompatibleBitmap != null) {
                            Win32Exception win32Exception8 = new Win32Exception(Native.getLastError());
                            win32Exception8.addSuppressedReflected(e);
                            e = win32Exception8;
                        }
                        if (hdcCreateCompatibleDC != null) {
                            Win32Exception win32Exception9 = new Win32Exception(Native.getLastError());
                            win32Exception9.addSuppressedReflected(e);
                            e = win32Exception9;
                        }
                        if (User32.INSTANCE.ReleaseDC(hwnd, hdc) != 0) {
                            k2d.a("Device context did not release properly.");
                            return null;
                        }
                        bufferedImage = null;
                        if (e == null) {
                            return bufferedImage;
                        }
                        throw e;
                    } catch (Throwable th2) {
                        th = th2;
                        hbitmapCreateCompatibleBitmap = hbitmapCreateCompatibleBitmap;
                        hdcCreateCompatibleDC = hdcCreateCompatibleDC;
                        r9 = 0;
                        if (r9 == 0) {
                            win32Exception = null;
                        } else {
                            win32Exception = null;
                        }
                        if (hbitmapCreateCompatibleBitmap != null) {
                            win32Exception3 = new Win32Exception(Native.getLastError());
                            if (win32Exception != null) {
                                win32Exception3.addSuppressedReflected(win32Exception);
                            }
                            win32Exception = win32Exception3;
                        }
                        if (hdcCreateCompatibleDC != null) {
                            win32Exception2 = new Win32Exception(Native.getLastError());
                            if (win32Exception != null) {
                                win32Exception2.addSuppressedReflected(win32Exception);
                            }
                        }
                        if (User32.INSTANCE.ReleaseDC(hwnd, hdc) == 0) {
                            throw th;
                        }
                        k2d.a("Device context did not release properly.");
                        return null;
                    }
                }
                try {
                    WinNT.HANDLE handleSelectObject3 = gdi32.SelectObject(hdcCreateCompatibleDC, hbitmapCreateCompatibleBitmap);
                    try {
                        if (handleSelectObject3 == null) {
                            throw new Win32Exception(Native.getLastError());
                        }
                        try {
                            if (!gdi32.BitBlt(hdcCreateCompatibleDC, 0, 0, i, i2, hdcGetDC, 0, 0, GDI32.SRCCOPY)) {
                                throw new Win32Exception(Native.getLastError());
                            }
                            WinGDI.BITMAPINFO bitmapinfo = new WinGDI.BITMAPINFO();
                            WinGDI.BITMAPINFOHEADER bitmapinfoheader = bitmapinfo.bmiHeader;
                            bitmapinfoheader.biWidth = i;
                            bitmapinfoheader.biHeight = -i2;
                            bitmapinfoheader.biPlanes = (short) 1;
                            bitmapinfoheader.biBitCount = (short) 32;
                            bitmapinfoheader.biCompression = 0;
                            int i3 = i * i2;
                            Memory memory = new Memory(i3 * 4);
                            try {
                                int iGetDIBits = gdi32.GetDIBits(hdcGetDC, hbitmapCreateCompatibleBitmap, 0, i2, memory, bitmapinfo, 0);
                                hdc = hdcGetDC;
                                hbitmap = hbitmapCreateCompatibleBitmap;
                                if (iGetDIBits == 0 || iGetDIBits == 87) {
                                    throw new Win32Exception(Native.getLastError());
                                }
                                try {
                                    try {
                                        hdc2 = hdcCreateCompatibleDC;
                                        try {
                                            BufferedImage bufferedImage2 = new BufferedImage(SCREENSHOT_COLOR_MODEL, Raster.createPackedRaster(new DataBufferInt(memory.getIntArray(0L, i3), i3), i, i2, i, SCREENSHOT_BAND_MASKS, (Point) null), false, (Hashtable) null);
                                            WinNT.HANDLE handleSelectObject4 = gdi32.SelectObject(hdc2, handleSelectObject3);
                                            e = (handleSelectObject4 == null || WinGDI.HGDI_ERROR.equals(handleSelectObject4)) ? new Win32Exception(Native.getLastError()) : null;
                                            if (!gdi32.DeleteObject(hbitmap)) {
                                                Win32Exception win32Exception10 = new Win32Exception(Native.getLastError());
                                                if (e != null) {
                                                    win32Exception10.addSuppressedReflected(e);
                                                }
                                                e = win32Exception10;
                                            }
                                            if (!gdi32.DeleteDC(hdc2)) {
                                                Win32Exception win32Exception11 = new Win32Exception(Native.getLastError());
                                                if (e != null) {
                                                    win32Exception11.addSuppressedReflected(e);
                                                }
                                                e = win32Exception11;
                                            }
                                            if (user32.ReleaseDC(hwnd, hdc) == 0) {
                                                k2d.a("Device context did not release properly.");
                                                return null;
                                            }
                                            bufferedImage = bufferedImage2;
                                        } catch (Win32Exception e3) {
                                            e = e3;
                                            r6 = handleSelectObject3;
                                            r12 = r6;
                                            hbitmapCreateCompatibleBitmap = hbitmap;
                                            hdcCreateCompatibleDC = hdc2;
                                            r10 = r12;
                                            if (r10 != 0) {
                                                Win32Exception win32Exception12 = new Win32Exception(Native.getLastError());
                                                win32Exception12.addSuppressedReflected(e);
                                                e = win32Exception12;
                                            }
                                            if (hbitmapCreateCompatibleBitmap != null) {
                                                Win32Exception win32Exception13 = new Win32Exception(Native.getLastError());
                                                win32Exception13.addSuppressedReflected(e);
                                                e = win32Exception13;
                                            }
                                            if (hdcCreateCompatibleDC != null) {
                                                Win32Exception win32Exception14 = new Win32Exception(Native.getLastError());
                                                win32Exception14.addSuppressedReflected(e);
                                                e = win32Exception14;
                                            }
                                            if (User32.INSTANCE.ReleaseDC(hwnd, hdc) != 0) {
                                                k2d.a("Device context did not release properly.");
                                                return null;
                                            }
                                            bufferedImage = null;
                                        } catch (Throwable th3) {
                                            th = th3;
                                            r5 = handleSelectObject3;
                                            r11 = r5;
                                            hbitmapCreateCompatibleBitmap = hbitmap;
                                            hdcCreateCompatibleDC = hdc2;
                                            r9 = r11;
                                            if (r9 == 0) {
                                                win32Exception = null;
                                            } else {
                                                win32Exception = null;
                                            }
                                            if (hbitmapCreateCompatibleBitmap != null) {
                                                win32Exception3 = new Win32Exception(Native.getLastError());
                                                if (win32Exception != null) {
                                                    win32Exception3.addSuppressedReflected(win32Exception);
                                                }
                                                win32Exception = win32Exception3;
                                            }
                                            if (hdcCreateCompatibleDC != null) {
                                                win32Exception2 = new Win32Exception(Native.getLastError());
                                                if (win32Exception != null) {
                                                    win32Exception2.addSuppressedReflected(win32Exception);
                                                }
                                            }
                                            if (User32.INSTANCE.ReleaseDC(hwnd, hdc) == 0) {
                                                throw th;
                                            }
                                            k2d.a("Device context did not release properly.");
                                            return null;
                                        }
                                    } catch (Win32Exception e4) {
                                        e = e4;
                                        hdc2 = hdcCreateCompatibleDC;
                                    } catch (Throwable th4) {
                                        th = th4;
                                        hdc2 = hdcCreateCompatibleDC;
                                    }
                                } catch (Win32Exception e5) {
                                    e = e5;
                                    hdc2 = hdcCreateCompatibleDC;
                                } catch (Throwable th5) {
                                    th = th5;
                                    hdc2 = hdcCreateCompatibleDC;
                                }
                            } catch (Win32Exception e6) {
                                e = e6;
                                hdc2 = hdcCreateCompatibleDC;
                                hdc = hdcGetDC;
                                hbitmap = hbitmapCreateCompatibleBitmap;
                            } catch (Throwable th6) {
                                th = th6;
                                hdc2 = hdcCreateCompatibleDC;
                                hdc = hdcGetDC;
                                hbitmap = hbitmapCreateCompatibleBitmap;
                            }
                        } catch (Win32Exception e7) {
                            e = e7;
                            hdc2 = hdcCreateCompatibleDC;
                            hdc = hdcGetDC;
                            r12 = handleSelectObject3;
                            hdcCreateCompatibleDC = hdc2;
                            r10 = r12;
                            if (r10 != 0) {
                                Win32Exception win32Exception15 = new Win32Exception(Native.getLastError());
                                win32Exception15.addSuppressedReflected(e);
                                e = win32Exception15;
                            }
                            if (hbitmapCreateCompatibleBitmap != null) {
                                Win32Exception win32Exception16 = new Win32Exception(Native.getLastError());
                                win32Exception16.addSuppressedReflected(e);
                                e = win32Exception16;
                            }
                            if (hdcCreateCompatibleDC != null) {
                                Win32Exception win32Exception17 = new Win32Exception(Native.getLastError());
                                win32Exception17.addSuppressedReflected(e);
                                e = win32Exception17;
                            }
                            if (User32.INSTANCE.ReleaseDC(hwnd, hdc) != 0) {
                                k2d.a("Device context did not release properly.");
                                return null;
                            }
                            bufferedImage = null;
                        } catch (Throwable th7) {
                            th = th7;
                            hdc2 = hdcCreateCompatibleDC;
                            hdc = hdcGetDC;
                            r11 = handleSelectObject3;
                            hdcCreateCompatibleDC = hdc2;
                            r9 = r11;
                            if (r9 == 0) {
                                win32Exception = null;
                            } else {
                                win32Exception = null;
                            }
                            if (hbitmapCreateCompatibleBitmap != null) {
                                win32Exception3 = new Win32Exception(Native.getLastError());
                                if (win32Exception != null) {
                                    win32Exception3.addSuppressedReflected(win32Exception);
                                }
                                win32Exception = win32Exception3;
                            }
                            if (hdcCreateCompatibleDC != null) {
                                win32Exception2 = new Win32Exception(Native.getLastError());
                                if (win32Exception != null) {
                                    win32Exception2.addSuppressedReflected(win32Exception);
                                }
                            }
                            if (User32.INSTANCE.ReleaseDC(hwnd, hdc) == 0) {
                                throw th;
                            }
                            k2d.a("Device context did not release properly.");
                            return null;
                        }
                    } catch (Win32Exception e8) {
                        e = e8;
                        r6 = i;
                    } catch (Throwable th8) {
                        th = th8;
                        r5 = i;
                    }
                } catch (Win32Exception e9) {
                    e = e9;
                    hdc = hdcGetDC;
                    r10 = 0;
                    if (r10 != 0) {
                        Win32Exception win32Exception18 = new Win32Exception(Native.getLastError());
                        win32Exception18.addSuppressedReflected(e);
                        e = win32Exception18;
                    }
                    if (hbitmapCreateCompatibleBitmap != null) {
                        Win32Exception win32Exception19 = new Win32Exception(Native.getLastError());
                        win32Exception19.addSuppressedReflected(e);
                        e = win32Exception19;
                    }
                    if (hdcCreateCompatibleDC != null) {
                        Win32Exception win32Exception110 = new Win32Exception(Native.getLastError());
                        win32Exception110.addSuppressedReflected(e);
                        e = win32Exception110;
                    }
                    if (User32.INSTANCE.ReleaseDC(hwnd, hdc) != 0) {
                        k2d.a("Device context did not release properly.");
                        return null;
                    }
                    bufferedImage = null;
                    if (e == null) {
                        return bufferedImage;
                    }
                    throw e;
                } catch (Throwable th9) {
                    th = th9;
                    hdc = hdcGetDC;
                    r9 = 0;
                    if (r9 == 0) {
                        win32Exception = null;
                    } else {
                        win32Exception = null;
                    }
                    if (hbitmapCreateCompatibleBitmap != null) {
                        win32Exception3 = new Win32Exception(Native.getLastError());
                        if (win32Exception != null) {
                            win32Exception3.addSuppressedReflected(win32Exception);
                        }
                        win32Exception = win32Exception3;
                    }
                    if (hdcCreateCompatibleDC != null) {
                        win32Exception2 = new Win32Exception(Native.getLastError());
                        if (win32Exception != null) {
                            win32Exception2.addSuppressedReflected(win32Exception);
                        }
                    }
                    if (User32.INSTANCE.ReleaseDC(hwnd, hdc) == 0) {
                        throw th;
                    }
                    k2d.a("Device context did not release properly.");
                    return null;
                }
            } catch (Win32Exception e10) {
                e = e10;
                hdc = hdcGetDC;
                r10 = 0;
                hbitmapCreateCompatibleBitmap = null;
                if (r10 != 0) {
                    Win32Exception win32Exception111 = new Win32Exception(Native.getLastError());
                    win32Exception111.addSuppressedReflected(e);
                    e = win32Exception111;
                }
                if (hbitmapCreateCompatibleBitmap != null) {
                    Win32Exception win32Exception112 = new Win32Exception(Native.getLastError());
                    win32Exception112.addSuppressedReflected(e);
                    e = win32Exception112;
                }
                if (hdcCreateCompatibleDC != null) {
                    Win32Exception win32Exception113 = new Win32Exception(Native.getLastError());
                    win32Exception113.addSuppressedReflected(e);
                    e = win32Exception113;
                }
                if (User32.INSTANCE.ReleaseDC(hwnd, hdc) != 0) {
                    k2d.a("Device context did not release properly.");
                    return null;
                }
                bufferedImage = null;
                if (e == null) {
                    return bufferedImage;
                }
                throw e;
            } catch (Throwable th10) {
                th = th10;
                hdc = hdcGetDC;
                r9 = 0;
                hbitmapCreateCompatibleBitmap = null;
                if (r9 == 0) {
                    win32Exception = null;
                } else {
                    win32Exception = null;
                }
                if (hbitmapCreateCompatibleBitmap != null) {
                    win32Exception3 = new Win32Exception(Native.getLastError());
                    if (win32Exception != null) {
                        win32Exception3.addSuppressedReflected(win32Exception);
                    }
                    win32Exception = win32Exception3;
                }
                if (hdcCreateCompatibleDC != null) {
                    win32Exception2 = new Win32Exception(Native.getLastError());
                    if (win32Exception != null) {
                        win32Exception2.addSuppressedReflected(win32Exception);
                    }
                }
                if (User32.INSTANCE.ReleaseDC(hwnd, hdc) == 0) {
                    throw th;
                }
                k2d.a("Device context did not release properly.");
                return null;
            }
        } catch (Win32Exception e11) {
            e = e11;
            hdc = hdcGetDC;
            hdcCreateCompatibleDC = null;
        } catch (Throwable th11) {
            th = th11;
            hdc = hdcGetDC;
            hdcCreateCompatibleDC = null;
        }
        if (e == null) {
            return bufferedImage;
        }
        throw e;
    }
}
