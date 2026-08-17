package org.eclipse.jdt.internal.compiler.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public final class CharArrayHashMap<V> implements CharArrayMapper<V>, Serializable {
    private static final long serialVersionUID = -4247853285180184851L;
    private final HashMap<CharArray, V> map;

    public CharArrayHashMap(int i) {
        this.map = new HashMap<>(i);
    }

    @Override // org.eclipse.jdt.internal.compiler.util.CharArrayMapper
    public boolean containsKey(char[] cArr) {
        return this.map.containsKey(new CharArray(cArr));
    }

    @Override // org.eclipse.jdt.internal.compiler.util.CharArrayMapper
    public V get(char[] cArr) {
        return this.map.get(new CharArray(cArr));
    }

    @Override // org.eclipse.jdt.internal.compiler.util.CharArrayMapper
    public Collection<char[]> keys() {
        return (Collection) this.map.keySet().stream().map(new Function() { // from class: org.eclipse.jdt.internal.compiler.util.a
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((CharArray) obj).getKey();
            }
        }).collect(Collectors.toList());
    }

    @Override // org.eclipse.jdt.internal.compiler.util.CharArrayMapper
    public V put(char[] cArr, V v) {
        return this.map.put(new CharArray(cArr), v);
    }

    @Override // org.eclipse.jdt.internal.compiler.util.CharArrayMapper
    public int size() {
        return this.map.size();
    }

    public String toString() {
        return CharArrayMapper.toString(this);
    }

    @Override // org.eclipse.jdt.internal.compiler.util.CharArrayMapper
    public Collection<V> values() {
        return new ArrayList(this.map.values());
    }
}
