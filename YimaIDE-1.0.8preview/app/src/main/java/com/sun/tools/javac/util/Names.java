package com.sun.tools.javac.util;

import com.intellij.psi.PsiKeyword;
import com.reandroid.arsc.value.CompoundEntry;
import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;
import com.sun.tools.classfile.Attribute;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Names {
    public static final Context.Key<Names> namesKey = new Context.Key<>();
    public final Name.Table table;
    public final Name asterisk = fromString("*");
    public final Name comma = fromString(",");
    public final Name empty = fromString("");
    public final Name hyphen = fromString("-");
    public final Name one = fromString("1");
    public final Name slash = fromString(PsuedoNames.PSEUDONAME_ROOT);
    public final Name _class = fromString("class");
    public final Name _super = fromString(PsiKeyword.SUPER);
    public final Name _this = fromString(PsiKeyword.THIS);
    public final Name var = fromString(PsiKeyword.VAR);
    public final Name exports = fromString(PsiKeyword.EXPORTS);
    public final Name opens = fromString(PsiKeyword.OPENS);
    public final Name module = fromString(PsiKeyword.MODULE);
    public final Name provides = fromString(PsiKeyword.PROVIDES);
    public final Name requires = fromString(PsiKeyword.REQUIRES);
    public final Name to = fromString(PsiKeyword.TO);
    public final Name transitive = fromString(PsiKeyword.TRANSITIVE);
    public final Name uses = fromString(PsiKeyword.USES);
    public final Name open = fromString(PsiKeyword.OPEN);
    public final Name underscore = fromString("_");
    public final Name when = fromString("when");
    public final Name with = fromString(PsiKeyword.WITH);
    public final Name yield = fromString(PsiKeyword.YIELD);
    public final Name _name = fromString("name");
    public final Name addSuppressed = fromString("addSuppressed");
    public final Name any = fromString("<any>");
    public final Name append = fromString("append");
    public final Name clinit = fromString(Const.STATIC_INITIALIZER_NAME);
    public final Name clone = fromString("clone");
    public final Name close = fromString("close");
    public final Name deserializeLambda = fromString("$deserializeLambda$");
    public final Name desiredAssertionStatus = fromString("desiredAssertionStatus");
    public final Name equals = fromString("equals");
    public final Name error = fromString("<error>");
    public final Name finalize = fromString("finalize");
    public final Name forRemoval = fromString("forRemoval");
    public final Name reflective = fromString("reflective");
    public final Name getClass = fromString("getClass");
    public final Name hasNext = fromString("hasNext");
    public final Name hashCode = fromString("hashCode");
    public final Name init = fromString(Const.CONSTRUCTOR_NAME);
    public final Name invoke = fromString("invoke");
    public final Name iterator = fromString(com.sun.org.apache.xalan.internal.xsltc.compiler.Constants.ITERATOR_PNAME);
    public final Name length = fromString("length");
    public final Name next = fromString(com.sun.org.apache.xalan.internal.xsltc.compiler.Constants.NEXT);
    public final Name of = fromString("of");
    public final Name ordinal = fromString("ordinal");
    public final Name provider = fromString("provider");
    public final Name serialVersionUID = fromString("serialVersionUID");
    public final Name toString = fromString("toString");
    public final Name value = fromString("value");
    public final Name valueOf = fromString("valueOf");
    public final Name values = fromString(CompoundEntry.NAME_values);
    public final Name readResolve = fromString("readResolve");
    public final Name readObject = fromString("readObject");
    public final Name dollarThis = fromString("$this");
    public final Name java_io_Serializable = fromString("java.io.Serializable");
    public final Name java_lang_Class = fromString(com.sun.org.apache.xalan.internal.xsltc.compiler.Constants.CLASS_CLASS);
    public final Name java_lang_Cloneable = fromString("java.lang.Cloneable");
    public final Name java_lang_Enum = fromString("java.lang.Enum");
    public final Name java_lang_Object = fromString(com.sun.org.apache.xalan.internal.xsltc.compiler.Constants.OBJECT_CLASS);
    public final Name Array = fromString("Array");
    public final Name Bound = fromString("Bound");
    public final Name Method = fromString("Method");
    public final Name java = fromString("java");
    public final Name java_lang = fromString("java.lang");
    public final Name jdk_internal_javac = fromString("jdk.internal.javac");
    public final Name java_base = fromString("java.base");
    public final Name java_se = fromString("java.se");
    public final Name jdk_unsupported = fromString("jdk.unsupported");
    public final Name Annotation = fromString("Annotation");
    public final Name AnnotationDefault = fromString(Attribute.AnnotationDefault);
    public final Name BootstrapMethods = fromString(Attribute.BootstrapMethods);
    public final Name Bridge = fromString("Bridge");
    public final Name CharacterRangeTable = fromString(Attribute.CharacterRangeTable);
    public final Name Code = fromString(Attribute.Code);
    public final Name CompilationID = fromString(Attribute.CompilationID);
    public final Name ConstantValue = fromString(Attribute.ConstantValue);
    public final Name Deprecated = fromString(Attribute.Deprecated);
    public final Name EnclosingMethod = fromString(Attribute.EnclosingMethod);
    public final Name Enum = fromString("Enum");
    public final Name Exceptions = fromString(Attribute.Exceptions);
    public final Name InnerClasses = fromString(Attribute.InnerClasses);
    public final Name LineNumberTable = fromString(Attribute.LineNumberTable);
    public final Name LocalVariableTable = fromString(Attribute.LocalVariableTable);
    public final Name LocalVariableTypeTable = fromString(Attribute.LocalVariableTypeTable);
    public final Name MethodParameters = fromString(Attribute.MethodParameters);
    public final Name Module = fromString(Attribute.Module);
    public final Name ModuleResolution = fromString(Attribute.ModuleResolution);
    public final Name NestHost = fromString(Attribute.NestHost);
    public final Name NestMembers = fromString(Attribute.NestMembers);
    public final Name Record = fromString(Attribute.Record);
    public final Name RuntimeInvisibleAnnotations = fromString(Attribute.RuntimeInvisibleAnnotations);
    public final Name RuntimeInvisibleParameterAnnotations = fromString(Attribute.RuntimeInvisibleParameterAnnotations);
    public final Name RuntimeInvisibleTypeAnnotations = fromString(Attribute.RuntimeInvisibleTypeAnnotations);
    public final Name RuntimeVisibleAnnotations = fromString(Attribute.RuntimeVisibleAnnotations);
    public final Name RuntimeVisibleParameterAnnotations = fromString(Attribute.RuntimeVisibleParameterAnnotations);
    public final Name RuntimeVisibleTypeAnnotations = fromString(Attribute.RuntimeVisibleTypeAnnotations);
    public final Name Signature = fromString(Attribute.Signature);
    public final Name SourceFile = fromString(Attribute.SourceFile);
    public final Name SourceID = fromString(Attribute.SourceID);
    public final Name StackMap = fromString(Attribute.StackMap);
    public final Name StackMapTable = fromString(Attribute.StackMapTable);
    public final Name Synthetic = fromString(Attribute.Synthetic);
    public final Name Value = fromString("Value");
    public final Name Varargs = fromString("Varargs");
    public final Name PermittedSubclasses = fromString(Attribute.PermittedSubclasses);
    public final Name ANNOTATION_TYPE = fromString("ANNOTATION_TYPE");
    public final Name CONSTRUCTOR = fromString("CONSTRUCTOR");
    public final Name FIELD = fromString("FIELD");
    public final Name LOCAL_VARIABLE = fromString("LOCAL_VARIABLE");
    public final Name METHOD = fromString("METHOD");
    public final Name MODULE = fromString("MODULE");
    public final Name PACKAGE = fromString("PACKAGE");
    public final Name PARAMETER = fromString("PARAMETER");
    public final Name TYPE = fromString("TYPE");
    public final Name TYPE_PARAMETER = fromString("TYPE_PARAMETER");
    public final Name TYPE_USE = fromString("TYPE_USE");
    public final Name RECORD_COMPONENT = fromString("RECORD_COMPONENT");
    public final Name CLASS = fromString("CLASS");
    public final Name RUNTIME = fromString("RUNTIME");
    public final Name SOURCE = fromString("SOURCE");
    public final Name T = fromString("T");
    public final Name ex = fromString("ex");
    public final Name module_info = fromString("module-info");
    public final Name package_info = fromString("package-info");
    public final Name requireNonNull = fromString("requireNonNull");
    public final Name main = fromString("main");
    public final Name lambda = fromString("lambda$");
    public final Name metafactory = fromString("metafactory");
    public final Name altMetafactory = fromString("altMetafactory");
    public final Name makeConcat = fromString("makeConcat");
    public final Name makeConcatWithConstants = fromString("makeConcatWithConstants");
    public final Name bootstrap = fromString("bootstrap");
    public final Name record = fromString(PsiKeyword.RECORD);
    public final Name non = fromString("non");
    public final Name serialPersistentFields = fromString("serialPersistentFields");
    public final Name writeObject = fromString("writeObject");
    public final Name writeReplace = fromString("writeReplace");
    public final Name readObjectNoData = fromString("readObjectNoData");
    public final Name permits = fromString(PsiKeyword.PERMITS);
    public final Name sealed = fromString(PsiKeyword.SEALED);
    public final Name typeSwitch = fromString("typeSwitch");
    public final Name enumSwitch = fromString("enumSwitch");
    public final Name enumConstant = fromString("enumConstant");
    public final Name requiresIdentityInternal = fromString("jdk.internal.RequiresIdentity+Annotation");

    public Names(Context context) {
        this.table = createTable(Options.instance(context));
    }

    public static Names instance(Context context) {
        Context.Key<Names> key = namesKey;
        Names names = (Names) context.get(key);
        if (names != null) {
            return names;
        }
        Names names2 = new Names(context);
        context.put(key, names2);
        return names2;
    }

    public Name.Table createTable(Options options) {
        if (options.isSet("useUnsharedTable")) {
            return newUnsharedNameTable();
        }
        return options.isSet("useSharedTable") ? newSharedNameTable() : newStringNameTable(options.isSet("internStringTable"));
    }

    public void dispose() {
        this.table.dispose();
    }

    public Name fromChars(char[] cArr, int i, int i2) {
        return this.table.fromChars(cArr, i, i2);
    }

    public Name fromString(String str) {
        return this.table.fromString(str);
    }

    public Name fromUtf(byte[] bArr) throws InvalidUtfException {
        return this.table.fromUtf(bArr);
    }

    public Name fromUtfLax(byte[] bArr, int i, int i2) {
        try {
            return this.table.fromUtf(bArr, i, i2, Convert.Validation.NONE);
        } catch (InvalidUtfException e) {
            x01.a(e);
            return null;
        }
    }

    public SharedNameTable newSharedNameTable() {
        return SharedNameTable.create(this);
    }

    public StringNameTable newStringNameTable(boolean z) {
        return StringNameTable.create(this, z);
    }

    public UnsharedNameTable newUnsharedNameTable() {
        return UnsharedNameTable.create(this);
    }

    public Name fromUtf(byte[] bArr, int i, int i2, Convert.Validation validation) throws InvalidUtfException {
        return this.table.fromUtf(bArr, i, i2, validation);
    }
}
