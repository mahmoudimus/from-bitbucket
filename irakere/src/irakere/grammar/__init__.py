from irakere.ast import List, AST, Token
from irakere.text import sep
from irakere.lang import concat, default
import abc

LIST_FIELD_ID = 'list'
LIST_ELEM_FIELD_ID = 'item'

class Grammar(AST):
    '''
    EBNF grammar with some special elemnts like field_id and inline symbols.
    '''
    def __init__(self):
        AST.__init__(self)
        self.name = None
        self.rules = []
        self.__rules_dict = {}
        self.__id = 0

    def build(self):
        '''
        Build the grammar.
        '''
        self.__rules_dict = {}
        
        for r in self.rules:
           self.declare_rule(r, append=False)
        
        for r in self.rules:
            r.build(self)
    
    def declare_rule(self, rule, append = True, first=False):
        '''
        Declare a rule in the grammar.
        '''
        self.__rules_dict[str(rule.name)] = rule
        if append: 
            if first:
                self.rules.insert(0, rule)
            else:
                self.rules.append(rule)
    
    def declare_list_rule(self, elem_alts, count):
        '''
        Declare a rule that produce a list of symbols and return the
        associated non terminal.
        '''
        name = '__list__' + str(self.__id)
        list_symbol = NonTerminal(name=name)
        self.__id += 1
        
        rule = Rule()
        rule.name = name
        rule.ast_name = 'List'
        
        for elem_alt in elem_alts:
            alt = Alt()
            alt.elems = Alt.join(elem_alt, [FieldElem(field_id=LIST_FIELD_ID, base=list_symbol)])
            rule.alts.append(alt)

        rule.alts += elem_alts
        
        self.declare_rule(rule)
        
        return list_symbol
        
    def to_bnf(self):
        '''
        Return a BNF equivalent grammar.
        '''
        self.build()
        grammar = Grammar()
        grammar.name = self.name
        first = True
        for rule in self.rules:
            bnf_rule = rule.to_bnf(grammar)
            grammar.declare_rule(bnf_rule,first=first)
            first = False
        
        grammar.build()
        
        return grammar
        
    def __contains__(self, rule):
        return rule in self.__rules_dict
        
    def __str__(self):
        s = ''
        if self.name:
            s += 'grammar ' + str(self.name) + '\n'
        for r in self.rules:
            s += '\n' + str(r) + '\n'
        return s
    
    def __iter__(self):
        return self.rules.__iter__()

class Alts(AST):
    __metaclass__ = abc.ABCMeta
   
    def __init__(self):
        AST.__init__(self)
        self.alts = []
    
    @abc.abstractmethod
    def build(self, grammar):
        pass
            
    def __iter__(self):
        return self.alts.__iter__()    


class SubRule(Alts):
    def __init__(self):
        Alts.__init__(self)
        self.alts = []
        self.grammar = None
    
    def build(self, grammar):
        for alt in self:
            alt.build(grammar)
    
    def to_bnf(self, grammar):
        '''
        Return a set of bnf alts.
        '''
        alts = []
        for alt in self:
            new_alts = alt.to_bnf(grammar)
            alts += new_alts
        return alts
        
    
    def update_field_id(self, field_id):
        for alt in self:
            if len(alt) == 1:
                f = FieldElem()
                f.base = alt[0]
                alt[0] = f
                f.update_field_id(field_id)
    
    def __str__(self):
        s = concat(self.alts, ' | ')
        return '(' + s + ')'
        
class Rule(Alts):
    def __init__(self):
        Alts.__init__(self)
        self.name = None
        self.ast_name = None
    
    def build(self, grammar):
        for alt in self:
            alt.build(grammar) 
    
    def to_bnf(self, grammar):
        '''
        Add to grammar the bnf rules associated to this.
        '''
        rule = Rule()
        rule.name = self.name
        rule.ast_name = self.ast_name
        rule.alts = []
        
        for alt in self:
           bnf_alts = alt.to_bnf(grammar)
           rule.alts += bnf_alts
        
        return rule

    def __str__(self):
        s = str(self.name) 
        if self.ast_name:
            s += '(' + self.ast_name + ')'
        s += ' :\n\t\t' + concat(self.alts, '\n\t|\t')
        return s
    
class ASTCreation(object):
    def __init__(self, ast_name):
        self.ast_name = ast_name
        self.__fields = []
    
    def add_field_init(self, name, value):
        '''
        Add a field init. A field init is a tuple (name, value).
        The value can be the index in the alt or an ASTCreation, in the case of
        inline symbols.
        '''
        self.__fields.append((name, value))
    
    def __str__(self):
        s = str(self.ast_name) + '('
        
        for field in self:
            name, index = field
            s += str(name) + "=" + str(index) + " "
        s += ')'
        return str(s)
    def __len__(self):
        return self.__fields.__len__()
    
    def __iter__(self):
        return self.__fields.__iter__()
            
