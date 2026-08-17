package org.jetbrains.kotlin.fir.java.enhancement;

import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationDataRegistry;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\"3\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010\u00018F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\b\u0010\t\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007\"\u001b\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u0006\u0012\u0002\b\u00030\n8F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u000b¨\u0006\f"}, d2 = {"<set-?>", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "inheritedKtPrivateCls", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "getInheritedKtPrivateCls", "(Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;)Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "setInheritedKtPrivateCls", "(Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "inheritedKtPrivateCls$delegate", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationDataRegistry$DeclarationDataAccessor;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;)Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "org.jetbrains.kotlin:fir-jvm"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class InheritedKtPrivateClassDataKeyKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new MutablePropertyReference1Impl<>(InheritedKtPrivateClassDataKeyKt.class, "inheritedKtPrivateCls", "getInheritedKtPrivateCls(Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;)Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", 1)};
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor inheritedKtPrivateCls$delegate = FirDeclarationDataRegistry.INSTANCE.data(InheritedKtPrivateClassDataKey.INSTANCE);

    public static final ConeKotlinType getInheritedKtPrivateCls(FirCallableDeclaration firCallableDeclaration) {
        firCallableDeclaration.getClass();
        return (ConeKotlinType) inheritedKtPrivateCls$delegate.getValue(firCallableDeclaration, $$delegatedProperties[0]);
    }

    public static final void setInheritedKtPrivateCls(FirCallableDeclaration firCallableDeclaration, ConeKotlinType coneKotlinType) {
        firCallableDeclaration.getClass();
        inheritedKtPrivateCls$delegate.setValue(firCallableDeclaration, $$delegatedProperties[0], coneKotlinType);
    }

    public static final ConeKotlinType getInheritedKtPrivateCls(FirCallableSymbol<?> firCallableSymbol) {
        firCallableSymbol.getClass();
        return getInheritedKtPrivateCls((FirCallableDeclaration) firCallableSymbol.getFir());
    }
}
