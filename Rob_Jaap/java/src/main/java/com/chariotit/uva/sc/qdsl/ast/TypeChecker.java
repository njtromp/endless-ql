package com.chariotit.uva.sc.qdsl.ast;


import com.chariotit.uva.sc.qdsl.ast.node.AstRoot;
import com.chariotit.uva.sc.qdsl.ast.visitor.SymbolTableBuilderVisitor;
import com.chariotit.uva.sc.qdsl.ast.visitor.TypeCheckError;
import com.chariotit.uva.sc.qdsl.ast.visitor.TypeCheckVisitor;

import java.util.List;

/**
 * Should check:
 *
 * cyclic dependencies between questions -> Need Symboltable
 * duplicate labels (warning) -> Can do
 *
 * Check BINOP booleans: >= etc
 * Check cyclic dependencies
 */
public class TypeChecker {

    public List<TypeCheckError> typeCheckAst(AstRoot astRoot) {

        // First run. Build symbol table
        SymbolTableBuilderVisitor symbolTableVisitor = new SymbolTableBuilderVisitor();
        astRoot.acceptVisitor(symbolTableVisitor);

        if (symbolTableVisitor.getErrors().size() > 0) {
            return symbolTableVisitor.getErrors();

        }

        // Second run. TypeNode checker
        TypeCheckVisitor typeCheckVisitor = new TypeCheckVisitor(astRoot.getSymbolTable());
        astRoot.acceptVisitor(typeCheckVisitor);

        return typeCheckVisitor.getErrors();
    }
}
