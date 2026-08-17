package org.jetbrains.kotlin.extensions;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.testFramework.LightVirtualFile;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.extensions.PreprocessedFileCreator;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0011\u001a\u00020\u0013R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R!\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\f¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/extensions/PreprocessedFileCreator;", Argument.Delimiters.none, "project", "Lcom/intellij/openapi/project/Project;", "<init>", "(Lcom/intellij/openapi/project/Project;)V", "getProject", "()Lcom/intellij/openapi/project/Project;", "validExts", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/extensions/PreprocessedVirtualFileFactoryExtension;", "getValidExts", "()[Lorg/jetbrains/kotlin/extensions/PreprocessedVirtualFileFactoryExtension;", "validExts$delegate", "Lkotlin/Lazy;", CoroutineCodegenUtilKt.SUSPEND_FUNCTION_CREATE_METHOD_NAME, "Lcom/intellij/openapi/vfs/VirtualFile;", "file", "createLight", "Lcom/intellij/testFramework/LightVirtualFile;", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PreprocessedFileCreator {
    private final Project project;

    /* JADX INFO: renamed from: validExts$delegate, reason: from kotlin metadata */
    private final Lazy validExts;

    public PreprocessedFileCreator(Project project) {
        project.getClass();
        this.project = project;
        this.validExts = LazyKt.lazy(new Function0() { // from class: i7b
            public final Object invoke() {
                return PreprocessedFileCreator.a(this.b);
            }
        });
    }

    public static PreprocessedVirtualFileFactoryExtension[] a(PreprocessedFileCreator preprocessedFileCreator) {
        List<PreprocessedVirtualFileFactoryExtension> instances = PreprocessedVirtualFileFactoryExtension.INSTANCE.getInstances(preprocessedFileCreator.project);
        ArrayList arrayList = new ArrayList();
        for (Object obj : instances) {
            if (!((PreprocessedVirtualFileFactoryExtension) obj).isPassThrough()) {
                arrayList.add(obj);
            }
        }
        return (PreprocessedVirtualFileFactoryExtension[]) arrayList.toArray(new PreprocessedVirtualFileFactoryExtension[0]);
    }

    private final PreprocessedVirtualFileFactoryExtension[] getValidExts() {
        return (PreprocessedVirtualFileFactoryExtension[]) this.validExts.getValue();
    }

    /* JADX WARN: Code duplicated, block: B:10:0x001a A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:9:0x0019 A[RETURN] */
    public final VirtualFile create(VirtualFile file) {
        VirtualFile virtualFileCreatePreprocessedFile;
        file.getClass();
        for (PreprocessedVirtualFileFactoryExtension preprocessedVirtualFileFactoryExtension : getValidExts()) {
            virtualFileCreatePreprocessedFile = preprocessedVirtualFileFactoryExtension.createPreprocessedFile(file);
            if (virtualFileCreatePreprocessedFile != null) {
                if (virtualFileCreatePreprocessedFile == null) {
                    return file;
                }
                return virtualFileCreatePreprocessedFile;
            }
        }
        virtualFileCreatePreprocessedFile = null;
        if (virtualFileCreatePreprocessedFile == null) {
            return file;
        }
        return virtualFileCreatePreprocessedFile;
    }

    /* JADX WARN: Code duplicated, block: B:10:0x001a A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:9:0x0019 A[RETURN] */
    public final LightVirtualFile createLight(LightVirtualFile file) {
        LightVirtualFile lightVirtualFileCreatePreprocessedLightFile;
        file.getClass();
        for (PreprocessedVirtualFileFactoryExtension preprocessedVirtualFileFactoryExtension : getValidExts()) {
            lightVirtualFileCreatePreprocessedLightFile = preprocessedVirtualFileFactoryExtension.createPreprocessedLightFile(file);
            if (lightVirtualFileCreatePreprocessedLightFile != null) {
                if (lightVirtualFileCreatePreprocessedLightFile == null) {
                    return file;
                }
                return lightVirtualFileCreatePreprocessedLightFile;
            }
        }
        lightVirtualFileCreatePreprocessedLightFile = null;
        if (lightVirtualFileCreatePreprocessedLightFile == null) {
            return file;
        }
        return lightVirtualFileCreatePreprocessedLightFile;
    }

    public final Project getProject() {
        return this.project;
    }
}
