package org.jetbrains.kotlin.metadata;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.protobuf.ExtensionRegistryLite;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001f\u0010\u0000\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\b\u0005¨\u0006\u0006"}, d2 = {"ExtensionRegistryLite", "Lorg/jetbrains/kotlin/protobuf/ExtensionRegistryLite;", "register", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "org.jetbrains.kotlin:metadata"}, k = 2, mv = {2, 0, 0}, xi = 48)
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
