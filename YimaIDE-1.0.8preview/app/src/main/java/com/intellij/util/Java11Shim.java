package com.intellij.util;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\b\n\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u0000 $2\u00020\u0001:\u0001$J8\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\b\b\u0000\u0010\u0004*\u00020\u0001\"\u0004\b\u0001\u0010\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003H&J9\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\b\b\u0000\u0010\u0004*\u00020\u0001\"\u0004\b\u0001\u0010\u00052\u0006\u0010\b\u001a\u0002H\u00042\u0006\u0010\t\u001a\u0002H\u0005H&¢\u0006\u0002\u0010\nJI\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\b\b\u0000\u0010\u0004*\u00020\u0001\"\u0004\b\u0001\u0010\u00052\u0006\u0010\b\u001a\u0002H\u00042\u0006\u0010\t\u001a\u0002H\u00052\u0006\u0010\u000b\u001a\u0002H\u00042\u0006\u0010\f\u001a\u0002H\u0005H&¢\u0006\u0002\u0010\rJ$\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\b\b\u0000\u0010\u0004*\u00020\u0001\"\u0004\b\u0001\u0010\u0005H&J\"\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u000e\"\u0004\b\u0000\u0010\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0011H&J\"\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0013\"\u0004\b\u0000\u0010\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0011H&J\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0013\"\u0004\b\u0000\u0010\u000fH&J!\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0013\"\u0004\b\u0000\u0010\u000f2\u0006\u0010\u0015\u001a\u0002H\u000fH&¢\u0006\u0002\u0010\u0016J)\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0013\"\u0004\b\u0000\u0010\u000f2\u0006\u0010\u0017\u001a\u0002H\u000f2\u0006\u0010\u0018\u001a\u0002H\u000fH&¢\u0006\u0002\u0010\u0019J/\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0013\"\u0004\b\u0000\u0010\u000f2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH&¢\u0006\u0002\u0010\u001eJ\u0018\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u00050 \"\b\b\u0000\u0010\u0005*\u00020\u0001H&J\u0016\u0010!\u001a\b\u0012\u0002\b\u0003\u0018\u00010\"2\u0006\u0010#\u001a\u00020\u001dH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006%À\u0006\u0001"}, d2 = {"Lcom/intellij/util/Java11Shim;", "", "copyOf", "", "K", "V", "map", "mapOf", "k", "v", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/util/Map;", "k2", "v2", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/util/Map;", "", "E", "collection", "", "copyOfList", "", "listOf", "element", "(Ljava/lang/Object;)Ljava/util/List;", "e1", "e2", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/util/List;", "array", "", "size", "", "([Ljava/lang/Object;I)Ljava/util/List;", "createConcurrentLongObjectMap", "Lcom/intellij/util/containers/ConcurrentLongObjectMap;", "getCallerClass", "Ljava/lang/Class;", "stackFrameIndex", "Companion", "intellij.platform.util"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface Java11Shim {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/intellij/util/Java11Shim$Companion;", "", "<init>", "()V", "INSTANCE", "Lcom/intellij/util/Java11Shim;", "getINSTANCE", "()Lcom/intellij/util/Java11Shim;", "setINSTANCE", "(Lcom/intellij/util/Java11Shim;)V", "intellij.platform.util"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static Java11Shim INSTANCE = new DefaultJava11Shim();

        private Companion() {
        }

        public final Java11Shim getINSTANCE() {
            return INSTANCE;
        }
    }

    <K, V> Map<K, V> copyOf(Map<K, ? extends V> map);

    <E> Set<E> copyOf(Collection<? extends E> collection);

    <E> List<E> copyOfList(Collection<? extends E> collection);

    Class<?> getCallerClass(int stackFrameIndex);

    <E> List<E> listOf();

    <E> List<E> listOf(E element);

    <E> List<E> listOf(E e1, E e2);

    <E> List<E> listOf(E[] array, int size);

    <K, V> Map<K, V> mapOf();

    <K, V> Map<K, V> mapOf(K k, V v);
}
