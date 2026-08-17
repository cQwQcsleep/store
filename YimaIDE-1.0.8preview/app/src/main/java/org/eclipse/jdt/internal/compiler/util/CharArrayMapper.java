package org.eclipse.jdt.internal.compiler.util;

import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.eclipse.jdt.internal.compiler.util.CharArrayMapper;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public interface CharArrayMapper<V> extends Cloneable {
    static /* synthetic */ String q9(CharArrayMapper charArrayMapper, char[] cArr) {
        return new String(cArr) + "->" + String.valueOf(charArrayMapper.get(cArr));
    }

    static <V> String toString(final CharArrayMapper<V> charArrayMapper) {
        return (String) charArrayMapper.keys().stream().map(new Function() { // from class: pf1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return CharArrayMapper.q9(this.b, (char[]) obj);
            }
        }).collect(Collectors.joining("\n"));
    }

    boolean containsKey(char[] cArr);

    V get(char[] cArr);

    Collection<char[]> keys();

    V put(char[] cArr, V v);

    int size();

    Collection<V> values();
}
