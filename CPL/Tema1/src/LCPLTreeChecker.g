tree grammar LCPLTreeChecker;

options {
    tokenVocab=LCPLTreeBuilder;
    ASTLabelType=CommonTree;
}

@members {
    LinkedList<LCPLClass> classes = new LinkedList<LCPLClass>();
}

@header {
    import java.util.LinkedList;
    import ro.pub.cs.lcpl.*;
    import vector.*;
}

program returns [Program result]
    : ^(PROGRAM class_definition+)
    { 
        $result=new Program($PROGRAM.line, classes); 
    }
    ;
class_definition
:^(CLASSDEFINITION classdef)
{
	classes.add($classdef.result);
};


classdef returns [LCPLClass result]
    :   ^(CLASS name=ID parent=ID members?)
    {
    	ArrayList<Feature> features = ($members.features != null) ? $members.features : new ArrayList<Feature>();
        $result= new LCPLClass($CLASS.line, $name.text, $parent.text, features);
    }
    |	^(CLASS name=ID members?)
    {
    	ArrayList<Feature> features = ($members.features != null) ? $members.features : new ArrayList<Feature>();
    	$result= new LCPLClass($CLASS.line, $name.text, null, features);
    }
    ;
    
method returns [Method result]
	scope{
		ArrayList<FormalParam> parameters;
	}
	@init{
		$method::parameters = new ArrayList<FormalParam>();
	}
    :   ^(EMPTY_METHOD ID)
    {
        result = new Method($EMPTY_METHOD.line, $ID.text, new ArrayList<FormalParam>(), "void", new Block(0, new ArrayList<Expression>()));
    }
    |   ^(METHOD ID block)
    {
    	result = new Method($METHOD.line, $ID.text, new ArrayList<FormalParam>(), "void", $block.result);
    }
    | ^(METHOD ID (param = formal_param {$method::parameters.add($param.result);})+ t = type? block)
    {
    	String type;
    	if (t == null) {
    		type = "void";
    	} else {
    		type = $t.result;
    	}
    	result = new Method($METHOD.line, $ID.text, $method::parameters, type, $block.result);
    }
    | ^(METHOD ID type block)
    {
    	result = new Method($METHOD.line, $ID.text, new ArrayList<FormalParam>(), $type.result, $block.result);
    }
    ;
    
local_decl returns [LocalDefinition result]
	scope {
		ArrayList<Attribute> exprs;
	}
	@init {
		$local_decl::exprs = new ArrayList<Attribute>();
	}
	:	^(LOCAL (a = attribute {$local_decl::exprs.add($a.result); })+ block?)
	{
		int i = $local_decl::exprs.size() - 1;
		int line = $LOCAL.line;
		Expression scope = $block.result;
		if (scope == null) {
			scope = new Block(0, new ArrayList<Expression>());
		}
		while (i >= 0) {
			Attribute attr = $local_decl::exprs.get(i);
			LocalDefinition localDefinition = new LocalDefinition(attr.getLineNumber(),
										attr.getName(),
										attr.getType(),
										attr.getInit(),
										scope);
			scope = localDefinition;
			i--;
		}
		result = (LocalDefinition)scope;
	};
    
block returns [Block result]
	scope {
		ArrayList<Expression> exprs;
	}
	@init {
		$block::exprs = new ArrayList<Expression>();
	}
	: ^(BLOCK (e = expression { $block::exprs.add($e.result) ;})+ (l = local_decl {$block::exprs.add($l.result);})?)
	{
		result = new Block($BLOCK.line, $block::exprs);
	}
	| ^(BLOCK local_decl)
	{
		$block::exprs.add($local_decl.result);
		result = new Block($BLOCK.line, $block::exprs);
	};
    
members	 returns[ArrayList<Feature> features]
	scope {
		ArrayList<Feature> local_features;
	}
	@init{
		$members::local_features = new ArrayList<Feature>();
	}
	: ^(MEMBERS member+)
	{
		features = $members::local_features;
	};
	
member	: ^(MEMBER decl_section)
	{
		$members::local_features.addAll($decl_section.features);
	}
	| ^(MEMBER method)
	{
		$members::local_features.add($method.result);
	};
	
formal_param returns[FormalParam result] 
	:	^(FORMAL_PARAM type ID)
	{
		result = new FormalParam($ID.text, $type.result);
	};
	
