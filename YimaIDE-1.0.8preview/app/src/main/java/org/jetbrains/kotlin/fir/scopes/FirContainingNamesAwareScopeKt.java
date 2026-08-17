package org.jetbrains.kotlin.fir.scopes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0002\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004\u001a\"\u0010\u0006\u001a\u00020\u0001*\u00020\u00022\u0016\u0010\u0003\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0007\u0012\u0004\u0012\u00020\u00010\u0004\u001a\"\u0010\b\u001a\u00020\u0001*\u00020\u00022\u0016\u0010\u0003\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t\u0012\u0004\u0012\u00020\u00010\u0004\u001a\"\u0010\n\u001a\u00020\u0001*\u00020\u00022\u0016\u0010\u0003\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b\u0012\u0004\u0012\u00020\u00010\u0004\u001a\u0014\u0010\f\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\r*\u00020\u0002\u001a\u0010\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\r*\u00020\u0002¨\u0006\u000f"}, d2 = {"processAllFunctions", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/scopes/FirContainingNamesAwareScope;", "processor", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "processAllProperties", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "processAllCallables", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "processAllClassifiers", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassifierSymbol;", "collectAllProperties", Argument.Delimiters.none, "collectAllFunctions", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirContainingNamesAwareScopeKt {
    public static final Collection<FirNamedFunctionSymbol> collectAllFunctions(FirContainingNamesAwareScope firContainingNamesAwareScope) {
        firContainingNamesAwareScope.getClass();
        ArrayList arrayList = new ArrayList();
        processAllFunctions(firContainingNamesAwareScope, new FirContainingNamesAwareScopeKt$collectAllFunctions$1$1(arrayList));
        return arrayList;
    }

    public static final Collection<FirVariableSymbol<?>> collectAllProperties(FirContainingNamesAwareScope firContainingNamesAwareScope) {
        firContainingNamesAwareScope.getClass();
        ArrayList arrayList = new ArrayList();
        processAllProperties(firContainingNamesAwareScope, new FirContainingNamesAwareScopeKt$collectAllProperties$1$1(arrayList));
        return arrayList;
    }

    public static final void processAllCallables(FirContainingNamesAwareScope firContainingNamesAwareScope, Function1<? super FirCallableSymbol<?>, Unit> function1) {
        firContainingNamesAwareScope.getClass();
        function1.getClass();
        for (Name name : firContainingNamesAwareScope.getCallableNames()) {
            firContainingNamesAwareScope.processFunctionsByName(name, function1);
            firContainingNamesAwareScope.processPropertiesByName(name, function1);
        }
    }

    public static final void processAllClassifiers(FirContainingNamesAwareScope firContainingNamesAwareScope, Function1<? super FirClassifierSymbol<?>, Unit> function1) {
        firContainingNamesAwareScope.getClass();
        function1.getClass();
        Iterator<Name> it = firContainingNamesAwareScope.getClassifierNames().iterator();
        while (it.hasNext()) {
            firContainingNamesAwareScope.processClassifiersByNameWithSubstitution(it.next(), new FirScopeKt.AnonymousClass1(function1));
        }
    }

    public static final void processAllFunctions(FirContainingNamesAwareScope firContainingNamesAwareScope, Function1<? super FirNamedFunctionSymbol, Unit> function1) {
        firContainingNamesAwareScope.getClass();
        function1.getClass();
        Iterator<Name> it = firContainingNamesAwareScope.getCallableNames().iterator();
        while (it.hasNext()) {
            firContainingNamesAwareScope.processFunctionsByName(it.next(), function1);
        }
    }

    public static final void processAllProperties(FirContainingNamesAwareScope firContainingNamesAwareScope, Function1<? super FirVariableSymbol<?>, Unit> function1) {
        firContainingNamesAwareScope.getClass();
        function1.getClass();
        Iterator<Name> it = firContainingNamesAwareScope.getCallableNames().iterator();
        while (it.hasNext()) {
            firContainingNamesAwareScope.processPropertiesByName(it.next(), function1);
        }
    }
}
