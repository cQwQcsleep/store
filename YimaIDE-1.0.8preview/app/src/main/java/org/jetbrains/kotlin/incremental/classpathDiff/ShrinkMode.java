package org.jetbrains.kotlin.incremental.classpathDiff;

import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.incremental.storage.LookupSymbolKey;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b2\u0018\u00002\u00020\u0001:\u0006\u0004\u0005\u0006\u0007\b\tB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0004\n\u000b\f\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/incremental/classpathDiff/ShrinkMode;", "", "<init>", "()V", "UnchangedLookupsUnchangedClasspath", "UnchangedLookupsChangedClasspath", "ChangedLookups", "ChangedLookupsUnchangedClasspath", "ChangedLookupsChangedClasspath", "NonIncremental", "Lorg/jetbrains/kotlin/incremental/classpathDiff/ShrinkMode$ChangedLookups;", "Lorg/jetbrains/kotlin/incremental/classpathDiff/ShrinkMode$NonIncremental;", "Lorg/jetbrains/kotlin/incremental/classpathDiff/ShrinkMode$UnchangedLookupsChangedClasspath;", "Lorg/jetbrains/kotlin/incremental/classpathDiff/ShrinkMode$UnchangedLookupsUnchangedClasspath;", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
abstract class ShrinkMode {

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B1\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0004\b\t\u0010\nR\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000e¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/incremental/classpathDiff/ShrinkMode$ChangedLookupsChangedClasspath;", "Lorg/jetbrains/kotlin/incremental/classpathDiff/ShrinkMode$ChangedLookups;", "addedLookupSymbols", "", "Lorg/jetbrains/kotlin/incremental/storage/LookupSymbolKey;", "currentClasspathSnapshot", "", "Lorg/jetbrains/kotlin/incremental/classpathDiff/AccessibleClassSnapshot;", "shrunkCurrentClasspathAgainstPreviousLookups", "<init>", "(Ljava/util/Set;Ljava/util/List;Ljava/util/List;)V", "getAddedLookupSymbols", "()Ljava/util/Set;", "getCurrentClasspathSnapshot", "()Ljava/util/List;", "getShrunkCurrentClasspathAgainstPreviousLookups", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class ChangedLookupsChangedClasspath extends ChangedLookups {
        private final Set<LookupSymbolKey> addedLookupSymbols;
        private final List<AccessibleClassSnapshot> currentClasspathSnapshot;
        private final List<AccessibleClassSnapshot> shrunkCurrentClasspathAgainstPreviousLookups;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public ChangedLookupsChangedClasspath(Set<LookupSymbolKey> set, List<? extends AccessibleClassSnapshot> list, List<? extends AccessibleClassSnapshot> list2) {
            super(null);
            set.getClass();
            list.getClass();
            list2.getClass();
            this.addedLookupSymbols = set;
            this.currentClasspathSnapshot = list;
            this.shrunkCurrentClasspathAgainstPreviousLookups = list2;
        }

        @Override // org.jetbrains.kotlin.incremental.classpathDiff.ShrinkMode.ChangedLookups
        public Set<LookupSymbolKey> getAddedLookupSymbols() {
            return this.addedLookupSymbols;
        }

        public final List<AccessibleClassSnapshot> getCurrentClasspathSnapshot() {
            return this.currentClasspathSnapshot;
        }

