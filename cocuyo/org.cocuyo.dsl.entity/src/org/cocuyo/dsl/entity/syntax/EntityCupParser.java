
//----------------------------------------------------
// The following code was generated by CUP v0.11a beta 20060608
// Mon Jan 05 14:16:31 EST 2009
//----------------------------------------------------

package org.cocuyo.dsl.entity.syntax;

import java_cup.runtime.*;

/** CUP v0.11a beta 20060608 generated parser.
  * @version Mon Jan 05 14:16:31 EST 2009
  */
public class EntityCupParser extends java_cup.runtime.lr_parser {

  /** Default constructor. */
  public EntityCupParser() {super();}

  /** Constructor which sets the default scanner. */
  public EntityCupParser(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public EntityCupParser(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\044\000\002\002\007\000\002\002\004\000\002\002" +
    "\005\000\002\002\005\000\002\002\003\000\002\003\007" +
    "\000\002\004\003\000\002\004\003\000\002\005\010\000" +
    "\002\005\006\000\002\006\006\000\002\006\005\000\002" +
    "\007\007\000\002\007\004\000\002\007\006\000\002\007" +
    "\003\000\002\010\003\000\002\011\005\000\002\011\003" +
    "\000\002\012\005\000\002\013\003\000\002\013\003\000" +
    "\002\013\003\000\002\014\006\000\002\014\006\000\002" +
    "\015\005\000\002\015\003\000\002\016\003\000\002\017" +
    "\004\000\002\017\002\000\002\020\005\000\002\020\002" +
    "\000\002\021\004\000\002\021\002\000\002\022\004\000" +
    "\002\022\002" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\107\000\012\002\uffe4\021\005\022\006\023\011\001" +
    "\002\000\004\002\ufffd\001\002\000\004\027\013\001\002" +
    "\000\004\027\013\001\002\000\006\002\uffe4\021\005\001" +
    "\002\000\004\002\023\001\002\000\004\027\013\001\002" +
    "\000\010\002\uffe4\021\005\022\020\001\002\000\040\002" +
    "\uffe6\004\uffe6\005\uffe6\006\uffe6\007\uffe6\011\uffe6\013\uffe6" +
    "\014\uffe6\015\uffe6\016\uffe6\017\uffe6\020\uffe6\021\uffe6\022" +
    "\uffe6\027\uffe6\001\002\000\032\002\uffe7\004\uffe7\005\015" +
    "\006\uffe7\011\uffe7\013\uffe7\015\uffe7\017\uffe7\020\uffe7\021" +
    "\uffe7\022\uffe7\027\uffe7\001\002\000\004\027\013\001\002" +
    "\000\030\002\uffe8\004\uffe8\006\uffe8\011\uffe8\013\uffe8\015" +
    "\uffe8\017\uffe8\020\uffe8\021\uffe8\022\uffe8\027\uffe8\001\002" +
    "\000\004\002\uffff\001\002\000\004\027\013\001\002\000" +
    "\006\002\uffe4\021\005\001\002\000\004\002\001\001\002" +
    "\000\004\002\000\001\002\000\004\002\uffe5\001\002\000" +
    "\006\002\uffe4\021\005\001\002\000\004\002\ufffe\001\002" +
    "\000\012\004\030\006\uffe2\017\uffe2\020\uffe2\001\002\000" +
    "\004\027\013\001\002\000\010\006\035\017\034\020\uffe0" +
    "\001\002\000\010\006\035\017\034\020\uffe0\001\002\000" +
    "\010\006\ufffa\017\ufffa\020\ufffa\001\002\000\004\027\013" +
    "\001\002\000\004\027\013\001\002\000\010\006\ufffb\017" +
    "\ufffb\020\ufffb\001\002\000\004\020\040\001\002\000\006" +
    "\002\ufffc\021\ufffc\001\002\000\004\007\042\001\002\000" +
    "\006\024\045\027\013\001\002\000\020\006\ufff2\011\047" +
    "\013\046\015\ufff2\017\ufff2\020\ufff2\027\ufff2\001\002\000" +
    "\010\006\uffea\017\uffea\020\uffea\001\002\000\010\006\uffe9" +
    "\017\uffe9\020\uffe9\001\002\000\004\027\013\001\002\000" +
    "\016\006\ufff1\013\ufff1\015\ufff1\017\ufff1\020\ufff1\027\ufff1" +
    "\001\002\000\016\006\ufff4\013\051\015\ufff4\017\ufff4\020" +
    "\ufff4\027\ufff4\001\002\000\004\027\013\001\002\000\004" +
    "\007\060\001\002\000\004\012\057\001\002\000\006\010" +
    "\055\012\uffef\001\002\000\004\027\013\001\002\000\004" +
    "\012\ufff0\001\002\000\014\006\ufff5\015\ufff5\017\ufff5\020" +
    "\ufff5\027\ufff5\001\002\000\010\024\062\025\063\026\064" +
    "\001\002\000\006\010\uffee\012\uffee\001\002\000\006\010" +
    "\uffed\012\uffed\001\002\000\006\010\uffec\012\uffec\001\002" +
    "\000\006\010\uffeb\012\uffeb\001\002\000\004\012\066\001" +
    "\002\000\014\006\ufff3\015\ufff3\017\ufff3\020\ufff3\027\ufff3" +
    "\001\002\000\012\015\072\016\073\020\uffde\027\013\001" +
    "\002\000\004\014\105\001\002\000\010\015\072\020\uffde" +
    "\027\013\001\002\000\004\027\013\001\002\000\004\027" +
    "\013\001\002\000\004\020\075\001\002\000\010\006\ufff8" +
    "\017\ufff8\020\ufff8\001\002\000\010\015\072\020\uffde\027" +
    "\013\001\002\000\004\020\100\001\002\000\010\006\ufff9" +
    "\017\ufff9\020\ufff9\001\002\000\004\014\102\001\002\000" +
    "\004\027\013\001\002\000\010\015\ufff7\020\ufff7\027\ufff7" +
    "\001\002\000\004\020\uffdf\001\002\000\004\027\013\001" +
    "\002\000\010\015\ufff6\020\ufff6\027\ufff6\001\002\000\004" +
    "\020\uffe1\001\002\000\012\004\030\006\uffe2\017\uffe2\020" +
    "\uffe2\001\002\000\010\006\uffe3\017\uffe3\020\uffe3\001\002" +
    "" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\107\000\010\002\007\003\006\017\003\001\001\000" +
    "\002\001\001\000\004\016\026\001\001\000\006\015\024" +
    "\016\013\001\001\000\006\003\006\017\023\001\001\000" +
    "\002\001\001\000\006\015\011\016\013\001\001\000\006" +
    "\003\006\017\016\001\001\000\002\001\001\000\002\001" +
    "\001\000\006\015\015\016\013\001\001\000\002\001\001" +
    "\000\002\001\001\000\006\015\020\016\013\001\001\000" +
    "\006\003\006\017\021\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\006\003\006\017\025\001" +
    "\001\000\002\001\001\000\004\020\030\001\001\000\006" +
    "\015\107\016\013\001\001\000\012\004\031\005\035\014" +
    "\032\021\036\001\001\000\012\004\031\005\035\014\032" +
    "\021\106\001\001\000\002\001\001\000\004\016\066\001" +
    "\001\000\004\016\040\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\010\007" +
    "\043\015\042\016\013\001\001\000\004\010\047\001\001" +
    "\000\002\001\001\000\002\001\001\000\010\011\064\012" +
    "\053\016\051\001\001\000\002\001\001\000\002\001\001" +
    "\000\010\011\052\012\053\016\051\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\010\011\055" +
    "\012\053\016\051\001\001\000\002\001\001\000\002\001" +
    "\001\000\004\013\060\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\010\006\070\016\067\022\073" +
    "\001\001\000\002\001\001\000\010\006\070\016\067\022" +
    "\103\001\001\000\004\016\100\001\001\000\006\015\075" +
    "\016\013\001\001\000\002\001\001\000\002\001\001\000" +
    "\010\006\070\016\067\022\076\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\010\007\102\015" +
    "\042\016\013\001\001\000\002\001\001\000\002\001\001" +
    "\000\010\007\105\015\042\016\013\001\001\000\002\001" +
    "\001\000\002\001\001\000\004\020\110\001\001\000\002" +
    "\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$EntityCupParser$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$EntityCupParser$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$EntityCupParser$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 1;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}


  /** User initialization code. */
  public void user_init() throws java.lang.Exception
    {
  
    }

  /** Scan to get the next Symbol. */
  public java_cup.runtime.Symbol scan()
    throws java.lang.Exception
    {
 return getScanner().next_token(); 
    }
}

/** Cup generated class to encapsulate user supplied action code.*/
class CUP$EntityCupParser$actions {
  private final EntityCupParser parser;

  /** Constructor */
  CUP$EntityCupParser$actions(EntityCupParser parser) {
    this.parser = parser;
  }

  /** Method with the actual generated action code. */
  public final java_cup.runtime.Symbol CUP$EntityCupParser$do_action(
    int                        CUP$EntityCupParser$act_num,
    java_cup.runtime.lr_parser CUP$EntityCupParser$parser,
    java.util.Stack            CUP$EntityCupParser$stack,
    int                        CUP$EntityCupParser$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$EntityCupParser$result;

      /* select the action based on the action number */
      switch (CUP$EntityCupParser$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 35: // EntityElementList ::= 
            {
              Object RESULT =null;

              CUP$EntityCupParser$result = parser.getSymbolFactory().newSymbol("EntityElementList",16, ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.peek()), RESULT);
            }
          return CUP$EntityCupParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 34: // EntityElementList ::= EntityElement EntityElementList 
            {
              Object RESULT =null;

              CUP$EntityCupParser$result = parser.getSymbolFactory().newSymbol("EntityElementList",16, ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.elementAt(CUP$EntityCupParser$top-1)), ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.peek()), RESULT);
            }
          return CUP$EntityCupParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 33: // ModelElementList ::= 
            {
              Object RESULT =null;

              CUP$EntityCupParser$result = parser.getSymbolFactory().newSymbol("ModelElementList",15, ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.peek()), RESULT);
            }
          return CUP$EntityCupParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 32: // ModelElementList ::= ModelElement ModelElementList 
            {
              Object RESULT =null;

              CUP$EntityCupParser$result = parser.getSymbolFactory().newSymbol("ModelElementList",15, ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.elementAt(CUP$EntityCupParser$top-1)), ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.peek()), RESULT);
            }
          return CUP$EntityCupParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 31: // IncludeList ::= 
            {
              Object RESULT =null;

              CUP$EntityCupParser$result = parser.getSymbolFactory().newSymbol("IncludeList",14, ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.peek()), RESULT);
            }
          return CUP$EntityCupParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 30: // IncludeList ::= LIT_15 Name IncludeList 
            {
              Object RESULT =null;

              CUP$EntityCupParser$result = parser.getSymbolFactory().newSymbol("IncludeList",14, ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.elementAt(CUP$EntityCupParser$top-2)), ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.peek()), RESULT);
            }
          return CUP$EntityCupParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 29: // ModelList ::= 
            {
              Object RESULT =null;

              CUP$EntityCupParser$result = parser.getSymbolFactory().newSymbol("ModelList",13, ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.peek()), RESULT);
            }
          return CUP$EntityCupParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 28: // ModelList ::= Model ModelList 
            {
              Object RESULT =null;

              CUP$EntityCupParser$result = parser.getSymbolFactory().newSymbol("ModelList",13, ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.elementAt(CUP$EntityCupParser$top-1)), ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.peek()), RESULT);
            }
          return CUP$EntityCupParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 27: // Id ::= ID 
            {
              Object RESULT =null;

              CUP$EntityCupParser$result = parser.getSymbolFactory().newSymbol("Id",12, ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.peek()), RESULT);
            }
          return CUP$EntityCupParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 26: // Name ::= Id 
            {
              Object RESULT =null;

              CUP$EntityCupParser$result = parser.getSymbolFactory().newSymbol("Name",11, ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.peek()), RESULT);
            }
          return CUP$EntityCupParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 25: // Name ::= Id LIT_14 Name 
            {
              Object RESULT =null;

              CUP$EntityCupParser$result = parser.getSymbolFactory().newSymbol("Name",11, ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.elementAt(CUP$EntityCupParser$top-2)), ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.peek()), RESULT);
            }
          return CUP$EntityCupParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 24: // Type ::= LIT_13 Id LIT_12 STRING 
            {
              Object RESULT =null;

              CUP$EntityCupParser$result = parser.getSymbolFactory().newSymbol("Type",10, ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.elementAt(CUP$EntityCupParser$top-3)), ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.peek()), RESULT);
            }
          return CUP$EntityCupParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 23: // Type ::= LIT_13 Id LIT_12 FieldType 
            {
              Object RESULT =null;

              CUP$EntityCupParser$result = parser.getSymbolFactory().newSymbol("Type",10, ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.elementAt(CUP$EntityCupParser$top-3)), ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.peek()), RESULT);
            }
          return CUP$EntityCupParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 22: // Literal ::= SYMBOL 
            {
              Object RESULT =null;

              CUP$EntityCupParser$result = parser.getSymbolFactory().newSymbol("Literal",9, ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.peek()), RESULT);
            }
          return CUP$EntityCupParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 21: // Literal ::= NUMBER 
            {
              Object RESULT =null;

              CUP$EntityCupParser$result = parser.getSymbolFactory().newSymbol("Literal",9, ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.peek()), RESULT);
            }
          return CUP$EntityCupParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 20: // Literal ::= STRING 
            {
              Object RESULT =null;

              CUP$EntityCupParser$result = parser.getSymbolFactory().newSymbol("Literal",9, ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.peek()), RESULT);
            }
          return CUP$EntityCupParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 19: // Option ::= Id LIT_12 Literal 
            {
              Object RESULT =null;

              CUP$EntityCupParser$result = parser.getSymbolFactory().newSymbol("Option",8, ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.elementAt(CUP$EntityCupParser$top-2)), ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.peek()), RESULT);
            }
          return CUP$EntityCupParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 18: // TypeOptionList ::= Option 
            {
              Object RESULT =null;

              CUP$EntityCupParser$result = parser.getSymbolFactory().newSymbol("TypeOptionList",7, ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.peek()), RESULT);
            }
          return CUP$EntityCupParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 17: // TypeOptionList ::= Option LIT_11 TypeOptionList 
            {
              Object RESULT =null;

              CUP$EntityCupParser$result = parser.getSymbolFactory().newSymbol("TypeOptionList",7, ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.elementAt(CUP$EntityCupParser$top-2)), ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.peek()), RESULT);
            }
          return CUP$EntityCupParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 16: // Cardinality ::= LIT_10 
            {
              Object RESULT =null;

              CUP$EntityCupParser$result = parser.getSymbolFactory().newSymbol("Cardinality",6, ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.peek()), RESULT);
            }
          return CUP$EntityCupParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 15: // FieldType ::= Name 
            {
              Object RESULT =null;

              CUP$EntityCupParser$result = parser.getSymbolFactory().newSymbol("FieldType",5, ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.peek()), RESULT);
            }
          return CUP$EntityCupParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // FieldType ::= Name LIT_8 TypeOptionList LIT_9 
            {
              Object RESULT =null;

              CUP$EntityCupParser$result = parser.getSymbolFactory().newSymbol("FieldType",5, ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.elementAt(CUP$EntityCupParser$top-3)), ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.peek()), RESULT);
            }
          return CUP$EntityCupParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // FieldType ::= Name Cardinality 
            {
              Object RESULT =null;

              CUP$EntityCupParser$result = parser.getSymbolFactory().newSymbol("FieldType",5, ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.elementAt(CUP$EntityCupParser$top-1)), ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.peek()), RESULT);
            }
          return CUP$EntityCupParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // FieldType ::= Name Cardinality LIT_8 TypeOptionList LIT_9 
            {
              Object RESULT =null;

              CUP$EntityCupParser$result = parser.getSymbolFactory().newSymbol("FieldType",5, ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.elementAt(CUP$EntityCupParser$top-4)), ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.peek()), RESULT);
            }
          return CUP$EntityCupParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // EntityElement ::= Id LIT_7 FieldType 
            {
              Object RESULT =null;

              CUP$EntityCupParser$result = parser.getSymbolFactory().newSymbol("EntityElement",4, ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.elementAt(CUP$EntityCupParser$top-2)), ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.peek()), RESULT);
            }
          return CUP$EntityCupParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // EntityElement ::= LIT_6 Id LIT_7 FieldType 
            {
              Object RESULT =null;

              CUP$EntityCupParser$result = parser.getSymbolFactory().newSymbol("EntityElement",4, ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.elementAt(CUP$EntityCupParser$top-3)), ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.peek()), RESULT);
            }
          return CUP$EntityCupParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // Entity ::= LIT_4 Id EntityElementList LIT_3 
            {
              Object RESULT =null;

              CUP$EntityCupParser$result = parser.getSymbolFactory().newSymbol("Entity",3, ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.elementAt(CUP$EntityCupParser$top-3)), ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.peek()), RESULT);
            }
          return CUP$EntityCupParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // Entity ::= LIT_4 Id LIT_5 Name EntityElementList LIT_3 
            {
              Object RESULT =null;

              CUP$EntityCupParser$result = parser.getSymbolFactory().newSymbol("Entity",3, ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.elementAt(CUP$EntityCupParser$top-5)), ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.peek()), RESULT);
            }
          return CUP$EntityCupParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // ModelElement ::= Type 
            {
              Object RESULT =null;

              CUP$EntityCupParser$result = parser.getSymbolFactory().newSymbol("ModelElement",2, ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.peek()), RESULT);
            }
          return CUP$EntityCupParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // ModelElement ::= Entity 
            {
              Object RESULT =null;

              CUP$EntityCupParser$result = parser.getSymbolFactory().newSymbol("ModelElement",2, ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.peek()), RESULT);
            }
          return CUP$EntityCupParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // Model ::= LIT_2 Id IncludeList ModelElementList LIT_3 
            {
              Object RESULT =null;

              CUP$EntityCupParser$result = parser.getSymbolFactory().newSymbol("Model",1, ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.elementAt(CUP$EntityCupParser$top-4)), ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.peek()), RESULT);
            }
          return CUP$EntityCupParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // EntityCompileUnit ::= ModelList 
            {
              Object RESULT =null;

              CUP$EntityCupParser$result = parser.getSymbolFactory().newSymbol("EntityCompileUnit",0, ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.peek()), RESULT);
            }
          return CUP$EntityCupParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // EntityCompileUnit ::= LIT_1 Name ModelList 
            {
              Object RESULT =null;

              CUP$EntityCupParser$result = parser.getSymbolFactory().newSymbol("EntityCompileUnit",0, ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.elementAt(CUP$EntityCupParser$top-2)), ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.peek()), RESULT);
            }
          return CUP$EntityCupParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // EntityCompileUnit ::= LIT_0 Name ModelList 
            {
              Object RESULT =null;

              CUP$EntityCupParser$result = parser.getSymbolFactory().newSymbol("EntityCompileUnit",0, ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.elementAt(CUP$EntityCupParser$top-2)), ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.peek()), RESULT);
            }
          return CUP$EntityCupParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // $START ::= EntityCompileUnit EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.elementAt(CUP$EntityCupParser$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.elementAt(CUP$EntityCupParser$top-1)).right;
		Object start_val = (Object)((java_cup.runtime.Symbol) CUP$EntityCupParser$stack.elementAt(CUP$EntityCupParser$top-1)).value;
		RESULT = start_val;
              CUP$EntityCupParser$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.elementAt(CUP$EntityCupParser$top-1)), ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$EntityCupParser$parser.done_parsing();
          return CUP$EntityCupParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // EntityCompileUnit ::= LIT_0 Name LIT_1 Name ModelList 
            {
              Object RESULT =null;

              CUP$EntityCupParser$result = parser.getSymbolFactory().newSymbol("EntityCompileUnit",0, ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.elementAt(CUP$EntityCupParser$top-4)), ((java_cup.runtime.Symbol)CUP$EntityCupParser$stack.peek()), RESULT);
            }
          return CUP$EntityCupParser$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number found in internal parse table");

        }
    }
}

