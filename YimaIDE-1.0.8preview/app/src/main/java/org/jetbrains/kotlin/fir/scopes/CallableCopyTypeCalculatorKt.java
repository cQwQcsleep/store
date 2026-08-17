package org.jetbrains.kotlin.fir.scopes;

import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationAttributes;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationDataRegistry;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\"3\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010\u00018F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\b\u0010\t\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"<set-?>", "Lorg/jetbrains/kotlin/fir/scopes/DeferredCallableCopyReturnType;", "deferredCallableCopyReturnType", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;", "getDeferredCallableCopyReturnType", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;)Lorg/jetbrains/kotlin/fir/scopes/DeferredCallableCopyReturnType;", "setDeferredCallableCopyReturnType", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;Lorg/jetbrains/kotlin/fir/scopes/DeferredCallableCopyReturnType;)V", "deferredCallableCopyReturnType$delegate", "Lkotlin/properties/ReadWriteProperty;", "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CallableCopyTypeCalculatorKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new MutablePropertyReference1Impl<>(CallableCopyTypeCalculatorKt.class, "deferredCallableCopyReturnType", "getDeferredCallableCopyReturnType(Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;)Lorg/jetbrains/kotlin/fir/scopes/DeferredCallableCopyReturnType;", 1)};
    private static final ReadWriteProperty deferredCallableCopyReturnType$delegate = FirDeclarationDataRegistry.INSTANCE.attributesAccessor(DeferredCallableCopyReturnTypeKey.INSTANCE);

    public static final DeferredCallableCopyReturnType getDeferredCallableCopyReturnType(FirDeclarationAttributes firDeclarationAttributes) {
        firDeclarationAttributes.getClass();
        return (DeferredCallableCopyReturnType) deferredCallableCopyReturnType$delegate.getValue(firDeclarationAttributes, $$delegatedProperties[0]);
    }

    public static final void setDeferredCallableCopyReturnType(FirDeclarationAttributes firDeclarationAttributes, DeferredCallableCopyReturnType deferredCallableCopyReturnType) {
        firDeclarationAttributes.getClass();
        deferredCallableCopyReturnType$delegate.setValue(firDeclarationAttributes, $$delegatedProperties[0], deferredCallableCopyReturnType);
    }
}
