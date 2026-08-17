package org.jetbrains.kotlin.incremental.storage;

import com.intellij.util.io.DataExternalizer;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.incremental.storage.LinkedHashMapExternalizer;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u000220\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0002\u0012 \u0012\u001e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0004j\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0002`\u00050\u0003B#\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00010\u0007¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/incremental/storage/LinkedHashMapExternalizer;", "K", "V", "Lorg/jetbrains/kotlin/incremental/storage/MapExternalizer;", "Ljava/util/LinkedHashMap;", "Lkotlin/collections/LinkedHashMap;", "keyExternalizer", "Lcom/intellij/util/io/DataExternalizer;", "valueExternalizer", "<init>", "(Lcom/intellij/util/io/DataExternalizer;Lcom/intellij/util/io/DataExternalizer;)V", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class LinkedHashMapExternalizer<K, V> extends MapExternalizer<K, V, LinkedHashMap<K, V>> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LinkedHashMapExternalizer(DataExternalizer<K> dataExternalizer, DataExternalizer<V> dataExternalizer2) {
        super(dataExternalizer, dataExternalizer2, new Function1() { // from class: ga9
            public final Object invoke(Object obj) {
                return LinkedHashMapExternalizer.b(((Integer) obj).intValue());
            }
        });
        dataExternalizer.getClass();
        dataExternalizer2.getClass();
    }

    public static Map b(int i) {
        return new LinkedHashMap(i);
    }
}
