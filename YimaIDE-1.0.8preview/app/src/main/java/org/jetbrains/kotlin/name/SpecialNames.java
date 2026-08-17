package org.jetbrains.kotlin.name;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u001cH\u0007J\u0010\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020\u001cH\u0007J\u0010\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u0005H\u0007J\u0012\u0010#\u001a\u00020\u00052\b\u0010\"\u001a\u0004\u0018\u00010\u0005H\u0007J\u0012\u0010#\u001a\u00020\u00052\b\u0010\"\u001a\u0004\u0018\u00010\nH\u0007J\u000e\u0010$\u001a\u00020!2\u0006\u0010\"\u001a\u00020\u0005R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lorg/jetbrains/kotlin/name/SpecialNames;", Argument.Delimiters.none, "<init>", "()V", "NO_NAME_PROVIDED", "Lorg/jetbrains/kotlin/name/Name;", "ROOT_PACKAGE", "DEFAULT_NAME_FOR_COMPANION_OBJECT", "SAFE_IDENTIFIER_FOR_NO_NAME", "ANONYMOUS_STRING", Argument.Delimiters.none, "ANONYMOUS", "ANONYMOUS_FQ_NAME", "Lorg/jetbrains/kotlin/name/FqName;", "UNARY", "THIS", "INIT", "WHEN_SUBJECT", "ITERATOR", "DESTRUCT", "LOCAL", "UNDERSCORE_FOR_UNUSED_VAR", "IMPLICIT_SET_PARAMETER", "ARRAY", "RECEIVER", "ENUM_GET_ENTRIES", "subscribeOperatorIndex", "idx", Argument.Delimiters.none, "ANONYMOUS_PARAMETER_NAME_PREFIX", "anonymousParameterName", "index", "isAnonymousParameterName", Argument.Delimiters.none, ModuleXmlParser.NAME, "safeIdentifier", "isSafeIdentifier", "org.jetbrains.kotlin:names"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SpecialNames {
    public static final Name ANONYMOUS;
    public static final FqName ANONYMOUS_FQ_NAME;
    public static final String ANONYMOUS_STRING = "<anonymous>";
    public static final Name ARRAY;
    public static final Name DEFAULT_NAME_FOR_COMPANION_OBJECT;
    public static final Name DESTRUCT;
    public static final Name ENUM_GET_ENTRIES;
    public static final Name IMPLICIT_SET_PARAMETER;
    public static final Name INIT;
    public static final SpecialNames INSTANCE = new SpecialNames();
    public static final Name ITERATOR;
    public static final Name LOCAL;
    public static final Name NO_NAME_PROVIDED;
    public static final Name RECEIVER;
    public static final Name ROOT_PACKAGE;
    public static final Name SAFE_IDENTIFIER_FOR_NO_NAME;
    public static final Name THIS;
    public static final Name UNARY;
    public static final Name UNDERSCORE_FOR_UNUSED_VAR;
    public static final Name WHEN_SUBJECT;

    static {
        Name nameSpecial = Name.special("<no name provided>");
        nameSpecial.getClass();
        NO_NAME_PROVIDED = nameSpecial;
        Name nameSpecial2 = Name.special("<root package>");
        nameSpecial2.getClass();
        ROOT_PACKAGE = nameSpecial2;
        Name nameIdentifier = Name.identifier("Companion");
        nameIdentifier.getClass();
        DEFAULT_NAME_FOR_COMPANION_OBJECT = nameIdentifier;
        Name nameIdentifier2 = Name.identifier("no_name_in_PSI_3d19d79d_1ba9_4cd0_b7f5_b46aa3cd5d40");
        nameIdentifier2.getClass();
        SAFE_IDENTIFIER_FOR_NO_NAME = nameIdentifier2;
        Name nameSpecial3 = Name.special(ANONYMOUS_STRING);
        nameSpecial3.getClass();
        ANONYMOUS = nameSpecial3;
        FqName.Companion companion = FqName.Companion;
        Name nameSpecial4 = Name.special(ANONYMOUS_STRING);
        nameSpecial4.getClass();
        ANONYMOUS_FQ_NAME = companion.topLevel(nameSpecial4);
        Name nameSpecial5 = Name.special("<unary>");
        nameSpecial5.getClass();
        UNARY = nameSpecial5;
        Name nameSpecial6 = Name.special("<this>");
        nameSpecial6.getClass();
        THIS = nameSpecial6;
        Name nameSpecial7 = Name.special("<init>");
        nameSpecial7.getClass();
        INIT = nameSpecial7;
        Name nameSpecial8 = Name.special("<when-subject>");
        nameSpecial8.getClass();
        WHEN_SUBJECT = nameSpecial8;
        Name nameSpecial9 = Name.special("<iterator>");
        nameSpecial9.getClass();
        ITERATOR = nameSpecial9;
        Name nameSpecial10 = Name.special("<destruct>");
        nameSpecial10.getClass();
        DESTRUCT = nameSpecial10;
        Name nameSpecial11 = Name.special("<local>");
        nameSpecial11.getClass();
        LOCAL = nameSpecial11;
        Name nameSpecial12 = Name.special("<unused var>");
        nameSpecial12.getClass();
        UNDERSCORE_FOR_UNUSED_VAR = nameSpecial12;
        Name nameSpecial13 = Name.special("<set-?>");
        nameSpecial13.getClass();
        IMPLICIT_SET_PARAMETER = nameSpecial13;
        Name nameSpecial14 = Name.special("<array>");
        nameSpecial14.getClass();
        ARRAY = nameSpecial14;
        Name nameSpecial15 = Name.special("<receiver>");
        nameSpecial15.getClass();
        RECEIVER = nameSpecial15;
        Name nameSpecial16 = Name.special("<get-entries>");
        nameSpecial16.getClass();
        ENUM_GET_ENTRIES = nameSpecial16;
    }

    private SpecialNames() {
    }

    @JvmStatic
    public static final Name anonymousParameterName(int index) {
        Name nameSpecial = Name.special("<anonymous parameter " + index + '>');
        nameSpecial.getClass();
        return nameSpecial;
    }

    @JvmStatic
    public static final boolean isAnonymousParameterName(Name name) {
        name.getClass();
        if (name.isSpecial()) {
            String strAsStringStripSpecialMarkers = name.asStringStripSpecialMarkers();
            strAsStringStripSpecialMarkers.getClass();
            if (StringsKt.startsWith$default(strAsStringStripSpecialMarkers, "anonymous parameter", false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }

    @JvmStatic
    public static final Name safeIdentifier(String name) {
        return safeIdentifier(name == null ? null : Name.identifier(name));
    }

    @JvmStatic
    public static final Name subscribeOperatorIndex(int idx) {
        if (idx < 0) {
            v1f.a("Index should be non-negative, but was ", idx);
            return null;
        }
        Name nameSpecial = Name.special("<index_" + idx + '>');
        nameSpecial.getClass();
        return nameSpecial;
    }

    public final boolean isSafeIdentifier(Name name) {
        name.getClass();
        String strAsString = name.asString();
        strAsString.getClass();
        return strAsString.length() > 0 && !name.isSpecial();
    }

    @JvmStatic
    public static final Name safeIdentifier(Name name) {
        return (name == null || name.isSpecial()) ? SAFE_IDENTIFIER_FOR_NO_NAME : name;
    }
}
