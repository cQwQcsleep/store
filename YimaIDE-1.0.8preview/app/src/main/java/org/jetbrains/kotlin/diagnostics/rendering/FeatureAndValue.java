package org.jetbrains.kotlin.diagnostics.rendering;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/rendering/FeatureAndValue;", Argument.Delimiters.none, "feature", "Lorg/jetbrains/kotlin/config/LanguageFeature;", "value", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/config/LanguageFeature;Ljava/lang/String;)V", "getFeature", "()Lorg/jetbrains/kotlin/config/LanguageFeature;", "getValue", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", "org.jetbrains.kotlin:frontend.common-psi"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final /* data */ class FeatureAndValue {
    private final LanguageFeature feature;
    private final String value;

    public FeatureAndValue(LanguageFeature languageFeature, String str) {
        languageFeature.getClass();
        str.getClass();
        this.feature = languageFeature;
        this.value = str;
    }

    public static /* synthetic */ FeatureAndValue copy$default(FeatureAndValue featureAndValue, LanguageFeature languageFeature, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            languageFeature = featureAndValue.feature;
        }
        if ((i & 2) != 0) {
            str = featureAndValue.value;
        }
        return featureAndValue.copy(languageFeature, str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final LanguageFeature getFeature() {
        return this.feature;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getValue() {
        return this.value;
    }

    public final FeatureAndValue copy(LanguageFeature feature, String value) {
        feature.getClass();
        value.getClass();
        return new FeatureAndValue(feature, value);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FeatureAndValue)) {
            return false;
        }
        FeatureAndValue featureAndValue = (FeatureAndValue) other;
        return this.feature == featureAndValue.feature && Intrinsics.areEqual(this.value, featureAndValue.value);
    }

    public final LanguageFeature getFeature() {
        return this.feature;
    }

    public final String getValue() {
        return this.value;
    }

    public int hashCode() {
        return (this.feature.hashCode() * 31) + this.value.hashCode();
    }

    public String toString() {
        return "FeatureAndValue(feature=" + this.feature + ", value=" + this.value + ')';
    }
}
