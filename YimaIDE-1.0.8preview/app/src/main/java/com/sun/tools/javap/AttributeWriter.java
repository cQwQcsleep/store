package com.sun.tools.javap;

import com.intellij.psi.PsiKeyword;
import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import com.sun.tools.classfile.AccessFlags;
import com.sun.tools.classfile.AnnotationDefault_attribute;
import com.sun.tools.classfile.Attribute;
import com.sun.tools.classfile.Attributes;
import com.sun.tools.classfile.BootstrapMethods_attribute;
import com.sun.tools.classfile.CharacterRangeTable_attribute;
import com.sun.tools.classfile.Code_attribute;
import com.sun.tools.classfile.CompilationID_attribute;
import com.sun.tools.classfile.ConstantPool;
import com.sun.tools.classfile.ConstantPoolException;
import com.sun.tools.classfile.ConstantValue_attribute;
import com.sun.tools.classfile.DefaultAttribute;
import com.sun.tools.classfile.Deprecated_attribute;
import com.sun.tools.classfile.Descriptor;
import com.sun.tools.classfile.EnclosingMethod_attribute;
import com.sun.tools.classfile.Exceptions_attribute;
import com.sun.tools.classfile.InnerClasses_attribute;
import com.sun.tools.classfile.LineNumberTable_attribute;
import com.sun.tools.classfile.LocalVariableTable_attribute;
import com.sun.tools.classfile.LocalVariableTypeTable_attribute;
import com.sun.tools.classfile.MethodParameters_attribute;
import com.sun.tools.classfile.ModuleHashes_attribute;
import com.sun.tools.classfile.ModuleMainClass_attribute;
import com.sun.tools.classfile.ModulePackages_attribute;
import com.sun.tools.classfile.ModuleResolution_attribute;
import com.sun.tools.classfile.ModuleTarget_attribute;
import com.sun.tools.classfile.Module_attribute;
import com.sun.tools.classfile.NestHost_attribute;
import com.sun.tools.classfile.NestMembers_attribute;
import com.sun.tools.classfile.PermittedSubclasses_attribute;
import com.sun.tools.classfile.Record_attribute;
import com.sun.tools.classfile.RuntimeInvisibleAnnotations_attribute;
import com.sun.tools.classfile.RuntimeInvisibleParameterAnnotations_attribute;
import com.sun.tools.classfile.RuntimeInvisibleTypeAnnotations_attribute;
import com.sun.tools.classfile.RuntimeParameterAnnotations_attribute;
import com.sun.tools.classfile.RuntimeVisibleAnnotations_attribute;
import com.sun.tools.classfile.RuntimeVisibleParameterAnnotations_attribute;
import com.sun.tools.classfile.RuntimeVisibleTypeAnnotations_attribute;
import com.sun.tools.classfile.Signature_attribute;
import com.sun.tools.classfile.SourceDebugExtension_attribute;
import com.sun.tools.classfile.SourceFile_attribute;
import com.sun.tools.classfile.SourceID_attribute;
import com.sun.tools.classfile.StackMapTable_attribute;
import com.sun.tools.classfile.StackMap_attribute;
import com.sun.tools.classfile.Synthetic_attribute;
import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.StringUtils;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class AttributeWriter extends BasicWriter implements Attribute.Visitor<Void, Void> {
    private static final String format = "%-31s%s";
    private final AnnotationWriter annotationWriter;
    private final CodeWriter codeWriter;
    private final ConstantWriter constantWriter;
    private ConstantPool constant_pool;
    private final Options options;
    private Object owner;

    public AttributeWriter(Context context) {
        super(context);
        context.put(AttributeWriter.class, this);
        this.annotationWriter = AnnotationWriter.instance(context);
        this.codeWriter = CodeWriter.instance(context);
        this.constantWriter = ConstantWriter.instance(context);
        this.options = Options.instance(context);
    }

    private String getAlgorithm(ModuleHashes_attribute moduleHashes_attribute) {
        try {
            return this.constant_pool.getUTF8Value(moduleHashes_attribute.algorithm_index);
        } catch (ConstantPoolException e) {
            return report(e);
        }
    }

    private String getJavaClassName(EnclosingMethod_attribute enclosingMethod_attribute) {
        try {
            return getJavaName(enclosingMethod_attribute.getClassName(this.constant_pool));
        } catch (ConstantPoolException e) {
            return report(e);
        }
    }

    private String getJavaException(Exceptions_attribute exceptions_attribute, int i) {
        try {
            return getJavaName(exceptions_attribute.getException(i, this.constant_pool));
        } catch (ConstantPoolException e) {
            return report(e);
        }
    }

    public static String getJavaName(String str) {
        return str.replace('/', '.');
    }

    private String getJavaPackage(ModulePackages_attribute modulePackages_attribute, int i) {
        try {
            return getJavaName(modulePackages_attribute.getPackage(i, this.constant_pool));
        } catch (ConstantPoolException e) {
            return report(e);
        }
    }

    private String getMethodName(EnclosingMethod_attribute enclosingMethod_attribute) {
        try {
            return enclosingMethod_attribute.getMethodName(this.constant_pool);
        } catch (ConstantPoolException e) {
            return report(e);
        }
    }

    private String getModuleName(ModuleHashes_attribute.Entry entry) {
        try {
            return this.constant_pool.getUTF8Value(this.constant_pool.getModuleInfo(entry.module_name_index).name_index);
        } catch (ConstantPoolException e) {
            return report(e);
        }
    }

    private String getSourceFile(SourceFile_attribute sourceFile_attribute) {
        try {
            return sourceFile_attribute.getSourceFile(this.constant_pool);
        } catch (ConstantPoolException e) {
            return report(e);
        }
    }

    private String getTargetPlatform(ModuleTarget_attribute moduleTarget_attribute) {
        try {
            return this.constant_pool.getUTF8Value(moduleTarget_attribute.target_platform_index);
        } catch (ConstantPoolException e) {
            return report(e);
        }
    }

    public static AttributeWriter instance(Context context) {
        AttributeWriter attributeWriter = (AttributeWriter) context.get(AttributeWriter.class);
        return attributeWriter == null ? new AttributeWriter(context) : attributeWriter;
    }

    public static String toHex(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length);
        for (byte b : bArr) {
            sb.append(String.format("%02x", Integer.valueOf(b & 255)));
        }
        return sb.toString();
    }

    private void visitParameterAnnotations(String str, RuntimeParameterAnnotations_attribute runtimeParameterAnnotations_attribute) {
        println(str);
        indent(1);
        for (int i = 0; i < runtimeParameterAnnotations_attribute.parameter_annotations.length; i++) {
            println("parameter " + i + ": ");
            indent(1);
            for (int i2 = 0; i2 < runtimeParameterAnnotations_attribute.parameter_annotations[i].length; i2++) {
                print(i2 + ": ");
                this.annotationWriter.write(runtimeParameterAnnotations_attribute.parameter_annotations[i][i2]);
                println();
            }
            indent(-1);
        }
        indent(-1);
    }

    private void writeInnerClassHeader() {
        println("InnerClasses:");
        indent(1);
    }

    public String getInnerName(ConstantPool constantPool, InnerClasses_attribute.Info info) {
        try {
            return info.getInnerName(constantPool);
        } catch (ConstantPoolException e) {
            return report(e);
        }
    }

    public String getJavaFieldType(Descriptor descriptor) {
        try {
            return getJavaName(descriptor.getFieldType(this.constant_pool));
        } catch (ConstantPoolException e) {
            return report(e);
        } catch (Descriptor.InvalidDescriptor e2) {
            return report(e2);
        }
    }

    public String getSignature(Signature_attribute signature_attribute) {
        try {
            return signature_attribute.getSignature(this.constant_pool);
        } catch (ConstantPoolException e) {
            return report(e);
        }
    }

    public String getValue(Descriptor descriptor) {
        try {
            return descriptor.getValue(this.constant_pool);
        } catch (ConstantPoolException e) {
            return report(e);
        }
    }

    public void printExportOpenEntry(int i, int i2, int[] iArr) {
        print("#" + i + "," + String.format("%x", Integer.valueOf(i2)));
        tab();
        print("// ");
        print(this.constantWriter.stringValue(i));
        if ((32768 & i2) != 0) {
            print(" ACC_MANDATED");
        }
        if ((i2 & 4096) != 0) {
            print(" ACC_SYNTHETIC");
        }
        if (iArr.length == 0) {
            println();
            return;
        }
        println(" to ... " + iArr.length);
        indent(1);
        for (int i3 : iArr) {
            print("#" + i3);
            tab();
            println("// ... to " + this.constantWriter.stringValue(i3));
        }
        indent(-1);
    }

    public void printExportsTable(Module_attribute module_attribute) {
        Module_attribute.ExportsEntry[] exportsEntryArr = module_attribute.exports;
        print(Integer.valueOf(exportsEntryArr.length));
        tab();
        println("// exports");
        indent(1);
        for (Module_attribute.ExportsEntry exportsEntry : exportsEntryArr) {
            printExportOpenEntry(exportsEntry.exports_index, exportsEntry.exports_flags, exportsEntry.exports_to_index);
        }
        indent(-1);
    }

    public void printOpensTable(Module_attribute module_attribute) {
        Module_attribute.OpensEntry[] opensEntryArr = module_attribute.opens;
        print(Integer.valueOf(opensEntryArr.length));
        tab();
        println("// opens");
        indent(1);
        for (Module_attribute.OpensEntry opensEntry : opensEntryArr) {
            printExportOpenEntry(opensEntry.opens_index, opensEntry.opens_flags, opensEntry.opens_to_index);
        }
        indent(-1);
    }

    public void printProvidesTable(Module_attribute module_attribute) {
        Module_attribute.ProvidesEntry[] providesEntryArr = module_attribute.provides;
        print(Integer.valueOf(providesEntryArr.length));
        tab();
        println("// provides");
        indent(1);
        for (Module_attribute.ProvidesEntry providesEntry : providesEntryArr) {
            print("#" + providesEntry.provides_index);
            tab();
            print("// ");
            print(this.constantWriter.stringValue(providesEntry.provides_index));
            println(" with ... " + providesEntry.with_count);
            indent(1);
            for (int i : providesEntry.with_index) {
                print("#" + i);
                tab();
                println("// ... with " + this.constantWriter.stringValue(i));
            }
            indent(-1);
        }
        indent(-1);
    }

    public void printRequiresTable(Module_attribute module_attribute) {
        Module_attribute.RequiresEntry[] requiresEntryArr = module_attribute.requires;
        print(Integer.valueOf(requiresEntryArr.length));
        tab();
        println("// requires");
        indent(1);
        for (Module_attribute.RequiresEntry requiresEntry : requiresEntryArr) {
            print("#" + requiresEntry.requires_index + "," + String.format("%x", Integer.valueOf(requiresEntry.requires_flags)));
            tab();
            StringBuilder sb = new StringBuilder("// ");
            sb.append(this.constantWriter.stringValue(requiresEntry.requires_index));
            print(sb.toString());
            if ((requiresEntry.requires_flags & 32) != 0) {
                print(" ACC_TRANSITIVE");
            }
            if ((requiresEntry.requires_flags & 64) != 0) {
                print(" ACC_STATIC_PHASE");
            }
            if ((requiresEntry.requires_flags & 4096) != 0) {
                print(" ACC_SYNTHETIC");
            }
            if ((requiresEntry.requires_flags & 32768) != 0) {
                print(" ACC_MANDATED");
            }
            println();
            print("#" + requiresEntry.requires_version_index);
            if (requiresEntry.requires_version_index != 0) {
                tab();
                print("// " + this.constantWriter.stringValue(requiresEntry.requires_version_index));
            }
            println();
        }
        indent(-1);
    }

    public void printUsesTable(Module_attribute module_attribute) {
        int[] iArr = module_attribute.uses_index;
        print(Integer.valueOf(iArr.length));
        tab();
        println("// uses");
        indent(1);
        for (int i : iArr) {
            print("#" + i);
            tab();
            println("// " + this.constantWriter.stringValue(i));
        }
        indent(-1);
    }

    @Override // com.sun.tools.classfile.Attribute.Visitor
    public Void visitAnnotationDefault(AnnotationDefault_attribute annotationDefault_attribute, Void r2) {
        println("AnnotationDefault:");
        indent(1);
        print("default_value: ");
        this.annotationWriter.write(annotationDefault_attribute.default_value);
        indent(-1);
        println();
        return null;
    }

    @Override // com.sun.tools.classfile.Attribute.Visitor
    public Void visitBootstrapMethods(BootstrapMethods_attribute bootstrapMethods_attribute, Void r8) {
        println("BootstrapMethods:");
        int i = 0;
        while (true) {
            BootstrapMethods_attribute.BootstrapMethodSpecifier[] bootstrapMethodSpecifierArr = bootstrapMethods_attribute.bootstrap_method_specifiers;
            if (i >= bootstrapMethodSpecifierArr.length) {
                return null;
            }
            BootstrapMethods_attribute.BootstrapMethodSpecifier bootstrapMethodSpecifier = bootstrapMethodSpecifierArr[i];
            indent(1);
            print(i + ": #" + bootstrapMethodSpecifier.bootstrap_method_ref + " ");
            println(this.constantWriter.stringValue(bootstrapMethodSpecifier.bootstrap_method_ref));
            indent(1);
            println("Method arguments:");
            indent(1);
            for (int i2 = 0; i2 < bootstrapMethodSpecifier.bootstrap_arguments.length; i2++) {
                print("#" + bootstrapMethodSpecifier.bootstrap_arguments[i2] + " ");
                println(this.constantWriter.stringValue(bootstrapMethodSpecifier.bootstrap_arguments[i2]));
            }
            indent(-3);
            i++;
        }
    }

    @Override // com.sun.tools.classfile.Attribute.Visitor
    public Void visitCharacterRangeTable(CharacterRangeTable_attribute characterRangeTable_attribute, Void r12) {
        println("CharacterRangeTable:");
        indent(1);
        for (CharacterRangeTable_attribute.Entry entry : characterRangeTable_attribute.character_range_table) {
            print(String.format("    %2d, %2d, %6x, %6x, %4x", Integer.valueOf(entry.start_pc), Integer.valueOf(entry.end_pc), Integer.valueOf(entry.character_range_start), Integer.valueOf(entry.character_range_end), Integer.valueOf(entry.flags)));
            tab();
            print(String.format("// %2d, %2d, %4d:%02d, %4d:%02d", Integer.valueOf(entry.start_pc), Integer.valueOf(entry.end_pc), Integer.valueOf(entry.character_range_start >> 10), Integer.valueOf(entry.character_range_start & 1023), Integer.valueOf(entry.character_range_end >> 10), Integer.valueOf(entry.character_range_end & 1023)));
            if ((entry.flags & 1) != 0) {
                print(", statement");
            }
            if ((entry.flags & 2) != 0) {
                print(", block");
            }
            if ((entry.flags & 4) != 0) {
                print(", assignment");
            }
            if ((entry.flags & 8) != 0) {
                print(", flow-controller");
            }
            if ((entry.flags & 16) != 0) {
                print(", flow-target");
            }
            if ((entry.flags & 32) != 0) {
                print(", invoke");
            }
            if ((entry.flags & 64) != 0) {
                print(", create");
            }
            if ((entry.flags & 128) != 0) {
                print(", branch-true");
            }
            if ((entry.flags & 256) != 0) {
                print(", branch-false");
            }
            println();
        }
        indent(-1);
        return null;
    }

    @Override // com.sun.tools.classfile.Attribute.Visitor
    public Void visitCode(Code_attribute code_attribute, Void r2) {
        this.codeWriter.write(code_attribute, this.constant_pool);
        return null;
    }

    @Override // com.sun.tools.classfile.Attribute.Visitor
    public Void visitCompilationID(CompilationID_attribute compilationID_attribute, Void r2) {
        this.constantWriter.write(compilationID_attribute.compilationID_index);
        return null;
    }

    @Override // com.sun.tools.classfile.Attribute.Visitor
    public Void visitConstantValue(ConstantValue_attribute constantValue_attribute, Void r2) {
        print("ConstantValue: ");
        this.constantWriter.write(constantValue_attribute.constantvalue_index);
        println();
        return null;
    }

    @Override // com.sun.tools.classfile.Attribute.Visitor
    public Void visitDefault(DefaultAttribute defaultAttribute, Void r7) {
        byte[] bArr = defaultAttribute.info;
        print("  ");
        try {
            print(defaultAttribute.getName(this.constant_pool));
        } catch (ConstantPoolException e) {
            report(e);
            print("attribute name = #" + defaultAttribute.attribute_name_index);
        }
        print(": ");
        print("length = 0x" + toHex(defaultAttribute.info.length));
        if (defaultAttribute.reason != null) {
            print(" (" + defaultAttribute.reason + ")");
        }
        println();
        print("   ");
        int i = 0;
        for (byte b : bArr) {
            print(toHex(b, 2));
            i++;
            if (i == 16) {
                println();
                print("   ");
                i = 0;
            } else {
                print(" ");
            }
        }
        println();
        return null;
    }

    @Override // com.sun.tools.classfile.Attribute.Visitor
    public Void visitEnclosingMethod(EnclosingMethod_attribute enclosingMethod_attribute, Void r3) {
        print("EnclosingMethod: #" + enclosingMethod_attribute.class_index + ".#" + enclosingMethod_attribute.method_index);
        tab();
        StringBuilder sb = new StringBuilder("// ");
        sb.append(getJavaClassName(enclosingMethod_attribute));
        print(sb.toString());
        if (enclosingMethod_attribute.method_index != 0) {
            print(Constants.ATTRVAL_THIS + getMethodName(enclosingMethod_attribute));
        }
        println();
        return null;
    }

    @Override // com.sun.tools.classfile.Attribute.Visitor
    public Void visitExceptions(Exceptions_attribute exceptions_attribute, Void r3) {
        println("Exceptions:");
        indent(1);
        print("throws ");
        for (int i = 0; i < exceptions_attribute.number_of_exceptions; i++) {
            if (i > 0) {
                print(", ");
            }
            print(getJavaException(exceptions_attribute, i));
        }
        println();
        indent(-1);
        return null;
    }

    @Override // com.sun.tools.classfile.Attribute.Visitor
    public Void visitInnerClasses(InnerClasses_attribute innerClasses_attribute, Void r9) {
        boolean z = true;
        for (InnerClasses_attribute.Info info : innerClasses_attribute.classes) {
            AccessFlags accessFlags = info.inner_class_access_flags;
            if (this.options.checkAccess(accessFlags)) {
                if (z) {
                    writeInnerClassHeader();
                    z = false;
                }
                Iterator<String> it = accessFlags.getInnerClassModifiers().iterator();
                while (it.hasNext()) {
                    print(it.next() + " ");
                }
                if (info.inner_name_index != 0) {
                    print("#" + info.inner_name_index + "= ");
                }
                print("#" + info.inner_class_info_index);
                if (info.outer_class_info_index != 0) {
                    print(" of #" + info.outer_class_info_index);
                }
                print(";");
                tab();
                print("// ");
                if (info.inner_name_index != 0) {
                    print(getInnerName(this.constant_pool, info) + "=");
                }
                this.constantWriter.write(info.inner_class_info_index);
                if (info.outer_class_info_index != 0) {
                    print(" of ");
                    this.constantWriter.write(info.outer_class_info_index);
                }
                println();
            }
        }
        if (z) {
            return null;
        }
        indent(-1);
        return null;
    }

    @Override // com.sun.tools.classfile.Attribute.Visitor
    public Void visitLineNumberTable(LineNumberTable_attribute lineNumberTable_attribute, Void r6) {
        println("LineNumberTable:");
        indent(1);
        for (LineNumberTable_attribute.Entry entry : lineNumberTable_attribute.line_number_table) {
            println("line " + entry.line_number + ": " + entry.start_pc);
        }
        indent(-1);
        return null;
    }

    @Override // com.sun.tools.classfile.Attribute.Visitor
    public Void visitLocalVariableTable(LocalVariableTable_attribute localVariableTable_attribute, Void r9) {
        println("LocalVariableTable:");
        indent(1);
        println("Start  Length  Slot  Name   Signature");
        for (LocalVariableTable_attribute.Entry entry : localVariableTable_attribute.local_variable_table) {
            println(String.format("%5d %7d %5d %5s   %s", Integer.valueOf(entry.start_pc), Integer.valueOf(entry.length), Integer.valueOf(entry.index), this.constantWriter.stringValue(entry.name_index), this.constantWriter.stringValue(entry.descriptor_index)));
        }
        indent(-1);
        return null;
    }

    @Override // com.sun.tools.classfile.Attribute.Visitor
    public Void visitLocalVariableTypeTable(LocalVariableTypeTable_attribute localVariableTypeTable_attribute, Void r9) {
        println("LocalVariableTypeTable:");
        indent(1);
        println("Start  Length  Slot  Name   Signature");
        for (LocalVariableTypeTable_attribute.Entry entry : localVariableTypeTable_attribute.local_variable_table) {
            println(String.format("%5d %7d %5d %5s   %s", Integer.valueOf(entry.start_pc), Integer.valueOf(entry.length), Integer.valueOf(entry.index), this.constantWriter.stringValue(entry.name_index), this.constantWriter.stringValue(entry.signature_index)));
        }
        indent(-1);
        return null;
    }

    @Override // com.sun.tools.classfile.Attribute.Visitor
    public Void visitMethodParameters(MethodParameters_attribute methodParameters_attribute, Void r10) {
        String str = String.format(format, SchemaSymbols.ATTVAL_NAME, "Flags");
        println("MethodParameters:");
        indent(1);
        println(str);
        for (MethodParameters_attribute.Entry entry : methodParameters_attribute.method_parameter_table) {
            int i = entry.name_index;
            String strStringValue = i != 0 ? this.constantWriter.stringValue(i) : "<no name>";
            StringBuilder sb = new StringBuilder();
            String str2 = "";
            sb.append((entry.flags & 16) != 0 ? "final " : "");
            sb.append((entry.flags & 32768) != 0 ? "mandated " : "");
            if ((entry.flags & 4096) != 0) {
                str2 = "synthetic";
            }
            sb.append(str2);
            println(String.format(format, strStringValue, sb.toString()));
        }
        indent(-1);
        return null;
    }

    @Override // com.sun.tools.classfile.Attribute.Visitor
    public Void visitModule(Module_attribute module_attribute, Void r6) {
        println("Module:");
        indent(1);
        print("#" + module_attribute.module_name);
        print(",");
        print(String.format("%x", Integer.valueOf(module_attribute.module_flags)));
        tab();
        print("// " + this.constantWriter.stringValue(module_attribute.module_name));
        if ((module_attribute.module_flags & 32) != 0) {
            print(" ACC_OPEN");
        }
        if ((module_attribute.module_flags & 32768) != 0) {
            print(" ACC_MANDATED");
        }
        if ((module_attribute.module_flags & 4096) != 0) {
            print(" ACC_SYNTHETIC");
        }
        println();
        print("#" + module_attribute.module_version_index);
        if (module_attribute.module_version_index != 0) {
            tab();
            print("// " + this.constantWriter.stringValue(module_attribute.module_version_index));
        }
        println();
        printRequiresTable(module_attribute);
        printExportsTable(module_attribute);
        printOpensTable(module_attribute);
        printUsesTable(module_attribute);
        printProvidesTable(module_attribute);
        indent(-1);
        return null;
    }

    @Override // com.sun.tools.classfile.Attribute.Visitor
    public Void visitModuleHashes(ModuleHashes_attribute moduleHashes_attribute, Void r7) {
        println("ModuleHashes:");
        indent(1);
        print("algorithm: #" + moduleHashes_attribute.algorithm_index);
        tab();
        println("// " + getAlgorithm(moduleHashes_attribute));
        print(Integer.valueOf(moduleHashes_attribute.hashes_table_length));
        tab();
        println("// hashes");
        for (ModuleHashes_attribute.Entry entry : moduleHashes_attribute.hashes_table) {
            print("#" + entry.module_name_index);
            tab();
            println("// " + getModuleName(entry));
            println("hash_length: " + entry.hash.length);
            println("hash: [" + toHex(entry.hash) + "]");
        }
        indent(-1);
        return null;
    }

    @Override // com.sun.tools.classfile.Attribute.Visitor
    public Void visitModuleMainClass(ModuleMainClass_attribute moduleMainClass_attribute, Void r3) {
        print("ModuleMainClass: #" + moduleMainClass_attribute.main_class_index);
        tab();
        print("// " + getJavaClassName(moduleMainClass_attribute));
        println();
        return null;
    }

    @Override // com.sun.tools.classfile.Attribute.Visitor
    public Void visitModulePackages(ModulePackages_attribute modulePackages_attribute, Void r4) {
        println("ModulePackages: ");
        indent(1);
        for (int i = 0; i < modulePackages_attribute.packages_count; i++) {
            print("#" + modulePackages_attribute.packages_index[i]);
            tab();
            println("// " + getJavaPackage(modulePackages_attribute, i));
        }
        indent(-1);
        return null;
    }

    @Override // com.sun.tools.classfile.Attribute.Visitor
    public Void visitModuleResolution(ModuleResolution_attribute moduleResolution_attribute, Void r3) {
        println("ModuleResolution:");
        indent(1);
        print(String.format("%x", Integer.valueOf(moduleResolution_attribute.resolution_flags)));
        tab();
        print("// ");
        int i = moduleResolution_attribute.resolution_flags;
        if ((i & 1) != 0) {
            print(" DO_NOT_RESOLVE_BY_DEFAULT");
        }
        if ((i & 2) != 0) {
            print(" WARN_DEPRECATED");
        }
        if ((i & 4) != 0) {
            print(" WARN_DEPRECATED_FOR_REMOVAL");
        }
        if ((i & 8) != 0) {
            print(" WARN_INCUBATING");
        }
        println();
        indent(-1);
        return null;
    }

    @Override // com.sun.tools.classfile.Attribute.Visitor
    public Void visitModuleTarget(ModuleTarget_attribute moduleTarget_attribute, Void r3) {
        println("ModuleTarget:");
        indent(1);
        print("target_platform: #" + moduleTarget_attribute.target_platform_index);
        if (moduleTarget_attribute.target_platform_index != 0) {
            tab();
            print("// " + getTargetPlatform(moduleTarget_attribute));
        }
        println();
        indent(-1);
        return null;
    }

    @Override // com.sun.tools.classfile.Attribute.Visitor
    public Void visitNestHost(NestHost_attribute nestHost_attribute, Void r2) {
        print("NestHost: ");
        this.constantWriter.write(nestHost_attribute.top_index);
        println();
        return null;
    }

    @Override // com.sun.tools.classfile.Attribute.Visitor
    public Void visitNestMembers(NestMembers_attribute nestMembers_attribute, Void r6) {
        println("NestMembers:");
        indent(1);
        try {
            ConstantPool.CONSTANT_Class_info[] children = nestMembers_attribute.getChildren(this.constant_pool);
            for (int i = 0; i < nestMembers_attribute.members_indexes.length; i++) {
                println(this.constantWriter.stringValue(children[i]));
            }
            indent(-1);
            return null;
        } catch (ConstantPoolException e) {
            x01.a(e);
            return null;
        }
    }

    @Override // com.sun.tools.classfile.Attribute.Visitor
    public Void visitPermittedSubclasses(PermittedSubclasses_attribute permittedSubclasses_attribute, Void r5) {
        println("PermittedSubclasses:");
        indent(1);
        try {
            for (ConstantPool.CONSTANT_Class_info cONSTANT_Class_info : permittedSubclasses_attribute.getSubtypes(this.constant_pool)) {
                println(this.constantWriter.stringValue(cONSTANT_Class_info));
            }
            indent(-1);
            return null;
        } catch (ConstantPoolException e) {
            x01.a(e);
            return null;
        }
    }

    @Override // com.sun.tools.classfile.Attribute.Visitor
    public Void visitRecord(Record_attribute record_attribute, Void r9) {
        println("Record:");
        indent(1);
        for (Record_attribute.ComponentInfo componentInfo : record_attribute.component_info_arr) {
            Signature_attribute signature_attribute = (Signature_attribute) componentInfo.attributes.get(Attribute.Signature);
            if (signature_attribute == null) {
                print(getJavaFieldType(componentInfo.descriptor));
            } else {
                try {
                    print(getJavaName(signature_attribute.getParsedSignature().getType(this.constant_pool).toString()));
                } catch (ConstantPoolException unused) {
                    print(getJavaFieldType(componentInfo.descriptor));
                }
            }
            print(" ");
            try {
                print(componentInfo.getName(this.constant_pool));
                print(";");
                println();
                indent(1);
                if (this.options.showDescriptors) {
                    println("descriptor: " + getValue(componentInfo.descriptor));
                }
                if (this.options.showAllAttrs) {
                    Iterator<Attribute> it = componentInfo.attributes.iterator();
                    while (it.hasNext()) {
                        write(componentInfo, it.next(), this.constant_pool);
                    }
                    println();
                }
                indent(-1);
            } catch (ConstantPoolException e) {
                report(e);
                return null;
            }
        }
        indent(-1);
        return null;
    }

    @Override // com.sun.tools.classfile.Attribute.Visitor
    public Void visitRuntimeInvisibleAnnotations(RuntimeInvisibleAnnotations_attribute runtimeInvisibleAnnotations_attribute, Void r4) {
        println("RuntimeInvisibleAnnotations:");
        indent(1);
        for (int i = 0; i < runtimeInvisibleAnnotations_attribute.annotations.length; i++) {
            print(i + ": ");
            this.annotationWriter.write(runtimeInvisibleAnnotations_attribute.annotations[i]);
            println();
        }
        indent(-1);
        return null;
    }

    @Override // com.sun.tools.classfile.Attribute.Visitor
    public Void visitRuntimeInvisibleTypeAnnotations(RuntimeInvisibleTypeAnnotations_attribute runtimeInvisibleTypeAnnotations_attribute, Void r4) {
        println("RuntimeInvisibleTypeAnnotations:");
        indent(1);
        for (int i = 0; i < runtimeInvisibleTypeAnnotations_attribute.annotations.length; i++) {
            print(i + ": ");
            this.annotationWriter.write(runtimeInvisibleTypeAnnotations_attribute.annotations[i]);
            println();
        }
        indent(-1);
        return null;
    }

    @Override // com.sun.tools.classfile.Attribute.Visitor
    public Void visitRuntimeVisibleAnnotations(RuntimeVisibleAnnotations_attribute runtimeVisibleAnnotations_attribute, Void r4) {
        println("RuntimeVisibleAnnotations:");
        indent(1);
        for (int i = 0; i < runtimeVisibleAnnotations_attribute.annotations.length; i++) {
            print(i + ": ");
            this.annotationWriter.write(runtimeVisibleAnnotations_attribute.annotations[i]);
            println();
        }
        indent(-1);
        return null;
    }

    @Override // com.sun.tools.classfile.Attribute.Visitor
    public Void visitRuntimeVisibleTypeAnnotations(RuntimeVisibleTypeAnnotations_attribute runtimeVisibleTypeAnnotations_attribute, Void r4) {
        println("RuntimeVisibleTypeAnnotations:");
        indent(1);
        for (int i = 0; i < runtimeVisibleTypeAnnotations_attribute.annotations.length; i++) {
            print(i + ": ");
            this.annotationWriter.write(runtimeVisibleTypeAnnotations_attribute.annotations[i]);
            println();
        }
        indent(-1);
        return null;
    }

    @Override // com.sun.tools.classfile.Attribute.Visitor
    public Void visitSignature(Signature_attribute signature_attribute, Void r3) {
        print("Signature: #" + signature_attribute.signature_index);
        tab();
        println("// " + getSignature(signature_attribute));
        return null;
    }

    @Override // com.sun.tools.classfile.Attribute.Visitor
    public Void visitSourceDebugExtension(SourceDebugExtension_attribute sourceDebugExtension_attribute, Void r4) {
        println("SourceDebugExtension:");
        indent(1);
        for (String str : sourceDebugExtension_attribute.getValue().split("[\r\n]+")) {
            println(str);
        }
        indent(-1);
        return null;
    }

    @Override // com.sun.tools.classfile.Attribute.Visitor
    public Void visitSourceFile(SourceFile_attribute sourceFile_attribute, Void r3) {
        println("SourceFile: \"" + getSourceFile(sourceFile_attribute) + "\"");
        return null;
    }

    @Override // com.sun.tools.classfile.Attribute.Visitor
    public Void visitSourceID(SourceID_attribute sourceID_attribute, Void r2) {
        this.constantWriter.write(sourceID_attribute.sourceID_index);
        return null;
    }

    @Override // com.sun.tools.classfile.Attribute.Visitor
    public Void visitStackMap(StackMap_attribute stackMap_attribute, Void r5) {
        println("StackMap: number_of_entries = " + stackMap_attribute.number_of_entries);
        indent(1);
        StackMapTableWriter stackMapTableWriter = new StackMapTableWriter();
        for (StackMap_attribute.stack_map_frame stack_map_frameVar : stackMap_attribute.entries) {
            stackMapTableWriter.write(stack_map_frameVar);
        }
        indent(-1);
        return null;
    }

    @Override // com.sun.tools.classfile.Attribute.Visitor
    public Void visitStackMapTable(StackMapTable_attribute stackMapTable_attribute, Void r5) {
        println("StackMapTable: number_of_entries = " + stackMapTable_attribute.number_of_entries);
        indent(1);
        StackMapTableWriter stackMapTableWriter = new StackMapTableWriter();
        for (StackMapTable_attribute.stack_map_frame stack_map_frameVar : stackMapTable_attribute.entries) {
            stackMapTableWriter.write(stack_map_frameVar);
        }
        indent(-1);
        return null;
    }

    public void write(Object obj, Attributes attributes, ConstantPool constantPool) {
        if (attributes != null) {
            Assert.checkNonNull(constantPool);
            Assert.checkNonNull(obj);
            this.constant_pool = constantPool;
            this.owner = obj;
            Iterator<Attribute> it = attributes.iterator();
            while (it.hasNext()) {
                it.next().accept(this, null);
            }
        }
    }

    public void writeList(String str, Collection<?> collection, String str2) {
        print(str);
        String str3 = "";
        for (Object obj : collection) {
            print(str3);
            print(obj);
            str3 = ", ";
        }
        print(str2);
    }

    public void writeModifiers(Collection<String> collection) {
        Iterator<String> it = collection.iterator();
        while (it.hasNext()) {
            print((Object) it.next());
            print(" ");
        }
    }

    public class StackMapTableWriter implements StackMapTable_attribute.stack_map_frame.Visitor<Void, Void> {
        public StackMapTableWriter() {
        }

        public String mapTypeName(int i) {
            switch (i) {
                case 0:
                    return "top";
                case 1:
                    return "int";
                case 2:
                    return "float";
                case 3:
                    return "double";
                case 4:
                    return "long";
                case 5:
                    return PsiKeyword.NULL;
                case 6:
                    return PsiKeyword.THIS;
                case 7:
                    return "CP";
                case 8:
                    return "uninitialized";
                default:
                    AttributeWriter.this.report("unrecognized verification_type_info tag: " + i);
                    return "[tag:" + i + "]";
            }
        }

        public void printHeader(StackMapTable_attribute.stack_map_frame stack_map_frameVar, String str) {
            AttributeWriter.this.print("frame_type = " + stack_map_frameVar.frame_type + " ");
            AttributeWriter.this.println(str);
        }

        public void printMap(String str, StackMapTable_attribute.verification_type_info[] verification_type_infoVarArr) {
            AttributeWriter.this.print(str + " = [");
            for (int i = 0; i < verification_type_infoVarArr.length; i++) {
                StackMapTable_attribute.verification_type_info verification_type_infoVar = verification_type_infoVarArr[i];
                int i2 = verification_type_infoVar.tag;
                String str2 = " ";
                if (i2 != 7) {
                    AttributeWriter attributeWriter = AttributeWriter.this;
                    if (i2 != 8) {
                        attributeWriter.print(" " + mapTypeName(i2));
                    } else {
                        attributeWriter.print(" " + mapTypeName(i2));
                        AttributeWriter.this.print(" " + ((StackMapTable_attribute.Uninitialized_variable_info) verification_type_infoVar).offset);
                    }
                } else {
                    AttributeWriter.this.print(" ");
                    AttributeWriter.this.constantWriter.write(((StackMapTable_attribute.Object_variable_info) verification_type_infoVar).cpool_index);
                }
                AttributeWriter attributeWriter2 = AttributeWriter.this;
                if (i != verification_type_infoVarArr.length - 1) {
                    str2 = ",";
                }
                attributeWriter2.print(str2);
            }
            AttributeWriter.this.println("]");
        }

        @Override // com.sun.tools.classfile.StackMapTable_attribute.stack_map_frame.Visitor
        public Void visit_append_frame(StackMapTable_attribute.append_frame append_frameVar, Void r4) {
            printHeader(append_frameVar, "/* append */");
            AttributeWriter.this.indent(1);
            AttributeWriter.this.println("offset_delta = " + append_frameVar.offset_delta);
            printMap("locals", append_frameVar.locals);
            AttributeWriter.this.indent(-1);
            return null;
        }

        @Override // com.sun.tools.classfile.StackMapTable_attribute.stack_map_frame.Visitor
        public Void visit_chop_frame(StackMapTable_attribute.chop_frame chop_frameVar, Void r4) {
            printHeader(chop_frameVar, "/* chop */");
            AttributeWriter.this.indent(1);
            AttributeWriter.this.println("offset_delta = " + chop_frameVar.offset_delta);
            AttributeWriter.this.indent(-1);
            return null;
        }

        @Override // com.sun.tools.classfile.StackMapTable_attribute.stack_map_frame.Visitor
        public Void visit_full_frame(StackMapTable_attribute.full_frame full_frameVar, Void r4) {
            if (full_frameVar instanceof StackMap_attribute.stack_map_frame) {
                printHeader(full_frameVar, "offset = " + full_frameVar.offset_delta);
                AttributeWriter.this.indent(1);
            } else {
                printHeader(full_frameVar, "/* full_frame */");
                AttributeWriter.this.indent(1);
                AttributeWriter.this.println("offset_delta = " + full_frameVar.offset_delta);
            }
            printMap("locals", full_frameVar.locals);
            printMap("stack", full_frameVar.stack);
            AttributeWriter.this.indent(-1);
            return null;
        }

        @Override // com.sun.tools.classfile.StackMapTable_attribute.stack_map_frame.Visitor
        public Void visit_same_frame_extended(StackMapTable_attribute.same_frame_extended same_frame_extendedVar, Void r4) {
            printHeader(same_frame_extendedVar, "/* same_frame_extended */");
            AttributeWriter.this.indent(1);
            AttributeWriter.this.println("offset_delta = " + same_frame_extendedVar.offset_delta);
            AttributeWriter.this.indent(-1);
            return null;
        }

        @Override // com.sun.tools.classfile.StackMapTable_attribute.stack_map_frame.Visitor
        public Void visit_same_locals_1_stack_item_frame(StackMapTable_attribute.same_locals_1_stack_item_frame same_locals_1_stack_item_frameVar, Void r3) {
            printHeader(same_locals_1_stack_item_frameVar, "/* same_locals_1_stack_item */");
            AttributeWriter.this.indent(1);
            printMap("stack", same_locals_1_stack_item_frameVar.stack);
            AttributeWriter.this.indent(-1);
            return null;
        }

        @Override // com.sun.tools.classfile.StackMapTable_attribute.stack_map_frame.Visitor
        public Void visit_same_locals_1_stack_item_frame_extended(StackMapTable_attribute.same_locals_1_stack_item_frame_extended same_locals_1_stack_item_frame_extendedVar, Void r4) {
            printHeader(same_locals_1_stack_item_frame_extendedVar, "/* same_locals_1_stack_item_frame_extended */");
            AttributeWriter.this.indent(1);
            AttributeWriter.this.println("offset_delta = " + same_locals_1_stack_item_frame_extendedVar.offset_delta);
            printMap("stack", same_locals_1_stack_item_frame_extendedVar.stack);
            AttributeWriter.this.indent(-1);
            return null;
        }

        public void write(StackMapTable_attribute.stack_map_frame stack_map_frameVar) {
            stack_map_frameVar.accept(this, null);
        }

        @Override // com.sun.tools.classfile.StackMapTable_attribute.stack_map_frame.Visitor
        public Void visit_same_frame(StackMapTable_attribute.same_frame same_frameVar, Void r2) {
            printHeader(same_frameVar, "/* same */");
            return null;
        }
    }

    @Override // com.sun.tools.classfile.Attribute.Visitor
    public Void visitDeprecated(Deprecated_attribute deprecated_attribute, Void r2) {
        println("Deprecated: true");
        return null;
    }

    @Override // com.sun.tools.classfile.Attribute.Visitor
    public Void visitRuntimeInvisibleParameterAnnotations(RuntimeInvisibleParameterAnnotations_attribute runtimeInvisibleParameterAnnotations_attribute, Void r2) {
        visitParameterAnnotations("RuntimeInvisibleParameterAnnotations:", runtimeInvisibleParameterAnnotations_attribute);
        return null;
    }

    @Override // com.sun.tools.classfile.Attribute.Visitor
    public Void visitRuntimeVisibleParameterAnnotations(RuntimeVisibleParameterAnnotations_attribute runtimeVisibleParameterAnnotations_attribute, Void r2) {
        visitParameterAnnotations("RuntimeVisibleParameterAnnotations:", runtimeVisibleParameterAnnotations_attribute);
        return null;
    }

    @Override // com.sun.tools.classfile.Attribute.Visitor
    public Void visitSynthetic(Synthetic_attribute synthetic_attribute, Void r2) {
        println("Synthetic: true");
        return null;
    }

    private String getJavaClassName(ModuleMainClass_attribute moduleMainClass_attribute) {
        try {
            return getJavaName(moduleMainClass_attribute.getMainClassName(this.constant_pool));
        } catch (ConstantPoolException e) {
            return report(e);
        }
    }

    public void write(Object obj, Attribute attribute, ConstantPool constantPool) {
        if (attribute != null) {
            Assert.checkNonNull(constantPool);
            Assert.checkNonNull(obj);
            this.constant_pool = constantPool;
            this.owner = obj;
            attribute.accept(this, null);
        }
    }

    public static String toHex(int i) {
        return StringUtils.toUpperCase(Integer.toString(i, 16));
    }

    public static String toHex(int i, int i2) {
        String upperCase = StringUtils.toUpperCase(Integer.toHexString(i));
        while (upperCase.length() < i2) {
            upperCase = "0".concat(upperCase);
        }
        return StringUtils.toUpperCase(upperCase);
    }

    public String toHex(byte b, int i) {
        return toHex(b & 255, i);
    }
}
