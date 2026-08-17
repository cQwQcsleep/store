package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirOuterClassTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeCyclicTypeBound;
import org.jetbrains.kotlin.fir.symbols.ConeTypeParameterLookupTag;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType;
import org.jetbrains.kotlin.fir.types.ConeTypesKt;
import org.jetbrains.kotlin.fir.types.FirErrorTypeRef;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ\u0012\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f*\u00020\u0011H\u0002J\u0012\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f*\u00020\u0010H\u0002J\u0016\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00110\u000f2\u0006\u0010\u0014\u001a\u00020\u0010H\u0002J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0016\u001a\u00020\u0017H\u0002¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirCyclicTypeBoundsChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirBasicDeclarationChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)V", "originalBounds", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "unwrapBound", "extractTypeParamSymbols", "ref", "extractTypeParamSymbol", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirCyclicTypeBoundsChecker extends FirDeclarationChecker<FirDeclaration> {
    public static final FirCyclicTypeBoundsChecker INSTANCE = new FirCyclicTypeBoundsChecker();

    private FirCyclicTypeBoundsChecker() {
        super(MppCheckerKind.Common);
    }

    private static final void check$findCycles(Set<FirTypeParameterSymbol> set, List<FirTypeParameterSymbol> list, List<List<FirTypeParameterSymbol>> list2, FirTypeParameterSymbol firTypeParameterSymbol) {
        if (set.add(firTypeParameterSymbol)) {
            list.add(firTypeParameterSymbol);
            List<FirResolvedTypeRef> resolvedBounds = firTypeParameterSymbol.getResolvedBounds();
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = resolvedBounds.iterator();
            while (it.hasNext()) {
                CollectionsKt.addAll(arrayList, INSTANCE.extractTypeParamSymbols((FirResolvedTypeRef) it.next()));
            }
            Iterator it2 = CollectionsKt.toSet(arrayList).iterator();
            while (it2.hasNext()) {
                check$findCycles(set, list, list2, (FirTypeParameterSymbol) it2.next());
            }
            list.remove(list.size() - 1);
            return;
        }
        if (list.isEmpty()) {
            return;
        }
        ArrayList arrayList2 = new ArrayList();
        boolean z = false;
        for (Object obj : list) {
            if (z) {
                arrayList2.add(obj);
            } else if (Intrinsics.areEqual((FirTypeParameterSymbol) obj, firTypeParameterSymbol)) {
                arrayList2.add(obj);
                z = true;
            }
        }
        list2.add(arrayList2);
    }

    private final FirTypeParameterSymbol extractTypeParamSymbol(ConeKotlinType type) {
        ConeTypeParameterLookupTag lookupTag;
        ConeSimpleKotlinType coneSimpleKotlinTypeUnwrapToSimpleTypeUsingLowerBound = ConeTypesKt.unwrapToSimpleTypeUsingLowerBound(type);
        ConeTypeParameterType coneTypeParameterType = coneSimpleKotlinTypeUnwrapToSimpleTypeUsingLowerBound instanceof ConeTypeParameterType ? (ConeTypeParameterType) coneSimpleKotlinTypeUnwrapToSimpleTypeUsingLowerBound : null;
        if (coneTypeParameterType == null || (lookupTag = coneTypeParameterType.getLookupTag()) == null) {
            return null;
        }
        return lookupTag.getTypeParameterSymbol();
    }

    private final List<FirTypeParameterSymbol> extractTypeParamSymbols(FirTypeRef ref) {
        List<FirTypeRef> listUnwrapBound = unwrapBound(ref);
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = listUnwrapBound.iterator();
        while (it.hasNext()) {
            FirTypeParameterSymbol firTypeParameterSymbolExtractTypeParamSymbol = INSTANCE.extractTypeParamSymbol(FirTypeUtilsKt.getConeType((FirTypeRef) it.next()));
            if (firTypeParameterSymbolExtractTypeParamSymbol != null) {
                arrayList.add(firTypeParameterSymbolExtractTypeParamSymbol);
            }
        }
        return arrayList;
    }

    private final List<FirTypeRef> originalBounds(FirTypeParameterSymbol firTypeParameterSymbol) {
        List<FirResolvedTypeRef> resolvedBounds = firTypeParameterSymbol.getResolvedBounds();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = resolvedBounds.iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(arrayList, INSTANCE.unwrapBound((FirResolvedTypeRef) it.next()));
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final List<FirTypeRef> unwrapBound(FirTypeRef firTypeRef) {
        if (firTypeRef instanceof FirErrorTypeRef) {
            FirErrorTypeRef firErrorTypeRef = (FirErrorTypeRef) firTypeRef;
            if (firErrorTypeRef.getDiagnostic() instanceof ConeCyclicTypeBound) {
                ConeDiagnostic diagnostic = firErrorTypeRef.getDiagnostic();
                diagnostic.getClass();
                return ((ConeCyclicTypeBound) diagnostic).getBounds();
            }
        }
        return CollectionsKt.listOf(firTypeRef);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration) {
        Collection collectionListOf;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firDeclaration.getClass();
        if (!(firDeclaration instanceof FirMemberDeclaration) || (firDeclaration instanceof FirConstructor) || (firDeclaration instanceof FirTypeAlias)) {
            return;
        }
        List<FirTypeParameterRef> typeParameters = ((FirMemberDeclaration) firDeclaration).getTypeParameters();
        ArrayList arrayList = new ArrayList();
        for (Object obj : typeParameters) {
            if (!(((FirTypeParameterRef) obj) instanceof FirOuterClassTypeParameterRef)) {
                arrayList.add(obj);
            }
        }
        if (arrayList.isEmpty()) {
            arrayList = null;
        }
        if (arrayList == null) {
            return;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        ArrayList<List> arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            check$findCycles(linkedHashSet, arrayList3, arrayList2, ((FirTypeParameterRef) it.next()).getSymbol());
        }
        for (List<FirTypeParameterSymbol> list : arrayList2) {
            for (FirTypeParameterSymbol firTypeParameterSymbol : list) {
                if (firDeclaration instanceof FirRegularClass) {
                    List<FirTypeRef> listOriginalBounds = originalBounds(firTypeParameterSymbol);
                    ArrayList arrayList4 = new ArrayList();
                    for (Object obj2 : listOriginalBounds) {
                        if (CollectionsKt.contains(list, INSTANCE.extractTypeParamSymbol(FirTypeUtilsKt.getConeType((FirTypeRef) obj2)))) {
                            arrayList4.add(obj2);
                        }
                    }
                    collectionListOf = new ArrayList();
                    Iterator it2 = arrayList4.iterator();
                    while (it2.hasNext()) {
                        KtSourceElement source = ((FirTypeRef) it2.next()).getSource();
                        if (source != null) {
                            collectionListOf.add(source);
                        }
                    }
                } else {
                    collectionListOf = CollectionsKt.listOf(firTypeParameterSymbol.getSource());
                }
                Iterator it3 = collectionListOf.iterator();
                while (it3.hasNext()) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) it3.next(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getCYCLIC_GENERIC_UPPER_BOUND(), (Object) list, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                }
            }
        }
    }
}
