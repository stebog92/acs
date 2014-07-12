
//import java_cup.runtime.*;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.HashMap;
import java.util.ArrayList;
// import .TuringSym;
// import static .TuringSym.*;

%%

%class TuringLex

%unicode
%line
%column

// %public
%final
// %abstract

// %cupsym TuringSym
// %cup
// %cupdebug
%standalone
%init{
	// TODO: code that goes to constructor
%init}

%{
/*	private Symbol sym(int type)
	{
		return sym(type, yytext());
	}

	private Symbol sym(int type, Object value)
	{
		return new Symbol(type, yyline, yycolumn, value);
	}

	private void error()
	throws IOException
	{
		throw new IOException("illegal text at line = "+yyline+", column = "+yycolumn+", text = '"+yytext()+"'");
	}*/
	public Alphabet alphabet = new Alphabet();
	public HashMap<String, ArrayList<String>> variableNames = new HashMap<String, ArrayList<String>>();
%}
chars = [a-zA-Z0-9 @*#$]
Alphabet = 	alphabet" "?::" "?[a-zA-Z0-9 @*#$]*;[\n]*
name = 		[a-zA-Z0-9_]*
elem = name
elementary = L | R | "L("\!?<?({chars})*>?")" | R\(\!?<?({chars})*>?\)
mt_call =	\[({name}|{chars})\][ \t\n]*|\[{elementary}\][ \t\n]* | \[<{name}>\][ \t\n]* | \[&{name}\][ \t\n]*
Machine_decl =	{name}" "?::=[ \n\t]*
mt_assign = {name}@
var_assign = {name}@
set = "{"[ ]*{chars}([,]" "?{chars}[ ]*)*"}" | <{name}> | "{"[ ]*<({chars})*>([,]" "?<({chars})*>[ ]*)*"}" 
reference = &{name}[ ]*
mt = {mt_call} | {mt_assign}{mt_call} | {reference} 
transitions = \([\n\t ]*{var_assign}?\!?{set}[ \t]*"-> "|[ ]*{var_assign}?\!?{set}[ \t]*"->"[ ]*
comments = ;([a-zA-Z0-9 @*#->]|>|\^)*
variable = {name}[ ]*=[ ]*{chars}[ ]*;[\n]*
set_decl = {name}[ ]*":="[ ]*{set}[ ]*;[\n]*
white_spaces = [ \t\n]*


%x construction
%%
{Alphabet}		{ 	String txt = yytext();
					for (int i = 8; i < txt.length(); i++) {
						if (txt.charAt(i) == ';') {
							break;
						}
						if (txt.charAt(i) != ' ' && txt.charAt(i) != ':') {
							alphabet.add(txt.charAt(i));
						} 
					}
					//System.out.println(alphabet.toString());
				}
{Machine_decl}/([a-zA-Z0-9\[] | [(])	{	
					//System.out.println(yytext());
					int i;
					for(i = 0; i < yytext().length() && yytext().charAt(i) != ' ' && yytext().charAt(i) != ':'; i++);
					Turing.turingMachineInConstruction = new TuringMachine(yytext().substring(0, i));
					//System.out.println("here");
					yybegin(construction);
					
				}
^{comments}[\n]*		{
						}
^{white_spaces}			{
						}
{variable}				{
							String text = yytext();
							StringTokenizer stokenizer = new StringTokenizer(text, " =;");
							Turing.variables.put(stokenizer.nextToken(), stokenizer.nextToken());
						}
{set_decl}				{
							String text = yytext();
							StringTokenizer stokenizer = new StringTokenizer(text, " :=");
							int begin = text.indexOf("{");
							int end = text.indexOf("}");
							Turing.variables.put(stokenizer.nextToken(), text.substring(begin, end + 1));
						}

<construction>{mt_call}	{
							//System.out.println(yytext());
							StringBuilder initialText = new StringBuilder(yytext());
							if (initialText.toString().contains("<")) {
									int begin = yytext().indexOf("<");
									int end = yytext().indexOf(">");
									initialText.replace (begin, end + 1, Turing.variables.get(initialText.substring(begin + 1, end)));
							}
							final String text = initialText.toString();
							
							if (text.contains("[R]")) {
								Turing.turingMachineInConstruction.getLastState().addTuringMachine(new TuringMachine("R") {
									@Override
									public void execute() {
										
										Turing.position++;
									}
								});
							} else
							if (text.contains("[L]")) {
								Turing.turingMachineInConstruction.getLastState().addTuringMachine(new TuringMachine("L") {
									@Override
									public void execute() {
										Turing.position--;
									}
								});
							} else
							if (text.contains("[R(!")) {
								Turing.turingMachineInConstruction.getLastState().addTuringMachine(new TuringMachine(text) {
									private Character otherThan = text.charAt(4);
									@Override
									public void execute() {
										
										Turing.position++;
										while (Turing.bus.charAt(Turing.position) == otherThan) {
											Turing.position++;
										}
									}
								});
							} else
							if (text.contains("[L(!")) {
								Turing.turingMachineInConstruction.getLastState().addTuringMachine(new TuringMachine(text) {
									private Character otherThan = text.charAt(4);
									@Override
									public void execute() {
										Turing.position--;
										while (Turing.bus.charAt(Turing.position) == otherThan) {
											Turing.position--;
										}
									}
								});
							} else
							if (text.contains("[R(")) {
								Turing.turingMachineInConstruction.getLastState().addTuringMachine(new TuringMachine(text) {
									private Character upTo = text.charAt(3);
									@Override
									public void execute() {
										Turing.position++;
										while (Turing.bus.charAt(Turing.position) != upTo) {
											Turing.position++;
										}
									}
								});
							} else
							if (text.contains("[L(")) {
								Turing.turingMachineInConstruction.getLastState().addTuringMachine(new TuringMachine(text) {
									private Character upTo = text.charAt(3);
									@Override
									public void execute() {
										
										Turing.position--;
										int initialpos = 0;
										try {
										initialpos = Turing.position;
										while (Turing.bus.charAt(Turing.position) != upTo) {
											Turing.position--;
										}
										} catch (StringIndexOutOfBoundsException e) {
											System.out.println("bus :" + Turing.bus + "\nchar:" + upTo + "\npos" + initialpos + "\nname" + name);
										}
									}
								});
							} else 
							if (text.charAt(0) == '[' && text.charAt(2) == ']') {
								Turing.turingMachineInConstruction.getLastState().addTuringMachine(new TuringMachine(text) {
									private Character toPrint = text.charAt(1);
									@Override
									public void execute() {
										Turing.bus.setCharAt(Turing.position, toPrint);
									}
								});
							} else
							if (text.charAt(0) == '[' && text.contains("[&")) {
								StringBuilder newNameCall = new StringBuilder(text);
								String newName = variableNames.get(text.substring(2,text.length() - 2)).get
														(variableNames.get(text.substring(2,text.length() - 2)).size() - 1);
								newNameCall.replace(2, text.length() - 2, newName);
								Turing.turingMachineInConstruction.getLastState().addTuringMachine(new TuringMachine(newNameCall.toString()) {
									private Character toPrint;
									@Override
									public void execute() {
										String sample = name.substring(2, name.length() - 2);
										toPrint = Turing.variables.get(name.substring(2, name.length() - 2)).charAt(0);
										Turing.bus.setCharAt(Turing.position, toPrint);
									}
								});
							} else
							{
								
								int begin = text.indexOf("[");
								int end = text.indexOf("]");
								
								String text2 = text.substring(begin + 1, end);
								Turing.turingMachineInConstruction.getLastState().addTuringMachine(
									Turing.turingMachines.get(text2));
							}
							//System.out.println(Turing.turingMachineInConstruction.getLastState().toString());
						}
<construction>{mt_assign} / ({mt_call} | {transitions})	{
											String text = yytext();
											Turing.turingMachineInConstruction.declareVar(yytext().substring(0, yytext().length() - 1));
										}

<construction>	{transitions} / {mt}		{
											//System.out.println(yytext()+ "\nhere");
											StringBuilder initialText = new StringBuilder(yytext());
											StringBuilder newSet;
											String text;
											if (initialText.toString().contains("<")) {
												int begin = yytext().indexOf("<");
												int end = yytext().indexOf(">");
												initialText.replace (begin, end + 1, Turing.variables.get(initialText.substring(begin + 1, end)));
											}
																					
											if (initialText.toString().contains("!{")) {
												int begin = initialText.toString().indexOf("{");
												int end = initialText.toString().indexOf("}");
												newSet = new StringBuilder("{");
												String set = initialText.toString().substring(begin, end + 1);
												for (Character x : alphabet.getElements()) {
													if (!set.contains("" + x)) {
														if (newSet.charAt(newSet.length() - 1) == '{') {
															newSet.append (" " + x);
														} else {
															newSet.append (", " + x);
														}
													}
												}
												newSet.append("}");
												int firstB = initialText.indexOf("{");
												int lastB = initialText.indexOf("}");
												initialText.replace (firstB - 1, lastB + 1, newSet.toString());
												text = initialText.toString();
											} else {
												text = initialText.toString();
											}
											State newState = new State(Turing.turingMachineInConstruction);
											if (text.contains("@{")) {
												int begin;
												int end = text.indexOf("@");
												for (begin = 0; text.charAt(begin) == ' ' | text.charAt(begin) == '\n' | 
																							text.charAt(begin) == '('; begin++);
												String varName = text.substring(begin, end);
												if (!variableNames.containsKey(varName)) {
													variableNames.put(varName, new ArrayList<String>());
													variableNames.get(varName).add(varName);
												} else {
													String newName = variableNames.get(varName).get(variableNames.get(varName).size() - 1) + "1";
													variableNames.get(varName).add(newName);
													varName = newName;
												}
												
												Turing.turingMachineInConstruction.getLastState().var = varName;
											}
											int begin = 0;
											if (text.contains("(")) {
												for (begin = 0; text.charAt(begin) != '{'; begin++);
											}
											
											String transitionKey = text.substring(begin, text.indexOf("}") + 1);
											Turing.turingMachineInConstruction.getLastState().addTransition(transitionKey, newState);
											for (int i = 0; i < transitionKey.length() && transitionKey.charAt(i) != '}'; i++) {
												if (transitionKey.charAt(i) != ' ' && transitionKey.charAt(i) != ',' && transitionKey.charAt(i) != '{') {
													Turing.turingMachineInConstruction.getLastState().mapCharToTransition (transitionKey.charAt(i), transitionKey);
												}
											}
											Turing.turingMachineInConstruction.pushState(newState);
									}
<construction> {reference}			{
										int Spacepos = yytext().indexOf(" ");
										if (Spacepos != -1) {
											Turing.turingMachineInConstruction.getLastState().loop_name = yytext().substring(1, Spacepos);
										} else {
											Turing.turingMachineInConstruction.getLastState().loop_name = yytext().substring(1);
										}
									}
<construction> ;"\n"*[ \t]*\)*[ ]*			{
										Turing.turingMachineInConstruction.popState();
										//System.out.println("close");
									}
<construction> [ ]*;;"\n"*				{
										yybegin(YYINITIAL);
										Turing.turingMachines.put(Turing.turingMachineInConstruction.getName(), 
										Turing.turingMachineInConstruction); 
									}

