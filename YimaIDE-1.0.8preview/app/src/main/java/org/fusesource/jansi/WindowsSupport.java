package org.fusesource.jansi;

import java.io.UnsupportedEncodingException;
import org.fusesource.jansi.internal.Kernel32;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class WindowsSupport {
    public static String getErrorMessage(int i) {
        byte[] bArr = new byte[160];
        Kernel32.FormatMessageW(Kernel32.FORMAT_MESSAGE_FROM_SYSTEM, 0L, i, 0, bArr, 160, null);
        try {
            return new String(bArr, "UTF-16LE").trim();
        } catch (UnsupportedEncodingException e) {
            e7f.a(e);
            return null;
        }
    }

    public static String getLastErrorMessage() {
        return getErrorMessage(Kernel32.GetLastError());
    }
}
