package com.intellij.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J8\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u00070\u0005\"\b\b\u0000\u0010\u0006*\u00020\b\"\u0004\b\u0001\u0010\u00072\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u00070\u0005H\u0016J9\u0010\n\u001a\u000e\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u00070\u0005\"\b\b\u0000\u0010\u0006*\u00020\b\"\u0004\b\u0001\u0010\u00072\u0006\u0010\u000b\u001a\u0002H\u00062\u0006\u0010\f\u001a\u0002H\u0007H\u0016¢\u0006\u0002\u0010\rJI\u0010\n\u001a\u000e\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u00070\u0005\"\b\b\u0000\u0010\u0006*\u00020\b\"\u0004\b\u0001\u0010\u00072\u0006\u0010\u000b\u001a\u0002H\u00062\u0006\u0010\f\u001a\u0002H\u00072\u0006\u0010\u000e\u001a\u0002H\u00062\u0006\u0010\u000f\u001a\u0002H\u0007H\u0016¢\u0006\u0002\u0010\u0010J\"\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00120\u0011\"\u0004\b\u0000\u0010\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00120\u0014H\u0016J\u0018\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0016\"\b\b\u0000\u0010\u0007*\u00020\bH\u0016J$\u0010\n\u001a\u000e\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u00070\u0005\"\b\b\u0000\u0010\u0006*\u00020\b\"\u0004\b\u0001\u0010\u0007H\u0016J\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u0002H\u00120\u0018\"\u0004\b\u0000\u0010\u0012H\u0016J!\u0010\u0017\u001a\b\u0012\u0004\u0012\u0002H\u00120\u0018\"\u0004\b\u0000\u0010\u00122\u0006\u0010\u0019\u001a\u0002H\u0012H\u0016¢\u0006\u0002\u0010\u001aJ\"\u0010\u001b\u001a\b\u0012\u0004\u0012\u0002H\u00120\u0018\"\u0004\b\u0000\u0010\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00120\u0014H\u0016J)\u0010\u0017\u001a\b\u0012\u0004\u0012\u0002H\u00120\u0018\"\u0004\b\u0000\u0010\u00122\u0006\u0010\u001c\u001a\u0002H\u00122\u0006\u0010\u001d\u001a\u0002H\u0012H\u0016¢\u0006\u0002\u0010\u001eJ/\u0010\u0017\u001a\b\u0012\u0004\u0012\u0002H\u00120\u0018\"\u0004\b\u0000\u0010\u00122\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u00120 2\u0006\u0010!\u001a\u00020\"H\u0016¢\u0006\u0002\u0010#J\u0016\u0010$\u001a\b\u0012\u0002\b\u0003\u0018\u00010%2\u0006\u0010&\u001a\u00020\"H\u0016¨\u0006'"}, d2 = {"Lcom/intellij/util/DefaultJava11Shim;", "Lcom/intellij/util/Java11Shim;", "<init>", "()V", "copyOf", "", "K", "V", "", "map", "mapOf", "k", "v", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/util/Map;", "k2", "v2", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/util/Map;", "", "E", "collection", "", "createConcurrentLongObjectMap", "Lcom/intellij/util/containers/ConcurrentLongObjectMap;", "listOf", "", "element", "(Ljava/lang/Object;)Ljava/util/List;", "copyOfList", "e1", "e2", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/util/List;", "array", "", "size", "", "([Ljava/lang/Object;I)Ljava/util/List;", "getCallerClass", "Ljava/lang/Class;", "stackFrameIndex", "intellij.platform.util"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class DefaultJava11Shim implements Java11Shim {
    @Override // com.intellij.util.Java11Shim
    public <E> Set<E> copyOf(Collection<? extends E> collection) {
        collection.getClass();
        Set<E> setUnmodifiableSet = Collections.unmodifiableSet(new HashSet(collection));
        setUnmodifiableSet.getClass();
        return setUnmodifiableSet;
    }

    @Override // com.intellij.util.Java11Shim
    public <E> List<E> copyOfList(Collection<? extends E> collection) {
        collection.getClass();
        List<E> listUnmodifiableList = Collections.unmodifiableList(CollectionsKt.toList(collection));
        listUnmodifiableList.getClass();
        return listUnmodifiableList;
    }

    @Override // com.intellij.util.Java11Shim
    public Class<?> getCallerClass(int stackFrameIndex) {
        return ReflectionUtil.getCallerClass(stackFrameIndex + 1);
    }

    @Override // com.intellij.util.Java11Shim
    public <E> List<E> listOf(E[] array, int size) {
        array.getClass();
        return array.length == size ? ArraysKt.asList(array) : ArraysKt.asList(array).subList(0, size);
    }

    @Override // com.intellij.util.Java11Shim
    public <K, V> Map<K, V> mapOf(K k, V v) {
        k.getClass();
        Map<K, V> mapSingletonMap = Collections.singletonMap(k, v);
        mapSingletonMap.getClass();
        return mapSingletonMap;
    }

    @Override // com.intellij.util.Java11Shim
    public <K, V> Map<K, V> mapOf() {
        Map<K, V> map = Collections.EMPTY_MAP;
        map.getClass();
        return map;
    }

    @Override // com.intellij.util.Java11Shim
    public <K, V> Map<K, V> copyOf(Map<K, ? extends V> map) {
        map.getClass();
        Map<K, V> mapUnmodifiableMap = Collections.unmodifiableMap(map);
        mapUnmodifiableMap.getClass();
        return mapUnmodifiableMap;
    }

    @Override // com.intellij.util.Java11Shim
    public <E> List<E> listOf(E element) {
        List<E> listSingletonList = Collections.singletonList(element);
        listSingletonList.getClass();
        return listSingletonList;
    }

    @Override // com.intellij.util.Java11Shim
    public <E> List<E> listOf(E e1, E e2) {
        List<E> listAsList = Arrays.asList(e1, e2);
        listAsList.getClass();
        return listAsList;
    }

    @Override // com.intellij.util.Java11Shim
    public <E> List<E> listOf() {
        List<E> list = Collections.EMPTY_LIST;
        list.getClass();
        return list;
    }
}
