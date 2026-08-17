package org.jetbrains.kotlin.utils.exceptions;

import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0002\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001¨\u0006\u0007"}, d2 = {"getExceptionMessage", "", "subsystemName", "message", "cause", "", "location", "org.jetbrains.kotlin:util"}, k = 2, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public final class ExceptionUtilKt {
    public static final String getExceptionMessage(String str, String str2, Throwable th, String str3) {
        String string;
        StackTraceElement stackTraceElement;
        str.getClass();
        str2.getClass();
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(" Internal error: ");
        sb.append(str2);
        sb.append('\n');
        if (str3 != null) {
            sb.append("File being compiled: ");
            sb.append(str3);
            sb.append('\n');
        } else {
            sb.append("File is unknown\n");
        }
        if (th != null) {
            sb.append("The root cause " + th.getClass().getName() + " was thrown at: ");
            StackTraceElement[] stackTrace = th.getStackTrace();
            if (stackTrace == null || (stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace)) == null || (string = stackTraceElement.toString()) == null) {
                string = "unknown";
            }
            sb.append(string);
        }
        return sb.toString();
    }
}
