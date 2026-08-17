package com.reandroid.dex.model;

import com.reandroid.dex.data.MethodParameter;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.program.MethodParameterProgram;
import com.reandroid.dex.program.ProgramElement;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.utils.ObjectsUtil;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DexMethodParameter extends Dex implements AnnotatedDex, MethodParameterProgram {
    private final DexMethod dexMethod;
    private final MethodParameter parameter;

    public DexMethodParameter(DexMethod dexMethod, MethodParameter methodParameter) {
        this.dexMethod = dexMethod;
        this.parameter = methodParameter;
    }

    public static DexMethodParameter create(DexMethod dexMethod, MethodParameter methodParameter) {
        if (dexMethod == null || methodParameter == null) {
            return null;
        }
        return new DexMethodParameter(dexMethod, methodParameter);
    }

    @Override // com.reandroid.dex.model.Dex, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        getParameter().append(smaliWriter);
    }

    @Override // com.reandroid.dex.model.Dex
    public DexClassRepository getClassRepository() {
        return getDexMethod().getClassRepository();
    }

    @Override // com.reandroid.dex.program.MethodParameterProgram
    public String getDebugName() {
        return getParameter().getDebugName();
    }

    public DexMethod getDexMethod() {
        return this.dexMethod;
    }

    public int getIndex() {
        return getParameter().getDefinitionIndex();
    }

    @Override // com.reandroid.dex.model.AnnotatedDex, com.reandroid.dex.program.ProgramElement, com.reandroid.dex.data.DefIndex
    public TypeKey getKey() {
        return getParameter().getKey();
    }

    public MethodParameter getParameter() {
        return this.parameter;
    }

    @Override // com.reandroid.dex.model.AnnotatedDex
    public ProgramElement getProgramElement() {
        return getParameter();
    }

    public TypeKey getType() {
        return getParameter().getType();
    }

    public DexClass getTypeClass() {
        return getClassRepository().getDexClass(getType());
    }

    @Override // com.reandroid.dex.model.Dex
    public boolean isRemoved() {
        DexMethod dexMethod = getDexMethod();
        return dexMethod.isRemoved() || !dexMethod.hasParameter(getIndex());
    }

    @Override // com.reandroid.dex.model.Dex
    public void removeSelf() {
        getDexMethod().removeParameter(getIndex());
    }

    @Override // com.reandroid.dex.program.MethodParameterProgram
    public void setDebugName(String str) {
        getParameter().setDebugName(str);
    }

    @Override // com.reandroid.dex.model.Dex
    public boolean uses(Key key) {
        if (ObjectsUtil.equals(getType(), key)) {
            return true;
        }
        Iterator<DexAnnotation> dexAnnotations = getDexAnnotations();
        while (dexAnnotations.hasNext()) {
            if (dexAnnotations.next().uses(key)) {
                return true;
            }
        }
        return false;
    }
}
