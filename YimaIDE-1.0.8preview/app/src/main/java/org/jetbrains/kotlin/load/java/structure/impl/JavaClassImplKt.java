package org.jetbrains.kotlin.load.java.structure.impl;

import com.intellij.openapi.util.Condition;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiClassType;
import com.intellij.psi.PsiElementFactory;
import com.intellij.psi.SyntaxTraverser;
import com.intellij.util.Function;
import com.intellij.util.containers.JBIterable;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.sequences.Sequence;
import org.jetbrains.kotlin.load.java.structure.JavaClassifierType;
import org.jetbrains.kotlin.load.java.structure.impl.source.JavaElementPsiSource;
import org.jetbrains.kotlin.load.java.structure.impl.source.JavaElementSourceFactory;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001c\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0002¨\u0006\u0006"}, d2 = {"lazilyComputePermittedTypesInSameFile", "Lkotlin/sequences/Sequence;", "Lorg/jetbrains/kotlin/load/java/structure/JavaClassifierType;", "psiElementSource", "Lorg/jetbrains/kotlin/load/java/structure/impl/source/JavaElementPsiSource;", "Lcom/intellij/psi/PsiClass;", "org.jetbrains.kotlin:frontend.common.jvm"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class JavaClassImplKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Sequence<JavaClassifierType> lazilyComputePermittedTypesInSameFile(final JavaElementPsiSource<PsiClass> javaElementPsiSource) {
        return new Sequence<JavaClassifierTypeImpl>() { // from class: org.jetbrains.kotlin.load.java.structure.impl.JavaClassImplKt$lazilyComputePermittedTypesInSameFile$$inlined$Sequence$1
            public Iterator<JavaClassifierTypeImpl> iterator() {
                final PsiClass psi = javaElementPsiSource.getPsi();
                final PsiElementFactory psiElementFactory = PsiElementFactory.getInstance(psi.getProject());
                JBIterable jBIterableFilter = SyntaxTraverser.psiTraverser(psi.getContainingFile()).filter(PsiClass.class);
                final Function1<PsiClass, Boolean> function1 = new Function1<PsiClass, Boolean>() { // from class: org.jetbrains.kotlin.load.java.structure.impl.JavaClassImplKt$lazilyComputePermittedTypesInSameFile$1$1
                    public final Boolean invoke(PsiClass psiClass) {
                        return Boolean.valueOf(psiClass.isInheritor(psi, false));
                    }
                };
                JBIterable jBIterableFilter2 = jBIterableFilter.filter(new Condition(function1) { // from class: org.jetbrains.kotlin.load.java.structure.impl.JavaClassImplKt$sam$com_intellij_openapi_util_Condition$0
                    private final /* synthetic */ Function1 function;

                    {
                        function1.getClass();
                        this.function = function1;
                    }

                    public final /* synthetic */ boolean value(Object obj) {
                        return ((Boolean) this.function.invoke(obj)).booleanValue();
                    }
                });
                final JavaElementPsiSource javaElementPsiSource2 = javaElementPsiSource;
                final Function1<PsiClass, JavaClassifierTypeImpl> function2 = new Function1<PsiClass, JavaClassifierTypeImpl>() { // from class: org.jetbrains.kotlin.load.java.structure.impl.JavaClassImplKt$lazilyComputePermittedTypesInSameFile$1$2
                    public final JavaClassifierTypeImpl invoke(PsiClass psiClass) {
                        JavaElementSourceFactory factory = javaElementPsiSource2.getFactory();
                        PsiClassType psiClassTypeCreateType = psiElementFactory.createType(psiClass);
                        psiClassTypeCreateType.getClass();
                        return new JavaClassifierTypeImpl(factory.createTypeSource(psiClassTypeCreateType));
                    }
                };
                return jBIterableFilter2.map(new Function(function2) { // from class: org.jetbrains.kotlin.load.java.structure.impl.JavaClassImplKt$sam$com_intellij_util_Function$0
                    private final /* synthetic */ Function1 function;

                    {
                        function2.getClass();
                        this.function = function2;
                    }

                    public final /* synthetic */ Object fun(Object obj) {
                        return this.function.invoke(obj);
                    }
                }).iterator();
            }
        };
    }
}
