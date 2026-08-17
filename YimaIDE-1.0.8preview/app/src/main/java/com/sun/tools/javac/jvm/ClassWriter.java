package com.sun.tools.javac.jvm;

import com.intellij.psi.PsiKeyword;
import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.tools.javac.code.Attribute;
import com.sun.tools.javac.code.Directive;
import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.code.Kinds;
import com.sun.tools.javac.code.Preview;
import com.sun.tools.javac.code.Scope;
import com.sun.tools.javac.code.Source;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.TargetType;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.code.TypeAnnotationPosition;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.code.Types;
import com.sun.tools.javac.comp.Check;
import com.sun.tools.javac.file.PathFileObject;
import com.sun.tools.javac.jvm.ClassWriter;
import com.sun.tools.javac.main.Option;
import com.sun.tools.javac.resources.CompilerProperties;
import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.ByteBuffer;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;
import com.sun.tools.javac.util.Options;
import com.sun.tools.javac.util.Pair;
import defpackage.s22;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import javax.tools.FileObject;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.StandardLocation;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ClassWriter extends ClassFile {
    static final int CLASS_BUF_SIZE = 131056;
    static final int DATA_BUF_SIZE = 65520;
    static final int FULL_FRAME = 255;
    static final int MAX_LOCAL_LENGTH_DIFF = 4;
    static final int SAME_FRAME_EXTENDED = 251;
    static final int SAME_FRAME_SIZE = 64;
    static final int SAME_LOCALS_1_STACK_ITEM_EXTENDED = 247;
    protected static final Context.Key<ClassWriter> classWriterKey = new Context.Key<>();
    private static final String[] flagName = {"PUBLIC", "PRIVATE", "PROTECTED", "STATIC", "FINAL", "SUPER", "VOLATILE", "TRANSIENT", "NATIVE", "INTERFACE", "ABSTRACT", "STRICTFP"};
    private Check check;
    private boolean debugstackmap;
    private boolean dumpClassModifiers;
    private boolean dumpFieldModifiers;
    private boolean dumpInnerClassModifiers;
    private boolean dumpMethodModifiers;
    private boolean emitSourceFile;
    private final JavaFileManager fileManager;
    private boolean genCrt;
    private final Log log;
    public boolean multiModuleMode;
    private final Names names;
    private final Options options;
    final PoolWriter poolWriter;
    private Preview preview;
    private Source source;
    private Target target;
    private Types types;
    private boolean verbose;
    private List<ToIntFunction<Symbol>> extraAttributeHooks = List.nil();
    public ByteBuffer databuf = new ByteBuffer(65520);
    ByteBuffer poolbuf = new ByteBuffer(CLASS_BUF_SIZE);
    AttributeWriter awriter = new AttributeWriter();

    /* JADX INFO: renamed from: com.sun.tools.javac.jvm.ClassWriter$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$code$Attribute$RetentionPolicy;
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$code$Kinds$Kind;
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$code$TargetType;
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$code$TypeTag;
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$jvm$Code$StackMapFormat;

        static {
            int[] iArr = new int[Kinds.Kind.values().length];
            $SwitchMap$com$sun$tools$javac$code$Kinds$Kind = iArr;
            try {
                iArr[Kinds.Kind.VAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$Kinds$Kind[Kinds.Kind.MTH.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$Kinds$Kind[Kinds.Kind.TYP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[Code.StackMapFormat.values().length];
            $SwitchMap$com$sun$tools$javac$jvm$Code$StackMapFormat = iArr2;
            try {
                iArr2[Code.StackMapFormat.CLDC.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$jvm$Code$StackMapFormat[Code.StackMapFormat.JSR202.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr3 = new int[TargetType.values().length];
            $SwitchMap$com$sun$tools$javac$code$TargetType = iArr3;
            try {
                iArr3[TargetType.INSTANCEOF.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.NEW.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.CONSTRUCTOR_REFERENCE.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.METHOD_REFERENCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.LOCAL_VARIABLE.ordinal()] = 5;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.RESOURCE_VARIABLE.ordinal()] = 6;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.EXCEPTION_PARAMETER.ordinal()] = 7;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.METHOD_RECEIVER.ordinal()] = 8;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.CLASS_TYPE_PARAMETER.ordinal()] = 9;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.METHOD_TYPE_PARAMETER.ordinal()] = 10;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.CLASS_TYPE_PARAMETER_BOUND.ordinal()] = 11;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.METHOD_TYPE_PARAMETER_BOUND.ordinal()] = 12;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.CLASS_EXTENDS.ordinal()] = 13;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.THROWS.ordinal()] = 14;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.METHOD_FORMAL_PARAMETER.ordinal()] = 15;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.CAST.ordinal()] = 16;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.CONSTRUCTOR_INVOCATION_TYPE_ARGUMENT.ordinal()] = 17;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.METHOD_INVOCATION_TYPE_ARGUMENT.ordinal()] = 18;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.CONSTRUCTOR_REFERENCE_TYPE_ARGUMENT.ordinal()] = 19;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.METHOD_REFERENCE_TYPE_ARGUMENT.ordinal()] = 20;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.METHOD_RETURN.ordinal()] = 21;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.FIELD.ordinal()] = 22;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.UNKNOWN.ordinal()] = 23;
            } catch (NoSuchFieldError unused28) {
            }
            int[] iArr4 = new int[TypeTag.values().length];
            $SwitchMap$com$sun$tools$javac$code$TypeTag = iArr4;
            try {
                iArr4[TypeTag.BYTE.ordinal()] = 1;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.CHAR.ordinal()] = 2;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.SHORT.ordinal()] = 3;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.INT.ordinal()] = 4;
            } catch (NoSuchFieldError unused32) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.LONG.ordinal()] = 5;
            } catch (NoSuchFieldError unused33) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.FLOAT.ordinal()] = 6;
            } catch (NoSuchFieldError unused34) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.DOUBLE.ordinal()] = 7;
            } catch (NoSuchFieldError unused35) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.BOOLEAN.ordinal()] = 8;
            } catch (NoSuchFieldError unused36) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.BOT.ordinal()] = 9;
            } catch (NoSuchFieldError unused37) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.CLASS.ordinal()] = 10;
            } catch (NoSuchFieldError unused38) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.ARRAY.ordinal()] = 11;
            } catch (NoSuchFieldError unused39) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.TYPEVAR.ordinal()] = 12;
            } catch (NoSuchFieldError unused40) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.UNINITIALIZED_THIS.ordinal()] = 13;
            } catch (NoSuchFieldError unused41) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.UNINITIALIZED_OBJECT.ordinal()] = 14;
            } catch (NoSuchFieldError unused42) {
            }
            int[] iArr5 = new int[Attribute.RetentionPolicy.values().length];
            $SwitchMap$com$sun$tools$javac$code$Attribute$RetentionPolicy = iArr5;
            try {
                iArr5[Attribute.RetentionPolicy.SOURCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused43) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$Attribute$RetentionPolicy[Attribute.RetentionPolicy.CLASS.ordinal()] = 2;
            } catch (NoSuchFieldError unused44) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$Attribute$RetentionPolicy[Attribute.RetentionPolicy.RUNTIME.ordinal()] = 3;
            } catch (NoSuchFieldError unused45) {
            }
        }
    }

    public class AttributeWriter implements Attribute.Visitor {
        public AttributeWriter() {
        }

        @Override // com.sun.tools.javac.code.Attribute.Visitor
        public void visitArray(Attribute.Array array) {
            ClassWriter.this.databuf.appendByte(91);
            ClassWriter.this.databuf.appendChar(array.values.length);
            for (Attribute attribute : array.values) {
                attribute.accept(this);
            }
        }

        @Override // com.sun.tools.javac.code.Attribute.Visitor
        public void visitClass(Attribute.Class r3) {
            ClassWriter.this.databuf.appendByte(99);
            ClassWriter classWriter = ClassWriter.this;
            classWriter.databuf.appendChar(classWriter.poolWriter.putDescriptor(r3.classType));
        }

        @Override // com.sun.tools.javac.code.Attribute.Visitor
        public void visitCompound(Attribute.Compound compound) {
            ClassWriter.this.databuf.appendByte(64);
            ClassWriter.this.writeCompoundAttribute(compound);
        }

        @Override // com.sun.tools.javac.code.Attribute.Visitor
        public void visitConstant(Attribute.Constant constant) {
            if (constant.type.getTag() == TypeTag.CLASS) {
                Assert.check(constant.value instanceof String);
                String str = (String) constant.value;
                ClassWriter.this.databuf.appendByte(115);
                ClassWriter classWriter = ClassWriter.this;
                classWriter.databuf.appendChar(classWriter.poolWriter.putName(classWriter.names.fromString(str)));
                return;
            }
            switch (AnonymousClass1.$SwitchMap$com$sun$tools$javac$code$TypeTag[constant.type.getTag().ordinal()]) {
                case 1:
                    ClassWriter.this.databuf.appendByte(66);
                    break;
                case 2:
                    ClassWriter.this.databuf.appendByte(67);
                    break;
                case 3:
                    ClassWriter.this.databuf.appendByte(83);
                    break;
                case 4:
                    ClassWriter.this.databuf.appendByte(73);
                    break;
                case 5:
                    ClassWriter.this.databuf.appendByte(74);
                    break;
                case 6:
                    ClassWriter.this.databuf.appendByte(70);
                    break;
                case 7:
                    ClassWriter.this.databuf.appendByte(68);
                    break;
                case 8:
                    ClassWriter.this.databuf.appendByte(90);
                    break;
                default:
                    x01.a(constant.type);
                    return;
            }
            ClassWriter classWriter2 = ClassWriter.this;
            classWriter2.databuf.appendChar(classWriter2.poolWriter.putConstant(constant.value));
        }

        @Override // com.sun.tools.javac.code.Attribute.Visitor
        public void visitEnum(Attribute.Enum r4) {
            ClassWriter.this.databuf.appendByte(101);
            ClassWriter classWriter = ClassWriter.this;
            classWriter.databuf.appendChar(classWriter.poolWriter.putDescriptor(r4.value.type));
            ClassWriter classWriter2 = ClassWriter.this;
            classWriter2.databuf.appendChar(classWriter2.poolWriter.putName(r4.value.name));
        }

        @Override // com.sun.tools.javac.code.Attribute.Visitor
        public void visitError(Attribute.Error error) {
            throw new AssertionError(error);
        }
    }

    public static class PoolOverflow extends RuntimeException {
        private static final long serialVersionUID = 0;
    }

    public static abstract class StackMapTableFrame {

        public static class AppendFrame extends StackMapTableFrame {
            final int frameType;
            final Type[] locals;
            final int offsetDelta;

            public AppendFrame(int i, int i2, Type[] typeArr) {
                this.frameType = i;
                this.offsetDelta = i2;
                this.locals = typeArr;
            }

            @Override // com.sun.tools.javac.jvm.ClassWriter.StackMapTableFrame
            public int getFrameType() {
                return this.frameType;
            }

            @Override // com.sun.tools.javac.jvm.ClassWriter.StackMapTableFrame
            public void write(ClassWriter classWriter) {
                super.write(classWriter);
                classWriter.databuf.appendChar(this.offsetDelta);
                if (classWriter.debugstackmap) {
                    System.out.print(" offset_delta=" + this.offsetDelta);
                }
                for (int i = 0; i < this.locals.length; i++) {
                    if (classWriter.debugstackmap) {
                        System.out.print(" locals[" + i + "]=");
                    }
                    classWriter.writeStackMapType(this.locals[i]);
                }
            }
        }

        public static class ChopFrame extends StackMapTableFrame {
            final int frameType;
            final int offsetDelta;

            public ChopFrame(int i, int i2) {
                this.frameType = i;
                this.offsetDelta = i2;
            }

            @Override // com.sun.tools.javac.jvm.ClassWriter.StackMapTableFrame
            public int getFrameType() {
                return this.frameType;
            }

            @Override // com.sun.tools.javac.jvm.ClassWriter.StackMapTableFrame
            public void write(ClassWriter classWriter) {
                super.write(classWriter);
                classWriter.databuf.appendChar(this.offsetDelta);
                if (classWriter.debugstackmap) {
                    System.out.print(" offset_delta=" + this.offsetDelta);
                }
            }
        }

        public static class FullFrame extends StackMapTableFrame {
            final Type[] locals;
            final int offsetDelta;
            final Type[] stack;

            public FullFrame(int i, Type[] typeArr, Type[] typeArr2) {
                this.offsetDelta = i;
                this.locals = typeArr;
                this.stack = typeArr2;
            }

            @Override // com.sun.tools.javac.jvm.ClassWriter.StackMapTableFrame
            public int getFrameType() {
                return 255;
            }

            @Override // com.sun.tools.javac.jvm.ClassWriter.StackMapTableFrame
            public void write(ClassWriter classWriter) {
                super.write(classWriter);
                classWriter.databuf.appendChar(this.offsetDelta);
                classWriter.databuf.appendChar(this.locals.length);
                if (classWriter.debugstackmap) {
                    System.out.print(" offset_delta=" + this.offsetDelta);
                    System.out.print(" nlocals=" + this.locals.length);
                }
                for (int i = 0; i < this.locals.length; i++) {
                    if (classWriter.debugstackmap) {
                        System.out.print(" locals[" + i + "]=");
                    }
                    classWriter.writeStackMapType(this.locals[i]);
                }
                classWriter.databuf.appendChar(this.stack.length);
                if (classWriter.debugstackmap) {
                    System.out.print(" nstack=" + this.stack.length);
                }
                for (int i2 = 0; i2 < this.stack.length; i2++) {
                    if (classWriter.debugstackmap) {
                        System.out.print(" stack[" + i2 + "]=");
                    }
                    classWriter.writeStackMapType(this.stack[i2]);
                }
            }
        }

        public static class SameFrame extends StackMapTableFrame {
            final int offsetDelta;

            public SameFrame(int i) {
                this.offsetDelta = i;
            }

            @Override // com.sun.tools.javac.jvm.ClassWriter.StackMapTableFrame
            public int getFrameType() {
                int i = this.offsetDelta;
                if (i < 64) {
                    return i;
                }
                return 251;
            }

            @Override // com.sun.tools.javac.jvm.ClassWriter.StackMapTableFrame
            public void write(ClassWriter classWriter) {
                super.write(classWriter);
                if (getFrameType() == 251) {
                    classWriter.databuf.appendChar(this.offsetDelta);
                    if (classWriter.debugstackmap) {
                        System.out.print(" offset_delta=" + this.offsetDelta);
                    }
                }
            }
        }

        public static class SameLocals1StackItemFrame extends StackMapTableFrame {
            final int offsetDelta;
            final Type stack;

            public SameLocals1StackItemFrame(int i, Type type) {
                this.offsetDelta = i;
                this.stack = type;
            }

            @Override // com.sun.tools.javac.jvm.ClassWriter.StackMapTableFrame
            public int getFrameType() {
                int i = this.offsetDelta;
                if (i < 64) {
                    return i + 64;
                }
                return 247;
            }

            @Override // com.sun.tools.javac.jvm.ClassWriter.StackMapTableFrame
            public void write(ClassWriter classWriter) {
                super.write(classWriter);
                if (getFrameType() == 247) {
                    classWriter.databuf.appendChar(this.offsetDelta);
                    if (classWriter.debugstackmap) {
                        System.out.print(" offset_delta=" + this.offsetDelta);
                    }
                }
                if (classWriter.debugstackmap) {
                    System.out.print(" stack[0]=");
                }
                classWriter.writeStackMapType(this.stack);
            }
        }

        public static int compare(Type[] typeArr, Type[] typeArr2, Types types) {
            int length = typeArr.length - typeArr2.length;
            if (length > 4 || length < -4) {
                return Integer.MAX_VALUE;
            }
            int length2 = length > 0 ? typeArr2.length : typeArr.length;
            for (int i = 0; i < length2; i++) {
                if (!isSameType(typeArr[i], typeArr2[i], types)) {
                    return Integer.MAX_VALUE;
                }
            }
            return length;
        }

        public static StackMapTableFrame getInstance(Code.StackMapFrame stackMapFrame, int i, Type[] typeArr, Types types) {
            Type[] typeArr2 = stackMapFrame.locals;
            Type[] typeArr3 = stackMapFrame.stack;
            int i2 = (stackMapFrame.pc - i) - 1;
            int i3 = 0;
            if (typeArr3.length == 1) {
                if (typeArr2.length == typeArr.length && compare(typeArr, typeArr2, types) == 0) {
                    return new SameLocals1StackItemFrame(i2, typeArr3[0]);
                }
            } else if (typeArr3.length == 0) {
                int iCompare = compare(typeArr, typeArr2, types);
                if (iCompare == 0) {
                    return new SameFrame(i2);
                }
                if (-4 < iCompare && iCompare < 0) {
                    Type[] typeArr4 = new Type[-iCompare];
                    int length = typeArr.length;
                    while (length < typeArr2.length) {
                        typeArr4[i3] = typeArr2[length];
                        length++;
                        i3++;
                    }
                    return new AppendFrame(251 - iCompare, i2, typeArr4);
                }
                if (iCompare > 0 && iCompare < 4) {
                    return new ChopFrame(251 - iCompare, i2);
                }
            }
            return new FullFrame(i2, typeArr2, typeArr3);
        }

        public static boolean isInt(Type type) {
            return type.getTag().isStrictSubRangeOf(TypeTag.INT) || type.hasTag(TypeTag.BOOLEAN);
        }

        public static boolean isSameType(Type type, Type type2, Types types) {
            if (type == null) {
                return type2 == null;
            }
            if (type2 == null) {
                return false;
            }
            if (isInt(type) && isInt(type2)) {
                return true;
            }
            TypeTag typeTag = TypeTag.UNINITIALIZED_THIS;
            if (type.hasTag(typeTag)) {
                return type2.hasTag(typeTag);
            }
            TypeTag typeTag2 = TypeTag.UNINITIALIZED_OBJECT;
            if (type.hasTag(typeTag2)) {
                return type2.hasTag(typeTag2) && ((UninitializedType) type).offset == ((UninitializedType) type2).offset;
            }
            if (type2.hasTag(typeTag) || type2.hasTag(typeTag2)) {
                return false;
            }
            return types.isSameType(type, type2);
        }

        public abstract int getFrameType();

        public void write(ClassWriter classWriter) {
            int frameType = getFrameType();
            classWriter.databuf.appendByte(frameType);
            if (classWriter.debugstackmap) {
                System.out.print(" frame_type=" + frameType);
            }
        }
    }

    public static class StringOverflow extends RuntimeException {
        private static final long serialVersionUID = 0;
        public final String value;

        public StringOverflow(String str) {
            this.value = str;
        }
    }

    public ClassWriter(Context context) {
        context.put(classWriterKey, this);
        this.log = Log.instance(context);
        this.names = Names.instance(context);
        Options optionsInstance = Options.instance(context);
        this.options = optionsInstance;
        this.preview = Preview.instance(context);
        this.target = Target.instance(context);
        this.source = Source.instance(context);
        this.types = Types.instance(context);
        this.check = Check.instance(context);
        this.fileManager = (JavaFileManager) context.get(JavaFileManager.class);
        this.poolWriter = Gen.instance(context).poolWriter;
        this.verbose = optionsInstance.isSet(Option.VERBOSE);
        this.genCrt = optionsInstance.isSet(Option.XJCOV);
        this.debugstackmap = optionsInstance.isSet("debug.stackmap");
        Option option = Option.G_CUSTOM;
        this.emitSourceFile = optionsInstance.isUnset(option) || optionsInstance.isSet(option, "source");
        String str = optionsInstance.get("debug.dumpmodifiers");
        if (str != null) {
            this.dumpClassModifiers = str.indexOf(99) != -1;
            this.dumpFieldModifiers = str.indexOf(102) != -1;
            this.dumpInnerClassModifiers = str.indexOf(105) != -1;
            this.dumpMethodModifiers = str.indexOf(109) != -1;
        }
    }

    public static /* synthetic */ boolean a(Symbol.VarSymbol varSymbol) {
        return (varSymbol.flags_field & 36864) != 0;
    }

    public static /* synthetic */ Set b(Symbol.ClassSymbol classSymbol) {
        return new LinkedHashSet();
    }

    private void checkAnnotationArraySizeInternal(Pair<Symbol.MethodSymbol, Attribute> pair) {
        Attribute attribute = pair.snd;
        if (!(attribute instanceof Attribute.Array) || ((Attribute.Array) attribute).values.length <= 65535) {
            return;
        }
        this.log.error(CompilerProperties.Errors.AnnotationArrayTooLarge(pair.fst.owner));
    }

    public static /* synthetic */ boolean d(Symbol.VarSymbol varSymbol) {
        return (varSymbol.flags_field & 36864) != 0;
    }

    public static /* synthetic */ void e(final ClassWriter classWriter, Symbol.ClassSymbol classSymbol, Set set) {
        classWriter.databuf.appendChar(classWriter.poolWriter.putClass(classSymbol));
        classWriter.databuf.appendChar(set.size());
        set.forEach(new Consumer() { // from class: tx1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ClassWriter classWriter2 = this.b;
                classWriter2.databuf.appendChar(classWriter2.poolWriter.putClass((Symbol.ClassSymbol) obj));
            }
        });
    }

    public static String flagNames(long j) {
        StringBuilder sb = new StringBuilder();
        long j2 = j & 4095;
        int i = 0;
        while (j2 != 0) {
            if ((1 & j2) != 0) {
                sb.append(" ");
                sb.append(flagName[i]);
            }
            j2 >>= 1;
            i++;
        }
        return sb.toString();
    }

    public static ClassWriter instance(Context context) {
        ClassWriter classWriter = (ClassWriter) context.get(classWriterKey);
        return classWriter == null ? new ClassWriter(context) : classWriter;
    }

    private void listNested(Symbol symbol, ListBuffer<Symbol.ClassSymbol> listBuffer) {
        if (symbol.kind != Kinds.Kind.TYP) {
            return;
        }
        Symbol.ClassSymbol classSymbol = (Symbol.ClassSymbol) symbol;
        if (classSymbol.owner.kind != Kinds.Kind.PCK) {
            listBuffer.add(classSymbol);
        }
        if (classSymbol.members() != null) {
            Iterator<Symbol> it = symbol.members().getSymbols().iterator();
            while (it.hasNext()) {
                listNested(it.next(), listBuffer);
            }
        }
        List<Symbol.ClassSymbol> list = classSymbol.trans_local;
        if (list != null) {
            Iterator<Symbol.ClassSymbol> it2 = list.iterator();
            while (it2.hasNext()) {
                listNested(it2.next(), listBuffer);
            }
        }
    }

    private boolean needsLocalVariableTypeEntry(Type type) {
        Types types = this.types;
        return !types.isSameType(type, types.erasure(type)) && this.check.checkDenotable(type);
    }

    private boolean requiresParamFlags(Symbol.MethodSymbol methodSymbol) {
        if (!methodSymbol.extraParams.isEmpty()) {
            return methodSymbol.extraParams.stream().anyMatch(new Predicate() { // from class: ux1
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ClassWriter.a((Symbol.VarSymbol) obj);
                }
            });
        }
        List<Symbol.VarSymbol> list = methodSymbol.params;
        if (list != null) {
            return list.stream().anyMatch(new Predicate() { // from class: vx1
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ClassWriter.d((Symbol.VarSymbol) obj);
                }
            });
        }
        return false;
    }

    private boolean requiresParamNames(Symbol.MethodSymbol methodSymbol) {
        if (this.options.isSet(Option.PARAMETERS)) {
            return true;
        }
        return methodSymbol.isConstructor() && (methodSymbol.flags_field & Flags.RECORD) != 0;
    }

    private void writeParamAnnotations(List<Symbol.VarSymbol> list, Attribute.RetentionPolicy retentionPolicy) {
        this.databuf.appendByte(list.length());
        for (Symbol.VarSymbol varSymbol : list) {
            ListBuffer listBuffer = new ListBuffer();
            for (Attribute.Compound compound : varSymbol.getRawAttributes()) {
                if (this.types.getRetention(compound) == retentionPolicy) {
                    listBuffer.append(compound);
                }
            }
            this.databuf.appendChar(listBuffer.length());
            Iterator it = listBuffer.iterator();
            while (it.hasNext()) {
                writeCompoundAttribute((Attribute.Compound) it.next());
            }
        }
    }

    public void addExtraAttributes(ToIntFunction<Symbol> toIntFunction) {
        this.extraAttributeHooks = this.extraAttributeHooks.prepend(toIntFunction);
    }

    public int adjustFlags(long j) {
        int i = (int) j;
        if (this.target.obsoleteAccStrict()) {
            i &= -2049;
        }
        if ((Flags.BRIDGE & j) != 0) {
            i |= 64;
        }
        if ((Flags.VARARGS & j) != 0) {
            i |= 128;
        }
        return (j & Flags.DEFAULT) != 0 ? i & (-1025) : i;
    }

    public int beginAttrs() {
        this.databuf.appendChar(0);
        return this.databuf.length;
    }

    public void endAttr(int i) {
        ByteBuffer byteBuffer = this.databuf;
        putInt(byteBuffer, i - 4, byteBuffer.length - i);
    }

    public void endAttrs(int i, int i2) {
        putChar(this.databuf, i - 2, i2);
    }

    public long getLastModified(FileObject fileObject) {
        return fileObject.getLastModified();
    }

    public void putChar(ByteBuffer byteBuffer, int i, int i2) {
        byte[] bArr = byteBuffer.elems;
        bArr[i] = (byte) ((i2 >> 8) & 255);
        bArr[i + 1] = (byte) (i2 & 255);
    }

    public void putInt(ByteBuffer byteBuffer, int i, int i2) {
        byte[] bArr = byteBuffer.elems;
        bArr[i] = (byte) ((i2 >> 24) & 255);
        bArr[i + 1] = (byte) ((i2 >> 16) & 255);
        bArr[i + 2] = (byte) ((i2 >> 8) & 255);
        bArr[i + 3] = (byte) (i2 & 255);
    }

    public int writeAttr(Name name) {
        this.databuf.appendChar(this.poolWriter.putName(name));
        this.databuf.appendInt(0);
        return this.databuf.length;
    }

    public void writeBootstrapMethods() {
        int size;
        int iWriteAttr = writeAttr(this.names.BootstrapMethods);
        do {
            size = this.poolWriter.bootstrapMethods.size();
            Iterator it = Collections.unmodifiableList(new ArrayList(this.poolWriter.bootstrapMethods.keySet())).iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                for (PoolConstant.LoadableConstant loadableConstant : ((PoolConstant.Dynamic.BsmKey) it.next()).staticArgs) {
                    this.poolWriter.putConstant(loadableConstant);
                }
            }
        } while (size < this.poolWriter.bootstrapMethods.size());
        this.databuf.appendChar(this.poolWriter.bootstrapMethods.size());
        for (PoolConstant.Dynamic.BsmKey bsmKey : this.poolWriter.bootstrapMethods.keySet()) {
            this.databuf.appendChar(this.poolWriter.putConstant(bsmKey.bsm));
            PoolConstant.LoadableConstant[] loadableConstantArr = bsmKey.staticArgs;
            this.databuf.appendChar(loadableConstantArr.length);
            for (PoolConstant.LoadableConstant loadableConstant2 : loadableConstantArr) {
                this.databuf.appendChar(this.poolWriter.putConstant(loadableConstant2));
            }
        }
        endAttr(iWriteAttr);
    }

    public JavaFileObject writeClass(Symbol.ClassSymbol classSymbol) throws StringOverflow, IOException, PoolOverflow {
        JavaFileManager.Location locationForModule;
        Kinds.Kind kind = classSymbol.owner.kind;
        Kinds.Kind kind2 = Kinds.Kind.MDL;
        String string = (kind == kind2 ? classSymbol.name : classSymbol.flatname).toString();
        if (this.multiModuleMode) {
            Symbol symbol = classSymbol.owner;
            locationForModule = this.fileManager.getLocationForModule(StandardLocation.CLASS_OUTPUT, (symbol.kind == kind2 ? (Symbol.ModuleSymbol) symbol : classSymbol.packge().modle).name.toString());
        } else {
            locationForModule = StandardLocation.CLASS_OUTPUT;
        }
        JavaFileObject javaFileForOutput = this.fileManager.getJavaFileForOutput(locationForModule, string, JavaFileObject.Kind.CLASS, classSymbol.sourcefile);
        OutputStream outputStreamOpenOutputStream = javaFileForOutput.openOutputStream();
        try {
            try {
                writeClassFile(outputStreamOpenOutputStream, classSymbol);
                if (this.verbose) {
                    this.log.printVerbose("wrote.file", javaFileForOutput.getName());
                }
                outputStreamOpenOutputStream.close();
                return javaFileForOutput;
            } catch (Types.SignatureGenerator.InvalidSignatureException e) {
                this.log.error(CompilerProperties.Errors.CannotGenerateClass(classSymbol, CompilerProperties.Fragments.IllegalSignature(classSymbol, e.type())));
                if (outputStreamOpenOutputStream == null) {
                    return javaFileForOutput;
                }
                outputStreamOpenOutputStream.close();
                javaFileForOutput.delete();
                return null;
            }
        } catch (Throwable th) {
            if (outputStreamOpenOutputStream != null) {
                outputStreamOpenOutputStream.close();
                javaFileForOutput.delete();
            }
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void writeClassFile(OutputStream outputStream, Symbol.ClassSymbol classSymbol) throws StringOverflow, IOException, PoolOverflow {
        int i;
        int i2 = 1;
        Assert.check((classSymbol.flags() & 16777216) == 0);
        this.databuf.reset();
        this.poolbuf.reset();
        Type typeSupertype = this.types.supertype(classSymbol.type);
        List listInterfaces = this.types.interfaces(classSymbol.type);
        List<Type> typeArguments = classSymbol.type.getTypeArguments();
        Kinds.Kind kind = classSymbol.owner.kind;
        Kinds.Kind kind2 = Kinds.Kind.MDL;
        if (kind == kind2) {
            i = 32768;
        } else {
            int iAdjustFlags = adjustFlags(classSymbol.flags() & (-8796093022209L));
            if ((iAdjustFlags & 4) != 0) {
                iAdjustFlags |= 1;
            }
            int i3 = iAdjustFlags & 30225;
            i = (iAdjustFlags & 512) == 0 ? i3 | 32 : i3;
        }
        if (this.dumpClassModifiers) {
            PrintWriter writer = this.log.getWriter(Log.WriterKind.ERROR);
            writer.println();
            writer.println("CLASSFILE  " + classSymbol.getQualifiedName());
            writer.println("---" + flagNames((long) i));
        }
        this.databuf.appendChar(i);
        Symbol symbol = classSymbol.owner;
        if (symbol.kind == kind2) {
            this.databuf.appendChar(this.poolWriter.putClass(new Symbol.ClassSymbol(0L, this.names.module_info, ((Symbol.ModuleSymbol) symbol).unnamedPackage)));
        } else {
            this.databuf.appendChar(this.poolWriter.putClass(classSymbol));
        }
        this.databuf.appendChar(typeSupertype.hasTag(TypeTag.CLASS) ? this.poolWriter.putClass((Symbol.ClassSymbol) typeSupertype.tsym) : 0);
        this.databuf.appendChar(listInterfaces.length());
        for (List list = listInterfaces; list.nonEmpty(); list = list.tail) {
            this.databuf.appendChar(this.poolWriter.putClass((Symbol.ClassSymbol) ((Type) list.head).tsym));
        }
        int i4 = 0;
        int i5 = 0;
        for (Symbol symbol2 : classSymbol.members().getSymbols(Scope.LookupKind.NON_RECURSIVE)) {
            int i6 = AnonymousClass1.$SwitchMap$com$sun$tools$javac$code$Kinds$Kind[symbol2.kind.ordinal()];
            if (i6 == 1) {
                i4++;
            } else if (i6 != 2) {
                if (i6 != 3) {
                    Assert.error();
                } else {
                    this.poolWriter.enterInner((Symbol.ClassSymbol) symbol2);
                }
            } else if ((symbol2.flags() & Flags.HYPOTHETICAL) == 0) {
                i5++;
            }
        }
        List<Symbol.ClassSymbol> list2 = classSymbol.trans_local;
        if (list2 != null) {
            Iterator<Symbol.ClassSymbol> it = list2.iterator();
            while (it.hasNext()) {
                this.poolWriter.enterInner(it.next());
            }
        }
        this.databuf.appendChar(i4);
        writeFields(classSymbol.members());
        this.databuf.appendChar(i5);
        writeMethods(classSymbol.members());
        int iBeginAttrs = beginAttrs();
        boolean z = (typeArguments.length() == 0 && typeSupertype.allparams().length() == 0) ? false : true;
        while (!z && listInterfaces.nonEmpty()) {
            z = ((Type) listInterfaces.head).allparams().length() != 0;
            listInterfaces = listInterfaces.tail;
        }
        if (z) {
            int iWriteAttr = writeAttr(this.names.Signature);
            this.databuf.appendChar(this.poolWriter.putSignature(classSymbol));
            endAttr(iWriteAttr);
        } else {
            i2 = 0;
        }
        if (classSymbol.sourcefile != null && this.emitSourceFile) {
            int iWriteAttr2 = writeAttr(this.names.SourceFile);
            this.databuf.appendChar(this.poolWriter.putName(this.names.fromString(PathFileObject.getSimpleName(classSymbol.sourcefile))));
            endAttr(iWriteAttr2);
            i2++;
        }
        if (this.genCrt) {
            int iWriteAttr3 = writeAttr(this.names.SourceID);
            this.databuf.appendChar(this.poolWriter.putName(this.names.fromString(Long.toString(getLastModified(classSymbol.sourcefile)))));
            endAttr(iWriteAttr3);
            int iWriteAttr4 = writeAttr(this.names.CompilationID);
            this.databuf.appendChar(this.poolWriter.putName(this.names.fromString(Long.toString(System.currentTimeMillis()))));
            endAttr(iWriteAttr4);
            i2 += 2;
        }
        int iWriteFlagAttrs = i2 + writeFlagAttrs(classSymbol.flags()) + writeJavaAnnotations(classSymbol.getRawAttributes()) + writeTypeAnnotations(classSymbol.getRawTypeAttributes(), false) + writeEnclosingMethodAttribute(classSymbol);
        Kinds.Kind kind3 = classSymbol.owner.kind;
        Kinds.Kind kind4 = Kinds.Kind.MDL;
        if (kind3 == kind4) {
            iWriteFlagAttrs = iWriteFlagAttrs + writeModuleAttribute(classSymbol) + writeFlagAttrs(classSymbol.owner.flags() & (-131073));
        }
        int iWriteExtraClassAttributes = iWriteFlagAttrs + writeExtraClassAttributes(classSymbol) + writeExtraAttributes(classSymbol);
        this.poolbuf.appendInt(-889275714);
        if (this.preview.isEnabled() && this.preview.usesPreview(classSymbol.sourcefile)) {
            this.poolbuf.appendChar(65535);
        } else {
            this.poolbuf.appendChar(this.target.minorVersion);
        }
        this.poolbuf.appendChar(this.target.majorVersion);
        if (classSymbol.owner.kind != kind4 && this.target.hasNestmateAccess()) {
            iWriteExtraClassAttributes = iWriteExtraClassAttributes + writeNestMembersIfNeeded(classSymbol) + writeNestHostIfNeeded(classSymbol);
        }
        if (classSymbol.isRecord()) {
            iWriteExtraClassAttributes += writeRecordAttribute(classSymbol);
        }
        if (this.target.hasSealedClasses()) {
            iWriteExtraClassAttributes += writePermittedSubclassesIfNeeded(classSymbol);
        }
        if (!this.poolWriter.bootstrapMethods.isEmpty()) {
            writeBootstrapMethods();
            iWriteExtraClassAttributes++;
        }
        if (!this.poolWriter.innerClasses.isEmpty()) {
            writeInnerClasses();
            iWriteExtraClassAttributes++;
        }
        endAttrs(iBeginAttrs, iWriteExtraClassAttributes);
        ByteBuffer byteBuffer = this.poolbuf;
        outputStream.write(byteBuffer.elems, 0, byteBuffer.length);
        this.poolWriter.writePool(outputStream);
        this.poolWriter.reset();
        ByteBuffer byteBuffer2 = this.databuf;
        outputStream.write(byteBuffer2.elems, 0, byteBuffer2.length);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void writeCode(Code code) {
        int i;
        CRTable cRTable;
        this.databuf.appendChar(code.max_stack);
        this.databuf.appendChar(code.max_locals);
        this.databuf.appendInt(code.cp);
        this.databuf.appendBytes(code.code, 0, code.cp);
        this.databuf.appendChar(code.catchInfo.length());
        for (List list = code.catchInfo.toList(); list.nonEmpty(); list = list.tail) {
            int i2 = 0;
            while (true) {
                A a = list.head;
                if (i2 < ((char[]) a).length) {
                    this.databuf.appendChar(((char[]) a)[i2]);
                    i2++;
                }
            }
        }
        int iBeginAttrs = beginAttrs();
        if (code.lineInfo.nonEmpty()) {
            int iWriteAttr = writeAttr(this.names.LineNumberTable);
            this.databuf.appendChar(code.lineInfo.length());
            for (List listReverse = code.lineInfo.reverse(); listReverse.nonEmpty(); listReverse = listReverse.tail) {
                int i3 = 0;
                while (true) {
                    A a2 = listReverse.head;
                    if (i3 < ((char[]) a2).length) {
                        this.databuf.appendChar(((char[]) a2)[i3]);
                        i3++;
                    }
                }
            }
            endAttr(iWriteAttr);
            i = 1;
        } else {
            i = 0;
        }
        if (this.genCrt && (cRTable = code.crt) != null) {
            int iWriteAttr2 = writeAttr(this.names.CharacterRangeTable);
            endAttrs(beginAttrs(), cRTable.writeCRT(this.databuf, code.lineMap, this.log));
            endAttr(iWriteAttr2);
            i++;
        }
        if (code.varDebugInfo && code.varBufferSize > 0) {
            int iWriteAttr3 = writeAttr(this.names.LocalVariableTable);
            this.databuf.appendChar(code.getLVTSize());
            int i4 = 0;
            for (int i5 = 0; i5 < code.varBufferSize; i5++) {
                Code.LocalVar localVar = code.varBuffer[i5];
                for (Code.LocalVar.Range range : localVar.aliveRanges) {
                    char c = range.start_pc;
                    Assert.check(c >= 0 && c <= code.cp);
                    this.databuf.appendChar(range.start_pc);
                    char c2 = range.length;
                    Assert.check(c2 > 0 && range.start_pc + c2 <= code.cp);
                    this.databuf.appendChar(range.length);
                    Symbol.VarSymbol varSymbol = localVar.sym;
                    this.databuf.appendChar(this.poolWriter.putName(varSymbol.name));
                    this.databuf.appendChar(this.poolWriter.putDescriptor(varSymbol));
                    this.databuf.appendChar(localVar.reg);
                    if (needsLocalVariableTypeEntry(localVar.sym.type)) {
                        i4++;
                    }
                }
            }
            endAttr(iWriteAttr3);
            int i6 = i + 1;
            if (i4 > 0) {
                int iWriteAttr4 = writeAttr(this.names.LocalVariableTypeTable);
                this.databuf.appendChar(i4);
                int i7 = 0;
                for (int i8 = 0; i8 < code.varBufferSize; i8++) {
                    Code.LocalVar localVar2 = code.varBuffer[i8];
                    Symbol.VarSymbol varSymbol2 = localVar2.sym;
                    if (needsLocalVariableTypeEntry(varSymbol2.type)) {
                        for (Code.LocalVar.Range range2 : localVar2.aliveRanges) {
                            this.databuf.appendChar(range2.start_pc);
                            this.databuf.appendChar(range2.length);
                            this.databuf.appendChar(this.poolWriter.putName(varSymbol2.name));
                            this.databuf.appendChar(this.poolWriter.putSignature(varSymbol2));
                            this.databuf.appendChar(localVar2.reg);
                            i7++;
                        }
                    }
                }
                Assert.check(i7 == i4);
                endAttr(iWriteAttr4);
                i += 2;
            } else {
                i = i6;
            }
        }
        if (code.stackMapBufferSize > 0) {
            if (this.debugstackmap) {
                System.out.println("Stack map for " + code.meth);
            }
            int iWriteAttr5 = writeAttr(code.stackMap.getAttributeName(this.names));
            writeStackMap(code);
            endAttr(iWriteAttr5);
            i++;
        }
        endAttrs(iBeginAttrs, i + writeTypeAnnotations(code.meth.getRawTypeAttributes(), true));
    }

    public void writeCompoundAttribute(Attribute.Compound compound) {
        this.databuf.appendChar(this.poolWriter.putDescriptor(compound.type));
        this.databuf.appendChar(compound.values.length());
        for (Pair<Symbol.MethodSymbol, Attribute> pair : compound.values) {
            checkAnnotationArraySizeInternal(pair);
            this.databuf.appendChar(this.poolWriter.putName(pair.fst.name));
            pair.snd.accept(this.awriter);
        }
    }

    /* JADX WARN: Code duplicated, block: B:13:0x003a  */
    public int writeEnclosingMethodAttribute(Name name, Symbol.ClassSymbol classSymbol) {
        Symbol.MethodSymbol methodSymbolOriginalEnclosingMethod;
        Kinds.Kind kind = classSymbol.owner.kind;
        Kinds.Kind kind2 = Kinds.Kind.MTH;
        if (kind != kind2 && classSymbol.name != this.names.empty) {
            return 0;
        }
        int iWriteAttr = writeAttr(name);
        Symbol.ClassSymbol classSymbolEnclClass = classSymbol.owner.enclClass();
        if ((classSymbol.owner.flags() & 1048576) == 0) {
            Symbol symbol = classSymbol.owner;
            if (symbol.kind != kind2) {
                methodSymbolOriginalEnclosingMethod = null;
            } else {
                methodSymbolOriginalEnclosingMethod = ((Symbol.MethodSymbol) symbol).originalEnclosingMethod();
            }
        } else {
            methodSymbolOriginalEnclosingMethod = null;
        }
        this.databuf.appendChar(this.poolWriter.putClass(classSymbolEnclClass));
        this.databuf.appendChar(methodSymbolOriginalEnclosingMethod != null ? this.poolWriter.putNameAndType(methodSymbolOriginalEnclosingMethod) : 0);
        endAttr(iWriteAttr);
        return 1;
    }

    public int writeExtraAttributes(Symbol symbol) {
        Iterator<ToIntFunction<Symbol>> it = this.extraAttributeHooks.iterator();
        int iApplyAsInt = 0;
        while (it.hasNext()) {
            iApplyAsInt += it.next().applyAsInt(symbol);
        }
        return iApplyAsInt;
    }

    public int writeExtraClassAttributes(Symbol.ClassSymbol classSymbol) {
        return 0;
    }

    public void writeField(Symbol.VarSymbol varSymbol) {
        int i;
        this.databuf.appendChar(adjustFlags(varSymbol.flags()));
        if (this.dumpFieldModifiers) {
            PrintWriter writer = this.log.getWriter(Log.WriterKind.ERROR);
            writer.println("FIELD  " + varSymbol.name);
            writer.println("---" + flagNames(varSymbol.flags()));
        }
        this.databuf.appendChar(this.poolWriter.putName(varSymbol.name));
        this.databuf.appendChar(this.poolWriter.putDescriptor(varSymbol));
        int iBeginAttrs = beginAttrs();
        if (varSymbol.getConstValue() != null) {
            int iWriteAttr = writeAttr(this.names.ConstantValue);
            this.databuf.appendChar(this.poolWriter.putConstant(varSymbol.getConstValue()));
            endAttr(iWriteAttr);
            i = 1;
        } else {
            i = 0;
        }
        endAttrs(iBeginAttrs, i + writeMemberAttrs(varSymbol, false) + writeExtraAttributes(varSymbol));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void writeFields(Scope scope) {
        List listNil = List.nil();
        for (Symbol symbol : scope.getSymbols(Scope.LookupKind.NON_RECURSIVE)) {
            if (symbol.kind == Kinds.Kind.VAR) {
                listNil = listNil.prepend((Symbol.VarSymbol) symbol);
            }
        }
        while (listNil.nonEmpty()) {
            writeField((Symbol.VarSymbol) listNil.head);
            listNil = listNil.tail;
        }
    }

    public int writeFlagAttrs(long j) {
        if ((j & Flags.BODY_ONLY_FINALIZE) == 0) {
            return 0;
        }
        endAttr(writeAttr(this.names.Deprecated));
        return 1;
    }

    public void writeInnerClasses() {
        int iWriteAttr = writeAttr(this.names.InnerClasses);
        this.databuf.appendChar(this.poolWriter.innerClasses.size());
        for (Symbol.ClassSymbol classSymbol : this.poolWriter.innerClasses) {
            classSymbol.markAbstractIfNeeded(this.types);
            int iAdjustFlags = adjustFlags(classSymbol.flags_field);
            if ((iAdjustFlags & 512) != 0) {
                iAdjustFlags |= 1024;
            }
            int i = iAdjustFlags & (-2049);
            if (this.dumpInnerClassModifiers) {
                PrintWriter writer = this.log.getWriter(Log.WriterKind.ERROR);
                writer.println("INNERCLASS  " + classSymbol.name);
                writer.println("---" + flagNames((long) i));
            }
            this.databuf.appendChar(this.poolWriter.putClass(classSymbol));
            int iPutName = 0;
            this.databuf.appendChar((classSymbol.owner.kind != Kinds.Kind.TYP || classSymbol.name.length() == 0) ? 0 : this.poolWriter.putClass((Symbol.ClassSymbol) classSymbol.owner));
            ByteBuffer byteBuffer = this.databuf;
            if (classSymbol.name.length() != 0) {
                iPutName = this.poolWriter.putName(classSymbol.name);
            }
            byteBuffer.appendChar(iPutName);
            this.databuf.appendChar(i);
        }
        endAttr(iWriteAttr);
    }

    public int writeJavaAnnotations(List<Attribute.Compound> list) {
        int i = 0;
        if (list.isEmpty()) {
            return 0;
        }
        ListBuffer listBuffer = new ListBuffer();
        ListBuffer listBuffer2 = new ListBuffer();
        for (Attribute.Compound compound : list) {
            int i2 = AnonymousClass1.$SwitchMap$com$sun$tools$javac$code$Attribute$RetentionPolicy[this.types.getRetention(compound).ordinal()];
            if (i2 == 2) {
                listBuffer2.append(compound);
            } else if (i2 == 3) {
                listBuffer.append(compound);
            }
        }
        if (listBuffer.length() != 0) {
            int iWriteAttr = writeAttr(this.names.RuntimeVisibleAnnotations);
            this.databuf.appendChar(listBuffer.length());
            Iterator it = listBuffer.iterator();
            while (it.hasNext()) {
                writeCompoundAttribute((Attribute.Compound) it.next());
            }
            endAttr(iWriteAttr);
            i = 1;
        }
        if (listBuffer2.length() == 0) {
            return i;
        }
        int iWriteAttr2 = writeAttr(this.names.RuntimeInvisibleAnnotations);
        this.databuf.appendChar(listBuffer2.length());
        Iterator it2 = listBuffer2.iterator();
        while (it2.hasNext()) {
            writeCompoundAttribute((Attribute.Compound) it2.next());
        }
        endAttr(iWriteAttr2);
        return i + 1;
    }

    public int writeMemberAttrs(Symbol symbol, boolean z) {
        int iWriteFlagAttrs = !z ? writeFlagAttrs(symbol.flags()) : 0;
        long jFlags = symbol.flags();
        if ((2147487744L & jFlags) != 4096 && (jFlags & 536870912) == 0) {
            Types types = this.types;
            if (!types.isSameType(symbol.type, symbol.erasure(types)) || this.poolWriter.signatureGen.hasTypeVar(symbol.type.mo74getThrownTypes())) {
                int iWriteAttr = writeAttr(this.names.Signature);
                this.databuf.appendChar(this.poolWriter.putSignature(symbol));
                endAttr(iWriteAttr);
                iWriteFlagAttrs++;
            }
        }
        return iWriteFlagAttrs + writeJavaAnnotations(symbol.getRawAttributes()) + writeTypeAnnotations(symbol.getRawTypeAttributes(), false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void writeMethod(Symbol.MethodSymbol methodSymbol) {
        int iWriteMethodParametersAttr;
        boolean zRequiresParamNames;
        this.databuf.appendChar(adjustFlags(methodSymbol.flags()));
        if (this.dumpMethodModifiers) {
            PrintWriter writer = this.log.getWriter(Log.WriterKind.ERROR);
            writer.println("METHOD  " + methodSymbol.name);
            writer.println("---" + flagNames(methodSymbol.flags()));
        }
        this.databuf.appendChar(this.poolWriter.putName(methodSymbol.name));
        this.databuf.appendChar(this.poolWriter.putDescriptor(methodSymbol));
        int iBeginAttrs = beginAttrs();
        if (methodSymbol.code != null) {
            int iWriteAttr = writeAttr(this.names.Code);
            writeCode(methodSymbol.code);
            methodSymbol.code = null;
            endAttr(iWriteAttr);
            iWriteMethodParametersAttr = 1;
        } else {
            iWriteMethodParametersAttr = 0;
        }
        List listMo74getThrownTypes = methodSymbol.erasure(this.types).mo74getThrownTypes();
        if (listMo74getThrownTypes.nonEmpty()) {
            int iWriteAttr2 = writeAttr(this.names.Exceptions);
            this.databuf.appendChar(listMo74getThrownTypes.length());
            while (listMo74getThrownTypes.nonEmpty()) {
                this.databuf.appendChar(this.poolWriter.putClass((Type) listMo74getThrownTypes.head));
                listMo74getThrownTypes = listMo74getThrownTypes.tail;
            }
            endAttr(iWriteAttr2);
            iWriteMethodParametersAttr++;
        }
        if (methodSymbol.defaultValue != null) {
            int iWriteAttr3 = writeAttr(this.names.AnnotationDefault);
            methodSymbol.defaultValue.accept(this.awriter);
            endAttr(iWriteAttr3);
            iWriteMethodParametersAttr++;
        }
        if (this.target.hasMethodParameters() && !methodSymbol.isLambdaMethod() && ((zRequiresParamNames = requiresParamNames(methodSymbol)) || requiresParamFlags(methodSymbol))) {
            iWriteMethodParametersAttr += writeMethodParametersAttr(methodSymbol, zRequiresParamNames);
        }
        int iWriteMemberAttrs = iWriteMethodParametersAttr + writeMemberAttrs(methodSymbol, false);
        if (!methodSymbol.isLambdaMethod()) {
            iWriteMemberAttrs += writeParameterAttrs(methodSymbol.params);
        }
        endAttrs(iBeginAttrs, iWriteMemberAttrs + writeExtraAttributes(methodSymbol));
    }

    public int writeMethodParametersAttr(Symbol.MethodSymbol methodSymbol, boolean z) {
        int size = methodSymbol.externalType(this.types).asMethodType().argtypes.size();
        if (methodSymbol.params == null || size == 0) {
            return 0;
        }
        int iWriteAttr = writeAttr(this.names.MethodParameters);
        this.databuf.appendByte(size);
        for (Symbol.VarSymbol varSymbol : methodSymbol.extraParams) {
            int iFlags = (36880 & ((int) varSymbol.flags())) | (((int) methodSymbol.flags()) & 4096);
            ByteBuffer byteBuffer = this.databuf;
            if (z) {
                byteBuffer.appendChar(this.poolWriter.putName(varSymbol.name));
            } else {
                byteBuffer.appendChar(0);
            }
            this.databuf.appendChar(iFlags);
        }
        for (Symbol.VarSymbol varSymbol2 : methodSymbol.params) {
            int iFlags2 = (((int) varSymbol2.flags()) & 36880) | (((int) methodSymbol.flags()) & 4096);
            ByteBuffer byteBuffer2 = this.databuf;
            if (z) {
                byteBuffer2.appendChar(this.poolWriter.putName(varSymbol2.name));
            } else {
                byteBuffer2.appendChar(0);
            }
            this.databuf.appendChar(iFlags2);
        }
        for (Symbol.VarSymbol varSymbol3 : methodSymbol.capturedLocals) {
            int iFlags3 = (((int) varSymbol3.flags()) & 36880) | (((int) methodSymbol.flags()) & 4096);
            ByteBuffer byteBuffer3 = this.databuf;
            if (z) {
                byteBuffer3.appendChar(this.poolWriter.putName(varSymbol3.name));
            } else {
                byteBuffer3.appendChar(0);
            }
            this.databuf.appendChar(iFlags3);
        }
        endAttr(iWriteAttr);
        return 1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void writeMethods(Scope scope) {
        List listNil = List.nil();
        for (Symbol symbol : scope.getSymbols(Scope.LookupKind.NON_RECURSIVE)) {
            if (symbol.kind == Kinds.Kind.MTH && (symbol.flags() & Flags.HYPOTHETICAL) == 0) {
                listNil = listNil.prepend((Symbol.MethodSymbol) symbol);
            }
        }
        while (listNil.nonEmpty()) {
            writeMethod((Symbol.MethodSymbol) listNil.head);
            listNil = listNil.tail;
        }
    }

    public int writeModuleAttribute(Symbol.ClassSymbol classSymbol) {
        Symbol.ModuleSymbol moduleSymbol = (Symbol.ModuleSymbol) classSymbol.owner;
        int iWriteAttr = writeAttr(this.names.Module);
        this.databuf.appendChar(this.poolWriter.putModule(moduleSymbol));
        this.databuf.appendChar(Symbol.ModuleFlags.value(moduleSymbol.flags));
        ByteBuffer byteBuffer = this.databuf;
        Name name = moduleSymbol.version;
        byteBuffer.appendChar(name != null ? this.poolWriter.putName(name) : 0);
        ListBuffer<Directive.RequiresDirective> listBuffer = new ListBuffer();
        for (Directive.RequiresDirective requiresDirective : moduleSymbol.requires) {
            if (!requiresDirective.flags.contains(Directive.RequiresFlag.EXTRA)) {
                listBuffer.add(requiresDirective);
            }
        }
        this.databuf.appendChar(listBuffer.size());
        for (Directive.RequiresDirective requiresDirective2 : listBuffer) {
            this.databuf.appendChar(this.poolWriter.putModule(requiresDirective2.module));
            this.databuf.appendChar(Directive.RequiresFlag.value(requiresDirective2.flags));
            ByteBuffer byteBuffer2 = this.databuf;
            Name name2 = requiresDirective2.module.version;
            byteBuffer2.appendChar(name2 != null ? this.poolWriter.putName(name2) : 0);
        }
        List<Directive.ExportsDirective> list = moduleSymbol.exports;
        this.databuf.appendChar(list.size());
        for (Directive.ExportsDirective exportsDirective : list) {
            this.databuf.appendChar(this.poolWriter.putPackage(exportsDirective.packge));
            this.databuf.appendChar(Directive.ExportsFlag.value(exportsDirective.flags));
            List<Symbol.ModuleSymbol> list2 = exportsDirective.modules;
            ByteBuffer byteBuffer3 = this.databuf;
            if (list2 == null) {
                byteBuffer3.appendChar(0);
            } else {
                byteBuffer3.appendChar(list2.size());
                Iterator<Symbol.ModuleSymbol> it = exportsDirective.modules.iterator();
                while (it.hasNext()) {
                    this.databuf.appendChar(this.poolWriter.putModule(it.next()));
                }
            }
        }
        List<Directive.OpensDirective> list3 = moduleSymbol.opens;
        this.databuf.appendChar(list3.size());
        for (Directive.OpensDirective opensDirective : list3) {
            this.databuf.appendChar(this.poolWriter.putPackage(opensDirective.packge));
            this.databuf.appendChar(Directive.OpensFlag.value(opensDirective.flags));
            List<Symbol.ModuleSymbol> list4 = opensDirective.modules;
            ByteBuffer byteBuffer4 = this.databuf;
            if (list4 == null) {
                byteBuffer4.appendChar(0);
            } else {
                byteBuffer4.appendChar(list4.size());
                Iterator<Symbol.ModuleSymbol> it2 = opensDirective.modules.iterator();
                while (it2.hasNext()) {
                    this.databuf.appendChar(this.poolWriter.putModule(it2.next()));
                }
            }
        }
        List<Directive.UsesDirective> list5 = moduleSymbol.uses;
        this.databuf.appendChar(list5.size());
        Iterator<Directive.UsesDirective> it3 = list5.iterator();
        while (it3.hasNext()) {
            this.databuf.appendChar(this.poolWriter.putClass(it3.next().service));
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Directive.ProvidesDirective providesDirective : moduleSymbol.provides) {
            ((Set) linkedHashMap.computeIfAbsent(providesDirective.service, new Function() { // from class: rx1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ClassWriter.b((Symbol.ClassSymbol) obj);
                }
            })).addAll(providesDirective.impls);
        }
        this.databuf.appendChar(linkedHashMap.size());
        linkedHashMap.forEach(new BiConsumer() { // from class: sx1
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ClassWriter.e(this.a, (Symbol.ClassSymbol) obj, (Set) obj2);
            }
        });
        endAttr(iWriteAttr);
        return 1;
    }

    public int writeNestHostIfNeeded(Symbol.ClassSymbol classSymbol) {
        if (classSymbol.owner.kind == Kinds.Kind.PCK) {
            return 0;
        }
        int iWriteAttr = writeAttr(this.names.NestHost);
        this.databuf.appendChar(this.poolWriter.putClass(classSymbol.outermostClass()));
        endAttr(iWriteAttr);
        return 1;
    }

    public int writeNestMembersIfNeeded(Symbol.ClassSymbol classSymbol) {
        ListBuffer<Symbol.ClassSymbol> listBuffer = new ListBuffer<>();
        listNested(classSymbol, listBuffer);
        LinkedHashSet linkedHashSet = new LinkedHashSet(listBuffer);
        if (classSymbol.owner.kind != Kinds.Kind.PCK || linkedHashSet.isEmpty()) {
            return 0;
        }
        int iWriteAttr = writeAttr(this.names.NestMembers);
        this.databuf.appendChar(linkedHashSet.size());
        Iterator it = linkedHashSet.iterator();
        while (it.hasNext()) {
            this.databuf.appendChar(this.poolWriter.putClass((Symbol.ClassSymbol) it.next()));
        }
        endAttr(iWriteAttr);
        return 1;
    }

    public int writeParameterAttrs(List<Symbol.VarSymbol> list) {
        boolean z;
        boolean z2;
        int i = 0;
        if (list != null) {
            Iterator<Symbol.VarSymbol> it = list.iterator();
            z = false;
            z2 = false;
            while (it.hasNext()) {
                Iterator<Attribute.Compound> it2 = it.next().getRawAttributes().iterator();
                while (it2.hasNext()) {
                    int i2 = AnonymousClass1.$SwitchMap$com$sun$tools$javac$code$Attribute$RetentionPolicy[this.types.getRetention(it2.next()).ordinal()];
                    if (i2 == 2) {
                        z2 = true;
                    } else if (i2 == 3) {
                        z = true;
                    }
                }
            }
        } else {
            z = false;
            z2 = false;
        }
        if (z) {
            int iWriteAttr = writeAttr(this.names.RuntimeVisibleParameterAnnotations);
            writeParamAnnotations(list, Attribute.RetentionPolicy.RUNTIME);
            endAttr(iWriteAttr);
            i = 1;
        }
        if (!z2) {
            return i;
        }
        int iWriteAttr2 = writeAttr(this.names.RuntimeInvisibleParameterAnnotations);
        writeParamAnnotations(list, Attribute.RetentionPolicy.CLASS);
        endAttr(iWriteAttr2);
        return i + 1;
    }

    public int writePermittedSubclassesIfNeeded(Symbol.ClassSymbol classSymbol) {
        if (!classSymbol.getPermittedSubclasses().nonEmpty()) {
            return 0;
        }
        int iWriteAttr = writeAttr(this.names.PermittedSubclasses);
        this.databuf.appendChar(classSymbol.getPermittedSubclasses().size());
        Iterator<Type> it = classSymbol.getPermittedSubclasses().iterator();
        while (it.hasNext()) {
            this.databuf.appendChar(this.poolWriter.putClass((Symbol.ClassSymbol) it.next().tsym));
        }
        endAttr(iWriteAttr);
        return 1;
    }

    public void writePosition(TypeAnnotationPosition typeAnnotationPosition) {
        this.databuf.appendByte(typeAnnotationPosition.type.targetTypeValue());
        switch (AnonymousClass1.$SwitchMap$com$sun$tools$javac$code$TargetType[typeAnnotationPosition.type.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
                this.databuf.appendChar(typeAnnotationPosition.offset);
                break;
            case 5:
            case 6:
                this.databuf.appendChar(typeAnnotationPosition.lvarOffset.length);
                int i = 0;
                while (true) {
                    int[] iArr = typeAnnotationPosition.lvarOffset;
                    if (i < iArr.length) {
                        this.databuf.appendChar(iArr[i]);
                        this.databuf.appendChar(typeAnnotationPosition.lvarLength[i]);
                        this.databuf.appendChar(typeAnnotationPosition.lvarIndex[i]);
                        i++;
                    }
                    break;
                }
                break;
            case 7:
                this.databuf.appendChar(typeAnnotationPosition.getExceptionIndex());
                break;
            case 8:
            case 21:
            case 22:
                break;
            case 9:
            case 10:
                this.databuf.appendByte(typeAnnotationPosition.parameter_index);
                break;
            case 11:
            case 12:
                this.databuf.appendByte(typeAnnotationPosition.parameter_index);
                this.databuf.appendByte(typeAnnotationPosition.bound_index);
                break;
            case 13:
                this.databuf.appendChar(typeAnnotationPosition.type_index);
                break;
            case 14:
                this.databuf.appendChar(typeAnnotationPosition.type_index);
                break;
            case 15:
                this.databuf.appendByte(typeAnnotationPosition.parameter_index);
                break;
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
                this.databuf.appendChar(typeAnnotationPosition.offset);
                this.databuf.appendByte(typeAnnotationPosition.type_index);
                break;
            case 23:
                x01.a("jvm.ClassWriter: UNKNOWN target type should never occur!");
                return;
            default:
                s22.a("jvm.ClassWriter: Unknown target type for position: ", typeAnnotationPosition);
                return;
        }
        this.databuf.appendByte(typeAnnotationPosition.location.size());
        Iterator<Integer> it = TypeAnnotationPosition.getBinaryFromTypePath(typeAnnotationPosition.location).iterator();
        while (it.hasNext()) {
            this.databuf.appendByte((byte) it.next().intValue());
        }
    }

    public int writeRecordAttribute(Symbol.ClassSymbol classSymbol) {
        int iWriteAttr = writeAttr(this.names.Record);
        classSymbol.members();
        this.databuf.appendChar(classSymbol.getRecordComponents().size());
        for (Symbol.RecordComponent recordComponent : classSymbol.getRecordComponents()) {
            this.databuf.appendChar(this.poolWriter.putName(recordComponent.name));
            this.databuf.appendChar(this.poolWriter.putDescriptor(recordComponent));
            endAttrs(beginAttrs(), writeMemberAttrs(recordComponent, true));
        }
        endAttr(iWriteAttr);
        return 1;
    }

    public void writeStackMap(Code code) {
        boolean z;
        int i = code.stackMapBufferSize;
        if (this.debugstackmap) {
            System.out.println(" nframes = " + i);
        }
        this.databuf.appendChar(i);
        int i2 = AnonymousClass1.$SwitchMap$com$sun$tools$javac$jvm$Code$StackMapFormat[code.stackMap.ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                x01.a("Unexpected stackmap format value");
                return;
            }
            Assert.checkNull(code.stackMapBuffer);
            for (int i3 = 0; i3 < i; i3++) {
                if (this.debugstackmap) {
                    System.out.print("  " + i3 + ":");
                }
                code.stackMapTableBuffer[i3].write(this);
                if (this.debugstackmap) {
                    System.out.println();
                }
            }
            return;
        }
        for (int i4 = 0; i4 < i; i4++) {
            if (this.debugstackmap) {
                System.out.print("  " + i4 + ":");
            }
            Code.StackMapFrame stackMapFrame = code.stackMapBuffer[i4];
            if (this.debugstackmap) {
                System.out.print(" pc=" + stackMapFrame.pc);
            }
            this.databuf.appendChar(stackMapFrame.pc);
            int iWidth = 0;
            int i5 = 0;
            while (true) {
                Type[] typeArr = stackMapFrame.locals;
                if (iWidth >= typeArr.length) {
                    break;
                }
                i5++;
                iWidth += Code.width(typeArr[iWidth]);
            }
            if (this.debugstackmap) {
                System.out.print(" nlocals=" + i5);
            }
            this.databuf.appendChar(i5);
            for (int iWidth2 = 0; iWidth2 < stackMapFrame.locals.length; iWidth2 += Code.width(stackMapFrame.locals[iWidth2])) {
                if (this.debugstackmap) {
                    System.out.print(" local[" + iWidth2 + "]=");
                }
                writeStackMapType(stackMapFrame.locals[iWidth2]);
            }
            int iWidth3 = 0;
            int i6 = 0;
            while (true) {
                Type[] typeArr2 = stackMapFrame.stack;
                if (iWidth3 >= typeArr2.length) {
                    break;
                }
                i6++;
                iWidth3 += Code.width(typeArr2[iWidth3]);
            }
            if (this.debugstackmap) {
                System.out.print(" nstack=" + i6);
            }
            this.databuf.appendChar(i6);
            int iWidth4 = 0;
            while (true) {
                int length = stackMapFrame.stack.length;
                z = this.debugstackmap;
                if (iWidth4 >= length) {
                    break;
                }
                if (z) {
                    System.out.print(" stack[" + iWidth4 + "]=");
                }
                writeStackMapType(stackMapFrame.stack[iWidth4]);
                iWidth4 += Code.width(stackMapFrame.stack[iWidth4]);
            }
            if (z) {
                System.out.println();
            }
        }
    }

    public void writeStackMapType(Type type) {
        if (type == null) {
            if (this.debugstackmap) {
                System.out.print(Constants.ELEMNAME_EMPTY_STRING);
            }
            this.databuf.appendByte(0);
        }
        switch (AnonymousClass1.$SwitchMap$com$sun$tools$javac$code$TypeTag[type.getTag().ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 8:
                if (this.debugstackmap) {
                    System.out.print("int");
                }
                this.databuf.appendByte(1);
                break;
            case 5:
                if (this.debugstackmap) {
                    System.out.print("long");
                }
                this.databuf.appendByte(4);
                break;
            case 6:
                if (this.debugstackmap) {
                    System.out.print("float");
                }
                this.databuf.appendByte(2);
                break;
            case 7:
                if (this.debugstackmap) {
                    System.out.print("double");
                }
                this.databuf.appendByte(3);
                break;
            case 9:
                if (this.debugstackmap) {
                    System.out.print(PsiKeyword.NULL);
                }
                this.databuf.appendByte(5);
                break;
            case 10:
            case 11:
            case 12:
                if (this.debugstackmap) {
                    System.out.print("object(" + this.types.erasure(type).tsym + ")");
                }
                this.databuf.appendByte(7);
                this.databuf.appendChar(this.poolWriter.putClass(this.types.erasure(type)));
                break;
            case 13:
                if (this.debugstackmap) {
                    System.out.print("uninit_this");
                }
                this.databuf.appendByte(6);
                break;
            case 14:
                UninitializedType uninitializedType = (UninitializedType) type;
                this.databuf.appendByte(8);
                if (this.debugstackmap) {
                    System.out.print("uninit_object@" + uninitializedType.offset);
                }
                this.databuf.appendChar(uninitializedType.offset);
                break;
            default:
                x1f.a();
                break;
        }
    }

    public void writeTypeAnnotation(Attribute.TypeCompound typeCompound) {
        writePosition(typeCompound.position);
        writeCompoundAttribute(typeCompound);
    }

    public int writeTypeAnnotations(List<Attribute.TypeCompound> list, boolean z) {
        int i = 0;
        if (list.isEmpty()) {
            return 0;
        }
        ListBuffer listBuffer = new ListBuffer();
        ListBuffer listBuffer2 = new ListBuffer();
        for (Attribute.TypeCompound typeCompound : list) {
            if (typeCompound.hasUnknownPosition() && !typeCompound.tryFixPosition()) {
                this.log.getWriter(Log.WriterKind.ERROR).println("ClassWriter: Position UNKNOWN in type annotation: " + typeCompound);
            } else if (typeCompound.position.type.isLocal() == z && typeCompound.position.emitToClassfile()) {
                int i2 = AnonymousClass1.$SwitchMap$com$sun$tools$javac$code$Attribute$RetentionPolicy[this.types.getRetention(typeCompound).ordinal()];
                if (i2 == 2) {
                    listBuffer2.append(typeCompound);
                } else if (i2 == 3) {
                    listBuffer.append(typeCompound);
                }
            }
        }
        if (listBuffer.length() != 0) {
            int iWriteAttr = writeAttr(this.names.RuntimeVisibleTypeAnnotations);
            this.databuf.appendChar(listBuffer.length());
            Iterator it = listBuffer.iterator();
            while (it.hasNext()) {
                writeTypeAnnotation((Attribute.TypeCompound) it.next());
            }
            endAttr(iWriteAttr);
            i = 1;
        }
        if (listBuffer2.length() == 0) {
            return i;
        }
        int iWriteAttr2 = writeAttr(this.names.RuntimeInvisibleTypeAnnotations);
        this.databuf.appendChar(listBuffer2.length());
        Iterator it2 = listBuffer2.iterator();
        while (it2.hasNext()) {
            writeTypeAnnotation((Attribute.TypeCompound) it2.next());
        }
        endAttr(iWriteAttr2);
        return i + 1;
    }

    public int writeEnclosingMethodAttribute(Symbol.ClassSymbol classSymbol) {
        return writeEnclosingMethodAttribute(this.names.EnclosingMethod, classSymbol);
    }

    private void writeParamAnnotations(Symbol.MethodSymbol methodSymbol, Attribute.RetentionPolicy retentionPolicy) {
        this.databuf.appendByte(methodSymbol.params.length());
        writeParamAnnotations(methodSymbol.params, retentionPolicy);
    }
}
