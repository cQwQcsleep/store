package org.jetbrains.kotlin.incremental.storage;

import com.intellij.util.io.DataExternalizer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.incremental.storage.ListExternalizer;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000*\u0004\b\u0000\u0010\u00012\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/incremental/storage/ListExternalizer;", "T", "Lorg/jetbrains/kotlin/incremental/storage/CollectionExternalizerV2;", "", "elementExternalizer", "Lcom/intellij/util/io/DataExternalizer;", "<init>", "(Lcom/intellij/util/io/DataExternalizer;)V", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ListExternalizer<T> extends CollectionExternalizerV2<T, List<? extends T>> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ListExternalizer(DataExternalizer<T> dataExternalizer) {
        super(dataExternalizer, new Function1() { // from class: fc9
            public final Object invoke(Object obj) {
                return ListExternalizer.b(((Integer) obj).intValue());
            }
        });
        dataExternalizer.getClass();
    }

    public static Collection b(int i) {
        return new ArrayList(i);
    }
}
