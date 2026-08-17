package com.sun.jna.platform;

import com.sun.jna.platform.win32.WinDef;
import java.awt.Rectangle;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DesktopWindow {
    private String filePath;
    private WinDef.HWND hwnd;
    private Rectangle locAndSize;
    private String title;

    public DesktopWindow(WinDef.HWND hwnd, String str, String str2, Rectangle rectangle) {
        this.hwnd = hwnd;
        this.title = str;
        this.filePath = str2;
        this.locAndSize = rectangle;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public WinDef.HWND getHWND() {
        return this.hwnd;
    }

    public Rectangle getLocAndSize() {
        return this.locAndSize;
    }

    public String getTitle() {
        return this.title;
    }
}
