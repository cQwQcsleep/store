package com.reandroid.dex.smali.model;

import com.reandroid.dex.common.AccessFlag;
import com.reandroid.dex.common.Modifier;
import com.reandroid.dex.key.MethodKey;
import com.reandroid.dex.key.StringKey;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.key.TypeListKey;
import com.reandroid.dex.program.ClassProgram;
import com.reandroid.dex.smali.SmaliDirective;
import com.reandroid.dex.smali.SmaliParseException;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliWriter;
import defpackage.l78;
import java.io.IOException;
import java.lang.annotation.ElementType;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SmaliClass extends SmaliDef implements ClassProgram {
    private final SmaliFieldSet fields;
    private final SmaliInterfaceSet interfaces;
    private final SmaliMethodSet methods;
    private StringKey sourceFile;
    private TypeKey superClass;

    public SmaliClass() {
        SmaliInterfaceSet smaliInterfaceSet = new SmaliInterfaceSet();
        this.interfaces = smaliInterfaceSet;
        SmaliFieldSet smaliFieldSet = new SmaliFieldSet();
        this.fields = smaliFieldSet;
        SmaliMethodSet smaliMethodSet = new SmaliMethodSet();
        this.methods = smaliMethodSet;
        smaliInterfaceSet.setParent(this);
        smaliFieldSet.setParent(this);
        smaliMethodSet.setParent(this);
    }

    private boolean parseNext(SmaliReader smaliReader) throws IOException {
        if (smaliReader.finished()) {
            return false;
        }
        smaliReader.skipWhitespacesOrComment();
        SmaliDirective smaliDirective = SmaliDirective.parse(smaliReader, false);
        if (smaliDirective == SmaliDirective.CLASS) {
            return false;
        }
        if (smaliDirective == SmaliDirective.SUPER) {
            parseSuper(smaliReader);
            return true;
        }
        if (smaliDirective == SmaliDirective.SOURCE) {
            parseSource(smaliReader);
            return true;
        }
        if (smaliDirective == SmaliDirective.ANNOTATION) {
            getOrCreateSmaliAnnotationSet().parse(smaliReader);
            return true;
        }
        if (smaliDirective == SmaliDirective.FIELD) {
            this.fields.parse(smaliReader);
            return true;
        }
        if (smaliDirective == SmaliDirective.METHOD) {
            this.methods.parse(smaliReader);
            return true;
        }
        if (smaliDirective != SmaliDirective.IMPLEMENTS) {
            return false;
        }
        this.interfaces.parse(smaliReader);
        return true;
    }

    private void parseSource(SmaliReader smaliReader) throws IOException {
        SmaliParseException.expect(smaliReader, SmaliDirective.SOURCE);
        smaliReader.skipSpaces();
        setSourceFile(StringKey.read(smaliReader));
    }

    private void parseSuper(SmaliReader smaliReader) throws IOException {
        SmaliParseException.expect(smaliReader, SmaliDirective.SUPER);
        setSuperClass(TypeKey.read(smaliReader));
    }

    public void addFields(Iterator<SmaliField> it) {
        this.fields.addAll(it);
    }

    public void addMethods(Iterator<SmaliMethod> it) {
        this.methods.addAll(it);
    }

    @Override // com.reandroid.dex.smali.model.Smali, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        getSmaliDirective().append(smaliWriter);
        Modifier.append(smaliWriter, getAccessFlags());
        smaliWriter.appendOptional(getKey());
        smaliWriter.newLine();
        SmaliDirective.SUPER.append(smaliWriter);
        smaliWriter.appendOptional(getSuperClassKey());
        StringKey sourceFileKey = getSourceFileKey();
        if (sourceFileKey != null) {
            smaliWriter.newLine();
            SmaliDirective.SOURCE.append(smaliWriter);
            sourceFileKey.append(smaliWriter);
        }
        getInterfaces().append(smaliWriter);
        if (hasAnnotation()) {
            smaliWriter.newLine();
            smaliWriter.appendCommentNewLine("annotations");
            smaliWriter.appendAllWithDoubleNewLine(getAnnotationSet().iterator());
        }
        this.fields.append(smaliWriter);
        this.methods.append(smaliWriter);
    }

    public void fixUninitializedFinalFields() {
        Iterator<SmaliField> staticFields = getStaticFields();
        while (staticFields.hasNext()) {
            staticFields.next().fixUninitializedFinalValue();
        }
    }

    @Override // com.reandroid.dex.program.ClassProgram
    public Iterator<SmaliMethod> getDirectMethods() {
        return this.methods.getDirectMethods();
    }

    @Override // com.reandroid.dex.program.ProgramElement, com.reandroid.dex.program.MethodProgram
    public ElementType getElementType() {
        return ElementType.TYPE;
    }

    @Override // com.reandroid.dex.program.ClassProgram
    public Iterator<SmaliField> getInstanceFields() {
        return this.fields.getInstanceFields();
    }

    public SmaliInterfaceSet getInterfaces() {
        return this.interfaces;
    }

    @Override // com.reandroid.dex.program.ClassProgram
    public TypeListKey getInterfacesKey() {
        return getInterfaces().getKey();
    }

    @Override // com.reandroid.dex.smali.model.SmaliDef, com.reandroid.dex.program.ProgramElement, com.reandroid.dex.data.DefIndex
    public TypeKey getKey() {
        return TypeKey.create(getName());
    }

    @Override // com.reandroid.dex.smali.model.SmaliDef, com.reandroid.dex.smali.SmaliRegion
    public SmaliDirective getSmaliDirective() {
        return SmaliDirective.CLASS;
    }

    public StringKey getSourceFileKey() {
        return this.sourceFile;
    }

    @Override // com.reandroid.dex.program.ClassProgram
    public String getSourceFileName() {
        StringKey sourceFileKey = getSourceFileKey();
        if (sourceFileKey != null) {
            return sourceFileKey.getString();
        }
        return null;
    }

    public SmaliMethod getStaticConstructor() {
        Iterator<SmaliMethod> directMethods = getDirectMethods();
        while (directMethods.hasNext()) {
            SmaliMethod next = directMethods.next();
            if (next.isConstructor() && next.isStatic() && MethodKey.CONSTRUCTOR_STATIC.equalsIgnoreDeclaring(next.getKey())) {
                return next;
            }
        }
        return null;
    }

    @Override // com.reandroid.dex.program.ClassProgram
    public Iterator<SmaliField> getStaticFields() {
        return this.fields.getStaticFields();
    }

    @Override // com.reandroid.dex.program.ClassProgram
    public TypeKey getSuperClassKey() {
        return this.superClass;
    }

    @Override // com.reandroid.dex.program.ClassProgram
    public Iterator<SmaliMethod> getVirtualMethods() {
        return this.methods.getVirtualMethods();
    }

    public boolean hasClassData() {
        return (this.fields.isEmpty() && this.methods.isEmpty()) ? false : true;
    }

    @Override // com.reandroid.dex.smali.SmaliParser
    public void parse(SmaliReader smaliReader) throws IOException {
        smaliReader.skipWhitespacesOrComment();
        SmaliParseException.expect(smaliReader, SmaliDirective.CLASS);
        setAccessFlags(AccessFlag.parse(smaliReader));
        setKey(TypeKey.read(smaliReader));
        while (parseNext(smaliReader)) {
            smaliReader.skipWhitespacesOrComment();
        }
        smaliReader.skipWhitespacesOrComment();
        if (smaliReader.finished() || SmaliDirective.CLASS.is(SmaliDirective.parse(smaliReader, false))) {
            fixUninitializedFinalFields();
        } else {
            l78.a("Unexpected character", smaliReader);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SmaliField parseField(SmaliReader smaliReader) throws IOException {
        return (SmaliField) this.fields.parseNext(smaliReader);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SmaliMethod parseMethod(SmaliReader smaliReader) throws IOException {
        return (SmaliMethod) this.methods.parseNext(smaliReader);
    }

    public void setInterfaces(TypeListKey typeListKey) {
        getInterfaces().setKey(typeListKey);
    }

    public void setKey(TypeKey typeKey) {
        setName(typeKey != null ? typeKey.getTypeName() : null);
    }

    public void setSourceFile(String str) {
        setSourceFile(str == null ? null : StringKey.create(str));
    }

    public void setSuperClass(TypeKey typeKey) {
        this.superClass = typeKey;
    }

    @Override // com.reandroid.dex.smali.model.Smali
    public String toDebugString() {
        return "class = " + getKey();
    }

    public void setSourceFile(StringKey stringKey) {
        this.sourceFile = stringKey;
    }
}
