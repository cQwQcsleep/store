package org.jetbrains.kotlin.fir.declarations;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.resolve.deprecation.DeprecationLevelValue;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/SimpleDeprecatedProvider;", "Lorg/jetbrains/kotlin/fir/declarations/DeprecationInfoProvider;", "level", "Lorg/jetbrains/kotlin/resolve/deprecation/DeprecationLevelValue;", "propagatesToOverride", Argument.Delimiters.none, "annotation", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "<init>", "(Lorg/jetbrains/kotlin/resolve/deprecation/DeprecationLevelValue;ZLorg/jetbrains/kotlin/fir/expressions/FirAnnotation;)V", "computeDeprecationInfo", "Lorg/jetbrains/kotlin/fir/declarations/FirDeprecationInfo;", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SimpleDeprecatedProvider extends DeprecationInfoProvider {
    private final FirAnnotation annotation;
    private final DeprecationLevelValue level;
    private final boolean propagatesToOverride;

    public SimpleDeprecatedProvider(DeprecationLevelValue deprecationLevelValue, boolean z, FirAnnotation firAnnotation) {
        deprecationLevelValue.getClass();
        firAnnotation.getClass();
        this.level = deprecationLevelValue;
        this.propagatesToOverride = z;
        this.annotation = firAnnotation;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.DeprecationInfoProvider
    public FirDeprecationInfo computeDeprecationInfo(LanguageVersionSettings languageVersionSettings) {
        languageVersionSettings.getClass();
        return new SimpleFirDeprecationInfo(this.level, this.propagatesToOverride, this.annotation);
    }
}
