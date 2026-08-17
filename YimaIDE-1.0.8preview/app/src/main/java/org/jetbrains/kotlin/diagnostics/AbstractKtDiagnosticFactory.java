package org.jetbrains.kotlin.diagnostics;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.AnalysisFlags;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.config.WarningLevel;
import org.jetbrains.kotlin.diagnostics.rendering.BaseDiagnosticRendererFactory;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B!\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0015\u001a\u00020\u0016J\n\u0010\u0017\u001a\u00020\u0003H\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013\u0082\u0001\u0002\u0018\u0019¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/AbstractKtDiagnosticFactory;", Argument.Delimiters.none, ModuleXmlParser.NAME, Argument.Delimiters.none, "severity", "Lorg/jetbrains/kotlin/diagnostics/Severity;", "rendererFactory", "Lorg/jetbrains/kotlin/diagnostics/rendering/BaseDiagnosticRendererFactory;", "<init>", "(Ljava/lang/String;Lorg/jetbrains/kotlin/diagnostics/Severity;Lorg/jetbrains/kotlin/diagnostics/rendering/BaseDiagnosticRendererFactory;)V", "getName", "()Ljava/lang/String;", "getSeverity", "()Lorg/jetbrains/kotlin/diagnostics/Severity;", "getRendererFactory", "()Lorg/jetbrains/kotlin/diagnostics/rendering/BaseDiagnosticRendererFactory;", "ktRenderer", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticRenderer;", "getKtRenderer", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticRenderer;", "getEffectiveSeverity", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "toString", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryN;", "Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticFactory;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class AbstractKtDiagnosticFactory {
    private final String name;
    private final BaseDiagnosticRendererFactory rendererFactory;
    private final Severity severity;

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[WarningLevel.values().length];
            try {
                iArr[WarningLevel.Error.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[WarningLevel.Warning.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[WarningLevel.Disabled.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private AbstractKtDiagnosticFactory(String str, Severity severity, BaseDiagnosticRendererFactory baseDiagnosticRendererFactory) {
        this.name = str;
        this.severity = severity;
        this.rendererFactory = baseDiagnosticRendererFactory;
    }

    public final Severity getEffectiveSeverity(LanguageVersionSettings languageVersionSettings) {
        languageVersionSettings.getClass();
        WarningLevel warningLevel = (WarningLevel) ((Map) languageVersionSettings.getFlag(AnalysisFlags.INSTANCE.getWarningLevels())).get(this.name);
        int i = warningLevel == null ? -1 : WhenMappings.$EnumSwitchMapping$0[warningLevel.ordinal()];
        if (i == -1) {
            return this.severity;
        }
        if (i == 1) {
            return Severity.ERROR;
        }
        if (i == 2) {
            return Severity.FIXED_WARNING;
        }
        if (i == 3) {
            return null;
        }
        bu8.a();
        return null;
    }

    public final KtDiagnosticRenderer getKtRenderer() {
        KtDiagnosticRenderer ktDiagnosticRenderer = this.rendererFactory.getMAP().get(this);
        if (ktDiagnosticRenderer != null) {
            return ktDiagnosticRenderer;
        }
        StringBuilder sb = new StringBuilder("Renderer is not found for factory ");
        sb.append(this);
        String name = this.rendererFactory.getMAP().getName();
        sb.append(" inside ");
        sb.append(name);
        sb.append(" renderer map");
        throw new IllegalStateException(sb.toString().toString());
    }

    public final String getName() {
        return this.name;
    }

    public final BaseDiagnosticRendererFactory getRendererFactory() {
        return this.rendererFactory;
    }

    public final Severity getSeverity() {
        return this.severity;
    }

    public String toString() {
        return this.name;
    }

    public /* synthetic */ AbstractKtDiagnosticFactory(String str, Severity severity, BaseDiagnosticRendererFactory baseDiagnosticRendererFactory, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, severity, baseDiagnosticRendererFactory);
    }
}
