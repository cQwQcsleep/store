package org.jetbrains.kotlin.js.backend.ast.metadata;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.js.backend.ast.metadata.HasMetadata;
import org.jetbrains.kotlin.library.KlibConstants;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000*\n\b\u0000\u0010\u0001 \u0000*\u00020\u0002*\u0004\b\u0001\u0010\u00032\u00020\u0004B\u000f\u0012\u0006\u0010\u0005\u001a\u00028\u0001¢\u0006\u0004\b\u0006\u0010\u0007J\"\u0010\u000b\u001a\u00028\u00012\u0006\u0010\f\u001a\u00028\u00002\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u000eH\u0086\u0002¢\u0006\u0002\u0010\u000fJ*\u0010\u0010\u001a\u00020\u00112\u0006\u0010\f\u001a\u00028\u00002\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u000e2\u0006\u0010\u0012\u001a\u00028\u0001H\u0086\u0002¢\u0006\u0002\u0010\u0013R\u0013\u0010\u0005\u001a\u00028\u0001¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\t¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/js/backend/ast/metadata/MetadataProperty;", "T", "Lorg/jetbrains/kotlin/js/backend/ast/metadata/HasMetadata;", "R", "", KlibConstants.KLIB_DEFAULT_COMPONENT_NAME, "<init>", "(Ljava/lang/Object;)V", "getDefault", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getValue", "thisRef", "desc", "Lkotlin/reflect/KProperty;", "(Lorg/jetbrains/kotlin/js/backend/ast/metadata/HasMetadata;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "setValue", "", "value", "(Lorg/jetbrains/kotlin/js/backend/ast/metadata/HasMetadata;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V", "org.jetbrains.kotlin:js.ast"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class MetadataProperty<T extends HasMetadata, R> {
    private final R default;

    public MetadataProperty(R r) {
        this.default = r;
    }

    public final R getDefault() {
        return this.default;
    }

    public final R getValue(T thisRef, KProperty<?> desc) {
        thisRef.getClass();
        desc.getClass();
        return !thisRef.hasData(desc.getName()) ? this.default : (R) thisRef.getData(desc.getName());
    }

    public final void setValue(T thisRef, KProperty<?> desc, R value) {
        thisRef.getClass();
        desc.getClass();
        if (Intrinsics.areEqual(value, this.default)) {
            thisRef.removeData(desc.getName());
        } else {
            thisRef.setData(desc.getName(), value);
        }
    }
}
