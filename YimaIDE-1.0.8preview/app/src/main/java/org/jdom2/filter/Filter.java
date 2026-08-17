package org.jdom2.filter;

import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public interface Filter<T> extends Serializable {
    Filter<T> and(Filter<?> filter);

    T filter(Object obj);

    List<T> filter(List<?> list);

    boolean matches(Object obj);

    Filter<? extends Object> negate();

    Filter<? extends Object> or(Filter<?> filter);

    <R> Filter<R> refine(Filter<R> filter);
}
