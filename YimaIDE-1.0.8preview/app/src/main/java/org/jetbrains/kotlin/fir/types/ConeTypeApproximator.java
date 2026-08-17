package org.jetbrains.kotlin.fir.types;

import java.util.Map;
import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.renderer.ConeTypeRenderer;
import org.jetbrains.kotlin.fir.renderer.ConeTypeRendererForDebugInfo;
import org.jetbrains.kotlin.fir.types.ConeTypeApproximator;
import org.jetbrains.kotlin.types.AbstractTypeApproximator;
import org.jetbrains.kotlin.types.TypeApproximatorConfiguration;
import org.jetbrains.kotlin.types.model.CapturedTypeMarker;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0018\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\fJ\u0018\u0010\r\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\fJ\f\u0010\u000e\u001a\u00020\u000f*\u00020\u0010H\u0014J\u0014\u0010\u0011\u001a\u00020\u0012*\u00020\t2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0018\u0010\u0013\u001a\u00020\u00122\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\fH\u0002¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/ConeTypeApproximator;", "Lorg/jetbrains/kotlin/types/AbstractTypeApproximator;", "inferenceContext", "Lorg/jetbrains/kotlin/fir/types/ConeInferenceContext;", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "<init>", "(Lorg/jetbrains/kotlin/fir/types/ConeInferenceContext;Lorg/jetbrains/kotlin/config/LanguageVersionSettings;)V", "approximateToSuperType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", ModuleXmlParser.TYPE, "conf", "Lorg/jetbrains/kotlin/types/TypeApproximatorConfiguration;", "approximateToSubType", "renderForDebugInfo", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;", "fastPathSkipApproximation", Argument.Delimiters.none, "mightNeedApproximation", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeTypeApproximator extends AbstractTypeApproximator {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConeTypeApproximator(ConeInferenceContext coneInferenceContext, LanguageVersionSettings languageVersionSettings) {
        super(coneInferenceContext, languageVersionSettings);
        coneInferenceContext.getClass();
        languageVersionSettings.getClass();
    }

    private final boolean fastPathSkipApproximation(ConeKotlinType coneKotlinType, final TypeApproximatorConfiguration typeApproximatorConfiguration) {
        if ((coneKotlinType instanceof ConeClassLikeType) && coneKotlinType.getTypeArguments().length == 0) {
            ConeClassLikeLookupTag lookupTag = ((ConeClassLikeType) coneKotlinType).getLookupTag();
            if (!isLocalType(lookupTag) && !isAnonymous(lookupTag)) {
                return true;
            }
        }
        if (getLanguageVersionSettings().supportsFeature(LanguageFeature.AvoidApproximationOfRecursiveCapturedTypesWithNoReason) && (typeApproximatorConfiguration instanceof TypeApproximatorConfiguration.AbstractCapturedTypesAndILTApproximation)) {
            return !contains(coneKotlinType, new Function1() { // from class: fq2
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(ConeTypeApproximator.u(this.b, typeApproximatorConfiguration, (KotlinTypeMarker) obj));
                }
            });
        }
        return false;
    }

    private final boolean mightNeedApproximation(ConeKotlinType type, TypeApproximatorConfiguration conf) {
        if (type instanceof ConeIntegerLiteralType) {
            return true;
        }
        if (type instanceof ConeCapturedType) {
            return conf.shouldApproximateCapturedType(this, (CapturedTypeMarker) type);
        }
        return false;
    }

    public static boolean u(ConeTypeApproximator coneTypeApproximator, TypeApproximatorConfiguration typeApproximatorConfiguration, KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return coneTypeApproximator.mightNeedApproximation((ConeKotlinType) kotlinTypeMarker, typeApproximatorConfiguration);
    }

    public final ConeKotlinType approximateToSubType(ConeKotlinType type, TypeApproximatorConfiguration conf) {
        ConeKotlinType coneKotlinTypeApproximateToSubType;
        type.getClass();
        conf.getClass();
        if (fastPathSkipApproximation(type, conf) || (coneKotlinTypeApproximateToSubType = super.approximateToSubType(type, conf, (Map) null)) == null) {
            return null;
        }
        return coneKotlinTypeApproximateToSubType;
    }

    public final ConeKotlinType approximateToSuperType(ConeKotlinType type, TypeApproximatorConfiguration conf) {
        ConeKotlinType coneKotlinTypeApproximateToSuperType;
        type.getClass();
        conf.getClass();
        if (fastPathSkipApproximation(type, conf) || (coneKotlinTypeApproximateToSuperType = super.approximateToSuperType(type, conf, (Map) null)) == null) {
            return null;
        }
        return coneKotlinTypeApproximateToSuperType;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public String renderForDebugInfo(KotlinTypeMarker kotlinTypeMarker) throws UninitializedPropertyAccessException {
        kotlinTypeMarker.getClass();
        StringBuilder sb = new StringBuilder();
        ConeTypeRenderer.render$default(new ConeTypeRendererForDebugInfo(sb, true), (ConeKotlinType) kotlinTypeMarker, null, 2, null);
        return sb.toString();
    }
}