        public final List<AccessibleClassSnapshot> getShrunkCurrentClasspathAgainstPreviousLookups() {
            return this.shrunkCurrentClasspathAgainstPreviousLookups;
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/incremental/classpathDiff/ShrinkMode$ChangedLookupsUnchangedClasspath;", "Lorg/jetbrains/kotlin/incremental/classpathDiff/ShrinkMode$ChangedLookups;", "addedLookupSymbols", "", "Lorg/jetbrains/kotlin/incremental/storage/LookupSymbolKey;", "<init>", "(Ljava/util/Set;)V", "getAddedLookupSymbols", "()Ljava/util/Set;", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class ChangedLookupsUnchangedClasspath extends ChangedLookups {
        private final Set<LookupSymbolKey> addedLookupSymbols;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ChangedLookupsUnchangedClasspath(Set<LookupSymbolKey> set) {
            super(null);
            set.getClass();
            this.addedLookupSymbols = set;
        }

        @Override // org.jetbrains.kotlin.incremental.classpathDiff.ShrinkMode.ChangedLookups
        public Set<LookupSymbolKey> getAddedLookupSymbols() {
            return this.addedLookupSymbols;
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/incremental/classpathDiff/ShrinkMode$NonIncremental;", "Lorg/jetbrains/kotlin/incremental/classpathDiff/ShrinkMode;", "<init>", "()V", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class NonIncremental extends ShrinkMode {
        public static final NonIncremental INSTANCE = new NonIncremental();

        private NonIncremental() {
            super(null);
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B#\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0006\u0010\u0007R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\t¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/incremental/classpathDiff/ShrinkMode$UnchangedLookupsChangedClasspath;", "Lorg/jetbrains/kotlin/incremental/classpathDiff/ShrinkMode;", "currentClasspathSnapshot", "", "Lorg/jetbrains/kotlin/incremental/classpathDiff/AccessibleClassSnapshot;", "shrunkCurrentClasspathAgainstPreviousLookups", "<init>", "(Ljava/util/List;Ljava/util/List;)V", "getCurrentClasspathSnapshot", "()Ljava/util/List;", "getShrunkCurrentClasspathAgainstPreviousLookups", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class UnchangedLookupsChangedClasspath extends ShrinkMode {
        private final List<AccessibleClassSnapshot> currentClasspathSnapshot;
        private final List<AccessibleClassSnapshot> shrunkCurrentClasspathAgainstPreviousLookups;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public UnchangedLookupsChangedClasspath(List<? extends AccessibleClassSnapshot> list, List<? extends AccessibleClassSnapshot> list2) {
            super(null);
            list.getClass();
            list2.getClass();
            this.currentClasspathSnapshot = list;
            this.shrunkCurrentClasspathAgainstPreviousLookups = list2;
        }

        public final List<AccessibleClassSnapshot> getCurrentClasspathSnapshot() {
            return this.currentClasspathSnapshot;
        }

        public final List<AccessibleClassSnapshot> getShrunkCurrentClasspathAgainstPreviousLookups() {
            return this.shrunkCurrentClasspathAgainstPreviousLookups;
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/incremental/classpathDiff/ShrinkMode$UnchangedLookupsUnchangedClasspath;", "Lorg/jetbrains/kotlin/incremental/classpathDiff/ShrinkMode;", "<init>", "()V", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class UnchangedLookupsUnchangedClasspath extends ShrinkMode {
        public static final UnchangedLookupsUnchangedClasspath INSTANCE = new UnchangedLookupsUnchangedClasspath();

        private UnchangedLookupsUnchangedClasspath() {
            super(null);
        }
    }

    public /* synthetic */ ShrinkMode(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003R\u0018\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b\u0082\u0001\u0002\t\n¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/incremental/classpathDiff/ShrinkMode$ChangedLookups;", "Lorg/jetbrains/kotlin/incremental/classpathDiff/ShrinkMode;", "<init>", "()V", "addedLookupSymbols", "", "Lorg/jetbrains/kotlin/incremental/storage/LookupSymbolKey;", "getAddedLookupSymbols", "()Ljava/util/Set;", "Lorg/jetbrains/kotlin/incremental/classpathDiff/ShrinkMode$ChangedLookupsChangedClasspath;", "Lorg/jetbrains/kotlin/incremental/classpathDiff/ShrinkMode$ChangedLookupsUnchangedClasspath;", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static abstract class ChangedLookups extends ShrinkMode {
        private ChangedLookups() {
            super(null);
        }

        public abstract Set<LookupSymbolKey> getAddedLookupSymbols();

        public /* synthetic */ ChangedLookups(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private ShrinkMode() {
    }
}
