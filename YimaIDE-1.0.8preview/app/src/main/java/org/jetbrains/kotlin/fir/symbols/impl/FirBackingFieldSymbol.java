package org.jetbrains.kotlin.fir.symbols.impl;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.name.CallableId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\u0011\u001a\u00020\u0012H\u0014R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/fir/symbols/impl/FirBackingFieldSymbol;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "Lorg/jetbrains/kotlin/fir/declarations/FirBackingField;", "<init>", "()V", "callableId", "Lorg/jetbrains/kotlin/name/CallableId;", "getCallableId", "()Lorg/jetbrains/kotlin/name/CallableId;", "propertySymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "getPropertySymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "getterSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertyAccessorSymbol;", "getGetterSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertyAccessorSymbol;", "deprecationsAreDefinitelyEmpty", Argument.Delimiters.none, "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirBackingFieldSymbol extends FirVariableSymbol<FirBackingField> {
    public FirBackingFieldSymbol() {
        super(null);
    }

    @Override // org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol
    public boolean deprecationsAreDefinitelyEmpty() {
        return currentDeclarationDeprecationsAreDefinitelyEmpty$org_jetbrains_kotlin_tree() && getPropertySymbol().currentDeclarationDeprecationsAreDefinitelyEmpty$org_jetbrains_kotlin_tree();
    }

    @Override // org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol
    public CallableId getCallableId() {
        return new CallableId(getName());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final FirPropertyAccessorSymbol getGetterSymbol() {
        FirPropertyAccessor getter = ((FirProperty) ((FirBackingField) getFir()).getPropertySymbol().getFir()).getGetter();
        if (getter != null) {
            return getter.getSymbol();
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final FirPropertySymbol getPropertySymbol() {
        return ((FirBackingField) getFir()).getPropertySymbol();
    }
}
