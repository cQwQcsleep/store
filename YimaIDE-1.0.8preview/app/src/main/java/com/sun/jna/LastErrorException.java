package com.sun.jna;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class LastErrorException extends RuntimeException {
    private static final long serialVersionUID = 1;
    private int errorCode;

    public LastErrorException(String str) {
        super(parseMessage(str.trim()));
        try {
            this.errorCode = Integer.parseInt(str.startsWith("[") ? str.substring(1, str.indexOf("]")) : str);
        } catch (NumberFormatException unused) {
            this.errorCode = -1;
        }
    }

    private static String formatMessage(int i) {
        StringBuilder sb = Platform.isWindows() ? new StringBuilder("GetLastError() returned ") : new StringBuilder("errno was ");
        sb.append(i);
        return sb.toString();
    }

    private static String parseMessage(String str) {
        try {
            return formatMessage(Integer.parseInt(str));
        } catch (NumberFormatException unused) {
            return str;
        }
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public LastErrorException(int i) {
        this(i, formatMessage(i));
    }

    public LastErrorException(int i, String str) {
        super(str);
        this.errorCode = i;
    }
}
