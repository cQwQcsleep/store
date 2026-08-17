package com.android.tools.r8.diagnostic.internal;

import com.android.tools.r8.diagnostic.DefinitionClassContext;
import com.android.tools.r8.diagnostic.DefinitionContext;
import com.android.tools.r8.diagnostic.DefinitionFieldContext;
import com.android.tools.r8.diagnostic.DefinitionMethodContext;
import com.android.tools.r8.diagnostic.MissingClassInfo;
import com.android.tools.r8.diagnostic.MissingDefinitionInfo;
import com.android.tools.r8.diagnostic.MissingFieldInfo;
import com.android.tools.r8.diagnostic.MissingMethodInfo;
import com.android.tools.r8.diagnostic.internal.k;
import com.android.tools.r8.internal.C0416Cp;
import com.android.tools.r8.internal.C1758id;
import com.android.tools.r8.internal.C1975l7;
import com.android.tools.r8.internal.MO;
import com.android.tools.r8.references.ClassReference;
import com.android.tools.r8.references.FieldReference;
import com.android.tools.r8.references.MethodReference;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class k {
    public static final Comparator a = new Comparator() { // from class: ogh
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return k.a((MissingDefinitionInfo) obj, (MissingDefinitionInfo) obj2);
        }
    };
    public static final /* synthetic */ boolean b = true;

    public static int a(MissingDefinitionInfo missingDefinitionInfo, MissingDefinitionInfo missingDefinitionInfo2) {
        if (missingDefinitionInfo.isMissingClass()) {
            ClassReference classReference = missingDefinitionInfo.asMissingClass().getClassReference();
            if (missingDefinitionInfo2.isMissingClass()) {
                return C1758id.a().compare(classReference, missingDefinitionInfo2.asMissingClass().getClassReference());
            }
            if (missingDefinitionInfo2.isMissingField()) {
                int iCompare = C1758id.a().compare(classReference, missingDefinitionInfo2.asMissingField().getFieldReference().getHolderClass());
                if (iCompare != 0) {
                    return iCompare;
                }
                return -1;
            }
            int iCompare2 = C1758id.a().compare(classReference, missingDefinitionInfo2.asMissingMethod().getMethodReference().getHolderClass());
            if (iCompare2 != 0) {
                return iCompare2;
            }
            return -1;
        }
        if (missingDefinitionInfo.isMissingField()) {
            FieldReference fieldReference = missingDefinitionInfo.asMissingField().getFieldReference();
            if (missingDefinitionInfo2.isMissingClass()) {
                ClassReference classReference2 = missingDefinitionInfo2.asMissingClass().getClassReference();
                Comparator comparator = C0416Cp.a;
                int iCompare3 = C1758id.a().compare(classReference2, fieldReference.getHolderClass());
                if (iCompare3 == 0) {
                    iCompare3 = -1;
                }
                return iCompare3 * (-1);
            }
            if (missingDefinitionInfo2.isMissingField()) {
                return C0416Cp.a.compare(fieldReference, missingDefinitionInfo2.asMissingField().getFieldReference());
            }
            MethodReference methodReference = missingDefinitionInfo2.asMissingMethod().getMethodReference();
            Comparator comparator2 = C0416Cp.a;
            int iCompare4 = C1758id.a().compare(fieldReference.getHolderClass(), methodReference.getHolderClass());
            if (iCompare4 != 0) {
                return iCompare4;
            }
            return -1;
        }
        MethodReference methodReference2 = missingDefinitionInfo.asMissingMethod().getMethodReference();
        if (missingDefinitionInfo2.isMissingClass()) {
            ClassReference classReference3 = missingDefinitionInfo2.asMissingClass().getClassReference();
            Comparator comparator3 = MO.a;
            int iCompare5 = C1758id.a().compare(classReference3, methodReference2.getHolderClass());
            if (iCompare5 == 0) {
                iCompare5 = -1;
            }
            return iCompare5 * (-1);
        }
        if (!missingDefinitionInfo2.isMissingField()) {
            return MO.a().compare(methodReference2, missingDefinitionInfo2.asMissingMethod().getMethodReference());
        }
        FieldReference fieldReference2 = missingDefinitionInfo2.asMissingField().getFieldReference();
        Comparator comparator4 = MO.a;
        Comparator comparator5 = C0416Cp.a;
        int iCompare6 = C1758id.a().compare(fieldReference2.getHolderClass(), methodReference2.getHolderClass());
        if (iCompare6 == 0) {
            iCompare6 = -1;
        }
        return iCompare6 * (-1);
    }

    public static void b(StringBuilder sb, MissingDefinitionInfo missingDefinitionInfo) {
        final C1975l7 c1975l7 = new C1975l7();
        final C1975l7 c1975l8 = new C1975l7();
        final C1975l7 c1975l9 = new C1975l7();
        Iterator<DefinitionContext> it = missingDefinitionInfo.getReferencedFromContexts().iterator();
        while (it.hasNext()) {
            d.a(it.next(), new Consumer() { // from class: keh
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    k.a(c1975l7, (DefinitionClassContext) obj);
                }
            }, new Consumer() { // from class: veh
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    k.a(c1975l8, (DefinitionFieldContext) obj);
                }
            }, new Consumer() { // from class: tfh
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    k.a(c1975l9, (DefinitionMethodContext) obj);
                }
            });
        }
        if (c1975l8.b()) {
            a(sb, missingDefinitionInfo, C0416Cp.a((FieldReference) c1975l8.a()));
        } else if (c1975l9.b()) {
            a(sb, missingDefinitionInfo, MO.b((MethodReference) c1975l9.a()));
        } else if (c1975l7.b()) {
            a(sb, missingDefinitionInfo, ((ClassReference) c1975l7.a()).getTypeName());
        }
    }

    public static void a(final StringBuilder sb, MissingDefinitionInfo missingDefinitionInfo) {
        Consumer consumer = new Consumer() { // from class: zfh
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                k.a(sb, (MissingClassInfo) obj);
            }
        };
        Consumer consumer2 = new Consumer() { // from class: egh
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                k.a(sb, (MissingFieldInfo) obj);
            }
        };
        Consumer consumer3 = new Consumer() { // from class: jgh
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                k.a(sb, (MissingMethodInfo) obj);
            }
        };
        if (missingDefinitionInfo.isMissingClass()) {
            consumer.accept(missingDefinitionInfo.asMissingClass());
        } else if (missingDefinitionInfo.isMissingField()) {
            consumer2.accept(missingDefinitionInfo.asMissingField());
        } else {
            if (!b && !missingDefinitionInfo.isMissingMethod()) {
                x1f.a();
                return;
            }
            consumer3.accept(missingDefinitionInfo.asMissingMethod());
        }
        b(sb, missingDefinitionInfo);
    }

    public static /* synthetic */ void a(StringBuilder sb, MissingClassInfo missingClassInfo) {
        sb.append("Missing class ");
        sb.append(missingClassInfo.getClassReference().getTypeName());
    }

    public static /* synthetic */ void a(StringBuilder sb, MissingFieldInfo missingFieldInfo) {
        sb.append("Missing field ");
        sb.append(C0416Cp.a(missingFieldInfo.getFieldReference()));
    }

    public static /* synthetic */ void a(StringBuilder sb, MissingMethodInfo missingMethodInfo) {
        sb.append("Missing method ");
        sb.append(MO.b(missingMethodInfo.getMethodReference()));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void a(C1975l7 c1975l7, DefinitionClassContext definitionClassContext) {
        ClassReference classReference = definitionClassContext.getClassReference();
        Comparator<ClassReference> comparatorA = C1758id.a();
        if (!c1975l7.b() || comparatorA.compare(classReference, c1975l7.a()) < 0) {
            c1975l7.a(classReference);
        }
    }

    public static void a(C1975l7 c1975l7, DefinitionFieldContext definitionFieldContext) {
        FieldReference fieldReference = definitionFieldContext.getFieldReference();
        Comparator comparator = C0416Cp.a;
        if (!c1975l7.b() || comparator.compare(fieldReference, c1975l7.a()) < 0) {
            c1975l7.a(fieldReference);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void a(C1975l7 c1975l7, DefinitionMethodContext definitionMethodContext) {
        MethodReference methodReference = definitionMethodContext.getMethodReference();
        Comparator<MethodReference> comparatorA = MO.a();
        if (!c1975l7.b() || comparatorA.compare(methodReference, c1975l7.a()) < 0) {
            c1975l7.a(methodReference);
        }
    }

    public static void a(StringBuilder sb, MissingDefinitionInfo missingDefinitionInfo, String str) {
        int size = missingDefinitionInfo.getReferencedFromContexts().size() - 1;
        if (!b && size < 0) {
            x1f.a();
            return;
        }
        sb.append(" (referenced from: ");
        sb.append(str);
        if (size >= 1) {
            sb.append(" and ");
            sb.append(size);
            sb.append(" other context");
            if (size >= 2) {
                sb.append("s");
            }
        }
        sb.append(")");
    }
}
