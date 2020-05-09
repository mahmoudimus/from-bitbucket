'''
Created on Dec 3, 2009

@author: arian
'''
def print_visitor(parent, depth, current):
    s = ' ' * depth + str(current)
    print(s)
    
class AST(object):
    def __init__(self):
        self.children = []
    
    def init_ast(self, **kws):
        for k, v in kws.items():
            setattr(self, k, v)
            self.children.append(v)
        
    def __str__(self):
        s = self.__class__.__name__ 
        return s
    
    def visit_root(self, visitor):
        self.visit(None, 0, visitor);
    
    def visit(self, parent, depth, visitor):
        visitor(parent, depth, self)
        for child in self.children:
            if hasattr(child, 'visit'):
                child.visit(self, depth + 1, visitor)
            else:
                visitor(self, depth + 1, child)

class Token(AST):
    def __init__(self):
        AST.__init__(self)
        self.value = None
        
    def __str__(self):
        return str(self.value)
    
    def __nonzero__(self):
        return bool(self.value)
    
    def visit(self, parent, depth, visitor):
        visitor(parent, depth, self.value)

class List(AST, list):
    def __init__(self):
        self.item = None
        self.elems = []
        AST.__init__(self)
        list.__init__(self)
        
    def init_ast(self, **kws):
        if 'list' in kws:
            self.extend(kws['list'])
                
        if 'item' in kws:
            self.item = kws['item']
            self.insert(0, self.item)
    
    def __str__(self):
        return '[]'
    
    def visit(self, parent, depth, visitor):
        visitor(parent, depth, self)
        for i in self:
            if hasattr(i, 'visit'):
                i.visit(self, depth + 1, visitor)
            else:
                visitor(self, depth + 1, i)