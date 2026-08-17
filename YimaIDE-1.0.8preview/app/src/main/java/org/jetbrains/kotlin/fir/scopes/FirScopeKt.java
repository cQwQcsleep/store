package org.jetbrains.kotlin.fir.scopes;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000N\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0018\u0010\u0000\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u001c\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00010\u0006*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0018\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0006*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u001c\u0010\t\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\u0006*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0010\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0006*\u00020\u0002\u001a,\u0010\r\u001a\u00020\u000e*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\b2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u000e0\u0012H\u0007b\u0002\b\u0013\u001a2\u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u000f0\u00062\u0006\u0010\u0016\u001a\u00020\u00172\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u000e0\u0012H\u0007b\u0002\b\u0013\u001a2\u0010\u0018\u001a\u00020\u0015*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0018\b\b\u0010\u0011\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0001\u0012\u0004\u0012\u00020\u00150\u0012H\u0086\bø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0019"}, d2 = {"getSingleClassifier", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassifierSymbol;", "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "getClassifiers", Argument.Delimiters.none, "getFunctions", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "getProperties", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "getDeclaredConstructors", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirConstructorSymbol;", "processOverriddenFunctionsAndSelf", "Lorg/jetbrains/kotlin/fir/scopes/ProcessorAction;", "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "functionSymbol", "processor", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/scopes/ScopeFunctionRequiresPrewarm;", "processOverriddenPropertiesAndSelf", Argument.Delimiters.none, "propertySymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "processClassifiersByName", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirScopeKt {
    public static final List<FirClassifierSymbol<?>> getClassifiers(FirScope firScope, Name name) {
        firScope.getClass();
        name.getClass();
        ArrayList arrayList = new ArrayList();
        firScope.processClassifiersByNameWithSubstitution(name, new AnonymousClass1(new FirScopeKt$getClassifiers$1$1(arrayList)));
        return arrayList;
    }

    public static final List<FirConstructorSymbol> getDeclaredConstructors(FirScope firScope) {
        firScope.getClass();
        ArrayList arrayList = new ArrayList();
        firScope.processDeclaredConstructors(new FirScopeKt$getDeclaredConstructors$1$1(arrayList));
        return arrayList;
    }

    public static final List<FirNamedFunctionSymbol> getFunctions(FirScope firScope, Name name) {
        firScope.getClass();
        name.getClass();
        ArrayList arrayList = new ArrayList();
        firScope.processFunctionsByName(name, new FirScopeKt$getFunctions$1$1(arrayList));
        return arrayList;
    }

    public static final List<FirVariableSymbol<?>> getProperties(FirScope firScope, Name name) {
        firScope.getClass();
        name.getClass();
        ArrayList arrayList = new ArrayList();
        firScope.processPropertiesByName(name, new FirScopeKt$getProperties$1$1(arrayList));
        return arrayList;
    }

    public static final FirClassifierSymbol<?> getSingleClassifier(FirScope firScope, Name name) {
        firScope.getClass();
        name.getClass();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        firScope.processClassifiersByNameWithSubstitution(name, new AnonymousClass1(new FirScopeKt$getSingleClassifier$1$1(linkedHashSet)));
        return (FirClassifierSymbol) CollectionsKt.singleOrNull(linkedHashSet);
    }

    public static final void processClassifiersByName(FirScope firScope, Name name, Function1<? super FirClassifierSymbol<?>, Unit> function1) {
        firScope.getClass();
        name.getClass();
        function1.getClass();
        firScope.processClassifiersByNameWithSubstitution(name, new AnonymousClass1(function1));
    }

    @ScopeFunctionRequiresPrewarm
    public static final ProcessorAction processOverriddenFunctionsAndSelf(FirTypeScope firTypeScope, FirNamedFunctionSymbol firNamedFunctionSymbol, Function1<? super FirNamedFunctionSymbol, ? extends ProcessorAction> function1) {
        firTypeScope.getClass();
        firNamedFunctionSymbol.getClass();
        function1.getClass();
        return ((ProcessorAction) function1.invoke(firNamedFunctionSymbol)).not() ? ProcessorAction.STOP : FirTypeScopeKt.processOverriddenFunctions(firTypeScope, firNamedFunctionSymbol, function1);
    }

    @ScopeFunctionRequiresPrewarm
    public static final void processOverriddenPropertiesAndSelf(List<? extends FirTypeScope> list, FirPropertySymbol firPropertySymbol, Function1<? super FirPropertySymbol, ? extends ProcessorAction> function1) {
        list.getClass();
        firPropertySymbol.getClass();
        function1.getClass();
        if (((ProcessorAction) function1.invoke(firPropertySymbol)).not()) {
            return;
        }
        FirTypeScopeKt.processOverriddenProperties(list, firPropertySymbol, function1);
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.scopes.FirScopeKt$processClassifiersByName$1, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 176)
    public static final class AnonymousClass1 implements Function2<FirClassifierSymbol<?>, ConeSubstitutor, Unit> {
        final /* synthetic */ Function1<FirClassifierSymbol<?>, Unit> $processor;

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(Function1<? super FirClassifierSymbol<?>, Unit> function1) {
            this.$processor = function1;
        }

        public final void invoke(FirClassifierSymbol<?> firClassifierSymbol, ConeSubstitutor coneSubstitutor) {
            firClassifierSymbol.getClass();
            coneSubstitutor.getClass();
            this.$processor.invoke(firClassifierSymbol);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((FirClassifierSymbol<?>) obj, (ConeSubstitutor) obj2);
            return Unit.INSTANCE;
        }
    }
}
