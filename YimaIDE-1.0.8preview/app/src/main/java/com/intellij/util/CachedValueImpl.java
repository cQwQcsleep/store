package com.intellij.util;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Getter;
import com.intellij.psi.util.CachedValue;
import com.intellij.psi.util.CachedValueProvider;
import java.lang.ref.SoftReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public class CachedValueImpl<T> extends CachedValueBase<T> implements CachedValue<T> {
    private volatile SoftReference<CachedValueBase.Data<T>> myData;
    private final CachedValueProvider<T> myProvider;
    private final boolean myTrackValue;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = i != 2 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i != 2 ? 3 : 2];
        if (i == 2) {
            objArr[0] = "com/intellij/util/CachedValueImpl";
        } else if (i != 3) {
            objArr[0] = "provider";
        } else {
            objArr[0] = "project";
        }
        if (i != 2) {
            objArr[1] = "com/intellij/util/CachedValueImpl";
        } else {
            objArr[1] = "getValueProvider";
        }
        if (i != 2) {
            if (i != 3) {
                objArr[2] = "<init>";
            } else {
                objArr[2] = "isFromMyProject";
            }
        }
        String str2 = String.format(str, objArr);
        if (i == 2) {
            throw new IllegalStateException(str2);
        }
    }

    public CachedValueImpl(CachedValueProvider<T> cachedValueProvider, boolean z) {
        if (cachedValueProvider == null) {
            $$$reportNull$$$0(1);
        }
        this.myProvider = cachedValueProvider;
        this.myTrackValue = z;
    }

    @Override // com.intellij.util.CachedValueBase
    public <P> CachedValueProvider.Result<T> doCompute(P p) {
        return this.myProvider.compute();
    }

    @Override // com.intellij.util.CachedValueBase
    public CachedValueBase.Data<T> getRawData() {
        return (CachedValueBase.Data) com.intellij.reference.SoftReference.dereference(this.myData);
    }

    @Override // com.intellij.psi.util.CachedValue
    public /* bridge */ /* synthetic */ Getter getUpToDateOrNull() {
        return super.getUpToDateOrNull();
    }

    @Override // com.intellij.psi.util.CachedValue
    public T getValue() {
        return getValueWithLock(null);
    }

    @Override // com.intellij.util.CachedValueBase, com.intellij.psi.util.CachedValue
    public CachedValueProvider<T> getValueProvider() {
        CachedValueProvider<T> cachedValueProvider = this.myProvider;
        if (cachedValueProvider == null) {
            $$$reportNull$$$0(2);
        }
        return cachedValueProvider;
    }

    @Override // com.intellij.util.CachedValueBase
    public boolean isFromMyProject(Project project) {
        if (project != null) {
            return true;
        }
        $$$reportNull$$$0(3);
        return true;
    }

    @Override // com.intellij.util.CachedValueBase
    public boolean isTrackValue() {
        return this.myTrackValue;
    }

    @Override // com.intellij.util.CachedValueBase
    public void setData(CachedValueBase.Data<T> data) {
        this.myData = data == null ? null : new SoftReference<>(data);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CachedValueImpl(CachedValueProvider<T> cachedValueProvider) {
        this(cachedValueProvider, false);
        if (cachedValueProvider == null) {
            $$$reportNull$$$0(0);
        }
    }
}
