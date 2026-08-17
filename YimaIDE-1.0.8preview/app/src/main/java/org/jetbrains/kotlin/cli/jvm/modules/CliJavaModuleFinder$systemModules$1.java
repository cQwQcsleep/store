package org.jetbrains.kotlin.cli.jvm.modules;

import com.intellij.openapi.vfs.VirtualFile;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.AdaptedFunctionReference;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.resolve.jvm.modules.JavaModule;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* synthetic */ class CliJavaModuleFinder$systemModules$1 extends AdaptedFunctionReference implements Function1<VirtualFile, JavaModule.Explicit> {
    public CliJavaModuleFinder$systemModules$1(Object obj) {
        super(1, obj, CliJavaModuleFinder.class, "findSystemModule", "findSystemModule(Lcom/intellij/openapi/vfs/VirtualFile;Z)Lorg/jetbrains/kotlin/resolve/jvm/modules/JavaModule$Explicit;", 0);
    }

    public final JavaModule.Explicit invoke(VirtualFile virtualFile) {
        virtualFile.getClass();
        return CliJavaModuleFinder.findSystemModule$default((CliJavaModuleFinder) ((AdaptedFunctionReference) this).receiver, virtualFile, false, 2, null);
    }
}
