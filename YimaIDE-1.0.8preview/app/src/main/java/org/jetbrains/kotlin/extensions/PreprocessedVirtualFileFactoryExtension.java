package org.jetbrains.kotlin.extensions;

import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.testFramework.LightVirtualFile;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Deprecated(level = DeprecationLevel.ERROR, message = "This extension point will be deleted in kotlin 2.4")
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bg\u0018\u0000 \t2\u00020\u0001:\u0001\tJ\b\u0010\u0002\u001a\u00020\u0003H&J\u0014\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H&J\u0014\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\u0006\u001a\u0004\u0018\u00010\bH&Ê\u0001\u0018\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\n\b\u000e\u0012\u0006\b\n0\u000f8\u0010ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/extensions/PreprocessedVirtualFileFactoryExtension;", Argument.Delimiters.none, "isPassThrough", Argument.Delimiters.none, "createPreprocessedFile", "Lcom/intellij/openapi/vfs/VirtualFile;", "file", "createPreprocessedLightFile", "Lcom/intellij/testFramework/LightVirtualFile;", "Companion", "org.jetbrains.kotlin:frontend", "Lkotlin/Deprecated;", "message", "This extension point will be deleted in kotlin 2.4", "level", "Lkotlin/DeprecationLevel;", "ERROR"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface PreprocessedVirtualFileFactoryExtension {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/extensions/PreprocessedVirtualFileFactoryExtension$Companion;", "Lorg/jetbrains/kotlin/extensions/ProjectExtensionDescriptor;", "Lorg/jetbrains/kotlin/extensions/PreprocessedVirtualFileFactoryExtension;", "<init>", "()V", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion extends ProjectExtensionDescriptor<PreprocessedVirtualFileFactoryExtension> {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
            super("org.jetbrains.kotlin.preprocessedVirtualFileFactoryExtension", PreprocessedVirtualFileFactoryExtension.class);
        }
    }

    VirtualFile createPreprocessedFile(VirtualFile file);

    LightVirtualFile createPreprocessedLightFile(LightVirtualFile file);

    boolean isPassThrough();
}
