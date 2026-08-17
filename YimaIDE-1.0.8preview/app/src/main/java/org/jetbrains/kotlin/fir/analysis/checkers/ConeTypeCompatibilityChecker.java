package org.jetbrains.kotlin.fir.analysis.checkers;

import defpackage.dwe;
import defpackage.ewe;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.ArrayDeque;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.PrimitivesKt;
import org.jetbrains.kotlin.fir.TypeUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.ConeTypeCompatibilityChecker;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousObjectSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeCapturedType;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeDefinitelyNotNullType;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeInferenceContext;
import org.jetbrains.kotlin.fir.types.ConeIntegerConstantOperatorType;
import org.jetbrains.kotlin.fir.types.ConeIntegerLiteralConstantType;
import org.jetbrains.kotlin.fir.types.ConeIntegerLiteralType;
import org.jetbrains.kotlin.fir.types.ConeIntersectionType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeConflictingProjection;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjectionIn;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjectionOut;
import org.jetbrains.kotlin.fir.types.ConeLookupTagBasedType;
import org.jetbrains.kotlin.fir.types.ConeStarProjection;
import org.jetbrains.kotlin.fir.types.ConeStubType;
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeTypeVariableType;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.types.Variance;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001:\u0005:;<=>B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0007\u001a\u00020\b*\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bJ\u001a\u0010\r\u001a\u00020\b*\b\u0012\u0004\u0012\u00020\u000b0\u000e2\u0006\u0010\u000f\u001a\u00020\tH\u0002J\f\u0010\u0010\u001a\u00020\u0011*\u00020\u000bH\u0002J@\u0010\r\u001a\u00020\b*\u00020\t2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0016\u001a\u00020\b2\u000e\b\u0002\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018H\u0002J\u001c\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00132\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0013H\u0002J$\u0010\u001d\u001a\u0004\u0018\u00010\b*\u00020\t2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001b0\u000e2\u0006\u0010\u0016\u001a\u00020\bH\u0002J\u0014\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013*\u0004\u0018\u00010\u000bH\u0002J0\u0010 \u001a\u00020!*\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020#0\"2\u0006\u0010$\u001a\u00020\u00142\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\bH\u0002J,\u0010%\u001a\u0004\u0018\u00010&*\u00020\u00142\u0006\u0010\u000f\u001a\u00020\t2\u0014\b\u0002\u0010'\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020)0(H\u0002JD\u0010*\u001a\u00020!*\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020#0\"2\u0006\u0010\u000f\u001a\u00020\t2\n\u0010+\u001a\u0006\u0012\u0002\b\u00030,2\u0006\u0010-\u001a\u00020\u00192\u0006\u0010.\u001a\u00020)2\u0006\u0010\u0016\u001a\u00020\bH\u0002J\u0016\u0010/\u001a\b\u0012\u0004\u0012\u00020\u001400*\u0006\u0012\u0002\b\u00030,H\u0002J\u001a\u00101\u001a\b\u0012\u0002\b\u0003\u0018\u00010,*\u00020\u00142\u0006\u0010\u000f\u001a\u00020\tH\u0002J\u001a\u00102\u001a\u0004\u0018\u00010\u0019*\u0006\u0012\u0002\b\u00030,2\u0006\u00103\u001a\u000204H\u0002J\u0016\u00105\u001a\u0004\u0018\u00010\u001b*\u00020\u00142\u0006\u0010\u000f\u001a\u00020\tH\u0002J\u0016\u00105\u001a\u0004\u0018\u00010\u001b*\u0002062\u0006\u0010\u000f\u001a\u00020\tH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u00107\u001a\u00020\u0011*\u00020\t8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b8\u00109¨\u0006?"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/ConeTypeCompatibilityChecker;", Argument.Delimiters.none, "<init>", "()V", "javaClassClassId", "Lorg/jetbrains/kotlin/name/ClassId;", "kotlinClassClassId", "isCompatible", "Lorg/jetbrains/kotlin/fir/analysis/checkers/ConeTypeCompatibilityChecker$Compatibility;", "Lorg/jetbrains/kotlin/fir/types/ConeInferenceContext;", "a", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "b", "getCompatibility", Argument.Delimiters.none, "ctx", "isConcreteType", Argument.Delimiters.none, "upperBounds", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "lowerBounds", "compatibilityUpperBound", "checkedTypeParameters", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "computeLeafClassesOrInterfaces", "Lorg/jetbrains/kotlin/fir/analysis/checkers/ConeTypeCompatibilityChecker$FirClassWithSuperClasses;", "upperBoundClasses", "areClassesOrInterfacesCompatible", "classesOrInterfaces", "collectLowerBounds", "collectTypeArgumentMapping", Argument.Delimiters.none, Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/ConeTypeCompatibilityChecker$BoundTypeArguments;", "coneType", "toTypeArgumentMapping", "Lorg/jetbrains/kotlin/fir/analysis/checkers/ConeTypeCompatibilityChecker$TypeArgumentMapping;", "envMapping", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/ConeTypeCompatibilityChecker$BoundTypeArgument;", "collect", "typeParameterOwner", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "parameter", "boundTypeArgument", "getSuperTypes", Argument.Delimiters.none, "getClassLikeElement", "getTypeParameter", "index", Argument.Delimiters.none, "toFirClassWithSuperClasses", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "prohibitComparisonOfIncompatibleClasses", "getProhibitComparisonOfIncompatibleClasses", "(Lorg/jetbrains/kotlin/fir/types/ConeInferenceContext;)Z", "Compatibility", "TypeArgumentMapping", "BoundTypeArgument", "BoundTypeArguments", "FirClassWithSuperClasses", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeTypeCompatibilityChecker {
    public static final ConeTypeCompatibilityChecker INSTANCE = new ConeTypeCompatibilityChecker();
    private static final ClassId javaClassClassId;
    private static final ClassId kotlinClassClassId;

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/ConeTypeCompatibilityChecker$BoundTypeArgument;", Argument.Delimiters.none, ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "variance", "Lorg/jetbrains/kotlin/types/Variance;", "<init>", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/types/Variance;)V", "getType", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getVariance", "()Lorg/jetbrains/kotlin/types/Variance;", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class BoundTypeArgument {
        private final ConeKotlinType type;
        private final Variance variance;

        public BoundTypeArgument(ConeKotlinType coneKotlinType, Variance variance) {
            coneKotlinType.getClass();
            variance.getClass();
            this.type = coneKotlinType;
            this.variance = variance;
        }

        public static /* synthetic */ BoundTypeArgument copy$default(BoundTypeArgument boundTypeArgument, ConeKotlinType coneKotlinType, Variance variance, int i, Object obj) {
            if ((i & 1) != 0) {
                coneKotlinType = boundTypeArgument.type;
            }
            if ((i & 2) != 0) {
                variance = boundTypeArgument.variance;
            }
            return boundTypeArgument.copy(coneKotlinType, variance);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final ConeKotlinType getType() {
            return this.type;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Variance getVariance() {
            return this.variance;
        }

        public final BoundTypeArgument copy(ConeKotlinType type, Variance variance) {
            type.getClass();
            variance.getClass();
            return new BoundTypeArgument(type, variance);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BoundTypeArgument)) {
                return false;
            }
            BoundTypeArgument boundTypeArgument = (BoundTypeArgument) other;
            return Intrinsics.areEqual(this.type, boundTypeArgument.type) && this.variance == boundTypeArgument.variance;
        }

        public final ConeKotlinType getType() {
            return this.type;
        }

        public final Variance getVariance() {
            return this.variance;
        }

        public int hashCode() {
            return (this.type.hashCode() * 31) + this.variance.hashCode();
        }

        public String toString() {
            return "BoundTypeArgument(type=" + this.type + ", variance=" + this.variance + ')';
        }
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B+\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J3\u0010\u0012\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0014\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/ConeTypeCompatibilityChecker$BoundTypeArguments;", Argument.Delimiters.none, "upper", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "lower", "compatibilityUpperBound", "Lorg/jetbrains/kotlin/fir/analysis/checkers/ConeTypeCompatibilityChecker$Compatibility;", "<init>", "(Ljava/util/Set;Ljava/util/Set;Lorg/jetbrains/kotlin/fir/analysis/checkers/ConeTypeCompatibilityChecker$Compatibility;)V", "getUpper", "()Ljava/util/Set;", "getLower", "getCompatibilityUpperBound", "()Lorg/jetbrains/kotlin/fir/analysis/checkers/ConeTypeCompatibilityChecker$Compatibility;", "component1", "component2", "component3", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class BoundTypeArguments {
        private final Compatibility compatibilityUpperBound;
        private final Set<ConeClassLikeType> lower;
        private final Set<ConeClassLikeType> upper;

        public BoundTypeArguments(Set<ConeClassLikeType> set, Set<ConeClassLikeType> set2, Compatibility compatibility) {
            set.getClass();
            set2.getClass();
            compatibility.getClass();
            this.upper = set;
            this.lower = set2;
            this.compatibilityUpperBound = compatibility;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ BoundTypeArguments copy$default(BoundTypeArguments boundTypeArguments, Set set, Set set2, Compatibility compatibility, int i, Object obj) {
            if ((i & 1) != 0) {
                set = boundTypeArguments.upper;
            }
            if ((i & 2) != 0) {
                set2 = boundTypeArguments.lower;
            }
            if ((i & 4) != 0) {
                compatibility = boundTypeArguments.compatibilityUpperBound;
            }
            return boundTypeArguments.copy(set, set2, compatibility);
        }

        public final Set<ConeClassLikeType> component1() {
            return this.upper;
        }

        public final Set<ConeClassLikeType> component2() {
            return this.lower;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Compatibility getCompatibilityUpperBound() {
            return this.compatibilityUpperBound;
        }

        public final BoundTypeArguments copy(Set<ConeClassLikeType> upper, Set<ConeClassLikeType> lower, Compatibility compatibilityUpperBound) {
            upper.getClass();
            lower.getClass();
            compatibilityUpperBound.getClass();
            return new BoundTypeArguments(upper, lower, compatibilityUpperBound);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BoundTypeArguments)) {
                return false;
            }
            BoundTypeArguments boundTypeArguments = (BoundTypeArguments) other;
            return Intrinsics.areEqual(this.upper, boundTypeArguments.upper) && Intrinsics.areEqual(this.lower, boundTypeArguments.lower) && this.compatibilityUpperBound == boundTypeArguments.compatibilityUpperBound;
        }

        public final Compatibility getCompatibilityUpperBound() {
            return this.compatibilityUpperBound;
        }

        public final Set<ConeClassLikeType> getLower() {
            return this.lower;
        }

        public final Set<ConeClassLikeType> getUpper() {
            return this.upper;
        }

        public int hashCode() {
            return (((this.upper.hashCode() * 31) + this.lower.hashCode()) * 31) + this.compatibilityUpperBound.hashCode();
        }

        public String toString() {
            return "BoundTypeArguments(upper=" + this.upper + ", lower=" + this.lower + ", compatibilityUpperBound=" + this.compatibilityUpperBound + ')';
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/ConeTypeCompatibilityChecker$Compatibility;", Argument.Delimiters.none, Argument.Delimiters.none, "<init>", "(Ljava/lang/String;I)V", "COMPATIBLE", "SOFT_INCOMPATIBLE", "HARD_INCOMPATIBLE", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public enum Compatibility implements Comparable<Compatibility> {
        COMPATIBLE,
        SOFT_INCOMPATIBLE,
        HARD_INCOMPATIBLE;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<Compatibility> getEntries() {
            return $ENTRIES;
        }
    }

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B'\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0004\b\b\u0010\tJ\r\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u0003HÆ\u0003J\u0015\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005HÆ\u0003J-\u0010\u0010\u001a\u00020\u00002\f\b\u0002\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u00032\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004R\u0015\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/ConeTypeCompatibilityChecker$TypeArgumentMapping;", Argument.Delimiters.none, "typeParameterOwner", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "mapping", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/ConeTypeCompatibilityChecker$BoundTypeArgument;", "<init>", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;Ljava/util/Map;)V", "getTypeParameterOwner", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "getMapping", "()Ljava/util/Map;", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class TypeArgumentMapping {
        private final Map<FirTypeParameterSymbol, BoundTypeArgument> mapping;
        private final FirClassLikeSymbol<?> typeParameterOwner;

        public TypeArgumentMapping(FirClassLikeSymbol<?> firClassLikeSymbol, Map<FirTypeParameterSymbol, BoundTypeArgument> map) {
            firClassLikeSymbol.getClass();
            map.getClass();
            this.typeParameterOwner = firClassLikeSymbol;
            this.mapping = map;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ TypeArgumentMapping copy$default(TypeArgumentMapping typeArgumentMapping, FirClassLikeSymbol firClassLikeSymbol, Map map, int i, Object obj) {
            if ((i & 1) != 0) {
                firClassLikeSymbol = typeArgumentMapping.typeParameterOwner;
            }
            if ((i & 2) != 0) {
                map = typeArgumentMapping.mapping;
            }
            return typeArgumentMapping.copy(firClassLikeSymbol, map);
        }

        public final FirClassLikeSymbol<?> component1() {
            return this.typeParameterOwner;
        }

        public final Map<FirTypeParameterSymbol, BoundTypeArgument> component2() {
            return this.mapping;
        }

        public final TypeArgumentMapping copy(FirClassLikeSymbol<?> typeParameterOwner, Map<FirTypeParameterSymbol, BoundTypeArgument> mapping) {
            typeParameterOwner.getClass();
            mapping.getClass();
            return new TypeArgumentMapping(typeParameterOwner, mapping);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TypeArgumentMapping)) {
                return false;
            }
            TypeArgumentMapping typeArgumentMapping = (TypeArgumentMapping) other;
            return Intrinsics.areEqual(this.typeParameterOwner, typeArgumentMapping.typeParameterOwner) && Intrinsics.areEqual(this.mapping, typeArgumentMapping.mapping);
        }

        public final Map<FirTypeParameterSymbol, BoundTypeArgument> getMapping() {
            return this.mapping;
        }

        public final FirClassLikeSymbol<?> getTypeParameterOwner() {
            return this.typeParameterOwner;
        }

        public int hashCode() {
            return (this.typeParameterOwner.hashCode() * 31) + this.mapping.hashCode();
        }

        public String toString() {
            return "TypeArgumentMapping(typeParameterOwner=" + this.typeParameterOwner + ", mapping=" + this.mapping + ')';
        }
    }

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Variance.values().length];
            try {
                iArr[Variance.IN_VARIANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Variance.OUT_VARIANCE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static {
        ClassId.Companion companion = ClassId.Companion;
        javaClassClassId = ClassId.Companion.fromString$default(companion, "java/lang/Class", false, 2, (Object) null);
        kotlinClassClassId = ClassId.Companion.fromString$default(companion, "kotlin/reflect/KClass", false, 2, (Object) null);
    }

    private ConeTypeCompatibilityChecker() {
    }

    public static BoundTypeArguments a(Function1 function1, Object obj) {
        return (BoundTypeArguments) function1.invoke(obj);
    }

    private final Compatibility areClassesOrInterfacesCompatible(ConeInferenceContext coneInferenceContext, Collection<FirClassWithSuperClasses> collection, Compatibility compatibility) {
        Object next;
        ArrayList arrayList = new ArrayList();
        for (Object obj : collection) {
            if (!((FirClassWithSuperClasses) obj).isInterface()) {
                arrayList.add(obj);
            }
        }
        if (arrayList.size() >= 2) {
            if (!arrayList.isEmpty()) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    if (((FirClassWithSuperClasses) it.next()).getHasPredefinedEqualityContract(coneInferenceContext)) {
                        return compatibility;
                    }
                }
            }
            return Compatibility.SOFT_INCOMPATIBLE;
        }
        Iterator it2 = arrayList.iterator();
        do {
            if (!it2.hasNext()) {
                next = null;
                break;
            }
            next = it2.next();
        } while (!((FirClassWithSuperClasses) next).isFinal());
        FirClassWithSuperClasses firClassWithSuperClasses = (FirClassWithSuperClasses) next;
        if (firClassWithSuperClasses != null && collection.size() > arrayList.size()) {
            return firClassWithSuperClasses.getHasPredefinedEqualityContract(coneInferenceContext) ? compatibility : Compatibility.SOFT_INCOMPATIBLE;
        }
        return null;
    }

    public static Compatibility b(Set set, ConeInferenceContext coneInferenceContext, Map.Entry entry) {
        entry.getClass();
        FirTypeParameterSymbol firTypeParameterSymbol = (FirTypeParameterSymbol) entry.getKey();
        BoundTypeArguments boundTypeArguments = (BoundTypeArguments) entry.getValue();
        Set<ConeClassLikeType> setComponent1 = boundTypeArguments.component1();
        Set<ConeClassLikeType> setComponent2 = boundTypeArguments.component2();
        Compatibility compatibilityUpperBound = boundTypeArguments.getCompatibilityUpperBound();
        if (set.contains(firTypeParameterSymbol)) {
            return Compatibility.COMPATIBLE;
        }
        set.add(firTypeParameterSymbol);
        return INSTANCE.getCompatibility(coneInferenceContext, setComponent1, setComponent2, compatibilityUpperBound, set);
    }

    public static BoundTypeArguments c(FirClassLikeSymbol firClassLikeSymbol, ConeInferenceContext coneInferenceContext, Compatibility compatibility, FirTypeParameterSymbol firTypeParameterSymbol) {
        firTypeParameterSymbol.getClass();
        ClassId classId = firClassLikeSymbol.getClassId();
        StandardClassIds standardClassIds = StandardClassIds.INSTANCE;
        if (!Intrinsics.areEqual(classId, standardClassIds.getEnum()) && (!INSTANCE.getProhibitComparisonOfIncompatibleClasses(coneInferenceContext) || !Intrinsics.areEqual(firClassLikeSymbol.getClassId(), standardClassIds.getKClass()))) {
            compatibility = Compatibility.SOFT_INCOMPATIBLE;
        }
        return new BoundTypeArguments(new LinkedHashSet(), new LinkedHashSet(), compatibility);
    }

    private final void collect(Map<FirTypeParameterSymbol, BoundTypeArguments> map, final ConeInferenceContext coneInferenceContext, final FirClassLikeSymbol<?> firClassLikeSymbol, FirTypeParameterSymbol firTypeParameterSymbol, BoundTypeArgument boundTypeArgument, final Compatibility compatibility) {
        final Function1 function1 = new Function1() { // from class: hq2
            public final Object invoke(Object obj) {
                return ConeTypeCompatibilityChecker.c(firClassLikeSymbol, coneInferenceContext, compatibility, (FirTypeParameterSymbol) obj);
            }
        };
        BoundTypeArguments boundTypeArgumentsComputeIfAbsent = map.computeIfAbsent(firTypeParameterSymbol, new Function() { // from class: iq2
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ConeTypeCompatibilityChecker.a(function1, obj);
            }
        });
        ConeKotlinType type = boundTypeArgument.getType();
        if (boundTypeArgument.getVariance().getAllowsInPosition()) {
            CollectionsKt.addAll(boundTypeArgumentsComputeIfAbsent.getLower(), INSTANCE.collectLowerBounds(type));
        }
        if (boundTypeArgument.getVariance().getAllowsOutPosition()) {
            CollectionsKt.addAll(boundTypeArgumentsComputeIfAbsent.getUpper(), TypeUtilsKt.collectUpperBounds(type, coneInferenceContext));
        }
    }

    private final Set<ConeClassLikeType> collectLowerBounds(ConeKotlinType coneKotlinType) {
        if (coneKotlinType != null && !(coneKotlinType instanceof ConeErrorType)) {
            Set<ConeClassLikeType> set = null;
            if (coneKotlinType instanceof ConeLookupTagBasedType) {
                ConeLookupTagBasedType coneLookupTagBasedType = (ConeLookupTagBasedType) coneKotlinType;
                if (coneLookupTagBasedType instanceof ConeClassLikeType) {
                    return SetsKt.setOf(coneKotlinType);
                }
                if (coneLookupTagBasedType instanceof ConeTypeParameterType) {
                    return SetsKt.emptySet();
                }
                dwe.a("missing branch for ".concat(coneKotlinType.getClass().getName()));
                return null;
            }
            if (coneKotlinType instanceof ConeTypeVariableType) {
                return SetsKt.emptySet();
            }
            if (coneKotlinType instanceof ConeDefinitelyNotNullType) {
                return collectLowerBounds(((ConeDefinitelyNotNullType) coneKotlinType).getOriginal());
            }
            if (coneKotlinType instanceof ConeIntersectionType) {
                Collection<ConeKotlinType> intersectedTypes = ((ConeIntersectionType) coneKotlinType).getIntersectedTypes();
                ArrayList arrayList = new ArrayList();
                Iterator<T> it = intersectedTypes.iterator();
                while (it.hasNext()) {
                    CollectionsKt.addAll(arrayList, INSTANCE.collectLowerBounds((ConeKotlinType) it.next()));
                }
                return CollectionsKt.toSet(arrayList);
            }
            if (coneKotlinType instanceof ConeFlexibleType) {
                return collectLowerBounds(((ConeFlexibleType) coneKotlinType).getLowerBound());
            }
            if (coneKotlinType instanceof ConeCapturedType) {
                List<ConeKotlinType> supertypes = ((ConeCapturedType) coneKotlinType).getConstructor().getSupertypes();
                if (supertypes != null) {
                    ArrayList arrayList2 = new ArrayList();
                    Iterator<T> it2 = supertypes.iterator();
                    while (it2.hasNext()) {
                        CollectionsKt.addAll(arrayList2, INSTANCE.collectLowerBounds((ConeKotlinType) it2.next()));
                    }
                    set = CollectionsKt.toSet(arrayList2);
                }
                return set == null ? SetsKt.emptySet() : set;
            }
            if (coneKotlinType instanceof ConeIntegerConstantOperatorType) {
                return SetsKt.setOf(ConeIntegerLiteralType.getApproximatedType$default((ConeIntegerLiteralType) coneKotlinType, null, 1, null));
            }
            if ((coneKotlinType instanceof ConeStubType) || (coneKotlinType instanceof ConeIntegerLiteralConstantType)) {
                ewe.a(coneKotlinType, " should not reach here");
                return null;
            }
            bu8.a();
            return null;
        }
        return SetsKt.emptySet();
    }

    private final void collectTypeArgumentMapping(Map<FirTypeParameterSymbol, BoundTypeArguments> map, ConeClassLikeType coneClassLikeType, ConeInferenceContext coneInferenceContext, Compatibility compatibility) {
        ArrayDeque arrayDeque = new ArrayDeque();
        TypeArgumentMapping typeArgumentMapping$default = toTypeArgumentMapping$default(this, coneClassLikeType, coneInferenceContext, null, 2, null);
        if (typeArgumentMapping$default == null) {
            return;
        }
        arrayDeque.addLast(typeArgumentMapping$default);
        while (!arrayDeque.isEmpty()) {
            TypeArgumentMapping typeArgumentMapping = (TypeArgumentMapping) arrayDeque.removeFirst();
            FirClassLikeSymbol<?> firClassLikeSymbolComponent1 = typeArgumentMapping.component1();
            Map<FirTypeParameterSymbol, BoundTypeArgument> mapComponent2 = typeArgumentMapping.component2();
            Iterator<ConeClassLikeType> it = getSuperTypes(firClassLikeSymbolComponent1).iterator();
            while (it.hasNext()) {
                TypeArgumentMapping typeArgumentMapping2 = toTypeArgumentMapping(it.next(), coneInferenceContext, mapComponent2);
                if (typeArgumentMapping2 != null) {
                    arrayDeque.addLast(typeArgumentMapping2);
                }
            }
            for (Map.Entry<FirTypeParameterSymbol, BoundTypeArgument> entry : mapComponent2.entrySet()) {
                collect(map, coneInferenceContext, firClassLikeSymbolComponent1, entry.getKey(), entry.getValue(), compatibility);
            }
        }
    }

    private final Set<FirClassWithSuperClasses> computeLeafClassesOrInterfaces(Set<FirClassWithSuperClasses> upperBoundClasses) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : upperBoundClasses) {
            linkedHashMap.put(obj, Boolean.TRUE);
        }
        ArrayDeque arrayDeque = new ArrayDeque(upperBoundClasses);
        while (!arrayDeque.isEmpty()) {
            for (FirClassWithSuperClasses firClassWithSuperClasses : ((FirClassWithSuperClasses) arrayDeque.removeFirst()).getSuperClasses()) {
                Boolean bool = (Boolean) linkedHashMap.get(firClassWithSuperClasses);
                if (Intrinsics.areEqual(bool, Boolean.TRUE)) {
                    linkedHashMap.put(firClassWithSuperClasses, Boolean.FALSE);
                } else {
                    Boolean bool2 = Boolean.FALSE;
                    if (!Intrinsics.areEqual(bool, bool2)) {
                        linkedHashMap.put(firClassWithSuperClasses, bool2);
                        arrayDeque.addLast(firClassWithSuperClasses);
                    }
                }
            }
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            if (((Boolean) entry.getValue()).booleanValue()) {
                linkedHashMap2.put(entry.getKey(), entry.getValue());
            }
        }
        return linkedHashMap2.keySet();
    }

    private final FirClassLikeSymbol<?> getClassLikeElement(ConeClassLikeType coneClassLikeType, ConeInferenceContext coneInferenceContext) {
        return ToSymbolUtilsKt.toSymbol(coneClassLikeType.getLookupTag(), coneInferenceContext.getSession());
    }

    private final Compatibility getCompatibility(final ConeInferenceContext coneInferenceContext, Set<? extends ConeClassLikeType> set, Set<? extends ConeClassLikeType> set2, Compatibility compatibility, final Set<FirTypeParameterSymbol> set3) {
        Set<FirClassWithSuperClasses> setEmptySet;
        Set<? extends ConeClassLikeType> set4 = set;
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = set4.iterator();
        while (it.hasNext()) {
            FirClassWithSuperClasses firClassWithSuperClasses = INSTANCE.toFirClassWithSuperClasses((ConeClassLikeType) it.next(), coneInferenceContext);
            if (firClassWithSuperClasses != null) {
                arrayList.add(firClassWithSuperClasses);
            }
        }
        Set<FirClassWithSuperClasses> set5 = CollectionsKt.toSet(arrayList);
        if (set2.isEmpty() && (set.size() < 2 || areClassesOrInterfacesCompatible(coneInferenceContext, set5, compatibility) == Compatibility.COMPATIBLE)) {
            return Compatibility.COMPATIBLE;
        }
        if (!(set4 instanceof Collection) || !set4.isEmpty()) {
            for (ConeClassLikeType coneClassLikeType : set4) {
                if (Intrinsics.areEqual(ConeTypeUtilsKt.getClassId(coneClassLikeType), javaClassClassId) || Intrinsics.areEqual(ConeTypeUtilsKt.getClassId(coneClassLikeType), kotlinClassClassId)) {
                    return Compatibility.COMPATIBLE;
                }
            }
        }
        Set<FirClassWithSuperClasses> setComputeLeafClassesOrInterfaces = computeLeafClassesOrInterfaces(set5);
        Compatibility compatibilityAreClassesOrInterfacesCompatible = areClassesOrInterfacesCompatible(coneInferenceContext, setComputeLeafClassesOrInterfaces, compatibility);
        if (compatibilityAreClassesOrInterfacesCompatible != null) {
            return compatibilityAreClassesOrInterfacesCompatible;
        }
        Set<? extends ConeClassLikeType> set6 = set2;
        if (!(set6 instanceof Collection) || !set6.isEmpty()) {
            Iterator<T> it2 = set6.iterator();
            while (it2.hasNext()) {
                FirClassWithSuperClasses firClassWithSuperClasses2 = INSTANCE.toFirClassWithSuperClasses((ConeClassLikeType) it2.next(), coneInferenceContext);
                if (firClassWithSuperClasses2 == null || (setEmptySet = firClassWithSuperClasses2.getThisAndAllSuperClasses()) == null) {
                    setEmptySet = SetsKt.emptySet();
                }
                Set<FirClassWithSuperClasses> set7 = setComputeLeafClassesOrInterfaces;
                if (!(set7 instanceof Collection) || !set7.isEmpty()) {
                    Iterator<T> it3 = set7.iterator();
                    while (it3.hasNext()) {
                        if (!setEmptySet.contains((FirClassWithSuperClasses) it3.next())) {
                            return compatibility;
                        }
                    }
                }
            }
        }
        if (set.size() < 2) {
            return Compatibility.COMPATIBLE;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator<? extends ConeClassLikeType> it4 = set.iterator();
        while (it4.hasNext()) {
            INSTANCE.collectTypeArgumentMapping(linkedHashMap, it4.next(), coneInferenceContext, compatibility);
        }
        Compatibility compatibility2 = Compatibility.COMPATIBLE;
        for (Compatibility compatibility3 : SequencesKt.map(MapsKt.asSequence(linkedHashMap), new Function1() { // from class: gq2
            public final Object invoke(Object obj) {
                return ConeTypeCompatibilityChecker.b(set3, coneInferenceContext, (Map.Entry) obj);
            }
        })) {
            if (compatibility3 == compatibility) {
                return compatibility3;
            }
            if (compatibility3.compareTo(compatibility2) > 0) {
                compatibility2 = compatibility3;
            }
        }
        return compatibility2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Compatibility getCompatibility$default(ConeTypeCompatibilityChecker coneTypeCompatibilityChecker, ConeInferenceContext coneInferenceContext, Set set, Set set2, Compatibility compatibility, Set set3, int i, Object obj) {
        if ((i & 8) != 0) {
            set3 = new LinkedHashSet();
        }
        return coneTypeCompatibilityChecker.getCompatibility(coneInferenceContext, set, set2, compatibility, set3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getProhibitComparisonOfIncompatibleClasses(ConeInferenceContext coneInferenceContext) {
        return FirLanguageSettingsComponentKt.getLanguageVersionSettings(coneInferenceContext.getSession()).supportsFeature(LanguageFeature.ProhibitComparisonOfIncompatibleClasses);
    }

    private final List<ConeClassLikeType> getSuperTypes(FirClassLikeSymbol<?> firClassLikeSymbol) {
        if (firClassLikeSymbol instanceof FirTypeAliasSymbol) {
            ConeKotlinType coneType = ((FirTypeAliasSymbol) firClassLikeSymbol).getResolvedExpandedTypeRef().getConeType();
            return CollectionsKt.listOfNotNull(coneType instanceof ConeClassLikeType ? (ConeClassLikeType) coneType : null);
        }
        if (!(firClassLikeSymbol instanceof FirClassSymbol)) {
            bu8.a();
            return null;
        }
        List<FirResolvedTypeRef> resolvedSuperTypeRefs = ((FirClassSymbol) firClassLikeSymbol).getResolvedSuperTypeRefs();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = resolvedSuperTypeRefs.iterator();
        while (it.hasNext()) {
            ConeKotlinType coneType2 = ((FirResolvedTypeRef) it.next()).getConeType();
            ConeClassLikeType coneClassLikeType = coneType2 instanceof ConeClassLikeType ? (ConeClassLikeType) coneType2 : null;
            if (coneClassLikeType != null) {
                arrayList.add(coneClassLikeType);
            }
        }
        return arrayList;
    }

    private final FirTypeParameterSymbol getTypeParameter(FirClassLikeSymbol<?> firClassLikeSymbol, int i) {
        if (firClassLikeSymbol instanceof FirTypeAliasSymbol) {
            return ((FirTypeAliasSymbol) firClassLikeSymbol).getTypeParameterSymbols().get(i);
        }
        if (firClassLikeSymbol instanceof FirClassSymbol) {
            return ((FirClassSymbol) firClassLikeSymbol).getTypeParameterSymbols().get(i);
        }
        bu8.a();
        return null;
    }

    private final boolean isConcreteType(ConeKotlinType coneKotlinType) {
        if (coneKotlinType instanceof ConeClassLikeType) {
            return true;
        }
        if (coneKotlinType instanceof ConeDefinitelyNotNullType) {
            return isConcreteType(((ConeDefinitelyNotNullType) coneKotlinType).getOriginal());
        }
        if (!(coneKotlinType instanceof ConeIntersectionType)) {
            return false;
        }
        Collection<ConeKotlinType> intersectedTypes = ((ConeIntersectionType) coneKotlinType).getIntersectedTypes();
        if ((intersectedTypes instanceof Collection) && intersectedTypes.isEmpty()) {
            return true;
        }
        Iterator<T> it = intersectedTypes.iterator();
        while (it.hasNext()) {
            if (!INSTANCE.isConcreteType((ConeKotlinType) it.next())) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FirClassWithSuperClasses toFirClassWithSuperClasses(ConeClassLikeLookupTag coneClassLikeLookupTag, ConeInferenceContext coneInferenceContext) {
        FirClassLikeSymbol<?> symbol = ToSymbolUtilsKt.toSymbol(coneClassLikeLookupTag, coneInferenceContext.getSession());
        if (!(symbol instanceof FirTypeAliasSymbol)) {
            if (symbol instanceof FirClassSymbol) {
                return new FirClassWithSuperClasses((FirClassSymbol) symbol, coneInferenceContext);
            }
            return null;
        }
        FirRegularClassSymbol firRegularClassSymbolFullyExpandedClass = DeclarationUtilsKt.fullyExpandedClass(symbol, coneInferenceContext.getSession());
        if (firRegularClassSymbolFullyExpandedClass != null) {
            return new FirClassWithSuperClasses(firRegularClassSymbolFullyExpandedClass, coneInferenceContext);
        }
        return null;
    }

    private final TypeArgumentMapping toTypeArgumentMapping(ConeClassLikeType coneClassLikeType, ConeInferenceContext coneInferenceContext, Map<FirTypeParameterSymbol, BoundTypeArgument> map) {
        BoundTypeArgument boundTypeArgument;
        BoundTypeArgument boundTypeArgument2;
        FirClassLikeSymbol<?> classLikeElement = getClassLikeElement(coneClassLikeType, coneInferenceContext);
        if (classLikeElement == null) {
            return null;
        }
        Map mapCreateMapBuilder = MapsKt.createMapBuilder();
        ConeKotlinTypeProjectionIn[] typeArguments = coneClassLikeType.getTypeArguments();
        int length = typeArguments.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            ConeKotlinTypeProjectionIn coneKotlinTypeProjectionIn = typeArguments[i];
            int i3 = i2 + 1;
            FirTypeParameterSymbol typeParameter = INSTANCE.getTypeParameter(classLikeElement, i2);
            if (typeParameter != null && !Intrinsics.areEqual(coneKotlinTypeProjectionIn, ConeStarProjection.INSTANCE)) {
                if (coneKotlinTypeProjectionIn instanceof ConeKotlinTypeProjectionIn) {
                    boundTypeArgument = new BoundTypeArgument(coneKotlinTypeProjectionIn.getType(), Variance.IN_VARIANCE);
                } else if (coneKotlinTypeProjectionIn instanceof ConeKotlinTypeProjectionOut) {
                    boundTypeArgument = new BoundTypeArgument(((ConeKotlinTypeProjectionOut) coneKotlinTypeProjectionIn).getType(), Variance.OUT_VARIANCE);
                } else if (coneKotlinTypeProjectionIn instanceof ConeKotlinTypeConflictingProjection) {
                    boundTypeArgument = new BoundTypeArgument(((ConeKotlinTypeConflictingProjection) coneKotlinTypeProjectionIn).getType(), Variance.INVARIANT);
                } else {
                    if (!(coneKotlinTypeProjectionIn instanceof ConeKotlinType)) {
                        bu8.a();
                        return null;
                    }
                    int i4 = WhenMappings.$EnumSwitchMapping$0[typeParameter.getVariance().ordinal()];
                    if (i4 != 1) {
                        boundTypeArgument = i4 != 2 ? new BoundTypeArgument((ConeKotlinType) coneKotlinTypeProjectionIn, Variance.INVARIANT) : new BoundTypeArgument((ConeKotlinType) coneKotlinTypeProjectionIn, Variance.OUT_VARIANCE);
                    } else {
                        boundTypeArgument = new BoundTypeArgument((ConeKotlinType) coneKotlinTypeProjectionIn, Variance.IN_VARIANCE);
                    }
                }
                ConeKotlinType type = boundTypeArgument.getType();
                if ((type instanceof ConeTypeParameterType) && (boundTypeArgument2 = map.get(((ConeTypeParameterType) type).getLookupTag().getTypeParameterSymbol())) != null) {
                    boundTypeArgument = boundTypeArgument2;
                }
                mapCreateMapBuilder.put(typeParameter, boundTypeArgument);
            }
            i++;
            i2 = i3;
        }
        return new TypeArgumentMapping(classLikeElement, MapsKt.build(mapCreateMapBuilder));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TypeArgumentMapping toTypeArgumentMapping$default(ConeTypeCompatibilityChecker coneTypeCompatibilityChecker, ConeClassLikeType coneClassLikeType, ConeInferenceContext coneInferenceContext, Map map, int i, Object obj) {
        if ((i & 2) != 0) {
            map = MapsKt.emptyMap();
        }
        return coneTypeCompatibilityChecker.toTypeArgumentMapping(coneClassLikeType, coneInferenceContext, map);
    }

    public final Compatibility isCompatible(ConeInferenceContext coneInferenceContext, ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2) {
        coneInferenceContext.getClass();
        coneKotlinType.getClass();
        coneKotlinType2.getClass();
        if (ConeBuiltinTypeUtilsKt.isNothing(coneKotlinType) || ConeBuiltinTypeUtilsKt.isNothing(coneKotlinType2)) {
            return Compatibility.COMPATIBLE;
        }
        if (coneKotlinType instanceof ConeIntersectionType) {
            Iterator<T> it = ((ConeIntersectionType) coneKotlinType).getIntersectedTypes().iterator();
            if (!it.hasNext()) {
                z0e.a();
                return null;
            }
            Compatibility compatibilityIsCompatible = INSTANCE.isCompatible(coneInferenceContext, (ConeKotlinType) it.next(), coneKotlinType2);
            while (it.hasNext()) {
                Compatibility compatibilityIsCompatible2 = INSTANCE.isCompatible(coneInferenceContext, (ConeKotlinType) it.next(), coneKotlinType2);
                if (compatibilityIsCompatible.compareTo(compatibilityIsCompatible2) > 0) {
                    compatibilityIsCompatible = compatibilityIsCompatible2;
                }
            }
            return compatibilityIsCompatible;
        }
        if (!(coneKotlinType2 instanceof ConeIntersectionType)) {
            ConeKotlinType coneKotlinTypeIntersectTypesOrNull = org.jetbrains.kotlin.fir.types.TypeUtilsKt.intersectTypesOrNull(coneInferenceContext, CollectionsKt.listOf(new ConeKotlinType[]{coneKotlinType, coneKotlinType2}));
            if (coneKotlinTypeIntersectTypesOrNull instanceof ConeIntersectionType) {
                return getCompatibility(((ConeIntersectionType) coneKotlinTypeIntersectTypesOrNull).getIntersectedTypes(), coneInferenceContext);
            }
            return (coneKotlinTypeIntersectTypesOrNull == null || !ConeBuiltinTypeUtilsKt.isNothing(coneKotlinTypeIntersectTypesOrNull)) ? Compatibility.COMPATIBLE : Compatibility.HARD_INCOMPATIBLE;
        }
        Iterator<T> it2 = ((ConeIntersectionType) coneKotlinType2).getIntersectedTypes().iterator();
        if (!it2.hasNext()) {
            z0e.a();
            return null;
        }
        Compatibility compatibilityIsCompatible3 = INSTANCE.isCompatible(coneInferenceContext, coneKotlinType, (ConeKotlinType) it2.next());
        while (it2.hasNext()) {
            Compatibility compatibilityIsCompatible4 = INSTANCE.isCompatible(coneInferenceContext, coneKotlinType, (ConeKotlinType) it2.next());
            if (compatibilityIsCompatible3.compareTo(compatibilityIsCompatible4) > 0) {
                compatibilityIsCompatible3 = compatibilityIsCompatible4;
            }
        }
        return compatibilityIsCompatible3;
    }

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0010\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u001b\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u0019\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\u0005J\r\u0010\u001b\u001a\u0006\u0012\u0002\b\u00030\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J!\u0010\u001d\u001a\u00020\u00002\f\b\u0002\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u001e\u001a\u00020\r2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010 \u001a\u00020!HÖ\u0081\u0004J\n\u0010\"\u001a\u00020#HÖ\u0081\u0004R\u0015\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\f\u0010\u000eR!\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00000\u00108FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0011\u0010\u0012R!\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00000\u00108FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\u0014\u001a\u0004\b\u0016\u0010\u0012R\u0011\u0010\u0018\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u000eR\u001c\u0010\u0018\u001a\u00020\r*\u0006\u0012\u0002\b\u00030\u00038BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u001a¨\u0006$"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/ConeTypeCompatibilityChecker$FirClassWithSuperClasses;", Argument.Delimiters.none, "firClass", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "ctx", "Lorg/jetbrains/kotlin/fir/types/ConeInferenceContext;", "<init>", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;Lorg/jetbrains/kotlin/fir/types/ConeInferenceContext;)V", "getFirClass", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "getCtx", "()Lorg/jetbrains/kotlin/fir/types/ConeInferenceContext;", "isInterface", Argument.Delimiters.none, "()Z", "superClasses", Argument.Delimiters.none, "getSuperClasses", "()Ljava/util/Set;", "superClasses$delegate", "Lkotlin/Lazy;", "thisAndAllSuperClasses", "getThisAndAllSuperClasses", "thisAndAllSuperClasses$delegate", "isFinal", "getHasPredefinedEqualityContract", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;)Z", "component1", "component2", "copy", "equals", "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class FirClassWithSuperClasses {
        private final ConeInferenceContext ctx;
        private final FirClassSymbol<?> firClass;

        /* JADX INFO: renamed from: superClasses$delegate, reason: from kotlin metadata */
        private final Lazy superClasses;

        /* JADX INFO: renamed from: thisAndAllSuperClasses$delegate, reason: from kotlin metadata */
        private final Lazy thisAndAllSuperClasses;

        public FirClassWithSuperClasses(FirClassSymbol<?> firClassSymbol, ConeInferenceContext coneInferenceContext) {
            firClassSymbol.getClass();
            coneInferenceContext.getClass();
            this.firClass = firClassSymbol;
            this.ctx = coneInferenceContext;
            this.superClasses = LazyKt.lazy(new Function0() { // from class: org.jetbrains.kotlin.fir.analysis.checkers.a
                public final Object invoke() {
                    return ConeTypeCompatibilityChecker.FirClassWithSuperClasses.a(this.b);
                }
            });
            this.thisAndAllSuperClasses = LazyKt.lazy(new Function0() { // from class: org.jetbrains.kotlin.fir.analysis.checkers.b
                public final Object invoke() {
                    return ConeTypeCompatibilityChecker.FirClassWithSuperClasses.b(this.b);
                }
            });
        }

        public static Set a(FirClassWithSuperClasses firClassWithSuperClasses) {
            List<ConeKotlinType> resolvedSuperTypes = firClassWithSuperClasses.firClass.getResolvedSuperTypes();
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = resolvedSuperTypes.iterator();
            while (it.hasNext()) {
                ConeClassLikeLookupTag classLikeLookupTagIfAny = ConeTypeUtilsKt.getClassLikeLookupTagIfAny((ConeKotlinType) it.next());
                FirClassWithSuperClasses firClassWithSuperClasses2 = classLikeLookupTagIfAny != null ? ConeTypeCompatibilityChecker.INSTANCE.toFirClassWithSuperClasses(classLikeLookupTagIfAny, firClassWithSuperClasses.ctx) : null;
                if (firClassWithSuperClasses2 != null) {
                    arrayList.add(firClassWithSuperClasses2);
                }
            }
            return CollectionsKt.toSet(arrayList);
        }

        public static Set b(FirClassWithSuperClasses firClassWithSuperClasses) {
            ArrayDeque arrayDeque = new ArrayDeque();
            arrayDeque.addLast(firClassWithSuperClasses);
            Set setCreateSetBuilder = SetsKt.createSetBuilder();
            setCreateSetBuilder.add(firClassWithSuperClasses);
            while (!arrayDeque.isEmpty()) {
                Set<FirClassWithSuperClasses> superClasses = ((FirClassWithSuperClasses) arrayDeque.removeFirst()).getSuperClasses();
                for (Object obj : superClasses) {
                    if (!setCreateSetBuilder.contains((FirClassWithSuperClasses) obj)) {
                        arrayDeque.add(obj);
                    }
                }
                setCreateSetBuilder.addAll(superClasses);
            }
            return SetsKt.build(setCreateSetBuilder);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ FirClassWithSuperClasses copy$default(FirClassWithSuperClasses firClassWithSuperClasses, FirClassSymbol firClassSymbol, ConeInferenceContext coneInferenceContext, int i, Object obj) {
            if ((i & 1) != 0) {
                firClassSymbol = firClassWithSuperClasses.firClass;
            }
            if ((i & 2) != 0) {
                coneInferenceContext = firClassWithSuperClasses.ctx;
            }
            return firClassWithSuperClasses.copy(firClassSymbol, coneInferenceContext);
        }

        private final boolean isFinal(FirClassSymbol<?> firClassSymbol) {
            if (firClassSymbol instanceof FirAnonymousObjectSymbol) {
                return true;
            }
            if (firClassSymbol instanceof FirRegularClassSymbol) {
                return firClassSymbol.getResolvedStatus().getModality() == Modality.FINAL;
            }
            bu8.a();
            return false;
        }

        public final FirClassSymbol<?> component1() {
            return this.firClass;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final ConeInferenceContext getCtx() {
            return this.ctx;
        }

        public final FirClassWithSuperClasses copy(FirClassSymbol<?> firClass, ConeInferenceContext ctx) {
            firClass.getClass();
            ctx.getClass();
            return new FirClassWithSuperClasses(firClass, ctx);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof FirClassWithSuperClasses)) {
                return false;
            }
            FirClassWithSuperClasses firClassWithSuperClasses = (FirClassWithSuperClasses) other;
            return Intrinsics.areEqual(this.firClass, firClassWithSuperClasses.firClass) && Intrinsics.areEqual(this.ctx, firClassWithSuperClasses.ctx);
        }

        public final ConeInferenceContext getCtx() {
            return this.ctx;
        }

        public final FirClassSymbol<?> getFirClass() {
            return this.firClass;
        }

        public final boolean getHasPredefinedEqualityContract(ConeInferenceContext ctx) {
            ctx.getClass();
            if (this.firClass.getClassKind() == ClassKind.ENUM_CLASS) {
                return true;
            }
            ClassId classId = this.firClass.getClassId();
            StandardClassIds standardClassIds = StandardClassIds.INSTANCE;
            if (Intrinsics.areEqual(classId, standardClassIds.getEnum()) || PrimitivesKt.isPrimitiveType(this.firClass)) {
                return true;
            }
            if ((ConeTypeCompatibilityChecker.INSTANCE.getProhibitComparisonOfIncompatibleClasses(ctx) && Intrinsics.areEqual(this.firClass.getClassId(), standardClassIds.getKClass())) || Intrinsics.areEqual(this.firClass.getClassId(), standardClassIds.getString()) || Intrinsics.areEqual(this.firClass.getClassId(), standardClassIds.getUnit())) {
                return true;
            }
            FirClassSymbol<?> firClassSymbol = this.firClass;
            if (!(firClassSymbol instanceof FirRegularClassSymbol)) {
                return false;
            }
            if (firClassSymbol.getRawStatus().isData()) {
                return true;
            }
            FirClassSymbol<?> firClassSymbol2 = this.firClass;
            return firClassSymbol2.getRawStatus().isInline() || firClassSymbol2.getRawStatus().isValue();
        }

        public final Set<FirClassWithSuperClasses> getSuperClasses() {
            return (Set) this.superClasses.getValue();
        }

        public final Set<FirClassWithSuperClasses> getThisAndAllSuperClasses() {
            return (Set) this.thisAndAllSuperClasses.getValue();
        }

        public int hashCode() {
            return (this.firClass.hashCode() * 31) + this.ctx.hashCode();
        }

        public final boolean isInterface() {
            return this.firClass.getClassKind() == ClassKind.INTERFACE;
        }

        public String toString() {
            return "FirClassWithSuperClasses(firClass=" + this.firClass + ", ctx=" + this.ctx + ')';
        }

        public final boolean isFinal() {
            return isFinal(this.firClass);
        }
    }

    private final FirClassWithSuperClasses toFirClassWithSuperClasses(ConeClassLikeType coneClassLikeType, ConeInferenceContext coneInferenceContext) {
        return toFirClassWithSuperClasses(coneClassLikeType.getLookupTag(), coneInferenceContext);
    }

    private final Compatibility getCompatibility(Collection<? extends ConeKotlinType> collection, ConeInferenceContext coneInferenceContext) {
        Compatibility compatibility;
        Collection<? extends ConeKotlinType> collection2 = collection;
        boolean z = collection2 instanceof Collection;
        if (!z || !collection2.isEmpty()) {
            Iterator<T> it = collection2.iterator();
            while (it.hasNext()) {
                if (!coneInferenceContext.isNullableType((ConeKotlinType) it.next())) {
                    if (!z || !collection2.isEmpty()) {
                        Iterator<T> it2 = collection2.iterator();
                        while (true) {
                            if (it2.hasNext()) {
                                if (!INSTANCE.isConcreteType((ConeKotlinType) it2.next())) {
                                    compatibility = Compatibility.SOFT_INCOMPATIBLE;
                                    break;
                                }
                            } else {
                                compatibility = Compatibility.HARD_INCOMPATIBLE;
                                break;
                            }
                        }
                    } else {
                        compatibility = Compatibility.HARD_INCOMPATIBLE;
                        break;
                    }
                    Compatibility compatibility2 = compatibility;
                    ArrayList arrayList = new ArrayList();
                    Iterator<T> it3 = collection2.iterator();
                    while (it3.hasNext()) {
                        CollectionsKt.addAll(arrayList, TypeUtilsKt.collectUpperBounds((ConeKotlinType) it3.next(), coneInferenceContext));
                    }
                    return getCompatibility$default(this, coneInferenceContext, CollectionsKt.toSet(arrayList), SetsKt.emptySet(), compatibility2, null, 8, null);
                }
            }
        }
        return Compatibility.COMPATIBLE;
    }
}
