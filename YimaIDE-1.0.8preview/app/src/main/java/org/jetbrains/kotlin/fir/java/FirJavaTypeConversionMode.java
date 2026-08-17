package org.jetbrains.kotlin.fir.java;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\n\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\rj\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/FirJavaTypeConversionMode;", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;I)V", "DEFAULT", "ANNOTATION_MEMBER", "ANNOTATION_CONSTRUCTOR_PARAMETER", "SUPERTYPE", "TYPE_PARAMETER_BOUND_FIRST_ROUND", "TYPE_PARAMETER_BOUND_AFTER_FIRST_ROUND", "insideAnnotation", Argument.Delimiters.none, "getInsideAnnotation", "()Z", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public enum FirJavaTypeConversionMode {
    DEFAULT,
    ANNOTATION_MEMBER,
    ANNOTATION_CONSTRUCTOR_PARAMETER,
    SUPERTYPE,
    TYPE_PARAMETER_BOUND_FIRST_ROUND,
    TYPE_PARAMETER_BOUND_AFTER_FIRST_ROUND;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    public static EnumEntries<FirJavaTypeConversionMode> getEntries() {
        return $ENTRIES;
    }

    public final boolean getInsideAnnotation() {
        return this == ANNOTATION_MEMBER || this == ANNOTATION_CONSTRUCTOR_PARAMETER;
    }
}
