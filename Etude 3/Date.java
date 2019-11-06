/**
 * This program checks users input dates and converts it to a date
 * of one format as well as checking if the date entered in valid or not.
 * @author Sean Kim
 */

import java.io.*;
import java.util.*;
import java.text.*;
import java.time.*;

/** Class Date which checks user input dates and validates the dates and converts
 *  to one consistent format.
 */
public class Date{
  /**
   * Main method consisting of validation checks and date formating.
   * @param args Array of Strings.
   */
  public static void main(String[] args) throws Exception{
    //Users entered date.
    String enteredDate = null;
    //Converted date.
    java.util.Date dateConv = null;
    //Checks if there was an error during the conversion of date.
    boolean error = false;
    //Sets minimum and maximum dates allowed to be entered by user.
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    java.util.Date dateMin = sdf.parse("01/01/1753");
    java.util.Date dateMax = sdf.parse("31/12/3000");
    Scanner sc = new Scanner(System.in);
    //Array of accepted date formats.
    List<String> dateFormats = Arrays.asList("d-M-y", "d/M/y", "d M y", "dd/MMM/yy", "dd-MMM-yy", "dd MMM yy");
    System.out.println("Please enter a date: ");

    // While loop scans user input until there is no input.
    while(sc.hasNextLine()){
      enteredDate = sc.nextLine();
      //Trys to parse entered date into all formats and catches error if no format can be used.
      for(String dateFormat : dateFormats){
        try{
          dateConv = new SimpleDateFormat(dateFormat).parse(enteredDate);
          error = false;
          break;
        } catch (Exception e){
          error = true;
        }
      }

      //If entered date caused error during formating, display error.
      if(error){
        System.out.print(enteredDate);
        System.out.println(" - INVALID: Please enter the correct date in forms d/m/y, d-m-y, or d m y.");
      }

      //If date entered is correct in format but below minimum date, display error.
      else if(dateConv.compareTo(dateMin) < 0){
        System.out.print(enteredDate);
        System.out.println(" - INVALID: Year out of range. Please enter a date greater than 1/1/1753.");
      }

      //If date entered is correct in format but above maximum date, display error.
      else if(dateConv.compareTo(dateMax) > 0){
        System.out.print(enteredDate);
        System.out.println(" - INVALID: Year out of range. Please enter a date less than 31/12/3000.");
      }

      else{
        //If date parse passes.
        if(dateConv != null){
          int day = 0;
          String days = null;
          int check = 0;
          String month = null;
          int year = 0;
          int count = 0;
          int i;
          //Splits input if space seperated.
          if((enteredDate.indexOf(' ') >= 0) && (enteredDate.indexOf('-') < 0) && (enteredDate.indexOf('/') < 0)){
            for(i = 0; i < enteredDate.length(); i++){
              if(enteredDate.charAt(i) == ' '){
                count++;
              }
            }

            if(count <= 2){
              String[] data = enteredDate.split(" ", 3);
              if(data[0].length() <= 2){
                day = Integer.parseInt(data[0]);
                if(day <= 0){
                  check = 6;
                }
              }
              else{
                check = 6;
              }

              month = data[1];
              
              //Only parses year if it is integer, otherwise displays error message.
              try{
                year = Integer.parseInt(data[2]);
              }
              catch(NumberFormatException e){
                check = 4;
              }
            }
            else{
              check = 3;
            }
          }
          //Splits input if "-" seperated.
          else if((enteredDate.indexOf('-') >= 0) && (enteredDate.indexOf(' ') < 0) && (enteredDate.indexOf('/') < 0)){
            for(i = 0; i < enteredDate.length(); i++){
              if(enteredDate.charAt(i) == '-'){
                count++;
              }
            }

            if(count <= 2){
              String[] data = enteredDate.split("-", 3);

              if(data[0].length() <= 2){
                day = Integer.parseInt(data[0]);
                if(day <= 0){
                  check = 6;
                }
              }
              else{
                check = 6;
              }

              month = data[1];

              //Only parses year if it is integer, otherwise displays error message.
              try{
                year = Integer.parseInt(data[2]);
              }
              catch(NumberFormatException e){
                check = 4;
              }
            }
            else{
              check = 3;
            }
          }
          //Splits input if "/" seperated.
          else if((enteredDate.indexOf('/') >= 0) && (enteredDate.indexOf(' ') < 0) && (enteredDate.indexOf('-') < 0)){
            for(i = 0; i < enteredDate.length(); i++){
              if(enteredDate.charAt(i) == '/'){
                count++;
              }
            }

            if(count <= 2){
              String[] data = enteredDate.split("/", 3);

              if(data[0].length() <= 2){
                day = Integer.parseInt(data[0]);
                if(day <= 0){
                  check = 6;
                }
              }
              else{
                check = 6;
              }

              month = data[1];

              //Only parses year if it is integer, otherwise displays error message.
              try{
                year = Integer.parseInt(data[2]);
              }
              catch(NumberFormatException e){
                check = 4;
              }
            }
            else{
              check = 3;
            }
          }
          //Error while parsing date.
          else{
            check = 5;
          }

          //If no errors.
          if(check < 3){
          //If year in 2 digit format, converts years correspondingly to 19XX or 20XX.
          if(year < 100){
            if(year < 50){
              year += 2000;
            }
            else if(year >=50){
              year += 1900;
            }
          }

          //Converts user input into months using 3 letters with first letter using capitals.
          if(month.equals("jan") || month.equals("01") || month.equals("1") || month.equals("JAN") || month.equals("january") || month.equals("January") || month.equals("JANUARY") || month.equals("Jan")){
            month = "Jan";
          }
          else if(month.equals("feb") || month.equals("02") || month.equals("2") || month.equals("FEB") || month.equals("february") || month.equals("February") || month.equals("FEBRUARY") || month.equals("Feb")){
            month = "Feb";
          }
          else if(month.equals("mar") || month.equals("03") || month.equals("3") || month.equals("MAR") || month.equals("march") || month.equals("March") || month.equals("MARCH") || month.equals("Mar")){
            month = "Mar";
          }
          else if(month.equals("apr") || month.equals("04") || month.equals("4") || month.equals("APR") || month.equals("april") || month.equals("April") || month.equals("APRIL") || month.equals("Apr")){
            month = "Apr";
          }
          else if(month.equals("may") || month.equals("05") || month.equals("5") || month.equals("MAY") || month.equals("May")){
            month = "May";
          }
          else if(month.equals("jun") || month.equals("06") || month.equals("6") || month.equals("JUN") || month.equals("june") || month.equals("June") || month.equals("JUNE") || month.equals("Jun")){
            month = "Jun";
          }
          else if(month.equals("jul") || month.equals("07") || month.equals("7") || month.equals("JUL") || month.equals("july") || month.equals("July") || month.equals("JULY") || month.equals("Jul")){
            month = "Jul";
          }
          else if(month.equals("aug") || month.equals("08") || month.equals("8") || month.equals("AUG") || month.equals("august") || month.equals("August") || month.equals("AUGUST") || month.equals("Aug")){
            month = "Aug";
          }
          else if(month.equals("sep") || month.equals("09") || month.equals("9") || month.equals("SEP") || month.equals("september") || month.equals("September") || month.equals("SEPTEMBER") || month.equals("Sep")){
            month = "Sep";
          }
          else if(month.equals("oct") || month.equals("10") || month.equals("OCT") || month.equals("october") || month.equals("October") || month.equals("OCTOBER") || month.equals("Oct")){
            month = "Oct";
          }
          else if(month.equals("nov") || month.equals("11") || month.equals("NOV") || month.equals("november") || month.equals("November") || month.equals("NOVEMBER") || month.equals("Nov")){
            month = "Nov";
          }
          else if(month.equals("dec") || month.equals("12") || month.equals("DEC") || month.equals("december") || month.equals("December") || month.equals("DECEMBER") || month.equals("Dec")){
            month = "Dec";
          }
          else {
            check = 7;
          }

          //Sets min and max days of months with 31 days.
          if(month.equals("Jan") || month.equals("Mar") || month.equals("May") || month.equals("Jul") || month.equals("Aug") || month.equals("Oct") || month.equals("Dec")){
            if(day <= 0 || day > 31){
              check = 1;
            }
          }
          //Sets min and max days of months with 30 days.
          else if(month.equals("Apr") || month.equals("Jun") || month.equals("Sep") || month.equals("Nov")){
            if(day <= 0 || day > 30){
              check = 9;
            }
          }

          //Converts days less than 10 with a 0 in front.
          if(day == 1){
            days = "01";
          }
          else if(day == 2){
            days = "02";
          }
          else if(day == 3){
            days = "03";
          }
          else if(day == 4){
            days = "04";
          }
          else if(day == 5){
            days = "05";
          }
          else if(day == 6){
            days = "06";
          }
          else if(day == 7){
            days = "07";
          }
          else if(day == 8){
            days = "08";
          }
          else if(day == 9){
            days = "09";
          }

          //Sets min and max day for February and calculates whether it is a leap year or not.
          if(month.equals("Feb")){
            if(day <= 0 || day > 29){
              check = 8;
            }

            if(day == 29){
              if((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))){}
              else{
                check = 2;
              }
            }
          }
        }

          //If days out of index for corresponding month, display error message.
          if(check == 1){
            System.out.println(day + " " + month + " " + year + " - INVALID: Day must be between 1-31 for this month.");
          }
          //If not leap year, display error message.
          else if(check == 2){
            System.out.println(day + " " + month + " " + year + " - INVALID: Year entered is not a leap year.");
          }
          //If date is not in correct form.
          else if(check == 3){
            System.out.print(enteredDate);
            System.out.println(" - INVALID: Date should only be seperated by two ' ', '-', or '/'. e.g.(dd/mm/yy)");
          }
          //If year has characters that are not numbers e.g. extra spaces, display error message.
          else if(check == 4){
            System.out.print(enteredDate);
            System.out.println(" - INVALID: Years should be of type integer. Please check that the year entered is correct.");
          }
          //If error during parsing, display error message.
          else if(check == 5){
            System.out.print(enteredDate);
            System.out.println(" - INVALID: Please enter the correct date. Please check that there are no extra spaces in the date.");
          }
          else if(check == 6){
            System.out.print(enteredDate);
            System.out.println(" - INVALID: Please enter a day between 1-31 for the corresponding month in d or dd format.");
          }
          else if(check == 7){
            System.out.print(enteredDate);
            System.out.println(" - INVALID: Please enter a month between 1-12 in m, mm, or mmm format e.g.( Jan, January, 01, 1).");
          }
          else if(check == 8){
            System.out.print(enteredDate);
            System.out.println(" - INVALID: Day must be between 1-29 for February.");
          }
          else if(check == 9){
            System.out.print(enteredDate);
            System.out.println(" - INVALID: Day must be between 1-30 for this month.");
          }
          //else display the date in correct format.
          else{
            if(day < 10){
              System.out.println(days + " " + month + " " + year);
            }
            else{
              System.out.println(day + " " + month + " " + year);
            }
          }
        }
      }
    }
  }
}
