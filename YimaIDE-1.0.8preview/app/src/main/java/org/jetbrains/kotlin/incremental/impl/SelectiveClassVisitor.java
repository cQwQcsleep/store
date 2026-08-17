package org.jetbrains.kotlin.incremental.impl;

import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import org.jetbrains.kotlin.load.java.structure.impl.classFiles.CommonMixinsKt;
import org.jetbrains.kotlin.metadata.jvm.deserialization.JvmMemberSignature;
import org.jetbrains.org.objectweb.asm.ClassVisitor;
import org.jetbrains.org.objectweb.asm.FieldVisitor;
import org.jetbrains.org.objectweb.asm.MethodVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\u0018\u00002\u00020\u0001Bv\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012<\u0010\u0003\u001a8\u0012\u0004\u0012\u00020\u0005\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00060\u0004\u0012'\u0010\u000b\u001a#\u0012\u0004\u0012\u00020\r\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\u00060\f¢\u0006\u0004\b\u000e\u0010\u000fJ6\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\b\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\b\u0010\u0016\u001a\u0004\u0018\u00010\u00142\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016JC\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\b\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\b\u0010\u0016\u001a\u0004\u0018\u00010\u00142\u0010\u0010\u001b\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0014\u0018\u00010\u001cH\u0016¢\u0006\u0002\u0010\u001dJ\f\u0010\t\u001a\u00020\u0006*\u00020\u0013H\u0002J\f\u0010\u001e\u001a\u00020\u0006*\u00020\u0013H\u0002RD\u0010\u0003\u001a8\u0012\u0004\u0012\u00020\u0005\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R/\u0010\u000b\u001a#\u0012\u0004\u0012\u00020\r\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\u00060\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lorg/jetbrains/kotlin/incremental/impl/SelectiveClassVisitor;", "Lorg/jetbrains/org/objectweb/asm/ClassVisitor;", "cv", "shouldVisitField", "Lkotlin/Function3;", "Lorg/jetbrains/kotlin/metadata/jvm/deserialization/JvmMemberSignature$Field;", "", "Lkotlin/ParameterName;", "name", "isPrivate", "isConstant", "shouldVisitMethod", "Lkotlin/Function2;", "Lorg/jetbrains/kotlin/metadata/jvm/deserialization/JvmMemberSignature$Method;", "<init>", "(Lorg/jetbrains/org/objectweb/asm/ClassVisitor;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;)V", "visitField", "Lorg/jetbrains/org/objectweb/asm/FieldVisitor;", "access", "", "", "desc", "signature", "value", "", "visitMethod", "Lorg/jetbrains/org/objectweb/asm/MethodVisitor;", "exceptions", "", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Lorg/jetbrains/org/objectweb/asm/MethodVisitor;", "isStaticFinal", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SelectiveClassVisitor extends ClassVisitor {
    private final Function3<JvmMemberSignature.Field, Boolean, Boolean, Boolean> shouldVisitField;
    private final Function2<JvmMemberSignature.Method, Boolean, Boolean> shouldVisitMethod;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public SelectiveClassVisitor(ClassVisitor classVisitor, Function3<? super JvmMemberSignature.Field, ? super Boolean, ? super Boolean, Boolean> function3, Function2<? super JvmMemberSignature.Method, ? super Boolean, Boolean> function2) {
        super(CommonMixinsKt.ASM_API_VERSION_FOR_CLASS_READING, classVisitor);
        classVisitor.getClass();
        function3.getClass();
        function2.getClass();
        this.shouldVisitField = function3;
        this.shouldVisitMethod = function2;
    }

    private final boolean isPrivate(int i) {
        return (i & 2) != 0;
    }

    private final boolean isStaticFinal(int i) {
        return (i & 24) == 24;
    }

    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        name.getClass();
        desc.getClass();
        if (((Boolean) this.shouldVisitField.invoke(new JvmMemberSignature.Field(name, desc), Boolean.valueOf(isPrivate(access)), Boolean.valueOf(isStaticFinal(access) && value != null))).booleanValue()) {
            return ((ClassVisitor) this).cv.visitField(access, name, desc, signature, value);
        }
        return null;
    }

    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        name.getClass();
        desc.getClass();
        if (((Boolean) this.shouldVisitMethod.invoke(new JvmMemberSignature.Method(name, desc), Boolean.valueOf(isPrivate(access)))).booleanValue()) {
            return ((ClassVisitor) this).cv.visitMethod(access, name, desc, signature, exceptions);
        }
        return null;
    }
}
