package edu.wpi.first.wpilibj.templates;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package recording;
import edu.wpi.first.wpilibj.*;

class CJoystickMimic{
    
    private class link
    {
        boolean bTurret;
        double dX;
        double dY;
        double dTmr;
        link next;
        link prev;
    }
    
    private link head;
    private link last;
    private link curLink;
    private int iNextIndex;
    private int size;
    
    CJoystickMimic()
    {
        head = null;
        curLink = null;
        last = null;
        iNextIndex = 0;
        size  = 0;
    }
    
    public void add(double dTimer, double dXVal, double dYVal, boolean bTurretStatus)
    {
        size++;
        link tempLink = new link();
        tempLink.dTmr = dTimer;
        tempLink.dX = dXVal;
        tempLink.dY = dYVal;
        tempLink.bTurret = bTurretStatus;
        
        if(head == null)
        {
            head = tempLink;
            curLink = head;
            last = head;
        }
        
        else
        {
            last.next = tempLink;
            tempLink.prev = last;
            last = tempLink;
        }
    }
    
    public double getMtRight(int index)
    {
        if(index == -1)
        {
            return curLink.dX;
        }
        
        else 
        {
            link tempLink;
        
            tempLink = getObject(index);
            return tempLink.dX; 
        }
    }
    
    public double getMtLeft(int index)
    {
        if(index == -1)
        {
            return curLink.dY;
        }
        
        else 
        {
            link tempLink;
        
            tempLink = getObject(index);
            return tempLink.dY; 
        }
    }
    
    public double getTmr(int index)
    {
        if(index == -1)
        {
            return curLink.dTmr;
        }
        
        else 
        {
            link tempLink;
        
            tempLink = getObject(index);
            return tempLink.dTmr; 
        }
    }
    
    public boolean getTurret(int index)
    {
        if(index == -1)
        {
            return curLink.bTurret;
        }
        
        else 
        {
            link tempLink;
        
            tempLink = getObject(index);
            return tempLink.bTurret; 
        }
    }
    public int getIter()
    {
        return  iNextIndex;
    }
    
    public void getNext()
    {
        if(curLink.next != null)
        {
            curLink = curLink.next;
            iNextIndex++;
        }
    }
        
    public void reset()
    {
        curLink = head;
        iNextIndex = 0;
    }
    
    public boolean isEmpty()
    {
        if(size == 0)
            return true;
        
        else
            return false;
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
        curLink = null;
        iNextIndex = 0;
        size = 0;
    }
    
    public int size()
    {
        return size;
    }       
}
