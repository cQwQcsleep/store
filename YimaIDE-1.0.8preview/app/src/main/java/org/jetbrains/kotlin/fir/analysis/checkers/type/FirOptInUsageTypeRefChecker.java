package org.jetbrains.kotlin.fir.analysis.checkers.type;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtRealSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirOptInUsageBaseChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.types.AbbreviatedTypeAttributeKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirQualifierPart;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirUserTypeRef;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.resolve.checkers.OptInNames;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ/\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f*\u0006\u0012\u0002\b\u00030\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0014JD\u0010\u0015\u001a\u00020\u0007*\u0006\u0012\u0002\b\u00030\u00112\u0006\u0010\u0016\u001a\u00020\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019H\u0082\u0010R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u001b¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirOptInUsageTypeRefChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirTypeChecker;", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirResolvedTypeRefChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "typeRef", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;)V", "loadClassifierExperimentalities", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirOptInUsageBaseChecker$Experimentality;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "isSupertypeRef", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;Z)Ljava/util/Set;", "checkContainingClasses", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "qualifier", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/FirQualifierPart;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;Lorg/jetbrains/kotlin/KtSourceElement;Ljava/util/List;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirOptInUsageTypeRefChecker extends FirTypeChecker<FirResolvedTypeRef> {
    public static final FirOptInUsageTypeRefChecker INSTANCE = new FirOptInUsageTypeRefChecker();

    private FirOptInUsageTypeRefChecker() {
        super(MppCheckerKind.Common);
    }

    private final void checkContainingClasses(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirClassLikeSymbol<?> firClassLikeSymbol, KtSourceElement ktSourceElement, List<? extends FirQualifierPart> list) {
        CheckerContext checkerContext2;
        DiagnosticReporter diagnosticReporter2;
        KtSourceElement ktSourceElement2;
        while (true) {
            ConeClassLikeLookupTag containingClassLookupTag = ClassMembersKt.getContainingClassLookupTag(firClassLikeSymbol);
            if (containingClassLookupTag == null || (firClassLikeSymbol = ToSymbolUtilsKt.toSymbol((SessionHolder) checkerContext, containingClassLookupTag)) == null) {
                return;
            }
            List<? extends FirQualifierPart> list2 = list;
            if (!(list2 instanceof Collection) || !list2.isEmpty()) {
                Iterator<T> it = list2.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (Intrinsics.areEqual(((FirQualifierPart) it.next()).getName(), firClassLikeSymbol.getName())) {
                            if (FirOptInUsageBaseChecker.INSTANCE.isExperimentalMarker(firClassLikeSymbol, checkerContext.getSession())) {
                                checkerContext2 = checkerContext;
                                diagnosticReporter2 = diagnosticReporter;
                                ktSourceElement2 = ktSourceElement;
                                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter2, (AbstractKtSourceElement) ktSourceElement2, FirErrors.INSTANCE.getOPT_IN_MARKER_CAN_ONLY_BE_USED_AS_ANNOTATION_OR_ARGUMENT_IN_OPT_IN(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                                break;
                            }
                        }
                    }
                    checkerContext2 = checkerContext;
                    diagnosticReporter2 = diagnosticReporter;
                    ktSourceElement2 = ktSourceElement;
                    break;
                }
            }
            checkerContext2 = checkerContext;
            diagnosticReporter2 = diagnosticReporter;
            ktSourceElement2 = ktSourceElement;
            break;
            break;
            checkerContext = checkerContext2;
            diagnosticReporter = diagnosticReporter2;
            ktSourceElement = ktSourceElement2;
        }
    }

    private final Set<FirOptInUsageBaseChecker.Experimentality> loadClassifierExperimentalities(CheckerContext checkerContext, FirClassLikeSymbol<?> firClassLikeSymbol, boolean z) {
        FirOptInUsageBaseChecker firOptInUsageBaseChecker = FirOptInUsageBaseChecker.INSTANCE;
        return z ? firOptInUsageBaseChecker.loadExperimentalitiesFromSupertype(checkerContext, firClassLikeSymbol) : firOptInUsageBaseChecker.loadExperimentalities(checkerContext, firClassLikeSymbol, false);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.type.FirTypeChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirResolvedTypeRef firResolvedTypeRef) {
        FirClassLikeSymbol<?> symbol;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firResolvedTypeRef.getClass();
        KtSourceElement source = firResolvedTypeRef.getSource();
        FirUserTypeRef delegatedTypeRef = firResolvedTypeRef.getDelegatedTypeRef();
        if ((source != null ? source.getKind() : null) instanceof KtRealSourceElementKind) {
            ConeRigidType coneRigidTypeLowerBoundIfFlexible = ConeTypeUtilsKt.lowerBoundIfFlexible(TypeExpansionUtilsKt.fullyExpandedType(checkerContext, firResolvedTypeRef.getConeType()));
            ConeClassLikeType coneClassLikeType = coneRigidTypeLowerBoundIfFlexible instanceof ConeClassLikeType ? (ConeClassLikeType) coneRigidTypeLowerBoundIfFlexible : null;
            if (coneClassLikeType == null) {
                return;
            }
            ConeKotlinType abbreviatedTypeOrSelf = AbbreviatedTypeAttributeKt.getAbbreviatedTypeOrSelf(coneClassLikeType);
            ConeClassLikeType coneClassLikeType2 = abbreviatedTypeOrSelf instanceof ConeClassLikeType ? (ConeClassLikeType) abbreviatedTypeOrSelf : null;
            if (coneClassLikeType2 == null || (symbol = ToSymbolUtilsKt.toSymbol((SessionHolder) checkerContext, coneClassLikeType2)) == null) {
                return;
            }
            if (!AbbreviatedTypeAttributeKt.isTypealiasExpansion(coneClassLikeType)) {
                coneClassLikeType = null;
            }
            FirClassLikeSymbol<?> symbol2 = coneClassLikeType != null ? ToSymbolUtilsKt.toSymbol((SessionHolder) checkerContext, coneClassLikeType) : null;
            FirClassLikeSymbol<?> firClassLikeSymbol = symbol2 == null ? symbol : symbol2;
            ClassId classId = firClassLikeSymbol.getClassId();
            Object objLastOrNull = CollectionsKt.lastOrNull(checkerContext.getCallsOrAssignments());
            FirAnnotation firAnnotation = objLastOrNull instanceof FirAnnotation ? (FirAnnotation) objLastOrNull : null;
            if (firAnnotation == null || firAnnotation.getAnnotationTypeRef() != firResolvedTypeRef) {
                OptInNames optInNames = OptInNames.INSTANCE;
                if (Intrinsics.areEqual(classId, optInNames.getREQUIRES_OPT_IN_CLASS_ID()) || Intrinsics.areEqual(classId, optInNames.getOPT_IN_CLASS_ID())) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, FirErrors.INSTANCE.getOPT_IN_CAN_ONLY_BE_USED_AS_ANNOTATION(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                } else if (FirOptInUsageBaseChecker.INSTANCE.isExperimentalMarker(firClassLikeSymbol, checkerContext.getSession())) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, FirErrors.INSTANCE.getOPT_IN_MARKER_CAN_ONLY_BE_USED_AS_ANNOTATION_OR_ARGUMENT_IN_OPT_IN(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                } else if (delegatedTypeRef instanceof FirUserTypeRef) {
                    FirUserTypeRef firUserTypeRef = delegatedTypeRef;
                    if (!firUserTypeRef.getQualifier().isEmpty()) {
                        checkContainingClasses(checkerContext, diagnosticReporter, firClassLikeSymbol, source, firUserTypeRef.getQualifier());
                    }
                }
            }
            Object objLastOrNull2 = CollectionsKt.lastOrNull(checkerContext.getContainingDeclarations());
            FirClassSymbol firClassSymbol = objLastOrNull2 instanceof FirClassSymbol ? (FirClassSymbol) objLastOrNull2 : null;
            List<FirResolvedTypeRef> resolvedSuperTypeRefs = firClassSymbol != null ? firClassSymbol.getResolvedSuperTypeRefs() : null;
            if (resolvedSuperTypeRefs == null) {
                resolvedSuperTypeRefs = CollectionsKt.emptyList();
            }
            boolean zContains = resolvedSuperTypeRefs.contains(firResolvedTypeRef);
            FirOptInUsageBaseChecker firOptInUsageBaseChecker = FirOptInUsageBaseChecker.INSTANCE;
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            FirOptInUsageTypeRefChecker firOptInUsageTypeRefChecker = INSTANCE;
            linkedHashSet.addAll(firOptInUsageTypeRefChecker.loadClassifierExperimentalities(checkerContext, symbol, zContains));
            if (symbol2 != null) {
                linkedHashSet.addAll(firOptInUsageTypeRefChecker.loadClassifierExperimentalities(checkerContext, symbol2, zContains));
            }
            linkedHashSet.addAll(firOptInUsageBaseChecker.loadExperimentalitiesFromConeArguments(checkerContext, ArraysKt.toList(coneClassLikeType2.getTypeArguments())));
            FirOptInUsageBaseChecker.reportNotAcceptedExperimentalities$default(firOptInUsageBaseChecker, checkerContext, diagnosticReporter, linkedHashSet, firResolvedTypeRef, null, false, 48, null);
        }
    }
}
