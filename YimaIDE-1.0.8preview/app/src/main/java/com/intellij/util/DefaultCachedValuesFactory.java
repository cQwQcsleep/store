package com.intellij.util;

import androidx.collection.ScatterMapKt;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.UserDataHolder;
import com.intellij.psi.util.CachedValue;
import com.intellij.psi.util.CachedValueProvider;
import com.intellij.psi.util.ParameterizedCachedValue;
import com.intellij.psi.util.ParameterizedCachedValueProvider;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
final class DefaultCachedValuesFactory implements CachedValuesFactory {
    private final Project myProject;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 4 || i == 8) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 4 || i == 8) ? 2 : 3];
        switch (i) {
            case 1:
            case 3:
            case 5:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[0] = "provider";
                break;
            case 2:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[0] = "userDataHolder";
                break;
            case 4:
            case 8:
                objArr[0] = "com/intellij/util/DefaultCachedValuesFactory";
                break;
            default:
                objArr[0] = "project";
                break;
        }
        if (i == 4) {
            objArr[1] = "createCachedValue";
        } else if (i != 8) {
            objArr[1] = "com/intellij/util/DefaultCachedValuesFactory";
        } else {
            objArr[1] = "createParameterizedCachedValue";
        }
        switch (i) {
            case 1:
            case 2:
            case 3:
                objArr[2] = "createCachedValue";
                break;
            case 4:
            case 8:
                break;
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[2] = "createParameterizedCachedValue";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 4 && i != 8) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public DefaultCachedValuesFactory(Project project) {
        if (project == null) {
            $$$reportNull$$$0(0);
        }
        this.myProject = project;
    }

    @Override // com.intellij.util.CachedValuesFactory
    public <T> CachedValue<T> createCachedValue(UserDataHolder userDataHolder, CachedValueProvider<T> cachedValueProvider, boolean z) {
        if (userDataHolder == null) {
            $$$reportNull$$$0(2);
        }
        if (cachedValueProvider == null) {
            $$$reportNull$$$0(3);
        }
        CachedValue<T> cachedValueCreateCachedValue = createCachedValue(cachedValueProvider, z);
        if (cachedValueCreateCachedValue == null) {
            $$$reportNull$$$0(4);
        }
        return cachedValueCreateCachedValue;
    }

    @Override // com.intellij.util.CachedValuesFactory
    public <T, P> ParameterizedCachedValue<T, P> createParameterizedCachedValue(UserDataHolder userDataHolder, ParameterizedCachedValueProvider<T, P> parameterizedCachedValueProvider, boolean z) {
        if (userDataHolder == null) {
            $$$reportNull$$$0(6);
        }
        if (parameterizedCachedValueProvider == null) {
            $$$reportNull$$$0(7);
        }
        ParameterizedCachedValue<T, P> parameterizedCachedValueCreateParameterizedCachedValue = createParameterizedCachedValue(parameterizedCachedValueProvider, z);
        if (parameterizedCachedValueCreateParameterizedCachedValue == null) {
            $$$reportNull$$$0(8);
        }
        return parameterizedCachedValueCreateParameterizedCachedValue;
    }

    @Override // com.intellij.util.CachedValuesFactory
    public <T> CachedValue<T> createCachedValue(CachedValueProvider<T> cachedValueProvider, boolean z) {
        if (cachedValueProvider == null) {
            $$$reportNull$$$0(1);
        }
        return new CachedValueImpl<T>(cachedValueProvider, z) { // from class: com.intellij.util.DefaultCachedValuesFactory.1
            private static /* synthetic */ void $$$reportNull$$$0(int i) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/intellij/util/DefaultCachedValuesFactory$1", "isFromMyProject"));
            }

            @Override // com.intellij.util.CachedValueImpl, com.intellij.util.CachedValueBase
            public boolean isFromMyProject(Project project) {
                if (project == null) {
                    $$$reportNull$$$0(0);
                }
                return DefaultCachedValuesFactory.this.myProject == project;
            }
        };
    }

    @Override // com.intellij.util.CachedValuesFactory
    public <T, P> ParameterizedCachedValue<T, P> createParameterizedCachedValue(ParameterizedCachedValueProvider<T, P> parameterizedCachedValueProvider, boolean z) {
        if (parameterizedCachedValueProvider == null) {
            $$$reportNull$$$0(5);
        }
        return new ParameterizedCachedValueImpl(this.myProject, parameterizedCachedValueProvider, z);
    }
}
