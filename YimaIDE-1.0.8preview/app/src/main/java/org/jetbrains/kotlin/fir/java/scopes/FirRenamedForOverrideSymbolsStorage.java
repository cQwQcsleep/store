package org.jetbrains.kotlin.fir.java.scopes;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.FirSessionComponent;
import org.jetbrains.kotlin.fir.caches.FirCache;
import org.jetbrains.kotlin.fir.caches.FirCachesFactory;
import org.jetbrains.kotlin.fir.caches.FirCachesFactoryKt;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.java.declarations.FirJavaClass;
import org.jetbrains.kotlin.fir.java.scopes.FirRenamedForOverrideSymbolsStorage;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0002\u0019\u001aB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\u0011\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\u0004\u0010\bR/\u0010\t\u001a \u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u000e0\n¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R1\u0010\u0011\u001a\"\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f\u0012\u0004\u0012\u00020\f0\n¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R/\u0010\u0013\u001a \u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00140\n¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010R/\u0010\u0016\u001a \u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00170\n¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0010¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/scopes/FirRenamedForOverrideSymbolsStorage;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "cachesFactory", "Lorg/jetbrains/kotlin/fir/caches/FirCachesFactory;", "<init>", "(Lorg/jetbrains/kotlin/fir/caches/FirCachesFactory;)V", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "renamedFunctionsCache", "Lorg/jetbrains/kotlin/fir/caches/FirCache;", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "Lorg/jetbrains/kotlin/name/Name;", "Lorg/jetbrains/kotlin/fir/java/scopes/FirRenamedForOverrideSymbolsStorage$RenamedFunctionCreationContext;", "getRenamedFunctionsCache", "()Lorg/jetbrains/kotlin/fir/caches/FirCache;", "declaredFunctionCopyWithParameterTypesFromSupertypeCache", "getDeclaredFunctionCopyWithParameterTypesFromSupertypeCache", "accidentalOverrideWithDeclaredFunctionHiddenCopyIfDeclaredFunctionParametersAreErasedCache", "Lorg/jetbrains/kotlin/fir/java/scopes/FirRenamedForOverrideSymbolsStorage$AccidentalOverrideWithDeclaredFunctionHiddenCopyCreationContext;", "getAccidentalOverrideWithDeclaredFunctionHiddenCopyIfDeclaredFunctionParametersAreErasedCache", "accidentalOverrideWithDeclaredFunctionHiddenCopyIfInheritedFunctionParametersAreErasedCache", "Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaClass;", "getAccidentalOverrideWithDeclaredFunctionHiddenCopyIfInheritedFunctionParametersAreErasedCache", "RenamedFunctionCreationContext", "AccidentalOverrideWithDeclaredFunctionHiddenCopyCreationContext", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirRenamedForOverrideSymbolsStorage implements FirSessionComponent {
    private final FirCache<Pair<? extends FirNamedFunctionSymbol, Name>, FirNamedFunctionSymbol, AccidentalOverrideWithDeclaredFunctionHiddenCopyCreationContext> accidentalOverrideWithDeclaredFunctionHiddenCopyIfDeclaredFunctionParametersAreErasedCache;
    private final FirCache<Pair<? extends FirNamedFunctionSymbol, Name>, FirNamedFunctionSymbol, FirJavaClass> accidentalOverrideWithDeclaredFunctionHiddenCopyIfInheritedFunctionParametersAreErasedCache;
    private final FirCache<Pair<? extends FirNamedFunctionSymbol, Name>, FirNamedFunctionSymbol, FirNamedFunctionSymbol> declaredFunctionCopyWithParameterTypesFromSupertypeCache;
    private final FirCache<Pair<? extends FirNamedFunctionSymbol, Name>, FirNamedFunctionSymbol, RenamedFunctionCreationContext> renamedFunctionsCache;

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/scopes/FirRenamedForOverrideSymbolsStorage$AccidentalOverrideWithDeclaredFunctionHiddenCopyCreationContext;", Argument.Delimiters.none, "explicitlyDeclaredFunctionWithErasedValueParameters", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "klass", "Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaClass;", "<init>", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaClass;)V", "getExplicitlyDeclaredFunctionWithErasedValueParameters", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "getKlass", "()Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaClass;", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class AccidentalOverrideWithDeclaredFunctionHiddenCopyCreationContext {
        private final FirNamedFunctionSymbol explicitlyDeclaredFunctionWithErasedValueParameters;
        private final FirJavaClass klass;

        public AccidentalOverrideWithDeclaredFunctionHiddenCopyCreationContext(FirNamedFunctionSymbol firNamedFunctionSymbol, FirJavaClass firJavaClass) {
            firNamedFunctionSymbol.getClass();
            firJavaClass.getClass();
            this.explicitlyDeclaredFunctionWithErasedValueParameters = firNamedFunctionSymbol;
            this.klass = firJavaClass;
        }

        public static /* synthetic */ AccidentalOverrideWithDeclaredFunctionHiddenCopyCreationContext copy$default(AccidentalOverrideWithDeclaredFunctionHiddenCopyCreationContext accidentalOverrideWithDeclaredFunctionHiddenCopyCreationContext, FirNamedFunctionSymbol firNamedFunctionSymbol, FirJavaClass firJavaClass, int i, Object obj) {
            if ((i & 1) != 0) {
                firNamedFunctionSymbol = accidentalOverrideWithDeclaredFunctionHiddenCopyCreationContext.explicitlyDeclaredFunctionWithErasedValueParameters;
            }
            if ((i & 2) != 0) {
                firJavaClass = accidentalOverrideWithDeclaredFunctionHiddenCopyCreationContext.klass;
            }
            return accidentalOverrideWithDeclaredFunctionHiddenCopyCreationContext.copy(firNamedFunctionSymbol, firJavaClass);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final FirNamedFunctionSymbol getExplicitlyDeclaredFunctionWithErasedValueParameters() {
            return this.explicitlyDeclaredFunctionWithErasedValueParameters;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final FirJavaClass getKlass() {
            return this.klass;
        }

        public final AccidentalOverrideWithDeclaredFunctionHiddenCopyCreationContext copy(FirNamedFunctionSymbol explicitlyDeclaredFunctionWithErasedValueParameters, FirJavaClass klass) {
            explicitlyDeclaredFunctionWithErasedValueParameters.getClass();
            klass.getClass();
            return new AccidentalOverrideWithDeclaredFunctionHiddenCopyCreationContext(explicitlyDeclaredFunctionWithErasedValueParameters, klass);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AccidentalOverrideWithDeclaredFunctionHiddenCopyCreationContext)) {
                return false;
            }
            AccidentalOverrideWithDeclaredFunctionHiddenCopyCreationContext accidentalOverrideWithDeclaredFunctionHiddenCopyCreationContext = (AccidentalOverrideWithDeclaredFunctionHiddenCopyCreationContext) other;
            return Intrinsics.areEqual(this.explicitlyDeclaredFunctionWithErasedValueParameters, accidentalOverrideWithDeclaredFunctionHiddenCopyCreationContext.explicitlyDeclaredFunctionWithErasedValueParameters) && Intrinsics.areEqual(this.klass, accidentalOverrideWithDeclaredFunctionHiddenCopyCreationContext.klass);
        }

        public final FirNamedFunctionSymbol getExplicitlyDeclaredFunctionWithErasedValueParameters() {
            return this.explicitlyDeclaredFunctionWithErasedValueParameters;
        }

        public final FirJavaClass getKlass() {
            return this.klass;
        }

        public int hashCode() {
            return (this.explicitlyDeclaredFunctionWithErasedValueParameters.hashCode() * 31) + this.klass.hashCode();
        }

        public String toString() {
            return "AccidentalOverrideWithDeclaredFunctionHiddenCopyCreationContext(explicitlyDeclaredFunctionWithErasedValueParameters=" + this.explicitlyDeclaredFunctionWithErasedValueParameters + ", klass=" + this.klass + ')';
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0007HÆ\u0003J)\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0014\u0010\u0013\u001a\u00020\u00052\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/scopes/FirRenamedForOverrideSymbolsStorage$RenamedFunctionCreationContext;", Argument.Delimiters.none, "klass", "Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaClass;", "isHidden", Argument.Delimiters.none, "origin", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "<init>", "(Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaClass;ZLorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;)V", "getKlass", "()Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaClass;", "()Z", "getOrigin", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class RenamedFunctionCreationContext {
        private final boolean isHidden;
        private final FirJavaClass klass;
        private final FirDeclarationOrigin origin;

        public RenamedFunctionCreationContext(FirJavaClass firJavaClass, boolean z, FirDeclarationOrigin firDeclarationOrigin) {
            firJavaClass.getClass();
            this.klass = firJavaClass;
            this.isHidden = z;
            this.origin = firDeclarationOrigin;
        }

        public static /* synthetic */ RenamedFunctionCreationContext copy$default(RenamedFunctionCreationContext renamedFunctionCreationContext, FirJavaClass firJavaClass, boolean z, FirDeclarationOrigin firDeclarationOrigin, int i, Object obj) {
            if ((i & 1) != 0) {
                firJavaClass = renamedFunctionCreationContext.klass;
            }
            if ((i & 2) != 0) {
                z = renamedFunctionCreationContext.isHidden;
            }
            if ((i & 4) != 0) {
                firDeclarationOrigin = renamedFunctionCreationContext.origin;
            }
            return renamedFunctionCreationContext.copy(firJavaClass, z, firDeclarationOrigin);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final FirJavaClass getKlass() {
            return this.klass;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getIsHidden() {
            return this.isHidden;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final FirDeclarationOrigin getOrigin() {
            return this.origin;
        }

        public final RenamedFunctionCreationContext copy(FirJavaClass klass, boolean isHidden, FirDeclarationOrigin origin) {
            klass.getClass();
            return new RenamedFunctionCreationContext(klass, isHidden, origin);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof RenamedFunctionCreationContext)) {
                return false;
            }
            RenamedFunctionCreationContext renamedFunctionCreationContext = (RenamedFunctionCreationContext) other;
            return Intrinsics.areEqual(this.klass, renamedFunctionCreationContext.klass) && this.isHidden == renamedFunctionCreationContext.isHidden && Intrinsics.areEqual(this.origin, renamedFunctionCreationContext.origin);
        }

        public final FirJavaClass getKlass() {
            return this.klass;
        }

        public final FirDeclarationOrigin getOrigin() {
            return this.origin;
        }

        public int hashCode() {
            int iHashCode = ((this.klass.hashCode() * 31) + Boolean.hashCode(this.isHidden)) * 31;
            FirDeclarationOrigin firDeclarationOrigin = this.origin;
            return iHashCode + (firDeclarationOrigin == null ? 0 : firDeclarationOrigin.hashCode());
        }

        public final boolean isHidden() {
            return this.isHidden;
        }

        public String toString() {
            return "RenamedFunctionCreationContext(klass=" + this.klass + ", isHidden=" + this.isHidden + ", origin=" + this.origin + ')';
        }
    }

    public FirRenamedForOverrideSymbolsStorage(FirCachesFactory firCachesFactory) {
        firCachesFactory.getClass();
        this.renamedFunctionsCache = firCachesFactory.createCache(new Function2() { // from class: gc5
            public final Object invoke(Object obj, Object obj2) {
                return FirRenamedForOverrideSymbolsStorage.b((Pair) obj, (FirRenamedForOverrideSymbolsStorage.RenamedFunctionCreationContext) obj2);
            }
        });
        this.declaredFunctionCopyWithParameterTypesFromSupertypeCache = firCachesFactory.createCache(new Function2() { // from class: hc5
            public final Object invoke(Object obj, Object obj2) {
                return FirRenamedForOverrideSymbolsStorage.c((Pair) obj, (FirNamedFunctionSymbol) obj2);
            }
        });
        this.accidentalOverrideWithDeclaredFunctionHiddenCopyIfDeclaredFunctionParametersAreErasedCache = firCachesFactory.createCache(new Function2() { // from class: ic5
            public final Object invoke(Object obj, Object obj2) {
                return FirRenamedForOverrideSymbolsStorage.d((Pair) obj, (FirRenamedForOverrideSymbolsStorage.AccidentalOverrideWithDeclaredFunctionHiddenCopyCreationContext) obj2);
            }
        });
        this.accidentalOverrideWithDeclaredFunctionHiddenCopyIfInheritedFunctionParametersAreErasedCache = firCachesFactory.createCache(new Function2() { // from class: jc5
            public final Object invoke(Object obj, Object obj2) {
                return FirRenamedForOverrideSymbolsStorage.a((Pair) obj, (FirJavaClass) obj2);
            }
        });
    }

    public static FirNamedFunctionSymbol a(Pair pair, FirJavaClass firJavaClass) {
        pair.getClass();
        firJavaClass.getClass();
        return JavaClassUseSiteMemberScope.INSTANCE.createAccidentalOverrideWithDeclaredFunctionHiddenIfInheritedFunctionParametersAreErasedCopy((FirNamedFunctionSymbol) pair.component1(), (Name) pair.component2(), firJavaClass);
    }

    public static FirNamedFunctionSymbol b(Pair pair, RenamedFunctionCreationContext renamedFunctionCreationContext) {
        pair.getClass();
        renamedFunctionCreationContext.getClass();
        return JavaClassUseSiteMemberScope.INSTANCE.createCopyWithNaturalName((FirNamedFunctionSymbol) pair.component1(), (Name) pair.component2(), renamedFunctionCreationContext.getKlass(), renamedFunctionCreationContext.getIsHidden(), renamedFunctionCreationContext.getOrigin());
    }

    public static FirNamedFunctionSymbol c(Pair pair, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        pair.getClass();
        firNamedFunctionSymbol.getClass();
        return JavaClassUseSiteMemberScope.INSTANCE.createDeclaredFunctionCopyWithParameterTypesFromSupertype((FirNamedFunctionSymbol) pair.component1(), (Name) pair.component2(), firNamedFunctionSymbol);
    }

    public static FirNamedFunctionSymbol d(Pair pair, AccidentalOverrideWithDeclaredFunctionHiddenCopyCreationContext accidentalOverrideWithDeclaredFunctionHiddenCopyCreationContext) {
        pair.getClass();
        accidentalOverrideWithDeclaredFunctionHiddenCopyCreationContext.getClass();
        return JavaClassUseSiteMemberScope.INSTANCE.createAccidentalOverrideWithDeclaredFunctionHiddenIfDeclaredFunctionParametersAreErasedCopy((FirNamedFunctionSymbol) pair.component1(), (Name) pair.component2(), accidentalOverrideWithDeclaredFunctionHiddenCopyCreationContext.getExplicitlyDeclaredFunctionWithErasedValueParameters(), accidentalOverrideWithDeclaredFunctionHiddenCopyCreationContext.getKlass());
    }

    public final FirCache<Pair<? extends FirNamedFunctionSymbol, Name>, FirNamedFunctionSymbol, AccidentalOverrideWithDeclaredFunctionHiddenCopyCreationContext> getAccidentalOverrideWithDeclaredFunctionHiddenCopyIfDeclaredFunctionParametersAreErasedCache() {
        return this.accidentalOverrideWithDeclaredFunctionHiddenCopyIfDeclaredFunctionParametersAreErasedCache;
    }

    public final FirCache<Pair<? extends FirNamedFunctionSymbol, Name>, FirNamedFunctionSymbol, FirJavaClass> getAccidentalOverrideWithDeclaredFunctionHiddenCopyIfInheritedFunctionParametersAreErasedCache() {
        return this.accidentalOverrideWithDeclaredFunctionHiddenCopyIfInheritedFunctionParametersAreErasedCache;
    }

    public final FirCache<Pair<? extends FirNamedFunctionSymbol, Name>, FirNamedFunctionSymbol, FirNamedFunctionSymbol> getDeclaredFunctionCopyWithParameterTypesFromSupertypeCache() {
        return this.declaredFunctionCopyWithParameterTypesFromSupertypeCache;
    }

    public final FirCache<Pair<? extends FirNamedFunctionSymbol, Name>, FirNamedFunctionSymbol, RenamedFunctionCreationContext> getRenamedFunctionsCache() {
        return this.renamedFunctionsCache;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FirRenamedForOverrideSymbolsStorage(FirSession firSession) {
        this(FirCachesFactoryKt.getFirCachesFactory(firSession));
        firSession.getClass();
    }
}
