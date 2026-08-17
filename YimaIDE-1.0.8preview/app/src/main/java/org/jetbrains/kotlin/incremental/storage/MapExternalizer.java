package org.jetbrains.kotlin.incremental.storage;

import com.intellij.util.io.DataExternalizer;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.incremental.storage.MapExternalizer;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u0002*\u0014\b\u0002\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00042\b\u0012\u0004\u0012\u0002H\u00030\u0005BT\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005\u0012/\b\u0002\u0010\b\u001a)\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000e0\t¢\u0006\u0004\b\u000f\u0010\u0010J\u001d\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00028\u0002H\u0016¢\u0006\u0002\u0010\u0016J\u0015\u0010\u0017\u001a\u00028\u00022\u0006\u0010\u0018\u001a\u00020\u0019H\u0016¢\u0006\u0002\u0010\u001aR\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R5\u0010\b\u001a)\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000e0\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/incremental/storage/MapExternalizer;", "K", "V", "M", "", "Lcom/intellij/util/io/DataExternalizer;", "keyExternalizer", "valueExternalizer", "newMap", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "size", "", "<init>", "(Lcom/intellij/util/io/DataExternalizer;Lcom/intellij/util/io/DataExternalizer;Lkotlin/jvm/functions/Function1;)V", "save", "", "output", "Ljava/io/DataOutput;", "map", "(Ljava/io/DataOutput;Ljava/util/Map;)V", "read", "input", "Ljava/io/DataInput;", "(Ljava/io/DataInput;)Ljava/util/Map;", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public class MapExternalizer<K, V, M extends Map<K, ? extends V>> implements DataExternalizer<M> {
    private final DataExternalizer<K> keyExternalizer;
    private final Function1<Integer, Map<K, V>> newMap;
    private final DataExternalizer<V> valueExternalizer;

    /* JADX WARN: Multi-variable type inference failed */
    public MapExternalizer(DataExternalizer<K> dataExternalizer, DataExternalizer<V> dataExternalizer2, Function1<? super Integer, ? extends Map<K, V>> function1) {
        dataExternalizer.getClass();
        dataExternalizer2.getClass();
        function1.getClass();
        this.keyExternalizer = dataExternalizer;
        this.valueExternalizer = dataExternalizer2;
        this.newMap = function1;
    }

    public static LinkedHashMap a(int i) {
        return new LinkedHashMap(i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public M read(DataInput input) throws IOException {
        input.getClass();
        int i = input.readInt();
        M m = (M) this.newMap.invoke(Integer.valueOf(i));
        for (int i2 = 0; i2 < i; i2++) {
            m.put(this.keyExternalizer.read(input), this.valueExternalizer.read(input));
        }
        m.getClass();
        return m;
    }

    public void save(DataOutput output, M map) throws IOException {
        output.getClass();
        map.getClass();
        output.writeInt(map.size());
        for (Map.Entry<K, V> entry : map.entrySet()) {
            K key = entry.getKey();
            V value = entry.getValue();
            this.keyExternalizer.save(output, key);
            this.valueExternalizer.save(output, value);
        }
    }

    public /* synthetic */ MapExternalizer(DataExternalizer dataExternalizer, DataExternalizer dataExternalizer2, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(dataExternalizer, dataExternalizer2, (i & 4) != 0 ? new Function1() { // from class: yt9
            public final Object invoke(Object obj) {
                return MapExternalizer.a(((Integer) obj).intValue());
            }
        } : function1);
    }
}
