package com.joestelmach.natty;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.TimeZone;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Runs the parser through the various date formats 
 * 
 * @author Joe Stelmach
 */
public class PortugueseDateTest extends AbstractTest {
  @BeforeClass
  public static void oneTime() {
    Locale.setDefault(Locale.US);
    TimeZone.setDefault(TimeZone.getTimeZone("US/Eastern"));
    initCalendarAndParser();
  }
  
  @Test
  public void testFormal() {
//    validateDate("1978-01-28", 1, 28, 1978);
//    validateDate("2009-10-10", 10, 10, 2009);
//    validateDate("1980-1-2", 1, 2, 1980);
//    validateDate("12/12/12", 12, 12, 2012);
//    validateDate("3/4", 3, 4, Calendar.getInstance().get(Calendar.YEAR));
//    validateDate("sun, 11/21/2010", 11, 21, 2010);
//    validateDate("in october 2006", 10, 1, 2006);
//    validateDate("feb 1979", 2, 1, 1979);
//    validateDate("jan '80", 1, 1, 1980);
	  
	  validateDate("23/1/2012", 1, 23, 2012);
	  validateDate("23-03-2010", 3, 23, 2010);
	  validateDate("12/05/12", 5, 12, 2012);
	  validateDate("12/6", 6, 12, Calendar.getInstance().get(Calendar.YEAR));
	  validateDate("no dia 12/01/10, eu fui à praia", 1, 12, 2010);
	  validateDate("15/02/2010", 2, 15, 2010);
	  validateDate("1 de janeiro de 1980", 1, 1, 1980);
	  validateDate("Noticia salva em 1 de março de 14", 3,1,2014);
    
  }
  
  @Test
  public void testRelaxed() {
    
  }
  
  public static void main(String[] args) {
    ConsoleHandler handler = new ConsoleHandler();
    handler.setLevel(Level.ALL);
    Logger logger = Logger.getLogger("com.joestelmach.natty");
    logger.setLevel(Level.FINEST);
    logger.addHandler(handler);
    
    String value = "next fall";

    Parser parser = new Parser();
    List<DateGroup> groups = parser.parse(value);
    for(DateGroup group:groups) {
      System.out.println(value);
      System.out.println(group.getSyntaxTree().toStringTree());
      System.out.println("line: " + group.getLine() + ", column: " + group.getPosition());
      System.out.println(group.getText());
      System.out.println(group.getDates());
      System.out.println("is time inferred: " + group.isTimeInferred());
      System.out.println("is recurring: " + group.isRecurring());
      System.out.println("recurs until: " + group.getRecursUntil());
      
      System.out.println("\n** Parse Locations **");
      for(Entry<String, List<ParseLocation>> entry:group.getParseLocations().entrySet()) {
        for(ParseLocation loc:entry.getValue()) {
          System.out.println(loc.getRuleName());
        }
      }
      
      List<ParseLocation> conjunctionLocations = group.getParseLocations().get("conjunction");
      if(conjunctionLocations != null) {
        System.out.print("\nconjunctions: ");
        for(ParseLocation location:conjunctionLocations) {
          System.out.print(location.getText() + " ");
        }
      }
      System.out.print("\n\n");
    }
  }
}
