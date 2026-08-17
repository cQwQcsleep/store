package org.jetbrains.kotlin.resolve.jvm.checkers;

import kotlin.Metadata;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\f\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u000e\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"isValidDalvikIdentifier", "", "identifier", "", "isValidDalvikCharacter", "c", "", "org.jetbrains.kotlin:backend.common.jvm"}, k = 2, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public final class DalvikIdentifierUtils {
    public static final boolean isValidDalvikCharacter(char c) {
        if ('A' <= c && c < '[') {
            return true;
        }
        if ('a' <= c && c < '{') {
            return true;
        }
        if (('0' <= c && c < ':') || c == '$' || c == '-' || c == '_') {
            return true;
        }
        if (161 <= c && c < 8192) {
            return true;
        }
        if (8208 <= c && c < 8232) {
            return true;
        }
        if (8240 > c || c >= 55296) {
            return 57344 <= c && c < 65520;
        }
        return true;
    }

    public static final boolean isValidDalvikIdentifier(String str) {
        str.getClass();
        for (int i = 0; i < str.length(); i++) {
            if (!isValidDalvikCharacter(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
