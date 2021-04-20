package com;
import model.Researcher; 
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 

//For JSON
import com.google.gson.*; 

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/Researcher") 
public class ResearcherService 
{ 
 Researcher itemObj = new Researcher(); 
@GET
@Path("/") 
@Produces(MediaType.TEXT_HTML) 
public String readItems() 
 { 
 return itemObj.readItems(); 
 } 
}
