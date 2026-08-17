package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.AnalysisFlags;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.references.FirResolvedNamedReference;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFileSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.FqNameUnsafe;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u0000 \u001e2\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003:\u0001\u001eB\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u0019\u0010\u0006\u001a\u00020\u0007H$R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\nJ/\u0010\u000b\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H$R\u00020\bR\u00020\rj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0002\u0010\u0011J!\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0014H\u0014R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0015J+\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u0002R\u00020\bR\u00020\rj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0002\u0010\u0018J)\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u001bH\u0014R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u001cJ\u0019\u0010\u001d\u001a\u00020\u0007H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\n¨\u0006\u001f"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/AbstractFirReflectionApiCallChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirBasicExpressionChecker;", "<init>", "()V", "isWholeReflectionApiAvailable", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;)Z", "report", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/KtSourceElement;)V", "isAllowedKClassMember", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/name/Name;)Z", "check", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirStatement;)V", "isAllowedReflectionApi", "containingClassId", "Lorg/jetbrains/kotlin/name/ClassId;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/name/Name;Lorg/jetbrains/kotlin/name/ClassId;)Z", "isReflectionSource", "Companion", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class AbstractFirReflectionApiCallChecker extends FirExpressionChecker<FirStatement> {
    private static final Set<ClassId> ALLOWED_CLASSES;
    private static final Set<Name> ALLOWED_MEMBER_NAMES;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final ClassId K_CLASS = ClassId.Companion.topLevel(StandardNames.FqNames.kClass.toSafe());
    private static final Name K_CLASS_IS_INSTANCE;
    private static final Name K_CLASS_IS_INTERFACE_NAME;
    private static final Name K_CLASS_QUALIFIED_NAME;
    private static final Name K_CLASS_SIMPLE_NAME;
    private static final Set<ClassId> K_PROPERTY_CLASSES;

    static {
        Name nameIdentifier = Name.identifier("simpleName");
        nameIdentifier.getClass();
        K_CLASS_SIMPLE_NAME = nameIdentifier;
        Name nameIdentifier2 = Name.identifier("isInstance");
        nameIdentifier2.getClass();
        K_CLASS_IS_INSTANCE = nameIdentifier2;
        Name nameIdentifier3 = Name.identifier("qualifiedName");
        nameIdentifier3.getClass();
        K_CLASS_QUALIFIED_NAME = nameIdentifier3;
        Name nameIdentifier4 = Name.identifier("isInterface");
        nameIdentifier4.getClass();
        K_CLASS_IS_INTERFACE_NAME = nameIdentifier4;
        List listListOf = CollectionsKt.listOf(new FqNameUnsafe[]{StandardNames.FqNames.kProperty0, StandardNames.FqNames.kProperty1, StandardNames.FqNames.kProperty2, StandardNames.FqNames.kMutableProperty0, StandardNames.FqNames.kMutableProperty1, StandardNames.FqNames.kMutableProperty2});
        HashSet hashSet = new HashSet();
        Iterator it = listListOf.iterator();
        while (it.hasNext()) {
            hashSet.add(ClassId.Companion.topLevel(((FqNameUnsafe) it.next()).toSafe()));
        }
        K_PROPERTY_CLASSES = hashSet;
        List listListOf2 = CollectionsKt.listOf(new String[]{"equals", "hashCode", "toString", "invoke", ModuleXmlParser.NAME});
        HashSet hashSet2 = new HashSet();
        Iterator it2 = listListOf2.iterator();
        while (it2.hasNext()) {
            hashSet2.add(Name.identifier((String) it2.next()));
        }
        ALLOWED_MEMBER_NAMES = hashSet2;
        List listListOf3 = CollectionsKt.listOf(new String[]{"KType", "KTypeParameter", "KTypeProjection", "KTypeProjection.Companion", "KVariance"});
        HashSet hashSet3 = new HashSet();
        Iterator it3 = listListOf3.iterator();
        while (it3.hasNext()) {
            hashSet3.add(new ClassId(StandardNames.KOTLIN_REFLECT_FQ_NAME, new FqName((String) it3.next()), false));
        }
        ALLOWED_CLASSES = hashSet3;
    }

    public AbstractFirReflectionApiCallChecker() {
        super(MppCheckerKind.Common);
    }

    public static final Name getK_CLASS_IS_INTERFACE_NAME() {
        return INSTANCE.getK_CLASS_IS_INTERFACE_NAME();
    }

    private final boolean isReflectionSource(CheckerContext checkerContext) {
        FirFileSymbol containingFileSymbol = checkerContext.getContainingFileSymbol();
        return containingFileSymbol != null && UtilsKt.getPackageFqName(containingFileSymbol).startsWith(StandardNames.KOTLIN_REFLECT_FQ_NAME);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public final void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirStatement firStatement) {
        FirReference reference;
        FirResolvedNamedReference resolved;
        FirExpression dispatchReceiver;
        ConeKotlinType resolvedType;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firStatement.getClass();
        if (isWholeReflectionApiAvailable(checkerContext) || isReflectionSource(checkerContext) || (reference = org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt.toReference(firStatement, checkerContext.getSession())) == null || (resolved = FirReferenceUtilsKt.getResolved(reference)) == null) {
            return;
        }
        FirBasedSymbol<?> resolvedSymbol = resolved.getResolvedSymbol();
        ClassId classIdFullyExpandedClassId = null;
        FirCallableSymbol firCallableSymbol = resolvedSymbol instanceof FirCallableSymbol ? (FirCallableSymbol) resolvedSymbol : null;
        if (firCallableSymbol == null) {
            return;
        }
        FirQualifiedAccessExpression firQualifiedAccessExpression = firStatement instanceof FirQualifiedAccessExpression ? (FirQualifiedAccessExpression) firStatement : null;
        if (firQualifiedAccessExpression != null && (dispatchReceiver = firQualifiedAccessExpression.getDispatchReceiver()) != null && (resolvedType = FirTypeUtilsKt.getResolvedType(dispatchReceiver)) != null) {
            classIdFullyExpandedClassId = FirHelpersKt.fullyExpandedClassId(resolvedType, checkerContext.getSession());
        }
        if (classIdFullyExpandedClassId == null || !Intrinsics.areEqual(classIdFullyExpandedClassId.getPackageFqName(), StandardNames.KOTLIN_REFLECT_FQ_NAME) || isAllowedReflectionApi(checkerContext, firCallableSymbol.getName(), classIdFullyExpandedClassId)) {
            return;
        }
        KtSourceElement source = resolved.getSource();
        if (source == null) {
            source = ((FirQualifiedAccessExpression) firStatement).getSource();
        }
        report(checkerContext, diagnosticReporter, source);
    }

    public boolean isAllowedKClassMember(CheckerContext checkerContext, Name name) {
        checkerContext.getClass();
        name.getClass();
        if (Intrinsics.areEqual(name, K_CLASS_SIMPLE_NAME) || Intrinsics.areEqual(name, K_CLASS_IS_INSTANCE)) {
            return true;
        }
        if (Intrinsics.areEqual(name, K_CLASS_QUALIFIED_NAME)) {
            return ((Boolean) checkerContext.get$languageVersionSettings().getFlag(AnalysisFlags.getAllowFullyQualifiedNameInKClass())).booleanValue();
        }
        return false;
    }

    public boolean isAllowedReflectionApi(CheckerContext checkerContext, Name name, ClassId classId) {
        checkerContext.getClass();
        name.getClass();
        classId.getClass();
        if (ALLOWED_MEMBER_NAMES.contains(name)) {
            return true;
        }
        if (Intrinsics.areEqual(classId, K_CLASS) && isAllowedKClassMember(checkerContext, name)) {
            return true;
        }
        return ((Intrinsics.areEqual(name.asString(), "get") || Intrinsics.areEqual(name.asString(), "set")) && K_PROPERTY_CLASSES.contains(classId)) || ALLOWED_CLASSES.contains(classId);
    }

    public abstract boolean isWholeReflectionApiAvailable(CheckerContext checkerContext);

    public abstract void report(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, KtSourceElement ktSourceElement);

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\n\u001a\u00020\u00078\u0004X\u0085\u0004r\u0002\b\u000e¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\u0003\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00070\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/AbstractFirReflectionApiCallChecker$Companion;", Argument.Delimiters.none, "<init>", "()V", "K_CLASS", "Lorg/jetbrains/kotlin/name/ClassId;", "K_CLASS_SIMPLE_NAME", "Lorg/jetbrains/kotlin/name/Name;", "K_CLASS_IS_INSTANCE", "K_CLASS_QUALIFIED_NAME", "K_CLASS_IS_INTERFACE_NAME", "getK_CLASS_IS_INTERFACE_NAME$annotations", "getK_CLASS_IS_INTERFACE_NAME", "()Lorg/jetbrains/kotlin/name/Name;", "Lkotlin/jvm/JvmStatic;", "K_PROPERTY_CLASSES", Argument.Delimiters.none, "ALLOWED_MEMBER_NAMES", "ALLOWED_CLASSES", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public static /* synthetic */ void getK_CLASS_IS_INTERFACE_NAME$annotations() {
        }

        public final Name getK_CLASS_IS_INTERFACE_NAME() {
            return AbstractFirReflectionApiCallChecker.K_CLASS_IS_INTERFACE_NAME;
        }

        private Companion() {
        }
    }
}
