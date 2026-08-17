package com.sun.tools.classfile;

import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;
import defpackage.s22;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class TypeAnnotation {
    public final Annotation annotation;
    public final ConstantPool constant_pool;
    public final Position position;

    /* JADX INFO: renamed from: com.sun.tools.classfile.TypeAnnotation$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType;

        static {
            int[] iArr = new int[TargetType.values().length];
            $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType = iArr;
            try {
                iArr[TargetType.INSTANCEOF.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TargetType.NEW.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TargetType.CONSTRUCTOR_REFERENCE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TargetType.METHOD_REFERENCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TargetType.LOCAL_VARIABLE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TargetType.RESOURCE_VARIABLE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TargetType.EXCEPTION_PARAMETER.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TargetType.METHOD_RECEIVER.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TargetType.CLASS_TYPE_PARAMETER.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TargetType.METHOD_TYPE_PARAMETER.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TargetType.CLASS_TYPE_PARAMETER_BOUND.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TargetType.METHOD_TYPE_PARAMETER_BOUND.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TargetType.CLASS_EXTENDS.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TargetType.THROWS.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TargetType.METHOD_FORMAL_PARAMETER.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TargetType.CAST.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TargetType.CONSTRUCTOR_INVOCATION_TYPE_ARGUMENT.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TargetType.METHOD_INVOCATION_TYPE_ARGUMENT.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TargetType.CONSTRUCTOR_REFERENCE_TYPE_ARGUMENT.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TargetType.METHOD_REFERENCE_TYPE_ARGUMENT.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TargetType.METHOD_RETURN.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TargetType.FIELD.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TargetType.UNKNOWN.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
        }
    }

    public TypeAnnotation(ClassReader classReader) throws Annotation.InvalidAnnotation, IOException {
        this.constant_pool = classReader.getConstantPool();
        this.position = read_position(classReader);
        this.annotation = new Annotation(classReader);
    }

    private static int position_length(Position position) {
        int i = 3;
        switch (AnonymousClass1.$SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[position.type.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 7:
            case 11:
            case 12:
            case 13:
            case 14:
                break;
            case 5:
            case 6:
                int length = position.lvarOffset.length * 2;
                i = 3 + length + length + length;
                break;
            case 8:
            case 21:
            case 22:
                i = 1;
                break;
            case 9:
            case 10:
            case 15:
                i = 2;
                break;
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
                i = 4;
                break;
            case 23:
                x01.a("TypeAnnotation: UNKNOWN target type should never occur!");
                return 0;
            default:
                pe1.a("TypeAnnotation: Unknown target type: ", position.type);
                return 0;
        }
        return i + 1 + (position.location.size() * 2);
    }

    private static Position read_position(ClassReader classReader) throws Annotation.InvalidAnnotation, IOException {
        int unsignedByte = classReader.readUnsignedByte();
        if (!TargetType.isValidTargetTypeValue(unsignedByte)) {
            throw new Annotation.InvalidAnnotation("TypeAnnotation: Invalid type annotation target type value: ".concat(String.format("0x%02X", Integer.valueOf(unsignedByte))));
        }
        TargetType targetTypeFromTargetTypeValue = TargetType.fromTargetTypeValue(unsignedByte);
        Position position = new Position();
        position.type = targetTypeFromTargetTypeValue;
        switch (AnonymousClass1.$SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[targetTypeFromTargetTypeValue.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
                position.offset = classReader.readUnsignedShort();
                break;
            case 5:
            case 6:
                int unsignedShort = classReader.readUnsignedShort();
                position.lvarOffset = new int[unsignedShort];
                position.lvarLength = new int[unsignedShort];
                position.lvarIndex = new int[unsignedShort];
                for (int i = 0; i < unsignedShort; i++) {
                    position.lvarOffset[i] = classReader.readUnsignedShort();
                    position.lvarLength[i] = classReader.readUnsignedShort();
                    position.lvarIndex[i] = classReader.readUnsignedShort();
                }
                break;
            case 7:
                position.exception_index = classReader.readUnsignedShort();
                break;
            case 8:
            case 21:
            case 22:
                break;
            case 9:
            case 10:
                position.parameter_index = classReader.readUnsignedByte();
                break;
            case 11:
            case 12:
                position.parameter_index = classReader.readUnsignedByte();
                position.bound_index = classReader.readUnsignedByte();
                break;
            case 13:
                position.type_index = classReader.readUnsignedShort();
                break;
            case 14:
                position.type_index = classReader.readUnsignedShort();
                break;
            case 15:
                position.parameter_index = classReader.readUnsignedByte();
                break;
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
                position.offset = classReader.readUnsignedShort();
                position.type_index = classReader.readUnsignedByte();
                break;
            case 23:
                x01.a("TypeAnnotation: UNKNOWN target type should never occur!");
                return null;
            default:
                s22.a("TypeAnnotation: Unknown target type: ", targetTypeFromTargetTypeValue);
                return null;
        }
        int unsignedByte2 = classReader.readUnsignedByte();
        ArrayList arrayList = new ArrayList(unsignedByte2);
        for (int i2 = 0; i2 < unsignedByte2 * 2; i2++) {
            arrayList.add(Integer.valueOf(classReader.readUnsignedByte()));
        }
        position.location = Position.getTypePathFromBinary(arrayList);
        return position;
    }

    public int length() {
        return this.annotation.length() + position_length(this.position);
    }

    public String toString() {
        try {
            return "@" + this.constant_pool.getUTF8Value(this.annotation.type_index).substring(1) + " pos: " + this.position.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    public TypeAnnotation(ConstantPool constantPool, Annotation annotation, Position position) {
        this.constant_pool = constantPool;
        this.position = position;
        this.annotation = annotation;
    }

    public static class Position {
        public TargetType type = TargetType.UNKNOWN;
        public List<TypePathEntry> location = new ArrayList(0);
        public int pos = -1;
        public boolean isValidOffset = false;
        public int offset = -1;
        public int[] lvarOffset = null;
        public int[] lvarLength = null;
        public int[] lvarIndex = null;
        public int bound_index = Integer.MIN_VALUE;
        public int parameter_index = Integer.MIN_VALUE;
        public int type_index = Integer.MIN_VALUE;
        public int exception_index = Integer.MIN_VALUE;

        public enum TypePathEntryKind {
            ARRAY(0),
            INNER_TYPE(1),
            WILDCARD(2),
            TYPE_ARGUMENT(3);

            public final int tag;

            TypePathEntryKind(int i) {
                this.tag = i;
            }
        }

        public static List<Integer> getBinaryFromTypePath(List<TypePathEntry> list) {
            ArrayList arrayList = new ArrayList(list.size() * 2);
            for (TypePathEntry typePathEntry : list) {
                arrayList.add(Integer.valueOf(typePathEntry.tag.tag));
                arrayList.add(Integer.valueOf(typePathEntry.arg));
            }
            return arrayList;
        }

        public static List<TypePathEntry> getTypePathFromBinary(List<Integer> list) {
            ArrayList arrayList = new ArrayList(list.size() / 2);
            for (int i = 0; i < list.size(); i += 2) {
                int i2 = i + 1;
                if (i2 == list.size()) {
                    s22.a("Could not decode type path: ", list);
                    return null;
                }
                arrayList.add(TypePathEntry.fromBinary(list.get(i).intValue(), list.get(i2).intValue()));
            }
            return arrayList;
        }

        public boolean emitToClassfile() {
            return !this.type.isLocal() || this.isValidOffset;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("[");
            sb.append(this.type);
            switch (AnonymousClass1.$SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[this.type.ordinal()]) {
                case 1:
                case 2:
                case 3:
                case 4:
                    sb.append(", offset = ");
                    sb.append(this.offset);
                    break;
                case 5:
                case 6:
                    if (this.lvarOffset == null) {
                        sb.append(", lvarOffset is null!");
                    } else {
                        sb.append(", {");
                        for (int i = 0; i < this.lvarOffset.length; i++) {
                            if (i != 0) {
                                sb.append("; ");
                            }
                            sb.append("start_pc = ");
                            sb.append(this.lvarOffset[i]);
                            sb.append(", length = ");
                            sb.append(this.lvarLength[i]);
                            sb.append(", index = ");
                            sb.append(this.lvarIndex[i]);
                        }
                        sb.append("}");
                    }
                    break;
                case 7:
                    sb.append(", exception_index = ");
                    sb.append(this.exception_index);
                    break;
                case 8:
                case 21:
                case 22:
                    break;
                case 9:
                case 10:
                    sb.append(", param_index = ");
                    sb.append(this.parameter_index);
                    break;
                case 11:
                case 12:
                    sb.append(", param_index = ");
                    sb.append(this.parameter_index);
                    sb.append(", bound_index = ");
                    sb.append(this.bound_index);
                    break;
                case 13:
                    sb.append(", type_index = ");
                    sb.append(this.type_index);
                    break;
                case 14:
                    sb.append(", type_index = ");
                    sb.append(this.type_index);
                    break;
                case 15:
                    sb.append(", param_index = ");
                    sb.append(this.parameter_index);
                    break;
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                    sb.append(", offset = ");
                    sb.append(this.offset);
                    sb.append(", type_index = ");
                    sb.append(this.type_index);
                    break;
                case 23:
                    sb.append(", position UNKNOWN!");
                    break;
                default:
                    pe1.a("Unknown target type: ", this.type);
                    return null;
            }
            if (!this.location.isEmpty()) {
                sb.append(", location = (");
                sb.append(this.location);
                sb.append(")");
            }
            sb.append(", pos = ");
            sb.append(this.pos);
            sb.append(']');
            return sb.toString();
        }

        public static class TypePathEntry {
            public static final TypePathEntry ARRAY = new TypePathEntry(TypePathEntryKind.ARRAY);
            public static final TypePathEntry INNER_TYPE = new TypePathEntry(TypePathEntryKind.INNER_TYPE);
            public static final TypePathEntry WILDCARD = new TypePathEntry(TypePathEntryKind.WILDCARD);
            public static final int bytesPerEntry = 2;
            public final int arg;
            public final TypePathEntryKind tag;

            private TypePathEntry(TypePathEntryKind typePathEntryKind) {
                if (typePathEntryKind != TypePathEntryKind.ARRAY && typePathEntryKind != TypePathEntryKind.INNER_TYPE && typePathEntryKind != TypePathEntryKind.WILDCARD) {
                    s22.a("Invalid TypePathEntryKind: ", typePathEntryKind);
                    throw null;
                }
                this.tag = typePathEntryKind;
                this.arg = 0;
            }

            public static TypePathEntry fromBinary(int i, int i2) {
                if (i2 != 0 && i != TypePathEntryKind.TYPE_ARGUMENT.tag) {
                    throw new AssertionError("Invalid TypePathEntry tag/arg: " + i + PsuedoNames.PSEUDONAME_ROOT + i2);
                }
                if (i == 0) {
                    return ARRAY;
                }
                if (i == 1) {
                    return INNER_TYPE;
                }
                if (i == 2) {
                    return WILDCARD;
                }
                if (i == 3) {
                    return new TypePathEntry(TypePathEntryKind.TYPE_ARGUMENT, i2);
                }
                ru7.a("Invalid TypePathEntryKind tag: ", i);
                return null;
            }

            public boolean equals(Object obj) {
                if (!(obj instanceof TypePathEntry)) {
                    return false;
                }
                TypePathEntry typePathEntry = (TypePathEntry) obj;
                return this.tag == typePathEntry.tag && this.arg == typePathEntry.arg;
            }

            public int hashCode() {
                return (this.tag.hashCode() * 17) + this.arg;
            }

            public String toString() {
                String str;
                StringBuilder sb = new StringBuilder();
                sb.append(this.tag.toString());
                if (this.tag == TypePathEntryKind.TYPE_ARGUMENT) {
                    str = "(" + this.arg + ")";
                } else {
                    str = "";
                }
                sb.append(str);
                return sb.toString();
            }

            public TypePathEntry(TypePathEntryKind typePathEntryKind, int i) {
                if (typePathEntryKind == TypePathEntryKind.TYPE_ARGUMENT) {
                    this.tag = typePathEntryKind;
                    this.arg = i;
                } else {
                    s22.a("Invalid TypePathEntryKind: ", typePathEntryKind);
                    throw null;
                }
            }
        }
    }

    public enum TargetType {
        CLASS_TYPE_PARAMETER(0),
        METHOD_TYPE_PARAMETER(1),
        CLASS_EXTENDS(16),
        CLASS_TYPE_PARAMETER_BOUND(17),
        METHOD_TYPE_PARAMETER_BOUND(18),
        FIELD(19),
        METHOD_RETURN(20),
        METHOD_RECEIVER(21),
        METHOD_FORMAL_PARAMETER(22),
        THROWS(23),
        LOCAL_VARIABLE(64, true),
        RESOURCE_VARIABLE(65, true),
        EXCEPTION_PARAMETER(66, true),
        INSTANCEOF(67, true),
        NEW(68, true),
        CONSTRUCTOR_REFERENCE(69, true),
        METHOD_REFERENCE(70, true),
        CAST(71, true),
        CONSTRUCTOR_INVOCATION_TYPE_ARGUMENT(72, true),
        METHOD_INVOCATION_TYPE_ARGUMENT(73, true),
        CONSTRUCTOR_REFERENCE_TYPE_ARGUMENT(74, true),
        METHOD_REFERENCE_TYPE_ARGUMENT(75, true),
        UNKNOWN(255);

        private static final int MAXIMUM_TARGET_TYPE_VALUE = 75;
        private static final TargetType[] targets = new TargetType[76];
        private final boolean isLocal;
        private final int targetTypeValue;

        static {
            for (TargetType targetType : values()) {
                int i = targetType.targetTypeValue;
                if (i != UNKNOWN.targetTypeValue) {
                    targets[i] = targetType;
                }
            }
            for (int i2 = 0; i2 <= 75; i2++) {
                TargetType[] targetTypeArr = targets;
                if (targetTypeArr[i2] == null) {
                    targetTypeArr[i2] = UNKNOWN;
                }
            }
        }

        TargetType(int i, boolean z) {
            if (i < 0 || i > 255) {
                x01.a("Attribute type value needs to be an unsigned byte: ".concat(String.format("0x%02X", Integer.valueOf(i))));
                throw null;
            }
            this.targetTypeValue = i;
            this.isLocal = z;
        }

        public static TargetType fromTargetTypeValue(int i) {
            TargetType targetType = UNKNOWN;
            if (i == targetType.targetTypeValue) {
                return targetType;
            }
            if (i >= 0) {
                TargetType[] targetTypeArr = targets;
                if (i < targetTypeArr.length) {
                    return targetTypeArr[i];
                }
            }
            ru7.a("Unknown TargetType: ", i);
            return null;
        }

        public static boolean isValidTargetTypeValue(int i) {
            if (i == UNKNOWN.targetTypeValue) {
                return true;
            }
            return i >= 0 && i < targets.length;
        }

        public boolean isLocal() {
            return this.isLocal;
        }

        public int targetTypeValue() {
            return this.targetTypeValue;
        }

        TargetType(int i) {
            this(i, false);
        }
    }
}
