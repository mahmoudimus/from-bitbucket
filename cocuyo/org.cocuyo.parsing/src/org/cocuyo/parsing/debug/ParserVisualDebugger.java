package org.cocuyo.parsing.debug;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

public class ParserVisualDebugger extends JFrame
{

    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException
    {
	final ParserVisualDebugger _frame = new ParserVisualDebugger();
	_frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	_frame.setPreferredSize(new Dimension(400, 400));
	_frame.setSize(_frame.getPreferredSize());

	DefaultMutableTreeNode _node = new DefaultMutableTreeNode(
		"<html><b>Raiz</b> <a href='www.raiz.com'>www.raiz.com</a></html>");
	DefaultMutableTreeNode _node2 = new DefaultMutableTreeNode("Hola");
	_node.add(_node2);
	_node2.add(new DefaultMutableTreeNode("La Bola"));

	JTree _tree = new JTree(_node);
	_tree.getSelectionModel().setSelectionMode(
		TreeSelectionModel.SINGLE_TREE_SELECTION);
	_frame.setTitle("Hola");
	_frame.add(_tree);
	_frame.setVisible(true);
    }
}
