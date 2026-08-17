package org.jetbrains.kotlin.load.java.structure.impl.classFiles;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.org.objectweb.asm.ClassReader;
import org.jetbrains.org.objectweb.asm.ClassVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0012\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003¨\u0006\u0004"}, d2 = {"isNotTopLevelClass", "", "classContent", "", "org.jetbrains.kotlin:frontend.common.jvm"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class OtherKt {
    public static final boolean isNotTopLevelClass(byte[] bArr) {
        bArr.getClass();
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        new ClassReader(bArr).accept(new ClassVisitor() { // from class: org.jetbrains.kotlin.load.java.structure.impl.classFiles.OtherKt.isNotTopLevelClass.1
            private String internalName;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(CommonMixinsKt.ASM_API_VERSION_FOR_CLASS_READING);
            }

            public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
                this.internalName = name;
            }

            public void visitInnerClass(String name, String outerName, String innerName, int access) {
                if (Intrinsics.areEqual(name, this.internalName)) {
                    if (innerName != null) {
                        if (!Intrinsics.areEqual(name, outerName + '$' + innerName)) {
                            return;
                        }
                    }
                    booleanRef.element = true;
                }
            }
        }, 7);
        return booleanRef.element;
    }
}
