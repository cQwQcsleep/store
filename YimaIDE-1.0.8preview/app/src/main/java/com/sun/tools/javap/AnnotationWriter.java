package com.sun.tools.javap;

import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.tools.classfile.Annotation;
import com.sun.tools.classfile.ConstantPoolException;
import com.sun.tools.classfile.Descriptor;
import com.sun.tools.classfile.TypeAnnotation;
import defpackage.s22;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class AnnotationWriter extends BasicWriter {
    private final ClassWriter classWriter;
    private final ConstantWriter constantWriter;
    element_value_Writer ev_writer;

    /* JADX INFO: renamed from: com.sun.tools.javap.AnnotationWriter$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType;

        static {
            int[] iArr = new int[TypeAnnotation.TargetType.values().length];
            $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType = iArr;
            try {
                iArr[TypeAnnotation.TargetType.INSTANCEOF.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TypeAnnotation.TargetType.NEW.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TypeAnnotation.TargetType.CONSTRUCTOR_REFERENCE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TypeAnnotation.TargetType.METHOD_REFERENCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TypeAnnotation.TargetType.LOCAL_VARIABLE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TypeAnnotation.TargetType.RESOURCE_VARIABLE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TypeAnnotation.TargetType.EXCEPTION_PARAMETER.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TypeAnnotation.TargetType.METHOD_RECEIVER.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TypeAnnotation.TargetType.CLASS_TYPE_PARAMETER.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TypeAnnotation.TargetType.METHOD_TYPE_PARAMETER.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TypeAnnotation.TargetType.CLASS_TYPE_PARAMETER_BOUND.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TypeAnnotation.TargetType.METHOD_TYPE_PARAMETER_BOUND.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TypeAnnotation.TargetType.CLASS_EXTENDS.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TypeAnnotation.TargetType.THROWS.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TypeAnnotation.TargetType.METHOD_FORMAL_PARAMETER.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TypeAnnotation.TargetType.CAST.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TypeAnnotation.TargetType.CONSTRUCTOR_INVOCATION_TYPE_ARGUMENT.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TypeAnnotation.TargetType.METHOD_INVOCATION_TYPE_ARGUMENT.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TypeAnnotation.TargetType.CONSTRUCTOR_REFERENCE_TYPE_ARGUMENT.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TypeAnnotation.TargetType.METHOD_REFERENCE_TYPE_ARGUMENT.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TypeAnnotation.TargetType.METHOD_RETURN.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TypeAnnotation.TargetType.FIELD.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[TypeAnnotation.TargetType.UNKNOWN.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
        }
    }

    public class element_value_Writer implements Annotation.element_value.Visitor<Void, Boolean> {
        public element_value_Writer() {
        }

        @Override // com.sun.tools.classfile.Annotation.element_value.Visitor
        public Void visitAnnotation(Annotation.Annotation_element_value annotation_element_value, Boolean bool) {
            AnnotationWriter.this.print(Character.valueOf((char) annotation_element_value.tag));
            AnnotationWriter.this.write(annotation_element_value.annotation_value, bool.booleanValue());
            return null;
        }

        @Override // com.sun.tools.classfile.Annotation.element_value.Visitor
        public Void visitArray(Annotation.Array_element_value array_element_value, Boolean bool) {
            AnnotationWriter.this.print("[");
            for (int i = 0; i < array_element_value.num_values; i++) {
                if (i > 0) {
                    AnnotationWriter.this.print(",");
                }
                write(array_element_value.values[i], bool.booleanValue());
            }
            AnnotationWriter.this.print("]");
            return null;
        }

        @Override // com.sun.tools.classfile.Annotation.element_value.Visitor
        public Void visitClass(Annotation.Class_element_value class_element_value, Boolean bool) {
            boolean zBooleanValue = bool.booleanValue();
            AnnotationWriter annotationWriter = AnnotationWriter.this;
            if (zBooleanValue) {
                annotationWriter.print("class ");
                AnnotationWriter.this.writeIndex(class_element_value.class_info_index, bool.booleanValue());
                return null;
            }
            annotationWriter.print(((char) class_element_value.tag) + "#" + class_element_value.class_info_index);
            return null;
        }

        @Override // com.sun.tools.classfile.Annotation.element_value.Visitor
        public Void visitEnum(Annotation.Enum_element_value enum_element_value, Boolean bool) {
            boolean zBooleanValue = bool.booleanValue();
            AnnotationWriter annotationWriter = AnnotationWriter.this;
            if (zBooleanValue) {
                annotationWriter.writeIndex(enum_element_value.type_name_index, bool.booleanValue());
                AnnotationWriter.this.print(Constants.ATTRVAL_THIS);
                AnnotationWriter.this.writeIndex(enum_element_value.const_name_index, bool.booleanValue());
                return null;
            }
            annotationWriter.print(((char) enum_element_value.tag) + "#" + enum_element_value.type_name_index + ".#" + enum_element_value.const_name_index);
            return null;
        }

        @Override // com.sun.tools.classfile.Annotation.element_value.Visitor
        public Void visitPrimitive(Annotation.Primitive_element_value primitive_element_value, Boolean bool) {
            if (!bool.booleanValue()) {
                AnnotationWriter.this.print(((char) primitive_element_value.tag) + "#" + primitive_element_value.const_value_index);
                return null;
            }
            int i = primitive_element_value.const_value_index;
            int i2 = primitive_element_value.tag;
            if (i2 != 70) {
                if (i2 == 83) {
                    AnnotationWriter.this.print("(short) ");
                    AnnotationWriter annotationWriter = AnnotationWriter.this;
                    annotationWriter.print(annotationWriter.constantWriter.stringValue(i));
                    return null;
                }
                if (i2 == 90) {
                    AnnotationWriter annotationWriter2 = AnnotationWriter.this;
                    annotationWriter2.print(annotationWriter2.constantWriter.booleanValue(i));
                    return null;
                }
                if (i2 == 115) {
                    AnnotationWriter.this.print("\"");
                    AnnotationWriter annotationWriter3 = AnnotationWriter.this;
                    annotationWriter3.print(annotationWriter3.constantWriter.stringValue(i));
                    AnnotationWriter.this.print("\"");
                    return null;
                }
                if (i2 != 73 && i2 != 74) {
                    switch (i2) {
                        case 66:
                            AnnotationWriter.this.print("(byte) ");
                            AnnotationWriter annotationWriter4 = AnnotationWriter.this;
                            annotationWriter4.print(annotationWriter4.constantWriter.stringValue(i));
                            break;
                        case 67:
                            AnnotationWriter.this.print("'");
                            AnnotationWriter annotationWriter5 = AnnotationWriter.this;
                            annotationWriter5.print(annotationWriter5.constantWriter.charValue(i));
                            AnnotationWriter.this.print("'");
                            break;
                        case 68:
                            break;
                        default:
                            AnnotationWriter.this.print(((char) primitive_element_value.tag) + "#" + primitive_element_value.const_value_index);
                            break;
                    }
                    return null;
                }
            }
            AnnotationWriter annotationWriter6 = AnnotationWriter.this;
            annotationWriter6.print(annotationWriter6.constantWriter.stringValue(i));
            return null;
        }

        public void write(Annotation.element_value element_valueVar, boolean z) {
            element_valueVar.accept(this, Boolean.valueOf(z));
        }
    }

    public AnnotationWriter(Context context) {
        super(context);
        this.ev_writer = new element_value_Writer();
        this.classWriter = ClassWriter.instance(context);
        this.constantWriter = ConstantWriter.instance(context);
    }

    public static AnnotationWriter instance(Context context) {
        AnnotationWriter annotationWriter = (AnnotationWriter) context.get(AnnotationWriter.class);
        return annotationWriter == null ? new AnnotationWriter(context) : annotationWriter;
    }

    private void writeDescriptor(int i, boolean z) {
        if (z) {
            try {
                print(new Descriptor(i).getFieldType(this.classWriter.getClassFile().constant_pool));
                return;
            } catch (ConstantPoolException | Descriptor.InvalidDescriptor unused) {
            }
        }
        print("#" + i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void writeIndex(int i, boolean z) {
        if (z) {
            print(this.constantWriter.stringValue(i));
            return;
        }
        print("#" + i);
    }

    public void write(TypeAnnotation.Position position, boolean z) {
        print(position.type);
        switch (AnonymousClass1.$SwitchMap$com$sun$tools$classfile$TypeAnnotation$TargetType[position.type.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
                if (z) {
                    print(", offset=");
                    print(Integer.valueOf(position.offset));
                }
                break;
            case 5:
            case 6:
                if (position.lvarOffset == null) {
                    print(", lvarOffset is Null!");
                } else {
                    print(", {");
                    for (int i = 0; i < position.lvarOffset.length; i++) {
                        if (i != 0) {
                            print("; ");
                        }
                        if (z) {
                            print("start_pc=");
                            print(Integer.valueOf(position.lvarOffset[i]));
                        }
                        print(", length=");
                        print(Integer.valueOf(position.lvarLength[i]));
                        print(", index=");
                        print(Integer.valueOf(position.lvarIndex[i]));
                    }
                    print("}");
                }
                break;
            case 7:
                print(", exception_index=");
                print(Integer.valueOf(position.exception_index));
                break;
            case 8:
            case 21:
            case 22:
                break;
            case 9:
            case 10:
                print(", param_index=");
                print(Integer.valueOf(position.parameter_index));
                break;
            case 11:
            case 12:
                print(", param_index=");
                print(Integer.valueOf(position.parameter_index));
                print(", bound_index=");
                print(Integer.valueOf(position.bound_index));
                break;
            case 13:
                print(", type_index=");
                print(Integer.valueOf(position.type_index));
                break;
            case 14:
                print(", type_index=");
                print(Integer.valueOf(position.type_index));
                break;
            case 15:
                print(", param_index=");
                print(Integer.valueOf(position.parameter_index));
                break;
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
                if (z) {
                    print(", offset=");
                    print(Integer.valueOf(position.offset));
                }
                print(", type_index=");
                print(Integer.valueOf(position.type_index));
                break;
            case 23:
                x01.a("AnnotationWriter: UNKNOWN target type should never occur!");
                return;
            default:
                s22.a("AnnotationWriter: Unknown target type for position: ", position);
                return;
        }
        if (position.location.isEmpty()) {
            return;
        }
        print(", location=");
        print(position.location);
    }

    public void write(Annotation annotation, boolean z) {
        writeDescriptor(annotation.type_index, z);
        if (z) {
            boolean z2 = annotation.num_element_value_pairs > 0;
            if (z2) {
                println("(");
                indent(1);
            }
            for (int i = 0; i < annotation.num_element_value_pairs; i++) {
                write(annotation.element_value_pairs[i], true);
                println();
            }
            if (z2) {
                indent(-1);
                print(")");
                return;
            }
            return;
        }
        print("(");
        for (int i2 = 0; i2 < annotation.num_element_value_pairs; i2++) {
            if (i2 > 0) {
                print(",");
            }
            write(annotation.element_value_pairs[i2], false);
        }
        print(")");
    }

    public void write(TypeAnnotation typeAnnotation) {
        write(typeAnnotation, true, false);
        println();
        indent(1);
        write(typeAnnotation.annotation, true);
        indent(-1);
    }

    public void write(TypeAnnotation typeAnnotation, boolean z, boolean z2) {
        write(typeAnnotation.annotation, z2);
        print(": ");
        write(typeAnnotation.position, z);
    }

    public void write(Annotation annotation) {
        write(annotation, false);
        println();
        indent(1);
        write(annotation, true);
        indent(-1);
    }

    public void write(Annotation.element_value_pair element_value_pairVar, boolean z) {
        writeIndex(element_value_pairVar.element_name_index, z);
        print("=");
        write(element_value_pairVar.value, z);
    }

    public void write(Annotation.element_value element_valueVar) {
        write(element_valueVar, false);
        println();
        indent(1);
        write(element_valueVar, true);
        indent(-1);
    }

    public void write(Annotation.element_value element_valueVar, boolean z) {
        this.ev_writer.write(element_valueVar, z);
    }
}
