package org.fusesource.jansi.io;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import org.eclipse.jdt.internal.compiler.parser.TerminalTokens;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class AnsiProcessor {
    protected static final int ATTRIBUTE_BLINK_FAST = 6;
    protected static final int ATTRIBUTE_BLINK_OFF = 25;
    protected static final int ATTRIBUTE_BLINK_SLOW = 5;
    protected static final int ATTRIBUTE_CONCEAL_OFF = 28;
    protected static final int ATTRIBUTE_CONCEAL_ON = 8;
    protected static final int ATTRIBUTE_INTENSITY_BOLD = 1;
    protected static final int ATTRIBUTE_INTENSITY_FAINT = 2;
    protected static final int ATTRIBUTE_INTENSITY_NORMAL = 22;
    protected static final int ATTRIBUTE_ITALIC = 3;
    protected static final int ATTRIBUTE_NEGATIVE_OFF = 27;
    protected static final int ATTRIBUTE_NEGATIVE_ON = 7;
    protected static final int ATTRIBUTE_UNDERLINE = 4;
    protected static final int ATTRIBUTE_UNDERLINE_DOUBLE = 21;
    protected static final int ATTRIBUTE_UNDERLINE_OFF = 24;
    protected static final int BLACK = 0;
    protected static final int BLUE = 4;
    protected static final int CYAN = 6;
    protected static final int ERASE_LINE = 2;
    protected static final int ERASE_LINE_TO_BEGINING = 1;
    protected static final int ERASE_LINE_TO_END = 0;
    protected static final int ERASE_SCREEN = 2;
    protected static final int ERASE_SCREEN_TO_BEGINING = 1;
    protected static final int ERASE_SCREEN_TO_END = 0;
    protected static final int GREEN = 2;
    protected static final int MAGENTA = 5;
    protected static final int RED = 1;
    protected static final int WHITE = 7;
    protected static final int YELLOW = 3;
    protected final OutputStream os;

    public AnsiProcessor(OutputStream outputStream) {
        this.os = outputStream;
    }

    private int optionInt(ArrayList<Object> arrayList, int i) {
        if (arrayList.size() <= i) {
            j2d.a();
            return 0;
        }
        Object obj = arrayList.get(i);
        if (obj == null) {
            j2d.a();
            return 0;
        }
        if (obj.getClass().equals(Integer.class)) {
            return ((Integer) obj).intValue();
        }
        j2d.a();
        return 0;
    }

    public int getNextOptionInt(Iterator<Object> it) throws IOException {
        while (it.hasNext()) {
            Object next = it.next();
            if (next != null) {
                return ((Integer) next).intValue();
            }
        }
        j2d.a();
        return 0;
    }

    public void processAttributeReset() throws IOException {
    }

    public void processChangeIconName(String str) {
    }

    public void processChangeIconNameAndWindowTitle(String str) {
        processChangeIconName(str);
        processChangeWindowTitle(str);
    }

    public void processChangeWindowTitle(String str) {
    }

    public boolean processCharsetSelect(ArrayList<Object> arrayList) {
        processCharsetSelect(optionInt(arrayList, 0), ((Character) arrayList.get(1)).charValue());
        return true;
    }

    public void processCursorDown(int i) throws IOException {
    }

    public void processCursorDownLine(int i) throws IOException {
        for (int i2 = 0; i2 < i; i2++) {
            this.os.write(10);
        }
    }

    public void processCursorLeft(int i) throws IOException {
    }

    public void processCursorRight(int i) throws IOException {
        for (int i2 = 0; i2 < i; i2++) {
            this.os.write(32);
        }
    }

    public void processCursorTo(int i, int i2) throws IOException {
    }

    public void processCursorToColumn(int i) throws IOException {
    }

    public void processCursorUp(int i) throws IOException {
    }

    public void processCursorUpLine(int i) throws IOException {
    }

    public void processDefaultBackgroundColor() throws IOException {
    }

    public void processDefaultTextColor() throws IOException {
    }

    public void processDeleteLine(int i) throws IOException {
    }

    public void processEraseLine(int i) throws IOException {
    }

    public void processEraseScreen(int i) throws IOException {
    }

    public boolean processEscapeCommand(ArrayList<Object> arrayList, int i) throws IOException {
        try {
            if (i == 83) {
                processScrollUp(optionInt(arrayList, 0, 1));
                return true;
            }
            if (i == 84) {
                processScrollDown(optionInt(arrayList, 0, 1));
                return true;
            }
            if (i != 102) {
                if (i == 109) {
                    for (Object obj : arrayList) {
                        if (obj != null && obj.getClass() != Integer.class) {
                            throw new IllegalArgumentException();
                        }
                    }
                    Iterator<Object> it = arrayList.iterator();
                    int i2 = 0;
                    while (it.hasNext()) {
                        Object next = it.next();
                        if (next != null) {
                            i2++;
                            int iIntValue = ((Integer) next).intValue();
                            if (30 <= iIntValue && iIntValue <= 37) {
                                processSetForegroundColor(iIntValue - 30);
                            } else if (40 <= iIntValue && iIntValue <= 47) {
                                processSetBackgroundColor(iIntValue - 40);
                            } else if (90 <= iIntValue && iIntValue <= 97) {
                                processSetForegroundColor(iIntValue - 90, true);
                            } else if (100 <= iIntValue && iIntValue <= 107) {
                                processSetBackgroundColor(iIntValue - 100, true);
                            } else if (iIntValue == 38 || iIntValue == 48) {
                                if (it.hasNext()) {
                                    int nextOptionInt = getNextOptionInt(it);
                                    if (nextOptionInt == 2) {
                                        int nextOptionInt2 = getNextOptionInt(it);
                                        int nextOptionInt3 = getNextOptionInt(it);
                                        int nextOptionInt4 = getNextOptionInt(it);
                                        if (nextOptionInt2 < 0 || nextOptionInt2 > 255 || nextOptionInt3 < 0 || nextOptionInt3 > 255 || nextOptionInt4 < 0 || nextOptionInt4 > 255) {
                                            throw new IllegalArgumentException();
                                        }
                                        if (iIntValue == 38) {
                                            processSetForegroundColorExt(nextOptionInt2, nextOptionInt3, nextOptionInt4);
                                        } else {
                                            processSetBackgroundColorExt(nextOptionInt2, nextOptionInt3, nextOptionInt4);
                                        }
                                    } else {
                                        if (nextOptionInt != 5) {
                                            throw new IllegalArgumentException();
                                        }
                                        int nextOptionInt5 = getNextOptionInt(it);
                                        if (nextOptionInt5 < 0 || nextOptionInt5 > 255) {
                                            throw new IllegalArgumentException();
                                        }
                                        if (iIntValue == 38) {
                                            processSetForegroundColorExt(nextOptionInt5);
                                        } else {
                                            processSetBackgroundColorExt(nextOptionInt5);
                                        }
                                    }
                                }
                            } else if (iIntValue == 0) {
                                processAttributeReset();
                            } else if (iIntValue == 39) {
                                processDefaultTextColor();
                            } else if (iIntValue != 49) {
                                processSetAttribute(iIntValue);
                            } else {
                                processDefaultBackgroundColor();
                            }
                        }
                    }
                    if (i2 == 0) {
                        processAttributeReset();
                    }
                    return true;
                }
                if (i == 115) {
                    processSaveCursorPosition();
                    return true;
                }
                if (i == 117) {
                    processRestoreCursorPosition();
                    return true;
                }
                switch (i) {
                    case 65:
                        processCursorUp(optionInt(arrayList, 0, 1));
                        return true;
                    case 66:
                        processCursorDown(optionInt(arrayList, 0, 1));
                        return true;
                    case 67:
                        processCursorRight(optionInt(arrayList, 0, 1));
                        return true;
                    case 68:
                        processCursorLeft(optionInt(arrayList, 0, 1));
                        return true;
                    case 69:
                        processCursorDownLine(optionInt(arrayList, 0, 1));
                        return true;
                    case 70:
                        processCursorUpLine(optionInt(arrayList, 0, 1));
                        return true;
                    case 71:
                        processCursorToColumn(optionInt(arrayList, 0));
                        return true;
                    case 72:
                        break;
                    default:
                        switch (i) {
                            case 74:
                                processEraseScreen(optionInt(arrayList, 0, 0));
                                return true;
                            case 75:
                                processEraseLine(optionInt(arrayList, 0, 0));
                                return true;
                            case TerminalTokens.TokenNameRestrictedIdentifierrecord /* 76 */:
                                processInsertLine(optionInt(arrayList, 0, 1));
                                return true;
                            case 77:
                                processDeleteLine(optionInt(arrayList, 0, 1));
                                return true;
                            default:
                                if (97 <= i && i <= 122) {
                                    processUnknownExtension(arrayList, i);
                                    return true;
                                }
                                if (65 > i || i > 90) {
                                    return false;
                                }
                                processUnknownExtension(arrayList, i);
                                return true;
                        }
                }
            }
            processCursorTo(optionInt(arrayList, 0, 1), optionInt(arrayList, 1, 1));
            return true;
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    public void processInsertLine(int i) throws IOException {
    }

    public boolean processOperatingSystemCommand(ArrayList<Object> arrayList) {
        int iOptionInt = optionInt(arrayList, 0);
        String str = (String) arrayList.get(1);
        try {
            if (iOptionInt == 0) {
                processChangeIconNameAndWindowTitle(str);
                return true;
            }
            if (iOptionInt == 1) {
                processChangeIconName(str);
                return true;
            }
            if (iOptionInt != 2) {
                processUnknownOperatingSystemCommand(iOptionInt, str);
                return true;
            }
            processChangeWindowTitle(str);
            return true;
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    public void processRestoreCursorPosition() throws IOException {
    }

    public void processSaveCursorPosition() throws IOException {
    }

    public void processScrollDown(int i) throws IOException {
    }

    public void processScrollUp(int i) throws IOException {
    }

    public void processSetAttribute(int i) throws IOException {
    }

    public void processSetBackgroundColor(int i) throws IOException {
        processSetBackgroundColor(i, false);
    }

    public void processSetBackgroundColorExt(int i) throws IOException {
    }

    public void processSetBackgroundColorExt(int i, int i2, int i3) throws IOException {
    }

    public void processSetForegroundColor(int i) throws IOException {
        processSetForegroundColor(i, false);
    }

    public void processSetForegroundColorExt(int i) throws IOException {
    }

    public void processSetForegroundColorExt(int i, int i2, int i3) throws IOException {
    }

    public void processUnknownExtension(ArrayList<Object> arrayList, int i) {
    }

    public void processUnknownOperatingSystemCommand(int i, String str) {
    }

    public void processSetBackgroundColor(int i, boolean z) throws IOException {
    }

    public void processSetForegroundColor(int i, boolean z) throws IOException {
    }

    public void processCharsetSelect(int i, char c) {
    }

    private int optionInt(ArrayList<Object> arrayList, int i, int i2) {
        Object obj;
        return (arrayList.size() <= i || (obj = arrayList.get(i)) == null) ? i2 : ((Integer) obj).intValue();
    }
}
