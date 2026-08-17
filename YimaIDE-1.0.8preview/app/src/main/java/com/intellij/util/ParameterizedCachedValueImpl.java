package com.intellij.util;

import com.intellij.openapi.project.Project;
import com.intellij.psi.util.CachedValueProvider;
import com.intellij.psi.util.ParameterizedCachedValue;
import com.intellij.psi.util.ParameterizedCachedValueProvider;
import java.lang.ref.SoftReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class ParameterizedCachedValueImpl<T, P> extends CachedValueBase<T> implements ParameterizedCachedValue<T, P> {
    private volatile SoftReference<CachedValueBase.Data<T>> myData;
    private final Project myProject;
    private final ParameterizedCachedValueProvider<T, P> myProvider;
    private final boolean myTrackValue;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = i != 3 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i != 3 ? 3 : 2];
        if (i == 1) {
            objArr[0] = "provider";
        } else if (i != 3) {
            objArr[0] = "project";
        } else {
            objArr[0] = "com/intellij/util/ParameterizedCachedValueImpl";
        }
        if (i != 3) {
            objArr[1] = "com/intellij/util/ParameterizedCachedValueImpl";
        } else {
            objArr[1] = "getValueProvider";
        }
        if (i == 2) {
            objArr[2] = "isFromMyProject";
        } else if (i != 3) {
            objArr[2] = "<init>";
        }
        String str2 = String.format(str, objArr);
        if (i == 3) {
            throw new IllegalStateException(str2);
        }
    }

    public ParameterizedCachedValueImpl(Project project, ParameterizedCachedValueProvider<T, P> parameterizedCachedValueProvider, boolean z) {
        if (project == null) {
            $$$reportNull$$$0(0);
        }
        if (parameterizedCachedValueProvider == null) {
            $$$reportNull$$$0(1);
        }
        this.myTrackValue = z;
        this.myProject = project;
        this.myProvider = parameterizedCachedValueProvider;
    }

    @Override // com.intellij.util.CachedValueBase
    public <X> CachedValueProvider.Result<T> doCompute(X x) {
        return this.myProvider.compute(x);
    }

    @Override // com.intellij.util.CachedValueBase
    public CachedValueBase.Data<T> getRawData() {
        return (CachedValueBase.Data) com.intellij.reference.SoftReference.dereference(this.myData);
    }

    public T getValue(P p) {
        return getValueWithLock(p);
    }

    @Override // com.intellij.util.CachedValueBase, com.intellij.psi.util.CachedValue
    public ParameterizedCachedValueProvider<T, P> getValueProvider() {
        ParameterizedCachedValueProvider<T, P> parameterizedCachedValueProvider = this.myProvider;
        if (parameterizedCachedValueProvider == null) {
            $$$reportNull$$$0(3);
        }
        return parameterizedCachedValueProvider;
    }

    @Override // com.intellij.util.CachedValueBase
    public boolean isFromMyProject(Project project) {
        if (project == null) {
            $$$reportNull$$$0(2);
        }
        return this.myProject == project;
    }

    @Override // com.intellij.util.CachedValueBase
    public boolean isTrackValue() {
        return this.myTrackValue;
    }

    @Override // com.intellij.util.CachedValueBase
    public void setData(CachedValueBase.Data<T> data) {
        this.myData = data == null ? null : new SoftReference<>(data);
    }
}
