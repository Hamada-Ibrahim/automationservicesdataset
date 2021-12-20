package hamada.Random.ServicePolicy_Generation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Random;

import com.google.gson.stream.JsonWriter;

public class Transformation 
      {
	   public static void main( String[] args )
             {
    	      String IntDevices [] = {"IDevice1", "IDevice2", "IDevice3", "IDevice4", "IDevice5", 
					  				  "IDevice6", "IDevice7", "IDevice8", "IDevice9", "IDevice10", 
					  				  "IDevice11", "IDevice12", "IDevice13", "IDevice14", "IDevice15", 
					  				  "IDevice16", "IDevice17", "IDevice18", "IDevice19", "IDevice20"};
    	      
    	      String BoolDevices [] = {"BDevice1", "BDevice2", "BDevice3", "BDevice4", "BDevice5", 
    	    		  				   "BDevice6", "BDevice7", "BDevice8", "BDevice9", "BDevice10", 
    	    		  				   "BDevice11", "BDevice12", "BDevice13", "BDevice14", "BDevice15", 
    	    		  				   "BDevice16", "BDevice17", "BDevice18", "BDevice19", "BDevice20"};

    	      String locations [] = {"Hall1", "Hall2", "Hall3", "Hall4", "Hall5",
		  				 			 "Lab1", "Lab2", "Lab3", "Lab4", "Lab5",
		  				 			 "Office1", "Office2", "Office3", "Office4", "Office5"};
		      

		      String repeatEvery [] = {"Sa", "Su", "Mo", "Tu", "We", "Th", "Fr"};

		  	  String IntDevices2 [] = {"", "IDevice1", "IDevice2", "IDevice3", "IDevice4", "IDevice5", 
	  				  "IDevice6", "IDevice7", "IDevice8", "IDevice9", "IDevice10", 
	  				  "IDevice11", "IDevice12", "IDevice13", "IDevice14", "IDevice15", 
	  				  "IDevice16", "IDevice17", "IDevice18", "IDevice19", "IDevice20"};

		      String operators [] = {"=","<", ">", "<=", ">="};
		      String Maxoperators [] = {"<", "<="};
		      String Minoperators [] = {">", ">="};
		      String logicaloperators [] = {"&","|"};
		      String Negation [] = {"!",""};
			  String signs [] = {"True", "False"};
			  String arithmatic [] = {"+","-"};
			  String type [] = {"I", "B"};
			  String condtype [] = {"Function", "Boolean", "Integer", "Variable"};
			  String condtype2 [] = {"Max", "Function", "Boolean", "Integer", "Variable"};
			  String condtype3 [] = {"Normal", "OP", "S"};
    	      HashSet<String> Istr = new HashSet<String>();
    	      HashSet<String> Bstr = new HashSet<String>();
    	      HashSet<String> until = new HashSet<String>();
    	      HashSet<String> addinNormal = new HashSet<String>();
    	      int total = 0;
    	      //generate random services
		      for (int numser = 1; numser <= 1; numser++)
		         {
		    	  try 
	    	        {
		    		 JsonWriter jsonWriter = null;
		    		 File sigRule = new File("/service400"+numser+".json");
	        	     jsonWriter = new JsonWriter(new FileWriter(sigRule));
	    		     jsonWriter.setIndent("  ");
	    		     jsonWriter.beginObject();
	    		     jsonWriter.name("ServiceID");
	        		 jsonWriter.value(numser);
 			         jsonWriter.name("UserID");
	    		     jsonWriter.value(randBetween(1,50));
	        		 jsonWriter.name("UserPriority");
	        		 jsonWriter.value(randBetween(1,5));  
	        		 jsonWriter.name("StartDate");
	        		 int month1 = randBetween(1,12);
	        		 int day1 = randBetween(1,30);
	        		 jsonWriter.value("2018-"+month1+"-"+day1);
	        		 jsonWriter.name("Time");
	        		 jsonWriter.value(randBetween(1,24)+"-"+randBetween(1,60)+"-00");
	        		 jsonWriter.name("Period");
	        		 jsonWriter.value(randBetween(1,24));
	        		 jsonWriter.name("RepeatEvery");
	        		 jsonWriter.beginArray();
			    	 int every = randBetween(1,3);
			    	 for (int i=0;i<every;i++)
			    		 {
			    		  String r = repeatEvery[new Random().nextInt(repeatEvery.length)];
			    		  while (until.contains(r)) 
		                       r = repeatEvery[new Random().nextInt(repeatEvery.length)];
		                  until.add(r);
			    		  jsonWriter.value(r);
			    		 }
			    	 until = new HashSet<String>();
			    	 jsonWriter.endArray();
			    	 jsonWriter.name("EndDate");
			    	 int month2 = randBetween(1,12);
			    	 int day2 = randBetween(1,30);
			    	 jsonWriter.value("2018-"+month2+"-"+day2);
			    	 jsonWriter.name("Rules");
			    	 jsonWriter.beginArray();
			    	 int numrules = randBetween(3,6);
			    	 total += numrules; 
	        		 for (int rule=1; rule<=numrules; rule++) 
		                {
	        			 String Loc2 =  locations[new Random().nextInt(locations.length)];
	        			 jsonWriter.beginObject();
	      			     jsonWriter.name("Ser_ID");
	      			     jsonWriter.value(numser);
	      			     jsonWriter.name("R_ID");
	      			     jsonWriter.value(rule);
	      			     jsonWriter.name("Priority");
	      			     jsonWriter.value(randBetween(1,5));
	      			     jsonWriter.name("RLoc");
	      			     jsonWriter.value(Loc2);
	      			     int numcond = randBetween(1,4);
	      			     jsonWriter.name("conditionGroup");
	      			     jsonWriter.beginArray();
	      			     for (int cond = 1; cond <= numcond; cond++)
	      			        {
	      			    	 String ss = condtype2[new Random().nextInt(condtype2.length)];
	      			    	 if (ss.equals("Max"))
		      			       {
	      			    		jsonWriter.beginObject(); 
	      			    		jsonWriter.name("DeviceName"); 
	      			    	    String n = IntDevices[new Random().nextInt(IntDevices.length)];
	      			    	    jsonWriter.value(Loc2+"_"+n);
			      			    int n1 = randBetween(10,40);
			      			    int n2 = randBetween(10,40);
			      			    if (n1 >= n2)
			      			      {
			      			       jsonWriter.name("Min").beginArray();
			      			       jsonWriter.value(""+n2);
			      			       jsonWriter.value(Minoperators[new Random().nextInt(Minoperators.length)]);
			      			       jsonWriter.endArray();
			      			       jsonWriter.name("Max").beginArray();
			      			       jsonWriter.value(""+n1);
			      			       jsonWriter.value(Maxoperators[new Random().nextInt(Maxoperators.length)]);
			      			       jsonWriter.endArray();
			      			      }
			      			    else
			      			      {
				      			   jsonWriter.name("Min").beginArray();
			      			       jsonWriter.value(""+n1);
			      			       jsonWriter.value(Minoperators[new Random().nextInt(Minoperators.length)]);
			      			       jsonWriter.endArray();
			      			       jsonWriter.name("Max").beginArray();
			      			       jsonWriter.value(""+n2);
			      			       jsonWriter.value(Maxoperators[new Random().nextInt(Maxoperators.length)]);
			      			       jsonWriter.endArray();
			      			      }
			      			    jsonWriter.endObject();
			      			   }
	      			    	 else if (ss.equals("Boolean"))
	      			    	   {
	      			    		jsonWriter.beginObject();
	      			    		jsonWriter.name("DeviceName"); 
	      			    		String n = BoolDevices[new Random().nextInt(BoolDevices.length)]; 
	      			    		jsonWriter.value(Loc2+"_"+n);
	      			    		jsonWriter.name("value");
	      			    		jsonWriter.value(signs[new Random().nextInt(signs.length)]);
	      			    		jsonWriter.endObject();
	      			    	   }
	      			    	 else if (ss.equals("Integer"))
	      			    	   {
	      			    		jsonWriter.beginObject();
	      			    		jsonWriter.name("DeviceName"); 
	      			    		String n = IntDevices[new Random().nextInt(IntDevices.length)];
	      			    		jsonWriter.value(Loc2+"_"+n);
	      			    		jsonWriter.name("operation");
	      			    		jsonWriter.value(operators[new Random().nextInt(operators.length)]);
	      			    		jsonWriter.name("value");
	      			    		jsonWriter.value(""+randBetween(10,40));
	      			    		jsonWriter.endObject();
	      			    	   }
	      			    	 else if (ss.equals("Variable"))
	      			    	   {
	      			    		jsonWriter.beginObject();
	      			    		jsonWriter.name("DeviceName"); 
	      			    		String t = type[new Random().nextInt(type.length)];
	      			    		if (t.equals("B"))
		      			    	  {
		      			    	   String n = BoolDevices[new Random().nextInt(BoolDevices.length)]; 
		      			    	   jsonWriter.value(Loc2+"_"+n);
		      			    	   jsonWriter.name("operation");
		      			    	   jsonWriter.value("=");
		      			    	   jsonWriter.name("value");
		      			    	   String n2 = BoolDevices[new Random().nextInt(BoolDevices.length)]; 
		      			    	   jsonWriter.value(Loc2+"_"+n2);
		      			    	  }
	      			    		else if (t.equals("I"))
	      			    		  {
		      			    	   String n = IntDevices[new Random().nextInt(IntDevices.length)];
		      			    	   jsonWriter.value(Loc2+"_"+n);
		      			    	   jsonWriter.name("operation");
		      			    	   jsonWriter.value(operators[new Random().nextInt(operators.length)]);
		      			    	   jsonWriter.name("value");
		      			    	   String n2 = IntDevices[new Random().nextInt(IntDevices.length)];
		      			    	   jsonWriter.value(Loc2+"_"+n2);
	      			    		  }
	      			    		jsonWriter.endObject();
	      			    	   }
	      			    	 else if (ss.equals("Function"))
	      			    	   {
	      			    		jsonWriter.beginObject();
	      			    		String op1 = "", op2 = ""; 
	      			    		String n1 = IntDevices[new Random().nextInt(IntDevices.length)];
		      			    	String n2 = IntDevices2[new Random().nextInt(IntDevices2.length)];
		      			    	String n3 = IntDevices[new Random().nextInt(IntDevices.length)];
		      			    	String n4 = IntDevices2[new Random().nextInt(IntDevices2.length)];
		      			    	
		      			    	if (!n2.equals(""))
		      			    	  op1 = arithmatic[new Random().nextInt(arithmatic.length)];
		      			    	if (!n4.equals(""))
		      			    	  op2 = arithmatic[new Random().nextInt(arithmatic.length)];
		      			    	
		      			    	jsonWriter.name("function").beginObject();
		      			    	jsonWriter.name("LHS").beginArray();
		      			    	jsonWriter.value(Loc2+"_"+n1);
		      			    	if (n2.equals(""))
		      			    	  jsonWriter.value(n2);
		      			    	else
		      			          jsonWriter.value(Loc2+"_"+n2);		
		      			    	jsonWriter.value(op1);
		      			    	jsonWriter.endArray();
		      			    	
		      			    	jsonWriter.name("RHS").beginArray();
		      			    	jsonWriter.value(Loc2+"_"+n3);
		      			    	if (n4.equals(""))
		      			     	  jsonWriter.value(n4);
		      			    	else
		      			    	  jsonWriter.value(Loc2+"_"+n4);	
		      			    	jsonWriter.value(op2);
		      			    	jsonWriter.endArray();
		      			    	jsonWriter.name("operator");
		      			    	jsonWriter.value(operators[new Random().nextInt(operators.length)]);
		      			    	jsonWriter.endObject();
		      			    	jsonWriter.endObject();
		      			       }
	      			    	 
	      			        }
	      			     jsonWriter.endArray();
	      			     int numact = randBetween(1,4);
		        	     jsonWriter.name("actionGroup");
	      			     jsonWriter.beginArray();
	      			     for (int act = 1; act <= numact; act++)
	      			        {
	      			    	 String ss = condtype[new Random().nextInt(condtype.length)];
	      			    	 if (ss.equals("Boolean"))
	      			    	   {
	      			    		jsonWriter.beginObject();
		      			    	jsonWriter.name("DeviceName"); 
	      			    		String n = BoolDevices[new Random().nextInt(BoolDevices.length)];
	      			    	    jsonWriter.value(Loc2+"_"+n);
	      			    		jsonWriter.name("value");
		      			    	jsonWriter.value(signs[new Random().nextInt(signs.length)]);
		      			    	jsonWriter.endObject();
	      			    	   }
	      			    	 else if (ss.equals("Integer"))
	      			    	   {
	      			    		jsonWriter.beginObject();
		      			    	jsonWriter.name("DeviceName"); 
	      			    		String n = IntDevices[new Random().nextInt(IntDevices.length)]; 
	      			            jsonWriter.value(Loc2+"_"+n);
	      			    		jsonWriter.name("value");
	      			    		jsonWriter.value(""+randBetween(10,40));
	      			    		jsonWriter.endObject();
	      			    	   }
	      			    	 else if (ss.equals("Variable"))
	      			    	   {
	      			    		jsonWriter.beginObject();
		      			    	jsonWriter.name("DeviceName"); 
		      			    	String t = type[new Random().nextInt(type.length)];
	      			    		if (t.equals("B"))
		      			    	  {
		      			    	   String n = BoolDevices[new Random().nextInt(BoolDevices.length)]; 
		      			    	   jsonWriter.value(Loc2+"_"+n);
		      			    	   jsonWriter.name("value");
		      			    	   String n2 = BoolDevices[new Random().nextInt(BoolDevices.length)]; 
		      			    	   jsonWriter.value(Loc2+"_"+n2);
		      			    	  }
	      			    		else if (t.equals("I"))
	      			    		  {
		      			    	   String n = IntDevices[new Random().nextInt(IntDevices.length)];
		      			    	   jsonWriter.value(Loc2+"_"+n);
		      			    	   jsonWriter.name("value");
		      			    	   String n2 = IntDevices[new Random().nextInt(IntDevices.length)];
		      			    	   jsonWriter.value(Loc2+"_"+n2);
	      			    		  }  
	      			    		 jsonWriter.endObject();
	      			    	   }
	      			    	 else if (ss.equals("Function"))
	      			    	   {
	      			    		jsonWriter.beginObject();
		      			   		String op1 = "", op2 = ""; 
	      			    		String n1 = IntDevices[new Random().nextInt(IntDevices.length)];
		      			    	String n2 = IntDevices2[new Random().nextInt(IntDevices2.length)];
		      			    	String n3 = IntDevices[new Random().nextInt(IntDevices.length)];
		      			    	String n4 = IntDevices2[new Random().nextInt(IntDevices2.length)];
		      			    	
		      			    	if (!n2.equals(""))
		      			    	  op1 = arithmatic[new Random().nextInt(arithmatic.length)];
		      			    	if (!n4.equals(""))
		      			    	  op2 = arithmatic[new Random().nextInt(arithmatic.length)];
		      			    	
		      			    	jsonWriter.name("function").beginObject();
		      			    	jsonWriter.name("LHS").beginArray();
		      			    	jsonWriter.value(Loc2+"_"+n1);
		      			    	if (n2.equals(""))
		      			    	  jsonWriter.value(n2);
		      			    	else
		      			    	  jsonWriter.value(Loc2+"_"+n2);	
		      			    	jsonWriter.value(op1);
		      			    	jsonWriter.endArray();
		      			    	
		      			    	jsonWriter.name("RHS").beginArray();
		      			    	jsonWriter.value(Loc2+"_"+n3);
		      			    	if (n4.equals(""))
		      			    	  jsonWriter.value(n4);
		      			    	else
		      			          jsonWriter.value(Loc2+"_"+n4);		
		      			    	jsonWriter.value(op2);
		      			    	jsonWriter.endArray();
		      			    	jsonWriter.name("operator");
		      			    	jsonWriter.value("=");
		      			    	jsonWriter.endObject(); 
		      			    	 jsonWriter.endObject();
	      			    	   }
	      			    	}
		      			 jsonWriter.endArray();
	      			     jsonWriter.endObject();
		                }
	        		 jsonWriter.endArray();
			    	 jsonWriter.endObject();//end of file
	        		 jsonWriter.close();
	    	        }  
	              catch (IOException e) {}
	              finally{try {jsonWriter.close();} catch (IOException e) {}}
		         }
		      
		      for (int numpol = 1; numpol <= 10; numpol++)
		         {
		    	  try 
	    	        {
		    		 File sigPolicy = new File("/policy"+numpol+".json"); 
	    		     jsonWriter = new JsonWriter(new FileWriter(sigPolicy));
	    		     jsonWriter.setIndent("  ");
	    		     jsonWriter.beginObject();
	    		     jsonWriter.name("Policy_ID");
	    		     jsonWriter.value(numpol);
	    		     jsonWriter.name("UserID");
	    		     jsonWriter.value(randBetween(1,50));
	    		     jsonWriter.name("Constraints");
	    		     jsonWriter.beginArray();
			    	 int numrules = randBetween(2,5);
			    	 
			    	 for (int i=1; i<=numrules; i++) 
		                {
			    		 String Loc2 =  locations[new Random().nextInt(locations.length)];
	        			 jsonWriter.beginObject();
	      			     String ss = condtype3[new Random().nextInt(condtype3.length)];
	      			     jsonWriter.name("P_ID");
	   	      			 jsonWriter.value(i); 
	   	      			 jsonWriter.name("PLoc");
	   	      			 jsonWriter.value(Loc2);
	      			     if (ss.equals("Normal"))
	      			       {
	      			    	jsonWriter.name("DeviceName");
		      			    String n = IntDevices[new Random().nextInt(IntDevices.length)]; 
		      			    jsonWriter.value(Loc2+"_"+n);
		      			    int n1 = randBetween(10,40);
		      			    int n2 = randBetween(10,40);
		      			    if (n1 > n2)
		      			      {
		      			       jsonWriter.name("Min").beginArray();
		      			       jsonWriter.value(""+n2);
		      			       jsonWriter.value(Minoperators[new Random().nextInt(Minoperators.length)]);
		      			       jsonWriter.endArray();
		      			       jsonWriter.name("Max").beginArray();
		      			       jsonWriter.value(""+n1);
		      			       jsonWriter.value(Maxoperators[new Random().nextInt(Maxoperators.length)]);
		      			       jsonWriter.endArray();
		      			      }
		      			    else
		      			      {
			      			   jsonWriter.name("Min").beginArray();
		      			       jsonWriter.value(""+n1);
		      			       jsonWriter.value(Minoperators[new Random().nextInt(Minoperators.length)]);
		      			       jsonWriter.endArray();
			      			   jsonWriter.name("Max").beginArray();
		      			       jsonWriter.value(""+n2);
		      			       jsonWriter.value(Maxoperators[new Random().nextInt(Maxoperators.length)]);
		      			       jsonWriter.endArray();
		      			      }
	      			       }
	      			     else if (ss.equals("OP"))
	      			       {
	      			    	jsonWriter.name("operatorGroup").beginObject();
	      			    	jsonWriter.name("deviceGroup").beginArray();
	      			    	for (int y=0;y<2; y++)
	      			    	   {
						        jsonWriter.beginObject();
						        if (type[new Random().nextInt(type.length)].equals("B"))
  			    	              {
						           jsonWriter.name("DeviceName");
						           String n = BoolDevices[new Random().nextInt(BoolDevices.length)];
						           jsonWriter.value(Loc2+"_"+n);
						           jsonWriter.name("value");
						           jsonWriter.value(signs[new Random().nextInt(signs.length)]);
						          }
						        else
						          {	
						           jsonWriter.name("DeviceName");
						           String n = IntDevices[new Random().nextInt(IntDevices.length)];
						           jsonWriter.value(Loc2+"_"+n);
						           jsonWriter.name("operation");
						           jsonWriter.value(operators[new Random().nextInt(operators.length)]);
						           jsonWriter.name("value");
						           jsonWriter.value(""+randBetween(10,40));
						           String neg = Negation[new Random().nextInt(Negation.length)];
						           if (!neg.equals(""))
						             {
						        	  jsonWriter.name("negative");
						        	  jsonWriter.value(neg);
						             }
						          }
						        jsonWriter.endObject();
						       }
	      			    	jsonWriter.endArray();
	      			    	jsonWriter.name("Operator");
	      			    	jsonWriter.value(logicaloperators[new Random().nextInt(logicaloperators.length)]);
	      			    	String neg = Negation[new Random().nextInt(Negation.length)];
	      			    	if (!neg.equals(""))
	      			    	  {
	      			    	   jsonWriter.name("Negation");
	      			    	   jsonWriter.value(neg);
	      			    	  }
	      			    	jsonWriter.endObject();
	      			       }
	      			     else if (ss.equals("S"))
	      			       {
	      			    	jsonWriter.name("DeviceName");
		      			    if (type[new Random().nextInt(type.length)].equals("B"))
    			    	      {
    			    		   String n = BoolDevices[new Random().nextInt(BoolDevices.length)]; 
    			    		   jsonWriter.value(Loc2+"_"+n);
    			    		   jsonWriter.name("value");
    			    		   jsonWriter.value(signs[new Random().nextInt(signs.length)]);
    			    	      }
    			    	    else
    			    	      {
    			    	       String n = IntDevices[new Random().nextInt(IntDevices.length)];
    			    	       jsonWriter.value(Loc2+"_"+n);
    			    	       jsonWriter.name("operation");
    			    	       jsonWriter.value(operators[new Random().nextInt(operators.length)]);
    			    	       jsonWriter.name("value");
    			    	       jsonWriter.value(""+randBetween(10,40));
    			    	      } 
	      			       }
	      			     jsonWriter.endObject();
	      			    }
			    	 jsonWriter.endArray();
			    	 jsonWriter.endObject();
			    	 jsonWriter.close();
			    	}  
		    	  catch (IOException e) {}
		    	  finally{try {jsonWriter.close();} catch (IOException e) {}}
		         }
             }
       public static int randBetween(int start, int end) 
             {
    	      return start + (int)Math.round(Math.random() * (end - start));
             }
}
