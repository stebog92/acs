package ro.pub.cs.lcpl;
import java.io.PrintStream;
import java.util.*;

/** A complete LCPL program
 * <i>class</i> ... 
 */
public class Program extends TreeNode {
	/** List of all classes in the program.
	 * Add the built-in classes below to this list: 
	 * Program.objectType, Program.stringType, Program.ioType. */
	private List<LCPLClass> classes;
	
    private ArrayList<String> cstrings = new ArrayList<String>();

    private HashMap<String, ArrayList<String>> vTables;
	/** Create a new IntType object representing the type of all Int expressions in the program. */
	private IntType intType;
	
	/** Create a new NoType object representing the type of all expressions in the program that do not return a value, such as 'while'. */
	private NoType noType;

	/** Create a new NullType object representing the type of all expressions in the program that are evaluated to the constant 'void'. */
	private NullType nullType;
	
	/** Create a new LCPLClass object representing the Object class */
	private LCPLClass objectType;

	/** Create a new LCPLClass object representing the String class */
	private LCPLClass stringType;
	
	/** Create a new LCPLClass object representing the IO class */
	private LCPLClass ioType;

    private HashMap<String, Integer> constantStrings;
    int label = 0;

	public List<LCPLClass> getClasses() {
		return classes;
	}

	public void setClasses(List<LCPLClass> classes) {
		this.classes = classes;
	}

	public IntType getIntType() {
		return intType;
	}

	public void setIntType(IntType intType) {
		this.intType = intType;
	}

	public NoType getNoType() {
		return noType;
	}

	public void setNoType(NoType noType) {
		this.noType = noType;
	}

	public NullType getNullType() {
		return nullType;
	}

	public void setNullType(NullType nullType) {
		this.nullType = nullType;
	}

	public LCPLClass getObjectType() {
		return objectType;
	}

	public void setObjectType(LCPLClass objectType) {
		this.objectType = objectType;
	}

	public LCPLClass getStringType() {
		return stringType;
	}

	public void setStringType(LCPLClass stringType) {
		this.stringType = stringType;
	}

	public LCPLClass getIoType() {
		return ioType;
	}

	public void setIoType(LCPLClass ioType) {
		this.ioType = ioType;
	}

	public Program(int lineNumber, List<LCPLClass> classes) {
		super(lineNumber);
		this.classes = classes;
	}
	public Program () {
        this.constantStrings = new HashMap<String, Integer>();
        this.vTables = new HashMap<String, ArrayList<String>>();
    }

    public int getNextLabel() {
        return label++;
    }

    public int getCurrentLabel() {
        return label;
    }
	
