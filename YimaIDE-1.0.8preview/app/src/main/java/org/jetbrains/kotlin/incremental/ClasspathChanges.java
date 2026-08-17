package org.jetbrains.kotlin.incremental;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0003\u0007\b\t¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/incremental/ClasspathChanges;", "Ljava/io/Serializable;", "<init>", "()V", "ClasspathSnapshotEnabled", "ClasspathSnapshotDisabled", "NotAvailableForJSCompiler", "Lorg/jetbrains/kotlin/incremental/ClasspathChanges$ClasspathSnapshotDisabled;", "Lorg/jetbrains/kotlin/incremental/ClasspathChanges$ClasspathSnapshotEnabled;", "Lorg/jetbrains/kotlin/incremental/ClasspathChanges$NotAvailableForJSCompiler;", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class ClasspathChanges implements Serializable {

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/incremental/ClasspathChanges$ClasspathSnapshotDisabled;", "Lorg/jetbrains/kotlin/incremental/ClasspathChanges;", "<init>", "()V", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class ClasspathSnapshotDisabled extends ClasspathChanges {
        public static final ClasspathSnapshotDisabled INSTANCE = new ClasspathSnapshotDisabled();

        private ClasspathSnapshotDisabled() {
            super(null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/incremental/ClasspathChanges$NotAvailableForJSCompiler;", "Lorg/jetbrains/kotlin/incremental/ClasspathChanges;", "<init>", "()V", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class NotAvailableForJSCompiler extends ClasspathChanges {
        public static final NotAvailableForJSCompiler INSTANCE = new NotAvailableForJSCompiler();

        private NotAvailableForJSCompiler() {
            super(null);
        }
    }

    public /* synthetic */ ClasspathChanges(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\b\t\nB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\u0082\u0001\u0003\u000b\f\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/incremental/ClasspathChanges$ClasspathSnapshotEnabled;", "Lorg/jetbrains/kotlin/incremental/ClasspathChanges;", "<init>", "()V", "classpathSnapshotFiles", "Lorg/jetbrains/kotlin/incremental/ClasspathSnapshotFiles;", "getClasspathSnapshotFiles", "()Lorg/jetbrains/kotlin/incremental/ClasspathSnapshotFiles;", "IncrementalRun", "NotAvailableDueToMissingClasspathSnapshot", "NotAvailableForNonIncrementalRun", "Lorg/jetbrains/kotlin/incremental/ClasspathChanges$ClasspathSnapshotEnabled$IncrementalRun;", "Lorg/jetbrains/kotlin/incremental/ClasspathChanges$ClasspathSnapshotEnabled$NotAvailableDueToMissingClasspathSnapshot;", "Lorg/jetbrains/kotlin/incremental/ClasspathChanges$ClasspathSnapshotEnabled$NotAvailableForNonIncrementalRun;", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class ClasspathSnapshotEnabled extends ClasspathChanges {

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/incremental/ClasspathChanges$ClasspathSnapshotEnabled$NotAvailableDueToMissingClasspathSnapshot;", "Lorg/jetbrains/kotlin/incremental/ClasspathChanges$ClasspathSnapshotEnabled;", "classpathSnapshotFiles", "Lorg/jetbrains/kotlin/incremental/ClasspathSnapshotFiles;", "<init>", "(Lorg/jetbrains/kotlin/incremental/ClasspathSnapshotFiles;)V", "getClasspathSnapshotFiles", "()Lorg/jetbrains/kotlin/incremental/ClasspathSnapshotFiles;", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class NotAvailableDueToMissingClasspathSnapshot extends ClasspathSnapshotEnabled {
            private final ClasspathSnapshotFiles classpathSnapshotFiles;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public NotAvailableDueToMissingClasspathSnapshot(ClasspathSnapshotFiles classpathSnapshotFiles) {
                super(null);
                classpathSnapshotFiles.getClass();
                this.classpathSnapshotFiles = classpathSnapshotFiles;
            }

            @Override // org.jetbrains.kotlin.incremental.ClasspathChanges.ClasspathSnapshotEnabled
            public ClasspathSnapshotFiles getClasspathSnapshotFiles() {
                return this.classpathSnapshotFiles;
            }
        }

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/incremental/ClasspathChanges$ClasspathSnapshotEnabled$NotAvailableForNonIncrementalRun;", "Lorg/jetbrains/kotlin/incremental/ClasspathChanges$ClasspathSnapshotEnabled;", "classpathSnapshotFiles", "Lorg/jetbrains/kotlin/incremental/ClasspathSnapshotFiles;", "<init>", "(Lorg/jetbrains/kotlin/incremental/ClasspathSnapshotFiles;)V", "getClasspathSnapshotFiles", "()Lorg/jetbrains/kotlin/incremental/ClasspathSnapshotFiles;", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class NotAvailableForNonIncrementalRun extends ClasspathSnapshotEnabled {
            private final ClasspathSnapshotFiles classpathSnapshotFiles;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public NotAvailableForNonIncrementalRun(ClasspathSnapshotFiles classpathSnapshotFiles) {
                super(null);
                classpathSnapshotFiles.getClass();
                this.classpathSnapshotFiles = classpathSnapshotFiles;
            }

            @Override // org.jetbrains.kotlin.incremental.ClasspathChanges.ClasspathSnapshotEnabled
            public ClasspathSnapshotFiles getClasspathSnapshotFiles() {
                return this.classpathSnapshotFiles;
            }
        }

        private ClasspathSnapshotEnabled() {
            super(null);
        }

        public abstract ClasspathSnapshotFiles getClasspathSnapshotFiles();

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/incremental/ClasspathChanges$ClasspathSnapshotEnabled$IncrementalRun;", "Lorg/jetbrains/kotlin/incremental/ClasspathChanges$ClasspathSnapshotEnabled;", "<init>", "()V", "NoChanges", "ToBeComputedByIncrementalCompiler", "Lorg/jetbrains/kotlin/incremental/ClasspathChanges$ClasspathSnapshotEnabled$IncrementalRun$NoChanges;", "Lorg/jetbrains/kotlin/incremental/ClasspathChanges$ClasspathSnapshotEnabled$IncrementalRun$ToBeComputedByIncrementalCompiler;", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static abstract class IncrementalRun extends ClasspathSnapshotEnabled {

            @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/incremental/ClasspathChanges$ClasspathSnapshotEnabled$IncrementalRun$NoChanges;", "Lorg/jetbrains/kotlin/incremental/ClasspathChanges$ClasspathSnapshotEnabled$IncrementalRun;", "classpathSnapshotFiles", "Lorg/jetbrains/kotlin/incremental/ClasspathSnapshotFiles;", "<init>", "(Lorg/jetbrains/kotlin/incremental/ClasspathSnapshotFiles;)V", "getClasspathSnapshotFiles", "()Lorg/jetbrains/kotlin/incremental/ClasspathSnapshotFiles;", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class NoChanges extends IncrementalRun {
                private final ClasspathSnapshotFiles classpathSnapshotFiles;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public NoChanges(ClasspathSnapshotFiles classpathSnapshotFiles) {
                    super(null);
                    classpathSnapshotFiles.getClass();
                    this.classpathSnapshotFiles = classpathSnapshotFiles;
                }

                @Override // org.jetbrains.kotlin.incremental.ClasspathChanges.ClasspathSnapshotEnabled
                public ClasspathSnapshotFiles getClasspathSnapshotFiles() {
                    return this.classpathSnapshotFiles;
                }
            }

            @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/incremental/ClasspathChanges$ClasspathSnapshotEnabled$IncrementalRun$ToBeComputedByIncrementalCompiler;", "Lorg/jetbrains/kotlin/incremental/ClasspathChanges$ClasspathSnapshotEnabled$IncrementalRun;", "classpathSnapshotFiles", "Lorg/jetbrains/kotlin/incremental/ClasspathSnapshotFiles;", "<init>", "(Lorg/jetbrains/kotlin/incremental/ClasspathSnapshotFiles;)V", "getClasspathSnapshotFiles", "()Lorg/jetbrains/kotlin/incremental/ClasspathSnapshotFiles;", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class ToBeComputedByIncrementalCompiler extends IncrementalRun {
                private final ClasspathSnapshotFiles classpathSnapshotFiles;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public ToBeComputedByIncrementalCompiler(ClasspathSnapshotFiles classpathSnapshotFiles) {
                    super(null);
                    classpathSnapshotFiles.getClass();
                    this.classpathSnapshotFiles = classpathSnapshotFiles;
                }

                @Override // org.jetbrains.kotlin.incremental.ClasspathChanges.ClasspathSnapshotEnabled
                public ClasspathSnapshotFiles getClasspathSnapshotFiles() {
                    return this.classpathSnapshotFiles;
                }
            }

            private IncrementalRun() {
                super(null);
            }

            public /* synthetic */ IncrementalRun(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }

        public /* synthetic */ ClasspathSnapshotEnabled(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private ClasspathChanges() {
    }
}
