package com.sun.jna.platform.win32;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface GDI32 extends StdCallLibrary {
    public static final GDI32 INSTANCE = (GDI32) Native.load("gdi32", GDI32.class, W32APIOptions.DEFAULT_OPTIONS);
    public static final int SRCCOPY = 13369376;

    boolean BitBlt(WinDef.HDC hdc, int i, int i2, int i3, int i4, WinDef.HDC hdc2, int i5, int i6, int i7);

    int ChoosePixelFormat(WinDef.HDC hdc, WinGDI.PIXELFORMATDESCRIPTOR.ByReference byReference);

    int CombineRgn(WinDef.HRGN hrgn, WinDef.HRGN hrgn2, WinDef.HRGN hrgn3, int i);

    WinDef.HBITMAP CreateCompatibleBitmap(WinDef.HDC hdc, int i, int i2);

    WinDef.HDC CreateCompatibleDC(WinDef.HDC hdc);

    WinDef.HBITMAP CreateDIBSection(WinDef.HDC hdc, WinGDI.BITMAPINFO bitmapinfo, int i, PointerByReference pointerByReference, Pointer pointer, int i2);

    WinDef.HBITMAP CreateDIBitmap(WinDef.HDC hdc, WinGDI.BITMAPINFOHEADER bitmapinfoheader, int i, Pointer pointer, WinGDI.BITMAPINFO bitmapinfo, int i2);

    WinDef.HRGN CreatePolyPolygonRgn(WinDef.POINT[] pointArr, int[] iArr, int i, int i2);

    WinDef.HRGN CreateRectRgn(int i, int i2, int i3, int i4);

    WinDef.HRGN CreateRoundRectRgn(int i, int i2, int i3, int i4, int i5, int i6);

    boolean DeleteDC(WinDef.HDC hdc);

    boolean DeleteObject(WinNT.HANDLE handle);

    WinDef.HRGN ExtCreateRegion(Pointer pointer, int i, WinGDI.RGNDATA rgndata);

    int GetDIBits(WinDef.HDC hdc, WinDef.HBITMAP hbitmap, int i, int i2, Pointer pointer, WinGDI.BITMAPINFO bitmapinfo, int i3);

    int GetDeviceCaps(WinDef.HDC hdc, int i);

    int GetObject(WinNT.HANDLE handle, int i, Pointer pointer);

    WinNT.HANDLE SelectObject(WinDef.HDC hdc, WinNT.HANDLE handle);

    int SetPixel(WinDef.HDC hdc, int i, int i2, int i3);

    boolean SetPixelFormat(WinDef.HDC hdc, int i, WinGDI.PIXELFORMATDESCRIPTOR.ByReference byReference);

    boolean SetRectRgn(WinDef.HRGN hrgn, int i, int i2, int i3, int i4);
}
