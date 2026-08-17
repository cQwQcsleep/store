package org.jetbrains.kotlin.fir.analysis.p001native.checkers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.backend.p002native.interop.FirObjCInteropKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a!\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u0004H\u0002R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0005\u001a\u001c\u0010\u0006\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00042\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0002¨\u0006\b"}, d2 = {"isInheritedFromObjc", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;)Z", "hasDifferentParameterNames", "other", "org.jetbrains.kotlin:checkers.native"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirNativeObjcOverrideApplicabilityCheckerKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean hasDifferentParameterNames(FirFunctionSymbol<?> firFunctionSymbol, FirFunctionSymbol<?> firFunctionSymbol2) {
        List listDrop = CollectionsKt.drop(firFunctionSymbol.getValueParameterSymbols(), 1);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listDrop, 10));
        Iterator it = listDrop.iterator();
        while (it.hasNext()) {
            arrayList.add(((FirValueParameterSymbol) it.next()).getName());
        }
        List listDrop2 = CollectionsKt.drop(firFunctionSymbol2.getValueParameterSymbols(), 1);
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(listDrop2, 10));
        Iterator it2 = listDrop2.iterator();
        while (it2.hasNext()) {
            arrayList2.add(((FirValueParameterSymbol) it2.next()).getName());
        }
        return !Intrinsics.areEqual(arrayList, arrayList2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isInheritedFromObjc(CheckerContext checkerContext, FirFunctionSymbol<?> firFunctionSymbol) {
        return FirObjCInteropKt.getObjCMethodInfoFromOverriddenFunctions(firFunctionSymbol, checkerContext.getSession(), checkerContext.getScopeSession()) != null;
    }
}
