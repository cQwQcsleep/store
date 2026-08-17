package org.jetbrains.kotlin.codegen;

import com.intellij.psi.PsiElement;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.inline.InlineCodegenUtilsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.MethodVisitor;
import org.jetbrains.org.objectweb.asm.tree.LocalVariableNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;
import org.jetbrains.org.objectweb.asm.util.Textifier;
import org.jetbrains.org.objectweb.asm.util.TraceMethodVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001:\u0001\u001aBM\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\u0010\u0010\t\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0006\u0018\u00010\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0004¢\u0006\u0004\b\f\u0010\rJ\b\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\u000fH$J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u000fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u00118F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/codegen/TransformationMethodVisitor;", "Lorg/jetbrains/org/objectweb/asm/MethodVisitor;", "delegate", "access", Argument.Delimiters.none, ModuleXmlParser.NAME, Argument.Delimiters.none, "desc", "signature", "exceptions", Argument.Delimiters.none, "api", "<init>", "(Lorg/jetbrains/org/objectweb/asm/MethodVisitor;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;I)V", "methodNode", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "traceMethodVisitorIfPossible", "Lorg/jetbrains/org/objectweb/asm/util/TraceMethodVisitor;", "getTraceMethodVisitorIfPossible", "()Lorg/jetbrains/org/objectweb/asm/util/TraceMethodVisitor;", "visitEnd", Argument.Delimiters.none, "performTransformations", "shouldBeTransformed", Argument.Delimiters.none, "node", "EndIgnoringMethodVisitorDecorator", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class TransformationMethodVisitor extends MethodVisitor {
    private final MethodVisitor delegate;
    private final MethodNode methodNode;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0001¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/codegen/TransformationMethodVisitor$EndIgnoringMethodVisitorDecorator;", "Lorg/jetbrains/org/objectweb/asm/MethodVisitor;", "api", Argument.Delimiters.none, "mv", "<init>", "(ILorg/jetbrains/org/objectweb/asm/MethodVisitor;)V", "visitEnd", Argument.Delimiters.none, "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class EndIgnoringMethodVisitorDecorator extends MethodVisitor {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public EndIgnoringMethodVisitorDecorator(int i, MethodVisitor methodVisitor) {
            super(i, methodVisitor);
            methodVisitor.getClass();
        }

        public void visitEnd() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TransformationMethodVisitor(MethodVisitor methodVisitor, int i, String str, String str2, String str3, String[] strArr, int i2) {
        super(i2);
        methodVisitor.getClass();
        str.getClass();
        str2.getClass();
        this.delegate = methodVisitor;
        MethodNode methodNode = new MethodNode(i, str, str2, str3, strArr);
        methodNode.localVariables = new ArrayList(5);
        this.methodNode = methodNode;
        ((MethodVisitor) this).mv = InlineCodegenUtilsKt.wrapWithMaxLocalCalc(methodNode);
    }

    private final boolean shouldBeTransformed(MethodNode node) {
        return node.instructions.size() > 0;
    }

    public final TraceMethodVisitor getTraceMethodVisitorIfPossible() {
        TraceMethodVisitor traceMethodVisitor = new TraceMethodVisitor(new Textifier());
        try {
            this.methodNode.accept(traceMethodVisitor);
            return traceMethodVisitor;
        } catch (Throwable unused) {
            return null;
        }
    }

    public abstract void performTransformations(MethodNode methodNode);

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.codegen.CompilationException */
    public void visitEnd() throws CompilationException {
        MethodNode methodNode = this.methodNode;
        if (methodNode.maxLocals <= 0 || methodNode.maxStack <= 0) {
            ((MethodVisitor) this).mv.visitMaxs(-1, -1);
        }
        super.visitEnd();
        try {
            if (shouldBeTransformed(this.methodNode)) {
                performTransformations(this.methodNode);
            }
            this.methodNode.accept(new EndIgnoringMethodVisitorDecorator(589824, this.delegate));
            if (this.methodNode.instructions.size() == 0 && (!(this.delegate instanceof MethodNode) || this.methodNode.localVariables != null)) {
                List list = this.methodNode.localVariables;
                int size = list != null ? list.size() : 0;
                for (int i = 0; i < size; i++) {
                    list.getClass();
                    ((LocalVariableNode) list.get(i)).accept(this.delegate);
                }
            }
            this.delegate.visitEnd();
        } catch (Throwable th) {
            throw new CompilationException("Couldn't transform method node:\n" + InlineCodegenUtilsKt.getNodeText(this.methodNode), th, (PsiElement) null);
        }
    }

    public /* synthetic */ TransformationMethodVisitor(MethodVisitor methodVisitor, int i, String str, String str2, String str3, String[] strArr, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(methodVisitor, i, str, str2, str3, strArr, (i3 & 64) != 0 ? 589824 : i2);
    }
}
