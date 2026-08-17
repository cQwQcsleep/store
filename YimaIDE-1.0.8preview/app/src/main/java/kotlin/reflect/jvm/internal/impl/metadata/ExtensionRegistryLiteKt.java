package kotlin.reflect.jvm.internal.impl.metadata;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public final class ExtensionRegistryLiteKt {
    public static final ExtensionRegistryLite ExtensionRegistryLite(Function1<? super ExtensionRegistryLite, Unit> function1) {
        function1.getClass();
        ExtensionRegistryLite extensionRegistryLiteNewInstance = ExtensionRegistryLite.newInstance();
        extensionRegistryLiteNewInstance.getClass();
        function1.invoke(extensionRegistryLiteNewInstance);
        ExtensionRegistryLite unmodifiable = extensionRegistryLiteNewInstance.getUnmodifiable();
        unmodifiable.getClass();
        return unmodifiable;
    }
}
