package org.jetbrains.kotlin.fir.declarations;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.ApiVersion;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.StandardClassIds$Annotations;
import org.jetbrains.kotlin.resolve.deprecation.DeprecationLevelValue;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ&\u0010\t\u001a\u0004\u0018\u00010\n*\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/DeprecatedSinceKotlinProvider;", "Lorg/jetbrains/kotlin/fir/declarations/DeprecationInfoProvider;", "deprecatedSinceKotlinAnnotation", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "deprecatedAnnotation", "propagatesToOverride", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;Z)V", "takeIfVersionMatches", "Lorg/jetbrains/kotlin/resolve/deprecation/DeprecationLevelValue;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "index", Argument.Delimiters.none, "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "computeDeprecationInfo", "Lorg/jetbrains/kotlin/fir/declarations/FirDeprecationInfo;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class DeprecatedSinceKotlinProvider extends DeprecationInfoProvider {
    private final FirAnnotation deprecatedAnnotation;
    private final FirAnnotation deprecatedSinceKotlinAnnotation;
    private final boolean propagatesToOverride;

    public DeprecatedSinceKotlinProvider(FirAnnotation firAnnotation, FirAnnotation firAnnotation2, boolean z) {
        firAnnotation.getClass();
        firAnnotation2.getClass();
        this.deprecatedSinceKotlinAnnotation = firAnnotation;
        this.deprecatedAnnotation = firAnnotation2;
        this.propagatesToOverride = z;
    }

    private final DeprecationLevelValue takeIfVersionMatches(DeprecationLevelValue deprecationLevelValue, Name name, int i, LanguageVersionSettings languageVersionSettings) {
        ApiVersion apiVersion;
        List<FirExpression> arguments;
        FirExpression firExpressionFindArgumentByName = FirAnnotationUtilsKt.findArgumentByName(this.deprecatedSinceKotlinAnnotation, name, false);
        if (firExpressionFindArgumentByName == null) {
            FirAnnotation firAnnotation = this.deprecatedSinceKotlinAnnotation;
            FirAnnotationCall firAnnotationCall = firAnnotation instanceof FirAnnotationCall ? (FirAnnotationCall) firAnnotation : null;
            firExpressionFindArgumentByName = (firAnnotationCall == null || (arguments = firAnnotationCall.getArgumentList().getArguments()) == null) ? null : (FirExpression) CollectionsKt.getOrNull(arguments, i);
        }
        FirLiteralExpression firLiteralExpression = firExpressionFindArgumentByName instanceof FirLiteralExpression ? (FirLiteralExpression) firExpressionFindArgumentByName : null;
        Object value = firLiteralExpression != null ? firLiteralExpression.getValue() : null;
        String str = value instanceof String ? (String) value : null;
        if (str == null || (apiVersion = ApiVersion.INSTANCE.parse(str)) == null || apiVersion.compareTo(languageVersionSettings.getApiVersion()) > 0) {
            return null;
        }
        return deprecationLevelValue;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.DeprecationInfoProvider
    public FirDeprecationInfo computeDeprecationInfo(LanguageVersionSettings languageVersionSettings) {
        languageVersionSettings.getClass();
        DeprecationLevelValue deprecationLevelValue = DeprecationLevelValue.HIDDEN;
        StandardClassIds$Annotations.ParameterNames parameterNames = StandardClassIds$Annotations.ParameterNames.INSTANCE;
        DeprecationLevelValue deprecationLevelValueTakeIfVersionMatches = takeIfVersionMatches(deprecationLevelValue, parameterNames.getDeprecatedSinceKotlinHiddenSince(), 2, languageVersionSettings);
        if (deprecationLevelValueTakeIfVersionMatches == null && (deprecationLevelValueTakeIfVersionMatches = takeIfVersionMatches(DeprecationLevelValue.ERROR, parameterNames.getDeprecatedSinceKotlinErrorSince(), 1, languageVersionSettings)) == null) {
            deprecationLevelValueTakeIfVersionMatches = takeIfVersionMatches(DeprecationLevelValue.WARNING, parameterNames.getDeprecatedSinceKotlinWarningSince(), 0, languageVersionSettings);
        }
        if (deprecationLevelValueTakeIfVersionMatches != null) {
            return new SimpleFirDeprecationInfo(deprecationLevelValueTakeIfVersionMatches, this.propagatesToOverride, this.deprecatedAnnotation);
        }
        return null;
    }
}
