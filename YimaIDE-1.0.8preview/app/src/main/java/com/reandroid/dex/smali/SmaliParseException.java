package com.reandroid.dex.smali;

import com.reandroid.common.Origin;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SmaliParseException extends IOException {
    private final SmaliReader reader;

    public SmaliParseException(String str, SmaliReader smaliReader) {
        super(str);
        this.reader = smaliReader;
    }

    public static void expect(SmaliReader smaliReader, SmaliDirective smaliDirective, boolean z) throws IOException {
        int iPosition = smaliReader.position();
        boolean z2 = z && !smaliDirective.isEnd(smaliReader);
        if ((z2 || SmaliDirective.parse(smaliReader) == smaliDirective) ? z2 : true) {
            smaliReader.position(iPosition);
            SmaliParseException smaliParseException = new SmaliParseException("expecting '" + smaliDirective.toString(z) + "'", smaliReader);
            sanitizeStackTrace(smaliParseException);
            throw smaliParseException;
        }
    }

    private static boolean remove(StackTraceElement stackTraceElement) {
        if (stackTraceElement == null) {
            return true;
        }
        return stackTraceElement.getClassName().equals(SmaliParseException.class.getName());
    }

    private static void sanitizeStackTrace(Exception exc) {
        StackTraceElement[] stackTrace = exc.getStackTrace();
        int i = 0;
        for (int i2 = 0; i2 < stackTrace.length; i2++) {
            if (remove(stackTrace[i2])) {
                stackTrace[i2] = null;
            } else {
                i++;
            }
        }
        StackTraceElement[] stackTraceElementArr = new StackTraceElement[i];
        int i3 = 0;
        for (StackTraceElement stackTraceElement : stackTrace) {
            if (stackTraceElement != null) {
                stackTraceElementArr[i3] = stackTraceElement;
                i3++;
            }
        }
        exc.setStackTrace(stackTraceElementArr);
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        String message = super.getMessage();
        SmaliReader smaliReader = this.reader;
        if (smaliReader == null) {
            return message;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(message);
        Origin currentOrigin = smaliReader.getCurrentOrigin();
        if (currentOrigin != null) {
            sb.append("\nat ");
            sb.append(currentOrigin);
        }
        return sb.toString();
    }

    public static void expect(SmaliReader smaliReader, SmaliDirective smaliDirective) throws IOException {
        expect(smaliReader, smaliDirective, false);
    }

    public static void expect(SmaliReader smaliReader, char c) throws IOException {
        if (smaliReader.skipIfChar(c)) {
            return;
        }
        SmaliParseException smaliParseException = new SmaliParseException("expecting '" + c + "'", smaliReader);
        sanitizeStackTrace(smaliParseException);
        throw smaliParseException;
    }

    public static char expect(SmaliReader smaliReader, char c, char c2) throws IOException {
        if (!smaliReader.finished()) {
            char ascii = smaliReader.readASCII();
            if (ascii == c || ascii == c2) {
                return ascii;
            }
            smaliReader.skip(-1);
        }
        SmaliParseException smaliParseException = new SmaliParseException("expecting '" + c + "', or '" + c2 + "'", smaliReader);
        sanitizeStackTrace(smaliParseException);
        throw smaliParseException;
    }
}
