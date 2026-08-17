package defpackage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.text.StringsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class n11 {
    public static final n11 a = new n11();

    public static Unit a(bl3.f fVar) {
        fVar.getClass();
        return Unit.INSTANCE;
    }

    public final List b(File file, List list, Function1 function1) {
        file.getClass();
        list.getClass();
        function1.getClass();
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            String name = ((File) obj).getName();
            name.getClass();
            String lowerCase = name.toLowerCase(Locale.ROOT);
            lowerCase.getClass();
            if (!StringsKt.startsWith$default(lowerCase, "kotlin-stdlib", false, 2, (Object) null)) {
                arrayList.add(obj);
            }
        }
        bl3 bl3Var = bl3.a;
        bl3.j jVarU = bl3.U(bl3Var, CollectionsKt.listOf("org.jetbrains.kotlin:kotlin-stdlib:2.4.0"), file, function1, null, 8, null);
        return (!jVarU.d().isEmpty() || jVarU.c().isEmpty()) ? list : bl3Var.u(CollectionsKt.plus(arrayList, jVarU.c()));
    }

    public final String c() {
        return "kotlinc=2.4.0;stdlib=2.4.0;compose=2.4.0;codeslot=9";
    }

    public final boolean d(File file) {
        file.getClass();
        String name = file.getName();
        name.getClass();
        String lowerCase = name.toLowerCase(Locale.ROOT);
        lowerCase.getClass();
        return StringsKt.startsWith$default(lowerCase, "kotlin-stdlib", false, 2, (Object) null) || StringsKt.startsWith$default(lowerCase, "kotlinx-coroutines", false, 2, (Object) null) || StringsKt.startsWith$default(lowerCase, "kotlin-reflect", false, 2, (Object) null);
    }

    public final List e(List list) {
        list.getClass();
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (a.d((File) obj)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public final List f(File file, List list) {
        file.getClass();
        list.getClass();
        bl3.j jVarU = bl3.U(bl3.a, CollectionsKt.listOf("org.jetbrains.kotlin:kotlin-stdlib:2.4.0"), file, null, new Function1() { // from class: m11
            public final Object invoke(Object obj) {
                return n11.a((bl3.f) obj);
            }
        }, 4, null);
        List listC = jVarU.d().isEmpty() ? jVarU.c() : CollectionsKt.emptyList();
        List listE = e(list);
        ArrayList arrayList = new ArrayList();
        for (Object obj : listE) {
            String name = ((File) obj).getName();
            name.getClass();
            String lowerCase = name.toLowerCase(Locale.ROOT);
            lowerCase.getClass();
            if (!StringsKt.startsWith$default(lowerCase, "kotlin-stdlib", false, 2, (Object) null)) {
                arrayList.add(obj);
            }
        }
        return bl3.a.u(CollectionsKt.plus(listC, arrayList));
    }
}
