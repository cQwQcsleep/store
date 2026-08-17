package com.sun.jna.platform.win32;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.W32APITypeMapper;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class User32Util {
    public static final EnumSet<Win32VK> WIN32VK_MAPPABLE = EnumSet.of(Win32VK.VK_BACK, Win32VK.VK_TAB, Win32VK.VK_CLEAR, Win32VK.VK_RETURN, Win32VK.VK_ESCAPE, Win32VK.VK_SPACE, Win32VK.VK_SELECT, Win32VK.VK_EXECUTE, Win32VK.VK_0, Win32VK.VK_1, Win32VK.VK_2, Win32VK.VK_3, Win32VK.VK_4, Win32VK.VK_5, Win32VK.VK_6, Win32VK.VK_7, Win32VK.VK_8, Win32VK.VK_9, Win32VK.VK_A, Win32VK.VK_B, Win32VK.VK_C, Win32VK.VK_D, Win32VK.VK_E, Win32VK.VK_F, Win32VK.VK_G, Win32VK.VK_H, Win32VK.VK_I, Win32VK.VK_J, Win32VK.VK_K, Win32VK.VK_L, Win32VK.VK_M, Win32VK.VK_N, Win32VK.VK_O, Win32VK.VK_P, Win32VK.VK_Q, Win32VK.VK_R, Win32VK.VK_S, Win32VK.VK_T, Win32VK.VK_U, Win32VK.VK_V, Win32VK.VK_W, Win32VK.VK_X, Win32VK.VK_Y, Win32VK.VK_Z, Win32VK.VK_NUMPAD0, Win32VK.VK_NUMPAD1, Win32VK.VK_NUMPAD2, Win32VK.VK_NUMPAD3, Win32VK.VK_NUMPAD4, Win32VK.VK_NUMPAD5, Win32VK.VK_NUMPAD6, Win32VK.VK_NUMPAD7, Win32VK.VK_NUMPAD8, Win32VK.VK_NUMPAD9, Win32VK.VK_MULTIPLY, Win32VK.VK_ADD, Win32VK.VK_SEPARATOR, Win32VK.VK_SUBTRACT, Win32VK.VK_DECIMAL, Win32VK.VK_DIVIDE, Win32VK.VK_OEM_NEC_EQUAL, Win32VK.VK_OEM_FJ_MASSHOU, Win32VK.VK_OEM_FJ_TOUROKU, Win32VK.VK_OEM_FJ_LOYA, Win32VK.VK_OEM_FJ_ROYA, Win32VK.VK_OEM_1, Win32VK.VK_OEM_PLUS, Win32VK.VK_OEM_COMMA, Win32VK.VK_OEM_MINUS, Win32VK.VK_OEM_PERIOD, Win32VK.VK_OEM_2, Win32VK.VK_OEM_3, Win32VK.VK_RESERVED_C1, Win32VK.VK_RESERVED_C2, Win32VK.VK_OEM_4, Win32VK.VK_OEM_5, Win32VK.VK_OEM_6, Win32VK.VK_OEM_7, Win32VK.VK_OEM_8, Win32VK.VK_OEM_AX, Win32VK.VK_OEM_102, Win32VK.VK_ICO_HELP, Win32VK.VK_PROCESSKEY, Win32VK.VK_ICO_CLEAR, Win32VK.VK_PACKET, Win32VK.VK_OEM_RESET, Win32VK.VK_OEM_JUMP, Win32VK.VK_OEM_PA1, Win32VK.VK_OEM_PA2, Win32VK.VK_OEM_PA3, Win32VK.VK_OEM_WSCTRL, Win32VK.VK_OEM_CUSEL, Win32VK.VK_OEM_ATTN, Win32VK.VK_OEM_FINISH, Win32VK.VK_OEM_COPY, Win32VK.VK_OEM_AUTO, Win32VK.VK_OEM_ENLW, Win32VK.VK_OEM_BACKTAB, Win32VK.VK_ATTN, Win32VK.VK_CRSEL, Win32VK.VK_EXSEL, Win32VK.VK_EREOF, Win32VK.VK_PLAY, Win32VK.VK_ZOOM, Win32VK.VK_NONAME, Win32VK.VK_PA1, Win32VK.VK_OEM_CLEAR);

    public static class MessageLoopThread extends Thread {
        private static long messageLoopId;
        private volatile int nativeThreadId = 0;
        private volatile long javaThreadId = 0;
        private final List<FutureTask> workQueue = Collections.synchronizedList(new ArrayList());

        public class Handler implements InvocationHandler {
            private final Object delegate;

            public Handler(Object obj) {
                this.delegate = obj;
            }

            @Override // java.lang.reflect.InvocationHandler
            public Object invoke(Object obj, final Method method, final Object[] objArr) throws Exception {
                try {
                    return MessageLoopThread.this.runOnThread(new Callable<Object>() { // from class: com.sun.jna.platform.win32.User32Util.MessageLoopThread.Handler.1
                        @Override // java.util.concurrent.Callable
                        public Object call() throws Exception {
                            return method.invoke(Handler.this.delegate, objArr);
                        }
                    });
                } catch (InvocationTargetException e) {
                    Throwable cause = e.getCause();
                    if (!(cause instanceof Exception)) {
                        throw e;
                    }
                    StackTraceElement[] stackTrace = cause.getStackTrace();
                    cause.fillInStackTrace();
                    StackTraceElement[] stackTrace2 = cause.getStackTrace();
                    StackTraceElement[] stackTraceElementArr = new StackTraceElement[stackTrace2.length + stackTrace.length];
                    System.arraycopy(stackTrace, 0, stackTraceElementArr, 0, stackTrace.length);
                    System.arraycopy(stackTrace2, 0, stackTraceElementArr, stackTrace.length, stackTrace2.length);
                    cause.setStackTrace(stackTraceElementArr);
                    throw ((Exception) cause);
                }
            }
        }

        public MessageLoopThread() {
            StringBuilder sb = new StringBuilder("JNA User32 MessageLoop ");
            long j = messageLoopId + 1;
            messageLoopId = j;
            sb.append(j);
            setName(sb.toString());
        }

        public void exit() {
            User32.INSTANCE.PostThreadMessage(this.nativeThreadId, 18, null, null);
        }

        public boolean getMessageFailed() {
            Logger.getLogger("com.sun.jna.platform.win32.User32Util.MessageLoopThread").log(Level.WARNING, "Message loop was interrupted by an error. [lastError: {0}]", Integer.valueOf(Kernel32.INSTANCE.GetLastError()));
            return true;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            WinUser.MSG msg = new WinUser.MSG();
            User32.INSTANCE.PeekMessage(msg, null, 0, 0, 0);
            this.javaThreadId = Thread.currentThread().getId();
            this.nativeThreadId = Kernel32.INSTANCE.GetCurrentThreadId();
            while (true) {
                int iGetMessage = User32.INSTANCE.GetMessage(msg, null, 0, 0);
                if (iGetMessage == 0) {
                    break;
                }
                if (iGetMessage != -1) {
                    while (!this.workQueue.isEmpty()) {
                        try {
                            this.workQueue.remove(0).run();
                        } catch (IndexOutOfBoundsException unused) {
                        }
                    }
                    User32 user32 = User32.INSTANCE;
                    user32.TranslateMessage(msg);
                    user32.DispatchMessage(msg);
                } else if (getMessageFailed()) {
                    break;
                }
            }
            while (!this.workQueue.isEmpty()) {
                this.workQueue.remove(0).cancel(false);
            }
        }

        public <V> Future<V> runAsync(Callable<V> callable) {
            while (this.nativeThreadId == 0) {
                try {
                    Thread.sleep(20L);
                } catch (InterruptedException e) {
                    Logger.getLogger(MessageLoopThread.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
                }
            }
            FutureTask futureTask = new FutureTask(callable);
            this.workQueue.add(futureTask);
            User32.INSTANCE.PostThreadMessage(this.nativeThreadId, 1024, null, null);
            return futureTask;
        }

        public <V> V runOnThread(Callable<V> callable) throws Exception {
            while (this.javaThreadId == 0) {
                try {
                    Thread.sleep(20L);
                } catch (InterruptedException e) {
                    Logger.getLogger(MessageLoopThread.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
                }
            }
            if (this.javaThreadId == Thread.currentThread().getId()) {
                return callable.call();
            }
            try {
                return runAsync(callable).get();
            } catch (InterruptedException e2) {
                throw e2;
            } catch (ExecutionException e3) {
                Throwable cause = e3.getCause();
                if (cause instanceof Exception) {
                    throw ((Exception) cause);
                }
                throw e3;
            }
        }
    }

    public static final List<WinUser.RAWINPUTDEVICELIST> GetRawInputDeviceList() {
        IntByReference intByReference = new IntByReference(0);
        WinUser.RAWINPUTDEVICELIST rawinputdevicelist = new WinUser.RAWINPUTDEVICELIST();
        int iSizeof = rawinputdevicelist.sizeof();
        User32 user32 = User32.INSTANCE;
        if (user32.GetRawInputDeviceList(null, intByReference, iSizeof) != 0) {
            throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
        }
        WinUser.RAWINPUTDEVICELIST[] rawinputdevicelistArr = (WinUser.RAWINPUTDEVICELIST[]) rawinputdevicelist.toArray(intByReference.getValue());
        int iGetRawInputDeviceList = user32.GetRawInputDeviceList(rawinputdevicelistArr, intByReference, iSizeof);
        if (iGetRawInputDeviceList == -1) {
            throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
        }
        if (iGetRawInputDeviceList == rawinputdevicelistArr.length) {
            return Arrays.asList(rawinputdevicelistArr);
        }
        throw new IllegalStateException("Mismatched allocated (" + rawinputdevicelistArr.length + ") vs. received devices count (" + iGetRawInputDeviceList + ")");
    }

    public static final WinDef.HWND createWindow(String str, String str2, int i, int i2, int i3, int i4, int i5, WinDef.HWND hwnd, WinDef.HMENU hmenu, WinDef.HINSTANCE hinstance, WinDef.LPVOID lpvoid) {
        return createWindowEx(0, str, str2, i, i2, i3, i4, i5, hwnd, hmenu, hinstance, lpvoid);
    }

    public static final WinDef.HWND createWindowEx(int i, String str, String str2, int i2, int i3, int i4, int i5, int i6, WinDef.HWND hwnd, WinDef.HMENU hmenu, WinDef.HINSTANCE hinstance, WinDef.LPVOID lpvoid) {
        WinDef.HWND hwndCreateWindowEx = User32.INSTANCE.CreateWindowEx(i, str, str2, i2, i3, i4, i5, i6, hwnd, hmenu, hinstance, lpvoid);
        if (hwndCreateWindowEx != null) {
            return hwndCreateWindowEx;
        }
        throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
    }

    public static final void destroyWindow(WinDef.HWND hwnd) {
        if (!User32.INSTANCE.DestroyWindow(hwnd)) {
            throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
        }
    }

    public static String loadString(String str) throws UnsupportedEncodingException {
        int iLastIndexOf = str.lastIndexOf(44);
        String strSubstring = str.substring(0, iLastIndexOf);
        int iAbs = Math.abs(Integer.parseInt(str.substring(iLastIndexOf + 1)));
        String strExpandEnvironmentStrings = Kernel32Util.expandEnvironmentStrings(strSubstring);
        Kernel32 kernel32 = Kernel32.INSTANCE;
        WinDef.HMODULE hmoduleLoadLibraryEx = kernel32.LoadLibraryEx(strExpandEnvironmentStrings, null, 2);
        if (hmoduleLoadLibraryEx == null) {
            throw new Win32Exception(kernel32.GetLastError());
        }
        Memory memory = new Memory(Native.POINTER_SIZE);
        int iLoadString = User32.INSTANCE.LoadString(hmoduleLoadLibraryEx, iAbs, memory, 0);
        if (iLoadString != 0) {
            return W32APITypeMapper.DEFAULT == W32APITypeMapper.UNICODE ? new String(memory.getPointer(0L).getCharArray(0L, iLoadString)) : new String(memory.getPointer(0L).getByteArray(0L, iLoadString), Native.getDefaultStringEncoding());
        }
        throw new Win32Exception(kernel32.GetLastError());
    }

    public static final int registerWindowMessage(String str) {
        int iRegisterWindowMessage = User32.INSTANCE.RegisterWindowMessage(str);
        if (iRegisterWindowMessage != 0) {
            return iRegisterWindowMessage;
        }
        throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
    }
}
