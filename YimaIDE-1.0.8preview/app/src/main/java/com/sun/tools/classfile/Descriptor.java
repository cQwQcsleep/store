package com.sun.tools.classfile;

import com.intellij.psi.PsiKeyword;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Descriptor {
    private int count;
    public final int index;

    public Descriptor(ClassReader classReader) throws IOException {
        this(classReader.readUnsignedShort());
    }

    private String parse(String str, int i, int i2) throws InvalidDescriptor {
        String strReplace;
        StringBuilder sb = new StringBuilder();
        this.count = 0;
        int i3 = 0;
        while (i < i2) {
            int i4 = i + 1;
            char cCharAt = str.charAt(i);
            if (cCharAt == '(') {
                sb.append('(');
            } else if (cCharAt != ')') {
                if (cCharAt == 'F') {
                    strReplace = "float";
                } else if (cCharAt == 'L') {
                    int iIndexOf = str.indexOf(59, i4);
                    if (iIndexOf == -1) {
                        throw new InvalidDescriptor(str, i);
                    }
                    strReplace = str.substring(i4, iIndexOf).replace('/', '.');
                    i4 = iIndexOf + 1;
                } else if (cCharAt == 'S') {
                    strReplace = "short";
                } else if (cCharAt == 'V') {
                    strReplace = PsiKeyword.VOID;
                } else if (cCharAt == 'I') {
                    strReplace = "int";
                } else if (cCharAt == 'J') {
                    strReplace = "long";
                } else if (cCharAt == 'Z') {
                    strReplace = "boolean";
                } else if (cCharAt != '[') {
                    switch (cCharAt) {
                        case 'B':
                            strReplace = "byte";
                            break;
                        case 'C':
                            strReplace = PsiKeyword.CHAR;
                            break;
                        case 'D':
                            strReplace = "double";
                            break;
                        default:
                            throw new InvalidDescriptor(str, i);
                    }
                } else {
                    i3++;
                }
                if (sb.length() > 1 && sb.charAt(0) == '(') {
                    sb.append(", ");
                }
                sb.append(strReplace);
                while (i3 > 0) {
                    sb.append("[]");
                    i3--;
                }
                this.count++;
            } else {
                sb.append(')');
            }
            i = i4;
        }
        return sb.toString();
    }

    public String getFieldType(ConstantPool constantPool) throws ConstantPoolException, InvalidDescriptor {
        String value = getValue(constantPool);
        return parse(value, 0, value.length());
    }

    public int getParameterCount(ConstantPool constantPool) throws ConstantPoolException, InvalidDescriptor {
        String value = getValue(constantPool);
        int iIndexOf = value.indexOf(")");
        if (iIndexOf == -1) {
            throw new InvalidDescriptor(value);
        }
        parse(value, 0, iIndexOf + 1);
        return this.count;
    }

    public String getParameterTypes(ConstantPool constantPool) throws ConstantPoolException, InvalidDescriptor {
        String value = getValue(constantPool);
        int iIndexOf = value.indexOf(")");
        if (iIndexOf != -1) {
            return parse(value, 0, iIndexOf + 1);
        }
        throw new InvalidDescriptor(value);
    }

    public String getReturnType(ConstantPool constantPool) throws ConstantPoolException, InvalidDescriptor {
        String value = getValue(constantPool);
        int iIndexOf = value.indexOf(")");
        if (iIndexOf != -1) {
            return parse(value, iIndexOf + 1, value.length());
        }
        throw new InvalidDescriptor(value);
    }

    public String getValue(ConstantPool constantPool) throws ConstantPoolException {
        return constantPool.getUTF8Value(this.index);
    }

    public static class InvalidDescriptor extends DescriptorException {
        private static final long serialVersionUID = 1;
        public final String desc;
        public final int index;

        public InvalidDescriptor(String str) {
            this.desc = str;
            this.index = -1;
        }

        @Override // java.lang.Throwable
        public String getMessage() {
            if (this.index == -1) {
                return "invalid descriptor \"" + this.desc + "\"";
            }
            return "descriptor is invalid at offset " + this.index + " in \"" + this.desc + "\"";
        }

        public InvalidDescriptor(String str, int i) {
            this.desc = str;
            this.index = i;
        }
    }

    public Descriptor(int i) {
        this.index = i;
    }
}
