package com.sun.tools.javac.util;

import com.sun.org.apache.xml.internal.utils.LocaleUtility;
import com.sun.tools.javac.api.DiagnosticFormatter;
import com.sun.tools.javac.api.Formattable;
import com.sun.tools.javac.code.Source;
import com.sun.tools.javac.file.PathFileObject;
import com.sun.tools.javac.tree.JCTree;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import javax.tools.JavaFileObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class RawDiagnosticFormatter extends AbstractDiagnosticFormatter {
    private static final Set<String> CODES_NEEDING_SOURCE_NORMALIZATION = Collections.unmodifiableSet(new HashSet(Arrays.asList("compiler.note.preview.filename", "compiler.note.preview.plural")));
    RawDiagnosticPosHelper rawDiagnosticPosHelper;

    public static class RawDiagnosticPosHelper {
        private final JCDiagnostic diag;

        public RawDiagnosticPosHelper(JCDiagnostic jCDiagnostic) {
            this.diag = jCDiagnostic;
        }

        public String getPosition(JCTree.JCExpression jCExpression) {
            DiagnosticSource diagnosticSource = this.diag.getDiagnosticSource();
            long lineNumber = this.diag.getLineNumber();
            long lineNumber2 = diagnosticSource.getLineNumber(jCExpression.pos);
            long columnNumber = diagnosticSource.getColumnNumber(jCExpression.pos, false);
            if (lineNumber2 == lineNumber) {
                return String.valueOf(columnNumber);
            }
            return lineNumber2 + ":" + columnNumber;
        }
    }

    public RawDiagnosticFormatter(Options options) {
        super(null, new AbstractDiagnosticFormatter.SimpleConfiguration(options, EnumSet.of(DiagnosticFormatter.Configuration.DiagnosticPart.SUMMARY, DiagnosticFormatter.Configuration.DiagnosticPart.DETAILS, DiagnosticFormatter.Configuration.DiagnosticPart.SUBDIAGNOSTICS)));
    }

    @Override // com.sun.tools.javac.util.AbstractDiagnosticFormatter
    public String formatArgument(JCDiagnostic jCDiagnostic, Object obj, Locale locale) {
        String argument;
        if (obj instanceof Formattable) {
            argument = obj.toString();
        } else if (obj instanceof JCTree.JCExpression) {
            Assert.checkNonNull(this.rawDiagnosticPosHelper);
            argument = "@" + this.rawDiagnosticPosHelper.getPosition((JCTree.JCExpression) obj);
        } else if (obj instanceof PathFileObject) {
            argument = ((PathFileObject) obj).getShortName();
        } else if (obj instanceof JCTree.Tag) {
            argument = "compiler.misc.tree.tag." + StringUtils.toLowerCase(((JCTree.Tag) obj).name());
        } else {
            argument = ((obj instanceof Source) && obj == Source.DEFAULT && CODES_NEEDING_SOURCE_NORMALIZATION.contains(jCDiagnostic.getCode())) ? "DEFAULT" : super.formatArgument(jCDiagnostic, obj, null);
        }
        if (!(obj instanceof JCDiagnostic)) {
            return argument;
        }
        return "(" + argument + ")";
    }

    @Override // com.sun.tools.javac.util.AbstractDiagnosticFormatter
    public String formatDiagnostic(JCDiagnostic jCDiagnostic, Locale locale) {
        try {
            this.rawDiagnosticPosHelper = new RawDiagnosticPosHelper(jCDiagnostic);
            StringBuilder sb = new StringBuilder();
            if (jCDiagnostic.getPosition() != -1) {
                sb.append(formatSource(jCDiagnostic, false, (Locale) null));
                sb.append(':');
                sb.append(formatPosition(jCDiagnostic, DiagnosticFormatter.PositionKind.LINE, (Locale) null));
                sb.append(':');
                sb.append(formatPosition(jCDiagnostic, DiagnosticFormatter.PositionKind.COLUMN, (Locale) null));
                sb.append(':');
            } else if (jCDiagnostic.getSource() == null || jCDiagnostic.getSource().getKind() != JavaFileObject.Kind.CLASS) {
                sb.append(LocaleUtility.IETF_SEPARATOR);
            } else {
                sb.append(formatSource(jCDiagnostic, false, (Locale) null));
                sb.append(":-:-:");
            }
            sb.append(' ');
            sb.append(formatMessage(jCDiagnostic, (Locale) null));
            if (displaySource(jCDiagnostic)) {
                sb.append("\n");
                sb.append(formatSourceLine(jCDiagnostic, 0));
            }
            return sb.toString();
        } catch (Exception unused) {
            return null;
        } finally {
            this.rawDiagnosticPosHelper = null;
        }
    }

    @Override // com.sun.tools.javac.api.DiagnosticFormatter
    public String formatMessage(JCDiagnostic jCDiagnostic, Locale locale) {
        StringBuilder sb = new StringBuilder();
        sb.append(localize(null, jCDiagnostic.getCode(), formatArguments(jCDiagnostic, locale).toArray()));
        if (jCDiagnostic.isMultiline() && getConfiguration().getVisible().contains(DiagnosticFormatter.Configuration.DiagnosticPart.SUBDIAGNOSTICS) && formatSubdiagnostics(jCDiagnostic, null).nonEmpty()) {
            sb.append(",{");
            String str = "";
            for (String str2 : formatSubdiagnostics(jCDiagnostic, null)) {
                sb.append(str);
                sb.append("(");
                sb.append(str2);
                sb.append(")");
                str = ",";
            }
            sb.append('}');
        }
        return sb.toString();
    }

    @Override // com.sun.tools.javac.util.AbstractDiagnosticFormatter
    public boolean isRaw() {
        return true;
    }

    @Override // com.sun.tools.javac.util.AbstractDiagnosticFormatter
    public String localize(Locale locale, String str, Object... objArr) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        int length = objArr.length;
        String str2 = ": ";
        int i = 0;
        while (i < length) {
            Object obj = objArr[i];
            sb.append(str2);
            sb.append(obj);
            i++;
            str2 = ", ";
        }
        return sb.toString();
    }
}
