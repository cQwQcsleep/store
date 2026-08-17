package org.eclipse.jdt.internal.compiler.lookup;

import org.eclipse.jdt.core.compiler.CharOperation;
import org.eclipse.jdt.internal.compiler.util.Util;
import org.jetbrains.kotlin.backend.common.serialization.mangle.MangleConstant;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public interface TypeConstants {
    public static final char[] ANNOTATION;
    public static final char[] APACHE;
    public static final char[][] APACHE_DBUTILS;
    public static final char[][] APACHE_IOUTILS;
    public static final char[] API;
    public static final char[] ASSERTIONS_CLASS;
    public static final char[] ASSERT_CLASS;
    public static final char[] ASSERT_FALSE;
    public static final char[] ASSERT_NOTNULL;
    public static final char[] ASSERT_NULL;
    public static final char[] ASSERT_TRUE;
    public static final String AUTOMATIC_MODULE_NAME = "Automatic-Module-Name";
    public static final char[] AUTOWIRED;
    public static final char[] BEANS;
    public static final char[] CHANNELS;
    public static final char[] CHECK_ARGUMENT;
    public static final char[] CHECK_NOT_NULL;
    public static final char[] CHECK_STATE;
    public static final char[] CLINIT;
    public static final char[] CLOSE;
    public static final char[] CLOSE_QUIETLY;
    public static final char[] COM;
    public static final char[] COMMONS;
    public static final char[] COMPILER;
    public static final char[][] COM_GOOGLE_COMMON_BASE_PRECONDITIONS;
    public static final char[][] COM_GOOGLE_INJECT_INJECT;
    public static final int CONSTRAINT_EQUAL = 0;
    public static final int CONSTRAINT_EXTENDS = 1;
    public static final int CONSTRAINT_SUPER = 2;
    public static final char[] CONTAINS;
    public static final char[] CONTAINS_ALL;
    public static final char[] CONTAINS_KEY;
    public static final char[] CONTAINS_VALUE;
    public static final char[] CORE;
    public static final char[] DEFAULT_LOCATION__ARRAY_CONTENTS;
    public static final char[] DEFAULT_LOCATION__FIELD;
    public static final char[] DEFAULT_LOCATION__PARAMETER;
    public static final char[] DEFAULT_LOCATION__RETURN_TYPE;
    public static final char[] DEFAULT_LOCATION__TYPE_ARGUMENT;
    public static final char[] DEFAULT_LOCATION__TYPE_BOUND;
    public static final char[] DEFAULT_LOCATION__TYPE_PARAMETER;
    public static final char[] DOM;
    public static final char[] ECLIPSE;
    public static final char[] ESSENTIAL_API;
    public static final char[] FACTORY;
    public static final char[] FILTER;
    public static final char[][][] FLUENT_RESOURCE_CLASSES;
    public static final char[] FOR_REMOVAL;
    public static final char[] FRAMEWORK;
    public static final char[] GET;
    public static final char[] GOOGLE;
    public static final char[][] GUAVA_CLOSEABLES;
    public static final String IMPLEMENTS = "implements";
    public static final char[] INDEX_OF;
    public static final char[] INIT;
    public static final char[] INJECT_PACKAGE;
    public static final char[] INJECT_TYPE;
    public static final char[] INTERNAL;
    public static final char[] INVOKE;
    public static final char[] IO;
    public static final char[] IS_INSTANCE;
    public static final char[] IS_NOTNULL;
    public static final char[] IS_NULL;
    public static final char[] IS_TRUE;
    public static final char[] ITYPEBINDING;
    public static final char[] JAKARTA;
    public static final char[][] JAKARTA_ANNOTATION_INJECT_INJECT;
    public static final char[] JAVA;
    public static final char[] JAVAC;
    public static final char[] JAVAX;
    public static final char[][] JAVAX_ANNOTATION_INJECT_INJECT;
    public static final char[][] JAVAX_RMI_CORBA_STUB;
    public static final char[] JAVA_BASE;
    public static final char[][] JAVA_IO;
    public static final char[][] JAVA_IO_CLOSEABLE;
    public static final char[][] JAVA_IO_EXTERNALIZABLE;
    public static final char[][] JAVA_IO_IOEXCEPTION;
    public static final char[][] JAVA_IO_OBJECTINPUTSTREAM;
    public static final char[][] JAVA_IO_OBJECTOUTPUTSTREAM;
    public static final char[][] JAVA_IO_OBJECTSTREAMEXCEPTION;
    public static final char[][] JAVA_IO_PRINTSTREAM;
    public static final char[][] JAVA_IO_RESOURCE_FREE_CLOSEABLES;
    public static final char[][] JAVA_IO_SERIALIZABLE;
    public static final char[][] JAVA_IO_WRAPPER_CLOSEABLES;
    public static final char[][] JAVA_LANG;
    public static final char[][] JAVA_LANG_ANNOTATION;
    public static final char[][] JAVA_LANG_ANNOTATION_ANNOTATION;
    public static final char[][] JAVA_LANG_ANNOTATION_DOCUMENTED;
    public static final char[][] JAVA_LANG_ANNOTATION_ELEMENTTYPE;
    public static final char[][] JAVA_LANG_ANNOTATION_INHERITED;
    public static final char[][] JAVA_LANG_ANNOTATION_REPEATABLE;
    public static final char[][] JAVA_LANG_ANNOTATION_RETENTION;
    public static final char[][] JAVA_LANG_ANNOTATION_RETENTIONPOLICY;
    public static final char[][] JAVA_LANG_ANNOTATION_TARGET;
    public static final char[][] JAVA_LANG_ASSERTIONERROR;
    public static final char[][] JAVA_LANG_AUTOCLOSEABLE;
    public static final char[][] JAVA_LANG_BOOLEAN;
    public static final char[][] JAVA_LANG_BYTE;
    public static final char[][] JAVA_LANG_CHARACTER;
    public static final char[][] JAVA_LANG_CLASS;
    public static final char[][] JAVA_LANG_CLASSNOTFOUNDEXCEPTION;
    public static final char[][] JAVA_LANG_CLONEABLE;
    public static final char[][] JAVA_LANG_CONSTANT_CLASSDESC;
    public static final char[][] JAVA_LANG_DEPRECATED;
    public static final char[][] JAVA_LANG_DOUBLE;
    public static final char[][] JAVA_LANG_ENUM;
    public static final char[][] JAVA_LANG_ENUM_ENUMDESC;
    public static final char[][] JAVA_LANG_ERROR;
    public static final char[][] JAVA_LANG_EXCEPTION;
    public static final char[][] JAVA_LANG_FLOAT;
    public static final char[][] JAVA_LANG_FUNCTIONAL_INTERFACE;
    public static final char[][] JAVA_LANG_ILLEGALARGUMENTEXCEPTION;
    public static final char[][] JAVA_LANG_INCOMPATIBLECLASSCHANGEERROR;
    public static final char[][] JAVA_LANG_INTEGER;
    public static final char[][] JAVA_LANG_INVOKE_CONSTANTBOOTSTRAP;
    public static final char[][] JAVA_LANG_INVOKE_LAMBDAMETAFACTORY;
    public static final char[][] JAVA_LANG_INVOKE_METHODHANDLE;
    public static final char[][] JAVA_LANG_INVOKE_METHODHANDLES;
    public static final char[][] JAVA_LANG_INVOKE_METHODHANDLE_$_POLYMORPHICSIGNATURE;
    public static final char[][] JAVA_LANG_INVOKE_METHODHANDLE_POLYMORPHICSIGNATURE;
    public static final char[][] JAVA_LANG_INVOKE_SERIALIZEDLAMBDA;
    public static final char[][] JAVA_LANG_INVOKE_STRING_CONCAT_FACTORY;
    public static final char[][] JAVA_LANG_INVOKE_VARHANDLE;
    public static final char[][] JAVA_LANG_ITERABLE;
    public static final char[][] JAVA_LANG_LONG;
    public static final char[][] JAVA_LANG_NOCLASSDEFERROR;
    public static final char[][] JAVA_LANG_NOSUCHFIELDERROR;
    public static final char[][] JAVA_LANG_OBJECT;
    public static final char[][] JAVA_LANG_OVERRIDE;
    public static final char[][] JAVA_LANG_RECORD;
    public static final char[][] JAVA_LANG_REFLECT_CONSTRUCTOR;
    public static final char[][] JAVA_LANG_REFLECT_FIELD;
    public static final char[][] JAVA_LANG_REFLECT_METHOD;
    public static final char[][] JAVA_LANG_RUNTIMEEXCEPTION;
    public static final char[][] JAVA_LANG_RUNTIME_OBJECTMETHODS;
    public static final char[][] JAVA_LANG_RUNTIME_SWITCHBOOTSTRAPS;
    public static final char[][] JAVA_LANG_RUNTIME_TEMPLATERUNTIME;
    public static final char[][] JAVA_LANG_SAFEVARARGS;
    public static final char[][] JAVA_LANG_SHORT;
    public static final char[][] JAVA_LANG_STRING;
    public static final char[][] JAVA_LANG_STRINGBUFFER;
    public static final char[][] JAVA_LANG_STRINGBUILDER;
    public static final char[][] JAVA_LANG_STRINGTEMPLATE;
    public static final char[][] JAVA_LANG_STRINGTEMPLATE_PROCESSOR;
    public static final char[][] JAVA_LANG_STRING_TEMPLATE_STR;
    public static final char[][] JAVA_LANG_SUPPRESSWARNINGS;
    public static final char[][] JAVA_LANG_SYSTEM;
    public static final char[][] JAVA_LANG_THROWABLE;
    public static final char[][] JAVA_LANG_VOID;
    public static final char[][] JAVA_NIO_FILE_FILES;
    public static final char[][] JAVA_UTIL_ARRAYS;
    public static final char[][] JAVA_UTIL_COLLECTION;
    public static final char[][] JAVA_UTIL_ITERATOR;
    public static final char[][] JAVA_UTIL_LIST;
    public static final char[][] JAVA_UTIL_MAP;
    public static final char[][] JAVA_UTIL_OBJECTS;
    public static final char[][] JAVA_UTIL_STREAM;
    public static final char[][] JAVA_UTIL_STREAM__STREAM;
    public static final char[][] JAVA_UTIL_ZIP_WRAPPER_CLOSEABLES;
    public static final char[] JDK;
    public static final char[][] JDK_INTERNAL_JAVAC_PREVIEW_FEATURE;
    public static final char[][] JDK_INTERNAL_PREVIEW_FEATURE;
    public static final char[][] JDK_INTERNAL_VALUEBASED;
    public static final char[] JDT;
    public static final char[] JUNIT;
    public static final char[][] JUNIT_FRAMEWORK_ASSERT;
    public static final char[] JUPITER;
    public static final String KEYWORD_EXTENDS = "extends";
    public static final char[] LANG;
    public static final char[] LANG3;
    public static final char[] LAST_INDEX_OF;
    public static final char[] LOOKUP;
    public static final String META_INF_MANIFEST_MF = "META-INF/MANIFEST.MF";
    public static final char[] METHOD_SOURCE;
    public static final char[] MODULE_INFO_CLASS_NAME;
    public static final String MODULE_INFO_CLASS_NAME_STRING = "module-info.class";
    public static final char[] MODULE_INFO_FILE_NAME;
    public static final String MODULE_INFO_FILE_NAME_STRING = "module-info.java";
    public static final char[] MODULE_INFO_NAME;
    public static final String MODULE_INFO_NAME_STRING = "module-info";
    public static final char[] NEXT;
    public static final char[] NIO;
    public static final char[] NON_NULL;
    public static final char[] NOT_NULL;
    public static final char[] OBJECT;
    public static final char[][] ONE_UTIL_STREAMEX;
    public static final char[] OPTIONAL;
    public static final char[] ORG;
    public static final char[][] ORG_APACHE_COMMONS_LANG3_VALIDATE;
    public static final char[][] ORG_APACHE_COMMONS_LANG_VALIDATE;
    public static final char[][] ORG_ECLIPSE_CORE_RUNTIME_ASSERT;
    public static final char[][] ORG_ECLIPSE_JDT_CORE_DOM_ITYPEBINDING;
    public static final char[][] ORG_ECLIPSE_JDT_INTERNAL_COMPILER_LOOKUP_TYPEBINDING;
    public static final char[][] ORG_JUNIT_ASSERT;
    public static final char[][] ORG_JUNIT_JUPITER_API_ASSERTIONS;
    public static final char[][] ORG_JUNIT_METHOD_SOURCE;
    public static final char[][] ORG_SPRING_AUTOWIRED;
    public static final char[][][] OTHER_WRAPPER_CLOSEABLES;
    public static final char[] PACKAGE_INFO_NAME;
    public static final char[] PARAMS;
    public static final char[] PROVIDER;
    public static final char[] RECORD_CLASS;
    public static final char[] REFLECT;
    public static final char[] REMOVE;
    public static final char[] REMOVE_ALL;
    public static final char[] REQUIRED;
    public static final char[] REQUIRE_NON_NULL;
    public static final char[][] RESOURCE_FREE_CLOSEABLE_J_U_STREAMS;
    public static final char[][] RESOURCE_FREE_CLOSEABLE_STREAMEX;
    public static final char[] RETAIN_ALL;
    public static final char[] RUNTIME;
    public static final char[] SINCE;
    public static final char[] SPRING;
    public static final char[] SYNTHETIC_ACCESS_METHOD_PREFIX;
    public static final char[] SYNTHETIC_ASSERT_DISABLED;
    public static final char[] SYNTHETIC_CLASS;
    public static final char[] SYNTHETIC_ENCLOSING_INSTANCE_PREFIX;
    public static final char[] SYNTHETIC_ENUM_CONSTANT_INITIALIZATION_METHOD_PREFIX;
    public static final char[] SYNTHETIC_ENUM_VALUES;
    public static final char[] SYNTHETIC_OUTER_LOCAL_PREFIX;
    public static final char[] SYNTHETIC_STATIC_FACTORY;
    public static final char[] SYNTHETIC_SWITCH_ENUM_TABLE;
    public static final char[] TYPEBINDING;
    public static final char[] UTIL;
    public static final char[] VALIDATE_CLASS;
    public static final CloseMethodRecord[] closeMethods;
    public static final char[] ZIP = {'z', 'i', 'p'};
    public static final char[] LENGTH = {'l', 'e', 'n', 'g', 't', 'h'};
    public static final char[] CLONE = {'c', 'l', 'o', 'n', 'e'};
    public static final char[] EQUALS = {'e', 'q', 'u', 'a', 'l', 's'};
    public static final char[] GETCLASS = {'g', 'e', 't', Util.C_CHAR, 'l', 'a', 's', 's'};
    public static final char[] HASHCODE = {'h', 'a', 's', 'h', Util.C_CHAR, 'o', 'd', 'e'};
    public static final char[] TOSTRING = {'t', 'o', Util.C_SHORT, 't', 'r', 'i', 'n', 'g'};
    public static final char[] MAIN = {'m', 'a', 'i', 'n'};
    public static final char[] SERIALVERSIONUID = {'s', 'e', 'r', 'i', 'a', 'l', Util.C_VOID, 'e', 'r', 's', 'i', 'o', 'n', 'U', Util.C_INT, Util.C_DOUBLE};
    public static final char[] SERIALPERSISTENTFIELDS = {'s', 'e', 'r', 'i', 'a', 'l', 'P', 'e', 'r', 's', 'i', 's', 't', 'e', 'n', 't', Util.C_FLOAT, 'i', 'e', 'l', 'd', 's'};
    public static final char[] READRESOLVE = {'r', 'e', 'a', 'd', 'R', 'e', 's', 'o', 'l', 'v', 'e'};
    public static final char[] WRITEREPLACE = {'w', 'r', 'i', 't', 'e', 'R', 'e', 'p', 'l', 'a', 'c', 'e'};
    public static final char[] READOBJECT = {'r', 'e', 'a', 'd', 'O', 'b', 'j', 'e', 'c', 't'};
    public static final char[] WRITEOBJECT = {'w', 'r', 'i', 't', 'e', 'O', 'b', 'j', 'e', 'c', 't'};
    public static final char[] CharArray_JAVA_LANG_OBJECT = {'j', 'a', 'v', 'a', '.', 'l', 'a', 'n', 'g', '.', 'O', 'b', 'j', 'e', 'c', 't'};
    public static final char[] CharArray_JAVA_LANG_ENUM = {'j', 'a', 'v', 'a', '.', 'l', 'a', 'n', 'g', '.', 'E', 'n', 'u', 'm'};
    public static final char[] CharArray_JAVA_LANG_RECORD = {'j', 'a', 'v', 'a', '.', 'l', 'a', 'n', 'g', '.', 'R', 'e', 'c', 'o', 'r', 'd'};
    public static final char[] CharArray_JAVA_LANG_RECORD_SLASH = {'j', 'a', 'v', 'a', '/', 'l', 'a', 'n', 'g', '/', 'R', 'e', 'c', 'o', 'r', 'd'};
    public static final char[] CharArray_JAVA_LANG_ANNOTATION_ANNOTATION = {'j', 'a', 'v', 'a', '.', 'l', 'a', 'n', 'g', '.', 'a', 'n', 'n', 'o', 't', 'a', 't', 'i', 'o', 'n', '.', 'A', 'n', 'n', 'o', 't', 'a', 't', 'i', 'o', 'n'};
    public static final char[] CharArray_JAVA_IO_OBJECTINPUTSTREAM = {'j', 'a', 'v', 'a', '.', 'i', 'o', '.', 'O', 'b', 'j', 'e', 'c', 't', Util.C_INT, 'n', 'p', 'u', 't', Util.C_SHORT, 't', 'r', 'e', 'a', 'm'};
    public static final char[] CharArray_JAVA_IO_OBJECTOUTPUTSTREAM = {'j', 'a', 'v', 'a', '.', 'i', 'o', '.', 'O', 'b', 'j', 'e', 'c', 't', 'O', 'u', 't', 'p', 'u', 't', Util.C_SHORT, 't', 'r', 'e', 'a', 'm'};
    public static final char[] CharArray_JAVA_IO_OBJECTSTREAMFIELD = {'j', 'a', 'v', 'a', '.', 'i', 'o', '.', 'O', 'b', 'j', 'e', 'c', 't', Util.C_SHORT, 't', 'r', 'e', 'a', 'm', Util.C_FLOAT, 'i', 'e', 'l', 'd'};
    public static final char[] ANONYM_PREFIX = {'n', 'e', 'w', ' '};
    public static final char[] ANONYM_SUFFIX = {Util.C_PARAM_START, Util.C_PARAM_END, '{', '}'};
    public static final char[] WILDCARD_NAME = {MangleConstant.Q_MARK};
    public static final char[] WILDCARD_SUPER = {' ', 's', 'u', 'p', 'e', 'r', ' '};
    public static final char[] WILDCARD_EXTENDS = {' ', 'e', 'x', 't', 'e', 'n', 'd', 's', ' '};
    public static final char[] WILDCARD_MINUS = {Util.C_SUPER};
    public static final char[] WILDCARD_STAR = {'*'};
    public static final char[] WILDCARD_PLUS = {Util.C_EXTENDS};
    public static final char[] WILDCARD_CAPTURE_NAME_PREFIX = {'c', 'a', 'p', 't', 'u', 'r', 'e', MangleConstant.FUNCTION_NAME_PREFIX};
    public static final char[] WILDCARD_CAPTURE_NAME_SUFFIX = {Util.C_SUPER, 'o', 'f', ' '};
    public static final char[] WILDCARD_CAPTURE_SIGNABLE_NAME_SUFFIX = {'c', 'a', 'p', 't', 'u', 'r', 'e', Util.C_SUPER, 'o', 'f', ' '};
    public static final char[] WILDCARD_CAPTURE = {Util.C_CAPTURE};
    public static final char[] CAPTURE18 = {Util.C_EXCEPTION_START};
    public static final char[] BYTE = {'b', 'y', 't', 'e'};
    public static final char[] SHORT = {'s', 'h', 'o', 'r', 't'};
    public static final char[] INT = {'i', 'n', 't'};
    public static final char[] LONG = {'l', 'o', 'n', 'g'};
    public static final char[] FLOAT = {'f', 'l', 'o', 'a', 't'};
    public static final char[] DOUBLE = {'d', 'o', 'u', 'b', 'l', 'e'};
    public static final char[] CHAR = {'c', 'h', 'a', 'r'};
    public static final char[] BOOLEAN = {'b', 'o', 'o', 'l', 'e', 'a', 'n'};
    public static final char[] NULL = {'n', 'u', 'l', 'l'};
    public static final char[] VOID = {'v', 'o', 'i', 'd'};
    public static final char[] VALUE = {'v', 'a', 'l', 'u', 'e'};
    public static final char[] VALUES = {'v', 'a', 'l', 'u', 'e', 's'};
    public static final char[] VALUEOF = {'v', 'a', 'l', 'u', 'e', 'O', 'f'};
    public static final char[] UPPER_SOURCE = {Util.C_SHORT, 'O', 'U', 'R', Util.C_CHAR, 'E'};
    public static final char[] UPPER_CLASS = {Util.C_CHAR, Util.C_RESOLVED, 'A', Util.C_SHORT, Util.C_SHORT};
    public static final char[] UPPER_RUNTIME = {'R', 'U', 'N', Util.C_TYPE_VARIABLE, Util.C_INT, 'M', 'E'};
    public static final char[] ANNOTATION_PREFIX = {'@'};
    public static final char[] ANNOTATION_SUFFIX = {Util.C_PARAM_START, Util.C_PARAM_END};
    public static final char[] TYPE = {Util.C_TYPE_VARIABLE, 'Y', 'P', 'E'};
    public static final char[] UPPER_FIELD = {Util.C_FLOAT, Util.C_INT, 'E', Util.C_RESOLVED, Util.C_DOUBLE};
    public static final char[] UPPER_METHOD = {'M', 'E', Util.C_TYPE_VARIABLE, 'H', 'O', Util.C_DOUBLE};
    public static final char[] UPPER_PARAMETER = {'P', 'A', 'R', 'A', 'M', 'E', Util.C_TYPE_VARIABLE, 'E', 'R'};
    public static final char[] UPPER_CONSTRUCTOR = {Util.C_CHAR, 'O', 'N', Util.C_SHORT, Util.C_TYPE_VARIABLE, 'R', 'U', Util.C_CHAR, Util.C_TYPE_VARIABLE, 'O', 'R'};
    public static final char[] UPPER_LOCAL_VARIABLE = {Util.C_RESOLVED, 'O', Util.C_CHAR, 'A', Util.C_RESOLVED, '_', Util.C_VOID, 'A', 'R', Util.C_INT, 'A', Util.C_BYTE, Util.C_RESOLVED, 'E'};
    public static final char[] UPPER_ANNOTATION_TYPE = {'A', 'N', 'N', 'O', Util.C_TYPE_VARIABLE, 'A', Util.C_TYPE_VARIABLE, Util.C_INT, 'O', 'N', '_', Util.C_TYPE_VARIABLE, 'Y', 'P', 'E'};
    public static final char[] UPPER_PACKAGE = {'P', 'A', Util.C_CHAR, 'K', 'A', 'G', 'E'};
    public static final char[] ANONYMOUS_METHOD = {'l', 'a', 'm', 'b', 'd', 'a', '$'};
    public static final char[] DESERIALIZE_LAMBDA = {'$', 'd', 'e', 's', 'e', 'r', 'i', 'a', 'l', 'i', 'z', 'e', Util.C_RESOLVED, 'a', 'm', 'b', 'd', 'a', '$'};
    public static final char[] LAMBDA_TYPE = {Util.C_GENERIC_START, 'l', 'a', 'm', 'b', 'd', 'a', Util.C_GENERIC_END};
    public static final char[] UPPER_MODULE = {'M', 'O', Util.C_DOUBLE, 'U', Util.C_RESOLVED, 'E'};
    public static final char[] UPPER_RECORD_COMPONENT = {'R', 'E', Util.C_CHAR, 'O', 'R', Util.C_DOUBLE, '_', Util.C_CHAR, 'O', 'M', 'P', 'O', 'N', 'E', 'N', Util.C_TYPE_VARIABLE};
    public static final char[] YIELD = {'y', 'i', 'e', 'l', 'd'};
    public static final char[] STRING_CONCAT_MARKER_1 = {1};
    public static final char[] VAR = {'v', 'a', 'r'};
    public static final char[] RECORD_RESTRICTED_IDENTIFIER = {'r', 'e', 'c', 'o', 'r', 'd'};
    public static final char[] PERMITS = {'p', 'e', 'r', 'm', 'i', 't', 's'};
    public static final char[] SEALED = {'s', 'e', 'a', 'l', 'e', 'd'};
    public static final char[] TYPE_USE_TARGET = {Util.C_TYPE_VARIABLE, 'Y', 'P', 'E', '_', 'U', Util.C_SHORT, 'E'};
    public static final char[] TYPE_PARAMETER_TARGET = {Util.C_TYPE_VARIABLE, 'Y', 'P', 'E', '_', 'P', 'A', 'R', 'A', 'M', 'E', Util.C_TYPE_VARIABLE, 'E', 'R'};

    public enum BoundCheckStatus {
        OK,
        NULL_PROBLEM,
        UNCHECKED,
        MISMATCH;

        private static volatile /* synthetic */ int[] $SWITCH_TABLE$org$eclipse$jdt$internal$compiler$lookup$TypeConstants$BoundCheckStatus;

        public static /* synthetic */ int[] $SWITCH_TABLE$org$eclipse$jdt$internal$compiler$lookup$TypeConstants$BoundCheckStatus() {
            int[] iArr = $SWITCH_TABLE$org$eclipse$jdt$internal$compiler$lookup$TypeConstants$BoundCheckStatus;
            if (iArr != null) {
                return iArr;
            }
            int[] iArr2 = new int[valuesCustom().length];
            try {
                iArr2[MISMATCH.ordinal()] = 4;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr2[NULL_PROBLEM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr2[OK.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr2[UNCHECKED.ordinal()] = 3;
            } catch (NoSuchFieldError unused4) {
            }
            $SWITCH_TABLE$org$eclipse$jdt$internal$compiler$lookup$TypeConstants$BoundCheckStatus = iArr2;
            return iArr2;
        }

        /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
        public static BoundCheckStatus[] valuesCustom() {
            BoundCheckStatus[] boundCheckStatusArrValuesCustom = values();
            int length = boundCheckStatusArrValuesCustom.length;
            BoundCheckStatus[] boundCheckStatusArr = new BoundCheckStatus[length];
            System.arraycopy(boundCheckStatusArrValuesCustom, 0, boundCheckStatusArr, 0, length);
            return boundCheckStatusArr;
        }

        public BoundCheckStatus betterOf(BoundCheckStatus boundCheckStatus) {
            return ordinal() < boundCheckStatus.ordinal() ? this : boundCheckStatus;
        }

        public boolean isOKbyJLS() {
            int i = $SWITCH_TABLE$org$eclipse$jdt$internal$compiler$lookup$TypeConstants$BoundCheckStatus()[ordinal()];
            return i == 1 || i == 2;
        }
    }

    public static class CloseMethodRecord {
        public int numCloseableArgs;
        public char[] selector;
        public char[][] typeName;

        public CloseMethodRecord(char[][] cArr, char[] cArr2, int i) {
            this.typeName = cArr;
            this.selector = cArr2;
            this.numCloseableArgs = i;
        }
    }

    public enum DangerousMethod {
        Contains,
        Remove,
        RemoveAll,
        ContainsAll,
        RetainAll,
        Get,
        ContainsKey,
        ContainsValue,
        IndexOf,
        LastIndexOf,
        Equals;

        public static DangerousMethod detectSelector(char[] cArr) {
            char c = cArr[0];
            if (c == 'c') {
                char[] cArr2 = TypeConstants.CONTAINS;
                if (!CharOperation.prefixEquals(cArr2, cArr)) {
                    return null;
                }
                if (CharOperation.equals(cArr, cArr2)) {
                    return Contains;
                }
                if (CharOperation.equals(cArr, TypeConstants.CONTAINS_ALL)) {
                    return ContainsAll;
                }
                if (CharOperation.equals(cArr, TypeConstants.CONTAINS_KEY)) {
                    return ContainsKey;
                }
                if (CharOperation.equals(cArr, TypeConstants.CONTAINS_VALUE)) {
                    return ContainsValue;
                }
                return null;
            }
            if (c == 'e') {
                if (CharOperation.equals(cArr, TypeConstants.EQUALS)) {
                    return Equals;
                }
                return null;
            }
            if (c == 'g') {
                if (CharOperation.equals(cArr, TypeConstants.GET)) {
                    return Get;
                }
                return null;
            }
            if (c == 'i') {
                if (CharOperation.equals(cArr, TypeConstants.INDEX_OF)) {
                    return IndexOf;
                }
                return null;
            }
            if (c == 'l') {
                if (CharOperation.equals(cArr, TypeConstants.LAST_INDEX_OF)) {
                    return LastIndexOf;
                }
                return null;
            }
            if (c != 'r') {
                return null;
            }
            char[] cArr3 = TypeConstants.REMOVE;
            if (!CharOperation.prefixEquals(cArr3, cArr)) {
                if (CharOperation.equals(cArr, TypeConstants.RETAIN_ALL)) {
                    return RetainAll;
                }
                return null;
            }
            if (CharOperation.equals(cArr, cArr3)) {
                return Remove;
            }
            if (CharOperation.equals(cArr, TypeConstants.REMOVE_ALL)) {
                return RemoveAll;
            }
            return null;
        }

        /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
        public static DangerousMethod[] valuesCustom() {
            DangerousMethod[] dangerousMethodArrValuesCustom = values();
            int length = dangerousMethodArrValuesCustom.length;
            DangerousMethod[] dangerousMethodArr = new DangerousMethod[length];
            System.arraycopy(dangerousMethodArrValuesCustom, 0, dangerousMethodArr, 0, length);
            return dangerousMethodArr;
        }
    }

    static {
        char[] cArr = {'j', 'a', 'v', 'a'};
        JAVA = cArr;
        char[] cArr2 = {'j', 'a', 'v', 'a', 'c'};
        JAVAC = cArr2;
        char[] cArr3 = {'j', 'a', 'v', 'a', 'x'};
        JAVAX = cArr3;
        char[] cArr4 = {'j', 'a', 'k', 'a', 'r', 't', 'a'};
        JAKARTA = cArr4;
        char[] cArr5 = {'l', 'a', 'n', 'g'};
        LANG = cArr5;
        char[] cArr6 = {'i', 'o'};
        IO = cArr6;
        char[] cArr7 = {'n', 'i', 'o'};
        NIO = cArr7;
        char[] cArr8 = {'u', 't', 'i', 'l'};
        UTIL = cArr8;
        char[] cArr9 = {'j', 'd', 'k'};
        JDK = cArr9;
        char[] cArr10 = {'a', 'n', 'n', 'o', 't', 'a', 't', 'i', 'o', 'n'};
        ANNOTATION = cArr10;
        char[] cArr11 = {'r', 'e', 'f', 'l', 'e', 'c', 't'};
        REFLECT = cArr11;
        char[] cArr12 = {'O', 'b', 'j', 'e', 'c', 't'};
        OBJECT = cArr12;
        char[] cArr13 = {'R', 'e', 'c', 'o', 'r', 'd'};
        RECORD_CLASS = cArr13;
        char[] cArr14 = {'o', 'r', 'g'};
        ORG = cArr14;
        char[] cArr15 = {'e', 'c', 'l', 'i', 'p', 's', 'e'};
        ECLIPSE = cArr15;
        char[] cArr16 = {'c', 'o', 'r', 'e'};
        CORE = cArr16;
        char[] cArr17 = {'r', 'u', 'n', 't', 'i', 'm', 'e'};
        RUNTIME = cArr17;
        char[] cArr18 = {'a', 'p', 'a', 'c', 'h', 'e'};
        APACHE = cArr18;
        char[] cArr19 = {'c', 'o', 'm', 'm', 'o', 'n', 's'};
        COMMONS = cArr19;
        char[] cArr20 = {'l', 'a', 'n', 'g', '3'};
        LANG3 = cArr20;
        char[] cArr21 = {'c', 'o', 'm'};
        COM = cArr21;
        char[] cArr22 = {'g', 'o', 'o', 'g', 'l', 'e'};
        GOOGLE = cArr22;
        char[] cArr23 = {'j', 'd', 't'};
        JDT = cArr23;
        char[] cArr24 = {'i', 'n', 't', 'e', 'r', 'n', 'a', 'l'};
        INTERNAL = cArr24;
        char[] cArr25 = {'c', 'o', 'm', 'p', 'i', 'l', 'e', 'r'};
        COMPILER = cArr25;
        char[] cArr26 = {'l', 'o', 'o', 'k', 'u', 'p'};
        LOOKUP = cArr26;
        char[] cArr27 = {Util.C_TYPE_VARIABLE, 'y', 'p', 'e', Util.C_BYTE, 'i', 'n', 'd', 'i', 'n', 'g'};
        TYPEBINDING = cArr27;
        char[] cArr28 = {'d', 'o', 'm'};
        DOM = cArr28;
        char[] cArr29 = {Util.C_INT, Util.C_TYPE_VARIABLE, 'y', 'p', 'e', Util.C_BYTE, 'i', 'n', 'd', 'i', 'n', 'g'};
        ITYPEBINDING = cArr29;
        char[] cArr30 = {'s', 'p', 'r', 'i', 'n', 'g', 'f', 'r', 'a', 'm', 'e', 'w', 'o', 'r', 'k'};
        SPRING = cArr30;
        JAVA_LANG = new char[][]{cArr, cArr5};
        JAVA_IO = new char[][]{cArr, cArr6};
        JAVA_LANG_ANNOTATION = new char[][]{cArr, cArr5, cArr10};
        JAVA_LANG_ANNOTATION_ANNOTATION = new char[][]{cArr, cArr5, cArr10, new char[]{'A', 'n', 'n', 'o', 't', 'a', 't', 'i', 'o', 'n'}};
        JAVA_LANG_ASSERTIONERROR = new char[][]{cArr, cArr5, new char[]{'A', 's', 's', 'e', 'r', 't', 'i', 'o', 'n', 'E', 'r', 'r', 'o', 'r'}};
        JAVA_LANG_CLASS = new char[][]{cArr, cArr5, new char[]{Util.C_CHAR, 'l', 'a', 's', 's'}};
        JAVA_LANG_CLASSNOTFOUNDEXCEPTION = new char[][]{cArr, cArr5, new char[]{Util.C_CHAR, 'l', 'a', 's', 's', 'N', 'o', 't', Util.C_FLOAT, 'o', 'u', 'n', 'd', 'E', 'x', 'c', 'e', 'p', 't', 'i', 'o', 'n'}};
        JAVA_LANG_NOSUCHFIELDERROR = new char[][]{cArr, cArr5, new char[]{'N', 'o', Util.C_SHORT, 'u', 'c', 'h', Util.C_FLOAT, 'i', 'e', 'l', 'd', 'E', 'r', 'r', 'o', 'r'}};
        JAVA_LANG_CLONEABLE = new char[][]{cArr, cArr5, new char[]{Util.C_CHAR, 'l', 'o', 'n', 'e', 'a', 'b', 'l', 'e'}};
        JAVA_LANG_ENUM = new char[][]{cArr, cArr5, new char[]{'E', 'n', 'u', 'm'}};
        JAVA_LANG_EXCEPTION = new char[][]{cArr, cArr5, new char[]{'E', 'x', 'c', 'e', 'p', 't', 'i', 'o', 'n'}};
        JAVA_LANG_ERROR = new char[][]{cArr, cArr5, new char[]{'E', 'r', 'r', 'o', 'r'}};
        JAVA_LANG_ILLEGALARGUMENTEXCEPTION = new char[][]{cArr, cArr5, new char[]{Util.C_INT, 'l', 'l', 'e', 'g', 'a', 'l', 'A', 'r', 'g', 'u', 'm', 'e', 'n', 't', 'E', 'x', 'c', 'e', 'p', 't', 'i', 'o', 'n'}};
        JAVA_LANG_INCOMPATIBLECLASSCHANGEERROR = new char[][]{cArr, cArr5, new char[]{Util.C_INT, 'n', 'c', 'o', 'm', 'p', 'a', 't', 'i', 'b', 'l', 'e', Util.C_CHAR, 'l', 'a', 's', 's', Util.C_CHAR, 'h', 'a', 'n', 'g', 'e', 'E', 'r', 'r', 'o', 'r'}};
        JAVA_LANG_ITERABLE = new char[][]{cArr, cArr5, new char[]{Util.C_INT, 't', 'e', 'r', 'a', 'b', 'l', 'e'}};
        JAVA_LANG_NOCLASSDEFERROR = new char[][]{cArr, cArr5, new char[]{'N', 'o', Util.C_CHAR, 'l', 'a', 's', 's', Util.C_DOUBLE, 'e', 'f', 'E', 'r', 'r', 'o', 'r'}};
        JAVA_LANG_OBJECT = new char[][]{cArr, cArr5, cArr12};
        JAVA_LANG_RECORD = new char[][]{cArr, cArr5, cArr13};
        JAVA_LANG_STRING = new char[][]{cArr, cArr5, new char[]{Util.C_SHORT, 't', 'r', 'i', 'n', 'g'}};
        JAVA_LANG_STRINGBUFFER = new char[][]{cArr, cArr5, new char[]{Util.C_SHORT, 't', 'r', 'i', 'n', 'g', Util.C_BYTE, 'u', 'f', 'f', 'e', 'r'}};
        JAVA_LANG_STRINGBUILDER = new char[][]{cArr, cArr5, new char[]{Util.C_SHORT, 't', 'r', 'i', 'n', 'g', Util.C_BYTE, 'u', 'i', 'l', 'd', 'e', 'r'}};
        JAVA_LANG_STRINGTEMPLATE = new char[][]{cArr, cArr5, new char[]{Util.C_SHORT, 't', 'r', 'i', 'n', 'g', Util.C_TYPE_VARIABLE, 'e', 'm', 'p', 'l', 'a', 't', 'e'}};
        JAVA_LANG_STRINGTEMPLATE_PROCESSOR = new char[][]{cArr, cArr5, new char[]{Util.C_SHORT, 't', 'r', 'i', 'n', 'g', Util.C_TYPE_VARIABLE, 'e', 'm', 'p', 'l', 'a', 't', 'e', '$', 'P', 'r', 'o', 'c', 'e', 's', 's', 'o', 'r'}};
        JAVA_LANG_SYSTEM = new char[][]{cArr, cArr5, new char[]{Util.C_SHORT, 'y', 's', 't', 'e', 'm'}};
        JAVA_LANG_RUNTIMEEXCEPTION = new char[][]{cArr, cArr5, new char[]{'R', 'u', 'n', 't', 'i', 'm', 'e', 'E', 'x', 'c', 'e', 'p', 't', 'i', 'o', 'n'}};
        JAVA_LANG_THROWABLE = new char[][]{cArr, cArr5, new char[]{Util.C_TYPE_VARIABLE, 'h', 'r', 'o', 'w', 'a', 'b', 'l', 'e'}};
        JAVA_LANG_REFLECT_CONSTRUCTOR = new char[][]{cArr, cArr5, cArr11, new char[]{Util.C_CHAR, 'o', 'n', 's', 't', 'r', 'u', 'c', 't', 'o', 'r'}};
        JAVA_IO_PRINTSTREAM = new char[][]{cArr, cArr6, new char[]{'P', 'r', 'i', 'n', 't', Util.C_SHORT, 't', 'r', 'e', 'a', 'm'}};
        JAVA_IO_SERIALIZABLE = new char[][]{cArr, cArr6, new char[]{Util.C_SHORT, 'e', 'r', 'i', 'a', 'l', 'i', 'z', 'a', 'b', 'l', 'e'}};
        JAVA_LANG_BYTE = new char[][]{cArr, cArr5, new char[]{Util.C_BYTE, 'y', 't', 'e'}};
        JAVA_LANG_SHORT = new char[][]{cArr, cArr5, new char[]{Util.C_SHORT, 'h', 'o', 'r', 't'}};
        JAVA_LANG_CHARACTER = new char[][]{cArr, cArr5, new char[]{Util.C_CHAR, 'h', 'a', 'r', 'a', 'c', 't', 'e', 'r'}};
        JAVA_LANG_INTEGER = new char[][]{cArr, cArr5, new char[]{Util.C_INT, 'n', 't', 'e', 'g', 'e', 'r'}};
        JAVA_LANG_LONG = new char[][]{cArr, cArr5, new char[]{Util.C_RESOLVED, 'o', 'n', 'g'}};
        JAVA_LANG_FLOAT = new char[][]{cArr, cArr5, new char[]{Util.C_FLOAT, 'l', 'o', 'a', 't'}};
        JAVA_LANG_DOUBLE = new char[][]{cArr, cArr5, new char[]{Util.C_DOUBLE, 'o', 'u', 'b', 'l', 'e'}};
        JAVA_LANG_BOOLEAN = new char[][]{cArr, cArr5, new char[]{Util.C_BYTE, 'o', 'o', 'l', 'e', 'a', 'n'}};
        JAVA_LANG_VOID = new char[][]{cArr, cArr5, new char[]{Util.C_VOID, 'o', 'i', 'd'}};
        JAVA_UTIL_COLLECTION = new char[][]{cArr, cArr8, new char[]{Util.C_CHAR, 'o', 'l', 'l', 'e', 'c', 't', 'i', 'o', 'n'}};
        JAVA_UTIL_ITERATOR = new char[][]{cArr, cArr8, new char[]{Util.C_INT, 't', 'e', 'r', 'a', 't', 'o', 'r'}};
        NEXT = new char[]{'n', 'e', 'x', 't'};
        JAVA_UTIL_OBJECTS = new char[][]{cArr, cArr8, new char[]{'O', 'b', 'j', 'e', 'c', 't', 's'}};
        JAVA_UTIL_LIST = new char[][]{cArr, cArr8, new char[]{Util.C_RESOLVED, 'i', 's', 't'}};
        JAVA_UTIL_ARRAYS = new char[][]{cArr, cArr8, new char[]{'A', 'r', 'r', 'a', 'y', 's'}};
        JAVA_LANG_DEPRECATED = new char[][]{cArr, cArr5, new char[]{Util.C_DOUBLE, 'e', 'p', 'r', 'e', 'c', 'a', 't', 'e', 'd'}};
        FOR_REMOVAL = new char[]{'f', 'o', 'r', 'R', 'e', 'm', 'o', 'v', 'a', 'l'};
        SINCE = new char[]{'s', 'i', 'n', 'c', 'e'};
        ESSENTIAL_API = new char[]{'e', 's', 's', 'e', 'n', 't', 'i', 'a', 'l', 'A', 'P', Util.C_INT};
        JAVA_LANG_ANNOTATION_DOCUMENTED = new char[][]{cArr, cArr5, cArr10, new char[]{Util.C_DOUBLE, 'o', 'c', 'u', 'm', 'e', 'n', 't', 'e', 'd'}};
        JAVA_LANG_ANNOTATION_INHERITED = new char[][]{cArr, cArr5, cArr10, new char[]{Util.C_INT, 'n', 'h', 'e', 'r', 'i', 't', 'e', 'd'}};
        JAVA_LANG_ANNOTATION_REPEATABLE = new char[][]{cArr, cArr5, cArr10, new char[]{'R', 'e', 'p', 'e', 'a', 't', 'a', 'b', 'l', 'e'}};
        JAVA_LANG_OVERRIDE = new char[][]{cArr, cArr5, new char[]{'O', 'v', 'e', 'r', 'r', 'i', 'd', 'e'}};
        JAVA_LANG_FUNCTIONAL_INTERFACE = new char[][]{cArr, cArr5, new char[]{Util.C_FLOAT, 'u', 'n', 'c', 't', 'i', 'o', 'n', 'a', 'l', Util.C_INT, 'n', 't', 'e', 'r', 'f', 'a', 'c', 'e'}};
        JAVA_LANG_ANNOTATION_RETENTION = new char[][]{cArr, cArr5, cArr10, new char[]{'R', 'e', 't', 'e', 'n', 't', 'i', 'o', 'n'}};
        JAVA_LANG_SUPPRESSWARNINGS = new char[][]{cArr, cArr5, new char[]{Util.C_SHORT, 'u', 'p', 'p', 'r', 'e', 's', 's', 'W', 'a', 'r', 'n', 'i', 'n', 'g', 's'}};
        JAVA_LANG_ANNOTATION_TARGET = new char[][]{cArr, cArr5, cArr10, new char[]{Util.C_TYPE_VARIABLE, 'a', 'r', 'g', 'e', 't'}};
        JAVA_LANG_ANNOTATION_RETENTIONPOLICY = new char[][]{cArr, cArr5, cArr10, new char[]{'R', 'e', 't', 'e', 'n', 't', 'i', 'o', 'n', 'P', 'o', 'l', 'i', 'c', 'y'}};
        JAVA_LANG_ANNOTATION_ELEMENTTYPE = new char[][]{cArr, cArr5, cArr10, new char[]{'E', 'l', 'e', 'm', 'e', 'n', 't', Util.C_TYPE_VARIABLE, 'y', 'p', 'e'}};
        JDK_INTERNAL_PREVIEW_FEATURE = new char[][]{cArr9, cArr24, new char[]{'P', 'r', 'e', 'v', 'i', 'e', 'w', Util.C_FLOAT, 'e', 'a', 't', 'u', 'r', 'e'}};
        JDK_INTERNAL_JAVAC_PREVIEW_FEATURE = new char[][]{cArr9, cArr24, cArr2, new char[]{'P', 'r', 'e', 'v', 'i', 'e', 'w', Util.C_FLOAT, 'e', 'a', 't', 'u', 'r', 'e'}};
        JAVA_LANG_REFLECT_FIELD = new char[][]{cArr, cArr5, cArr11, new char[]{Util.C_FLOAT, 'i', 'e', 'l', 'd'}};
        JAVA_LANG_REFLECT_METHOD = new char[][]{cArr, cArr5, cArr11, new char[]{'M', 'e', 't', 'h', 'o', 'd'}};
        JAVA_IO_CLOSEABLE = new char[][]{cArr, cArr6, new char[]{Util.C_CHAR, 'l', 'o', 's', 'e', 'a', 'b', 'l', 'e'}};
        JAVA_IO_OBJECTSTREAMEXCEPTION = new char[][]{cArr, cArr6, new char[]{'O', 'b', 'j', 'e', 'c', 't', Util.C_SHORT, 't', 'r', 'e', 'a', 'm', 'E', 'x', 'c', 'e', 'p', 't', 'i', 'o', 'n'}};
        JAVA_IO_EXTERNALIZABLE = new char[][]{cArr, cArr6, new char[]{'E', 'x', 't', 'e', 'r', 'n', 'a', 'l', 'i', 'z', 'a', 'b', 'l', 'e'}};
        JAVA_IO_IOEXCEPTION = new char[][]{cArr, cArr6, new char[]{Util.C_INT, 'O', 'E', 'x', 'c', 'e', 'p', 't', 'i', 'o', 'n'}};
        JAVA_IO_OBJECTOUTPUTSTREAM = new char[][]{cArr, cArr6, new char[]{'O', 'b', 'j', 'e', 'c', 't', 'O', 'u', 't', 'p', 'u', 't', Util.C_SHORT, 't', 'r', 'e', 'a', 'm'}};
        JAVA_IO_OBJECTINPUTSTREAM = new char[][]{cArr, cArr6, new char[]{'O', 'b', 'j', 'e', 'c', 't', Util.C_INT, 'n', 'p', 'u', 't', Util.C_SHORT, 't', 'r', 'e', 'a', 'm'}};
        JAVA_NIO_FILE_FILES = new char[][]{cArr, new char[]{'n', 'i', 'o'}, new char[]{'f', 'i', 'l', 'e'}, new char[]{Util.C_FLOAT, 'i', 'l', 'e', 's'}};
        JAVAX_RMI_CORBA_STUB = new char[][]{cArr3, new char[]{'r', 'm', 'i'}, new char[]{Util.C_CHAR, 'O', 'R', Util.C_BYTE, 'A'}, new char[]{Util.C_SHORT, 't', 'u', 'b'}};
        JAVA_LANG_SAFEVARARGS = new char[][]{cArr, cArr5, new char[]{Util.C_SHORT, 'a', 'f', 'e', Util.C_VOID, 'a', 'r', 'a', 'r', 'g', 's'}};
        char[] cArr31 = {'i', 'n', 'v', 'o', 'k', 'e'};
        INVOKE = cArr31;
        JAVA_LANG_INVOKE_METHODHANDLE_POLYMORPHICSIGNATURE = new char[][]{cArr, cArr5, cArr31, new char[]{'M', 'e', 't', 'h', 'o', 'd', 'H', 'a', 'n', 'd', 'l', 'e'}, new char[]{'P', 'o', 'l', 'y', 'm', 'o', 'r', 'p', 'h', 'i', 'c', Util.C_SHORT, 'i', 'g', 'n', 'a', 't', 'u', 'r', 'e'}};
        JAVA_LANG_INVOKE_METHODHANDLE_$_POLYMORPHICSIGNATURE = new char[][]{cArr, cArr5, cArr31, new char[]{'M', 'e', 't', 'h', 'o', 'd', 'H', 'a', 'n', 'd', 'l', 'e', '$', 'P', 'o', 'l', 'y', 'm', 'o', 'r', 'p', 'h', 'i', 'c', Util.C_SHORT, 'i', 'g', 'n', 'a', 't', 'u', 'r', 'e'}};
        JAVA_LANG_INVOKE_LAMBDAMETAFACTORY = new char[][]{cArr, cArr5, cArr31, new char[]{Util.C_RESOLVED, 'a', 'm', 'b', 'd', 'a', 'M', 'e', 't', 'a', 'f', 'a', 'c', 't', 'o', 'r', 'y'}};
        JAVA_LANG_INVOKE_SERIALIZEDLAMBDA = new char[][]{cArr, cArr5, cArr31, new char[]{Util.C_SHORT, 'e', 'r', 'i', 'a', 'l', 'i', 'z', 'e', 'd', Util.C_RESOLVED, 'a', 'm', 'b', 'd', 'a'}};
        JAVA_LANG_INVOKE_METHODHANDLES = new char[][]{cArr, cArr5, cArr31, new char[]{'M', 'e', 't', 'h', 'o', 'd', 'H', 'a', 'n', 'd', 'l', 'e', 's'}};
        JAVA_LANG_INVOKE_METHODHANDLE = new char[][]{cArr, cArr5, cArr31, new char[]{'M', 'e', 't', 'h', 'o', 'd', 'H', 'a', 'n', 'd', 'l', 'e'}};
        JAVA_LANG_INVOKE_VARHANDLE = new char[][]{cArr, cArr5, cArr31, new char[]{Util.C_VOID, 'a', 'r', 'H', 'a', 'n', 'd', 'l', 'e'}};
        JAVA_LANG_INVOKE_STRING_CONCAT_FACTORY = new char[][]{cArr, cArr5, cArr31, new char[]{Util.C_SHORT, 't', 'r', 'i', 'n', 'g', Util.C_CHAR, 'o', 'n', 'c', 'a', 't', Util.C_FLOAT, 'a', 'c', 't', 'o', 'r', 'y'}};
        JAVA_LANG_AUTOCLOSEABLE = new char[][]{cArr, cArr5, new char[]{'A', 'u', 't', 'o', Util.C_CHAR, 'l', 'o', 's', 'e', 'a', 'b', 'l', 'e'}};
        char[] cArr32 = {'c', 'l', 'o', 's', 'e'};
        CLOSE = cArr32;
        JAVA_LANG_RUNTIME_OBJECTMETHODS = new char[][]{cArr, cArr5, cArr17, new char[]{'O', 'b', 'j', 'e', 'c', 't', 'M', 'e', 't', 'h', 'o', 'd', 's'}};
        JAVA_LANG_RUNTIME_SWITCHBOOTSTRAPS = new char[][]{cArr, cArr5, cArr17, new char[]{Util.C_SHORT, 'w', 'i', 't', 'c', 'h', Util.C_BYTE, 'o', 'o', 't', 's', 't', 'r', 'a', 'p', 's'}};
        JAVA_LANG_INVOKE_CONSTANTBOOTSTRAP = new char[][]{cArr, cArr5, cArr31, new char[]{Util.C_CHAR, 'o', 'n', 's', 't', 'a', 'n', 't', Util.C_BYTE, 'o', 'o', 't', 's', 't', 'r', 'a', 'p', 's'}};
        JAVA_LANG_ENUM_ENUMDESC = new char[][]{cArr, cArr5, new char[]{'E', 'n', 'u', 'm', '$', 'E', 'n', 'u', 'm', Util.C_DOUBLE, 'e', 's', 'c'}};
        JAVA_LANG_CONSTANT_CLASSDESC = new char[][]{cArr, cArr5, new char[]{'c', 'o', 'n', 's', 't', 'a', 'n', 't'}, new char[]{Util.C_CHAR, 'l', 'a', 's', 's', Util.C_DOUBLE, 'e', 's', 'c'}};
        JAVA_LANG_RUNTIME_TEMPLATERUNTIME = new char[][]{cArr, cArr5, cArr17, new char[]{Util.C_TYPE_VARIABLE, 'e', 'm', 'p', 'l', 'a', 't', 'e', 'R', 'u', 'n', 't', 'i', 'm', 'e'}};
        JAVA_LANG_STRING_TEMPLATE_STR = new char[][]{cArr, cArr5, new char[]{Util.C_SHORT, 't', 'r', 'i', 'n', 'g', Util.C_TYPE_VARIABLE, 'e', 'm', 'p', 'l', 'a', 't', 'e'}, new char[]{Util.C_SHORT, Util.C_TYPE_VARIABLE, 'R'}};
        char[][] cArr33 = {cArr21, cArr22, new char[]{'c', 'o', 'm', 'm', 'o', 'n'}, cArr6, new char[]{Util.C_CHAR, 'l', 'o', 's', 'e', 'a', 'b', 'l', 'e', 's'}};
        GUAVA_CLOSEABLES = cArr33;
        char[][] cArr34 = {cArr14, cArr18, cArr19, cArr6, new char[]{Util.C_INT, 'O', 'U', 't', 'i', 'l', 's'}};
        APACHE_IOUTILS = cArr34;
        char[][] cArr35 = {cArr14, cArr18, cArr19, new char[]{'d', 'b', 'u', 't', 'i', 'l', 's'}, new char[]{Util.C_DOUBLE, 'b', 'U', 't', 'i', 'l', 's'}};
        APACHE_DBUTILS = cArr35;
        char[] cArr36 = {'c', 'l', 'o', 's', 'e', Util.C_UNRESOLVED, 'u', 'i', 'e', 't', 'l', 'y'};
        CLOSE_QUIETLY = cArr36;
        closeMethods = new CloseMethodRecord[]{new CloseMethodRecord(cArr33, cArr36, 1), new CloseMethodRecord(cArr33, cArr32, 1), new CloseMethodRecord(cArr34, cArr36, 1), new CloseMethodRecord(cArr35, cArr32, 1), new CloseMethodRecord(cArr35, cArr36, 3), new CloseMethodRecord(cArr35, new char[]{'c', 'o', 'm', 'm', 'i', 't', 'A', 'n', 'd', Util.C_CHAR, 'l', 'o', 's', 'e'}, 1), new CloseMethodRecord(cArr35, new char[]{'c', 'o', 'm', 'm', 'i', 't', 'A', 'n', 'd', Util.C_CHAR, 'l', 'o', 's', 'e', Util.C_UNRESOLVED, 'u', 'i', 'e', 't', 'l', 'y'}, 1), new CloseMethodRecord(cArr35, new char[]{'r', 'o', 'l', 'l', 'b', 'a', 'c', 'k', 'A', 'n', 'd', Util.C_CHAR, 'l', 'o', 's', 'e'}, 1), new CloseMethodRecord(cArr35, new char[]{'r', 'o', 'l', 'l', 'b', 'a', 'c', 'k', 'A', 'n', 'd', Util.C_CHAR, 'l', 'o', 's', 'e', Util.C_UNRESOLVED, 'u', 'i', 'e', 't', 'l', 'y'}, 1)};
        JAVA_IO_WRAPPER_CLOSEABLES = new char[][]{new char[]{Util.C_BYTE, 'u', 'f', 'f', 'e', 'r', 'e', 'd', Util.C_INT, 'n', 'p', 'u', 't', Util.C_SHORT, 't', 'r', 'e', 'a', 'm'}, new char[]{Util.C_BYTE, 'u', 'f', 'f', 'e', 'r', 'e', 'd', 'O', 'u', 't', 'p', 'u', 't', Util.C_SHORT, 't', 'r', 'e', 'a', 'm'}, new char[]{Util.C_BYTE, 'u', 'f', 'f', 'e', 'r', 'e', 'd', 'R', 'e', 'a', 'd', 'e', 'r'}, new char[]{Util.C_BYTE, 'u', 'f', 'f', 'e', 'r', 'e', 'd', 'W', 'r', 'i', 't', 'e', 'r'}, new char[]{Util.C_INT, 'n', 'p', 'u', 't', Util.C_SHORT, 't', 'r', 'e', 'a', 'm', 'R', 'e', 'a', 'd', 'e', 'r'}, new char[]{'P', 'r', 'i', 'n', 't', 'W', 'r', 'i', 't', 'e', 'r'}, new char[]{Util.C_RESOLVED, 'i', 'n', 'e', 'N', 'u', 'm', 'b', 'e', 'r', 'R', 'e', 'a', 'd', 'e', 'r'}, new char[]{Util.C_DOUBLE, 'a', 't', 'a', Util.C_INT, 'n', 'p', 'u', 't', Util.C_SHORT, 't', 'r', 'e', 'a', 'm'}, new char[]{Util.C_DOUBLE, 'a', 't', 'a', 'O', 'u', 't', 'p', 'u', 't', Util.C_SHORT, 't', 'r', 'e', 'a', 'm'}, new char[]{'O', 'b', 'j', 'e', 'c', 't', Util.C_INT, 'n', 'p', 'u', 't', Util.C_SHORT, 't', 'r', 'e', 'a', 'm'}, new char[]{'O', 'b', 'j', 'e', 'c', 't', 'O', 'u', 't', 'p', 'u', 't', Util.C_SHORT, 't', 'r', 'e', 'a', 'm'}, new char[]{Util.C_FLOAT, 'i', 'l', 't', 'e', 'r', Util.C_INT, 'n', 'p', 'u', 't', Util.C_SHORT, 't', 'r', 'e', 'a', 'm'}, new char[]{Util.C_FLOAT, 'i', 'l', 't', 'e', 'r', 'O', 'u', 't', 'p', 'u', 't', Util.C_SHORT, 't', 'r', 'e', 'a', 'm'}, new char[]{'P', 'u', 's', 'h', 'b', 'a', 'c', 'k', Util.C_INT, 'n', 'p', 'u', 't', Util.C_SHORT, 't', 'r', 'e', 'a', 'm'}, new char[]{Util.C_SHORT, 'e', 'q', 'u', 'e', 'n', 'c', 'e', Util.C_INT, 'n', 'p', 'u', 't', Util.C_SHORT, 't', 'r', 'e', 'a', 'm'}, new char[]{'P', 'r', 'i', 'n', 't', Util.C_SHORT, 't', 'r', 'e', 'a', 'm'}, new char[]{'P', 'u', 's', 'h', 'b', 'a', 'c', 'k', 'R', 'e', 'a', 'd', 'e', 'r'}, new char[]{'O', 'u', 't', 'p', 'u', 't', Util.C_SHORT, 't', 'r', 'e', 'a', 'm', 'W', 'r', 'i', 't', 'e', 'r'}};
        JAVA_UTIL_ZIP_WRAPPER_CLOSEABLES = new char[][]{new char[]{'G', 'Z', Util.C_INT, 'P', Util.C_INT, 'n', 'p', 'u', 't', Util.C_SHORT, 't', 'r', 'e', 'a', 'm'}, new char[]{Util.C_INT, 'n', 'f', 'l', 'a', 't', 'e', 'r', Util.C_INT, 'n', 'p', 'u', 't', Util.C_SHORT, 't', 'r', 'e', 'a', 'm'}, new char[]{Util.C_DOUBLE, 'e', 'f', 'l', 'a', 't', 'e', 'r', Util.C_INT, 'n', 'p', 'u', 't', Util.C_SHORT, 't', 'r', 'e', 'a', 'm'}, new char[]{Util.C_CHAR, 'h', 'e', 'c', 'k', 'e', 'd', Util.C_INT, 'n', 'p', 'u', 't', Util.C_SHORT, 't', 'r', 'e', 'a', 'm'}, new char[]{'Z', 'i', 'p', Util.C_INT, 'n', 'p', 'u', 't', Util.C_SHORT, 't', 'r', 'e', 'a', 'm'}, new char[]{Util.C_LONG, 'a', 'r', Util.C_INT, 'n', 'p', 'u', 't', Util.C_SHORT, 't', 'r', 'e', 'a', 'm'}, new char[]{'G', 'Z', Util.C_INT, 'P', 'O', 'u', 't', 'p', 'u', 't', Util.C_SHORT, 't', 'r', 'e', 'a', 'm'}, new char[]{Util.C_INT, 'n', 'f', 'l', 'a', 't', 'e', 'r', 'O', 'u', 't', 'p', 'u', 't', Util.C_SHORT, 't', 'r', 'e', 'a', 'm'}, new char[]{Util.C_DOUBLE, 'e', 'f', 'l', 'a', 't', 'e', 'r', 'O', 'u', 't', 'p', 'u', 't', Util.C_SHORT, 't', 'r', 'e', 'a', 'm'}, new char[]{Util.C_CHAR, 'h', 'e', 'c', 'k', 'e', 'd', 'O', 'u', 't', 'p', 'u', 't', Util.C_SHORT, 't', 'r', 'e', 'a', 'm'}, new char[]{'Z', 'i', 'p', 'O', 'u', 't', 'p', 'u', 't', Util.C_SHORT, 't', 'r', 'e', 'a', 'm'}, new char[]{Util.C_LONG, 'a', 'r', 'O', 'u', 't', 'p', 'u', 't', Util.C_SHORT, 't', 'r', 'e', 'a', 'm'}};
        OTHER_WRAPPER_CLOSEABLES = new char[][][]{new char[][]{cArr, new char[]{'s', 'e', 'c', 'u', 'r', 'i', 't', 'y'}, new char[]{Util.C_DOUBLE, 'i', 'g', 'e', 's', 't', Util.C_INT, 'n', 'p', 'u', 't', Util.C_SHORT, 't', 'r', 'e', 'a', 'm'}}, new char[][]{cArr, new char[]{'s', 'e', 'c', 'u', 'r', 'i', 't', 'y'}, new char[]{Util.C_DOUBLE, 'i', 'g', 'e', 's', 't', 'O', 'u', 't', 'p', 'u', 't', Util.C_SHORT, 't', 'r', 'e', 'a', 'm'}}, new char[][]{cArr, new char[]{'b', 'e', 'a', 'n', 's'}, new char[]{'X', 'M', Util.C_RESOLVED, 'E', 'n', 'c', 'o', 'd', 'e', 'r'}}, new char[][]{cArr, new char[]{'b', 'e', 'a', 'n', 's'}, new char[]{'X', 'M', Util.C_RESOLVED, Util.C_DOUBLE, 'e', 'c', 'o', 'd', 'e', 'r'}}, new char[][]{cArr3, new char[]{'s', 'o', 'u', 'n', 'd'}, new char[]{'s', 'a', 'm', 'p', 'l', 'e', 'd'}, new char[]{'A', 'u', 'd', 'i', 'o', Util.C_INT, 'n', 'p', 'u', 't', Util.C_SHORT, 't', 'r', 'e', 'a', 'm'}}};
        JAVA_IO_RESOURCE_FREE_CLOSEABLES = new char[][]{new char[]{Util.C_SHORT, 't', 'r', 'i', 'n', 'g', 'R', 'e', 'a', 'd', 'e', 'r'}, new char[]{Util.C_SHORT, 't', 'r', 'i', 'n', 'g', 'W', 'r', 'i', 't', 'e', 'r'}, new char[]{Util.C_BYTE, 'y', 't', 'e', 'A', 'r', 'r', 'a', 'y', Util.C_INT, 'n', 'p', 'u', 't', Util.C_SHORT, 't', 'r', 'e', 'a', 'm'}, new char[]{Util.C_BYTE, 'y', 't', 'e', 'A', 'r', 'r', 'a', 'y', 'O', 'u', 't', 'p', 'u', 't', Util.C_SHORT, 't', 'r', 'e', 'a', 'm'}, new char[]{Util.C_CHAR, 'h', 'a', 'r', 'A', 'r', 'r', 'a', 'y', 'R', 'e', 'a', 'd', 'e', 'r'}, new char[]{Util.C_CHAR, 'h', 'a', 'r', 'A', 'r', 'r', 'a', 'y', 'W', 'r', 'i', 't', 'e', 'r'}, new char[]{Util.C_SHORT, 't', 'r', 'i', 'n', 'g', Util.C_BYTE, 'u', 'f', 'f', 'e', 'r', Util.C_INT, 'n', 'p', 'u', 't', Util.C_SHORT, 't', 'r', 'e', 'a', 'm'}};
        JAVA_UTIL_STREAM = new char[][]{cArr, cArr8, new char[]{'s', 't', 'r', 'e', 'a', 'm'}};
        RESOURCE_FREE_CLOSEABLE_J_U_STREAMS = new char[][]{new char[]{Util.C_SHORT, 't', 'r', 'e', 'a', 'm'}, new char[]{Util.C_DOUBLE, 'o', 'u', 'b', 'l', 'e', Util.C_SHORT, 't', 'r', 'e', 'a', 'm'}, new char[]{Util.C_RESOLVED, 'o', 'n', 'g', Util.C_SHORT, 't', 'r', 'e', 'a', 'm'}, new char[]{Util.C_INT, 'n', 't', Util.C_SHORT, 't', 'r', 'e', 'a', 'm'}};
        ONE_UTIL_STREAMEX = new char[][]{new char[]{'o', 'n', 'e'}, cArr8, new char[]{'s', 't', 'r', 'e', 'a', 'm', 'e', 'x'}};
        RESOURCE_FREE_CLOSEABLE_STREAMEX = new char[][]{new char[]{Util.C_SHORT, 't', 'r', 'e', 'a', 'm', 'E', 'x'}, new char[]{Util.C_INT, 'n', 't', Util.C_SHORT, 't', 'r', 'e', 'a', 'm', 'E', 'x'}, new char[]{Util.C_DOUBLE, 'o', 'u', 'b', 'l', 'e', Util.C_SHORT, 't', 'r', 'e', 'a', 'm', 'E', 'x'}, new char[]{Util.C_RESOLVED, 'o', 'n', 'g', Util.C_SHORT, 't', 'r', 'e', 'a', 'm', 'E', 'x'}, new char[]{'E', 'n', 't', 'r', 'y', Util.C_SHORT, 't', 'r', 'e', 'a', 'm'}};
        char[] cArr37 = {'c', 'h', 'a', 'n', 'n', 'e', 'l', 's'};
        CHANNELS = cArr37;
        FLUENT_RESOURCE_CLASSES = new char[][][]{new char[][]{cArr, cArr6, new char[]{Util.C_CHAR, 'h', 'a', 'r', 'A', 'r', 'r', 'a', 'y', 'W', 'r', 'i', 't', 'e', 'r'}}, new char[][]{cArr, cArr6, new char[]{Util.C_CHAR, 'o', 'n', 's', 'o', 'l', 'e'}}, new char[][]{cArr, cArr6, new char[]{'P', 'r', 'i', 'n', 't', Util.C_SHORT, 't', 'r', 'e', 'a', 'm'}}, new char[][]{cArr, cArr6, new char[]{'P', 'r', 'i', 'n', 't', 'W', 'r', 'i', 't', 'e', 'r'}}, new char[][]{cArr, cArr6, new char[]{Util.C_SHORT, 't', 'r', 'i', 'n', 'g', 'W', 'r', 'i', 't', 'e', 'r'}}, new char[][]{cArr, cArr6, new char[]{'W', 'r', 'i', 't', 'e', 'r'}}, new char[][]{cArr, cArr7, cArr37, new char[]{'A', 's', 'y', 'n', 'c', 'h', 'r', 'o', 'n', 'o', 'u', 's', Util.C_FLOAT, 'i', 'l', 'e', Util.C_CHAR, 'h', 'a', 'n', 'n', 'e', 'l'}}, new char[][]{cArr, cArr7, cArr37, new char[]{'A', 's', 'y', 'n', 'c', 'h', 'r', 'o', 'n', 'o', 'u', 's', Util.C_SHORT, 'e', 'r', 'v', 'e', 'r', Util.C_SHORT, 'o', 'c', 'k', 'e', 't', Util.C_CHAR, 'h', 'a', 'n', 'n', 'e', 'l'}}, new char[][]{cArr, cArr7, cArr37, new char[]{Util.C_FLOAT, 'i', 'l', 'e', Util.C_CHAR, 'h', 'a', 'n', 'n', 'e', 'l'}}, new char[][]{cArr, cArr7, cArr37, new char[]{'N', 'e', 't', 'w', 'o', 'r', 'k', Util.C_CHAR, 'h', 'a', 'n', 'n', 'e', 'l'}}, new char[][]{cArr, cArr7, cArr37, new char[]{Util.C_SHORT, 'e', 'e', 'k', 'a', 'b', 'l', 'e', Util.C_BYTE, 'y', 't', 'e', Util.C_CHAR, 'h', 'a', 'n', 'n', 'e', 'l'}}, new char[][]{cArr, cArr7, cArr37, new char[]{Util.C_SHORT, 'e', 'l', 'e', 'c', 't', 'a', 'b', 'l', 'e', Util.C_CHAR, 'h', 'a', 'n', 'n', 'e', 'l'}}, new char[][]{cArr, cArr7, cArr37, new char[]{Util.C_SHORT, 'e', 'l', 'e', 'c', 't', 'o', 'r'}}, new char[][]{cArr, cArr7, cArr37, new char[]{Util.C_SHORT, 'e', 'r', 'v', 'e', 'r', Util.C_SHORT, 'o', 'c', 'k', 'e', 't', Util.C_CHAR, 'h', 'a', 'n', 'n', 'e', 'l'}}, new char[][]{cArr, cArr8, new char[]{Util.C_FLOAT, 'o', 'r', 'm', 'a', 't', 't', 'e', 'r'}}, new char[][]{cArr, cArr8, new char[]{Util.C_SHORT, 'c', 'a', 'n', 'n', 'e', 'r'}}};
        JAVA_UTIL_STREAM__STREAM = new char[][]{cArr, cArr8, new char[]{'s', 't', 'r', 'e', 'a', 'm'}, new char[]{Util.C_SHORT, 't', 'r', 'e', 'a', 'm'}};
        FILTER = new char[]{'f', 'i', 'l', 't', 'e', 'r'};
        char[] cArr38 = {'A', 's', 's', 'e', 'r', 't'};
        ASSERT_CLASS = cArr38;
        char[] cArr39 = {'A', 's', 's', 'e', 'r', 't', 'i', 'o', 'n', 's'};
        ASSERTIONS_CLASS = cArr39;
        ORG_ECLIPSE_CORE_RUNTIME_ASSERT = new char[][]{cArr14, cArr15, cArr16, cArr17, cArr38};
        IS_NOTNULL = new char[]{'i', 's', 'N', 'o', 't', 'N', 'u', 'l', 'l'};
        char[] cArr40 = {'j', 'u', 'n', 'i', 't'};
        JUNIT = cArr40;
        char[] cArr41 = {'f', 'r', 'a', 'm', 'e', 'w', 'o', 'r', 'k'};
        FRAMEWORK = cArr41;
        char[] cArr42 = {'j', 'u', 'p', 'i', 't', 'e', 'r'};
        JUPITER = cArr42;
        char[] cArr43 = {'p', 'a', 'r', 'a', 'm', 's'};
        PARAMS = cArr43;
        char[] cArr44 = {'p', 'r', 'o', 'v', 'i', 'd', 'e', 'r'};
        PROVIDER = cArr44;
        char[] cArr45 = {'a', 'p', 'i'};
        API = cArr45;
        JUNIT_FRAMEWORK_ASSERT = new char[][]{cArr40, cArr41, cArr38};
        ORG_JUNIT_ASSERT = new char[][]{cArr14, cArr40, cArr38};
        ORG_JUNIT_JUPITER_API_ASSERTIONS = new char[][]{cArr14, cArr40, cArr42, cArr45, cArr39};
        ASSERT_NULL = new char[]{'a', 's', 's', 'e', 'r', 't', 'N', 'u', 'l', 'l'};
        ASSERT_NOTNULL = new char[]{'a', 's', 's', 'e', 'r', 't', 'N', 'o', 't', 'N', 'u', 'l', 'l'};
        ASSERT_TRUE = new char[]{'a', 's', 's', 'e', 'r', 't', Util.C_TYPE_VARIABLE, 'r', 'u', 'e'};
        ASSERT_FALSE = new char[]{'a', 's', 's', 'e', 'r', 't', Util.C_FLOAT, 'a', 'l', 's', 'e'};
        char[] cArr46 = {'M', 'e', 't', 'h', 'o', 'd', Util.C_SHORT, 'o', 'u', 'r', 'c', 'e'};
        METHOD_SOURCE = cArr46;
        ORG_JUNIT_METHOD_SOURCE = new char[][]{cArr14, cArr40, cArr42, cArr43, cArr44, cArr46};
        char[] cArr47 = {Util.C_VOID, 'a', 'l', 'i', 'd', 'a', 't', 'e'};
        VALIDATE_CLASS = cArr47;
        ORG_APACHE_COMMONS_LANG_VALIDATE = new char[][]{cArr14, cArr18, cArr19, cArr5, cArr47};
        ORG_APACHE_COMMONS_LANG3_VALIDATE = new char[][]{cArr14, cArr18, cArr19, cArr20, cArr47};
        ORG_ECLIPSE_JDT_INTERNAL_COMPILER_LOOKUP_TYPEBINDING = new char[][]{cArr14, cArr15, cArr23, cArr24, cArr25, cArr26, cArr27};
        ORG_ECLIPSE_JDT_CORE_DOM_ITYPEBINDING = new char[][]{cArr14, cArr15, cArr23, cArr16, cArr28, cArr29};
        IS_TRUE = new char[]{'i', 's', Util.C_TYPE_VARIABLE, 'r', 'u', 'e'};
        NOT_NULL = new char[]{'n', 'o', 't', 'N', 'u', 'l', 'l'};
        COM_GOOGLE_COMMON_BASE_PRECONDITIONS = new char[][]{cArr21, cArr22, new char[]{'c', 'o', 'm', 'm', 'o', 'n'}, new char[]{'b', 'a', 's', 'e'}, new char[]{'P', 'r', 'e', 'c', 'o', 'n', 'd', 'i', 't', 'i', 'o', 'n', 's'}};
        CHECK_NOT_NULL = new char[]{'c', 'h', 'e', 'c', 'k', 'N', 'o', 't', 'N', 'u', 'l', 'l'};
        CHECK_ARGUMENT = new char[]{'c', 'h', 'e', 'c', 'k', 'A', 'r', 'g', 'u', 'm', 'e', 'n', 't'};
        CHECK_STATE = new char[]{'c', 'h', 'e', 'c', 'k', Util.C_SHORT, 't', 'a', 't', 'e'};
        REQUIRE_NON_NULL = new char[]{'r', 'e', 'q', 'u', 'i', 'r', 'e', 'N', 'o', 'n', 'N', 'u', 'l', 'l'};
        char[] cArr48 = {'i', 'n', 'j', 'e', 'c', 't'};
        INJECT_PACKAGE = cArr48;
        char[] cArr49 = {Util.C_INT, 'n', 'j', 'e', 'c', 't'};
        INJECT_TYPE = cArr49;
        JAVAX_ANNOTATION_INJECT_INJECT = new char[][]{cArr3, cArr48, cArr49};
        JAKARTA_ANNOTATION_INJECT_INJECT = new char[][]{cArr4, cArr48, cArr49};
        COM_GOOGLE_INJECT_INJECT = new char[][]{cArr21, cArr22, cArr48, cArr49};
        OPTIONAL = new char[]{'o', 'p', 't', 'i', 'o', 'n', 'a', 'l'};
        IS_INSTANCE = new char[]{'i', 's', Util.C_INT, 'n', 's', 't', 'a', 'n', 'c', 'e'};
        NON_NULL = new char[]{'n', 'o', 'n', 'N', 'u', 'l', 'l'};
        IS_NULL = new char[]{'i', 's', 'N', 'u', 'l', 'l'};
        JAVA_UTIL_MAP = new char[][]{cArr, cArr8, new char[]{'M', 'a', 'p'}};
        GET = new char[]{'g', 'e', 't'};
        REMOVE = new char[]{'r', 'e', 'm', 'o', 'v', 'e'};
        REMOVE_ALL = new char[]{'r', 'e', 'm', 'o', 'v', 'e', 'A', 'l', 'l'};
        CONTAINS_ALL = new char[]{'c', 'o', 'n', 't', 'a', 'i', 'n', 's', 'A', 'l', 'l'};
        RETAIN_ALL = new char[]{'r', 'e', 't', 'a', 'i', 'n', 'A', 'l', 'l'};
        CONTAINS_KEY = new char[]{'c', 'o', 'n', 't', 'a', 'i', 'n', 's', 'K', 'e', 'y'};
        CONTAINS_VALUE = new char[]{'c', 'o', 'n', 't', 'a', 'i', 'n', 's', Util.C_VOID, 'a', 'l', 'u', 'e'};
        CONTAINS = new char[]{'c', 'o', 'n', 't', 'a', 'i', 'n', 's'};
        INDEX_OF = new char[]{'i', 'n', 'd', 'e', 'x', 'O', 'f'};
        LAST_INDEX_OF = new char[]{'l', 'a', 's', 't', Util.C_INT, 'n', 'd', 'e', 'x', 'O', 'f'};
        char[] cArr50 = {'A', 'u', 't', 'o', 'w', 'i', 'r', 'e', 'd'};
        AUTOWIRED = cArr50;
        char[] cArr51 = {'b', 'e', 'a', 'n', 's'};
        BEANS = cArr51;
        char[] cArr52 = {'f', 'a', 'c', 't', 'o', 'r', 'y'};
        FACTORY = cArr52;
        ORG_SPRING_AUTOWIRED = new char[][]{cArr14, cArr30, cArr51, cArr52, cArr10, cArr50};
        REQUIRED = new char[]{'r', 'e', 'q', 'u', 'i', 'r', 'e', 'd'};
        INIT = new char[]{Util.C_GENERIC_START, 'i', 'n', 'i', 't', Util.C_GENERIC_END};
        CLINIT = new char[]{Util.C_GENERIC_START, 'c', 'l', 'i', 'n', 'i', 't', Util.C_GENERIC_END};
        SYNTHETIC_SWITCH_ENUM_TABLE = new char[]{'$', Util.C_SHORT, 'W', Util.C_INT, Util.C_TYPE_VARIABLE, Util.C_CHAR, 'H', '_', Util.C_TYPE_VARIABLE, 'A', Util.C_BYTE, Util.C_RESOLVED, 'E', '$'};
        SYNTHETIC_ENUM_VALUES = new char[]{'E', 'N', 'U', 'M', '$', Util.C_VOID, 'A', Util.C_RESOLVED, 'U', 'E', Util.C_SHORT};
        SYNTHETIC_ASSERT_DISABLED = new char[]{'$', 'a', 's', 's', 'e', 'r', 't', 'i', 'o', 'n', 's', Util.C_DOUBLE, 'i', 's', 'a', 'b', 'l', 'e', 'd'};
        SYNTHETIC_CLASS = new char[]{'c', 'l', 'a', 's', 's', '$'};
        SYNTHETIC_OUTER_LOCAL_PREFIX = new char[]{'v', 'a', 'l', '$'};
        SYNTHETIC_ENCLOSING_INSTANCE_PREFIX = new char[]{'t', 'h', 'i', 's', '$'};
        SYNTHETIC_ACCESS_METHOD_PREFIX = new char[]{'a', 'c', 'c', 'e', 's', 's', '$'};
        SYNTHETIC_ENUM_CONSTANT_INITIALIZATION_METHOD_PREFIX = new char[]{' ', 'e', 'n', 'u', 'm', ' ', 'c', 'o', 'n', 's', 't', 'a', 'n', 't', ' ', 'i', 'n', 'i', 't', 'i', 'a', 'l', 'i', 'z', 'a', 't', 'i', 'o', 'n', '$'};
        SYNTHETIC_STATIC_FACTORY = new char[]{Util.C_GENERIC_START, 'f', 'a', 'c', 't', 'o', 'r', 'y', Util.C_GENERIC_END};
        DEFAULT_LOCATION__PARAMETER = new char[]{'P', 'A', 'R', 'A', 'M', 'E', Util.C_TYPE_VARIABLE, 'E', 'R'};
        DEFAULT_LOCATION__RETURN_TYPE = new char[]{'R', 'E', Util.C_TYPE_VARIABLE, 'U', 'R', 'N', '_', Util.C_TYPE_VARIABLE, 'Y', 'P', 'E'};
        DEFAULT_LOCATION__FIELD = new char[]{Util.C_FLOAT, Util.C_INT, 'E', Util.C_RESOLVED, Util.C_DOUBLE};
        DEFAULT_LOCATION__TYPE_ARGUMENT = new char[]{Util.C_TYPE_VARIABLE, 'Y', 'P', 'E', '_', 'A', 'R', 'G', 'U', 'M', 'E', 'N', Util.C_TYPE_VARIABLE};
        DEFAULT_LOCATION__TYPE_PARAMETER = new char[]{Util.C_TYPE_VARIABLE, 'Y', 'P', 'E', '_', 'P', 'A', 'R', 'A', 'M', 'E', Util.C_TYPE_VARIABLE, 'E', 'R'};
        DEFAULT_LOCATION__TYPE_BOUND = new char[]{Util.C_TYPE_VARIABLE, 'Y', 'P', 'E', '_', Util.C_BYTE, 'O', 'U', 'N', Util.C_DOUBLE};
        DEFAULT_LOCATION__ARRAY_CONTENTS = new char[]{'A', 'R', 'R', 'A', 'Y', '_', Util.C_CHAR, 'O', 'N', Util.C_TYPE_VARIABLE, 'E', 'N', Util.C_TYPE_VARIABLE, Util.C_SHORT};
        PACKAGE_INFO_NAME = new char[]{'p', 'a', 'c', 'k', 'a', 'g', 'e', Util.C_SUPER, 'i', 'n', 'f', 'o'};
        MODULE_INFO_NAME = new char[]{'m', 'o', 'd', 'u', 'l', 'e', Util.C_SUPER, 'i', 'n', 'f', 'o'};
        MODULE_INFO_FILE_NAME = new char[]{'m', 'o', 'd', 'u', 'l', 'e', Util.C_SUPER, 'i', 'n', 'f', 'o', '.', 'j', 'a', 'v', 'a'};
        MODULE_INFO_CLASS_NAME = new char[]{'m', 'o', 'd', 'u', 'l', 'e', Util.C_SUPER, 'i', 'n', 'f', 'o', '.', 'c', 'l', 'a', 's', 's'};
        JAVA_BASE = new char[]{'j', 'a', 'v', 'a', '.', 'b', 'a', 's', 'e'};
        JDK_INTERNAL_VALUEBASED = new char[][]{new char[]{'j', 'd', 'k'}, new char[]{'i', 'n', 't', 'e', 'r', 'n', 'a', 'l'}, new char[]{Util.C_VOID, 'a', 'l', 'u', 'e', Util.C_BYTE, 'a', 's', 'e', 'd'}};
    }
}
