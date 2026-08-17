package androidx.compose.compiler.plugins.kotlin;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSeverity;
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSourceLocation;
import org.jetbrains.kotlin.cli.common.messages.MessageCollector;
import org.jetbrains.kotlin.config.CommonConfigurationKeys;
import org.jetbrains.kotlin.config.CompilerConfiguration;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\tH\u0002J\u0010\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\tH\u0002J\u0016\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00020\tJ\u0016\u0010\u0015\u001a\u00020\u000e2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0002J\u000e\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u0019J\u0010\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\tH\u0002R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/FeatureFlags;", "", "featureConfiguration", "", "", "<init>", "(Ljava/util/List;)V", "setForCompatibility", "", "Landroidx/compose/compiler/plugins/kotlin/FeatureFlag;", "duplicate", "enabledFeatures", "disabledFeatures", "enableFeature", "", "feature", "disableFeature", "setFeature", "value", "", "isEnabled", "processConfigurationList", "featuresNames", "validateFeatureFlags", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "currentState", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class FeatureFlags {
    private final Set<FeatureFlag> disabledFeatures;
    private final Set<FeatureFlag> duplicate;
    private final Set<FeatureFlag> enabledFeatures;
    private final Set<FeatureFlag> setForCompatibility;

    public FeatureFlags(List<String> list) {
        list.getClass();
        this.setForCompatibility = new LinkedHashSet();
        this.duplicate = new LinkedHashSet();
        this.enabledFeatures = new LinkedHashSet();
        this.disabledFeatures = new LinkedHashSet();
        processConfigurationList(list);
    }

    private final String currentState(FeatureFlag feature) {
        return "With the given options set, the feature is ".concat(isEnabled(feature) ? "enabled" : "disabled");
    }

    private final void disableFeature(FeatureFlag feature) {
        if (this.enabledFeatures.contains(feature)) {
            this.duplicate.add(feature);
            this.enabledFeatures.remove(feature);
        }
        this.disabledFeatures.add(feature);
    }

    private final void enableFeature(FeatureFlag feature) {
        if (this.disabledFeatures.contains(feature)) {
            this.duplicate.add(feature);
            this.disabledFeatures.remove(feature);
        }
        this.enabledFeatures.add(feature);
    }

    private final void processConfigurationList(List<String> featuresNames) {
        Iterator<String> it = featuresNames.iterator();
        while (it.hasNext()) {
            Pair<FeatureFlag, Boolean> pairFromString = FeatureFlag.INSTANCE.fromString(it.next());
            FeatureFlag featureFlag = (FeatureFlag) pairFromString.component1();
            boolean zBooleanValue = ((Boolean) pairFromString.component2()).booleanValue();
            if (featureFlag != null) {
                if (zBooleanValue) {
                    enableFeature(featureFlag);
                } else {
                    disableFeature(featureFlag);
                }
            }
        }
    }

    private static final void validateFeatureFlags$report(Set<FeatureFlag> set, MessageCollector messageCollector, FeatureFlag featureFlag, String str) {
        if (set.contains(featureFlag)) {
            return;
        }
        set.add(featureFlag);
        MessageCollector.report$default(messageCollector, CompilerMessageSeverity.WARNING, str, (CompilerMessageSourceLocation) null, 4, (Object) null);
    }

    public final boolean isEnabled(FeatureFlag feature) {
        feature.getClass();
        if (this.enabledFeatures.contains(feature)) {
            return true;
        }
        return feature.getDefault() && !this.disabledFeatures.contains(feature);
    }

    public final void setFeature(FeatureFlag feature, boolean value) {
        feature.getClass();
        if (feature.getDefault() != value) {
            this.setForCompatibility.add(feature);
            if (value) {
                enableFeature(feature);
            } else {
                disableFeature(feature);
            }
        }
    }

    public final void validateFeatureFlags(CompilerConfiguration configuration) {
        configuration.getClass();
        MessageCollector messageCollector = (MessageCollector) configuration.get(CommonConfigurationKeys.MESSAGE_COLLECTOR_KEY);
        if (messageCollector != null) {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            for (FeatureFlag featureFlag : CollectionsKt.intersect(this.setForCompatibility, SetsKt.plus(this.enabledFeatures, this.disabledFeatures))) {
                validateFeatureFlags$report(linkedHashSet, messageCollector, featureFlag, "Feature " + ComposePluginKt.featureFlagName() + '=' + featureFlag.getFeatureName() + " is using featureFlags and is set using the deprecated option. It is recommended to only use featureFlag. " + currentState(featureFlag));
            }
            for (FeatureFlag featureFlag2 : this.duplicate) {
                if (!linkedHashSet.contains(featureFlag2)) {
                    validateFeatureFlags$report(linkedHashSet, messageCollector, featureFlag2, "Feature " + ComposePluginKt.featureFlagName() + '=' + featureFlag2.getFeatureName() + " was both enabled and disabled. " + currentState(featureFlag2));
                }
            }
            for (FeatureFlag featureFlag3 : this.disabledFeatures) {
                if (!featureFlag3.getDefault()) {
                    validateFeatureFlags$report(linkedHashSet, messageCollector, featureFlag3, "The feature " + ComposePluginKt.featureFlagName() + '=' + featureFlag3.getFeatureName() + " is disabled by default and specifying this option explicitly is not necessary.");
                }
            }
            for (FeatureFlag featureFlag4 : this.enabledFeatures) {
                if (featureFlag4.getDefault()) {
                    validateFeatureFlags$report(linkedHashSet, messageCollector, featureFlag4, "The feature " + ComposePluginKt.featureFlagName() + '=' + featureFlag4.getFeatureName() + " is enabled by default and specifying this option explicitly is not necessary.");
                }
            }
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public FeatureFlags() {
        List list = null;
        this(list, 1, list);
    }

    public /* synthetic */ FeatureFlags(List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? CollectionsKt.emptyList() : list);
    }
}
