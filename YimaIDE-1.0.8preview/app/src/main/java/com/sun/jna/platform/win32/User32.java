package com.sun.jna.platform.win32;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface User32 extends WinNT, WinUser, StdCallLibrary {
    public static final int CS_GLOBALCLASS = 16384;
    public static final int DEVICE_NOTIFY_ALL_INTERFACE_CLASSES = 4;
    public static final int DEVICE_NOTIFY_SERVICE_HANDLE = 1;
    public static final int DEVICE_NOTIFY_WINDOW_HANDLE = 0;
    public static final int SW_SHOWDEFAULT = 10;
    public static final int WS_EX_TOPMOST = 8;
    public static final User32 INSTANCE = (User32) Native.load("user32", User32.class, W32APIOptions.DEFAULT_OPTIONS);
    public static final WinDef.HWND HWND_MESSAGE = new WinDef.HWND(Pointer.createConstant(-3));

    WinDef.BOOL AdjustWindowRect(WinDef.RECT rect, WinDef.DWORD dword, WinDef.BOOL bool);

    WinDef.BOOL AdjustWindowRectEx(WinDef.RECT rect, WinDef.DWORD dword, WinDef.BOOL bool, WinDef.DWORD dword2);

    boolean AttachThreadInput(WinDef.DWORD dword, WinDef.DWORD dword2, boolean z);

    boolean BringWindowToTop(WinDef.HWND hwnd);

    WinDef.LRESULT CallNextHookEx(WinUser.HHOOK hhook, int i, WinDef.WPARAM wparam, WinDef.LPARAM lparam);

    WinDef.LRESULT CallWindowProc(Pointer pointer, WinDef.HWND hwnd, int i, WinDef.WPARAM wparam, WinDef.LPARAM lparam);

    boolean CloseWindow(WinDef.HWND hwnd);

    WinDef.HICON CopyIcon(WinDef.HICON hicon);

    WinDef.HWND CreateWindowEx(int i, String str, String str2, int i2, int i3, int i4, int i5, int i6, WinDef.HWND hwnd, WinDef.HMENU hmenu, WinDef.HINSTANCE hinstance, WinDef.LPVOID lpvoid);

    WinDef.LRESULT DefWindowProc(WinDef.HWND hwnd, int i, WinDef.WPARAM wparam, WinDef.LPARAM lparam);

    boolean DestroyIcon(WinDef.HICON hicon);

    boolean DestroyWindow(WinDef.HWND hwnd);

    WinDef.LRESULT DispatchMessage(WinUser.MSG msg);

    boolean EnumChildWindows(WinDef.HWND hwnd, WinUser.WNDENUMPROC wndenumproc, Pointer pointer);

    WinDef.BOOL EnumDisplayMonitors(WinDef.HDC hdc, WinDef.RECT rect, WinUser.MONITORENUMPROC monitorenumproc, WinDef.LPARAM lparam);

    boolean EnumThreadWindows(int i, WinUser.WNDENUMPROC wndenumproc, Pointer pointer);

    boolean EnumWindows(WinUser.WNDENUMPROC wndenumproc, Pointer pointer);

    WinDef.BOOL ExitWindowsEx(WinDef.UINT uint, WinDef.DWORD dword);

    WinDef.HWND FindWindow(String str, String str2);

    WinDef.HWND FindWindowEx(WinDef.HWND hwnd, WinDef.HWND hwnd2, String str, String str2);

    boolean FlashWindowEx(WinUser.FLASHWINFO flashwinfo);

    WinDef.HWND GetActiveWindow();

    WinDef.HWND GetAncestor(WinDef.HWND hwnd, int i);

    short GetAsyncKeyState(int i);

    boolean GetClassInfoEx(WinDef.HINSTANCE hinstance, String str, WinUser.WNDCLASSEX wndclassex);

    int GetClassLong(WinDef.HWND hwnd, int i);

    BaseTSD.ULONG_PTR GetClassLongPtr(WinDef.HWND hwnd, int i);

    int GetClassName(WinDef.HWND hwnd, char[] cArr, int i);

    boolean GetClientRect(WinDef.HWND hwnd, WinDef.RECT rect);

    boolean GetCursorPos(WinDef.POINT point);

    WinDef.HDC GetDC(WinDef.HWND hwnd);

    WinDef.HWND GetDesktopWindow();

    WinDef.HWND GetForegroundWindow();

    boolean GetGUIThreadInfo(int i, WinUser.GUITHREADINFO guithreadinfo);

    boolean GetIconInfo(WinDef.HICON hicon, WinGDI.ICONINFO iconinfo);

    WinDef.HKL GetKeyboardLayout(int i);

    int GetKeyboardLayoutList(int i, WinDef.HKL[] hklArr);

    boolean GetKeyboardLayoutName(char[] cArr);

    boolean GetKeyboardState(byte[] bArr);

    boolean GetLastInputInfo(WinUser.LASTINPUTINFO lastinputinfo);

    boolean GetLayeredWindowAttributes(WinDef.HWND hwnd, IntByReference intByReference, ByteByReference byteByReference, IntByReference intByReference2);

    int GetMessage(WinUser.MSG msg, WinDef.HWND hwnd, int i, int i2);

    WinDef.BOOL GetMonitorInfo(WinUser.HMONITOR hmonitor, WinUser.MONITORINFO monitorinfo);

    WinDef.BOOL GetMonitorInfo(WinUser.HMONITOR hmonitor, WinUser.MONITORINFOEX monitorinfoex);

    WinDef.HWND GetParent(WinDef.HWND hwnd);

    int GetRawInputDeviceList(WinUser.RAWINPUTDEVICELIST[] rawinputdevicelistArr, IntByReference intByReference, int i);

    int GetSystemMetrics(int i);

    WinDef.HWND GetWindow(WinDef.HWND hwnd, WinDef.DWORD dword);

    boolean GetWindowInfo(WinDef.HWND hwnd, WinUser.WINDOWINFO windowinfo);

    int GetWindowLong(WinDef.HWND hwnd, int i);

    BaseTSD.LONG_PTR GetWindowLongPtr(WinDef.HWND hwnd, int i);

    int GetWindowModuleFileName(WinDef.HWND hwnd, char[] cArr, int i);

    WinDef.BOOL GetWindowPlacement(WinDef.HWND hwnd, WinUser.WINDOWPLACEMENT windowplacement);

    boolean GetWindowRect(WinDef.HWND hwnd, WinDef.RECT rect);

    int GetWindowText(WinDef.HWND hwnd, char[] cArr, int i);

    int GetWindowTextLength(WinDef.HWND hwnd);

    int GetWindowThreadProcessId(WinDef.HWND hwnd, IntByReference intByReference);

    boolean InvalidateRect(WinDef.HWND hwnd, WinDef.RECT rect, boolean z);

    boolean IsWindow(WinDef.HWND hwnd);

    boolean IsWindowEnabled(WinDef.HWND hwnd);

    boolean IsWindowVisible(WinDef.HWND hwnd);

    WinDef.HICON LoadIcon(WinDef.HINSTANCE hinstance, String str);

    WinNT.HANDLE LoadImage(WinDef.HINSTANCE hinstance, String str, int i, int i2, int i3, int i4);

    int LoadString(WinDef.HINSTANCE hinstance, int i, Pointer pointer, int i2);

    WinDef.BOOL LockWorkStation();

    int MapVirtualKeyEx(int i, int i2, WinDef.HKL hkl);

    WinUser.HMONITOR MonitorFromPoint(WinDef.POINT.ByValue byValue, int i);

    WinUser.HMONITOR MonitorFromRect(WinDef.RECT rect, int i);

    WinUser.HMONITOR MonitorFromWindow(WinDef.HWND hwnd, int i);

    boolean MoveWindow(WinDef.HWND hwnd, int i, int i2, int i3, int i4, boolean z);

    boolean PeekMessage(WinUser.MSG msg, WinDef.HWND hwnd, int i, int i2, int i3);

    void PostMessage(WinDef.HWND hwnd, int i, WinDef.WPARAM wparam, WinDef.LPARAM lparam);

    void PostQuitMessage(int i);

    int PostThreadMessage(int i, int i2, WinDef.WPARAM wparam, WinDef.LPARAM lparam);

    boolean PrintWindow(WinDef.HWND hwnd, WinDef.HDC hdc, int i);

    boolean RedrawWindow(WinDef.HWND hwnd, WinDef.RECT rect, WinDef.HRGN hrgn, WinDef.DWORD dword);

    WinDef.ATOM RegisterClassEx(WinUser.WNDCLASSEX wndclassex);

    int RegisterClipboardFormat(String str);

    WinUser.HDEVNOTIFY RegisterDeviceNotification(WinNT.HANDLE handle, Structure structure, int i);

    boolean RegisterHotKey(WinDef.HWND hwnd, int i, int i2, int i3);

    int RegisterWindowMessage(String str);

    int ReleaseDC(WinDef.HWND hwnd, WinDef.HDC hdc);

    WinDef.DWORD SendInput(WinDef.DWORD dword, WinUser.INPUT[] inputArr, int i);

    WinDef.LRESULT SendMessage(WinDef.HWND hwnd, int i, WinDef.WPARAM wparam, WinDef.LPARAM lparam);

    WinDef.LRESULT SendMessageTimeout(WinDef.HWND hwnd, int i, WinDef.WPARAM wparam, WinDef.LPARAM lparam, int i2, int i3, WinDef.DWORDByReference dWORDByReference);

    boolean SetCursorPos(long j, long j2);

    WinDef.HWND SetFocus(WinDef.HWND hwnd);

    boolean SetForegroundWindow(WinDef.HWND hwnd);

    boolean SetLayeredWindowAttributes(WinDef.HWND hwnd, int i, byte b, int i2);

    WinDef.HWND SetParent(WinDef.HWND hwnd, WinDef.HWND hwnd2);

    WinNT.HANDLE SetWinEventHook(int i, int i2, WinDef.HMODULE hmodule, WinUser.WinEventProc winEventProc, int i3, int i4, int i5);

    int SetWindowLong(WinDef.HWND hwnd, int i, int i2);

    Pointer SetWindowLongPtr(WinDef.HWND hwnd, int i, Pointer pointer);

    WinDef.BOOL SetWindowPlacement(WinDef.HWND hwnd, WinUser.WINDOWPLACEMENT windowplacement);

    boolean SetWindowPos(WinDef.HWND hwnd, WinDef.HWND hwnd2, int i, int i2, int i3, int i4, int i5);

    int SetWindowRgn(WinDef.HWND hwnd, WinDef.HRGN hrgn, boolean z);

    WinUser.HHOOK SetWindowsHookEx(int i, WinUser.HOOKPROC hookproc, WinDef.HINSTANCE hinstance, int i2);

    boolean ShowWindow(WinDef.HWND hwnd, int i);

    int ToUnicodeEx(int i, int i2, byte[] bArr, char[] cArr, int i3, int i4, WinDef.HKL hkl);

    boolean TranslateMessage(WinUser.MSG msg);

    boolean UnhookWinEvent(WinNT.HANDLE handle);

    boolean UnhookWindowsHookEx(WinUser.HHOOK hhook);

    boolean UnregisterClass(String str, WinDef.HINSTANCE hinstance);

    boolean UnregisterDeviceNotification(WinUser.HDEVNOTIFY hdevnotify);

    boolean UnregisterHotKey(Pointer pointer, int i);

    boolean UpdateLayeredWindow(WinDef.HWND hwnd, WinDef.HDC hdc, WinDef.POINT point, WinUser.SIZE size, WinDef.HDC hdc2, WinDef.POINT point2, int i, WinUser.BLENDFUNCTION blendfunction, int i2);

    boolean UpdateWindow(WinDef.HWND hwnd);

    short VkKeyScanExA(byte b, WinDef.HKL hkl);

    short VkKeyScanExW(char c, WinDef.HKL hkl);

    WinDef.DWORD WaitForInputIdle(WinNT.HANDLE handle, WinDef.DWORD dword);
}
