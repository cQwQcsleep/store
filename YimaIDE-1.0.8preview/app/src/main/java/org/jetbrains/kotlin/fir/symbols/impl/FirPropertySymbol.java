package org.jetbrains.kotlin.fir.symbols.impl;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.references.FirControlFlowGraphReference;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.mpp.PropertySymbolMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\t\b\u0004¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010&\u001a\u00020\u0019H\u0014R\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0016\u0010\n\u001a\u0004\u0018\u00010\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\tR\u0013\u0010\f\u001a\u0004\u0018\u00010\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u00118F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u00158F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0018\u001a\u00020\u00198F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u001c\u001a\u00020\u00198F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001bR\u0013\u0010\u001e\u001a\u0004\u0018\u00010\u001f8F¢\u0006\u0006\u001a\u0004\b \u0010!R\u0013\u0010\"\u001a\u0004\u0018\u00010#8F¢\u0006\u0006\u001a\u0004\b$\u0010%\u0082\u0001\u0002'(¨\u0006)"}, d2 = {"Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "Lorg/jetbrains/kotlin/mpp/PropertySymbolMarker;", "<init>", "()V", "getterSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertyAccessorSymbol;", "getGetterSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertyAccessorSymbol;", "setterSymbol", "getSetterSymbol", "backingFieldSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirBackingFieldSymbol;", "getBackingFieldSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirBackingFieldSymbol;", "delegateFieldSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirDelegateFieldSymbol;", "getDelegateFieldSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirDelegateFieldSymbol;", "delegate", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "getDelegate", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "hasDelegate", Argument.Delimiters.none, "getHasDelegate", "()Z", "hasInitializer", "getHasInitializer", "initializerSource", "Lorg/jetbrains/kotlin/KtSourceElement;", "getInitializerSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "controlFlowGraphReference", "Lorg/jetbrains/kotlin/fir/references/FirControlFlowGraphReference;", "getControlFlowGraphReference", "()Lorg/jetbrains/kotlin/fir/references/FirControlFlowGraphReference;", "deprecationsAreDefinitelyEmpty", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirLocalPropertySymbol;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularPropertySymbol;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirPropertySymbol extends FirVariableSymbol<FirProperty> implements PropertySymbolMarker {
    private FirPropertySymbol() {
        super(null);
    }

    @Override // org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol
    public boolean deprecationsAreDefinitelyEmpty() {
        if (!currentDeclarationDeprecationsAreDefinitelyEmpty$org_jetbrains_kotlin_tree()) {
            return false;
        }
        FirPropertyAccessorSymbol getterSymbol = getGetterSymbol();
        if (getterSymbol != null && !getterSymbol.currentDeclarationDeprecationsAreDefinitelyEmpty$org_jetbrains_kotlin_tree()) {
            return false;
        }
        FirPropertyAccessorSymbol setterSymbol = getSetterSymbol();
        return setterSymbol == null || setterSymbol.currentDeclarationDeprecationsAreDefinitelyEmpty$org_jetbrains_kotlin_tree();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final FirBackingFieldSymbol getBackingFieldSymbol() {
        FirBackingField backingField = ((FirProperty) getFir()).getBackingField();
        if (backingField != null) {
            return backingField.getSymbol();
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final FirControlFlowGraphReference getControlFlowGraphReference() {
        FirLazyDeclarationResolverKt.lazyResolveToPhase(this, FirResolvePhase.BODY_RESOLVE);
        return ((FirProperty) getFir()).getControlFlowGraphReference();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final FirExpression getDelegate() {
        return ((FirProperty) getFir()).getDelegate();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final FirDelegateFieldSymbol getDelegateFieldSymbol() {
        return ((FirProperty) getFir()).getDelegateFieldSymbol();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public FirPropertyAccessorSymbol getGetterSymbol() {
        FirPropertyAccessor getter = ((FirProperty) getFir()).getGetter();
        if (getter != null) {
            return getter.getSymbol();
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean getHasDelegate() {
        return ((FirProperty) getFir()).getDelegate() != null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean getHasInitializer() {
        return ((FirProperty) getFir()).getInitializer() != null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final KtSourceElement getInitializerSource() {
        FirExpression initializer = ((FirProperty) getFir()).getInitializer();
        if (initializer != null) {
            return initializer.getSource();
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public FirPropertyAccessorSymbol getSetterSymbol() {
        FirPropertyAccessor setter = ((FirProperty) getFir()).getSetter();
        if (setter != null) {
            return setter.getSymbol();
        }
        return null;
    }

    public /* synthetic */ FirPropertySymbol(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
