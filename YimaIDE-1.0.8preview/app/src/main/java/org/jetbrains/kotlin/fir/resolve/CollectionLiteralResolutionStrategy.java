package org.jetbrains.kotlin.fir.resolve;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirCollectionLiteral;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionContext;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH ¢\u0006\u0002\b\u0010J\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u000fH&R\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/CollectionLiteralResolutionStrategy;", Argument.Delimiters.none, "context", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;)V", "getContext", "()Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "components", "Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;", "getComponents", "()Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;", "declaresOperatorOf", Argument.Delimiters.none, "expectedType", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "declaresOperatorOf$org_jetbrains_kotlin_resolve", "prepareRawCall", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "collectionLiteral", "Lorg/jetbrains/kotlin/fir/expressions/FirCollectionLiteral;", "expectedClass", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class CollectionLiteralResolutionStrategy {
    private final ResolutionContext context;

    public CollectionLiteralResolutionStrategy(ResolutionContext resolutionContext) {
        resolutionContext.getClass();
        this.context = resolutionContext;
    }

    public abstract boolean declaresOperatorOf$org_jetbrains_kotlin_resolve(FirRegularClassSymbol expectedType);

    public final BodyResolveComponents getComponents() {
        return this.context.getBodyResolveComponents();
    }

    public final ResolutionContext getContext() {
        return this.context;
    }

    public abstract FirFunctionCall prepareRawCall(FirCollectionLiteral collectionLiteral, FirRegularClassSymbol expectedClass);
}
