package com.reandroid.dex.smali.model;

import com.reandroid.dex.key.AnnotationItemKey;
import com.reandroid.dex.key.AnnotationSetKey;
import com.reandroid.dex.key.ProtoKey;
import com.reandroid.dex.key.StringKey;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.program.MethodParameterProgram;
import com.reandroid.dex.smali.SmaliDirective;
import com.reandroid.dex.smali.SmaliParseException;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliRegion;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.utils.StringsUtil;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SmaliMethodParameter extends SmaliDebug implements MethodParameterProgram, SmaliRegion {
    private SmaliAnnotationSet annotationSet;
    private StringKey name;
    private final SmaliRegisterSet registerSet = new SmaliRegisterSet();

    private void parseAnnotationSet(SmaliReader smaliReader) throws IOException {
        smaliReader.skipWhitespacesOrComment();
        if (SmaliDirective.parse(smaliReader, false) != SmaliDirective.ANNOTATION) {
            return;
        }
        int iPosition = smaliReader.position();
        SmaliAnnotationSet smaliAnnotationSet = new SmaliAnnotationSet();
        smaliAnnotationSet.parse(smaliReader);
        smaliReader.skipWhitespacesOrComment();
        if (!getSmaliDirective().isEnd(smaliReader)) {
            smaliReader.position(iPosition);
        } else {
            setSmaliAnnotationSet(smaliAnnotationSet);
            SmaliDirective.parse(smaliReader);
        }
    }

    private void parseName(SmaliReader smaliReader) throws IOException {
        smaliReader.skipSpaces();
        if (smaliReader.get() == 34) {
            setName(StringKey.read(smaliReader));
        }
    }

    @Override // com.reandroid.dex.smali.model.SmaliDebug, com.reandroid.dex.smali.model.Smali, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        SmaliDirective smaliDirective = getSmaliDirective();
        smaliDirective.append(smaliWriter);
        getRegisterSet().append(smaliWriter);
        StringKey nameKey = getNameKey();
        if (nameKey != null) {
            smaliWriter.append(", ");
            nameKey.append(smaliWriter);
        }
        SmaliAnnotationSet smaliAnnotationSet = getSmaliAnnotationSet();
        if (smaliAnnotationSet != null) {
            smaliWriter.indentPlus();
            smaliWriter.newLine();
            smaliAnnotationSet.append(smaliWriter);
            smaliWriter.indentMinus();
            smaliDirective.appendEnd(smaliWriter);
        }
    }

    @Override // com.reandroid.dex.program.AnnotatedProgram
    public void clearAnnotations() {
        setSmaliAnnotationSet(null);
    }

    @Override // com.reandroid.dex.program.AnnotatedProgram
    public AnnotationSetKey getAnnotation() {
        SmaliAnnotationSet smaliAnnotationSet = getSmaliAnnotationSet();
        return smaliAnnotationSet != null ? smaliAnnotationSet.getKey() : AnnotationSetKey.empty();
    }

    @Override // com.reandroid.dex.program.MethodParameterProgram
    public String getDebugName() {
        StringKey nameKey = getNameKey();
        if (nameKey != null) {
            return nameKey.getString();
        }
        return null;
    }

    public int getDefinitionIndex() {
        ProtoKey protoKey;
        SmaliRegister smaliRegister;
        SmaliMethod smaliMethod = (SmaliMethod) getParentInstance(SmaliMethod.class);
        if (smaliMethod == null || (protoKey = smaliMethod.getProtoKey()) == null || (smaliRegister = getSmaliRegister()) == null) {
            return -1;
        }
        int number = smaliRegister.getNumber();
        if (!smaliMethod.isStatic()) {
            number--;
        }
        return protoKey.getParameterIndex(number);
    }

    @Override // com.reandroid.dex.program.ProgramElement, com.reandroid.dex.data.DefIndex
    public TypeKey getKey() {
        ProtoKey protoKey;
        SmaliRegister smaliRegister;
        SmaliMethod smaliMethod = (SmaliMethod) getParentInstance(SmaliMethod.class);
        if (smaliMethod == null || (protoKey = smaliMethod.getProtoKey()) == null || (smaliRegister = getSmaliRegister()) == null) {
            return null;
        }
        int number = smaliRegister.getNumber();
        if (!smaliMethod.isStatic()) {
            number--;
        }
        return protoKey.getParameter(protoKey.getParameterIndex(number));
    }

    public StringKey getNameKey() {
        return this.name;
    }

    public SmaliAnnotationSet getOrCreateSmaliAnnotationSet() {
        SmaliAnnotationSet smaliAnnotationSet = getSmaliAnnotationSet();
        if (smaliAnnotationSet != null) {
            return smaliAnnotationSet;
        }
        SmaliAnnotationSet smaliAnnotationSet2 = new SmaliAnnotationSet();
        setSmaliAnnotationSet(smaliAnnotationSet2);
        return smaliAnnotationSet2;
    }

    public SmaliRegisterSet getRegisterSet() {
        return this.registerSet;
    }

    public SmaliAnnotationSet getSmaliAnnotationSet() {
        return this.annotationSet;
    }

    @Override // com.reandroid.dex.smali.model.SmaliDebug, com.reandroid.dex.smali.SmaliRegion
    public SmaliDirective getSmaliDirective() {
        return SmaliDirective.PARAM;
    }

    public SmaliRegister getSmaliRegister() {
        SmaliRegisterSet registerSet = getRegisterSet();
        if (registerSet.isEmpty()) {
            return null;
        }
        return registerSet.get(0);
    }

    @Override // com.reandroid.dex.smali.model.SmaliDebug, com.reandroid.dex.smali.model.SmaliCode, com.reandroid.dex.smali.SmaliParser
    public void parse(SmaliReader smaliReader) throws IOException {
        super.parse(smaliReader);
        getRegisterSet().parse(smaliReader);
        smaliReader.skipWhitespacesOrComment();
        if (smaliReader.get() == 44) {
            smaliReader.skip(1);
            smaliReader.skipWhitespacesOrComment();
        }
        parseName(smaliReader);
        parseAnnotationSet(smaliReader);
        AnnotationItemKey duplicate = getAnnotation().getDuplicate();
        if (duplicate == null) {
            return;
        }
        throw new SmaliParseException("Multiple annotation of type: " + duplicate.getType() + "\n", smaliReader);
    }

    @Override // com.reandroid.dex.program.AnnotatedProgram
    public void setAnnotation(AnnotationSetKey annotationSetKey) {
        if (annotationSetKey == null || annotationSetKey.isEmpty()) {
            setSmaliAnnotationSet(null);
        } else {
            getOrCreateSmaliAnnotationSet().setKey(annotationSetKey);
        }
    }

    @Override // com.reandroid.dex.program.MethodParameterProgram
    public void setDebugName(String str) {
        if (StringsUtil.isEmpty(str)) {
            str = null;
        }
        setName(str != null ? StringKey.create(str) : null);
    }

    public void setName(StringKey stringKey) {
        this.name = stringKey;
    }

    public void setSmaliAnnotationSet(SmaliAnnotationSet smaliAnnotationSet) {
        this.annotationSet = smaliAnnotationSet;
        if (smaliAnnotationSet != null) {
            smaliAnnotationSet.setParent(this);
        }
    }
}
