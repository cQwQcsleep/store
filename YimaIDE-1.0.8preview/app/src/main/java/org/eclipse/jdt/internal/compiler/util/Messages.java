package org.eclipse.jdt.internal.compiler.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public final class Messages {
    private static final String BUNDLE_NAME = "org.eclipse.jdt.internal.compiler.messages";
    private static final String EXTENSION = ".properties";
    public static String abort_againstSourceModel;
    public static String abort_externaAnnotationFile;
    public static String abort_invalidAttribute;
    public static String abort_invalidExceptionAttribute;
    public static String abort_invalidOpcode;
    public static String abort_missingCode;
    public static String accept_cannot;
    public static String ast_missingCode;
    public static String compilation_beginningToCompile;
    public static String compilation_done;
    public static String compilation_internalError;
    public static String compilation_loadBinary;
    public static String compilation_process;
    public static String compilation_processing;
    public static String compilation_request;
    public static String compilation_unit;
    public static String compilation_units;
    public static String compilation_unresolvedProblem;
    public static String compilation_unresolvedProblems;
    public static String compilation_write;
    public static String constant_cannotCastedInto;
    public static String constant_cannotConvertedTo;
    private static String[] nlSuffixes;
    public static String output_isFile;
    public static String output_notValid;
    public static String output_notValidAll;
    public static String parser_corruptedFile;
    public static String parser_endOfConstructor;
    public static String parser_endOfFile;
    public static String parser_endOfInitializer;
    public static String parser_endOfMethod;
    public static String parser_incorrectPath;
    public static String parser_missingFile;
    public static String parser_moveFiles;
    public static String parser_regularParse;
    public static String parser_syntaxRecovery;
    public static String pattern_matching_instanceof;
    public static String pattern_matching_switch;
    public static String problem_atLine;
    public static String problem_noSourceInformation;
    public static String record_patterns;
    public static String records;
    public static String sealed_types;
    public static String string_templates;
    public static String switch_expression;
    public static String text_block;
    public static String unnamed_classes_and_instance_main_methods;
    public static String unnamed_patterns_and_vars;

    public static class MessagesProperties extends Properties {
        private static final int MOD_EXPECTED = 9;
        private static final int MOD_MASK = 25;
        private static final long serialVersionUID = 1;
        private final Map fields;

        public MessagesProperties(Field[] fieldArr, String str) {
            int length = fieldArr.length;
            this.fields = new HashMap(length * 2);
            for (int i = 0; i < length; i++) {
                this.fields.put(fieldArr[i].getName(), fieldArr[i]);
            }
        }

        @Override // java.util.Hashtable, java.util.Dictionary, java.util.Map
        public synchronized Object put(Object obj, Object obj2) {
            try {
                Field field = (Field) this.fields.get(obj);
                if (field == null) {
                    return null;
                }
                if ((field.getModifiers() & 25) != 9) {
                    return null;
                }
                field.set(null, obj2);
                return null;
            } catch (SecurityException | Exception unused) {
            }
        }
    }

    static {
        initializeMessages(BUNDLE_NAME, Messages.class);
    }

    private Messages() {
    }

    public static String bind(String str, Object obj) {
        return bind(str, new Object[]{obj});
    }

    private static String[] buildVariants(String str) {
        if (nlSuffixes == null) {
            String string = Locale.getDefault().toString();
            ArrayList arrayList = new ArrayList(4);
            while (true) {
                arrayList.add("_" + string + EXTENSION);
                int iLastIndexOf = string.lastIndexOf(95);
                if (iLastIndexOf == -1) {
                    break;
                }
                string = string.substring(0, iLastIndexOf);
            }
            arrayList.add(EXTENSION);
            nlSuffixes = (String[]) arrayList.toArray(new String[arrayList.size()]);
        }
        String strReplace = str.replace('.', '/');
        int length = nlSuffixes.length;
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            strArr[i] = strReplace + nlSuffixes[i];
        }
        return strArr;
    }

    private static InputStream createInputStream(ClassLoader classLoader, String str) {
        return classLoader == null ? ClassLoader.getSystemResourceAsStream(str) : classLoader.getResourceAsStream(str);
    }

    public static void initializeMessages(String str, Class cls) throws Throwable {
        Field[] declaredFields = cls.getDeclaredFields();
        load(str, cls.getClassLoader(), declaredFields);
        for (Field field : declaredFields) {
            if ((field.getModifiers() & 25) == 9) {
                try {
                    if (field.get(cls) == null) {
                        field.set(null, "Missing message: " + field.getName() + " in: " + str);
                    }
                } catch (IllegalAccessException | IllegalArgumentException unused) {
                }
            }
        }
    }

    public static void load(String str, ClassLoader classLoader, Field[] fieldArr) throws Throwable {
        String[] strArrBuildVariants = buildVariants(str);
        int length = strArrBuildVariants.length;
        while (true) {
            length--;
            if (length < 0) {
                return;
            }
            Throwable th = null;
            try {
                InputStream inputStreamCreateInputStream = createInputStream(classLoader, strArrBuildVariants[length]);
                if (inputStreamCreateInputStream != null) {
                    try {
                        new MessagesProperties(fieldArr, str).load(inputStreamCreateInputStream);
                    } catch (Throwable th2) {
                        th = th2;
                        inputStreamCreateInputStream.close();
                        throw th;
                    }
                } else if (inputStreamCreateInputStream != null) {
                }
                inputStreamCreateInputStream.close();
            } catch (Throwable th3) {
                if (th == null) {
                    th = th3;
                } else if (th != th3) {
                    try {
                        th.addSuppressed(th3);
                    } catch (IOException unused) {
                        continue;
                    }
                }
                throw th;
            }
        }
    }

    public static String bind(String str) {
        return bind(str, (Object[]) null);
    }

    public static String bind(String str, Object obj, Object obj2) {
        return bind(str, new Object[]{obj, obj2});
    }

    public static String bind(String str, Object[] objArr) {
        return MessageFormat.format(str, objArr);
    }
}
