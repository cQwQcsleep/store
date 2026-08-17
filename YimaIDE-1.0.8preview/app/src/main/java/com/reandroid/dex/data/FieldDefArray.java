package com.reandroid.dex.data;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.Creator;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.dex.data.FieldDef;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class FieldDefArray extends DefArray<FieldDef> {
    private static final Creator<FieldDef> CREATOR = new Creator() { // from class: ko4
        @Override // com.reandroid.arsc.base.Creator
        public final Block newInstance() {
            return new FieldDef();
        }
    };

    public FieldDefArray(IntegerReference integerReference) {
        super(integerReference, CREATOR);
    }

    @Override // com.reandroid.dex.data.DefArray
    public void sortAnnotations() {
        AnnotationsDirectory annotationsDirectory = getAnnotationsDirectory();
        if (annotationsDirectory != null) {
            annotationsDirectory.sortFields();
        }
    }
}
