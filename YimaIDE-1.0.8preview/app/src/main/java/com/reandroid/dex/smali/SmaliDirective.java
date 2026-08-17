package com.reandroid.dex.smali;

import com.intellij.psi.PsiKeyword;
import com.reandroid.utils.StringsUtil;
import com.sun.org.apache.bcel.internal.classfile.ElementValue;
import com.sun.org.apache.xalan.internal.templates.Constants;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SmaliDirective implements SmaliFormat {
    public static final SmaliDirective ANNOTATION;
    public static final SmaliDirective ARRAY_DATA;
    public static final SmaliDirective CATCH;
    public static final SmaliDirective CATCH_ALL;
    public static final SmaliDirective CLASS;
    private static final byte[] END_BYTES = {46, ElementValue.ENUM_CONSTANT, 110, 100};
    public static final SmaliDirective END_LOCAL;
    public static final SmaliDirective ENUM;
    public static final SmaliDirective EPILOGUE;
    public static final SmaliDirective FIELD;
    public static final SmaliDirective IMPLEMENTS;
    public static final SmaliDirective LINE;
    public static final SmaliDirective LOCAL;
    public static final SmaliDirective LOCALS;
    public static final SmaliDirective METHOD;
    public static final SmaliDirective PACKED_SWITCH;
    public static final SmaliDirective PARAM;
    public static final SmaliDirective PROLOGUE;
    public static final SmaliDirective REGISTERS;
    public static final SmaliDirective RESTART_LOCAL;
    public static final SmaliDirective SET_SOURCE_FILE;
    public static final SmaliDirective SOURCE;
    public static final SmaliDirective SPARSE_SWITCH;
    public static final SmaliDirective SUB_ANNOTATION;
    public static final SmaliDirective SUPER;
    private static final SmaliDirective[] VALUES;
    private final boolean methodCode;
    private final String name;
    private final byte[] nameBytes;

    static {
        SmaliDirective smaliDirective = new SmaliDirective("class");
        CLASS = smaliDirective;
        SmaliDirective smaliDirective2 = new SmaliDirective(PsiKeyword.SUPER);
        SUPER = smaliDirective2;
        SmaliDirective smaliDirective3 = new SmaliDirective("source");
        SOURCE = smaliDirective3;
        SmaliDirective smaliDirective4 = new SmaliDirective(PsiKeyword.IMPLEMENTS);
        IMPLEMENTS = smaliDirective4;
        SmaliDirective smaliDirective5 = new SmaliDirective("annotation");
        ANNOTATION = smaliDirective5;
        SmaliDirective smaliDirective6 = new SmaliDirective("subannotation");
        SUB_ANNOTATION = smaliDirective6;
        SmaliDirective smaliDirective7 = new SmaliDirective("field");
        FIELD = smaliDirective7;
        SmaliDirective smaliDirective8 = new SmaliDirective(Constants.ATTRNAME_OUTPUT_METHOD);
        METHOD = smaliDirective8;
        SmaliDirective smaliDirective9 = new SmaliDirective(PsiKeyword.ENUM);
        ENUM = smaliDirective9;
        SmaliDirective smaliDirective10 = new SmaliDirective("locals");
        LOCALS = smaliDirective10;
        SmaliDirective smaliDirective11 = new SmaliDirective("registers");
        REGISTERS = smaliDirective11;
        SmaliDirective smaliDirective12 = new SmaliDirective(PsiKeyword.CATCH, true);
        CATCH = smaliDirective12;
        SmaliDirective smaliDirective13 = new SmaliDirective("catchall", true);
        CATCH_ALL = smaliDirective13;
        SmaliDirective smaliDirective14 = new SmaliDirective("array-data", true);
        ARRAY_DATA = smaliDirective14;
        SmaliDirective smaliDirective15 = new SmaliDirective("packed-switch", true);
        PACKED_SWITCH = smaliDirective15;
        SmaliDirective smaliDirective16 = new SmaliDirective("sparse-switch", true);
        SPARSE_SWITCH = smaliDirective16;
        SmaliDirective smaliDirective17 = new SmaliDirective("line", true);
        LINE = smaliDirective17;
        SmaliDirective smaliDirective18 = new SmaliDirective("restart local", true);
        RESTART_LOCAL = smaliDirective18;
        SmaliDirective smaliDirective19 = new SmaliDirective("prologue", true);
        PROLOGUE = smaliDirective19;
        SmaliDirective smaliDirective20 = new SmaliDirective(Constants.ELEMNAME_PARAMVARIABLE_STRING, true);
        PARAM = smaliDirective20;
        SmaliDirective smaliDirective21 = new SmaliDirective("end local", true);
        END_LOCAL = smaliDirective21;
        SmaliDirective smaliDirective22 = new SmaliDirective("local", true);
        LOCAL = smaliDirective22;
        SmaliDirective smaliDirective23 = new SmaliDirective("epilogue", true);
        EPILOGUE = smaliDirective23;
        SmaliDirective smaliDirective24 = new SmaliDirective("set source file", true);
        SET_SOURCE_FILE = smaliDirective24;
        VALUES = new SmaliDirective[]{smaliDirective, smaliDirective2, smaliDirective3, smaliDirective4, smaliDirective5, smaliDirective6, smaliDirective7, smaliDirective8, smaliDirective9, smaliDirective10, smaliDirective13, smaliDirective12, smaliDirective14, smaliDirective15, smaliDirective16, smaliDirective17, smaliDirective18, smaliDirective23, smaliDirective24, smaliDirective19, smaliDirective20, smaliDirective21, smaliDirective22, smaliDirective11};
    }

    public SmaliDirective(String str, byte[] bArr, boolean z) {
        this.name = str;
        this.nameBytes = bArr;
        this.methodCode = z;
    }

    private static SmaliDirective directiveOf(SmaliReader smaliReader) {
        if (smaliReader.finished()) {
            return null;
        }
        for (SmaliDirective smaliDirective : VALUES) {
            if (smaliDirective.readMatches(smaliReader)) {
                return smaliDirective;
            }
        }
        return null;
    }

    public static SmaliDirective parse(SmaliReader smaliReader, boolean z) {
        if (smaliReader == null) {
            return null;
        }
        int iPosition = smaliReader.position();
        smaliReader.skipWhitespaces();
        if (smaliReader.finished()) {
            return null;
        }
        if (smaliReader.get() != 46) {
            smaliReader.position(iPosition);
            return null;
        }
        byte[] bArr = END_BYTES;
        if (smaliReader.startsWith(bArr)) {
            smaliReader.skip(1);
            SmaliDirective smaliDirective = END_LOCAL;
            if (smaliDirective.readMatches(smaliReader)) {
                if (!z) {
                    smaliReader.position(iPosition);
                }
                return smaliDirective;
            }
            smaliReader.skip(bArr.length - 1);
            smaliReader.skipWhitespaces();
        } else {
            smaliReader.skip(1);
        }
        SmaliDirective smaliDirectiveDirectiveOf = directiveOf(smaliReader);
        if (!z) {
            smaliReader.position(iPosition);
        }
        return smaliDirectiveDirectiveOf;
    }

    @Override // com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        smaliWriter.append('.');
        smaliWriter.append((CharSequence) getName());
        smaliWriter.append(' ');
    }

    public void appendEnd(SmaliWriter smaliWriter) throws IOException {
        smaliWriter.newLine();
        smaliWriter.append(".end ");
        smaliWriter.append((CharSequence) getName());
    }

    public boolean equals(Object obj) {
        return obj == this;
    }

    public String getName() {
        return this.name;
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public boolean is(SmaliDirective smaliDirective) {
        return smaliDirective == this;
    }

    public boolean isEnd(SmaliReader smaliReader) {
        return smaliReader.startsWith(END_BYTES) && parse(smaliReader, false) == this;
    }

    public boolean isMethodCode() {
        return this.methodCode;
    }

    public boolean readMatches(SmaliReader smaliReader) {
        int iStartsWithSqueezeSpaces = smaliReader.startsWithSqueezeSpaces(this.nameBytes);
        if (iStartsWithSqueezeSpaces <= 0) {
            return false;
        }
        smaliReader.skip(iStartsWithSqueezeSpaces);
        return true;
    }

    public boolean skipEnd(SmaliReader smaliReader) {
        smaliReader.skipWhitespaces();
        if (!smaliReader.startsWith(END_BYTES)) {
            return false;
        }
        int iPosition = smaliReader.position();
        if (parse(smaliReader, true) == this) {
            return true;
        }
        smaliReader.position(iPosition);
        return false;
    }

    public String toString(boolean z) {
        if (z) {
            return ".end " + getName();
        }
        return Constants.ATTRVAL_THIS + getName();
    }

    public SmaliDirective(String str, boolean z) {
        this(str, StringsUtil.getASCII(str), z);
    }

    public SmaliDirective(String str) {
        this(str, StringsUtil.getASCII(str), false);
    }

    public String toString() {
        return Constants.ATTRVAL_THIS + getName();
    }

    public static SmaliDirective parse(SmaliReader smaliReader) {
        return parse(smaliReader, true);
    }
}
