package com.android.tools.r8.shaking;

import com.android.tools.r8.experimental.graphinfo.ClassGraphNode;
import com.android.tools.r8.experimental.graphinfo.FieldGraphNode;
import com.android.tools.r8.experimental.graphinfo.GraphConsumer;
import com.android.tools.r8.experimental.graphinfo.GraphNode;
import com.android.tools.r8.experimental.graphinfo.KeepRuleGraphNode;
import com.android.tools.r8.experimental.graphinfo.MethodGraphNode;
import com.android.tools.r8.internal.C0929Wj;
import com.android.tools.r8.internal.C1405eW;
import com.android.tools.r8.internal.C2807us;
import com.android.tools.r8.internal.C2847vL;
import com.android.tools.r8.internal.Wf0;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;
import com.android.tools.r8.position.TextPosition;
import com.android.tools.r8.position.TextRange;
import com.android.tools.r8.references.ClassReference;
import com.android.tools.r8.references.FieldReference;
import com.android.tools.r8.references.MethodReference;
import defpackage.ykg;
import java.io.PrintStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class x4 extends C3457t {
    public static final /* synthetic */ boolean c = true;

    public x4(GraphConsumer graphConsumer) {
        super(graphConsumer);
    }

    public static String d(GraphNode graphNode) {
        if (graphNode instanceof ClassGraphNode) {
            return C0929Wj.b(((ClassGraphNode) graphNode).getReference().getDescriptor());
        }
        if (graphNode instanceof MethodGraphNode) {
            MethodReference reference = ((MethodGraphNode) graphNode).getReference();
            return (reference.getReturnType() == null ? "void" : reference.getReturnType().getTypeName()) + " " + reference.getHolderClass().getTypeName() + "." + reference.getMethodName() + Wf0.a(",", C2847vL.a((Collection) reference.getFormalTypes(), (Function) new ykg()), Wf0.a.b);
        }
        if (graphNode instanceof FieldGraphNode) {
            FieldReference reference2 = ((FieldGraphNode) graphNode).getReference();
            return reference2.getFieldType().getTypeName() + " " + reference2.getHolderClass().getTypeName() + "." + reference2.getFieldName();
        }
        if (!(graphNode instanceof KeepRuleGraphNode)) {
            if (GraphNode.cycle().equals(graphNode)) {
                return "only cyclic dependencies remain, failed to determine a path from a keep rule";
            }
            if (c) {
                return Objects.toString(graphNode);
            }
            s22.a("Unexpected graph node type: ", graphNode);
            return null;
        }
        KeepRuleGraphNode keepRuleGraphNode = (KeepRuleGraphNode) graphNode;
        if (Origin.unknown().equals(keepRuleGraphNode.getOrigin())) {
            return keepRuleGraphNode.getContent();
        }
        return keepRuleGraphNode.getOrigin() + ":" + a(keepRuleGraphNode.getPosition());
    }

    public final void a(GraphNode graphNode, PrintStream printStream) {
        v4 v4Var = new v4(printStream);
        ArrayList arrayListA = null;
        w4 w4Var = null;
        if (graphNode != null) {
            IdentityHashMap identityHashMap = new IdentityHashMap();
            ArrayDeque arrayDeque = new ArrayDeque();
            GraphNode graphNode2 = graphNode;
            while (true) {
                Map<GraphNode, Set<C2807us>> mapA = a(graphNode2);
                if (mapA == null) {
                    arrayListA = a(graphNode, w4Var);
                    break;
                }
                if (!c && mapA.isEmpty()) {
                    x1f.a();
                    return;
                }
                for (GraphNode graphNode3 : mapA.keySet()) {
                    if (!identityHashMap.containsKey(graphNode3)) {
                        identityHashMap.put(graphNode3, graphNode3);
                        arrayDeque.addLast(new w4(graphNode3, w4Var));
                    }
                }
                if (arrayDeque.isEmpty()) {
                    arrayListA = a(graphNode, new w4(GraphNode.cycle(), w4Var));
                    break;
                } else {
                    w4Var = (w4) arrayDeque.removeFirst();
                    graphNode2 = w4Var.a;
                }
            }
        }
        if (arrayListA == null) {
            printStream.print("Nothing is keeping ");
            printStream.println(d(graphNode));
            return;
        }
        String strD = d(graphNode);
        v4Var.b++;
        v4Var.a();
        v4Var.a.println(strD);
        for (int size = arrayListA.size() - 1; size >= 0; size--) {
            C1405eW c1405eW = (C1405eW) arrayListA.get(size);
            a((GraphNode) c1405eW.a(), (C2807us) c1405eW.b(), v4Var);
        }
        v4Var.b--;
    }

    public void a(MethodReference methodReference, PrintStream printStream) {
        MethodGraphNode methodGraphNode;
        Iterator<GraphNode> it = a().iterator();
        while (true) {
            if (!it.hasNext()) {
                methodGraphNode = null;
                break;
            }
            GraphNode next = it.next();
            if (next instanceof MethodGraphNode) {
                methodGraphNode = (MethodGraphNode) next;
                if (methodGraphNode.getReference().equals(methodReference)) {
                    break;
                }
            }
        }
        if (methodGraphNode == null) {
            methodGraphNode = new MethodGraphNode(false, methodReference);
        }
        a(methodGraphNode, printStream);
    }

    public void a(FieldReference fieldReference, PrintStream printStream) {
        FieldGraphNode fieldGraphNode;
        Iterator<GraphNode> it = a().iterator();
        while (true) {
            if (!it.hasNext()) {
                fieldGraphNode = null;
                break;
            }
            GraphNode next = it.next();
            if (next instanceof FieldGraphNode) {
                fieldGraphNode = (FieldGraphNode) next;
                if (fieldGraphNode.getReference().equals(fieldReference)) {
                    break;
                }
            }
        }
        if (fieldGraphNode == null) {
            fieldGraphNode = new FieldGraphNode(false, fieldReference);
        }
        a(fieldGraphNode, printStream);
    }

    public void a(ClassReference classReference, PrintStream printStream) {
        ClassGraphNode classGraphNode;
        Iterator<GraphNode> it = a().iterator();
        while (true) {
            if (!it.hasNext()) {
                classGraphNode = null;
                break;
            }
            GraphNode next = it.next();
            if (next instanceof ClassGraphNode) {
                classGraphNode = (ClassGraphNode) next;
                if (classGraphNode.getReference().equals(classReference)) {
                    break;
                }
            }
        }
        if (classGraphNode == null) {
            classGraphNode = new ClassGraphNode(false, classReference);
        }
        a(classGraphNode, printStream);
    }

    public final ArrayList a(GraphNode graphNode, w4 w4Var) {
        if (w4Var == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        while (w4Var.b != null) {
            GraphNode graphNode2 = w4Var.a;
            if (graphNode2.isCycle()) {
                arrayList.add(new C1405eW(graphNode2, new C2807us(C2807us.a.t)));
            } else {
                arrayList.add(new C1405eW(graphNode2, a(a(w4Var.b.a).get(graphNode2))));
            }
            w4Var = w4Var.b;
        }
        arrayList.add(new C1405eW(w4Var.a, a(a(graphNode).get(w4Var.a))));
        return arrayList;
    }

    public static C2807us a(Set set) {
        for (C2807us.a aVar : C2807us.a.values()) {
            Iterator it = set.iterator();
            while (it.hasNext()) {
                C2807us c2807us = (C2807us) it.next();
                if (c2807us.a() == aVar) {
                    return c2807us;
                }
            }
        }
        if (c) {
            return C2807us.b;
        }
        x01.a("Unexpected empty set of graph edge info");
        return null;
    }

    public static void a(GraphNode graphNode, C2807us c2807us, v4 v4Var) {
        v4Var.a("is " + c2807us.b() + ":");
        for (String str : Wf0.f(d(graphNode))) {
            v4Var.a();
            v4Var.a.print("|  ");
            v4Var.a.println(str);
        }
    }

    public static String a(Position position) {
        if (position instanceof TextRange) {
            TextPosition start = ((TextRange) position).getStart();
            return start.getLine() + ":" + start.getColumn();
        }
        return position.getDescription();
    }
}
