package org.jetbrains.kotlin.codegen.inline;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.ClassBuilder;
import org.jetbrains.kotlin.codegen.InsnSequence;
import org.jetbrains.kotlin.codegen.inline.WhenMappingTransformer;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.resolve.jvm.diagnostics.JvmDeclarationOrigin;
import org.jetbrains.org.objectweb.asm.ClassReader;
import org.jetbrains.org.objectweb.asm.ClassVisitor;
import org.jetbrains.org.objectweb.asm.FieldVisitor;
import org.jetbrains.org.objectweb.asm.MethodVisitor;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.FieldInsnNode;
import org.jetbrains.org.objectweb.asm.tree.InsnList;
import org.jetbrains.org.objectweb.asm.tree.MethodInsnNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0002J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000e\u001a\u00020\u0011H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/WhenMappingTransformer;", "Lorg/jetbrains/kotlin/codegen/inline/ObjectTransformer;", "Lorg/jetbrains/kotlin/codegen/inline/WhenMappingTransformationInfo;", "whenObjectRegenerationInfo", "inliningContext", "Lorg/jetbrains/kotlin/codegen/inline/InliningContext;", "<init>", "(Lorg/jetbrains/kotlin/codegen/inline/WhenMappingTransformationInfo;Lorg/jetbrains/kotlin/codegen/inline/InliningContext;)V", "doTransform", "Lorg/jetbrains/kotlin/codegen/inline/InlineResult;", "parentRemapper", "Lorg/jetbrains/kotlin/codegen/inline/FieldRemapper;", "cutOtherMappings", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "node", "isValues", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class WhenMappingTransformer extends ObjectTransformer<WhenMappingTransformationInfo> {
    private final InliningContext inliningContext;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WhenMappingTransformer(WhenMappingTransformationInfo whenMappingTransformationInfo, InliningContext inliningContext) {
        super(whenMappingTransformationInfo, inliningContext.getState());
        whenMappingTransformationInfo.getClass();
        inliningContext.getClass();
        this.inliningContext = inliningContext;
    }

    public static AbstractInsnNode a(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        return abstractInsnNode.getNext();
    }

    public static AbstractInsnNode b(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        return abstractInsnNode.getPrevious();
    }

    private final MethodNode cutOtherMappings(MethodNode node) {
        InsnList insnList = node.instructions;
        insnList.getClass();
        for (FieldInsnNode fieldInsnNode : new InsnSequence(insnList)) {
            if ((fieldInsnNode instanceof FieldInsnNode) && Intrinsics.areEqual(fieldInsnNode.name, ((WhenMappingTransformationInfo) this.transformationInfo).getFieldNode().name)) {
                for (AbstractInsnNode abstractInsnNode : SequencesKt.generateSequence(fieldInsnNode, new Function1() { // from class: amf
                    public final Object invoke(Object obj) {
                        return WhenMappingTransformer.b((AbstractInsnNode) obj);
                    }
                })) {
                    if (isValues(abstractInsnNode)) {
                        for (AbstractInsnNode abstractInsnNode2 : SequencesKt.generateSequence(fieldInsnNode, new Function1() { // from class: bmf
                            public final Object invoke(Object obj) {
                                return WhenMappingTransformer.a((AbstractInsnNode) obj);
                            }
                        })) {
                            if (isValues(abstractInsnNode2) || abstractInsnNode2.getOpcode() == 177) {
                                int i = node.access;
                                String str = node.name;
                                String str2 = node.desc;
                                String str3 = node.signature;
                                List list = node.exceptions;
                                list.getClass();
                                MethodNode methodNode = new MethodNode(i, str, str2, str3, (String[]) list.toArray(new String[0]));
                                Iterator it = new InsnSequence(abstractInsnNode, abstractInsnNode2).iterator();
                                while (it.hasNext()) {
                                    ((AbstractInsnNode) it.next()).accept(methodNode);
                                }
                                methodNode.visitInsn(177);
                                return methodNode;
                            }
                        }
                        hb9.a("Sequence contains no element matching the predicate.");
                        return null;
                    }
                }
                hb9.a("Sequence contains no element matching the predicate.");
                return null;
            }
        }
        hb9.a("Sequence contains no element matching the predicate.");
        return null;
    }

    private static final CharSequence doTransform$lambda$0$0(MethodNode methodNode) {
        methodNode.getClass();
        String str = methodNode.name;
        str.getClass();
        return str;
    }

    private final boolean isValues(AbstractInsnNode node) {
        if (!(node instanceof MethodInsnNode)) {
            return false;
        }
        MethodInsnNode methodInsnNode = (MethodInsnNode) node;
        if (methodInsnNode.getOpcode() != 184 || !Intrinsics.areEqual(methodInsnNode.name, "values")) {
            return false;
        }
        String str = methodInsnNode.desc;
        StringBuilder sb = new StringBuilder("()[");
        sb.append(Type.getObjectType(methodInsnNode.owner).getDescriptor());
        return Intrinsics.areEqual(str, sb.toString());
    }

    @Override // org.jetbrains.kotlin.codegen.inline.ObjectTransformer
    public InlineResult doTransform(FieldRemapper parentRemapper) {
        parentRemapper.getClass();
        ClassReader classReaderCreateClassReader = createClassReader();
        final ClassBuilder classBuilderCreateRemappingClassBuilderViaFactory = createRemappingClassBuilderViaFactory(this.inliningContext);
        final ArrayList arrayList = new ArrayList();
        final FieldInsnNode fieldNode = ((WhenMappingTransformationInfo) this.transformationInfo).getFieldNode();
        classReaderCreateClassReader.accept(new ClassVisitor(classBuilderCreateRemappingClassBuilderViaFactory.getVisitor()) { // from class: org.jetbrains.kotlin.codegen.inline.WhenMappingTransformer.doTransform.1
            public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
                name.getClass();
                superName.getClass();
                interfaces.getClass();
                classBuilderCreateRemappingClassBuilderViaFactory.defineClass(null, Math.max(version, this.getState().getConfig().getClassFileVersion()), access, name, signature, superName, interfaces);
            }

            public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
                name.getClass();
                desc.getClass();
                if (Intrinsics.areEqual(name, fieldNode.name)) {
                    return classBuilderCreateRemappingClassBuilderViaFactory.newField(JvmDeclarationOrigin.NO_ORIGIN, access, name, desc, signature, value);
                }
                return null;
            }

            public void visitInnerClass(String name, String outerName, String innerName, int access) {
            }

            public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
                name.getClass();
                desc.getClass();
                MethodNode methodNode = new MethodNode(access, name, desc, signature, exceptions);
                arrayList.add(methodNode);
                return methodNode;
            }
        }, 4);
        arrayList.size();
        MethodNode methodNode = (MethodNode) CollectionsKt.first(arrayList);
        Intrinsics.areEqual(methodNode.name, "<clinit>");
        MethodNode methodNodeCutOtherMappings = cutOtherMappings(methodNode);
        JvmDeclarationOrigin jvmDeclarationOrigin = JvmDeclarationOrigin.NO_ORIGIN;
        int i = methodNodeCutOtherMappings.access;
        String str = methodNodeCutOtherMappings.name;
        String str2 = methodNodeCutOtherMappings.desc;
        String str3 = methodNodeCutOtherMappings.signature;
        List list = methodNodeCutOtherMappings.exceptions;
        list.getClass();
        MethodVisitor methodVisitorNewMethod = classBuilderCreateRemappingClassBuilderViaFactory.newMethod(jvmDeclarationOrigin, i, str, str2, str3, (String[]) list.toArray(new String[0]));
        methodVisitorNewMethod.getClass();
        methodNodeCutOtherMappings.accept(methodVisitorNewMethod);
        classBuilderCreateRemappingClassBuilderViaFactory.done(getState().getConfig().getGenerateSmapCopyToAnnotation());
        return this.transformationResult;
    }
}
