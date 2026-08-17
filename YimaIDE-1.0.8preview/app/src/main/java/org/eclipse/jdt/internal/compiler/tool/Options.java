package org.eclipse.jdt.internal.compiler.tool;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import org.fusesource.jansi.AnsiRenderer;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public final class Options {
    private static final Set<String> FILE_MANAGER_OPTIONS;
    private static final Set<String> ONE_ARGUMENT_OPTIONS;
    private static final Set<String> ZERO_ARGUMENT_OPTIONS;

    static {
        HashSet hashSet = new HashSet();
        ZERO_ARGUMENT_OPTIONS = hashSet;
        hashSet.add("-progress");
        hashSet.add("-proceedOnError");
        hashSet.add("-proceedOnError:Fatal");
        hashSet.add("-time");
        hashSet.add("-v");
        hashSet.add("-version");
        hashSet.add("-showversion");
        hashSet.add("-deprecation");
        hashSet.add("-help");
        hashSet.add("-?");
        hashSet.add("-help:warn");
        hashSet.add("-?:warn");
        hashSet.add("-noExit");
        hashSet.add("-verbose");
        hashSet.add("-referenceInfo");
        hashSet.add("-inlineJSR");
        hashSet.add("-g");
        hashSet.add("-g:none");
        hashSet.add("-warn:none");
        hashSet.add("-preserveAllLocals");
        hashSet.add("-enableJavadoc");
        hashSet.add("-Xemacs");
        hashSet.add("-X");
        hashSet.add("-O");
        hashSet.add("-1.3");
        hashSet.add("-1.4");
        hashSet.add("-1.5");
        hashSet.add("-5");
        hashSet.add("-5.0");
        hashSet.add("-1.6");
        hashSet.add("-6");
        hashSet.add("-6.0");
        hashSet.add("-1.7");
        hashSet.add("-7");
        hashSet.add("-7.0");
        hashSet.add("-1.8");
        hashSet.add("-8");
        hashSet.add("-8.0");
        hashSet.add("-proc:only");
        hashSet.add("-proc:none");
        hashSet.add("-XprintProcessorInfo");
        hashSet.add("-XprintRounds");
        hashSet.add("-parameters");
        hashSet.add("-genericsignature");
        HashSet hashSet2 = new HashSet();
        FILE_MANAGER_OPTIONS = hashSet2;
        hashSet2.add("-bootclasspath");
        hashSet2.add("-encoding");
        hashSet2.add("-d");
        hashSet2.add("-classpath");
        hashSet2.add("-cp");
        hashSet2.add("-sourcepath");
        hashSet2.add("-extdirs");
        hashSet2.add("-endorseddirs");
        hashSet2.add("-s");
        hashSet2.add("-processorpath");
        HashSet hashSet3 = new HashSet();
        ONE_ARGUMENT_OPTIONS = hashSet3;
        hashSet3.addAll(hashSet2);
        hashSet3.add("-log");
        hashSet3.add("-repeat");
        hashSet3.add("-maxProblems");
        hashSet3.add("-source");
        hashSet3.add("-target");
        hashSet3.add("-processor");
        hashSet3.add("-classNames");
        hashSet3.add("-properties");
    }

    public static int processOptions(String str) {
        if (str == null) {
            return -1;
        }
        if (ZERO_ARGUMENT_OPTIONS.contains(str)) {
            return 0;
        }
        if (ONE_ARGUMENT_OPTIONS.contains(str)) {
            return 1;
        }
        if (str.startsWith("-g")) {
            if (str.length() > 3) {
                StringTokenizer stringTokenizer = new StringTokenizer(str.substring(3, str.length()), AnsiRenderer.CODE_LIST_SEPARATOR);
                while (stringTokenizer.hasMoreTokens()) {
                    String strNextToken = stringTokenizer.nextToken();
                    if ("vars".equals(strNextToken) || "lines".equals(strNextToken) || "source".equals(strNextToken)) {
                    }
                }
                return 0;
            }
            return -1;
        }
        int i = 7;
        if (!str.startsWith("-warn")) {
            if (!str.startsWith("-nowarn")) {
                return (str.startsWith("-J") || str.startsWith("-X") || str.startsWith("-A")) ? 0 : -1;
            }
            int length = str.length();
            if (length == 7) {
                return 0;
            }
            if (length != 8) {
                int iIndexOf = str.indexOf(91) + 1;
                int iLastIndexOf = str.lastIndexOf(93);
                if (iIndexOf > 8 && iLastIndexOf != -1 && iIndexOf <= iLastIndexOf && iLastIndexOf >= str.length() - 1 && str.substring(iIndexOf, iLastIndexOf).length() > 0) {
                    return 0;
                }
            }
            return -1;
        }
        if (str.length() <= 6) {
            return -1;
        }
        char cCharAt = str.charAt(6);
        if (cCharAt != '+' && cCharAt != '-') {
            i = 6;
        }
        StringTokenizer stringTokenizer2 = new StringTokenizer(str.substring(i, str.length()), AnsiRenderer.CODE_LIST_SEPARATOR);
        int i2 = 0;
        while (stringTokenizer2.hasMoreTokens()) {
            String strNextToken2 = stringTokenizer2.nextToken();
            i2++;
            if (!strNextToken2.equals("allDeadCode") && !strNextToken2.equals("allDeprecation") && !strNextToken2.equals("allJavadoc") && !strNextToken2.equals("allOver-ann") && !strNextToken2.equals("assertIdentifier") && !strNextToken2.equals("boxing") && !strNextToken2.equals("charConcat") && !strNextToken2.equals("compareIdentical") && !strNextToken2.equals("conditionAssign") && !strNextToken2.equals("constructorName") && !strNextToken2.equals("deadCode") && !strNextToken2.equals("dep-ann") && !strNextToken2.equals("deprecation") && !strNextToken2.equals("discouraged") && !strNextToken2.equals("emptyBlock") && !strNextToken2.equals("enumIdentifier") && !strNextToken2.equals("enumSwitch") && !strNextToken2.equals("fallthrough") && !strNextToken2.equals("fieldHiding") && !strNextToken2.equals("finalBound") && !strNextToken2.equals("finally") && !strNextToken2.equals("forbidden") && !strNextToken2.equals("hashCode") && !strNextToken2.equals("hiding") && !strNextToken2.equals("includeAssertNull") && !strNextToken2.equals("incomplete-switch") && !strNextToken2.equals("indirectStatic") && !strNextToken2.equals("interfaceNonInherited") && !strNextToken2.equals("intfAnnotation") && !strNextToken2.equals("intfNonInherited") && !strNextToken2.equals("intfRedundant") && !strNextToken2.equals("javadoc") && !strNextToken2.equals("localHiding") && !strNextToken2.equals("maskedCatchBlock") && !strNextToken2.equals("maskedCatchBlocks") && !strNextToken2.equals("nls") && !strNextToken2.equals("noEffectAssign") && !strNextToken2.equals("noImplicitStringConversion") && !strNextToken2.equals("null") && !strNextToken2.equals("nullDereference") && !strNextToken2.equals("over-ann") && !strNextToken2.equals("packageDefaultMethod") && !strNextToken2.equals("paramAssign") && !strNextToken2.equals("pkgDefaultMethod") && !strNextToken2.equals("raw") && !strNextToken2.equals("semicolon") && !strNextToken2.equals("serial") && !strNextToken2.equals("specialParamHiding") && !strNextToken2.equals("static-access") && !strNextToken2.equals("staticReceiver") && !strNextToken2.equals("super") && !strNextToken2.equals("suppress") && !strNextToken2.equals("syncOverride") && !strNextToken2.equals("synthetic-access") && !strNextToken2.equals("syntheticAccess") && !strNextToken2.equals("typeHiding") && !strNextToken2.equals("unchecked") && !strNextToken2.equals("unnecessaryElse") && !strNextToken2.equals("unnecessaryOperator") && !strNextToken2.equals("unqualified-field-access") && !strNextToken2.equals("unqualifiedField") && !strNextToken2.equals("unsafe") && !strNextToken2.equals("unused") && !strNextToken2.equals("unusedArgument") && !strNextToken2.equals("unusedArguments") && !strNextToken2.equals("unusedImport") && !strNextToken2.equals("unusedImports") && !strNextToken2.equals("unusedLabel") && !strNextToken2.equals("unusedLocal") && !strNextToken2.equals("unusedLocals") && !strNextToken2.equals("unusedPrivate") && !strNextToken2.equals("unusedThrown") && !strNextToken2.equals("unusedTypeArgs") && !strNextToken2.equals("uselessTypeCheck") && !strNextToken2.equals("varargsCast") && !strNextToken2.equals("warningToken")) {
                if (strNextToken2.equals("tasks")) {
                    int iIndexOf2 = strNextToken2.indexOf(40);
                    int iIndexOf3 = strNextToken2.indexOf(41);
                    if (((iIndexOf2 < 0 || iIndexOf3 < 0 || iIndexOf2 >= iIndexOf3) ? "" : strNextToken2.substring(iIndexOf2 + 1, iIndexOf3).trim().replace('|', ',')).length() == 0) {
                    }
                }
                return -1;
            }
        }
        return i2 == 0 ? -1 : 0;
    }

    public static int processOptionsFileManager(String str) {
        return (str != null && FILE_MANAGER_OPTIONS.contains(str)) ? 1 : -1;
    }
}
