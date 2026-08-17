package org.jetbrains.kotlin.load.kotlin;

import com.intellij.openapi.project.Project;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007b\u0002\b\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/load/kotlin/ModuleVisibilityManager$SERVICE;", "", "<init>", "()V", "getInstance", "Lorg/jetbrains/kotlin/load/kotlin/ModuleVisibilityManager;", "project", "Lcom/intellij/openapi/project/Project;", "Lkotlin/jvm/JvmStatic;", "org.jetbrains.kotlin:frontend.java"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ModuleVisibilityManager$SERVICE {
    public static final ModuleVisibilityManager$SERVICE INSTANCE = new ModuleVisibilityManager$SERVICE();

    private ModuleVisibilityManager$SERVICE() {
    }

    @JvmStatic
    public static final ModuleVisibilityManager getInstance(Project project) {
        project.getClass();
        Object service = project.getService(ModuleVisibilityManager.class);
        service.getClass();
        return (ModuleVisibilityManager) service;
    }
}
