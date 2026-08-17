package com.sun.jna.platform.win32;

import com.sun.jna.Structure;
import com.sun.jna.Union;
import com.sun.jna.ptr.IntByReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface Wincon {
    public static final int ATTACH_PARENT_PROCESS = -1;
    public static final int CONSOLE_FULLSCREEN = 1;
    public static final int CONSOLE_FULLSCREEN_HARDWARE = 2;
    public static final int CTRL_BREAK_EVENT = 1;
    public static final int CTRL_C_EVENT = 0;
    public static final int DISABLE_NEWLINE_AUTO_RETURN = 8;
    public static final int ENABLE_ECHO_INPUT = 4;
    public static final int ENABLE_EXTENDED_FLAGS = 128;
    public static final int ENABLE_INSERT_MODE = 32;
    public static final int ENABLE_LINE_INPUT = 2;
    public static final int ENABLE_MOUSE_INPUT = 16;
    public static final int ENABLE_PROCESSED_INPUT = 1;
    public static final int ENABLE_PROCESSED_OUTPUT = 1;
    public static final int ENABLE_QUICK_EDIT_MODE = 64;
    public static final int ENABLE_VIRTUAL_TERMINAL_INPUT = 512;
    public static final int ENABLE_VIRTUAL_TERMINAL_PROCESSING = 4;
    public static final int ENABLE_WINDOW_INPUT = 8;
    public static final int ENABLE_WRAP_AT_EOL_OUTPUT = 2;
    public static final int MAX_CONSOLE_TITLE_LENGTH = 65536;
    public static final int STD_ERROR_HANDLE = -12;
    public static final int STD_INPUT_HANDLE = -10;
    public static final int STD_OUTPUT_HANDLE = -11;

    @Structure.FieldOrder({"dwSize", "dwCursorPosition", "wAttributes", "srWindow", "dwMaximumWindowSize"})
    public static class CONSOLE_SCREEN_BUFFER_INFO extends Structure {
        public COORD dwCursorPosition;
        public COORD dwMaximumWindowSize;
        public COORD dwSize;
        public SMALL_RECT srWindow;
        public short wAttributes;

        @Override // com.sun.jna.Structure
        public String toString() {
            return String.format("CONSOLE_SCREEN_BUFFER_INFO(%s,%s,%s,%s,%s)", this.dwSize, this.dwCursorPosition, Short.valueOf(this.wAttributes), this.srWindow, this.dwMaximumWindowSize);
        }
    }

    @Structure.FieldOrder({"X", "Y"})
    public static class COORD extends Structure {
        public short X;
        public short Y;

        @Override // com.sun.jna.Structure
        public String toString() {
            return String.format("COORD(%s,%s)", Short.valueOf(this.X), Short.valueOf(this.Y));
        }
    }

    @Structure.FieldOrder({"EventType", "Event"})
    public static class INPUT_RECORD extends Structure {
        public static final short KEY_EVENT = 1;
        public static final short MOUSE_EVENT = 2;
        public static final short WINDOW_BUFFER_SIZE_EVENT = 4;
        public Event Event;
        public short EventType;

        public static class Event extends Union {
            public KEY_EVENT_RECORD KeyEvent;
            public MOUSE_EVENT_RECORD MouseEvent;
            public WINDOW_BUFFER_SIZE_RECORD WindowBufferSizeEvent;
        }

        @Override // com.sun.jna.Structure
        public void read() {
            super.read();
            short s = this.EventType;
            if (s == 1) {
                this.Event.setType("KeyEvent");
            } else if (s == 2) {
                this.Event.setType("MouseEvent");
            } else if (s == 4) {
                this.Event.setType("WindowBufferSizeEvent");
            }
            this.Event.read();
        }

        @Override // com.sun.jna.Structure
        public String toString() {
            return String.format("INPUT_RECORD(%s)", Short.valueOf(this.EventType));
        }
    }

    @Structure.FieldOrder({"bKeyDown", "wRepeatCount", "wVirtualKeyCode", "wVirtualScanCode", "uChar", "dwControlKeyState"})
    public static class KEY_EVENT_RECORD extends Structure {
        public boolean bKeyDown;
        public int dwControlKeyState;
        public char uChar;
        public short wRepeatCount;
        public short wVirtualKeyCode;
        public short wVirtualScanCode;

        @Override // com.sun.jna.Structure
        public String toString() {
            return String.format("KEY_EVENT_RECORD(%s,%s,%s,%s,%s,%s)", Boolean.valueOf(this.bKeyDown), Short.valueOf(this.wRepeatCount), Short.valueOf(this.wVirtualKeyCode), Short.valueOf(this.wVirtualKeyCode), Short.valueOf(this.wVirtualScanCode), Character.valueOf(this.uChar), Integer.valueOf(this.dwControlKeyState));
        }
    }

    @Structure.FieldOrder({"dwMousePosition", "dwButtonState", "dwControlKeyState", "dwEventFlags"})
    public static class MOUSE_EVENT_RECORD extends Structure {
        public int dwButtonState;
        public int dwControlKeyState;
        public int dwEventFlags;
        public COORD dwMousePosition;

        @Override // com.sun.jna.Structure
        public String toString() {
            return String.format("MOUSE_EVENT_RECORD(%s,%s,%s,%s)", this.dwMousePosition, Integer.valueOf(this.dwButtonState), Integer.valueOf(this.dwControlKeyState), Integer.valueOf(this.dwEventFlags));
        }
    }

    @Structure.FieldOrder({"Left", "Top", "Right", "Bottom"})
    public static class SMALL_RECT extends Structure {
        public short Bottom;
        public short Left;
        public short Right;
        public short Top;

        @Override // com.sun.jna.Structure
        public String toString() {
            return String.format("SMALL_RECT(%s,%s)(%s,%s)", Short.valueOf(this.Left), Short.valueOf(this.Top), Short.valueOf(this.Right), Short.valueOf(this.Bottom));
        }
    }

    @Structure.FieldOrder({"dwSize"})
    public static class WINDOW_BUFFER_SIZE_RECORD extends Structure {
        public COORD dwSize;

        @Override // com.sun.jna.Structure
        public String toString() {
            return String.format("WINDOW_BUFFER_SIZE_RECORD(%s)", this.dwSize);
        }
    }

    boolean AllocConsole();

    boolean AttachConsole(int i);

    boolean FlushConsoleInputBuffer(WinNT.HANDLE handle);

    boolean FreeConsole();

    boolean GenerateConsoleCtrlEvent(int i, int i2);

    int GetConsoleCP();

    boolean GetConsoleDisplayMode(IntByReference intByReference);

    boolean GetConsoleMode(WinNT.HANDLE handle, IntByReference intByReference);

    int GetConsoleOriginalTitle(char[] cArr, int i);

    int GetConsoleOutputCP();

    boolean GetConsoleScreenBufferInfo(WinNT.HANDLE handle, CONSOLE_SCREEN_BUFFER_INFO console_screen_buffer_info);

    int GetConsoleTitle(char[] cArr, int i);

    WinDef.HWND GetConsoleWindow();

    boolean GetNumberOfConsoleInputEvents(WinNT.HANDLE handle, IntByReference intByReference);

    boolean GetNumberOfConsoleMouseButtons(IntByReference intByReference);

    WinNT.HANDLE GetStdHandle(int i);

    boolean ReadConsoleInput(WinNT.HANDLE handle, INPUT_RECORD[] input_recordArr, int i, IntByReference intByReference);

    boolean SetConsoleCP(int i);

    boolean SetConsoleMode(WinNT.HANDLE handle, int i);

    boolean SetConsoleOutputCP(int i);

    boolean SetConsoleTitle(String str);

    boolean SetStdHandle(int i, WinNT.HANDLE handle);

    boolean WriteConsole(WinNT.HANDLE handle, String str, int i, IntByReference intByReference, WinDef.LPVOID lpvoid);
}
