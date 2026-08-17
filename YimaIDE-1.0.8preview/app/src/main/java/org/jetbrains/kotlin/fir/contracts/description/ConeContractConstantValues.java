package org.jetbrains.kotlin.fir.contracts.description;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.KtBooleanConstantReference;
import org.jetbrains.kotlin.contracts.description.KtConstantReference;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001d\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u001d\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\tR\u001d\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\tR\u001d\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\t¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/contracts/description/ConeContractConstantValues;", Argument.Delimiters.none, "<init>", "()V", "NULL", "Lorg/jetbrains/kotlin/contracts/description/KtConstantReference;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "getNULL", "()Lorg/jetbrains/kotlin/contracts/description/KtConstantReference;", "WILDCARD", "getWILDCARD", "NOT_NULL", "getNOT_NULL", "TRUE", "getTRUE", "FALSE", "getFALSE", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeContractConstantValues {
    public static final ConeContractConstantValues INSTANCE = new ConeContractConstantValues();
    private static final KtConstantReference<ConeKotlinType, ConeDiagnostic> NULL = new KtConstantReference<>("NULL");
    private static final KtConstantReference<ConeKotlinType, ConeDiagnostic> WILDCARD = new KtConstantReference<>("WILDCARD");
    private static final KtConstantReference<ConeKotlinType, ConeDiagnostic> NOT_NULL = new KtConstantReference<>("NOT_NULL");
    private static final KtConstantReference<ConeKotlinType, ConeDiagnostic> TRUE = new KtBooleanConstantReference("TRUE");
    private static final KtConstantReference<ConeKotlinType, ConeDiagnostic> FALSE = new KtBooleanConstantReference("FALSE");

    private ConeContractConstantValues() {
    }

    public final KtConstantReference<ConeKotlinType, ConeDiagnostic> getFALSE() {
        return FALSE;
    }

    public final KtConstantReference<ConeKotlinType, ConeDiagnostic> getNOT_NULL() {
        return NOT_NULL;
    }

    public final KtConstantReference<ConeKotlinType, ConeDiagnostic> getNULL() {
        return NULL;
    }

    public final KtConstantReference<ConeKotlinType, ConeDiagnostic> getTRUE() {
        return TRUE;
    }

    public final KtConstantReference<ConeKotlinType, ConeDiagnostic> getWILDCARD() {
        return WILDCARD;
    }
}
