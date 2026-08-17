package com.intellij.util.containers;

import com.intellij.openapi.util.io.FileUtilRt;
import it.unimi.dsi.fastutil.Hash;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class FastUtilHashingStrategies {
    private static final Hash.Strategy<CharSequence> CASE_SENSITIVE = new FastUtilCharSequenceHashingStrategy(true);
    private static final Hash.Strategy<CharSequence> CASE_INSENSITIVE = new FastUtilCharSequenceHashingStrategy(false);
    public static final Hash.Strategy<String> FILE_PATH_HASH_STRATEGY = new Hash.Strategy<String>() { // from class: com.intellij.util.containers.FastUtilHashingStrategies.1
        public int hashCode(String str) {
            return FileUtilRt.pathHashCode(str);
        }

        public boolean equals(String str, String str2) {
            return FileUtilRt.pathsEqual(str, str2);
        }
    };

    interface SerializableHashStrategy extends Hash.Strategy, Serializable {
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = i != 4 ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[i != 4 ? 2 : 3];
        if (i != 4) {
            objArr[0] = "com/intellij/util/containers/FastUtilHashingStrategies";
        } else {
            objArr[0] = "hashingStrategy";
        }
        if (i == 1) {
            objArr[1] = "getStringStrategy";
        } else if (i == 2) {
            objArr[1] = "getCaseInsensitiveStringStrategy";
        } else if (i == 3) {
            objArr[1] = "getCanonicalStrategy";
        } else if (i != 4) {
            objArr[1] = "getCharSequenceStrategy";
        } else {
            objArr[1] = "com/intellij/util/containers/FastUtilHashingStrategies";
        }
        if (i == 4) {
            objArr[2] = "adaptAsNotNull";
        }
        String str2 = String.format(str, objArr);
        if (i == 4) {
            throw new IllegalArgumentException(str2);
        }
    }

    public static Hash.Strategy<String> getCaseInsensitiveStringStrategy() {
        Hash.Strategy<String> strategy = FastUtilCaseInsensitiveStringHashingStrategy.INSTANCE;
        if (strategy == null) {
            $$$reportNull$$$0(2);
        }
        return strategy;
    }

    public static Hash.Strategy<CharSequence> getCharSequenceStrategy(boolean z) {
        Hash.Strategy<CharSequence> strategy = z ? CASE_SENSITIVE : CASE_INSENSITIVE;
        if (strategy == null) {
            $$$reportNull$$$0(0);
        }
        return strategy;
    }
}
