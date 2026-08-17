package org.jetbrains.kotlin.backend.common;

import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010!\n\u0002\b\u0006\u001a#\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0004\u001a\u0002H\u0002¢\u0006\u0002\u0010\u0005\u001a\u001b\u0010\u0006\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003¢\u0006\u0002\u0010\u0007\u001a\u001d\u0010\b\u001a\u0004\u0018\u0001H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003¢\u0006\u0002\u0010\u0007¨\u0006\t"}, d2 = {"push", "", "E", "", "element", "(Ljava/util/List;Ljava/lang/Object;)Z", "pop", "(Ljava/util/List;)Ljava/lang/Object;", "peek", "org.jetbrains.kotlin:ir.backend.common"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class UtilsKt {
    public static final <E> E peek(List<E> list) {
        list.getClass();
        if (list.size() == 0) {
            return null;
        }
        return list.get(list.size() - 1);
    }

    public static final <E> E pop(List<E> list) {
        list.getClass();
        return list.remove(list.size() - 1);
    }

    public static final <E> boolean push(List<E> list, E e) {
        list.getClass();
        return list.add(e);
    }
}
