package kotlin;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.reflect.KMutableProperty0;
import kotlin.reflect.KMutableProperty1;
import kotlin.reflect.KProperty;
import kotlin.reflect.KProperty0;
import kotlin.reflect.KProperty1;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000>\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aG\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006H\u0087\u008a\u0004b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\nb\u0002\b\u000b¢\u0006\u0002\u0010\u0007\u001aO\u0010\f\u001a\u00020\r\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u000e2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\u000f\u001a\u0002H\u0001H\u0087\u008a\u0004b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\nb\u0002\b\u000b¢\u0006\u0002\u0010\u0010\u001aQ\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0011\"\u0004\b\u0001\u0010\u0001*\u000e\u0012\u0004\u0012\u0002H\u0011\u0012\u0004\u0012\u0002H\u00010\u00122\u0006\u0010\u0003\u001a\u0002H\u00112\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006H\u0087\u008a\u0004b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\nb\u0002\b\u000b¢\u0006\u0002\u0010\u0013\u001aY\u0010\f\u001a\u00020\r\"\u0004\b\u0000\u0010\u0011\"\u0004\b\u0001\u0010\u0001*\u000e\u0012\u0004\u0012\u0002H\u0011\u0012\u0004\u0012\u0002H\u00010\u00142\u0006\u0010\u0003\u001a\u0002H\u00112\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\u000f\u001a\u0002H\u0001H\u0087\u008a\u0004b\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\nb\u0002\b\u000b¢\u0006\u0002\u0010\u0015¨\u0006\u0016"}, d2 = {"getValue", "V", "Lkotlin/reflect/KProperty0;", "thisRef", "", "property", "Lkotlin/reflect/KProperty;", "(Lkotlin/reflect/KProperty0;Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "Lkotlin/SinceKotlin;", "version", "1.4", "Lkotlin/internal/InlineOnly;", "setValue", "", "Lkotlin/reflect/KMutableProperty0;", "value", "(Lkotlin/reflect/KMutableProperty0;Ljava/lang/Object;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V", "T", "Lkotlin/reflect/KProperty1;", "(Lkotlin/reflect/KProperty1;Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "Lkotlin/reflect/KMutableProperty1;", "(Lkotlin/reflect/KMutableProperty1;Ljava/lang/Object;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V", "kotlin-stdlib"}, k = 2, mv = {2, 4, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final class PropertyReferenceDelegatesKt {
    private static final <V> V getValue(KProperty0<? extends V> kProperty0, Object obj, KProperty<?> kProperty) {
        kProperty0.getClass();
        kProperty.getClass();
        return kProperty0.get();
    }

    private static final <V> void setValue(KMutableProperty0<V> kMutableProperty0, Object obj, KProperty<?> kProperty, V v) {
        kMutableProperty0.getClass();
        kProperty.getClass();
        kMutableProperty0.set(v);
    }

    private static final <T, V> void setValue(KMutableProperty1<T, V> kMutableProperty1, T t, KProperty<?> kProperty, V v) {
        kMutableProperty1.getClass();
        kProperty.getClass();
        kMutableProperty1.set(t, v);
    }

    private static final <T, V> V getValue(KProperty1<T, ? extends V> kProperty1, T t, KProperty<?> kProperty) {
        kProperty1.getClass();
        kProperty.getClass();
        return kProperty1.get(t);
    }
}
