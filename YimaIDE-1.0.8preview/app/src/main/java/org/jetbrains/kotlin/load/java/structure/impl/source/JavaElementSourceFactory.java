package org.jetbrains.kotlin.load.java.structure.impl.source;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiClassType;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiExpression;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiType;
import com.intellij.psi.PsiTypeParameter;
import com.intellij.psi.PsiVariable;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u0000 $2\u00020\u0001:\u0001$B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J%\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\b\b\u0000\u0010\u0006*\u00020\u00072\u0006\u0010\b\u001a\u0002H\u0006H&¢\u0006\u0002\u0010\tJ%\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\f0\u000b\"\b\b\u0000\u0010\f*\u00020\r2\u0006\u0010\u000e\u001a\u0002H\fH&¢\u0006\u0002\u0010\u000fJ(\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\f0\u000b\"\b\b\u0000\u0010\f*\u00020\r2\u000e\u0010\u0011\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00120\u0005H&J(\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\f0\u000b\"\b\b\u0000\u0010\f*\u00020\r2\u000e\u0010\u0014\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00150\u0005H&J0\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\f0\u000b\"\b\b\u0000\u0010\f*\u00020\r2\u000e\u0010\u0017\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00180\u00052\u0006\u0010\u0019\u001a\u00020\u001aH&J&\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u000b2\u000e\u0010\u0017\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001d0\u00052\u0006\u0010\u001e\u001a\u00020\u001aH&J(\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\f0\u000b\"\b\b\u0000\u0010\f*\u00020\r2\u000e\u0010 \u001a\n\u0012\u0006\b\u0001\u0012\u00020!0\u0005H&J&\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u001c0\u000b2\u000e\u0010\u0017\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001d0\u00052\u0006\u0010#\u001a\u00020\u001aH&¨\u0006%"}, d2 = {"Lorg/jetbrains/kotlin/load/java/structure/impl/source/JavaElementSourceFactory;", "", "<init>", "()V", "createPsiSource", "Lorg/jetbrains/kotlin/load/java/structure/impl/source/JavaElementPsiSource;", "PSI", "Lcom/intellij/psi/PsiElement;", "psi", "(Lcom/intellij/psi/PsiElement;)Lorg/jetbrains/kotlin/load/java/structure/impl/source/JavaElementPsiSource;", "createTypeSource", "Lorg/jetbrains/kotlin/load/java/structure/impl/source/JavaElementTypeSource;", "TYPE", "Lcom/intellij/psi/PsiType;", "type", "(Lcom/intellij/psi/PsiType;)Lorg/jetbrains/kotlin/load/java/structure/impl/source/JavaElementTypeSource;", "createVariableReturnTypeSource", "psiVariableSource", "Lcom/intellij/psi/PsiVariable;", "createMethodReturnTypeSource", "psiMethodSource", "Lcom/intellij/psi/PsiMethod;", "createTypeParameterUpperBoundTypeSource", "psiTypeParameterSource", "Lcom/intellij/psi/PsiTypeParameter;", "boundIndex", "", "createSuperTypeSource", "Lcom/intellij/psi/PsiClassType;", "Lcom/intellij/psi/PsiClass;", "superTypeIndex", "createExpressionTypeSource", "psiExpressionSource", "Lcom/intellij/psi/PsiExpression;", "createPermittedTypeSource", "permittedTypeIndex", "Companion", "org.jetbrains.kotlin:frontend.common.jvm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class JavaElementSourceFactory {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @JvmStatic
    public static final JavaElementSourceFactory getInstance(Project project) {
        return INSTANCE.getInstance(project);
    }

    public abstract <TYPE extends PsiType> JavaElementTypeSource<TYPE> createExpressionTypeSource(JavaElementPsiSource<? extends PsiExpression> psiExpressionSource);

    public abstract <TYPE extends PsiType> JavaElementTypeSource<TYPE> createMethodReturnTypeSource(JavaElementPsiSource<? extends PsiMethod> psiMethodSource);

    public abstract JavaElementTypeSource<PsiClassType> createPermittedTypeSource(JavaElementPsiSource<? extends PsiClass> psiTypeParameterSource, int permittedTypeIndex);

    public abstract <PSI extends PsiElement> JavaElementPsiSource<PSI> createPsiSource(PSI psi);

    public abstract JavaElementTypeSource<PsiClassType> createSuperTypeSource(JavaElementPsiSource<? extends PsiClass> psiTypeParameterSource, int superTypeIndex);

    public abstract <TYPE extends PsiType> JavaElementTypeSource<TYPE> createTypeParameterUpperBoundTypeSource(JavaElementPsiSource<? extends PsiTypeParameter> psiTypeParameterSource, int boundIndex);

    public abstract <TYPE extends PsiType> JavaElementTypeSource<TYPE> createTypeSource(TYPE type);

    public abstract <TYPE extends PsiType> JavaElementTypeSource<TYPE> createVariableReturnTypeSource(JavaElementPsiSource<? extends PsiVariable> psiVariableSource);

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007b\u0002\b\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/load/java/structure/impl/source/JavaElementSourceFactory$Companion;", "", "<init>", "()V", "getInstance", "Lorg/jetbrains/kotlin/load/java/structure/impl/source/JavaElementSourceFactory;", "project", "Lcom/intellij/openapi/project/Project;", "Lkotlin/jvm/JvmStatic;", "org.jetbrains.kotlin:frontend.common.jvm"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final JavaElementSourceFactory getInstance(Project project) {
            project.getClass();
            Object service = project.getService(JavaElementSourceFactory.class);
            service.getClass();
            return (JavaElementSourceFactory) service;
        }

        private Companion() {
        }
    }
}
