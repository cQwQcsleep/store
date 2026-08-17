package org.jetbrains.kotlin.fir.resolve.calls;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.SessionAndScopeSessionHolder;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.InaccessibleReceiverKind;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.ImplicitReceiverValue;
import org.jetbrains.kotlin.fir.scopes.CallableCopyTypeCalculator;
import org.jetbrains.kotlin.fir.scopes.DelicateScopeAPI;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.symbols.impl.FirThisOwnerSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\f\b\u0000\u0010\u0001*\u0006\u0012\u0002\b\u00030\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\u00020\u00042\u00020\u0005BE\b\u0004\u0012\u0006\u0010\u0006\u001a\u00028\u0000\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b\u0012\u0010\u0013J\b\u0010!\u001a\u00020\"H\u0014J\u001b\u0010#\u001a\u0004\u0018\u00010\u001cH\u0016R\u00020\u0005j\u0006\u0010$\u001a\u00020\u0005¢\u0006\u0002\u0010%J\u0014\u0010)\u001a\u00020*2\u0006\u0010\u0007\u001a\u00020\bH\u0017b\u0002\b+J\u0016\u0010,\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010-\u001a\u00020\u000fH&J\"\u0010.\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010/\u001a\u00020\u000b2\u0006\u00100\u001a\u00020\rH'b\u0002\b1R\u0016\u0010\u0006\u001a\u00028\u0000X\u0096\u0004¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\f\u001a\u00020\rX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u001b\u001a\u0004\u0018\u00010\u001c8F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u0016\u0010\u001f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001c0 X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010&\u001a\u00020\"8F¢\u0006\u0006\u001a\u0004\b'\u0010(\u0082\u0001\u00042345¨\u00066"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/ImplicitReceiverValue;", "S", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirThisOwnerSymbol;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ImplicitValue;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ReceiverValue;", "Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;", "boundSymbol", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "originalType", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "mutable", Argument.Delimiters.none, "inaccessibleReceiverKind", "Lorg/jetbrains/kotlin/fir/expressions/InaccessibleReceiverKind;", "<init>", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirThisOwnerSymbol;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;ZLorg/jetbrains/kotlin/fir/expressions/InaccessibleReceiverKind;)V", "getBoundSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirThisOwnerSymbol;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirThisOwnerSymbol;", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "getScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "implicitScope", "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "getImplicitScope", "()Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "lazyImplicitScope", "Lkotlin/Lazy;", "computeOriginalExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "scope", "c", "(Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;)Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "receiverExpression", "getReceiverExpression", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "updateTypeFromSmartcast", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/ImplicitValue$ImplicitValueInternals;", "createSnapshot", "keepMutable", "withReplacedSessionOrNull", "newSession", "newScopeSession", "Lorg/jetbrains/kotlin/fir/scopes/DelicateScopeAPI;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ImplicitDispatchReceiverValue;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ImplicitExtensionReceiverValue;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ImplicitReceiverValueForScriptOrSnippet;", "Lorg/jetbrains/kotlin/fir/resolve/calls/InaccessibleImplicitReceiverValue;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class ImplicitReceiverValue<S extends FirThisOwnerSymbol<?>> extends ImplicitValue<S> implements SessionAndScopeSessionHolder, ReceiverValue {
    private final S boundSymbol;
    private final InaccessibleReceiverKind inaccessibleReceiverKind;
    private Lazy<? extends FirTypeScope> lazyImplicitScope;
    private final ScopeSession scopeSession;
    private final FirSession session;

    private ImplicitReceiverValue(S s, final ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2, FirSession firSession, ScopeSession scopeSession, boolean z, InaccessibleReceiverKind inaccessibleReceiverKind) {
        super(coneKotlinType, coneKotlinType2, z, null);
        this.boundSymbol = s;
        this.session = firSession;
        this.scopeSession = scopeSession;
        this.inaccessibleReceiverKind = inaccessibleReceiverKind;
        this.lazyImplicitScope = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: hl6
            public final Object invoke() {
                return ImplicitReceiverValue.b(this.b, coneKotlinType);
            }
        });
    }

    public static FirTypeScope b(ImplicitReceiverValue implicitReceiverValue, ConeKotlinType coneKotlinType) {
        return ScopeUtilsKt.scope(implicitReceiverValue, coneKotlinType, CallableCopyTypeCalculator.DoNothing.INSTANCE, FirResolvePhase.STATUS);
    }

    public static FirTypeScope c(ImplicitReceiverValue implicitReceiverValue, ConeKotlinType coneKotlinType) {
        return ScopeUtilsKt.scope(implicitReceiverValue, coneKotlinType, CallableCopyTypeCalculator.DoNothing.INSTANCE, FirResolvePhase.STATUS);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.ImplicitValue
    public FirExpression computeOriginalExpression() {
        return FirReceiversKt.receiverExpression(getBoundSymbol(), getOriginalType(), this.inaccessibleReceiverKind);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.ImplicitValue
    public abstract ImplicitReceiverValue<S> createSnapshot(boolean keepMutable);

    public final FirTypeScope getImplicitScope() {
        return (FirTypeScope) this.lazyImplicitScope.getValue();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.ReceiverValue
    public final FirExpression getReceiverExpression() {
        return computeExpression();
    }

    @Override // org.jetbrains.kotlin.fir.ScopeSessionHolder
    public ScopeSession getScopeSession() {
        return this.scopeSession;
    }

    @Override // org.jetbrains.kotlin.fir.SessionHolder
    public FirSession getSession() {
        return this.session;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.ReceiverValue
    public FirTypeScope scope(SessionAndScopeSessionHolder sessionAndScopeSessionHolder) {
        sessionAndScopeSessionHolder.getClass();
        return getImplicitScope();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.ImplicitValue
    @ImplicitValue.ImplicitValueInternals
    public void updateTypeFromSmartcast(final ConeKotlinType type) {
        type.getClass();
        super.updateTypeFromSmartcast(type);
        this.lazyImplicitScope = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: gl6
            public final Object invoke() {
                return ImplicitReceiverValue.c(this.b, type);
            }
        });
    }

    @DelicateScopeAPI
    public abstract ImplicitReceiverValue<S> withReplacedSessionOrNull(FirSession newSession, ScopeSession newScopeSession);

    @Override // org.jetbrains.kotlin.fir.resolve.calls.ImplicitValue
    public S getBoundSymbol() {
        return this.boundSymbol;
    }

    public /* synthetic */ ImplicitReceiverValue(FirThisOwnerSymbol firThisOwnerSymbol, ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2, FirSession firSession, ScopeSession scopeSession, boolean z, InaccessibleReceiverKind inaccessibleReceiverKind, DefaultConstructorMarker defaultConstructorMarker) {
        this(firThisOwnerSymbol, coneKotlinType, coneKotlinType2, firSession, scopeSession, z, inaccessibleReceiverKind);
    }

    public /* synthetic */ ImplicitReceiverValue(FirThisOwnerSymbol firThisOwnerSymbol, ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2, FirSession firSession, ScopeSession scopeSession, boolean z, InaccessibleReceiverKind inaccessibleReceiverKind, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(firThisOwnerSymbol, coneKotlinType, coneKotlinType2, firSession, scopeSession, z, (i & 64) != 0 ? null : inaccessibleReceiverKind, null);
    }
}
