grammar Ex2;
@header {
        import java.util.HashMap;
    }
@members {
	public class Value{
		public int intValue;
		public float floatValue;
		public String type = "";
		public Value(int val) {
			this.intValue = val;
			type = "int";
		}
		public Value(float val) {
			this.floatValue = val;
			type = "float";
		}
	}
        HashMap memory = new HashMap();
    }
prog: stat+ ;

stat
    : expr NEWLINE {System.out.println($expr.value.type == "int"? $expr.value.intValue : $expr.value.floatValue);}
    | ID '=' expr NEWLINE
        { memory.put($ID.text, $expr.value); }
    | NEWLINE
    ;

expr returns [Value value]
    : e=term {$value = $e.value;}
    ( '+' e=term {
    			if ($value.type == "int") {
    				if ($e.value.type == "int"){
    					$value = new Value($value.intValue + $e.value.intValue);
    				}else {
    					$value = new Value($value.intValue + $e.value.floatValue);
    				}
    	 		} else {
    	 			if ($e.value.type == "int"){
    					$value = new Value($value.floatValue + $e.value.intValue);
    				}else {
    					$value = new Value($value.floatValue + $e.value.floatValue);
    				}
    			}
    		}
      | '-' e=term {
      			if ($value.type == "int") {
    				if ($e.value.type == "int"){
    					$value = new Value($value.intValue - $e.value.intValue);
    				}else {
    					$value = new Value($value.intValue - $e.value.floatValue);
    				}
    	 		} else {
    	 			if ($e.value.type == "int"){
    					$value = new Value($value.floatValue - $e.value.intValue);
    				}else {
    					$value = new Value($value.floatValue - $e.value.floatValue);
    				}
    			}
      			}
    )*;

term returns [Value value]
    : e=fact {$value = $e.value; }
    ( '*' e=fact {
    			if ($value.type == "int") {
    				if ($e.value.type == "int"){
    					$value = new Value($value.intValue * $e.value.intValue);
    				}else {
    					$value = new Value($value.intValue * $e.value.floatValue);
    				}
    	 		} else {
    	 			if ($e.value.type == "int"){
    					$value = new Value($value.floatValue * $e.value.intValue);
    				}else {
    					$value = new Value($value.floatValue * $e.value.floatValue);
    				}
    			}
    		}
      | '/' e=fact {
      			if ($value.type == "int") {
    				if ($e.value.type == "int"){
    					$value = new Value($value.intValue / $e.value.intValue);
    				}else {
    					$value = new Value($value.intValue / $e.value.floatValue);
    				}
    	 		} else {
    	 			if ($e.value.type == "int"){
    					$value = new Value($value.floatValue / $e.value.intValue);
    				}else {
    					$value = new Value($value.floatValue / $e.value.floatValue);
    				}
    			}
    		}
    )*;

fact returns [Value value]
    : INT {$value = new Value(Integer.parseInt($INT.text));}
    | FLOAT {$value = new Value(Float.parseFloat($FLOAT.text));}
    | ID
        {
            Value v = (Value)memory.get($ID.text);
            if ( v!=null ) $value = v;
            else System.err.println("undefined variable "+$ID.text);
        }
    | '(' expr ')' {$value = $expr.value;}
    ;

ID : ('a'..'z'|'A'..'Z')+ ;
INT : '0'..'9'+ ;
FLOAT	:	'0'..'9'+ '.' '0'..'9'+;
NEWLINE:'\r'? '\n' ;
WS : (' '|'\t')+ {skip();} ;
