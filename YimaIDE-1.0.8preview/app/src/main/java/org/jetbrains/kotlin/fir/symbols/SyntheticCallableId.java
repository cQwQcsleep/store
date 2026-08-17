package org.jetbrains.kotlin.fir.symbols;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u000f\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\u0011\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\fR\u0011\u0010\u0013\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\fR\u0011\u0010\u0015\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\fR\u0011\u0010\u0017\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\f¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/fir/symbols/SyntheticCallableId;", Argument.Delimiters.none, "<init>", "()V", "syntheticPackageName", "Lorg/jetbrains/kotlin/name/FqName;", "syntheticCallableId", "Lorg/jetbrains/kotlin/name/CallableId;", ModuleXmlParser.NAME, Argument.Delimiters.none, "WHEN", "getWHEN", "()Lorg/jetbrains/kotlin/name/CallableId;", "TRY", "getTRY", "CHECK_NOT_NULL", "getCHECK_NOT_NULL", "ELVIS", "getELVIS", "EQUALITY", "getEQUALITY", "ID", "getID", "ACCEPT_SPECIFIC_TYPE", "getACCEPT_SPECIFIC_TYPE", "org.jetbrains.kotlin:cones"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SyntheticCallableId {
    private static final CallableId ACCEPT_SPECIFIC_TYPE;
    private static final CallableId CHECK_NOT_NULL;
    private static final CallableId ELVIS;
    private static final CallableId EQUALITY;
    private static final CallableId ID;
    public static final SyntheticCallableId INSTANCE;
    private static final CallableId TRY;
    private static final CallableId WHEN;
    private static final FqName syntheticPackageName;

    static {
        SyntheticCallableId syntheticCallableId = new SyntheticCallableId();
        INSTANCE = syntheticCallableId;
        syntheticPackageName = new FqName("_synthetic");
        WHEN = syntheticCallableId.syntheticCallableId("WHEN_CALL");
        TRY = syntheticCallableId.syntheticCallableId("TRY_CALL");
        CHECK_NOT_NULL = syntheticCallableId.syntheticCallableId("CHECK_NOT_NULL_CALL");
        ELVIS = syntheticCallableId.syntheticCallableId("ELVIS_CALL");
        EQUALITY = syntheticCallableId.syntheticCallableId("EQUALITY_CALL");
        ID = syntheticCallableId.syntheticCallableId("ID_CALL");
        ACCEPT_SPECIFIC_TYPE = syntheticCallableId.syntheticCallableId("ACCEPT_SPECIFIC_TYPE_CALL");
    }

    private SyntheticCallableId() {
    }

    private final CallableId syntheticCallableId(String name) {
        FqName fqName = syntheticPackageName;
        Name nameIdentifier = Name.identifier(name);
        nameIdentifier.getClass();
        return new CallableId(fqName, nameIdentifier);
    }

    public final CallableId getACCEPT_SPECIFIC_TYPE() {
        return ACCEPT_SPECIFIC_TYPE;
    }

    public final CallableId getCHECK_NOT_NULL() {
        return CHECK_NOT_NULL;
    }

    public final CallableId getELVIS() {
        return ELVIS;
    }

    public final CallableId getEQUALITY() {
        return EQUALITY;
    }

    public final CallableId getID() {
        return ID;
    }

    public final CallableId getTRY() {
        return TRY;
    }

    public final CallableId getWHEN() {
        return WHEN;
    }
}
