package org.jetbrains.kotlin.fir.resolve.calls;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationDataRegistry;
import org.jetbrains.kotlin.fir.declarations.synthetic.FirSyntheticProperty;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\"3\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010\u00018B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\b\u0010\t\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u000b\"3\u0010\f\u001a\u0004\u0018\u00010\u0001*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010\u00018B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u000f\u0010\t\u001a\u0004\b\r\u0010\u0005\"\u0004\b\u000e\u0010\u0007\"\u0015\u0010\f\u001a\u00020\u0001*\u00020\n8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000b¨\u0006\u0010"}, d2 = {"<set-?>", Argument.Delimiters.none, "noJavaOrigin", "Lorg/jetbrains/kotlin/fir/declarations/synthetic/FirSyntheticProperty;", "getNoJavaOrigin", "(Lorg/jetbrains/kotlin/fir/declarations/synthetic/FirSyntheticProperty;)Ljava/lang/Boolean;", "setNoJavaOrigin", "(Lorg/jetbrains/kotlin/fir/declarations/synthetic/FirSyntheticProperty;Ljava/lang/Boolean;)V", "noJavaOrigin$delegate", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationDataRegistry$DeclarationDataAccessor;", "Lorg/jetbrains/kotlin/fir/resolve/calls/FirSimpleSyntheticPropertySymbol;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/FirSimpleSyntheticPropertySymbol;)Z", "deprecatedOverrideOfHidden", "getDeprecatedOverrideOfHidden", "setDeprecatedOverrideOfHidden", "deprecatedOverrideOfHidden$delegate", "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SyntheticsKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new MutablePropertyReference1Impl<>(SyntheticsKt.class, "noJavaOrigin", "getNoJavaOrigin(Lorg/jetbrains/kotlin/fir/declarations/synthetic/FirSyntheticProperty;)Ljava/lang/Boolean;", 1), new MutablePropertyReference1Impl<>(SyntheticsKt.class, "deprecatedOverrideOfHidden", "getDeprecatedOverrideOfHidden(Lorg/jetbrains/kotlin/fir/declarations/synthetic/FirSyntheticProperty;)Ljava/lang/Boolean;", 1)};
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor deprecatedOverrideOfHidden$delegate;
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor noJavaOrigin$delegate;

    static {
        FirDeclarationDataRegistry firDeclarationDataRegistry = FirDeclarationDataRegistry.INSTANCE;
        noJavaOrigin$delegate = firDeclarationDataRegistry.data(NoJavaOriginKey.INSTANCE);
        deprecatedOverrideOfHidden$delegate = firDeclarationDataRegistry.data(DeprecatedOverrideOfHidden.INSTANCE);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    public static final boolean getDeprecatedOverrideOfHidden(FirSimpleSyntheticPropertySymbol firSimpleSyntheticPropertySymbol) throws KotlinIllegalArgumentExceptionWithAttachments {
        firSimpleSyntheticPropertySymbol.getClass();
        D fir = firSimpleSyntheticPropertySymbol.getFir();
        fir.getClass();
        return Intrinsics.areEqual(getDeprecatedOverrideOfHidden((FirSyntheticProperty) fir), Boolean.TRUE);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    public static final boolean getNoJavaOrigin(FirSimpleSyntheticPropertySymbol firSimpleSyntheticPropertySymbol) throws KotlinIllegalArgumentExceptionWithAttachments {
        firSimpleSyntheticPropertySymbol.getClass();
        D fir = firSimpleSyntheticPropertySymbol.getFir();
        fir.getClass();
        return Intrinsics.areEqual(getNoJavaOrigin((FirSyntheticProperty) fir), Boolean.TRUE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setDeprecatedOverrideOfHidden(FirSyntheticProperty firSyntheticProperty, Boolean bool) {
        deprecatedOverrideOfHidden$delegate.setValue(firSyntheticProperty, $$delegatedProperties[1], bool);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setNoJavaOrigin(FirSyntheticProperty firSyntheticProperty, Boolean bool) {
        noJavaOrigin$delegate.setValue(firSyntheticProperty, $$delegatedProperties[0], bool);
    }

    private static final Boolean getDeprecatedOverrideOfHidden(FirSyntheticProperty firSyntheticProperty) {
        return (Boolean) deprecatedOverrideOfHidden$delegate.getValue(firSyntheticProperty, $$delegatedProperties[1]);
    }

    private static final Boolean getNoJavaOrigin(FirSyntheticProperty firSyntheticProperty) {
        return (Boolean) noJavaOrigin$delegate.getValue(firSyntheticProperty, $$delegatedProperties[0]);
    }
}
