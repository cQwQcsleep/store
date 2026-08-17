package org.jetbrains.kotlin.psi.stubs.impl;

import com.intellij.util.io.StringRef;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/psi/stubs/impl/Utils;", "", "<init>", "()V", "wrapStrings", "", "Lcom/intellij/util/io/StringRef;", "names", "", "", "(Ljava/util/List;)[Lcom/intellij/util/io/StringRef;", "org.jetbrains.kotlin:psi-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class Utils {
    public static final Utils INSTANCE = new Utils();

    private Utils() {
    }

    public final StringRef[] wrapStrings(List<String> names) {
        names.getClass();
        if (names.isEmpty()) {
            StringRef[] stringRefArr = StringRef.EMPTY_ARRAY;
            stringRefArr.getClass();
            return stringRefArr;
        }
        int size = names.size();
        StringRef[] stringRefArr2 = new StringRef[size];
        for (int i = 0; i < size; i++) {
            StringRef stringRefFromString = StringRef.fromString(names.get(i));
            stringRefFromString.getClass();
            stringRefArr2[i] = stringRefFromString;
        }
        return stringRefArr2;
    }
}
