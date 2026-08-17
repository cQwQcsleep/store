package org.jetbrains.kotlin.codegen.optimization;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.ClassBuilder;
import org.jetbrains.kotlin.codegen.DelegatingClassBuilder;
import org.jetbrains.kotlin.codegen.state.GenerationState;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.resolve.jvm.diagnostics.JvmDeclarationOrigin;
import org.jetbrains.kotlin.resolve.jvm.diagnostics.JvmDeclarationOriginKind;
import org.jetbrains.org.objectweb.asm.MethodVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\b\u001a\u00020\u0003H\u0016JI\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u00102\u0010\u0010\u0013\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0010\u0018\u00010\u0014H\u0016¢\u0006\u0002\u0010\u0015R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/OptimizationClassBuilder;", "Lorg/jetbrains/kotlin/codegen/DelegatingClassBuilder;", "delegate", "Lorg/jetbrains/kotlin/codegen/ClassBuilder;", "generationState", "Lorg/jetbrains/kotlin/codegen/state/GenerationState;", "<init>", "(Lorg/jetbrains/kotlin/codegen/ClassBuilder;Lorg/jetbrains/kotlin/codegen/state/GenerationState;)V", "getDelegate", "newMethod", "Lorg/jetbrains/org/objectweb/asm/MethodVisitor;", "origin", "Lorg/jetbrains/kotlin/resolve/jvm/diagnostics/JvmDeclarationOrigin;", "access", Argument.Delimiters.none, ModuleXmlParser.NAME, Argument.Delimiters.none, "desc", "signature", "exceptions", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/resolve/jvm/diagnostics/JvmDeclarationOrigin;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Lorg/jetbrains/org/objectweb/asm/MethodVisitor;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class OptimizationClassBuilder extends DelegatingClassBuilder {
    private final ClassBuilder delegate;
    private final GenerationState generationState;

    public OptimizationClassBuilder(ClassBuilder classBuilder, GenerationState generationState) {
        classBuilder.getClass();
        generationState.getClass();
        this.delegate = classBuilder;
        this.generationState = generationState;
    }

    @Override // org.jetbrains.kotlin.codegen.DelegatingClassBuilder
    public ClassBuilder getDelegate() {
        return this.delegate;
    }

    @Override // org.jetbrains.kotlin.codegen.DelegatingClassBuilder, org.jetbrains.kotlin.codegen.ClassBuilder
    public MethodVisitor newMethod(JvmDeclarationOrigin origin, int access, String name, String desc, String signature, String[] exceptions) {
        origin.getClass();
        name.getClass();
        desc.getClass();
        MethodVisitor methodVisitorNewMethod = super.newMethod(origin, access, name, desc, signature, exceptions);
        methodVisitorNewMethod.getClass();
        return new OptimizationMethodVisitor(methodVisitorNewMethod, origin.getOriginKind() == JvmDeclarationOriginKind.INLINE_VERSION_OF_SUSPEND_FUN, this.generationState, access, name, desc, signature, exceptions);
    }
}
