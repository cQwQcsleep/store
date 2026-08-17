package org.jetbrains.kotlin.backend.common.diagnostics;

import java.io.ByteArrayInputStream;
import java.util.jar.Manifest;
import kotlin.Metadata;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.konan.file.File;
import org.jetbrains.kotlin.library.KlibComponent;
import org.jetbrains.kotlin.library.KlibLayoutReader;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001:\u0001\u000bB\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b8F¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/diagnostics/JarManifestComponent;", "Lorg/jetbrains/kotlin/library/KlibComponent;", "layoutReader", "Lorg/jetbrains/kotlin/library/KlibLayoutReader;", "Lorg/jetbrains/kotlin/backend/common/diagnostics/JarManifestComponentLayout;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/library/KlibLayoutReader;)V", "jarManifest", "Ljava/util/jar/Manifest;", "getJarManifest", "()Ljava/util/jar/Manifest;", "Kind", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
final class JarManifestComponent implements KlibComponent {
    private final KlibLayoutReader<JarManifestComponentLayout> layoutReader;

    public JarManifestComponent(KlibLayoutReader<JarManifestComponentLayout> klibLayoutReader) {
        klibLayoutReader.getClass();
        this.layoutReader = klibLayoutReader;
    }

    public static Manifest a(JarManifestComponentLayout jarManifestComponentLayout) {
        jarManifestComponentLayout.getClass();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(jarManifestComponentLayout.getJarManifestFile().readBytes());
        try {
            Manifest manifest = new Manifest(byteArrayInputStream);
            CloseableKt.closeFinally(byteArrayInputStream, (Throwable) null);
            return manifest;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(byteArrayInputStream, th);
                throw th2;
            }
        }
    }

    public final Manifest getJarManifest() {
        return (Manifest) this.layoutReader.readInPlaceOrFallback((Object) null, new Function1() { // from class: org.jetbrains.kotlin.backend.common.diagnostics.a
            public final Object invoke(Object obj) {
                return JarManifestComponent.a((JarManifestComponentLayout) obj);
            }
        });
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0018\u0010\t\u001a\u0004\u0018\u00010\u00022\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000bH\u0016¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/diagnostics/JarManifestComponent$Kind;", "Lorg/jetbrains/kotlin/library/KlibComponent$Kind;", "Lorg/jetbrains/kotlin/backend/common/diagnostics/JarManifestComponent;", "Lorg/jetbrains/kotlin/backend/common/diagnostics/JarManifestComponentLayout;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "createLayout", "root", "Lorg/jetbrains/kotlin/konan/file/File;", "createComponentIfDataInKlibIsAvailable", "layoutReader", "Lorg/jetbrains/kotlin/library/KlibLayoutReader;", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Kind implements KlibComponent.Kind<JarManifestComponent, JarManifestComponentLayout> {
        public static final Kind INSTANCE = new Kind();

        private Kind() {
        }

        public static boolean a(JarManifestComponentLayout jarManifestComponentLayout) {
            jarManifestComponentLayout.getClass();
            return jarManifestComponentLayout.getJarManifestFile().isFile();
        }

        public JarManifestComponent createComponentIfDataInKlibIsAvailable(KlibLayoutReader<JarManifestComponentLayout> layoutReader) {
            layoutReader.getClass();
            if (((Boolean) layoutReader.readInPlaceOrFallback(Boolean.FALSE, new Function1() { // from class: org.jetbrains.kotlin.backend.common.diagnostics.b
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(JarManifestComponent.Kind.a((JarManifestComponentLayout) obj));
                }
            })).booleanValue()) {
                return new JarManifestComponent(layoutReader);
            }
            return null;
        }

        /* JADX INFO: renamed from: createLayout, reason: merged with bridge method [inline-methods] */
        public JarManifestComponentLayout m141createLayout(File root) {
            root.getClass();
            return new JarManifestComponentLayout(root);
        }

        /* JADX INFO: renamed from: createComponentIfDataInKlibIsAvailable, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ KlibComponent m140createComponentIfDataInKlibIsAvailable(KlibLayoutReader klibLayoutReader) {
            return createComponentIfDataInKlibIsAvailable((KlibLayoutReader<JarManifestComponentLayout>) klibLayoutReader);
        }
    }
}
