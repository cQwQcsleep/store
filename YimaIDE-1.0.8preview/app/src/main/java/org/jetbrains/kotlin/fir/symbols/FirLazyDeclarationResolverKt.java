package org.jetbrains.kotlin.fir.symbols;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElementWithResolveState;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.synthetic.FirSyntheticProperty;
import org.jetbrains.kotlin.fir.declarations.synthetic.FirSyntheticPropertyAccessor;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.util.ArrayMapAccessor;
import org.jetbrains.kotlin.util.TypeRegistry;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\t\u001a\u00020\n*\u0006\u0012\u0002\b\u00030\u000b2\u0006\u0010\f\u001a\u00020\r\u001a\u0012\u0010\t\u001a\u00020\n*\u00020\u00072\u0006\u0010\f\u001a\u00020\r\u001a\u0016\u0010\u000e\u001a\u00020\n*\u0006\u0012\u0002\b\u00030\u000f2\u0006\u0010\f\u001a\u00020\r\u001a\u0012\u0010\u000e\u001a\u00020\n*\u00020\u00102\u0006\u0010\f\u001a\u00020\r\u001a\u0016\u0010\u0011\u001a\u00020\n*\u0006\u0012\u0002\b\u00030\u000b2\u0006\u0010\f\u001a\u00020\r\u001a\u0012\u0010\u0011\u001a\u00020\n*\u00020\u00072\u0006\u0010\f\u001a\u00020\r\u001a9\u0010\u0012\u001a\u00020\n*\u00020\u00072\u0006\u0010\f\u001a\u00020\r2#\u0010\u0013\u001a\u001f\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\n0\u0014¢\u0006\u0002\b\u0015H\u0002\"\u001f\u0010\u0000\u001a\u00020\u0001*\u00020\u00028FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00078BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\b¨\u0006\u0016"}, d2 = {"lazyDeclarationResolver", "Lorg/jetbrains/kotlin/fir/symbols/FirLazyDeclarationResolver;", "Lorg/jetbrains/kotlin/fir/FirSession;", "getLazyDeclarationResolver", "(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/symbols/FirLazyDeclarationResolver;", "lazyDeclarationResolver$delegate", "Lorg/jetbrains/kotlin/util/ArrayMapAccessor;", "Lorg/jetbrains/kotlin/fir/FirElementWithResolveState;", "(Lorg/jetbrains/kotlin/fir/FirElementWithResolveState;)Lorg/jetbrains/kotlin/fir/symbols/FirLazyDeclarationResolver;", "lazyResolveToPhase", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "toPhase", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "lazyResolveToPhaseWithCallableMembers", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "lazyResolveToPhaseRecursively", "invokeLazyResolveToPhase", "resolver", "Lkotlin/Function3;", "Lkotlin/ExtensionFunctionType;", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirLazyDeclarationResolverKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(FirLazyDeclarationResolverKt.class, "lazyDeclarationResolver", "getLazyDeclarationResolver(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/symbols/FirLazyDeclarationResolver;", 1)};
    private static final ArrayMapAccessor lazyDeclarationResolver$delegate = TypeRegistry.generateAccessor$default(FirSession.INSTANCE, Reflection.getOrCreateKotlinClass(FirLazyDeclarationResolver.class), (Object) null, 2, (Object) null);

    public static final FirLazyDeclarationResolver getLazyDeclarationResolver(FirSession firSession) {
        firSession.getClass();
        return (FirLazyDeclarationResolver) lazyDeclarationResolver$delegate.getValue(firSession, $$delegatedProperties[0]);
    }

    private static final void invokeLazyResolveToPhase(FirElementWithResolveState firElementWithResolveState, FirResolvePhase firResolvePhase, Function3<? super FirLazyDeclarationResolver, ? super FirElementWithResolveState, ? super FirResolvePhase, Unit> function3) {
        if (firElementWithResolveState instanceof FirSyntheticPropertyAccessor) {
            invokeLazyResolveToPhase(((FirSyntheticPropertyAccessor) firElementWithResolveState).getDelegate(), firResolvePhase, function3);
            return;
        }
        if (!(firElementWithResolveState instanceof FirSyntheticProperty)) {
            function3.invoke(getLazyDeclarationResolver(firElementWithResolveState), firElementWithResolveState, firResolvePhase);
            return;
        }
        FirSyntheticProperty firSyntheticProperty = (FirSyntheticProperty) firElementWithResolveState;
        invokeLazyResolveToPhase(firSyntheticProperty.getGetter(), firResolvePhase, function3);
        FirSyntheticPropertyAccessor setter = firSyntheticProperty.getSetter();
        if (setter != null) {
            invokeLazyResolveToPhase(setter, firResolvePhase, function3);
        }
    }

    public static final void lazyResolveToPhase(FirBasedSymbol<?> firBasedSymbol, FirResolvePhase firResolvePhase) {
        firBasedSymbol.getClass();
        firResolvePhase.getClass();
        lazyResolveToPhase(firBasedSymbol.getFir(), firResolvePhase);
    }

    public static final void lazyResolveToPhaseRecursively(FirBasedSymbol<?> firBasedSymbol, FirResolvePhase firResolvePhase) {
        firBasedSymbol.getClass();
        firResolvePhase.getClass();
        lazyResolveToPhaseRecursively(firBasedSymbol.getFir(), firResolvePhase);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final void lazyResolveToPhaseWithCallableMembers(FirClassSymbol<?> firClassSymbol, FirResolvePhase firResolvePhase) {
        firClassSymbol.getClass();
        firResolvePhase.getClass();
        lazyResolveToPhaseWithCallableMembers((FirClass) firClassSymbol.getFir(), firResolvePhase);
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt$lazyResolveToPhase$1, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function3<FirLazyDeclarationResolver, FirElementWithResolveState, FirResolvePhase, Unit> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(3, FirLazyDeclarationResolver.class, "lazyResolveToPhase", "lazyResolveToPhase(Lorg/jetbrains/kotlin/fir/FirElementWithResolveState;Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;)V", 0);
        }

        public final void invoke(FirLazyDeclarationResolver firLazyDeclarationResolver, FirElementWithResolveState firElementWithResolveState, FirResolvePhase firResolvePhase) {
            firLazyDeclarationResolver.getClass();
            firElementWithResolveState.getClass();
            firResolvePhase.getClass();
            firLazyDeclarationResolver.lazyResolveToPhase(firElementWithResolveState, firResolvePhase);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            invoke((FirLazyDeclarationResolver) obj, (FirElementWithResolveState) obj2, (FirResolvePhase) obj3);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt$lazyResolveToPhaseRecursively$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class C00761 extends FunctionReferenceImpl implements Function3<FirLazyDeclarationResolver, FirElementWithResolveState, FirResolvePhase, Unit> {
        public static final C00761 INSTANCE = new C00761();

        public C00761() {
            super(3, FirLazyDeclarationResolver.class, "lazyResolveToPhaseRecursively", "lazyResolveToPhaseRecursively(Lorg/jetbrains/kotlin/fir/FirElementWithResolveState;Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;)V", 0);
        }

        public final void invoke(FirLazyDeclarationResolver firLazyDeclarationResolver, FirElementWithResolveState firElementWithResolveState, FirResolvePhase firResolvePhase) {
            firLazyDeclarationResolver.getClass();
            firElementWithResolveState.getClass();
            firResolvePhase.getClass();
            firLazyDeclarationResolver.lazyResolveToPhaseRecursively(firElementWithResolveState, firResolvePhase);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            invoke((FirLazyDeclarationResolver) obj, (FirElementWithResolveState) obj2, (FirResolvePhase) obj3);
            return Unit.INSTANCE;
        }
    }

    public static final void lazyResolveToPhase(FirElementWithResolveState firElementWithResolveState, FirResolvePhase firResolvePhase) {
        firElementWithResolveState.getClass();
        firResolvePhase.getClass();
        invokeLazyResolveToPhase(firElementWithResolveState, firResolvePhase, AnonymousClass1.INSTANCE);
    }

    public static final void lazyResolveToPhaseRecursively(FirElementWithResolveState firElementWithResolveState, FirResolvePhase firResolvePhase) {
        firElementWithResolveState.getClass();
        firResolvePhase.getClass();
        invokeLazyResolveToPhase(firElementWithResolveState, firResolvePhase, C00761.INSTANCE);
    }

    public static final void lazyResolveToPhaseWithCallableMembers(FirClass firClass, FirResolvePhase firResolvePhase) {
        firClass.getClass();
        firResolvePhase.getClass();
        getLazyDeclarationResolver(firClass).lazyResolveToPhaseWithCallableMembers(firClass, firResolvePhase);
    }

    private static final FirLazyDeclarationResolver getLazyDeclarationResolver(FirElementWithResolveState firElementWithResolveState) {
        return getLazyDeclarationResolver(firElementWithResolveState.getModuleData().getSession());
    }
}
