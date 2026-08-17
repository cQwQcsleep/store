package org.jetbrains.kotlin.library;

import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0007\b\u0000¢\u0006\u0002\u0010\u0002J(\u0010\u0006\u001a\u0004\u0018\u0001H\u0007\"\b\b\u0000\u0010\u0007*\u00020\u00012\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0005H\u0086\u0002¢\u0006\u0002\u0010\tJ2\u0010\n\u001a\u0004\u0018\u0001H\u0007\"\b\b\u0000\u0010\u0007*\u00020\u00012\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00070\u00052\b\u0010\u000b\u001a\u0004\u0018\u0001H\u0007H\u0086\u0002¢\u0006\u0002\u0010\fR\u001e\u0010\u0003\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005\u0012\u0004\u0012\u00020\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/library/KlibAttributes;", "", "()V", "attributes", "", "Lorg/jetbrains/kotlin/library/KlibAttribute;", "get", "T", "attribute", "(Lorg/jetbrains/kotlin/library/KlibAttribute;)Ljava/lang/Object;", "set", "value", "(Lorg/jetbrains/kotlin/library/KlibAttribute;Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-util-klib"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class KlibAttributes {
    private final Map<KlibAttribute<?>, Object> attributes = new HashMap();

    public final <T> T get(KlibAttribute<T> attribute) {
        attribute.getClass();
        return (T) this.attributes.get(attribute);
    }

    public final <T> T set(KlibAttribute<T> attribute, T value) {
        attribute.getClass();
        Map<KlibAttribute<?>, Object> map = this.attributes;
        return value != null ? (T) map.put(attribute, value) : (T) map.remove(attribute);
    }
}
