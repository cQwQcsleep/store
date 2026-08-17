package org.jetbrains.kotlin.asJava.classes;

import com.intellij.openapi.util.ModificationTracker;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.impl.PsiClassImplUtil;
import com.intellij.psi.impl.source.PsiExtensibleClass;
import com.intellij.psi.util.CachedValueProvider;
import com.intellij.psi.util.CachedValuesManager;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ!\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u0012J\u0018\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ=\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u0002H\u00170\u0016\"\u0004\b\u0000\u0010\u00172 \b\u0004\u0010\u0018\u001a\u001a\u0012\u0004\u0012\u00020\u0003\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u0002H\u00170\u00160\u0019H\u0082\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/asJava/classes/ClassContentFinderCache;", "", "extensibleClass", "Lcom/intellij/psi/impl/source/PsiExtensibleClass;", "modificationTrackers", "", "Lcom/intellij/openapi/util/ModificationTracker;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lcom/intellij/psi/impl/source/PsiExtensibleClass;Ljava/util/List;)V", "findFieldByName", "Lcom/intellij/psi/PsiField;", "name", "", "checkBases", "", "findMethodsByName", "", "Lcom/intellij/psi/PsiMethod;", "(Ljava/lang/String;Z)[Lcom/intellij/psi/PsiMethod;", "findInnerClassByName", "Lcom/intellij/psi/PsiClass;", "cachedMap", "", "T", "provider", "Lkotlin/Function1;", "org.jetbrains.kotlin:light-classes-base"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ClassContentFinderCache {
    private final PsiExtensibleClass extensibleClass;
    private final List<ModificationTracker> modificationTrackers;

    public ClassContentFinderCache(PsiExtensibleClass psiExtensibleClass, List<? extends ModificationTracker> list) {
        psiExtensibleClass.getClass();
        list.getClass();
        this.extensibleClass = psiExtensibleClass;
        this.modificationTrackers = list;
    }

    public final PsiField findFieldByName(String name, boolean checkBases) {
        name.getClass();
        PsiExtensibleClass psiExtensibleClass = this.extensibleClass;
        if (checkBases) {
            return PsiClassImplUtil.findFieldByName(psiExtensibleClass, name, true);
        }
        Object cachedValue = CachedValuesManager.getCachedValue(psiExtensibleClass, new CachedValueProvider() { // from class: org.jetbrains.kotlin.asJava.classes.ClassContentFinderCache$findFieldByName$$inlined$cachedMap$1
            public final CachedValueProvider.Result<Map<String, T>> compute() {
                return CachedValueProvider.Result.create(ClassContentFinderCacheKt.getFieldsMap(this.this$0.extensibleClass), this.this$0.modificationTrackers);
            }
        });
        cachedValue.getClass();
        return (PsiField) ((Map) cachedValue).get(name);
    }

    public final PsiClass findInnerClassByName(String name, boolean checkBases) {
        name.getClass();
        PsiExtensibleClass psiExtensibleClass = this.extensibleClass;
        if (checkBases) {
            return PsiClassImplUtil.findInnerByName(psiExtensibleClass, name, true);
        }
        Object cachedValue = CachedValuesManager.getCachedValue(psiExtensibleClass, new CachedValueProvider() { // from class: org.jetbrains.kotlin.asJava.classes.ClassContentFinderCache$findInnerClassByName$$inlined$cachedMap$1
            public final CachedValueProvider.Result<Map<String, T>> compute() {
                return CachedValueProvider.Result.create(ClassContentFinderCacheKt.getInnerClassesMap(this.this$0.extensibleClass), this.this$0.modificationTrackers);
            }
        });
        cachedValue.getClass();
        return (PsiClass) ((Map) cachedValue).get(name);
    }

    public final PsiMethod[] findMethodsByName(String name, boolean checkBases) {
        PsiMethod[] psiMethodArr;
        name.getClass();
        PsiExtensibleClass psiExtensibleClass = this.extensibleClass;
        if (checkBases) {
            PsiMethod[] psiMethodArrFindMethodsByName = PsiClassImplUtil.findMethodsByName(psiExtensibleClass, name, true);
            psiMethodArrFindMethodsByName.getClass();
            return psiMethodArrFindMethodsByName;
        }
        Object cachedValue = CachedValuesManager.getCachedValue(psiExtensibleClass, new CachedValueProvider() { // from class: org.jetbrains.kotlin.asJava.classes.ClassContentFinderCache$findMethodsByName$$inlined$cachedMap$1
            public final CachedValueProvider.Result<Map<String, T>> compute() {
                return CachedValueProvider.Result.create(ClassContentFinderCacheKt.getMethodsMap(this.this$0.extensibleClass), this.this$0.modificationTrackers);
            }
        });
        cachedValue.getClass();
        List list = (List) ((Map) cachedValue).get(name);
        if (list != null) {
            if (list.isEmpty()) {
                list = null;
            }
            if (list != null && (psiMethodArr = (PsiMethod[]) list.toArray(new PsiMethod[0])) != null) {
                return psiMethodArr;
            }
        }
        PsiMethod[] psiMethodArr2 = PsiMethod.EMPTY_ARRAY;
        psiMethodArr2.getClass();
        return psiMethodArr2;
    }
}
