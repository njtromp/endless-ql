package ql.checker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ql.ast.expression.Expression;
import ql.ast.expression.Identifier;
import ql.ast.statement.Question;
import ql.ast.type.Bool;
import ql.ast.type.Type;

public class TypeChecker {

    private List<String> errors;
    private List<String> warnings;
    
    public TypeChecker() {
        this.errors     = new ArrayList<String>();
        this.warnings   = new ArrayList<String>();
    }
    
    public void checkUndefinedRefs(List<Identifier> refs, Map<String,Type> symbolTable) {
        
        for(Identifier id : refs)
        {
            if(!symbolTable.containsKey(id.getName()))
            {
                errors.add("Reference [ " + id.getName() + " ] to undefined question found @ " + id.getLocation());
            }
        }
    }
    
    public void checkConflictingQuestionTypes(List<Question> questions) {
        
        for(int o = 0; o < questions.size(); o++)
        {
            Question orig = questions.get(o);
            
            for(int d = o + 1; d < questions.size(); d++) 
            {
                Question dup = questions.get(d);
                
                if(orig.getIdentifier().getName().equals(dup.getIdentifier().getName()) && !orig.getType().equals(dup.getType()))
                {
                    errors.add("Duplicate questions with different types found @ " + orig.getLocation() + " and " + " @ " + dup.getLocation() + "; [ " + orig.toString() + " ], [ " + dup.toString() + " ]");
                }
            }
        }
    }
    
    public void checkConditionTypes(List<Expression> conditions, Map<String,Type> symbolTable) {
        
        for(Expression c : conditions)
        {
            if(c.isIdentifier())
            {
                Identifier id = (Identifier) c;
                
                if(!symbolTable.containsKey(id.getName()))
                {
                    errors.add("Non-boolean condition [ " + id.getName() + " ] with type [ " + id.getType().toString() + " ] found @ "+ id.getLocation());
                }
                else if(!symbolTable.get(id.getName()).equals(new Bool()))
                {
                    errors.add("Non-boolean condition [ " + id.getName() + " ] with type [ " + symbolTable.get(id.getName()).toString() + " ] found @ "+ id.getLocation());
                }
            }
            else if(!c.getType().equals(new Bool()))
            {
                errors.add("Non-boolean condition [ " + c.toString() + " ] with type [ " + c.getType().toString() + " ] found @ "+ c.getLocation());
            }
        }
    }
    
    public void checkDuplicateLabels(List<Question> questions) {
        
        for(int o = 0; o < questions.size(); o++)
        {
            Question orig = questions.get(o);
            
            for(int d = o + 1; d < questions.size(); d++) 
            {
                Question dup = questions.get(d);
                
                if(orig.getLabel().equals(dup.getLabel()))
                {
                    warnings.add("Duplicate labels [ " + orig.getLabel() + " ] found @ " + orig.getLocation() + " and  @ " + dup.getLocation());
                }
            }
        }
    }
    
    public boolean hasErrors() {
        return !errors.isEmpty();
    }
    
    public List<String> getErrors() {
        return errors;
    }
    
    public boolean hasWarnings() {
        return !errors.isEmpty();
    }
    
    public List<String> getWarnings() {
        return warnings;
    }

    public void printErrors() {
        for(String msg : errors) System.err.println("ERROR: " + msg);
    }
    
    public void printWarnings() {
        for(String msg : warnings) System.out.println("WARNING: " + msg);
    }
}