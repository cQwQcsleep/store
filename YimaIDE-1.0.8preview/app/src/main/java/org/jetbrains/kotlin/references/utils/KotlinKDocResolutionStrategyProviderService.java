package org.jetbrains.kotlin.references.utils;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.project.Project;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004J\b\u0010\u0002\u001a\u00020\u0003H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0005À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/references/utils/KotlinKDocResolutionStrategyProviderService;", "Lcom/intellij/openapi/Disposable;", "shouldUseExperimentalStrategy", "", "Companion", "org.jetbrains.kotlin:kt-references"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface KotlinKDocResolutionStrategyProviderService extends Disposable {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/references/utils/KotlinKDocResolutionStrategyProviderService$Companion;", "", "<init>", "()V", "getService", "Lorg/jetbrains/kotlin/references/utils/KotlinKDocResolutionStrategyProviderService;", "project", "Lcom/intellij/openapi/project/Project;", "org.jetbrains.kotlin:kt-references"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        public final KotlinKDocResolutionStrategyProviderService getService(Project project) {
            project.getClass();
            return (KotlinKDocResolutionStrategyProviderService) project.getService(KotlinKDocResolutionStrategyProviderService.class);
        }
    }

    boolean shouldUseExperimentalStrategy();
}
