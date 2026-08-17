package org.jetbrains.kotlin.js.backend.ast.metadata;

import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010$\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u000b\u001a\u0002H\f\"\u0004\b\u0000\u0010\f2\u0006\u0010\r\u001a\u00020\u0006¢\u0006\u0002\u0010\u000eJ!\u0010\u000f\u001a\u00020\u0010\"\u0004\b\u0000\u0010\f2\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u0002H\f¢\u0006\u0002\u0010\u0012J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\r\u001a\u00020\u0006J\u000e\u0010\u0015\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u0006J\u000e\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u0001J\u0014\u0010\u0018\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0019R\u001e\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/js/backend/ast/metadata/HasMetadataImpl;", "Lorg/jetbrains/kotlin/js/backend/ast/metadata/HasMetadata;", "<init>", "()V", "metadataImpl", "", "", "", "metadata", "getMetadata", "()Ljava/util/Map;", "getData", "T", "key", "(Ljava/lang/String;)Ljava/lang/Object;", "setData", "", "value", "(Ljava/lang/String;Ljava/lang/Object;)V", "hasData", "", "removeData", "copyMetadataFrom", "other", "getRawMetadata", "", "org.jetbrains.kotlin:js.ast"}, k = 1, mv = {2, 4, 0}, xi = 48)
public class HasMetadataImpl implements HasMetadata {
    private Map<String, Object> metadataImpl;

    private final Map<String, Object> getMetadata() {
        Map<String, Object> map = this.metadataImpl;
        if (map != null) {
            return map;
        }
        HashMap map2 = new HashMap();
        this.metadataImpl = map2;
        return map2;
    }

    @Override // org.jetbrains.kotlin.js.backend.ast.metadata.HasMetadata
    public final void copyMetadataFrom(HasMetadata other) {
        other.getClass();
        Map<String, Object> rawMetadata = other.getRawMetadata();
        if (rawMetadata.isEmpty()) {
            return;
        }
        getMetadata().putAll(rawMetadata);
    }

    @Override // org.jetbrains.kotlin.js.backend.ast.metadata.HasMetadata
    public final <T> T getData(String key) {
        key.getClass();
        return (T) getMetadata().get(key);
    }

    @Override // org.jetbrains.kotlin.js.backend.ast.metadata.HasMetadata
    public final Map<String, Object> getRawMetadata() {
        Map<String, Object> map = this.metadataImpl;
        return map == null ? MapsKt.emptyMap() : map;
    }

    @Override // org.jetbrains.kotlin.js.backend.ast.metadata.HasMetadata
    public final boolean hasData(String key) {
        key.getClass();
        Map<String, Object> map = this.metadataImpl;
        return map != null && map.containsKey(key);
    }

    @Override // org.jetbrains.kotlin.js.backend.ast.metadata.HasMetadata
    public final void removeData(String key) {
        key.getClass();
        Map<String, Object> map = this.metadataImpl;
        if (map != null) {
            map.remove(key);
        }
    }

    @Override // org.jetbrains.kotlin.js.backend.ast.metadata.HasMetadata
    public final <T> void setData(String key, T value) {
        key.getClass();
        getMetadata().put(key, value);
    }
}
