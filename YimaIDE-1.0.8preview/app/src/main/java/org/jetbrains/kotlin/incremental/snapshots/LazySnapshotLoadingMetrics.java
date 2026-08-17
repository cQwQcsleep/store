package org.jetbrains.kotlin.incremental.snapshots;

import kotlin.Metadata;
import org.jetbrains.kotlin.build.report.metrics.GradleBuildTimeMetric;
import org.jetbrains.kotlin.build.report.metrics.INCREMENTAL_LOAD_CURRENT_CLASSPATH_SNAPSHOT;
import org.jetbrains.kotlin.build.report.metrics.INCREMENTAL_LOAD_SHRUNK_CURRENT_CLASSPATH_SNAPSHOT_AGAINST_PREVIOUS_LOOKUPS;
import org.jetbrains.kotlin.build.report.metrics.INCREMENTAL_REMOVE_DUPLICATE_CLASSES;
import org.jetbrains.kotlin.build.report.metrics.INCREMENTAL_SHRINK_CURRENT_CLASSPATH_SNAPSHOT;
import org.jetbrains.kotlin.build.report.metrics.LOAD_CURRENT_CLASSPATH_SNAPSHOT;
import org.jetbrains.kotlin.build.report.metrics.LOAD_SHRUNK_PREVIOUS_CLASSPATH_SNAPSHOT;
import org.jetbrains.kotlin.build.report.metrics.NON_INCREMENTAL_LOAD_CURRENT_CLASSPATH_SNAPSHOT;
import org.jetbrains.kotlin.build.report.metrics.NON_INCREMENTAL_REMOVE_DUPLICATE_CLASSES;
import org.jetbrains.kotlin.build.report.metrics.NON_INCREMENTAL_SHRINK_CURRENT_CLASSPATH_SNAPSHOT;
import org.jetbrains.kotlin.build.report.metrics.REMOVE_DUPLICATE_CLASSES;
import org.jetbrains.kotlin.build.report.metrics.SHRINK_CURRENT_CLASSPATH_SNAPSHOT;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bp\u0018\u00002\u00020\u0001:\u0004\f\r\u000e\u000fR\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u0012\u0010\b\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0005R\u0012\u0010\n\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0005\u0082\u0001\u0004\u0010\u0011\u0012\u0013ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0014À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/incremental/snapshots/LazySnapshotLoadingMetrics;", "", "loadClasspathSnapshotTag", "Lorg/jetbrains/kotlin/build/report/metrics/GradleBuildTimeMetric;", "getLoadClasspathSnapshotTag", "()Lorg/jetbrains/kotlin/build/report/metrics/GradleBuildTimeMetric;", "removeDuplicateClassesTag", "getRemoveDuplicateClassesTag", "calculateShrunkClasspathTag", "getCalculateShrunkClasspathTag", "loadShrunkClasspathTag", "getLoadShrunkClasspathTag", "OnClasspathDiffComputation", "OnIncrementalShrunkClasspathUpdate", "OnNonIncrementalShrunkClasspathUpdate", "AssertThatDataIsAlreadyComputed", "Lorg/jetbrains/kotlin/incremental/snapshots/LazySnapshotLoadingMetrics$AssertThatDataIsAlreadyComputed;", "Lorg/jetbrains/kotlin/incremental/snapshots/LazySnapshotLoadingMetrics$OnClasspathDiffComputation;", "Lorg/jetbrains/kotlin/incremental/snapshots/LazySnapshotLoadingMetrics$OnIncrementalShrunkClasspathUpdate;", "Lorg/jetbrains/kotlin/incremental/snapshots/LazySnapshotLoadingMetrics$OnNonIncrementalShrunkClasspathUpdate;", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface LazySnapshotLoadingMetrics {

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0007R\u0014\u0010\f\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u0007¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/incremental/snapshots/LazySnapshotLoadingMetrics$AssertThatDataIsAlreadyComputed;", "Lorg/jetbrains/kotlin/incremental/snapshots/LazySnapshotLoadingMetrics;", "<init>", "()V", "loadClasspathSnapshotTag", "", "getLoadClasspathSnapshotTag", "()Ljava/lang/Void;", "removeDuplicateClassesTag", "getRemoveDuplicateClassesTag", "calculateShrunkClasspathTag", "getCalculateShrunkClasspathTag", "loadShrunkClasspathTag", "getLoadShrunkClasspathTag", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class AssertThatDataIsAlreadyComputed implements LazySnapshotLoadingMetrics {
        public static final AssertThatDataIsAlreadyComputed INSTANCE = new AssertThatDataIsAlreadyComputed();

        private AssertThatDataIsAlreadyComputed() {
        }

        @Override // org.jetbrains.kotlin.incremental.snapshots.LazySnapshotLoadingMetrics
        /* JADX INFO: renamed from: getCalculateShrunkClasspathTag, reason: merged with bridge method [inline-methods] */
        public Void mo105getCalculateShrunkClasspathTag() {
            throw new IllegalStateException("Expected that shrunk classpath is computed, but it was null.");
        }

        @Override // org.jetbrains.kotlin.incremental.snapshots.LazySnapshotLoadingMetrics
        /* JADX INFO: renamed from: getLoadClasspathSnapshotTag, reason: merged with bridge method [inline-methods] */
        public Void mo106getLoadClasspathSnapshotTag() {
            throw new IllegalStateException("Expected that snapshot is loaded, but it was null.");
        }

        @Override // org.jetbrains.kotlin.incremental.snapshots.LazySnapshotLoadingMetrics
        /* JADX INFO: renamed from: getLoadShrunkClasspathTag, reason: merged with bridge method [inline-methods] */
        public Void mo107getLoadShrunkClasspathTag() {
            throw new IllegalStateException("Expected that previous shrunk classpath was read, but it was null.");
        }

        @Override // org.jetbrains.kotlin.incremental.snapshots.LazySnapshotLoadingMetrics
        /* JADX INFO: renamed from: getRemoveDuplicateClassesTag, reason: merged with bridge method [inline-methods] */
        public Void mo108getRemoveDuplicateClassesTag() {
            throw new IllegalStateException("Expected that snapshot is loaded, but it was null.");
        }
    }

    /* JADX INFO: renamed from: getCalculateShrunkClasspathTag */
    GradleBuildTimeMetric mo105getCalculateShrunkClasspathTag();

    /* JADX INFO: renamed from: getLoadClasspathSnapshotTag */
    GradleBuildTimeMetric mo106getLoadClasspathSnapshotTag();

    /* JADX INFO: renamed from: getLoadShrunkClasspathTag */
    GradleBuildTimeMetric mo107getLoadShrunkClasspathTag();

    /* JADX INFO: renamed from: getRemoveDuplicateClassesTag */
    GradleBuildTimeMetric mo108getRemoveDuplicateClassesTag();

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\rX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u0011X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/incremental/snapshots/LazySnapshotLoadingMetrics$OnClasspathDiffComputation;", "Lorg/jetbrains/kotlin/incremental/snapshots/LazySnapshotLoadingMetrics;", "<init>", "()V", "loadClasspathSnapshotTag", "Lorg/jetbrains/kotlin/build/report/metrics/LOAD_CURRENT_CLASSPATH_SNAPSHOT;", "getLoadClasspathSnapshotTag", "()Lorg/jetbrains/kotlin/build/report/metrics/LOAD_CURRENT_CLASSPATH_SNAPSHOT;", "removeDuplicateClassesTag", "Lorg/jetbrains/kotlin/build/report/metrics/REMOVE_DUPLICATE_CLASSES;", "getRemoveDuplicateClassesTag", "()Lorg/jetbrains/kotlin/build/report/metrics/REMOVE_DUPLICATE_CLASSES;", "calculateShrunkClasspathTag", "Lorg/jetbrains/kotlin/build/report/metrics/SHRINK_CURRENT_CLASSPATH_SNAPSHOT;", "getCalculateShrunkClasspathTag", "()Lorg/jetbrains/kotlin/build/report/metrics/SHRINK_CURRENT_CLASSPATH_SNAPSHOT;", "loadShrunkClasspathTag", "Lorg/jetbrains/kotlin/build/report/metrics/LOAD_SHRUNK_PREVIOUS_CLASSPATH_SNAPSHOT;", "getLoadShrunkClasspathTag", "()Lorg/jetbrains/kotlin/build/report/metrics/LOAD_SHRUNK_PREVIOUS_CLASSPATH_SNAPSHOT;", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class OnClasspathDiffComputation implements LazySnapshotLoadingMetrics {
        public static final OnClasspathDiffComputation INSTANCE = new OnClasspathDiffComputation();
        private static final LOAD_CURRENT_CLASSPATH_SNAPSHOT loadClasspathSnapshotTag = LOAD_CURRENT_CLASSPATH_SNAPSHOT.INSTANCE;
        private static final REMOVE_DUPLICATE_CLASSES removeDuplicateClassesTag = REMOVE_DUPLICATE_CLASSES.INSTANCE;
        private static final SHRINK_CURRENT_CLASSPATH_SNAPSHOT calculateShrunkClasspathTag = SHRINK_CURRENT_CLASSPATH_SNAPSHOT.INSTANCE;
        private static final LOAD_SHRUNK_PREVIOUS_CLASSPATH_SNAPSHOT loadShrunkClasspathTag = LOAD_SHRUNK_PREVIOUS_CLASSPATH_SNAPSHOT.INSTANCE;

        private OnClasspathDiffComputation() {
        }

        @Override // org.jetbrains.kotlin.incremental.snapshots.LazySnapshotLoadingMetrics
        /* JADX INFO: renamed from: getCalculateShrunkClasspathTag, reason: merged with bridge method [inline-methods] */
        public SHRINK_CURRENT_CLASSPATH_SNAPSHOT mo105getCalculateShrunkClasspathTag() {
            return calculateShrunkClasspathTag;
        }

        @Override // org.jetbrains.kotlin.incremental.snapshots.LazySnapshotLoadingMetrics
        /* JADX INFO: renamed from: getLoadClasspathSnapshotTag, reason: merged with bridge method [inline-methods] */
        public LOAD_CURRENT_CLASSPATH_SNAPSHOT mo106getLoadClasspathSnapshotTag() {
            return loadClasspathSnapshotTag;
        }

        @Override // org.jetbrains.kotlin.incremental.snapshots.LazySnapshotLoadingMetrics
        /* JADX INFO: renamed from: getLoadShrunkClasspathTag, reason: merged with bridge method [inline-methods] */
        public LOAD_SHRUNK_PREVIOUS_CLASSPATH_SNAPSHOT mo107getLoadShrunkClasspathTag() {
            return loadShrunkClasspathTag;
        }

        @Override // org.jetbrains.kotlin.incremental.snapshots.LazySnapshotLoadingMetrics
        /* JADX INFO: renamed from: getRemoveDuplicateClassesTag, reason: merged with bridge method [inline-methods] */
        public REMOVE_DUPLICATE_CLASSES mo108getRemoveDuplicateClassesTag() {
            return removeDuplicateClassesTag;
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\rX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u0011X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/incremental/snapshots/LazySnapshotLoadingMetrics$OnIncrementalShrunkClasspathUpdate;", "Lorg/jetbrains/kotlin/incremental/snapshots/LazySnapshotLoadingMetrics;", "<init>", "()V", "loadClasspathSnapshotTag", "Lorg/jetbrains/kotlin/build/report/metrics/INCREMENTAL_LOAD_CURRENT_CLASSPATH_SNAPSHOT;", "getLoadClasspathSnapshotTag", "()Lorg/jetbrains/kotlin/build/report/metrics/INCREMENTAL_LOAD_CURRENT_CLASSPATH_SNAPSHOT;", "removeDuplicateClassesTag", "Lorg/jetbrains/kotlin/build/report/metrics/INCREMENTAL_REMOVE_DUPLICATE_CLASSES;", "getRemoveDuplicateClassesTag", "()Lorg/jetbrains/kotlin/build/report/metrics/INCREMENTAL_REMOVE_DUPLICATE_CLASSES;", "calculateShrunkClasspathTag", "Lorg/jetbrains/kotlin/build/report/metrics/INCREMENTAL_SHRINK_CURRENT_CLASSPATH_SNAPSHOT;", "getCalculateShrunkClasspathTag", "()Lorg/jetbrains/kotlin/build/report/metrics/INCREMENTAL_SHRINK_CURRENT_CLASSPATH_SNAPSHOT;", "loadShrunkClasspathTag", "Lorg/jetbrains/kotlin/build/report/metrics/INCREMENTAL_LOAD_SHRUNK_CURRENT_CLASSPATH_SNAPSHOT_AGAINST_PREVIOUS_LOOKUPS;", "getLoadShrunkClasspathTag", "()Lorg/jetbrains/kotlin/build/report/metrics/INCREMENTAL_LOAD_SHRUNK_CURRENT_CLASSPATH_SNAPSHOT_AGAINST_PREVIOUS_LOOKUPS;", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class OnIncrementalShrunkClasspathUpdate implements LazySnapshotLoadingMetrics {
        public static final OnIncrementalShrunkClasspathUpdate INSTANCE = new OnIncrementalShrunkClasspathUpdate();
        private static final INCREMENTAL_LOAD_CURRENT_CLASSPATH_SNAPSHOT loadClasspathSnapshotTag = INCREMENTAL_LOAD_CURRENT_CLASSPATH_SNAPSHOT.INSTANCE;
        private static final INCREMENTAL_REMOVE_DUPLICATE_CLASSES removeDuplicateClassesTag = INCREMENTAL_REMOVE_DUPLICATE_CLASSES.INSTANCE;
        private static final INCREMENTAL_SHRINK_CURRENT_CLASSPATH_SNAPSHOT calculateShrunkClasspathTag = INCREMENTAL_SHRINK_CURRENT_CLASSPATH_SNAPSHOT.INSTANCE;
        private static final INCREMENTAL_LOAD_SHRUNK_CURRENT_CLASSPATH_SNAPSHOT_AGAINST_PREVIOUS_LOOKUPS loadShrunkClasspathTag = INCREMENTAL_LOAD_SHRUNK_CURRENT_CLASSPATH_SNAPSHOT_AGAINST_PREVIOUS_LOOKUPS.INSTANCE;

        private OnIncrementalShrunkClasspathUpdate() {
        }

        @Override // org.jetbrains.kotlin.incremental.snapshots.LazySnapshotLoadingMetrics
        /* JADX INFO: renamed from: getCalculateShrunkClasspathTag, reason: merged with bridge method [inline-methods] */
        public INCREMENTAL_SHRINK_CURRENT_CLASSPATH_SNAPSHOT mo105getCalculateShrunkClasspathTag() {
            return calculateShrunkClasspathTag;
        }

        @Override // org.jetbrains.kotlin.incremental.snapshots.LazySnapshotLoadingMetrics
        /* JADX INFO: renamed from: getLoadClasspathSnapshotTag, reason: merged with bridge method [inline-methods] */
        public INCREMENTAL_LOAD_CURRENT_CLASSPATH_SNAPSHOT mo106getLoadClasspathSnapshotTag() {
            return loadClasspathSnapshotTag;
        }

        @Override // org.jetbrains.kotlin.incremental.snapshots.LazySnapshotLoadingMetrics
        /* JADX INFO: renamed from: getLoadShrunkClasspathTag, reason: merged with bridge method [inline-methods] */
        public INCREMENTAL_LOAD_SHRUNK_CURRENT_CLASSPATH_SNAPSHOT_AGAINST_PREVIOUS_LOOKUPS mo107getLoadShrunkClasspathTag() {
            return loadShrunkClasspathTag;
        }

        @Override // org.jetbrains.kotlin.incremental.snapshots.LazySnapshotLoadingMetrics
        /* JADX INFO: renamed from: getRemoveDuplicateClassesTag, reason: merged with bridge method [inline-methods] */
        public INCREMENTAL_REMOVE_DUPLICATE_CLASSES mo108getRemoveDuplicateClassesTag() {
            return removeDuplicateClassesTag;
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\rX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/incremental/snapshots/LazySnapshotLoadingMetrics$OnNonIncrementalShrunkClasspathUpdate;", "Lorg/jetbrains/kotlin/incremental/snapshots/LazySnapshotLoadingMetrics;", "<init>", "()V", "loadClasspathSnapshotTag", "Lorg/jetbrains/kotlin/build/report/metrics/NON_INCREMENTAL_LOAD_CURRENT_CLASSPATH_SNAPSHOT;", "getLoadClasspathSnapshotTag", "()Lorg/jetbrains/kotlin/build/report/metrics/NON_INCREMENTAL_LOAD_CURRENT_CLASSPATH_SNAPSHOT;", "removeDuplicateClassesTag", "Lorg/jetbrains/kotlin/build/report/metrics/NON_INCREMENTAL_REMOVE_DUPLICATE_CLASSES;", "getRemoveDuplicateClassesTag", "()Lorg/jetbrains/kotlin/build/report/metrics/NON_INCREMENTAL_REMOVE_DUPLICATE_CLASSES;", "calculateShrunkClasspathTag", "Lorg/jetbrains/kotlin/build/report/metrics/NON_INCREMENTAL_SHRINK_CURRENT_CLASSPATH_SNAPSHOT;", "getCalculateShrunkClasspathTag", "()Lorg/jetbrains/kotlin/build/report/metrics/NON_INCREMENTAL_SHRINK_CURRENT_CLASSPATH_SNAPSHOT;", "loadShrunkClasspathTag", "", "getLoadShrunkClasspathTag", "()Ljava/lang/Void;", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class OnNonIncrementalShrunkClasspathUpdate implements LazySnapshotLoadingMetrics {
        public static final OnNonIncrementalShrunkClasspathUpdate INSTANCE = new OnNonIncrementalShrunkClasspathUpdate();
        private static final NON_INCREMENTAL_LOAD_CURRENT_CLASSPATH_SNAPSHOT loadClasspathSnapshotTag = NON_INCREMENTAL_LOAD_CURRENT_CLASSPATH_SNAPSHOT.INSTANCE;
        private static final NON_INCREMENTAL_REMOVE_DUPLICATE_CLASSES removeDuplicateClassesTag = NON_INCREMENTAL_REMOVE_DUPLICATE_CLASSES.INSTANCE;
        private static final NON_INCREMENTAL_SHRINK_CURRENT_CLASSPATH_SNAPSHOT calculateShrunkClasspathTag = NON_INCREMENTAL_SHRINK_CURRENT_CLASSPATH_SNAPSHOT.INSTANCE;

        private OnNonIncrementalShrunkClasspathUpdate() {
        }

        @Override // org.jetbrains.kotlin.incremental.snapshots.LazySnapshotLoadingMetrics
        /* JADX INFO: renamed from: getLoadShrunkClasspathTag, reason: merged with bridge method [inline-methods] */
        public Void mo107getLoadShrunkClasspathTag() {
            throw new IllegalStateException("Non-incremental classpath shrinker should not need previous shrunk classpath.");
        }

        @Override // org.jetbrains.kotlin.incremental.snapshots.LazySnapshotLoadingMetrics
        /* JADX INFO: renamed from: getCalculateShrunkClasspathTag, reason: merged with bridge method [inline-methods] */
        public NON_INCREMENTAL_SHRINK_CURRENT_CLASSPATH_SNAPSHOT mo105getCalculateShrunkClasspathTag() {
            return calculateShrunkClasspathTag;
        }

        @Override // org.jetbrains.kotlin.incremental.snapshots.LazySnapshotLoadingMetrics
        /* JADX INFO: renamed from: getLoadClasspathSnapshotTag, reason: merged with bridge method [inline-methods] */
        public NON_INCREMENTAL_LOAD_CURRENT_CLASSPATH_SNAPSHOT mo106getLoadClasspathSnapshotTag() {
            return loadClasspathSnapshotTag;
        }

        @Override // org.jetbrains.kotlin.incremental.snapshots.LazySnapshotLoadingMetrics
        /* JADX INFO: renamed from: getRemoveDuplicateClassesTag, reason: merged with bridge method [inline-methods] */
        public NON_INCREMENTAL_REMOVE_DUPLICATE_CLASSES mo108getRemoveDuplicateClassesTag() {
            return removeDuplicateClassesTag;
        }
    }
}
