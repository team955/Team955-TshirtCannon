/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

/**
 *
 * @author Fauzi
 */

public class LinkedListDouble {
    
    private class link
    {
        double dVal;
        link next;
        link prev;
    }
    
    private link head;
    private link last;
    private int size;
    
    LinkedListDouble()
    {
        head = null;
        last = null;
        size  = 0;
    }
    
    public void add(double dValue)
    {
        size++;
        link tempLink = new link();
        tempLink.dVal = dValue;
        
        if(head == null)
        {
            head = tempLink;
            last = head;
        }
        
        else
        {
            last.next = tempLink;
            tempLink.prev = last;
            last = tempLink;
        }
    }
    
    public double get(int index)
    {
        link tempLink;
     
        if(index <= (size/2))
        {
            tempLink = head;
            
            for(int i = 0; i < index; ++i)
                tempLink = tempLink.next;
        }
        
        else
        {
            tempLink = last;
            
            for(int i = 1; i < (size-index); ++i)
                tempLink = tempLink.prev;    
        }
        
        return tempLink.dVal;
    }
    
    private link getObject(int index)
    {
        link tempLink;
     
        if(index <= (size/2))
        {
            tempLink = head;
            
            for(int i = 0; i < index; ++i)
               tempLink = tempLink.next;
        }
        
        else
        {
            tempLink = last;
            
            for(int i = 1; i < (size-index); ++i)
                tempLink = tempLink.prev;    
        } 
        
        return tempLink;
    }
        
    public void remove(int index)
    {
        size--;
        link tempLink = getObject(index);
        
        if(tempLink.next != null)
            tempLink.next.prev = tempLink.next;
        
        if(tempLink.prev != null)
            tempLink.prev.next = tempLink.prev;
        
        if(index == size)
            last = tempLink.prev;
        
        if(index == 0)
            head = tempLink.next;
        
        tempLink.next = null;
        tempLink.prev = null;
    }
    
    public void replace(int index, double dReplaceWith)
    {
        link tempLink = getObject(index);
        tempLink.dVal = dReplaceWith;
    } 
    
    public void deleteAll()
    {
        while(head.next != null)
        {
            head = head.next;
            head.prev.next = null;
            head.prev = null;
        }
        
        head = null;
        last = null;
        size = 0;
    }
    
    public int size()
    {
        return size;
    }    
}