	public void emitIR(PrintStream os)
	{
		os.println(";;;;;;; Standard data types from runtime");
        os.println("%struct.TObject = type { %struct.__lcpl_rtti* }");
        os.println("%struct.TString = type { %struct.__lcpl_rtti*, i32, i8* }");
        os.println("%struct.TIO = type { %struct.__lcpl_rtti* }");
        os.println("%struct.__lcpl_rtti = type { %struct.TString*, i32, %struct.__lcpl_rtti*, [0 x i8*] }");
        os.println("\n");

        os.println("%struct.RObject = type %struct.__lcpl_rtti*");
        os.println("@RObject = external global %struct.RObject");

        os.println("%struct.RString = type %struct.__lcpl_rtti*");
        os.println("@RString = external global %struct.RString");

        os.println("%struct.RIO = type %struct.__lcpl_rtti*");
        os.println("@RIO = external global %struct.RIO");

        os.println("declare void @Object_init(%struct.TObject*)");
        os.println("declare void @M6_Object_abort(%struct.TObject*)");
        os.println("declare %struct.TString* @M6_Object_typeName(%struct.TObject*)");
        os.println("declare %struct.TObject* @M6_Object_copy(%struct.TObject*)");

        os.println("declare void @IO_init(%struct.TIO*)");
        os.println("declare %struct.TString* @M2_IO_in(%struct.TIO*)");
        os.println("declare %struct.TIO* @M2_IO_out(%struct.TIO*, %struct.TString*)");

        os.println("declare void @String_init(%struct.TString*)");
        os.println("declare i32 @M6_String_length(%struct.TString*)");
        os.println("declare i32 @M6_String_toInt(%struct.TString*)");
        os.println("declare %struct.TString* @M6_String_substring(%struct.TString*, i32, i32)");
        os.println("declare %struct.TString* @M6_String_concat(%struct.TString*, %struct.TString*)");
        os.println("declare i32 @M6_String_equal(%struct.TString*, %struct.TString*)");
        os.println("\n");

        os.println("declare i8* @__lcpl_new(%struct.__lcpl_rtti*)");
        os.println("declare void @__lcpl_checkNull(i8*)");
        os.println("declare i8* @__lcpl_cast(i8*, %struct.__lcpl_rtti*)");
        os.println("declare %struct.TString* @__lcpl_intToString(i32)");


		os.println(";;;;;;; Custom code");
        os.println(";");


        os.println("; Structure of classes\n;");
        for (LCPLClass _class : this.classes) {
            if (_class.getName().equals("String") || _class.getName().equals("IO") || _class.getName().equals("Object"))
                continue;
            os.print("%struct.T" + _class.getName() + " = type { %struct.__lcpl_rtti* ");
            printAttributes(os, _class);
            /*for (Feature feature : _class.getFeatures()) {
                if (feature instanceof Attribute) {
                    if (((Attribute)feature).getType().equals("Int")) {
                        os.print(", i32");
                    } else {
                        os.print(", %struct.T" + ((Attribute)feature).getType() + "*");
                    }
                }
            }*/
            os.println("}");
        }

        os.println("; Name of classes");
        for (LCPLClass _class : this.classes) {
            if (_class.getName().equals("String") || _class.getName().equals("IO") || _class.getName().equals("Object"))
                continue;
            os.println("@class_name." + _class.getName() + " = internal constant [" + (_class.getName().length() + 1) + 
                    " x i8] c\"" + _class.getName() + "\\00\"");
            os.println("@N" + _class.getName() + " = global %struct.TString { %struct.__lcpl_rtti* bitcast(%struct.RString* @RString to"
                    + " %struct.__lcpl_rtti*), i32 " + _class.getName().length() + ", i8* getelementptr ([" + (_class.getName().length() + 1) 
                    + " x i8]* @class_name." + _class.getName() + ", i32 0, i32 0) }"); 
        }

        os.println("; String constants used in the program");

        os.println("@string_constant.strEmpty = internal constant [1 x i8] c\"\\00\"\n");
        os.println("@_string_constant.strEmpty = global %struct.TString { %struct.__lcpl_rtti* bitcast(%struct.RString* @RString to %struct.__lcpl_rtti*), i32 0, i8* getelementptr ([1 x i8]* @string_constant.strEmpty, i32 0, i32 0)}\n");

        int nr = 0;
        for (LCPLClass _class : this.classes) {
            if (_class.getName().equals("String") || _class.getName().equals("IO") || _class.getName().equals("Object"))
                continue;
            for (Feature feature: _class.getFeatures()) {
                if (feature instanceof Attribute) {
                    getConstantStringOf(((Attribute)feature).getInit());
                } else {
                    getConstantStringOf(((Method)feature).getBody());
                }
            }
            for (String string : cstrings) {
                int len = string.length();
                string = string.replace("\\", "\\5C").replace("\n", "\\0A").replace("\r", "\\0D").replace("\"", "\\22").replace("\0", "\\00");
                os.println("@string_constant." + nr + " = constant [" + (len + 1) + 
                    " x i8] c\"" + string + "\\00\"");
                os.println("@_string_constant." + nr + " = global %struct.TString { %struct.__lcpl_rtti* bitcast(%struct.RString* @RString to"
                        + " %struct.__lcpl_rtti*), i32 " + len + ", i8* getelementptr ([" + (len + 1) + " x i8]*"
                    + " @string_constant." + nr + ", i32 0, i32 0) }");
                constantStrings.put(string, nr);
                nr++;
            }
            
        }

        os.println("; Runtime type information and virtual tables");
        os.println(";");

        for (LCPLClass _class : this.classes) {
            int method_size = 0;
            int attr_size = 0;
                        
            printVtable(os, _class);
            if (_class.getName().equals("String") || _class.getName().equals("IO") || _class.getName().equals("Object"))
                continue;
            os.println("]}");
        }
        for (LCPLClass _class : this.classes) {
            if (_class.getName().equals("String") || _class.getName().equals("IO") || _class.getName().equals("Object"))
                continue;
            os.println("; Class initializer (constructor) for" + _class.getName());
            os.println("define void @" + _class.getName() + "_init(%struct.T" + _class.getName()+ "* %self) {\n"
                    +"%1 = alloca %struct.T"+ _class.getName() +"*\n"
                    +"store %struct.T" + _class.getName() + "* %self, %struct.T" + _class.getName() +"** %1\n"
                    +"%2 = load %struct.T" + _class.getName() + "** %1\n"
                    +"%3 = bitcast %struct.T" + _class.getName() + "* %2 to %struct.T" + _class.getParent() + "*\n"
                    +"call void @" + _class.getParent()+ "_init(%struct.T"+ _class.getParent()+"* %3)\n");
            
            int attr_var = 4;
            int attr_index = 1;
            for (Feature feature : _class.getFeatures()) {
                if (feature instanceof Attribute) {
                    os.println("%" + attr_var + " = getelementptr %struct.T" + _class.getName() + "* %2, i32 0, i32 " + attr_index);
                    Expression init = ((Attribute)feature).getInit();
                    String type = ((Attribute)feature).getType();
                    if (init == null) {
                        if (type.equals("Int")) {
                            os.println("store i32 0, i32* %" + attr_var);
                        } else if (type.equals("String")) {
                            os.println("store %struct.TString* @_string_constant.strEmpty, %struct.TString** %" + attr_var);
                        }
                    } else {
                        if (init instanceof IntConstant) {
                            os.println("store i32 " + ((IntConstant)init).getValue() + ", i32* %" + attr_var);
                        } else if (init.getType().equals("Int")) {
                             int location = attr_var;
                            attr_var++;
                             
                            attr_var = Integer.parseInt(init.emitIR(os, constantStrings, attr_var, _class, this).substring(1));
                            os.println("store i32 %" + attr_var + ", i32* %" + location);
                        } else if (init instanceof StringConstant) {
                            os.println("store %struct.TString* @_string_constant." + constantStrings.get(((StringConstant)init).getValue()) + 
                                    ", %struct.TString** %" + attr_var); 
                        } else {
                            int location = attr_var;
                            attr_var++;
                             
                            attr_var = Integer.parseInt(init.emitIR(os, constantStrings, attr_var, _class, this).substring(1));
                            os.println("store %struct.T" + init.getTypeData().getName() + "* %" + attr_var + ", %struct.T" + init.getTypeData().getName() + "** %" + location);
                            
                        }
                    }
                    attr_var++;
                    attr_index++;
                }
            }

            os.println("ret void");
            os.println("}");
        }
        os.println("");

        for (LCPLClass _class : this.classes) {
            if (_class.getName().equals("String") || _class.getName().equals("IO") || _class.getName().equals("Object"))
                continue;
            String name = _class.getName();
            for (Feature feature : _class.getFeatures()) {
                if (feature instanceof Attribute)
                    continue;
                Method m = (Method)feature;
                if (m.getReturnType().equals("void")) {
                    os.print("define " +  m.getReturnType() + " @M" + name.length() + "_" + name + "_" + m.getName() +
                        " (%struct.T" + name + "* %self");
                } else if (m.getReturnType().equals("Int")) {
                    os.print("define i32 @M" + name.length() + "_" + name + "_" + m.getName() +
                        " (%struct.T" + name + "* %self");
                } else {
                    os.print("define %struct.T" +  m.getReturnType() + "* @M" + name.length() + "_" + name + "_" + m.getName() +
                        " (%struct.T" + name + "* %self");
                }
                for (FormalParam param : m.getParameters()) {
                    if (param.getType().equals("Int")) {
                        os.print(", i32 %" + param.getName());
                    } else {
                        os.print(", %struct.T" + param.getVariableType().getName() + "* %" + param.getName());
                    }
                }

                int var_name = 2;

                os.println(") {\n"
                        +"\t; Prologue - save parameters\n"
                        +"\t%1 = alloca %struct.T" + name + "*\n"
                        +"\tstore %struct.T" + name + "* %self, %struct.T" + name + "** %1\n");
                
                for (FormalParam param: m.getParameters()) {
                    if (param.getType().equals("Int")) {
                        os.println("\t%" + var_name + " = alloca i32");
                        os.println("\tstore i32 %" + param.getName() + ", i32* %" + var_name);
                    } else {
                        os.println("\t%" + var_name + " = alloca %struct.T" + param.getType() + "*");
                        os.println("\tstore %struct.T" + param.getType() + "* %" + param.getName() + ", %struct.T" + param.getType()+ "** %" + var_name);
                    }
                    
                    param.setVarIndex(var_name);
                    var_name++;
                }

                int index = 0;
                String intConstant = "";
                if (m.getBody() != null) {
                    boolean sc = false, ic = false;
                    for (Expression expr : ((Block)m.getBody()).getExpressions()) {
                        sc = false;
                        if (expr instanceof BaseDispatch) {
                            var_name = Integer.parseInt(expr.emitIR(os, constantStrings, var_name, _class, this).substring(1)) + 1;
                        } else if(expr instanceof LocalDefinition) {
                            var_name = Integer.parseInt(
                                    ((LocalDefinition)expr).emitIR(os, constantStrings, var_name, _class, this).substring(1)) + 1;
                        } else {
                            
                            if (expr.getTypeData().getName().equals("String") && expr instanceof StringConstant) {
                                String temp = expr.emitIR(os, constantStrings, var_name, _class, this);
                                sc = true;
                                var_name = Integer.parseInt(temp.substring(temp.indexOf(".") + 1)) + 1;
                            } else if (expr instanceof IntConstant) {
                                ic = true;
                                intConstant = expr.emitIR(os, constantStrings, var_name, _class, this);
                            } else {
                                var_name = Integer.parseInt(expr.emitIR(os, constantStrings, var_name, _class, this).substring(1)) + 1;
                            }
                        }
                    }
                    if (sc) {
                        os.println("\tret %struct.TString* @_string_constant." + (var_name - 1));
                    } else if (ic) {
                        os.println("\tret i32 " + intConstant);
                    } else if (m.getReturnType().equals("void")) {
                        os.println("\tret void");
                    } else if (m.getReturnType().equals("Int")) {
                        os.println("\tret i32 %" + (var_name - 1));
                    } else {
                        os.println("\tret %struct.T" + m.getReturnType() + "* %" + (var_name - 1));
                    }
                }
                os.println("}");
            }
        }


        os.println("define void @startup() {\n"
                + "\t%1 = call i8* @__lcpl_new(%struct.__lcpl_rtti* bitcast (%struct.RMain* @RMain to %struct.__lcpl_rtti*))\n"
                + "\t%2 = bitcast i8* %1 to %struct.TMain*                       ; Convert char * into destination type\n"
                + "\t; Dispatch to main method\n"
                + "\t%3 = bitcast %struct.TMain* %2 to i8*\n"
                + "\tcall void @__lcpl_checkNull(i8* %3)\n"
                + "\t%4 = getelementptr %struct.TMain* %2, i32 0, i32 0\n"
                + "\t%5 = load %struct.__lcpl_rtti** %4\n"
                + "\t%6 = getelementptr %struct.__lcpl_rtti* %5, i32 0, i32 3\n"
                + "\t%7 = bitcast [0 x i8*]* %6 to i8**");
        int mainIndex = getMethodIndex("Main", "main", null);
        os.println("\t%8 = getelementptr i8** %7, i32 " + mainIndex + "\n"
                + "\t%9 = load i8** %8\n"
                + "\t%10 = bitcast i8* %9 to void (%struct.TMain*)*\n"
                + "\tcall void %10(%struct.TMain* %2)\n"
                + "\tret void\n}");
    }

