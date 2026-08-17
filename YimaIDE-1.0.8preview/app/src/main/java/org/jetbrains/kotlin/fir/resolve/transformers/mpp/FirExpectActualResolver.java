package org.jetbrains.kotlin.fir.resolve.transformers.mpp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.fir.FirExpectActualMatchingContext;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.ExpectActualAttributesKt;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.utils.FirSymbolStatusUtilsKt;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderKt;
import org.jetbrains.kotlin.fir.resolve.transformers.mpp.FirExpectActualResolver;
import org.jetbrains.kotlin.fir.scopes.impl.FirPackageMemberScope;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.utils.exceptions.FirExceptionUtilsKt;
import org.jetbrains.kotlin.mpp.CallableSymbolMarker;
import org.jetbrains.kotlin.mpp.ClassLikeSymbolMarker;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.resolve.calls.mpp.AbstractExpectActualMatcher;
import org.jetbrains.kotlin.resolve.multiplatform.ExpectActualMatchingCompatibility;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J<\u0010\u0004\u001a\u001c\u0012\u0004\u0012\u00020\u0006\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b0\u00070\u0005j\u0002`\t2\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/mpp/FirExpectActualResolver;", Argument.Delimiters.none, "<init>", "()V", "findExpectForActual", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualMatchingCompatibility;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "Lorg/jetbrains/kotlin/fir/declarations/ExpectForActualMatchingData;", "actualSymbol", "useSiteSession", "Lorg/jetbrains/kotlin/fir/FirSession;", "context", "Lorg/jetbrains/kotlin/fir/FirExpectActualMatchingContext;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirExpectActualResolver {
    public static final FirExpectActualResolver INSTANCE = new FirExpectActualResolver();

    private FirExpectActualResolver() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit findExpectForActual$lambda$0$2$0(List list, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        list.add(firNamedFunctionSymbol);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit findExpectForActual$lambda$0$2$1(List list, FirVariableSymbol firVariableSymbol) {
        firVariableSymbol.getClass();
        list.add(firVariableSymbol);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    public final Map<ExpectActualMatchingCompatibility, List<FirBasedSymbol<?>>> findExpectForActual(FirBasedSymbol<?> actualSymbol, FirSession useSiteSession, FirExpectActualMatchingContext context) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirExpectActualMatchingContext firExpectActualMatchingContext;
        Collection collectionFilterContainedInTheFirstWaveOfDependsOnDominatorTree;
        FirRegularClassSymbol firRegularClassSymbol;
        FirRegularClassSymbol firRegularClassSymbol2;
        FirRegularClass firRegularClass;
        Map<ExpectActualMatchingCompatibility, List<FirBasedSymbol<?>>> expectForActual;
        List<FirBasedSymbol<?>> list;
        actualSymbol.getClass();
        useSiteSession.getClass();
        context.getClass();
        if (!(actualSymbol instanceof FirCallableSymbol)) {
            if (!(actualSymbol instanceof FirClassLikeSymbol)) {
                return MapsKt.emptyMap();
            }
            FirClassLikeSymbol firClassLikeSymbol = (FirClassLikeSymbol) actualSymbol;
            List<FirModuleData> allDependsOnDependencies = firClassLikeSymbol.getModuleData().getAllDependsOnDependencies();
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = allDependsOnDependencies.iterator();
            while (it.hasNext()) {
                FirClassLikeSymbol<?> classLikeSymbolByClassId = FirSymbolProviderKt.getSymbolProvider(((FirModuleData) it.next()).getSession()).getClassLikeSymbolByClassId(firClassLikeSymbol.getClassId());
                if (classLikeSymbolByClassId != null) {
                    arrayList.add(classLikeSymbolByClassId);
                }
            }
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : arrayList) {
                FirClassLikeSymbol firClassLikeSymbol2 = (FirClassLikeSymbol) obj;
                if (firClassLikeSymbol2.getRawStatus().isExpect() && allDependsOnDependencies.contains(firClassLikeSymbol2.getModuleData())) {
                    arrayList2.add(obj);
                }
            }
            ArrayList arrayList3 = new ArrayList();
            for (Object obj2 : arrayList2) {
                if (obj2 instanceof FirRegularClassSymbol) {
                    arrayList3.add(obj2);
                }
            }
            List listFilterContainedInTheFirstWaveOfDependsOnDominatorTree = FirExpectActualResolverKt.filterContainedInTheFirstWaveOfDependsOnDominatorTree(CollectionsKt.distinct(arrayList3), firClassLikeSymbol.getModuleData());
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Object obj3 : listFilterContainedInTheFirstWaveOfDependsOnDominatorTree) {
                ExpectActualMatchingCompatibility expectActualMatchingCompatibilityMatchClassifiers = AbstractExpectActualMatcher.INSTANCE.matchClassifiers((FirRegularClassSymbol) obj3, (ClassLikeSymbolMarker) actualSymbol, context);
                Object arrayList4 = linkedHashMap.get(expectActualMatchingCompatibilityMatchClassifiers);
                if (arrayList4 == null) {
                    arrayList4 = new ArrayList();
                    linkedHashMap.put(expectActualMatchingCompatibilityMatchClassifiers, arrayList4);
                }
                ((List) arrayList4).add(obj3);
            }
            return linkedHashMap;
        }
        FirCallableSymbol firCallableSymbol = (FirCallableSymbol) actualSymbol;
        CallableId callableId = firCallableSymbol.getCallableId();
        Collection staticCallablesForExpectClass = null;
        if (callableId == null) {
            KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Symbol without callableId passed to expect/actual resolver", (Throwable) null);
            ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
            FirExceptionUtilsKt.withFirSymbolEntry(exceptionAttachmentBuilder, "symbol", actualSymbol);
            kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
            throw kotlinIllegalArgumentExceptionWithAttachments;
        }
        ClassId classId = callableId.getClassId();
        if (firCallableSymbol.isLocal()) {
            return MapsKt.emptyMap();
        }
        if (classId != null) {
            FirClassLikeSymbol<?> classLikeSymbolByClassId2 = FirSymbolProviderKt.getSymbolProvider(useSiteSession).getClassLikeSymbolByClassId(classId);
            FirRegularClassSymbol firRegularClassSymbolFullyExpandedClass = classLikeSymbolByClassId2 != null ? DeclarationUtilsKt.fullyExpandedClass(classLikeSymbolByClassId2, useSiteSession) : null;
            FirBasedSymbol firBasedSymbol = (firRegularClassSymbolFullyExpandedClass == null || (firRegularClass = (FirRegularClass) firRegularClassSymbolFullyExpandedClass.getFir()) == null || (expectForActual = ExpectActualAttributesKt.getExpectForActual(firRegularClass)) == null || (list = expectForActual.get(ExpectActualMatchingCompatibility.MatchedSuccessfully.INSTANCE)) == null) ? null : (FirBasedSymbol) CollectionsKt.singleOrNull(list);
            FirRegularClassSymbol firRegularClassSymbol3 = firBasedSymbol instanceof FirRegularClassSymbol ? (FirRegularClassSymbol) firBasedSymbol : null;
            if (!(actualSymbol instanceof FirConstructorSymbol)) {
                firExpectActualMatchingContext = context;
                staticCallablesForExpectClass = firCallableSymbol.getRawStatus().isStatic() ? firRegularClassSymbol3 != null ? firExpectActualMatchingContext.getStaticCallablesForExpectClass(firRegularClassSymbol3, firCallableSymbol.getName()) : null : firRegularClassSymbol3 != null ? firExpectActualMatchingContext.getCallablesForExpectClass(firRegularClassSymbol3, firCallableSymbol.getName()) : null;
            } else if (firRegularClassSymbol3 != null) {
                firExpectActualMatchingContext = context;
                staticCallablesForExpectClass = FirExpectActualMatchingContext.getConstructors$default(firExpectActualMatchingContext, firRegularClassSymbol3, context.getExpectScopeSession(), null, 2, null);
            } else {
                firExpectActualMatchingContext = context;
            }
            if (staticCallablesForExpectClass == null) {
                staticCallablesForExpectClass = CollectionsKt.emptyList();
            }
            collectionFilterContainedInTheFirstWaveOfDependsOnDominatorTree = new ArrayList();
            for (Object obj4 : staticCallablesForExpectClass) {
                FirCallableSymbol firCallableSymbol2 = (FirCallableSymbol) obj4;
                if (!firExpectActualMatchingContext.isFakeOverride(firCallableSymbol2, firRegularClassSymbol3) || !Intrinsics.areEqual(firExpectActualMatchingContext.getVisibility(firCallableSymbol2), Visibilities.Private.INSTANCE)) {
                    collectionFilterContainedInTheFirstWaveOfDependsOnDominatorTree.add(obj4);
                }
            }
            firRegularClassSymbol2 = firRegularClassSymbolFullyExpandedClass;
            firRegularClassSymbol = firRegularClassSymbol3;
        } else {
            firExpectActualMatchingContext = context;
            List<FirModuleData> allDependsOnDependencies2 = firCallableSymbol.getModuleData().getAllDependsOnDependencies();
            FirPackageMemberScope firPackageMemberScope = new FirPackageMemberScope(callableId.getPackageName(), useSiteSession, FirSymbolProviderKt.getDependenciesSymbolProvider(useSiteSession), null, 8, null);
            final ArrayList arrayList5 = new ArrayList();
            firPackageMemberScope.processFunctionsByName(callableId.getCallableName(), new Function1() { // from class: j65
                public final Object invoke(Object obj5) {
                    return FirExpectActualResolver.findExpectForActual$lambda$0$2$0(arrayList5, (FirNamedFunctionSymbol) obj5);
                }
            });
            firPackageMemberScope.processPropertiesByName(callableId.getCallableName(), new Function1() { // from class: k65
                public final Object invoke(Object obj5) {
                    return FirExpectActualResolver.findExpectForActual$lambda$0$2$1(arrayList5, (FirVariableSymbol) obj5);
                }
            });
            ArrayList arrayList6 = new ArrayList();
            for (Object obj5 : arrayList5) {
                FirCallableSymbol firCallableSymbol3 = (FirCallableSymbol) obj5;
                if (firExpectActualMatchingContext.isExpect(firCallableSymbol3) && allDependsOnDependencies2.contains(firCallableSymbol3.getModuleData()) && FirSymbolStatusUtilsKt.isCompanionExtension(firCallableSymbol3) == FirSymbolStatusUtilsKt.isCompanionExtension(firCallableSymbol)) {
                    arrayList6.add(obj5);
                }
            }
            collectionFilterContainedInTheFirstWaveOfDependsOnDominatorTree = FirExpectActualResolverKt.filterContainedInTheFirstWaveOfDependsOnDominatorTree(arrayList6, firCallableSymbol.getModuleData());
            firRegularClassSymbol = null;
            firRegularClassSymbol2 = null;
        }
        ArrayList arrayList7 = new ArrayList();
        for (Object obj6 : collectionFilterContainedInTheFirstWaveOfDependsOnDominatorTree) {
            if (!Intrinsics.areEqual(actualSymbol, (FirCallableSymbol) obj6)) {
                arrayList7.add(obj6);
            }
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (Object obj7 : arrayList7) {
            ExpectActualMatchingCompatibility callablesMatchingCompatibility = AbstractExpectActualMatcher.INSTANCE.getCallablesMatchingCompatibility((FirCallableSymbol) obj7, (CallableSymbolMarker) actualSymbol, firRegularClassSymbol, firRegularClassSymbol2, firExpectActualMatchingContext);
            Object arrayList8 = linkedHashMap2.get(callablesMatchingCompatibility);
            if (arrayList8 == null) {
                arrayList8 = new ArrayList();
                linkedHashMap2.put(callablesMatchingCompatibility, arrayList8);
            }
            ((List) arrayList8).add(obj7);
        }
        ExpectActualMatchingCompatibility.MatchedSuccessfully matchedSuccessfully = ExpectActualMatchingCompatibility.MatchedSuccessfully.INSTANCE;
        List list2 = (List) linkedHashMap2.get(matchedSuccessfully);
        return list2 == null ? linkedHashMap2 : MapsKt.mapOf(TuplesKt.to(matchedSuccessfully, list2));
    }
}
