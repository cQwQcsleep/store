package org.jetbrains.kotlin.idea;

import com.intellij.openapi.application.ApplicationManager;
import javax.swing.Icon;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public abstract class KotlinIconProviderService {

    public static class CompilerKotlinFileIconProviderService extends KotlinIconProviderService {
        @Override // org.jetbrains.kotlin.idea.KotlinIconProviderService
        public Icon getBuiltInFileIcon() {
            return null;
        }

        @Override // org.jetbrains.kotlin.idea.KotlinIconProviderService
        public Icon getFileIcon() {
            return null;
        }
    }

    public static KotlinIconProviderService getInstance() {
        KotlinIconProviderService kotlinIconProviderService = (KotlinIconProviderService) ApplicationManager.getApplication().getService(KotlinIconProviderService.class);
        return kotlinIconProviderService != null ? kotlinIconProviderService : new CompilerKotlinFileIconProviderService();
    }

    public abstract Icon getBuiltInFileIcon();

    public abstract Icon getFileIcon();
}
