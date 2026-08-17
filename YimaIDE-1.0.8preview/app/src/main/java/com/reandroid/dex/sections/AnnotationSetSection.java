package com.reandroid.dex.sections;

import com.reandroid.dex.base.IntegerPair;
import com.reandroid.dex.data.AnnotationSet;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class AnnotationSetSection extends DataSection<AnnotationSet> {
    public AnnotationSetSection(IntegerPair integerPair, SectionType<AnnotationSet> sectionType) {
        super(integerPair, sectionType);
    }

    private void ensureNotEmpty() {
        if (isEmpty()) {
            add(new AnnotationSet.EmptyAnnotationSet());
        }
    }

    @Override // com.reandroid.dex.sections.Section
    public void onRefreshed() {
        super.onRefreshed();
        ensureNotEmpty();
    }
}
