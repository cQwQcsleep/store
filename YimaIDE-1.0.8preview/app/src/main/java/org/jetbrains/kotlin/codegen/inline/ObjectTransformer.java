package org.jetbrains.kotlin.codegen.inline;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.ClassBuilder;
import org.jetbrains.kotlin.codegen.ClassFileFactory;
import org.jetbrains.kotlin.codegen.inline.TransformationInfo;
import org.jetbrains.kotlin.codegen.state.GenerationState;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.resolve.jvm.diagnostics.JvmDeclarationOrigin;
import org.jetbrains.org.objectweb.asm.ClassReader;
import org.jetbrains.org.objectweb.asm.Type;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b&\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\u00020\u0003B\u0017\u0012\u0006\u0010\u0004\u001a\u00028\u0000\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H&J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0004J\u0006\u0010\u0016\u001a\u00020\u0017R\u0017\u0010\u0004\u001a\u00028\u00008\u0006X\u0087\u0004\u0092\u0002\u0002\b\n¢\u0006\u0004\n\u0002\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0015\u0010\u0011\u001a\u00020\u000e8\u0004X\u0085\u0004\u0092\u0002\u0002\b\n¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/ObjectTransformer;", "T", "Lorg/jetbrains/kotlin/codegen/inline/TransformationInfo;", Argument.Delimiters.none, "transformationInfo", "state", "Lorg/jetbrains/kotlin/codegen/state/GenerationState;", "<init>", "(Lorg/jetbrains/kotlin/codegen/inline/TransformationInfo;Lorg/jetbrains/kotlin/codegen/state/GenerationState;)V", "Lorg/jetbrains/kotlin/codegen/inline/TransformationInfo;", "Lkotlin/jvm/JvmField;", "getState", "()Lorg/jetbrains/kotlin/codegen/state/GenerationState;", "doTransform", "Lorg/jetbrains/kotlin/codegen/inline/InlineResult;", "parentRemapper", "Lorg/jetbrains/kotlin/codegen/inline/FieldRemapper;", "transformationResult", "createRemappingClassBuilderViaFactory", "Lorg/jetbrains/kotlin/codegen/ClassBuilder;", "inliningContext", "Lorg/jetbrains/kotlin/codegen/inline/InliningContext;", "createClassReader", "Lorg/jetbrains/org/objectweb/asm/ClassReader;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class ObjectTransformer<T extends TransformationInfo> {
    private final GenerationState state;
    public final T transformationInfo;
    protected final InlineResult transformationResult;

    public ObjectTransformer(T t, GenerationState generationState) {
        t.getClass();
        generationState.getClass();
        this.transformationInfo = t;
        this.state = generationState;
        this.transformationResult = InlineResult.INSTANCE.create();
    }

    public final ClassReader createClassReader() {
        return new ClassReader(InlineCodegenUtilsKt.loadClassBytesByInternalName(this.state, this.transformationInfo.getOldClassName()));
    }

    public final ClassBuilder createRemappingClassBuilderViaFactory(InliningContext inliningContext) {
        inliningContext.getClass();
        ClassFileFactory factory = this.state.getFactory();
        JvmDeclarationOrigin jvmDeclarationOrigin = JvmDeclarationOrigin.NO_ORIGIN;
        Type objectType = Type.getObjectType(this.transformationInfo.getNewClassName());
        objectType.getClass();
        return new RemappingClassBuilder(factory.newVisitor(jvmDeclarationOrigin, objectType, CollectionsKt.listOfNotNull(inliningContext.getCallSiteInfo().getFile())), new AsmTypeRemapper(inliningContext.getTypeRemapper(), this.transformationResult));
    }

    public abstract InlineResult doTransform(FieldRemapper parentRemapper);

    public final GenerationState getState() {
        return this.state;
    }
}
