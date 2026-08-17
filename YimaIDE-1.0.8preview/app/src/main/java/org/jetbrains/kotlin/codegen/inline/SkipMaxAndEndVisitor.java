package org.jetbrains.kotlin.codegen.inline;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.MethodVisitor;
import org.jetbrains.org.objectweb.asm.commons.InstructionAdapter;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016J\b\u0010\u000b\u001a\u00020\u0007H\u0016¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/SkipMaxAndEndVisitor;", "Lorg/jetbrains/org/objectweb/asm/commons/InstructionAdapter;", "mv", "Lorg/jetbrains/org/objectweb/asm/MethodVisitor;", "<init>", "(Lorg/jetbrains/org/objectweb/asm/MethodVisitor;)V", "visitMaxs", Argument.Delimiters.none, "maxStack", Argument.Delimiters.none, "maxLocals", "visitEnd", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class SkipMaxAndEndVisitor extends InstructionAdapter {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SkipMaxAndEndVisitor(MethodVisitor methodVisitor) {
        super(589824, methodVisitor);
        methodVisitor.getClass();
    }

    public void visitEnd() {
    }

    public void visitMaxs(int maxStack, int maxLocals) {
    }
}
