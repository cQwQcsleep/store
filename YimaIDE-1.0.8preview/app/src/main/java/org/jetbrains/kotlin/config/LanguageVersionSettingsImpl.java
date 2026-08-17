package org.jetbrains.kotlin.config;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eBK\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u001a\b\u0002\u0010\u0006\u001a\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0007\u0012\u0014\b\u0002\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\u0007¢\u0006\u0004\b\r\u0010\u000eJ!\u0010\u0013\u001a\u0002H\u0014\"\u0004\b\u0000\u0010\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00140\bH\u0016¢\u0006\u0002\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u000bH\u0016J\u0014\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\u0007H\u0016J\n\u0010\u001a\u001a\u00020\u001bH\u0096\u0080\u0004J\b\u0010\u001c\u001a\u00020\u001dH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0006\u001a\u0010\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b\u0012\u0002\b\u00030\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lorg/jetbrains/kotlin/config/LanguageVersionSettingsImpl;", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "languageVersion", "Lorg/jetbrains/kotlin/config/LanguageVersion;", "apiVersion", "Lorg/jetbrains/kotlin/config/ApiVersion;", "analysisFlags", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/config/AnalysisFlag;", Argument.Delimiters.none, "specificFeatures", "Lorg/jetbrains/kotlin/config/LanguageFeature;", "Lorg/jetbrains/kotlin/config/LanguageFeature$State;", "<init>", "(Lorg/jetbrains/kotlin/config/LanguageVersion;Lorg/jetbrains/kotlin/config/ApiVersion;Ljava/util/Map;Ljava/util/Map;)V", "getLanguageVersion", "()Lorg/jetbrains/kotlin/config/LanguageVersion;", "getApiVersion", "()Lorg/jetbrains/kotlin/config/ApiVersion;", "getFlag", "T", "flag", "(Lorg/jetbrains/kotlin/config/AnalysisFlag;)Ljava/lang/Object;", "getFeatureSupport", "feature", "getCustomizedLanguageFeatures", "toString", Argument.Delimiters.none, "isPreRelease", Argument.Delimiters.none, "Companion", "org.jetbrains.kotlin:language.version-settings"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class LanguageVersionSettingsImpl implements LanguageVersionSettings {
    public static final LanguageVersionSettingsImpl DEFAULT = new LanguageVersionSettingsImpl(LanguageVersion.LATEST_STABLE, ApiVersion.LATEST_STABLE, null, null, 12, null);
    private final Map<AnalysisFlag<?>, ?> analysisFlags;
    private final ApiVersion apiVersion;
    private final LanguageVersion languageVersion;
    private final Map<LanguageFeature, LanguageFeature.State> specificFeatures;

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LanguageFeature.State.values().length];
            try {
                iArr[LanguageFeature.State.ENABLED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[LanguageFeature.State.DISABLED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public LanguageVersionSettingsImpl(LanguageVersion languageVersion, ApiVersion apiVersion, Map<AnalysisFlag<?>, ? extends Object> map, Map<LanguageFeature, ? extends LanguageFeature.State> map2) {
        languageVersion.getClass();
        apiVersion.getClass();
        map.getClass();
        map2.getClass();
        this.languageVersion = languageVersion;
        this.apiVersion = apiVersion;
        Map<AnalysisFlag<?>, ?> mapUnmodifiableMap = Collections.unmodifiableMap(map);
        mapUnmodifiableMap.getClass();
        this.analysisFlags = mapUnmodifiableMap;
        Map<LanguageFeature, LanguageFeature.State> mapUnmodifiableMap2 = Collections.unmodifiableMap(map2);
        mapUnmodifiableMap2.getClass();
        this.specificFeatures = mapUnmodifiableMap2;
    }

    @Override // org.jetbrains.kotlin.config.LanguageVersionSettings
    public ApiVersion getApiVersion() {
        return this.apiVersion;
    }

    @Override // org.jetbrains.kotlin.config.LanguageVersionSettings
    public Map<LanguageFeature, LanguageFeature.State> getCustomizedLanguageFeatures() {
        return this.specificFeatures;
    }

    @Override // org.jetbrains.kotlin.config.LanguageVersionSettings
    public LanguageFeature.State getFeatureSupport(LanguageFeature feature) {
        feature.getClass();
        LanguageFeature.State state = this.specificFeatures.get(feature);
        if (state != null) {
            return state;
        }
        return LanguageVersionSettingsKt.isEnabledByDefault(this, feature) ? LanguageFeature.State.ENABLED : LanguageFeature.State.DISABLED;
    }

    @Override // org.jetbrains.kotlin.config.LanguageVersionSettings
    public <T> T getFlag(AnalysisFlag<? extends T> flag) {
        flag.getClass();
        T t = (T) this.analysisFlags.get(flag);
        return t == null ? flag.getDefaultValue() : t;
    }

    @Override // org.jetbrains.kotlin.config.LanguageVersionSettings
    public LanguageVersion getLanguageVersion() {
        return this.languageVersion;
    }

    @Override // org.jetbrains.kotlin.config.LanguageVersionSettings
    public boolean isPreRelease() {
        if (LanguageVersionSettingsKt.isPreRelease(getLanguageVersion())) {
            return true;
        }
        Map<LanguageFeature, LanguageFeature.State> map = this.specificFeatures;
        if (map.isEmpty()) {
            return false;
        }
        for (Map.Entry<LanguageFeature, LanguageFeature.State> entry : map.entrySet()) {
            LanguageFeature key = entry.getKey();
            if (entry.getValue() == LanguageFeature.State.ENABLED && LanguageVersionSettingsKt.forcesPreReleaseBinariesIfEnabled(key)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        char c;
        StringBuilder sb = new StringBuilder();
        sb.append("Language = " + getLanguageVersion() + ", API = " + getApiVersion());
        for (Map.Entry entry : CollectionsKt.sortedWith(this.specificFeatures.entrySet(), new Comparator() { // from class: org.jetbrains.kotlin.config.LanguageVersionSettingsImpl$toString$lambda$0$$inlined$sortedBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(Integer.valueOf(((LanguageFeature) ((Map.Entry) t).getKey()).ordinal()), Integer.valueOf(((LanguageFeature) ((Map.Entry) t2).getKey()).ordinal()));
            }
        })) {
            LanguageFeature languageFeature = (LanguageFeature) entry.getKey();
            int i = WhenMappings.$EnumSwitchMapping$0[((LanguageFeature.State) entry.getValue()).ordinal()];
            if (i == 1) {
                c = '+';
            } else {
                if (i != 2) {
                    bu8.a();
                    return null;
                }
                c = '-';
            }
            sb.append(Argument.Delimiters.space + c + languageFeature);
        }
        for (Map.Entry entry2 : CollectionsKt.sortedWith(this.analysisFlags.entrySet(), new Comparator() { // from class: org.jetbrains.kotlin.config.LanguageVersionSettingsImpl$toString$lambda$0$$inlined$sortedBy$2
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(((AnalysisFlag) ((Map.Entry) t).getKey()).getName(), ((AnalysisFlag) ((Map.Entry) t2).getKey()).getName());
            }
        })) {
            sb.append(Argument.Delimiters.space + ((AnalysisFlag) entry2.getKey()) + ':' + entry2.getValue());
        }
        return sb.toString();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LanguageVersionSettingsImpl(LanguageVersion languageVersion, ApiVersion apiVersion, Map<AnalysisFlag<?>, ? extends Object> map) {
        this(languageVersion, apiVersion, map, null, 8, null);
        languageVersion.getClass();
        apiVersion.getClass();
        map.getClass();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LanguageVersionSettingsImpl(LanguageVersion languageVersion, ApiVersion apiVersion) {
        this(languageVersion, apiVersion, null, null, 12, null);
        languageVersion.getClass();
        apiVersion.getClass();
    }

    public /* synthetic */ LanguageVersionSettingsImpl(LanguageVersion languageVersion, ApiVersion apiVersion, Map map, Map map2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(languageVersion, apiVersion, (i & 4) != 0 ? MapsKt.emptyMap() : map, (i & 8) != 0 ? MapsKt.emptyMap() : map2);
    }
}
