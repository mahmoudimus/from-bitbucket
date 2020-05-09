
from irakere.lang import valueof

NL_CHAR = "\n"

TAB_CHAR = "\t"

class code(object):
    def __init__(self, *children):
        self.__children = children

    def to_str(self, **kws):        
        s = ''
        for child in self:
            if isinstance(child, code):
                child_str = child.to_str(**kws)
            else:
                child_str = str(child)
            s += child_str
        return s

    def __add__(self, value):
        if not isinstance(self.__children, list):
            self.__children = list(self.__children)
        self.__children.append(value)
        return self

    def __radd__(self, value):
        if not isinstance(self.__children, list):
            self.__children = list(self.__children)
        self.__children.insert(0, value)
        return self

    def __or__(self, value):
        return code(self, NL, value)

    def __ror__(self, value):
        return code(value, NL, self)

    def __iter__(self):
        return self.__children.__iter__()
    
    def __str__(self):
        return self.to_str()

class block(code):
    def __init__(self, *children, **kws):
        code.__init__(self, *children)
        self.add_nl = valueof('add_nl', kws, True)

    def to_str(self, indent=0, **kws):
        s = code.to_str(self, indent=indent+1, **kws)
        if self.add_nl:
            s = nl().to_str(indent=indent+1, **kws) + s + nl().to_str(indent=indent, **kws)
        return s

    def __radd__(self, value):
        return code(value, self)
    
class get(code):
    def __init__(self, start_tag, end_tag, *children):
        code.__init__(self, *children)
        self.start_tag = start_tag
        self.end_tag = end_tag

    def to_str(self, old_text='', **kws):
        s = ''
        try:
            start = old_text.index(self.start_tag) + len(self.start_tag)
            end = old_text.index(self.end_tag)
            s = old_text[start:end]
        except ValueError:
            s = code.to_str(self, **kws)
        res = code(self.start_tag, s, self.end_tag)
        return res.to_str(**kws)

class nl(code):
    def __init__(self):
        code.__init__(self)

    def to_str(self, indent=0, **kws):
        s = NL_CHAR + TAB_CHAR * indent
        return s + code.to_str(self, **kws)

    def __add__(self, value):
        return code(nl(), value)

    def __radd__(self, value):
        return code(value, nl())


class sep(code):
    def __init__(self, *children, **kws):
        code.__init__(self, *children)
        self.sepa = valueof('sepa', kws, ' ')
        self.last = valueof('last', kws, False)
        self.generator = valueof('generator', kws, lambda x: x)

    def to_str(self, **kws):
        c = code()
        first = True
        if self.last:
            for child in self:
                c = code(c, self.generator(child), self.sepa)
        else:
            for child in self:
                if not first:
                    c = code(c, self.sepa)
                first = False
                c = code(c, self.generator(child))
                
        return c.to_str(**kws)

NL = nl()