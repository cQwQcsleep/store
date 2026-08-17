package org.jetbrains.kotlin.fir.declarations;

import kotlin.DeprecationLevel;
import kotlin.Metadata;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.metadata.deserialization.VersionRequirement;
import org.jetbrains.kotlin.resolve.deprecation.CommonDeprecationUtilsKt;
import org.jetbrains.kotlin.resolve.deprecation.DeprecationLevelValue;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/RequireKotlinProvider;", "Lorg/jetbrains/kotlin/fir/declarations/DeprecationInfoProvider;", "versionRequirement", "Lorg/jetbrains/kotlin/metadata/deserialization/VersionRequirement;", "<init>", "(Lorg/jetbrains/kotlin/metadata/deserialization/VersionRequirement;)V", "computeDeprecationInfo", "Lorg/jetbrains/kotlin/fir/declarations/FirDeprecationInfo;", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class RequireKotlinProvider extends DeprecationInfoProvider {
    private final VersionRequirement versionRequirement;

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DeprecationLevel.values().length];
            try {
                iArr[DeprecationLevel.WARNING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[DeprecationLevel.ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[DeprecationLevel.HIDDEN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public RequireKotlinProvider(VersionRequirement versionRequirement) {
        versionRequirement.getClass();
        this.versionRequirement = versionRequirement;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.DeprecationInfoProvider
    public FirDeprecationInfo computeDeprecationInfo(LanguageVersionSettings languageVersionSettings) {
        DeprecationLevelValue deprecationLevelValue;
        languageVersionSettings.getClass();
        if (CommonDeprecationUtilsKt.isFulfilled(this.versionRequirement, languageVersionSettings)) {
            return null;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[this.versionRequirement.getLevel().ordinal()];
        if (i == 1) {
            deprecationLevelValue = DeprecationLevelValue.WARNING;
        } else if (i == 2) {
            deprecationLevelValue = DeprecationLevelValue.ERROR;
        } else {
            if (i != 3) {
                bu8.a();
                return null;
            }
            deprecationLevelValue = DeprecationLevelValue.HIDDEN;
        }
        return new RequireKotlinDeprecationInfo(deprecationLevelValue, this.versionRequirement);
    }
}
