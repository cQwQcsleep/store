package com.sun.tools.classfile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class Attribute {
    public static final String AnnotationDefault = "AnnotationDefault";
    public static final String BootstrapMethods = "BootstrapMethods";
    public static final String CharacterRangeTable = "CharacterRangeTable";
    public static final String Code = "Code";
    public static final String CompilationID = "CompilationID";
    public static final String ConstantValue = "ConstantValue";
    public static final String Deprecated = "Deprecated";
    public static final String EnclosingMethod = "EnclosingMethod";
    public static final String Exceptions = "Exceptions";
    public static final String InnerClasses = "InnerClasses";
    public static final String LineNumberTable = "LineNumberTable";
    public static final String LocalVariableTable = "LocalVariableTable";
    public static final String LocalVariableTypeTable = "LocalVariableTypeTable";
    public static final String MethodParameters = "MethodParameters";
    public static final String Module = "Module";
    public static final String ModuleHashes = "ModuleHashes";
    public static final String ModuleMainClass = "ModuleMainClass";
    public static final String ModulePackages = "ModulePackages";
    public static final String ModuleResolution = "ModuleResolution";
    public static final String ModuleTarget = "ModuleTarget";
    public static final String NestHost = "NestHost";
    public static final String NestMembers = "NestMembers";
    public static final String PermittedSubclasses = "PermittedSubclasses";
    public static final String Record = "Record";
    public static final String RuntimeInvisibleAnnotations = "RuntimeInvisibleAnnotations";
    public static final String RuntimeInvisibleParameterAnnotations = "RuntimeInvisibleParameterAnnotations";
    public static final String RuntimeInvisibleTypeAnnotations = "RuntimeInvisibleTypeAnnotations";
    public static final String RuntimeVisibleAnnotations = "RuntimeVisibleAnnotations";
    public static final String RuntimeVisibleParameterAnnotations = "RuntimeVisibleParameterAnnotations";
    public static final String RuntimeVisibleTypeAnnotations = "RuntimeVisibleTypeAnnotations";
    public static final String Signature = "Signature";
    public static final String SourceDebugExtension = "SourceDebugExtension";
    public static final String SourceFile = "SourceFile";
    public static final String SourceID = "SourceID";
    public static final String StackMap = "StackMap";
    public static final String StackMapTable = "StackMapTable";
    public static final String Synthetic = "Synthetic";
    public final int attribute_length;
    public final int attribute_name_index;

    public static class Factory {
        private Map<String, Class<? extends Attribute>> standardAttributes;

        public Attribute createAttribute(ClassReader classReader, int i, byte[] bArr) throws IOException {
            String string;
            if (this.standardAttributes == null) {
                init();
            }
            try {
                Class<? extends Attribute> cls = this.standardAttributes.get(classReader.getConstantPool().getUTF8Value(i));
                if (cls != null) {
                    try {
                        Class cls2 = Integer.TYPE;
                        return cls.getDeclaredConstructor(ClassReader.class, cls2, cls2).newInstance(classReader, Integer.valueOf(i), Integer.valueOf(bArr.length));
                    } catch (Throwable th) {
                        string = th.toString();
                        return new DefaultAttribute(classReader, i, bArr, string);
                    }
                }
                string = "unknown attribute";
            } catch (ConstantPoolException e) {
                string = e.toString();
            }
            return new DefaultAttribute(classReader, i, bArr, string);
        }

        public void init() {
            HashMap map = new HashMap();
            this.standardAttributes = map;
            map.put(Attribute.AnnotationDefault, AnnotationDefault_attribute.class);
            this.standardAttributes.put(Attribute.BootstrapMethods, BootstrapMethods_attribute.class);
            this.standardAttributes.put(Attribute.CharacterRangeTable, CharacterRangeTable_attribute.class);
            this.standardAttributes.put(Attribute.Code, Code_attribute.class);
            this.standardAttributes.put(Attribute.CompilationID, CompilationID_attribute.class);
            this.standardAttributes.put(Attribute.ConstantValue, ConstantValue_attribute.class);
            this.standardAttributes.put(Attribute.Deprecated, Deprecated_attribute.class);
            this.standardAttributes.put(Attribute.EnclosingMethod, EnclosingMethod_attribute.class);
            this.standardAttributes.put(Attribute.Exceptions, Exceptions_attribute.class);
            this.standardAttributes.put(Attribute.InnerClasses, InnerClasses_attribute.class);
            this.standardAttributes.put(Attribute.LineNumberTable, LineNumberTable_attribute.class);
            this.standardAttributes.put(Attribute.LocalVariableTable, LocalVariableTable_attribute.class);
            this.standardAttributes.put(Attribute.LocalVariableTypeTable, LocalVariableTypeTable_attribute.class);
            this.standardAttributes.put(Attribute.MethodParameters, MethodParameters_attribute.class);
            this.standardAttributes.put(Attribute.Module, Module_attribute.class);
            this.standardAttributes.put(Attribute.ModuleHashes, ModuleHashes_attribute.class);
            this.standardAttributes.put(Attribute.ModuleMainClass, ModuleMainClass_attribute.class);
            this.standardAttributes.put(Attribute.ModulePackages, ModulePackages_attribute.class);
            this.standardAttributes.put(Attribute.ModuleResolution, ModuleResolution_attribute.class);
            this.standardAttributes.put(Attribute.ModuleTarget, ModuleTarget_attribute.class);
            this.standardAttributes.put(Attribute.NestHost, NestHost_attribute.class);
            this.standardAttributes.put(Attribute.NestMembers, NestMembers_attribute.class);
            this.standardAttributes.put(Attribute.Record, Record_attribute.class);
            this.standardAttributes.put(Attribute.RuntimeInvisibleAnnotations, RuntimeInvisibleAnnotations_attribute.class);
            this.standardAttributes.put(Attribute.RuntimeInvisibleParameterAnnotations, RuntimeInvisibleParameterAnnotations_attribute.class);
            this.standardAttributes.put(Attribute.RuntimeVisibleAnnotations, RuntimeVisibleAnnotations_attribute.class);
            this.standardAttributes.put(Attribute.RuntimeVisibleParameterAnnotations, RuntimeVisibleParameterAnnotations_attribute.class);
            this.standardAttributes.put(Attribute.RuntimeVisibleTypeAnnotations, RuntimeVisibleTypeAnnotations_attribute.class);
            this.standardAttributes.put(Attribute.RuntimeInvisibleTypeAnnotations, RuntimeInvisibleTypeAnnotations_attribute.class);
            this.standardAttributes.put(Attribute.PermittedSubclasses, PermittedSubclasses_attribute.class);
            this.standardAttributes.put(Attribute.Signature, Signature_attribute.class);
            this.standardAttributes.put(Attribute.SourceDebugExtension, SourceDebugExtension_attribute.class);
            this.standardAttributes.put(Attribute.SourceFile, SourceFile_attribute.class);
            this.standardAttributes.put(Attribute.SourceID, SourceID_attribute.class);
            this.standardAttributes.put(Attribute.StackMap, StackMap_attribute.class);
            this.standardAttributes.put(Attribute.StackMapTable, StackMapTable_attribute.class);
            this.standardAttributes.put(Attribute.Synthetic, Synthetic_attribute.class);
        }
    }

    public interface Visitor<R, P> {
        R visitAnnotationDefault(AnnotationDefault_attribute annotationDefault_attribute, P p);

        R visitBootstrapMethods(BootstrapMethods_attribute bootstrapMethods_attribute, P p);

        R visitCharacterRangeTable(CharacterRangeTable_attribute characterRangeTable_attribute, P p);

        R visitCode(Code_attribute code_attribute, P p);

        R visitCompilationID(CompilationID_attribute compilationID_attribute, P p);

        R visitConstantValue(ConstantValue_attribute constantValue_attribute, P p);

        R visitDefault(DefaultAttribute defaultAttribute, P p);

        R visitDeprecated(Deprecated_attribute deprecated_attribute, P p);

        R visitEnclosingMethod(EnclosingMethod_attribute enclosingMethod_attribute, P p);

        R visitExceptions(Exceptions_attribute exceptions_attribute, P p);

        R visitInnerClasses(InnerClasses_attribute innerClasses_attribute, P p);

        R visitLineNumberTable(LineNumberTable_attribute lineNumberTable_attribute, P p);

        R visitLocalVariableTable(LocalVariableTable_attribute localVariableTable_attribute, P p);

        R visitLocalVariableTypeTable(LocalVariableTypeTable_attribute localVariableTypeTable_attribute, P p);

        R visitMethodParameters(MethodParameters_attribute methodParameters_attribute, P p);

        R visitModule(Module_attribute module_attribute, P p);

        R visitModuleHashes(ModuleHashes_attribute moduleHashes_attribute, P p);

        R visitModuleMainClass(ModuleMainClass_attribute moduleMainClass_attribute, P p);

        R visitModulePackages(ModulePackages_attribute modulePackages_attribute, P p);

        R visitModuleResolution(ModuleResolution_attribute moduleResolution_attribute, P p);

        R visitModuleTarget(ModuleTarget_attribute moduleTarget_attribute, P p);

        R visitNestHost(NestHost_attribute nestHost_attribute, P p);

        R visitNestMembers(NestMembers_attribute nestMembers_attribute, P p);

        R visitPermittedSubclasses(PermittedSubclasses_attribute permittedSubclasses_attribute, P p);

        R visitRecord(Record_attribute record_attribute, P p);

        R visitRuntimeInvisibleAnnotations(RuntimeInvisibleAnnotations_attribute runtimeInvisibleAnnotations_attribute, P p);

        R visitRuntimeInvisibleParameterAnnotations(RuntimeInvisibleParameterAnnotations_attribute runtimeInvisibleParameterAnnotations_attribute, P p);

        R visitRuntimeInvisibleTypeAnnotations(RuntimeInvisibleTypeAnnotations_attribute runtimeInvisibleTypeAnnotations_attribute, P p);

        R visitRuntimeVisibleAnnotations(RuntimeVisibleAnnotations_attribute runtimeVisibleAnnotations_attribute, P p);

        R visitRuntimeVisibleParameterAnnotations(RuntimeVisibleParameterAnnotations_attribute runtimeVisibleParameterAnnotations_attribute, P p);

        R visitRuntimeVisibleTypeAnnotations(RuntimeVisibleTypeAnnotations_attribute runtimeVisibleTypeAnnotations_attribute, P p);

        R visitSignature(Signature_attribute signature_attribute, P p);

        R visitSourceDebugExtension(SourceDebugExtension_attribute sourceDebugExtension_attribute, P p);

        R visitSourceFile(SourceFile_attribute sourceFile_attribute, P p);

        R visitSourceID(SourceID_attribute sourceID_attribute, P p);

        R visitStackMap(StackMap_attribute stackMap_attribute, P p);

        R visitStackMapTable(StackMapTable_attribute stackMapTable_attribute, P p);

        R visitSynthetic(Synthetic_attribute synthetic_attribute, P p);
    }

    public Attribute(int i, int i2) {
        this.attribute_name_index = i;
        this.attribute_length = i2;
    }

    public static Attribute read(ClassReader classReader) throws IOException {
        return classReader.readAttribute();
    }

    public abstract <R, D> R accept(Visitor<R, D> visitor, D d);

    public int byteLength() {
        return this.attribute_length + 6;
    }

    public String getName(ConstantPool constantPool) throws ConstantPoolException {
        return constantPool.getUTF8Value(this.attribute_name_index);
    }
}
