package com.reandroid.dex.data;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.Creator;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.dex.data.MethodDef;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class MethodDefArray extends DefArray<MethodDef> {
    private static final Creator<MethodDef> CREATOR = new Creator() { // from class: a1a
        @Override // com.reandroid.arsc.base.Creator
        public final Block newInstance() {
            return new MethodDef();
        }
    };

    public MethodDefArray(IntegerReference integerReference) {
        super(integerReference, CREATOR);
    }

    @Override // com.reandroid.dex.data.DefArray
    public void sortAnnotations() {
        AnnotationsDirectory annotationsDirectory = getAnnotationsDirectory();
        if (annotationsDirectory != null) {
            annotationsDirectory.sortMethods();
        }
    }
}
