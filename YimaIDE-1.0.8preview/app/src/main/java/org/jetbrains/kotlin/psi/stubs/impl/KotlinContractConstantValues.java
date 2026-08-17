package org.jetbrains.kotlin.psi.stubs.impl;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.KtBooleanConstantReference;
import org.jetbrains.kotlin.contracts.description.KtConstantReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001f\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001f\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u001f\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\tR\u001f\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001f\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/psi/stubs/impl/KotlinContractConstantValues;", Argument.Delimiters.none, "<init>", "()V", "NULL", "Lorg/jetbrains/kotlin/contracts/description/KtConstantReference;", "Lorg/jetbrains/kotlin/psi/stubs/impl/KotlinTypeBean;", Argument.Delimiters.none, "getNULL", "()Lorg/jetbrains/kotlin/contracts/description/KtConstantReference;", "WILDCARD", "getWILDCARD", "NOT_NULL", "getNOT_NULL", "TRUE", "Lorg/jetbrains/kotlin/contracts/description/KtBooleanConstantReference;", "getTRUE", "()Lorg/jetbrains/kotlin/contracts/description/KtBooleanConstantReference;", "FALSE", "getFALSE", "org.jetbrains.kotlin:psi-impl"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class KotlinContractConstantValues {
    public static final KotlinContractConstantValues INSTANCE = new KotlinContractConstantValues();
    private static final KtConstantReference NULL = new KtConstantReference("NULL");
    private static final KtConstantReference WILDCARD = new KtConstantReference("WILDCARD");
    private static final KtConstantReference NOT_NULL = new KtConstantReference("NOT_NULL");
    private static final KtBooleanConstantReference TRUE = new KtBooleanConstantReference("TRUE");
    private static final KtBooleanConstantReference FALSE = new KtBooleanConstantReference("FALSE");

    private KotlinContractConstantValues() {
    }

    public final KtBooleanConstantReference getFALSE() {
        return FALSE;
    }

    public final KtConstantReference getNOT_NULL() {
        return NOT_NULL;
    }

    public final KtConstantReference getNULL() {
        return NULL;
    }

    public final KtBooleanConstantReference getTRUE() {
        return TRUE;
    }

    public final KtConstantReference getWILDCARD() {
        return WILDCARD;
    }
}