class Alt(AST):

    @staticmethod
    def join(alt_a, alt_b):
        '''
        Join two alts:
        'a b c' joined with 'd e' is 'a b c d e'
        '''
        alt = Alt()
        alt.extend(alt_a)
        alt.extend(alt_b)
        return alt
        
    def __init__(self, elems = None):
        AST.__init__(self)
        self.elems = default(elems, [])
        
    def build(self, grammar):
        for elem in self:
            elem.build(grammar)
    
    def to_bnf(self, grammar):
        alts = [Alt()]
        for elem in self:
            elem_alts = elem.to_bnf(grammar)
            new_alts = []
            for alt in alts:
                for elem_alt in elem_alts:
                    new_alt = Alt.join(alt, elem_alt)
                    new_alts.append(new_alt)
            alts = new_alts
        return alts
    
    def get_creation(self, ast_name, index=0):
    
        def elem_field(elem, index):            
            base = elem.base
            if isinstance(base, InlineSymbol):
                alt = base.alt
                return str(elem.field_id), get_creation(base.name, base.alt, index)
            return str(elem.field_id), index              
        
        if ast_name is None and len(self) == 1 and isinstance(self[0], NonTerminal):
            symbol = self[0]
            if isinstance(symbol, InlineSymbol):
                return symbol.alt.get_creation(symbol.name, index)
            return "ABSTRACT"
        
        creation = ASTCreation(ast_name)
        i = index
        for elem in self:
            if isinstance(elem, FieldElem):
                name, value = elem_field(elem, i)
                creation.add_field_init(name, value)
            i += 1
        return creation
                    
    def extend(self, elems):
        self.elems.extend(elems)
    
    def append(self, elem):
        self.elems.append(elem)
        
    def __len__(self):
        return len(self.elems)

    def __getitem__(self, index):
        return self.elems[index]
    
    def __setitem__(self, index, value):
        self.elems[index] = value
    
    def __iter__(self):
        return self.elems.__iter__()
    
    def __str__(self):
        s = concat(self.elems)
        return s
    
class AltComplexElem(AST):
    def __init__(self, base=None):
        AST.__init__(self)
        self.base = base
    
    def build(self, grammar):
        self.base.build(grammar)
    
    def __nonzero__(self):
        return bool(self.base)

class ListElem(AltComplexElem):
    def __init__(self):
        AltComplexElem.__init__(self)
        self.count = None
    
    def to_bnf(self, grammar):
        alts = self.base.to_bnf(grammar)
        count = str(self.count)
        if count == '?':
            alts.append(Alt())
        elif count in ('+', '*'):
            symbol = grammar.declare_list_rule(alts, self.count)
            alt = Alt()
            alt.elems = [symbol]
            alts = [alt]
            if count == '*':
                alts.append(Alt())
        
        return alts
        
    def __str__(self):
        s = str(self.base)
        if self.count: 
            s = '(' + s +')' + str(self.count)
        return s

class FieldElem(AltComplexElem):
    def __init__(self, field_id=None, base=None):
        AltComplexElem.__init__(self, base=base)
        self.field_id = field_id
        
    def build(self, grammar):
        AltComplexElem.build(self, grammar)
        if self.field_id:
            self.update_field_id(self.field_id)
    
    def update_field_id(self, field_id):
        if not self.field_id:
            self.field_id = field_id
        
        if hasattr(self.base, 'update_field_id'):
            self.base.update_field_id(field_id=self.field_id)
    
    def to_bnf(self, grammar):
        alts = self.base.to_bnf(grammar)
        
        for i, alt in enumerate(alts):
            if len(alt) == 1:
                field = FieldElem()
                field.field_id = self.field_id
                field.base = alt
                field.reduce()
                alts[i] = Alt(elems=[field])
                
        return alts
    
    def reduce(self):
        '''
        Reduce the field base elem to the minimal base. For example:
        
        (("+"):op):xx 
        
        is reudced to
        
        "+":op
        
        '''
        if isinstance(self.base, Alt) and len(self.base) == 1:
            self.base = self.base[0]
        if isinstance(self.base, FieldElem):
            base_field = self.base
            base_field.reduce()
            self.base = base_field.base
            self.field_id = base_field.field_id
            
            
    def __str__(self):
        s = str(self.base)
        if self.field_id and (not isinstance(self.base, FieldElem) or self.base.field_id != self.field_id):
            s += ':' + str(self.field_id)
        
        return s
        
class Symbol(AST):
    '''
    A symbol, non terminal or terminal.
    '''
    def __init__(self, name=None):
        AST.__init__(self)
        self.name = name
    
    def build(self, grammar):
        '''
        Change the class of the symbol to non terminal or terminal depends his name and the defined rules
        in the grammar.
        '''
        if str(self.name) in grammar:
            self.__class__ = NonTerminal
        else:
            self.__class__ = Terminal
    
    def to_bnf(self, grammar):
        alt = Alt()
        alt.append(self)
        return [alt]
            
    def __str__(self):
        return str(self.name)

class NonTerminal(Symbol):
    def __str__(self):
        return '<' + Symbol.__str__(self) + '>'

class Terminal(Symbol):
    pass

class Literal(Terminal):
    def __init__(self):
        self.value = None
        
    def __str__(self):
        return str(self.value)
    
class InlineSymbol(NonTerminal):
    def __init__(self, alt=None):
        NonTerminal.__init__(self)
        self.alt = default(alt, Alt())
        
    def build(self, grammar):
        self.alt.build(grammar)
    
    def to_bnf(self, grammar):
        alts = []
        base_alts = self.alt.to_bnf(grammar)
        for alt in base_alts:
            symbol = InlineSymbol(alt=alt)
            symbol.name = self.name
            alts.append(Alt(elems=[symbol]))
        
        return alts
        
        
    def __str__(self):
        return '<' + str(self.name)  + ' => ' + str(self.alt) + '>'