package org.jetbrains.kotlin.fir.analysis.checkers;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.ApiVersion;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.name.StandardClassIds$Annotations;
import org.jetbrains.kotlin.resolve.checkers.OptInNames;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u001a\u001f\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u0004R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0005\u001a\u001a\u0010\u0006\u001a\u0004\u0018\u00010\u0007*\u0006\u0012\u0002\b\u00030\u00042\u0006\u0010\b\u001a\u00020\tH\u0002\u001a\u001e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b*\u0006\u0012\u0002\b\u00030\u00042\u0006\u0010\b\u001a\u00020\tH\u0002¨\u0006\r"}, d2 = {"checkSinceKotlinVersionAccessibility", "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirSinceKotlinAccessibility;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;)Lorg/jetbrains/kotlin/fir/analysis/checkers/FirSinceKotlinAccessibility;", "getOwnSinceKotlinVersion", "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirSinceKotlinValue;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "loadWasExperimentalMarkerClasses", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "org.jetbrains.kotlin:checkers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirSinceKotlinHelpersKt {
    public static final FirSinceKotlinAccessibility checkSinceKotlinVersionAccessibility(CheckerContext checkerContext, FirBasedSymbol<?> firBasedSymbol) {
        checkerContext.getClass();
        firBasedSymbol.getClass();
        FirSinceKotlinValue ownSinceKotlinVersion = getOwnSinceKotlinVersion(firBasedSymbol, checkerContext.getSession());
        ApiVersion apiVersion = ownSinceKotlinVersion != null ? ownSinceKotlinVersion.getApiVersion() : null;
        LanguageVersionSettings languageVersionSettings = FirLanguageSettingsComponentKt.getLanguageVersionSettings(checkerContext.getSession());
        if (apiVersion == null || apiVersion.compareTo(languageVersionSettings.getApiVersion()) <= 0) {
            return FirSinceKotlinAccessibility.Accessible.INSTANCE;
        }
        List<FirRegularClassSymbol> wasExperimentalMarkerClasses = ownSinceKotlinVersion.getWasExperimentalMarkerClasses();
        return !wasExperimentalMarkerClasses.isEmpty() ? new FirSinceKotlinAccessibility.NotAccessibleButWasExperimental(apiVersion, wasExperimentalMarkerClasses) : new FirSinceKotlinAccessibility.NotAccessible(apiVersion);
    }

    private static final FirSinceKotlinValue getOwnSinceKotlinVersion(FirBasedSymbol<?> firBasedSymbol, FirSession firSession) {
        FirAnnotation annotationWithResolvedArgumentsByClassId = FirAnnotationUtilsKt.getAnnotationWithResolvedArgumentsByClassId(firBasedSymbol, StandardClassIds$Annotations.INSTANCE.getSinceKotlin(), firSession);
        FirExpression firExpressionFindArgumentByName$default = annotationWithResolvedArgumentsByClassId != null ? FirAnnotationUtilsKt.findArgumentByName$default(annotationWithResolvedArgumentsByClassId, StandardClassIds$Annotations.ParameterNames.INSTANCE.getSinceKotlinVersion(), false, 2, null) : null;
        FirLiteralExpression firLiteralExpression = firExpressionFindArgumentByName$default instanceof FirLiteralExpression ? (FirLiteralExpression) firExpressionFindArgumentByName$default : null;
        Object value = firLiteralExpression != null ? firLiteralExpression.getValue() : null;
        String str = value instanceof String ? (String) value : null;
        ApiVersion apiVersion = str != null ? ApiVersion.INSTANCE.parse(str) : null;
        if (apiVersion != null) {
            return new FirSinceKotlinValue(apiVersion, loadWasExperimentalMarkerClasses(firBasedSymbol, firSession));
        }
        return null;
    }

    private static final List<FirRegularClassSymbol> loadWasExperimentalMarkerClasses(FirBasedSymbol<?> firBasedSymbol, FirSession firSession) {
        FirExpression firExpressionFindArgumentByName$default;
        OptInNames optInNames = OptInNames.INSTANCE;
        FirAnnotation annotationWithResolvedArgumentsByClassId = FirAnnotationUtilsKt.getAnnotationWithResolvedArgumentsByClassId(firBasedSymbol, optInNames.getWAS_EXPERIMENTAL_CLASS_ID(), firSession);
        if (annotationWithResolvedArgumentsByClassId != null && (firExpressionFindArgumentByName$default = FirAnnotationUtilsKt.findArgumentByName$default(annotationWithResolvedArgumentsByClassId, optInNames.getWAS_EXPERIMENTAL_ANNOTATION_CLASS(), false, 2, null)) != null) {
            return FirAnnotationHelpersKt.extractClassesFromArgument(firExpressionFindArgumentByName$default, firSession);
        }
        return CollectionsKt.emptyList();
    }
}
