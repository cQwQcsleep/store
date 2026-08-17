package com.sun.tools.javac.jvm;

import com.intellij.psi.PsiKeyword;
import com.sun.org.apache.xpath.internal.XPath;
import com.sun.tools.javac.code.Attribute;
import com.sun.tools.javac.code.Kinds;
import com.sun.tools.javac.code.Scope;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Symtab;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.code.Types;
import com.sun.tools.javac.main.Option;
import com.sun.tools.javac.model.JavacElements;
import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.Options;
import com.sun.tools.javac.util.Pair;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import javax.lang.model.element.Name;
import javax.lang.model.type.TypeKind;
import javax.tools.FileObject;
import javax.tools.JavaFileManager;
import javax.tools.StandardLocation;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class JNIWriter {
    private boolean checkAll;
    private Context context;
    private final JavaFileManager fileManager;
    private final HashMap<String, String> filesWritten = new HashMap<>();
    private final Log log;
    public boolean multiModuleMode;
    Symtab syms;
    Types types;
    private boolean verbose;
    protected static final Context.Key<JNIWriter> jniWriterKey = new Context.Key<>();
    private static final boolean isWindows = System.getProperty("os.name").startsWith("Windows");

    /* JADX INFO: renamed from: com.sun.tools.javac.jvm.JNIWriter$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$javax$lang$model$type$TypeKind;

        static {
            int[] iArr = new int[TypeKind.values().length];
            $SwitchMap$javax$lang$model$type$TypeKind = iArr;
            try {
                iArr[TypeKind.BOOLEAN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.BYTE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.SHORT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.INT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.LONG.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.CHAR.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.FLOAT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.DOUBLE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.ARRAY.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.DECLARED.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.VOID.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }

    public enum EncoderType {
        CLASS,
        FIELDSTUB,
        FIELD,
        JNI,
        SIGNATURE
    }

    private JNIWriter(Context context) {
        context.put(jniWriterKey, this);
        this.fileManager = (JavaFileManager) context.get(JavaFileManager.class);
        this.log = Log.instance(context);
        Options optionsInstance = Options.instance(context);
        this.verbose = optionsInstance.isSet(Option.VERBOSE);
        this.checkAll = optionsInstance.isSet("javah:full");
        this.context = context;
    }

    public static String encode(CharSequence charSequence, EncoderType encoderType) {
        StringBuilder sb = new StringBuilder(100);
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = charSequence.charAt(i);
            if (isalnum(cCharAt)) {
                sb.append(cCharAt);
            } else {
                int iOrdinal = encoderType.ordinal();
                if (iOrdinal != 0) {
                    if (iOrdinal == 1) {
                        sb.append(cCharAt == '_' ? Character.valueOf(cCharAt) : encodeChar(cCharAt));
                    } else if (iOrdinal != 3) {
                        if (iOrdinal != 4) {
                            sb.append(encodeChar(cCharAt));
                        } else {
                            sb.append(isprint(cCharAt) ? Character.valueOf(cCharAt) : encodeChar(cCharAt));
                        }
                    } else if (cCharAt == '.' || cCharAt == '/') {
                        sb.append("_");
                    } else if (cCharAt == ';') {
                        sb.append("_2");
                    } else if (cCharAt == '[') {
                        sb.append("_3");
                    } else if (cCharAt != '_') {
                        sb.append(encodeChar(cCharAt));
                    } else {
                        sb.append("_1");
                    }
                } else if (cCharAt == '$') {
                    sb.append("__");
                } else if (cCharAt == '.' || cCharAt == '_') {
                    sb.append("_");
                } else {
                    sb.append(encodeChar(cCharAt));
                }
            }
        }
        return sb.toString();
    }

    public static String encodeChar(char c) {
        String hexString = Integer.toHexString(c);
        int length = hexString.length();
        int i = 5 - length;
        char[] cArr = new char[6];
        int i2 = 0;
        cArr[0] = '_';
        for (int i3 = 1; i3 <= i; i3++) {
            cArr[i3] = '0';
        }
        int i4 = 6 - length;
        while (i4 < 6) {
            cArr[i4] = hexString.charAt(i2);
            i4++;
            i2++;
        }
        return new String(cArr);
    }

    private static boolean hasFlag(Symbol symbol, int i) {
        return (((long) i) & symbol.flags()) != 0;
    }

    public static JNIWriter instance(Context context) {
        JNIWriter jNIWriter = (JNIWriter) context.get(jniWriterKey);
        return jNIWriter == null ? new JNIWriter(context) : jNIWriter;
    }

    public static boolean isFinal(Symbol symbol) {
        return hasFlag(symbol, 16);
    }

    public static boolean isNative(Symbol symbol) {
        return hasFlag(symbol, 256);
    }

    public static boolean isStatic(Symbol symbol) {
        return hasFlag(symbol, 8);
    }

    public static boolean isSynthetic(Symbol symbol) {
        return hasFlag(symbol, 4096);
    }

    private static boolean isalnum(char c) {
        if (c > 127) {
            return false;
        }
        if (c >= 'A' && c <= 'Z') {
            return true;
        }
        if (c < 'a' || c > 'z') {
            return c >= '0' && c <= '9';
        }
        return true;
    }

    private static boolean isprint(char c) {
        return c >= ' ' && c <= '~';
    }

    private void lazyInit() {
        if (this.types == null) {
            this.types = Types.instance(this.context);
        }
        if (this.syms == null) {
            this.syms = Symtab.instance(this.context);
        }
    }

    private boolean needsHeader(Symbol.ClassSymbol classSymbol, boolean z) {
        if (!classSymbol.isDirectlyOrIndirectlyLocal() && !isSynthetic(classSymbol)) {
            for (Symbol symbol : classSymbol.members_field.getSymbols(Scope.LookupKind.NON_RECURSIVE)) {
                if (symbol.kind == Kinds.Kind.MTH && isNative(symbol)) {
                    return true;
                }
                Iterator<Attribute.Compound> it = symbol.getDeclarationAttributes().iterator();
                while (it.hasNext()) {
                    if (it.next().type.tsym == this.syms.nativeHeaderType.tsym) {
                        return true;
                    }
                }
            }
            if (z) {
                for (Symbol symbol2 : classSymbol.members_field.getSymbols(Scope.LookupKind.NON_RECURSIVE)) {
                    if (symbol2.kind == Kinds.Kind.TYP && needsHeader((Symbol.ClassSymbol) symbol2, true)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void printStaticDefines(PrintWriter printWriter, Pair<Symbol.ClassSymbol, Symbol.VarSymbol> pair) {
        String string;
        Symbol.ClassSymbol classSymbol = pair.fst;
        Symbol.VarSymbol varSymbol = pair.snd;
        Object constantValue = varSymbol.getConstantValue();
        switch (AnonymousClass1.$SwitchMap$javax$lang$model$type$TypeKind[varSymbol.asType().getKind().ordinal()]) {
            case 1:
                string = !((Boolean) constantValue).booleanValue() ? "0L" : "1L";
                break;
            case 2:
            case 3:
            case 4:
                string = constantValue.toString() + "L";
                break;
            case 5:
                StringBuilder sb = new StringBuilder();
                sb.append(constantValue.toString());
                sb.append(isWindows ? "i64" : "LL");
                string = sb.toString();
                break;
            case 6:
                string = String.valueOf(((Character) constantValue).charValue() & 65535) + "L";
                break;
            case 7:
                float fFloatValue = ((Float) constantValue).floatValue();
                string = !Float.isInfinite(fFloatValue) ? constantValue.toString() + "f" : (fFloatValue < 0.0f ? "-" : "").concat("Inff");
                break;
            case 8:
                double dDoubleValue = ((Double) constantValue).doubleValue();
                string = !Double.isInfinite(dDoubleValue) ? constantValue.toString() : (dDoubleValue < XPath.MATCH_SCORE_QNAME ? "-" : "").concat("InfD");
                break;
            default:
                string = null;
                break;
        }
        if (string != null) {
            printWriter.print("#undef ");
            String strEncode = encode(classSymbol.getQualifiedName(), EncoderType.CLASS);
            String strEncode2 = encode(varSymbol.getSimpleName(), EncoderType.FIELDSTUB);
            printWriter.println(strEncode + "_" + strEncode2);
            StringBuilder sb2 = new StringBuilder("#define ");
            sb2.append(strEncode);
            sb2.append("_");
            printWriter.print(sb2.toString());
            printWriter.println(strEncode2 + " " + string);
        }
    }

    public void cppGuardBegin(PrintWriter printWriter) {
        printWriter.println("#ifdef __cplusplus");
        printWriter.println("extern \"C\" {");
        printWriter.println("#endif");
    }

    public void cppGuardEnd(PrintWriter printWriter) {
        printWriter.println("#ifdef __cplusplus");
        printWriter.println("}");
        printWriter.println("#endif");
    }

    public String encodeMethod(Symbol symbol, Symbol.ClassSymbol classSymbol, boolean z) throws TypeSignature.SignatureException {
        StringBuilder sb = new StringBuilder(100);
        sb.append("Java_");
        String string = classSymbol.flatname.toString();
        EncoderType encoderType = EncoderType.JNI;
        sb.append(encode(string, encoderType));
        sb.append('_');
        sb.append(encode(symbol.getSimpleName(), encoderType));
        if (z) {
            StringBuilder parameterSignature = new TypeSignature(this.types).getParameterSignature(symbol.type, true);
            sb.append("__");
            sb.append(encode(parameterSignature, encoderType));
        }
        return sb.toString();
    }

    public void fileTop(PrintWriter printWriter) {
        printWriter.println("/* DO NOT EDIT THIS FILE - it is machine generated */");
    }

    public void guardBegin(PrintWriter printWriter, String str) {
        printWriter.println("/* Header for class " + str + " */");
        printWriter.println();
        printWriter.println("#ifndef _Included_" + str);
        printWriter.println("#define _Included_" + str);
    }

    public void guardEnd(PrintWriter printWriter) {
        printWriter.println("#endif");
    }

    public void includes(PrintWriter printWriter) {
        printWriter.println("#include <jni.h>");
    }

    public final String jniType(Type type) {
        int[] iArr = AnonymousClass1.$SwitchMap$javax$lang$model$type$TypeKind;
        switch (iArr[type.getKind().ordinal()]) {
            case 1:
                return "jboolean";
            case 2:
                return "jbyte";
            case 3:
                return "jshort";
            case 4:
                return "jint";
            case 5:
                return "jlong";
            case 6:
                return "jchar";
            case 7:
                return "jfloat";
            case 8:
                return "jdouble";
            case 9:
                Type componentType = ((Type.ArrayType) type).getComponentType();
                switch (iArr[componentType.getKind().ordinal()]) {
                    case 1:
                        return "jbooleanArray";
                    case 2:
                        return "jbyteArray";
                    case 3:
                        return "jshortArray";
                    case 4:
                        return "jintArray";
                    case 5:
                        return "jlongArray";
                    case 6:
                        return "jcharArray";
                    case 7:
                        return "jfloatArray";
                    case 8:
                        return "jdoubleArray";
                    case 9:
                    case 10:
                        return "jobjectArray";
                    default:
                        throw new Error(componentType.toString());
                }
            case 10:
                Type type2 = type.tsym.type;
                Symtab symtab = this.syms;
                if (type2 == symtab.stringType) {
                    return "jstring";
                }
                if (this.types.isAssignable(type, symtab.throwableType)) {
                    return "jthrowable";
                }
                return this.types.isAssignable(type, this.syms.classType) ? "jclass" : "jobject";
            case 11:
                return PsiKeyword.VOID;
            default:
                Assert.check(false, "jni unknown type");
                return null;
        }
    }

    public FileObject write(Symbol.ClassSymbol classSymbol) throws IOException {
        JavaFileManager.Location locationForModule;
        String string = classSymbol.flatName().toString();
        if (this.multiModuleMode) {
            Symbol symbol = classSymbol.owner;
            locationForModule = this.fileManager.getLocationForModule(StandardLocation.NATIVE_HEADER_OUTPUT, (symbol.kind == Kinds.Kind.MDL ? (Symbol.ModuleSymbol) symbol : classSymbol.packge().modle).name.toString());
        } else {
            locationForModule = StandardLocation.NATIVE_HEADER_OUTPUT;
        }
        String str = string.replaceAll("[.$]", "_") + ".h";
        String strPut = this.filesWritten.put(str, string);
        if (strPut != null) {
            b16.a("native header file collision between %s and %s (both generate %s)", new Object[]{strPut, string, str});
            return null;
        }
        FileObject fileForOutput = this.fileManager.getFileForOutput(locationForModule, "", str, null);
        PrintWriter printWriter = new PrintWriter(fileForOutput.openWriter());
        try {
            write(printWriter, classSymbol);
            if (this.verbose) {
                this.log.printVerbose("wrote.file", fileForOutput.getName());
            }
            printWriter.close();
            return fileForOutput;
        } catch (Throwable th) {
            printWriter.close();
            fileForOutput.delete();
            throw th;
        }
    }

    public void writeMethods(PrintWriter printWriter, Symbol.ClassSymbol classSymbol, String str) throws IOException, TypeSignature.SignatureException {
        List<Symbol> enclosedElements = classSymbol.getEnclosedElements();
        for (Symbol symbol : enclosedElements) {
            if (isNative(symbol)) {
                TypeSignature typeSignature = new TypeSignature(this.types);
                Name simpleName = symbol.getSimpleName();
                boolean z = false;
                for (Symbol symbol2 : enclosedElements) {
                    if (symbol2 != symbol && simpleName.equals(symbol2.getSimpleName()) && isNative(symbol2)) {
                        z = true;
                    }
                }
                printWriter.println("/*");
                printWriter.println(" * Class:     " + str);
                printWriter.println(" * Method:    " + encode(simpleName, EncoderType.FIELDSTUB));
                printWriter.println(" * Signature: " + ((Object) typeSignature.getSignature(symbol.type)));
                printWriter.println(" */");
                printWriter.println("JNIEXPORT " + jniType(this.types.erasure(symbol.type.mo73getReturnType())) + " JNICALL " + encodeMethod(symbol, classSymbol, z));
                printWriter.print("  (JNIEnv *, ");
                printWriter.print(symbol.isStatic() ? "jclass" : "jobject");
                for (Type type : this.types.erasure(symbol.type.mo71getParameterTypes())) {
                    printWriter.print(", ");
                    printWriter.print(jniType(type));
                }
                printWriter.println(");");
                printWriter.println();
            }
        }
    }

    public void writeStatics(PrintWriter printWriter, Symbol.ClassSymbol classSymbol) throws IOException {
        ArrayList arrayList = new ArrayList();
        for (Symbol.ClassSymbol classSymbol2 = classSymbol; classSymbol2 != null; classSymbol2 = (Symbol.ClassSymbol) classSymbol2.getSuperclass().tsym) {
            arrayList.add(classSymbol2);
        }
        Collections.reverse(arrayList);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            for (Symbol symbol : ((Symbol.ClassSymbol) it.next()).getEnclosedElements()) {
                if (isFinal(symbol) && symbol.isStatic() && symbol.kind == Kinds.Kind.VAR) {
                    Symbol.VarSymbol varSymbol = (Symbol.VarSymbol) symbol;
                    if (varSymbol.getConstantValue() != null) {
                        printStaticDefines(printWriter, new Pair(classSymbol, varSymbol));
                    }
                }
            }
        }
    }

    public static class TypeSignature {
        private static final String SIG_ARRAY = "[";
        private static final String SIG_BOOLEAN = "Z";
        private static final String SIG_BYTE = "B";
        private static final String SIG_CHAR = "C";
        private static final String SIG_CLASS = "L";
        private static final String SIG_DOUBLE = "D";
        private static final String SIG_FLOAT = "F";
        private static final String SIG_INT = "I";
        private static final String SIG_LONG = "J";
        private static final String SIG_SHORT = "S";
        private static final String SIG_VOID = "V";
        JavacElements elems;
        Types types;

        public static class SignatureException extends Exception {
            private static final long serialVersionUID = 1;

            public SignatureException(String str) {
                super(str);
            }
        }

        public TypeSignature(Types types) {
            this.types = types;
        }

        public StringBuilder getJvmSignature(Type type, boolean z) {
            Type typeErasure = this.types.erasure(type);
            StringBuilder sb = new StringBuilder();
            new JvmTypeVisitor(z).visitType(typeErasure, sb);
            return sb;
        }

        public StringBuilder getParameterSignature(Type type, boolean z) throws SignatureException {
            StringBuilder sb = new StringBuilder();
            Iterator<Type> it = type.mo71getParameterTypes().iterator();
            while (it.hasNext()) {
                sb.append((CharSequence) getJvmSignature(it.next(), z));
            }
            return sb;
        }

        public StringBuilder getReturnSignature(Type type) throws SignatureException {
            return getJvmSignature(type.mo73getReturnType(), false);
        }

        public StringBuilder getSignature(Type type) throws SignatureException {
            StringBuilder sb = new StringBuilder();
            sb.append("(");
            sb.append((CharSequence) getParameterSignature(type, false));
            sb.append(")");
            sb.append((CharSequence) getReturnSignature(type));
            return sb;
        }

        public static class JvmTypeVisitor extends SimpleTypeVisitor<Type, StringBuilder> {
            private final boolean useFlatname;

            public JvmTypeVisitor(boolean z) {
                this.useFlatname = z;
            }

            private String getJvmPrimitiveSignature(Type type) {
                int i = AnonymousClass1.$SwitchMap$javax$lang$model$type$TypeKind[type.getKind().ordinal()];
                if (i == 11) {
                    return TypeSignature.SIG_VOID;
                }
                switch (i) {
                    case 1:
                        return "Z";
                    case 2:
                        return TypeSignature.SIG_BYTE;
                    case 3:
                        return TypeSignature.SIG_SHORT;
                    case 4:
                        return "I";
                    case 5:
                        return TypeSignature.SIG_LONG;
                    case 6:
                        return TypeSignature.SIG_CHAR;
                    case 7:
                        return TypeSignature.SIG_FLOAT;
                    case 8:
                        return TypeSignature.SIG_DOUBLE;
                    default:
                        Assert.error("unknown type: should not happen");
                        return null;
                }
            }

            private void setDeclaredType(Type type, StringBuilder sb) {
                String strReplace = (this.useFlatname ? type.tsym.flatName().toString() : type.tsym.getQualifiedName().toString()).replace('.', '/');
                sb.append(TypeSignature.SIG_CLASS);
                sb.append(strReplace);
                sb.append(";");
            }

            @Override // com.sun.tools.javac.jvm.JNIWriter.SimpleTypeVisitor, com.sun.tools.javac.code.Type.Visitor
            public Type visitArrayType(Type.ArrayType arrayType, StringBuilder sb) {
                sb.append(TypeSignature.SIG_ARRAY);
                return (Type) arrayType.getComponentType().accept(this, sb);
            }

            @Override // com.sun.tools.javac.jvm.JNIWriter.SimpleTypeVisitor, com.sun.tools.javac.code.Type.Visitor
            public Type visitType(Type type, StringBuilder sb) {
                if (!type.isPrimitiveOrVoid()) {
                    return (Type) type.accept(this, sb);
                }
                sb.append(getJvmPrimitiveSignature(type));
                return null;
            }

            @Override // com.sun.tools.javac.jvm.JNIWriter.SimpleTypeVisitor, com.sun.tools.javac.code.Type.Visitor
            public Type visitClassType(Type.ClassType classType, StringBuilder sb) {
                setDeclaredType(classType, sb);
                return null;
            }
        }
    }

    public static class SimpleTypeVisitor<R, P> implements Type.Visitor<R, P> {
        protected final R DEFAULT_VALUE;

        public SimpleTypeVisitor() {
            this.DEFAULT_VALUE = null;
        }

        public R defaultAction(Type type, P p) {
            return this.DEFAULT_VALUE;
        }

        @Override // com.sun.tools.javac.code.Type.Visitor
        public R visitArrayType(Type.ArrayType arrayType, P p) {
            return defaultAction(arrayType, p);
        }

        @Override // com.sun.tools.javac.code.Type.Visitor
        public R visitCapturedType(Type.CapturedType capturedType, P p) {
            return defaultAction(capturedType, p);
        }

        @Override // com.sun.tools.javac.code.Type.Visitor
        public R visitClassType(Type.ClassType classType, P p) {
            return defaultAction(classType, p);
        }

        @Override // com.sun.tools.javac.code.Type.Visitor
        public R visitErrorType(Type.ErrorType errorType, P p) {
            return defaultAction(errorType, p);
        }

        @Override // com.sun.tools.javac.code.Type.Visitor
        public R visitForAll(Type.ForAll forAll, P p) {
            return defaultAction(forAll, p);
        }

        @Override // com.sun.tools.javac.code.Type.Visitor
        public R visitMethodType(Type.MethodType methodType, P p) {
            return defaultAction(methodType, p);
        }

        @Override // com.sun.tools.javac.code.Type.Visitor
        public R visitModuleType(Type.ModuleType moduleType, P p) {
            return defaultAction(moduleType, p);
        }

        @Override // com.sun.tools.javac.code.Type.Visitor
        public R visitPackageType(Type.PackageType packageType, P p) {
            return defaultAction(packageType, p);
        }

        @Override // com.sun.tools.javac.code.Type.Visitor
        public R visitType(Type type, P p) {
            return defaultAction(type, p);
        }

        @Override // com.sun.tools.javac.code.Type.Visitor
        public R visitTypeVar(Type.TypeVar typeVar, P p) {
            return defaultAction(typeVar, p);
        }

        @Override // com.sun.tools.javac.code.Type.Visitor
        public R visitUndetVar(Type.UndetVar undetVar, P p) {
            return defaultAction(undetVar, p);
        }

        @Override // com.sun.tools.javac.code.Type.Visitor
        public R visitWildcardType(Type.WildcardType wildcardType, P p) {
            return defaultAction(wildcardType, p);
        }

        public SimpleTypeVisitor(R r) {
            this.DEFAULT_VALUE = r;
        }
    }

    public boolean needsHeader(Symbol.ClassSymbol classSymbol) {
        lazyInit();
        if (classSymbol.isDirectlyOrIndirectlyLocal() || isSynthetic(classSymbol)) {
            return false;
        }
        if (this.checkAll) {
            return needsHeader(classSymbol.outermostClass(), true);
        }
        return needsHeader(classSymbol, false);
    }

    public void write(PrintWriter printWriter, Symbol.ClassSymbol classSymbol) throws IOException {
        lazyInit();
        try {
            String strEncode = encode(classSymbol.fullname, EncoderType.CLASS);
            fileTop(printWriter);
            includes(printWriter);
            guardBegin(printWriter, strEncode);
            cppGuardBegin(printWriter);
            writeStatics(printWriter, classSymbol);
            writeMethods(printWriter, classSymbol, strEncode);
            cppGuardEnd(printWriter);
            guardEnd(printWriter);
        } catch (TypeSignature.SignatureException e) {
            throw new IOException(e);
        }
    }
}
