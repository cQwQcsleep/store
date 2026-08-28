package com.shadow.okio;

import com.shadow.kotlin.collections.CollectionsKt;
import com.shadow.kotlin.io.CloseableKt;
import com.shadow.kotlin.jvm.internal.DefaultConstructorMarker;
import com.shadow.okio.Options;
import java.util.List;
import java.util.RandomAccess;
import kotlin.collections.AbstractList;
import kotlin.jvm.functions.Function1;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class TypedOptions<T> extends AbstractList<T> implements RandomAccess {
    public static final Companion Companion = new Companion(null);
    private final List<T> list;
    private final Options options;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final <T> TypedOptions<T> of(Iterable<? extends T> iterable, Function1<? super T, ? extends ByteString> function1) {
            CloseableKt.checkNotNullParameter(iterable, "values");
            CloseableKt.checkNotNullParameter(function1, "encode");
            List listG = CollectionsKt.g(iterable);
            Options.Companion companion = Options.Companion;
            int size = listG.size();
            ByteString[] byteStringArr = new ByteString[size];
            for (int i = 0; i < size; i++) {
                byteStringArr[i] = function1.invoke(listG.get(i));
            }
            return new TypedOptions<>(listG, companion.of(byteStringArr));
        }

        private Companion() {
        }
    }

    public TypedOptions(List<? extends T> list, Options options) {
        CloseableKt.checkNotNullParameter(list, "list");
        CloseableKt.checkNotNullParameter(options, "options");
        this.options = options;
        List<T> listG = CollectionsKt.g(list);
        this.list = listG;
        if (listG.size() != options.size()) {
            throw new IllegalArgumentException("Failed requirement.");
        }
    }

    public static final <T> TypedOptions<T> of(Iterable<? extends T> iterable, Function1<? super T, ? extends ByteString> function1) {
        return Companion.of(iterable, function1);
    }

    public T get(int i) {
        return this.list.get(i);
    }

    public final List<T> getList$okio() {
        return this.list;
    }

    public final Options getOptions$okio() {
        return this.options;
    }

    public int getSize() {
        return this.list.size();
    }
}
