package org.jetbrains.kotlin.fir.backend;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationDataRegistry;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.ir.IrAttribute;
import org.jetbrains.kotlin.ir.IrAttributeKt;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrDeclaration;
import org.jetbrains.kotlin.ir.symbols.IrBindableSymbol;
import org.jetbrains.kotlin.ir.symbols.UnsafeDuringIrConstructionAPI;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0002\u001a\u0016\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0002\u001a\u0016\u0010\u0006\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0002\u001a+\u0010\u0016\u001a\u0004\u0018\u0001H\u0017\"\b\b\u0000\u0010\u0017*\u00020\u0018*\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u0002H\u00170\u0019H\u0001b\u0002\b\u001b¢\u0006\u0002\u0010\u001a\"3\u0010\b\u001a\u0004\u0018\u00010\u0001*\u00020\t2\b\u0010\u0007\u001a\u0004\u0018\u00010\u00018@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\b\r\u0010\u000e\u001a\u0004\b\b\u0010\n\"\u0004\b\u000b\u0010\f\"/\u0010\u000f\u001a\u00020\u0001*\u00020\u00102\u0006\u0010\u0007\u001a\u00020\u00018@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u000f\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006\u001c"}, d2 = {"isFakeOverrideOrDelegated", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "fakeOverrideOwnerLookupTag", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "isFakeOverride", "isFakeOverrideImpl", "<set-?>", "isStubPropertyForPureField", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "(Lorg/jetbrains/kotlin/fir/declarations/FirProperty;)Ljava/lang/Boolean;", "setStubPropertyForPureField", "(Lorg/jetbrains/kotlin/fir/declarations/FirProperty;Ljava/lang/Boolean;)V", "isStubPropertyForPureField$delegate", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationDataRegistry$DeclarationDataAccessor;", "isNonCachedSourceFileFacade", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "(Lorg/jetbrains/kotlin/ir/declarations/IrClass;)Z", "setNonCachedSourceFileFacade", "(Lorg/jetbrains/kotlin/ir/declarations/IrClass;Z)V", "isNonCachedSourceFileFacade$delegate", "Lorg/jetbrains/kotlin/ir/IrAttribute$Flag;", "ownerIfBound", "D", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclaration;", "Lorg/jetbrains/kotlin/ir/symbols/IrBindableSymbol;", "(Lorg/jetbrains/kotlin/ir/symbols/IrBindableSymbol;)Lorg/jetbrains/kotlin/ir/declarations/IrDeclaration;", "Lorg/jetbrains/kotlin/ir/symbols/UnsafeDuringIrConstructionAPI;", "org.jetbrains.kotlin:fir2ir"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class Fir2IrDeclarationStorageKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;
    private static final IrAttribute.Flag isNonCachedSourceFileFacade$delegate;
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor isStubPropertyForPureField$delegate;

    static {
        KProperty<Object>[] kPropertyArr = {new MutablePropertyReference1Impl<>(Fir2IrDeclarationStorageKt.class, "isStubPropertyForPureField", "isStubPropertyForPureField(Lorg/jetbrains/kotlin/fir/declarations/FirProperty;)Ljava/lang/Boolean;", 1), new MutablePropertyReference1Impl<>(Fir2IrDeclarationStorageKt.class, "isNonCachedSourceFileFacade", "isNonCachedSourceFileFacade(Lorg/jetbrains/kotlin/ir/declarations/IrClass;)Z", 1)};
        $$delegatedProperties = kPropertyArr;
        isStubPropertyForPureField$delegate = FirDeclarationDataRegistry.INSTANCE.data(IsStubPropertyForPureFieldKey.INSTANCE);
        isNonCachedSourceFileFacade$delegate = IrAttributeKt.irFlag(false).provideDelegate((Object) null, kPropertyArr[1]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isFakeOverride(FirCallableDeclaration firCallableDeclaration, ConeClassLikeLookupTag coneClassLikeLookupTag) {
        if (ClassMembersKt.isSubstitutionOrIntersectionOverride(firCallableDeclaration)) {
            return true;
        }
        return isFakeOverrideImpl(firCallableDeclaration, coneClassLikeLookupTag);
    }

    private static final boolean isFakeOverrideImpl(FirCallableDeclaration firCallableDeclaration, ConeClassLikeLookupTag coneClassLikeLookupTag) {
        if (coneClassLikeLookupTag == null) {
            return false;
        }
        return !Intrinsics.areEqual(coneClassLikeLookupTag, ClassMembersKt.containingClassLookupTag(firCallableDeclaration));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isFakeOverrideOrDelegated(FirCallableDeclaration firCallableDeclaration, ConeClassLikeLookupTag coneClassLikeLookupTag) {
        if (ClassMembersKt.isCopyCreatedInScope(firCallableDeclaration)) {
            return true;
        }
        return isFakeOverrideImpl(firCallableDeclaration, coneClassLikeLookupTag);
    }

    public static final boolean isNonCachedSourceFileFacade(IrClass irClass) {
        irClass.getClass();
        return isNonCachedSourceFileFacade$delegate.get(irClass);
    }

    public static final Boolean isStubPropertyForPureField(FirProperty firProperty) {
        firProperty.getClass();
        return (Boolean) isStubPropertyForPureField$delegate.getValue(firProperty, $$delegatedProperties[0]);
    }

    @UnsafeDuringIrConstructionAPI
    public static final <D extends IrDeclaration> D ownerIfBound(IrBindableSymbol<?, D> irBindableSymbol) {
        irBindableSymbol.getClass();
        if (irBindableSymbol.isBound()) {
            return irBindableSymbol.getOwner();
        }
        return null;
    }

    public static final void setNonCachedSourceFileFacade(IrClass irClass, boolean z) {
        irClass.getClass();
        isNonCachedSourceFileFacade$delegate.set(irClass, z);
    }

    public static final void setStubPropertyForPureField(FirProperty firProperty, Boolean bool) {
        firProperty.getClass();
        isStubPropertyForPureField$delegate.setValue(firProperty, $$delegatedProperties[0], bool);
    }
}
