package com.sun.tools.javac.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class GraphUtils {

    public static abstract class AbstractNode<D, N extends AbstractNode<D, N>> implements Node<D, N> {
        public final D data;

        public AbstractNode(D d) {
            this.data = d;
        }

        @Override // com.sun.tools.javac.util.GraphUtils.Node
        public <A> void accept(NodeVisitor<D, N, A> nodeVisitor, A a) {
            nodeVisitor.visitNode(this, a);
            for (DependencyKind dependencyKind : getSupportedDependencyKinds()) {
                Iterator it = new ArrayList(getDependenciesByKind(dependencyKind)).iterator();
                while (it.hasNext()) {
                    nodeVisitor.visitDependency(dependencyKind, this, (AbstractNode) it.next(), a);
                }
            }
        }

        public abstract Collection<? extends N> getDependenciesByKind(DependencyKind dependencyKind);

        public abstract DependencyKind[] getSupportedDependencyKinds();

        public String toString() {
            return this.data.toString();
        }
    }

    public interface DependencyKind {
    }

    public static class DotVisitor<D, N extends DottableNode<D, N>> extends NodeVisitor<D, N, StringBuilder> {
        public static String wrap(String str) {
            return ("\"" + str + "\"").replaceAll("\n", "");
        }

        public String formatProperties(Properties properties) {
            return properties.toString().replace(',', ' ').replace('{', '[').replace('}', ']');
        }

        @Override // com.sun.tools.javac.util.GraphUtils.NodeVisitor
        public /* bridge */ /* synthetic */ void visit(Collection collection, StringBuilder sb) {
            super.visit(collection, sb);
        }

        @Override // com.sun.tools.javac.util.GraphUtils.NodeVisitor
        public void visitDependency(DependencyKind dependencyKind, N n, N n2, StringBuilder sb) {
            sb.append(String.format("%s -> %s", Integer.valueOf(n.hashCode()), Integer.valueOf(n2.hashCode())));
            sb.append(formatProperties(n.dependencyAttributes(n2, dependencyKind)));
            sb.append('\n');
        }

        @Override // com.sun.tools.javac.util.GraphUtils.NodeVisitor
        public void visitNode(N n, StringBuilder sb) {
            sb.append(String.format("%s ", Integer.valueOf(n.hashCode())));
            sb.append(formatProperties(n.nodeAttributes()));
            sb.append('\n');
        }
    }

    public interface DottableNode<D, N extends DottableNode<D, N>> extends Node<D, N> {
        Properties dependencyAttributes(N n, DependencyKind dependencyKind);

        Properties nodeAttributes();
    }

    public interface Node<D, N extends Node<D, N>> {
        <A> void accept(NodeVisitor<D, N, A> nodeVisitor, A a);
    }

    public static abstract class NodeVisitor<D, N extends Node<D, N>, A> {
        public void visit(Collection<? extends N> collection, A a) {
            Iterator it = new ArrayList(collection).iterator();
            while (it.hasNext()) {
                ((Node) it.next()).accept(this, a);
            }
        }

        public abstract void visitDependency(DependencyKind dependencyKind, N n, N n2, A a);

        public abstract void visitNode(N n, A a);
    }

    public static abstract class TarjanNode<D, N extends TarjanNode<D, N>> extends AbstractNode<D, N> implements Comparable<N> {
        boolean active;
        int index;
        int lowlink;

        public TarjanNode(D d) {
            super(d);
            this.index = -1;
        }

        @Override // java.lang.Comparable
        public int compareTo(N n) {
            return Integer.compare(this.index, n.index);
        }

        public abstract Iterable<? extends N> getAllDependencies();
    }

    public static <D, N extends TarjanNode<D, N>> List<? extends List<? extends N>> tarjan(Iterable<? extends N> iterable) {
        return new Tarjan().findSCC(iterable);
    }

    public static <D, N extends DottableNode<D, N>> String toDot(Collection<? extends N> collection, String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("digraph %s {\n", str));
        sb.append(String.format("label = %s;\n", DotVisitor.wrap(str2)));
        new DotVisitor().visit(collection, sb);
        sb.append("}\n");
        return sb.toString();
    }

    public static class Tarjan<D, N extends TarjanNode<D, N>> {
        int index;
        ListBuffer<List<N>> sccs;
        ListBuffer<N> stack;

        private Tarjan() {
            this.index = 0;
            this.sccs = new ListBuffer<>();
            this.stack = new ListBuffer<>();
        }

        private void addSCC(N n) {
            N nRemove;
            ListBuffer listBuffer = new ListBuffer();
            do {
                nRemove = this.stack.remove();
                nRemove.active = false;
                listBuffer.add(nRemove);
            } while (nRemove != n);
            this.sccs.add(listBuffer.toList());
        }

        private void findSCC(N n) {
            visitNode(n);
            for (N n2 : n.getAllDependencies()) {
                if (n2.index == -1) {
                    findSCC(n2);
                    n.lowlink = Math.min(n.lowlink, n2.lowlink);
                } else if (this.stack.contains(n2)) {
                    n.lowlink = Math.min(n.lowlink, n2.index);
                }
            }
            if (n.lowlink == n.index) {
                addSCC(n);
            }
        }

        private void visitNode(N n) {
            int i = this.index;
            n.index = i;
            n.lowlink = i;
            this.index = i + 1;
            this.stack.prepend(n);
            n.active = true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public List<? extends List<? extends N>> findSCC(Iterable<? extends N> iterable) {
            for (N n : iterable) {
                if (n.index == -1) {
                    findSCC(n);
                }
            }
            return this.sccs.toList();
        }
    }
}
