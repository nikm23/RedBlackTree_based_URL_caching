/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawler;

/**
 *
 * @author Dell
 */
public class RedBlackNode {
    RedBlackNode left;
    RedBlackNode right;
     String element;
     int color;
 
     /* Constructor */
     public RedBlackNode(String theElement)
     {
         this( theElement, null, null );
     } 
     /* Constructor */
     public RedBlackNode(String theElement, RedBlackNode lt, RedBlackNode rt)
     {
         left = lt;
         right = rt;
         element = theElement;
         color = 1;
     }
}
