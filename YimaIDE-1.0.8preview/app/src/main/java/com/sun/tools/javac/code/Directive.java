package com.sun.tools.javac.code;

import com.sun.tools.javac.util.List;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Set;
import javax.lang.model.element.ModuleElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class Directive implements ModuleElement.Directive {

    public enum ExportsFlag {
        SYNTHETIC(4096),
        MANDATED(32768);

        public final int value;

        ExportsFlag(int i) {
            this.value = i;
        }

        public static int value(Set<ExportsFlag> set) {
            Iterator<ExportsFlag> it = set.iterator();
            int i = 0;
            while (it.hasNext()) {
                i |= it.next().value;
            }
            return i;
        }
    }

    public enum OpensFlag {
        SYNTHETIC(4096),
        MANDATED(32768);

        public final int value;

        OpensFlag(int i) {
            this.value = i;
        }

        public static int value(Set<OpensFlag> set) {
            Iterator<OpensFlag> it = set.iterator();
            int i = 0;
            while (it.hasNext()) {
                i |= it.next().value;
            }
            return i;
        }
    }

    public enum RequiresFlag {
        TRANSITIVE(32),
        STATIC_PHASE(64),
        SYNTHETIC(4096),
        MANDATED(32768),
        EXTRA(65536);

        public final int value;

        RequiresFlag(int i) {
            this.value = i;
        }

        public static int value(Set<RequiresFlag> set) {
            Iterator<RequiresFlag> it = set.iterator();
            int i = 0;
            while (it.hasNext()) {
                i |= it.next().value;
            }
            return i;
        }

        @Override // java.lang.Enum
        public String toString() {
            return String.format("ACC_%s (0x%04x)", name(), Integer.valueOf(this.value));
        }
    }

    public static class ExportsDirective extends Directive implements ModuleElement.ExportsDirective {
        public final Set<ExportsFlag> flags;
        public final List<Symbol.ModuleSymbol> modules;
        public final Symbol.PackageSymbol packge;

        public ExportsDirective(Symbol.PackageSymbol packageSymbol, List<Symbol.ModuleSymbol> list) {
            this(packageSymbol, list, EnumSet.noneOf(ExportsFlag.class));
        }

        @Override // javax.lang.model.element.ModuleElement.Directive
        public <R, P> R accept(ModuleElement.DirectiveVisitor<R, P> directiveVisitor, P p) {
            return directiveVisitor.visitExports(this, p);
        }

        @Override // javax.lang.model.element.ModuleElement.Directive
        public ModuleElement.DirectiveKind getKind() {
            return ModuleElement.DirectiveKind.EXPORTS;
        }

        @Override // javax.lang.model.element.ModuleElement.ExportsDirective
        public java.util.List<Symbol.ModuleSymbol> getTargetModules() {
            List<Symbol.ModuleSymbol> list = this.modules;
            if (list == null) {
                return null;
            }
            return Collections.unmodifiableList(list);
        }

        public String toString() {
            List<Symbol.ModuleSymbol> list = this.modules;
            Symbol.PackageSymbol packageSymbol = this.packge;
            if (list == null) {
                return "Exports[" + packageSymbol + "]";
            }
            return "Exports[" + packageSymbol + ":" + this.modules + "]";
        }

        @Override // javax.lang.model.element.ModuleElement.ExportsDirective
        public Symbol.PackageSymbol getPackage() {
            return this.packge;
        }

        public ExportsDirective(Symbol.PackageSymbol packageSymbol, List<Symbol.ModuleSymbol> list, Set<ExportsFlag> set) {
            this.packge = packageSymbol;
            this.modules = list;
            this.flags = set;
        }
    }

    public static class OpensDirective extends Directive implements ModuleElement.OpensDirective {
        public final Set<OpensFlag> flags;
        public final List<Symbol.ModuleSymbol> modules;
        public final Symbol.PackageSymbol packge;

        public OpensDirective(Symbol.PackageSymbol packageSymbol, List<Symbol.ModuleSymbol> list) {
            this(packageSymbol, list, EnumSet.noneOf(OpensFlag.class));
        }

        @Override // javax.lang.model.element.ModuleElement.Directive
        public <R, P> R accept(ModuleElement.DirectiveVisitor<R, P> directiveVisitor, P p) {
            return directiveVisitor.visitOpens(this, p);
        }

        @Override // javax.lang.model.element.ModuleElement.Directive
        public ModuleElement.DirectiveKind getKind() {
            return ModuleElement.DirectiveKind.OPENS;
        }

        @Override // javax.lang.model.element.ModuleElement.OpensDirective
        public java.util.List<Symbol.ModuleSymbol> getTargetModules() {
            List<Symbol.ModuleSymbol> list = this.modules;
            if (list == null) {
                return null;
            }
            return Collections.unmodifiableList(list);
        }

        public String toString() {
            List<Symbol.ModuleSymbol> list = this.modules;
            Symbol.PackageSymbol packageSymbol = this.packge;
            if (list == null) {
                return "Opens[" + packageSymbol + "]";
            }
            return "Opens[" + packageSymbol + ":" + this.modules + "]";
        }

        @Override // javax.lang.model.element.ModuleElement.OpensDirective
        public Symbol.PackageSymbol getPackage() {
            return this.packge;
        }

        public OpensDirective(Symbol.PackageSymbol packageSymbol, List<Symbol.ModuleSymbol> list, Set<OpensFlag> set) {
            this.packge = packageSymbol;
            this.modules = list;
            this.flags = set;
        }
    }

    public static class ProvidesDirective extends Directive implements ModuleElement.ProvidesDirective {
        public final List<Symbol.ClassSymbol> impls;
        public final Symbol.ClassSymbol service;

        public ProvidesDirective(Symbol.ClassSymbol classSymbol, List<Symbol.ClassSymbol> list) {
            this.service = classSymbol;
            this.impls = list;
        }

        @Override // javax.lang.model.element.ModuleElement.Directive
        public <R, P> R accept(ModuleElement.DirectiveVisitor<R, P> directiveVisitor, P p) {
            return directiveVisitor.visitProvides(this, p);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ProvidesDirective)) {
                return false;
            }
            ProvidesDirective providesDirective = (ProvidesDirective) obj;
            return this.service == providesDirective.service && this.impls.equals(providesDirective.impls);
        }

        @Override // javax.lang.model.element.ModuleElement.Directive
        public ModuleElement.DirectiveKind getKind() {
            return ModuleElement.DirectiveKind.PROVIDES;
        }

        public int hashCode() {
            return (this.service.hashCode() * 31) + (this.impls.hashCode() * 37);
        }

        public String toString() {
            return "Provides[" + this.service + "," + this.impls + "]";
        }

        @Override // javax.lang.model.element.ModuleElement.ProvidesDirective
        public List<Symbol.ClassSymbol> getImplementations() {
            return this.impls;
        }

        @Override // javax.lang.model.element.ModuleElement.ProvidesDirective
        public Symbol.ClassSymbol getService() {
            return this.service;
        }
    }

    public static class RequiresDirective extends Directive implements ModuleElement.RequiresDirective {
        public final Set<RequiresFlag> flags;
        public final Symbol.ModuleSymbol module;

        public RequiresDirective(Symbol.ModuleSymbol moduleSymbol) {
            this(moduleSymbol, EnumSet.noneOf(RequiresFlag.class));
        }

        @Override // javax.lang.model.element.ModuleElement.Directive
        public <R, P> R accept(ModuleElement.DirectiveVisitor<R, P> directiveVisitor, P p) {
            return directiveVisitor.visitRequires(this, p);
        }

        @Override // javax.lang.model.element.ModuleElement.Directive
        public ModuleElement.DirectiveKind getKind() {
            return ModuleElement.DirectiveKind.REQUIRES;
        }

        @Override // javax.lang.model.element.ModuleElement.RequiresDirective
        public boolean isStatic() {
            return this.flags.contains(RequiresFlag.STATIC_PHASE);
        }

        @Override // javax.lang.model.element.ModuleElement.RequiresDirective
        public boolean isTransitive() {
            return this.flags.contains(RequiresFlag.TRANSITIVE);
        }

        public String toString() {
            return "Requires[" + this.flags + "," + this.module + "]";
        }

        @Override // javax.lang.model.element.ModuleElement.RequiresDirective
        public Symbol.ModuleSymbol getDependency() {
            return this.module;
        }

        public RequiresDirective(Symbol.ModuleSymbol moduleSymbol, Set<RequiresFlag> set) {
            this.module = moduleSymbol;
            this.flags = set;
        }
    }

    public static class UsesDirective extends Directive implements ModuleElement.UsesDirective {
        public final Symbol.ClassSymbol service;

        public UsesDirective(Symbol.ClassSymbol classSymbol) {
            this.service = classSymbol;
        }

        @Override // javax.lang.model.element.ModuleElement.Directive
        public <R, P> R accept(ModuleElement.DirectiveVisitor<R, P> directiveVisitor, P p) {
            return directiveVisitor.visitUses(this, p);
        }

        public boolean equals(Object obj) {
            return (obj instanceof UsesDirective) && this.service == ((UsesDirective) obj).service;
        }

        @Override // javax.lang.model.element.ModuleElement.Directive
        public ModuleElement.DirectiveKind getKind() {
            return ModuleElement.DirectiveKind.USES;
        }

        public int hashCode() {
            return this.service.hashCode() * 31;
        }

        public String toString() {
            return "Uses[" + this.service + "]";
        }

        @Override // javax.lang.model.element.ModuleElement.UsesDirective
        public Symbol.ClassSymbol getService() {
            return this.service;
        }
    }
}
