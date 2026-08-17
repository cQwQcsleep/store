package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactoryForDeprecation1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.resolve.SupertypeSupplier;
import org.jetbrains.kotlin.fir.resolve.SupertypeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.substitution.ChainedSubstitutorKt;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.symbols.SymbolInternals;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J1\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0017R\u00020\bR\u00020\nb\u0002\b\u000ej\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirAnnotationClassInheritanceChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirClassChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirClass;)V", "Lorg/jetbrains/kotlin/fir/symbols/SymbolInternals;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirAnnotationClassInheritanceChecker extends FirDeclarationChecker<FirClass> {
    public static final FirAnnotationClassInheritanceChecker INSTANCE = new FirAnnotationClassInheritanceChecker();

    private FirAnnotationClassInheritanceChecker() {
        super(MppCheckerKind.Common);
    }

    /* JADX WARN: Code duplicated, block: B:28:0x00de  */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    @SymbolInternals
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirClass firClass) {
        Collection<Pair> collectionEmptyList;
        ConeClassLikeType coneClassLikeTypeComputePartialExpansion;
        CheckerContext checkerContext2 = checkerContext;
        checkerContext2.getClass();
        diagnosticReporter.getClass();
        firClass.getClass();
        List<FirTypeRef> superTypeRefs = firClass.getSuperTypeRefs();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = superTypeRefs.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            FirTypeRef firTypeRef = (FirTypeRef) it.next();
            FirClassLikeSymbol<?> classLikeSymbol = ToSymbolUtilsKt.toClassLikeSymbol(checkerContext2, FirTypeUtilsKt.getConeType(firTypeRef));
            Pair pair = classLikeSymbol != null ? TuplesKt.to(classLikeSymbol, firTypeRef.getSource()) : null;
            if (pair != null) {
                arrayList.add(pair);
            }
        }
        Map mutableMap = MapsKt.toMutableMap(MapsKt.toMap(arrayList));
        FirClassSymbol<FirClass> symbol = firClass.getSymbol();
        SupertypeSupplier.Default r10 = SupertypeSupplier.Default.INSTANCE;
        FirSession session = checkerContext2.getSession();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        List listMutableListOf = CollectionsKt.mutableListOf(new Object[]{TuplesKt.to(symbol, ConeSubstitutor.Empty.INSTANCE)});
        while (!listMutableListOf.isEmpty()) {
            Pair pair2 = (Pair) AddToStdlibKt.popLast(listMutableListOf);
            if (linkedHashSet.add((FirClassLikeSymbol) pair2.component1())) {
                FirClassLikeSymbol firClassLikeSymbol = (FirClassLikeSymbol) pair2.component1();
                ConeSubstitutor coneSubstitutor = (ConeSubstitutor) pair2.component2();
                if (firClassLikeSymbol instanceof FirClassSymbol) {
                    List<ConeClassLikeType> listForClass = r10.forClass((FirClass) ((FirClassSymbol) firClassLikeSymbol).getFir(), session);
                    ArrayList<ConeClassLikeType> arrayList2 = new ArrayList();
                    Iterator<T> it2 = listForClass.iterator();
                    while (it2.hasNext()) {
                        ConeClassLikeType coneClassLikeTypeComputePartialExpansion2 = SupertypeUtilsKt.computePartialExpansion((ConeClassLikeType) it2.next(), session, r10);
                        if (coneClassLikeTypeComputePartialExpansion2 != null) {
                            arrayList2.add(coneClassLikeTypeComputePartialExpansion2);
                        }
                    }
                    Iterator it3 = arrayList2.iterator();
                    while (it3.hasNext()) {
                        ConeKotlinType coneKotlinTypeSubstituteOrSelf = coneSubstitutor.substituteOrSelf((ConeClassLikeType) it3.next());
                        coneKotlinTypeSubstituteOrSelf.getClass();
                        FirClassLikeSymbol<?> classLikeSymbol2 = ToSymbolUtilsKt.toClassLikeSymbol(checkerContext2, (ConeClassLikeType) coneKotlinTypeSubstituteOrSelf);
                        if (classLikeSymbol2 != null) {
                            mutableMap.putIfAbsent(classLikeSymbol2, mutableMap.get(firClassLikeSymbol));
                            if ((classLikeSymbol2 instanceof FirRegularClassSymbol) && ((FirRegularClassSymbol) classLikeSymbol2).getClassKind() == ClassKind.ANNOTATION_CLASS) {
                                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter, (AbstractKtSourceElement) (Intrinsics.areEqual(firClassLikeSymbol, firClass.getSymbol()) ? (KtSourceElement) mutableMap.get(classLikeSymbol2) : firClass.getSource()), (KtDiagnosticFactoryForDeprecation1) FirErrors.INSTANCE.getEXTENDING_AN_ANNOTATION_CLASS(), (Object) classLikeSymbol2, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                            }
                        }
                        checkerContext2 = checkerContext;
                        arrayList2 = arrayList2;
                    }
                    collectionEmptyList = new ArrayList();
                    for (ConeClassLikeType coneClassLikeType : arrayList2) {
                        Pair pair3 = coneClassLikeType instanceof ConeErrorType ? null : TuplesKt.to(coneClassLikeType, ChainedSubstitutorKt.chain(SupertypeUtilsKt.createSubstitutionForSupertype(coneClassLikeType, session), coneSubstitutor));
                        if (pair3 != null) {
                            collectionEmptyList.add(pair3);
                        }
                    }
                } else if (!(firClassLikeSymbol instanceof FirTypeAliasSymbol)) {
                    bu8.a();
                    return;
                } else {
                    ConeClassLikeType coneClassLikeTypeExpansionForTypeAlias = r10.expansionForTypeAlias((FirTypeAlias) ((FirTypeAliasSymbol) firClassLikeSymbol).getFir(), session);
                    collectionEmptyList = (coneClassLikeTypeExpansionForTypeAlias == null || (coneClassLikeTypeComputePartialExpansion = SupertypeUtilsKt.computePartialExpansion(coneClassLikeTypeExpansionForTypeAlias, session, r10)) == null) ? CollectionsKt.emptyList() : CollectionsKt.listOf(TuplesKt.to(coneClassLikeTypeComputePartialExpansion, coneSubstitutor));
                }
                for (Pair pair4 : collectionEmptyList) {
                    ConeClassLikeType coneClassLikeType2 = (ConeClassLikeType) pair4.component1();
                    ConeSubstitutor coneSubstitutor2 = (ConeSubstitutor) pair4.component2();
                    FirClassLikeSymbol<?> symbol2 = ToSymbolUtilsKt.toSymbol(coneClassLikeType2.getLookupTag(), session);
                    Pair pair5 = symbol2 != null ? TuplesKt.to(symbol2, coneSubstitutor2) : null;
                    if (pair5 != null) {
                        listMutableListOf.add(pair5);
                    }
                }
            }
            checkerContext2 = checkerContext;
        }
    }
}
