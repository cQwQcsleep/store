package org.jetbrains.kotlin.backend.konan.cgen;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.ranges.CharRange;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlin.text.UStringsKt;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\u0005\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001\"\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"quoteAsCStringLiteral", "", "str", "asciiCharsAllowedInCStringLiterals", "", "", "org.jetbrains.kotlin:ir.backend.native"}, k = 2, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public final class CSyntaxSupportKt {
    private static final Set<Byte> asciiCharsAllowedInCStringLiterals;

    static {
        Set setCreateSetBuilder = SetsKt.createSetBuilder();
        Set set = setCreateSetBuilder;
        CollectionsKt.addAll(set, new CharRange('A', 'Z'));
        CollectionsKt.addAll(set, new CharRange('a', 'z'));
        CollectionsKt.addAll(set, new CharRange('0', '9'));
        setCreateSetBuilder.addAll(StringsKt.toList("!#%&'()*+,-./:;<=>?[]^_{|}~ "));
        Set setBuild = SetsKt.build(setCreateSetBuilder);
        HashSet hashSet = new HashSet();
        Iterator it = setBuild.iterator();
        while (it.hasNext()) {
            char cCharValue = ((Character) it.next()).charValue();
            if (cCharValue < 0 || cCharValue >= 128) {
                throw new IllegalStateException(("Allowed char is not ASCII: " + cCharValue).toString());
            }
            hashSet.add(Byte.valueOf((byte) cCharValue));
        }
        asciiCharsAllowedInCStringLiterals = hashSet;
    }

    public static final String quoteAsCStringLiteral(String str) {
        str.getClass();
        StringBuilder sb = new StringBuilder("\"");
        byte[] bytes = str.getBytes(Charsets.UTF_8);
        bytes.getClass();
        for (byte b : bytes) {
            if (asciiCharsAllowedInCStringLiterals.contains(Byte.valueOf(b))) {
                sb.append((char) b);
            } else {
                sb.append("\\" + StringsKt.padStart(UStringsKt.toString-LxnNnR4(UByte.constructor-impl(b), 8), 3, '0'));
            }
        }
        sb.append('\"');
        return sb.toString();
    }
}
