package org.bouncycastle.util;

import java.util.Collection;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface Store<T> {
    Collection<T> getMatches(Selector<T> selector) throws StoreException;
}
