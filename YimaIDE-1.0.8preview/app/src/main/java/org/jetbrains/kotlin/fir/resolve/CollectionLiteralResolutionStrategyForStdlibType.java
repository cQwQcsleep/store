package org.jetbrains.kotlin.fir.resolve;

import kotlin.Metadata;
import kotlin.Pair;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirCollectionLiteral;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionContext;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0010¢\u0006\u0002\b\nJ\u001c\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\tH\u0016¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/CollectionLiteralResolutionStrategyForStdlibType;", "Lorg/jetbrains/kotlin/fir/resolve/CollectionLiteralResolutionStrategy;", "context", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;)V", "declaresOperatorOf", Argument.Delimiters.none, "expectedType", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "declaresOperatorOf$org_jetbrains_kotlin_resolve", "prepareRawCall", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "collectionLiteral", "Lorg/jetbrains/kotlin/fir/expressions/FirCollectionLiteral;", "expectedClass", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class CollectionLiteralResolutionStrategyForStdlibType extends CollectionLiteralResolutionStrategy {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CollectionLiteralResolutionStrategyForStdlibType(ResolutionContext resolutionContext) {
        super(resolutionContext);
        resolutionContext.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.CollectionLiteralResolutionStrategy
    public boolean declaresOperatorOf$org_jetbrains_kotlin_resolve(FirRegularClassSymbol expectedType) {
        expectedType.getClass();
        return StdlibFactoryFunctionsUtilsKt.toCollectionOfFactoryPackageAndName(expectedType, getContext().getSession()) != null;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.CollectionLiteralResolutionStrategy
    public FirFunctionCall prepareRawCall(FirCollectionLiteral collectionLiteral, FirRegularClassSymbol expectedClass) {
        Pair<FqName, Name> collectionOfFactoryPackageAndName;
        collectionLiteral.getClass();
        if (expectedClass == null || (collectionOfFactoryPackageAndName = StdlibFactoryFunctionsUtilsKt.toCollectionOfFactoryPackageAndName(expectedClass, getContext().getSession())) == null) {
            return null;
        }
        return CollectionLiteralResolutionUtilsKt.buildCollectionLiteralCallForStdlibType(getComponents(), (FqName) collectionOfFactoryPackageAndName.component1(), (Name) collectionOfFactoryPackageAndName.component2(), collectionLiteral);
    }
}
