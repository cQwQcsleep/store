package org.jetbrains.kotlin.cli.common.arguments;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.sequences.SequencesKt;
import kotlin.text.CharsKt;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.PreprocessCommandLineArgumentsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.analysis.collectors.AbstractDiagnosticCollector;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000J\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a(\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u001a\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\u00072\u0006\u0010\r\u001a\u00020\u0001H\u0007\u001a\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007*\u00020\u000f2\u0006\u0010\t\u001a\u00020\u000bH\u0002\u001a\u000e\u0010\u0010\u001a\u0004\u0018\u00010\u0001*\u00020\u0011H\u0002\u001a \u0010\u0012\u001a\u00020\u0013*\u00020\u00112\n\u0010\u0014\u001a\u00060\u0015j\u0002`\u00162\u0006\u0010\u0017\u001a\u00020\u0003H\u0002\u001a\u0013\u0010\u0018\u001a\u0004\u0018\u00010\u0003*\u00020\u0011H\u0002¢\u0006\u0002\u0010\u0019\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u0018\u0010\u001a\u001a\u00020\u0001*\u00020\u00018BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c\"\u0018\u0010\u001d\u001a\u00020\u001e*\u00020\u00018BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001f¨\u0006 "}, d2 = {"ARGFILE_ARGUMENT", Argument.Delimiters.none, "SINGLE_QUOTE", Argument.Delimiters.none, "DOUBLE_QUOTE", "BACKSLASH", "preprocessCommandLineArguments", Argument.Delimiters.none, "args", AbstractDiagnosticCollector.SUPPRESS_ALL_ERRORS, "Lkotlin/Lazy;", "Lorg/jetbrains/kotlin/cli/common/arguments/ArgumentParseErrors;", "readArgumentsFromArgFile", "content", "expand", "Ljava/io/File;", "parseNextArgument", "Ljava/io/Reader;", "consumeRestOfQuotedSequence", Argument.Delimiters.none, "sb", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "quote", "nextChar", "(Ljava/io/Reader;)Ljava/lang/Character;", "argfilePath", "getArgfilePath", "(Ljava/lang/String;)Ljava/lang/String;", "isArgfileArgument", Argument.Delimiters.none, "(Ljava/lang/String;)Z", "org.jetbrains.kotlin:cli-base"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PreprocessCommandLineArgumentsKt {
    public static final String ARGFILE_ARGUMENT = "@";

    public static String b(StringReader stringReader) {
        return parseNextArgument(stringReader);
    }

    private static final void consumeRestOfQuotedSequence(Reader reader, StringBuilder sb, char c) {
        Character chNextChar = nextChar(reader);
        while (chNextChar != null && chNextChar.charValue() != c) {
            if (chNextChar.charValue() != '\\' || (chNextChar = nextChar(reader)) != null) {
                sb.append(chNextChar.charValue());
            }
            chNextChar = nextChar(reader);
        }
    }

    private static final List<String> expand(File file, ArgumentParseErrors argumentParseErrors) {
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), Charsets.UTF_8), 8192);
            try {
                List<String> list = SequencesKt.toList(SequencesKt.generateSequence(new Function0() { // from class: g7b
                    public final Object invoke() {
                        return PreprocessCommandLineArgumentsKt.parseNextArgument(bufferedReader);
                    }
                }));
                CloseableKt.closeFinally(bufferedReader, (Throwable) null);
                return list;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(bufferedReader, th);
                    throw th2;
                }
            }
        } catch (FileNotFoundException unused) {
            argumentParseErrors.getArgfileErrors().add("Argfile not found: " + file.getAbsolutePath());
            return CollectionsKt.emptyList();
        } catch (IOException e) {
            argumentParseErrors.getArgfileErrors().add("Error while reading argfile: " + e);
            return CollectionsKt.emptyList();
        }
    }

    private static final String getArgfilePath(String str) {
        return StringsKt.removePrefix(str, ARGFILE_ARGUMENT);
    }

    private static final boolean isArgfileArgument(String str) {
        return StringsKt.startsWith$default(str, ARGFILE_ARGUMENT, false, 2, (Object) null);
    }

    private static final Character nextChar(Reader reader) {
        Integer numValueOf = Integer.valueOf(reader.read());
        if (numValueOf.intValue() == -1) {
            numValueOf = null;
        }
        if (numValueOf != null) {
            return Character.valueOf((char) numValueOf.intValue());
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String parseNextArgument(Reader reader) {
        StringBuilder sb = new StringBuilder();
        Character chNextChar = nextChar(reader);
        while (chNextChar != null && CharsKt.isWhitespace(chNextChar.charValue())) {
            chNextChar = nextChar(reader);
        }
        while (chNextChar != null && !CharsKt.isWhitespace(chNextChar.charValue())) {
            if (chNextChar.charValue() == '\"' || chNextChar.charValue() == '\'') {
                consumeRestOfQuotedSequence(reader, sb, chNextChar.charValue());
                return sb.toString();
            }
            sb.append(chNextChar.charValue());
            chNextChar = nextChar(reader);
        }
        String string = sb.toString();
        if (string.length() > 0) {
            return string;
        }
        return null;
    }

    public static final List<String> preprocessCommandLineArguments(List<String> list, Lazy<ArgumentParseErrors> lazy) {
        list.getClass();
        lazy.getClass();
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            CollectionsKt.addAll(arrayList, isArgfileArgument(str) ? expand(new File(getArgfilePath(str)), (ArgumentParseErrors) lazy.getValue()) : CollectionsKt.listOf(str));
        }
        return arrayList;
    }

    public static final List<String> readArgumentsFromArgFile(String str) {
        str.getClass();
        final StringReader stringReader = new StringReader(str);
        return SequencesKt.toList(SequencesKt.generateSequence(new Function0() { // from class: h7b
            public final Object invoke() {
                return PreprocessCommandLineArgumentsKt.b(stringReader);
            }
        }));
    }
}
