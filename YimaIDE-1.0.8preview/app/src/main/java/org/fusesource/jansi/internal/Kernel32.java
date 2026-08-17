package org.fusesource.jansi.internal;

import java.io.IOException;
import org.fusesource.jansi.WindowsSupport;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class Kernel32 {
    public static short BACKGROUND_BLUE;
    public static short BACKGROUND_GREEN;
    public static short BACKGROUND_INTENSITY;
    public static short BACKGROUND_RED;
    public static short COMMON_LVB_GRID_HORIZONTAL;
    public static short COMMON_LVB_GRID_LVERTICAL;
    public static short COMMON_LVB_GRID_RVERTICAL;
    public static short COMMON_LVB_LEADING_BYTE;
    public static short COMMON_LVB_REVERSE_VIDEO;
    public static short COMMON_LVB_TRAILING_BYTE;
    public static short COMMON_LVB_UNDERSCORE;
    public static short FOREGROUND_BLUE;
    public static short FOREGROUND_GREEN;
    public static short FOREGROUND_INTENSITY;
    public static short FOREGROUND_RED;
    public static int FORMAT_MESSAGE_FROM_SYSTEM;
    public static int INVALID_HANDLE_VALUE;
    public static int STD_ERROR_HANDLE;
    public static int STD_INPUT_HANDLE;
    public static int STD_OUTPUT_HANDLE;

    public static class CHAR_INFO {
        public static int SIZEOF;
        public short attributes;
        public char unicodeChar;

        static {
            JansiLoader.initialize();
            init();
        }

        private static native void init();
    }

    public static class CONSOLE_SCREEN_BUFFER_INFO {
        public static int SIZEOF;
        public short attributes;
        public COORD size = new COORD();
        public COORD cursorPosition = new COORD();
        public SMALL_RECT window = new SMALL_RECT();
        public COORD maximumWindowSize = new COORD();

        static {
            JansiLoader.initialize();
            init();
        }

        private static native void init();

        public int windowHeight() {
            return this.window.height() + 1;
        }

        public int windowWidth() {
            return this.window.width() + 1;
        }
    }

    public static class COORD {
        public static int SIZEOF;
        public short x;
        public short y;

        static {
            JansiLoader.initialize();
            init();
        }

        private static native void init();

        public COORD copy() {
            COORD coord = new COORD();
            coord.x = this.x;
            coord.y = this.y;
            return coord;
        }
    }

    public static class FOCUS_EVENT_RECORD {
        public static int SIZEOF;
        public boolean setFocus;

        static {
            JansiLoader.initialize();
            init();
        }

        private static native void init();
    }

    public static class INPUT_RECORD {
        public static short FOCUS_EVENT;
        public static short KEY_EVENT;
        public static short MENU_EVENT;
        public static short MOUSE_EVENT;
        public static int SIZEOF;
        public static short WINDOW_BUFFER_SIZE_EVENT;
        public short eventType;
        public KEY_EVENT_RECORD keyEvent = new KEY_EVENT_RECORD();
        public MOUSE_EVENT_RECORD mouseEvent = new MOUSE_EVENT_RECORD();
        public WINDOW_BUFFER_SIZE_RECORD windowBufferSizeEvent = new WINDOW_BUFFER_SIZE_RECORD();
        public MENU_EVENT_RECORD menuEvent = new MENU_EVENT_RECORD();
        public FOCUS_EVENT_RECORD focusEvent = new FOCUS_EVENT_RECORD();

        static {
            JansiLoader.initialize();
            init();
        }

        private static native void init();

        public static native void memmove(INPUT_RECORD input_record, long j, long j2);
    }

    public static class KEY_EVENT_RECORD {
        public static int CAPSLOCK_ON;
        public static int ENHANCED_KEY;
        public static int LEFT_ALT_PRESSED;
        public static int LEFT_CTRL_PRESSED;
        public static int NUMLOCK_ON;
        public static int RIGHT_ALT_PRESSED;
        public static int RIGHT_CTRL_PRESSED;
        public static int SCROLLLOCK_ON;
        public static int SHIFT_PRESSED;
        public static int SIZEOF;
        public int controlKeyState;
        public short keyCode;
        public boolean keyDown;
        public short repeatCount;
        public short scanCode;
        public char uchar;

        static {
            JansiLoader.initialize();
            init();
        }

        private static native void init();

        public String toString() {
            return "KEY_EVENT_RECORD{keyDown=" + this.keyDown + ", repeatCount=" + ((int) this.repeatCount) + ", keyCode=" + ((int) this.keyCode) + ", scanCode=" + ((int) this.scanCode) + ", uchar=" + this.uchar + ", controlKeyState=" + this.controlKeyState + '}';
        }
    }

    public static class MENU_EVENT_RECORD {
        public static int SIZEOF;
        public int commandId;

        static {
            JansiLoader.initialize();
            init();
        }

        private static native void init();
    }

    public static class MOUSE_EVENT_RECORD {
        public static int CAPSLOCK_ON;
        public static int DOUBLE_CLICK;
        public static int ENHANCED_KEY;
        public static int FROM_LEFT_1ST_BUTTON_PRESSED;
        public static int FROM_LEFT_2ND_BUTTON_PRESSED;
        public static int FROM_LEFT_3RD_BUTTON_PRESSED;
        public static int FROM_LEFT_4TH_BUTTON_PRESSED;
        public static int LEFT_ALT_PRESSED;
        public static int LEFT_CTRL_PRESSED;
        public static int MOUSE_HWHEELED;
        public static int MOUSE_MOVED;
        public static int MOUSE_WHEELED;
        public static int NUMLOCK_ON;
        public static int RIGHTMOST_BUTTON_PRESSED;
        public static int RIGHT_ALT_PRESSED;
        public static int RIGHT_CTRL_PRESSED;
        public static int SCROLLLOCK_ON;
        public static int SHIFT_PRESSED;
        public static int SIZEOF;
        public int buttonState;
        public int controlKeyState;
        public int eventFlags;
        public COORD mousePosition = new COORD();

        static {
            JansiLoader.initialize();
            init();
        }

        private static native void init();

        public String toString() {
            return "MOUSE_EVENT_RECORD{mousePosition=" + this.mousePosition + ", buttonState=" + this.buttonState + ", controlKeyState=" + this.controlKeyState + ", eventFlags=" + this.eventFlags + '}';
        }
    }

    public static class SMALL_RECT {
        public static int SIZEOF;
        public short bottom;
        public short left;
        public short right;
        public short top;

        static {
            JansiLoader.initialize();
            init();
        }

        private static native void init();

        public SMALL_RECT copy() {
            SMALL_RECT small_rect = new SMALL_RECT();
            small_rect.left = this.left;
            small_rect.top = this.top;
            small_rect.right = this.right;
            small_rect.bottom = this.bottom;
            return small_rect;
        }

        public short height() {
            return (short) (this.bottom - this.top);
        }

        public short width() {
            return (short) (this.right - this.left);
        }
    }

    public static class WINDOW_BUFFER_SIZE_RECORD {
        public static int SIZEOF;
        public COORD size = new COORD();

        static {
            JansiLoader.initialize();
            init();
        }

        private static native void init();

        public String toString() {
            return "WINDOW_BUFFER_SIZE_RECORD{size=" + this.size + '}';
        }
    }

    static {
        if (JansiLoader.initialize()) {
            init();
        }
    }

    public static native int CloseHandle(long j);

    public static native int FillConsoleOutputAttribute(long j, short s, int i, COORD coord, int[] iArr);

    public static native int FillConsoleOutputCharacterW(long j, char c, int i, COORD coord, int[] iArr);

    public static native int FlushConsoleInputBuffer(long j);

    public static native int FormatMessageW(int i, long j, int i2, int i3, byte[] bArr, int i4, long[] jArr);

    public static native int GetConsoleMode(long j, int[] iArr);

    public static native int GetConsoleOutputCP();

    public static native int GetConsoleScreenBufferInfo(long j, CONSOLE_SCREEN_BUFFER_INFO console_screen_buffer_info);

    public static native int GetLastError();

    public static native int GetNumberOfConsoleInputEvents(long j, int[] iArr);

    public static native long GetStdHandle(int i);

    private static native int PeekConsoleInputW(long j, long j2, int i, int[] iArr);

    private static native int ReadConsoleInputW(long j, long j2, int i, int[] iArr);

    public static native int ScrollConsoleScreenBuffer(long j, SMALL_RECT small_rect, SMALL_RECT small_rect2, COORD coord, CHAR_INFO char_info);

    public static native int SetConsoleCursorPosition(long j, COORD coord);

    public static native int SetConsoleMode(long j, int i);

    public static native int SetConsoleOutputCP(int i);

    public static native int SetConsoleTextAttribute(long j, short s);

    public static native int SetConsoleTitle(String str);

    public static native int WaitForSingleObject(long j, int i);

    public static native int WriteConsoleW(long j, char[] cArr, int i, int[] iArr, long j2);

    public static native int _getch();

    public static native void free(long j);

    private static native void init();

    public static native long malloc(long j);

    public static INPUT_RECORD[] readConsoleInputHelper(long j, int i, boolean z) throws Throwable {
        Throwable th;
        long jMalloc;
        int[] iArr = new int[1];
        try {
            jMalloc = malloc(INPUT_RECORD.SIZEOF * i);
            try {
                if (jMalloc == 0) {
                    throw new IOException("cannot allocate memory with JNI");
                }
                if ((z ? PeekConsoleInputW(j, jMalloc, i, iArr) : ReadConsoleInputW(j, jMalloc, i, iArr)) == 0) {
                    throw new IOException("ReadConsoleInputW failed: " + WindowsSupport.getLastErrorMessage());
                }
                int i2 = iArr[0];
                if (i2 <= 0) {
                    INPUT_RECORD[] input_recordArr = new INPUT_RECORD[0];
                    if (jMalloc != 0) {
                        free(jMalloc);
                    }
                    return input_recordArr;
                }
                INPUT_RECORD[] input_recordArr2 = new INPUT_RECORD[i2];
                for (int i3 = 0; i3 < i2; i3++) {
                    INPUT_RECORD input_record = new INPUT_RECORD();
                    input_recordArr2[i3] = input_record;
                    int i4 = INPUT_RECORD.SIZEOF;
                    INPUT_RECORD.memmove(input_record, ((long) (i3 * i4)) + jMalloc, i4);
                }
                if (jMalloc != 0) {
                    free(jMalloc);
                }
                return input_recordArr2;
            } catch (Throwable th2) {
                th = th2;
                if (jMalloc == 0) {
                    throw th;
                }
                free(jMalloc);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            jMalloc = 0;
        }
    }

    public static INPUT_RECORD[] readConsoleKeyInput(long j, int i, boolean z) throws Throwable {
        INPUT_RECORD[] consoleInputHelper;
        int i2;
        int i3;
        do {
            consoleInputHelper = readConsoleInputHelper(j, i, z);
            i3 = 0;
            for (INPUT_RECORD input_record : consoleInputHelper) {
                if (input_record.eventType == INPUT_RECORD.KEY_EVENT) {
                    i3++;
                }
            }
        } while (i3 <= 0);
        INPUT_RECORD[] input_recordArr = new INPUT_RECORD[i3];
        int i4 = 0;
        for (INPUT_RECORD input_record2 : consoleInputHelper) {
            if (input_record2.eventType == INPUT_RECORD.KEY_EVENT) {
                input_recordArr[i4] = input_record2;
                i4++;
            }
        }
        return input_recordArr;
    }
}
