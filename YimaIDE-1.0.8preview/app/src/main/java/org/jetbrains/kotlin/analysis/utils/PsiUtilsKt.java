package org.jetbrains.kotlin.analysis.utils;

import com.intellij.psi.PsiAnonymousClass;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiClassOwner;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.analysis.utils.PsiUtilsKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\u001a\n\u0010\u0005\u001a\u00020\u0006*\u00020\u0002\"\u0017\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0007"}, d2 = {"classId", "Lorg/jetbrains/kotlin/name/ClassId;", "Lcom/intellij/psi/PsiClass;", "getClassId", "(Lcom/intellij/psi/PsiClass;)Lorg/jetbrains/kotlin/name/ClassId;", "isLocalClass", "", "org.jetbrains.kotlin:analysis-internal-utils"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class PsiUtilsKt {
    public static PsiClass a(PsiClass psiClass) {
        psiClass.getClass();
        return psiClass.getContainingClass();
    }

    public static final ClassId getClassId(PsiClass psiClass) {
        String packageName;
        psiClass.getClass();
        PsiClassOwner containingFile = psiClass.getContainingFile();
        PsiClassOwner psiClassOwner = containingFile instanceof PsiClassOwner ? containingFile : null;
        if (psiClassOwner == null || (packageName = psiClassOwner.getPackageName()) == null || psiClass.getQualifiedName() == null) {
            return null;
        }
        Sequence sequenceGenerateSequence = SequencesKt.generateSequence(psiClass, new Function1() { // from class: eub
            public final Object invoke(Object obj) {
                return PsiUtilsKt.a((PsiClass) obj);
            }
        });
        Iterator it = sequenceGenerateSequence.iterator();
        while (it.hasNext()) {
            if (((PsiClass) it.next()) instanceof PsiAnonymousClass) {
                return null;
            }
        }
        ArrayList arrayList = new ArrayList();
        Iterator it2 = sequenceGenerateSequence.iterator();
        while (it2.hasNext()) {
            arrayList.add(((PsiClass) it2.next()).getName());
        }
        List listAsReversedMutable = CollectionsKt.asReversedMutable(arrayList);
        if (!(listAsReversedMutable instanceof Collection) || !listAsReversedMutable.isEmpty()) {
            Iterator it3 = listAsReversedMutable.iterator();
            while (it3.hasNext()) {
                if (((String) it3.next()) == null) {
                    return null;
                }
            }
        }
        return new ClassId(new FqName(packageName), new FqName(CollectionsKt.joinToString$default(listAsReversedMutable, ".", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null)), false);
    }

    public static final boolean isLocalClass(PsiClass psiClass) {
        ClassId classId;
        psiClass.getClass();
        String qualifiedName = psiClass.getQualifiedName();
        if (qualifiedName == null || (classId = getClassId(psiClass)) == null) {
            return true;
        }
        return !Intrinsics.areEqual(StringsKt.replace$default(classId.asFqNameString(), '$', '.', false, 4, (Object) null), StringsKt.replace$default(qualifiedName, '$', '.', false, 4, (Object) null));
    }
}
