package org.jetbrains.kotlin.fir.analysis.checkers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.collections.MapsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.substitution.ChainedSubstitutorKt;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.AbbreviatedTypeAttributeKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeConflictingProjection;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjectionIn;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjectionOut;
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjectionKt;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ProjectionKind;
import org.jetbrains.kotlin.mpp.DeclarationSymbolMarker;
import org.jetbrains.kotlin.types.Variance;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\bÀ\u0002\u0018\u00002\u00020\u0001:\u0003+,-B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JG\u0010\u0004\u001a\u00020\u00052\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b2\u0006\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0011J7\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u000b2\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0002R\u00020\u0006j\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\u0014J\"\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00130\u000b*\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0002JB\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u00182\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\f0\u001a2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00130\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002JM\u0010 \u001a\u00020\u00052\u0006\u0010!\u001a\u00020\"2\u0006\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u00182\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00130\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0014\u0010#\u001a\u0010\u0012\u0004\u0012\u00020%\u0012\u0006\u0012\u0004\u0018\u00010\f0$H\u0082\bR\u0018\u0010&\u001a\u00020'*\u00020(8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b)\u0010*¨\u0006."}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/ProjectionRelationCheckerImpl;", Argument.Delimiters.none, "<init>", "()V", "doCheck", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "argumentsWithSources", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirTypeRefSource;", "abbreviatedType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "deprecation", "Lorg/jetbrains/kotlin/fir/analysis/checkers/ProjectionRelationCheckerImpl$Deprecation;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Ljava/util/List;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/analysis/checkers/ProjectionRelationCheckerImpl$Deprecation;)V", "collectPotentiallyProblematicArguments", "Lorg/jetbrains/kotlin/fir/analysis/checkers/ProjectionRelationCheckerImpl$TypeArgumentData;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Ljava/util/List;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Ljava/util/List;", "extractImmediateTypeArgumentData", ModuleXmlParser.TYPE, "previousSubstitutor", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "parametersToSources", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", CoroutineCodegenUtilKt.CONTINUATION_RESULT_FIELD_NAME, Argument.Delimiters.none, "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "collectPotentiallyProblematicArgumentsFromTypeAliasExpansion", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeAliasSymbol;", "argumentIndexToSource", "Lkotlin/Function1;", Argument.Delimiters.none, "canBeProblematic", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ProjectionKind;", "getCanBeProblematic", "(Lorg/jetbrains/kotlin/fir/types/ProjectionKind;)Z", "TypeArgumentData", "Deprecation", "ProjectionRelation", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ProjectionRelationCheckerImpl {
    public static final ProjectionRelationCheckerImpl INSTANCE = new ProjectionRelationCheckerImpl();

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/ProjectionRelationCheckerImpl$Deprecation;", Argument.Delimiters.none, "feature", "Lorg/jetbrains/kotlin/config/LanguageFeature;", "warningDiagnostic", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "<init>", "(Lorg/jetbrains/kotlin/config/LanguageFeature;Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;)V", "getFeature", "()Lorg/jetbrains/kotlin/config/LanguageFeature;", "getWarningDiagnostic", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class Deprecation {
        private final LanguageFeature feature;
        private final KtDiagnosticFactory1<ConeKotlinType> warningDiagnostic;

        public Deprecation(LanguageFeature languageFeature, KtDiagnosticFactory1<ConeKotlinType> ktDiagnosticFactory1) {
            languageFeature.getClass();
            ktDiagnosticFactory1.getClass();
            this.feature = languageFeature;
            this.warningDiagnostic = ktDiagnosticFactory1;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Deprecation copy$default(Deprecation deprecation, LanguageFeature languageFeature, KtDiagnosticFactory1 ktDiagnosticFactory1, int i, Object obj) {
            if ((i & 1) != 0) {
                languageFeature = deprecation.feature;
            }
            if ((i & 2) != 0) {
                ktDiagnosticFactory1 = deprecation.warningDiagnostic;
            }
            return deprecation.copy(languageFeature, ktDiagnosticFactory1);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final LanguageFeature getFeature() {
            return this.feature;
        }

        public final KtDiagnosticFactory1<ConeKotlinType> component2() {
            return this.warningDiagnostic;
        }

        public final Deprecation copy(LanguageFeature feature, KtDiagnosticFactory1<ConeKotlinType> warningDiagnostic) {
            feature.getClass();
            warningDiagnostic.getClass();
            return new Deprecation(feature, warningDiagnostic);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Deprecation)) {
                return false;
            }
            Deprecation deprecation = (Deprecation) other;
            return this.feature == deprecation.feature && Intrinsics.areEqual(this.warningDiagnostic, deprecation.warningDiagnostic);
        }

        public final LanguageFeature getFeature() {
            return this.feature;
        }

        public final KtDiagnosticFactory1<ConeKotlinType> getWarningDiagnostic() {
            return this.warningDiagnostic;
        }

        public int hashCode() {
            return (this.feature.hashCode() * 31) + this.warningDiagnostic.hashCode();
        }

        public String toString() {
            return "Deprecation(feature=" + this.feature + ", warningDiagnostic=" + this.warningDiagnostic + ')';
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/ProjectionRelationCheckerImpl$ProjectionRelation;", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;I)V", "Conflicting", "Redundant", "None", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public enum ProjectionRelation {
        Conflicting,
        Redundant,
        None;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<ProjectionRelation> getEntries() {
            return $ENTRIES;
        }
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0017\u001a\u00020\tHÆ\u0003J1\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0014\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001c\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010\u001d\u001a\u00020\u001eHÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001f"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/ProjectionRelationCheckerImpl$TypeArgumentData;", Argument.Delimiters.none, "constructor", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "index", Argument.Delimiters.none, "projection", "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "source", "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirTypeRefSource;", "<init>", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;ILorg/jetbrains/kotlin/fir/types/ConeTypeProjection;Lorg/jetbrains/kotlin/fir/analysis/checkers/FirTypeRefSource;)V", "getConstructor", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getIndex", "()I", "getProjection", "()Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "getSource", "()Lorg/jetbrains/kotlin/fir/analysis/checkers/FirTypeRefSource;", "component1", "component2", "component3", "component4", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class TypeArgumentData {
        private final ConeKotlinType constructor;
        private final int index;
        private final ConeTypeProjection projection;
        private final FirTypeRefSource source;

        public TypeArgumentData(ConeKotlinType coneKotlinType, int i, ConeTypeProjection coneTypeProjection, FirTypeRefSource firTypeRefSource) {
            coneKotlinType.getClass();
            coneTypeProjection.getClass();
            firTypeRefSource.getClass();
            this.constructor = coneKotlinType;
            this.index = i;
            this.projection = coneTypeProjection;
            this.source = firTypeRefSource;
        }

        public static /* synthetic */ TypeArgumentData copy$default(TypeArgumentData typeArgumentData, ConeKotlinType coneKotlinType, int i, ConeTypeProjection coneTypeProjection, FirTypeRefSource firTypeRefSource, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                coneKotlinType = typeArgumentData.constructor;
            }
            if ((i2 & 2) != 0) {
                i = typeArgumentData.index;
            }
            if ((i2 & 4) != 0) {
                coneTypeProjection = typeArgumentData.projection;
            }
            if ((i2 & 8) != 0) {
                firTypeRefSource = typeArgumentData.source;
            }
            return typeArgumentData.copy(coneKotlinType, i, coneTypeProjection, firTypeRefSource);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final ConeKotlinType getConstructor() {
            return this.constructor;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getIndex() {
            return this.index;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final ConeTypeProjection getProjection() {
            return this.projection;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final FirTypeRefSource getSource() {
            return this.source;
        }

        public final TypeArgumentData copy(ConeKotlinType constructor, int index, ConeTypeProjection projection, FirTypeRefSource source) {
            constructor.getClass();
            projection.getClass();
            source.getClass();
            return new TypeArgumentData(constructor, index, projection, source);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TypeArgumentData)) {
                return false;
            }
            TypeArgumentData typeArgumentData = (TypeArgumentData) other;
            return Intrinsics.areEqual(this.constructor, typeArgumentData.constructor) && this.index == typeArgumentData.index && Intrinsics.areEqual(this.projection, typeArgumentData.projection) && Intrinsics.areEqual(this.source, typeArgumentData.source);
        }

        public final ConeKotlinType getConstructor() {
            return this.constructor;
        }

        public final int getIndex() {
            return this.index;
        }

        public final ConeTypeProjection getProjection() {
            return this.projection;
        }

        public final FirTypeRefSource getSource() {
            return this.source;
        }

        public int hashCode() {
            return (((((this.constructor.hashCode() * 31) + Integer.hashCode(this.index)) * 31) + this.projection.hashCode()) * 31) + this.source.hashCode();
        }

        public String toString() {
            return "TypeArgumentData(constructor=" + this.constructor + ", index=" + this.index + ", projection=" + this.projection + ", source=" + this.source + ')';
        }
    }

    private ProjectionRelationCheckerImpl() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final List<TypeArgumentData> collectPotentiallyProblematicArguments(CheckerContext checkerContext, List<FirTypeRefSource> list, ConeKotlinType coneKotlinType) {
        List<TypeArgumentData> listExtractImmediateTypeArgumentData = extractImmediateTypeArgumentData(list, coneKotlinType);
        ArrayList<TypeArgumentData> arrayList = new ArrayList();
        for (Object obj : listExtractImmediateTypeArgumentData) {
            if (INSTANCE.getCanBeProblematic(((TypeArgumentData) obj).getProjection().getKind())) {
                arrayList.add(obj);
            }
        }
        FirClassifierSymbol<?> symbol = ToSymbolUtilsKt.toSymbol(checkerContext, coneKotlinType);
        if (!(symbol instanceof FirTypeAliasSymbol)) {
            return arrayList;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(arrayList, 10)), 16));
        for (TypeArgumentData typeArgumentData : arrayList) {
            linkedHashMap.put(typeArgumentData.getProjection(), typeArgumentData.getSource());
        }
        List<TypeArgumentData> listCreateListBuilder = CollectionsKt.createListBuilder();
        ProjectionRelationCheckerImpl projectionRelationCheckerImpl = INSTANCE;
        FirTypeAliasSymbol firTypeAliasSymbol = (FirTypeAliasSymbol) symbol;
        ConeSubstitutor.Empty empty = ConeSubstitutor.Empty.INSTANCE;
        FirSession session = checkerContext.getSession();
        FirLazyDeclarationResolverKt.lazyResolveToPhase(firTypeAliasSymbol, FirResolvePhase.SUPER_TYPES);
        FirTypeAlias firTypeAlias = (FirTypeAlias) firTypeAliasSymbol.getFir();
        List<Pair<FirTypeParameterSymbol, ConeTypeProjection>> listMapParametersToArgumentsOf = TypeExpansionUtilsKt.mapParametersToArgumentsOf(firTypeAlias, coneKotlinType);
        IntRange indices = CollectionsKt.getIndices(listMapParametersToArgumentsOf);
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(indices, 10));
        IntIterator it = indices.iterator();
        while (it.hasNext()) {
            arrayList2.add((FirTypeRefSource) linkedHashMap.get(coneKotlinType.getTypeArguments()[it.nextInt()]));
        }
        ArrayList<Number> arrayList3 = new ArrayList();
        for (Object obj2 : indices) {
            if (arrayList2.get(((Number) obj2).intValue()) != null) {
                arrayList3.add(obj2);
            }
        }
        if (arrayList3.isEmpty()) {
            arrayList3 = null;
        }
        if (arrayList3 != null) {
            LinkedHashMap linkedHashMap2 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(arrayList3, 10)), 16));
            for (Number number : arrayList3) {
                linkedHashMap2.put((FirTypeParameterSymbol) listMapParametersToArgumentsOf.get(number.intValue()).getFirst(), (ConeTypeProjection) listMapParametersToArgumentsOf.get(number.intValue()).getSecond());
            }
            ConeSubstitutor coneSubstitutorCreateParametersSubstitutor = TypeExpansionUtilsKt.createParametersSubstitutor(session, linkedHashMap2);
            LinkedHashMap linkedHashMap3 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(arrayList3, 10)), 16));
            for (Number number2 : arrayList3) {
                FirTypeParameterSymbol firTypeParameterSymbol = (FirTypeParameterSymbol) listMapParametersToArgumentsOf.get(number2.intValue()).getFirst();
                FirTypeRefSource firTypeRefSource = (FirTypeRefSource) arrayList2.get(number2.intValue());
                if (firTypeRefSource == null) {
                    k2d.a("Should have calculated");
                    return null;
                }
                linkedHashMap3.put(firTypeParameterSymbol, firTypeRefSource);
            }
            projectionRelationCheckerImpl.collectPotentiallyProblematicArguments(AbbreviatedTypeAttributeKt.getAbbreviatedTypeOrSelf(FirTypeUtilsKt.getConeType(firTypeAlias.getExpandedTypeRef())), ChainedSubstitutorKt.chain(coneSubstitutorCreateParametersSubstitutor, empty), linkedHashMap3, listCreateListBuilder, session);
        }
        return CollectionsKt.build(listCreateListBuilder);
    }

    public static /* synthetic */ void doCheck$default(ProjectionRelationCheckerImpl projectionRelationCheckerImpl, CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, List list, ConeKotlinType coneKotlinType, Deprecation deprecation, int i, Object obj) {
        if ((i & 16) != 0) {
            deprecation = null;
        }
        projectionRelationCheckerImpl.doCheck(checkerContext, diagnosticReporter, list, coneKotlinType, deprecation);
    }

    private final List<TypeArgumentData> extractImmediateTypeArgumentData(List<FirTypeRefSource> list, ConeKotlinType coneKotlinType) {
        ArrayList arrayList;
        List listZip;
        if (list == null || (listZip = ArraysKt.zip(coneKotlinType.getTypeArguments(), list)) == null) {
            arrayList = null;
        } else {
            List list2 = listZip;
            arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
            int i = 0;
            for (Object obj : list2) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                Pair pair = (Pair) obj;
                arrayList.add(new TypeArgumentData(coneKotlinType, i, (ConeTypeProjection) pair.getFirst(), (FirTypeRefSource) pair.getSecond()));
                i = i2;
            }
        }
        return arrayList == null ? CollectionsKt.emptyList() : arrayList;
    }

    private final boolean getCanBeProblematic(ProjectionKind projectionKind) {
        return projectionKind == ProjectionKind.IN || projectionKind == ProjectionKind.OUT;
    }

    public final void doCheck(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, List<FirTypeRefSource> list, ConeKotlinType coneKotlinType, Deprecation deprecation) {
        KtDiagnosticFactory1<ConeKotlinType> conflicting_projection_in_typealias_expansion;
        LanguageFeature feature;
        boolean z;
        boolean z2;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        coneKotlinType.getClass();
        List<TypeArgumentData> listCollectPotentiallyProblematicArguments = collectPotentiallyProblematicArguments(checkerContext, list, coneKotlinType);
        ConeKotlinType coneKotlinTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType(checkerContext, coneKotlinType);
        for (TypeArgumentData typeArgumentData : listCollectPotentiallyProblematicArguments) {
            FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(checkerContext, typeArgumentData.getConstructor());
            if (regularClassSymbol != null) {
                FirTypeParameterSymbol firTypeParameterSymbol = regularClassSymbol.getTypeParameterSymbols().get(typeArgumentData.getIndex());
                ConeTypeProjection projection = typeArgumentData.getProjection();
                Variance variance = firTypeParameterSymbol.getVariance();
                ProjectionRelation projectionRelation = ((projection instanceof ConeKotlinTypeConflictingProjection) || (((z = projection instanceof ConeKotlinTypeProjectionIn)) && variance == Variance.OUT_VARIANCE) || (((z2 = projection instanceof ConeKotlinTypeProjectionOut)) && variance == Variance.IN_VARIANCE)) ? ProjectionRelation.Conflicting : ((z && variance == Variance.IN_VARIANCE) || (z2 && variance == Variance.OUT_VARIANCE)) ? ProjectionRelation.Redundant : ProjectionRelation.None;
                FirTypeRefSource source = typeArgumentData.getSource();
                if (projectionRelation != ProjectionRelation.None) {
                    if (projectionRelation == ProjectionRelation.Redundant) {
                        conflicting_projection_in_typealias_expansion = FirErrors.INSTANCE.getREDUNDANT_PROJECTION();
                    } else if (deprecation == null || (feature = deprecation.getFeature()) == null || !LanguageVersionUtilsKt.isDisabled(checkerContext, feature)) {
                        conflicting_projection_in_typealias_expansion = !Intrinsics.areEqual(coneKotlinType, coneKotlinTypeFullyExpandedType) ? FirErrors.INSTANCE.getCONFLICTING_PROJECTION_IN_TYPEALIAS_EXPANSION() : FirErrors.INSTANCE.getCONFLICTING_PROJECTION();
                    } else {
                        conflicting_projection_in_typealias_expansion = deprecation.getWarningDiagnostic();
                    }
                    KtDiagnosticFactory1<ConeKotlinType> ktDiagnosticFactory1 = conflicting_projection_in_typealias_expansion;
                    KtSourceElement source2 = source.getSource();
                    if (source2 == null) {
                        FirTypeRef typeRef = source.getTypeRef();
                        source2 = typeRef != null ? typeRef.getSource() : null;
                    }
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source2, (KtDiagnosticFactory1) ktDiagnosticFactory1, (Object) coneKotlinTypeFullyExpandedType, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void collectPotentiallyProblematicArguments(ConeKotlinType type, ConeSubstitutor previousSubstitutor, Map<FirTypeParameterSymbol, FirTypeRefSource> parametersToSources, List<TypeArgumentData> result, FirSession session) {
        ConeTypeProjection coneTypeProjectionSubstituteArgument;
        FirTypeRefSource firTypeRefSource;
        DeclarationSymbolMarker<Number> symbol;
        if (type instanceof ConeClassLikeType) {
            FirClassLikeSymbol<?> symbol2 = ToSymbolUtilsKt.toSymbol((ConeClassLikeType) type, session);
            if (symbol2 instanceof FirTypeAliasSymbol) {
                FirTypeAliasSymbol firTypeAliasSymbol = (FirTypeAliasSymbol) symbol2;
                FirLazyDeclarationResolverKt.lazyResolveToPhase(firTypeAliasSymbol, FirResolvePhase.SUPER_TYPES);
                FirTypeAlias firTypeAlias = (FirTypeAlias) firTypeAliasSymbol.getFir();
                List<Pair<FirTypeParameterSymbol, ConeTypeProjection>> listMapParametersToArgumentsOf = TypeExpansionUtilsKt.mapParametersToArgumentsOf(firTypeAlias, type);
                IntRange indices = CollectionsKt.getIndices(listMapParametersToArgumentsOf);
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(indices, 10));
                IntIterator it = indices.iterator();
                while (true) {
                    symbol = null;
                    if (!it.hasNext()) {
                        break;
                    }
                    ConeKotlinType type2 = ConeTypeProjectionKt.getType(type.getTypeArguments()[it.nextInt()]);
                    if (type2 != null) {
                        symbol = ToSymbolUtilsKt.toSymbol(type2, session);
                    }
                    arrayList.add(parametersToSources.get(symbol));
                }
                DeclarationSymbolMarker arrayList2 = new ArrayList();
                for (Object obj : indices) {
                    if (arrayList.get(((Number) obj).intValue()) != null) {
                        arrayList2.add(obj);
                    }
                }
                symbol = arrayList2.isEmpty() ? null : arrayList2;
                if (symbol == null) {
                    return;
                }
                LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(symbol, 10)), 16));
                for (Number number : symbol) {
                    linkedHashMap.put((FirTypeParameterSymbol) listMapParametersToArgumentsOf.get(number.intValue()).getFirst(), (ConeTypeProjection) listMapParametersToArgumentsOf.get(number.intValue()).getSecond());
                }
                ConeSubstitutor coneSubstitutorCreateParametersSubstitutor = TypeExpansionUtilsKt.createParametersSubstitutor(session, linkedHashMap);
                LinkedHashMap linkedHashMap2 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(symbol, 10)), 16));
                for (Number number2 : symbol) {
                    FirTypeParameterSymbol firTypeParameterSymbol = (FirTypeParameterSymbol) listMapParametersToArgumentsOf.get(number2.intValue()).getFirst();
                    FirTypeRefSource firTypeRefSource2 = (FirTypeRefSource) arrayList.get(number2.intValue());
                    if (firTypeRefSource2 != null) {
                        linkedHashMap2.put(firTypeParameterSymbol, firTypeRefSource2);
                    } else {
                        k2d.a("Should have calculated");
                        return;
                    }
                }
                collectPotentiallyProblematicArguments(AbbreviatedTypeAttributeKt.getAbbreviatedTypeOrSelf(FirTypeUtilsKt.getConeType(firTypeAlias.getExpandedTypeRef())), ChainedSubstitutorKt.chain(coneSubstitutorCreateParametersSubstitutor, previousSubstitutor), linkedHashMap2, result, session);
                return;
            }
            ConeKotlinType coneKotlinTypeSubstituteOrSelf = previousSubstitutor.substituteOrSelf(type);
            ConeTypeProjection[] typeArguments = type.getTypeArguments();
            int length = typeArguments.length;
            for (int i = 0; i < length; i++) {
                ConeTypeProjection coneTypeProjection = typeArguments[i];
                ConeKotlinType type3 = ConeTypeProjectionKt.getType(coneTypeProjection);
                if (type3 != null) {
                    collectPotentiallyProblematicArguments(type3, previousSubstitutor, parametersToSources, result, session);
                    if ((type3 instanceof ConeTypeParameterType) && (coneTypeProjectionSubstituteArgument = previousSubstitutor.substituteArgument(coneTypeProjection, i)) != null) {
                        FirClassifierSymbol<?> symbol3 = ToSymbolUtilsKt.toSymbol(type3, session);
                        if (symbol3 != null && (firTypeRefSource = parametersToSources.get(symbol3)) != null) {
                            result.add(new TypeArgumentData(coneKotlinTypeSubstituteOrSelf, i, coneTypeProjectionSubstituteArgument, firTypeRefSource));
                        } else {
                            k2d.a("Should have calculated");
                            return;
                        }
                    }
                }
            }
        }
    }
}
