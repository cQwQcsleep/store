package org.jetbrains.kotlin.fir.symbols.impl;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\t\b\u0004¢\u0006\u0004\b\u0004\u0010\u0005R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u00078F¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0013\u0010\n\u001a\u0004\u0018\u00010\u00078F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\f\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\f\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u000eR\u0014\u0010\u0010\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013\u0082\u0001\u0006\u0014\u0015\u0016\u0017\u0018\u0019¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "E", "Lorg/jetbrains/kotlin/fir/declarations/FirVariable;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "<init>", "()V", "resolvedInitializer", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "getResolvedInitializer", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "resolvedDefaultValue", "getResolvedDefaultValue", "isVal", Argument.Delimiters.none, "()Z", "isVar", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirBackingFieldSymbol;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirDelegateFieldSymbol;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirEnumEntrySymbol;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFieldSymbol;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirValueParameterSymbol;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirVariableSymbol<E extends FirVariable> extends FirCallableSymbol<E> {
    public /* synthetic */ FirVariableSymbol(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol
    public Name getName() {
        return ((FirVariable) getFir()).getName();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    public final FirExpression getResolvedDefaultValue() throws KotlinIllegalArgumentExceptionWithAttachments {
        D fir = getFir();
        FirValueParameter firValueParameter = fir instanceof FirValueParameter ? (FirValueParameter) fir : null;
        if ((firValueParameter != null ? firValueParameter.getDefaultValue() : null) == null) {
            return null;
        }
        FirLazyDeclarationResolverKt.lazyResolveToPhase(this, FirResolvePhase.BODY_RESOLVE);
        return firValueParameter.getDefaultValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final FirExpression getResolvedInitializer() {
        if (((FirVariable) getFir()).getInitializer() == null) {
            return null;
        }
        FirLazyDeclarationResolverKt.lazyResolveToPhase(this, getRawStatus().isConst() ? FirResolvePhase.IMPLICIT_TYPES_BODY_RESOLVE : FirResolvePhase.BODY_RESOLVE);
        return ((FirVariable) getFir()).getInitializer();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean isVal() {
        return ((FirVariable) getFir()).isVal();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean isVar() {
        return ((FirVariable) getFir()).isVar();
    }

    private FirVariableSymbol() {
    }
}