    public void printAttributes(PrintStream os, LCPLClass _class) {
        if (_class == null) {
            return;
        }
        printAttributes(os, getParent(_class));
        for (Feature feature : _class.getFeatures()) {
                if (feature instanceof Attribute) {
                    if (((Attribute)feature).getType().equals("Int")) {
                        os.print(", i32");
                    } else {
                        os.print(", %struct.T" + ((Attribute)feature).getType() + "*");
                    }
                }
            }
    }


    public void printVtable(PrintStream os, LCPLClass _class) {
        ArrayList<String> methods = getMethods(_class);

        if (!vTables.containsKey(_class.getName())) {
            vTables.put(_class.getName(), methods);
        }
        if (_class.getName().equals("String") || _class.getName().equals("IO") || _class.getName().equals("Object"))
                return;
        int attr = countAttr(_class);

        os.println("%struct.R" + _class.getName() + " = type { %struct.TString*, i32, %struct.__lcpl_rtti*, [" + (methods.size() + 1) + " x i8*] }");
        os.print("@R" + _class.getName() + " = global %struct.R" + _class.getName() + "{ %struct.TString* @N" + _class.getName()
                        + ", i32 " + (4 + attr * 3) + ", %struct.__lcpl_rtti* bitcast(%struct.R" + _class.getParent() + "* @R"
                        + _class.getParent() + " to %struct.__lcpl_rtti*),\n"
                        + "[" + (methods.size() + 1 ) + " x i8*] ["
                        + "i8* bitcast (void (%struct.T" + _class.getName() + "*)* @" + _class.getName() + "_init"+" to i8*)\n");

        for (String method : methods) {
            os.println(",\t" + method);
        }
    }

