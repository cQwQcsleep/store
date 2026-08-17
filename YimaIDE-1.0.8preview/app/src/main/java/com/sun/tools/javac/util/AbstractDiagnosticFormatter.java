package com.sun.tools.javac.util;

import com.sun.jna.platform.win32.COM.tlb.imp.TlbBase;
import com.sun.tools.javac.api.DiagnosticFormatter;
import com.sun.tools.javac.api.Formattable;
import com.sun.tools.javac.code.Lint;
import com.sun.tools.javac.code.Printer;
import com.sun.tools.javac.code.Source;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.file.PathFileObject;
import com.sun.tools.javac.jvm.Profile;
import com.sun.tools.javac.jvm.Target;
import com.sun.tools.javac.main.Option;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.Pretty;
import defpackage.s22;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.tools.JavaFileObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class AbstractDiagnosticFormatter implements DiagnosticFormatter<JCDiagnostic> {
    private SimpleConfiguration config;
    protected JavacMessages messages;
    protected int depth = 0;
    private List<Type> allCaptured = List.nil();
    protected Printer printer = new Printer() { // from class: com.sun.tools.javac.util.AbstractDiagnosticFormatter.1
        @Override // com.sun.tools.javac.code.Printer
        public String capturedVarId(Type.CapturedType capturedType, Locale locale) {
            return "" + (AbstractDiagnosticFormatter.this.allCaptured.indexOf(capturedType) + 1);
        }

        @Override // com.sun.tools.javac.code.Printer
        public String localize(Locale locale, String str, Object... objArr) {
            return AbstractDiagnosticFormatter.this.localize(locale, str, objArr);
        }

        @Override // com.sun.tools.javac.code.Printer, com.sun.tools.javac.code.Type.Visitor
        public String visitCapturedType(Type.CapturedType capturedType, Locale locale) {
            if (!AbstractDiagnosticFormatter.this.allCaptured.contains(capturedType)) {
                AbstractDiagnosticFormatter abstractDiagnosticFormatter = AbstractDiagnosticFormatter.this;
                abstractDiagnosticFormatter.allCaptured = abstractDiagnosticFormatter.allCaptured.append(capturedType);
            }
            return super.visitCapturedType(capturedType, locale);
        }
    };

    /* JADX INFO: renamed from: com.sun.tools.javac.util.AbstractDiagnosticFormatter$2, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$api$DiagnosticFormatter$PositionKind;
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag;
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$util$JCDiagnostic$DiagnosticType;

        static {
            int[] iArr = new int[JCTree.Tag.values().length];
            $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag = iArr;
            try {
                iArr[JCTree.Tag.PARENS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.LAMBDA.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.REFERENCE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.CONDEXPR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[DiagnosticFormatter.PositionKind.values().length];
            $SwitchMap$com$sun$tools$javac$api$DiagnosticFormatter$PositionKind = iArr2;
            try {
                iArr2[DiagnosticFormatter.PositionKind.START.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$api$DiagnosticFormatter$PositionKind[DiagnosticFormatter.PositionKind.END.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$api$DiagnosticFormatter$PositionKind[DiagnosticFormatter.PositionKind.LINE.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$api$DiagnosticFormatter$PositionKind[DiagnosticFormatter.PositionKind.COLUMN.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$api$DiagnosticFormatter$PositionKind[DiagnosticFormatter.PositionKind.OFFSET.ordinal()] = 5;
            } catch (NoSuchFieldError unused9) {
            }
            int[] iArr3 = new int[JCDiagnostic.DiagnosticType.values().length];
            $SwitchMap$com$sun$tools$javac$util$JCDiagnostic$DiagnosticType = iArr3;
            try {
                iArr3[JCDiagnostic.DiagnosticType.FRAGMENT.ordinal()] = 1;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$util$JCDiagnostic$DiagnosticType[JCDiagnostic.DiagnosticType.NOTE.ordinal()] = 2;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$util$JCDiagnostic$DiagnosticType[JCDiagnostic.DiagnosticType.WARNING.ordinal()] = 3;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$util$JCDiagnostic$DiagnosticType[JCDiagnostic.DiagnosticType.ERROR.ordinal()] = 4;
            } catch (NoSuchFieldError unused13) {
            }
        }
    }

    public AbstractDiagnosticFormatter(JavacMessages javacMessages, SimpleConfiguration simpleConfiguration) {
        this.messages = javacMessages;
        this.config = simpleConfiguration;
    }

    private String expr2String(JCTree.JCExpression jCExpression) {
        int i = AnonymousClass2.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[jCExpression.getTag().ordinal()];
        if (i == 1) {
            return expr2String(((JCTree.JCParens) jCExpression).expr);
        }
        if (i == 2 || i == 3 || i == 4) {
            return Pretty.toSimpleString(jCExpression);
        }
        Assert.error("unexpected tree kind " + jCExpression.getKind());
        return null;
    }

    private long getPosition(JCDiagnostic jCDiagnostic, DiagnosticFormatter.PositionKind positionKind) {
        int i = AnonymousClass2.$SwitchMap$com$sun$tools$javac$api$DiagnosticFormatter$PositionKind[positionKind.ordinal()];
        if (i == 1) {
            return jCDiagnostic.getIntStartPosition();
        }
        if (i == 2) {
            return jCDiagnostic.getIntEndPosition();
        }
        if (i == 3) {
            return jCDiagnostic.getLineNumber();
        }
        if (i == 4) {
            return jCDiagnostic.getColumnNumber();
        }
        if (i == 5) {
            return jCDiagnostic.getIntPosition();
        }
        s22.a("Unknown diagnostic position: ", positionKind);
        return 0L;
    }

    @Override // com.sun.tools.javac.api.DiagnosticFormatter
    public boolean displaySource(JCDiagnostic jCDiagnostic) {
        return (!this.config.getVisible().contains(DiagnosticFormatter.Configuration.DiagnosticPart.SOURCE) || jCDiagnostic.getType() == JCDiagnostic.DiagnosticType.FRAGMENT || jCDiagnostic.getIntPosition() == -1) ? false : true;
    }

    @Override // com.sun.tools.javac.api.DiagnosticFormatter
    public String format(JCDiagnostic jCDiagnostic, Locale locale) {
        this.allCaptured = List.nil();
        return formatDiagnostic(jCDiagnostic, locale);
    }

    public String formatArgument(JCDiagnostic jCDiagnostic, Object obj, Locale locale) {
        if (obj instanceof JCDiagnostic) {
            JCDiagnostic jCDiagnostic2 = (JCDiagnostic) obj;
            this.depth++;
            try {
                JCDiagnostic jCDiagnosticRewrite = jCDiagnostic2.hasRewriter() ? jCDiagnostic2.rewrite() : null;
                if (jCDiagnosticRewrite != null) {
                    jCDiagnostic2 = jCDiagnosticRewrite;
                }
                return formatMessage(jCDiagnostic2, locale);
            } finally {
                this.depth--;
            }
        }
        if (obj instanceof JCTree.JCExpression) {
            return expr2String((JCTree.JCExpression) obj);
        }
        if (obj instanceof Iterable) {
            Iterable<?> iterable = (Iterable) obj;
            if (!(obj instanceof Path)) {
                return formatIterable(jCDiagnostic, iterable, locale);
            }
        }
        if (obj instanceof Type) {
            return this.printer.visit(((Type) obj).stripMetadata(), locale);
        }
        if (obj instanceof JCDiagnostic.AnnotatedType) {
            return this.printer.visit(((JCDiagnostic.AnnotatedType) obj).type(), locale);
        }
        if (obj instanceof Symbol) {
            return this.printer.visit((Symbol) obj, locale);
        }
        if (obj instanceof JavaFileObject) {
            return ((JavaFileObject) obj).getName();
        }
        if (obj instanceof Profile) {
            return ((Profile) obj).name;
        }
        if (obj instanceof Option) {
            return ((Option) obj).primaryName;
        }
        if (obj instanceof Formattable) {
            return ((Formattable) obj).toString(locale, this.messages);
        }
        if (obj instanceof Target) {
            return ((Target) obj).name;
        }
        if (obj instanceof Source) {
            return ((Source) obj).name;
        }
        if (!(obj instanceof JCTree.Tag)) {
            return String.valueOf(obj);
        }
        return this.messages.getLocalizedString(locale, "compiler.misc.tree.tag." + StringUtils.toLowerCase(((JCTree.Tag) obj).name()), new Object[0]);
    }

    public Collection<String> formatArguments(JCDiagnostic jCDiagnostic, Locale locale) {
        ListBuffer listBuffer = new ListBuffer();
        for (Object obj : jCDiagnostic.getArgs()) {
            listBuffer.append(formatArgument(jCDiagnostic, obj, locale));
        }
        return listBuffer.toList();
    }

    public abstract String formatDiagnostic(JCDiagnostic jCDiagnostic, Locale locale);

    public String formatIterable(JCDiagnostic jCDiagnostic, Iterable<?> iterable, Locale locale) {
        StringBuilder sb = new StringBuilder();
        String str = "";
        for (Object obj : iterable) {
            sb.append(str);
            sb.append(formatArgument(jCDiagnostic, obj, locale));
            str = ",";
        }
        return sb.toString();
    }

    @Override // com.sun.tools.javac.api.DiagnosticFormatter
    public String formatKind(JCDiagnostic jCDiagnostic, Locale locale) {
        int i = AnonymousClass2.$SwitchMap$com$sun$tools$javac$util$JCDiagnostic$DiagnosticType[jCDiagnostic.getType().ordinal()];
        if (i == 1) {
            return "";
        }
        if (i == 2) {
            return localize(locale, "compiler.note.note", new Object[0]);
        }
        if (i == 3) {
            return localize(locale, "compiler.warn.warning", new Object[0]);
        }
        if (i == 4) {
            return localize(locale, "compiler.err.error", new Object[0]);
        }
        pe1.a("Unknown diagnostic type: ", jCDiagnostic.getType());
        return null;
    }

    public String formatLintCategory(JCDiagnostic jCDiagnostic, Locale locale) {
        Lint.LintCategory lintCategory = jCDiagnostic.getLintCategory();
        return lintCategory == null ? "" : localize(locale, "compiler.warn.lintOption", lintCategory.option);
    }

    @Override // com.sun.tools.javac.api.DiagnosticFormatter
    public String formatPosition(JCDiagnostic jCDiagnostic, DiagnosticFormatter.PositionKind positionKind, Locale locale) {
        Assert.check(jCDiagnostic.getPosition() != -1);
        return String.valueOf(getPosition(jCDiagnostic, positionKind));
    }

    @Override // com.sun.tools.javac.api.DiagnosticFormatter
    public String formatSource(JCDiagnostic jCDiagnostic, boolean z, Locale locale) {
        JavaFileObject source = jCDiagnostic.getSource();
        if (source == null) {
            j2d.a();
            return null;
        }
        if (z) {
            return source.getName();
        }
        return source instanceof PathFileObject ? ((PathFileObject) source).getShortName() : PathFileObject.getSimpleName(source);
    }

    public String formatSourceLine(JCDiagnostic jCDiagnostic, int i) {
        StringBuilder sb = new StringBuilder();
        DiagnosticSource diagnosticSource = jCDiagnostic.getDiagnosticSource();
        int intPosition = jCDiagnostic.getIntPosition();
        if (jCDiagnostic.getIntPosition() == -1) {
            x1f.a();
            return null;
        }
        String line = diagnosticSource != null ? diagnosticSource.getLine(intPosition) : null;
        if (line == null) {
            return "";
        }
        sb.append(indent(line, i));
        int columnNumber = diagnosticSource.getColumnNumber(intPosition, false);
        if (this.config.isCaretEnabled()) {
            sb.append("\n");
            for (int i2 = 0; i2 < columnNumber - 1; i2++) {
                sb.append(line.charAt(i2) == '\t' ? TlbBase.TAB : " ");
            }
            sb.append(indent("^", i));
        }
        return sb.toString();
    }

    public String formatSubdiagnostic(JCDiagnostic jCDiagnostic, JCDiagnostic jCDiagnostic2, Locale locale) {
        return formatMessage(jCDiagnostic2, locale);
    }

    public List<String> formatSubdiagnostics(JCDiagnostic jCDiagnostic, Locale locale) {
        List<String> listNil = List.nil();
        int multilineLimit = this.config.getMultilineLimit(DiagnosticFormatter.Configuration.MultilineLimit.DEPTH);
        if (multilineLimit != -1 && this.depth >= multilineLimit) {
            return listNil;
        }
        this.depth++;
        try {
            int multilineLimit2 = this.config.getMultilineLimit(DiagnosticFormatter.Configuration.MultilineLimit.LENGTH);
            int i = 0;
            for (JCDiagnostic jCDiagnostic2 : jCDiagnostic.getSubdiagnostics()) {
                if (multilineLimit2 != -1 && i >= multilineLimit2) {
                    break;
                }
                listNil = listNil.append(formatSubdiagnostic(jCDiagnostic, jCDiagnostic2, locale));
                i++;
            }
            this.depth--;
            return listNil;
        } catch (Throwable th) {
            this.depth--;
            throw th;
        }
    }

    public Printer getPrinter() {
        return this.printer;
    }

    public String indent(String str, int i) {
        String strIndentString = indentString(i);
        StringBuilder sb = new StringBuilder();
        String[] strArrSplit = str.split("\n");
        int length = strArrSplit.length;
        String str2 = "";
        int i2 = 0;
        while (i2 < length) {
            String str3 = strArrSplit[i2];
            sb.append(str2);
            sb.append(strIndentString + str3);
            i2++;
            str2 = "\n";
        }
        return sb.toString();
    }

    public String indentString(int i) {
        if (i <= 24) {
            return "                        ".substring(0, i);
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(" ");
        }
        return sb.toString();
    }

    public boolean isRaw() {
        return false;
    }

    public String localize(Locale locale, String str, Object... objArr) {
        return this.messages.getLocalizedString(locale, str, objArr);
    }

    public void setPrinter(Printer printer) {
        this.printer = printer;
    }

    @Override // com.sun.tools.javac.api.DiagnosticFormatter
    public SimpleConfiguration getConfiguration() {
        return this.config;
    }

    public static class SimpleConfiguration implements DiagnosticFormatter.Configuration {
        protected boolean caretEnabled;
        protected Map<DiagnosticFormatter.Configuration.MultilineLimit, Integer> multilineLimits;
        protected EnumSet<DiagnosticFormatter.Configuration.DiagnosticPart> visibleParts;

        /* JADX WARN: Code duplicated, block: B:40:0x00b6 A[Catch: NumberFormatException -> 0x00c2, TRY_LEAVE, TryCatch #0 {NumberFormatException -> 0x00c2, blocks: (B:29:0x0092, B:35:0x009b, B:37:0x00a3, B:38:0x00ae, B:40:0x00b6), top: B:50:0x0092 }] */
        public SimpleConfiguration(Options options, Set<DiagnosticFormatter.Configuration.DiagnosticPart> set) {
            this(set);
            String str = options.get("diags.showSource");
            if (str != null) {
                if (str.equals("true")) {
                    setVisiblePart(DiagnosticFormatter.Configuration.DiagnosticPart.SOURCE, true);
                } else if (str.equals("false")) {
                    setVisiblePart(DiagnosticFormatter.Configuration.DiagnosticPart.SOURCE, false);
                }
            }
            String str2 = options.get("diags.formatterOptions");
            if (str2 != null) {
                java.util.List listAsList = Arrays.asList(str2.split(","));
                if (listAsList.contains("short")) {
                    setVisiblePart(DiagnosticFormatter.Configuration.DiagnosticPart.DETAILS, false);
                    setVisiblePart(DiagnosticFormatter.Configuration.DiagnosticPart.SUBDIAGNOSTICS, false);
                }
                if (listAsList.contains("source")) {
                    setVisiblePart(DiagnosticFormatter.Configuration.DiagnosticPart.SOURCE, true);
                }
                if (listAsList.contains("-source")) {
                    setVisiblePart(DiagnosticFormatter.Configuration.DiagnosticPart.SOURCE, false);
                }
            }
            String str3 = options.get("diags.multilinePolicy");
            if (str3 != null) {
                if (str3.equals("disabled")) {
                    setVisiblePart(DiagnosticFormatter.Configuration.DiagnosticPart.SUBDIAGNOSTICS, false);
                } else if (str3.startsWith("limit:")) {
                    String[] strArrSplit = str3.substring(6).split(":");
                    try {
                        int length = strArrSplit.length;
                        if (length == 1) {
                            if (!strArrSplit[0].equals("*")) {
                                setMultilineLimit(DiagnosticFormatter.Configuration.MultilineLimit.LENGTH, Integer.parseInt(strArrSplit[0]));
                            }
                        } else if (length == 2) {
                            if (!strArrSplit[1].equals("*")) {
                                setMultilineLimit(DiagnosticFormatter.Configuration.MultilineLimit.DEPTH, Integer.parseInt(strArrSplit[1]));
                            }
                            if (!strArrSplit[0].equals("*")) {
                                setMultilineLimit(DiagnosticFormatter.Configuration.MultilineLimit.LENGTH, Integer.parseInt(strArrSplit[0]));
                            }
                        }
                    } catch (NumberFormatException unused) {
                        setMultilineLimit(DiagnosticFormatter.Configuration.MultilineLimit.DEPTH, -1);
                        setMultilineLimit(DiagnosticFormatter.Configuration.MultilineLimit.LENGTH, -1);
                    }
                }
            }
            String str4 = options.get("diags.showCaret");
            if (str4 == null || !str4.equals("false")) {
                setCaretEnabled(true);
            } else {
                setCaretEnabled(false);
            }
        }

        @Override // com.sun.tools.javac.api.DiagnosticFormatter.Configuration
        public int getMultilineLimit(DiagnosticFormatter.Configuration.MultilineLimit multilineLimit) {
            return this.multilineLimits.get(multilineLimit).intValue();
        }

        @Override // com.sun.tools.javac.api.DiagnosticFormatter.Configuration
        public EnumSet<DiagnosticFormatter.Configuration.DiagnosticPart> getVisible() {
            return EnumSet.copyOf((EnumSet) this.visibleParts);
        }

        public boolean isCaretEnabled() {
            return this.caretEnabled;
        }

        public void setCaretEnabled(boolean z) {
            this.caretEnabled = z;
        }

        @Override // com.sun.tools.javac.api.DiagnosticFormatter.Configuration
        public void setMultilineLimit(DiagnosticFormatter.Configuration.MultilineLimit multilineLimit, int i) {
            Map<DiagnosticFormatter.Configuration.MultilineLimit, Integer> map = this.multilineLimits;
            if (i < -1) {
                i = -1;
            }
            map.put(multilineLimit, Integer.valueOf(i));
        }

        @Override // com.sun.tools.javac.api.DiagnosticFormatter.Configuration
        public void setVisible(Set<DiagnosticFormatter.Configuration.DiagnosticPart> set) {
            this.visibleParts = EnumSet.copyOf((Collection) set);
        }

        public void setVisiblePart(DiagnosticFormatter.Configuration.DiagnosticPart diagnosticPart, boolean z) {
            EnumSet<DiagnosticFormatter.Configuration.DiagnosticPart> enumSet = this.visibleParts;
            if (z) {
                enumSet.add(diagnosticPart);
            } else {
                enumSet.remove(diagnosticPart);
            }
        }

        public SimpleConfiguration(Set<DiagnosticFormatter.Configuration.DiagnosticPart> set) {
            this.multilineLimits = new HashMap();
            setVisible(set);
            setMultilineLimit(DiagnosticFormatter.Configuration.MultilineLimit.DEPTH, -1);
            setMultilineLimit(DiagnosticFormatter.Configuration.MultilineLimit.LENGTH, -1);
            setCaretEnabled(true);
        }
    }
}
