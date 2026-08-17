package com.sun.jna.platform.win32.COM;

import com.sun.jna.platform.win32.WinNT;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class COMInvokeException extends COMException {
    private static final long serialVersionUID = 1;
    private final String description;
    private final Integer errorArg;
    private final Integer helpContext;
    private final String helpFile;
    private final Integer scode;
    private final String source;
    private final Integer wCode;

    public COMInvokeException(String str, WinNT.HRESULT hresult, Integer num, String str2, Integer num2, String str3, Integer num3, String str4, Integer num4) {
        super(formatMessage(hresult, str, num), hresult);
        this.description = str2;
        this.errorArg = num;
        this.helpContext = num2;
        this.helpFile = str3;
        this.scode = num3;
        this.source = str4;
        this.wCode = num4;
    }

    private static String formatMessage(WinNT.HRESULT hresult, String str, Integer num) {
        if (hresult.intValue() != -2147352571 && hresult.intValue() != -2147352572) {
            return str;
        }
        return str + " (puArgErr=" + num + ")";
    }

    public String getDescription() {
        return this.description;
    }

    public Integer getErrorArg() {
        return this.errorArg;
    }

    public Integer getHelpContext() {
        return this.helpContext;
    }

    public String getHelpFile() {
        return this.helpFile;
    }

    public Integer getScode() {
        return this.scode;
    }

    public String getSource() {
        return this.source;
    }

    public Integer getWCode() {
        return this.wCode;
    }

    public COMInvokeException(String str) {
        this(str, null);
    }

    public COMInvokeException(Throwable th) {
        this(null, th);
    }

    public COMInvokeException(String str, Throwable th) {
        super(str, th);
        this.description = null;
        this.errorArg = null;
        this.helpContext = null;
        this.helpFile = null;
        this.scode = null;
        this.source = null;
        this.wCode = null;
    }

    public COMInvokeException() {
        this("", null);
    }
}
