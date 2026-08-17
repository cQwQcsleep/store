package org.jetbrains.kotlin.ir.backend.js;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.psi.KtFile;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/MainModule;", "", "<init>", "()V", "SourceFiles", "Klib", "Lorg/jetbrains/kotlin/ir/backend/js/MainModule$Klib;", "Lorg/jetbrains/kotlin/ir/backend/js/MainModule$SourceFiles;", "org.jetbrains.kotlin:ir.serialization.js"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class MainModule {

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/MainModule$Klib;", "Lorg/jetbrains/kotlin/ir/backend/js/MainModule;", "libPath", "", "<init>", "(Ljava/lang/String;)V", "getLibPath", "()Ljava/lang/String;", "org.jetbrains.kotlin:ir.serialization.js"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Klib extends MainModule {
        private final String libPath;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Klib(String str) {
            super(null);
            str.getClass();
            this.libPath = str;
        }

        public final String getLibPath() {
            return this.libPath;
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/MainModule$SourceFiles;", "Lorg/jetbrains/kotlin/ir/backend/js/MainModule;", "files", "", "Lorg/jetbrains/kotlin/psi/KtFile;", "<init>", "(Ljava/util/List;)V", "getFiles", "()Ljava/util/List;", "org.jetbrains.kotlin:ir.serialization.js"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class SourceFiles extends MainModule {
        private final List<KtFile> files;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public SourceFiles(List<? extends KtFile> list) {
            super(null);
            list.getClass();
            this.files = list;
        }

        public final List<KtFile> getFiles() {
            return this.files;
        }
    }

    public /* synthetic */ MainModule(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private MainModule() {
    }
}
