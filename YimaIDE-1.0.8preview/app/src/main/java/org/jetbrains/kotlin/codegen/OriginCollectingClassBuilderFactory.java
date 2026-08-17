package org.jetbrains.kotlin.codegen;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.resolve.jvm.diagnostics.JvmDeclarationOrigin;
import org.jetbrains.org.objectweb.asm.ClassWriter;
import org.jetbrains.org.objectweb.asm.FieldVisitor;
import org.jetbrains.org.objectweb.asm.MethodVisitor;
import org.jetbrains.org.objectweb.asm.tree.ClassNode;
import org.jetbrains.org.objectweb.asm.tree.FieldNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u001bB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0011\u001a\u00020\u0003H\u0016J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000eH\u0016J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0017\u001a\u00020\u0018H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001d\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/codegen/OriginCollectingClassBuilderFactory;", "Lorg/jetbrains/kotlin/codegen/ClassBuilderFactory;", "builderMode", "Lorg/jetbrains/kotlin/codegen/ClassBuilderMode;", "<init>", "(Lorg/jetbrains/kotlin/codegen/ClassBuilderMode;)V", "compiledClasses", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/tree/ClassNode;", "getCompiledClasses", "()Ljava/util/List;", "origins", Argument.Delimiters.none, Argument.Delimiters.none, "Lorg/jetbrains/kotlin/resolve/jvm/diagnostics/JvmDeclarationOrigin;", "getOrigins", "()Ljava/util/Map;", "getClassBuilderMode", "newClassBuilder", "Lorg/jetbrains/kotlin/codegen/AbstractClassBuilder$Concrete;", "origin", "asBytes", Argument.Delimiters.none, "builder", "Lorg/jetbrains/kotlin/codegen/ClassBuilder;", "asText", Argument.Delimiters.none, "OriginCollectingClassBuilder", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class OriginCollectingClassBuilderFactory implements ClassBuilderFactory {
    private final ClassBuilderMode builderMode;
    private final List<ClassNode> compiledClasses;
    private final Map<Object, JvmDeclarationOrigin> origins;

    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J<\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016JI\u0010\u0014\u001a\u00020\u00152\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u000f2\u0010\u0010\u0016\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u000f\u0018\u00010\u0017H\u0016¢\u0006\u0002\u0010\u0018R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/codegen/OriginCollectingClassBuilderFactory$OriginCollectingClassBuilder;", "Lorg/jetbrains/kotlin/codegen/AbstractClassBuilder$Concrete;", "classNode", "Lorg/jetbrains/org/objectweb/asm/tree/ClassNode;", "<init>", "(Lorg/jetbrains/kotlin/codegen/OriginCollectingClassBuilderFactory;Lorg/jetbrains/org/objectweb/asm/tree/ClassNode;)V", "getClassNode", "()Lorg/jetbrains/org/objectweb/asm/tree/ClassNode;", "newField", "Lorg/jetbrains/org/objectweb/asm/FieldVisitor;", "origin", "Lorg/jetbrains/kotlin/resolve/jvm/diagnostics/JvmDeclarationOrigin;", "access", Argument.Delimiters.none, ModuleXmlParser.NAME, Argument.Delimiters.none, "desc", "signature", "value", Argument.Delimiters.none, "newMethod", "Lorg/jetbrains/org/objectweb/asm/MethodVisitor;", "exceptions", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/resolve/jvm/diagnostics/JvmDeclarationOrigin;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Lorg/jetbrains/org/objectweb/asm/MethodVisitor;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public final class OriginCollectingClassBuilder extends AbstractClassBuilder.Concrete {
        private final ClassNode classNode;
        final /* synthetic */ OriginCollectingClassBuilderFactory this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public OriginCollectingClassBuilder(OriginCollectingClassBuilderFactory originCollectingClassBuilderFactory, ClassNode classNode) {
            super(classNode);
            classNode.getClass();
            this.this$0 = originCollectingClassBuilderFactory;
            this.classNode = classNode;
        }

        public final ClassNode getClassNode() {
            return this.classNode;
        }

        @Override // org.jetbrains.kotlin.codegen.AbstractClassBuilder, org.jetbrains.kotlin.codegen.ClassBuilder
        public FieldVisitor newField(JvmDeclarationOrigin origin, int access, String name, String desc, String signature, Object value) {
            origin.getClass();
            name.getClass();
            desc.getClass();
            FieldNode fieldNodeNewField = super.newField(origin, access, name, desc, signature, value);
            fieldNodeNewField.getClass();
            FieldNode fieldNode = fieldNodeNewField;
            this.this$0.getOrigins().put(fieldNode, origin);
            return fieldNode;
        }

        @Override // org.jetbrains.kotlin.codegen.AbstractClassBuilder, org.jetbrains.kotlin.codegen.ClassBuilder
        public MethodVisitor newMethod(JvmDeclarationOrigin origin, int access, String name, String desc, String signature, String[] exceptions) {
            origin.getClass();
            name.getClass();
            desc.getClass();
            MethodNode methodNodeNewMethod = super.newMethod(origin, access, name, desc, signature, exceptions);
            methodNodeNewMethod.getClass();
            MethodNode methodNode = methodNodeNewMethod;
            this.this$0.getOrigins().put(methodNode, origin);
            if ((access & 1024) != 0 && methodNode.localVariables == null) {
                methodNode.localVariables = new ArrayList();
            }
            return methodNode;
        }
    }

    public OriginCollectingClassBuilderFactory(ClassBuilderMode classBuilderMode) {
        classBuilderMode.getClass();
        this.builderMode = classBuilderMode;
        this.compiledClasses = new ArrayList();
        this.origins = new LinkedHashMap();
    }

    @Override // org.jetbrains.kotlin.codegen.ClassBuilderFactory
    public byte[] asBytes(ClassBuilder builder) {
        builder.getClass();
        ClassWriter classWriter = new ClassWriter(3);
        ((OriginCollectingClassBuilder) builder).getClassNode().accept(classWriter);
        byte[] byteArray = classWriter.toByteArray();
        byteArray.getClass();
        return byteArray;
    }

    /* JADX INFO: renamed from: asText, reason: collision with other method in class */
    public Void m51asText(ClassBuilder builder) {
        builder.getClass();
        throw new UnsupportedOperationException();
    }

    @Override // org.jetbrains.kotlin.codegen.ClassBuilderFactory
    /* JADX INFO: renamed from: getClassBuilderMode, reason: from getter */
    public ClassBuilderMode getBuilderMode() {
        return this.builderMode;
    }

    public final List<ClassNode> getCompiledClasses() {
        return this.compiledClasses;
    }

    public final Map<Object, JvmDeclarationOrigin> getOrigins() {
        return this.origins;
    }

    @Override // org.jetbrains.kotlin.codegen.ClassBuilderFactory
    public AbstractClassBuilder.Concrete newClassBuilder(JvmDeclarationOrigin origin) {
        origin.getClass();
        ClassNode classNode = new ClassNode();
        this.compiledClasses.add(classNode);
        this.origins.put(classNode, origin);
        return new OriginCollectingClassBuilder(this, classNode);
    }

    @Override // org.jetbrains.kotlin.codegen.ClassBuilderFactory
    public /* bridge */ /* synthetic */ String asText(ClassBuilder classBuilder) {
        return (String) m51asText(classBuilder);
    }
}
