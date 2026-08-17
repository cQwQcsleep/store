package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.EffectiveVisibility;
import org.jetbrains.kotlin.descriptors.RelationToType;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory4;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.analysis.checkers.FirVisibilityHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.PermissivenessForExposedVisibility;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvedDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameterKind;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.extensions.FirScriptResolutionHacksComponent;
import org.jetbrains.kotlin.fir.extensions.FirScriptResolutionHacksComponentKt;
import org.jetbrains.kotlin.fir.resolve.ContainingClassUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirLocalPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeStarProjection;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010#\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003:\u0001;B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ-\u0010\u000e\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u000fH\u0002R\u00020\nR\u00020\bj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0010J-\u0010\u0011\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u000fH\u0002R\u00020\nR\u00020\bj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0010J5\u0010\u0012\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002R\u00020\nR\u00020\bj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0016J-\u0010\u0017\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0018H\u0002R\u00020\nR\u00020\bj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0019J-\u0010\u001a\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u001bH\u0002R\u00020\nR\u00020\bj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u001cJ-\u0010\u001d\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u001eH\u0002R\u00020\nR\u00020\bj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u001fJ1\u0010 \u001a\u00020\u0007*\u00020!2\u0006\u0010\"\u001a\u00020\u0015H\u0002R\u00020\nR\u00020\bj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010#J9\u0010$\u001a\u00020\u00072\b\u0010%\u001a\u0004\u0018\u00010&2\b\u0010'\u001a\u0004\u0018\u00010(H\u0002R\u00020\nR\u00020\bj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010)JA\u0010*\u001a\u0004\u0018\u00010+*\u00020,2\u0006\u0010-\u001a\u00020\u00152\b\b\u0002\u0010.\u001a\u00020/2\u000e\b\u0002\u00100\u001a\b\u0012\u0004\u0012\u00020,01H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u00102J4\u00103\u001a\u00020+2\n\u00104\u001a\u0006\u0012\u0002\b\u0003052\u0006\u00106\u001a\u00020\u00152\u0006\u00107\u001a\u00020\u00152\u0006\u00108\u001a\u00020/2\u0006\u00109\u001a\u00020:H\u0002¨\u0006<"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirExposedVisibilityDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirBasicDeclarationChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)V", "checkClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;)V", "checkSupertypes", "checkParameterBounds", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRefsOwner;", "visibility", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRefsOwner;Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;)V", "checkTypeAlias", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;)V", "checkFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/declarations/FirFunction;)V", "checkProperty", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/declarations/FirProperty;)V", "checkExposure", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "declarationVisibility", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;)V", "checkMemberReceiver", "typeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "memberDeclaration", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/types/FirTypeRef;Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;)V", "findVisibilityExposure", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirExposedVisibilityDeclarationChecker$SymbolWithRelation;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "base", "ignoreInternalExposure", Argument.Delimiters.none, "visitedTypes", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;ZLjava/util/Set;)Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirExposedVisibilityDeclarationChecker$SymbolWithRelation;", "symbolWithRelation", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "symbolVisibility", "baseVisibility", "fromTypeArgument", "permissiveness", "Lorg/jetbrains/kotlin/fir/analysis/checkers/PermissivenessForExposedVisibility;", "SymbolWithRelation", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirExposedVisibilityDeclarationChecker extends FirDeclarationChecker<FirDeclaration> {
    public static final FirExposedVisibilityDeclarationChecker INSTANCE = new FirExposedVisibilityDeclarationChecker();

    @Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B3\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0005¢\u0006\u0004\b\u000b\u0010\fJQ\u0010\u0016\u001a\u00020\u00172\"\u0010\u001c\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0003\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00050\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fR\u00020\u0018R\u00020\u001aj\u0006\u0010\u0019\u001a\u00020\u0018j\u0006\u0010\u001b\u001a\u00020\u001a¢\u0006\u0002\u0010 J\r\u0010!\u001a\u0006\u0012\u0002\b\u00030\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0005HÆ\u0003J\t\u0010#\u001a\u00020\u0007HÆ\u0003J\t\u0010$\u001a\u00020\tHÆ\u0003J\t\u0010%\u001a\u00020\u0005HÆ\u0003J?\u0010&\u001a\u00020\u00002\f\b\u0002\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u0005HÆ\u0001J\u0014\u0010'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010*\u001a\u00020+HÖ\u0081\u0004J\n\u0010,\u001a\u00020-HÖ\u0081\u0004R\u0015\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010¨\u0006."}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirExposedVisibilityDeclarationChecker$SymbolWithRelation;", Argument.Delimiters.none, "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "symbolVisibility", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;", "relation", "Lorg/jetbrains/kotlin/descriptors/RelationToType;", "permissiveness", "Lorg/jetbrains/kotlin/fir/analysis/checkers/PermissivenessForExposedVisibility;", "baseVisibility", "<init>", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;Lorg/jetbrains/kotlin/descriptors/RelationToType;Lorg/jetbrains/kotlin/fir/analysis/checkers/PermissivenessForExposedVisibility;Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;)V", "getSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "getSymbolVisibility", "()Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;", "getRelation", "()Lorg/jetbrains/kotlin/descriptors/RelationToType;", "getPermissiveness", "()Lorg/jetbrains/kotlin/fir/analysis/checkers/PermissivenessForExposedVisibility;", "getBaseVisibility", "report", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "c", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "factory", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory4;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory4;Lorg/jetbrains/kotlin/KtSourceElement;)V", "component1", "component2", "component3", "component4", "component5", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class SymbolWithRelation {
        private final EffectiveVisibility baseVisibility;
        private final PermissivenessForExposedVisibility permissiveness;
        private final RelationToType relation;
        private final FirClassLikeSymbol<?> symbol;
        private final EffectiveVisibility symbolVisibility;

        public SymbolWithRelation(FirClassLikeSymbol<?> firClassLikeSymbol, EffectiveVisibility effectiveVisibility, RelationToType relationToType, PermissivenessForExposedVisibility permissivenessForExposedVisibility, EffectiveVisibility effectiveVisibility2) {
            firClassLikeSymbol.getClass();
            effectiveVisibility.getClass();
            relationToType.getClass();
            permissivenessForExposedVisibility.getClass();
            effectiveVisibility2.getClass();
            this.symbol = firClassLikeSymbol;
            this.symbolVisibility = effectiveVisibility;
            this.relation = relationToType;
            this.permissiveness = permissivenessForExposedVisibility;
            this.baseVisibility = effectiveVisibility2;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ SymbolWithRelation copy$default(SymbolWithRelation symbolWithRelation, FirClassLikeSymbol firClassLikeSymbol, EffectiveVisibility effectiveVisibility, RelationToType relationToType, PermissivenessForExposedVisibility permissivenessForExposedVisibility, EffectiveVisibility effectiveVisibility2, int i, Object obj) {
            if ((i & 1) != 0) {
                firClassLikeSymbol = symbolWithRelation.symbol;
            }
            if ((i & 2) != 0) {
                effectiveVisibility = symbolWithRelation.symbolVisibility;
            }
            if ((i & 4) != 0) {
                relationToType = symbolWithRelation.relation;
            }
            if ((i & 8) != 0) {
                permissivenessForExposedVisibility = symbolWithRelation.permissiveness;
            }
            if ((i & 16) != 0) {
                effectiveVisibility2 = symbolWithRelation.baseVisibility;
            }
            EffectiveVisibility effectiveVisibility3 = effectiveVisibility2;
            RelationToType relationToType2 = relationToType;
            return symbolWithRelation.copy(firClassLikeSymbol, effectiveVisibility, relationToType2, permissivenessForExposedVisibility, effectiveVisibility3);
        }

        public final FirClassLikeSymbol<?> component1() {
            return this.symbol;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final EffectiveVisibility getSymbolVisibility() {
            return this.symbolVisibility;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final RelationToType getRelation() {
            return this.relation;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final PermissivenessForExposedVisibility getPermissiveness() {
            return this.permissiveness;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final EffectiveVisibility getBaseVisibility() {
            return this.baseVisibility;
        }

        public final SymbolWithRelation copy(FirClassLikeSymbol<?> symbol, EffectiveVisibility symbolVisibility, RelationToType relation, PermissivenessForExposedVisibility permissiveness, EffectiveVisibility baseVisibility) {
            symbol.getClass();
            symbolVisibility.getClass();
            relation.getClass();
            permissiveness.getClass();
            baseVisibility.getClass();
            return new SymbolWithRelation(symbol, symbolVisibility, relation, permissiveness, baseVisibility);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SymbolWithRelation)) {
                return false;
            }
            SymbolWithRelation symbolWithRelation = (SymbolWithRelation) other;
            return Intrinsics.areEqual(this.symbol, symbolWithRelation.symbol) && Intrinsics.areEqual(this.symbolVisibility, symbolWithRelation.symbolVisibility) && this.relation == symbolWithRelation.relation && this.permissiveness == symbolWithRelation.permissiveness && Intrinsics.areEqual(this.baseVisibility, symbolWithRelation.baseVisibility);
        }

        public final EffectiveVisibility getBaseVisibility() {
            return this.baseVisibility;
        }

        public final PermissivenessForExposedVisibility getPermissiveness() {
            return this.permissiveness;
        }

        public final RelationToType getRelation() {
            return this.relation;
        }

        public final FirClassLikeSymbol<?> getSymbol() {
            return this.symbol;
        }

        public final EffectiveVisibility getSymbolVisibility() {
            return this.symbolVisibility;
        }

        public int hashCode() {
            return (((((((this.symbol.hashCode() * 31) + this.symbolVisibility.hashCode()) * 31) + this.relation.hashCode()) * 31) + this.permissiveness.hashCode()) * 31) + this.baseVisibility.hashCode();
        }

        public final void report(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, KtDiagnosticFactory4<EffectiveVisibility, FirClassLikeSymbol<?>, RelationToType, EffectiveVisibility> ktDiagnosticFactory4, KtSourceElement ktSourceElement) {
            checkerContext.getClass();
            diagnosticReporter.getClass();
            ktDiagnosticFactory4.getClass();
            boolean z = this.permissiveness == PermissivenessForExposedVisibility.PACKAGE_PRIVATE_FROM_INTERNAL;
            if (z && LanguageVersionUtilsKt.isDisabled(checkerContext, LanguageFeature.ForbidExposingPackagePrivateInInternal)) {
                return;
            }
            KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, z ? FirErrors.INSTANCE.getEXPOSED_PACKAGE_PRIVATE_TYPE_FROM_INTERNAL_WARNING() : ktDiagnosticFactory4, this.baseVisibility, this.symbol, this.relation, this.symbolVisibility, ktDiagnosticFactory4.getDefaultPositioningStrategy());
        }

        public String toString() {
            return "SymbolWithRelation(symbol=" + this.symbol + ", symbolVisibility=" + this.symbolVisibility + ", relation=" + this.relation + ", permissiveness=" + this.permissiveness + ", baseVisibility=" + this.baseVisibility + ')';
        }
    }

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PermissivenessForExposedVisibility.values().length];
            try {
                iArr[PermissivenessForExposedVisibility.LESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[PermissivenessForExposedVisibility.UNKNOWN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[PermissivenessForExposedVisibility.PACKAGE_PRIVATE_FROM_INTERNAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[PermissivenessForExposedVisibility.SAME.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[PermissivenessForExposedVisibility.MORE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private FirExposedVisibilityDeclarationChecker() {
        super(MppCheckerKind.Common);
    }

    private final void checkClass(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, FirRegularClass firRegularClass) {
        EffectiveVisibility effectiveVisibility;
        checkSupertypes(diagnosticReporter, checkerContext, firRegularClass);
        FirDeclarationStatus status = firRegularClass.getStatus();
        FirResolvedDeclarationStatus firResolvedDeclarationStatus = status instanceof FirResolvedDeclarationStatus ? (FirResolvedDeclarationStatus) status : null;
        if (firResolvedDeclarationStatus == null || (effectiveVisibility = firResolvedDeclarationStatus.getEffectiveVisibility()) == null) {
            effectiveVisibility = EffectiveVisibility.Local.INSTANCE;
        }
        checkParameterBounds(diagnosticReporter, checkerContext, firRegularClass, effectiveVisibility);
    }

    private final void checkExposure(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, FirValueParameter firValueParameter, EffectiveVisibility effectiveVisibility) {
        FirExposedVisibilityDeclarationChecker firExposedVisibilityDeclarationChecker;
        CheckerContext checkerContext2;
        SymbolWithRelation symbolWithRelationFindVisibilityExposure$default;
        EffectiveVisibility effectiveVisibility2;
        EffectiveVisibility.Local local = EffectiveVisibility.Local.INSTANCE;
        if (Intrinsics.areEqual(effectiveVisibility, local)) {
            firExposedVisibilityDeclarationChecker = this;
            checkerContext2 = checkerContext;
        } else {
            SymbolWithRelation symbolWithRelationFindVisibilityExposure$default2 = findVisibilityExposure$default(this, checkerContext, FirTypeUtilsKt.getConeType(firValueParameter.getReturnTypeRef()), effectiveVisibility, false, null, 12, null);
            firExposedVisibilityDeclarationChecker = this;
            checkerContext2 = checkerContext;
            if (symbolWithRelationFindVisibilityExposure$default2 != null) {
                symbolWithRelationFindVisibilityExposure$default2.report(checkerContext2, diagnosticReporter, firValueParameter.getValueParameterKind() == FirValueParameterKind.LegacyContextReceiver ? FirErrors.INSTANCE.getEXPOSED_RECEIVER_TYPE() : FirErrors.INSTANCE.getEXPOSED_PARAMETER_TYPE(), firValueParameter.getSource());
                return;
            }
        }
        FirProperty correspondingProperty = ClassMembersKt.getCorrespondingProperty(firValueParameter);
        if (correspondingProperty == null || (correspondingProperty.getSymbol() instanceof FirLocalPropertySymbol)) {
            return;
        }
        FirDeclarationStatus status = correspondingProperty.getStatus();
        FirResolvedDeclarationStatus firResolvedDeclarationStatus = status instanceof FirResolvedDeclarationStatus ? (FirResolvedDeclarationStatus) status : null;
        EffectiveVisibility effectiveVisibility3 = (firResolvedDeclarationStatus == null || (effectiveVisibility2 = firResolvedDeclarationStatus.getEffectiveVisibility()) == null) ? local : effectiveVisibility2;
        if (Intrinsics.areEqual(effectiveVisibility3, local) || (symbolWithRelationFindVisibilityExposure$default = findVisibilityExposure$default(firExposedVisibilityDeclarationChecker, checkerContext2, FirTypeUtilsKt.getConeType(correspondingProperty.getReturnTypeRef()), effectiveVisibility3, false, null, 12, null)) == null) {
            return;
        }
        symbolWithRelationFindVisibilityExposure$default.report(checkerContext2, diagnosticReporter, FirErrors.INSTANCE.getEXPOSED_PROPERTY_TYPE_IN_CONSTRUCTOR_ERROR(), firValueParameter.getSource());
    }

    private final void checkFunction(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, FirFunction firFunction) {
        EffectiveVisibility effectiveVisibility;
        FirExposedVisibilityDeclarationChecker firExposedVisibilityDeclarationChecker;
        CheckerContext checkerContext2;
        KtSourceElement source = firFunction.getSource();
        if ((source != null ? source.getKind() : null) instanceof KtFakeSourceElementKind) {
            return;
        }
        FirDeclarationStatus status = firFunction.getStatus();
        FirResolvedDeclarationStatus firResolvedDeclarationStatus = status instanceof FirResolvedDeclarationStatus ? (FirResolvedDeclarationStatus) status : null;
        if (firResolvedDeclarationStatus == null || (effectiveVisibility = firResolvedDeclarationStatus.getEffectiveVisibility()) == null) {
            effectiveVisibility = EffectiveVisibility.Local.INSTANCE;
        }
        boolean z = firFunction instanceof FirConstructor;
        if (z && firFunction.getStatus().isFromSealedClass()) {
            effectiveVisibility = EffectiveVisibility.PrivateInClass.INSTANCE;
        }
        EffectiveVisibility effectiveVisibility2 = effectiveVisibility;
        boolean zAreEqual = Intrinsics.areEqual(effectiveVisibility2, EffectiveVisibility.Local.INSTANCE);
        if (firFunction instanceof FirPropertyAccessor) {
            firExposedVisibilityDeclarationChecker = this;
            checkerContext2 = checkerContext;
        } else {
            if (zAreEqual || z) {
                firExposedVisibilityDeclarationChecker = this;
                checkerContext2 = checkerContext;
            } else {
                firExposedVisibilityDeclarationChecker = this;
                checkerContext2 = checkerContext;
                SymbolWithRelation symbolWithRelationFindVisibilityExposure$default = findVisibilityExposure$default(firExposedVisibilityDeclarationChecker, checkerContext2, FirTypeUtilsKt.getConeType(firFunction.getReturnTypeRef()), effectiveVisibility2, false, null, 12, null);
                if (symbolWithRelationFindVisibilityExposure$default != null) {
                    symbolWithRelationFindVisibilityExposure$default.report(checkerContext2, diagnosticReporter, FirErrors.INSTANCE.getEXPOSED_FUNCTION_RETURN_TYPE(), firFunction.getSource());
                }
            }
            Iterator<FirValueParameter> it = firFunction.getValueParameters().iterator();
            while (it.hasNext()) {
                firExposedVisibilityDeclarationChecker.checkExposure(diagnosticReporter, checkerContext2, it.next(), effectiveVisibility2);
            }
            Iterator<FirValueParameter> it2 = firFunction.getContextParameters().iterator();
            while (it2.hasNext()) {
                firExposedVisibilityDeclarationChecker.checkExposure(diagnosticReporter, checkerContext2, it2.next(), effectiveVisibility2);
            }
        }
        if (!zAreEqual) {
            FirReceiverParameter receiverParameter = firFunction.getReceiverParameter();
            firExposedVisibilityDeclarationChecker.checkMemberReceiver(diagnosticReporter, checkerContext2, receiverParameter != null ? receiverParameter.getTypeRef() : null, firFunction);
        }
        firExposedVisibilityDeclarationChecker.checkParameterBounds(diagnosticReporter, checkerContext2, firFunction, effectiveVisibility2);
    }

    private final void checkMemberReceiver(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, FirTypeRef firTypeRef, FirCallableDeclaration firCallableDeclaration) {
        EffectiveVisibility effectiveVisibility;
        SymbolWithRelation symbolWithRelationFindVisibilityExposure$default;
        if (firTypeRef == null || firCallableDeclaration == null) {
            return;
        }
        ConeKotlinType coneType = FirTypeUtilsKt.getConeType(firTypeRef);
        FirDeclarationStatus status = firCallableDeclaration.getStatus();
        FirResolvedDeclarationStatus firResolvedDeclarationStatus = status instanceof FirResolvedDeclarationStatus ? (FirResolvedDeclarationStatus) status : null;
        if (firResolvedDeclarationStatus == null || (effectiveVisibility = firResolvedDeclarationStatus.getEffectiveVisibility()) == null) {
            effectiveVisibility = EffectiveVisibility.Local.INSTANCE;
        }
        EffectiveVisibility effectiveVisibility2 = effectiveVisibility;
        if (Intrinsics.areEqual(effectiveVisibility2, EffectiveVisibility.Local.INSTANCE) || (symbolWithRelationFindVisibilityExposure$default = findVisibilityExposure$default(this, checkerContext, coneType, effectiveVisibility2, false, null, 12, null)) == null) {
            return;
        }
        symbolWithRelationFindVisibilityExposure$default.report(checkerContext, diagnosticReporter, FirErrors.INSTANCE.getEXPOSED_RECEIVER_TYPE(), firTypeRef.getSource());
    }

    private final void checkParameterBounds(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, FirTypeParameterRefsOwner firTypeParameterRefsOwner, EffectiveVisibility effectiveVisibility) {
        Pair pair;
        if (Intrinsics.areEqual(effectiveVisibility, EffectiveVisibility.Local.INSTANCE) || (firTypeParameterRefsOwner instanceof FirConstructor)) {
            return;
        }
        KtDiagnosticFactory4<EffectiveVisibility, FirClassLikeSymbol<?>, RelationToType, EffectiveVisibility> ktDiagnosticFactory4CheckParameterBounds$getDiagnosticByFeature = checkParameterBounds$getDiagnosticByFeature(checkerContext, firTypeParameterRefsOwner, LanguageFeature.ReportExposedTypeForMoreCasesOfTypeParameterBounds);
        KtDiagnosticFactory4<EffectiveVisibility, FirClassLikeSymbol<?>, RelationToType, EffectiveVisibility> ktDiagnosticFactory4CheckParameterBounds$getDiagnosticByFeature2 = checkParameterBounds$getDiagnosticByFeature(checkerContext, firTypeParameterRefsOwner, LanguageFeature.ReportExposedTypeForInternalTypeParameterBounds);
        Iterator<FirTypeParameterRef> it = firTypeParameterRefsOwner.getTypeParameters().iterator();
        while (it.hasNext()) {
            for (FirResolvedTypeRef firResolvedTypeRef : it.next().getSymbol().getResolvedBounds()) {
                SymbolWithRelation symbolWithRelationCheckParameterBounds$findVisibilityExposure = checkParameterBounds$findVisibilityExposure(firResolvedTypeRef, checkerContext, effectiveVisibility, true);
                if (symbolWithRelationCheckParameterBounds$findVisibilityExposure == null || (pair = TuplesKt.to(symbolWithRelationCheckParameterBounds$findVisibilityExposure, ktDiagnosticFactory4CheckParameterBounds$getDiagnosticByFeature)) == null) {
                    SymbolWithRelation symbolWithRelationCheckParameterBounds$findVisibilityExposure2 = checkParameterBounds$findVisibilityExposure(firResolvedTypeRef, checkerContext, effectiveVisibility, false);
                    if (symbolWithRelationCheckParameterBounds$findVisibilityExposure2 != null) {
                        pair = TuplesKt.to(symbolWithRelationCheckParameterBounds$findVisibilityExposure2, ktDiagnosticFactory4CheckParameterBounds$getDiagnosticByFeature2);
                    }
                }
                ((SymbolWithRelation) pair.component1()).report(checkerContext, diagnosticReporter, (KtDiagnosticFactory4) pair.component2(), firResolvedTypeRef.getSource());
            }
        }
    }

    private static final SymbolWithRelation checkParameterBounds$findVisibilityExposure(FirResolvedTypeRef firResolvedTypeRef, CheckerContext checkerContext, EffectiveVisibility effectiveVisibility, boolean z) {
        return findVisibilityExposure$default(INSTANCE, checkerContext, firResolvedTypeRef.getConeType(), effectiveVisibility, z, null, 8, null);
    }

    private static final KtDiagnosticFactory4<EffectiveVisibility, FirClassLikeSymbol<?>, RelationToType, EffectiveVisibility> checkParameterBounds$getDiagnosticByFeature(CheckerContext checkerContext, FirTypeParameterRefsOwner firTypeParameterRefsOwner, LanguageFeature languageFeature) {
        return (LanguageVersionUtilsKt.isEnabled(checkerContext, languageFeature) || (firTypeParameterRefsOwner instanceof FirRegularClass)) ? FirErrors.INSTANCE.getEXPOSED_TYPE_PARAMETER_BOUND() : FirErrors.INSTANCE.getEXPOSED_TYPE_PARAMETER_BOUND_DEPRECATION_WARNING();
    }

    private final void checkProperty(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, FirProperty firProperty) {
        EffectiveVisibility effectiveVisibility;
        FirScriptResolutionHacksComponent scriptResolutionHacksComponent;
        Boolean fromPrimaryConstructor = DeclarationAttributesKt.getFromPrimaryConstructor(firProperty);
        Boolean bool = Boolean.TRUE;
        if (Intrinsics.areEqual(fromPrimaryConstructor, bool) || (firProperty.getSymbol() instanceof FirLocalPropertySymbol)) {
            return;
        }
        KtSourceElement source = firProperty.getSource();
        if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.EnumGeneratedDeclaration.INSTANCE)) {
            return;
        }
        FirDeclarationStatus status = firProperty.getStatus();
        FirResolvedDeclarationStatus firResolvedDeclarationStatus = status instanceof FirResolvedDeclarationStatus ? (FirResolvedDeclarationStatus) status : null;
        if (firResolvedDeclarationStatus == null || (effectiveVisibility = firResolvedDeclarationStatus.getEffectiveVisibility()) == null) {
            effectiveVisibility = EffectiveVisibility.Local.INSTANCE;
        }
        EffectiveVisibility effectiveVisibility2 = effectiveVisibility;
        if (Intrinsics.areEqual(effectiveVisibility2, EffectiveVisibility.Local.INSTANCE) || Intrinsics.areEqual(firProperty.getOrigin(), FirDeclarationOrigin.ScriptCustomization.ResultProperty.INSTANCE)) {
            return;
        }
        if (Intrinsics.areEqual(DeclarationAttributesKt.isScriptTopLevelDeclaration(firProperty), bool) && (scriptResolutionHacksComponent = FirScriptResolutionHacksComponentKt.getScriptResolutionHacksComponent(checkerContext.getSession())) != null && scriptResolutionHacksComponent.getSkipTowerDataCleanupForTopLevelInitializers()) {
            return;
        }
        SymbolWithRelation symbolWithRelationFindVisibilityExposure$default = findVisibilityExposure$default(this, checkerContext, FirTypeUtilsKt.getConeType(firProperty.getReturnTypeRef()), effectiveVisibility2, false, null, 12, null);
        if (symbolWithRelationFindVisibilityExposure$default != null) {
            symbolWithRelationFindVisibilityExposure$default.report(checkerContext, diagnosticReporter, FirErrors.INSTANCE.getEXPOSED_PROPERTY_TYPE(), firProperty.getSource());
        }
        FirReceiverParameter receiverParameter = firProperty.getReceiverParameter();
        checkMemberReceiver(diagnosticReporter, checkerContext, receiverParameter != null ? receiverParameter.getTypeRef() : null, firProperty);
        checkParameterBounds(diagnosticReporter, checkerContext, firProperty, effectiveVisibility2);
        Iterator<FirValueParameter> it = firProperty.getContextParameters().iterator();
        while (it.hasNext()) {
            checkExposure(diagnosticReporter, checkerContext, it.next(), effectiveVisibility2);
        }
    }

    private final void checkSupertypes(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, FirRegularClass firRegularClass) {
        EffectiveVisibility effectiveVisibility;
        ConeKotlinType coneType;
        FirRegularClassSymbol regularClassSymbol;
        SymbolWithRelation symbolWithRelationFindVisibilityExposure$default;
        FirDeclarationStatus status = firRegularClass.getStatus();
        FirResolvedDeclarationStatus firResolvedDeclarationStatus = status instanceof FirResolvedDeclarationStatus ? (FirResolvedDeclarationStatus) status : null;
        if (firResolvedDeclarationStatus == null || (effectiveVisibility = firResolvedDeclarationStatus.getEffectiveVisibility()) == null) {
            effectiveVisibility = EffectiveVisibility.Local.INSTANCE;
        }
        EffectiveVisibility effectiveVisibility2 = effectiveVisibility;
        if (Intrinsics.areEqual(effectiveVisibility2, EffectiveVisibility.Local.INSTANCE)) {
            return;
        }
        List<FirTypeRef> superTypeRefs = firRegularClass.getSuperTypeRefs();
        boolean z = firRegularClass.getClassKind() == ClassKind.INTERFACE;
        for (FirTypeRef firTypeRef : superTypeRefs) {
            KtSourceElement source = firTypeRef.getSource();
            if (!Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.EnumSuperTypeRef.INSTANCE) && (regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(checkerContext, (coneType = FirTypeUtilsKt.getConeType(firTypeRef)))) != null) {
                if ((regularClassSymbol.getClassKind() == ClassKind.INTERFACE) == z && (symbolWithRelationFindVisibilityExposure$default = findVisibilityExposure$default(this, checkerContext, coneType, effectiveVisibility2, false, null, 12, null)) != null) {
                    FirErrors firErrors = FirErrors.INSTANCE;
                    KtDiagnosticFactory4<EffectiveVisibility, FirClassLikeSymbol<?>, RelationToType, EffectiveVisibility> exposed_super_interface = z ? firErrors.getEXPOSED_SUPER_INTERFACE() : firErrors.getEXPOSED_SUPER_CLASS();
                    KtSourceElement source2 = firTypeRef.getSource();
                    if (source2 == null) {
                        source2 = firRegularClass.getSource();
                    }
                    symbolWithRelationFindVisibilityExposure$default.report(checkerContext, diagnosticReporter, exposed_super_interface, source2);
                }
            }
        }
    }

    private final void checkTypeAlias(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, FirTypeAlias firTypeAlias) {
        EffectiveVisibility effectiveVisibility;
        SymbolWithRelation symbolWithRelationFindVisibilityExposure$default;
        ConeClassLikeType expandedConeType = FirDeclarationUtilKt.getExpandedConeType(firTypeAlias);
        FirDeclarationStatus status = firTypeAlias.getStatus();
        FirResolvedDeclarationStatus firResolvedDeclarationStatus = status instanceof FirResolvedDeclarationStatus ? (FirResolvedDeclarationStatus) status : null;
        if (firResolvedDeclarationStatus == null || (effectiveVisibility = firResolvedDeclarationStatus.getEffectiveVisibility()) == null) {
            effectiveVisibility = EffectiveVisibility.Local.INSTANCE;
        }
        EffectiveVisibility effectiveVisibility2 = effectiveVisibility;
        if (Intrinsics.areEqual(effectiveVisibility2, EffectiveVisibility.Local.INSTANCE)) {
            return;
        }
        checkParameterBounds(diagnosticReporter, checkerContext, firTypeAlias, effectiveVisibility2);
        if (expandedConeType == null || (symbolWithRelationFindVisibilityExposure$default = findVisibilityExposure$default(this, checkerContext, expandedConeType, effectiveVisibility2, false, null, 12, null)) == null) {
            return;
        }
        symbolWithRelationFindVisibilityExposure$default.report(checkerContext, diagnosticReporter, FirErrors.INSTANCE.getEXPOSED_TYPEALIAS_EXPANDED_TYPE(), firTypeAlias.getSource());
    }

    private final SymbolWithRelation findVisibilityExposure(CheckerContext checkerContext, ConeKotlinType coneKotlinType, EffectiveVisibility effectiveVisibility, boolean z, Set<ConeKotlinType> set) {
        ConeClassLikeType coneClassLikeType;
        EffectiveVisibility effectiveVisibility2;
        boolean z2;
        Set<ConeKotlinType> set2;
        CheckerContext checkerContext2;
        FirExposedVisibilityDeclarationChecker firExposedVisibilityDeclarationChecker;
        List<FirTypeParameterSymbol> typeParameterSymbols;
        FirTypeParameterSymbol firTypeParameterSymbol;
        List<FirResolvedTypeRef> resolvedBounds;
        CheckerContext checkerContext3;
        SymbolWithRelation symbolWithRelationFindVisibilityExposure;
        if (!set.add(coneKotlinType)) {
            return null;
        }
        if (!(coneKotlinType instanceof ConeClassLikeType)) {
            if (coneKotlinType instanceof ConeFlexibleType) {
                ConeRigidType lowerBound = ((ConeFlexibleType) coneKotlinType).getLowerBound();
                coneClassLikeType = lowerBound instanceof ConeClassLikeType ? (ConeClassLikeType) lowerBound : null;
                if (coneClassLikeType == null) {
                    return null;
                }
            }
            return null;
        }
        coneClassLikeType = (ConeClassLikeType) coneKotlinType;
        FirClassLikeSymbol<?> symbol = ToSymbolUtilsKt.toSymbol((SessionHolder) checkerContext, TypeExpansionUtilsKt.fullyExpandedType((SessionHolder) checkerContext, coneClassLikeType).getLookupTag());
        if (symbol == null) {
            return null;
        }
        if ((symbol instanceof FirRegularClassSymbol) || (symbol instanceof FirTypeAliasSymbol)) {
            EffectiveVisibility effectiveVisibility3 = symbol.getResolvedStatus().getEffectiveVisibility();
            effectiveVisibility2 = effectiveVisibility3;
        } else {
            effectiveVisibility2 = null;
        }
        if (z && Intrinsics.areEqual(effectiveVisibility2, EffectiveVisibility.Internal.INSTANCE)) {
            return null;
        }
        int i = 0;
        if (effectiveVisibility2 != null) {
            PermissivenessForExposedVisibility permissivenessForExposedVisibilityRelationForExposedVisibility = FirVisibilityHelpersKt.relationForExposedVisibility(checkerContext, effectiveVisibility2, effectiveVisibility);
            int i2 = WhenMappings.$EnumSwitchMapping$0[permissivenessForExposedVisibilityRelationForExposedVisibility.ordinal()];
            if (i2 == 1 || i2 == 2 || i2 == 3) {
                return symbolWithRelation(symbol, effectiveVisibility2, effectiveVisibility, set.size() > 1, permissivenessForExposedVisibilityRelationForExposedVisibility);
            }
            if (i2 != 4 && i2 != 5) {
                bu8.a();
                return null;
            }
        }
        FirExposedVisibilityDeclarationChecker firExposedVisibilityDeclarationChecker2 = this;
        ConeKotlinTypeProjection[] typeArguments = coneClassLikeType.getTypeArguments();
        int length = typeArguments.length;
        while (i < length) {
            ConeKotlinTypeProjection coneKotlinTypeProjection = typeArguments[i];
            if (coneKotlinTypeProjection instanceof ConeClassLikeType) {
                ConeKotlinType coneKotlinType2 = (ConeKotlinType) coneKotlinTypeProjection;
                CheckerContext checkerContext4 = checkerContext;
                z2 = z;
                set2 = set;
                SymbolWithRelation symbolWithRelationFindVisibilityExposure2 = firExposedVisibilityDeclarationChecker2.findVisibilityExposure(checkerContext4, coneKotlinType2, effectiveVisibility, z2, set2);
                if (symbolWithRelationFindVisibilityExposure2 != null) {
                    return symbolWithRelationFindVisibilityExposure2;
                }
                checkerContext3 = checkerContext4;
                firExposedVisibilityDeclarationChecker = firExposedVisibilityDeclarationChecker2;
            } else {
                z2 = z;
                set2 = set;
                CheckerContext checkerContext5 = checkerContext;
                if (!(coneKotlinTypeProjection instanceof ConeKotlinTypeProjection)) {
                    checkerContext2 = checkerContext5;
                    firExposedVisibilityDeclarationChecker = firExposedVisibilityDeclarationChecker2;
                    if (!(coneKotlinTypeProjection instanceof ConeStarProjection)) {
                        bu8.a();
                        break;
                    }
                    FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol((SessionHolder) checkerContext2, coneClassLikeType);
                    if (regularClassSymbol != null && (typeParameterSymbols = regularClassSymbol.getTypeParameterSymbols()) != null && (firTypeParameterSymbol = (FirTypeParameterSymbol) CollectionsKt.getOrNull(typeParameterSymbols, i)) != null && (resolvedBounds = firTypeParameterSymbol.getResolvedBounds()) != null) {
                        Iterator<T> it = resolvedBounds.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                checkerContext3 = checkerContext2;
                                symbolWithRelationFindVisibilityExposure = null;
                                break;
                            }
                            checkerContext3 = checkerContext2;
                            symbolWithRelationFindVisibilityExposure = INSTANCE.findVisibilityExposure(checkerContext3, ((FirResolvedTypeRef) it.next()).getConeType(), effectiveVisibility, z2, set2);
                            if (symbolWithRelationFindVisibilityExposure != null) {
                                break;
                            }
                            checkerContext2 = checkerContext3;
                        }
                        if (symbolWithRelationFindVisibilityExposure != null) {
                            return symbolWithRelationFindVisibilityExposure;
                        }
                    }
                } else {
                    SymbolWithRelation symbolWithRelationFindVisibilityExposure3 = firExposedVisibilityDeclarationChecker2.findVisibilityExposure(checkerContext5, coneKotlinTypeProjection.getType(), effectiveVisibility, z2, set2);
                    firExposedVisibilityDeclarationChecker = firExposedVisibilityDeclarationChecker2;
                    checkerContext2 = checkerContext5;
                    if (symbolWithRelationFindVisibilityExposure3 != null) {
                        return symbolWithRelationFindVisibilityExposure3;
                    }
                }
                checkerContext3 = checkerContext2;
            }
            i++;
            firExposedVisibilityDeclarationChecker2 = firExposedVisibilityDeclarationChecker;
            checkerContext = checkerContext3;
            z = z2;
            set = set2;
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SymbolWithRelation findVisibilityExposure$default(FirExposedVisibilityDeclarationChecker firExposedVisibilityDeclarationChecker, CheckerContext checkerContext, ConeKotlinType coneKotlinType, EffectiveVisibility effectiveVisibility, boolean z, Set set, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        boolean z2 = z;
        if ((i & 8) != 0) {
            set = new LinkedHashSet();
        }
        return firExposedVisibilityDeclarationChecker.findVisibilityExposure(checkerContext, coneKotlinType, effectiveVisibility, z2, set);
    }

    private final SymbolWithRelation symbolWithRelation(FirClassLikeSymbol<?> symbol, EffectiveVisibility symbolVisibility, EffectiveVisibility baseVisibility, boolean fromTypeArgument, PermissivenessForExposedVisibility permissiveness) {
        Visibility visibility = symbolVisibility.toVisibility();
        Visibility visibility2 = symbol.getRawStatus().getVisibility();
        FirClassLikeSymbol<?> firClassLikeSymbol = symbol;
        for (FirClassLikeSymbol<?> containingClassSymbol = ContainingClassUtilsKt.getContainingClassSymbol(symbol); containingClassSymbol != null && !Intrinsics.areEqual(visibility2, visibility); containingClassSymbol = ContainingClassUtilsKt.getContainingClassSymbol(containingClassSymbol)) {
            Integer numCompareTo = containingClassSymbol.getRawStatus().getVisibility().compareTo(visibility2);
            if (numCompareTo != null && numCompareTo.intValue() < 0) {
                visibility2 = containingClassSymbol.getRawStatus().getVisibility();
                firClassLikeSymbol = containingClassSymbol;
            }
        }
        RelationToType relationToTypeContainerRelation = fromTypeArgument ? RelationToType.ARGUMENT : RelationToType.CONSTRUCTOR;
        if (firClassLikeSymbol != symbol) {
            relationToTypeContainerRelation = relationToTypeContainerRelation.containerRelation();
        }
        return new SymbolWithRelation(firClassLikeSymbol, symbolVisibility, relationToTypeContainerRelation, permissiveness, baseVisibility);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firDeclaration.getClass();
        if (firDeclaration instanceof FirAnonymousFunction) {
            return;
        }
        if (firDeclaration instanceof FirTypeAlias) {
            checkTypeAlias(diagnosticReporter, checkerContext, (FirTypeAlias) firDeclaration);
            return;
        }
        if (firDeclaration instanceof FirProperty) {
            checkProperty(diagnosticReporter, checkerContext, (FirProperty) firDeclaration);
        } else if (firDeclaration instanceof FirFunction) {
            checkFunction(diagnosticReporter, checkerContext, (FirFunction) firDeclaration);
        } else if (firDeclaration instanceof FirRegularClass) {
            checkClass(diagnosticReporter, checkerContext, (FirRegularClass) firDeclaration);
        }
    }
}
