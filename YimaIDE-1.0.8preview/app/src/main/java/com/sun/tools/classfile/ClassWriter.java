package com.sun.tools.classfile;

import defpackage.s22;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ClassWriter {
    protected ClassFile classFile;
    protected AttributeWriter attributeWriter = new AttributeWriter();
    protected ConstantPoolWriter constantPoolWriter = new ConstantPoolWriter();
    protected ClassOutputStream out = new ClassOutputStream();

    /* JADX INFO: renamed from: com.sun.tools.classfile.ClassWriter$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType;

        static {
            int[] iArr = new int[TypeAnnotation.TargetType.values().length];
            $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType = iArr;
            try {
                iArr[TypeAnnotation.TargetType.INSTANCEOF.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TypeAnnotation.TargetType.NEW.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TypeAnnotation.TargetType.CONSTRUCTOR_REFERENCE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TypeAnnotation.TargetType.METHOD_REFERENCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TypeAnnotation.TargetType.LOCAL_VARIABLE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TypeAnnotation.TargetType.RESOURCE_VARIABLE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TypeAnnotation.TargetType.EXCEPTION_PARAMETER.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TypeAnnotation.TargetType.METHOD_RECEIVER.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TypeAnnotation.TargetType.CLASS_TYPE_PARAMETER.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TypeAnnotation.TargetType.METHOD_TYPE_PARAMETER.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TypeAnnotation.TargetType.CLASS_TYPE_PARAMETER_BOUND.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TypeAnnotation.TargetType.METHOD_TYPE_PARAMETER_BOUND.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TypeAnnotation.TargetType.CLASS_EXTENDS.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TypeAnnotation.TargetType.THROWS.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TypeAnnotation.TargetType.METHOD_FORMAL_PARAMETER.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TypeAnnotation.TargetType.CAST.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TypeAnnotation.TargetType.CONSTRUCTOR_INVOCATION_TYPE_ARGUMENT.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TypeAnnotation.TargetType.METHOD_INVOCATION_TYPE_ARGUMENT.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TypeAnnotation.TargetType.CONSTRUCTOR_REFERENCE_TYPE_ARGUMENT.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TypeAnnotation.TargetType.METHOD_REFERENCE_TYPE_ARGUMENT.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TypeAnnotation.TargetType.METHOD_RETURN.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TypeAnnotation.TargetType.FIELD.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TypeAnnotation.TargetType.UNKNOWN.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
        }
    }

    public static class ClassOutputStream extends ByteArrayOutputStream {
        private final DataOutputStream d = new DataOutputStream(this);

        public void writeByte(int i) {
            try {
                this.d.writeByte(i);
            } catch (IOException unused) {
            }
        }

        public void writeDouble(double d) {
            try {
                this.d.writeDouble(d);
            } catch (IOException unused) {
            }
        }

        public void writeFloat(float f) {
            try {
                this.d.writeFloat(f);
            } catch (IOException unused) {
            }
        }

        public void writeInt(int i) {
            try {
                this.d.writeInt(i);
            } catch (IOException unused) {
            }
        }

        public void writeLong(long j) {
            try {
                this.d.writeLong(j);
            } catch (IOException unused) {
            }
        }

        public void writeShort(int i) {
            try {
                this.d.writeShort(i);
            } catch (IOException unused) {
            }
        }

        public void writeTo(ClassOutputStream classOutputStream) {
            try {
                super.writeTo((OutputStream) classOutputStream);
            } catch (IOException unused) {
            }
        }

        public void writeUTF(String str) {
            try {
                this.d.writeUTF(str);
            } catch (IOException unused) {
            }
        }
    }

    public void write() throws IOException {
        writeHeader();
        writeConstantPool();
        writeAccessFlags(this.classFile.access_flags);
        writeClassInfo();
        writeFields();
        writeMethods();
        writeAttributes(this.classFile.attributes);
    }

    public void writeAccessFlags(AccessFlags accessFlags) {
        this.out.writeShort(accessFlags.flags);
    }

    public void writeAttributes(Attributes attributes) {
        this.out.writeShort(attributes.size());
        Iterator<Attribute> it = attributes.iterator();
        while (it.hasNext()) {
            this.attributeWriter.write(it.next(), this.out);
        }
    }

    public void writeClassInfo() {
        this.out.writeShort(this.classFile.this_class);
        this.out.writeShort(this.classFile.super_class);
        int[] iArr = this.classFile.interfaces;
        this.out.writeShort(iArr.length);
        for (int i : iArr) {
            this.out.writeShort(i);
        }
    }

    public void writeConstantPool() {
        ConstantPool constantPool = this.classFile.constant_pool;
        this.out.writeShort(constantPool.size());
        Iterator<ConstantPool.CPInfo> it = constantPool.entries().iterator();
        while (it.hasNext()) {
            this.constantPoolWriter.write(it.next(), this.out);
        }
    }

    public void writeDescriptor(Descriptor descriptor) {
        this.out.writeShort(descriptor.index);
    }

    public void writeField(Field field) throws IOException {
        writeAccessFlags(field.access_flags);
        this.out.writeShort(field.name_index);
        writeDescriptor(field.descriptor);
        writeAttributes(field.attributes);
    }

    public void writeFields() throws IOException {
        Field[] fieldArr = this.classFile.fields;
        this.out.writeShort(fieldArr.length);
        for (Field field : fieldArr) {
            writeField(field);
        }
    }

    public void writeHeader() {
        this.out.writeInt(this.classFile.magic);
        this.out.writeShort(this.classFile.minor_version);
        this.out.writeShort(this.classFile.major_version);
    }

    public void writeMethod(Method method) throws IOException {
        writeAccessFlags(method.access_flags);
        this.out.writeShort(method.name_index);
        writeDescriptor(method.descriptor);
        writeAttributes(method.attributes);
    }

    public void writeMethods() throws IOException {
        Method[] methodArr = this.classFile.methods;
        this.out.writeShort(methodArr.length);
        for (Method method : methodArr) {
            writeMethod(method);
        }
    }

    public static class AnnotationWriter implements Annotation.element_value.Visitor<Void, ClassOutputStream> {
        private void write(TypeAnnotation.Position position, ClassOutputStream classOutputStream) {
            classOutputStream.writeByte(position.type.targetTypeValue());
            switch (AnonymousClass1.$SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[position.type.ordinal()]) {
                case 1:
                case 2:
                case 3:
                case 4:
                    classOutputStream.writeShort(position.offset);
                    break;
                case 5:
                case 6:
                    int length = position.lvarOffset.length;
                    classOutputStream.writeShort(length);
                    for (int i = 0; i < length; i++) {
                        classOutputStream.writeShort(1);
                        classOutputStream.writeShort(position.lvarOffset[i]);
                        classOutputStream.writeShort(position.lvarLength[i]);
                        classOutputStream.writeShort(position.lvarIndex[i]);
                    }
                    break;
                case 7:
                    classOutputStream.writeShort(position.exception_index);
                    break;
                case 8:
                case 21:
                case 22:
                    break;
                case 9:
                case 10:
                    classOutputStream.writeByte(position.parameter_index);
                    break;
                case 11:
                case 12:
                    classOutputStream.writeByte(position.parameter_index);
                    classOutputStream.writeByte(position.bound_index);
                    break;
                case 13:
                    classOutputStream.writeShort(position.type_index);
                    break;
                case 14:
                    classOutputStream.writeShort(position.type_index);
                    break;
                case 15:
                    classOutputStream.writeByte(position.parameter_index);
                    break;
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                    classOutputStream.writeShort(position.offset);
                    classOutputStream.writeByte(position.type_index);
                    break;
                case 23:
                    x01.a("ClassWriter: UNKNOWN target type should never occur!");
                    return;
                default:
                    s22.a("ClassWriter: Unknown target type for position: ", position);
                    return;
            }
            classOutputStream.writeByte((byte) position.location.size());
            Iterator<Integer> it = TypeAnnotation.Position.getBinaryFromTypePath(position.location).iterator();
            while (it.hasNext()) {
                classOutputStream.writeByte((byte) it.next().intValue());
            }
        }

        @Override // com.sun.tools.classfile.Annotation.element_value.Visitor
        public Void visitArray(Annotation.Array_element_value array_element_value, ClassOutputStream classOutputStream) {
            classOutputStream.writeShort(array_element_value.num_values);
            for (Annotation.element_value element_valueVar : array_element_value.values) {
                write(element_valueVar, classOutputStream);
            }
            return null;
        }

        @Override // com.sun.tools.classfile.Annotation.element_value.Visitor
        public Void visitEnum(Annotation.Enum_element_value enum_element_value, ClassOutputStream classOutputStream) {
            classOutputStream.writeShort(enum_element_value.type_name_index);
            classOutputStream.writeShort(enum_element_value.const_name_index);
            return null;
        }

        @Override // com.sun.tools.classfile.Annotation.element_value.Visitor
        public Void visitAnnotation(Annotation.Annotation_element_value annotation_element_value, ClassOutputStream classOutputStream) {
            write(annotation_element_value.annotation_value, classOutputStream);
            return null;
        }

        @Override // com.sun.tools.classfile.Annotation.element_value.Visitor
        public Void visitClass(Annotation.Class_element_value class_element_value, ClassOutputStream classOutputStream) {
            classOutputStream.writeShort(class_element_value.class_info_index);
            return null;
        }

        @Override // com.sun.tools.classfile.Annotation.element_value.Visitor
        public Void visitPrimitive(Annotation.Primitive_element_value primitive_element_value, ClassOutputStream classOutputStream) {
            classOutputStream.writeShort(primitive_element_value.const_value_index);
            return null;
        }

        public void write(TypeAnnotation[] typeAnnotationArr, ClassOutputStream classOutputStream) {
            classOutputStream.writeShort(typeAnnotationArr.length);
            for (TypeAnnotation typeAnnotation : typeAnnotationArr) {
                write(typeAnnotation, classOutputStream);
            }
        }

        public void write(Annotation annotation, ClassOutputStream classOutputStream) {
            classOutputStream.writeShort(annotation.type_index);
            classOutputStream.writeShort(annotation.element_value_pairs.length);
            for (Annotation.element_value_pair element_value_pairVar : annotation.element_value_pairs) {
                write(element_value_pairVar, classOutputStream);
            }
        }

        public void write(TypeAnnotation typeAnnotation, ClassOutputStream classOutputStream) {
            write(typeAnnotation.position, classOutputStream);
            write(typeAnnotation.annotation, classOutputStream);
        }

        public void write(Annotation.element_value_pair element_value_pairVar, ClassOutputStream classOutputStream) {
            classOutputStream.writeShort(element_value_pairVar.element_name_index);
            write(element_value_pairVar.value, classOutputStream);
        }

        public void write(Annotation.element_value element_valueVar, ClassOutputStream classOutputStream) {
            classOutputStream.writeByte(element_valueVar.tag);
            element_valueVar.accept(this, classOutputStream);
        }

        public void write(Annotation[] annotationArr, ClassOutputStream classOutputStream) {
            classOutputStream.writeShort(annotationArr.length);
            for (Annotation annotation : annotationArr) {
                write(annotation, classOutputStream);
            }
        }
    }

    public static class AttributeWriter implements Attribute.Visitor<Void, ClassOutputStream> {
        protected AnnotationWriter annotationWriter = new AnnotationWriter();
        protected StackMapTableWriter stackMapWriter;

        @Override // com.sun.tools.classfile.Attribute.Visitor
        public Void visitAnnotationDefault(AnnotationDefault_attribute annotationDefault_attribute, ClassOutputStream classOutputStream) {
            this.annotationWriter.write(annotationDefault_attribute.default_value, classOutputStream);
            return null;
        }

        @Override // com.sun.tools.classfile.Attribute.Visitor
        public Void visitBootstrapMethods(BootstrapMethods_attribute bootstrapMethods_attribute, ClassOutputStream classOutputStream) {
            classOutputStream.writeShort(bootstrapMethods_attribute.bootstrap_method_specifiers.length);
            for (BootstrapMethods_attribute.BootstrapMethodSpecifier bootstrapMethodSpecifier : bootstrapMethods_attribute.bootstrap_method_specifiers) {
                classOutputStream.writeShort(bootstrapMethodSpecifier.bootstrap_method_ref);
                classOutputStream.writeShort(bootstrapMethodSpecifier.bootstrap_arguments.length);
                for (int i : bootstrapMethodSpecifier.bootstrap_arguments) {
                    classOutputStream.writeShort(i);
                }
            }
            return null;
        }

        @Override // com.sun.tools.classfile.Attribute.Visitor
        public Void visitCharacterRangeTable(CharacterRangeTable_attribute characterRangeTable_attribute, ClassOutputStream classOutputStream) {
            classOutputStream.writeShort(characterRangeTable_attribute.character_range_table.length);
            for (CharacterRangeTable_attribute.Entry entry : characterRangeTable_attribute.character_range_table) {
                writeCharacterRangeTableEntry(entry, classOutputStream);
            }
            return null;
        }

        @Override // com.sun.tools.classfile.Attribute.Visitor
        public Void visitCode(Code_attribute code_attribute, ClassOutputStream classOutputStream) throws IOException {
            classOutputStream.writeShort(code_attribute.max_stack);
            classOutputStream.writeShort(code_attribute.max_locals);
            classOutputStream.writeInt(code_attribute.code.length);
            byte[] bArr = code_attribute.code;
            classOutputStream.write(bArr, 0, bArr.length);
            classOutputStream.writeShort(code_attribute.exception_table.length);
            for (Code_attribute.Exception_data exception_data : code_attribute.exception_table) {
                writeExceptionTableEntry(exception_data, classOutputStream);
            }
            new AttributeWriter().write(code_attribute.attributes, classOutputStream);
            return null;
        }

        @Override // com.sun.tools.classfile.Attribute.Visitor
        public Void visitDefault(DefaultAttribute defaultAttribute, ClassOutputStream classOutputStream) throws IOException {
            byte[] bArr = defaultAttribute.info;
            classOutputStream.write(bArr, 0, bArr.length);
            return null;
        }

        @Override // com.sun.tools.classfile.Attribute.Visitor
        public Void visitEnclosingMethod(EnclosingMethod_attribute enclosingMethod_attribute, ClassOutputStream classOutputStream) {
            classOutputStream.writeShort(enclosingMethod_attribute.class_index);
            classOutputStream.writeShort(enclosingMethod_attribute.method_index);
            return null;
        }

        @Override // com.sun.tools.classfile.Attribute.Visitor
        public Void visitExceptions(Exceptions_attribute exceptions_attribute, ClassOutputStream classOutputStream) {
            classOutputStream.writeShort(exceptions_attribute.exception_index_table.length);
            for (int i : exceptions_attribute.exception_index_table) {
                classOutputStream.writeShort(i);
            }
            return null;
        }

        @Override // com.sun.tools.classfile.Attribute.Visitor
        public Void visitInnerClasses(InnerClasses_attribute innerClasses_attribute, ClassOutputStream classOutputStream) {
            classOutputStream.writeShort(innerClasses_attribute.classes.length);
            for (InnerClasses_attribute.Info info : innerClasses_attribute.classes) {
                writeInnerClassesInfo(info, classOutputStream);
            }
            return null;
        }

        @Override // com.sun.tools.classfile.Attribute.Visitor
        public Void visitLineNumberTable(LineNumberTable_attribute lineNumberTable_attribute, ClassOutputStream classOutputStream) {
            classOutputStream.writeShort(lineNumberTable_attribute.line_number_table.length);
            for (LineNumberTable_attribute.Entry entry : lineNumberTable_attribute.line_number_table) {
                writeLineNumberTableEntry(entry, classOutputStream);
            }
            return null;
        }

        @Override // com.sun.tools.classfile.Attribute.Visitor
        public Void visitLocalVariableTable(LocalVariableTable_attribute localVariableTable_attribute, ClassOutputStream classOutputStream) {
            classOutputStream.writeShort(localVariableTable_attribute.local_variable_table.length);
            for (LocalVariableTable_attribute.Entry entry : localVariableTable_attribute.local_variable_table) {
                writeLocalVariableTableEntry(entry, classOutputStream);
            }
            return null;
        }

        @Override // com.sun.tools.classfile.Attribute.Visitor
        public Void visitLocalVariableTypeTable(LocalVariableTypeTable_attribute localVariableTypeTable_attribute, ClassOutputStream classOutputStream) {
            classOutputStream.writeShort(localVariableTypeTable_attribute.local_variable_table.length);
            for (LocalVariableTypeTable_attribute.Entry entry : localVariableTypeTable_attribute.local_variable_table) {
                writeLocalVariableTypeTableEntry(entry, classOutputStream);
            }
            return null;
        }

        @Override // com.sun.tools.classfile.Attribute.Visitor
        public Void visitMethodParameters(MethodParameters_attribute methodParameters_attribute, ClassOutputStream classOutputStream) {
            classOutputStream.writeByte(methodParameters_attribute.method_parameter_table.length);
            for (MethodParameters_attribute.Entry entry : methodParameters_attribute.method_parameter_table) {
                classOutputStream.writeShort(entry.name_index);
                classOutputStream.writeShort(entry.flags);
            }
            return null;
        }

        @Override // com.sun.tools.classfile.Attribute.Visitor
        public Void visitModule(Module_attribute module_attribute, ClassOutputStream classOutputStream) {
            classOutputStream.writeShort(module_attribute.module_name);
            classOutputStream.writeShort(module_attribute.module_flags);
            classOutputStream.writeShort(module_attribute.module_version_index);
            classOutputStream.writeShort(module_attribute.requires.length);
            for (Module_attribute.RequiresEntry requiresEntry : module_attribute.requires) {
                classOutputStream.writeShort(requiresEntry.requires_index);
                classOutputStream.writeShort(requiresEntry.requires_flags);
                classOutputStream.writeShort(requiresEntry.requires_version_index);
            }
            classOutputStream.writeShort(module_attribute.exports.length);
            for (Module_attribute.ExportsEntry exportsEntry : module_attribute.exports) {
                classOutputStream.writeShort(exportsEntry.exports_index);
                classOutputStream.writeShort(exportsEntry.exports_flags);
                classOutputStream.writeShort(exportsEntry.exports_to_index.length);
                for (int i : exportsEntry.exports_to_index) {
                    classOutputStream.writeShort(i);
                }
            }
            classOutputStream.writeShort(module_attribute.opens.length);
            for (Module_attribute.OpensEntry opensEntry : module_attribute.opens) {
                classOutputStream.writeShort(opensEntry.opens_index);
                classOutputStream.writeShort(opensEntry.opens_flags);
                classOutputStream.writeShort(opensEntry.opens_to_index.length);
                for (int i2 : opensEntry.opens_to_index) {
                    classOutputStream.writeShort(i2);
                }
            }
            classOutputStream.writeShort(module_attribute.uses_index.length);
            for (int i3 : module_attribute.uses_index) {
                classOutputStream.writeShort(i3);
            }
            classOutputStream.writeShort(module_attribute.provides.length);
            for (Module_attribute.ProvidesEntry providesEntry : module_attribute.provides) {
                classOutputStream.writeShort(providesEntry.provides_index);
                classOutputStream.writeShort(providesEntry.with_count);
                for (int i4 : providesEntry.with_index) {
                    classOutputStream.writeShort(i4);
                }
            }
            return null;
        }

        @Override // com.sun.tools.classfile.Attribute.Visitor
        public Void visitModuleHashes(ModuleHashes_attribute moduleHashes_attribute, ClassOutputStream classOutputStream) {
            classOutputStream.writeShort(moduleHashes_attribute.algorithm_index);
            classOutputStream.writeShort(moduleHashes_attribute.hashes_table.length);
            for (ModuleHashes_attribute.Entry entry : moduleHashes_attribute.hashes_table) {
                classOutputStream.writeShort(entry.module_name_index);
                classOutputStream.writeShort(entry.hash.length);
                for (byte b : entry.hash) {
                    classOutputStream.writeByte(b);
                }
            }
            return null;
        }

        @Override // com.sun.tools.classfile.Attribute.Visitor
        public Void visitModulePackages(ModulePackages_attribute modulePackages_attribute, ClassOutputStream classOutputStream) {
            classOutputStream.writeShort(modulePackages_attribute.packages_count);
            for (int i : modulePackages_attribute.packages_index) {
                classOutputStream.writeShort(i);
            }
            return null;
        }

        @Override // com.sun.tools.classfile.Attribute.Visitor
        public Void visitNestMembers(NestMembers_attribute nestMembers_attribute, ClassOutputStream classOutputStream) {
            classOutputStream.writeShort(nestMembers_attribute.members_indexes.length);
            for (int i : nestMembers_attribute.members_indexes) {
                classOutputStream.writeShort(i);
            }
            return null;
        }

        @Override // com.sun.tools.classfile.Attribute.Visitor
        public Void visitPermittedSubclasses(PermittedSubclasses_attribute permittedSubclasses_attribute, ClassOutputStream classOutputStream) {
            int length = permittedSubclasses_attribute.subtypes.length;
            classOutputStream.writeShort(length);
            for (int i = 0; i < length; i++) {
                classOutputStream.writeShort(permittedSubclasses_attribute.subtypes[i]);
            }
            return null;
        }

        @Override // com.sun.tools.classfile.Attribute.Visitor
        public Void visitRecord(Record_attribute record_attribute, ClassOutputStream classOutputStream) {
            classOutputStream.writeShort(record_attribute.component_count);
            for (Record_attribute.ComponentInfo componentInfo : record_attribute.component_info_arr) {
                classOutputStream.writeShort(componentInfo.name_index);
                classOutputStream.writeShort(componentInfo.descriptor.index);
                classOutputStream.writeShort(componentInfo.attributes.size());
                Iterator<Attribute> it = componentInfo.attributes.iterator();
                while (it.hasNext()) {
                    write(it.next(), classOutputStream);
                }
            }
            return null;
        }

        @Override // com.sun.tools.classfile.Attribute.Visitor
        public Void visitRuntimeInvisibleAnnotations(RuntimeInvisibleAnnotations_attribute runtimeInvisibleAnnotations_attribute, ClassOutputStream classOutputStream) {
            this.annotationWriter.write(runtimeInvisibleAnnotations_attribute.annotations, classOutputStream);
            return null;
        }

        @Override // com.sun.tools.classfile.Attribute.Visitor
        public Void visitRuntimeInvisibleParameterAnnotations(RuntimeInvisibleParameterAnnotations_attribute runtimeInvisibleParameterAnnotations_attribute, ClassOutputStream classOutputStream) {
            classOutputStream.writeByte(runtimeInvisibleParameterAnnotations_attribute.parameter_annotations.length);
            for (Annotation[] annotationArr : runtimeInvisibleParameterAnnotations_attribute.parameter_annotations) {
                this.annotationWriter.write(annotationArr, classOutputStream);
            }
            return null;
        }

        @Override // com.sun.tools.classfile.Attribute.Visitor
        public Void visitRuntimeInvisibleTypeAnnotations(RuntimeInvisibleTypeAnnotations_attribute runtimeInvisibleTypeAnnotations_attribute, ClassOutputStream classOutputStream) {
            this.annotationWriter.write(runtimeInvisibleTypeAnnotations_attribute.annotations, classOutputStream);
            return null;
        }

        @Override // com.sun.tools.classfile.Attribute.Visitor
        public Void visitRuntimeVisibleAnnotations(RuntimeVisibleAnnotations_attribute runtimeVisibleAnnotations_attribute, ClassOutputStream classOutputStream) {
            this.annotationWriter.write(runtimeVisibleAnnotations_attribute.annotations, classOutputStream);
            return null;
        }

        @Override // com.sun.tools.classfile.Attribute.Visitor
        public Void visitRuntimeVisibleParameterAnnotations(RuntimeVisibleParameterAnnotations_attribute runtimeVisibleParameterAnnotations_attribute, ClassOutputStream classOutputStream) {
            classOutputStream.writeByte(runtimeVisibleParameterAnnotations_attribute.parameter_annotations.length);
            for (Annotation[] annotationArr : runtimeVisibleParameterAnnotations_attribute.parameter_annotations) {
                this.annotationWriter.write(annotationArr, classOutputStream);
            }
            return null;
        }

        @Override // com.sun.tools.classfile.Attribute.Visitor
        public Void visitRuntimeVisibleTypeAnnotations(RuntimeVisibleTypeAnnotations_attribute runtimeVisibleTypeAnnotations_attribute, ClassOutputStream classOutputStream) {
            this.annotationWriter.write(runtimeVisibleTypeAnnotations_attribute.annotations, classOutputStream);
            return null;
        }

        @Override // com.sun.tools.classfile.Attribute.Visitor
        public Void visitSourceDebugExtension(SourceDebugExtension_attribute sourceDebugExtension_attribute, ClassOutputStream classOutputStream) throws IOException {
            byte[] bArr = sourceDebugExtension_attribute.debug_extension;
            classOutputStream.write(bArr, 0, bArr.length);
            return null;
        }

        @Override // com.sun.tools.classfile.Attribute.Visitor
        public Void visitStackMap(StackMap_attribute stackMap_attribute, ClassOutputStream classOutputStream) throws IOException {
            if (this.stackMapWriter == null) {
                this.stackMapWriter = new StackMapTableWriter();
            }
            classOutputStream.writeShort(stackMap_attribute.entries.length);
            for (StackMap_attribute.stack_map_frame stack_map_frameVar : stackMap_attribute.entries) {
                this.stackMapWriter.write(stack_map_frameVar, classOutputStream);
            }
            return null;
        }

        @Override // com.sun.tools.classfile.Attribute.Visitor
        public Void visitStackMapTable(StackMapTable_attribute stackMapTable_attribute, ClassOutputStream classOutputStream) throws IOException {
            if (this.stackMapWriter == null) {
                this.stackMapWriter = new StackMapTableWriter();
            }
            classOutputStream.writeShort(stackMapTable_attribute.entries.length);
            for (StackMapTable_attribute.stack_map_frame stack_map_frameVar : stackMapTable_attribute.entries) {
                this.stackMapWriter.write(stack_map_frameVar, classOutputStream);
            }
            return null;
        }

        public void write(Attributes attributes, ClassOutputStream classOutputStream) {
            classOutputStream.writeShort(attributes.size());
            Iterator<Attribute> it = attributes.iterator();
            while (it.hasNext()) {
                write(it.next(), classOutputStream);
            }
        }

        public void writeAccessFlags(AccessFlags accessFlags, ClassOutputStream classOutputStream) {
            classOutputStream.writeShort(accessFlags.flags);
        }

        public void writeCharacterRangeTableEntry(CharacterRangeTable_attribute.Entry entry, ClassOutputStream classOutputStream) {
            classOutputStream.writeShort(entry.start_pc);
            classOutputStream.writeShort(entry.end_pc);
            classOutputStream.writeInt(entry.character_range_start);
            classOutputStream.writeInt(entry.character_range_end);
            classOutputStream.writeShort(entry.flags);
        }

        public void writeExceptionTableEntry(Code_attribute.Exception_data exception_data, ClassOutputStream classOutputStream) {
            classOutputStream.writeShort(exception_data.start_pc);
            classOutputStream.writeShort(exception_data.end_pc);
            classOutputStream.writeShort(exception_data.handler_pc);
            classOutputStream.writeShort(exception_data.catch_type);
        }

        public void writeInnerClassesInfo(InnerClasses_attribute.Info info, ClassOutputStream classOutputStream) {
            classOutputStream.writeShort(info.inner_class_info_index);
            classOutputStream.writeShort(info.outer_class_info_index);
            classOutputStream.writeShort(info.inner_name_index);
            writeAccessFlags(info.inner_class_access_flags, classOutputStream);
        }

        public void writeLineNumberTableEntry(LineNumberTable_attribute.Entry entry, ClassOutputStream classOutputStream) {
            classOutputStream.writeShort(entry.start_pc);
            classOutputStream.writeShort(entry.line_number);
        }

        public void writeLocalVariableTableEntry(LocalVariableTable_attribute.Entry entry, ClassOutputStream classOutputStream) {
            classOutputStream.writeShort(entry.start_pc);
            classOutputStream.writeShort(entry.length);
            classOutputStream.writeShort(entry.name_index);
            classOutputStream.writeShort(entry.descriptor_index);
            classOutputStream.writeShort(entry.index);
        }

        public void writeLocalVariableTypeTableEntry(LocalVariableTypeTable_attribute.Entry entry, ClassOutputStream classOutputStream) {
            classOutputStream.writeShort(entry.start_pc);
            classOutputStream.writeShort(entry.length);
            classOutputStream.writeShort(entry.name_index);
            classOutputStream.writeShort(entry.signature_index);
            classOutputStream.writeShort(entry.index);
        }

        @Override // com.sun.tools.classfile.Attribute.Visitor
        public Void visitCompilationID(CompilationID_attribute compilationID_attribute, ClassOutputStream classOutputStream) {
            classOutputStream.writeShort(compilationID_attribute.compilationID_index);
            return null;
        }

        @Override // com.sun.tools.classfile.Attribute.Visitor
        public Void visitConstantValue(ConstantValue_attribute constantValue_attribute, ClassOutputStream classOutputStream) {
            classOutputStream.writeShort(constantValue_attribute.constantvalue_index);
            return null;
        }

        @Override // com.sun.tools.classfile.Attribute.Visitor
        public Void visitDeprecated(Deprecated_attribute deprecated_attribute, ClassOutputStream classOutputStream) {
            return null;
        }

        @Override // com.sun.tools.classfile.Attribute.Visitor
        public Void visitModuleMainClass(ModuleMainClass_attribute moduleMainClass_attribute, ClassOutputStream classOutputStream) {
            classOutputStream.writeShort(moduleMainClass_attribute.main_class_index);
            return null;
        }

        @Override // com.sun.tools.classfile.Attribute.Visitor
        public Void visitModuleResolution(ModuleResolution_attribute moduleResolution_attribute, ClassOutputStream classOutputStream) {
            classOutputStream.writeShort(moduleResolution_attribute.resolution_flags);
            return null;
        }

        @Override // com.sun.tools.classfile.Attribute.Visitor
        public Void visitModuleTarget(ModuleTarget_attribute moduleTarget_attribute, ClassOutputStream classOutputStream) {
            classOutputStream.writeShort(moduleTarget_attribute.target_platform_index);
            return null;
        }

        @Override // com.sun.tools.classfile.Attribute.Visitor
        public Void visitNestHost(NestHost_attribute nestHost_attribute, ClassOutputStream classOutputStream) {
            classOutputStream.writeShort(nestHost_attribute.top_index);
            return null;
        }

        @Override // com.sun.tools.classfile.Attribute.Visitor
        public Void visitSignature(Signature_attribute signature_attribute, ClassOutputStream classOutputStream) {
            classOutputStream.writeShort(signature_attribute.signature_index);
            return null;
        }

        @Override // com.sun.tools.classfile.Attribute.Visitor
        public Void visitSourceFile(SourceFile_attribute sourceFile_attribute, ClassOutputStream classOutputStream) {
            classOutputStream.writeShort(sourceFile_attribute.sourcefile_index);
            return null;
        }

        @Override // com.sun.tools.classfile.Attribute.Visitor
        public Void visitSourceID(SourceID_attribute sourceID_attribute, ClassOutputStream classOutputStream) {
            classOutputStream.writeShort(sourceID_attribute.sourceID_index);
            return null;
        }

        @Override // com.sun.tools.classfile.Attribute.Visitor
        public Void visitSynthetic(Synthetic_attribute synthetic_attribute, ClassOutputStream classOutputStream) {
            return null;
        }

        public void write(Attribute attribute, ClassOutputStream classOutputStream) {
            classOutputStream.writeShort(attribute.attribute_name_index);
            ClassOutputStream classOutputStream2 = new ClassOutputStream();
            attribute.accept(this, classOutputStream2);
            classOutputStream.writeInt(classOutputStream2.size());
            classOutputStream2.writeTo(classOutputStream);
        }
    }

    public static class ConstantPoolWriter implements ConstantPool.Visitor<Integer, ClassOutputStream> {
        @Override // com.sun.tools.classfile.ConstantPool.Visitor
        public Integer visitClass(ConstantPool.CONSTANT_Class_info cONSTANT_Class_info, ClassOutputStream classOutputStream) {
            classOutputStream.writeShort(cONSTANT_Class_info.name_index);
            return 1;
        }

        @Override // com.sun.tools.classfile.ConstantPool.Visitor
        public Integer visitDouble(ConstantPool.CONSTANT_Double_info cONSTANT_Double_info, ClassOutputStream classOutputStream) {
            classOutputStream.writeDouble(cONSTANT_Double_info.value);
            return 2;
        }

        @Override // com.sun.tools.classfile.ConstantPool.Visitor
        public Integer visitDynamicConstant(ConstantPool.CONSTANT_Dynamic_info cONSTANT_Dynamic_info, ClassOutputStream classOutputStream) {
            classOutputStream.writeShort(cONSTANT_Dynamic_info.bootstrap_method_attr_index);
            classOutputStream.writeShort(cONSTANT_Dynamic_info.name_and_type_index);
            return 1;
        }

        @Override // com.sun.tools.classfile.ConstantPool.Visitor
        public Integer visitFieldref(ConstantPool.CONSTANT_Fieldref_info cONSTANT_Fieldref_info, ClassOutputStream classOutputStream) {
            writeRef(cONSTANT_Fieldref_info, classOutputStream);
            return 1;
        }

        @Override // com.sun.tools.classfile.ConstantPool.Visitor
        public Integer visitFloat(ConstantPool.CONSTANT_Float_info cONSTANT_Float_info, ClassOutputStream classOutputStream) {
            classOutputStream.writeFloat(cONSTANT_Float_info.value);
            return 1;
        }

        @Override // com.sun.tools.classfile.ConstantPool.Visitor
        public Integer visitInteger(ConstantPool.CONSTANT_Integer_info cONSTANT_Integer_info, ClassOutputStream classOutputStream) {
            classOutputStream.writeInt(cONSTANT_Integer_info.value);
            return 1;
        }

        @Override // com.sun.tools.classfile.ConstantPool.Visitor
        public Integer visitInterfaceMethodref(ConstantPool.CONSTANT_InterfaceMethodref_info cONSTANT_InterfaceMethodref_info, ClassOutputStream classOutputStream) {
            writeRef(cONSTANT_InterfaceMethodref_info, classOutputStream);
            return 1;
        }

        @Override // com.sun.tools.classfile.ConstantPool.Visitor
        public Integer visitInvokeDynamic(ConstantPool.CONSTANT_InvokeDynamic_info cONSTANT_InvokeDynamic_info, ClassOutputStream classOutputStream) {
            classOutputStream.writeShort(cONSTANT_InvokeDynamic_info.bootstrap_method_attr_index);
            classOutputStream.writeShort(cONSTANT_InvokeDynamic_info.name_and_type_index);
            return 1;
        }

        @Override // com.sun.tools.classfile.ConstantPool.Visitor
        public Integer visitLong(ConstantPool.CONSTANT_Long_info cONSTANT_Long_info, ClassOutputStream classOutputStream) {
            classOutputStream.writeLong(cONSTANT_Long_info.value);
            return 2;
        }

        @Override // com.sun.tools.classfile.ConstantPool.Visitor
        public Integer visitMethodHandle(ConstantPool.CONSTANT_MethodHandle_info cONSTANT_MethodHandle_info, ClassOutputStream classOutputStream) {
            classOutputStream.writeByte(cONSTANT_MethodHandle_info.reference_kind.tag);
            classOutputStream.writeShort(cONSTANT_MethodHandle_info.reference_index);
            return 1;
        }

        @Override // com.sun.tools.classfile.ConstantPool.Visitor
        public Integer visitMethodType(ConstantPool.CONSTANT_MethodType_info cONSTANT_MethodType_info, ClassOutputStream classOutputStream) {
            classOutputStream.writeShort(cONSTANT_MethodType_info.descriptor_index);
            return 1;
        }

        @Override // com.sun.tools.classfile.ConstantPool.Visitor
        public Integer visitModule(ConstantPool.CONSTANT_Module_info cONSTANT_Module_info, ClassOutputStream classOutputStream) {
            classOutputStream.writeShort(cONSTANT_Module_info.name_index);
            return 1;
        }

        @Override // com.sun.tools.classfile.ConstantPool.Visitor
        public Integer visitNameAndType(ConstantPool.CONSTANT_NameAndType_info cONSTANT_NameAndType_info, ClassOutputStream classOutputStream) {
            classOutputStream.writeShort(cONSTANT_NameAndType_info.name_index);
            classOutputStream.writeShort(cONSTANT_NameAndType_info.type_index);
            return 1;
        }

        @Override // com.sun.tools.classfile.ConstantPool.Visitor
        public Integer visitPackage(ConstantPool.CONSTANT_Package_info cONSTANT_Package_info, ClassOutputStream classOutputStream) {
            classOutputStream.writeShort(cONSTANT_Package_info.name_index);
            return 1;
        }

        @Override // com.sun.tools.classfile.ConstantPool.Visitor
        public Integer visitString(ConstantPool.CONSTANT_String_info cONSTANT_String_info, ClassOutputStream classOutputStream) {
            classOutputStream.writeShort(cONSTANT_String_info.string_index);
            return 1;
        }

        @Override // com.sun.tools.classfile.ConstantPool.Visitor
        public Integer visitUtf8(ConstantPool.CONSTANT_Utf8_info cONSTANT_Utf8_info, ClassOutputStream classOutputStream) {
            classOutputStream.writeUTF(cONSTANT_Utf8_info.value);
            return 1;
        }

        public int write(ConstantPool.CPInfo cPInfo, ClassOutputStream classOutputStream) {
            classOutputStream.writeByte(cPInfo.getTag());
            return ((Integer) cPInfo.accept(this, classOutputStream)).intValue();
        }

        public Integer writeRef(ConstantPool.CPRefInfo cPRefInfo, ClassOutputStream classOutputStream) {
            classOutputStream.writeShort(cPRefInfo.class_index);
            classOutputStream.writeShort(cPRefInfo.name_and_type_index);
            return 1;
        }

        @Override // com.sun.tools.classfile.ConstantPool.Visitor
        public Integer visitMethodref(ConstantPool.CONSTANT_Methodref_info cONSTANT_Methodref_info, ClassOutputStream classOutputStream) {
            return writeRef(cONSTANT_Methodref_info, classOutputStream);
        }
    }

    public static class StackMapTableWriter implements StackMapTable_attribute.stack_map_frame.Visitor<Void, ClassOutputStream> {
        @Override // com.sun.tools.classfile.StackMapTable_attribute.stack_map_frame.Visitor
        public Void visit_append_frame(StackMapTable_attribute.append_frame append_frameVar, ClassOutputStream classOutputStream) throws IOException {
            classOutputStream.writeShort(append_frameVar.offset_delta);
            for (StackMapTable_attribute.verification_type_info verification_type_infoVar : append_frameVar.locals) {
                writeVerificationTypeInfo(verification_type_infoVar, classOutputStream);
            }
            return null;
        }

        @Override // com.sun.tools.classfile.StackMapTable_attribute.stack_map_frame.Visitor
        public Void visit_full_frame(StackMapTable_attribute.full_frame full_frameVar, ClassOutputStream classOutputStream) throws IOException {
            classOutputStream.writeShort(full_frameVar.offset_delta);
            classOutputStream.writeShort(full_frameVar.locals.length);
            for (StackMapTable_attribute.verification_type_info verification_type_infoVar : full_frameVar.locals) {
                writeVerificationTypeInfo(verification_type_infoVar, classOutputStream);
            }
            classOutputStream.writeShort(full_frameVar.stack.length);
            for (StackMapTable_attribute.verification_type_info verification_type_infoVar2 : full_frameVar.stack) {
                writeVerificationTypeInfo(verification_type_infoVar2, classOutputStream);
            }
            return null;
        }

        @Override // com.sun.tools.classfile.StackMapTable_attribute.stack_map_frame.Visitor
        public Void visit_same_locals_1_stack_item_frame(StackMapTable_attribute.same_locals_1_stack_item_frame same_locals_1_stack_item_frameVar, ClassOutputStream classOutputStream) throws IOException {
            writeVerificationTypeInfo(same_locals_1_stack_item_frameVar.stack[0], classOutputStream);
            return null;
        }

        @Override // com.sun.tools.classfile.StackMapTable_attribute.stack_map_frame.Visitor
        public Void visit_same_locals_1_stack_item_frame_extended(StackMapTable_attribute.same_locals_1_stack_item_frame_extended same_locals_1_stack_item_frame_extendedVar, ClassOutputStream classOutputStream) throws IOException {
            classOutputStream.writeShort(same_locals_1_stack_item_frame_extendedVar.offset_delta);
            writeVerificationTypeInfo(same_locals_1_stack_item_frame_extendedVar.stack[0], classOutputStream);
            return null;
        }

        public void write(StackMapTable_attribute.stack_map_frame stack_map_frameVar, ClassOutputStream classOutputStream) throws IOException {
            classOutputStream.write(stack_map_frameVar.frame_type);
            stack_map_frameVar.accept(this, classOutputStream);
        }

        public void writeVerificationTypeInfo(StackMapTable_attribute.verification_type_info verification_type_infoVar, ClassOutputStream classOutputStream) throws IOException {
            classOutputStream.write(verification_type_infoVar.tag);
            switch (verification_type_infoVar.tag) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                    return;
                case 7:
                    classOutputStream.writeShort(((StackMapTable_attribute.Object_variable_info) verification_type_infoVar).cpool_index);
                    return;
                case 8:
                    classOutputStream.writeShort(((StackMapTable_attribute.Uninitialized_variable_info) verification_type_infoVar).offset);
                    return;
                default:
                    throw new Error();
            }
        }

        @Override // com.sun.tools.classfile.StackMapTable_attribute.stack_map_frame.Visitor
        public Void visit_chop_frame(StackMapTable_attribute.chop_frame chop_frameVar, ClassOutputStream classOutputStream) {
            classOutputStream.writeShort(chop_frameVar.offset_delta);
            return null;
        }

        @Override // com.sun.tools.classfile.StackMapTable_attribute.stack_map_frame.Visitor
        public Void visit_same_frame(StackMapTable_attribute.same_frame same_frameVar, ClassOutputStream classOutputStream) {
            return null;
        }

        @Override // com.sun.tools.classfile.StackMapTable_attribute.stack_map_frame.Visitor
        public Void visit_same_frame_extended(StackMapTable_attribute.same_frame_extended same_frame_extendedVar, ClassOutputStream classOutputStream) {
            classOutputStream.writeShort(same_frame_extendedVar.offset_delta);
            return null;
        }
    }

    public void write(ClassFile classFile, OutputStream outputStream) throws IOException {
        this.classFile = classFile;
        this.out.reset();
        write();
        this.out.writeTo(outputStream);
    }

    public void write(ClassFile classFile, File file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            write(classFile, fileOutputStream);
            fileOutputStream.close();
        } catch (Throwable th) {
            try {
                fileOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }
}
