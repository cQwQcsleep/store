package com.reandroid.dex.data;

import com.reandroid.dex.dalvik.DalvikSignature;
import com.reandroid.dex.debug.DebugParameter;
import com.reandroid.dex.id.ProtoId;
import com.reandroid.dex.id.TypeId;
import com.reandroid.dex.key.AnnotationSetKey;
import com.reandroid.dex.key.DalvikSignatureKey;
import com.reandroid.dex.key.ParameterisedTypeKey;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.program.MethodParameterProgram;
import com.reandroid.dex.smali.SmaliDirective;
import com.reandroid.dex.smali.SmaliRegion;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.dex.smali.model.SmaliMethodParameter;
import com.reandroid.utils.StringsUtil;
import com.reandroid.utils.collection.ComputeIterator;
import com.reandroid.utils.collection.EmptyIterator;
import com.reandroid.utils.collection.ExpandIterator;
import defpackage.df3;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class MethodParameter implements DefIndex, MethodParameterProgram, SmaliRegion {
    private final int index;
    private final MethodDef methodDef;

    public MethodParameter(MethodDef methodDef, int i) {
        this.methodDef = methodDef;
        this.index = i;
    }

    private Iterator<AnnotationItem> getAnnotationItemBlocks() {
        MethodDef methodDef = getMethodDef();
        AnnotationsDirectory annotationsDirectory = methodDef.getAnnotationsDirectory();
        return annotationsDirectory != null ? ExpandIterator.of(annotationsDirectory.getParameterAnnotation(methodDef, getDefinitionIndex())) : EmptyIterator.of();
    }

    private boolean hasAnnotationSetBlocks() {
        MethodDef methodDef = getMethodDef();
        AnnotationsDirectory annotationsDirectory = methodDef.getAnnotationsDirectory();
        if (annotationsDirectory != null) {
            return annotationsDirectory.getParameterAnnotation(methodDef, getDefinitionIndex()).hasNext();
        }
        return false;
    }

    private void writeAnnotation(AnnotationSetKey annotationSetKey) {
        MethodDef methodDef = getMethodDef();
        int definitionIndex = getDefinitionIndex();
        if (annotationSetKey == null || annotationSetKey.isEmpty()) {
            if (hasAnnotationSetBlocks()) {
                methodDef.getOrCreateUniqueAnnotationsDirectory().removeParameterAnnotation(methodDef, definitionIndex);
            }
        } else {
            AnnotationsDirectory orCreateUniqueAnnotationsDirectory = methodDef.getOrCreateUniqueAnnotationsDirectory();
            orCreateUniqueAnnotationsDirectory.removeParameterAnnotation(methodDef, definitionIndex);
            orCreateUniqueAnnotationsDirectory.setParameterAnnotation(methodDef, definitionIndex, annotationSetKey);
        }
    }

    @Override // com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        DebugParameter debugParameter = getDebugParameter();
        boolean z = (debugParameter == null || debugParameter.getNameId() == null) ? false : true;
        AnnotationSetKey annotation = getAnnotation();
        boolean zIsEmpty = annotation.isEmpty();
        if (z || !zIsEmpty) {
            smaliWriter.newLine();
            getSmaliDirective().append(smaliWriter);
            smaliWriter.append('p');
            smaliWriter.appendInteger(getRegister());
            if (z) {
                debugParameter.append(smaliWriter);
            }
            ParameterisedTypeKey parameterisedTypeKey = getParameterisedTypeKey();
            if (parameterisedTypeKey != null) {
                smaliWriter.appendComment(parameterisedTypeKey.getComment());
            } else {
                smaliWriter.appendComment(getType().getTypeName());
            }
            if (zIsEmpty) {
                return;
            }
            smaliWriter.indentPlus();
            smaliWriter.appendAllWithDoubleNewLine(annotation.iterator());
            smaliWriter.indentMinus();
            getSmaliDirective().appendEnd(smaliWriter);
        }
    }

    @Override // com.reandroid.dex.program.AnnotatedProgram
    public void clearAnnotations() {
        writeAnnotation(AnnotationSetKey.empty());
    }

    public void clearDebugParameter() {
        DebugInfo debugInfo = getMethodDef().getDebugInfo();
        if (debugInfo != null) {
            debugInfo.removeDebugParameter(getDefinitionIndex());
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            MethodParameter methodParameter = (MethodParameter) obj;
            if (this.index == methodParameter.index && getMethodDef() == methodParameter.getMethodDef()) {
                return true;
            }
        }
        return false;
    }

    public void fromSmali(SmaliMethodParameter smaliMethodParameter) {
        if (smaliMethodParameter.hasAnnotations()) {
            setAnnotation(smaliMethodParameter.getSmaliAnnotationSet().getKey());
        }
        setDebugName(smaliMethodParameter.getDebugName());
    }

    @Override // com.reandroid.dex.program.AnnotatedProgram
    public AnnotationSetKey getAnnotation() {
        return AnnotationSetKey.create(ComputeIterator.of(getAnnotationItemBlocks(), new df3()));
    }

    @Override // com.reandroid.dex.program.MethodParameterProgram
    public String getDebugName() {
        DebugParameter debugParameter = getDebugParameter();
        if (debugParameter != null) {
            return debugParameter.getName();
        }
        return null;
    }

    public DebugParameter getDebugParameter() {
        DebugInfo debugInfo = getMethodDef().getDebugInfo();
        if (debugInfo != null) {
            return debugInfo.getDebugParameter(getDefinitionIndex());
        }
        return null;
    }

    @Override // com.reandroid.dex.data.DefIndex
    public int getDefinitionIndex() {
        return this.index;
    }

    @Override // com.reandroid.dex.data.DefIndex
    public TypeKey getKey() {
        TypeId typeId = getTypeId();
        if (typeId != null) {
            return typeId.getKey();
        }
        return null;
    }

    public MethodDef getMethodDef() {
        return this.methodDef;
    }

    public ParameterisedTypeKey getParameterisedTypeKey() {
        DalvikSignatureKey signature;
        ParameterisedTypeKey protoParameter;
        DalvikSignature dalvikSignatureOf = DalvikSignature.of(getMethodDef());
        if (dalvikSignatureOf == null || (signature = dalvikSignatureOf.getSignature()) == null || (protoParameter = signature.getProtoParameter(getDefinitionIndex())) == null || !protoParameter.isParametrisedType()) {
            return null;
        }
        return protoParameter;
    }

    public int getRegister() {
        MethodDef methodDef = getMethodDef();
        return (!methodDef.isStatic() ? 1 : 0) + methodDef.getKey().getRegister(getDefinitionIndex());
    }

    @Override // com.reandroid.dex.smali.SmaliRegion
    public SmaliDirective getSmaliDirective() {
        return SmaliDirective.PARAM;
    }

    public TypeKey getType() {
        TypeId typeId = getTypeId();
        if (typeId != null) {
            return typeId.getKey();
        }
        return null;
    }

    public TypeId getTypeId() {
        ProtoId protoId = getMethodDef().getProtoId();
        if (protoId != null) {
            return protoId.getParameter(getDefinitionIndex());
        }
        return null;
    }

    public int hashCode() {
        return (this.methodDef.hashCode() * 31) + this.index;
    }

    public void onRemoved() {
        clearAnnotations();
        clearDebugParameter();
    }

    @Override // com.reandroid.dex.program.AnnotatedProgram
    public void setAnnotation(AnnotationSetKey annotationSetKey) {
        clearAnnotations();
        writeAnnotation(annotationSetKey);
    }

    @Override // com.reandroid.dex.program.MethodParameterProgram
    public void setDebugName(String str) {
        if (StringsUtil.isEmpty(str)) {
            str = null;
        }
        MethodDef methodDef = getMethodDef();
        DebugInfo debugInfo = methodDef.getDebugInfo();
        if (debugInfo == null) {
            if (str == null) {
                return;
            } else {
                debugInfo = methodDef.getOrCreateDebugInfo();
            }
        }
        if (str == null) {
            debugInfo.removeDebugParameter(getDefinitionIndex());
        } else {
            debugInfo.getOrCreateDebugParameter(getDefinitionIndex()).setName(str);
        }
    }

    public String toString() {
        return SmaliWriter.toStringSafe(this);
    }
}
