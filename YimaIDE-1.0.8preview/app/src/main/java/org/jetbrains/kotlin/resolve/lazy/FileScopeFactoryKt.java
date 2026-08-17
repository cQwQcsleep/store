package org.jetbrains.kotlin.resolve.lazy;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0003\u001a/\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u000e\u0010\u0003\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001H\u0082\u0004¨\u0006\u0004"}, d2 = {"concat", "", "T", "other", "org.jetbrains.kotlin:frontend"}, k = 2, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public final class FileScopeFactoryKt {
    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> Collection<T> concat(Collection<? extends T> collection, Collection<? extends T> collection2) {
        return (collection2 == null || collection2.isEmpty()) ? collection : CollectionsKt.plus(collection, collection2);
    }
}