dispatch returns[Expression result]
	:	^(DISPATCH (o = primaryExpression)? (t = type)? ID (b = dispatch_arguments)?)
	{
		ArrayList<Expression> args = ((b != null) ? $b.args : new ArrayList<Expression>());
		if (t != null) {
			result = new StaticDispatch($DISPATCH.line, $o.result, $t.result, $ID.text, args);
		} else {
			result = new Dispatch($DISPATCH.line, o, $ID.text, args);
		}
	}
	|	^(DISPATCH_S ID (t = type)? b= dispatch_arguments?)
	{
		ArrayList<Expression> args = ((b != null) ? $b.args : new ArrayList<Expression>());
		if (t != null) {
			result = new StaticDispatch($DISPATCH_S.line, new Symbol($ID.line, "self"), $t.result, $ID.text, args);
		} else {
			result = new Dispatch($DISPATCH_S.line, new Symbol($ID.line, "self"), $ID.text, args);
		}
	}
	;
	
dispatch_arguments returns[ArrayList<Expression> args]
	scope {
		ArrayList<Expression> exprs;
	}
	@init {
		$dispatch_arguments::exprs = new ArrayList<Expression>();
	}
	:	^(DISPATCH_ARGUMENTS (i = expression { $dispatch_arguments::exprs.add($i.result);} )+)
	{
		args = $dispatch_arguments::exprs;
	};

decl_section returns [ArrayList<Feature> features]
	scope {
		ArrayList<Feature> local_features;
	}
	@init{
		$decl_section::local_features = new ArrayList<Feature>();
	}
	:	^(DECL_SECTION (a = attribute {$decl_section::local_features.add($a.result);})+)
  	{
  		features = $decl_section::local_features;
  	};
  	
attribute returns [Attribute result]
	: ^(ATTRIBUTE type ID)
	{
		result = new Attribute($ATTRIBUTE.line, $ID.text, $type.result, null);
	}
	| ^(ATTRIBUTE type ID e = expression)
	{
		result = new Attribute($ATTRIBUTE.line, $ID.text, $type.result, $e.result);
	}
	| ^(ATTRIBUTE_ARRAY type ID)
	{
		result = new AttributeArray($ATTRIBUTE_ARRAY.line, $ID.text, $type.result, null);
	}
	| ^(ATTRIBUTE_ARRAY type ID e = expression)
	{
		result = new AttributeArray($ATTRIBUTE_ARRAY.line, $ID.text, $type.result, $e.result);
	}
	;
	
statement returns[Expression result]
	:	^(IF cond=expression ifBlock = block thenBlock = block?)
	{
		result = new IfStatement($IF.line, $cond.result, $ifBlock.result, thenBlock);
	}
	|	^(WHILE cond = expression loopBody = block)
	{
		result = new WhileStatement($WHILE.line, $cond.result, $loopBody.result);
	}
	;

cast	returns[Expression result]
	:	^(CAST type e = expression)
	{
		result = new Cast($CAST.line, $type.result, $e.result);
	};	
expression returns[Expression result]
	:	^(EXPRESSION e=assignmentExpression)
	{
		result = $e.result;
	};
	
assignmentExpression returns[Expression result]
	:	^(EQ e = equalityExpression b=assignmentExpression)
	{
		String name = "";
		if ($e.result instanceof ArrayAccess) {
			Expression type = ((ArrayAccess)$e.result).getExpression();
			Expression index = ((ArrayAccess)$e.result).getValue();
			result = new ArrayAssignment($EQ.line, type, index, $b.result); 
		} else {
			name = ((Symbol)$e.result).getName();
			result = new Assignment($EQ.line, name, $b.result);
		}
	}
	|	^(EQ 'self.' ID b = assignmentExpression)
	{
		String name = "self." + $ID.text;
		result = new Assignment($EQ.line, name, $b.result);
	}
	|	e = equalityExpression
	{
		result = $e.result;
	}
	;

	
equalityExpression returns[Expression result]
	:	^(EQUAL a=equalityExpression  b=relationalExpression)
	{
		result = new EqualComparison($EQUAL.line, $a.result, $b.result);
	}
	|	e = relationalExpression
	{
		result = $e.result;
	};
	
