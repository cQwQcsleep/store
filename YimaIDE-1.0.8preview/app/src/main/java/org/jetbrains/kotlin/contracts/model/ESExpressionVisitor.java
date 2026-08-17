package org.jetbrains.kotlin.contracts.model;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.model.structure.ESAnd;
import org.jetbrains.kotlin.contracts.model.structure.ESConstant;
import org.jetbrains.kotlin.contracts.model.structure.ESEqual;
import org.jetbrains.kotlin.contracts.model.structure.ESIs;
import org.jetbrains.kotlin.contracts.model.structure.ESNot;
import org.jetbrains.kotlin.contracts.model.structure.ESOr;
import org.jetbrains.kotlin.contracts.model.structure.ESReceiver;
import org.jetbrains.kotlin.contracts.model.structure.ESVariable;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002J\u0015\u0010\u0003\u001a\u00028\u00002\u0006\u0010\u0004\u001a\u00020\u0005H&¢\u0006\u0002\u0010\u0006J\u0015\u0010\u0007\u001a\u00028\u00002\u0006\u0010\b\u001a\u00020\tH&¢\u0006\u0002\u0010\nJ\u0015\u0010\u000b\u001a\u00028\u00002\u0006\u0010\f\u001a\u00020\rH&¢\u0006\u0002\u0010\u000eJ\u0015\u0010\u000f\u001a\u00028\u00002\u0006\u0010\u0010\u001a\u00020\u0011H&¢\u0006\u0002\u0010\u0012J\u0015\u0010\u0013\u001a\u00028\u00002\u0006\u0010\u0014\u001a\u00020\u0015H&¢\u0006\u0002\u0010\u0016J\u0015\u0010\u0017\u001a\u00028\u00002\u0006\u0010\u0018\u001a\u00020\u0019H&¢\u0006\u0002\u0010\u001aJ\u0015\u0010\u001b\u001a\u00028\u00002\u0006\u0010\u001c\u001a\u00020\u001dH&¢\u0006\u0002\u0010\u001eJ\u0015\u0010\u001f\u001a\u00028\u00002\u0006\u0010 \u001a\u00020!H&¢\u0006\u0002\u0010\"J\u0015\u0010#\u001a\u00028\u00002\u0006\u0010$\u001a\u00020%H&¢\u0006\u0002\u0010&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006'À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/contracts/model/ESExpressionVisitor;", "T", Argument.Delimiters.none, "visitIs", "isOperator", "Lorg/jetbrains/kotlin/contracts/model/structure/ESIs;", "(Lorg/jetbrains/kotlin/contracts/model/structure/ESIs;)Ljava/lang/Object;", "visitEqual", "equal", "Lorg/jetbrains/kotlin/contracts/model/structure/ESEqual;", "(Lorg/jetbrains/kotlin/contracts/model/structure/ESEqual;)Ljava/lang/Object;", "visitAnd", "and", "Lorg/jetbrains/kotlin/contracts/model/structure/ESAnd;", "(Lorg/jetbrains/kotlin/contracts/model/structure/ESAnd;)Ljava/lang/Object;", "visitNot", "not", "Lorg/jetbrains/kotlin/contracts/model/structure/ESNot;", "(Lorg/jetbrains/kotlin/contracts/model/structure/ESNot;)Ljava/lang/Object;", "visitOr", "or", "Lorg/jetbrains/kotlin/contracts/model/structure/ESOr;", "(Lorg/jetbrains/kotlin/contracts/model/structure/ESOr;)Ljava/lang/Object;", "visitVariable", "esVariable", "Lorg/jetbrains/kotlin/contracts/model/structure/ESVariable;", "(Lorg/jetbrains/kotlin/contracts/model/structure/ESVariable;)Ljava/lang/Object;", "visitConstant", "esConstant", "Lorg/jetbrains/kotlin/contracts/model/structure/ESConstant;", "(Lorg/jetbrains/kotlin/contracts/model/structure/ESConstant;)Ljava/lang/Object;", "visitReceiver", "esReceiver", "Lorg/jetbrains/kotlin/contracts/model/structure/ESReceiver;", "(Lorg/jetbrains/kotlin/contracts/model/structure/ESReceiver;)Ljava/lang/Object;", "visitLambda", "lambda", "Lorg/jetbrains/kotlin/contracts/model/ESValue;", "(Lorg/jetbrains/kotlin/contracts/model/ESValue;)Ljava/lang/Object;", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface ESExpressionVisitor<T> {
    T visitAnd(ESAnd and);

    T visitConstant(ESConstant esConstant);

    T visitEqual(ESEqual equal);

    T visitIs(ESIs isOperator);

    T visitLambda(ESValue lambda);

    T visitNot(ESNot not);

    T visitOr(ESOr or);

    T visitReceiver(ESReceiver esReceiver);

    T visitVariable(ESVariable esVariable);
}
