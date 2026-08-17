package com.intellij.util;

import androidx.collection.ScatterMapKt;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Computable;
import com.intellij.openapi.util.Getter;
import com.intellij.openapi.util.ModificationTracker;
import com.intellij.openapi.util.RecursionGuard;
import com.intellij.openapi.util.RecursionManager;
import com.intellij.openapi.util.Ref;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.util.CachedValueProfiler;
import com.intellij.psi.util.CachedValueProvider;
import com.intellij.psi.util.PsiModificationTracker;
import com.intellij.util.CachedValueBase;
import com.intellij.util.containers.NotNullList;
import java.lang.ref.Reference;
import java.util.List;
import java.util.function.Supplier;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public abstract class CachedValueBase<T> {
    private static final Object[] PSI_MODIFICATION_DEPENDENCIES = {PsiModificationTracker.MODIFICATION_COUNT};

    public static abstract class Data<T> implements Getter<T> {
        private final T myValue;

        public Data(T t) {
            this.myValue = t;
        }

        public T get() {
            return getValue();
        }

        public abstract Object[] getDependencies();

        public abstract long[] getTimeStamps();

        public T getValue() {
            return this.myValue;
        }
    }

    public static class DefaultData<T> extends Data<T> {
        private final Object[] myDependencies;
        private final long[] myTimeStamps;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            String str = (i == 2 || i == 3) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
            Object[] objArr = new Object[(i == 2 || i == 3) ? 2 : 3];
            if (i == 1) {
                objArr[0] = "timeStamps";
            } else if (i == 2 || i == 3) {
                objArr[0] = "com/intellij/util/CachedValueBase$DefaultData";
            } else {
                objArr[0] = "dependencies";
            }
            if (i == 2) {
                objArr[1] = "getDependencies";
            } else if (i != 3) {
                objArr[1] = "com/intellij/util/CachedValueBase$DefaultData";
            } else {
                objArr[1] = "getTimeStamps";
            }
            if (i != 2 && i != 3) {
                objArr[2] = "<init>";
            }
            String str2 = String.format(str, objArr);
            if (i != 2 && i != 3) {
                throw new IllegalArgumentException(str2);
            }
            throw new IllegalStateException(str2);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DefaultData(T t, Object[] objArr, long[] jArr) {
            super(t);
            if (objArr == null) {
                $$$reportNull$$$0(0);
            }
            if (jArr == null) {
                $$$reportNull$$$0(1);
            }
            this.myDependencies = objArr;
            this.myTimeStamps = jArr;
        }

        @Override // com.intellij.util.CachedValueBase.Data
        public T get() {
            return getValue();
        }

        @Override // com.intellij.util.CachedValueBase.Data
        public Object[] getDependencies() {
            Object[] objArr = this.myDependencies;
            if (objArr == null) {
                $$$reportNull$$$0(2);
            }
            return objArr;
        }

        @Override // com.intellij.util.CachedValueBase.Data
        public long[] getTimeStamps() {
            long[] jArr = this.myTimeStamps;
            if (jArr == null) {
                $$$reportNull$$$0(3);
            }
            return jArr;
        }
    }

    public static final class PsiDependentData<T> extends Data<T> {
        private final long myPsiTimeStamp;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[2];
            objArr[0] = "com/intellij/util/CachedValueBase$PsiDependentData";
            if (i != 1) {
                objArr[1] = "getDependencies";
            } else {
                objArr[1] = "getTimeStamps";
            }
            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", objArr));
        }

        public PsiDependentData(T t, long j) {
            super(t);
            this.myPsiTimeStamp = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public long getTimeStamp() {
            return this.myPsiTimeStamp;
        }

        @Override // com.intellij.util.CachedValueBase.Data
        public Object[] getDependencies() {
            Object[] objArr = CachedValueBase.PSI_MODIFICATION_DEPENDENCIES;
            if (objArr == null) {
                $$$reportNull$$$0(0);
            }
            return objArr;
        }

        @Override // com.intellij.util.CachedValueBase.Data
        public long[] getTimeStamps() {
            return new long[]{this.myPsiTimeStamp};
        }
    }

    public static final class TrackedData<T> extends DefaultData<T> {
        final CachedValueProfiler.ValueTracker trackingInfo;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            if (i != 1) {
                objArr[0] = "dependencies";
            } else {
                objArr[0] = "timeStamps";
            }
            objArr[1] = "com/intellij/util/CachedValueBase$TrackedData";
            objArr[2] = "<init>";
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public TrackedData(T t, Object[] objArr, long[] jArr, CachedValueProfiler.ValueTracker valueTracker) {
            super(t, objArr, jArr);
            if (objArr == null) {
                $$$reportNull$$$0(0);
            }
            if (jArr == null) {
                $$$reportNull$$$0(1);
            }
            this.trackingInfo = valueTracker;
        }

        @Override // com.intellij.util.CachedValueBase.Data
        public T getValue() {
            CachedValueProfiler.ValueTracker valueTracker = this.trackingInfo;
            if (valueTracker != null) {
                valueTracker.onValueUsed();
            }
            return (T) super.getValue();
        }
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = i != 2 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i != 2 ? 3 : 2];
        switch (i) {
            case 1:
                objArr[0] = "dependencyItems";
                break;
            case 2:
                objArr[0] = "com/intellij/util/CachedValueBase";
                break;
            case 3:
            case 4:
                objArr[0] = "data";
                break;
            case 5:
            case 8:
                objArr[0] = "dependency";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[0] = "resultingDeps";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[0] = "dependencies";
                break;
            case 9:
                objArr[0] = "result";
                break;
            default:
                objArr[0] = "doCompute";
                break;
        }
        if (i != 2) {
            objArr[1] = "com/intellij/util/CachedValueBase";
        } else {
            objArr[1] = "normalizeDependencies";
        }
        switch (i) {
            case 1:
                objArr[2] = "normalizeDependencies";
                break;
            case 2:
                break;
            case 3:
                objArr[2] = "checkUpToDate";
                break;
            case 4:
                objArr[2] = "isUpToDate";
                break;
            case 5:
                objArr[2] = "isDependencyOutOfDate";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[2] = "collectDependencies";
                break;
            case 8:
                objArr[2] = "getTimeStamp";
                break;
            case 9:
                objArr[2] = "setValue";
                break;
            default:
                objArr[2] = "computeData";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i == 2) {
            throw new IllegalStateException(str2);
        }
    }

    public static /* synthetic */ Data b(final CachedValueBase cachedValueBase, final Object obj) {
        cachedValueBase.getClass();
        return cachedValueBase.computeData(new Computable() { // from class: o91
            public final Object compute() {
                return this.b.doCompute(obj);
            }
        });
    }

    public static /* synthetic */ Data c(final CachedValueBase cachedValueBase, final Object obj) {
        cachedValueBase.getClass();
        return cachedValueBase.computeData(new Computable() { // from class: p91
            public final Object compute() {
                return this.b.doCompute(obj);
            }
        });
    }

    private synchronized Data<T> cacheOrGetData(Data<T> data, Data<T> data2) {
        if (data != getRawData()) {
            return null;
        }
        if (data2 == null) {
            return data;
        }
        setRawData(data2);
        return data2;
    }

    private boolean checkUpToDate(Data<T> data) {
        CachedValueProfiler.ValueTracker valueTracker;
        if (data == null) {
            $$$reportNull$$$0(3);
        }
        if (isUpToDate(data)) {
            return true;
        }
        if (!(data instanceof TrackedData) || (valueTracker = ((TrackedData) data).trackingInfo) == null) {
            return false;
        }
        valueTracker.onValueInvalidated();
        return false;
    }

    private static void collectDependencies(Object[] objArr, List<? super Object> list) {
        if (list == null) {
            $$$reportNull$$$0(6);
        }
        if (objArr == null) {
            $$$reportNull$$$0(7);
        }
        for (Object obj : objArr) {
            if (obj != ObjectUtils.NULL) {
                if (obj instanceof Object[]) {
                    collectDependencies((Object[]) obj, list);
                } else {
                    list.add(obj);
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Data<T> computeData(Computable<CachedValueProvider.Result<T>> computable) {
        CachedValueProvider.Result result;
        CachedValueProfiler.ValueTracker valueTrackerNewValueTracker;
        if (computable == null) {
            $$$reportNull$$$0(0);
        }
        if (CachedValueProfiler.isProfiling()) {
            CachedValueProfiler.Frame frameNewFrame = CachedValueProfiler.newFrame();
            try {
                result = (CachedValueProvider.Result) computable.compute();
                valueTrackerNewValueTracker = result == null ? null : frameNewFrame.newValueTracker(result);
                if (frameNewFrame != null) {
                    frameNewFrame.close();
                }
            } catch (Throwable th) {
                if (frameNewFrame != null) {
                    try {
                        frameNewFrame.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } else {
            result = (CachedValueProvider.Result) computable.compute();
            valueTrackerNewValueTracker = null;
        }
        if (result == null) {
            return new DefaultData(null, ArrayUtilRt.EMPTY_OBJECT_ARRAY, ArrayUtil.EMPTY_LONG_ARRAY);
        }
        Object value = result.getValue();
        Object[] objArrNormalizeDependencies = normalizeDependencies(value, result.getDependencyItems());
        long[] jArr = new long[objArrNormalizeDependencies.length];
        for (int i = 0; i < objArrNormalizeDependencies.length; i++) {
            jArr[i] = getTimeStamp(objArrNormalizeDependencies[i]);
        }
        if (valueTrackerNewValueTracker != null) {
            return new TrackedData(value, objArrNormalizeDependencies, jArr, valueTrackerNewValueTracker);
        }
        return (objArrNormalizeDependencies.length == 1 && objArrNormalizeDependencies[0] == PsiModificationTracker.MODIFICATION_COUNT) ? new PsiDependentData(value, jArr[0]) : new DefaultData(value, objArrNormalizeDependencies, jArr);
    }

    public static /* synthetic */ CachedValueProvider.Result d(CachedValueProvider.Result result) {
        return result;
    }

    private synchronized void setRawData(Data<T> data) {
        setData(data);
    }

    public void clear() {
        setRawData(null);
    }

    public abstract <P> CachedValueProvider.Result<T> doCompute(P p);

    public String getIdempotenceFailureContext() {
        return "";
    }

    public abstract Data<T> getRawData();

    public long getTimeStamp(Object obj) {
        if (obj == null) {
            $$$reportNull$$$0(8);
        }
        if (obj instanceof VirtualFile) {
            return ((VirtualFile) obj).getModificationStamp();
        }
        if (obj instanceof ModificationTracker) {
            return ((ModificationTracker) obj).getModificationCount();
        }
        if (obj instanceof Reference) {
            Object obj2 = ((Reference) obj).get();
            if (obj2 == null) {
                return -1L;
            }
            return getTimeStamp(obj2);
        }
        if (obj instanceof Ref) {
            Object obj3 = ((Ref) obj).get();
            if (obj3 == null) {
                return -1L;
            }
            return getTimeStamp(obj3);
        }
        if (obj instanceof Document) {
            return ((Document) obj).getModificationStamp();
        }
        if (obj instanceof CachedValueBase) {
            return 0L;
        }
        Logger.getInstance(CachedValueBase.class).error("Wrong dependency type: " + obj.getClass());
        return -1L;
    }

    public final Data<T> getUpToDateOrNull() {
        Data<T> rawData = getRawData();
        if (rawData == null || !checkUpToDate(rawData)) {
            return null;
        }
        return rawData;
    }

    public abstract Object getValueProvider();

    public <P> T getValueWithLock(final P p) {
        Data<T> dataCacheOrGetData;
        CachedValueProfiler.ValueTracker valueTracker;
        Data<T> upToDateOrNull = getUpToDateOrNull();
        if (upToDateOrNull != null) {
            if (IdempotenceChecker.areRandomChecksEnabled()) {
                IdempotenceChecker.applyForRandomCheck(upToDateOrNull, getValueProvider(), new Computable() { // from class: l91
                    public final Object compute() {
                        return CachedValueBase.b(this.b, p);
                    }
                });
            }
            return upToDateOrNull.getValue();
        }
        RecursionGuard.StackStamp stackStampMarkStack = RecursionManager.markStack();
        Computable computable = new Computable() { // from class: m91
            public final Object compute() {
                return CachedValueBase.c(this.b, p);
            }
        };
        Data<T> data = (Data) RecursionManager.doPreventingRecursion(this, true, computable);
        if (data == null) {
            data = (Data) computable.compute();
        } else if (stackStampMarkStack.mayCacheNow()) {
            do {
                Data<T> rawData = getRawData();
                boolean z = rawData != null && checkUpToDate(rawData);
                if (z) {
                    IdempotenceChecker.checkEquivalence(rawData, data, getValueProvider().getClass(), computable, new Supplier() { // from class: n91
                        @Override // java.util.function.Supplier
                        public final Object get() {
                            return this.b.getIdempotenceFailureContext();
                        }
                    });
                }
                dataCacheOrGetData = cacheOrGetData(rawData, z ? null : data);
            } while (dataCacheOrGetData == null);
            if (data != dataCacheOrGetData && (data instanceof TrackedData) && (valueTracker = ((TrackedData) data).trackingInfo) != null) {
                valueTracker.onValueRejected();
            }
            return dataCacheOrGetData.getValue();
        }
        return data.getValue();
    }

    public boolean hasUpToDateValue() {
        return getUpToDateOrNull() != null;
    }

    public boolean isDependencyOutOfDate(Object obj, long j) {
        if (obj == null) {
            $$$reportNull$$$0(5);
        }
        if (obj instanceof CachedValueBase) {
            return !((CachedValueBase) obj).hasUpToDateValue();
        }
        long timeStamp = getTimeStamp(obj);
        return timeStamp < 0 || timeStamp != j;
    }

    public abstract boolean isFromMyProject(Project project);

    public abstract boolean isTrackValue();

    public boolean isUpToDate(Data<T> data) {
        if (data == null) {
            $$$reportNull$$$0(4);
        }
        if (data instanceof PsiDependentData) {
            return !isDependencyOutOfDate(PSI_MODIFICATION_DEPENDENCIES[0], ((PsiDependentData) data).getTimeStamp());
        }
        for (int i = 0; i < data.getDependencies().length; i++) {
            if (isDependencyOutOfDate(data.getDependencies()[i], data.getTimeStamps()[i])) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Object[] normalizeDependencies(T t, Object[] objArr) {
        if (objArr == null) {
            $$$reportNull$$$0(1);
        }
        NotNullList notNullList = new NotNullList(objArr.length + 1);
        collectDependencies(objArr, notNullList);
        if (isTrackValue() && t != 0) {
            if (t instanceof Object[]) {
                collectDependencies((Object[]) t, notNullList);
            } else {
                notNullList.add(t);
            }
        }
        Object[] objectArray = ArrayUtil.toObjectArray(notNullList);
        if (objectArray == null) {
            $$$reportNull$$$0(2);
        }
        return objectArray;
    }

    public abstract void setData(Data<T> data);

    public T setValue(final CachedValueProvider.Result<T> result) {
        if (result == null) {
            $$$reportNull$$$0(9);
        }
        Data<T> dataComputeData = computeData(new Computable() { // from class: q91
            public final Object compute() {
                return CachedValueBase.d(result);
            }
        });
        setRawData(dataComputeData);
        return dataComputeData.getValue();
    }

    public String toString() {
        return getClass().getSimpleName() + "{" + getValueProvider() + "}";
    }
}
