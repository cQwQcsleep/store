package org.jetbrains.kotlin.js.config;

import java.io.File;
import kotlin.Metadata;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u001a\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0005J\u0010\u0010\u001b\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0005J\u0010\u0010\u001c\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0005J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0007HÆ\u0003J\t\u0010 \u001a\u00020\u0005HÆ\u0003J\t\u0010!\u001a\u00020\nHÆ\u0003J\t\u0010\"\u001a\u00020\fHÆ\u0003JE\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fHÆ\u0001J\u0014\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010'\u001a\u00020(HÖ\u0081\u0004J\n\u0010)\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019¨\u0006*"}, d2 = {"Lorg/jetbrains/kotlin/js/config/WebArtifactConfiguration;", "", "moduleKind", "Lorg/jetbrains/kotlin/js/config/ModuleKind;", "moduleName", "", "outputDirectory", "Ljava/io/File;", "outputName", "granularity", "Lorg/jetbrains/kotlin/js/config/JsGenerationGranularity;", "tsCompilationStrategy", "Lorg/jetbrains/kotlin/js/config/TsCompilationStrategy;", "<init>", "(Lorg/jetbrains/kotlin/js/config/ModuleKind;Ljava/lang/String;Ljava/io/File;Ljava/lang/String;Lorg/jetbrains/kotlin/js/config/JsGenerationGranularity;Lorg/jetbrains/kotlin/js/config/TsCompilationStrategy;)V", "getModuleKind", "()Lorg/jetbrains/kotlin/js/config/ModuleKind;", "getModuleName", "()Ljava/lang/String;", "getOutputDirectory", "()Ljava/io/File;", "getOutputName", "getGranularity", "()Lorg/jetbrains/kotlin/js/config/JsGenerationGranularity;", "getTsCompilationStrategy", "()Lorg/jetbrains/kotlin/js/config/TsCompilationStrategy;", "outputJsFile", "outputSourceMapFile", "outputDtsFile", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "org.jetbrains.kotlin:js.config"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class WebArtifactConfiguration {
    private final JsGenerationGranularity granularity;
    private final ModuleKind moduleKind;
    private final String moduleName;
    private final File outputDirectory;
    private final String outputName;
    private final TsCompilationStrategy tsCompilationStrategy;

    public WebArtifactConfiguration(ModuleKind moduleKind, String str, File file, String str2, JsGenerationGranularity jsGenerationGranularity, TsCompilationStrategy tsCompilationStrategy) {
        moduleKind.getClass();
        str.getClass();
        file.getClass();
        str2.getClass();
        jsGenerationGranularity.getClass();
        tsCompilationStrategy.getClass();
        this.moduleKind = moduleKind;
        this.moduleName = str;
        this.outputDirectory = file;
        this.outputName = str2;
        this.granularity = jsGenerationGranularity;
        this.tsCompilationStrategy = tsCompilationStrategy;
    }

    public static /* synthetic */ WebArtifactConfiguration copy$default(WebArtifactConfiguration webArtifactConfiguration, ModuleKind moduleKind, String str, File file, String str2, JsGenerationGranularity jsGenerationGranularity, TsCompilationStrategy tsCompilationStrategy, int i, Object obj) {
        if ((i & 1) != 0) {
            moduleKind = webArtifactConfiguration.moduleKind;
        }
        if ((i & 2) != 0) {
            str = webArtifactConfiguration.moduleName;
        }
        if ((i & 4) != 0) {
            file = webArtifactConfiguration.outputDirectory;
        }
        if ((i & 8) != 0) {
            str2 = webArtifactConfiguration.outputName;
        }
        if ((i & 16) != 0) {
            jsGenerationGranularity = webArtifactConfiguration.granularity;
        }
        if ((i & 32) != 0) {
            tsCompilationStrategy = webArtifactConfiguration.tsCompilationStrategy;
        }
        JsGenerationGranularity jsGenerationGranularity2 = jsGenerationGranularity;
        TsCompilationStrategy tsCompilationStrategy2 = tsCompilationStrategy;
        return webArtifactConfiguration.copy(moduleKind, str, file, str2, jsGenerationGranularity2, tsCompilationStrategy2);
    }

    public static /* synthetic */ File outputDtsFile$default(WebArtifactConfiguration webArtifactConfiguration, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = webArtifactConfiguration.outputName;
        }
        return webArtifactConfiguration.outputDtsFile(str);
    }

    public static /* synthetic */ File outputJsFile$default(WebArtifactConfiguration webArtifactConfiguration, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = webArtifactConfiguration.outputName;
        }
        return webArtifactConfiguration.outputJsFile(str);
    }

    public static /* synthetic */ File outputSourceMapFile$default(WebArtifactConfiguration webArtifactConfiguration, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = webArtifactConfiguration.outputName;
        }
        return webArtifactConfiguration.outputSourceMapFile(str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final ModuleKind getModuleKind() {
        return this.moduleKind;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getModuleName() {
        return this.moduleName;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final File getOutputDirectory() {
        return this.outputDirectory;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getOutputName() {
        return this.outputName;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final JsGenerationGranularity getGranularity() {
        return this.granularity;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final TsCompilationStrategy getTsCompilationStrategy() {
        return this.tsCompilationStrategy;
    }

    public final WebArtifactConfiguration copy(ModuleKind moduleKind, String moduleName, File outputDirectory, String outputName, JsGenerationGranularity granularity, TsCompilationStrategy tsCompilationStrategy) {
        moduleKind.getClass();
        moduleName.getClass();
        outputDirectory.getClass();
        outputName.getClass();
        granularity.getClass();
        tsCompilationStrategy.getClass();
        return new WebArtifactConfiguration(moduleKind, moduleName, outputDirectory, outputName, granularity, tsCompilationStrategy);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WebArtifactConfiguration)) {
            return false;
        }
        WebArtifactConfiguration webArtifactConfiguration = (WebArtifactConfiguration) other;
        return this.moduleKind == webArtifactConfiguration.moduleKind && Intrinsics.areEqual(this.moduleName, webArtifactConfiguration.moduleName) && Intrinsics.areEqual(this.outputDirectory, webArtifactConfiguration.outputDirectory) && Intrinsics.areEqual(this.outputName, webArtifactConfiguration.outputName) && this.granularity == webArtifactConfiguration.granularity && this.tsCompilationStrategy == webArtifactConfiguration.tsCompilationStrategy;
    }

    public final JsGenerationGranularity getGranularity() {
        return this.granularity;
    }

    public final ModuleKind getModuleKind() {
        return this.moduleKind;
    }

    public final String getModuleName() {
        return this.moduleName;
    }

    public final File getOutputDirectory() {
        return this.outputDirectory;
    }

    public final String getOutputName() {
        return this.outputName;
    }

    public final TsCompilationStrategy getTsCompilationStrategy() {
        return this.tsCompilationStrategy;
    }

    public int hashCode() {
        return (((((((((this.moduleKind.hashCode() * 31) + this.moduleName.hashCode()) * 31) + this.outputDirectory.hashCode()) * 31) + this.outputName.hashCode()) * 31) + this.granularity.hashCode()) * 31) + this.tsCompilationStrategy.hashCode();
    }

    public final File outputDtsFile(String outputName) {
        outputName.getClass();
        return FilesKt.resolve(this.outputDirectory, outputName + this.moduleKind.getDtsExtension());
    }

    public final File outputJsFile(String outputName) {
        outputName.getClass();
        return FilesKt.resolve(this.outputDirectory, outputName + this.moduleKind.getJsExtension());
    }

    public final File outputSourceMapFile(String outputName) {
        outputName.getClass();
        return FilesKt.resolve(this.outputDirectory, outputName + this.moduleKind.getJsExtension() + ".map");
    }

    public String toString() {
        return "WebArtifactConfiguration(moduleKind=" + this.moduleKind + ", moduleName=" + this.moduleName + ", outputDirectory=" + this.outputDirectory + ", outputName=" + this.outputName + ", granularity=" + this.granularity + ", tsCompilationStrategy=" + this.tsCompilationStrategy + ')';
    }
}
