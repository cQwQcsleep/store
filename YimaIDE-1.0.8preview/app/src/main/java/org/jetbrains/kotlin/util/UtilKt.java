package org.jetbrains.kotlin.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.konan.file.File;
import org.jetbrains.kotlin.library.abi.AbiQualifiedName;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0001\u001a\u001e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u000bH\u0002\u001a\u001c\u0010\f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u000b\u001a&\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\u00012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u000bH\u0002\u001a\u001d\u0010\u0010\u001a\u0004\u0018\u0001H\u0011\"\u0004\b\u0000\u0010\u0011*\b\u0012\u0004\u0012\u0002H\u00110\u0012¢\u0006\u0002\u0010\u0013\u001a\u0012\u0010\u0014\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u0001\u001a\u0012\u0010\u0016\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u0001\u001a\u0012\u0010\u0017\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u0001\u001a\u0012\u0010\u0019\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u0001¨\u0006\u001a"}, d2 = {"nTabs", "", "amount", "", "parseSpaceSeparatedArgs", "", "argsString", "printMilliseconds", "", "message", "body", "Lkotlin/Function0;", "profile", "profileIf", "condition", "", "getValueOrNull", "T", "Lkotlin/Lazy;", "(Lkotlin/Lazy;)Ljava/lang/Object;", "prefixBaseNameIfNot", "prefix", "prefixIfNot", "removeSuffixIfPresent", "suffix", "suffixIfNot", "kotlin-util-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class UtilKt {
    public static final <T> T getValueOrNull(Lazy<? extends T> lazy) {
        lazy.getClass();
        if (lazy.isInitialized()) {
            return (T) lazy.getValue();
        }
        return null;
    }

    public static final String nTabs(int i) {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        return String.format("%1$-" + ((i + 1) * 4) + 's', Arrays.copyOf(new Object[]{""}, 1));
    }

    public static final List<String> parseSpaceSeparatedArgs(String str) {
        str.getClass();
        ArrayList arrayList = new ArrayList();
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new StringBuilder();
        boolean z = false;
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '\"') {
                boolean z2 = !z;
                if (z) {
                    parseSpaceSeparatedArgs$saveArg(objectRef, arrayList, true);
                }
                z = z2;
            } else if (!CharsKt.isWhitespace(cCharAt) || z) {
                ((StringBuilder) objectRef.element).append(cCharAt);
            } else {
                parseSpaceSeparatedArgs$saveArg(objectRef, arrayList, false);
            }
        }
        if (z) {
            c2f.a("No close-quote was found in ", objectRef.element, 46);
            return null;
        }
        parseSpaceSeparatedArgs$saveArg(objectRef, arrayList, false);
        return arrayList;
    }

    private static final void parseSpaceSeparatedArgs$saveArg(Ref.ObjectRef<StringBuilder> objectRef, List<String> list, boolean z) {
        if (z || !StringsKt.isBlank((CharSequence) objectRef.element)) {
            list.add(((StringBuilder) objectRef.element).toString());
            objectRef.element = new StringBuilder();
        }
    }

    public static final String prefixBaseNameIfNot(String str, String str2) {
        str.getClass();
        str2.getClass();
        File absoluteFile = new File(str).getAbsoluteFile();
        String name = absoluteFile.getName();
        return absoluteFile.getParent() + AbiQualifiedName.SEPARATOR + prefixIfNot(name, str2);
    }

    public static final String prefixIfNot(String str, String str2) {
        str.getClass();
        str2.getClass();
        if (StringsKt.startsWith$default(str, str2, false, 2, (Object) null)) {
            return str;
        }
        return str2 + str;
    }

    private static final void printMilliseconds(String str, Function0<Unit> function0) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        function0.invoke();
        System.out.println((Object) (str + ": " + (System.currentTimeMillis() - jCurrentTimeMillis) + " ms"));
    }

    public static final void profile(String str, Function0<Unit> function0) {
        str.getClass();
        function0.getClass();
        String property = System.getProperty("konan.profile");
        profileIf(property != null ? property.equals("true") : false, str, function0);
    }

    private static final void profileIf(boolean z, String str, Function0<Unit> function0) {
        if (z) {
            printMilliseconds(str, function0);
        } else {
            function0.invoke();
        }
    }

    public static final String removeSuffixIfPresent(String str, String str2) {
        str.getClass();
        str2.getClass();
        return StringsKt.endsWith$default(str, str2, false, 2, (Object) null) ? StringsKt.dropLast(str, str2.length()) : str;
    }

    public static final String suffixIfNot(String str, String str2) {
        str.getClass();
        str2.getClass();
        if (StringsKt.endsWith$default(str, str2, false, 2, (Object) null)) {
            return str;
        }
        return str + str2;
    }
}
