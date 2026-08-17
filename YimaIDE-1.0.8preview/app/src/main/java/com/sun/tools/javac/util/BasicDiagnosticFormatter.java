package com.sun.tools.javac.util;

import com.sun.tools.javac.api.DiagnosticFormatter;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import javax.tools.JavaFileObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class BasicDiagnosticFormatter extends AbstractDiagnosticFormatter {

    /* JADX INFO: renamed from: com.sun.tools.javac.util.BasicDiagnosticFormatter$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$util$JCDiagnostic$DiagnosticType;

        static {
            int[] iArr = new int[JCDiagnostic.DiagnosticType.values().length];
            $SwitchMap$com$sun$tools$javac$util$JCDiagnostic$DiagnosticType = iArr;
            try {
                iArr[JCDiagnostic.DiagnosticType.FRAGMENT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$util$JCDiagnostic$DiagnosticType[JCDiagnostic.DiagnosticType.ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public BasicDiagnosticFormatter(Options options, JavacMessages javacMessages) {
        super(javacMessages, new BasicConfiguration(options));
    }

    private String selectFormat(JCDiagnostic jCDiagnostic) {
        DiagnosticSource diagnosticSource = jCDiagnostic.getDiagnosticSource();
        String format = getConfiguration().getFormat(BasicConfiguration.BasicFormatKind.DEFAULT_NO_POS_FORMAT);
        if (diagnosticSource != null && diagnosticSource != DiagnosticSource.NO_SOURCE) {
            if (jCDiagnostic.getIntPosition() != -1) {
                return getConfiguration().getFormat(BasicConfiguration.BasicFormatKind.DEFAULT_POS_FORMAT);
            }
            if (diagnosticSource.getFile() != null && diagnosticSource.getFile().getKind() == JavaFileObject.Kind.CLASS) {
                return getConfiguration().getFormat(BasicConfiguration.BasicFormatKind.DEFAULT_CLASS_FORMAT);
            }
        }
        return format;
    }

    public String addSourceLineIfNeeded(JCDiagnostic jCDiagnostic, String str) {
        if (!displaySource(jCDiagnostic)) {
            return str;
        }
        String str2 = "\n" + formatSourceLine(jCDiagnostic, getConfiguration().getIndentation(DiagnosticFormatter.Configuration.DiagnosticPart.SOURCE));
        if (!str.contains("\n") || getConfiguration().getSourcePosition() == BasicConfiguration.SourcePosition.BOTTOM) {
            return str.concat(str2);
        }
        return str.replaceFirst("\n", Matcher.quoteReplacement(str2) + "\n");
    }

    @Override // com.sun.tools.javac.util.AbstractDiagnosticFormatter
    public String formatDiagnostic(JCDiagnostic jCDiagnostic, Locale locale) {
        boolean z;
        if (locale == null) {
            locale = this.messages.getCurrentLocale();
        }
        String strSelectFormat = selectFormat(jCDiagnostic);
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < strSelectFormat.length()) {
            char cCharAt = strSelectFormat.charAt(i);
            if (cCharAt != '%' || i >= strSelectFormat.length() - 1) {
                z = false;
            } else {
                i++;
                cCharAt = strSelectFormat.charAt(i);
                z = true;
            }
            sb.append(z ? formatMeta(cCharAt, jCDiagnostic, locale) : String.valueOf(cCharAt));
            i++;
        }
        return this.depth == 0 ? addSourceLineIfNeeded(jCDiagnostic, sb.toString()) : sb.toString();
    }

    @Override // com.sun.tools.javac.api.DiagnosticFormatter
    public String formatMessage(JCDiagnostic jCDiagnostic, Locale locale) {
        StringBuilder sb = new StringBuilder();
        String[] strArrSplit = localize(locale, jCDiagnostic.getCode(), formatArguments(jCDiagnostic, locale).toArray()).split("\n");
        int indentation = 0;
        if (strArrSplit.length == 0) {
            strArrSplit = new String[]{""};
        }
        EnumSet<DiagnosticFormatter.Configuration.DiagnosticPart> visible = getConfiguration().getVisible();
        DiagnosticFormatter.Configuration.DiagnosticPart diagnosticPart = DiagnosticFormatter.Configuration.DiagnosticPart.SUMMARY;
        if (visible.contains(diagnosticPart)) {
            int indentation2 = getConfiguration().getIndentation(diagnosticPart);
            sb.append(indent(strArrSplit[0], indentation2));
            indentation = indentation2;
        }
        if (strArrSplit.length > 1) {
            EnumSet<DiagnosticFormatter.Configuration.DiagnosticPart> visible2 = getConfiguration().getVisible();
            DiagnosticFormatter.Configuration.DiagnosticPart diagnosticPart2 = DiagnosticFormatter.Configuration.DiagnosticPart.DETAILS;
            if (visible2.contains(diagnosticPart2)) {
                indentation += getConfiguration().getIndentation(diagnosticPart2);
                for (int i = 1; i < strArrSplit.length; i++) {
                    sb.append("\n" + indent(strArrSplit[i], indentation));
                }
            }
        }
        if (jCDiagnostic.isMultiline()) {
            EnumSet<DiagnosticFormatter.Configuration.DiagnosticPart> visible3 = getConfiguration().getVisible();
            DiagnosticFormatter.Configuration.DiagnosticPart diagnosticPart3 = DiagnosticFormatter.Configuration.DiagnosticPart.SUBDIAGNOSTICS;
            if (visible3.contains(diagnosticPart3)) {
                int indentation3 = indentation + getConfiguration().getIndentation(diagnosticPart3);
                Iterator<String> it = formatSubdiagnostics(jCDiagnostic, locale).iterator();
                while (it.hasNext()) {
                    sb.append("\n" + indent(it.next(), indentation3));
                }
            }
        }
        return sb.toString();
    }

    public String formatMeta(char c, JCDiagnostic jCDiagnostic, Locale locale) {
        if (c == '%') {
            return "%";
        }
        if (c == 'L') {
            return formatLintCategory(jCDiagnostic, locale);
        }
        if (c == '_') {
            return " ";
        }
        if (c == 'b') {
            return formatSource(jCDiagnostic, false, locale);
        }
        if (c == 'c') {
            return formatPosition(jCDiagnostic, DiagnosticFormatter.PositionKind.COLUMN, locale);
        }
        if (c == 'e') {
            return formatPosition(jCDiagnostic, DiagnosticFormatter.PositionKind.END, locale);
        }
        if (c == 'f') {
            return formatSource(jCDiagnostic, true, locale);
        }
        if (c == 'l') {
            return formatPosition(jCDiagnostic, DiagnosticFormatter.PositionKind.LINE, locale);
        }
        if (c == 'm') {
            return formatMessage(jCDiagnostic, locale);
        }
        if (c == 'o') {
            return formatPosition(jCDiagnostic, DiagnosticFormatter.PositionKind.OFFSET, locale);
        }
        if (c == 'p') {
            return formatKind(jCDiagnostic, locale);
        }
        if (c == 's') {
            return formatPosition(jCDiagnostic, DiagnosticFormatter.PositionKind.START, locale);
        }
        if (c != 't') {
            return String.valueOf(c);
        }
        int i = AnonymousClass1.$SwitchMap$com$sun$tools$javac$util$JCDiagnostic$DiagnosticType[jCDiagnostic.getType().ordinal()];
        if (i != 1) {
            return (i == 2 && jCDiagnostic.getIntPosition() != -1) ? "" : formatKind(jCDiagnostic, locale);
        }
        return "";
    }

    @Override // com.sun.tools.javac.util.AbstractDiagnosticFormatter, com.sun.tools.javac.api.DiagnosticFormatter
    public BasicConfiguration getConfiguration() {
        return (BasicConfiguration) super.getConfiguration();
    }

    public BasicDiagnosticFormatter(JavacMessages javacMessages) {
        super(javacMessages, new BasicConfiguration());
    }

    public static class BasicConfiguration extends AbstractDiagnosticFormatter.SimpleConfiguration {
        protected Map<BasicFormatKind, String> availableFormats;
        protected Map<DiagnosticFormatter.Configuration.DiagnosticPart, Integer> indentationLevels;
        protected SourcePosition sourcePosition;

        public enum BasicFormatKind {
            DEFAULT_POS_FORMAT,
            DEFAULT_NO_POS_FORMAT,
            DEFAULT_CLASS_FORMAT
        }

        public enum SourcePosition {
            BOTTOM,
            AFTER_SUMMARY
        }

        /* JADX WARN: Illegal instructions before constructor call */
        public BasicConfiguration(Options options) {
            DiagnosticFormatter.Configuration.DiagnosticPart diagnosticPart = DiagnosticFormatter.Configuration.DiagnosticPart.SUMMARY;
            DiagnosticFormatter.Configuration.DiagnosticPart diagnosticPart2 = DiagnosticFormatter.Configuration.DiagnosticPart.DETAILS;
            DiagnosticFormatter.Configuration.DiagnosticPart diagnosticPart3 = DiagnosticFormatter.Configuration.DiagnosticPart.SUBDIAGNOSTICS;
            DiagnosticFormatter.Configuration.DiagnosticPart diagnosticPart4 = DiagnosticFormatter.Configuration.DiagnosticPart.SOURCE;
            super(options, EnumSet.of(diagnosticPart, diagnosticPart2, diagnosticPart3, diagnosticPart4));
            initFormat();
            initIndentation();
            if (options.isSet("diags.legacy")) {
                initOldFormat();
            }
            String str = options.get("diags.layout");
            if (str != null) {
                if (str.equals("OLD")) {
                    initOldFormat();
                } else {
                    initFormats(str);
                }
            }
            String str2 = options.get("diags.sourcePosition");
            if (str2 == null || !str2.equals("bottom")) {
                setSourcePosition(SourcePosition.AFTER_SUMMARY);
            } else {
                setSourcePosition(SourcePosition.BOTTOM);
            }
            String str3 = options.get("diags.indent");
            if (str3 != null) {
                String[] strArrSplit = str3.split("\\|");
                try {
                    int length = strArrSplit.length;
                    if (length == 2) {
                        setIndentation(diagnosticPart2, Integer.parseInt(strArrSplit[1]));
                    } else {
                        if (length != 3) {
                            if (length != 4) {
                                if (length == 5) {
                                    setIndentation(DiagnosticFormatter.Configuration.DiagnosticPart.JLS, Integer.parseInt(strArrSplit[4]));
                                }
                            }
                            setIndentation(diagnosticPart3, Integer.parseInt(strArrSplit[3]));
                        }
                        setIndentation(diagnosticPart4, Integer.parseInt(strArrSplit[2]));
                        setIndentation(diagnosticPart2, Integer.parseInt(strArrSplit[1]));
                    }
                    setIndentation(diagnosticPart, Integer.parseInt(strArrSplit[0]));
                } catch (NumberFormatException unused) {
                    initIndentation();
                }
            }
        }

        private void initFormat() {
            initFormats("%f:%l:%_%p%L%m", "%p%L%m", "%f:%_%p%L%m");
        }

        private void initFormats(String str) {
            String[] strArrSplit = str.split("\\|");
            int length = strArrSplit.length;
            if (length == 2) {
                setFormat(BasicFormatKind.DEFAULT_NO_POS_FORMAT, strArrSplit[1]);
            } else if (length == 3) {
                setFormat(BasicFormatKind.DEFAULT_CLASS_FORMAT, strArrSplit[2]);
                setFormat(BasicFormatKind.DEFAULT_NO_POS_FORMAT, strArrSplit[1]);
            }
            setFormat(BasicFormatKind.DEFAULT_POS_FORMAT, strArrSplit[0]);
        }

        private void initIndentation() {
            this.indentationLevels = new HashMap();
            setIndentation(DiagnosticFormatter.Configuration.DiagnosticPart.SUMMARY, 0);
            setIndentation(DiagnosticFormatter.Configuration.DiagnosticPart.DETAILS, 2);
            setIndentation(DiagnosticFormatter.Configuration.DiagnosticPart.SUBDIAGNOSTICS, 4);
            setIndentation(DiagnosticFormatter.Configuration.DiagnosticPart.SOURCE, 0);
        }

        private void initOldFormat() {
            initFormats("%f:%l:%_%t%L%m", "%p%L%m", "%f:%_%t%L%m");
        }

        public String getFormat(BasicFormatKind basicFormatKind) {
            return this.availableFormats.get(basicFormatKind);
        }

        public int getIndentation(DiagnosticFormatter.Configuration.DiagnosticPart diagnosticPart) {
            return this.indentationLevels.get(diagnosticPart).intValue();
        }

        public SourcePosition getSourcePosition() {
            return this.sourcePosition;
        }

        public void setFormat(BasicFormatKind basicFormatKind, String str) {
            this.availableFormats.put(basicFormatKind, str);
        }

        public void setIndentation(DiagnosticFormatter.Configuration.DiagnosticPart diagnosticPart, int i) {
            this.indentationLevels.put(diagnosticPart, Integer.valueOf(i));
        }

        public void setSourcePosition(SourcePosition sourcePosition) {
            this.sourcePosition = sourcePosition;
        }

        private void initFormats(String str, String str2, String str3) {
            this.availableFormats = new EnumMap(BasicFormatKind.class);
            setFormat(BasicFormatKind.DEFAULT_POS_FORMAT, str);
            setFormat(BasicFormatKind.DEFAULT_NO_POS_FORMAT, str2);
            setFormat(BasicFormatKind.DEFAULT_CLASS_FORMAT, str3);
        }

        public BasicConfiguration() {
            super(EnumSet.of(DiagnosticFormatter.Configuration.DiagnosticPart.SUMMARY, DiagnosticFormatter.Configuration.DiagnosticPart.DETAILS, DiagnosticFormatter.Configuration.DiagnosticPart.SUBDIAGNOSTICS, DiagnosticFormatter.Configuration.DiagnosticPart.SOURCE));
            initFormat();
            initIndentation();
        }
    }
}
