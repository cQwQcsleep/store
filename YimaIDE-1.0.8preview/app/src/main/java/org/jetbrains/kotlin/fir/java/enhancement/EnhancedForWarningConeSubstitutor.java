package org.jetbrains.kotlin.fir.java.enhancement;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.ExplicitTypeArgumentIfMadeFlexibleSyntheticallyTypeAttribute;
import org.jetbrains.kotlin.fir.expressions.ExplicitTypeArgumentIfMadeFlexibleSyntheticallyTypeAttributeKt;
import org.jetbrains.kotlin.fir.resolve.substitution.AbstractConeSubstitutor;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeContext;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\tH\u0016J\u000e\u0010\u000b\u001a\u0004\u0018\u00010\t*\u00020\tH\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/enhancement/EnhancedForWarningConeSubstitutor;", "Lorg/jetbrains/kotlin/fir/resolve/substitution/AbstractConeSubstitutor;", "typeContext", "Lorg/jetbrains/kotlin/fir/types/ConeTypeContext;", "useExplicitTypeArgumentIfMadeFlexibleSyntheticallyWithFeature", "Lorg/jetbrains/kotlin/config/LanguageFeature;", "<init>", "(Lorg/jetbrains/kotlin/fir/types/ConeTypeContext;Lorg/jetbrains/kotlin/config/LanguageFeature;)V", "substituteType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", ModuleXmlParser.TYPE, "replacementTopLevelTypeOrNull", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class EnhancedForWarningConeSubstitutor extends AbstractConeSubstitutor {
    private final LanguageFeature useExplicitTypeArgumentIfMadeFlexibleSyntheticallyWithFeature;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EnhancedForWarningConeSubstitutor(ConeTypeContext coneTypeContext, LanguageFeature languageFeature) {
        super(coneTypeContext);
        coneTypeContext.getClass();
        this.useExplicitTypeArgumentIfMadeFlexibleSyntheticallyWithFeature = languageFeature;
    }

    private final ConeKotlinType replacementTopLevelTypeOrNull(ConeKotlinType coneKotlinType) {
        ConeKotlinType enhancedTypeForWarning = EnhancedTypeForWarningAttributeKt.getEnhancedTypeForWarning(coneKotlinType);
        if (enhancedTypeForWarning == null) {
            ExplicitTypeArgumentIfMadeFlexibleSyntheticallyTypeAttribute explicitTypeArgumentIfMadeFlexibleSynthetically = ExplicitTypeArgumentIfMadeFlexibleSyntheticallyTypeAttributeKt.getExplicitTypeArgumentIfMadeFlexibleSynthetically(coneKotlinType.getAttributes());
            enhancedTypeForWarning = null;
            if (explicitTypeArgumentIfMadeFlexibleSynthetically != null) {
                if (explicitTypeArgumentIfMadeFlexibleSynthetically.getRelevantFeature() != this.useExplicitTypeArgumentIfMadeFlexibleSyntheticallyWithFeature) {
                    explicitTypeArgumentIfMadeFlexibleSynthetically = null;
                }
                if (explicitTypeArgumentIfMadeFlexibleSynthetically != null) {
                    return explicitTypeArgumentIfMadeFlexibleSynthetically.getConeType();
                }
            }
        }
        return enhancedTypeForWarning;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.substitution.AbstractConeSubstitutor
    public ConeKotlinType substituteType(ConeKotlinType type) {
        type.getClass();
        if (!(type instanceof ConeFlexibleType)) {
            ConeKotlinType coneKotlinTypeReplacementTopLevelTypeOrNull = replacementTopLevelTypeOrNull(type);
            if (coneKotlinTypeReplacementTopLevelTypeOrNull != null) {
                return substituteOrSelf(coneKotlinTypeReplacementTopLevelTypeOrNull);
            }
            return null;
        }
        ConeFlexibleType coneFlexibleType = (ConeFlexibleType) type;
        if (coneFlexibleType.getIsTrivial() && replacementTopLevelTypeOrNull(coneFlexibleType.getLowerBound()) == null && replacementTopLevelTypeOrNull(coneFlexibleType.getUpperBound()) == null) {
            return null;
        }
        ConeKotlinType coneKotlinTypeSubstituteOrNull = substituteOrNull(coneFlexibleType.getLowerBound());
        ConeKotlinType coneKotlinTypeSubstituteOrNull2 = substituteOrNull(coneFlexibleType.getUpperBound());
        if (coneKotlinTypeSubstituteOrNull == null && coneKotlinTypeSubstituteOrNull2 == null) {
            return null;
        }
        ConeTypeContext typeContext = getTypeContext();
        if (coneKotlinTypeSubstituteOrNull == null) {
            coneKotlinTypeSubstituteOrNull = coneFlexibleType.getLowerBound();
        }
        if (coneKotlinTypeSubstituteOrNull2 == null) {
            coneKotlinTypeSubstituteOrNull2 = coneFlexibleType.getUpperBound();
        }
        return TypeUtilsKt.coneFlexibleOrSimpleType(typeContext, coneKotlinTypeSubstituteOrNull, coneKotlinTypeSubstituteOrNull2, false);
    }

    public /* synthetic */ EnhancedForWarningConeSubstitutor(ConeTypeContext coneTypeContext, LanguageFeature languageFeature, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(coneTypeContext, (i & 2) != 0 ? null : languageFeature);
    }
}
