package org.jetbrains.kotlin.fir.analysis.p001native.checkers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.analysis.checkers.FirAnnotationHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirKeywordUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.p000native.FirNativeErrors;
import org.jetbrains.kotlin.fir.analysis.p001native.checkers.FirNativeThrowsChecker;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirCall;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvable;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.FirVarargArgumentsExpression;
import org.jetbrains.kotlin.fir.expressions.FirWrappedArgumentExpression;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.FirTypeScopeKt;
import org.jetbrains.kotlin.fir.scopes.MemberWithBaseScope;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.lexer.KtModifierKeywordToken;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.resolve.annotations.ThrowUtilKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 &2\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003:\u0004$%&'B\u0011\b\u0004\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J-\u0010\b\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u0002H\u0016R\u00020\nR\u00020\fj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010\u000fJ7\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u00022\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0002R\u00020\nR\u00020\fj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010\u0014J7\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00180\u00162\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0002R\u00020\nj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u001bJ\u000e\u0010\u001c\u001a\u00020\u0011*\u0004\u0018\u00010\u001dH\u0002J\u001a\u0010\u001e\u001a\u00020\u00182\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u001f\u001a\u00020 H\u0002J\u001a\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"*\u00020\u00132\u0006\u0010\u001f\u001a\u00020 H\u0002\u0082\u0001\u0002()¨\u0006*"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/native/checkers/FirNativeThrowsChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirBasicDeclarationChecker;", "mppKind", "Lorg/jetbrains/kotlin/fir/analysis/checkers/MppCheckerKind;", "<init>", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/MppCheckerKind;)V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)V", "checkInheritance", Argument.Delimiters.none, "throwsAnnotation", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;)Z", "getInheritedThrows", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "Lorg/jetbrains/kotlin/fir/analysis/native/checkers/FirNativeThrowsChecker$ThrowsFilter;", "function", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;)Ljava/util/Map;", "hasUnresolvedArgument", "Lorg/jetbrains/kotlin/fir/FirElement;", "decodeThrowsFilter", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "getClassIds", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/ClassId;", "Regular", "ForExpectClass", "Companion", "ThrowsFilter", "Lorg/jetbrains/kotlin/fir/analysis/native/checkers/FirNativeThrowsChecker$ForExpectClass;", "Lorg/jetbrains/kotlin/fir/analysis/native/checkers/FirNativeThrowsChecker$Regular;", "org.jetbrains.kotlin:checkers.native"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirNativeThrowsChecker extends FirDeclarationChecker<FirDeclaration> {
    private static final Set<ClassId> cancellationExceptionAndSupersClassIds;
    private static final FqName cancellationExceptionFqName;
    private static final ClassId throwsClassId;

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J-\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/native/checkers/FirNativeThrowsChecker$ForExpectClass;", "Lorg/jetbrains/kotlin/fir/analysis/native/checkers/FirNativeThrowsChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)V", "org.jetbrains.kotlin:checkers.native"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ForExpectClass extends FirNativeThrowsChecker {
        public static final ForExpectClass INSTANCE = new ForExpectClass();

        private ForExpectClass() {
            super(MppCheckerKind.Common, null);
        }

        @Override // org.jetbrains.kotlin.fir.analysis.p001native.checkers.FirNativeThrowsChecker, org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
        public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration) {
            checkerContext.getClass();
            diagnosticReporter.getClass();
            firDeclaration.getClass();
            FirMemberDeclaration firMemberDeclaration = firDeclaration instanceof FirMemberDeclaration ? (FirMemberDeclaration) firDeclaration : null;
            if (firMemberDeclaration == null || !firMemberDeclaration.getStatus().isExpect()) {
                return;
            }
            super.check(checkerContext, diagnosticReporter, firDeclaration);
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J-\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/native/checkers/FirNativeThrowsChecker$Regular;", "Lorg/jetbrains/kotlin/fir/analysis/native/checkers/FirNativeThrowsChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)V", "org.jetbrains.kotlin:checkers.native"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Regular extends FirNativeThrowsChecker {
        public static final Regular INSTANCE = new Regular();

        private Regular() {
            super(MppCheckerKind.Platform, null);
        }

        @Override // org.jetbrains.kotlin.fir.analysis.p001native.checkers.FirNativeThrowsChecker, org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
        public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration) {
            checkerContext.getClass();
            diagnosticReporter.getClass();
            firDeclaration.getClass();
            FirMemberDeclaration firMemberDeclaration = firDeclaration instanceof FirMemberDeclaration ? (FirMemberDeclaration) firDeclaration : null;
            if (firMemberDeclaration == null || !firMemberDeclaration.getStatus().isExpect()) {
                super.check(checkerContext, diagnosticReporter, firDeclaration);
            }
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0011\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u001b\u0010\n\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004R\u0019\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/native/checkers/FirNativeThrowsChecker$ThrowsFilter;", Argument.Delimiters.none, "classes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/ClassId;", "<init>", "(Ljava/util/Set;)V", "getClasses", "()Ljava/util/Set;", "component1", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:checkers.native"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class ThrowsFilter {
        private final Set<ClassId> classes;

        public ThrowsFilter(Set<ClassId> set) {
            this.classes = set;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ThrowsFilter copy$default(ThrowsFilter throwsFilter, Set set, int i, Object obj) {
            if ((i & 1) != 0) {
                set = throwsFilter.classes;
            }
            return throwsFilter.copy(set);
        }

        public final Set<ClassId> component1() {
            return this.classes;
        }

        public final ThrowsFilter copy(Set<ClassId> classes) {
            return new ThrowsFilter(classes);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof ThrowsFilter) && Intrinsics.areEqual(this.classes, ((ThrowsFilter) other).classes);
        }

        public final Set<ClassId> getClasses() {
            return this.classes;
        }

        public int hashCode() {
            Set<ClassId> set = this.classes;
            if (set == null) {
                return 0;
            }
            return set.hashCode();
        }

        public String toString() {
            return "ThrowsFilter(classes=" + this.classes + ')';
        }
    }

    static {
        ClassId.Companion companion = ClassId.Companion;
        throwsClassId = companion.topLevel(ThrowUtilKt.getKOTLIN_THROWS_ANNOTATION_FQ_NAME());
        FqName fqName = new FqName("kotlin.coroutines.cancellation.CancellationException");
        cancellationExceptionFqName = fqName;
        cancellationExceptionAndSupersClassIds = SetsKt.setOf(new ClassId[]{companion.topLevel(StandardNames.FqNames.throwable), companion.topLevel(new FqName("kotlin.Exception")), companion.topLevel(new FqName("kotlin.RuntimeException")), companion.topLevel(new FqName("kotlin.IllegalStateException")), companion.topLevel(fqName)});
    }

    public /* synthetic */ FirNativeThrowsChecker(MppCheckerKind mppCheckerKind, DefaultConstructorMarker defaultConstructorMarker) {
        this(mppCheckerKind);
    }

    public static Unit b(FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        return Unit.INSTANCE;
    }

    private final boolean checkInheritance(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration, FirAnnotation firAnnotation) {
        ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag;
        FirRegularClassSymbol regularClassSymbol;
        if (!(firDeclaration instanceof FirNamedFunction)) {
            return true;
        }
        FirNamedFunction firNamedFunction = (FirNamedFunction) firDeclaration;
        Set<Map.Entry<FirNamedFunctionSymbol, ThrowsFilter>> setEntrySet = getInheritedThrows(checkerContext, firNamedFunction, firAnnotation).entrySet();
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        for (Object obj : setEntrySet) {
            if (hashSet.add((ThrowsFilter) ((Map.Entry) obj).getValue())) {
                arrayList.add(obj);
            }
        }
        if (arrayList.size() >= 2) {
            KtSourceElement source = firNamedFunction.getSource();
            KtDiagnosticFactory1<Collection<FirRegularClassSymbol>> incompatible_throws_inherited = FirNativeErrors.INSTANCE.getINCOMPATIBLE_THROWS_INHERITED();
            ArrayList arrayList2 = new ArrayList();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag2 = ClassMembersKt.containingClassLookupTag((FirCallableSymbol<?>) ((Map.Entry) it.next()).getKey());
                FirRegularClassSymbol regularClassSymbol2 = coneClassLikeLookupTagContainingClassLookupTag2 != null ? ToSymbolUtilsKt.toRegularClassSymbol((SessionHolder) checkerContext, coneClassLikeLookupTagContainingClassLookupTag2) : null;
                if (regularClassSymbol2 != null) {
                    arrayList2.add(regularClassSymbol2);
                }
            }
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, (KtDiagnosticFactory1) incompatible_throws_inherited, (Object) arrayList2, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            return false;
        }
        Map.Entry entry = (Map.Entry) CollectionsKt.firstOrNull(arrayList);
        if (entry == null) {
            return true;
        }
        FirNamedFunctionSymbol firNamedFunctionSymbol = (FirNamedFunctionSymbol) entry.getKey();
        ThrowsFilter throwsFilter = (ThrowsFilter) entry.getValue();
        if ((firAnnotation != null ? firAnnotation.getSource() : null) == null || Intrinsics.areEqual(decodeThrowsFilter(firAnnotation, checkerContext.getSession()), throwsFilter) || (coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag(firNamedFunctionSymbol)) == null || (regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol((SessionHolder) checkerContext, coneClassLikeLookupTagContainingClassLookupTag)) == null || regularClassSymbol.getRawStatus().isExpect()) {
            return true;
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotation.getSource(), (KtDiagnosticFactory1) FirNativeErrors.INSTANCE.getINCOMPATIBLE_THROWS_OVERRIDE(), (Object) regularClassSymbol, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        return false;
    }

    private final ThrowsFilter decodeThrowsFilter(FirAnnotation throwsAnnotation, FirSession session) {
        List<ClassId> classIds;
        return new ThrowsFilter((throwsAnnotation == null || (classIds = getClassIds(throwsAnnotation, session)) == null) ? null : CollectionsKt.toSet(classIds));
    }

    private final List<ClassId> getClassIds(FirAnnotation firAnnotation, FirSession firSession) {
        List<FirExpression> listUnwrapVarargValue;
        FirExpression firExpression = (FirExpression) CollectionsKt.firstOrNull(firAnnotation.getArgumentMapping().getMapping().values());
        if (firExpression == null || (listUnwrapVarargValue = FirAnnotationUtilsKt.unwrapVarargValue(firExpression)) == null) {
            return CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = listUnwrapVarargValue.iterator();
        while (it.hasNext()) {
            FirRegularClassSymbol firRegularClassSymbolExtractClassFromArgument = FirAnnotationHelpersKt.extractClassFromArgument((FirExpression) it.next(), firSession);
            ClassId classId = firRegularClassSymbolExtractClassFromArgument != null ? firRegularClassSymbolExtractClassFromArgument.getClassId() : null;
            if (classId != null) {
                arrayList.add(classId);
            }
        }
        return arrayList;
    }

    private final Map<FirNamedFunctionSymbol, ThrowsFilter> getInheritedThrows(CheckerContext checkerContext, FirNamedFunction firNamedFunction, FirAnnotation firAnnotation) {
        FirRegularClassSymbol regularClassSymbol;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag(firNamedFunction.getSymbol());
        FirTypeScope firTypeScopeUnsubstitutedScope = (coneClassLikeLookupTagContainingClassLookupTag == null || (regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol((SessionHolder) checkerContext, coneClassLikeLookupTagContainingClassLookupTag)) == null) ? null : FirHelpersKt.unsubstitutedScope(checkerContext, regularClassSymbol);
        if (firTypeScopeUnsubstitutedScope != null) {
            firTypeScopeUnsubstitutedScope.processFunctionsByName(firNamedFunction.getName(), new Function1() { // from class: fb5
                public final Object invoke(Object obj) {
                    return FirNativeThrowsChecker.b((FirNamedFunctionSymbol) obj);
                }
            });
            getInheritedThrows$getInheritedThrows(linkedHashSet, firNamedFunction, linkedHashMap, this, checkerContext, firAnnotation, new MemberWithBaseScope(firNamedFunction.getSymbol(), firTypeScopeUnsubstitutedScope));
        }
        return linkedHashMap;
    }

    private static final void getInheritedThrows$getInheritedThrows(Set<FirNamedFunctionSymbol> set, FirNamedFunction firNamedFunction, Map<FirNamedFunctionSymbol, ThrowsFilter> map, FirNativeThrowsChecker firNativeThrowsChecker, CheckerContext checkerContext, FirAnnotation firAnnotation, MemberWithBaseScope<? extends FirNamedFunctionSymbol> memberWithBaseScope) {
        FirNamedFunctionSymbol firNamedFunctionSymbol = (FirNamedFunctionSymbol) memberWithBaseScope.getMember();
        if (set.add(firNamedFunctionSymbol)) {
            List<MemberWithBaseScope<FirNamedFunctionSymbol>> directOverriddenFunctionsWithBaseScope = FirTypeScopeKt.getDirectOverriddenFunctionsWithBaseScope(memberWithBaseScope.getBaseScope(), firNamedFunctionSymbol);
            if (!Intrinsics.areEqual(firNamedFunctionSymbol, firNamedFunction.getSymbol()) && (firAnnotation != null || directOverriddenFunctionsWithBaseScope.isEmpty())) {
                map.put(firNamedFunctionSymbol, firNativeThrowsChecker.decodeThrowsFilter(firAnnotation, checkerContext.getSession()));
                return;
            }
            for (MemberWithBaseScope<FirNamedFunctionSymbol> memberWithBaseScope2 : directOverriddenFunctionsWithBaseScope) {
                FirNamedFunctionSymbol firNamedFunctionSymbol2 = (FirNamedFunctionSymbol) memberWithBaseScope2.getMember();
                Set<FirNamedFunctionSymbol> set2 = set;
                FirNamedFunction firNamedFunction2 = firNamedFunction;
                Map<FirNamedFunctionSymbol, ThrowsFilter> map2 = map;
                FirNativeThrowsChecker firNativeThrowsChecker2 = firNativeThrowsChecker;
                CheckerContext checkerContext2 = checkerContext;
                getInheritedThrows$getInheritedThrows(set2, firNamedFunction2, map2, firNativeThrowsChecker2, checkerContext2, ClassMembersKt.isSubstitutionOrIntersectionOverride(firNamedFunctionSymbol2) ? null : FirAnnotationUtilsKt.getAnnotationByClassId(firNamedFunctionSymbol2, throwsClassId, checkerContext.getSession()), memberWithBaseScope2);
                set = set2;
                firNamedFunction = firNamedFunction2;
                map = map2;
                firNativeThrowsChecker = firNativeThrowsChecker2;
                checkerContext = checkerContext2;
            }
        }
    }

    private final boolean hasUnresolvedArgument(FirElement firElement) {
        FirClassLikeSymbol<?> symbol;
        if (firElement instanceof FirWrappedArgumentExpression) {
            return hasUnresolvedArgument(((FirWrappedArgumentExpression) firElement).getExpression());
        }
        if ((firElement instanceof FirResolvable) && FirReferenceUtilsKt.isError(((FirResolvable) firElement).getCalleeReference())) {
            return true;
        }
        if (firElement instanceof FirVarargArgumentsExpression) {
            Iterator<FirExpression> it = ((FirVarargArgumentsExpression) firElement).getArguments().iterator();
            while (it.hasNext()) {
                if (hasUnresolvedArgument(it.next())) {
                    return true;
                }
            }
        }
        if (firElement instanceof FirCall) {
            Iterator<FirExpression> it2 = ((FirCall) firElement).getArgumentList().getArguments().iterator();
            while (it2.hasNext()) {
                if (hasUnresolvedArgument(it2.next())) {
                    return true;
                }
            }
        }
        return (firElement instanceof FirResolvedQualifier) && (symbol = ((FirResolvedQualifier) firElement).getSymbol()) != null && (symbol instanceof FirTypeAliasSymbol) && ConeTypeUtilsKt.hasError(((FirTypeAliasSymbol) symbol).getResolvedExpandedTypeRef().getConeType());
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration) {
        List<ClassId> classIds;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firDeclaration.getClass();
        FirAnnotation annotationByClassId = FirAnnotationUtilsKt.getAnnotationByClassId(firDeclaration, throwsClassId, checkerContext.getSession());
        if (!checkInheritance(checkerContext, diagnosticReporter, firDeclaration, annotationByClassId) || hasUnresolvedArgument(annotationByClassId) || annotationByClassId == null || (classIds = getClassIds(annotationByClassId, checkerContext.getSession())) == null) {
            return;
        }
        if (classIds.isEmpty()) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) annotationByClassId.getSource(), FirNativeErrors.INSTANCE.getTHROWS_LIST_EMPTY(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            return;
        }
        KtModifierKeywordToken ktModifierKeywordToken = KtTokens.SUSPEND_KEYWORD;
        ktModifierKeywordToken.getClass();
        if (FirKeywordUtilsKt.hasModifier(firDeclaration, ktModifierKeywordToken)) {
            List<ClassId> list = classIds;
            if (!(list instanceof Collection) || !list.isEmpty()) {
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    if (cancellationExceptionAndSupersClassIds.contains((ClassId) it.next())) {
                        return;
                    }
                }
            }
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) annotationByClassId.getSource(), (KtDiagnosticFactory1) FirNativeErrors.INSTANCE.getMISSING_EXCEPTION_IN_THROWS_ON_SUSPEND(), (Object) cancellationExceptionFqName, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        }
    }

    private FirNativeThrowsChecker(MppCheckerKind mppCheckerKind) {
        super(mppCheckerKind);
    }
}