    public int getMethodIndex(String _class, String method, String type) {
        ArrayList<String> methods = vTables.get(_class);
        for (int i = methods.size() - 1; i >= 0; i--) {
            int begin = methods.get(i).indexOf("@");
            int end = methods.get(i).substring(begin).indexOf(" ");
            String _method = methods.get(i).substring(begin, begin + end);
            int c_begin = _method.indexOf("_") + 1;
            int c_end = _method.substring(c_begin).indexOf("_");
            
            if (type == null) {
                if (_method.substring(c_begin + c_end + 1).equals(method)) {

                    return i + 1;
                }
            } else {
                if (_method.substring(c_begin, c_begin + c_end).equals(type) && _method.substring(c_begin + c_end + 1).equals(method)) {
                    return i + 1;
                }
            }
        }
        return -1;
    }

    public int countAttr(LCPLClass _class) {
        if (_class == null) {
            return 0;
        }
        int count = 0;
        count += countAttr(getParent(_class));
        for (Feature feature: _class.getFeatures()) {
            if (feature instanceof Attribute) {
               count++; 
               ((Attribute)feature).setVarIndex(count);
            }
        }
        return count++;
    }

    public ArrayList<String> getMethods(LCPLClass _class) {
        if (_class == null) {
            return new ArrayList<String>();
        }
        ArrayList<String> methods = new ArrayList<String>();
        methods.addAll(getMethods(getParent(_class)));
        for (Feature feature: _class.getFeatures()) {
            if (feature instanceof Method) {
                String returnType = ((Method)feature).getReturnType();
                if (returnType.equals("Int")) {
                    returnType = "i32";
                } else if (!returnType.equals("void")) {
                    returnType = "%struct.T" + returnType + "*";
                }
                String params = "";
                for (FormalParam param: ((Method)feature).getParameters()) {
                    if (param.getType().equals("Int")) {
                        params += ", i32";
                    } else {
                        params += ", %struct.T" + param.getType() + "*";
                    }
                }
                methods.add("i8* bitcast (" + returnType + "(%struct.T" + _class.getName() + "*" + params + ")* @M" + _class.getName().length()
                        + "_" + _class.getName() + "_" + ((Method)feature).getName() + " to i8*)");
            }
        } 
        return methods;
    }


