package org.jetbrains.kotlin.incremental.impl;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.load.java.structure.impl.classFiles.CommonMixinsKt;
import org.jetbrains.kotlin.metadata.jvm.deserialization.JvmMemberSignature;
import org.jetbrains.kotlin.resolve.jvm.JvmClassName;
import org.jetbrains.org.objectweb.asm.ClassVisitor;
import org.jetbrains.org.objectweb.asm.MethodVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\u00020\u0001BA\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001\u0012\u001c\b\u0002\u0010\u0003\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0018\u00010\u0004\u0012\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006¢\u0006\u0004\b\t\u0010\nJI\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u00102\u0012\u0010\u0013\u001a\u000e\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u0010\u0018\u00010\u0014H\u0016¢\u0006\u0002\u0010\u0015R\"\u0010\u0003\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0018\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/incremental/impl/InstanceOwnerRecordingClassVisitor;", "Lorg/jetbrains/org/objectweb/asm/ClassVisitor;", "delegateClassVisitor", "methodToUsedClassesMap", "", "Lorg/jetbrains/kotlin/metadata/jvm/deserialization/JvmMemberSignature$Method;", "", "Lorg/jetbrains/kotlin/resolve/jvm/JvmClassName;", "allUsedClassesSet", "<init>", "(Lorg/jetbrains/org/objectweb/asm/ClassVisitor;Ljava/util/Map;Ljava/util/Set;)V", "visitMethod", "Lorg/jetbrains/org/objectweb/asm/MethodVisitor;", "access", "", "name", "", "descriptor", "signature", "exceptions", "", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Lorg/jetbrains/org/objectweb/asm/MethodVisitor;", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class InstanceOwnerRecordingClassVisitor extends ClassVisitor {
    private final Set<JvmClassName> allUsedClassesSet;
    private final Map<JvmMemberSignature.Method, Set<JvmClassName>> methodToUsedClassesMap;

    public /* synthetic */ InstanceOwnerRecordingClassVisitor(ClassVisitor classVisitor, Map map, Set set, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(classVisitor, (i & 2) != 0 ? null : map, (i & 4) != 0 ? null : set);
    }

    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        if (name != null) {
            final JvmMemberSignature.Method method = descriptor != null ? new JvmMemberSignature.Method(name, descriptor) : null;
            if (method != null) {
                return new MethodVisitor(super.visitMethod(access, name, descriptor, signature, exceptions)) { // from class: org.jetbrains.kotlin.incremental.impl.InstanceOwnerRecordingClassVisitor.visitMethod.1
                    private final void storeUsage(String internalName) {
                        JvmClassName jvmClassNameByInternalName = JvmClassName.byInternalName(internalName);
                        jvmClassNameByInternalName.getClass();
                        Map map = InstanceOwnerRecordingClassVisitor.this.methodToUsedClassesMap;
                        if (map != null) {
                            JvmMemberSignature.Method method2 = method;
                            Object linkedHashSet = map.get(method2);
                            if (linkedHashSet == null) {
                                linkedHashSet = new LinkedHashSet();
                                map.put(method2, linkedHashSet);
                            }
                            ((Set) linkedHashSet).add(jvmClassNameByInternalName);
                        }
                        Set set = InstanceOwnerRecordingClassVisitor.this.allUsedClassesSet;
                        if (set != null) {
                            set.add(jvmClassNameByInternalName);
                        }
                    }

                    public void visitFieldInsn(int opcode, String owner, String name2, String descriptor2) {
                        if (opcode == 178 && Intrinsics.areEqual(name2, "INSTANCE") && owner != null && StringsKt.contains$default(owner, "$", false, 2, (Object) null)) {
                            storeUsage(owner);
                        }
                        super.visitFieldInsn(opcode, owner, name2, descriptor2);
                    }

                    public void visitTypeInsn(int opcode, String type) {
                        if (opcode == 187 && type != null && StringsKt.contains$default(type, "$", false, 2, (Object) null)) {
                            storeUsage(type);
                        }
                        super.visitTypeInsn(opcode, type);
                    }
                };
            }
        }
        return null;
    }

    public InstanceOwnerRecordingClassVisitor(ClassVisitor classVisitor, Map<JvmMemberSignature.Method, Set<JvmClassName>> map, Set<JvmClassName> set) {
        super(CommonMixinsKt.ASM_API_VERSION_FOR_CLASS_READING, classVisitor);
        this.methodToUsedClassesMap = map;
        this.allUsedClassesSet = set;
    }
}
