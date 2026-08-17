package org.jetbrains.kotlin.resolve;

import javax.xml.transform.OutputKeys;
import kotlin.Metadata;
import kotlin.text.Regex;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u000e\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0011\u0010\u0010\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000bR\u0011\u0010\u0012\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000bR\u0011\u0010\u0014\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/resolve/RequireKotlinConstants;", "", "<init>", "()V", "FQ_NAME", "Lorg/jetbrains/kotlin/name/FqName;", "getFQ_NAME", "()Lorg/jetbrains/kotlin/name/FqName;", "VERSION", "Lorg/jetbrains/kotlin/name/Name;", "getVERSION", "()Lorg/jetbrains/kotlin/name/Name;", "MESSAGE", "getMESSAGE", "LEVEL", "getLEVEL", "VERSION_KIND", "getVERSION_KIND", "ERROR_CODE", "getERROR_CODE", "VERSION_REGEX", "Lkotlin/text/Regex;", "getVERSION_REGEX", "()Lkotlin/text/Regex;", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class RequireKotlinConstants {
    private static final Name ERROR_CODE;
    private static final Name LEVEL;
    private static final Name MESSAGE;
    private static final Name VERSION;
    private static final Name VERSION_KIND;
    private static final Regex VERSION_REGEX;
    public static final RequireKotlinConstants INSTANCE = new RequireKotlinConstants();
    private static final FqName FQ_NAME = new FqName("kotlin.internal.RequireKotlin");

    static {
        Name nameIdentifier = Name.identifier(OutputKeys.VERSION);
        nameIdentifier.getClass();
        VERSION = nameIdentifier;
        Name nameIdentifier2 = Name.identifier("message");
        nameIdentifier2.getClass();
        MESSAGE = nameIdentifier2;
        Name nameIdentifier3 = Name.identifier("level");
        nameIdentifier3.getClass();
        LEVEL = nameIdentifier3;
        Name nameIdentifier4 = Name.identifier("versionKind");
        nameIdentifier4.getClass();
        VERSION_KIND = nameIdentifier4;
        Name nameIdentifier5 = Name.identifier("errorCode");
        nameIdentifier5.getClass();
        ERROR_CODE = nameIdentifier5;
        VERSION_REGEX = new Regex("(0|[1-9][0-9]*)\\.(0|[1-9][0-9]*)(\\.(0|[1-9][0-9]*))?");
    }

    private RequireKotlinConstants() {
    }

    public final Name getERROR_CODE() {
        return ERROR_CODE;
    }

    public final FqName getFQ_NAME() {
        return FQ_NAME;
    }

    public final Name getLEVEL() {
        return LEVEL;
    }

    public final Name getMESSAGE() {
        return MESSAGE;
    }

    public final Name getVERSION() {
        return VERSION;
    }

    public final Name getVERSION_KIND() {
        return VERSION_KIND;
    }

    public final Regex getVERSION_REGEX() {
        return VERSION_REGEX;
    }
}
