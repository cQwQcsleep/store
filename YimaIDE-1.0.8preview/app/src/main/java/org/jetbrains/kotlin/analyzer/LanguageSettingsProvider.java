package org.jetbrains.kotlin.analyzer;

import com.intellij.openapi.project.Project;
import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.LanguageVersionSettingsImpl;
import org.jetbrains.kotlin.platform.TargetPlatformVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001\nJ\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000bÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/analyzer/LanguageSettingsProvider;", "", "getLanguageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "moduleInfo", "Lorg/jetbrains/kotlin/analyzer/ModuleInfo;", "project", "Lcom/intellij/openapi/project/Project;", "getTargetPlatform", "Lorg/jetbrains/kotlin/platform/TargetPlatformVersion;", "Default", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface LanguageSettingsProvider {

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/analyzer/LanguageSettingsProvider$Default;", "Lorg/jetbrains/kotlin/analyzer/LanguageSettingsProvider;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "getLanguageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettingsImpl;", "moduleInfo", "Lorg/jetbrains/kotlin/analyzer/ModuleInfo;", "project", "Lcom/intellij/openapi/project/Project;", "getTargetPlatform", "Lorg/jetbrains/kotlin/platform/TargetPlatformVersion;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Default implements LanguageSettingsProvider {
        public static final Default INSTANCE = new Default();

        private Default() {
        }

        @Override // org.jetbrains.kotlin.analyzer.LanguageSettingsProvider
        public LanguageVersionSettingsImpl getLanguageVersionSettings(ModuleInfo moduleInfo, Project project) {
            moduleInfo.getClass();
            project.getClass();
            return LanguageVersionSettingsImpl.DEFAULT;
        }

        @Override // org.jetbrains.kotlin.analyzer.LanguageSettingsProvider
        public TargetPlatformVersion getTargetPlatform(ModuleInfo moduleInfo, Project project) {
            moduleInfo.getClass();
            project.getClass();
            return TargetPlatformVersion.NoVersion.INSTANCE;
        }
    }

    LanguageVersionSettings getLanguageVersionSettings(ModuleInfo moduleInfo, Project project);

    TargetPlatformVersion getTargetPlatform(ModuleInfo moduleInfo, Project project);
}