    public LCPLClass getParent(LCPLClass _class) {
        for (LCPLClass _parent : this.classes) {
            if (_parent.getName().equals(_class.getParent())) {
                return _parent;
            }
        }
        return null;
    }

    public void printMethods(LCPLClass _class, PrintStream os) {
        for (Feature feature: _class.getFeatures()) {
            if (feature instanceof Method) {
                os.println(",\ni8* bitcast (struct.T" + ((Method)feature).getReturnType() + " (%struct.T" + _class.getName()+"*)* @M" + _class.getName().length() + "_"
                        + _class.getName() + "_" + ((Method)feature).getName() + " to i8*)");
            }
        }
    }

    public String getTypeObject(Expression object) {
        if (object instanceof NewObject) {
            return object.getType();
        } else if(object instanceof Symbol) {
            if (((Symbol)object).getVariable() instanceof FormalParam) {
                return ((FormalParam)((Symbol)object).getVariable()).getType();
            } else if (((Symbol)object).getVariable() instanceof Attribute){
                if (((Attribute)((Symbol)object).getVariable()).getInit() == null) {
                    return ((Attribute)((Symbol)object).getVariable()).getTypeData().getName();
                } else {
                    return getTypeObject(((Attribute)((Symbol)object).getVariable()).getInit());
                }
            } else {
                return getTypeObject((Expression)((Symbol)object).getVariable());
            }
        } else if (object instanceof LocalDefinition) {
            if (((LocalDefinition)object).getInit() instanceof Cast) {
                if (((Cast)((LocalDefinition)object).getInit()).getE1() instanceof NewObject) {
                    return ((Cast)((LocalDefinition)object).getInit()).getE1().getType();
                } else {
                    return ((Cast)((LocalDefinition)object).getInit()).getType();
                }
            } else {
                if (((LocalDefinition)object).getInit() == null) {
                    return ((LocalDefinition)object).getType();
                } else {
                    return getTypeObject(((LocalDefinition)object).getInit());
                }
            }
        } else if (object instanceof Cast) {
            //return getTypeObject(((Cast)object).getE1());
            return ((Cast)object).getType();
        } else if (object instanceof BaseDispatch) {
            return object.getType();
        } else if (object instanceof StringConstant) {
            return "String";
        }
        return null;
    }

