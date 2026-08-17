package androidx.compose.compiler.plugins.kotlin;

import com.intellij.util.keyFMap.KeyFMap;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import kotlin.Metadata;
import org.jetbrains.kotlin.ir.IrElement;
import org.jetbrains.kotlin.util.slicedMap.KeyWithSlice;
import org.jetbrains.kotlin.util.slicedMap.ReadOnlySlice;
import org.jetbrains.kotlin.util.slicedMap.WritableSlice;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010%\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J?\u0010\t\u001a\u00020\n\"\b\b\u0000\u0010\u000b*\u00020\f\"\u0004\b\u0001\u0010\r2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u0002H\r0\u000f2\u0006\u0010\u0010\u001a\u0002H\u000b2\u0006\u0010\u0011\u001a\u0002H\r¢\u0006\u0002\u0010\u0012J<\u0010\u0013\u001a\u0004\u0018\u0001H\r\"\b\b\u0000\u0010\u000b*\u00020\f\"\u0004\b\u0001\u0010\r2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u0002H\r0\u00142\u0006\u0010\u0010\u001a\u0002H\u000bH\u0086\u0002¢\u0006\u0002\u0010\u0015RN\u0010\u0004\u001aB\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00010\u0001\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00070\u0007 \u0006* \u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00010\u0001\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00070\u0007\u0018\u00010\b0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/WeakBindingTrace;", "", "<init>", "()V", "map", "", "kotlin.jvm.PlatformType", "Lcom/intellij/util/keyFMap/KeyFMap;", "", "record", "", "K", "Lorg/jetbrains/kotlin/ir/IrElement;", "V", "slice", "Lorg/jetbrains/kotlin/util/slicedMap/WritableSlice;", "key", "value", "(Lorg/jetbrains/kotlin/util/slicedMap/WritableSlice;Lorg/jetbrains/kotlin/ir/IrElement;Ljava/lang/Object;)V", "get", "Lorg/jetbrains/kotlin/util/slicedMap/ReadOnlySlice;", "(Lorg/jetbrains/kotlin/util/slicedMap/ReadOnlySlice;Lorg/jetbrains/kotlin/ir/IrElement;)Ljava/lang/Object;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class WeakBindingTrace {
    private final Map<Object, KeyFMap> map = Collections.synchronizedMap(new WeakHashMap());

    public final <K extends IrElement, V> V get(ReadOnlySlice<K, V> slice, K key) {
        slice.getClass();
        key.getClass();
        KeyFMap keyFMap = this.map.get(key.getAttributeOwnerId());
        if (keyFMap != null) {
            return (V) keyFMap.get(slice.getKey());
        }
        return null;
    }

    public final <K extends IrElement, V> void record(WritableSlice<K, V> slice, K key, V value) {
        slice.getClass();
        key.getClass();
        KeyFMap keyFMapMinus = this.map.get(key.getAttributeOwnerId());
        if (keyFMapMinus == null) {
            keyFMapMinus = KeyFMap.EMPTY_MAP;
        }
        if (keyFMapMinus.get(slice.getKey()) != null) {
            keyFMapMinus = keyFMapMinus.minus(slice.getKey());
        }
        KeyWithSlice key2 = slice.getKey();
        value.getClass();
        KeyFMap keyFMapPlus = keyFMapMinus.plus(key2, value);
        Map<Object, KeyFMap> map = this.map;
        map.getClass();
        map.put(key.getAttributeOwnerId(), keyFMapPlus);
    }
}
