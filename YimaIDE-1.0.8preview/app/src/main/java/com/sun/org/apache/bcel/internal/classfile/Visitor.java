package com.sun.org.apache.bcel.internal.classfile;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface Visitor {
    void visitAnnotation(Annotations annotations);

    void visitAnnotationDefault(AnnotationDefault annotationDefault);

    void visitAnnotationEntry(AnnotationEntry annotationEntry);

    void visitBootstrapMethods(BootstrapMethods bootstrapMethods);

    void visitCode(Code code);

    void visitCodeException(CodeException codeException);

    void visitConstantClass(ConstantClass constantClass);

    void visitConstantDouble(ConstantDouble constantDouble);

    default void visitConstantDynamic(ConstantDynamic constantDynamic) {
    }

    void visitConstantFieldref(ConstantFieldref constantFieldref);

    void visitConstantFloat(ConstantFloat constantFloat);

    void visitConstantInteger(ConstantInteger constantInteger);

    void visitConstantInterfaceMethodref(ConstantInterfaceMethodref constantInterfaceMethodref);

    void visitConstantInvokeDynamic(ConstantInvokeDynamic constantInvokeDynamic);

    void visitConstantLong(ConstantLong constantLong);

    void visitConstantMethodHandle(ConstantMethodHandle constantMethodHandle);

    void visitConstantMethodType(ConstantMethodType constantMethodType);

    void visitConstantMethodref(ConstantMethodref constantMethodref);

    void visitConstantModule(ConstantModule constantModule);

    void visitConstantNameAndType(ConstantNameAndType constantNameAndType);

    void visitConstantPackage(ConstantPackage constantPackage);

    void visitConstantPool(ConstantPool constantPool);

    void visitConstantString(ConstantString constantString);

    void visitConstantUtf8(ConstantUtf8 constantUtf8);

    void visitConstantValue(ConstantValue constantValue);

    void visitDeprecated(Deprecated deprecated);

    void visitEnclosingMethod(EnclosingMethod enclosingMethod);

    void visitExceptionTable(ExceptionTable exceptionTable);

    void visitField(Field field);

    void visitInnerClass(InnerClass innerClass);

    void visitInnerClasses(InnerClasses innerClasses);

    void visitJavaClass(JavaClass javaClass);

    void visitLineNumber(LineNumber lineNumber);

    void visitLineNumberTable(LineNumberTable lineNumberTable);

    void visitLocalVariable(LocalVariable localVariable);

    void visitLocalVariableTable(LocalVariableTable localVariableTable);

    void visitLocalVariableTypeTable(LocalVariableTypeTable localVariableTypeTable);

    void visitMethod(Method method);

    default void visitMethodParameter(MethodParameter methodParameter) {
    }

    void visitMethodParameters(MethodParameters methodParameters);

    default void visitModule(Module module) {
    }

    default void visitModuleExports(ModuleExports moduleExports) {
    }

    default void visitModuleMainClass(ModuleMainClass moduleMainClass) {
    }

    default void visitModuleOpens(ModuleOpens moduleOpens) {
    }

    default void visitModulePackages(ModulePackages modulePackages) {
    }

    default void visitModuleProvides(ModuleProvides moduleProvides) {
    }

    default void visitModuleRequires(ModuleRequires moduleRequires) {
    }

    default void visitNestHost(NestHost nestHost) {
    }

    default void visitNestMembers(NestMembers nestMembers) {
    }

    void visitParameterAnnotation(ParameterAnnotations parameterAnnotations);

    void visitParameterAnnotationEntry(ParameterAnnotationEntry parameterAnnotationEntry);

    void visitSignature(Signature signature);

    void visitSourceFile(SourceFile sourceFile);

    void visitStackMap(StackMap stackMap);

    void visitStackMapEntry(StackMapEntry stackMapEntry);

    void visitSynthetic(Synthetic synthetic);

    void visitUnknown(Unknown unknown);
}
