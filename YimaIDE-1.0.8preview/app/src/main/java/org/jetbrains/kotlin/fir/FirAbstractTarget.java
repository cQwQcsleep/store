package org.jetbrains.kotlin.fir;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirTargetElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0002\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0011\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0015\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u000eR\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0018\u0010\n\u001a\u00028\u0000X¤\u000e¢\u0006\f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00028\u00008F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\f¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/fir/FirAbstractTarget;", "E", "Lorg/jetbrains/kotlin/fir/FirTargetElement;", "Lorg/jetbrains/kotlin/fir/FirTarget;", "labelName", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;)V", "getLabelName", "()Ljava/lang/String;", "_labeledElement", "get_labeledElement", "()Lorg/jetbrains/kotlin/fir/FirTargetElement;", "set_labeledElement", "(Lorg/jetbrains/kotlin/fir/FirTargetElement;)V", "labeledElement", "getLabeledElement", "bind", Argument.Delimiters.none, "element", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirAbstractTarget<E extends FirTargetElement> implements FirTarget<E> {
    private final String labelName;

    public FirAbstractTarget(String str) {
        this.labelName = str;
    }

    @Override // org.jetbrains.kotlin.fir.FirTarget
    public void bind(E element) {
        element.getClass();
        set_labeledElement(element);
    }

    @Override // org.jetbrains.kotlin.fir.FirTarget
    public String getLabelName() {
        return this.labelName;
    }

    @Override // org.jetbrains.kotlin.fir.FirTarget
    public final E getLabeledElement() {
        return (E) get_labeledElement();
    }

    public abstract E get_labeledElement();

    public abstract void set_labeledElement(E e);
}
