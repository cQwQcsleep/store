package org.jetbrains.kotlin.cli.common.arguments;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0014\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004J\n\u0010\u0019\u001a\u00020\u0007HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/arguments/ManualLanguageFeatureSetting;", Argument.Delimiters.none, "languageFeature", "Lorg/jetbrains/kotlin/config/LanguageFeature;", "state", "Lorg/jetbrains/kotlin/config/LanguageFeature$State;", "stringRepresentation", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/config/LanguageFeature;Lorg/jetbrains/kotlin/config/LanguageFeature$State;Ljava/lang/String;)V", "getLanguageFeature", "()Lorg/jetbrains/kotlin/config/LanguageFeature;", "getState", "()Lorg/jetbrains/kotlin/config/LanguageFeature$State;", "getStringRepresentation", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class ManualLanguageFeatureSetting {
    private final LanguageFeature languageFeature;
    private final LanguageFeature.State state;
    private final String stringRepresentation;

    public ManualLanguageFeatureSetting(LanguageFeature languageFeature, LanguageFeature.State state, String str) {
        languageFeature.getClass();
        state.getClass();
        str.getClass();
        this.languageFeature = languageFeature;
        this.state = state;
        this.stringRepresentation = str;
    }

    public static /* synthetic */ ManualLanguageFeatureSetting copy$default(ManualLanguageFeatureSetting manualLanguageFeatureSetting, LanguageFeature languageFeature, LanguageFeature.State state, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            languageFeature = manualLanguageFeatureSetting.languageFeature;
        }
        if ((i & 2) != 0) {
            state = manualLanguageFeatureSetting.state;
        }
        if ((i & 4) != 0) {
            str = manualLanguageFeatureSetting.stringRepresentation;
        }
        return manualLanguageFeatureSetting.copy(languageFeature, state, str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final LanguageFeature getLanguageFeature() {
        return this.languageFeature;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final LanguageFeature.State getState() {
        return this.state;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getStringRepresentation() {
        return this.stringRepresentation;
    }

    public final ManualLanguageFeatureSetting copy(LanguageFeature languageFeature, LanguageFeature.State state, String stringRepresentation) {
        languageFeature.getClass();
        state.getClass();
        stringRepresentation.getClass();
        return new ManualLanguageFeatureSetting(languageFeature, state, stringRepresentation);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ManualLanguageFeatureSetting)) {
            return false;
        }
        ManualLanguageFeatureSetting manualLanguageFeatureSetting = (ManualLanguageFeatureSetting) other;
        return this.languageFeature == manualLanguageFeatureSetting.languageFeature && this.state == manualLanguageFeatureSetting.state && Intrinsics.areEqual(this.stringRepresentation, manualLanguageFeatureSetting.stringRepresentation);
    }

    public final LanguageFeature getLanguageFeature() {
        return this.languageFeature;
    }

    public final LanguageFeature.State getState() {
        return this.state;
    }

    public final String getStringRepresentation() {
        return this.stringRepresentation;
    }

    public int hashCode() {
        return (((this.languageFeature.hashCode() * 31) + this.state.hashCode()) * 31) + this.stringRepresentation.hashCode();
    }

    public String toString() {
        return "ManualLanguageFeatureSetting(languageFeature=" + this.languageFeature + ", state=" + this.state + ", stringRepresentation=" + this.stringRepresentation + ')';
    }
}
