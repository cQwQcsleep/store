package org.jetbrains.kotlin.load.java.structure.impl;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.load.java.structure.impl.ClassNamesUtilKt;
import org.jetbrains.kotlin.name.SpecialNames;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0001\u001a\u0012\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003*\u00020\u0001H\u0002¨\u0006\u0004"}, d2 = {"convertCanonicalNameToQName", "", "splitCanonicalFqName", "", "org.jetbrains.kotlin:frontend.common.jvm"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class ClassNamesUtilKt {
    public static CharSequence a(String str) {
        str.getClass();
        return StringsKt.substringBefore$default(str, '<', (String) null, 2, (Object) null);
    }

    public static final String convertCanonicalNameToQName(String str) {
        str.getClass();
        return CollectionsKt.joinToString$default(splitCanonicalFqName(str), ".", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: hw1
            public final Object invoke(Object obj) {
                return ClassNamesUtilKt.a((String) obj);
            }
        }, 30, (Object) null);
    }

    private static final List<String> splitCanonicalFqName(String str) {
        ArrayList arrayList = new ArrayList();
        int length = str.length();
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            char cCharAt = str.charAt(i3);
            if (cCharAt != '.') {
                if (cCharAt == '<') {
                    i2++;
                } else if (cCharAt == '>') {
                    i2--;
                }
            } else if (i2 == 0) {
                arrayList.add(splitCanonicalFqName$toNonEmpty(str.substring(i, i3)));
                i = i3 + 1;
            }
        }
        arrayList.add(splitCanonicalFqName$toNonEmpty(str.substring(i)));
        return arrayList;
    }

    private static final String splitCanonicalFqName$toNonEmpty(String str) {
        if (str.length() > 0) {
            return str;
        }
        String strAsString = SpecialNames.SAFE_IDENTIFIER_FOR_NO_NAME.asString();
        strAsString.getClass();
        return strAsString;
    }
}
