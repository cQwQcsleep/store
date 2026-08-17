package org.jetbrains.kotlin.psi;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016¢\u0006\u0002\u0010\t¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/psi/KotlinReferenceProvidersService;", "", "<init>", "()V", "getReferences", "", "Lcom/intellij/psi/PsiReference;", "psiElement", "Lcom/intellij/psi/PsiElement;", "(Lcom/intellij/psi/PsiElement;)[Lcom/intellij/psi/PsiReference;", "Companion", "org.jetbrains.kotlin:psi-api"}, k = 1, mv = {2, 4, 0}, xi = 48)
public class KotlinReferenceProvidersService {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final KotlinReferenceProvidersService NO_REFERENCES_SERVICE = new KotlinReferenceProvidersService();

    @JvmStatic
    public static final KotlinReferenceProvidersService getInstance(Project project) {
        return INSTANCE.getInstance(project);
    }

    @JvmStatic
    public static final PsiReference[] getReferencesFromProviders(PsiElement psiElement) {
        return INSTANCE.getReferencesFromProviders(psiElement);
    }

    public PsiReference[] getReferences(PsiElement psiElement) {
        psiElement.getClass();
        PsiReference[] psiReferenceArr = PsiReference.EMPTY_ARRAY;
        psiReferenceArr.getClass();
        return psiReferenceArr;
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0007b\u0002\b\tJ\u001f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0007b\u0002\b\t¢\u0006\u0002\u0010\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/psi/KotlinReferenceProvidersService$Companion;", "", "<init>", "()V", "NO_REFERENCES_SERVICE", "Lorg/jetbrains/kotlin/psi/KotlinReferenceProvidersService;", "getInstance", "project", "Lcom/intellij/openapi/project/Project;", "Lkotlin/jvm/JvmStatic;", "getReferencesFromProviders", "", "Lcom/intellij/psi/PsiReference;", "psiElement", "Lcom/intellij/psi/PsiElement;", "(Lcom/intellij/psi/PsiElement;)[Lcom/intellij/psi/PsiReference;", "org.jetbrains.kotlin:psi-api"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final KotlinReferenceProvidersService getInstance(Project project) {
            project.getClass();
            KotlinReferenceProvidersService kotlinReferenceProvidersService = (KotlinReferenceProvidersService) project.getService(KotlinReferenceProvidersService.class);
            return kotlinReferenceProvidersService == null ? KotlinReferenceProvidersService.NO_REFERENCES_SERVICE : kotlinReferenceProvidersService;
        }

        @JvmStatic
        public final PsiReference[] getReferencesFromProviders(PsiElement psiElement) {
            psiElement.getClass();
            Project project = psiElement.getProject();
            project.getClass();
            return getInstance(project).getReferences(psiElement);
        }

        private Companion() {
        }
    }
}
