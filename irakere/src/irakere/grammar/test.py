'''
Created on Jan 20, 2010

@author: arian
'''

import irakere.grammar.parser as parser
from irakere.ply import ply_parser
import irakere.grammar as ast_provider

f = open('irakere.gr')
src = f.read()
f.close()

parser = ply_parser(parser, ast_provider)
bnf = parser.parse(src).to_bnf()

import irakere.ply.gr2ply as gr2ply

gen = gr2ply.PlyParserGenerator(bnf)

print(gen.generate())

print('done!')