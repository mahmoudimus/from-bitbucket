'''
Created on Jun 24, 2009

@author: arian.fornaris
'''

def valueof(key, dict, default):
    '''
    If key in dict, return dict[key]; else return default
    '''
    if key in dict:
        return dict[key]
    return default

def default(value, default_value):
    if value is None:
        return default_value
    return value

def concat(iterable, sep = ' '):
    s = ''
    first = True
    for i in iterable:
         if not first:
             s += sep
         first = False
         s += str(i)
    return s

def assertion(pre = lambda *vars, **kws:True, pos = lambda *vars, **kws:True):
    def decorator(f):        
        def wrapper(*vars, **kws):
            class Args(object):        
                def __init__(self):
                    import inspect
                    f_spec = inspect.getargspec(f)    
                    if f_spec.args:    
                        for i, name in enumerate(f_spec.args):
                            if f_spec.keywords and name in f_spec.keywords:
                                self.__dict__[name] = kws[name]
                            else:
                                self.__dict__[name] = vars[i]
                    if f_spec.keywords:
                        for name, value in kws.items():
                            self.__dict__[name] = value
                    self.vars = vars
                    self.kws = kws                                    
                    print(vars)
                    print(kws)
            args = Args()
            if not pre(args):
                raise AssertionError("Precondition violation at '" + f.__name__ +    "'")            
            args.res = f(*vars, **kws)
            if not pos(args):
                raise AssertionError("Poscondition violation at '" + f.__name__ +    "'")            
            return args.res
        return wrapper
    return decorator

def once(f): 
    import functools   
    @functools.wraps(f)
    def wrapper(self):
        name = '__' + f.__name__ + '_value__'
        if hasattr(self, name):
            return getattr(self, name)
        else:
            value = f(self)
            setattr(self, name, value)
            return value
    return wrapper

def just_one_time2(f):
    name = '__' + f.__name__ + '_just_one__'
    def wrapper(self, *args, **kws):
        if hasattr(self, name):
            raise Exception("The method '" + f.__name__ + "' can be executed just one time")
        else:
            setattr(self, name, True)
            return f(self, *args, **kws)
    return wrapper

if __name__ == '__main__':    
    class A:
        @assertion(pre=lambda args: bool(args.vars)
                   , pos=lambda args: args.res == args.a + args.b)
        def sum(self, a, b, *l):
            return a + b
    a = A()
    a.sum(1,2)