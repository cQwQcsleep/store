package org.jetbrains.kotlin.resolve;

import com.intellij.openapi.project.Project;
import kotlin.Metadata;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.descriptors.ModuleDescriptor;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006J\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/resolve/LanguageVersionSettingsProvider;", "", "getModuleLanguageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "module", "Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "Companion", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public interface LanguageVersionSettingsProvider {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/resolve/LanguageVersionSettingsProvider$Companion;", "", "<init>", "()V", "getInstance", "Lorg/jetbrains/kotlin/resolve/LanguageVersionSettingsProvider;", "project", "Lcom/intellij/openapi/project/Project;", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        public final LanguageVersionSettingsProvider getInstance(Project project) {
            project.getClass();
            return (LanguageVersionSettingsProvider) project.getService(LanguageVersionSettingsProvider.class);
        }
    }

    LanguageVersionSettings getModuleLanguageVersionSettings(ModuleDescriptor module);
}
