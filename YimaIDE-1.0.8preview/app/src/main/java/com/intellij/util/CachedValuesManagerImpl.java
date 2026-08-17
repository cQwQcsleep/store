package com.intellij.util;

import androidx.collection.ScatterMapKt;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Getter;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.UserDataHolder;
import com.intellij.openapi.util.UserDataHolderEx;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.CachedValue;
import com.intellij.psi.util.CachedValueProvider;
import com.intellij.psi.util.CachedValuesManager;
import com.intellij.psi.util.ParameterizedCachedValue;
import com.intellij.psi.util.ParameterizedCachedValueProvider;
import com.intellij.util.containers.CollectionFactory;
import com.intellij.util.containers.ContainerUtil;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class CachedValuesManagerImpl extends CachedValuesManager {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Object NULL = new Object();
    private final CachedValuesFactory myFactory;
    private final Project myProject;
    private ConcurrentMap<UserDataHolder, Object> myCacheHolders = CollectionFactory.createConcurrentWeakIdentityMap();
    private Set<Key<?>> myKeys = ContainerUtil.newConcurrentSet();

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 1 || i == 4 || i == 6 || i == 9) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 1 || i == 4 || i == 6 || i == 9) ? 2 : 3];
        switch (i) {
            case 1:
            case 4:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case 9:
                objArr[0] = "com/intellij/util/CachedValuesManagerImpl";
                break;
            case 2:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[0] = "userDataHolder";
                break;
            case 3:
            case 5:
            case 8:
            case 12:
            default:
                objArr[0] = "provider";
                break;
            case 10:
            case 13:
            case 15:
            case 17:
                objArr[0] = "dataHolder";
                break;
            case 11:
            case 14:
            case 16:
                objArr[0] = "key";
                break;
        }
        if (i == 1 || i == 4) {
            objArr[1] = "createCachedValue";
        } else if (i == 6 || i == 9) {
            objArr[1] = "createParameterizedCachedValue";
        } else {
            objArr[1] = "com/intellij/util/CachedValuesManagerImpl";
        }
        if (i != 1) {
            switch (i) {
                case 4:
                case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                case 9:
                    break;
                case 5:
                case ScatterMapKt.ClonedMetadataCount /* 7 */:
                case 8:
                    objArr[2] = "createParameterizedCachedValue";
                    break;
                case 10:
                case 11:
                case 12:
                    objArr[2] = "getCachedValue";
                    break;
                case 13:
                case 14:
                    objArr[2] = "saveInUserData";
                    break;
                case 15:
                case 16:
                    objArr[2] = "trackKeyHolder";
                    break;
                case 17:
                    objArr[2] = "isClearedOnPluginUnload";
                    break;
                default:
                    objArr[2] = "createCachedValue";
                    break;
            }
        }
        String str2 = String.format(str, objArr);
        if (i != 1 && i != 4 && i != 6 && i != 9) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public CachedValuesManagerImpl(Project project, CachedValuesFactory cachedValuesFactory) {
        this.myProject = project;
        this.myFactory = cachedValuesFactory == null ? new DefaultCachedValuesFactory(project) : cachedValuesFactory;
    }

    private <T> CachedValue<T> freshCachedValue(UserDataHolder userDataHolder, Key<CachedValue<T>> key, CachedValueProvider<T> cachedValueProvider, boolean z) {
        CachedValueLeakChecker.checkProviderDoesNotLeakPSI(cachedValueProvider, key, userDataHolder);
        return this.myFactory.createCachedValue(userDataHolder, cachedValueProvider, z);
    }

    private static boolean isClearedOnPluginUnload(UserDataHolder userDataHolder) {
        if (userDataHolder == null) {
            $$$reportNull$$$0(17);
        }
        return (userDataHolder instanceof PsiElement) || (userDataHolder instanceof ASTNode) || (userDataHolder instanceof FileViewProvider);
    }

    private <T> CachedValue<T> saveInUserData(UserDataHolder userDataHolder, Key<CachedValue<T>> key, CachedValue<T> cachedValue) {
        if (userDataHolder == null) {
            $$$reportNull$$$0(13);
        }
        if (key == null) {
            $$$reportNull$$$0(14);
        }
        trackKeyHolder(userDataHolder, key);
        if (userDataHolder instanceof UserDataHolderEx) {
            return (CachedValue) ((UserDataHolderEx) userDataHolder).putUserDataIfAbsent(key, cachedValue);
        }
        synchronized (userDataHolder) {
            try {
                CachedValue<T> cachedValue2 = (CachedValue) userDataHolder.getUserData(key);
                if (cachedValue2 != null) {
                    return cachedValue2;
                }
                userDataHolder.putUserData(key, cachedValue);
                return cachedValue;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public <T> CachedValue<T> createCachedValue(UserDataHolder userDataHolder, CachedValueProvider<T> cachedValueProvider, boolean z) {
        if (userDataHolder == null) {
            $$$reportNull$$$0(2);
        }
        if (cachedValueProvider == null) {
            $$$reportNull$$$0(3);
        }
        CachedValue<T> cachedValueCreateCachedValue = this.myFactory.createCachedValue(userDataHolder, cachedValueProvider, z);
        if (cachedValueCreateCachedValue == null) {
            $$$reportNull$$$0(4);
        }
        return cachedValueCreateCachedValue;
    }

    public <T, P> ParameterizedCachedValue<T, P> createParameterizedCachedValue(UserDataHolder userDataHolder, ParameterizedCachedValueProvider<T, P> parameterizedCachedValueProvider, boolean z) {
        if (userDataHolder == null) {
            $$$reportNull$$$0(7);
        }
        if (parameterizedCachedValueProvider == null) {
            $$$reportNull$$$0(8);
        }
        ParameterizedCachedValue<T, P> parameterizedCachedValueCreateParameterizedCachedValue = this.myFactory.createParameterizedCachedValue(userDataHolder, parameterizedCachedValueProvider, z);
        if (parameterizedCachedValueCreateParameterizedCachedValue == null) {
            $$$reportNull$$$0(9);
        }
        return parameterizedCachedValueCreateParameterizedCachedValue;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> T getCachedValue(UserDataHolder userDataHolder, Key<CachedValue<T>> key, CachedValueProvider<T> cachedValueProvider, boolean z) {
        if (userDataHolder == null) {
            $$$reportNull$$$0(10);
        }
        if (key == null) {
            $$$reportNull$$$0(11);
        }
        if (cachedValueProvider == null) {
            $$$reportNull$$$0(12);
        }
        CachedValue cachedValueSaveInUserData = (CachedValue) userDataHolder.getUserData(key);
        if ((cachedValueSaveInUserData instanceof CachedValueBase) && ((CachedValueBase) cachedValueSaveInUserData).isFromMyProject(this.myProject)) {
            Getter upToDateOrNull = cachedValueSaveInUserData.getUpToDateOrNull();
            if (upToDateOrNull != null) {
                return (T) upToDateOrNull.get();
            }
            CachedValueStabilityChecker.checkProvidersEquivalent(cachedValueProvider, cachedValueSaveInUserData.getValueProvider(), key);
        }
        if (cachedValueSaveInUserData == 0) {
            cachedValueSaveInUserData = saveInUserData(userDataHolder, key, freshCachedValue(userDataHolder, key, cachedValueProvider, z));
        }
        return (T) cachedValueSaveInUserData.getValue();
    }

    public void trackKeyHolder(UserDataHolder userDataHolder, Key<?> key) {
        if (userDataHolder == null) {
            $$$reportNull$$$0(15);
        }
        if (key == null) {
            $$$reportNull$$$0(16);
        }
        if (isClearedOnPluginUnload(userDataHolder)) {
            return;
        }
        this.myCacheHolders.put(userDataHolder, NULL);
        this.myKeys.add(key);
    }

    public <T> CachedValue<T> createCachedValue(CachedValueProvider<T> cachedValueProvider, boolean z) {
        if (cachedValueProvider == null) {
            $$$reportNull$$$0(0);
        }
        CachedValue<T> cachedValueCreateCachedValue = this.myFactory.createCachedValue(cachedValueProvider, z);
        if (cachedValueCreateCachedValue == null) {
            $$$reportNull$$$0(1);
        }
        return cachedValueCreateCachedValue;
    }

    public <T, P> ParameterizedCachedValue<T, P> createParameterizedCachedValue(ParameterizedCachedValueProvider<T, P> parameterizedCachedValueProvider, boolean z) {
        if (parameterizedCachedValueProvider == null) {
            $$$reportNull$$$0(5);
        }
        ParameterizedCachedValue<T, P> parameterizedCachedValueCreateParameterizedCachedValue = this.myFactory.createParameterizedCachedValue(parameterizedCachedValueProvider, z);
        if (parameterizedCachedValueCreateParameterizedCachedValue == null) {
            $$$reportNull$$$0(6);
        }
        return parameterizedCachedValueCreateParameterizedCachedValue;
    }
}
