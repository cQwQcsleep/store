package org.jetbrains.kotlin.fir.declarations.synthetic;

import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.declarations.DeprecationsProvider;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.symbols.impl.FirSyntheticPropertySymbol;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u00101\u001a\u000202R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0017X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\u001dX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001c\u0010\"\u001a\u0004\u0018\u00010#X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u001c\u0010(\u001a\u0004\u0018\u00010\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u0019\"\u0004\b*\u0010\u001bR\u001c\u0010+\u001a\u0004\u0018\u00010,X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100¨\u00063"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/synthetic/FirSyntheticPropertyBuilder;", Argument.Delimiters.none, "<init>", "()V", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "getModuleData", "()Lorg/jetbrains/kotlin/fir/FirModuleData;", "setModuleData", "(Lorg/jetbrains/kotlin/fir/FirModuleData;)V", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "setName", "(Lorg/jetbrains/kotlin/name/Name;)V", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirSyntheticPropertySymbol;", "getSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirSyntheticPropertySymbol;", "setSymbol", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirSyntheticPropertySymbol;)V", "delegateGetter", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "getDelegateGetter", "()Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "setDelegateGetter", "(Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;)V", "deprecationsProvider", "Lorg/jetbrains/kotlin/fir/declarations/DeprecationsProvider;", "getDeprecationsProvider", "()Lorg/jetbrains/kotlin/fir/declarations/DeprecationsProvider;", "setDeprecationsProvider", "(Lorg/jetbrains/kotlin/fir/declarations/DeprecationsProvider;)V", "customStatus", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;", "getCustomStatus", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;", "setCustomStatus", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;)V", "delegateSetter", "getDelegateSetter", "setDelegateSetter", "dispatchReceiverType", "Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;", "getDispatchReceiverType", "()Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;", "setDispatchReceiverType", "(Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;)V", "build", "Lorg/jetbrains/kotlin/fir/declarations/synthetic/FirSyntheticProperty;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirSyntheticPropertyBuilder {
    private FirDeclarationStatus customStatus;
    public FirNamedFunction delegateGetter;
    private FirNamedFunction delegateSetter;
    public DeprecationsProvider deprecationsProvider;
    private ConeSimpleKotlinType dispatchReceiverType;
    public FirModuleData moduleData;
    public Name name;
    public FirSyntheticPropertySymbol symbol;

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final FirSyntheticProperty build() throws UninitializedPropertyAccessException {
        boolean z;
        FirModuleData moduleData = getModuleData();
        Name name = getName();
        boolean z2 = false;
        if (this.delegateSetter != null) {
            z2 = true;
            z = true;
        } else {
            z = true;
        }
        FirSyntheticPropertySymbol symbol = getSymbol();
        boolean z3 = z;
        FirDeclarationStatus firDeclarationStatus = this.customStatus;
        FirSyntheticPropertyAccessor firSyntheticPropertyAccessor = new FirSyntheticPropertyAccessor(getDelegateGetter(), z3, getSymbol());
        FirNamedFunction firNamedFunction = this.delegateSetter;
        FirSyntheticPropertyAccessor firSyntheticPropertyAccessor2 = firNamedFunction != null ? new FirSyntheticPropertyAccessor(firNamedFunction, false, getSymbol()) : null;
        ConeSimpleKotlinType dispatchReceiverType = this.dispatchReceiverType;
        if (dispatchReceiverType == null) {
            dispatchReceiverType = getDelegateGetter().getDispatchReceiverType();
        }
        return new FirSyntheticProperty(moduleData, name, z2, symbol, firDeclarationStatus, firSyntheticPropertyAccessor, dispatchReceiverType, firSyntheticPropertyAccessor2, getDeprecationsProvider());
    }

    public final FirDeclarationStatus getCustomStatus() {
        return this.customStatus;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final FirNamedFunction getDelegateGetter() throws UninitializedPropertyAccessException {
        FirNamedFunction firNamedFunction = this.delegateGetter;
        if (firNamedFunction != null) {
            return firNamedFunction;
        }
        Intrinsics.throwUninitializedPropertyAccessException("delegateGetter");
        return null;
    }

    public final FirNamedFunction getDelegateSetter() {
        return this.delegateSetter;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final DeprecationsProvider getDeprecationsProvider() throws UninitializedPropertyAccessException {
        DeprecationsProvider deprecationsProvider = this.deprecationsProvider;
        if (deprecationsProvider != null) {
            return deprecationsProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("deprecationsProvider");
        return null;
    }

    public final ConeSimpleKotlinType getDispatchReceiverType() {
        return this.dispatchReceiverType;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final FirModuleData getModuleData() throws UninitializedPropertyAccessException {
        FirModuleData firModuleData = this.moduleData;
        if (firModuleData != null) {
            return firModuleData;
        }
        Intrinsics.throwUninitializedPropertyAccessException("moduleData");
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final Name getName() throws UninitializedPropertyAccessException {
        Name name = this.name;
        if (name != null) {
            return name;
        }
        Intrinsics.throwUninitializedPropertyAccessException(ModuleXmlParser.NAME);
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final FirSyntheticPropertySymbol getSymbol() throws UninitializedPropertyAccessException {
        FirSyntheticPropertySymbol firSyntheticPropertySymbol = this.symbol;
        if (firSyntheticPropertySymbol != null) {
            return firSyntheticPropertySymbol;
        }
        Intrinsics.throwUninitializedPropertyAccessException("symbol");
        return null;
    }

    public final void setCustomStatus(FirDeclarationStatus firDeclarationStatus) {
        this.customStatus = firDeclarationStatus;
    }

    public final void setDelegateGetter(FirNamedFunction firNamedFunction) {
        firNamedFunction.getClass();
        this.delegateGetter = firNamedFunction;
    }

    public final void setDelegateSetter(FirNamedFunction firNamedFunction) {
        this.delegateSetter = firNamedFunction;
    }

    public final void setDeprecationsProvider(DeprecationsProvider deprecationsProvider) {
        deprecationsProvider.getClass();
        this.deprecationsProvider = deprecationsProvider;
    }

    public final void setDispatchReceiverType(ConeSimpleKotlinType coneSimpleKotlinType) {
        this.dispatchReceiverType = coneSimpleKotlinType;
    }

    public final void setModuleData(FirModuleData firModuleData) {
        firModuleData.getClass();
        this.moduleData = firModuleData;
    }

    public final void setName(Name name) {
        name.getClass();
        this.name = name;
    }

    public final void setSymbol(FirSyntheticPropertySymbol firSyntheticPropertySymbol) {
        firSyntheticPropertySymbol.getClass();
        this.symbol = firSyntheticPropertySymbol;
    }
}
