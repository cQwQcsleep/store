package org.jetbrains.kotlin.resolve.multiplatform;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002:\u0002\f\rB\t\b\u0004¢\u0006\u0004\b\u0003\u0010\u0004J&\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\t0\u0000\"\u0004\b\u0001\u0010\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u0002H\t0\u000bR\u0012\u0010\u0005\u001a\u00028\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\u0082\u0001\u0002\u000e\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualAnnotationsIncompatibilityType;", "A", "", "<init>", "()V", "expectAnnotation", "getExpectAnnotation", "()Ljava/lang/Object;", "mapAnnotationType", "A2", "mapper", "Lkotlin/Function1;", "MissingOnActual", "DifferentOnActual", "Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualAnnotationsIncompatibilityType$DifferentOnActual;", "Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualAnnotationsIncompatibilityType$MissingOnActual;", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public abstract class ExpectActualAnnotationsIncompatibilityType<A> {

    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00028\u0001\u0012\u0006\u0010\u0004\u001a\u00028\u0001¢\u0006\u0004\b\u0005\u0010\u0006R\u0016\u0010\u0003\u001a\u00028\u0001X\u0096\u0004¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u00028\u0001¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\n\u0010\b¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualAnnotationsIncompatibilityType$DifferentOnActual;", "A", "Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualAnnotationsIncompatibilityType;", "expectAnnotation", "actualAnnotation", "<init>", "(Ljava/lang/Object;Ljava/lang/Object;)V", "getExpectAnnotation", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getActualAnnotation", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
    public static final class DifferentOnActual<A> extends ExpectActualAnnotationsIncompatibilityType<A> {
        private final A actualAnnotation;
        private final A expectAnnotation;

        public DifferentOnActual(A a, A a2) {
            super(null);
            this.expectAnnotation = a;
            this.actualAnnotation = a2;
        }

        public final A getActualAnnotation() {
            return this.actualAnnotation;
        }

        @Override // org.jetbrains.kotlin.resolve.multiplatform.ExpectActualAnnotationsIncompatibilityType
        public A getExpectAnnotation() {
            return this.expectAnnotation;
        }
    }

    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00028\u0001¢\u0006\u0004\b\u0004\u0010\u0005R\u0016\u0010\u0003\u001a\u00028\u0001X\u0096\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualAnnotationsIncompatibilityType$MissingOnActual;", "A", "Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualAnnotationsIncompatibilityType;", "expectAnnotation", "<init>", "(Ljava/lang/Object;)V", "getExpectAnnotation", "()Ljava/lang/Object;", "Ljava/lang/Object;", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
    public static final class MissingOnActual<A> extends ExpectActualAnnotationsIncompatibilityType<A> {
        private final A expectAnnotation;

        public MissingOnActual(A a) {
            super(null);
            this.expectAnnotation = a;
        }

        @Override // org.jetbrains.kotlin.resolve.multiplatform.ExpectActualAnnotationsIncompatibilityType
        public A getExpectAnnotation() {
            return this.expectAnnotation;
        }
    }

    public /* synthetic */ ExpectActualAnnotationsIncompatibilityType(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract A getExpectAnnotation();

    public final <A2> ExpectActualAnnotationsIncompatibilityType<A2> mapAnnotationType(Function1<? super A, ? extends A2> mapper) {
        mapper.getClass();
        if (this instanceof MissingOnActual) {
            return new MissingOnActual(mapper.invoke(((MissingOnActual) this).getExpectAnnotation()));
        }
        if (this instanceof DifferentOnActual) {
            DifferentOnActual differentOnActual = (DifferentOnActual) this;
            return new DifferentOnActual(mapper.invoke(differentOnActual.getExpectAnnotation()), mapper.invoke(differentOnActual.getActualAnnotation()));
        }
        bu8.a();
        return null;
    }

    private ExpectActualAnnotationsIncompatibilityType() {
    }
}
