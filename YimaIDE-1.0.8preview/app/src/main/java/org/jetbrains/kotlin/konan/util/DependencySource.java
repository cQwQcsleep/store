package org.jetbrains.kotlin.konan.util;

import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/konan/util/DependencySource;", "", "()V", "Local", "Remote", "Lorg/jetbrains/kotlin/konan/util/DependencySource$Local;", "Lorg/jetbrains/kotlin/konan/util/DependencySource$Remote;", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class DependencySource {

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/konan/util/DependencySource$Local;", "Lorg/jetbrains/kotlin/konan/util/DependencySource;", "path", "Ljava/io/File;", "(Ljava/io/File;)V", "getPath", "()Ljava/io/File;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final /* data */ class Local extends DependencySource {
        private final File path;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Local(File file) {
            super(null);
            file.getClass();
            this.path = file;
        }

        public static /* synthetic */ Local copy$default(Local local, File file, int i, Object obj) {
            if ((i & 1) != 0) {
                file = local.path;
            }
            return local.copy(file);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final File getPath() {
            return this.path;
        }

        public final Local copy(File path) {
            path.getClass();
            return new Local(path);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Local) && Intrinsics.areEqual(this.path, ((Local) other).path);
        }

        public final File getPath() {
            return this.path;
        }

        public int hashCode() {
            return this.path.hashCode();
        }

        public String toString() {
            return "Local(path=" + this.path + ')';
        }
    }

    public /* synthetic */ DependencySource(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/konan/util/DependencySource$Remote;", "Lorg/jetbrains/kotlin/konan/util/DependencySource;", "()V", "Internal", "Public", "Lorg/jetbrains/kotlin/konan/util/DependencySource$Remote$Internal;", "Lorg/jetbrains/kotlin/konan/util/DependencySource$Remote$Public;", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static abstract class Remote extends DependencySource {

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lorg/jetbrains/kotlin/konan/util/DependencySource$Remote$Internal;", "Lorg/jetbrains/kotlin/konan/util/DependencySource$Remote;", "()V", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
        public static final class Internal extends Remote {
            public static final Internal INSTANCE = new Internal();

            private Internal() {
                super(null);
            }
        }

        private Remote() {
            super(null);
        }

        public /* synthetic */ Remote(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/konan/util/DependencySource$Remote$Public;", "Lorg/jetbrains/kotlin/konan/util/DependencySource$Remote;", "subDirectory", "", "(Ljava/lang/String;)V", "getSubDirectory", "()Ljava/lang/String;", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
        public static final class Public extends Remote {
            private final String subDirectory;

            public /* synthetic */ Public(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this((i & 1) != 0 ? null : str);
            }

            public final String getSubDirectory() {
                return this.subDirectory;
            }

            public Public(String str) {
                super(null);
                this.subDirectory = str;
            }

            /* JADX WARN: Illegal instructions before constructor call */
            public Public() {
                String str = null;
                this(str, 1, str);
            }
        }
    }

    private DependencySource() {
    }
}
