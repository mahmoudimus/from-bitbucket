def ply_parser(module, ast_module):
    import ast
      
    def define(module, funcs):
        for f in funcs:
            setattr(module, f.__name__, f)
    
    def create_list(*items):
        
        if len(items) == 0:
            _list = ast.List()
        elif len(items) == 1:
            _list = list()
            _list.extend([items[0]])
        else:
            _list = items[1]
            _list.insert(0, items[0]) 
            
        return _list
    
    def create_ast(_ast_name, **kws):
        constructor = getattr(ast_module, _ast_name)
        ast = constructor()
        if hasattr(ast, 'init_ast'):
            ast.init_ast(**kws)
        else:
            for k, v in kws.items():
                setattr(ast, k, v)
        return ast
    
    def p_error(t):
        if t:
            print('Unexpected token ' + str(t))
        else:
            print('Unexpected EOF')
    
    define(module, [p_error, create_list, create_ast])
    
    from ply import yacc, lex
    lexer=lex.lex(module=module)
    return yacc.yacc(module=module)