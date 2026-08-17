package org.jetbrains.kotlin.backend.common.diagnostics;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.konan.file.File;
import org.jetbrains.kotlin.library.KlibComponentLayout;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0006\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/diagnostics/JarManifestComponentLayout;", "Lorg/jetbrains/kotlin/library/KlibComponentLayout;", "root", "Lorg/jetbrains/kotlin/konan/file/File;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/konan/file/File;)V", "jarManifestFile", "getJarManifestFile", "()Lorg/jetbrains/kotlin/konan/file/File;", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
final class JarManifestComponentLayout extends KlibComponentLayout {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JarManifestComponentLayout(File file) {
        super(file);
        file.getClass();
    }

    public final File getJarManifestFile() {
        return getRoot().child("META-INF/MANIFEST.MF");
    }
}