relationalExpression returns[Expression result]
	:	^(LTEQ a=relationalExpression b=additiveExpression)
	{
		result = new LessThanEqual($LTEQ.line, $a.result, $b.result);
	}
	|	^(LT a=relationalExpression b=additiveExpression)
	{	
		result = new LessThan($LT.line, $a.result, $b.result);
	}
	|	e = additiveExpression
	{
		result = $e.result;
	};
	
additiveExpression returns[Expression result]
	:	^(PLUS a = additiveExpression b = multiplicativeExpression)
	{
		result = new Addition($PLUS.line, $a.result, $b.result);
	}
	|	^(SUB a = additiveExpression b = multiplicativeExpression)
	{
		result = new Subtraction($SUB.line, $a.result, $b.result);
	}
	|	e = multiplicativeExpression
	{
		result = $e.result;
	}
	;
multiplicativeExpression returns [Expression result]
	:	^(STAR a = multiplicativeExpression b = unaryExpression)
	{
		result = new Multiplication($STAR.line, $a.result, $b.result);
	}
	|	^(DIV a = multiplicativeExpression b = unaryExpression)
	{
		result = new Division($DIV.line, $a.result, $b.result);
	}
	|	e = unaryExpression
	{
		result = $e.result;
	}
	;
	
unaryExpression returns[Expression result]
	:	^(UNARY_MINUS e = unaryExpression)
	{
		result = new UnaryMinus($UNARY_MINUS.line, $e.result);
	}
	|	simpleUnaryExpression
	{
		result = $simpleUnaryExpression.result;
	};
	
simpleUnaryExpression returns [Expression result]
	:	^(NOT e = unaryExpression)
	{
		result = new LogicalNegation($NOT.line, $e.result);
	}
	|	^(SUBSTR e = simpleUnaryExpression x = expression)
	{
		result = new SubString($SUBSTR.line, ((UnaryBracket)$e.result).getExpression(),
						 ((UnaryBracket)$e.result).getValue(), $x.result);
	}
	|	^(UNARY_BRACKET  e = simpleUnaryExpression s = expression)
	{
		if ($e.result instanceof NewObject) {
			result = new NewObjectArray($UNARY_BRACKET.line, $e.result, $s.result);
		} else {
			result = new ArrayAccess($UNARY_BRACKET.line, $e.result, $s.result);
		}
	}
	|	primaryExpression
	{
		result = $primaryExpression.result;
	};
	
primaryExpression returns[Expression result]
	:	^(PAR_EXPR e = expression)
	{
		result = $e.result;
	}
	|	literal
	{
		result = $literal.result;
	}
	|	dispatch
	{
		result = $dispatch.result;
	}
	|	new_object
	{
		result = $new_object.result;
	}
	|	cast
	{
		result = $cast.result;
	}
	|	statement
	{
		result = $statement.result;
	};

literal	returns[Expression result]
	: integer 	{result = $integer.result;}
	| string 	{result = $string.result;}
	| ^(SYMBOL type) {result = new Symbol($SYMBOL.line, $type.result);}
	| VOID_CONSTANT {result = new VoidConstant($VOID_CONSTANT.line);}
	| SELF 		{result = new Symbol($SELF.line, "self");}
	;

integer	returns [IntConstant result]	
	:	^(CONSTANT_INT value=INTEGER)
	{
		result = new IntConstant($CONSTANT_INT.line, Integer.valueOf($value.text));
	};
	
string returns [StringConstant result]
	:	^(CONSTANT_STRING value=STRING_LITERAL)
	{
		String s = $value.text.substring(1, $value.text.length() - 1);
		StringBuilder newString = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '\\' && (i + 1) < s.length()) {
				c = s.charAt(i + 1);
				i++;
				if (c == 'n') {
					newString.append('\n');
				} else if (c == 'r') {
					newString.append('\r');
				} else if (c == 't') {
					newString.append('\t');
				} else {
					newString.append(c);
				}
			} else if (c != '\\') {
				newString.append(c);
			}
		}
		result = new StringConstant($CONSTANT_STRING.line, newString.toString());
	};

new_object returns[NewObject result]
	:	^(NEW type)
	{
		result = new NewObject($NEW.line, $type.result);
	};
	
type returns [String result]
	: ^(TYPE INT) { result = "Int"; }
	| ^(TYPE STRING) {result = "String"; }
	| ^(TYPE ID) { result = $ID.text;   }
	;