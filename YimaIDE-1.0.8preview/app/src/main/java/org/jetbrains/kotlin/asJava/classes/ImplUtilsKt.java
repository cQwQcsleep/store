package org.jetbrains.kotlin.asJava.classes;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiArrayType;
import com.intellij.psi.PsiClassType;
import com.intellij.psi.PsiJavaCodeReferenceElement;
import com.intellij.psi.PsiPrimitiveType;
import com.intellij.psi.PsiReferenceList;
import com.intellij.psi.PsiType;
import com.intellij.psi.PsiWildcardType;
import com.intellij.psi.TypeAnnotationProvider;
import com.intellij.psi.impl.light.LightElement;
import com.intellij.util.IncorrectOperationException;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import org.jetbrains.kotlin.asJava.classes.ImplUtilsKt;
import org.jetbrains.kotlin.psi.KtPsiFactory;
import org.jetbrains.kotlin.psi.KtSuperTypeList;
import org.jetbrains.kotlin.psi.KtSuperTypeListEntry;
import org.jetbrains.kotlin.psi.KtUserType;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a \u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u000b0\n\"\u0004\b\u0000\u0010\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\r\u001a\n\u0010\u000e\u001a\u00020\u000f*\u00020\u0010\u001a\"\u0010\u0011\u001a\u00020\u0012*\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u0017\u001a\u001e\u0010 \u001a\u00020\u001a*\u00020\u001a2\u0012\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020$0#0\"\"\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u0004*\u00020\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b\"-\u0010\u0018\u001a\u0014\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u00120\u00198BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b\u001c\u0010\u001d¨\u0006%"}, d2 = {"findEntry", "Lorg/jetbrains/kotlin/psi/KtSuperTypeListEntry;", "Lorg/jetbrains/kotlin/psi/KtSuperTypeList;", "fqNameToFind", "", "fqName", "Lorg/jetbrains/kotlin/psi/KtUserType;", "getFqName", "(Lorg/jetbrains/kotlin/psi/KtUserType;)Ljava/lang/String;", "lazyPub", "Lkotlin/Lazy;", "T", "initializer", "Lkotlin/Function0;", "cannotModify", "", "Lcom/intellij/psi/impl/light/LightElement;", "addSuperTypeEntry", "", "Lcom/intellij/psi/PsiReferenceList;", "superTypeList", "entry", "reference", "Lcom/intellij/psi/PsiJavaCodeReferenceElement;", "setPsiTypeAnnotationProvider", "Lkotlin/Function2;", "Lcom/intellij/psi/PsiType;", "Lcom/intellij/psi/TypeAnnotationProvider;", "getSetPsiTypeAnnotationProvider", "()Lkotlin/jvm/functions/Function2;", "setPsiTypeAnnotationProvider$delegate", "Lkotlin/Lazy;", "annotateByTypeAnnotationProvider", "annotations", "Lkotlin/sequences/Sequence;", "", "Lcom/intellij/psi/PsiAnnotation;", "org.jetbrains.kotlin:light-classes-base"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class ImplUtilsKt {
    private static final Lazy setPsiTypeAnnotationProvider$delegate = lazyPub(new Function0() { // from class: el6
        public final Object invoke() {
            return ImplUtilsKt.b();
        }
    });

    public static final void addSuperTypeEntry(PsiReferenceList psiReferenceList, KtSuperTypeList ktSuperTypeList, KtSuperTypeListEntry ktSuperTypeListEntry, PsiJavaCodeReferenceElement psiJavaCodeReferenceElement) {
        KtSuperTypeListEntry ktSuperTypeListEntryCreateSuperTypeCallEntry;
        psiReferenceList.getClass();
        ktSuperTypeList.getClass();
        ktSuperTypeListEntry.getClass();
        psiJavaCodeReferenceElement.getClass();
        PsiReferenceList parent = psiJavaCodeReferenceElement.getParent();
        PsiReferenceList psiReferenceList2 = parent instanceof PsiReferenceList ? parent : null;
        if ((psiReferenceList2 != null ? psiReferenceList2.getRole() : null) == PsiReferenceList.Role.IMPLEMENTS_LIST && psiReferenceList.getRole() == PsiReferenceList.Role.EXTENDS_LIST) {
            Project project = psiReferenceList.getProject();
            project.getClass();
            ktSuperTypeListEntryCreateSuperTypeCallEntry = new KtPsiFactory(project, false, 2, (DefaultConstructorMarker) null).createSuperTypeCallEntry(ktSuperTypeListEntry.getText() + "()");
        } else {
            ktSuperTypeListEntryCreateSuperTypeCallEntry = ktSuperTypeListEntry;
        }
        if (Intrinsics.areEqual(ktSuperTypeListEntry.getParent(), ktSuperTypeList)) {
            ktSuperTypeListEntry.replace(ktSuperTypeListEntryCreateSuperTypeCallEntry);
        } else {
            ktSuperTypeList.addEntry(ktSuperTypeListEntryCreateSuperTypeCallEntry);
        }
    }

    public static final PsiType annotateByTypeAnnotationProvider(PsiType psiType, Sequence<? extends List<? extends PsiAnnotation>> sequence) {
        psiType.getClass();
        sequence.getClass();
        Iterator it = sequence.iterator();
        if (!it.hasNext()) {
            return psiType;
        }
        if (!(psiType instanceof PsiPrimitiveType)) {
            annotateByTypeAnnotationProvider$recursiveAnnotator(it, psiType);
            return psiType;
        }
        List list = (List) it.next();
        if (list.isEmpty()) {
            return psiType;
        }
        TypeAnnotationProvider typeAnnotationProviderCreate = TypeAnnotationProvider.Static.create((PsiAnnotation[]) list.toArray(new PsiAnnotation[0]));
        typeAnnotationProviderCreate.getClass();
        PsiPrimitiveType psiPrimitiveTypeAnnotate = ((PsiPrimitiveType) psiType).annotate(typeAnnotationProviderCreate);
        psiPrimitiveTypeAnnotate.getClass();
        return psiPrimitiveTypeAnnotate;
    }

    private static final void annotateByTypeAnnotationProvider$recursiveAnnotator(Iterator<? extends List<? extends PsiAnnotation>> it, PsiType psiType) {
        if (it.hasNext()) {
            if (psiType instanceof PsiWildcardType) {
                PsiType bound = ((PsiWildcardType) psiType).getBound();
                if (bound != null) {
                    annotateByTypeAnnotationProvider$recursiveAnnotator(it, bound);
                    return;
                }
                return;
            }
            List<? extends PsiAnnotation> next = it.next();
            if (psiType instanceof PsiPrimitiveType) {
                return;
            }
            if (psiType instanceof PsiClassType) {
                PsiType[] parameters = ((PsiClassType) psiType).getParameters();
                parameters.getClass();
                for (PsiType psiType2 : parameters) {
                    psiType2.getClass();
                    annotateByTypeAnnotationProvider$recursiveAnnotator(it, psiType2);
                }
            } else if (psiType instanceof PsiArrayType) {
                PsiType componentType = ((PsiArrayType) psiType).getComponentType();
                componentType.getClass();
                annotateByTypeAnnotationProvider$recursiveAnnotator(it, componentType);
            }
            if (next.isEmpty()) {
                return;
            }
            TypeAnnotationProvider typeAnnotationProviderCreate = TypeAnnotationProvider.Static.create((PsiAnnotation[]) next.toArray(new PsiAnnotation[0]));
            typeAnnotationProviderCreate.getClass();
            getSetPsiTypeAnnotationProvider().invoke(psiType, typeAnnotationProviderCreate);
        }
    }

    public static Function2 b() throws NoSuchFieldException {
        final Field field = null;
        try {
            Field declaredField = PsiType.class.getDeclaredField("myAnnotationProvider");
            declaredField.setAccessible(true);
            field = declaredField;
        } catch (NoSuchFieldException e) {
            if (ApplicationManager.getApplication().isInternal()) {
                throw e;
            }
        } catch (SecurityException e2) {
            if (ApplicationManager.getApplication().isInternal()) {
                throw e2;
            }
        }
        return new Function2() { // from class: fl6
            public final Object invoke(Object obj, Object obj2) {
                return ImplUtilsKt.setPsiTypeAnnotationProvider_delegate$lambda$0$1(field, (PsiType) obj, (TypeAnnotationProvider) obj2);
            }
        };
    }

    public static final Void cannotModify(LightElement lightElement) {
        lightElement.getClass();
        throw new IncorrectOperationException("Modification not implemented.");
    }

    public static final KtSuperTypeListEntry findEntry(KtSuperTypeList ktSuperTypeList, String str) {
        Object obj;
        Object next;
        KtUserType typeAsUserType;
        ktSuperTypeList.getClass();
        str.getClass();
        List entries = ktSuperTypeList.getEntries();
        entries.getClass();
        Iterator it = entries.iterator();
        do {
            obj = null;
            if (it.hasNext()) {
                next = it.next();
                typeAsUserType = ((KtSuperTypeListEntry) next).getTypeAsUserType();
            }
            return (KtSuperTypeListEntry) obj;
        } while (!Intrinsics.areEqual(typeAsUserType != null ? getFqName(typeAsUserType) : null, str));
        obj = next;
        return (KtSuperTypeListEntry) obj;
    }

    private static final String getFqName(KtUserType ktUserType) {
        KtUserType qualifier = ktUserType.getQualifier();
        if (qualifier == null) {
            return ktUserType.getReferencedName();
        }
        return getFqName(qualifier) + '.' + ktUserType.getReferencedName();
    }

    private static final Function2<PsiType, TypeAnnotationProvider, Unit> getSetPsiTypeAnnotationProvider() {
        return (Function2) setPsiTypeAnnotationProvider$delegate.getValue();
    }

    public static final <T> Lazy<T> lazyPub(Function0<? extends T> function0) {
        function0.getClass();
        return LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, function0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit setPsiTypeAnnotationProvider_delegate$lambda$0$1(Field field, PsiType psiType, TypeAnnotationProvider typeAnnotationProvider) throws IllegalAccessException {
        psiType.getClass();
        typeAnnotationProvider.getClass();
        if (field != null) {
            field.set(psiType, typeAnnotationProvider);
        }
        return Unit.INSTANCE;
    }
}
