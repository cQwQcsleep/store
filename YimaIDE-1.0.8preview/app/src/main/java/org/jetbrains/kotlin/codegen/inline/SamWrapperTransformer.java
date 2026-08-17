package org.jetbrains.kotlin.codegen.inline;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.ClassBuilder;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.ClassReader;
import org.jetbrains.org.objectweb.asm.ClassVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/SamWrapperTransformer;", "Lorg/jetbrains/kotlin/codegen/inline/ObjectTransformer;", "Lorg/jetbrains/kotlin/codegen/inline/SamWrapperTransformationInfo;", "transformationInfo", "inliningContext", "Lorg/jetbrains/kotlin/codegen/inline/InliningContext;", "<init>", "(Lorg/jetbrains/kotlin/codegen/inline/SamWrapperTransformationInfo;Lorg/jetbrains/kotlin/codegen/inline/InliningContext;)V", "doTransform", "Lorg/jetbrains/kotlin/codegen/inline/InlineResult;", "parentRemapper", "Lorg/jetbrains/kotlin/codegen/inline/FieldRemapper;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SamWrapperTransformer extends ObjectTransformer<SamWrapperTransformationInfo> {
    private final InliningContext inliningContext;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SamWrapperTransformer(SamWrapperTransformationInfo samWrapperTransformationInfo, InliningContext inliningContext) {
        super(samWrapperTransformationInfo, inliningContext.getState());
        samWrapperTransformationInfo.getClass();
        inliningContext.getClass();
        this.inliningContext = inliningContext;
    }

    @Override // org.jetbrains.kotlin.codegen.inline.ObjectTransformer
    public InlineResult doTransform(FieldRemapper parentRemapper) {
        parentRemapper.getClass();
        ClassReader classReaderCreateClassReader = createClassReader();
        final ClassBuilder classBuilderCreateRemappingClassBuilderViaFactory = createRemappingClassBuilderViaFactory(this.inliningContext);
        classReaderCreateClassReader.accept(new ClassVisitor(classBuilderCreateRemappingClassBuilderViaFactory.getVisitor()) { // from class: org.jetbrains.kotlin.codegen.inline.SamWrapperTransformer.doTransform.1
            public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
                name.getClass();
                superName.getClass();
                interfaces.getClass();
                classBuilderCreateRemappingClassBuilderViaFactory.defineClass(null, Math.max(version, this.getState().getConfig().getClassFileVersion()), access, name, signature, superName, interfaces);
            }
        }, 4);
        classBuilderCreateRemappingClassBuilderViaFactory.done(this.inliningContext.getState().getConfig().getGenerateSmapCopyToAnnotation());
        return this.transformationResult;
    }
}
