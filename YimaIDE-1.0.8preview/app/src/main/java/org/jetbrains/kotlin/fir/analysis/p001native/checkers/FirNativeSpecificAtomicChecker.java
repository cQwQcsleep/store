package org.jetbrains.kotlin.fir.analysis.p001native.checkers;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
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
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.p000native.FirNativeErrors;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ-\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0010H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u0014\u001a\r\u0012\t\u0012\u00070\u0016¢\u0006\u0002\b\u00170\u0015X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/native/checkers/FirNativeSpecificAtomicChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirCallableDeclarationChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;)V", "checkType", "typeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/types/FirTypeRef;)V", "CONCURRENT_PACKAGE", "Lorg/jetbrains/kotlin/name/FqName;", "CONCURRENT_NAME_SET", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/Name;", "Lkotlin/jvm/internal/EnhancedNullability;", "org.jetbrains.kotlin:checkers.native"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirNativeSpecificAtomicChecker extends FirDeclarationChecker<FirCallableDeclaration> {
    private static final Set<Name> CONCURRENT_NAME_SET;
    public static final FirNativeSpecificAtomicChecker INSTANCE = new FirNativeSpecificAtomicChecker();
    private static final FqName CONCURRENT_PACKAGE = new FqName("kotlin.concurrent");

    static {
        List listListOf = CollectionsKt.listOf(new String[]{"AtomicIntArray", "AtomicLongArray", "AtomicArray", "AtomicInt", "AtomicLong", "AtomicReference"});
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator it = listListOf.iterator();
        while (it.hasNext()) {
            linkedHashSet.add(Name.identifier((String) it.next()));
        }
        CONCURRENT_NAME_SET = CollectionsKt.toSet(linkedHashSet);
    }

    private FirNativeSpecificAtomicChecker() {
        super(MppCheckerKind.Platform);
    }

    private final void checkType(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirTypeRef firTypeRef) {
        ClassId classIdFullyExpandedClassId = FirHelpersKt.fullyExpandedClassId(FirTypeUtilsKt.getConeType(firTypeRef), checkerContext.getSession());
        if (classIdFullyExpandedClassId != null && Intrinsics.areEqual(classIdFullyExpandedClassId.getPackageFqName(), CONCURRENT_PACKAGE) && classIdFullyExpandedClassId.getOuterClassId() == null) {
            Name shortClassName = classIdFullyExpandedClassId.getShortClassName();
            if (CONCURRENT_NAME_SET.contains(shortClassName)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firTypeRef.getSource(), (KtDiagnosticFactory1) FirNativeErrors.INSTANCE.getNATIVE_SPECIFIC_ATOMIC(), (Object) shortClassName, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirCallableDeclaration firCallableDeclaration) {
        FirTypeRef typeRef;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firCallableDeclaration.getClass();
        if (!firCallableDeclaration.getStatus().getVisibility().getIsPublicAPI() || (firCallableDeclaration instanceof FirValueParameter) || (firCallableDeclaration instanceof FirAnonymousFunction)) {
            return;
        }
        FirReceiverParameter receiverParameter = firCallableDeclaration.getReceiverParameter();
        if (receiverParameter != null && (typeRef = receiverParameter.getTypeRef()) != null) {
            INSTANCE.checkType(checkerContext, diagnosticReporter, typeRef);
        }
        Iterator<T> it = firCallableDeclaration.getContextParameters().iterator();
        while (it.hasNext()) {
            INSTANCE.checkType(checkerContext, diagnosticReporter, ((FirValueParameter) it.next()).getReturnTypeRef());
        }
        FirTypeRef returnTypeRef = firCallableDeclaration.getReturnTypeRef();
        KtSourceElement source = returnTypeRef.getSource();
        if (!((source != null ? source.getKind() : null) instanceof KtRealSourceElementKind)) {
            returnTypeRef = null;
        }
        if (returnTypeRef != null) {
            INSTANCE.checkType(checkerContext, diagnosticReporter, returnTypeRef);
        }
        if (!(firCallableDeclaration instanceof FirFunction) || (firCallableDeclaration instanceof FirPropertyAccessor)) {
            return;
        }
        Iterator<T> it2 = ((FirFunction) firCallableDeclaration).getValueParameters().iterator();
        while (it2.hasNext()) {
            INSTANCE.checkType(checkerContext, diagnosticReporter, ((FirValueParameter) it2.next()).getReturnTypeRef());
        }
    }
}
