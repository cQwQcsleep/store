package org.jetbrains.kotlin.fir.symbols;

import java.util.function.Supplier;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElementWithResolveState;
import org.jetbrains.kotlin.fir.FirSessionComponent;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolver;
import org.jetbrains.kotlin.util.PrivateForInline;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H&J\u0010\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H&J\u0006\u0010\u0017\u001a\u00020\u0013J%\u0010\u0018\u001a\u0002H\u0019\"\u0004\b\u0000\u0010\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00190\u001bH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u001cJ%\u0010\u001d\u001a\u0002H\u0019\"\u0004\b\u0000\u0010\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00190\u001bH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u001cJ\b\u0010\u001e\u001a\u00020\u0013H\u0004J\u0018\u0010\u001f\u001a\u00020\u00132\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u0015H&J\u0018\u0010#\u001a\u00020\u00132\u0006\u0010$\u001a\u00020%2\u0006\u0010\"\u001a\u00020\u0015H&J\u0018\u0010&\u001a\u00020\u00132\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u0015H&R&\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004r\u0002\b\n¢\u0006\u000e\n\u0000\u0012\u0004\b\u0007\u0010\u0003\u001a\u0004\b\b\u0010\tR\u0017\u0010\u000b\u001a\u00020\u00068F¢\u0006\f\u0012\u0004\b\f\u0010\u0003\u001a\u0004\b\r\u0010\u000eR&\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004r\u0002\b\n¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\u0003\u001a\u0004\b\u0011\u0010\t\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006'"}, d2 = {"Lorg/jetbrains/kotlin/fir/symbols/FirLazyDeclarationResolver;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "<init>", "()V", "_lazyResolveContractChecksEnabled", "Ljava/lang/ThreadLocal;", Argument.Delimiters.none, "get_lazyResolveContractChecksEnabled$annotations", "get_lazyResolveContractChecksEnabled", "()Ljava/lang/ThreadLocal;", "Lorg/jetbrains/kotlin/util/PrivateForInline;", "lazyResolveContractChecksEnabled", "getLazyResolveContractChecksEnabled$annotations", "getLazyResolveContractChecksEnabled", "()Z", "_lazyResolveIsAllowed", "get_lazyResolveIsAllowed$annotations", "get_lazyResolveIsAllowed", "startResolvingPhase", Argument.Delimiters.none, "phase", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "finishResolvingPhase", "disableLazyResolveContractChecks", "disableLazyResolveContractChecksInside", "T", "action", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "forbidLazyResolveInside", "assertLazyResolveAllowed", "lazyResolveToPhase", "element", "Lorg/jetbrains/kotlin/fir/FirElementWithResolveState;", "toPhase", "lazyResolveToPhaseWithCallableMembers", "clazz", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "lazyResolveToPhaseRecursively", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirLazyDeclarationResolver implements FirSessionComponent {
    private final ThreadLocal<Boolean> _lazyResolveContractChecksEnabled;
    private final ThreadLocal<Boolean> _lazyResolveIsAllowed;

    public FirLazyDeclarationResolver() {
        ThreadLocal<Boolean> threadLocalWithInitial = ThreadLocal.withInitial(new Supplier() { // from class: ja5
            @Override // java.util.function.Supplier
            public final Object get() {
                return FirLazyDeclarationResolver.b();
            }
        });
        threadLocalWithInitial.getClass();
        this._lazyResolveContractChecksEnabled = threadLocalWithInitial;
        ThreadLocal<Boolean> threadLocalWithInitial2 = ThreadLocal.withInitial(new Supplier() { // from class: ka5
            @Override // java.util.function.Supplier
            public final Object get() {
                return FirLazyDeclarationResolver.a();
            }
        });
        threadLocalWithInitial2.getClass();
        this._lazyResolveIsAllowed = threadLocalWithInitial2;
    }

    public static Boolean a() {
        return Boolean.TRUE;
    }

    public static Boolean b() {
        return Boolean.TRUE;
    }

    public static /* synthetic */ void getLazyResolveContractChecksEnabled$annotations() {
    }

    @PrivateForInline
    public static /* synthetic */ void get_lazyResolveContractChecksEnabled$annotations() {
    }

    @PrivateForInline
    public static /* synthetic */ void get_lazyResolveIsAllowed$annotations() {
    }

    public final void assertLazyResolveAllowed() {
        if (!this._lazyResolveIsAllowed.get().booleanValue()) {
            throw new FirLazyResolveForbiddenException();
        }
    }

    public final void disableLazyResolveContractChecks() {
        this._lazyResolveContractChecksEnabled.set(Boolean.FALSE);
    }

    public final <T> T disableLazyResolveContractChecksInside(Function0<? extends T> action) {
        action.getClass();
        Boolean bool = get_lazyResolveContractChecksEnabled().get();
        get_lazyResolveContractChecksEnabled().set(Boolean.FALSE);
        try {
            return (T) action.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            get_lazyResolveContractChecksEnabled().set(bool);
            InlineMarker.finallyEnd(1);
        }
    }

    public abstract void finishResolvingPhase(FirResolvePhase phase);

    public final <T> T forbidLazyResolveInside(Function0<? extends T> action) {
        action.getClass();
        Boolean bool = get_lazyResolveIsAllowed().get();
        get_lazyResolveIsAllowed().set(Boolean.FALSE);
        try {
            return (T) action.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            get_lazyResolveIsAllowed().set(bool);
            InlineMarker.finallyEnd(1);
        }
    }

    public final boolean getLazyResolveContractChecksEnabled() {
        Boolean bool = this._lazyResolveContractChecksEnabled.get();
        bool.getClass();
        return bool.booleanValue();
    }

    public final ThreadLocal<Boolean> get_lazyResolveContractChecksEnabled() {
        return this._lazyResolveContractChecksEnabled;
    }

    public final ThreadLocal<Boolean> get_lazyResolveIsAllowed() {
        return this._lazyResolveIsAllowed;
    }

    public abstract void lazyResolveToPhase(FirElementWithResolveState element, FirResolvePhase toPhase);

    public abstract void lazyResolveToPhaseRecursively(FirElementWithResolveState element, FirResolvePhase toPhase);

    public abstract void lazyResolveToPhaseWithCallableMembers(FirClass clazz, FirResolvePhase toPhase);

    public abstract void startResolvingPhase(FirResolvePhase phase);
}
