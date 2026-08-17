package com.sun.org.apache.bcel.internal.classfile;

import com.sun.org.apache.bcel.internal.classfile.Constant;
import com.sun.org.apache.bcel.internal.classfile.DescendingVisitor;
import com.sun.org.apache.bcel.internal.classfile.MethodParameter;
import com.sun.org.apache.bcel.internal.classfile.Node;
import java.util.Objects;
import java.util.Stack;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DescendingVisitor implements Visitor {
    private final JavaClass clazz;
    private final Stack<Object> stack = new Stack<>();
    private final Visitor visitor;

    public DescendingVisitor(JavaClass javaClass, Visitor visitor) {
        this.clazz = javaClass;
        this.visitor = visitor;
    }

    public static /* synthetic */ void a(DescendingVisitor descendingVisitor, Node node) {
        descendingVisitor.getClass();
        node.accept(descendingVisitor);
    }

    private <E extends Node> void accept(E[] eArr) {
        Stream.of((Object[]) eArr).forEach(new Consumer() { // from class: cm3
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                DescendingVisitor.a(this.b, (Node) obj);
            }
        });
    }

    public static /* synthetic */ void b(DescendingVisitor descendingVisitor, Constant constant) {
        descendingVisitor.getClass();
        constant.accept(descendingVisitor);
    }

    public static /* synthetic */ void c(DescendingVisitor descendingVisitor, MethodParameter methodParameter) {
        descendingVisitor.getClass();
        methodParameter.accept(descendingVisitor);
    }

    public Object current() {
        return this.stack.peek();
    }

    public Object predecessor(int i) {
        int size = this.stack.size();
        if (size < 2 || i < 0) {
            return null;
        }
        return this.stack.elementAt(size - (i + 2));
    }

    public void visit() {
        this.clazz.accept(this);
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitAnnotation(Annotations annotations) {
        this.stack.push(annotations);
        annotations.accept(this.visitor);
        accept(annotations.getAnnotationEntries());
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitAnnotationDefault(AnnotationDefault annotationDefault) {
        this.stack.push(annotationDefault);
        annotationDefault.accept(this.visitor);
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitAnnotationEntry(AnnotationEntry annotationEntry) {
        this.stack.push(annotationEntry);
        annotationEntry.accept(this.visitor);
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitBootstrapMethods(BootstrapMethods bootstrapMethods) {
        this.stack.push(bootstrapMethods);
        bootstrapMethods.accept(this.visitor);
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitCode(Code code) {
        this.stack.push(code);
        code.accept(this.visitor);
        accept(code.getExceptionTable());
        accept(code.getAttributes());
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitCodeException(CodeException codeException) {
        this.stack.push(codeException);
        codeException.accept(this.visitor);
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitConstantClass(ConstantClass constantClass) {
        this.stack.push(constantClass);
        constantClass.accept(this.visitor);
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitConstantDouble(ConstantDouble constantDouble) {
        this.stack.push(constantDouble);
        constantDouble.accept(this.visitor);
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitConstantDynamic(ConstantDynamic constantDynamic) {
        this.stack.push(constantDynamic);
        constantDynamic.accept(this.visitor);
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitConstantFieldref(ConstantFieldref constantFieldref) {
        this.stack.push(constantFieldref);
        constantFieldref.accept(this.visitor);
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitConstantFloat(ConstantFloat constantFloat) {
        this.stack.push(constantFloat);
        constantFloat.accept(this.visitor);
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitConstantInteger(ConstantInteger constantInteger) {
        this.stack.push(constantInteger);
        constantInteger.accept(this.visitor);
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitConstantInterfaceMethodref(ConstantInterfaceMethodref constantInterfaceMethodref) {
        this.stack.push(constantInterfaceMethodref);
        constantInterfaceMethodref.accept(this.visitor);
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitConstantInvokeDynamic(ConstantInvokeDynamic constantInvokeDynamic) {
        this.stack.push(constantInvokeDynamic);
        constantInvokeDynamic.accept(this.visitor);
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitConstantLong(ConstantLong constantLong) {
        this.stack.push(constantLong);
        constantLong.accept(this.visitor);
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitConstantMethodHandle(ConstantMethodHandle constantMethodHandle) {
        this.stack.push(constantMethodHandle);
        constantMethodHandle.accept(this.visitor);
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitConstantMethodType(ConstantMethodType constantMethodType) {
        this.stack.push(constantMethodType);
        constantMethodType.accept(this.visitor);
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitConstantMethodref(ConstantMethodref constantMethodref) {
        this.stack.push(constantMethodref);
        constantMethodref.accept(this.visitor);
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitConstantModule(ConstantModule constantModule) {
        this.stack.push(constantModule);
        constantModule.accept(this.visitor);
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitConstantNameAndType(ConstantNameAndType constantNameAndType) {
        this.stack.push(constantNameAndType);
        constantNameAndType.accept(this.visitor);
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitConstantPackage(ConstantPackage constantPackage) {
        this.stack.push(constantPackage);
        constantPackage.accept(this.visitor);
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitConstantPool(ConstantPool constantPool) {
        this.stack.push(constantPool);
        constantPool.accept(this.visitor);
        Stream.of((Object[]) constantPool.getConstantPool()).filter(new Predicate() { // from class: am3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Objects.nonNull((Constant) obj);
            }
        }).forEach(new Consumer() { // from class: bm3
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                DescendingVisitor.b(this.b, (Constant) obj);
            }
        });
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitConstantString(ConstantString constantString) {
        this.stack.push(constantString);
        constantString.accept(this.visitor);
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitConstantUtf8(ConstantUtf8 constantUtf8) {
        this.stack.push(constantUtf8);
        constantUtf8.accept(this.visitor);
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitConstantValue(ConstantValue constantValue) {
        this.stack.push(constantValue);
        constantValue.accept(this.visitor);
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitDeprecated(Deprecated deprecated) {
        this.stack.push(deprecated);
        deprecated.accept(this.visitor);
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitEnclosingMethod(EnclosingMethod enclosingMethod) {
        this.stack.push(enclosingMethod);
        enclosingMethod.accept(this.visitor);
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitExceptionTable(ExceptionTable exceptionTable) {
        this.stack.push(exceptionTable);
        exceptionTable.accept(this.visitor);
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitField(Field field) {
        this.stack.push(field);
        field.accept(this.visitor);
        accept(field.getAttributes());
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitInnerClass(InnerClass innerClass) {
        this.stack.push(innerClass);
        innerClass.accept(this.visitor);
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitInnerClasses(InnerClasses innerClasses) {
        this.stack.push(innerClasses);
        innerClasses.accept(this.visitor);
        accept(innerClasses.getInnerClasses());
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitJavaClass(JavaClass javaClass) {
        this.stack.push(javaClass);
        javaClass.accept(this.visitor);
        accept(javaClass.getFields());
        accept(javaClass.getMethods());
        accept(javaClass.getAttributes());
        javaClass.getConstantPool().accept(this);
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitLineNumber(LineNumber lineNumber) {
        this.stack.push(lineNumber);
        lineNumber.accept(this.visitor);
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitLineNumberTable(LineNumberTable lineNumberTable) {
        this.stack.push(lineNumberTable);
        lineNumberTable.accept(this.visitor);
        accept(lineNumberTable.getLineNumberTable());
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitLocalVariable(LocalVariable localVariable) {
        this.stack.push(localVariable);
        localVariable.accept(this.visitor);
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitLocalVariableTable(LocalVariableTable localVariableTable) {
        this.stack.push(localVariableTable);
        localVariableTable.accept(this.visitor);
        accept(localVariableTable.getLocalVariableTable());
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitLocalVariableTypeTable(LocalVariableTypeTable localVariableTypeTable) {
        this.stack.push(localVariableTypeTable);
        localVariableTypeTable.accept(this.visitor);
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitMethod(Method method) {
        this.stack.push(method);
        method.accept(this.visitor);
        accept(method.getAttributes());
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitMethodParameter(MethodParameter methodParameter) {
        this.stack.push(methodParameter);
        methodParameter.accept(this.visitor);
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitMethodParameters(MethodParameters methodParameters) {
        this.stack.push(methodParameters);
        methodParameters.accept(this.visitor);
        Stream.of((Object[]) methodParameters.getParameters()).forEach(new Consumer() { // from class: zl3
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                DescendingVisitor.c(this.b, (MethodParameter) obj);
            }
        });
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitModule(Module module) {
        this.stack.push(module);
        module.accept(this.visitor);
        accept(module.getRequiresTable());
        accept(module.getExportsTable());
        accept(module.getOpensTable());
        accept(module.getProvidesTable());
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitModuleExports(ModuleExports moduleExports) {
        this.stack.push(moduleExports);
        moduleExports.accept(this.visitor);
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitModuleMainClass(ModuleMainClass moduleMainClass) {
        this.stack.push(moduleMainClass);
        moduleMainClass.accept(this.visitor);
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitModuleOpens(ModuleOpens moduleOpens) {
        this.stack.push(moduleOpens);
        moduleOpens.accept(this.visitor);
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitModulePackages(ModulePackages modulePackages) {
        this.stack.push(modulePackages);
        modulePackages.accept(this.visitor);
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitModuleProvides(ModuleProvides moduleProvides) {
        this.stack.push(moduleProvides);
        moduleProvides.accept(this.visitor);
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitModuleRequires(ModuleRequires moduleRequires) {
        this.stack.push(moduleRequires);
        moduleRequires.accept(this.visitor);
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitNestHost(NestHost nestHost) {
        this.stack.push(nestHost);
        nestHost.accept(this.visitor);
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitNestMembers(NestMembers nestMembers) {
        this.stack.push(nestMembers);
        nestMembers.accept(this.visitor);
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitParameterAnnotation(ParameterAnnotations parameterAnnotations) {
        this.stack.push(parameterAnnotations);
        parameterAnnotations.accept(this.visitor);
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitParameterAnnotationEntry(ParameterAnnotationEntry parameterAnnotationEntry) {
        this.stack.push(parameterAnnotationEntry);
        parameterAnnotationEntry.accept(this.visitor);
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitSignature(Signature signature) {
        this.stack.push(signature);
        signature.accept(this.visitor);
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitSourceFile(SourceFile sourceFile) {
        this.stack.push(sourceFile);
        sourceFile.accept(this.visitor);
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitStackMap(StackMap stackMap) {
        this.stack.push(stackMap);
        stackMap.accept(this.visitor);
        accept(stackMap.getStackMap());
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitStackMapEntry(StackMapEntry stackMapEntry) {
        this.stack.push(stackMapEntry);
        stackMapEntry.accept(this.visitor);
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitSynthetic(Synthetic synthetic) {
        this.stack.push(synthetic);
        synthetic.accept(this.visitor);
        this.stack.pop();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Visitor
    public void visitUnknown(Unknown unknown) {
        this.stack.push(unknown);
        unknown.accept(this.visitor);
        this.stack.pop();
    }

    public Object predecessor() {
        return predecessor(0);
    }
}
