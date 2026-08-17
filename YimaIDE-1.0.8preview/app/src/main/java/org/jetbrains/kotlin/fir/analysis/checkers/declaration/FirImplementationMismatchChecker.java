package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtRealSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKind;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory4;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.diagnostics.impl.DeduplicatingDiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.impl.DeduplicatingDiagnosticReporterKt;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.DelegatedWrapperData;
import org.jetbrains.kotlin.fir.FirVisibilityCheckerKt;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirImplementationMismatchChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutorByMapKt;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.FirTypeScopeKt;
import org.jetbrains.kotlin.fir.scopes.MemberWithBaseScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirNestedClassifierScopeKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirIntersectionCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirIntersectionOverrideFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirIntersectionOverridePropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.mpp.DeclarationSymbolMarker;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.types.AbstractTypeChecker;
import org.jetbrains.kotlin.types.TypeCheckerState;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003:\u0002-.B\u0011\b\u0004\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J-\u0010\b\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u0002H\u0016R\u00020\nR\u00020\fj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010\u000fJI\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\n\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002R\u00020\nR\u00020\fj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010\u0018JA\u0010\u0019\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u00022\n\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\u001a2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002R\u00020\nR\u00020\fj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010\u001bJ=\u0010\u001c\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002R\u00020\nR\u00020\fj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010\u001fJ7\u0010 \u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00150!*\u00020\u00172\u0006\u0010\"\u001a\u00020#2\u0006\u0010\u0011\u001a\u00020\u0002H\u0002R\u00020\nj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010$J=\u0010%\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010&\u001a\u00020\u00172\u0006\u0010\"\u001a\u00020#H\u0002R\u00020\nR\u00020\fj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010'J5\u0010(\u001a\u00020)*\u00020)2\n\u0010*\u001a\u0006\u0012\u0002\b\u00030\u00152\n\u0010+\u001a\u0006\u0012\u0002\b\u00030\u0015H\u0002R\u00020\nj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010,\u0082\u0001\u0002/0¨\u00061"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirImplementationMismatchChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirClassChecker;", "mppKind", "Lorg/jetbrains/kotlin/fir/analysis/checkers/MppCheckerKind;", "<init>", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/MppCheckerKind;)V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirClass;)V", "checkInheritanceClash", "containingClass", "typeCheckerState", "Lorg/jetbrains/kotlin/types/TypeCheckerState;", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "classScope", "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirClass;Lorg/jetbrains/kotlin/types/TypeCheckerState;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;)V", "checkValOverridesVar", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirClass;Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;)V", "checkDifferentNamesForTheSameParameterInSupertypes", "functionSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirClass;Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;)V", "collectCallablesNamed", Argument.Delimiters.none, ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;Lorg/jetbrains/kotlin/name/Name;Lorg/jetbrains/kotlin/fir/declarations/FirClass;)Ljava/util/List;", "checkConflictingMembers", "scope", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirClass;Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;Lorg/jetbrains/kotlin/name/Name;)V", "substituteTypeParameters", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "fromDeclaration", "toDeclaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;)Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Regular", "ForExpectClass", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirImplementationMismatchChecker$ForExpectClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirImplementationMismatchChecker$Regular;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirImplementationMismatchChecker extends FirDeclarationChecker<FirClass> {

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J-\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirImplementationMismatchChecker$ForExpectClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirImplementationMismatchChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirClass;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ForExpectClass extends FirImplementationMismatchChecker {
        public static final ForExpectClass INSTANCE = new ForExpectClass();

        private ForExpectClass() {
            super(MppCheckerKind.Common, null);
        }

        @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirImplementationMismatchChecker, org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
        public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirClass firClass) {
            checkerContext.getClass();
            diagnosticReporter.getClass();
            firClass.getClass();
            if (firClass.getStatus().isExpect()) {
                super.check(checkerContext, diagnosticReporter, firClass);
            }
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J-\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirImplementationMismatchChecker$Regular;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirImplementationMismatchChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirClass;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Regular extends FirImplementationMismatchChecker {
        public static final Regular INSTANCE = new Regular();

        private Regular() {
            super(MppCheckerKind.Platform, null);
        }

        @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirImplementationMismatchChecker, org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
        public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirClass firClass) {
            checkerContext.getClass();
            diagnosticReporter.getClass();
            firClass.getClass();
            if (firClass.getStatus().isExpect()) {
                return;
            }
            super.check(checkerContext, diagnosticReporter, firClass);
        }
    }

    @Metadata(d1 = {"\u0000+\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\u008a\b\u0018\u00002\u00020\u0001B/\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u0011\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J<\u0010\u0011\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u0012J\u0014\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u001a"}, d2 = {"org/jetbrains/kotlin/fir/analysis/checkers/declaration/FirImplementationMismatchChecker$checkConflictingMembers$GroupingKey", Argument.Delimiters.none, "contextParamets", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "extensionReceiver", "valueParameters", "<init>", "(Ljava/util/List;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Ljava/util/List;)V", "getContextParamets", "()Ljava/util/List;", "getExtensionReceiver", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getValueParameters", "component1", "component2", "component3", "copy", "(Ljava/util/List;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Ljava/util/List;)Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirImplementationMismatchChecker$checkConflictingMembers$GroupingKey;", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class GroupingKey {
        private final List<ConeKotlinType> contextParamets;
        private final ConeKotlinType extensionReceiver;
        private final List<ConeKotlinType> valueParameters;

        public GroupingKey(List<? extends ConeKotlinType> list, ConeKotlinType coneKotlinType, List<? extends ConeKotlinType> list2) {
            list.getClass();
            this.contextParamets = list;
            this.extensionReceiver = coneKotlinType;
            this.valueParameters = list2;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ GroupingKey copy$default(GroupingKey groupingKey, List list, ConeKotlinType coneKotlinType, List list2, int i, Object obj) {
            if ((i & 1) != 0) {
                list = groupingKey.contextParamets;
            }
            if ((i & 2) != 0) {
                coneKotlinType = groupingKey.extensionReceiver;
            }
            if ((i & 4) != 0) {
                list2 = groupingKey.valueParameters;
            }
            return groupingKey.copy(list, coneKotlinType, list2);
        }

        public final List<ConeKotlinType> component1() {
            return this.contextParamets;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final ConeKotlinType getExtensionReceiver() {
            return this.extensionReceiver;
        }

        public final List<ConeKotlinType> component3() {
            return this.valueParameters;
        }

        public final GroupingKey copy(List<? extends ConeKotlinType> contextParamets, ConeKotlinType extensionReceiver, List<? extends ConeKotlinType> valueParameters) {
            contextParamets.getClass();
            return new GroupingKey(contextParamets, extensionReceiver, valueParameters);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof GroupingKey)) {
                return false;
            }
            GroupingKey groupingKey = (GroupingKey) other;
            return Intrinsics.areEqual(this.contextParamets, groupingKey.contextParamets) && Intrinsics.areEqual(this.extensionReceiver, groupingKey.extensionReceiver) && Intrinsics.areEqual(this.valueParameters, groupingKey.valueParameters);
        }

        public final List<ConeKotlinType> getContextParamets() {
            return this.contextParamets;
        }

        public final ConeKotlinType getExtensionReceiver() {
            return this.extensionReceiver;
        }

        public final List<ConeKotlinType> getValueParameters() {
            return this.valueParameters;
        }

        public int hashCode() {
            int iHashCode = this.contextParamets.hashCode() * 31;
            ConeKotlinType coneKotlinType = this.extensionReceiver;
            int iHashCode2 = (iHashCode + (coneKotlinType == null ? 0 : coneKotlinType.hashCode())) * 31;
            List<ConeKotlinType> list = this.valueParameters;
            return iHashCode2 + (list != null ? list.hashCode() : 0);
        }

        public String toString() {
            return "GroupingKey(contextParamets=" + this.contextParamets + ", extensionReceiver=" + this.extensionReceiver + ", valueParameters=" + this.valueParameters + ')';
        }
    }

    public /* synthetic */ FirImplementationMismatchChecker(MppCheckerKind mppCheckerKind, DefaultConstructorMarker defaultConstructorMarker) {
        this(mppCheckerKind);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Unit c(CheckerContext checkerContext, List list, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        if (firNamedFunctionSymbol instanceof FirIntersectionOverrideFunctionSymbol) {
            List list2 = list;
            Iterator<T> it = org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt.getNonSubsumedOverriddenSymbols(checkerContext, (FirIntersectionCallableSymbol) firNamedFunctionSymbol).iterator();
            while (it.hasNext()) {
                FirCallableSymbol firCallableSymbol = (FirCallableSymbol) it.next();
                FirNamedFunctionSymbol firNamedFunctionSymbol2 = firCallableSymbol instanceof FirNamedFunctionSymbol ? (FirNamedFunctionSymbol) firCallableSymbol : null;
                if (firNamedFunctionSymbol2 != null) {
                    list2.add(firNamedFunctionSymbol2);
                }
            }
        } else {
            list.add(firNamedFunctionSymbol);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit check$lambda$0$0(FirImplementationMismatchChecker firImplementationMismatchChecker, CheckerContext checkerContext, DeduplicatingDiagnosticReporter deduplicatingDiagnosticReporter, FirClass firClass, TypeCheckerState typeCheckerState, FirTypeScope firTypeScope, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        firImplementationMismatchChecker.checkInheritanceClash(checkerContext, deduplicatingDiagnosticReporter, firClass, typeCheckerState, firNamedFunctionSymbol, firTypeScope);
        firImplementationMismatchChecker.checkDifferentNamesForTheSameParameterInSupertypes(checkerContext, deduplicatingDiagnosticReporter, firClass, firNamedFunctionSymbol, firTypeScope);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit check$lambda$0$1(FirImplementationMismatchChecker firImplementationMismatchChecker, CheckerContext checkerContext, DeduplicatingDiagnosticReporter deduplicatingDiagnosticReporter, FirClass firClass, TypeCheckerState typeCheckerState, FirTypeScope firTypeScope, FirVariableSymbol firVariableSymbol) {
        firVariableSymbol.getClass();
        firImplementationMismatchChecker.checkInheritanceClash(checkerContext, deduplicatingDiagnosticReporter, firClass, typeCheckerState, firVariableSymbol, firTypeScope);
        firImplementationMismatchChecker.checkValOverridesVar(checkerContext, deduplicatingDiagnosticReporter, firClass, firVariableSymbol, firTypeScope);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void checkConflictingMembers(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirClass firClass, FirTypeScope firTypeScope, Name name) {
        Object next;
        Pair pair;
        List<FirValueParameterSymbol> valueParameterSymbols;
        List<FirCallableSymbol<?>> listCollectCallablesNamed = collectCallablesNamed(checkerContext, firTypeScope, name, firClass);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator<T> it = listCollectCallablesNamed.iterator();
        while (true) {
            ArrayList arrayList = null;
            if (!it.hasNext()) {
                break;
            }
            Object next2 = it.next();
            FirCallableSymbol firCallableSymbol = (FirCallableSymbol) next2;
            List<FirValueParameterSymbol> contextParameterSymbols = firCallableSymbol.getContextParameterSymbols();
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(contextParameterSymbols, 10));
            Iterator<T> it2 = contextParameterSymbols.iterator();
            while (it2.hasNext()) {
                arrayList2.add(((FirValueParameterSymbol) it2.next()).getResolvedReturnType());
            }
            ConeKotlinType resolvedReceiverType = firCallableSymbol.getResolvedReceiverType();
            FirNamedFunctionSymbol firNamedFunctionSymbol = firCallableSymbol instanceof FirNamedFunctionSymbol ? (FirNamedFunctionSymbol) firCallableSymbol : null;
            if (firNamedFunctionSymbol != null && (valueParameterSymbols = firNamedFunctionSymbol.getValueParameterSymbols()) != null) {
                List<FirValueParameterSymbol> list = valueParameterSymbols;
                arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                Iterator<T> it3 = list.iterator();
                while (it3.hasNext()) {
                    arrayList.add(((FirValueParameterSymbol) it3.next()).getResolvedReturnTypeRef().getConeType());
                }
            }
            GroupingKey groupingKey = new GroupingKey(arrayList2, resolvedReceiverType, arrayList);
            Object arrayList3 = linkedHashMap.get(groupingKey);
            if (arrayList3 == null) {
                arrayList3 = new ArrayList();
                linkedHashMap.put(groupingKey, arrayList3);
            }
            ((List) arrayList3).add(next2);
        }
        Collection collectionValues = linkedHashMap.values();
        ArrayList<Pair> arrayList4 = new ArrayList();
        Iterator it4 = collectionValues.iterator();
        while (it4.hasNext()) {
            Iterator it5 = CollectionsKt.zipWithNext((List) it4.next()).iterator();
            do {
                if (!it5.hasNext()) {
                    next = null;
                    break;
                } else {
                    next = it5.next();
                    pair = (Pair) next;
                }
            } while (((FirCallableSymbol) pair.component1()).getTypeParameterSymbols().size() == ((FirCallableSymbol) pair.component2()).getTypeParameterSymbols().size());
            Pair pair2 = (Pair) next;
            if (pair2 != null) {
                arrayList4.add(pair2);
            }
        }
        for (Pair pair3 : arrayList4) {
            FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) ((FirCallableSymbol) pair3.getFirst()).getFir();
            while (true) {
                FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
                if (originalForSubstitutionOverrideAttr == null) {
                    break;
                } else {
                    firCallableDeclaration = originalForSubstitutionOverrideAttr;
                }
            }
            FirCallableSymbol<FirCallableDeclaration> symbol = firCallableDeclaration.getSymbol();
            if (symbol == null) {
                x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol<*>");
                return;
            }
            FirCallableDeclaration firCallableDeclaration2 = (FirCallableDeclaration) ((FirCallableSymbol) pair3.getSecond()).getFir();
            while (true) {
                FirCallableDeclaration originalForSubstitutionOverrideAttr2 = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration2) || (firCallableDeclaration2.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration2) : null;
                if (originalForSubstitutionOverrideAttr2 == null) {
                    break;
                } else {
                    firCallableDeclaration2 = originalForSubstitutionOverrideAttr2;
                }
            }
            FirCallableSymbol<FirCallableDeclaration> symbol2 = firCallableDeclaration2.getSymbol();
            if (symbol2 == null) {
                x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol<*>");
                return;
            }
            ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag(symbol);
            ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag2 = ClassMembersKt.containingClassLookupTag(symbol2);
            if (!Intrinsics.areEqual(coneClassLikeLookupTagContainingClassLookupTag, coneClassLikeLookupTagContainingClassLookupTag2)) {
                ConeClassLikeLookupTag lookupTag = firClass.getSymbol().getLookupTag();
                if (Intrinsics.areEqual(coneClassLikeLookupTagContainingClassLookupTag, lookupTag)) {
                    KtSourceElement source = symbol.getSource();
                    if ((source != null ? source.getKind() : null) instanceof KtRealSourceElementKind) {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) symbol.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getCONFLICTING_OVERLOADS(), (Object) TuplesKt.toList(pair3), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                    }
                }
                if (Intrinsics.areEqual(coneClassLikeLookupTagContainingClassLookupTag2, lookupTag)) {
                    KtSourceElement source2 = symbol2.getSource();
                    if ((source2 != null ? source2.getKind() : null) instanceof KtRealSourceElementKind) {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) symbol2.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getCONFLICTING_OVERLOADS(), (Object) TuplesKt.toList(pair3), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                    }
                }
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firClass.getSource(), (KtDiagnosticFactory2) FirErrors.INSTANCE.getCONFLICTING_INHERITED_MEMBERS(), (Object) firClass.getSymbol(), (Object) TuplesKt.toList(pair3), (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
            }
        }
    }

    private final void checkDifferentNamesForTheSameParameterInSupertypes(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirClass firClass, FirNamedFunctionSymbol firNamedFunctionSymbol, FirTypeScope firTypeScope) {
        final CheckerContext checkerContext2;
        final DiagnosticReporter diagnosticReporter2;
        final FirClass firClass2;
        if (firNamedFunctionSymbol instanceof FirIntersectionCallableSymbol) {
            int i = 0;
            List directOverriddenFunctions$default = FirTypeScopeKt.getDirectOverriddenFunctions$default(firTypeScope, firNamedFunctionSymbol, false, 2, null);
            int size = directOverriddenFunctions$default.size();
            while (i < size) {
                Object obj = directOverriddenFunctions$default.get(i);
                if (!((FirNamedFunctionSymbol) obj).getResolvedStatus().getHasStableParameterNames()) {
                    obj = null;
                }
                final FirNamedFunctionSymbol firNamedFunctionSymbol2 = (FirNamedFunctionSymbol) obj;
                if (firNamedFunctionSymbol2 != null) {
                    int i2 = i + 1;
                    int size2 = directOverriddenFunctions$default.size();
                    while (i2 < size2) {
                        Object obj2 = directOverriddenFunctions$default.get(i2);
                        if (!((FirNamedFunctionSymbol) obj2).getResolvedStatus().getHasStableParameterNames()) {
                            obj2 = null;
                        }
                        final FirNamedFunctionSymbol firNamedFunctionSymbol3 = (FirNamedFunctionSymbol) obj2;
                        if (firNamedFunctionSymbol3 == null) {
                            checkerContext2 = checkerContext;
                            diagnosticReporter2 = diagnosticReporter;
                            firClass2 = firClass;
                        } else {
                            checkerContext2 = checkerContext;
                            diagnosticReporter2 = diagnosticReporter;
                            firClass2 = firClass;
                            DeclarationUtilsKt.checkValueParameterNamesWith(firNamedFunctionSymbol2, firNamedFunctionSymbol3, (Function3<? super FirValueParameterSymbol, ? super FirValueParameterSymbol, ? super Integer, Unit>) new Function3() { // from class: j85
                                public final Object invoke(Object obj3, Object obj4, Object obj5) {
                                    return FirImplementationMismatchChecker.d(checkerContext2, diagnosticReporter2, firClass2, firNamedFunctionSymbol2, firNamedFunctionSymbol3, (FirValueParameterSymbol) obj3, (FirValueParameterSymbol) obj4, ((Integer) obj5).intValue());
                                }
                            });
                        }
                        i2++;
                        checkerContext = checkerContext2;
                        diagnosticReporter = diagnosticReporter2;
                        firClass = firClass2;
                    }
                }
                i++;
                checkerContext = checkerContext;
                diagnosticReporter = diagnosticReporter;
                firClass = firClass;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void checkInheritanceClash(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirClass firClass, TypeCheckerState typeCheckerState, FirCallableSymbol<?> firCallableSymbol, FirTypeScope firTypeScope) {
        FirClass firClass2;
        List intersections;
        FirCallableSymbol<?> next;
        Pair pair;
        boolean z;
        ConeKotlinType coneKotlinType;
        boolean z2;
        FirCallableSymbol<?> firCallableSymbol2;
        FirCallableSymbol<?> firCallableSymbol3;
        ConeClassLikeLookupTag containingClass;
        Object next2;
        List intersections2;
        if (ClassMembersKt.isSubstitutionOverride(firCallableSymbol)) {
            return;
        }
        Object obj = null;
        if (ClassMembersKt.getDelegatedWrapperData(firCallableSymbol) != null) {
            List directOverriddenMembers$default = FirTypeScopeKt.getDirectOverriddenMembers$default(firTypeScope, firCallableSymbol, false, 2, null);
            Iterator it = directOverriddenMembers$default.iterator();
            do {
                if (!it.hasNext()) {
                    next2 = null;
                    break;
                }
                next2 = it.next();
            } while (!(((FirCallableSymbol) next2) instanceof FirIntersectionCallableSymbol));
            DeclarationSymbolMarker declarationSymbolMarker = (FirCallableSymbol) next2;
            if (declarationSymbolMarker == null || (intersections2 = ((FirIntersectionCallableSymbol) declarationSymbolMarker).getIntersections()) == null) {
                intersections2 = directOverriddenMembers$default;
            }
            intersections = CollectionsKt.plus(intersections2, firCallableSymbol);
            firClass2 = firClass;
        } else {
            if (!(firCallableSymbol instanceof FirIntersectionCallableSymbol)) {
                return;
            }
            firClass2 = firClass;
            if (checkInheritanceClash$isTrivialIntersectionOverride(firCallableSymbol, firClass2, firTypeScope)) {
                return;
            } else {
                intersections = ((FirIntersectionCallableSymbol) firCallableSymbol).getIntersections();
            }
        }
        Collection<FirCallableSymbol<?>> collection = intersections;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection, 10));
        for (FirCallableSymbol<?> firCallableSymbol4 : collection) {
            arrayList.add(TuplesKt.to(firCallableSymbol4, checkerContext.getReturnTypeCalculator().tryCalculateReturnType(firCallableSymbol4).getConeType()));
        }
        if (!arrayList.isEmpty()) {
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                if (((Pair) it2.next()).getSecond() instanceof ConeErrorType) {
                    return;
                }
            }
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator<FirCallableSymbol<?>> it3 = intersections.iterator();
        while (true) {
            if (!it3.hasNext()) {
                next = null;
                break;
            }
            next = it3.next();
            DelegatedWrapperData delegatedWrapperData = ClassMembersKt.getDelegatedWrapperData(next);
            if (Intrinsics.areEqual((delegatedWrapperData == null || (containingClass = delegatedWrapperData.getContainingClass()) == null) ? null : containingClass.getClassId(), FirDeclarationUtilKt.getClassId(firClass2))) {
                break;
            } else if (next.getResolvedStatus().getModality() != Modality.ABSTRACT) {
                arrayList2.add(next);
            }
        }
        if (!arrayList.isEmpty()) {
            Iterator it4 = arrayList.iterator();
            pair = null;
            while (true) {
                if (!it4.hasNext()) {
                    z = false;
                    break;
                }
                Pair pair2 = (Pair) it4.next();
                FirCallableSymbol firCallableSymbol5 = (FirCallableSymbol) pair2.component1();
                ConeKotlinType coneKotlinType2 = (ConeKotlinType) pair2.component2();
                if (arrayList.isEmpty()) {
                    z2 = true;
                    break;
                }
                Iterator it5 = arrayList.iterator();
                while (true) {
                    if (!it5.hasNext()) {
                        z2 = true;
                        break;
                        break;
                    }
                    Pair pair3 = (Pair) it5.next();
                    FirCallableSymbol firCallableSymbol6 = (FirCallableSymbol) pair3.component1();
                    ConeKotlinType coneKotlinType3 = (ConeKotlinType) pair3.component2();
                    boolean zCheckInheritanceClash$canOverride = checkInheritanceClash$canOverride(this, checkerContext, typeCheckerState, firCallableSymbol5, coneKotlinType2, firCallableSymbol6, coneKotlinType3);
                    if (zCheckInheritanceClash$canOverride || pair != null) {
                        coneKotlinType = coneKotlinType2;
                    } else {
                        FirCallableSymbol firCallableSymbol7 = firCallableSymbol5;
                        coneKotlinType = coneKotlinType2;
                        firCallableSymbol5 = firCallableSymbol7;
                        if (!checkInheritanceClash$canOverride(this, checkerContext, typeCheckerState, firCallableSymbol6, coneKotlinType3, firCallableSymbol7, coneKotlinType)) {
                            pair = TuplesKt.to(firCallableSymbol5, firCallableSymbol6);
                        }
                    }
                    if (!zCheckInheritanceClash$canOverride) {
                        z2 = false;
                        break;
                    }
                    coneKotlinType2 = coneKotlinType;
                }
                if (z2) {
                    z = true;
                    break;
                }
            }
        } else {
            z = false;
            pair = null;
        }
        if (pair != null) {
            if (z) {
                pair = null;
            }
            if (pair != null) {
                checkInheritanceClash$reportTypeMismatch(checkerContext, diagnosticReporter, firClass2, (FirCallableSymbol) pair.component1(), (FirCallableSymbol) pair.component2(), false);
                return;
            }
        }
        if (next == null && arrayList2.isEmpty()) {
            return;
        }
        if (next == null) {
            FirCallableSymbol<?> firCallableSymbol8 = (FirCallableSymbol) CollectionsKt.singleOrNull(arrayList2);
            if (firCallableSymbol8 == null) {
                return;
            } else {
                firCallableSymbol2 = firCallableSymbol8;
            }
        } else {
            firCallableSymbol2 = next;
        }
        ConeKotlinType coneType = checkerContext.getReturnTypeCalculator().tryCalculateReturnType(firCallableSymbol2).getConeType();
        Iterator it6 = arrayList.iterator();
        while (true) {
            if (!it6.hasNext()) {
                firCallableSymbol3 = firCallableSymbol2;
                break;
            }
            Object next3 = it6.next();
            Pair pair4 = (Pair) next3;
            firCallableSymbol3 = firCallableSymbol2;
            if (!checkInheritanceClash$canOverride(this, checkerContext, typeCheckerState, firCallableSymbol3, coneType, (FirCallableSymbol) pair4.component1(), (ConeKotlinType) pair4.component2())) {
                obj = next3;
                break;
            }
            firCallableSymbol2 = firCallableSymbol3;
        }
        Pair pair5 = (Pair) obj;
        if (pair5 == null) {
            return;
        }
        checkInheritanceClash$reportTypeMismatch(checkerContext, diagnosticReporter, firClass, firCallableSymbol3, (FirCallableSymbol) pair5.component1(), next != null);
    }

    private static final boolean checkInheritanceClash$canOverride(FirImplementationMismatchChecker firImplementationMismatchChecker, CheckerContext checkerContext, TypeCheckerState typeCheckerState, FirCallableSymbol<?> firCallableSymbol, ConeKotlinType coneKotlinType, FirCallableSymbol<?> firCallableSymbol2, ConeKotlinType coneKotlinType2) {
        ConeKotlinType coneKotlinTypeSubstituteTypeParameters = firImplementationMismatchChecker.substituteTypeParameters(checkerContext, coneKotlinType, firCallableSymbol, firCallableSymbol2);
        return ((firCallableSymbol2 instanceof FirPropertySymbol) && ((FirPropertySymbol) firCallableSymbol2).isVar()) ? AbstractTypeChecker.INSTANCE.equalTypes(typeCheckerState, coneKotlinTypeSubstituteTypeParameters, coneKotlinType2) : AbstractTypeChecker.isSubtypeOf$default(AbstractTypeChecker.INSTANCE, typeCheckerState, coneKotlinTypeSubstituteTypeParameters, coneKotlinType2, false, 8, (Object) null);
    }

    private static final boolean checkInheritanceClash$isTrivialIntersectionOverride(FirCallableSymbol<?> firCallableSymbol, FirClass firClass, FirTypeScope firTypeScope) {
        CallableId callableId = firCallableSymbol.getCallableId();
        return !Intrinsics.areEqual(callableId != null ? callableId.getClassId() : null, FirDeclarationUtilKt.getClassId(firClass)) || org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt.isTrivialIntersection(new MemberWithBaseScope(firCallableSymbol, firTypeScope));
    }

    private static final void checkInheritanceClash$reportTypeMismatch(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirClass firClass, FirCallableSymbol<?> firCallableSymbol, FirCallableSymbol<?> firCallableSymbol2, boolean z) {
        KtDiagnosticFactory2<FirCallableSymbol<?>, FirCallableSymbol<?>> return_type_mismatch_by_delegation;
        if (!(firCallableSymbol instanceof FirPropertySymbol) || !(firCallableSymbol2 instanceof FirPropertySymbol)) {
            return_type_mismatch_by_delegation = z ? FirErrors.INSTANCE.getRETURN_TYPE_MISMATCH_BY_DELEGATION() : FirErrors.INSTANCE.getRETURN_TYPE_MISMATCH_ON_INHERITANCE();
        } else if (((FirPropertySymbol) firCallableSymbol).isVar() || ((FirPropertySymbol) firCallableSymbol2).isVar()) {
            return_type_mismatch_by_delegation = FirErrors.INSTANCE.getVAR_TYPE_MISMATCH_ON_INHERITANCE();
        } else {
            return_type_mismatch_by_delegation = z ? FirErrors.INSTANCE.getPROPERTY_TYPE_MISMATCH_BY_DELEGATION() : FirErrors.INSTANCE.getPROPERTY_TYPE_MISMATCH_ON_INHERITANCE();
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firClass.getSource(), (KtDiagnosticFactory2) return_type_mismatch_by_delegation, (Object) firCallableSymbol, (Object) firCallableSymbol2, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
    }

    private final void checkValOverridesVar(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirClass firClass, FirVariableSymbol<?> firVariableSymbol, FirTypeScope firTypeScope) {
        Object next;
        if (firVariableSymbol instanceof FirPropertySymbol) {
            FirPropertySymbol firPropertySymbol = (FirPropertySymbol) firVariableSymbol;
            if (firPropertySymbol.isVar() || ClassMembersKt.getDelegatedWrapperData(firVariableSymbol) == null) {
                return;
            }
            Iterator<T> it = FirTypeScopeKt.getDirectOverriddenProperties(firTypeScope, firPropertySymbol, true).iterator();
            do {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
            } while (!((FirPropertySymbol) next).isVar());
            FirPropertySymbol firPropertySymbol2 = (FirPropertySymbol) next;
            if (firPropertySymbol2 == null) {
                return;
            }
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firClass.getSource(), (KtDiagnosticFactory2) FirErrors.INSTANCE.getVAR_OVERRIDDEN_BY_VAL_BY_DELEGATION(), (Object) firVariableSymbol, (Object) firPropertySymbol2, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
        }
    }

    private final List<FirCallableSymbol<?>> collectCallablesNamed(final CheckerContext checkerContext, FirTypeScope firTypeScope, Name name, FirClass firClass) {
        final ArrayList arrayList = new ArrayList();
        firTypeScope.processFunctionsByName(name, new Function1() { // from class: k85
            public final Object invoke(Object obj) {
                return FirImplementationMismatchChecker.c(checkerContext, arrayList, (FirNamedFunctionSymbol) obj);
            }
        });
        firTypeScope.processPropertiesByName(name, new Function1() { // from class: l85
            public final Object invoke(Object obj) {
                return FirImplementationMismatchChecker.e(checkerContext, arrayList, (FirVariableSymbol) obj);
            }
        });
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (FirVisibilityCheckerKt.isVisibleInClass((FirCallableSymbol) obj, firClass.getSymbol())) {
                arrayList2.add(obj);
            }
        }
        return arrayList2;
    }

    public static Unit d(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirClass firClass, FirNamedFunctionSymbol firNamedFunctionSymbol, FirNamedFunctionSymbol firNamedFunctionSymbol2, FirValueParameterSymbol firValueParameterSymbol, FirValueParameterSymbol firValueParameterSymbol2, int i) {
        firValueParameterSymbol.getClass();
        firValueParameterSymbol2.getClass();
        KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firClass.getSource(), (KtDiagnosticFactory4<FirValueParameterSymbol, FirValueParameterSymbol, Integer, List>) ((KtDiagnosticFactory4<Object, Object, Object, Object>) FirErrors.INSTANCE.getDIFFERENT_NAMES_FOR_THE_SAME_PARAMETER_IN_SUPERTYPES()), firValueParameterSymbol, firValueParameterSymbol2, Integer.valueOf(i), CollectionsKt.listOf(new FirNamedFunctionSymbol[]{firNamedFunctionSymbol, firNamedFunctionSymbol2}), (128 & 128) != 0 ? null : null);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Unit e(CheckerContext checkerContext, List list, FirVariableSymbol firVariableSymbol) {
        firVariableSymbol.getClass();
        if (firVariableSymbol instanceof FirIntersectionOverridePropertySymbol) {
            List list2 = list;
            Iterator<T> it = org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt.getNonSubsumedOverriddenSymbols(checkerContext, (FirIntersectionCallableSymbol) firVariableSymbol).iterator();
            while (it.hasNext()) {
                FirCallableSymbol firCallableSymbol = (FirCallableSymbol) it.next();
                FirNamedFunctionSymbol firNamedFunctionSymbol = firCallableSymbol instanceof FirNamedFunctionSymbol ? (FirNamedFunctionSymbol) firCallableSymbol : null;
                if (firNamedFunctionSymbol != null) {
                    list2.add(firNamedFunctionSymbol);
                }
            }
        } else {
            list.add(firVariableSymbol);
        }
        return Unit.INSTANCE;
    }

    private final ConeKotlinType substituteTypeParameters(CheckerContext checkerContext, ConeKotlinType coneKotlinType, FirCallableSymbol<?> firCallableSymbol, FirCallableSymbol<?> firCallableSymbol2) {
        List<FirTypeParameterSymbol> typeParameterSymbols = firCallableSymbol.getTypeParameterSymbols();
        List<FirTypeParameterSymbol> typeParameterSymbols2 = firCallableSymbol2.getTypeParameterSymbols();
        Iterator<T> it = typeParameterSymbols.iterator();
        Iterator<T> it2 = typeParameterSymbols2.iterator();
        ArrayList arrayList = new ArrayList(Math.min(CollectionsKt.collectionSizeOrDefault(typeParameterSymbols, 10), CollectionsKt.collectionSizeOrDefault(typeParameterSymbols2, 10)));
        while (it.hasNext() && it2.hasNext()) {
            arrayList.add(TuplesKt.to((FirTypeParameterSymbol) it.next(), FirNestedClassifierScopeKt.toConeType((FirTypeParameterSymbol) it2.next())));
        }
        return ConeSubstitutorByMapKt.substitutorByMap$default(MapsKt.toMap(arrayList), checkerContext.getSession(), false, 4, null).substituteOrSelf(coneKotlinType);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirClass firClass) {
        ClassKind classKind;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firClass.getClass();
        KtSourceElement source = firClass.getSource();
        if (source == null) {
            return;
        }
        KtSourceElementKind kind = source.getKind();
        if (!(kind instanceof KtFakeSourceElementKind) || Intrinsics.areEqual(kind, KtFakeSourceElementKind.EnumInitializer.INSTANCE)) {
            if (((firClass instanceof FirRegularClass) && firClass.getStatus().isExpect()) || (classKind = firClass.getClassKind()) == ClassKind.ANNOTATION_CLASS || classKind == ClassKind.ENUM_CLASS) {
                return;
            }
            final TypeCheckerState typeCheckerStateNewTypeCheckerState = TypeComponentsKt.getTypeContext(checkerContext.getSession()).newTypeCheckerState(false, false, LanguageVersionUtilsKt.isEnabled(checkerContext, LanguageFeature.AllowDnnTypeOverridingFlexibleType));
            final FirTypeScope firTypeScopeUnsubstitutedScope = FirHelpersKt.unsubstitutedScope(checkerContext, firClass);
            final DeduplicatingDiagnosticReporter deduplicatingDiagnosticReporterDeduplicating = DeduplicatingDiagnosticReporterKt.deduplicating(diagnosticReporter);
            for (Name name : firTypeScopeUnsubstitutedScope.getCallableNames()) {
                final FirImplementationMismatchChecker firImplementationMismatchChecker = this;
                final CheckerContext checkerContext2 = checkerContext;
                final FirClass firClass2 = firClass;
                firTypeScopeUnsubstitutedScope.processFunctionsByName(name, new Function1() { // from class: m85
                    public final Object invoke(Object obj) {
                        return FirImplementationMismatchChecker.check$lambda$0$0(this.b, checkerContext2, deduplicatingDiagnosticReporterDeduplicating, firClass2, typeCheckerStateNewTypeCheckerState, firTypeScopeUnsubstitutedScope, (FirNamedFunctionSymbol) obj);
                    }
                });
                firTypeScopeUnsubstitutedScope.processPropertiesByName(name, new Function1() { // from class: n85
                    public final Object invoke(Object obj) {
                        return FirImplementationMismatchChecker.check$lambda$0$1(this.b, checkerContext2, deduplicatingDiagnosticReporterDeduplicating, firClass2, typeCheckerStateNewTypeCheckerState, firTypeScopeUnsubstitutedScope, (FirVariableSymbol) obj);
                    }
                });
                firImplementationMismatchChecker.checkConflictingMembers(checkerContext2, deduplicatingDiagnosticReporterDeduplicating, firClass2, firTypeScopeUnsubstitutedScope, name);
                this = firImplementationMismatchChecker;
                checkerContext = checkerContext2;
                firClass = firClass2;
            }
        }
    }

    private FirImplementationMismatchChecker(MppCheckerKind mppCheckerKind) {
        super(mppCheckerKind);
    }
}
