package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Ref;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirMissingDependencyClassProxy;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeDefinitelyNotNullType;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeIntersectionType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b`\u0018\u00002\u00020\u0001:\u0001\u0017J \u0010\u0002\u001a\u00020\u0003*\u00020\u00042\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00030\u0006H\u0016J/\u0010\b\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00042\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\rH\u0016R\u00020\tj\u0006\u0010\n\u001a\u00020\t¢\u0006\u0002\u0010\u000eJE\u0010\u000f\u001a\u00020\u00032\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\r2\u0006\u0010\u0014\u001a\u00020\u0015H\u0016R\u00020\tR\u00020\u0010j\u0006\u0010\n\u001a\u00020\tj\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0002\u0010\u0016ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0018À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirMissingDependencyClassProxy;", Argument.Delimiters.none, "forEachClassLikeType", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "action", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "considerType", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", ModuleXmlParser.TYPE, "missingTypes", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Ljava/util/Set;)V", "reportMissingTypes", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "missingTypeOrigin", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirMissingDependencyClassProxy$MissingTypeOrigin;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/KtSourceElement;Ljava/util/Set;Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirMissingDependencyClassProxy$MissingTypeOrigin;)V", "MissingTypeOrigin", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface FirMissingDependencyClassProxy {
    static Unit a(Ref.BooleanRef booleanRef, FirMissingDependencyClassProxy firMissingDependencyClassProxy, CheckerContext checkerContext, Set set, Ref.ObjectRef objectRef, ConeClassLikeType coneClassLikeType) {
        coneClassLikeType.getClass();
        if (coneClassLikeType instanceof ConeErrorType) {
            ConeKotlinType delegatedType = ((ConeErrorType) coneClassLikeType).getDelegatedType();
            if (delegatedType == null) {
                booleanRef.element = true;
            } else {
                firMissingDependencyClassProxy.considerType(checkerContext, delegatedType, set);
            }
        } else if (ToSymbolUtilsKt.toSymbol((SessionHolder) checkerContext, coneClassLikeType.getLookupTag()) == null) {
            Collection linkedHashSet = (Set) objectRef.element;
            if (linkedHashSet == null) {
                linkedHashSet = new LinkedHashSet();
                objectRef.element = linkedHashSet;
            }
            linkedHashSet.add(TypeConstructionUtilsKt.constructClassType$default(coneClassLikeType.getLookupTag(), null, false, null, 7, null));
        }
        return Unit.INSTANCE;
    }

    default void considerType(final CheckerContext checkerContext, ConeKotlinType coneKotlinType, final Set<ConeClassLikeType> set) {
        Set set2;
        checkerContext.getClass();
        coneKotlinType.getClass();
        set.getClass();
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        forEachClassLikeType(coneKotlinType, new Function1() { // from class: sa5
            public final Object invoke(Object obj) {
                return FirMissingDependencyClassProxy.a(booleanRef, this, checkerContext, set, objectRef, (ConeClassLikeType) obj);
            }
        });
        if (booleanRef.element || (set2 = (Set) objectRef.element) == null) {
            return;
        }
        set.addAll(set2);
    }

    default void forEachClassLikeType(ConeKotlinType coneKotlinType, Function1<? super ConeClassLikeType, Unit> function1) {
        coneKotlinType.getClass();
        function1.getClass();
        if (coneKotlinType instanceof ConeFlexibleType) {
            ConeFlexibleType coneFlexibleType = (ConeFlexibleType) coneKotlinType;
            forEachClassLikeType(coneFlexibleType.getLowerBound(), function1);
            forEachClassLikeType(coneFlexibleType.getUpperBound(), function1);
        } else {
            if (coneKotlinType instanceof ConeDefinitelyNotNullType) {
                forEachClassLikeType(((ConeDefinitelyNotNullType) coneKotlinType).getOriginal(), function1);
                return;
            }
            if (coneKotlinType instanceof ConeIntersectionType) {
                Iterator<T> it = ((ConeIntersectionType) coneKotlinType).getIntersectedTypes().iterator();
                while (it.hasNext()) {
                    forEachClassLikeType((ConeKotlinType) it.next(), function1);
                }
            } else if (coneKotlinType instanceof ConeClassLikeType) {
                function1.invoke(coneKotlinType);
            }
        }
    }

    default void reportMissingTypes(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, KtSourceElement ktSourceElement, Set<ConeClassLikeType> set, MissingTypeOrigin missingTypeOrigin) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        set.getClass();
        missingTypeOrigin.getClass();
        LanguageVersionSettings languageVersionSettings = FirLanguageSettingsComponentKt.getLanguageVersionSettings(checkerContext.getSession());
        for (ConeClassLikeType coneClassLikeType : set) {
            if ((missingTypeOrigin instanceof MissingTypeOrigin.LambdaParameter) && coneClassLikeType.getTypeArguments().length == 0 && !languageVersionSettings.supportsFeature(LanguageFeature.ForbidLambdaParameterWithMissingDependencyType)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory2) FirErrors.INSTANCE.getMISSING_DEPENDENCY_CLASS_IN_LAMBDA_PARAMETER(), (Object) coneClassLikeType, (Object) ((MissingTypeOrigin.LambdaParameter) missingTypeOrigin).getName(), (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
            } else {
                CheckerContext checkerContext2 = checkerContext;
                DiagnosticReporter diagnosticReporter2 = diagnosticReporter;
                KtSourceElement ktSourceElement2 = ktSourceElement;
                if ((missingTypeOrigin instanceof MissingTypeOrigin.LambdaReceiver) && coneClassLikeType.getTypeArguments().length == 0 && !languageVersionSettings.supportsFeature(LanguageFeature.ForbidLambdaParameterWithMissingDependencyType)) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter2, (AbstractKtSourceElement) ktSourceElement2, (KtDiagnosticFactory1) FirErrors.INSTANCE.getMISSING_DEPENDENCY_CLASS_IN_LAMBDA_RECEIVER(), (Object) coneClassLikeType, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                } else if (!(missingTypeOrigin instanceof MissingTypeOrigin.Expression) || languageVersionSettings.supportsFeature(LanguageFeature.ForbidUsingExpressionTypesWithInaccessibleContent)) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter2, (AbstractKtSourceElement) ktSourceElement2, (KtDiagnosticFactory1) FirErrors.INSTANCE.getMISSING_DEPENDENCY_CLASS(), (Object) coneClassLikeType, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                } else {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter2, (AbstractKtSourceElement) ktSourceElement2, (KtDiagnosticFactory1) FirErrors.INSTANCE.getMISSING_DEPENDENCY_CLASS_IN_EXPRESSION_TYPE(), (Object) coneClassLikeType, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                }
                checkerContext = checkerContext2;
                diagnosticReporter = diagnosticReporter2;
                ktSourceElement = ktSourceElement2;
            }
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0004\u0005\u0006\u0007B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0004\b\t\n\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirMissingDependencyClassProxy$MissingTypeOrigin;", Argument.Delimiters.none, "<init>", "()V", "LambdaParameter", "LambdaReceiver", "Expression", "Other", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirMissingDependencyClassProxy$MissingTypeOrigin$Expression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirMissingDependencyClassProxy$MissingTypeOrigin$LambdaParameter;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirMissingDependencyClassProxy$MissingTypeOrigin$LambdaReceiver;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirMissingDependencyClassProxy$MissingTypeOrigin$Other;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static abstract class MissingTypeOrigin {

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirMissingDependencyClassProxy$MissingTypeOrigin$Expression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirMissingDependencyClassProxy$MissingTypeOrigin;", "<init>", "()V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class Expression extends MissingTypeOrigin {
            public static final Expression INSTANCE = new Expression();

            private Expression() {
                super(null);
            }
        }

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirMissingDependencyClassProxy$MissingTypeOrigin$LambdaParameter;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirMissingDependencyClassProxy$MissingTypeOrigin;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "<init>", "(Lorg/jetbrains/kotlin/name/Name;)V", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class LambdaParameter extends MissingTypeOrigin {
            private final Name name;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public LambdaParameter(Name name) {
                super(null);
                name.getClass();
                this.name = name;
            }

            public final Name getName() {
                return this.name;
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirMissingDependencyClassProxy$MissingTypeOrigin$LambdaReceiver;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirMissingDependencyClassProxy$MissingTypeOrigin;", "<init>", "()V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class LambdaReceiver extends MissingTypeOrigin {
            public static final LambdaReceiver INSTANCE = new LambdaReceiver();

            private LambdaReceiver() {
                super(null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirMissingDependencyClassProxy$MissingTypeOrigin$Other;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirMissingDependencyClassProxy$MissingTypeOrigin;", "<init>", "()V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class Other extends MissingTypeOrigin {
            public static final Other INSTANCE = new Other();

            private Other() {
                super(null);
            }
        }

        public /* synthetic */ MissingTypeOrigin(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private MissingTypeOrigin() {
        }
    }
}
