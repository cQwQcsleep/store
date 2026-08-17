package org.jetbrains.kotlin.fir.resolve;

import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirTowerDataContext;
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess;
import org.jetbrains.kotlin.fir.resolve.inference.FirInferenceSession;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousFunctionSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010\r\u001a\u0016\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007j\u0004\u0018\u0001`\n2\u0006\u0010\u000e\u001a\u00020\u0006J\"\u0010\u000f\u001a\u0016\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007j\u0004\u0018\u0001`\n2\u0006\u0010\u0010\u001a\u00020\fJ\u001e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\tJ\u000e\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u000e\u001a\u00020\u0006J\u001e\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\tJ\u000e\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0010\u001a\u00020\fJ\u000e\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u0000J\u0006\u0010\u001a\u001a\u00020\u0012R*\u0010\u0004\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007j\u0002`\n0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\u000b\u001a\u001e\u0012\u0004\u0012\u00020\f\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007j\u0002`\n0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/FirSpecialTowerDataContexts;", Argument.Delimiters.none, "<init>", "()V", "contextForAnonymousFunctions", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirAnonymousFunctionSymbol;", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/fir/declarations/FirTowerDataContext;", "Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceSession;", "Lorg/jetbrains/kotlin/fir/resolve/PostponedAtomsResolutionContext;", "contextForCallableReferences", "Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;", "getAnonymousFunctionContext", "symbol", "getCallableReferenceContext", "access", "storeAnonymousFunctionContext", Argument.Delimiters.none, "context", "inferenceSession", "dropAnonymousFunctionContext", "storeCallableReferenceContext", "dropCallableReferenceContext", "putAll", "contexts", "clear", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirSpecialTowerDataContexts {
    private final Map<FirAnonymousFunctionSymbol, Pair<FirTowerDataContext, FirInferenceSession>> contextForAnonymousFunctions = new HashMap();
    private final Map<FirCallableReferenceAccess, Pair<FirTowerDataContext, FirInferenceSession>> contextForCallableReferences = new HashMap();

    public final void clear() {
        this.contextForAnonymousFunctions.clear();
        this.contextForCallableReferences.clear();
    }

    public final void dropAnonymousFunctionContext(FirAnonymousFunctionSymbol symbol) {
        symbol.getClass();
        this.contextForAnonymousFunctions.remove(symbol);
    }

    public final void dropCallableReferenceContext(FirCallableReferenceAccess access) {
        access.getClass();
        this.contextForCallableReferences.remove(access);
    }

    public final Pair<FirTowerDataContext, FirInferenceSession> getAnonymousFunctionContext(FirAnonymousFunctionSymbol symbol) {
        symbol.getClass();
        return this.contextForAnonymousFunctions.get(symbol);
    }

    public final Pair<FirTowerDataContext, FirInferenceSession> getCallableReferenceContext(FirCallableReferenceAccess access) {
        access.getClass();
        return this.contextForCallableReferences.get(access);
    }

    public final void putAll(FirSpecialTowerDataContexts contexts) {
        contexts.getClass();
        this.contextForCallableReferences.putAll(contexts.contextForCallableReferences);
        this.contextForAnonymousFunctions.putAll(contexts.contextForAnonymousFunctions);
    }

    public final void storeAnonymousFunctionContext(FirAnonymousFunctionSymbol symbol, FirTowerDataContext context, FirInferenceSession inferenceSession) {
        symbol.getClass();
        context.getClass();
        inferenceSession.getClass();
        this.contextForAnonymousFunctions.put(symbol, new Pair<>(context, inferenceSession));
    }

    public final void storeCallableReferenceContext(FirCallableReferenceAccess access, FirTowerDataContext context, FirInferenceSession inferenceSession) {
        access.getClass();
        context.getClass();
        inferenceSession.getClass();
        this.contextForCallableReferences.put(access, new Pair<>(context, inferenceSession));
    }
}
