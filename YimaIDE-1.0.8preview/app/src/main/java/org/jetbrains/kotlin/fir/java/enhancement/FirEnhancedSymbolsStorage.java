package org.jetbrains.kotlin.fir.java.enhancement;

import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.functions.Function2;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.FirSessionComponent;
import org.jetbrains.kotlin.fir.caches.FirCache;
import org.jetbrains.kotlin.fir.caches.FirCachesFactory;
import org.jetbrains.kotlin.fir.caches.FirCachesFactoryKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.java.enhancement.FirEnhancedSymbolsStorage;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0002\u0010\u0011B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\u0011\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\u0004\u0010\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R%\u0010\t\u001a\u0016\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f\u0012\u0006\u0012\u0004\u0018\u00010\r0\n¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/enhancement/FirEnhancedSymbolsStorage;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "cachesFactory", "Lorg/jetbrains/kotlin/fir/caches/FirCachesFactory;", "<init>", "(Lorg/jetbrains/kotlin/fir/caches/FirCachesFactory;)V", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "cacheByOwner", "Lorg/jetbrains/kotlin/fir/caches/FirCache;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "Lorg/jetbrains/kotlin/fir/java/enhancement/FirEnhancedSymbolsStorage$EnhancementSymbolsCache;", Argument.Delimiters.none, "getCacheByOwner", "()Lorg/jetbrains/kotlin/fir/caches/FirCache;", "FunctionEnhancementContext", "EnhancementSymbolsCache", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirEnhancedSymbolsStorage implements FirSessionComponent {
    private final FirCache cacheByOwner;
    private final FirCachesFactory cachesFactory;

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R1\u0010\u0006\u001a\u001c\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u000e\n\u0000\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR=\u0010\u000e\u001a(\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000f\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u00100\u0007¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\u000b\u001a\u0004\b\u0014\u0010\r¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/enhancement/FirEnhancedSymbolsStorage$EnhancementSymbolsCache;", Argument.Delimiters.none, "cachesFactory", "Lorg/jetbrains/kotlin/fir/caches/FirCachesFactory;", "<init>", "(Lorg/jetbrains/kotlin/fir/caches/FirCachesFactory;)V", "enhancedFunctions", "Lorg/jetbrains/kotlin/fir/caches/FirCache;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;", "Lorg/jetbrains/kotlin/fir/java/enhancement/FirEnhancedSymbolsStorage$FunctionEnhancementContext;", "getEnhancedFunctions$annotations", "()V", "getEnhancedFunctions", "()Lorg/jetbrains/kotlin/fir/caches/FirCache;", "enhancedVariables", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/fir/java/enhancement/FirSignatureEnhancement;", "Lorg/jetbrains/kotlin/name/Name;", "getEnhancedVariables$annotations", "getEnhancedVariables", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class EnhancementSymbolsCache {
        private final FirCache<FirFunctionSymbol<?>, FirFunctionSymbol<?>, FunctionEnhancementContext> enhancedFunctions;
        private final FirCache<FirVariableSymbol<?>, FirVariableSymbol<?>, Pair<FirSignatureEnhancement, Name>> enhancedVariables;

        public EnhancementSymbolsCache(FirCachesFactory firCachesFactory) {
            firCachesFactory.getClass();
            this.enhancedFunctions = firCachesFactory.createCache(new Function2() { // from class: m55
                public final Object invoke(Object obj, Object obj2) {
                    return FirEnhancedSymbolsStorage.EnhancementSymbolsCache.b((FirFunctionSymbol) obj, (FirEnhancedSymbolsStorage.FunctionEnhancementContext) obj2);
                }
            });
            this.enhancedVariables = firCachesFactory.createCache(new Function2() { // from class: n55
                public final Object invoke(Object obj, Object obj2) {
                    return FirEnhancedSymbolsStorage.EnhancementSymbolsCache.a((FirVariableSymbol) obj, (Pair) obj2);
                }
            });
        }

        public static FirVariableSymbol a(FirVariableSymbol firVariableSymbol, Pair pair) {
            firVariableSymbol.getClass();
            pair.getClass();
            return ((FirSignatureEnhancement) pair.component1()).enhance$org_jetbrains_kotlin_fir_jvm(firVariableSymbol, (Name) pair.component2());
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
        public static FirFunctionSymbol b(FirFunctionSymbol firFunctionSymbol, FunctionEnhancementContext functionEnhancementContext) throws KotlinIllegalArgumentExceptionWithAttachments {
            firFunctionSymbol.getClass();
            functionEnhancementContext.getClass();
            FirFunctionSymbol<?> firFunctionSymbolEnhance$org_jetbrains_kotlin_fir_jvm = functionEnhancementContext.getEnhancement().enhance$org_jetbrains_kotlin_fir_jvm(firFunctionSymbol, functionEnhancementContext.getName(), functionEnhancementContext.getPrecomputedOverridden());
            FirFunction firFunction = (FirFunction) firFunctionSymbolEnhance$org_jetbrains_kotlin_fir_jvm.getFir();
            FirNamedFunctionSymbol initialSignatureAttr = ClassMembersKt.getInitialSignatureAttr(firFunction);
            if (initialSignatureAttr != null) {
                ClassMembersKt.setInitialSignatureAttr(firFunction, FirSignatureEnhancement.enhancedFunction$default(functionEnhancementContext.getEnhancement(), initialSignatureAttr, initialSignatureAttr.getName(), null, 4, null));
            }
            return firFunctionSymbolEnhance$org_jetbrains_kotlin_fir_jvm;
        }

        public static /* synthetic */ void getEnhancedFunctions$annotations() {
        }

        public static /* synthetic */ void getEnhancedVariables$annotations() {
        }

        public final FirCache<FirFunctionSymbol<?>, FirFunctionSymbol<?>, FunctionEnhancementContext> getEnhancedFunctions() {
            return this.enhancedFunctions;
        }

        public final FirCache<FirVariableSymbol<?>, FirVariableSymbol<?>, Pair<FirSignatureEnhancement, Name>> getEnhancedVariables() {
            return this.enhancedVariables;
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0019\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/enhancement/FirEnhancedSymbolsStorage$FunctionEnhancementContext;", Argument.Delimiters.none, "enhancement", "Lorg/jetbrains/kotlin/fir/java/enhancement/FirSignatureEnhancement;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "precomputedOverridden", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "<init>", "(Lorg/jetbrains/kotlin/fir/java/enhancement/FirSignatureEnhancement;Lorg/jetbrains/kotlin/name/Name;Ljava/util/List;)V", "getEnhancement", "()Lorg/jetbrains/kotlin/fir/java/enhancement/FirSignatureEnhancement;", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "getPrecomputedOverridden", "()Ljava/util/List;", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class FunctionEnhancementContext {
        private final FirSignatureEnhancement enhancement;
        private final Name name;
        private final List<FirCallableDeclaration> precomputedOverridden;

        /* JADX WARN: Multi-variable type inference failed */
        public FunctionEnhancementContext(FirSignatureEnhancement firSignatureEnhancement, Name name, List<? extends FirCallableDeclaration> list) {
            firSignatureEnhancement.getClass();
            this.enhancement = firSignatureEnhancement;
            this.name = name;
            this.precomputedOverridden = list;
        }

        public final FirSignatureEnhancement getEnhancement() {
            return this.enhancement;
        }

        public final Name getName() {
            return this.name;
        }

        public final List<FirCallableDeclaration> getPrecomputedOverridden() {
            return this.precomputedOverridden;
        }
    }

    public FirEnhancedSymbolsStorage(FirCachesFactory firCachesFactory) {
        firCachesFactory.getClass();
        this.cachesFactory = firCachesFactory;
        this.cacheByOwner = firCachesFactory.createCache(new Function2() { // from class: org.jetbrains.kotlin.fir.java.enhancement.FirEnhancedSymbolsStorage$special$$inlined$createCache$1
            public final FirEnhancedSymbolsStorage.EnhancementSymbolsCache invoke(FirRegularClassSymbol firRegularClassSymbol, Void r2) {
                firRegularClassSymbol.getClass();
                return new FirEnhancedSymbolsStorage.EnhancementSymbolsCache(this.this$0.cachesFactory);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                return invoke((FirRegularClassSymbol) obj, (Void) obj2);
            }
        });
    }

    public final FirCache getCacheByOwner() {
        return this.cacheByOwner;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FirEnhancedSymbolsStorage(FirSession firSession) {
        this(FirCachesFactoryKt.getFirCachesFactory(firSession));
        firSession.getClass();
    }
}
