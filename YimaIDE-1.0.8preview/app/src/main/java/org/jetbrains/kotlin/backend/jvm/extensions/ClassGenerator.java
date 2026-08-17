package org.jetbrains.kotlin.backend.jvm.extensions;

import kotlin.Metadata;
import org.jetbrains.kotlin.ir.declarations.IrField;
import org.jetbrains.kotlin.ir.declarations.IrFunction;
import org.jetbrains.org.objectweb.asm.AnnotationVisitor;
import org.jetbrains.org.objectweb.asm.FieldVisitor;
import org.jetbrains.org.objectweb.asm.MethodVisitor;
import org.jetbrains.org.objectweb.asm.RecordComponentVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\bf\u0018\u00002\u00020\u0001JG\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\n\u001a\u00020\b2\u000e\u0010\u000b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\b0\fH&¢\u0006\u0002\u0010\rJ>\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\b2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H&JK\u0010\u0014\u001a\u00020\u00152\b\u0010\u0010\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\b2\u0010\u0010\u0017\u001a\f\u0012\u0006\b\u0001\u0012\u00020\b\u0018\u00010\fH&¢\u0006\u0002\u0010\u0018J\"\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\bH&J\u0018\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0012\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\u001eH&J,\u0010\u001f\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\b\u0010 \u001a\u0004\u0018\u00010\b2\b\u0010!\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0006\u001a\u00020\u0005H&J$\u0010\"\u001a\u00020\u00032\u0006\u0010#\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\u0012\u001a\u0004\u0018\u00010\bH&J\u001a\u0010$\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\b\u0010%\u001a\u0004\u0018\u00010\bH&J\u0010\u0010&\u001a\u00020\u00032\u0006\u0010'\u001a\u00020\u001eH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006(À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/extensions/ClassGenerator;", "", "defineClass", "", "version", "", "access", "name", "", "signature", "superName", "interfaces", "", "(IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)V", "newField", "Lorg/jetbrains/org/objectweb/asm/FieldVisitor;", "declaration", "Lorg/jetbrains/kotlin/ir/declarations/IrField;", "desc", "value", "newMethod", "Lorg/jetbrains/org/objectweb/asm/MethodVisitor;", "Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", "exceptions", "(Lorg/jetbrains/kotlin/ir/declarations/IrFunction;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Lorg/jetbrains/org/objectweb/asm/MethodVisitor;", "newRecordComponent", "Lorg/jetbrains/org/objectweb/asm/RecordComponentVisitor;", "visitAnnotation", "Lorg/jetbrains/org/objectweb/asm/AnnotationVisitor;", "visible", "", "visitInnerClass", "outerName", "innerName", "visitEnclosingMethod", "owner", "visitSource", "debug", "done", "generateSmapCopyToAnnotation", "org.jetbrains.kotlin:backend.jvm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface ClassGenerator {
    void defineClass(int version, int access, String name, String signature, String superName, String[] interfaces);

    void done(boolean generateSmapCopyToAnnotation);

    FieldVisitor newField(IrField declaration, int access, String name, String desc, String signature, Object value);

    MethodVisitor newMethod(IrFunction declaration, int access, String name, String desc, String signature, String[] exceptions);

    RecordComponentVisitor newRecordComponent(String name, String desc, String signature);

    AnnotationVisitor visitAnnotation(String desc, boolean visible);

    void visitEnclosingMethod(String owner, String name, String desc);

    void visitInnerClass(String name, String outerName, String innerName, int access);

    void visitSource(String name, String debug);
}
