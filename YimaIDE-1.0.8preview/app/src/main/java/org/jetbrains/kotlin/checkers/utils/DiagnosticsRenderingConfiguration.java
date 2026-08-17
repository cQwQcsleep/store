package org.jetbrains.kotlin.checkers.utils;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.jdt.internal.compiler.util.Util;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.config.LanguageVersionSettings;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005¢\u0006\u0004\b\t\u0010\nJ\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J5\u0010\u0016\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u0017\u001a\u00020\u00052\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0019\u001a\u00020\u001aHÖ\u0081\u0004J\n\u0010\u001b\u001a\u00020\u0003HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000e¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/checkers/utils/DiagnosticsRenderingConfiguration;", "", "platform", "", "withNewInference", "", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "skipDebugInfoDiagnostics", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/lang/String;ZLorg/jetbrains/kotlin/config/LanguageVersionSettings;Z)V", "getPlatform", "()Ljava/lang/String;", "getWithNewInference", "()Z", "getLanguageVersionSettings", "()Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "getSkipDebugInfoDiagnostics", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final /* data */ class DiagnosticsRenderingConfiguration {
    private final LanguageVersionSettings languageVersionSettings;
    private final String platform;
    private final boolean skipDebugInfoDiagnostics;
    private final boolean withNewInference;

    public DiagnosticsRenderingConfiguration(String str, boolean z, LanguageVersionSettings languageVersionSettings, boolean z2) {
        this.platform = str;
        this.withNewInference = z;
        this.languageVersionSettings = languageVersionSettings;
        this.skipDebugInfoDiagnostics = z2;
    }

    public static /* synthetic */ DiagnosticsRenderingConfiguration copy$default(DiagnosticsRenderingConfiguration diagnosticsRenderingConfiguration, String str, boolean z, LanguageVersionSettings languageVersionSettings, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = diagnosticsRenderingConfiguration.platform;
        }
        if ((i & 2) != 0) {
            z = diagnosticsRenderingConfiguration.withNewInference;
        }
        if ((i & 4) != 0) {
            languageVersionSettings = diagnosticsRenderingConfiguration.languageVersionSettings;
        }
        if ((i & 8) != 0) {
            z2 = diagnosticsRenderingConfiguration.skipDebugInfoDiagnostics;
        }
        return diagnosticsRenderingConfiguration.copy(str, z, languageVersionSettings, z2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getPlatform() {
        return this.platform;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getWithNewInference() {
        return this.withNewInference;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final LanguageVersionSettings getLanguageVersionSettings() {
        return this.languageVersionSettings;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getSkipDebugInfoDiagnostics() {
        return this.skipDebugInfoDiagnostics;
    }

    public final DiagnosticsRenderingConfiguration copy(String platform, boolean withNewInference, LanguageVersionSettings languageVersionSettings, boolean skipDebugInfoDiagnostics) {
        return new DiagnosticsRenderingConfiguration(platform, withNewInference, languageVersionSettings, skipDebugInfoDiagnostics);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DiagnosticsRenderingConfiguration)) {
            return false;
        }
        DiagnosticsRenderingConfiguration diagnosticsRenderingConfiguration = (DiagnosticsRenderingConfiguration) other;
        return Intrinsics.areEqual(this.platform, diagnosticsRenderingConfiguration.platform) && this.withNewInference == diagnosticsRenderingConfiguration.withNewInference && Intrinsics.areEqual(this.languageVersionSettings, diagnosticsRenderingConfiguration.languageVersionSettings) && this.skipDebugInfoDiagnostics == diagnosticsRenderingConfiguration.skipDebugInfoDiagnostics;
    }

    public final LanguageVersionSettings getLanguageVersionSettings() {
        return this.languageVersionSettings;
    }

    public final String getPlatform() {
        return this.platform;
    }

    public final boolean getSkipDebugInfoDiagnostics() {
        return this.skipDebugInfoDiagnostics;
    }

    public final boolean getWithNewInference() {
        return this.withNewInference;
    }

    public int hashCode() {
        String str = this.platform;
        int iHashCode = (((str == null ? 0 : str.hashCode()) * 31) + Boolean.hashCode(this.withNewInference)) * 31;
        LanguageVersionSettings languageVersionSettings = this.languageVersionSettings;
        return ((iHashCode + (languageVersionSettings != null ? languageVersionSettings.hashCode() : 0)) * 31) + Boolean.hashCode(this.skipDebugInfoDiagnostics);
    }

    public String toString() {
        return "DiagnosticsRenderingConfiguration(platform=" + this.platform + ", withNewInference=" + this.withNewInference + ", languageVersionSettings=" + this.languageVersionSettings + ", skipDebugInfoDiagnostics=" + this.skipDebugInfoDiagnostics + Util.C_PARAM_END;
    }

    public /* synthetic */ DiagnosticsRenderingConfiguration(String str, boolean z, LanguageVersionSettings languageVersionSettings, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, z, languageVersionSettings, (i & 8) != 0 ? false : z2);
    }
}
