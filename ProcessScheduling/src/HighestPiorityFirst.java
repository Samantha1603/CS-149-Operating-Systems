
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jagrut
 */
public class HighestPiorityFirst 
{
ArrayList<Process> processes;
   HighestPiorityFirst(ArrayList<Process> p) 
   {
      processes = p;
   }

   ArrayList<Process> hpfnp() 
   {
      ArrayList<Process> result = new ArrayList<Process>();
      ArrayList<Process> priority1 = new ArrayList<Process>();
      ArrayList<Process> priority2 = new ArrayList<Process>();
      ArrayList<Process> priority3 = new ArrayList<Process>();
      ArrayList<Process> priority4 = new ArrayList<Process>();
      for(Process p: processes)
      {
         if(p.getPriority()==1)
         {
            priority1.add(p); 
         }
         if(p.getPriority()==2)
         {
            priority2.add(p); 
         }
         if(p.getPriority()==3)
         {
            priority3.add(p); 
         }
         if(p.getPriority()==4)
         {
            priority4.add(p); 
         }
      }
         
		boolean isFull = false;
		int counter = 0, runTime = 0;
		int Total_Processes = processes.size();
		Process p = null;
      		
		while(!isFull && counter<priority4.size()) {
			
         p = priority4.get(counter);
         
         while(p.getRunTime() > 0)
         {
            result.add(p);
            p.setRunTime(p.getRunTime()-1);
            runTime ++; 
         }
         counter++;
			
			if(runTime >= 100) {
				isFull = true;
			}
		}
      
      while(!isFull && counter < priority4.size() + priority3.size()) {
			
         p = priority3.get(counter-priority4.size());
         
         while(p.getRunTime() > 0)
         {
            result.add(p);
            p.setRunTime(p.getRunTime()-1);
            runTime ++; 
         }
         counter++;
			
			if(runTime >= 100) {
				isFull = true;
			}
		}
      
      while(!isFull && counter < priority4.size() + priority3.size() + priority2.size()) {
			
         p = priority2.get(counter-priority4.size()-priority3.size());
         
         while(p.getRunTime() > 0)
         {
            result.add(p);
            p.setRunTime(p.getRunTime()-1);
            runTime ++; 
         }
         counter++;
			
			if(runTime >= 100) {
				isFull = true;
			}
		}
      
      while(!isFull && counter < priority4.size() + priority3.size() + priority2.size() + priority1.size()) {
			
         p = priority1.get(counter-priority4.size()-priority3.size()-priority2.size());
         
         while(p.getRunTime() > 0)
         {
            result.add(p);
            p.setRunTime(p.getRunTime()-1);
            runTime ++; 
         }
         counter++;
			
			if(runTime >= 100) {
				isFull = true;
			}
		}
      
      System.out.println("Total processes arrived = "+Total_Processes);
		System.out.println("Total processes served = "+(counter));
		System.out.println("Total processes runtime = "+runTime);
		System.out.println("\nTotal runtime = "+runTime);
		System.out.println("Last Process served was "+ p.getName()+".\n");
      
      return result;
   }

   ArrayList<Process> hpfp() 
   {
      ArrayList<Process> result = new ArrayList<Process>();
      ArrayList<Process> priority1 = new ArrayList<Process>();
      ArrayList<Process> priority2 = new ArrayList<Process>();
      ArrayList<Process> priority3 = new ArrayList<Process>();
      ArrayList<Process> priority4 = new ArrayList<Process>();
      for(Process p: processes)
      {
         if(p.getPriority()==1)
         {
            priority1.add(p); 
         }
         if(p.getPriority()==2)
         {
            priority2.add(p); 
         }
         if(p.getPriority()==3)
         {
            priority3.add(p); 
         }
         if(p.getPriority()==4)
         {
            priority4.add(p); 
         }
      }
         
		boolean isFull = false;
		int counter = 0, runTime = 0;
		int Total_Processes = processes.size();
      Process p = null; 
    
		while(!isFull && counter<priority4.size()) {
         int tempCounter = 0; 
         while(priority4.size()> 0 && !isFull)
         {
            result.add(priority4.get(tempCounter));
            priority4.get(tempCounter).setRunTime(priority4.get(tempCounter).getRunTime()-1);
            runTime ++; 
            
            if(priority4.get(tempCounter).getRunTime()<=0)
            {
               priority4.remove(tempCounter);
               counter++;
            }
            
            if(runTime >= 100) {
               p = priority4.get(tempCounter);
               isFull = true;
         	}
            
            tempCounter++;
            if(tempCounter >= priority4.size()-1) tempCounter = 0; 
            
         }		
      }
      
      

      while(!isFull && counter<(counter + priority3.size())) {
         int tempCounter = 0; 
         while(priority3.size()> 0 && !isFull)
         {
            result.add(priority3.get(tempCounter));
            priority3.get(tempCounter).setRunTime(priority3.get(tempCounter).getRunTime()-1);
            runTime ++; 
            
            if(priority3.get(tempCounter).getRunTime()<=0)
            {
               priority3.remove(tempCounter);
               counter++;
            }
            
            if(runTime >= 100) {
               p = priority3.get(tempCounter);
               isFull = true;
         	}
            
            tempCounter++;
            if(tempCounter >= priority3.size()-1) tempCounter = 0; 
            
         }		
         
      }
      
      
      
      while(!isFull && counter<counter + priority2.size()) {
         int tempCounter = 0; 
         while(priority2.size()> 0 && !isFull)
         {
            result.add(priority2.get(tempCounter));
            priority2.get(tempCounter).setRunTime(priority2.get(tempCounter).getRunTime()-1);
            runTime ++; 
            
            if(priority2.get(tempCounter).getRunTime()<=0)
            {
               priority2.remove(tempCounter);
               counter++;
            }
            
            if(runTime >= 100) {
               p = priority2.get(tempCounter);
               isFull = true;
         	}
            
            tempCounter++;
            if(tempCounter >= priority2.size()-1) tempCounter = 0; 
            
         }		
      }
      
     
      
      while(!isFull && counter<counter + priority1.size()) {
         int tempCounter = 0; 
         while(priority1.size()> 0 && !isFull)
         {
            
            result.add(priority1.get(tempCounter));
            priority1.get(tempCounter).setRunTime(priority1.get(tempCounter).getRunTime()-1);
            runTime ++; 
            
            if(priority1.get(tempCounter).getRunTime()<=0)
            {
               priority1.remove(tempCounter);
               counter++;
            }
            
            if(runTime >= 100) {
               p = priority1.get(tempCounter);
               isFull = true;
         	}
            
            tempCounter++;
            if(tempCounter >= priority1.size()-1) tempCounter = 0; 
            
         }	
      }
      

      
      System.out.println("Total processes arrived = "+Total_Processes);
		System.out.println("Total processes served = "+(counter));
		System.out.println("Total processes runtime = "+runTime);
		System.out.println("\nTotal runtime = "+runTime);
		System.out.println("Last Process served was "+ result.get(result.size()-1).getName() +".\n");
            
      return result;
   }
   
   
}