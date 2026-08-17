package com.intellij.util.containers;

import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@FunctionalInterface
public interface Convertor<Src, Dst> extends Function<Src, Dst> {
    public static final IntoSelf SELF = new IntoSelf();

    public static final class IntoSelf<Src> implements Convertor<Src, Src> {
        @Override // com.intellij.util.containers.Convertor
        public Src convert(Src src) {
            return src;
        }
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[2];
        objArr[0] = "com/intellij/util/containers/Convertor";
        if (i != 1) {
            objArr[1] = "self";
        } else {
            objArr[1] = "asFunction";
        }
        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", objArr));
    }

    static <T> Convertor<T, T> self() {
        IntoSelf intoSelf = SELF;
        if (intoSelf == null) {
            $$$reportNull$$$0(0);
        }
        return intoSelf;
    }

    @Override // java.util.function.Function
    default Dst apply(Src src) {
        return convert(src);
    }

    Dst convert(Src src);
}
