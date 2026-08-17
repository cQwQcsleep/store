package org.jetbrains.kotlin.name;

import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.library.abi.AbiCompoundName;
import org.jetbrains.kotlin.library.abi.AbiQualifiedName;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0007H\u0007J\u0010\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u0007H\u0007J\u0010\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0007H\u0003J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0007H\u0007J\u0010\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0012H\u0007J\u0010\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0012H\u0007J\u0010\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u0007H\u0007J\u0018\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u0007H\u0002J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\f\u001a\u00020\u0012H\u0007J\u0010\u0010\u001c\u001a\u00020\u00122\u0006\u0010\u001d\u001a\u00020\u001eH\u0007J\u0010\u0010\u001f\u001a\u00020\u00122\u0006\u0010 \u001a\u00020\u0012H\u0007J\u0010\u0010!\u001a\u00020\u00122\u0006\u0010\u001d\u001a\u00020\u001eH\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0006\u001a\u00020\u00078\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\b\u0010\u0003\u001a\u0004\b\t\u0010\n¨\u0006\""}, d2 = {"Lorg/jetbrains/kotlin/name/NameUtils;", "", "<init>", "()V", "SANITIZE_AS_JAVA_INVALID_CHARACTERS", "Lkotlin/text/Regex;", "CONTEXT_RECEIVER_PREFIX", "", "getCONTEXT_RECEIVER_PREFIX$annotations", "getCONTEXT_RECEIVER_PREFIX", "()Ljava/lang/String;", "sanitizeAsJavaIdentifier", "name", "getPackagePartClassNamePrefix", "shortFileName", "capitalizeAsJavaClassName", "str", "getScriptNameForFile", "Lorg/jetbrains/kotlin/name/Name;", "filePath", "getScriptTargetClassName", "originalName", "getSnippetTargetClassName", "fileName", "getSnippetOrScriptTargetClassName", "prefix", "hasName", "", "delegateFieldName", "index", "", "propertyDelegateName", "propertyName", "contextReceiverName", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class NameUtils {
    public static final NameUtils INSTANCE = new NameUtils();
    private static final Regex SANITIZE_AS_JAVA_INVALID_CHARACTERS = new Regex("[^\\p{L}\\p{Digit}]");
    private static final String CONTEXT_RECEIVER_PREFIX = "$context_receiver";

    private NameUtils() {
    }

    @JvmStatic
    private static final String capitalizeAsJavaClassName(String str) {
        if (!Character.isJavaIdentifierStart(str.charAt(0))) {
            return "_".concat(str);
        }
        StringBuilder sb = new StringBuilder();
        String strValueOf = String.valueOf(str.charAt(0));
        strValueOf.getClass();
        String upperCase = strValueOf.toUpperCase(Locale.ROOT);
        upperCase.getClass();
        sb.append(upperCase);
        sb.append(str.substring(1));
        return sb.toString();
    }

    @JvmStatic
    public static final Name contextReceiverName(int index) {
        Name nameIdentifier = Name.identifier(CONTEXT_RECEIVER_PREFIX + '_' + index);
        nameIdentifier.getClass();
        return nameIdentifier;
    }

    @JvmStatic
    public static final Name delegateFieldName(int index) {
        Name nameIdentifier = Name.identifier("$$delegate_" + index);
        nameIdentifier.getClass();
        return nameIdentifier;
    }

    public static final String getCONTEXT_RECEIVER_PREFIX() {
        return CONTEXT_RECEIVER_PREFIX;
    }

    @JvmStatic
    public static /* synthetic */ void getCONTEXT_RECEIVER_PREFIX$annotations() {
    }

    @JvmStatic
    public static final String getPackagePartClassNamePrefix(String shortFileName) {
        shortFileName.getClass();
        return shortFileName.length() == 0 ? "_" : capitalizeAsJavaClassName(sanitizeAsJavaIdentifier(shortFileName));
    }

    @JvmStatic
    public static final Name getScriptNameForFile(String filePath) {
        filePath.getClass();
        Name nameIdentifier = Name.identifier(getPackagePartClassNamePrefix(StringsKt.substringBeforeLast$default(StringsKt.substringAfterLast$default(filePath, AbiQualifiedName.SEPARATOR, (String) null, 2, (Object) null), AbiCompoundName.SEPARATOR, (String) null, 2, (Object) null)));
        nameIdentifier.getClass();
        return nameIdentifier;
    }

    @JvmStatic
    public static final Name getScriptTargetClassName(Name originalName) {
        originalName.getClass();
        return INSTANCE.getSnippetOrScriptTargetClassName(originalName, "script-");
    }

    private final Name getSnippetOrScriptTargetClassName(Name originalName, String prefix) {
        if (!originalName.isSpecial()) {
            return originalName;
        }
        String strAsStringStripSpecialMarkers = originalName.asStringStripSpecialMarkers();
        strAsStringStripSpecialMarkers.getClass();
        return getScriptNameForFile(StringsKt.removePrefix(strAsStringStripSpecialMarkers, prefix));
    }

    @JvmStatic
    public static final Name getSnippetTargetClassName(String fileName) {
        fileName.getClass();
        Name nameSpecial = Name.special("<" + fileName + '>');
        nameSpecial.getClass();
        return getSnippetTargetClassName(nameSpecial);
    }

    @JvmStatic
    public static final boolean hasName(Name name) {
        name.getClass();
        return (Intrinsics.areEqual(name, SpecialNames.NO_NAME_PROVIDED) || Intrinsics.areEqual(name, SpecialNames.ANONYMOUS)) ? false : true;
    }

    @JvmStatic
    public static final Name propertyDelegateName(Name propertyName) {
        propertyName.getClass();
        Name nameIdentifier = Name.identifier(propertyName.asString() + "$delegate");
        nameIdentifier.getClass();
        return nameIdentifier;
    }

    @JvmStatic
    public static final String sanitizeAsJavaIdentifier(String name) {
        name.getClass();
        return SANITIZE_AS_JAVA_INVALID_CHARACTERS.replace(name, "_");
    }

    @JvmStatic
    public static final Name getSnippetTargetClassName(Name originalName) {
        originalName.getClass();
        return INSTANCE.getSnippetOrScriptTargetClassName(originalName, "snippet-");
    }
}