    public void getConstantStringOf(Expression expression) {
        if (expression instanceof StringConstant) {
            cstrings.add(((StringConstant)expression).getValue());
            return;
        } else if (expression instanceof BinaryOp) {
            getConstantStringOf(((BinaryOp)expression).getE1());
            getConstantStringOf(((BinaryOp)expression).getE2());
        } else if (expression instanceof Assignment) {
            getConstantStringOf(((Assignment)expression).getE1());
        } else if (expression instanceof BaseDispatch) {
            for (Expression e : ((BaseDispatch)expression).getArguments()) {
                getConstantStringOf(e);
            }
            getConstantStringOf(((BaseDispatch)expression).getObject());
        } else if (expression instanceof Block) {
            for (Expression e : ((Block)expression).getExpressions()) {
                getConstantStringOf(e);
            }
        } else if (expression instanceof Cast) {
            getConstantStringOf(((Cast)expression).getE1());
        } else if (expression instanceof IfStatement) {
            getConstantStringOf(((IfStatement)expression).getCondition());
            getConstantStringOf(((IfStatement)expression).getIfExpr());
            getConstantStringOf(((IfStatement)expression).getThenExpr());
        } else if (expression instanceof LocalDefinition) {
            getConstantStringOf(((LocalDefinition)expression).getInit());
            getConstantStringOf(((LocalDefinition)expression).getScope());
        } else if (expression instanceof SubString) {
            getConstantStringOf(((SubString)expression).getStartPosition());
            getConstantStringOf(((SubString)expression).getEndPosition());
            getConstantStringOf(((SubString)expression).getStringExpr());
        } else if (expression instanceof WhileStatement) {
            getConstantStringOf(((WhileStatement)expression).getCondition());
            getConstantStringOf(((WhileStatement)expression).getLoopBody());
        } else if (expression instanceof UnaryOp) {
            getConstantStringOf(((UnaryOp)expression).getE1());
        }
    }
}
