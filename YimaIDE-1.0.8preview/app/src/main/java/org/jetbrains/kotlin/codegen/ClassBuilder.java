package org.jetbrains.kotlin.codegen;

import com.intellij.psi.PsiElement;
import org.jetbrains.kotlin.codegen.inline.SourceMapper;
import org.jetbrains.kotlin.codegen.serialization.JvmSerializationBindings;
import org.jetbrains.kotlin.resolve.jvm.diagnostics.JvmDeclarationOrigin;
import org.jetbrains.org.objectweb.asm.AnnotationVisitor;
import org.jetbrains.org.objectweb.asm.ClassVisitor;
import org.jetbrains.org.objectweb.asm.FieldVisitor;
import org.jetbrains.org.objectweb.asm.MethodVisitor;
import org.jetbrains.org.objectweb.asm.RecordComponentVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public interface ClassBuilder {
    void defineClass(PsiElement psiElement, int i, int i2, String str, String str2, String str3, String[] strArr);

    void done(boolean z);

    JvmSerializationBindings getSerializationBindings();

    String getThisName();

    ClassVisitor getVisitor();

    AnnotationVisitor newAnnotation(String str, boolean z);

    FieldVisitor newField(JvmDeclarationOrigin jvmDeclarationOrigin, int i, String str, String str2, String str3, Object obj);

    MethodVisitor newMethod(JvmDeclarationOrigin jvmDeclarationOrigin, int i, String str, String str2, String str3, String[] strArr);

    RecordComponentVisitor newRecordComponent(String str, String str2, String str3);

    void visitInnerClass(String str, String str2, String str3, int i);

    void visitOuterClass(String str, String str2, String str3);

    void visitSMAP(SourceMapper sourceMapper, boolean z, boolean z2);

    void visitSource(String str, String str2);
}
