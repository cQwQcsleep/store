package org.jetbrains.kotlin.incremental;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \u00062\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0002\u0007\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/incremental/ChangedFiles;", "Ljava/io/Serializable;", "<init>", "()V", "DeterminableFiles", "Unknown", "Companion", "Lorg/jetbrains/kotlin/incremental/ChangedFiles$DeterminableFiles;", "Lorg/jetbrains/kotlin/incremental/ChangedFiles$Unknown;", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class ChangedFiles implements Serializable {
    public static final long serialVersionUID = 1;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0002J\n\u0010\u0006\u001a\u00020\u0007H\u0096\u0080\u0004¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/incremental/ChangedFiles$Unknown;", "Lorg/jetbrains/kotlin/incremental/ChangedFiles;", "<init>", "()V", "readResolve", "", "toString", "", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Unknown extends ChangedFiles {
        public static final Unknown INSTANCE = new Unknown();

        private Unknown() {
            super(null);
        }

        private final Object readResolve() {
            return INSTANCE;
        }

        public String toString() {
            return "Unknown";
        }
    }

    public /* synthetic */ ChangedFiles(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/incremental/ChangedFiles$DeterminableFiles;", "Lorg/jetbrains/kotlin/incremental/ChangedFiles;", "<init>", "()V", "Known", "ToBeComputed", "Lorg/jetbrains/kotlin/incremental/ChangedFiles$DeterminableFiles$Known;", "Lorg/jetbrains/kotlin/incremental/ChangedFiles$DeterminableFiles$ToBeComputed;", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static abstract class DeterminableFiles extends ChangedFiles {

        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0002J\n\u0010\u0006\u001a\u00020\u0007H\u0096\u0080\u0004¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/incremental/ChangedFiles$DeterminableFiles$ToBeComputed;", "Lorg/jetbrains/kotlin/incremental/ChangedFiles$DeterminableFiles;", "<init>", "()V", "readResolve", "", "toString", "", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public static final class ToBeComputed extends DeterminableFiles {
            public static final ToBeComputed INSTANCE = new ToBeComputed();

            private ToBeComputed() {
                super(null);
            }

            private final Object readResolve() {
                return INSTANCE;
            }

            public String toString() {
                return "ToBeComputed";
            }
        }

        private DeterminableFiles() {
            super(null);
        }

        public /* synthetic */ DeterminableFiles(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B-\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\n\u0010\u000f\u001a\u00020\u0010H\u0096\u0080\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/incremental/ChangedFiles$DeterminableFiles$Known;", "Lorg/jetbrains/kotlin/incremental/ChangedFiles$DeterminableFiles;", "modified", "", "Ljava/io/File;", "removed", "forDependencies", "", "<init>", "(Ljava/util/List;Ljava/util/List;Z)V", "getModified", "()Ljava/util/List;", "getRemoved", "getForDependencies", "()Z", "toString", "", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public static final class Known extends DeterminableFiles {
            private final boolean forDependencies;
            private final List<File> modified;
            private final List<File> removed;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public Known(List<? extends File> list, List<? extends File> list2, boolean z) {
                super(null);
                list.getClass();
                list2.getClass();
                this.modified = list;
                this.removed = list2;
                this.forDependencies = z;
            }

            public final boolean getForDependencies() {
                return this.forDependencies;
            }

            public final List<File> getModified() {
                return this.modified;
            }

            public final List<File> getRemoved() {
                return this.removed;
            }

            public String toString() {
                return "Known(modified=" + this.modified + ", removed=" + this.removed + ", forDependencies=" + this.forDependencies + ')';
            }

            public /* synthetic */ Known(List list, List list2, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this(list, list2, (i & 4) != 0 ? false : z);
            }
        }
    }

    private ChangedFiles() {
    }
}
