package com.joestelmach.natty;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joestelmach.natty.DateGroup;
import com.joestelmach.natty.ParseLocation;
import com.joestelmach.natty.Parser;


public class Exemplo {

	public static void main(String[] args) {
		Parser parser = new Parser();
		List<DateGroup> groups = parser.parse("14 de janeiro de 1989, e 12 de junho de 2014");
		for(DateGroup group:groups) {
		    List<Date> dates = group.getDates();
		    int line = group.getLine();
		    int column = group.getPosition();
		    String matchingValue = group.getText();
			String syntaxTree = group.getSyntaxTree().toStringTree();
			Map<String, List<ParseLocation>> parseMap = group.getParseLocations();
			boolean isRecurreing = group.isRecurring();
			Date recursUntil = group.getRecursUntil();
			
			for (Date foundDate: dates){
				System.out.println(foundDate);
			}
		}	
	}

}