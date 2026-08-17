package com.reandroid.dex.common;

import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.utils.StringsUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class AnnotationVisibility extends Modifier {
    public static final AnnotationVisibility BUILD;
    public static final AnnotationVisibility RUNTIME;
    public static final AnnotationVisibility SYSTEM;
    private static final AnnotationVisibility[] VALUES;

    static {
        AnnotationVisibility annotationVisibility = new AnnotationVisibility(0, "build");
        BUILD = annotationVisibility;
        AnnotationVisibility annotationVisibility2 = new AnnotationVisibility(1, "runtime");
        RUNTIME = annotationVisibility2;
        AnnotationVisibility annotationVisibility3 = new AnnotationVisibility(2, "system");
        SYSTEM = annotationVisibility3;
        VALUES = new AnnotationVisibility[]{annotationVisibility, annotationVisibility2, annotationVisibility3};
    }

    private AnnotationVisibility(int i, String str) {
        super(i, str);
    }

    public static AnnotationVisibility parse(SmaliReader smaliReader) {
        smaliReader.skipSpaces();
        int iPosition = smaliReader.position();
        AnnotationVisibility annotationVisibilityValueOf = valueOf(smaliReader.readString(smaliReader.indexOfWhiteSpace() - iPosition));
        if (annotationVisibilityValueOf == null) {
            smaliReader.position(iPosition);
        }
        return annotationVisibilityValueOf;
    }

    public static AnnotationVisibility valueOf(String str) {
        String lowercase = StringsUtil.toLowercase(str);
        if (lowercase.equals("build")) {
            return BUILD;
        }
        if (lowercase.equals("runtime")) {
            return RUNTIME;
        }
        if (lowercase.equals("system")) {
            return SYSTEM;
        }
        return null;
    }

    @Override // com.reandroid.dex.common.Modifier
    public boolean isSet(int i) {
        return i == getValue();
    }

    public static AnnotationVisibility valueOf(int i) {
        if (i < 0) {
            return null;
        }
        AnnotationVisibility[] annotationVisibilityArr = VALUES;
        if (i >= annotationVisibilityArr.length) {
            return null;
        }
        return annotationVisibilityArr[i];
    }
}
