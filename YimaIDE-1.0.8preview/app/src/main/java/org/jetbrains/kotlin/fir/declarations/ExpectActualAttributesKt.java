package org.jetbrains.kotlin.fir.declarations;

import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.symbols.SymbolInternals;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.resolve.multiplatform.ExpectActualMatchingCompatibility;
import org.jetbrains.kotlin.util.ArrayMapAccessor;
import org.jetbrains.kotlin.util.TypeRegistry;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000V\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aB\u0010\u0012\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0013*\u0006\u0012\u0002\b\u00030\u0013H\u0007b*\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0016\u0012\u001c\b\u0017\u0012\u0018\b\u000bB\u0014\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0006\b\u001b\u0012\u0002\b\f\u001a\u0014\u0010\u001c\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0013*\u0006\u0012\u0002\b\u00030\u0013\u001a\u0014\u0010\u001c\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0004*\u0006\u0012\u0002\b\u00030\u0004\"u\u0010\u0007\u001a \u0012\u0004\u0012\u00020\u0002\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0003\u0018\u00010\u0001j\u0004\u0018\u0001`\u0006*\u00020\b2$\u0010\u0005\u001a \u0012\u0004\u0012\u00020\u0002\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0003\u0018\u00010\u0001j\u0004\u0018\u0001`\u00068F@FX\u0087\u008e\u0002r\u0002\b\u0011¢\u0006\u0018\n\u0004\b\u000f\u0010\u0010\u0012\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e\"7\u0010\u0007\u001a \u0012\u0004\u0012\u00020\u0002\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0003\u0018\u00010\u0001j\u0004\u0018\u0001`\u0006*\u0006\u0012\u0002\b\u00030\u00048F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u001d\"\u001f\u0010\"\u001a\u00020#*\u00020$8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b'\u0010(\u001a\u0004\b%\u0010&*6\u0010\u0000\"\u0018\u0012\u0004\u0012\u00020\u0002\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u00030\u00012\u0018\u0012\u0004\u0012\u00020\u0002\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u00030\u0001*b\u0010\u001e\".\u0012\u0014\u0012\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u0004\u0012\u00020!0 \u0012\u0014\u0012\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u0004\u0012\u00020\u00020\u001f0\u001f2.\u0012\u0014\u0012\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u0004\u0012\u00020!0 \u0012\u0014\u0012\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u0004\u0012\u00020\u00020\u001f0\u001f¨\u0006)"}, d2 = {"ExpectForActualMatchingData", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualMatchingCompatibility;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "<set-?>", "Lorg/jetbrains/kotlin/fir/declarations/ExpectForActualMatchingData;", "expectForActual", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "getExpectForActual$annotations", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)V", "getExpectForActual", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)Ljava/util/Map;", "setExpectForActual", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;Ljava/util/Map;)V", "expectForActual$delegate", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationDataRegistry$DeclarationDataAccessor;", "Lorg/jetbrains/kotlin/fir/symbols/SymbolInternals;", "getSingleExpectForActualOrNull", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;", "Lkotlin/Deprecated;", "message", "Use getSingleMatchedExpectForActualOrNull instead", "replaceWith", "Lkotlin/ReplaceWith;", "expression", "getSingleMatchedExpectForActualOrNull()", "imports", "getSingleMatchedExpectForActualOrNull", "(Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;)Ljava/util/Map;", "MemberExpectForActualData", "Ljava/util/concurrent/ConcurrentMap;", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "expectActualMappingStorage", "Lorg/jetbrains/kotlin/fir/declarations/FirExpectActualMappingStorage;", "Lorg/jetbrains/kotlin/fir/FirSession;", "getExpectActualMappingStorage", "(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/declarations/FirExpectActualMappingStorage;", "expectActualMappingStorage$delegate", "Lorg/jetbrains/kotlin/util/ArrayMapAccessor;", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ExpectActualAttributesKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new MutablePropertyReference1Impl<>(ExpectActualAttributesKt.class, "expectForActual", "getExpectForActual(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)Ljava/util/Map;", 1), new PropertyReference1Impl<>(ExpectActualAttributesKt.class, "expectActualMappingStorage", "getExpectActualMappingStorage(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/declarations/FirExpectActualMappingStorage;", 1)};
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor expectForActual$delegate = FirDeclarationDataRegistry.INSTANCE.data(ExpectForActualAttributeKey.INSTANCE);
    private static final ArrayMapAccessor expectActualMappingStorage$delegate = TypeRegistry.generateAccessor$default(FirSession.INSTANCE, Reflection.getOrCreateKotlinClass(FirExpectActualMappingStorage.class), (Object) null, 2, (Object) null);

    public static final FirExpectActualMappingStorage getExpectActualMappingStorage(FirSession firSession) {
        firSession.getClass();
        return (FirExpectActualMappingStorage) expectActualMappingStorage$delegate.getValue(firSession, $$delegatedProperties[1]);
    }

    public static final Map<ExpectActualMatchingCompatibility, List<FirBasedSymbol<?>>> getExpectForActual(FirDeclaration firDeclaration) {
        firDeclaration.getClass();
        return (Map) expectForActual$delegate.getValue(firDeclaration, $$delegatedProperties[0]);
    }

    @SymbolInternals
    public static /* synthetic */ void getExpectForActual$annotations(FirDeclaration firDeclaration) {
    }

    @Deprecated(message = "Use getSingleMatchedExpectForActualOrNull instead", replaceWith = @ReplaceWith(expression = "getSingleMatchedExpectForActualOrNull()", imports = {}))
    public static final FirFunctionSymbol<?> getSingleExpectForActualOrNull(FirFunctionSymbol<?> firFunctionSymbol) {
        firFunctionSymbol.getClass();
        return getSingleMatchedExpectForActualOrNull(firFunctionSymbol);
    }

    public static final FirBasedSymbol<?> getSingleMatchedExpectForActualOrNull(FirBasedSymbol<?> firBasedSymbol) {
        List<FirBasedSymbol<?>> list;
        firBasedSymbol.getClass();
        Map<ExpectActualMatchingCompatibility, List<FirBasedSymbol<?>>> expectForActual = getExpectForActual(firBasedSymbol);
        if (expectForActual == null || (list = expectForActual.get(ExpectActualMatchingCompatibility.MatchedSuccessfully.INSTANCE)) == null) {
            return null;
        }
        return (FirBasedSymbol) CollectionsKt.singleOrNull(list);
    }

    public static final void setExpectForActual(FirDeclaration firDeclaration, Map<ExpectActualMatchingCompatibility, ? extends List<? extends FirBasedSymbol<?>>> map) {
        firDeclaration.getClass();
        expectForActual$delegate.setValue(firDeclaration, $$delegatedProperties[0], map);
    }

    public static final Map<ExpectActualMatchingCompatibility, List<FirBasedSymbol<?>>> getExpectForActual(FirBasedSymbol<?> firBasedSymbol) {
        firBasedSymbol.getClass();
        FirLazyDeclarationResolverKt.lazyResolveToPhase(firBasedSymbol, FirResolvePhase.EXPECT_ACTUAL_MATCHING);
        return getExpectForActual(firBasedSymbol.getFir());
    }

    public static final FirFunctionSymbol<?> getSingleMatchedExpectForActualOrNull(FirFunctionSymbol<?> firFunctionSymbol) {
        firFunctionSymbol.getClass();
        FirBasedSymbol<?> singleMatchedExpectForActualOrNull = getSingleMatchedExpectForActualOrNull((FirBasedSymbol<?>) firFunctionSymbol);
        if (singleMatchedExpectForActualOrNull instanceof FirFunctionSymbol) {
            return (FirFunctionSymbol) singleMatchedExpectForActualOrNull;
        }
        return null;
    }
}
