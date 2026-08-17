package com.sun.jna.platform.win32;

import com.sun.jna.Function;
import com.sun.jna.Pointer;
import com.sun.org.apache.bcel.internal.Const;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class OpenGL32Util {
    public static int countGpusNV() {
        WinDef.HWND hwndCreateWindow = User32Util.createWindow("Message", null, 0, 0, 0, 0, 0, null, null, null, null);
        User32 user32 = User32.INSTANCE;
        WinDef.HDC hdcGetDC = user32.GetDC(hwndCreateWindow);
        WinGDI.PIXELFORMATDESCRIPTOR.ByReference byReference = new WinGDI.PIXELFORMATDESCRIPTOR.ByReference();
        byReference.nVersion = (short) 1;
        byReference.dwFlags = 37;
        byReference.iPixelType = (byte) 0;
        byReference.cColorBits = Const.ATTR_MODULE_MAIN_CLASS;
        byReference.cDepthBits = (byte) 16;
        byReference.iLayerType = (byte) 0;
        GDI32 gdi32 = GDI32.INSTANCE;
        gdi32.SetPixelFormat(hdcGetDC, gdi32.ChoosePixelFormat(hdcGetDC, byReference), byReference);
        OpenGL32 openGL32 = OpenGL32.INSTANCE;
        WinDef.HGLRC hglrcWglCreateContext = openGL32.wglCreateContext(hdcGetDC);
        openGL32.wglMakeCurrent(hdcGetDC, hglrcWglCreateContext);
        Pointer pointerWglGetProcAddress = openGL32.wglGetProcAddress("wglEnumGpusNV");
        Function function = pointerWglGetProcAddress == null ? null : Function.getFunction(pointerWglGetProcAddress);
        openGL32.wglDeleteContext(hglrcWglCreateContext);
        user32.ReleaseDC(hwndCreateWindow, hdcGetDC);
        User32Util.destroyWindow(hwndCreateWindow);
        if (function == null) {
            return 0;
        }
        WinDef.HGLRCByReference hGLRCByReference = new WinDef.HGLRCByReference();
        for (int i = 0; i < 16; i++) {
            if (!((Boolean) function.invoke(Boolean.class, new Object[]{Integer.valueOf(i), hGLRCByReference})).booleanValue()) {
                return i;
            }
        }
        return 0;
    }

    public static Function wglGetProcAddress(String str) {
        Pointer pointerWglGetProcAddress = OpenGL32.INSTANCE.wglGetProcAddress("wglEnumGpusNV");
        if (pointerWglGetProcAddress == null) {
            return null;
        }
        return Function.getFunction(pointerWglGetProcAddress);
    }
}
