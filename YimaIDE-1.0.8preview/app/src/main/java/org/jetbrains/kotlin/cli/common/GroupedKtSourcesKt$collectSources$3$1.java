package org.jetbrains.kotlin.cli.common;

import com.intellij.openapi.vfs.VirtualFile;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.jvm.compiler.VfsBasedProjectEnvironment;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* synthetic */ class GroupedKtSourcesKt$collectSources$3$1 extends FunctionReferenceImpl implements Function1<File, VirtualFile> {
    final /* synthetic */ VfsBasedProjectEnvironment $projectEnvironment;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GroupedKtSourcesKt$collectSources$3$1(VfsBasedProjectEnvironment vfsBasedProjectEnvironment) {
        super(1, Intrinsics.Kotlin.class, "findVirtualFile", "collectSources$findVirtualFile(Lorg/jetbrains/kotlin/cli/jvm/compiler/VfsBasedProjectEnvironment;Ljava/io/File;)Lcom/intellij/openapi/vfs/VirtualFile;", 0);
        this.$projectEnvironment = vfsBasedProjectEnvironment;
    }

    public final VirtualFile invoke(File file) {
        file.getClass();
        return GroupedKtSourcesKt.collectSources$findVirtualFile(this.$projectEnvironment, file);
    }
}
