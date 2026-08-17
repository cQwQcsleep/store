package org.jetbrains.kotlin.asJava.classes;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.impl.source.PsiExtensibleClass;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachementBuilderUtilsKt;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\"$\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001*\u00020\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\"*\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0001*\u00020\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u0006\"$\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\f0\u0001*\u00020\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u0006¨\u0006\u000e"}, d2 = {"fieldsMap", "", "", "Lcom/intellij/psi/PsiField;", "Lcom/intellij/psi/impl/source/PsiExtensibleClass;", "getFieldsMap", "(Lcom/intellij/psi/impl/source/PsiExtensibleClass;)Ljava/util/Map;", "methodsMap", "", "Lcom/intellij/psi/PsiMethod;", "getMethodsMap", "innerClassesMap", "Lcom/intellij/psi/PsiClass;", "getInnerClassesMap", "org.jetbrains.kotlin:light-classes-base"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class ClassContentFinderCacheKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Map<String, PsiField> getFieldsMap(PsiExtensibleClass psiExtensibleClass) {
        Map mapCreateMapBuilder = MapsKt.createMapBuilder();
        for (PsiField psiField : psiExtensibleClass.getOwnFields()) {
            String name = psiField.getName();
            name.getClass();
            mapCreateMapBuilder.putIfAbsent(name, psiField);
        }
        return MapsKt.build(mapCreateMapBuilder);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Map<String, PsiClass> getInnerClassesMap(PsiExtensibleClass psiExtensibleClass) {
        Map mapCreateMapBuilder = MapsKt.createMapBuilder();
        for (PsiElement psiElement : psiExtensibleClass.getOwnInnerClasses()) {
            String name = psiElement.getName();
            if (name == null) {
                Logger logger = Logger.getInstance(Map.class);
                logger.getClass();
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Inner class doesn't have a name", (Throwable) null);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                ExceptionAttachementBuilderUtilsKt.withPsiEntry(exceptionAttachmentBuilder, "innerClass", psiElement);
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                logger.error(kotlinIllegalArgumentExceptionWithAttachments);
            } else {
                mapCreateMapBuilder.putIfAbsent(name, psiElement);
            }
        }
        return MapsKt.build(mapCreateMapBuilder);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Map<String, List<PsiMethod>> getMethodsMap(PsiExtensibleClass psiExtensibleClass) {
        List ownMethods = psiExtensibleClass.getOwnMethods();
        ownMethods.getClass();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : ownMethods) {
            String name = ((PsiMethod) obj).getName();
            Object arrayList = linkedHashMap.get(name);
            if (arrayList == null) {
                arrayList = new ArrayList();
                linkedHashMap.put(name, arrayList);
            }
            ((List) arrayList).add(obj);
        }
        return linkedHashMap.isEmpty() ? MapsKt.emptyMap() : linkedHashMap;
    }
}
