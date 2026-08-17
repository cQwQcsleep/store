package com.intellij.psi.stubs;

import com.intellij.openapi.extensions.PluginId;
import com.intellij.psi.PsiElement;
import com.intellij.util.indexing.ID;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class StubIndexKey<K, Psi extends PsiElement> extends ID<K, Psi> {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = i != 1 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i != 1 ? 3 : 2];
        if (i != 1) {
            objArr[0] = "name";
        } else {
            objArr[0] = "com/intellij/psi/stubs/StubIndexKey";
        }
        if (i != 1) {
            objArr[1] = "com/intellij/psi/stubs/StubIndexKey";
        } else {
            objArr[1] = "createIndexKey";
        }
        if (i != 1) {
            objArr[2] = "createIndexKey";
        }
        String str2 = String.format(str, objArr);
        if (i == 1) {
            throw new IllegalStateException(str2);
        }
    }

    private StubIndexKey(String str, PluginId pluginId) {
        super(str, pluginId);
    }

    /* JADX WARN: In static synchronized method top region not synchronized by class const: (wrap java.lang.Class:0x0002: CONST_CLASS  A[WRAPPED] com.intellij.psi.stubs.StubIndexKey.class) */
    public static synchronized <K, Psi extends PsiElement> StubIndexKey<K, Psi> createIndexKey(String str) {
        synchronized (StubIndexKey.class) {
            if (str == null) {
                try {
                    $$$reportNull$$$0(0);
                } catch (Throwable th) {
                    throw th;
                }
            }
            PluginId callerPluginId = ID.getCallerPluginId();
            ID idFindByName = ID.findByName(str, true, callerPluginId);
            if (idFindByName == null) {
                return new StubIndexKey<>(str, callerPluginId);
            }
            if (idFindByName instanceof StubIndexKey) {
                return (StubIndexKey) idFindByName;
            }
            throw new IllegalStateException("key with id " + str + " is already registered", idFindByName.getRegistrationTrace());
        }
    }
}
