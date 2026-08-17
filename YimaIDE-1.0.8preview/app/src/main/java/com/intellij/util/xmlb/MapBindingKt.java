package com.intellij.util.xmlb;

import com.intellij.util.xmlb.MapBindingKt;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000L\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\u001a(\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00022\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0002\u001a(\u0010\n\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u000b\u001a\u00020\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\rH\u0002\u001a=\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00102\u000e\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00122\u000e\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u00122\u0006\u0010\u0014\u001a\u00020\u0015H\u0002¢\u0006\u0002\u0010\u0016\u001aB\u0010\u0017\u001a\u0004\u0018\u00010\u0002\"\b\b\u0000\u0010\u0018*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\u0019\u001a\u0004\u0018\u00010\u00022\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001dH\u0000\"\u0016\u0010\u0000\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"KEY_COMPARATOR", "Ljava/util/Comparator;", "", "keyOrValueToJson", "Lkotlinx/serialization/json/JsonElement;", "value", "binding", "Lcom/intellij/util/xmlb/Binding;", "filter", "Lcom/intellij/util/xmlb/SerializationFilter;", "keyOrValueFromJson", "element", "valueClass", "Ljava/lang/Class;", "createMapElement", "hasComplexKey", "", "keys", "", "values", "size", "", "(Z[Ljava/lang/Object;[Lkotlinx/serialization/json/JsonElement;I)Lkotlinx/serialization/json/JsonElement;", "deserializeList", "T", "currentValue", "elements", "", "adapter", "Lcom/intellij/util/xmlb/DomAdapter;", "intellij.platform.util"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class MapBindingKt {
    private static final Comparator<Object> KEY_COMPARATOR = new Comparator() { // from class: wt9
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return MapBindingKt.a(obj, obj2);
        }
    };

    public static int a(Object obj, Object obj2) {
        if ((obj instanceof Comparable) && (obj2 instanceof Comparable)) {
            return ((Comparable) obj).compareTo((Comparable) obj2);
        }
        return 0;
    }

    public static final <T> Object deserializeList(Binding binding, Object obj, List<? extends T> list, DomAdapter<T> domAdapter) {
        binding.getClass();
        list.getClass();
        domAdapter.getClass();
        if (binding instanceof MultiNodeBinding) {
            return ((MultiNodeBinding) binding).deserializeList(obj, list, domAdapter);
        }
        if (list.size() == 1) {
            return binding.deserialize(obj, list.get(0), domAdapter);
        }
        if (list.isEmpty()) {
            return null;
        }
        iti.a("Duplicate data for ", binding, " will be ignored");
        return null;
    }
}
